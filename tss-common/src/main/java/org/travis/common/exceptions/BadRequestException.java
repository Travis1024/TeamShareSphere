package org.travis.common.exceptions;

import lombok.Getter;
import org.travis.common.enums.BizCodeEnum;

/**
 * @ClassName BadRequestException
 * @Description 请求参数异常类, 400
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
@Getter
public class BadRequestException extends CommonException {

    private static final int CODE = BizCodeEnum.BAD_REQUEST.getCode();
    private static final String MESSAGE = BizCodeEnum.BAD_REQUEST.getMessage();

    public BadRequestException() {
        this(MESSAGE);
    }

    public BadRequestException(String message) {
        this(CODE, message);
    }

    public BadRequestException(int code, String message) {
        super(code, message);
    }
}
