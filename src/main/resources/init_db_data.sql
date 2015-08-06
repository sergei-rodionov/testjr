-- SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `user`
-- ----------------------------
-- DROP TABLE IF EXISTS `user`;
-- CREATE TABLE `user` ( `id` int(8) NOT NULL AUTO_INCREMENT, `name` varchar(25) NOT NULL, `age` int(11) DEFAULT NULL, `isAdmin` bit(1) DEFAULT NULL, `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY (`id`),   UNIQUE KEY `UK_syftr7gx86fwf7ox7bgvnnta7` (`name`)) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records DEMO DATA of user
-- ----------------------------
INSERT INTO `user` (`id`, `name`, `age`, `isAdmin`, `createDate`) VALUES ('1', 'admin', '20', b'1', '2015-08-03 21:03:54');
INSERT INTO `user` (`id`, `name`, `age`, `isAdmin`, `createDate`) VALUES ('2', 'user00', '12', b'0', '2015-08-03 21:03:55');
INSERT INTO `user` (`id`, `name`, `age`, `isAdmin`, `createDate`) VALUES ('3', 'user01', '12', b'0', '2015-08-03 21:03:55');
INSERT INTO `user` (`id`, `name`, `age`, `isAdmin`, `createDate`) VALUES ('4', 'user02', '12', b'0', '2015-08-03 21:03:55');
INSERT INTO `user` (`id`, `name`, `age`, `isAdmin`, `createDate`) VALUES ('5', 'user03', '22', b'0', '2015-08-03 21:03:55');
INSERT INTO `user` (`id`, `name`, `age`, `isAdmin`, `createDate`) VALUES ('6', 'user4admin', '13', b'0', '2015-08-03 21:03:56');
INSERT INTO `user` (`id`, `name`, `age`, `isAdmin`, `createDate`) VALUES ('7', 'user5admim', '14', b'0', '2015-08-03 21:03:56');
INSERT INTO `user` (`id`, `name`, `age`, `isAdmin`, `createDate`) VALUES ('8', 'user6', '15', b'0', '2015-08-03 21:03:56');
INSERT INTO `user` (`id`, `name`, `age`, `isAdmin`, `createDate`) VALUES ('9', 'user7', '15', b'0', '2015-08-03 21:03:57');
INSERT INTO `user` (`id`, `name`, `age`, `isAdmin`, `createDate`) VALUES ('10', 'admin8', '15', b'0', '2015-08-03 21:03:57');
INSERT INTO `user` (`id`, `name`, `age`, `isAdmin`, `createDate`) VALUES ('11', 'user9', '17', b'0', '2015-08-03 21:03:57');
INSERT INTO `user` (`id`, `name`, `age`, `isAdmin`, `createDate`) VALUES ('12', 'user010', '17', b'0', '2015-08-03 21:03:57');
INSERT INTO `user` (`id`, `name`, `age`, `isAdmin`, `createDate`) VALUES ('13', 'user11', '18', b'0', '2015-08-03 21:03:57');
INSERT INTO `user` (`id`, `name`, `age`, `isAdmin`, `createDate`) VALUES ('14', 'user12', '18', b'0', '2015-08-03 21:03:58');
INSERT INTO `user` (`id`, `name`, `age`, `isAdmin`, `createDate`) VALUES ('15', 'user13admin', '19', b'1', '2015-08-03 21:03:58');
INSERT INTO `user` (`id`, `name`, `age`, `isAdmin`, `createDate`) VALUES ('16', 'user014', '19', b'0', '2015-08-03 21:03:58');
INSERT INTO `user` (`id`, `name`, `age`, `isAdmin`, `createDate`) VALUES ('17', 'user015', '19', b'0', '2015-08-03 21:03:58');
INSERT INTO `user` (`id`, `name`, `age`, `isAdmin`, `createDate`) VALUES ('18', 'user16admin', '20', b'0', '2015-08-03 21:03:59');
INSERT INTO `user` (`id`, `name`, `age`, `isAdmin`, `createDate`) VALUES ('19', 'user17', '21', b'0', '2015-08-03 21:03:59');
INSERT INTO `user` (`id`, `name`, `age`, `isAdmin`, `createDate`) VALUES ('20', 'user18', '21', b'0', '2015-08-03 21:03:59');
INSERT INTO `user` (`id`, `name`, `age`, `isAdmin`, `createDate`) VALUES ('21', 'admin19', '22', b'0', '2015-08-03 21:03:59');
INSERT INTO `user` (`id`, `name`, `age`, `isAdmin`, `createDate`) VALUES ('22', 'user20admin', '22', b'0', '2015-08-03 21:03:59');