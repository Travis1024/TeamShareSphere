package org.travis.file.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.travis.common.constants.SystemConstant;
import org.travis.common.utils.UserThreadLocalUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName OnceTokenUtil
 * @Description OnceTokenUtil
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/6
 */
@Component
public class OnceTokenUtil {

    @Value("${tss-file.jwt.secretKey}")
    private String secretKey;

    public String getOnceToken() {
        DateTime now = DateTime.now();
        DateTime newTime = now.offsetNew(DateField.MINUTE, 30);

        Map<String,Object> payload = new HashMap<>();
        // 签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        // 过期时间
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        // 生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        // 载荷-userId
        payload.put(SystemConstant.USER_ID_HEADER, UserThreadLocalUtil.getUserId());

        return JWTUtil.createToken(payload, secretKey.getBytes());
    }

    public boolean verifyToken(String token) {
        try {
            // 0、校验 token 是否为空
            if (StrUtil.isEmpty(token)) {
                return false;
            }
            JWT jwt = JWTUtil.parseToken(token);
            // 1、解析并验证 token
            boolean verified = jwt.setKey(secretKey.getBytes()).verify();
            if (!verified) {
                return false;
            }
            // 2、校验用户ID
            String tokenUserId = jwt.getPayload(SystemConstant.USER_ID_HEADER).toString();
            if (!UserThreadLocalUtil.getUserId().toString().equals(tokenUserId)) {
                return false;
            }
            // 3、校验是否过期
            return jwt.validate(0);
        } catch (Exception e ) {
            return false;
        }
    }
}
