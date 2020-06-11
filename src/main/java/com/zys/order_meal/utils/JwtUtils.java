package com.zys.order_meal.utils;

import com.zys.order_meal.enums.ResultEnum;
import com.zys.order_meal.exception.CustomException;
import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * 生成token工具类
 */

@Component
@Data
@ConfigurationProperties("jwt.config")
public class JwtUtils {

    //签名私钥
    private String key;
    //签名失效时间
    private Long failureTime;

    //公用密钥-保存在服务端,客户端是不会知道密钥的,以防被攻击
    public static String SECRET = "ThisIsASecret";

    /**
     * 设置认证token
     *
     * @param id      用户登录ID
     * @param subject 用户登录名
     * @param map     其他私有数据
     * @return
     */
    public String createJwt(String id, String subject, Map<String, Object> map) {

        //1、设置失效时间啊
        long now = System.currentTimeMillis();  //毫秒
        long exp = now + failureTime;

        //2、创建JwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder().setId(id).setSubject(subject)
                .setIssuedAt(new Date())
                //设置签名防止篡改
                .signWith(SignatureAlgorithm.HS256, key);

        //3、根据map设置claims
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            jwtBuilder.claim(entry.getKey(), entry.getValue());
        }
        jwtBuilder.setExpiration(new Date(exp));

        //4、创建token
        String token = jwtBuilder.compact();
        return token;
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public Claims parseJwt(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            return claims;
        }
        catch (ExpiredJwtException eje) {
            System.out.println("===== Token过期 =====");
            throw new CustomException(ResultEnum.PERMISSION_TOKEN_EXPIRED);
        } catch (Exception e){
            System.out.println("===== token解析异常 =====");
            throw new CustomException(ResultEnum.PERMISSION_TOKEN_INVALID);
        }
    }
}
