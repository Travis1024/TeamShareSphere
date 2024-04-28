package org.travis.auth.resource.filter.consumer;

import cn.dev33.satoken.same.SaSameUtil;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.cluster.filter.ClusterFilter;

/**
 * @ClassName DubboSameTokenFilter
 * @Description 服务内部 SameToken Dubbo 鉴权
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/28
 */
@Slf4j
@Activate(group = CommonConstants.CONSUMER, order = 1)
public class DubboSameTokenFilter implements ClusterFilter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 为 Dubbo 的 RCP 调用 添加隐式参数 Same-Token
        RpcContext.getClientAttachment().setAttachment(SaSameUtil.SAME_TOKEN, SaSameUtil.getToken());
        // 为保持被调用方的会话状态，将 sa-token 添加到隐式参数中
        RpcContext.getClientAttachment().setAttachment(StpUtil.getTokenName(), StpUtil.getTokenValue());
        log.info("[Dubbo-Filter] 添加 Same-Token：{}", SaSameUtil.getToken());
        return invoker.invoke(invocation);
    }
}
