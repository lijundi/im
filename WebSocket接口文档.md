[toc]

# 高血压智能预问诊助手-WebSocket接口文档

## 1 订阅接口

### 1.1 单聊消息

#### 接口描述

> 接收单聊消息

#### URL

> /user/topic/chat

#### 返回参数

| 字段名    | 类型   | 描述               |
| :-------- | :----- | ------------------ |
| fromId    | string | 发送方id           |
| toId      | string | 接收方id           |
| type      | string | 消息类型 text=文本 |
| content   | string | 消息内容           |
| timeStamp | string | 发送消息的时间戳   |



### 1.2 机器人消息

#### 接口描述

> 接收机器人回复消息

#### URL

> /user/topic/robotChat

#### 返回参数

| 字段名        | 类型            | 描述                                |
| :------------ | :-------------- | ----------------------------------- |
| wsChatMessage | message         | 回复消息                            |
| inputs        | string          | 与buttons字段互斥，表示血压值输入框 |
| buttons       | array of button | 与inputs字段互斥，表示选择项        |

##### message

| 字段名    | 类型   | 描述               |
| --------- | ------ | ------------------ |
| fromId    | string | 消息发送方id       |
| toId      | string | 消息接收方id       |
| type      | string | 消息类型 text=文本 |
| content   | string | 消息内容           |
| timeStamp | string | 发送消息的时间戳   |

##### button

| 字段名 | 类型   | 描述       |
| ------ | ------ | ---------- |
| title  | string | 选择项的值 |

##### 示例

- inputs消息

```JSON
{
    "wsChatMessage":{
        "fromId":"xxx1",
        "toId":"xxx2",
        "type":"text",
        "content":"hello",
        "timeStamp":"2020-10-10 21:51:57"
    },
    "inputs":"inputs"
}
```

- buttons消息

```JSON
{
    "wsChatMessage":{
        "fromId":"xxx1",
        "toId":"xxx2",
        "type":"text",
        "content":"hello",
        "timeStamp":"2020-10-10 21:51:57"
    },
    "buttons":[
        {
            "title":"1"
        },
        {
            "title":"2"
        }
    ]
}
```





## 2 请求接口

### 2.1 单聊消息

#### 接口描述

> 发送一对一单聊消息，

#### URL

> /user/app/chat

#### 请求参数

| 参数名    | 类型   | 是否必填 | 描述               |
| :-------- | :----- | -------- | ------------------ |
| fromId    | string | 是       | 发送方id           |
| toId      | string | 是       | 接收方id           |
| type      | string | 是       | 消息类型 text=文本 |
| content   | string | 是       | 消息内容           |
| timeStamp | string | 是       | 发送消息的时间戳   |

#### 返回参数

| 返回字段 | 字段类型 | 说明     |
| :------- | :------- | :------- |
| code     | int      | 状态码   |
| msg      | string   | 状态信息 |
|          | -        | -        |

##### 示例

- 响应成功

```JSON
{
    "code":1,
    "msg":"成功",
    "data":null
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



### 2.2 机器人消息

#### 接口描述

> 向机器人发送聊天消息

#### URL

> /user/app/robotChat

#### 请求参数

| 参数名    | 类型   | 是否必填 | 描述               |
| :-------- | :----- | -------- | ------------------ |
| fromId    | string | 是       | 发送方id           |
| toId      | string | 是       | 接收方id           |
| type      | string | 是       | 消息类型 text=文本 |
| content   | string | 是       | 消息内容           |
| timeStamp | string | 是       | 发送消息的时间戳   |

#### 返回参数

| 返回字段 | 字段类型 | 说明     |
| :------- | :------- | :------- |
| code     | int      | 状态码   |
| msg      | string   | 状态信息 |
| data     | -        | -        |

##### 示例

- 响应成功

```JSON
{
    "code":1,
    "msg":"成功",
    "data":null
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





------

修改时间：2020.10.29