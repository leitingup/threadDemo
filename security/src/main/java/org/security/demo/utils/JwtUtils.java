package org.security.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.HashMap;

/**
 * 主要用于生成jwt token串和获取token串中的载荷值。
 * */
public class JwtUtils {
    private static final String jwtClaimKey="tokenObj-key";
    private static final String jwtSecretKet="jwtSecret-key";


    /**
    *@Description 生成jwt的token串
    *@Param [value]
    *@Return java.lang.String
    */
    public static String createJwtToken(String value){
        HashMap<String,Object> claims = new HashMap<>();
        claims.put(jwtClaimKey,value);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY,24);//token在24小时后过期
        return Jwts.builder()
                .setClaims(claims)//设置载荷部分
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS512,jwtSecretKet)//设置加密算法
                .compact();
    }

    public static String getJwtTokenClaimValue(String tokenStr){
        String result = null;
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecretKet)
                    .parseClaimsJws(tokenStr)
                    .getBody();
            if (claims.getExpiration().compareTo(Calendar.getInstance().getTime()) > 0) {
                //token 未过期
                result = claims.get(jwtClaimKey, String.class);
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return result;

    }
}
