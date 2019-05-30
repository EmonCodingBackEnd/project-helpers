-- --------------------------------------------------------------------------------
-- 初始化数据脚本
-- 要求：
--    1、保证sql可以重复执行，也就是insert之前先delete（删除表还是某一条，根据需要而定）
--    2、sql关键字推荐大写
-- --------------------------------------------------------------------------------

-- 数据文件
-- 示例
-- DELETE FROM `user` WHERE categoryId=1;
-- INSERT INTO `user` (categoryId, create_time, modify_time, version, acct, intro, mgr_shops, mobile, name, pwd, role_id, sex, status) VALUES(1, '2018-04-16 18:46:01.000', '2018-04-16 18:46:04.000', 0, 'acct', NULL, '123456,678910', '13777485757', 'wtf', 'e10adc3949ba59abbe56e057f20f883e', 0, NULL, '1');