package org.travis.common.exceptions;

import lombok.Getter;

/**
 * @ClassName BadRequestException
 * @Description 请求参数异常类, 400
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
@Getter
public class BadRequestException extends CommonException {

    private static final int CODE = 400;

    public BadRequestException(String message) {
        super(CODE, message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(CODE, message, cause);
    }
}
