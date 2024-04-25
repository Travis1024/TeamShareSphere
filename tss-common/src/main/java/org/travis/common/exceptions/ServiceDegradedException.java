package org.travis.common.exceptions;

import lombok.Getter;
import org.travis.common.enums.BizCodeEnum;

/**
 * @ClassName ServiceDegradedException
 * @Description 服务降级异常
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/25
 */
@Getter
public class ServiceDegradedException extends CommonException{

    private static final int CODE = BizCodeEnum.DEGRADED_SERVICE.getCode();
    private static final String MESSAGE = BizCodeEnum.DEGRADED_SERVICE.getMessage();


    public ServiceDegradedException() {
        this(MESSAGE);
    }

    public ServiceDegradedException(String message) {
        this(CODE, message);
    }

    public ServiceDegradedException(int code, String message) {
        super(code, message);
    }
}
