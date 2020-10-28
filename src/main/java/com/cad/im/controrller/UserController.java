package com.cad.im.controrller;

import com.alibaba.fastjson.JSONObject;
import com.cad.im.entity.http.LoginInfo;
import com.cad.im.entity.mysql.User;
import com.cad.im.service.UserService;
import com.cad.im.util.Result;
import com.cad.im.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
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
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }
}
