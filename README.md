# K-Mall
## Introduction
An online shopping project.


## Database table
**member table**

```sql
CREATE TABLE `member`(
	`id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT, -- id
    `username` VARCHAR(10) UNIQUE NOT NULL,
    `password` VARCHAR(32) NOT NULL,
    `username` VARCHAR(64) NOT NULL,
    `create_time` datetime,
	`update_time` datetime
)CHARSET utf8 ENGINE INNODB;
```

**furniture table**

```sql
CREATE TABLE `furniture`(
	`id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT, -- id
	`name` VARCHAR(64) NOT NULL, -- 家居名
	`manufacturer` VARCHAR(64) NOT NULL, -- 制造商
	`price` DECIMAL(11,2) NOT NULL , -- 价格 
	`sales` INT UNSIGNED NOT NULL, -- 销量
	`stock` INT UNSIGNED NOT NULL, -- 库存
	`img_path` VARCHAR(256) NOT null, -- 存放图片的路径
	`create_time` datetime,
	`update_time` datetime
)CHARSET utf8 ENGINE INNODB;
```

