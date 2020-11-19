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

> 根据用户身份获取不同的好友列表，医生获取患者列表，患者获取医生列表

#### URL

> /friend/list

#### 请求方式

> GET

#### 请求参数

| 参数名   | 类型   | 是否必填 | 描述                                  |
| -------- | ------ | -------- | ------------------------------------- |
| identity | string | 是       | 用户身份标识 doctor=医生 patient=患者 |

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
    "code":1,
    "msg":"成功",
    "data":[
        {
            "userId": "xxxx0",
            "nickName": "xcx0",
            "avatarUrl": "../xcx0.jpg"
        },
        {
            "userId": "xxxx1",
            "nickName": "xcx1",
            "avatarUrl": "../xcx1.jpg"
        },
        {
            "userId": "xxxx2",
            "nickName": "xcx2",
            "avatarUrl": "../xcx2.jpg"
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

> 暂时省略，以后补全



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

- 失败

```json
{
    "code":0,
    "msg":"失败",
    "data": null
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

| 字段名            | 类型    | 描述                           |
| ----------------- | ------- | ------------------------------ |
| userId            | varchar | 主键，对应用户ID               |
| gender            | varchar | 性别：男/女                    |
| age               | int     | 年龄                           |
| smoke             | varchar | 吸烟情况：吸烟/被动吸烟/不吸烟 |
| igt               | varchar | 糖耐量受损：是/否/未知         |
| h2Bg              | float   | 2h血糖含量                     |
| fasting_bg        | float   | 空腹血糖含量                   |
| dyslipidemia      | varchar | 血脂异常：是/否/未知           |
| family_history    | varchar | 早发心血管病家族史：是/否/未知 |
| abdominal_obesity | varchar | 腹型肥胖：是/否/未知           |
| waist             | float   | 腰围（cm）                     |
| bmi               | float   | BMI（kg/m2)                    |
| cysteine          | float   | 半胱氨酸含量                   |
| rf_num            | integer | 危险因素数量                   |

##### bpLevel（血压分级）

| 字段名     | 类型     | 描述                                                         |
| ---------- | -------- | ------------------------------------------------------------ |
| userId     | varchar  | 主键，对应用户ID                                             |
| updateTime | datetime | 更新时间                                                     |
| sbp        | int      | 收缩压                                                       |
| dbp        | int      | 舒张压                                                       |
| bp_level   | varchar  | 血压水平分类：正常/正常高值/1级高血压 /2级高血压/3级高血压/单纯收缩期高血压 |

##### clinicalComplications（临床并发症）

| 字段名      | 类型    | 描述                     |
| ----------- | ------- | ------------------------ |
| userId      | varchar | 主键，对应用户ID         |
| cvd         | varchar | 脑血管疾病：是/否/未知   |
| chd         | varchar | 心脏疾病：是/否/未知     |
| ckd         | varchar | 肾脏疾病：是/否/未知     |
| pvd         | varchar | 外周血管疾病：是/否/未知 |
| retionpathy | varchar | 视网膜病变：是/否/未知   |
| diabetes    | varchar | 糖尿病：有/无/未知       |

##### targetOrganDamage（靶器官损害）

| 字段名           | 类型    | 描述                               |
| ---------------- | ------- | ---------------------------------- |
| userId           | varchar | 主键，对应用户ID                   |
| left_ventricular | varchar | 左心室肥厚：是/否/未知             |
| usca             | varchar | 超声显示颈动脉粥样硬化：是/否/未知 |
| dgfr             | varchar | 肾小球滤过率降低：是/否/未知       |
| isc              | varchar | 血清肌酐升高：是/否/未知           |
| microalbuminuria | varchar | 微量白蛋白尿：是/否/未知           |

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

##### riskFactors（危险因素）

| 字段名            | 类型    | 描述                           |
| ----------------- | ------- | ------------------------------ |
| userId            | varchar | 主键，对应用户ID               |
| gender            | varchar | 性别：男/女                    |
| age               | int     | 年龄                           |
| smoke             | varchar | 吸烟情况：吸烟/被动吸烟/不吸烟 |
| igt               | varchar | 糖耐量受损：是/否/未知         |
| h2Bg              | float   | 2h血糖含量                     |
| fasting_bg        | float   | 空腹血糖含量                   |
| dyslipidemia      | varchar | 血脂异常：是/否/未知           |
| family_history    | varchar | 早发心血管病家族史：是/否/未知 |
| abdominal_obesity | varchar | 腹型肥胖：是/否/未知           |
| waist             | float   | 腰围（cm）                     |
| bmi               | float   | BMI（kg/m2)                    |
| cysteine          | float   | 半胱氨酸含量                   |
| rf_num            | integer | 危险因素数量                   |

##### bpLevel（血压分级）

| 字段名     | 类型     | 描述                                                         |
| ---------- | -------- | ------------------------------------------------------------ |
| userId     | varchar  | 主键，对应用户ID                                             |
| updateTime | datetime | 更新时间                                                     |
| sbp        | int      | 收缩压                                                       |
| dbp        | int      | 舒张压                                                       |
| bp_level   | varchar  | 血压水平分类：正常/正常高值/1级高血压 /2级高血压/3级高血压/单纯收缩期高血压 |

##### clinicalComplications（临床并发症）

| 字段名      | 类型    | 描述                     |
| ----------- | ------- | ------------------------ |
| userId      | varchar | 主键，对应用户ID         |
| cvd         | varchar | 脑血管疾病：是/否/未知   |
| chd         | varchar | 心脏疾病：是/否/未知     |
| ckd         | varchar | 肾脏疾病：是/否/未知     |
| pvd         | varchar | 外周血管疾病：是/否/未知 |
| retionpathy | varchar | 视网膜病变：是/否/未知   |
| diabetes    | varchar | 糖尿病：有/无/未知       |

##### targetOrganDamage（靶器官损害）

| 字段名           | 类型    | 描述                               |
| ---------------- | ------- | ---------------------------------- |
| userId           | varchar | 主键，对应用户ID                   |
| left_ventricular | varchar | 左心室肥厚：是/否/未知             |
| usca             | varchar | 超声显示颈动脉粥样硬化：是/否/未知 |
| dgfr             | varchar | 肾小球滤过率降低：是/否/未知       |
| isc              | varchar | 血清肌酐升高：是/否/未知           |
| microalbuminuria | varchar | 微量白蛋白尿：是/否/未知           |



##### 示例

```json
{
    "code": 1,
    "msg": "成功",
    "data": {
        "riskFactors": {
            "userId": "1",
            "gender": "女",
            "age": 19,
            "smoke": "不吸烟",
            "igt": "未知",
            "h2Bg": 10.0,
            "fasting_bg": 20.0,
            "dyslipidemia": "未知",
            "family_history": "否",
            "abdominal_obesity": "否",
            "waistline": 60.0,
            "bmi": 20.0,
            "cysteine": 10.0,
            "rf_num": 0
        },
        "bpLevel": {
            "userId": "1",
            "updateTime": "2020-11-17T02:53:19.000+0000",
            "sbp": 90,
            "dbp": 90,
            "bp_level": "正常"
        },
        "clinicalComplications": {
            "userId": "1",
            "cvd": "否",
            "chd": "否",
            "ckd": "未知",
            "pvd": "否",
            "retionpathy": "未知",
            "diabetes": "未知"
        },
        "targetOrganDamage": {
            "userId": "1",
            "left_ventricular": "未知",
            "usca": "否",
            "dgfr": "否",
            "isc": "否",
            "microalbuminuria": "未知"
        }
    }
}
```





------

修改时间：2020.11.17