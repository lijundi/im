package com.cad.im.bean;

import com.cad.im.entity.mysql.MonitorTask;
import com.cad.im.entity.profile.PhysicalExamination;
import com.cad.im.entity.websocket.WsSystemMessage;
import com.cad.im.repository.MonitorTaskRepository;
import com.cad.im.repository.PhysicalExaminationRepository;
import com.cad.im.service.MessageService;
import com.cad.im.service.SessionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class TaskHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskHandler.class);

    private final MessageService messageService;
    private final MonitorTaskRepository monitorTaskRepository;
    private final SessionHandler sessionHandler;
    private final PhysicalExaminationRepository physicalExaminationRepository;

    public TaskHandler(MessageService messageService, MonitorTaskRepository monitorTaskRepository, SessionHandler sessionHandler, PhysicalExaminationRepository physicalExaminationRepository) {
        this.messageService = messageService;
        this.monitorTaskRepository = monitorTaskRepository;
        this.sessionHandler = sessionHandler;
        this.physicalExaminationRepository = physicalExaminationRepository;
    }



//    @Scheduled(cron = "8,14,20 * * * * ?")
    @Scheduled(cron = "0 0 8,14,20 * * ?")
    public void execute() {
        // 查表
        List<MonitorTask> monitorTaskList = monitorTaskRepository.findAll();
//        LOGGER.info("定时");
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
//        int hour = Calendar.getInstance().get(Calendar.SECOND);
        for(MonitorTask monitorTask: monitorTaskList){
            // 该天是否执行任务
            if(monitorTask.getRemainingDays().equals(0)){
                // 该时间点是否执行任务
                String frequency = monitorTask.getFrequency();
                if(frequency.contains("day3") || frequency.contains("day2")&&hour!=14 || frequency.contains("day1")&&hour==8){
//                    LOGGER.info("执行任务");
                    runTask(monitorTask, hour);
                    // 任务次数是否完成
                    if(monitorTask.getNum()==1){
                        switch (monitorTask.getTarget()){
                            case "bpCheck":
//                                LOGGER.info("血压是否达标");
                                monitorTask = bpCheck(monitorTask.getUserId(), monitorTask.getFrequency());
                                break;
                            case "nothing":
                                monitorTask = createTask(monitorTask.getUserId(), monitorTask.getFrequency());
                                break;
                        }
                    } else {
                        monitorTask.setNum(monitorTask.getNum()-1);
                    }
                    monitorTaskRepository.save(monitorTask);
                }
            }
            // 调整剩余天数
            if(hour==20 && monitorTask.getRemainingDays()!=0){
//                LOGGER.info("调整天数");
                monitorTask.setRemainingDays(monitorTask.getRemainingDays()-1);
                monitorTaskRepository.save(monitorTask);
            }
        }
    }

    private void runTask(MonitorTask monitorTask, int hour){
        String userId = monitorTask.getUserId();
        // 血压监测
        WsSystemMessage wsSystemMessage = new WsSystemMessage("bp", "您该测量血压了，请及时测量！", new Date());
        if(sessionHandler.isOnline(userId)){
            messageService.forwardSystemMessage(wsSystemMessage, userId);
        }
        messageService.storeSystemMessage(wsSystemMessage, userId);
        // 靶器官及心血管病症状评估 一天之内只发一次
        if(hour==8){
            wsSystemMessage = new WsSystemMessage("tocda", "您该填写近期症状评估表单了，请及时填写！", new Date());
            if(sessionHandler.isOnline(userId)){
                messageService.forwardSystemMessage(wsSystemMessage, userId);
            }
            messageService.storeSystemMessage(wsSystemMessage, userId);
        }
    }

    public MonitorTask createTask(String userId, String frequency){
        MonitorTask monitorTask = new MonitorTask();
        monitorTask.setUserId(userId);
        monitorTask.setFrequency(frequency);
        switch (frequency){
            case "day3":
                monitorTask.setNum(3);
                monitorTask.setRemainingDays(1);
                monitorTask.setTarget("bpCheck");
                break;
            case "2week#day3":
                monitorTask.setNum(3*14);
                monitorTask.setRemainingDays(1);
                monitorTask.setTarget("bpCheck");
                break;
            case "4week#day3":
                monitorTask.setNum(3*30);
                monitorTask.setRemainingDays(1);
                monitorTask.setTarget("bpCheck");
                break;
            case "2week#day2":
                monitorTask.setNum(2*14);
                monitorTask.setRemainingDays(1);
                monitorTask.setTarget("bpCheck");
                break;
            case "4week#day2":
                monitorTask.setNum(2*30);
                monitorTask.setRemainingDays(1);
                monitorTask.setTarget("bpCheck");
                break;
            case "week1&day2":
                monitorTask.setNum(2);
                monitorTask.setRemainingDays(8);
                monitorTask.setTarget("nothing");
                break;
            case "year1&day1":
                monitorTask.setNum(1);
                monitorTask.setRemainingDays(366);
                monitorTask.setTarget("nothing");
                break;
        }
        return monitorTask;
    }

    public MonitorTask bpCheck(String userId, String frequency){
        // 以体格检查的血压值为标准
        PhysicalExamination pe = physicalExaminationRepository.findById(userId).orElse(null);
        assert pe!=null;
        String bp = pe.getBlood_pressure();
        String[] sList = bp.substring(0, bp.length()-4).split("/");
        Integer sbp = Integer.valueOf(sList[0]);
        Integer dbp = Integer.valueOf(sList[1]);
        // 血压是否达标
        if(sbp<135 || dbp<85){
            return createTask(userId, "week1&day2");
        } else {
            if(frequency.contains("2week"))
                return createTask(userId, "2week#day3");
            else if(frequency.contains("4week"))
                return createTask(userId, "4week#day3");
            else
                return createTask(userId, frequency);
        }
    }
}
