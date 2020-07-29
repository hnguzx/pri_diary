ALTER TABLE pd_diary MODIFY detail_photo varchar(256) COMMENT '日记封面';
ALTER TABLE pd_diary MODIFY diary_location varchar(256) COMMENT '当天日记所在位置';
ALTER TABLE pd_diary MODIFY detail_content varchar(1000) COMMENT '日记详情';
