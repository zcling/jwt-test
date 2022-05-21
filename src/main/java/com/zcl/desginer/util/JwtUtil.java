package com.zcl.desginer.util;


import java.util.Date;

/**
 * @title: JwtUti
 * @Author chenglin.zou
 * @Date: 2022/5/21 16:57
 * @description
 * @Version 1.0
 */
public class JwtUtil {
    private String secret;
    private long expire;
    private String header;

    /**
     * 生成token
     * @param subject
     * @return
     */
    /*public String createToken (String subject){
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);//过期时间

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(subject)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }*/
}
