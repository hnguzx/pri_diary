create table if not exists pd_authority
(
    authority_id          int auto_increment
        primary key,
    authority_name        varchar(32)  null,
    authority_description varchar(255) null,
    authority_url         varchar(255) null
);

create table if not exists pd_blog
(
    blog_id          int(10)       not null comment '博客ID'
        primary key,
    user_id          int(10)       not null comment '博客所属用户ID',
    blog_type        varchar(20)   not null comment '博客类型（一句话/小故事）',
    blog_image       varchar(255)  null comment '博客图片',
    blog_context     varchar(1000) not null comment '博客具体内容',
    blog_label       varchar(20)   not null comment '博客标签',
    blog_create_time varchar(14)   not null comment '博客创建时间',
    blog_update_time varchar(14)   not null comment '博客更新时间'
);

create table if not exists pd_comment
(
    comment_id          int(10)       not null comment '评论ID'
        primary key,
    blog_id             int(10)       not null comment '评论所属博客ID',
    comment_user_id     int(10)       not null comment '评论人ID',
    blog_owner_id       int(10)       not null comment '博客所属人ID',
    comment_create_time varchar(14)   not null comment '评论时间',
    commented_user_id   int(10)       not null comment '被评论者ID',
    comment_context     varchar(1000) not null comment '评论内容'
);

create table if not exists pd_diary
(
    user_id           int(10)       not null comment '日记所属用户id',
    diary_id          int(10) auto_increment comment '日记唯一标识'
        primary key,
    diary_photo       varchar(256)  null comment '日记封面',
    diary_title       varchar(50)   not null comment '日记标题',
    diary_weather     varchar(30)   null comment '当天天气',
    diary_mood        varchar(20)   not null comment '当天心情',
    diary_event       varchar(30)   not null comment '当天主要事件',
    diary_content     varchar(1000) null comment '日记详情',
    diary_location    varchar(256)  null comment '当天日记所在位置',
    diary_longitude   varchar(20)   null comment '日记记录经度',
    diary_latitude    varchar(20)   null comment '日记记录纬度',
    diary_create_time varchar(14)   null comment '日记创建时间',
    diary_create_day  varchar(8)    null comment '日记创建日期',
    diary_update_time varchar(14)   null comment '日记最后更新时间'
);

create table if not exists pd_friend
(
    friend_id           int auto_increment comment '好友ID'
        primary key,
    my_user_id          int(10)          not null comment '我的用户ID',
    my_email            varchar(100)     null comment '我的邮箱',
    my_phone            varchar(30)      null comment '我的手机号码',
    friend_user_id      int(10)          not null comment '好友的用户ID',
    friend_email        varchar(100)     null comment '好友的邮箱',
    friend_phone        varchar(30)      null comment '好友的手机号码',
    friend_remark       varchar(100)     null comment '好友的备注',
    friend_apply_result int(1) default 0 not null comment '好友申请结果，0-拒绝，1-同意'
);

create table if not exists pd_message
(
    msg_id          int(10) auto_increment comment '消息主键'
        primary key,
    msg_sender      int(10)      not null comment '发送用户',
    msg_receiver    int(10)      not null comment '接收用户',
    msg_create_time varchar(14)  not null comment '发送时间',
    msg_is_readed   varchar(10)  not null comment '接收者是否查看',
    msg_content     varchar(255) not null comment '消息内容（图片，音频，文件2.0处理）'
);

create table if not exists pd_praise
(
    praise_id  int(10) not null comment '点赞ID'
        primary key,
    user_id    int(10) not null comment '点赞用户ID',
    blog_id    int(10) not null comment '所属博客ID',
    comment_id int(10) null comment '所属评论ID'
);

create table if not exists pd_role
(
    role_id   int auto_increment
        primary key,
    role_name varchar(255) not null
);

create table if not exists pd_role_authority
(
    role_authority_id int auto_increment
        primary key,
    role_id           int not null,
    authority_id      int not null
);

create table if not exists pd_user
(
    user_id              int(10) auto_increment comment '用户唯一标识'
        primary key,
    user_name            varchar(30)  not null comment '用户昵称',
    user_birthday        varchar(10)  not null comment '用户出生日期',
    user_sex             varchar(10)  not null comment '用户性别',
    user_password        varchar(255) not null comment '用户登录密码',
    user_state           varchar(20)  not null comment '用户状态',
    user_phone           varchar(30)  null comment '手机号码',
    user_email           varchar(100) null comment '邮箱地址',
    user_head            varchar(255) null comment '用户头像图片存储地址',
    user_create_time     datetime     null comment '用户创建时间',
    user_last_login_time datetime     null comment '上次登录时间',
    user_update_time     datetime     null comment '用户信息修改时间',
    constraint pd_user_user_name_uindex
        unique (user_name)
);

create table if not exists pd_user_role
(
    user_role_id int auto_increment
        primary key,
    user_id      int not null,
    role_id      int not null
);

