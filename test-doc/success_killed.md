# 秒杀成功明细表(success_killed)
| 列名   | 类型   | KEY  | 是否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|seckill_id|bigint(20)|PRI|NO|秒杀商品id|
|user_id|bigint(20)|PRI|NO|用户Id|
|state|tinyint(4)||NO|状态标示：-1指无效，0指成功，1指已付款|
|create_time|timestamp|MUL|NO|创建时间|
