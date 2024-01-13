package com.flyhigh.sms.domain.convert;

import com.flyhigh.api.dto.messageInfo.BizMessageUserDetailDTO;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.enums.MessageApproveEnum;
import com.flyhigh.common.enums.MessageReceiveStatusEnum;
import com.flyhigh.sms.domain.BizMessageUserDetail;
import com.flyhigh.sms.domain.vo.messageUserDetail.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 消息接收人转换对象消息接收人 Convert
 *
 * @author flyhigh
 */
@Mapper
public interface BizMessageUserDetailConvert {

    BizMessageUserDetailConvert INSTANCE = Mappers.getMapper(BizMessageUserDetailConvert.class);

    BizMessageUserDetail convert(BizMessageUserDetailCreateReq bean);

    BizMessageUserDetail convert(BizMessageUserDetailUpdateReq bean);

    BizMessageUserDetail convert(BizMessageUserDetailExportReq bean);

    BizMessageUserDetailVO convert(BizMessageUserDetail bean);

    List<BizMessageUserDetailVO> convertList(List<BizMessageUserDetail> list);

    List<BizMessageUserDetailDTO> convertDTOList(List<BizMessageUserDetail> list);

    TableDataInfo<BizMessageUserDetailVO> convertPage(TableDataInfo<BizMessageUserDetail> page);

    List<BizMessageUserDetailExcelVO> convertListExcel(List<BizMessageUserDetail> list);

    default BizMessageUserDetail convert(Long userId, Long messageId) {
        BizMessageUserDetailCreateReq bizMessageUserDetailCreateReq = new BizMessageUserDetailCreateReq();
        bizMessageUserDetailCreateReq.setUserId(userId);
        bizMessageUserDetailCreateReq.setReceiveStatus(MessageReceiveStatusEnum.Unread.getCode());
        bizMessageUserDetailCreateReq.setMessageId(messageId);
        bizMessageUserDetailCreateReq.setWhetherApprove(MessageApproveEnum.NO.getCode());
        return this.convert(bizMessageUserDetailCreateReq);
    }
}
