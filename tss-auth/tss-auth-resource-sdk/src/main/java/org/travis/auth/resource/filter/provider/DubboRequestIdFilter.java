package org.travis.auth.resource.filter.provider;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.MDC;
import org.travis.common.constants.SystemConstant;

/**
 * @ClassName DubboRequestIdFilter
 * @Description Dubbo 处理「请求ID」
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/28
 */
@Slf4j
@Activate(group = CommonConstants.PROVIDER, order = 0)
public class DubboRequestIdFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 1.获取隐式参数中的「请求ID」
        String requestId = RpcContext.getServerAttachment().getAttachment(SystemConstant.REQUEST_ID_HEADER);
        // 2.将「请求ID」添加到 MDC
        if (StrUtil.isNotEmpty(requestId)) {
            MDC.put(SystemConstant.REQUEST_ID_HEADER, requestId);
        }
        log.info("[Dubbo-Provider-Filter] 添加请求ID：{}", requestId);
        return invoker.invoke(invocation);
    }
}
