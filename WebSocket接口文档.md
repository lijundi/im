[toc]



## WebSocket

### 一、订阅接口

#### 1. 订阅单聊消息

##### URL

> /user/topic/chat

##### 接口说明

> 接收单聊消息

##### 返回数据

> | 返回字段   | 字段类型 | 说明                                 |
> | :--------- | :------- | ------------------------------------ |
> | message_id | int      | 当前会话id                           |
> | from_id    | int      | 发送方id                             |
> | to_id      | int      | 接收方id                             |
> | type       | String   | 消息类型。“text”：文本；“file”：文件 |
> | content    | String   | 消息内容。file类型为URL              |
> | timeStamp  | String   | 发送消息的时间戳                     |

#### 2. 订阅应答消息

##### URL

> /user/topic/result

##### 接口说明

> 接收服务器应答消息

##### 返回数据

> | 返回字段 | 字段类型 | 说明     |
> | :------- | :------- | :------- |
> | code     | int      | 状态码   |
> | msg      | String   | 状态信息 |
> | data     | json     | 数据     |

### 二、请求接口

#### 1. 发送单聊消息

##### URL

> /app/chat

##### 接口说明

> 发送一对一消息，返回成功状态码表示消息发送成功

##### 请求参数

> | 参数       | 必选 | 类型   | 说明                                 |
> | :--------- | :--- | :----- | ------------------------------------ |
> | message_id | ture | int    | 当前会话id                           |
> | from_id    | ture | int    | 发送方id                             |
> | to_id      | ture | int    | 接收方id                             |
> | type       | ture | String | 消息类型。“text”：文本；“file”：文件 |
> | content    | true | String | 消息内容。file类型为URL              |
> | timeStamp  | ture | String | 发送消息的时间戳                     |

##### 返回数据

> | 返回字段 | 字段类型 | 说明                                   |
> | :------- | :------- | :------------------------------------- |
> | code     | int      | 状态码。成功：1；失败：0               |
> | msg      | String   | 状态信息。成功：“成功”；失败：“失败”   |
> | data     | json     | 数据。成功：发送消息的会话id；失败：空 |

##### 接口示例

> 返回结果

```json
{
    "code": 1,
    "msg": "成功",
    "data": {
        "message_id": 55
    }
}
```





