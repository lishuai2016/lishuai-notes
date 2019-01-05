# 秒杀库存表(seckill)
| 列名   | 类型   | KEY  | 是否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|seckill_id|bigint(20)|PRI|NO|商品库存id|
|name|varchar(120)||NO|商品名称|
|number|int(11)||NO|库存数量|
|start_time|timestamp|MUL|NO|秒杀开启时间|
|end_time|timestamp|MUL|NO|秒杀结束时间|
|create_time|timestamp|MUL|NO|创建时间|
|version|int(11)||NO|版本号|
