// package org.travis.api.client.team.fallback;
//
// import cn.hutool.json.JSONUtil;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.cloud.openfeign.FallbackFactory;
// import org.springframework.stereotype.Component;
// import org.travis.api.client.team.TeamClient;
// import org.travis.api.dto.team.UserCheckInfoDTO;
// import org.travis.common.domain.R;
// import org.travis.common.enums.BizCodeEnum;
//
// /**
//  * @ClassName TeamClientFallback
//  * @Description TeamClientFallback
//  * @Author travis-wei
//  * @Version v1.0
//  * @Data 2024/4/22
//  */
// @Slf4j
// @Component
// public class TeamClientFallback implements FallbackFactory<TeamClient> {
//     @Override
//     public TeamClient create(Throwable cause) {
//         log.error("[Feign-TeamClient] Call exception! Details: ", cause);
//         return new TeamClient() {
//             @Override
//             public String checkUserInfoAndPassword(UserCheckInfoDTO userCheckInfoDTO) {
//                 return JSONUtil.toJsonStr(R.error(BizCodeEnum.DEGRADED_SERVICE.getCode(), BizCodeEnum.DEGRADED_SERVICE.getMessage()));
//             }
//         };
//     }
// }
