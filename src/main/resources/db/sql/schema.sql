CREATE TABLE user
(
    `id`            INT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT 'id',
    `email`         VARCHAR(30)     NOT NULL    COMMENT '이메일 (unique)',
    `password`      VARCHAR(20)     NOT NULL    COMMENT '비밀번호',
    `name`          VARCHAR(10)     NOT NULL    COMMENT '이름',
    `phone_number`  CHAR(12)        NOT NULL    COMMENT '전화번호 (unique)',
    `id_number`     CHAR(7)        NOT NULL    COMMENT 'yymmdd-s 7자리 (unique)',
    CONSTRAINT PK_user PRIMARY KEY (id)
);