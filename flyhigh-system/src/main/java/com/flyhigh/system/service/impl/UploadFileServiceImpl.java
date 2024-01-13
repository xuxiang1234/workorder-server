package com.flyhigh.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.flyhigh.common.config.FlyhighConfig;
import com.flyhigh.common.constant.Constants;
import com.flyhigh.common.utils.DateUtils;
import com.flyhigh.common.utils.SecurityUtils;
import com.flyhigh.common.utils.StringUtils;
import com.flyhigh.common.exception.ErrorCodeConstants;
import com.flyhigh.common.exception.ServiceExceptionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.flyhigh.common.utils.file.FileUtils;
import com.flyhigh.system.domain.vo.UploadFileVO;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.flyhigh.system.domain.UploadFile;
import com.flyhigh.system.mapper.UploadFileMapper;
import com.flyhigh.system.service.IUploadFileService;

import java.util.List;
import java.util.Collection;
import java.util.Optional;

/**
 * 上传文件Service业务层处理
 *
 * @author flyhigh
 * @date 2022-12-29
 */
@Service
@Validated
public class UploadFileServiceImpl implements IUploadFileService {

    /**
     * 历史关联ID
     */
    public static final Long CLEAN_RELATION_ID = 0L;

    @Resource
    private UploadFileMapper uploadFileMapper;

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(UploadFile entity) {
        // 做一些数据校验,如唯一约束
    }

    /**
     * 校验是否存在
     */
    private void validateUploadFileExists(Long id) {
        if (uploadFileMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.SYSTEM_ERROR);
        }
    }

    /**
     * 查询上传文件
     *
     * @param id 上传文件主键
     * @return 上传文件
     */
    @Override
    public UploadFile queryById(Long id) {
        return uploadFileMapper.selectById(id);
    }

    /**
     * 查询上传文件列表
     *
     * @param uploadFile 上传文件
     * @return 上传文件
     */
    @Override
    public List<UploadFile> queryList(UploadFile uploadFile) {
        return uploadFileMapper.selectList(uploadFile);
    }

    /**
     * 构建原生mybatis-plus 查询wrapper
     *
     * @return
     */
    private LambdaQueryWrapper<UploadFile> buildQueryWrapper(UploadFile uploadFile) {
        LambdaQueryWrapper<UploadFile> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(uploadFile.getFileName()), UploadFile::getFileName, uploadFile.getFileName());
        lqw.like(StringUtils.isNotBlank(uploadFile.getOriginalName()), UploadFile::getOriginalName, uploadFile.getOriginalName());
        lqw.eq(StringUtils.isNotBlank(uploadFile.getFileType()), UploadFile::getFileType, uploadFile.getFileType());
        lqw.eq(uploadFile.getRelationId() != null, UploadFile::getRelationId, uploadFile.getRelationId());
        lqw.eq(StringUtils.isNotBlank(uploadFile.getFileSuffix()), UploadFile::getFileSuffix, uploadFile.getFileSuffix());
        return lqw;
    }

    /**
     * 新增上传文件
     *
     * @param uploadFile 上传文件
     * @return 结果
     */
    @Override
    public Boolean insertUploadFile(UploadFile uploadFile) {
        validEntityBeforeSave(uploadFile);
        uploadFileMapper.insert(uploadFile);
        return uploadFile.getId() > 0;
    }

    /**
     * 修改上传文件
     *
     * @param uploadFile 上传文件
     * @return 结果
     */
    @Override
    public Boolean updateUploadFile(UploadFile uploadFile) {
        // 校验存在
        validateUploadFileExists(uploadFile.getId());
        validEntityBeforeSave(uploadFile);
        return uploadFileMapper.updateById(uploadFile) > 0;
    }

    /**
     * 批量删除上传文件
     *
     * @param ids 需要删除的上传文件主键
     * @return 结果
     */
    @Override
    public Boolean deleteUploadFileByIds(Collection<Long> ids) {
        return uploadFileMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 通过ids批量查询上传文件
     *
     * @param ids 需要查询的上传文件主键
     * @return 结果
     */
    @Override
    public List<UploadFile> getUploadFileList(Collection<Long> ids) {
        return uploadFileMapper.selectBatchIds(ids);
    }

    /**
     * 根据关联ID获取文件列表
     *
     * @param relationId 关联ID
     * @param fileType   文件类型
     * @return 结果
     */
    @Override
    public List<UploadFile> getUploadFileListByRelationId(Long relationId, String fileType) {
        return uploadFileMapper.selectList(new LambdaQueryWrapper<UploadFile>()
                .eq(UploadFile::getRelationId, relationId)
                .eq(UploadFile::getFileType, fileType)
                .orderByDesc(UploadFile::getCreateTime));
    }

    /**
     * 根据关联ID列表获取文件列表
     *
     * @param relationIds 关联ID集合
     * @param fileType    文件类型
     * @return 结果
     */
    @Override
    public List<UploadFile> getUploadFileListByRelationIds(Collection<Long> relationIds, String fileType) {
        if (CollectionUtil.isEmpty(relationIds)) {
            return Lists.newArrayList();
        }
        return uploadFileMapper.selectList(new LambdaQueryWrapper<UploadFile>()
                .in(UploadFile::getRelationId, relationIds)
                .eq(UploadFile::getFileType, fileType)
                .orderByDesc(UploadFile::getCreateTime));
    }

    /**
     * 获取最新文件对象
     *
     * @param relationId 关联ID
     * @param fileType   文件类型
     * @return 结果
     */
    @Override
    public UploadFile getLastUploadFile(Long relationId, String fileType) {
        return Optional.ofNullable(uploadFileMapper.selectOne(new LambdaQueryWrapper<UploadFile>()
                .eq(UploadFile::getRelationId, relationId)
                .eq(UploadFile::getFileType, fileType)
                .orderByDesc(UploadFile::getCreateTime)
                .last(" LIMIT 1")))
                .orElse(new UploadFile());
    }

    /**
     * 更新关联ID
     *
     * @param id         文件ID
     * @param relationId 关联ID
     * @param fileType   文件类型
     * @return 结果
     */
    @Override
    public Boolean updateRelationIdById(Long id, Long relationId, String fileType) {
        UploadFile uploadFile = new UploadFile();
        uploadFile.setRelationId(relationId);
        int result = uploadFileMapper.update(uploadFile, new LambdaQueryWrapper<UploadFile>()
                .eq(UploadFile::getId, id)
                .eq(UploadFile::getFileType, fileType));
        return result > 0;
    }

    /**
     * 批量更新关联ID
     * 取消历史关联ID
     *
     * @param ids        文件ID集合
     * @param relationId 关联ID
     * @param fileType   文件类型
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateRelationIdById(Collection<Long> ids, Long relationId, String fileType) {
        UploadFile uploadFile = new UploadFile();
        uploadFile.setRelationId(CLEAN_RELATION_ID);

        // 取消历史关联ID
        uploadFileMapper.update(uploadFile, new LambdaQueryWrapper<UploadFile>()
                .in(UploadFile::getRelationId, relationId)
                .eq(UploadFile::getFileType, fileType));

        uploadFile.setRelationId(relationId);
        if (CollectionUtils.isNotEmpty(ids)) {
            int result = uploadFileMapper.update(uploadFile, new LambdaQueryWrapper<UploadFile>()
                    .in(UploadFile::getId, ids)
                    .eq(UploadFile::getFileType, fileType));

            return result > 0;
        }
        return true;
    }

    /**
     * 根据关联ID复制文件数据
     *
     * @param sourceRelationId 原编码
     * @param targetRelationId 目标编码
     * @param fileType         文件类型
     * @return 结果
     */
    @Override
    public Boolean copyUploadFileByRelationId(Long sourceRelationId, Long targetRelationId, String fileType) {
        List<UploadFile> uploadFileList = this.getUploadFileListByRelationId(sourceRelationId, fileType);
        if (CollectionUtils.isEmpty(uploadFileList)) {
            return false;
        }
        for (UploadFile uploadFile : uploadFileList) {
            uploadFile.setId(null);
            uploadFile.setRelationId(targetRelationId);
            uploadFile.setCreateTime(DateUtils.getNowDate());
            uploadFile.setCreateBy(SecurityUtils.getUsername());
        }
        return uploadFileMapper.insertBatch(uploadFileList);
    }

    /**
     * 转换文件列表VO
     *
     * @param list 文件列表
     * @return 结果
     */
    @Override
    public List<UploadFileVO> convertListVO(List<UploadFile> list) {
        List<UploadFileVO> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(list)) {
            return result;
        }
        list.forEach(item -> result.add(this.convertListVO(item)));
        return result;
    }

    /**
     * 转换文件列表VO
     *
     * @param file 文件
     * @return 结果
     */
    @Override
    public UploadFileVO convertListVO(UploadFile file) {
        if (null == file) {
            return null;
        }
        return new UploadFileVO(file.getId(), file.getUrl(), file.getOriginalName());
    }

    /**
     * 删除文件
     *
     * @param filePath 文件路径
     * @return 结果
     */
    @Override
    public Boolean deleteFile(String filePath) {
        String localPath = FlyhighConfig.getProfile();
        // 数据库资源地址
        String downloadPath = localPath + StringUtils.substringAfter(filePath, Constants.RESOURCE_PREFIX);
        return FileUtils.deleteFile(downloadPath);
    }

}
