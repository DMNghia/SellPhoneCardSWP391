CREATE SCHEMA `sellphonecard`;

USE
sellphonecard;

create table user
(
    id          int          not null AUTO_INCREMENT,
    account     varchar(255) not null,
    password    varchar(255) not null,
    email       varchar(255) not null,
    role        int,
    phoneNumber varchar(255),
    isDelete    boolean,
    isActive    boolean,
    createdAt   datetime,
    createdBy   int,
    updatedAt   datetime,
    updatedBy   int,
    deletedAt   datetime,
    deletedBy   int,
    primary key (id)
);