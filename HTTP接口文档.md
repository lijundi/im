[toc]

# 高血压智能预问诊助手-HTTP接口文档

## 0 公共

### 接口描述

> API公共部分说明，其它API皆基于此API定义

### 请求方法及参数

> 具体参见API定义

### 返回参数

| 返回参数 | 参数类型 | 参数说明                          |
| -------- | -------- | --------------------------------- |
| code     | Int      | 状态码                            |
| msg      | String   | 状态信息                          |
| data     | Object   | 业务响应（对应具体API的返回参数） |

#### 状态码及信息

| 状态码 | 状态信息 |
| ------ | -------- |
| 1      | 成功     |
| 0      | 失败     |

#### 示例

- 响应成功

```JSON
{
    "code":1,
    "msg":"成功",
    "data":{
        "userId":"xxxxx",
        "identity":"患者"
    }
}
```

- 响应失败

```JSON
{
    "code":0,
    "msg":"失败",
    "data":null
}
```



## 1 好友

### 1.1 好友列表

#### 接口描述

> 根据用户Id获取好友列表

#### URL

> /friend/list

#### 请求方式

> GET

#### 请求参数

| 参数名 | 类型   | 是否必填 | 描述   |
| ------ | ------ | -------- | ------ |
| userId | string | 是       | 用户id |

#### 返回参数

| 字段名 | 类型            | 描述         |
| ------ | --------------- | ------------ |
| -      | array of friend | 返回好友列表 |

##### friend

| 字段名    | 类型   | 描述     |
| --------- | ------ | -------- |
| userId    | string | 好友id   |
| nickName  | string | 好友昵称 |
| avatarUrl | string | 好友头像 |

##### 示例

```json
{
    "code": 1,
    "msg": "成功",
    "data": [
        {
            "userId": "xxxx0",
            "nickName": "xcx0",
            "avatarUrl": "../xcx0.jpg"
    	}
    ]
}
```

 

### 1.2 好友基本信息

#### 接口描述

> 根据好友id获取好友昵称、头像等基本信息

#### URL

> /friend/info

#### 请求方式

> GET

#### 请求参数

| 参数名   | 类型   | 是否必填 | 描述   |
| -------- | ------ | -------- | ------ |
| friendId | string | 是       | 好友id |

#### 返回参数

| 字段名    | 类型   | 描述     |
| --------- | ------ | -------- |
| userId    | string | 好友id   |
| nickName  | string | 好友昵称 |
| avatarUrl | string | 好友头像 |

##### 示例

```json
{
    "code":1,
    "msg":"成功",
    "data":{
        "userId": "xxxx0",
        "nickName": "xcx0",
        "avatarUrl": "../xcx0.jpg"
    }
}
```



### 1.3好友搜索

#### 接口描述

> 根据搜索框关键词获取好友与非好友列表

#### URL

> /friend/find

#### 请求方式

> GET

#### 请求参数

| 参数名     | 类型   | 是否必填 | 描述         |
| ---------- | ------ | -------- | ------------ |
| userId     | string | 是       | 用户id       |
| friendName | string | 是       | 好友名关键词 |

#### 返回参数

| 字段名      | 类型            | 描述           |
| ----------- | --------------- | -------------- |
| non-friends | array of friend | 返回非好友列表 |
| friends     | array of friend | 返回好友列表   |

##### friend

| 字段名    | 类型   | 描述     |
| --------- | ------ | -------- |
| userId    | string | 好友id   |
| nickName  | string | 好友昵称 |
| avatarUrl | string | 好友头像 |

##### 示例

```json
{
    "code": 1,
    "msg": "成功",
    "data": {
        "non-friends": [
            {
                "userId": "xxxx0",
                "nickName": "xcx0",
                "avatarUrl": "../xcx0.jpg"
    		},
            {
                "userId": "xxxx0",
                "nickName": "xcx0",
                "avatarUrl": "../xcx0.jpg"
    		}
        ],
        "friends": [
           	{
                "userId": "xxxx0",
                "nickName": "xcx0",
                "avatarUrl": "../xcx0.jpg"
    		},
            {
                "userId": "xxxx0",
                "nickName": "xcx0",
                "avatarUrl": "../xcx0.jpg"
    		}
        ]
    }
}
```



### 1.4好友申请

#### 接口描述

> 发起好友请求

#### URL

> /friend/apply

#### 请求方式

> GET

#### 请求参数

| 参数名     | 类型   | 是否必填 | 描述   |
| ---------- | ------ | -------- | ------ |
| userId     | string | 是       | 用户id |
| friendId   | string | 是       | 好友id |
| userName   | string | 是       | 用户名 |
| friendName | string | 是       | 好友名 |

#### 返回参数

> 无



### 1.5好友确认

#### 接口描述

> 接受好友请求

#### URL

> /friend/agree

#### 请求方式

> GET

#### 请求参数

| 参数名   | 类型   | 是否必填 | 描述   |
| -------- | ------ | -------- | ------ |
| userId   | string | 是       | 用户id |
| friendId | string | 是       | 好友id |

#### 返回参数

> 无



### 1.6好友删除

#### 接口描述

> 删除好友关系

#### URL

> /friend/del

#### 请求方式

> GET

#### 请求参数

| 参数名   | 类型   | 是否必填 | 描述   |
| -------- | ------ | -------- | ------ |
| userId   | string | 是       | 用户id |
| friendId | string | 是       | 好友id |

#### 返回参数

> 无



### 1.7 好友申请列表

#### 接口描述

> 获取未同意的好友申请列表

#### URL

> /friend/nonfriends

#### 请求方式

> GET

#### 请求参数

| 参数名 | 类型   | 是否必填 | 描述   |
| ------ | ------ | -------- | ------ |
| userId | string | 是       | 用户id |

#### 返回参数

| 字段名 | 类型            | 描述                     |
| ------ | --------------- | ------------------------ |
| -      | array of friend | 返回未同意的好友申请列表 |

##### friend

| 字段名    | 类型   | 描述     |
| --------- | ------ | -------- |
| userId    | string | 好友id   |
| nickName  | string | 好友昵称 |
| avatarUrl | string | 好友头像 |

##### 示例

```json
{
    "code":1,
    "msg":"成功",
    "data":[
        {
            "userId": "xxxx0",
            "nickName": "xcx0",
            "avatarUrl": "../xcx0.jpg"
        },
        {
            "userId": "xxxx0",
            "nickName": "xcx0",
            "avatarUrl": "../xcx0.jpg"
        }
    ]
}
```





## 2 对话消息

### 2.1 离线对话消息

#### 接口描述

> 登入小程序前，获取用户离线聊天消息

#### URL

> /message/offline

#### 请求方式

> GET

#### 请求参数

| 参数   | 类型   |  是否必填    | 描述 |
| ------ | ------ | ---- | ------------ |
| userId | string | 是   | 用户id       |

#### 返回参数

| 字段名 | 类型             | 描述                 |
| ------ | ---------------- | -------------------- |
| -      | array of message | 返回离线对话消息列表 |

##### message

| 字段名    | 类型   | 描述               |
| --------- | ------ | ------------------ |
| fromId    | string | 消息发送方id       |
| toId      | string | 消息接收方id       |
| type      | string | 消息类型 text=文本 |
| content   | string | 消息内容           |
| timeStamp | string | 发送消息的时间戳   |

##### 示例

```json
{
    "code":1,
    "msg":"成功",
    "data":[
        {
            "fromId":"xxx1",
            "toId":"xxx2",
            "type":"text",
            "content":"hello",
            "timeStamp":"2020-10-10 21:51:57"
    	},
        {
            "fromId":"xxx3",
            "toId":"xxx2",
            "type":"text",
            "content":"hello",
            "timeStamp":"2020-10-10 21:55:57"
    	},
        {
            "fromId":"xxx1",
            "toId":"xxx2",
            "type":"text",
            "content":"hello",
            "timeStamp":"2020-10-10 21:55:57"
    	}
    ]
}
```



### 2.2 历史对话消息

#### 接口描述

> 根据时间戳获取用户与某个好友之间的历史对话消息，每次返回至多10条记录

#### URL

> /message/history

#### 请求方式

> GET

#### 请求参数

| 参数      | 类型   | 是否必填 | 描述   |
| --------- | ------ | -------- | ------ |
| userId    | string | 是       | 用户id |
| friendId  | string | 是       | 好友id |
| timeStamp | string | 是       | 时间戳 |

#### 返回参数

| 字段名 | 类型             | 描述                               |
| ------ | ---------------- | ---------------------------------- |
| -      | array of message | 返回历史对话消息列表，至多10条记录 |

##### message

> 参见对话消息-离线对话消息-返回参数-message

##### 示例

> 参见对话消息-离线对话消息-返回参数-示例



## 3 用药管理

### 3.1 用药列表

#### 接口描述

> 获取用药历史记录

#### URL

> /medication/list

#### 请求方式

> GET

#### 请求参数

| 参数   | 类型   | 是否必填 | 描述   |
| ------ | ------ | -------- | ------ |
| userId | string | 是       | 用户id |

#### 返回参数

| 字段名 | 类型                | 描述             |
| ------ | ------------------- | ---------------- |
| -      | array of medication | 返回用药历史列表 |

##### medication

| 字段名       | 类型   | 描述           |
| ------------ | ------ | -------------- |
| id           | int    | 用药记录id     |
| createTime   | string | 记录创建时间   |
| medicineName | string | 药物名称       |
| startTime    | string | 服用开始时间   |
| endTime      | string | 服用结束时间   |
| frequency    | string | 每日服用次数   |
| medicineDose | string | 每次服用剂量mg |
| beforeBp     | string | 服用前血压值   |
| afterBp      | string | 服用后血压值   |

##### 示例

```json
{
    "code":1,
    "msg":"成功",
    "data":[
        {
            "id":1,
            "createTime":"2020-10-10 21:51:57",
            "medicineName":"阿米洛利",
            "startTime":"2020-10-10",
            "endTime":"2020-10-11",
            "frequency":"2",
            "medicineDose":"15",
            "beforeBp":"150/170mmHg",
            "afterBp":"140/150mmHg"
    	},
        {
            "id":2,
            "createTime":"2020-10-10 22:51:57",
            "medicineName":"阿米洛利",
            "startTime":"2020-10-10",
            "endTime":"2020-10-11",
            "frequency":"2",
            "medicineDose":"15",
            "beforeBp":"150/170mmHg",
            "afterBp":"140/150mmHg"
    	}
    ]
}
```



### 3.2 新增用药记录

#### 接口描述

> 增加一条用药记录

#### URL

> /medication/add

#### 请求方式

> POST，数据为json格式

#### 请求参数

| 参数         | 类型   | 是否必填 | 描述           |
| ------------ | ------ | -------- | -------------- |
| userId       | string | 是       | 用户id         |
| medicineName | string | 是       | 药物名称       |
| startTime    | string | 是       | 服用开始时间   |
| endTime      | string | 是       | 服用结束时间   |
| frequency    | string | 是       | 每日服用次数   |
| medicineDose | string | 是       | 每次服用剂量mg |
| beforeBp     | string | 是       | 服用前血压值   |
| afterBp      | string | 是       | 服用后血压值   |

#### 返回参数

> 无



### 3.3 删除用药记录

#### 接口描述

> 删除一条用药记录

#### URL

> /medication/del

#### 请求方式

> GET

#### 请求参数

| 参数 | 类型 | 是否必填 | 描述       |
| ---- | ---- | -------- | ---------- |
| id   | int  | 是       | 用药记录id |

#### 返回参数

> 无



## 4 健康档案

> 仅部分接口



### 4.1 生活习惯

#### 接口描述

> 获取关于生活习惯预问诊信息

#### URL

> /profile/lifeHabits/list

#### 请求方式

> GET

#### 请求参数

| 参数   | 类型   | 是否必填 | 描述   |
| ------ | ------ | -------- | ------ |
| userId | string | 是       | 用户id |

#### 返回参数

| 字段名              | 类型   | 描述                          |
| ------------------- | ------ | ----------------------------- |
| userId              | string | 用户id                        |
| staple_food         | int    | 每餐主食的份量                |
| pickles_bean_paste  | string | 喜食咸菜、豆瓣酱等习惯：有/无 |
| sleep_hour          | int    | 每晚睡眠时间                  |
| sleep_quality       | string | 睡眠质量：好/一般/差          |
| life_stay_up_late   | string | 经常熬夜：有/无               |
| snoring_night       | string | 夜间打鼾：有/无               |
| life_nervous        | string | 精神紧张：有/无               |
| week_sports_num     | int    | 每周运动次数                  |
| life_smoke          | string | 吸烟：有/无                   |
| smoke_year          | int    | 吸烟年数                      |
| smoke_num           | int    | 每天吸烟支数                  |
| high_drink          | string | 饮高度酒：有/无               |
| high_drink_year     | int    | 饮高度酒年数                  |
| high_drink_quantity | int    | 每天饮高度酒两数              |
| life_drinking       | string | 有、无 酗酒：有/无            |

##### 示例

```json
{
    "code": 1,
    "msg": "成功",
    "data": {
        "userId": "o0TDd4si-9vPiJfI9l1B6Uzb15m8",
        "staple_food": null,
        "pickles_bean_paste": null,
        "sleep_hour": 7,
        "sleep_quality": "一般",
        "life_stay_up_late": "无",
        "snoring_night": null,
        "life_nervous": "有",
        "week_sports_num": null,
        "life_smoke": "有",
        "smoke_year": 10,
        "smoke_num": 3,
        "high_drink": "无",
        "high_drink_year": null,
        "high_drink_quantity": null,
        "life_drinking": "无"
    }
}
```



### 4.2 修改生活习惯

#### 接口描述

> 修改用户生活习惯预问诊信息

#### URL

> /profile/lifeHabits/add

#### 请求方式

> POST，数据为json格式

#### 请求参数

| 字段名              | 类型   | 是否必填 | 描述                          |
| ------------------- | ------ | -------- | ----------------------------- |
| userId              | string | 是       | 用户id                        |
| staple_food         | int    | 否       | 每餐主食的份量                |
| pickles_bean_paste  | string | 否       | 喜食咸菜、豆瓣酱等习惯：有/无 |
| sleep_hour          | int    | 否       | 每晚睡眠时间                  |
| sleep_quality       | string | 否       | 睡眠质量：好/一般/差          |
| life_stay_up_late   | string | 否       | 经常熬夜：有/无               |
| snoring_night       | string | 否       | 夜间打鼾：有/无               |
| life_nervous        | string | 否       | 精神紧张：有/无               |
| week_sports_num     | int    | 否       | 每周运动次数                  |
| life_smoke          | string | 否       | 吸烟：有/无                   |
| smoke_year          | int    | 否       | 吸烟年数                      |
| smoke_num           | int    | 否       | 每天吸烟支数                  |
| high_drink          | string | 否       | 饮高度酒：有/无               |
| high_drink_year     | int    | 否       | 饮高度酒年数                  |
| high_drink_quantity | int    | 否       | 每天饮高度酒两数              |
| life_drinking       | string | 否       | 有、无 酗酒：有/无            |

#### 返回参数

> 无



## 5 用户管理

### 5.1 登录

#### 接口描述

> 用户使用微信号登录小程序

#### URL

> /user/login

#### 请求方式

> POST，数据为json格式

#### 请求参数

| 参数      | 类型   | 是否必填 | 描述         |
| --------- | ------ | -------- | ------------ |
| code      | string | 是       | 微信临时凭证 |
| nickName  | string | 是       | 用户最新昵称 |
| avatarUrl | string | 是       | 用户最新头像 |

#### 返回参数

| 字段名   | 类型   | 描述     |
| -------- | ------ | -------- |
| userId   | string | 用户id   |
| identity | string | 用户身份 |

##### 示例

```json
{
    "code":1,
    "msg":"成功",
    "data":{
        "userId":"xxxxx",
        "identity":"患者"
    }
}
```



### 5.2 医生身份认证

#### 接口描述

> 用户角色由患者升级为医生

#### URL

> /user/identity/doctor

#### 请求方式

> GET

#### 请求参数

| 参数   | 类型   | 是否必填 | 描述   |
| ------ | ------ | -------- | ------ |
| userId | string | 是       | 用户id |
| code   | string | 是       | 认证码 |

#### 返回参数

> 无



## 6 机器人

### 6.1 重启当前会话

#### 接口描述

> 结束与机器人的当前会话，开始新的会话

#### URL

> /robot/restart

#### 请求方式

> GET

#### 请求参数

| 参数   | 类型   | 是否必填 | 描述   |
| ------ | ------ | -------- | ------ |
| sender | string | 是       | 用户id |

#### 返回参数

> 无



## 7 知识问答

### 7.1 问题列表

#### 接口描述

> 根据用户提出的单个问题预测多个问题

#### URL

> /knowledge/predict

#### 请求方式

> POST，数据为json格式

#### 请求参数

| 参数     | 类型   | 是否必填 | 描述           |
| -------- | ------ | -------- | -------------- |
| question | string | 是       | 用户提出的问题 |

#### 返回参数

| 字段名 | 类型              | 描述             |
| ------ | ----------------- | ---------------- |
| -      | array of question | 返回相似问题列表 |

##### question

| 字段名  | 类型   | 描述     |
| ------- | ------ | -------- |
| qid     | string | 问题id   |
| content | string | 问题内容 |

##### 示例

```json
{
    "code": 1,
    "msg": "成功",
    "data": {
        "data": [
            {
                "qid": "1603074677241",
                "content": "高血压的分类"
            },
            {
                "qid": "1603074721687",
                "content": "继发性高血压分类"
            },
            {
                "qid": "1603074828467",
                "content": "居家高血压自我管理"
            },
            {
                "qid": "1603074676060",
                "content": "高血压是什么"
            }
        ]
    }
}
```



### 7.2 获取答案

#### 接口描述

> 根据用户选择的问题返回答案

#### URL

> /knowledge/answer

#### 请求方式

> GET

#### 请求参数

| 参数 | 类型   | 是否必填 | 描述   |
| ---- | ------ | -------- | ------ |
| qid  | string | 是       | 问题id |

#### 返回参数

| 字段名 | 类型   | 描述     |
| ------ | ------ | -------- |
| answer | string | 问题答案 |

##### 示例

```json
{
    "code": 1,
    "msg": "成功",
    "data": {
        "answer": "高血压有两种类型，一是原发性高血压，二是继发性高血压。原发性高血压是一种以血压升高为主要临床表现但病因尚未明确的独立疾病，占所有高血压患者的90%以上。继发性高血压又称为症状性高血压，病因明确，是某种疾病的临床表现之一，血压可暂时性或持久性升高。"
    }
}
```



## 8 风险评估

### 8.1 风险评估

#### 接口描述

> 对患者的病历数据进行评估，生成高血压风险等级评估结果

#### URL

> /risk/assessment

#### 请求方式

> GET

#### 请求参数

| 参数   | 类型   | 是否必填 | 描述   |
| ------ | ------ | -------- | ------ |
| userId | string | 是       | 用户id |

#### 返回参数

| 字段名        | 类型            | 描述           |
| ------------- | --------------- | -------------- |
| riskLevel     | string          | 风险等级       |
| updateTime    | string          | 更新时间       |
| bpLevel       | string          | 血压等级       |
| factors       | factors         | 危险因素       |
| organs        | array of string | 靶器官损害列表 |
| complications | array of string | 临床并发症列表 |

##### factors

| 字段名   | 类型            | 描述         |
| -------- | --------------- | ------------ |
| num      | int             | 危险因素个数 |
| contents | array of string | 危险因素列表 |

##### 示例

- 成功

```json
{
    "code":1,
    "msg":"成功",
    "data":{
        "riskLevel": "中危",
        "updateTime": "2020-10-10 22:51:57",
        "bpLevel": "1级高血压",
        "factors": {
            "num": 2,
            "contents": [
                "吸烟",
                "血脂异常"
            ]
        },
        "organs": [],
        "complications": []
    }
}
```

- 失败-血压正常

```json
{
    "code":0,
    "msg":"失败",
    "data": "血压正常"
}
```

- 失败-无评估结果

```json
{
    "code":0,
    "msg":"失败",
    "data": "无评估结果"
}
```

- 失败-缺少血压值

```json
{
    "code":0,
    "msg":"失败",
    "data": "缺少血压值"
}
```



### 8.2 评估结果

#### 接口描述

> 获取已生成的高血压风险等级评估结果

#### URL

> /risk/result

#### 请求方式

> GET

#### 请求参数

| 参数   | 类型   | 是否必填 | 描述   |
| ------ | ------ | -------- | ------ |
| userId | string | 是       | 用户id |

#### 返回参数

> 参见风险评估-风险评估-返回参数



### 8.3 补充评估条件

#### 接口描述

> 用户完善高血压风险等级评估条件

#### URL

> /risk/add

#### 请求方式

> POST，数据为json格式

#### 请求参数

| 参数               | 类型    | 是否必填 | 描述                             |
| ------------------ | ------- | -------- | -------------------------------- |
| userId             | varchar | 是       | 主键，对应用户ID                 |
| sbp                | int     | 否       | 收缩压（mmHg）                   |
| dbp                | int     | 否       | 舒张压（mmHg）                   |
| gender             | varchar | 否       | 性别：男/女                      |
| age                | int     | 否       | 年龄                             |
| smoke              | varchar | 否       | 吸烟情况：吸烟/被动吸烟/不吸烟   |
| h2_bg              | float   | 否       | 2h血糖含量（mmol/L）             |
| fasting_bg         | float   | 否       | 空腹血糖含量（mmol/L）           |
| dyslipidemia       | varchar | 否       | 血脂异常：是/否                  |
| tc                 | float   | 否       | 总胆固醇含量（mmol/L）           |
| ldl_c              | float   | 否       | 低密度脂蛋白胆固醇含量（mmol/L） |
| hdl_c              | float   | 否       | 高密度脂蛋白胆固醇含量（mmol/L） |
| cvd_family_history | varchar | 否       | 早发心血管病家族史：是/否        |
| waistline          | float   | 否       | 腰围（cm）                       |
| bmi                | float   | 否       | BMI（kg/m2)                      |
| cysteine           | float   | 否       | 半胱氨酸含量（umol/L）           |
| sl_voltage         | float   | 否       | 心电图Sokolow-Lyon电压（mV）     |
| lvmi               | float   | 否       | 左心室重量指数（g/m2）           |
| imt                | float   | 否       | 颈动脉内膜中层厚度（mm）         |
| egfr               | float   | 否       | 肾小球滤过率（ml·min-1·1.73m-2） |
| serum_creatinine   | float   | 否       | 血清肌酐（umol/L）               |
| proteinuria        | float   | 否       | 蛋白尿（mg/24h）                 |
| cvd                | varchar | 否       | 脑血管疾病：是/否                |
| chd                | varchar | 否       | 心脏疾病：是/否                  |
| ckd                | varchar | 否       | 肾脏疾病：是/否                  |
| pvd                | varchar | 否       | 外周血管疾病：是/否              |
| retionpathy        | varchar | 否       | 视网膜病变：是/否                |
| diabetes           | varchar | 否       | 糖尿病：是/否                    |


#### 返回参数

> 无



### 8.4 评估条件

#### 接口描述

> 获取高血压风险等级评估条件列表

#### URL

> /risk/list

#### 请求方式

> GET

#### 请求参数

| 参数   | 类型   | 是否必填 | 描述   |
| ------ | ------ | -------- | ------ |
| userId | string | 是       | 用户Id |

#### 返回参数

| 字段名             | 类型    | 描述                             |
| ------------------ | ------- | -------------------------------- |
| userId             | varchar | 主键，对应用户ID                 |
| sbp                | int     | 收缩压（mmHg）                   |
| dbp                | int     | 舒张压（mmHg）                   |
| gender             | varchar | 性别：男/女                      |
| age                | int     | 年龄                             |
| smoke              | varchar | 吸烟情况：吸烟/被动吸烟/不吸烟   |
| h2_bg              | float   | 2h血糖含量（mmol/L）             |
| fasting_bg         | float   | 空腹血糖含量（mmol/L）           |
| dyslipidemia       | varchar | 血脂异常：是/否                  |
| tc                 | float   | 总胆固醇含量（mmol/L）           |
| ldl_c              | float   | 低密度脂蛋白胆固醇含量（mmol/L） |
| hdl_c              | float   | 高密度脂蛋白胆固醇含量（mmol/L） |
| cvd_family_history | varchar | 早发心血管病家族史：是/否        |
| waistline          | float   | 腰围（cm）                       |
| bmi                | float   | BMI（kg/m2)                      |
| cysteine           | float   | 半胱氨酸含量（umol/L）           |
| sl_voltage         | float   | 心电图Sokolow-Lyon电压（mV）     |
| lvmi               | float   | 左心室重量指数（g/m2）           |
| imt                | float   | 颈动脉内膜中层厚度（mm）         |
| egfr               | float   | 肾小球滤过率（ml·min-1·1.73m-2） |
| serum_creatinine   | float   | 血清肌酐（umol/L）               |
| proteinuria        | float   | 蛋白尿（mg/24h）                 |
| cvd                | varchar | 脑血管疾病：是/否                |
| chd                | varchar | 心脏疾病：是/否                  |
| ckd                | varchar | 肾脏疾病：是/否                  |
| pvd                | varchar | 外周血管疾病：是/否              |
| retionpathy        | varchar | 视网膜病变：是/否                |
| diabetes           | varchar | 糖尿病：是/否                    |

##### 示例

```json
{
    "code": 1,
    "msg": "成功",
    "data": {
        "userId": "o0TDd4kSohtusMWy2fUYHyNI69Uw",
        "sbp": 100,
        "dbp": 150,
        "gender": "男",
        "age": 55,
        "smoke": "吸烟",
        "h2Bg": null,
        "fasting_bg": null,
        "dyslipidemia": "否",
        "tc": null,
        "ldl_c": null,
        "hdl_c": null,
        "cvd_family_history": "否",
        "waistline": 80.0,
        "bmi": 22.49,
        "cysteine": null,
        "sl_voltage": null,
        "lvmi": null,
        "imt": null,
        "egfr": null,
        "serum_creatinine": null,
        "proteinuria": null,
        "cvd": "否",
        "chd": "否",
        "ckd": "否",
        "pvd": "否",
        "retionpathy": "否",
        "diabetes": "是"
    }
}
```





------

修改时间：2020.12.2