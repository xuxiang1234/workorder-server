package com.flyhigh.quartz.controller;

import java.util.List;

import com.flyhigh.common.annotation.Log;
import com.flyhigh.common.constant.Constants;
import com.flyhigh.common.core.controller.BaseController;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.enums.BusinessType;
import com.flyhigh.common.exception.job.TaskException;
import com.flyhigh.common.utils.StringUtils;
import com.flyhigh.common.utils.poi.ExcelUtil;
import com.flyhigh.quartz.service.ISysJobService;
import io.swagger.annotations.Api;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.flyhigh.quartz.domain.SysJob;
import com.flyhigh.quartz.util.CronUtils;
import com.flyhigh.quartz.util.ScheduleUtils;

/**
 * 调度任务信息操作处理
 *
 * @author ruoyi
 */
@Api(value = "调度任务控制器", tags = {"调度任务管理"})
@Controller
@RequestMapping("/monitor/job")
public class SysJobController extends BaseController {

    private String prefix = "monitor/job";

    @Autowired
    private ISysJobService jobService;

    @PreAuthorize("@ss.hasPermi('monitor:job:view')")
    @GetMapping()
    public String job() {
        return prefix + "/job";
    }

    /**
     * 查询任务列表
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:list')")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo<SysJob> list(SysJob job) {
        startPage();
        List<SysJob> list = jobService.selectJobList(job);
        return getDataTable(list);
    }

    /**
     * 导出任务列表
     */
    @Log(title = "定时任务", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('monitor:job:export')")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysJob job) {
        List<SysJob> list = jobService.selectJobList(job);
        ExcelUtil<SysJob> util = new ExcelUtil<SysJob>(SysJob.class);
        return util.exportExcel(list, "定时任务");
    }

    /**
     * 删除任务
     */
    @Log(title = "定时任务", businessType = BusinessType.DELETE)
    @PreAuthorize("@ss.hasPermi('monitor:job:remove')")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult<Boolean> remove(String ids) throws SchedulerException {
        jobService.deleteJobByIds(ids);
        return success();
    }

    /**
     * 任务详情
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:detail')")
    @GetMapping("/detail/{jobId}")
    public String detail(@PathVariable("jobId") Long jobId, ModelMap mmap) {
        mmap.put("name", "job");
        mmap.put("job", jobService.selectJobById(jobId));
        return prefix + "/detail";
    }

    /**
     * 任务调度状态修改
     */
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PreAuthorize("@ss.hasPermi('monitor:job:changeStatus')")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult<Boolean> changeStatus(SysJob job) throws SchedulerException {
        SysJob newJob = jobService.selectJobById(job.getJobId());
        newJob.setStatus(job.getStatus());
        return toAjax(jobService.changeStatus(newJob));
    }

    /**
     * 任务调度立即执行一次
     */
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PreAuthorize("@ss.hasPermi('monitor:job:changeStatus')")
    @PostMapping("/run")
    @ResponseBody
    public AjaxResult<Boolean> run(SysJob job) throws SchedulerException {
        boolean result = jobService.run(job);
        return result ? success() : error("任务不存在或已过期！");
    }

    /**
     * 新增调度
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存调度
     */
    @Log(title = "定时任务", businessType = BusinessType.INSERT)
    @PreAuthorize("@ss.hasPermi('monitor:job:add')")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult<Boolean> addSave(@Validated @RequestBody SysJob job) throws SchedulerException, TaskException {
        if (!CronUtils.isValid(job.getCronExpression())) {
            return error("新增任务'" + job.getJobName() + "'失败，Cron表达式不正确");
        } else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), Constants.LOOKUP_RMI)) {
            return error("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'rmi'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[]{Constants.LOOKUP_LDAP, Constants.LOOKUP_LDAPS})) {
            return error("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'ldap(s)'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[]{Constants.HTTP, Constants.HTTPS})) {
            return error("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'http(s)'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), Constants.JOB_ERROR_STR)) {
            return error("新增任务'" + job.getJobName() + "'失败，目标字符串存在违规");
        } else if (!ScheduleUtils.whiteList(job.getInvokeTarget())) {
            return error("新增任务'" + job.getJobName() + "'失败，目标字符串不在白名单内");
        }
        job.setCreateBy(getUsername());
        return toAjax(jobService.insertJob(job));
    }

    /**
     * 修改调度
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:edit')")
    @GetMapping("/edit/{jobId}")
    public String edit(@PathVariable("jobId") Long jobId, ModelMap mmap) {
        mmap.put("job", jobService.selectJobById(jobId));
        return prefix + "/edit";
    }

    /**
     * 修改保存调度
     */
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PreAuthorize("@ss.hasPermi('monitor:job:edit')")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult<Boolean> editSave(@Validated SysJob job) throws SchedulerException, TaskException {
        if (!CronUtils.isValid(job.getCronExpression())) {
            return error("修改任务'" + job.getJobName() + "'失败，Cron表达式不正确");
        } else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), Constants.LOOKUP_RMI)) {
            return error("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'rmi'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[]{Constants.LOOKUP_LDAP, Constants.LOOKUP_LDAPS})) {
            return error("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'ldap'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[]{Constants.HTTP, Constants.HTTPS})) {
            return error("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'http(s)'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), Constants.JOB_ERROR_STR)) {
            return error("修改任务'" + job.getJobName() + "'失败，目标字符串存在违规");
        } else if (!ScheduleUtils.whiteList(job.getInvokeTarget())) {
            return error("修改任务'" + job.getJobName() + "'失败，目标字符串不在白名单内");
        }
        return toAjax(jobService.updateJob(job));
    }

    /**
     * 校验cron表达式是否有效
     */
    @PostMapping("/checkCronExpressionIsValid")
    @ResponseBody
    public boolean checkCronExpressionIsValid(SysJob job) {
        return jobService.checkCronExpressionIsValid(job.getCronExpression());
    }

    /**
     * Cron表达式在线生成
     */
    @GetMapping("/cron")
    public String cron() {
        return prefix + "/cron";
    }

    /**
     * 查询cron表达式近5次的执行时间
     */
    @GetMapping("/queryCronExpression")
    @ResponseBody
    public AjaxResult<List<String>> queryCronExpression(@RequestParam(value = "cronExpression", required = false) String cronExpression) {
        if (jobService.checkCronExpressionIsValid(cronExpression)) {
            List<String> dateList = CronUtils.getRecentTriggerTime(cronExpression);
            return AjaxResult.success(dateList);
        } else {
            return error("表达式无效");
        }
    }
}