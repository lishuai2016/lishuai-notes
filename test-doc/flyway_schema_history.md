# (flyway_schema_history)
| 列名   | 类型   | KEY  | 是否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|installed_rank|int(11)|PRI|NO||
|version|varchar(50)||YES||
|description|varchar(200)||NO||
|type|varchar(20)||NO||
|script|varchar(1000)||NO||
|checksum|int(11)||YES||
|installed_by|varchar(100)||NO||
|installed_on|timestamp||NO||
|execution_time|int(11)||NO||
|success|tinyint(1)|MUL|NO||
