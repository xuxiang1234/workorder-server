package com.flyhigh.system.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.flyhigh.api.service.UploadFileServiceApi;
import com.flyhigh.common.enums.TimeUnitEnum;
import com.flyhigh.common.utils.DateUtils;
import com.flyhigh.system.domain.UploadFile;
import com.flyhigh.system.mapper.UploadFileMapper;
import com.flyhigh.system.service.IUploadFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件上传
 *
 * @author Mr.Lai
 * @date 2023/3/22
 */
@Slf4j
@Service
public class UploadFileServiceApiImpl implements UploadFileServiceApi {

    @Resource
    private UploadFileMapper uploadFileMapper;

    @Autowired
    private IUploadFileService uploadFileService;

    /**
     * 清空历史文件
     *
     * @param timeUnit 时间颗粒度
     * @param timeout  时间
     */
    @Override
    public void cleanUploadFile(String timeUnit, Integer timeout) {
        Date dateTime = this.formatQueryTime(TimeUnitEnum.getTimeUnit(timeUnit), timeout);

        String today = DateUtils.parseDateToStr(DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND, dateTime);
        List<UploadFile> uploadFileList = uploadFileMapper.selectList(new LambdaQueryWrapper<UploadFile>()
                .lt(UploadFile::getCreateTime, today)
                .and(qw -> qw.eq(UploadFile::getRelationId, UploadFileServiceImpl.CLEAN_RELATION_ID).or()
                        .isNull(UploadFile::getRelationId))
        );
        log.info("清空历史文件：{} 数量 - {}", today, uploadFileList.size());

        if (CollectionUtils.isEmpty(uploadFileList)) {
            return;
        }
        List<Long> ids = uploadFileList.stream().map(UploadFile::getId).collect(Collectors.toList());
        int result = uploadFileMapper.deleteUploadFileByIds(ids);
        if (result > 0) {
            // 删除文件
            for (UploadFile uploadFile : uploadFileList) {
                uploadFileService.deleteFile(uploadFile.getUrl());
            }

            log.info("清空历史文件：{} SUCCESS", today);
        }
    }

    private Date formatQueryTime(TimeUnitEnum timeUnitEnum, Integer timeout) {
        Date nowDate = DateUtils.getNowDate();

        if (null == timeUnitEnum || null == timeout) {
            // 默认一天前
            return DateUtil.offsetDay(nowDate, -1);
        }

        switch (timeUnitEnum) {
            case HOURS:
                return DateUtil.offsetHour(nowDate, -timeout);
            case DAYS:
                return DateUtil.offsetDay(nowDate, -timeout);
            case MONTH:
                return DateUtil.offsetMonth(nowDate, -timeout);
            case YEARS:
                return DateUtil.offset(nowDate, DateField.YEAR, -timeout);
            default:
                return DateUtils.getNowDate();
        }
    }

}
