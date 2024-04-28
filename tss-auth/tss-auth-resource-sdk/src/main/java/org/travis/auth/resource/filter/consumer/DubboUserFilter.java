package org.travis.auth.resource.filter.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.cluster.filter.ClusterFilter;
import org.travis.common.constants.SystemConstant;
import org.travis.common.utils.UserThreadLocalUtil;

/**
 * @ClassName DubboUserFilter
 * @Description Dubbo「用户ID」处理过滤器
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/28
 */
@Slf4j
@Activate(group = CommonConstants.CONSUMER, order = 2)
public class DubboUserFilter implements ClusterFilter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 1.判断线程上下文中是否有用户信息
        Long userId = UserThreadLocalUtil.getUserId();
        // 2.将用户 ID 添加到隐式参数中
        if (userId != null) {
            RpcContext.getClientAttachment().setAttachment(SystemConstant.USER_ID_HEADER, userId);
        }
        log.info("[Dubbo-Filter] 添加用户ID：{}", userId);
        return invoker.invoke(invocation);
    }
}
