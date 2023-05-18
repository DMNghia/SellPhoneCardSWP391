CREATE SCHEMA `sellphonecard` ;

USE sellphonecard;

create table user (
	id int not null AUTO_INCREMENT,
	account varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) not null,
    role int,
    phone_number varchar(255),
    primary key (id),
    constraint user_unique unique key (email, account)
);

