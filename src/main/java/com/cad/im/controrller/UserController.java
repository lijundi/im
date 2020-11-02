package com.cad.im.controrller;

import com.alibaba.fastjson.JSONObject;
import com.cad.im.entity.http.LoginInfo;
import com.cad.im.entity.mysql.User;
import com.cad.im.service.UserService;
import com.cad.im.util.Result;
import com.cad.im.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginInfo loginInfo){
        try{
            User user = userService.login(loginInfo);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", user.getUserId());
            jsonObject.put("identity", user.getIdentity());
            return Result.success(jsonObject);
        } catch (Exception ex){
            LOGGER.error(ex.toString());
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    @GetMapping("/identity/doctor")
    public Result identDoctor(String userId, String code){
        try{
            if(code.equals("666")){
                userService.changeIdentity("doctor", userId);
            } else {
                throw new Exception("认证码错误");
            }
            return Result.success();
        } catch (Exception ex){
            LOGGER.error(ex.toString());
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }
}
