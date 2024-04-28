// package org.travis.auth.resource.interceptor;
//
// import cn.hutool.core.util.StrUtil;
// import feign.RequestInterceptor;
// import feign.RequestTemplate;
// import org.slf4j.MDC;
// import org.travis.common.constants.SystemConstant;
//
// /**
//  * @ClassName FeignRequestIdInterceptor
//  * @Description Feign「请求ID」处理拦截器
//  * @Author travis-wei
//  * @Version v1.0
//  * @Data 2024/4/21
//  */
// public class FeignRequestIdInterceptor implements RequestInterceptor {
//     @Override
//     public void apply(RequestTemplate requestTemplate) {
//         // 1.获取 请求ID
//         String requestId = MDC.get(SystemConstant.REQUEST_ID_HEADER);
//         // 2.如果「请求ID」不为空，添加到 feign 请求头中
//         if (StrUtil.isNotEmpty(requestId)) {
//             requestTemplate.header(SystemConstant.REQUEST_ID_HEADER, requestId);
//         }
//     }
// }
