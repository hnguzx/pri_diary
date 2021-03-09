alter table pd_message modify msg_create_time datetime not null comment '发送时间';

alter table pd_diary modify diary_create_time datetime null comment '日记创建时间';

alter table pd_diary modify diary_update_time datetime null comment '日记最后更新时间';

alter table pd_message modify msg_is_readed varchar(1) not null comment '接收者是否查看';

alter table pd_comment modify comment_create_time datetime not null comment '评论时间';

alter table pd_blog modify blog_create_time DATETIME not null comment '博客创建时间';

alter table pd_blog modify blog_update_time DATETIME not null comment '博客更新时间';

alter table pd_blog modify blog_id varchar(10) not null comment '博客ID';

create unique index pd_blog_blog_id_uindex
    on pd_blog (blog_id);

alter table pd_message modify msg_id varchar(21) not null comment '消息主键';

CREATE TABLE pub_sequence (
      BST_NAME VARCHAR ( 50 ) NOT NULL,
      BST_PREFIX VARCHAR ( 3 ) NOT NULL UNIQUE,
      BST_CURVAL BIGINT NOT NULL,
      BST_INCREMENT INT NOT NULL DEFAULT 1,
      BST_BEGINVAL BIGINT NOT NULL,
      BST_DESC VARCHAR ( 150 ) NOT NULL,
      PRIMARY KEY ( BST_NAME )
);

ALTER TABLE pd_message AUTO_INCREMENT = 10000;
ALTER TABLE pd_comment AUTO_INCREMENT = 10000;
ALTER TABLE pd_diary AUTO_INCREMENT = 10000;
ALTER TABLE pd_friend AUTO_INCREMENT = 10000;
ALTER TABLE pd_message AUTO_INCREMENT = 10000;
ALTER TABLE pd_praise AUTO_INCREMENT = 10000;
ALTER TABLE pd_user AUTO_INCREMENT = 10000;
ALTER TABLE pd_blog AUTO_INCREMENT = 10000;

CREATE DEFINER=`root`@`localhost` FUNCTION `getSequence`(
    businessType varchar(50)
) RETURNS bigint(20)
BEGIN DECLARE
    flowNo BIGINT;
UPDATE pub_sequence SET BST_CURVAL=LAST_INSERT_ID(BST_CURVAL+BST_INCREMENT) WHERE BST_PREFIX=businessType;
IF row_count()=0 THEN
    SET flowNo=0;
ELSE
    SET flowNo=LAST_INSERT_ID();
END IF;
RETURN flowNo;
END