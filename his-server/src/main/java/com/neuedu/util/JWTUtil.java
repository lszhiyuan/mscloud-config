package com.neuedu.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.neuedu.pojo.UmsUser;

import java.time.ZoneOffset;

public class JWTUtil {
    public static final String KEY = "umsuser";
    public static final String PERMISSION_KEY = "umspermission";

    public static String create(UmsUser umsUser) {
        return JWT.create()
                .withClaim("username",umsUser.getUsername())
                .withClaim("password",umsUser.getPassword())

                .withClaim("id",umsUser.getId())                //员工的id
                .withClaim("deptId",umsUser.getDeptId())        //部门的id

                .withClaim("timestamp",umsUser.getLastlogin().toInstant(ZoneOffset.of("+8")).toEpochMilli())
                .sign(Algorithm.HMAC256(KEY));
    }

    public static void main(String[] args) {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IiQyYSQxMCRvN2hkNzVQY1VkLjFySjcwYmZiR0V1NWFCZEIwTzV6SnNNQlpvNzFwbXZuMXVqNmNZZU8zVyIsImRlcHRJZCI6MiwiaWQiOjIsInVzZXJuYW1lIjoidXNlcjEiLCJ0aW1lc3RhbXAiOjE1OTg1ODIyNjgwNzF9.xn5dNbmW60lOrZncUo3IZg6mcUanOOFb92LV904moTk";
        DecodedJWT decode = JWT.decode(token);

        String id = decode.getClaim("id").asString();
        String deptId = decode.getClaim("deptId").asString();
        System.out.println("id:"+id);
        System.out.println("deptId:"+deptId);

    }
}
