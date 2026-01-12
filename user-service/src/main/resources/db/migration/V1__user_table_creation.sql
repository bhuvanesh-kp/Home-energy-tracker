create table `user` (
    `id` bigint not null auto_increment primary key ,
    `name` varchar(255) not null ,
    `surname` varchar(255) ,
    `address` text ,
    `email` varchar(255) not null ,
    `alerting` tinyint(1) not null default 0,
    `alerting_Threshold` double not null default 0,
    unique key `uk_user_email` (`email`)
);