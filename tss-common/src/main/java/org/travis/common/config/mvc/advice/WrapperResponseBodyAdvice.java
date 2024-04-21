package org.travis.common.config.mvc.advice;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.travis.common.constants.SystemConstant;
import com.travis.common.domain.R;
import com.travis.common.utils.RequestUtil;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @ClassName WrapperResponseBodyAdvice
 * @Description 响应信息包装类（R）
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/1/18
 */
@RestControllerAdvice
public class WrapperResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    /**
     * 判断响应信息是否需要包装
     *
     * @param returnType
     * @param converterType
     * @return true:执行 beforeBodyWrite 进行包装 | false：不执行 beforeBodyWrite
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 如果当前响应信息已经被 R 类包装 && 当前请求来自网关路由 返回 true，执行 beforeBodyWrite
        return returnType.getParameterType() != R.class && RequestUtil.isGatewayRequest();
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 1.判断是否是来自 swagger 的请求
        if (request.getURI().getPath().startsWith("/v3")) {
            return body;
        }
        // 2.判断响应体是否为空
        if (ObjectUtil.isEmpty(body)) {
            return R.ok().requestId(MDC.get(SystemConstant.REQUEST_ID_HEADER));
        }
        // 3.判断响应体是否已经被 R 包装
        if (body instanceof R) {
            return body;
        }
        // 4.判断返回类型是否是字符串（单独处理）
        if (returnType.getParameterType().isAssignableFrom(String.class)) {
            return JSONUtil.toJsonStr(R.ok(body).requestId(MDC.get(SystemConstant.REQUEST_ID_HEADER)));
        }
        // 5.其他无报错请求,包装 R
        return R.ok(body).requestId(MDC.get(SystemConstant.REQUEST_ID_HEADER));
    }
}
