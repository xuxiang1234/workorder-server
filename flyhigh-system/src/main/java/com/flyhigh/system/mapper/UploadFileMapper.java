package com.flyhigh.system.mapper;

import com.flyhigh.system.domain.UploadFile;
import com.flyhigh.common.core.mapper.BaseMapperPlus;
import com.flyhigh.common.core.query.LambdaQueryWrapperPlus;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * 上传文件Mapper接口
 *
 * @author flyhigh
 * @date 2022-12-29
 */
public interface UploadFileMapper extends BaseMapperPlus<UploadFileMapper, UploadFile> {

    /**
     * 查询上传文件
     *
     * @param id 上传文件主键
     * @return 上传文件
     */
    UploadFile selectUploadFileById(Long id);

    /**
     * 查询上传文件列表
     *
     * @param uploadFile 上传文件
     * @return 上传文件集合
     */
    List<UploadFile> selectUploadFileList(UploadFile uploadFile);

    /**
     * 新增上传文件
     *
     * @param uploadFile 上传文件
     * @return 结果
     */
    int insertUploadFile(UploadFile uploadFile);

    /**
     * 修改上传文件
     *
     * @param uploadFile 上传文件
     * @return 结果
     */
    int updateUploadFile(UploadFile uploadFile);

    /**
     * 删除上传文件
     *
     * @param id 上传文件主键
     * @return 结果
     */
    int deleteUploadFileById(Long id);

    /**
     * 批量删除上传文件
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteUploadFileByIds(@Param("ids") Collection<Long> ids);

    /**
     * 导出列表查询
     *
     * @param uploadFile
     * @return
     */
    default List<UploadFile> selectList(UploadFile uploadFile) {
        return selectList(new LambdaQueryWrapperPlus<UploadFile>()
                .likeIfPresent(UploadFile::getFileName, uploadFile.getFileName())
                .likeIfPresent(UploadFile::getOriginalName, uploadFile.getOriginalName())
                .eqIfPresent(UploadFile::getFileType, uploadFile.getFileType())
                .eqIfPresent(UploadFile::getRelationId, uploadFile.getRelationId())
                .eqIfPresent(UploadFile::getFileSuffix, uploadFile.getFileSuffix())
                .orderByDesc(UploadFile::getId));
    }

}
