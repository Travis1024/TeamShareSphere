package org.travis.auth.resource.filter.provider;

import cn.dev33.satoken.same.SaSameUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.travis.common.enums.BizCodeEnum;

/**
 * @ClassName DubboSameTokenFilter
 * @Description 服务间内部调用鉴权
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/28
 */
@Slf4j
@Activate(group = CommonConstants.PROVIDER, order = 1)
public class DubboSameTokenFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 1.获取隐式参数中的「Same-Token」
        String sameToken = RpcContext.getServerAttachment().getAttachment(SaSameUtil.SAME_TOKEN);
        // 2.校验 Same-Token 身份凭证，服务间内部调用鉴权
        try {
            SaSameUtil.checkToken(sameToken);
        } catch (Exception e) {
            log.error("服务内部调用鉴权失败：{}", e.getMessage());
            throw new RpcException(BizCodeEnum.INTERNAL_TOKEN_CHECK_FAILED.getCode(), "[Dubbo]-服务内部调用鉴权失败");
        }
        log.info("[Dubbo-Provider-Filter] 服务内部调用鉴权：{}", sameToken);
        return invoker.invoke(invocation);
    }
}
