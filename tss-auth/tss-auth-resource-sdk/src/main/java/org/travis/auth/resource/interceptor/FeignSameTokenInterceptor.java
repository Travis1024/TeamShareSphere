// package org.travis.auth.resource.interceptor;
//
// import cn.dev33.satoken.same.SaSameUtil;
// import cn.dev33.satoken.stp.StpUtil;
// import feign.RequestInterceptor;
// import feign.RequestTemplate;
//
// /**
//  * @ClassName FeignSameTokenInterceptor
//  * @Description 服务内部 SameToken feign 鉴权
//  * @Author travis-wei
//  * @Version v1.0
//  * @Data 2024/4/22
//  */
// public class FeignSameTokenInterceptor implements RequestInterceptor {
//     @Override
//     public void apply(RequestTemplate requestTemplate) {
//         // 为 Feign 的 RCP 调用 添加请求头 Same-Token
//         requestTemplate.header(SaSameUtil.SAME_TOKEN, SaSameUtil.getToken());
//         // 为保持被调用方的会话状态，将 sa-token 添加到请求头中
//         requestTemplate.header(StpUtil.getTokenName(), StpUtil.getTokenValue());
//     }
// }
