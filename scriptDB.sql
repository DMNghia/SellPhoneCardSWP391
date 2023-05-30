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

create table Product(
                        id int not null AUTO_INCREMENT,
                        name varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT null,
                        quantity int ,
                        price double,
                        supplier int,
                        createdAt datetime,
                        createdBy int ,
                        isDelete boolean,
                        deletedAt datetime,
                        deletedBy int,
                        updatedAt datetime,
                        updatedBy int,
                        primary key(id)
);

create table Supplier(
                         id int not null AUTO_INCREMENT,
                         name varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT null,
                         createdAt datetime,
                         createdBy int ,
                         isDelete boolean,
                         deletedAt datetime,
                         deletedBy int,
                         updatedAt datetime,
                         updatedBy int,
                         primary key(id)
);

alter table Supplier add image varchar(255);
INSERT INTO product (`id`,`name`,`quantity`,`price`,`supplier`,`createdAt`,`createdBy`,`isDelete`,`deletedAt`,`deletedBy`,`updatedAt`,`updatedBy`) VALUES (1,'Thẻ Viettel 30.000',10,30000,1,NULL,NULL,0,NULL,NULL,NULL,NULL);
INSERT INTO product (`id`,`name`,`quantity`,`price`,`supplier`,`createdAt`,`createdBy`,`isDelete`,`deletedAt`,`deletedBy`,`updatedAt`,`updatedBy`) VALUES (2,'Thẻ Vinaphone 20.000',5,20000,2,NULL,NULL,0,NULL,NULL,NULL,NULL);
INSERT INTO product (`id`,`name`,`quantity`,`price`,`supplier`,`createdAt`,`createdBy`,`isDelete`,`deletedAt`,`deletedBy`,`updatedAt`,`updatedBy`) VALUES (3,'Thẻ Vietnammoblie 50.000',15,50000,3,NULL,NULL,0,NULL,NULL,NULL,NULL);
INSERT INTO product (`id`,`name`,`quantity`,`price`,`supplier`,`createdAt`,`createdBy`,`isDelete`,`deletedAt`,`deletedBy`,`updatedAt`,`updatedBy`) VALUES (4,'Thẻ Mobifone 100.000',10,100000,4,NULL,NULL,0,NULL,NULL,NULL,NULL);
INSERT INTO product (`id`,`name`,`quantity`,`price`,`supplier`,`createdAt`,`createdBy`,`isDelete`,`deletedAt`,`deletedBy`,`updatedAt`,`updatedBy`) VALUES (5,'Thẻ Viettel 200.000',15,200000,1,NULL,NULL,0,NULL,NULL,NULL,NULL);
INSERT INTO product (`id`,`name`,`quantity`,`price`,`supplier`,`createdAt`,`createdBy`,`isDelete`,`deletedAt`,`deletedBy`,`updatedAt`,`updatedBy`) VALUES (6,'Thẻ Vinaphone 500.000',10,500000,2,NULL,NULL,0,NULL,NULL,NULL,NULL);
INSERT INTO product (`id`,`name`,`quantity`,`price`,`supplier`,`createdAt`,`createdBy`,`isDelete`,`deletedAt`,`deletedBy`,`updatedAt`,`updatedBy`) VALUES (7,'Thẻ Mobifone 20.000',5,20000,4,NULL,NULL,0,NULL,NULL,NULL,NULL);
INSERT INTO product (`id`,`name`,`quantity`,`price`,`supplier`,`createdAt`,`createdBy`,`isDelete`,`deletedAt`,`deletedBy`,`updatedAt`,`updatedBy`) VALUES (8,'Thẻ Viettel 100.000',20,100000,1,NULL,NULL,0,NULL,NULL,NULL,NULL);
INSERT INTO product (`id`,`name`,`quantity`,`price`,`supplier`,`createdAt`,`createdBy`,`isDelete`,`deletedAt`,`deletedBy`,`updatedAt`,`updatedBy`) VALUES (9,'Thẻ Vietnammobile 20.000',5,20000,3,NULL,NULL,0,NULL,NULL,NULL,NULL);
INSERT INTO product (`id`,`name`,`quantity`,`price`,`supplier`,`createdAt`,`createdBy`,`isDelete`,`deletedAt`,`deletedBy`,`updatedAt`,`updatedBy`) VALUES (10,'Thẻ Vinaphone 100.000',20,100000,2,NULL,NULL,0,NULL,NULL,NULL,NULL);
INSERT INTO product (`id`,`name`,`quantity`,`price`,`supplier`,`createdAt`,`createdBy`,`isDelete`,`deletedAt`,`deletedBy`,`updatedAt`,`updatedBy`) VALUES (11,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL);

INSERT INTO supplier (`id`,`name`,`createdAt`,`createdBy`,`isDelete`,`deletedAt`,`deletedBy`,`updatedAt`,`updatedBy`,`image`) VALUES (1,'Viettel',NULL,NULL,0,NULL,NULL,NULL,NULL,'image/viettel.png');
INSERT INTO supplier (`id`,`name`,`createdAt`,`createdBy`,`isDelete`,`deletedAt`,`deletedBy`,`updatedAt`,`updatedBy`,`image`) VALUES (2,'Vinaphone',NULL,NULL,0,NULL,NULL,NULL,NULL,'image/vinaphone.png');
INSERT INTO supplier (`id`,`name`,`createdAt`,`createdBy`,`isDelete`,`deletedAt`,`deletedBy`,`updatedAt`,`updatedBy`,`image`) VALUES (3,'Vietnammobile',NULL,NULL,0,NULL,NULL,NULL,NULL,'image/vietnammobile.png');
INSERT INTO supplier (`id`,`name`,`createdAt`,`createdBy`,`isDelete`,`deletedAt`,`deletedBy`,`updatedAt`,`updatedBy`,`image`) VALUES (4,'Mobifone',NULL,NULL,0,NULL,NULL,NULL,NULL,'image/mobifone.png');
