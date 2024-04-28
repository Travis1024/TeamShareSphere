package org.travis.team.service.dubbo;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.travis.api.client.team.TeamClient;
import org.travis.api.dto.team.UserCheckInfoDTO;
import org.travis.common.domain.R;
import org.travis.common.enums.BizCodeEnum;

/**
 * @ClassName TeamClientImpl
 * @Description TeamClient Dubbo 服务提供者
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/27
 */
@Slf4j
@DubboService(timeout = 3000)
public class TeamClientImpl implements TeamClient {
    @Override
    public R<Long> checkUserInfoAndPassword(UserCheckInfoDTO userCheckInfoDTO) {
        log.warn("请求用户名密码校验:{}", DateUtil.date());

        if ("throw".equals(userCheckInfoDTO.getUsername())) {
            throw new RuntimeException("测试抛出异常");
        }

        if ("admin".equals(userCheckInfoDTO.getUsername()) && "123456".equals(userCheckInfoDTO.getPassword())) {
            return R.ok(12879709126912940L);
        } else {
            return R.error(BizCodeEnum.BAD_REQUEST.getCode(), "用户名或密码错误!");
        }
    }
}
