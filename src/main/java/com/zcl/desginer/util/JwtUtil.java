package com.zcl.desginer.util;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @title: JwtUti
 * @Author chenglin.zou
 * @Date: 2022/5/21 16:57
 * @description
 * @Version 1.0
 */
public class JwtUtil {
    /**
     * 生成token
     * 
     * @param subject
     * @return
     */
    public static String createToken(String subject) {
        Date nowDate = new Date();
        Date expireDate = DateUtils.addSeconds(nowDate, 30);
        return Jwts.builder().setHeaderParam("typ", "JWT").setSubject(subject).setIssuedAt(nowDate)
            .setExpiration(expireDate).signWith(SignatureAlgorithm.HS512, "123".getBytes()).compact();
    }

    /**
     * 解码token
     * 
     * @author chenglin.zou
     * @date: 2022/5/22 18:51
     **/
    public static String decryptToken(String token) {
        DecodedJWT decode = JWT.decode(token);
        decode.getSignature();
        return Jwts.parser().setSigningKey("123".getBytes()).parse(token).getBody().toString();
    }
}
