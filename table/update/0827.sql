alter TABLE pd_user MODIFY COLUMN user_password VARCHAR(40) NOT NULL COMMENT '用户登录密码';
ALTER TABLE pd_user add user_create_time VARCHAR(14) COMMENT '用户创建时间';