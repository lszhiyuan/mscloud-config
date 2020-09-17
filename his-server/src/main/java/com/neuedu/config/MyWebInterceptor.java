package com.neuedu.config;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuedu.pojo.UmsPermission;
import com.neuedu.service.IRedisService;
import com.neuedu.service.IUmsPermissionService;
import com.neuedu.util.CommonResult;
import com.neuedu.util.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Component
public class MyWebInterceptor extends HandlerInterceptorAdapter {

    @Resource
    IRedisService redisService;

    @Resource
    IUmsPermissionService umsPermissionService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 返回true 就是可以 通过  返回false 就是不能通过
//        System.out.println("进入拦截器");
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String token = request.getHeader("token");

//        System.out.printf("uri:%s\t,method:%s\t,token:%s\r\n",
//                uri,
//                method,
//                token
//                );


        //1 如果没有token ，或者再ssdb中用户不存在， // NOTOKEN
        if(StringUtils.isBlank(token)){
            writeMsg(response,CommonResult.notoken());
            return false;
        }


        //2 获取ssdb中的 密码 跟 token中的密码是否一致，//NOTOKEN
        //3 客户端的登录时间 和 ssdb中的登录时间 不一致（被其它人登录过）//NOTOKEN
        //获取token中的username，根据key 获取 ssdb中的登录信息
        String username = JWT.decode(token).getClaim("username").asString();
        Integer userId = JWT.decode(token).getClaim("id").asInt();
        String ssdbKey = JWTUtil.KEY +"-"+username;
        if(redisService.hashkey(ssdbKey)){
            //
            String password = JWT.decode(token).getClaim("password").asString();
            Long timestamp = JWT.decode(token).getClaim("timestamp").asLong();

            String userJson = redisService.get(ssdbKey);
            ObjectMapper objectMapper =  new ObjectMapper();
            Map userMap = objectMapper.readValue(userJson, Map.class);

            long lastlogin = (long) userMap.get("lastlogin");

            if(!password.equals(userMap.get("password"))
                    || timestamp.longValue() != lastlogin){
                writeMsg(response,CommonResult.notoken());
                return false;
            }

        }else{
            writeMsg(response,CommonResult.notoken());
            return false;
        }



        //从ssdb缓存中获取权限列表, 如果没有，从数据库中获取，并放到ssdb中


        //4  用户-角色与访问的url权限不符， // NOPERMISS
        if(!validateUriPermission(username,userId,uri)){
            writeMsg(response,CommonResult.nopremiss());
            return false;
        }


        //5 更新下 有效期
        redisService.expire(ssdbKey, 60 * 30);
        return true;
    }


    /**
     * 响应json 返回内容
     * @param response
     * @param result
     * @throws IOException
     */
    private void writeMsg(HttpServletResponse response, CommonResult result) throws IOException {
        //result  --json
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result);

        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();

    }


    private boolean validateUriPermission(String username ,Integer userId,String uri) throws IOException {
        //查询权限列表
        List<UmsPermission> umsPermissionList = null;

        //从ssdb缓存数据库中获取当前用户的权限列表

        //
        ObjectMapper objectMapper = new ObjectMapper();
        String json = redisService.get(JWTUtil.PERMISSION_KEY+"_"+username);
        if(StringUtils.isBlank(json)){
            //从数据库中获取权限列表
            umsPermissionList = umsPermissionService.userPermissionListValidate(userId);
            //并序列化成json，放到ssdb中

            json = objectMapper.writeValueAsString(umsPermissionList);
            redisService.set(JWTUtil.PERMISSION_KEY+"_"+username,json,30*60);

        }else{
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class,UmsPermission.class);
            umsPermissionList = objectMapper.readValue(json,javaType);

        }

        for (UmsPermission permission : umsPermissionList) {
            if(uri.equals(permission.getUrl())){
                return true;
            }
        }


        return true;
    }


}
