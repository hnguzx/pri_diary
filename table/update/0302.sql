alter table pd_message modify msg_create_time datetime not null comment '发送时间';

alter table pd_diary modify diary_create_time datetime null comment '日记创建时间';

alter table pd_diary modify diary_update_time datetime null comment '日记最后更新时间';

alter table pd_message modify msg_is_readed varchar(1) not null comment '接收者是否查看';

alter table pd_comment modify comment_create_time datetime not null comment '评论时间';

alter table pd_blog modify blog_create_time DATETIME not null comment '博客创建时间';

alter table pd_blog modify blog_update_time DATETIME not null comment '博客更新时间';

