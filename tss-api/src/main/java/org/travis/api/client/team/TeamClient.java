package org.travis.api.client.team;

import org.springframework.web.bind.annotation.RequestBody;
import org.travis.api.dto.team.UserCheckInfoDTO;
import org.travis.common.domain.R;

/**
 * @ClassName TeamClient
 * @Description TeamClient
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/22
 */
public interface TeamClient {
    R<Long> checkUserInfoAndPassword(@RequestBody UserCheckInfoDTO userCheckInfoDTO);
}
