--	创建数据库
drop database if exists wise ;
create database	wise default charset utf8;

--	创建用户
create user 'wise'@'localhost' identified by 'wise';


--	分配权限
grant all privileges on wise.* to 'wise'@'localhost' with grant option;

grant super on *.* to 'wise'@'localhost' with grant option;

flush privileges;