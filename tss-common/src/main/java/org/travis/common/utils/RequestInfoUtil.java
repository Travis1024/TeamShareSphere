package org.travis.common.utils;

import cn.hutool.core.util.StrUtil;
import org.travis.common.constants.SystemConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @ClassName RequestInfoUtil
 * @Description 请求工具类
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
@Slf4j
public class RequestInfoUtil {

    /**
     * 获取ServletRequestAttributes
     *
     * @return ServletRequestAttributes
     */
    public static ServletRequestAttributes getServletRequestAttributes() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        return (ServletRequestAttributes) requestAttributes;
    }

    /**
     * 获取request
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = getServletRequestAttributes();
        return servletRequestAttributes == null ? null : servletRequestAttributes.getRequest();
    }

    /**
     * 获取response
     *
     * @return HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes servletRequestAttributes = getServletRequestAttributes();
        return servletRequestAttributes == null ? null : servletRequestAttributes.getResponse();
    }

    /**
     * 获取request header中的内容
     *
     * @param headerName 请求头名称
     * @return 请求头的值
     */
    public static String getHeader(String headerName) {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        return getRequest().getHeader(headerName);
    }

    /**
     * 判断当前请求是否来自网关路由
     * @return true：来自网关模块
     */
    public static boolean isGatewayRequest() {
        String originName = getHeader(SystemConstant.REQUEST_FROM_HEADER);
        return SystemConstant.GATEWAY_ORIGIN_NAME.equals(originName);
    }

    /**
     * 添加响应头信息
     * @param key 响应头 Key
     * @param value 响应头 Value
     */
    public static void setResponseHeader(String key, String value){
        HttpServletResponse response = getResponse();
        if (response == null) {
            return;
        }
        response.setHeader(key, value);
    }

    /**
     * 获取请求地址
     */
    public static String getRemoteAddr() {
        HttpServletRequest request = getRequest();
        return request == null ? StrUtil.EMPTY : request.getRemoteAddr();
    }
}
