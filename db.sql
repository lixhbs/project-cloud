use d_user;
CREATE TABLE user
(
    ID           VARCHAR(32) NOT NULL COMMENT 'ID',
    REVISION     INT COMMENT '乐观锁',
    CREATED_BY   VARCHAR(32) COMMENT '创建人',
    CREATED_TIME DATETIME COMMENT '创建时间',
    UPDATED_BY   VARCHAR(32) COMMENT '更新人',
    UPDATED_TIME DATETIME COMMENT '更新时间',
    name         VARCHAR(128) COMMENT '姓名',
    account      VARCHAR(128) COMMENT '账号',
    phone        VARCHAR(128) COMMENT '手机号码',
    pwd          VARCHAR(1024) COMMENT '密码',
    role         INT COMMENT '角色',
    status       INT COMMENT '状态 0、删除；1、正常',
    openid       VARCHAR(128) COMMENT '微信标识',
    head_img     VARCHAR(3072) COMMENT '头像',
    sex          INT COMMENT '性别 0表示女，1表示男',
    secret       VARCHAR(64) COMMENT 'secret',
    mail         VARCHAR(64) COMMENT '邮箱',
    slogan       VARCHAR(3072) COMMENT '用户个人性签名',
    PRIMARY KEY (ID)
) COMMENT = '用户 ';

CREATE TABLE project
(
    ID             VARCHAR(32) NOT NULL COMMENT 'ID',
    REVISION       INT COMMENT '乐观锁',
    CREATED_BY     VARCHAR(32) COMMENT '创建人',
    CREATED_TIME   DATETIME COMMENT '创建时间',
    UPDATED_BY     VARCHAR(32) COMMENT '更新人',
    UPDATED_TIME   DATETIME COMMENT '更新时间',
    name           VARCHAR(32) COMMENT '项目名称',
    des            VARCHAR(3072) COMMENT '项目描述',
    principal_id   VARCHAR(32) COMMENT '负责人ID',
    principal_name VARCHAR(32) COMMENT '负责人',
    module_name    VARCHAR(32) COMMENT '项目工程名称',
    status         VARCHAR(32) COMMENT '项目状态',
    PRIMARY KEY (ID)
) COMMENT = ' ';


CREATE TABLE project_user_map
(
    ID           VARCHAR(32) NOT NULL COMMENT 'ID',
    REVISION     INT COMMENT '乐观锁',
    CREATED_BY   VARCHAR(32) COMMENT '创建人',
    CREATED_TIME DATETIME COMMENT '创建时间',
    UPDATED_BY   VARCHAR(32) COMMENT '更新人',
    UPDATED_TIME DATETIME COMMENT '更新时间',
    project_id   VARCHAR(32) COMMENT '项目id',
    user_id      VARCHAR(32) COMMENT '用户id',
    role         VARCHAR(32) COMMENT '角色',
    status       INT COMMENT '状态',
    PRIMARY KEY (ID)
) COMMENT = ' ';
