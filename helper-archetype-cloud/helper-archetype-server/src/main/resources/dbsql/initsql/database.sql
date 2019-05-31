-- --------------------------------------------------------------------------------
-- 不会被SpringBoot调用，项目初始化时通过命令行创建
-- 建库脚本
-- --------------------------------------------------------------------------------

-- 创建用户
create user 'springboot'@'%' identified by 'SpringBoot@123';

-- 授权用户
grant all privileges on *.* to 'springboot'@'%' with grant option;

-- 如果是MySQL8会报错：Unable to load authentication plugin 'caching_sha2_password'.
-- 更新密码
alter user 'springboot'@'%' identified with mysql_native_password by 'SpringBoot@123';

-- 创建数据库
CREATE DATABASE IF NOT EXISTS selldb DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 切换到数据库
use selldb;

-- ================================================================================

-- 创建备份用户
create user 'backup'@'%' identified by 'Backup@123';

-- 授权用户
grant select,reload,lock tables,replication client,show view,event,process on *.* to 'backup'@'%' with grant option;

-- 如果是MySQL8会报错：Unable to load authentication plugin 'caching_sha2_password'.
-- 更新密码
alter user 'springboot'@'%' identified with mysql_native_password by 'SpringBoot@123';