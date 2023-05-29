use sellPhoneCard;

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
