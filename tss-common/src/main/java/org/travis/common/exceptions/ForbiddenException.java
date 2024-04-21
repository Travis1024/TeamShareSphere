package org.travis.common.exceptions;

/**
 * @ClassName ForbiddenException
 * @Description 权限异常类
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
public class ForbiddenException extends CommonException{
    public ForbiddenException(int code, String message) {
        super(code, message);
    }

    public ForbiddenException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
