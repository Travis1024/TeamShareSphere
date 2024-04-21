package org.travis.auth.resource.interceptor;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.travis.common.constants.SystemConstant;
import org.travis.common.utils.UserThreadLocalUtil;

/**
 * @ClassName UserInfoInterceptor
 * @Description 用户信息拦截器
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
@Slf4j
public class UserInfoInterceptor implements HandlerInterceptor {

    /**
     * 注意：当前拦截器不进行拦截，全部放行，只解析用户信息并存储到线程上下文，后续拦截器再判断当前请求URL是否需要用户登录
     */
    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        // 1.尝试获取请求头中的用户信息 (userId)
        String userIdStr = request.getHeader(SystemConstant.USER_ID_HEADER);
        if (StrUtil.isEmpty(userIdStr)) {
            return true;
        }
        // 2.转为Long类型用户ID并保存
        try {
            Long userId = Long.parseLong(userIdStr);
            UserThreadLocalUtil.setUserId(userId);
            return true;
        } catch (NumberFormatException e) {
            log.error("[UserInfoInterceptor] - 用户身份信息格式非法！用户信息：{}, 错误：{}", userIdStr, e.getMessage());
            return true;
        }
    }

    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清除线程上下文中的用户ID信息
        UserThreadLocalUtil.removeUserId();
    }
}
