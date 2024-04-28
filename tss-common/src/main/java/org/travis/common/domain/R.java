package org.travis.common.domain;

import org.travis.common.constants.SystemConstant;
import org.travis.common.enums.BizCodeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.slf4j.MDC;

import java.io.Serializable;

/**
 * @ClassName R
 * @Description 封装通用返回类
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
@Data
@Schema(description = "通用响应结果")
public class R<T> implements Serializable {

    @Schema(description = "业务状态码，200-成功, 其它-失败, 999-未知")
    private int code;
    @Schema(description = "响应消息", example = "OK")
    private String msg;
    @Schema(description = "响应数据")
    private T data;
    @Schema(description = "请求id", example = "1af123c11412e")
    private String requestId;


    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.requestId = MDC.get(SystemConstant.REQUEST_ID_HEADER);
    }


    /**
     * @MethodName ok
     * @Description 请求成功-1
     * @Author travis-wei
     * @Data 2024/4/21
     * @Return org.travis.common.domain.R<java.lang.Void>
     **/
    public static R<Void> ok() {
        return new R<Void>(BizCodeEnum.SUCCESS.getCode(), BizCodeEnum.SUCCESS.getMessage(), null);
    }

    /**
     * @MethodName ok
     * @Description 请求成功-2
     * @Author travis-wei
     * @Data 2024/4/21
     * @param data	返回数据信息
     * @Return org.travis.common.domain.R<T>
     **/
    public static <T> R<T> ok(T data) {
        return new R<>(BizCodeEnum.SUCCESS.getCode(), BizCodeEnum.SUCCESS.getMessage(), data);
    }

    /**
     * @MethodName error
     * @Description 请求错误-1
     * @Author travis-wei
     * @Data 2024/4/21
     * @param msg	失败消息
     * @Return org.travis.common.domain.R<T>
     **/
    public static <T> R<T> error(String msg) {
        return new R<>(BizCodeEnum.UNKNOW.getCode(), msg, null);
    }

    /**
     * @MethodName error
     * @Description 请求错误-2
     * @Author travis-wei
     * @Data 2024/4/21
     * @param code	失败请求码
     * @param msg	失败消息
     * @Return org.travis.common.domain.R<T>
     **/
    public static <T> R<T> error(int code, String msg) {
        return new R<>(code, msg, null);
    }

    /**
     * @MethodName error
     * @Description 请求错误-3
     * @Author travis-wei
     * @Data 2024/4/21
     * @param code	失败请求码
     * @param msg	失败消息
     * @Return org.travis.common.domain.R<T>
     **/
    public static <T> R<T> error(int code, String msg, T object) {
        return new R<>(code, msg, object);
    }

    /**
     * @MethodName checkSuccess
     * @Description 判断请求是否成功
     * @Author travis-wei
     * @Data 2024/4/21
     * @Return boolean
     **/
    public boolean checkSuccess(){
        return code == BizCodeEnum.SUCCESS.getCode();
    }

    /**
     * @MethodName checkSuccess
     * @Description 判断服务是否降级
     * @Author travis-wei
     * @Data 2024/4/21
     * @Return boolean
     **/
    public boolean checkServiceDegraded(){
        return code == BizCodeEnum.DEGRADED_SERVICE.getCode();
    }

    /**
     * @MethodName requestId
     * @Description 手动设置 requestId
     * @Author travis-wei
     * @Data 2024/4/21
     * @param requestId	请求ID
     * @Return org.travis.common.domain.R<T>
     **/
    public R<T> requestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    /**
     * @MethodName autoSetRequestId
     * @Description 自动设置 requestId
     * @Author travis-wei
     * @Data 2024/4/27
     * @Return org.travis.common.domain.R<T>
     **/
    public R<T> autoSetRequestId() {
        this.requestId = MDC.get(SystemConstant.REQUEST_ID_HEADER);
        return this;
    }
}
