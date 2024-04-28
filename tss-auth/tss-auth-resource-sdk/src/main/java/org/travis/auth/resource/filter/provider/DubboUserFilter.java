package org.travis.auth.resource.filter.provider;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.travis.common.constants.SystemConstant;
import org.travis.common.utils.UserThreadLocalUtil;

/**
 * @ClassName DubboUserFilter
 * @Description Dubbo 「用户ID」
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/28
 */
@Slf4j
@Activate(group = CommonConstants.PROVIDER, order = 2)
public class DubboUserFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 1.获取隐式参数中的用户ID
        String userIdStr = RpcContext.getServerAttachment().getAttachment(SystemConstant.USER_ID_HEADER);
        // 2.添加用户ID 到 ThreadLocal 中
        if (StrUtil.isNotEmpty(userIdStr)) {
            Long userId = Long.parseLong(userIdStr);
            UserThreadLocalUtil.setUserId(userId);
        }
        log.info("[Dubbo-Provider-Filter] 添加用户 ID 到 ThreadLocal 中：{}", userIdStr);
        return invoker.invoke(invocation);
    }
}
