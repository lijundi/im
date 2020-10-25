package com.cad.im.controrller;

import com.cad.im.service.RobotService;
import com.cad.im.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/robot")
public class RobotController {
    @Autowired
    RobotService robotService;

    @GetMapping("/restart")
    public Result restart(Integer sender){
        robotService.sendRestartToRobot(sender);
        return Result.success();
    }
}
