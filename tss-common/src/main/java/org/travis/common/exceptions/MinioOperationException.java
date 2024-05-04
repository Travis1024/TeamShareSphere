package org.travis.common.exceptions;

import org.travis.common.enums.BizCodeEnum;

/**
 * @ClassName MinioOperationException
 * @Description Minio操作失败异常类
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/3
 */
public class MinioOperationException extends CommonException {

    private static final int CODE = BizCodeEnum.MINIO_OPERATION_FAILED.getCode();
    private static final String MESSAGE = BizCodeEnum.MINIO_OPERATION_FAILED.getMessage();

    public MinioOperationException() {
        this(MESSAGE);
    }

    public MinioOperationException(String message) {
        this(CODE, message);
    }

    public MinioOperationException(int code, String message) {
        super(code, message);
    }

}
