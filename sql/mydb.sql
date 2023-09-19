/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.19 : Database - mydb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mydb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `mydb`;

/*Table structure for table `sys_file` */

DROP TABLE IF EXISTS `sys_file`;

CREATE TABLE `sys_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '文件名称',
  `type` varchar(255) DEFAULT NULL COMMENT '文件类型',
  `size` bigint(20) DEFAULT NULL COMMENT '文件大小',
  `url` varchar(255) DEFAULT NULL COMMENT '下载路径',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `enable` tinyint(1) DEFAULT NULL COMMENT '是否禁用链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `sys_file` */

insert  into `sys_file`(`id`,`name`,`type`,`size`,`url`,`is_delete`,`enable`) values 
(7,'pexels-picjumbocom-196659.jpg','jpg',709,'http://localhost:9090/file/42e9944fb16f4fc0a0836a421856ee95.jpg',0,0),
(8,'pexels-picjumbocom-196659.jpg','jpg',709,'http://localhost:9090/file/d8c4e7e20c134fc9b9c4f15a41928882.jpg',0,0),
(9,'avatar.jpg','jpg',11,'http://localhost:9090/file/08f5e741f85f421bab2abb6baea49482.jpg',0,0),
(10,'design_pattten.jpg','jpg',907,'http://localhost:9090/file/0042e614d43d46f5abeb9a98038d205f.jpg',0,1),
(11,'avatar.jpg','jpg',11,'http://localhost:9090/file/cf686ca9422e496cb279d49812754acf.jpg',0,0),
(12,'avatar.jpg','jpg',11,'http://localhost:9090/file/bb5f0ba2543b4310b87750c5286f3b31.jpg',0,1),
(13,'avatar.jpg','jpg',11,'http://localhost:9090/file/3af00d1fb86c48ba9676b6b9efd2433f.jpg',0,1),
(14,'avatar.jpg','jpg',11,'http://localhost:9090/file/036db7293eb3439e908606c2f21e2053.jpg',0,1),
(15,'avatar.jpg','jpg',11,'http://localhost:9090/file/94d1c90d24e740e2855e66abcdcafc81.jpg',0,0);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2132582409 DEFAULT CHARSET=latin1;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`password`,`nickname`,`email`,`phone`,`address`,`create_time`,`avatar_url`) values 
(1,'King','590906','killer','aaa@qq.com','1618685608','shenzhen','2023-01-20 11:13:19',NULL),
(2,'Honour','594762','11','aaa@163.com','1574049062','miami','2023-07-19 11:13:23',NULL),
(3,'admin','111','tom','aaa@139.com','11115678','guangzhou','2023-09-19 11:39:34','https://postimg.cc/dk3WKXsT'),
(4,'Kenway','123456','killer','aaa@live.com','1441443126','hangzhou','2023-10-14 11:13:27',NULL),
(5,'felixh','680395','watcher','aaa@outlook.com','1701730217','shanghai','2023-11-24 11:13:34',NULL),
(6,'Kendall','860429','lister','aaa@sina.com','1856161813','beijing','2023-02-23 11:13:39',NULL),
(7,'Marlon','433099','killer','aaa@126.com','1702043750','haikou','2023-03-23 11:13:44',NULL),
(8,'Kate','255511','a','aaa@yahoo.com','1444918458','austrin','2023-06-14 11:13:49',NULL),
(9,'Giles','892197','b','@110.com','1713534938','chengdu','2023-06-14 11:13:54',NULL),
(10,'tom','395651','cat','tom@163.com','1892463','England','2023-07-18 11:12:22',NULL),
(11,'Lively','597766','da1','@111.com','1629197009','changsha','2023-02-23 11:14:01','da1'),
(12,'Leslie','658506','aaa','aaa@hotmail.com','1483854767','xian','2023-03-11 11:14:07',NULL),
(13,'Hartley','235384',NULL,'aaa@live.cn','1587016366','nanjing','2023-01-30 11:14:29',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
