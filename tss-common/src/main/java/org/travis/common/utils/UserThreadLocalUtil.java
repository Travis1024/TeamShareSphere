package org.travis.common.utils;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * @ClassName UserThreadLocalUtil
 * @Description 线程上下文工具类（存储userId）
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
public class UserThreadLocalUtil {
    private static final TransmittableThreadLocal<Long> USER_THREAD_LOCAL = new TransmittableThreadLocal<>();

    /**
     * 线程上下文保存用户ID信息
     * @param userId 用户ID
     */
    public static void setUserId(Long userId) {
        USER_THREAD_LOCAL.set(userId);
    }

    /**
     * 获取线程上下文中的用户ID信息
     */
    public static Long getUserId() {
        return USER_THREAD_LOCAL.get();
    }

    /**
     * 清空线程上下文中的用户ID信息
     */
    public static void removeUserId() {
        USER_THREAD_LOCAL.remove();
    }
}
