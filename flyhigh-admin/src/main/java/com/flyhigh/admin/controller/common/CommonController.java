package com.flyhigh.admin.controller.common;

import cn.hutool.core.io.FileUtil;
import com.flyhigh.common.config.FlyhighConfig;
import com.flyhigh.common.constant.Constants;
import com.flyhigh.common.core.controller.BaseController;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.utils.DateUtils;
import com.flyhigh.common.utils.StringUtils;
import com.flyhigh.common.utils.file.FileUploadUtils;
import com.flyhigh.common.utils.file.FileUtils;
import com.flyhigh.framework.config.ServerConfig;
import com.flyhigh.system.domain.UploadFile;
import com.flyhigh.system.service.IUploadFileService;
import com.flyhigh.workorder.service.ICommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@Api(value = "附件上传", tags = {"附件上传"})
@RestController
@RequestMapping("/common")
public class CommonController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    private static final String FILE_DELIMETER = ",";

    @Autowired
    private IUploadFileService uploadFileService;

    @Value("${web.returnUrl}")
    private String returnUrl;

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete   是否删除
     */
    @ApiOperation("通用下载请求")
    @GetMapping("/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response) {
        try {
            if (!FileUtils.checkAllowDownload(fileName)) {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = FlyhighConfig.getDownloadPath() + fileName;
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete) {
                FileUtils.deleteFile(filePath);
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求（单个）
     *
     * @param file     文件对象
     * @param fileType 文件类型
     * @return
     */
    @ApiOperation("通用上传请求（单个）")
    @PostMapping("/upload")
    public AjaxResult uploadFile(@RequestParam("file") MultipartFile file, String fileType, HttpServletRequest request) {
        try {
            fileType = StringUtils.isNotBlank(fileType) ? fileType.replaceAll(FILE_DELIMETER, "") : fileType;
            String domain = ServerConfig.getDomain(request);

            // 上传文件路径
            String filePath = FlyhighConfig.getUploadPath();
            if (StringUtils.isNotBlank(fileType)) {
                filePath += "/" + fileType;
            }
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = returnUrl + fileName;
            UploadFile uploadFile = new UploadFile();
            uploadFile.setFileType(fileType);
            uploadFile.setFileName(FileUtil.getName(fileName));
            uploadFile.setFileSuffix(FileUtil.getSuffix(fileName));
            uploadFile.setOriginalName(file.getOriginalFilename());
            uploadFile.setUrl(url);
            uploadFile.setCreateTime(DateUtils.getNowDate());
            uploadFile.setCreateBy(getUsername());
            uploadFileService.insertUploadFile(uploadFile);

            // 回显拼接全路径：domain + url
            uploadFile.setUrl(domain + url);
            // 短链接
            uploadFile.setShortUrl(url);

            return AjaxResult.success(uploadFile);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 通用上传请求（多个）
     *
     * @param files    文件对象
     * @param fileType 文件类型
     * @return
     */
    @ApiOperation("通用上传请求（多个）")
    @PostMapping("/uploads")
    public AjaxResult uploadFiles(@RequestParam("file") List<MultipartFile> files, String fileType, HttpServletRequest request) {
        try {
            fileType = StringUtils.isNotBlank(fileType) ? fileType.replaceAll(FILE_DELIMETER, "") : fileType;
            String domain = ServerConfig.getDomain(request);

            // 上传文件路径
            String filePath = FlyhighConfig.getUploadPath();
            String username = getUsername();
            List<UploadFile> list = new ArrayList<>();
            for (MultipartFile file : files) {
                // 上传并返回新文件名称
                String fileName = FileUploadUtils.upload(filePath, file);
                String url = returnUrl + fileName;
                UploadFile uploadFile = new UploadFile();
                uploadFile.setFileType(fileType);
                uploadFile.setFileName(FileUtil.getName(fileName));
                uploadFile.setFileSuffix(FileUtil.getSuffix(fileName));
                uploadFile.setOriginalName(file.getOriginalFilename());
                uploadFile.setUrl(url);
                uploadFile.setCreateTime(DateUtils.getNowDate());
                uploadFile.setCreateBy(username);
                uploadFileService.insertUploadFile(uploadFile);

                // 回显拼接全路径：domain + url
                uploadFile.setUrl(domain + url);
                // 短链接
                uploadFile.setShortUrl(url);

                list.add(uploadFile);
            }
            return AjaxResult.success(list);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 本地资源通用下载
     */
    @ApiOperation("本地资源通用下载")
    @GetMapping("/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (!FileUtils.checkAllowDownload(resource)) {
                throw new Exception(StringUtils.format("资源文件({})非法，不允许下载。 ", resource));
            }
            // 本地资源路径
            String localPath = FlyhighConfig.getProfile();
            // 数据库资源地址
            String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
            // 下载名称
            String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 删除文件
     */
    @ApiOperation("删除文件")
    @DeleteMapping("/removeFile/{ids}")
    public AjaxResult<Boolean> removeFile(@PathVariable List<Long> ids) {
        return AjaxResult.success(uploadFileService.deleteUploadFileByIds(ids));
    }

}
