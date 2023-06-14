CREATE SCHEMA `sellphonecard`;

USE sellphonecard;

create table user
(
    id          int          not null AUTO_INCREMENT,
    account     varchar(255) not null,
    password    varchar(255) not null,
    email       varchar(255) not null,
    role        int,
    phoneNumber varchar(255),
    balance     double,
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

create table product
(
    id        int not null AUTO_INCREMENT,
    name      varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT null,
    quantity  int,
    price     double,
    supplier  int,
    createdAt datetime,
    createdBy int,
    isDelete  boolean,
    deletedAt datetime,
    deletedBy int,
    updatedAt datetime,
    updatedBy int,
    primary key (id)
);

create table supplier
(
    id        int not null AUTO_INCREMENT,
    name      varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT null,
    createdAt datetime,
    createdBy int,
    isDelete  boolean,
    deletedAt datetime,
    deletedBy int,
    updatedAt datetime,
    updatedBy int,
    primary key (id)
);

alter table supplier
    add image varchar(255);
INSERT INTO product (`id`, `name`, `quantity`, `price`, `supplier`, `createdAt`, `createdBy`, `isDelete`, `deletedAt`,
                     `deletedBy`, `updatedAt`, `updatedBy`)
VALUES (1, 'Thẻ Viettel 30.000', 10, 30000, 1, NULL, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO product (`id`, `name`, `quantity`, `price`, `supplier`, `createdAt`, `createdBy`, `isDelete`, `deletedAt`,
                     `deletedBy`, `updatedAt`, `updatedBy`)
VALUES (2, 'Thẻ Vinaphone 20.000', 5, 20000, 2, NULL, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO product (`id`, `name`, `quantity`, `price`, `supplier`, `createdAt`, `createdBy`, `isDelete`, `deletedAt`,
                     `deletedBy`, `updatedAt`, `updatedBy`)
VALUES (3, 'Thẻ Vietnammoblie 50.000', 15, 50000, 3, NULL, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO product (`id`, `name`, `quantity`, `price`, `supplier`, `createdAt`, `createdBy`, `isDelete`, `deletedAt`,
                     `deletedBy`, `updatedAt`, `updatedBy`)
VALUES (4, 'Thẻ Mobifone 100.000', 10, 100000, 4, NULL, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO product (`id`, `name`, `quantity`, `price`, `supplier`, `createdAt`, `createdBy`, `isDelete`, `deletedAt`,
                     `deletedBy`, `updatedAt`, `updatedBy`)
VALUES (5, 'Thẻ Viettel 200.000', 15, 200000, 1, NULL, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO product (`id`, `name`, `quantity`, `price`, `supplier`, `createdAt`, `createdBy`, `isDelete`, `deletedAt`,
                     `deletedBy`, `updatedAt`, `updatedBy`)
VALUES (6, 'Thẻ Vinaphone 500.000', 10, 500000, 2, NULL, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO product (`id`, `name`, `quantity`, `price`, `supplier`, `createdAt`, `createdBy`, `isDelete`, `deletedAt`,
                     `deletedBy`, `updatedAt`, `updatedBy`)
VALUES (7, 'Thẻ Mobifone 20.000', 5, 20000, 4, NULL, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO product (`id`, `name`, `quantity`, `price`, `supplier`, `createdAt`, `createdBy`, `isDelete`, `deletedAt`,
                     `deletedBy`, `updatedAt`, `updatedBy`)
VALUES (8, 'Thẻ Viettel 100.000', 20, 100000, 1, NULL, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO product (`id`, `name`, `quantity`, `price`, `supplier`, `createdAt`, `createdBy`, `isDelete`, `deletedAt`,
                     `deletedBy`, `updatedAt`, `updatedBy`)
VALUES (9, 'Thẻ Vietnammobile 20.000', 5, 20000, 3, NULL, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO product (`id`, `name`, `quantity`, `price`, `supplier`, `createdAt`, `createdBy`, `isDelete`, `deletedAt`,
                     `deletedBy`, `updatedAt`, `updatedBy`)
VALUES (10, 'Thẻ Vinaphone 100.000', 20, 100000, 2, NULL, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO product (`id`, `name`, `quantity`, `price`, `supplier`, `createdAt`, `createdBy`, `isDelete`, `deletedAt`,
                     `deletedBy`, `updatedAt`, `updatedBy`)
VALUES (11, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL);

INSERT INTO supplier (`id`, `name`, `createdAt`, `createdBy`, `isDelete`, `deletedAt`, `deletedBy`, `updatedAt`,
                      `updatedBy`, `image`)
VALUES (1, 'Viettel', NULL, NULL, 0, NULL, NULL, NULL, NULL, 'image/viettel.png');
INSERT INTO supplier (`id`, `name`, `createdAt`, `createdBy`, `isDelete`, `deletedAt`, `deletedBy`, `updatedAt`,
                      `updatedBy`, `image`)
VALUES (2, 'Vinaphone', NULL, NULL, 0, NULL, NULL, NULL, NULL, 'image/vinaphone.png');
INSERT INTO supplier (`id`, `name`, `createdAt`, `createdBy`, `isDelete`, `deletedAt`, `deletedBy`, `updatedAt`,
                      `updatedBy`, `image`)
VALUES (3, 'Vietnammobile', NULL, NULL, 0, NULL, NULL, NULL, NULL, 'image/vietnammobile.png');
INSERT INTO supplier (`id`, `name`, `createdAt`, `createdBy`, `isDelete`, `deletedAt`, `deletedBy`, `updatedAt`,
                      `updatedBy`, `image`)
VALUES (4, 'Mobifone', NULL, NULL, 0, NULL, NULL, NULL, NULL, 'image/mobifone.png');

INSERT into user(account, password, email, role, isDelete, isActive) VALUE ('sys_admin',
                                                                            'af.$ac240BE518FABD2724DDB6F04EEB1DA5967448D7E831C08C8FA822809F74C720A9',
                                                                            'swp391grou5@gmail.com', 1, false, true);

CREATE table storage
(
    id           bigint not null AUTO_INCREMENT,
    serialNumber varchar(255),
    cardNumber   varchar(255),
    expiredAt    datetime,
    productId    int,
    createdAt    datetime,
    createdBy    int,
    updatedAt    datetime,
    updatedBy    int,
    deletedAt    datetime,
    deletedBy    int,
    primary key (id)
);

alter table storage add column isUsed boolean;

insert into storage (serialnumber, cardnumber, expiredat, productid, isUsed, createdAt, createdBy) values
                                                                                                       ('98273561904', '3982061487513', '2023-12-15', 1, false, '2023-06-13 22:40:15', 1),
                                                                                                       ('57164923806', '9720513648123', '2023-12-15', 1, false, '2023-06-13 22:40:15', 1),
                                                                                                       ('20843796521', '5047392162836', '2023-12-15', 1, false, '2023-06-13 22:40:15', 1),
                                                                                                       ('96382514073', '8253160974631', '2023-12-15', 1, false, '2023-06-13 22:40:15', 1),
                                                                                                       ('43095628715', '1429785632093', '2023-12-15', 2, false, '2023-06-13 22:40:15', 1),
                                                                                                       ('61729458302', '6731402958612', '2023-12-15', 2, false, '2023-06-13 22:40:15', 1),
                                                                                                       ('35468109273', '3206951837649', '2023-12-15', 3, false, '2023-06-13 22:40:15', 1),
                                                                                                       ('12704598634', '9164378256301', '2023-12-15', 3, false, '2023-06-13 22:40:15', 1),
                                                                                                       ('85926307145', '2510834973164', '2023-12-15', 3, false, '2023-06-13 22:40:15', 1),
                                                                                                       ('29351784608', '7059134624802', '2023-12-15', 3, false, '2023-06-13 22:40:15', 1),
                                                                                                       ('80964237518', '8165739204685', '2024-12-15', 3, false, '2023-06-13 22:40:15', 1),
                                                                                                       ('24593671805', '2071345982764', '2024-12-15', 2, false, '2023-06-13 22:40:15', 1),
                                                                                                       ('50387269410', '4398160257432', '2024-7-15 12:01:15', 2, false, '2023-06-13 22:40:15', 1),
                                                                                                       ('72149806533', '6524891073124', '2023-8-5', 2, false, '2023-06-13 22:40:15', 1),
                                                                                                       ('39851746280', '3150624978301', '2023-8-11', 2, false, '2023-06-13 22:40:15', 1),
                                                                                                       ('68493721506', '7254169305862', '2023-7-7', 2, false, '2023-06-13 22:40:15', 1),
                                                                                                       ('13572049863', '3802965714382', '2023-12-15 11:05:23', 4, false, '2023-06-13 22:40:15', 1),
                                                                                                       ('26759418307', '9762301485624', '2023-10-15 11:05:23', 4, false, '2023-06-13 22:40:15', 1),
                                                                                                       ('80364192573', '5420138679512', '2023-10-17 11:05:23', 4, false, '2023-06-13 22:40:15', 1),
                                                                                                       ('45971283604', '1985360427316', '2023-12-15 11:05:23', 4, false, '2023-06-13 22:40:15', 1);