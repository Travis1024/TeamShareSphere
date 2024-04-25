package org.travis.common.exceptions;

import org.travis.common.enums.BizCodeEnum;

/**
 * @ClassName ForbiddenException
 * @Description 权限异常类
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
public class ForbiddenException extends CommonException{

    private static final int CODE = BizCodeEnum.TOKEN_CHECK_FAILED.getCode();
    private static final String MESSAGE = BizCodeEnum.TOKEN_CHECK_FAILED.getMessage();


    public ForbiddenException() {
        this(MESSAGE);
    }

    public ForbiddenException(String message) {
        this(CODE, message);
    }

    public ForbiddenException(int code, String message) {
        super(code, message);
    }
}
