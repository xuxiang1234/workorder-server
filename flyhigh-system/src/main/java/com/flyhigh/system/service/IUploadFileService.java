package com.flyhigh.system.service;

import com.flyhigh.system.domain.UploadFile;
import com.flyhigh.system.domain.vo.UploadFileVO;

import java.util.Collection;
import java.util.List;

/**
 * 上传文件Service接口
 *
 * @author flyhigh
 * @date 2022-12-29
 */
public interface IUploadFileService {

    /**
     * 查询上传文件
     *
     * @param id 上传文件主键
     * @return 上传文件
     */
    UploadFile queryById(Long id);

    /**
     * 查询上传文件列表
     *
     * @param uploadFile 上传文件
     * @return 上传文件集合
     */
    List<UploadFile> queryList(UploadFile uploadFile);

    /**
     * 创建上传文件
     *
     * @param uploadFile 上传文件
     * @return 结果
     */
    Boolean insertUploadFile(UploadFile uploadFile);

    /**
     * 修改上传文件
     *
     * @param uploadFile 上传文件
     * @return 结果
     */
    Boolean updateUploadFile(UploadFile uploadFile);

    /**
     * 校验并批量删除上传文件信息
     *
     * @param ids 需要删除的上传文件主键集合
     * @return 结果
     */
    Boolean deleteUploadFileByIds(Collection<Long> ids);

    /**
     * 获得上传文件列表
     *
     * @param ids 编号
     */
    List<UploadFile> getUploadFileList(Collection<Long> ids);

    /**
     * 根据关联ID获取文件列表
     *
     * @param relationId 关联ID
     * @param fileType   文件类型
     * @return 结果
     */
    List<UploadFile> getUploadFileListByRelationId(Long relationId, String fileType);

    /**
     * 根据关联ID列表获取文件列表
     *
     * @param relationIds 关联ID集合
     * @param fileType    文件类型
     * @return 结果
     */
    List<UploadFile> getUploadFileListByRelationIds(Collection<Long> relationIds, String fileType);

    /**
     * 获取最新文件对象
     *
     * @param relationId 关联ID
     * @param fileType   文件类型
     * @return 结果
     */
    UploadFile getLastUploadFile(Long relationId, String fileType);

    /**
     * 更新关联ID
     *
     * @param id         文件ID
     * @param relationId 关联ID
     * @param fileType   文件类型
     * @return 结果
     */
    Boolean updateRelationIdById(Long id, Long relationId, String fileType);

    /**
     * 批量更新关联ID
     * 取消历史关联ID
     *
     * @param ids        文件ID集合
     * @param relationId 关联ID
     * @param fileType   文件类型
     * @return 结果
     */
    Boolean updateRelationIdById(Collection<Long> ids, Long relationId, String fileType);

    /**
     * 根据关联ID复制文件数据
     *
     * @param sourceRelationId 原编码
     * @param targetRelationId 目标编码
     * @param fileType         文件类型
     * @return 结果
     */
    Boolean copyUploadFileByRelationId(Long sourceRelationId, Long targetRelationId, String fileType);

    /**
     * 转换文件列表VO
     *
     * @param list 文件列表
     * @return 结果
     */
    List<UploadFileVO> convertListVO(List<UploadFile> list);

    /**
     * 转换文件列表VO
     *
     * @param file 文件
     * @return 结果
     */
    UploadFileVO convertListVO(UploadFile file);

    /**
     * 删除文件
     *
     * @param filePath 文件路径
     * @return 结果
     */
    Boolean deleteFile(String filePath);

}
