package org.travis.common.exceptions;

import lombok.Getter;

/**
 * @ClassName CommonException
 * @Description 统一异常的父类
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
@Getter
public class CommonException extends RuntimeException {

    private final int code;

    public CommonException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CommonException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
