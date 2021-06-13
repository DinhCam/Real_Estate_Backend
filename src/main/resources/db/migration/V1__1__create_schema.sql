USE real_estate;

CREATE TABLE `role` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_profile` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `avatar` varchar(2000) DEFAULT NULL,
                                `phone` varchar(255) DEFAULT NULL,
                                `fullname` varchar(255) DEFAULT NULL,
                                `email` varchar(255) DEFAULT NULL,
                                `status` boolean DEFAULT TRUE,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `role_id` int DEFAULT NULL,
                        `profile_id` int DEFAULT NULL,
                        `username` varchar(255) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        `status` boolean DEFAULT TRUE,
                        PRIMARY KEY (`id`),
                        KEY `profile_id` (`profile_id`),
                        KEY `role_id` (`role_id`),
                        CONSTRAINT `user_ibfk_1` FOREIGN KEY (`profile_id`) REFERENCES `user_profile` (`id`),
                        CONSTRAINT `user_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `real_estate` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `seller_id` int DEFAULT NULL,
                               `staff_id` int DEFAULT NULL,
                               `title` varchar(255) DEFAULT NULL,
                               `view` varchar(255) DEFAULT NULL,
                               `create_at` datetime DEFAULT NULL,
                               `is_active` boolean DEFAULT TRUE,
                               PRIMARY KEY (`id`),
                               KEY `seller_id` (`seller_id`),
                               KEY `staff_id` (`staff_id`),
                               CONSTRAINT `real_estate_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`),
                               CONSTRAINT `real_estate_ibfk_2` FOREIGN KEY (`staff_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `conversation` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `buyer_id` int DEFAULT NULL,
                                `seller_id` int DEFAULT NULL,
                                `real_estate_id` int DEFAULT NULL,
                                `name` varchar(255) DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                KEY `buyer_id` (`buyer_id`),
                                KEY `seller_id` (`seller_id`),
                                KEY `real_estate_id` (`real_estate_id`),
                                CONSTRAINT `conversation_ibfk_1` FOREIGN KEY (`buyer_id`) REFERENCES `user` (`id`),
                                CONSTRAINT `conversation_ibfk_2` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`),
                                CONSTRAINT `conversation_ibfk_3` FOREIGN KEY (`real_estate_id`) REFERENCES `real_estate` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `message` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `conversation_id` int DEFAULT NULL,
                           `sender_id` int DEFAULT NULL,
                           `text` varchar(255) DEFAULT NULL,
                           `file` varchar(2000) DEFAULT NULL,
                           `create_at` datetime DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `conversation_id` (`conversation_id`),
                           CONSTRAINT `message_ibfk_1` FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `deal` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `conversation_id` int DEFAULT NULL,
                        `create_at` datetime DEFAULT NULL,
                        `offered_price` double DEFAULT NULL,
                        `status` boolean DEFAULT TRUE,
                        PRIMARY KEY (`id`),
                        KEY `conversation_id` (`conversation_id`),
                        CONSTRAINT `deal_ibfk_1` FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `appointment` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `conversation_id` int DEFAULT NULL,
                               `staff_id` int DEFAULT NULL,
                               `schedule_date` datetime DEFAULT NULL,
                               `create_at` datetime DEFAULT NULL,
                               `status` boolean DEFAULT TRUE,
                               PRIMARY KEY (`id`),
                               KEY `staff_id` (`staff_id`),
                               KEY `conversation_id` (`conversation_id`),
                               CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`staff_id`) REFERENCES `user` (`id`),
                               CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `transaction` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `buyer_id` int DEFAULT NULL,
                               `seller_id` int DEFAULT NULL,
                               `staff_id` int DEFAULT NULL,
                               `real_estate_id` int DEFAULT NULL,
                               `price` double DEFAULT NULL,
                               `down_price` double DEFAULT NULL,
                               `create_at` datetime DEFAULT NULL,
                               `status` boolean DEFAULT TRUE,
                               PRIMARY KEY (`id`),
                               KEY `buyer_id` (`buyer_id`),
                               KEY `seller_id` (`seller_id`),
                               KEY `staff_id` (`staff_id`),
                               KEY `real_estate_id` (`real_estate_id`),
                               CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`buyer_id`) REFERENCES `user` (`id`),
                               CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`),
                               CONSTRAINT `transaction_ibfk_3` FOREIGN KEY (`staff_id`) REFERENCES `user` (`id`),
                               CONSTRAINT `transaction_ibfk_4` FOREIGN KEY (`real_estate_id`) REFERENCES `real_estate` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `district` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ward` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `district_id` int DEFAULT NULL,
                        `name` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        KEY `district_id` (`district_id`),
                        CONSTRAINT `ward_ibfk_1` FOREIGN KEY (`district_id`) REFERENCES `district` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `street` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `name` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `street_ward` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `ward_id` int DEFAULT NULL,
                               `street_id` int DEFAULT NULL,
                               `average_price` double DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               KEY `ward_id` (`ward_id`),
                               KEY `street_id` (`street_id`),
                               CONSTRAINT `street_ward_ibfk_1` FOREIGN KEY (`ward_id`) REFERENCES `ward` (`id`),
                               CONSTRAINT `street_ward_ibfk_2` FOREIGN KEY (`street_id`) REFERENCES `street` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `real_estate_type` (
                                    `id` int NOT NULL AUTO_INCREMENT,
                                    `name` varchar(255) DEFAULT NULL,
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `real_estate_detail` (
                                      `id` int NOT NULL AUTO_INCREMENT,
                                      `real_estate_id` int DEFAULT NULL,
                                      `facility_id` int DEFAULT NULL,
                                      `street_ward_id` int DEFAULT NULL,
                                      `type_id` int DEFAULT NULL,
                                      `description` varchar(255) DEFAULT NULL,
                                      `area` double DEFAULT NULL,
                                      `price` double DEFAULT NULL,
                                      `direction` varchar(255) DEFAULT NULL,
                                      `number_of_bedroom` int DEFAULT NULL,
                                      `number_of_bathroom` int DEFAULT NULL,
                                      `lot` int DEFAULT NULL,
                                      PRIMARY KEY (`id`),
                                      KEY `street_ward_id` (`street_ward_id`),
                                      KEY `facility_id` (`facility_id`),
                                      KEY `type_id` (`type_id`),
                                      KEY `real_estate_id` (`real_estate_id`),
                                      CONSTRAINT `real_estate_detail_ibfk_1` FOREIGN KEY (`street_ward_id`) REFERENCES `street_ward` (`id`),
                                      CONSTRAINT `real_estate_detail_ibfk_2` FOREIGN KEY (`type_id`) REFERENCES `real_estate_type` (`id`),
                                      CONSTRAINT `real_estate_detail_ibfk_3` FOREIGN KEY (`real_estate_id`) REFERENCES `real_estate` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `image_resource` (
                                  `id` int NOT NULL AUTO_INCREMENT,
                                  `real_estate_id` int DEFAULT NULL,
                                  `img_url` varchar(2000) DEFAULT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `real_estate_id` (`real_estate_id`),
                                  CONSTRAINT `image_resource_ibfk_1` FOREIGN KEY (`real_estate_id`) REFERENCES `real_estate` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `facility_type` (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `name` varchar(255) DEFAULT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `facility` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `type_id` int DEFAULT NULL,
                            `name` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `type_id` (`type_id`),
                            CONSTRAINT `facility_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `facility_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `real_estate_facility` (
                                        `id` int NOT NULL AUTO_INCREMENT,
                                        `real_estate_detail_id` int DEFAULT NULL,
                                        `facility_id` int DEFAULT NULL,
                                        `distance` double DEFAULT NULL,
                                        PRIMARY KEY (`id`),
                                        KEY `real_estate_detail_id` (`real_estate_detail_id`),
                                        KEY `facility_id` (`facility_id`),
                                        CONSTRAINT `real_estate_facility_ibfk_1` FOREIGN KEY (`real_estate_detail_id`) REFERENCES `real_estate_detail` (`id`),
                                        CONSTRAINT `real_estate_facility_ibfk_2` FOREIGN KEY (`facility_id`) REFERENCES `facility` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `feature` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `name` varchar(255) DEFAULT NULL,
                           `description` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `permission` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `user_id` int DEFAULT NULL,
                              `feature_id` int DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              KEY `user_id` (`user_id`),
                              KEY `feature_id` (`feature_id`),
                              CONSTRAINT `permission_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                              CONSTRAINT `permission_ibfk_2` FOREIGN KEY (`feature_id`) REFERENCES `feature` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `time_frame` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `start_time` time NULL DEFAULT NULL,
                              `end_time` time NULL DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `week_day` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `schedule` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `seller_id` int DEFAULT NULL,
                            `week_day_id` int DEFAULT NULL,
                            `time_frame_id` int DEFAULT NULL,
                            `status` boolean DEFAULT TRUE,
                            PRIMARY KEY (`id`),
                            KEY `seller_id` (`seller_id`),
                            KEY `week_day_id` (`week_day_id`),
                            KEY `time_frame_id` (`time_frame_id`),
                            CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`),
                            CONSTRAINT `schedule_ibfk_2` FOREIGN KEY (`week_day_id`) REFERENCES `week_day` (`id`),
                            CONSTRAINT `schedule_ibfk_3` FOREIGN KEY (`time_frame_id`) REFERENCES `time_frame` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;








