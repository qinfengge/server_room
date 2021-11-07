package xyz.qinfengge.serversp.util;

import io.jsonwebtoken.*;
import xyz.qinfengge.serversp.entity.User;

import java.util.Date;
import java.util.UUID;

/**
 * @author lizhiao
 * @version 1.0
 * @date 2021/11/7 17:56
 */
public class JwtUtil {

    public static long time = 1000*10*30;
    public static String signature = "8fv12Fg";

    public static String generateToken(User user) {
        JwtBuilder jwtBuilder = Jwts.builder();
        String JwtToken = jwtBuilder
                //header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //payload
                .setSubject("user")
                .claim("username", user.getUsername())
                .claim("password", user.getPassword())
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
        return JwtToken;
    }

    public static Boolean checkToken(String token){
        if (token == null){
            return false;
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signature).parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
