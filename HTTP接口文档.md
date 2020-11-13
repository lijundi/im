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

| 参数         | 类型   |      | 描述           |
| ------------ | ------ | ---- | -------------- |
| medicineName | string | 是   | 药物名称       |
| startTime    | string | 是   | 服用开始时间   |
| endTime      | string | 是   | 服用结束时间   |
| frequency    | string | 是   | 每日服用次数   |
| medicineDose | string | 是   | 每次服用剂量mg |
| beforeBp     | string | 是   | 服用前血压值   |
| afterBp      | string | 是   | 服用后血压值   |

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
| userId   | string | 用户Id   |
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
| userId | string | 是       | 用户Id |
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



## 7 QA知识库

### 7.1 获取QA问题列表

#### 接口描述

> 根据用户提出的单个问题预测多个问题

#### URL

> /qaai/predict

#### 请求方式

> POST，数据为json格式

##### 示例

```json
{
    "userText":"高血压"
}
```

#### 请求参数

| 参数     | 类型   | 是否必填 | 描述           |
| -------- | ------ | -------- | -------------- |
| userText | string | 是       | 用户提出的问题 |

#### 返回参数

| 字段名     | 类型   | 描述     |
| ---------- | ------ | -------- |
| question   | string | 相似问题 |
| qapid      | string | 问题Id   |
| confidence | double | 命中率   |

##### 示例

```json
{
    "body": {
        "retDesc": "预测成功",
        "retData": {
            "0": {
                "question": "高血压的危害",
                "answer": "持续的血压升高造成心、脑、肾、全身血管损害，严重时发生脑卒中、心肌梗死、心力衰竭、肾功能衰竭、失明等危及生命的临床并发症。研究显示70%的脑卒中和50%的心肌梗死与高血压有关。中国每年由于血压升高而导致过早死亡人数高达200万。高血压死亡占总死亡的1/4，慢病死亡的30%。在所有心血管病死亡中，64%是由高血压造成的，其中87.3%的高血压心脏病死亡、70.8%的脑卒中死亡。这些都是让人非常吃惊的数字。高血压一旦发生心脑肾等严重并发症，后果严重。",
                "confidence": 0.9077772390265546
            },
            "1": {
                "question": "高血压的分类",
                "qapid": "1603074677241",
                "confidence": 0.8671810923373827
            },
            "2": {
                "question": "继发性高血压分类",
                "qapid": "1603074721687",
                "confidence": 0.8514236201454359
            },
            "3": {
                "question": "居家高血压自我管理",
                "qapid": "1603074828467",
                "confidence": 0.8500104547981066
            },
            "4": {
                "question": "高血压是什么",
                "qapid": "1603074676060",
                "confidence": 0.8439354098572105
            }
        },
        "retCode": 21200
    }
}
```

### 7.2 获取QA答案

#### 接口描述

> 根据用户选择的问题返回答案

#### URL

> /qaai/answer

#### 请求方式

> GET，数据为json格式

##### 示例

```json
{
    "qapid": "1603074677241"
}
```

#### 请求参数

| 参数  | 类型   | 是否必填 | 描述   |
| ----- | ------ | -------- | ------ |
| qapid | string | 是       | 问题Id |

#### 返回参数

| 字段名  | 类型   | 描述         |
| ------- | ------ | ------------ |
| retDesc | string | 提示信息     |
| retData | string | 返回问题答案 |
| retCode | int    | 状态码       |

##### 示例

```json
{
    "body": {
        "retDesc": "获取答案成功",
        "retData": "高血压有两种类型，一是原发性高血压，二是继发性高血压。原发性高血压是一种以血压升高为主要临床表现但病因尚未明确的独立疾病，占所有高血压患者的90%以上。继发性高血压又称为症状性高血压，病因明确，是某种疾病的临床表现之一，血压可暂时性或持久性升高。",
        "retCode": 21200
    }
}
```







------

修改时间：2020.11.13