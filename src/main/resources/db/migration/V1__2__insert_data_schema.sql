INSERT INTO `real_estate`.`role` (`name`) VALUES ('seller');
INSERT INTO `real_estate`.`role` (`name`) VALUES ('buyer');
INSERT INTO `real_estate`.`role` (`name`) VALUES ('staff');

INSERT INTO `real_estate`.`user_profile` (`avatar`, `phone`, `fullname`, `email`) VALUES ('trong', '1234', 'cctrong', 'aaaaaa');
INSERT INTO `real_estate`.`user_profile` (`avatar`, `phone`, `fullname`, `email`) VALUES ('cam', '5678', 'cccam', 'bbb');
INSERT INTO `real_estate`.`user_profile` (`avatar`, `phone`, `fullname`, `email`) VALUES ('asjkfh', '21341', 'jashfdj', 'dsuhfuis');

INSERT INTO `real_estate`.`user` (`role_id`, `profile_id`, `username`, `password`) VALUES ('1', '1', 'trongnv', '1234');
INSERT INTO `real_estate`.`user` (`role_id`, `profile_id`, `username`, `password`) VALUES ('2', '2', 'camld', '12345');
INSERT INTO `real_estate`.`user` (`role_id`, `profile_id`, `username`, `password`) VALUES ('3', '3', 'jasjfdhj', '12421');

INSERT INTO `real_estate`.`real_estate` (`seller_id`, `staff_id`, `title`, `view`, `create_at`) VALUES ('1', '1', 'nha mat pho', 'song sai gon', '2020-09-12');
INSERT INTO `real_estate`.`real_estate` (`seller_id`, `staff_id`, `title`, `view`, `create_at`) VALUES ('2', '2', 'chung cu le van viet', 'ao ba lien', '2021-11-11');
INSERT INTO `real_estate`.`real_estate` (`seller_id`, `staff_id`, `title`, `view`, `create_at`) VALUES ('3', '3', 'cao oc', 'bien', '2010-10-10');