[toc]

# 高血压智能预问诊助手-WebSocket接口文档

## 1 订阅接口

### 1.1 单聊消息

#### 接口描述

> 接收单聊消息

#### URL

> /user/topic/chat

#### 返回参数

| 字段名    | 类型   | 描述                               |
| :-------- | :----- | ---------------------------------- |
| fromId    | string | 发送方id                           |
| toId      | string | 接收方id                           |
| type      | string | 消息类型 text=文本 friend=好友请求 |
| content   | string | 消息内容                           |
| timeStamp | string | 发送消息的时间戳                   |

##### 示例

- 系统好友请求消息

```JSON
{
    "fromId":"2",
    "toId":"xxxxxxxxx1",
    "type":"friend",
    "content":"xxxxxxxx2的好友请求",
    "timeStamp":"2020-12-1 21:51:57"
}
```

- 普通聊天消息

```JSON
{
    "fromId":"xxx1",
    "toId":"xxx2",
    "type":"text",
    "content":"hello",
    "timeStamp":"2020-10-10 21:51:57"
}
```



### 1.2 机器人消息

#### 接口描述

> 接收机器人回复消息

#### URL

> /user/topic/robotChat

#### 返回参数

| 字段名        | 类型            | 描述                                                         |
| :------------ | :-------------- | ------------------------------------------------------------ |
| wsChatMessage | message         | 回复消息                                                     |
| type          | string          | 用户输入类型 text=文本输入框 bp=血压值输入框 num=数字输入框 navigate=页面跳转 buttons=选择项 btnsText=选择项加文本输入框 |
| buttons       | array of string | 当type=buttons或btnsText时返回该字段，表示选择项             |
| navigate      | string          | 当type=navigate时返回该字段，表示要跳转的页面 medicine=用药管理 risk=风险评估 |

##### message

| 字段名    | 类型   | 描述               |
| --------- | ------ | ------------------ |
| fromId    | string | 消息发送方id       |
| toId      | string | 消息接收方id       |
| type      | string | 消息类型 text=文本 |
| content   | string | 消息内容           |
| timeStamp | string | 发送消息的时间戳   |

##### 示例

- 页面跳转消息

```JSON
{
    "wsChatMessage":{
        "fromId":"1",
        "toId":"xxx2",
        "type":"text",
        "content":"hello",
        "timeStamp":"2020-10-10 21:51:57"
    },
    "type":"navigate",
    "navigate":"medicine"
}
```

- 数字输入框消息

```JSON
{
    "wsChatMessage":{
        "fromId":"1",
        "toId":"xxx2",
        "type":"text",
        "content":"hello",
        "timeStamp":"2020-10-10 21:51:57"
    },
    "type":"num"
}
```

- buttons消息

```JSON
{
    "wsChatMessage":{
        "fromId":"1",
        "toId":"xxx2",
        "type":"text",
        "content":"hello",
        "timeStamp":"2020-10-10 21:51:57"
    },
    "type":"buttons",
    "buttons":[
        "主诉",
        "退出问诊"
    ]
}
```



### 1.3 系统消息

#### 接口描述

> 接收系统消息

#### URL

> /user/topic/systemChat

#### 返回参数

| 字段名    | 类型   | 描述                                                         |
| :-------- | :----- | ------------------------------------------------------------ |
| type      | string | 消息类型 bp=血压监测 friend=好友请求 tocda=靶器官及心血管病症状评估 |
| content   | string | 消息内容                                                     |
| timeStamp | string | 发送消息的时间戳                                             |

##### 示例

- 系统好友请求消息

```JSON
{
    "type":"friend",
    "content":"xxxxxxxx2的好友请求",
    "timeStamp":"2020-12-1 21:51:57"
}
```

- 血压监测消息

```JSON
{
    "type":"bp",
    "content":"您该测量今天的血压了，请及时录入！",
    "timeStamp":"2020-12-10 16:24:01"
}
```

- 靶器官及心血管病症状评估消息

```JSON
{
    "type":"tocda",
    "content":"您该填写靶器官及心血管病症状评估表单了，请及时录入！",
    "timeStamp":"2020-12-10 16:24:01"
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

修改时间：2020.12.29