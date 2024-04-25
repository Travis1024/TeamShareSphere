package org.travis.api.client.team;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.travis.api.client.team.fallback.TeamClientFallback;
import org.travis.api.dto.team.UserCheckInfoDTO;

/**
 * @ClassName TeamClient
 * @Description TeamClient
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/22
 */
@Component
@FeignClient(value = "team-service", fallbackFactory = TeamClientFallback.class)
public interface TeamClient {
    @PostMapping(value = "/user/check")
    String checkUserInfoAndPassword(@RequestBody UserCheckInfoDTO userCheckInfoDTO);
}
