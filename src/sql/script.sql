CREATE TABLE `t_cd_house_trade_info` (
                                         `id` int NOT NULL AUTO_INCREMENT,
                                         `data_date` date DEFAULT NULL,
                                         `urban_new_house_num` int DEFAULT NULL,
                                         `urban_new_house_are` decimal(30,10) DEFAULT NULL,
                                         `country_new_house_num` int DEFAULT NULL,
                                         `country_new_house_area` decimal(30,10) DEFAULT NULL,
                                         `urban_old_house_num` int DEFAULT NULL,
                                         `urban_old_house_are` decimal(30,10) DEFAULT NULL,
                                         `country_old_house_num` int DEFAULT NULL,
                                         `country_old_house_are` decimal(30,10) DEFAULT NULL,
                                         PRIMARY KEY (`id`),
                                         UNIQUE KEY `t_cd_house_trade_info_data_date_uindex` (`data_date`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='成都房屋交易数据';