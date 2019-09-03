drop database if exists ADI_Test;

create database ADI_Test;

use ADI_Test;

create table users (
	id int not null auto_increment,
	name varchar(128) not null,
	birthdate varchar(64) not null,
	constraint pk_users primary key (id),
	constraint uk_users_name unique key (name)
);
