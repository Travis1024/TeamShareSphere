package org.travis.team.service.dubbo;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.travis.api.client.team.TeamClient;
import org.travis.api.dto.team.UserCheckInfoDTO;
import org.travis.common.domain.R;
import org.travis.common.enums.BizCodeEnum;
import org.travis.common.exceptions.BadRequestException;
import org.travis.team.service.UserService;

import javax.annotation.Resource;

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
    @Resource
    private UserService userService;

    @Override
    public R<Long> checkUserInfoAndPassword(UserCheckInfoDTO userCheckInfoDTO) {
        log.warn("请求用户名密码校验:{}", DateUtil.date());
        try {
            Long userId = userService.checkPassword(userCheckInfoDTO.getUsername(), userCheckInfoDTO.getPassword());
            return R.ok(userId);
        } catch (RuntimeException e) {
            return R.error(BizCodeEnum.BAD_REQUEST.getCode(), e.getMessage());
        }
    }
}
