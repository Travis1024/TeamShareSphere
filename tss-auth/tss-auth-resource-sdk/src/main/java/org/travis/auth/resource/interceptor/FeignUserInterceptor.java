package org.travis.auth.resource.interceptor;

import cn.hutool.log.Log;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.travis.common.constants.SystemConstant;
import org.travis.common.utils.UserThreadLocalUtil;

/**
 * @ClassName FeignUserInterceptor
 * @Description Feign「用户ID」处理拦截器
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
public class FeignUserInterceptor implements RequestInterceptor {

    /**
     * RequestInterceptor是 Feign 中的重要组件，用于在发送 feign 请求之前对请求进行拦截和处理
     * apply 方法在发送请求之前被调用
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 1.判断线程上下文中是否有用户信息
        Long userId = UserThreadLocalUtil.getUserId();
        // 2.将用户ID写入请求头中
        if (userId != null) {
            requestTemplate.header(SystemConstant.USER_ID_HEADER, String.valueOf(userId));
        }
    }
}
