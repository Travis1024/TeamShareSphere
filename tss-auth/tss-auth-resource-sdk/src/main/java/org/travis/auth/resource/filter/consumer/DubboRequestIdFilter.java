package org.travis.auth.resource.filter.consumer;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.cluster.filter.ClusterFilter;
import org.slf4j.MDC;
import org.travis.common.constants.SystemConstant;

/**
 * @ClassName DubboRequestIdFilter
 * @Description Dubbo「请求ID」处理过滤器
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/28
 */
@Slf4j
@Activate(group = CommonConstants.CONSUMER, order = 0)
public class DubboRequestIdFilter implements ClusterFilter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 1.获取「请求ID」
        String requestId = MDC.get(SystemConstant.REQUEST_ID_HEADER);

        /**
         * Dubbo-调用链路传递隐式参数
         * <a>https://cn.dubbo.apache.org/zh-cn/overview/mannual/java-sdk/advanced-features-and-usage/service/attachment/</a>
         */
        // 2.如果「请求ID」不为空，添加到隐式参数中
        if (StrUtil.isNotEmpty(requestId)) {
            RpcContext.getClientAttachment().setAttachment(SystemConstant.REQUEST_ID_HEADER, requestId);
        }
        log.info("[Dubbo-Filter] 获取请求ID：{}", requestId);
        return invoker.invoke(invocation);
    }
}
