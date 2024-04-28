package org.travis.common.config.mvc.advice;

import org.apache.dubbo.rpc.RpcException;
import org.travis.common.constants.SystemConstant;
import org.travis.common.domain.R;
import org.travis.common.enums.BizCodeEnum;
import org.travis.common.exceptions.CommonException;
import org.travis.common.utils.RequestInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.NestedServletException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @ClassName CommonExceptionAdvice
 * @Description 统一异常处理类
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionAdvice {

    @ExceptionHandler(CommonException.class)
    public Object handleDatabaseException(CommonException exception) {
        log.error("[自定义异常] -> 异常类:{}, 状态码:{}, 异常信息:", exception.getClass().getName(), exception.getCode(), exception);
        return processResponse(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(RpcException.class)
    public Object handleDatabaseException(RpcException exception) {
        log.error("[Dubbo远程调用异常] -> 异常类:{}, 状态码:{}, 异常信息:", exception.getClass().getName(), exception.getCode(), exception);
        return processResponse(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        // 拼接请求参数校验异常信息
        String message = exception.getBindingResult().getAllErrors()
                .stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("|"));
        log.error("[请求参数校验异常] -> 异常类:{}, 状态码:{}, 异常信息:", exception.getClass().getName(), 400, exception);
        return processResponse(BizCodeEnum.BAD_REQUEST.getCode(), message);
    }

    @ExceptionHandler(BindException.class)
    public Object handleBindException(BindException exception) {
        log.error("[请求参数绑定异常] -> 异常类:{}, 状态码:{}, 异常信息:", exception.getClass().getName(), 400, exception);
        return processResponse(BizCodeEnum.BAD_REQUEST.getCode(), exception.getMessage());
    }

    @ExceptionHandler(NestedServletException.class)
    public Object handleNestedServletException(NestedServletException exception) {
        log.error("[嵌套服务异常] -> 异常类:{}, 状态码:{}, 异常信息:", exception.getClass().getName(), 400, exception);
        return processResponse(BizCodeEnum.BAD_REQUEST.getCode(), exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Object handViolationException(ConstraintViolationException exception) {
        // 拼接异常信息
        String message = exception.getConstraintViolations()
                .stream().map(ConstraintViolation::getMessage)
                .distinct().collect(Collectors.joining("|"));
        log.error("[约束违反异常] -> 异常类:{}, 状态码:{}, 异常信息:", exception.getClass().getName(), 400, exception);
        return processResponse(BizCodeEnum.BAD_REQUEST.getCode(), message);
    }

    @ExceptionHandler(Exception.class)
    public Object handleRuntimeException(Exception exception) {
        log.error("[未知异常] -> 异常类:{}, 状态码:{}, URI:{}, 异常信息:",
                exception.getClass().getName(),
                BizCodeEnum.UNKNOW.getCode(),
                RequestInfoUtil.getRequest() != null ? RequestInfoUtil.getRequest().getRequestURI() : "NULL",
                exception
        );
        return processResponse(BizCodeEnum.INTERNAL_SERVER_ERROR.getCode(), BizCodeEnum.INTERNAL_SERVER_ERROR.getMessage());
    }


    private Object processResponse(int code, String msg){
        // 1.标记异常响应已处理（避免重复处理）
        RequestInfoUtil.setResponseHeader(SystemConstant.BODY_PROCESSED_MARK_HEADER, "true");
        // 2.如果是网关请求，包装错误响应请求，前端基于业务状态码code来判断状态。如果是微服务请求，http状态码基于异常原样返回，微服务自己做 fallback 处理。
        return RequestInfoUtil.isGatewayRequest() ? R.error(code, msg) : ResponseEntity.status(code).body(msg);
    }
}
