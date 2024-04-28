package org.travis.auth.main.mock;

import org.travis.api.client.team.TeamClient;
import org.travis.api.dto.team.UserCheckInfoDTO;
import org.travis.common.domain.R;
import org.travis.common.enums.BizCodeEnum;

/**
 * @ClassName TeamClientMock
 * @Description TeamClientMock
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/28
 */
public class TeamClientMock implements TeamClient {
    @Override
    public R<Long> checkUserInfoAndPassword(UserCheckInfoDTO userCheckInfoDTO) {
        return R.error(BizCodeEnum.DEGRADED_SERVICE.getCode(), BizCodeEnum.DEGRADED_SERVICE.getMessage());
    }
}
