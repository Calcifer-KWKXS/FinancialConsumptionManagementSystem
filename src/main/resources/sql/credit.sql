
drop database if exists credit;
create database if not exists credit;
use credit;

CREATE TABLE `tb_user` (
  `id` int primary key auto_increment,
  `account` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `realname` varchar(20) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `createtime` datetime NOT NULL,
  `state` int(11) NOT NULL
);

INSERT INTO `tb_user` VALUES ('1', 'a1', '123', '菜菜', '菜菜', '13917592607', ' ', '2023-03-16 11:51:50', '1');


DROP TABLE IF EXISTS `porder`;
CREATE TABLE `porder` (
  `id` int primary key auto_increment,
  `totalprice` decimal(10,2) NOT NULL,
  `consumer_id` int NOT NULL,  -- 用户id
  `prov` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `district` varchar(50) NOT NULL,
  `addr` text NOT NULL,
  `createtime` datetime NOT NULL,
  `state` int NOT NULL
);

-- 属性及对应的属性值对”完全相同的商品，是一批商品的共同特征的提取，为一个 SPU
CREATE TABLE `spu` (
  `id` int primary key auto_increment,
  `name` varchar(50) NOT NULL,
  `catalog_id` int NOT NULL,
  `createtime` datetime NOT NULL,
  `state` int NOT NULL
);

INSERT INTO `spu` VALUES ('1', '小米 10S', '4', '2022-11-15 15:59:13', '1');
INSERT INTO `spu` VALUES ('2', '黑鲨5Pro', '4', '2022-11-15 16:00:38', '1');
INSERT INTO `spu` VALUES ('3', '小米 Redmi G Pro', '6', '2022-11-15 16:03:43', '1');


CREATE TABLE `sku` (
  `id` int primary key auto_increment,
  `name` varchar(100) NOT NULL,
  `spu_id` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `imgsrc` text NOT NULL,
  `createtime` datetime NOT NULL,
  `state` int NOT NULL
);

INSERT INTO `sku` VALUES ('1', '小米 Redmi G Pro 锐龙R7+RTX3060', '3', '7799.00', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farchive%2F0dd1e1289e1b65e56d1541fe05392aa903a33142.jpg&refer=http%3A%2F%2Fi0.hdslb.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1671616245&t=ed39a308a0ecb0c099e14e0b0f2d22bd', '2022-11-15 16:05:04', '1');
INSERT INTO `sku` VALUES ('2', '小米 Redmi G Pro 12代i5+RTX3060', '3', '6688.00', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farchive%2F0dd1e1289e1b65e56d1541fe05392aa903a33142.jpg&refer=http%3A%2F%2Fi0.hdslb.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1671616245&t=ed39a308a0ecb0c099e14e0b0f2d22bd', '2022-11-15 16:06:01', '1');
INSERT INTO `sku` VALUES ('3', '小米 10s 黑色版 8G+256G', '1', '3088.00', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farchive%2F0dd1e1289e1b65e56d1541fe05392aa903a33142.jpg&refer=http%3A%2F%2Fi0.hdslb.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1671616245&t=ed39a308a0ecb0c099e14e0b0f2d22bd', '2022-11-15 16:08:24', '1');
INSERT INTO `sku` VALUES ('4', '小米 10s 蓝色版 8G+128G', '1', '3066.00', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farchive%2F0dd1e1289e1b65e56d1541fe05392aa903a33142.jpg&refer=http%3A%2F%2Fi0.hdslb.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1671616245&t=ed39a308a0ecb0c099e14e0b0f2d22bd', '2022-11-15 16:09:03', '1');
INSERT INTO `sku` VALUES ('5', '小米 10s 白色版 12G+256G', '1', '4022.00', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farchive%2F0dd1e1289e1b65e56d1541fe05392aa903a33142.jpg&refer=http%3A%2F%2Fi0.hdslb.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1671616245&t=ed39a308a0ecb0c099e14e0b0f2d22bd', '2022-11-15 16:09:50', '1');



CREATE TABLE `orderdetail` (
  `id` int primary key auto_increment,
  `sku_id` int NOT NULL,
  `sku_name` varchar(50)  NOT NULL,
  `price` decimal(10,2) NOT NULL, -- 单价
  `scount` int NOT NULL, -- 数量
  `sumprice` decimal(10,2) NOT NULL,  -- 单价*数量
  `order_id` int NOT NULL,
  `createtime` datetime NOT NULL,
  `state` int NOT NULL
);

-- 帐单
CREATE TABLE `credit` (
  `id` int primary key auto_increment,
  `amount` decimal(10,2) NOT NULL,
  `period` int NOT NULL,
  `consumer_id` int NOT NULL,
  `order_id` int NOT NULL,
  `createtime` datetime NOT NULL,
  `state` int NOT NULL
);


CREATE TABLE `payback` (
  `id` int primary key auto_increment,
  `amount` decimal(10,2) NOT NULL,
  `credit_id` int NOT NULL,
  `expectpaytime` datetime,
  `realpaytime` datetime,
  `createtime` datetime,
  `state` int NOT NULL
);

CREATE TABLE `tb_amount` (
  `id` int primary key auto_increment,
  `account` varchar(50) NOT NULL,
  `totalamount` decimal(10,2) NOT NULL,
  `useamount` decimal(10,2) NOT NULL,
  `freeamount` decimal(10,2) NOT NULL,
  `createtime` datetime NOT NULL,
  `state` int NOT NULL
);







