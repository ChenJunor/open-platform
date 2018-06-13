CREATE DATABASE IF NOT EXISTS wuhu DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

CREATE USER 'wuhu'@'%' IDENTIFIED BY '123456'; 

grant all on wuhu.* to 'wuhu'@'%';  identified by ‘123456’;

use wuhu;

drop table if exists user;
create table user
(
  id int NOT NULL auto_increment,
  name char(15) not null check(name !=''),
  password char(15),
  email varchar(20) not null unique,
  cellphone varchar(20) not null unique,
  openid varchar(50) not null unique,
  level int(2) not null default 1,
  primary key(id)          
)engine=innodb default charset=utf8 auto_increment=1;

drop table if exists article;
create table article
(
	id int NOT NULL auto_increment,
	author varchar(20),
	title varchar(100) not null check(title != ''),
	description varchar(200),
	thumb_image varchar(30),
	url varchar(30),
	content mediumtext,
	sort varchar(10) not null default 'NEWS',
	enabled int not null default 1,
	create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	last_modify_time TIMESTAMP,
	last_modify_author varchar(20),
	primary key(id)
)engine=innodb default charset=utf8 auto_increment=1;

truncate table article;
insert into article(author,title,description,thumb_image,url,sort,last_modify_time,last_modify_author) values('周益','五虎理财正式成立','五虎理财于2015年开始正式筹划，16年2月正式成立。','../image/logo.jpg','news/companyEstablish.html','NEWS','2016-03-05 21:16:00','周益');
insert into article(author,title,description,thumb_image,url,sort,last_modify_time,last_modify_author) values('陈军','五虎理财公众号上线','为了提供更贴心的服务，实现掌上理财，我们的公众号荣耀登场。','../image/weixin.jpg','news/weiXinPublic.html','NEWS','2016-03-05 21:16:00','陈军');

drop table if exists role;
create table role
(
	id int NOT NULL auto_increment,
	name varchar(64),
	description varchar(200),
	create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	last_modify_time TIMESTAMP,
	last_modify_author varchar(20),
	primary key(id)
)engine=innodb default charset=utf8 auto_increment=1;

insert into role(name,description,last_modify_author) values('主管','公司财务主管,监管公司所有项目的财务数据','陈军');
insert into role(name,description,last_modify_author) values('系统管理员','分配角色、调整股份额度','陈军');
insert into role(name,description,last_modify_author) values('项目对口人','上传项目运营数据，如报表、进度等','陈军');
insert into role(name,description,last_modify_author) values('项目股东','查看经营数据，如分红、进度等','陈军');
insert into role(name,description,last_modify_author) values('公众号会员','接收推送及查看项目进度','陈军');

drop table if exists user_role;
create table user_role
(
	id int NOT NULL auto_increment,
	user_id int,
	role_id int,
	enabled int not null default 1,
	create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	last_modify_time TIMESTAMP,
	last_modify_author varchar(20),
	primary key(id)
)engine=innodb default charset=utf8 auto_increment=1;

drop table if exists res;
create table res
(
	id int NOT NULL auto_increment,
	name varchar(64),
	description varchar(200),
	url varchar(64), 
	create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	last_modify_time TIMESTAMP,
	last_modify_author varchar(20),
	primary key(id)
)engine=innodb default charset=utf8 auto_increment=1;

insert into res(name,description,url) values('ROLE_MANAGEMENT','角色管理权限','svrhall/role');

drop table if exists role_res;
create table role_res
(
	id int NOT NULL auto_increment,
	role_id int,
	res_id int,
	enabled int not null default 1,
	create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	last_modify_time TIMESTAMP,
	last_modify_author varchar(20),
	primary key(id)
)engine=innodb default charset=utf8 auto_increment=1;

insert into role_res(role_id,res_id) values(1,1);
insert into role_res(role_id,res_id) values(2,1);

drop table if exists project;
create table project
(
	id int NOT NULL auto_increment,
	name varchar(64),
	description varchar(500),
	progress varchar(64), 
	invest int,
	reward int,
	online int not null default 0,
	success int not null default 0,
	create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	last_modify_time TIMESTAMP,
	last_modify_author varchar(20),
	primary key(id)
)engine=innodb default charset=utf8 auto_increment=1;

