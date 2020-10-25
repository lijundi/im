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
        "identity":"患者",
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

| 参数   | 类型   |      | 描述   |
| ------ | ------ | ---- | ------ |
| userId | string | 是   | 用户id |

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

> POST

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





------

修改时间：2020.10.22