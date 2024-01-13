package com.flyhigh.common.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flyhigh.common.exception.ErrorCode;
import com.flyhigh.common.exception.GlobalErrorCodeConstants;
import com.flyhigh.common.exception.ServiceException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * 操作消息提醒
 *
 * @author flyhigh
 */
@Data
@ApiModel("公共返回结果")
public class AjaxResult<T> {
    private static final long serialVersionUID = 1L;

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public AjaxResult() {
    }

    /**
     * 错误码
     *
     * @see ErrorCode#getCode()
     */
    @ApiModelProperty("错误码")
    private Integer code;
    /**
     * 返回数据
     */
    @ApiModelProperty("返回数据")
    private T data;
    /**
     * 错误提示，用户可阅读
     *
     * @see ErrorCode#getMsg() ()
     */
    @ApiModelProperty("错误提示，用户可阅读")
    private String msg;

    /**
     * 将传入的 result 对象，转换成另外一个泛型结果的对象
     * <p>
     * 因为 A 方法返回的 AjaxResult 对象，不满足调用其的 B 方法的返回，所以需要进行转换。
     *
     * @param result 传入的 result 对象
     * @param <T>    返回的泛型
     * @return 新的 AjaxResult 对象
     */
    public static <T> AjaxResult<T> error(AjaxResult<?> result) {
        return error(result.getCode(), result.getMsg());
    }

    public static AjaxResult error() {
        return AjaxResult.error(GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR.getCode(), "操作失败");
    }

    public static <T> AjaxResult<T> error(String msg) {
        return AjaxResult.error(GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR.getCode(), msg);
    }

    public static <T> AjaxResult<T> error(Integer code, String message) {
        Assert.isTrue(!GlobalErrorCodeConstants.SUCCESS.getCode().equals(code), "code 必须是错误的！");
        AjaxResult<T> result = new AjaxResult<>();
        result.code = code;
        result.msg = message;
        return result;
    }

    public static <T> AjaxResult<T> error(ErrorCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }

    public static AjaxResult success() {
        return AjaxResult.success(GlobalErrorCodeConstants.SUCCESS.getCode(), "操作成功");
    }


    public static <T> AjaxResult<T> success(Integer code, String message) {
        AjaxResult<T> result = new AjaxResult<>();
        result.code = code;
        result.msg = message;
        return result;
    }

    public static <T> AjaxResult<T> success(T data) {
        AjaxResult<T> result = new AjaxResult<>();
        result.code = GlobalErrorCodeConstants.SUCCESS.getCode();
        result.data = data;
        result.msg = "";
        return result;
    }

    public static boolean isSuccess(Integer code) {
        return Objects.equals(code, GlobalErrorCodeConstants.SUCCESS.getCode());
    }

    @JsonIgnore // 避免 jackson 序列化
    public boolean isSuccess() {
        return isSuccess(code);
    }

    @JsonIgnore // 避免 jackson 序列化
    public boolean isError() {
        return !isSuccess();
    }

    /**
     * 判断是否有异常。如果有，则抛出 {@link ServiceException} 异常
     */
    public void checkError() throws ServiceException {
        if (isSuccess()) {
            return;
        }
        // 业务异常
        throw new ServiceException(msg, code);
    }

    public static <T> AjaxResult<T> error(ServiceException serviceException) {
        return error(serviceException.getCode(), serviceException.getMessage());
    }
}
