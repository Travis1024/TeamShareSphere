package org.travis.common.exceptions;

import org.checkerframework.checker.units.qual.C;
import org.travis.common.enums.BizCodeEnum;

/**
 * @ClassName DatabaseOperationException
 * @Description 数据库操作失败异常类
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/3
 */
public class DatabaseOperationException extends CommonException {

    private static final int CODE = BizCodeEnum.DATABASE_OPERATION_FAILED.getCode();
    private static final String MESSAGE = BizCodeEnum.DATABASE_OPERATION_FAILED.getMessage();

    public DatabaseOperationException() {
        this(MESSAGE);
    }

    public DatabaseOperationException(String message) {
        this(CODE, message);
    }

    public DatabaseOperationException(int code, String message) {
        super(code, message);
    }

}
