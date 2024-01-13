package com.flyhigh.workorder.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.flyhigh.api.vo.template.TemplatePageReq;
import com.flyhigh.api.vo.template.TemplateSimplifyVO;
import com.flyhigh.api.vo.template.TemplateVO;
import com.flyhigh.api.vo.templateprocess.TemplateProcessVO;
import com.flyhigh.common.core.domain.model.LoginUser;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.exception.ErrorCodeConstants;
import com.flyhigh.common.exception.GlobalErrorCodeConstants;
import com.flyhigh.common.exception.ServiceExceptionUtil;
import com.flyhigh.common.utils.SecurityUtils;
import com.flyhigh.api.enums.SystemIdentification;
import com.flyhigh.api.vo.formtemplate.FormTemplateCreateReq;
import com.flyhigh.api.vo.formtemplate.FormTemplateUpdateReq;
import com.flyhigh.api.vo.formtemplate.FormTemplateVO;
import com.flyhigh.form.service.IFormTemplateService;
import com.flyhigh.workorder.domain.convert.TemplateConvert;
import com.flyhigh.workorder.domain.convert.TemplateProcessConvert;
import com.flyhigh.workorder.domain.entity.Template;
import com.flyhigh.workorder.domain.entity.TemplateProcess;
import com.flyhigh.workorder.domain.vo.template.TemplateCreateReq;
import com.flyhigh.workorder.domain.vo.template.TemplateUpdateReq;
import com.flyhigh.workorder.mapper.TemplateMapper;
import com.flyhigh.workorder.mapper.TemplateProcessMapper;
import com.flyhigh.workorder.service.ITemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板Service业务层处理
 *
 * @author flyhigh
 * @date 2022-05-14
 */
@Service
@Validated
@RequiredArgsConstructor
public class TemplateServiceImpl implements ITemplateService {

    private final TemplateMapper templateMapper;

    private final TemplateProcessMapper templateProcessMapper;

    private final IFormTemplateService formTemplateService;

    /**
     * 校验是否存在
     */
    private void validatetemplateExists(Long id) {
        if (templateMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DATA_ERROR);
        }
    }

    /**
     * 获取模板详细信息
     *
     * @param id 模板主键
     * @return 模板
     */
    @Override
    public TemplateVO queryById(Long id) {
        //查询模板信息
        validatetemplateExists(id);
        Template template = templateMapper.selectById(id);
        TemplateVO templateVO = TemplateConvert.INSTANCE.convert(template);

        //查询模板流程
        List<TemplateProcess> templateProcessList = queryTemplateProcessList(template.getId());
        List<TemplateProcessVO> templateProcessVOList = TemplateProcessConvert.INSTANCE.convertList(templateProcessList);

        //查询表单
        for (TemplateProcessVO tv : templateProcessVOList) {
            FormTemplateVO formTemplateVO = formTemplateService.queryFormtemplate(tv.getId(), SystemIdentification.WORK_ORDER_SYSTEM);
            if (null != formTemplateVO) {
                tv.setFormTemplateContent(formTemplateVO.getContent());
                tv.setIsMultForm(formTemplateVO.getIsMultForm());
            }

        }
        templateVO.setTemplateProcessVOList(templateProcessVOList);

        return templateVO;
    }

    /**
     * 查询模板列表
     *
     * @param pageReq 模板
     * @return 模板
     */
    @Override
    public TableDataInfo<TemplateVO> queryPageList(TemplatePageReq pageReq) {
        TableDataInfo<Template> templateTableDataInfo = templateMapper.selectPage(pageReq);
        return TemplateConvert.INSTANCE.convertPage(templateTableDataInfo);
    }

    /**
     * 查询模板列表 - 简化接口
     *
     * @param templateName 模板名称
     * @return
     */
    @Override
    public List<TemplateSimplifyVO> simplifyPageList(String templateName) {
        Template template = new Template();
        template.setTemplateName(templateName);
        List<Template> templateList = templateMapper.selectTemplateList(template);
        return TemplateConvert.INSTANCE.convertSimplifyList(templateList);
    }

    /**
     * 查询模板总数
     *
     * @return total
     */
    @Override
    public Long queryTotal() {
        return templateMapper.queryTotal();
    }


    /**
     * 新增模板
     *
     * @param reateReq 模板
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createTemplate(TemplateCreateReq reateReq) {
        //验证模板名称是否重复
        Template t = templateMapper.selectByName(reateReq.getTemplateName());
        if (null != t) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.TEMPLATE_NAME_EXISTS);
        }

        LoginUser loginUser = SecurityUtils.getLoginUser();

        //添加模板主数据
        Template template = TemplateConvert.INSTANCE.convert(reateReq);
        template.setCreateId(loginUser.getUserId());
        template.setCreateName(loginUser.getUsername());
        templateMapper.insert(template);

        //添加模板流程
        List<TemplateProcess> templateProcessList = TemplateProcessConvert.INSTANCE.convertCreateReqList(reateReq.getTemplateProcessReqs());
        addtemplateProcess(templateProcessList, template.getId());

        //添加表单模板
        List<FormTemplateCreateReq> formTemplateReqList = new ArrayList<>();
        for (int i = 0; i < templateProcessList.size(); i++) {
            String formTemplateContent = reateReq.getTemplateProcessReqs().get(i).getFormTemplateContent();
            if (StringUtils.isBlank(formTemplateContent)) {
                continue;
            }

            FormTemplateCreateReq formTemplateReq = new FormTemplateCreateReq();
            formTemplateReq.setContactId(templateProcessList.get(i).getId());
            formTemplateReq.setSystem(SystemIdentification.WORK_ORDER_SYSTEM);
            formTemplateReq.setIsMultForm(reateReq.getTemplateProcessReqs().get(i).getIsMultForm());
            formTemplateReq.setContent(formTemplateContent);
            formTemplateReqList.add(formTemplateReq);
        }
        formTemplateService.createFormtemplate(formTemplateReqList);

        return true;
    }

    /**
     * 修改模板
     *
     * @param updateReq 模板
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateTemplate(TemplateUpdateReq updateReq) {


        validatetemplateExists(updateReq.getId());

        // 更新
        Template template = TemplateConvert.INSTANCE.convert(updateReq);
        templateMapper.updateById(template);

        //删除旧的模板流程
        templateProcessMapper.deleteTemplatetemplateId(template.getId());

        //添加新的流程
        List<TemplateProcess> templateProcessList = TemplateProcessConvert.INSTANCE.convertUpdateReqList(updateReq.getTemplateProcessReqs());
        addtemplateProcess(templateProcessList, template.getId());

        //修改表单模板
        List<FormTemplateUpdateReq> formTemplateReqList = new ArrayList<>();
        for (int i = 0; i < templateProcessList.size(); i++) {
            String formTemplateContent = updateReq.getTemplateProcessReqs().get(i).getFormTemplateContent();
            if (StringUtils.isBlank(formTemplateContent)) {
                continue;
            }

            FormTemplateUpdateReq formTemplateReq = new FormTemplateUpdateReq();
            formTemplateReq.setContactId(templateProcessList.get(i).getId());
            formTemplateReq.setSystem(SystemIdentification.WORK_ORDER_SYSTEM);
            formTemplateReq.setIsMultForm(updateReq.getTemplateProcessReqs().get(i).getIsMultForm());
            formTemplateReq.setContent(formTemplateContent);
            formTemplateReqList.add(formTemplateReq);
        }
        formTemplateService.updateFormtemplate(formTemplateReqList);

        return true;
    }

    /**
     * 添加流程
     *
     * @param templateProcessList
     * @param templateId
     */
    @Transactional(rollbackFor = Exception.class)
    public void addtemplateProcess(List<TemplateProcess> templateProcessList, Long templateId) {
        for (int i = 0; i < templateProcessList.size(); i++) {
            templateProcessList.get(i).setProcessIndex(i + 1);
            templateProcessList.get(i).setTemplateId(templateId);
        }
        templateProcessList.get(templateProcessList.size() - 1).setIsLast(1);
        templateProcessMapper.insertBatch(templateProcessList);
    }


    /**
     * 根据模板编号查询模板流程
     *
     * @param templateId
     * @return
     */
    @Override
    public List<TemplateProcess> queryTemplateProcessList(Long templateId) {

        // 校验存在
        validatetemplateExists(templateId);

        TemplateProcess templateProcess = new TemplateProcess();
        templateProcess.setTemplateId(templateId);
        List<TemplateProcess> templateProcessList = templateProcessMapper.selectTemplateProcessList(templateProcess);
        return templateProcessList;
    }


    /**
     * 删除模板
     *
     * @param id 主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteById(Long id) {
        Template template = templateMapper.selectById(id);
        if (template == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DATA_ERROR);
        }

        if (!template.getCreateId().equals(SecurityUtils.getUserId())) {
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.FORBIDDEN);
        }

        templateMapper.deleteById(id);

        //删除模板流程
        templateProcessMapper.deleteTemplatetemplateId(id);
        return true;
    }

}
