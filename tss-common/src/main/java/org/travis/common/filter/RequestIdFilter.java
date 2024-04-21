package org.travis.common.filter;

import org.springframework.core.annotation.Order;
import org.travis.common.constants.SystemConstant;
import org.slf4j.MDC;
import org.springframework.core.Ordered;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName RequestIdFilter
 * @Description 请求 ID 过滤器
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@WebFilter(filterName = "RequestIdFilter", urlPatterns = "/**")
public class RequestIdFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1.获取 request
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 2.获取请求头中的 requestId
        String requestId = request.getHeader(SystemConstant.REQUEST_ID_HEADER);
        try {
            // 3.将 requestId 存入 MDC
            MDC.put(SystemConstant.REQUEST_ID_HEADER, requestId);
            // 4.执行业务方法
            filterChain.doFilter(request, servletResponse);
        } finally {
            // 5.移除 MDC
            MDC.clear();
        }
    }
}
