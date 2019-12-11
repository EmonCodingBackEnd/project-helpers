drop table if exists param;

/*==============================================================*/
/* Table: param                                                 */
/*==============================================================*/
create table param
(
   id                   bigint(20) not null,
   param_code           varchar(50) not null comment '系统参数代码',
   param_value          varchar(2000) not null comment '系统参数值',
   param_desc           varchar(256) not null comment '系统参数的描述',
   showable             tinyint not null comment '是否展示
            0-否
            1-是
            该字段的作用，如果可以展示，则展示到页面给用户；否则，用户没有渠道看到该参数。',
   enabled              tinyint not null comment '是否启用
            0-停用
            1-启用',
   deleted              tinyint not null default 0 comment '记录状态
            0-未删除
            1-已删除',
   create_time          timestamp not null default current_timestamp comment '创建时间',
   modify_time          timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
   version              int not null default 0 comment '版本控制',
   primary key (id)
);

alter table param comment '系统参数表';
