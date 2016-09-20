-- 数据库初始化脚本

-- 创建数据库
CREATE DATABASE seckill;

use seckill;
-- 对于5.6之后的版本，mysql表中只能有一个时间戳，其余相似时间字段，用datetime

CREATE TABLE `seckill` (
  `seckill_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `number` int(10) unsigned NOT NULL COMMENT '库存数量',
  `start_time` datetime NOT NULL COMMENT '秒杀开始时间',
  `end_time` datetime NOT NULL COMMENT '秒杀结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '秒杀创建时间',
  PRIMARY KEY (`seckill_id`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表'

insert into
		seckill(name,number,start_time,end_time)
values
		('秒杀iphone6s',100,'2016-06-06 00:00:00','2016-06-07 00:00:00'),
		('秒杀ipad3',200,'2016-06-06 00:00:00','2016-06-07 00:00:00'),
		('秒杀小米5',300,'2016-06-06 00:00:00','2016-06-07 00:00:00'),
		('秒杀暗影精灵2',400,'2016-06-06 00:00:00','2016-06-07 00:00:00');

-- 秒杀成功明细表
-- 用户登录认证相关的信息
CREATE TABLE `success_killed` (
  `seckill_id` int(11) NOT NULL COMMENT '秒杀商品id',
  `user_phone` int(11) NOT NULL COMMENT '用户手机号',
  `state` int(1) NOT NULL COMMENT '-1表示无效 0为成功 1表示已付款',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`,`user_phone`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表'


