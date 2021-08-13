CREATE TABLE user
(
    `id`           INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `email`        VARCHAR(30)  NOT NULL COMMENT '이메일 (unique)',
    `password`     VARCHAR(20)  NOT NULL COMMENT '비밀번호',
    `name`         VARCHAR(10)  NOT NULL COMMENT '이름',
    `phone_number` CHAR(12)     NOT NULL COMMENT '전화번호 (unique)',
    `id_number`    CHAR(7)      NOT NULL COMMENT 'yymmdd-s 7자리 (unique)',
    CONSTRAINT PK_user PRIMARY KEY (id)
);

ALTER TABLE user
    COMMENT '일반 사용자';

CREATE UNIQUE INDEX UQ_user_1
    ON user (email);

CREATE UNIQUE INDEX UQ_user_2
    ON user (phone_number);

CREATE UNIQUE INDEX UQ_user_3
    ON user (id_number);

-- virus Table Create SQL
CREATE TABLE virus
(
    `id`   INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `code` VARCHAR(10)  NULL COMMENT '코드명 (uniqe)',
    `name` VARCHAR(50)  NULL COMMENT '이름',
    CONSTRAINT PK_virus PRIMARY KEY (id)
);

ALTER TABLE virus
    COMMENT '바이러스 종류';


-- agency Table Create SQL
CREATE TABLE agency
(
    `id`             INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `email`          VARCHAR(30)  NOT NULL COMMENT '이메일 (uniqe)',
    `password`       VARCHAR(20)  NOT NULL COMMENT '비밀번호',
    `name`           VARCHAR(20)  NOT NULL COMMENT '기관 명',
    `phone_number`   CHAR(12)     NOT NULL COMMENT '전화번호',
    `zip_code`       CHAR(7)      NOT NULL COMMENT '우편번호',
    `si_do`          VARCHAR(20)  NOT NULL COMMENT '시/도',
    `si_gun_gu`      VARCHAR(20)  NOT NULL COMMENT '시/군/구',
    `eup_myeon_dong` VARCHAR(50)  NOT NULL COMMENT '읍/면/동/도로명',
    `address`        VARCHAR(50)  NOT NULL COMMENT '나머지 주소',
    `lat`            FLOAT        NOT NULL COMMENT '위도',
    `lng`            FLOAT        NOT NULL COMMENT '경도',
    CONSTRAINT PK_agency PRIMARY KEY (id)
);

ALTER TABLE agency
    COMMENT '예방 접종 기관 (기관 사용자)';

CREATE UNIQUE INDEX UQ_agency_1
    ON agency (email);


-- vaccine Table Create SQL
CREATE TABLE vaccine
(
    `id`       INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `code`     CHAR(2)      NOT NULL COMMENT '2글자 코드명 (uniqe)',
    `name`     VARCHAR(20)  NOT NULL COMMENT '백신 명 (uniqe)',
    `dose`     INT          NOT NULL COMMENT '필요 접종 횟수',
    `virus_id` INT UNSIGNED NOT NULL COMMENT '바이러스 종류',
    CONSTRAINT PK_vaccine PRIMARY KEY (id)
);

ALTER TABLE vaccine
    COMMENT '백신 종류';

CREATE UNIQUE INDEX UQ_vaccine_1
    ON vaccine (code);

CREATE UNIQUE INDEX UQ_vaccine_2
    ON vaccine (name);

ALTER TABLE vaccine
    ADD CONSTRAINT FK_vaccine_virus_id_virus_id FOREIGN KEY (virus_id)
        REFERENCES virus (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- acquired_vaccine Table Create SQL
CREATE TABLE acquired_vaccine
(
    `id`           BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'id',
    `agency_id`    INT UNSIGNED NOT NULL COMMENT '기관 id',
    `vaccine_id`   INT UNSIGNED NOT NULL COMMENT '백신 id',
    `amount`       INT          NOT NULL COMMENT '확보 백신 물량',
    `vaccinate_at` TIMESTAMP    NOT NULL COMMENT '백신 접종 시간',
    `rest_amount`  INT          NOT NULL COMMENT '잔여 백신 물량',
    CONSTRAINT PK_acquired_vaccine PRIMARY KEY (id)
);

ALTER TABLE acquired_vaccine
    COMMENT '확보된 백신 물량';

ALTER TABLE acquired_vaccine
    ADD CONSTRAINT FK_acquired_vaccine_agency_id_agency_id FOREIGN KEY (agency_id)
        REFERENCES agency (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE acquired_vaccine
    ADD CONSTRAINT FK_acquired_vaccine_vaccine_id_vaccine_id FOREIGN KEY (vaccine_id)
        REFERENCES vaccine (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- booking Table Create SQL
CREATE TABLE booking
(
    `user_id`             INT UNSIGNED NOT NULL COMMENT '사용자 id',
    `acquired_vaccine_id` BIGINT       NOT NULL COMMENT '확보 백신 id',
    `vaccinate_at`        TIMESTAMP    NOT NULL COMMENT '백신 접종 시간',
    CONSTRAINT PK_booking PRIMARY KEY (user_id, acquired_vaccine_id)
);

ALTER TABLE booking
    COMMENT '백신예약(acquired_vaccine <-> user)';

ALTER TABLE booking
    ADD CONSTRAINT FK_booking_user_id_user_user_id FOREIGN KEY (user_id)
        REFERENCES user (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE booking
    ADD CONSTRAINT FK_booking_acquired_vaccine_id_acquired_vaccine_id FOREIGN KEY (acquired_vaccine_id)
        REFERENCES acquired_vaccine (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- vaccinated_state Table Create SQL
CREATE TABLE vaccinated_state
(
    `user_id`    INT UNSIGNED NOT NULL COMMENT '사용자 id',
    `vaccine_id` INT UNSIGNED NOT NULL COMMENT '백신 id',
    `dose_num`   INT          NOT NULL COMMENT '접종 횟수',
    CONSTRAINT PK_vaccinated_state PRIMARY KEY (user_id, vaccine_id)
);

ALTER TABLE vaccinated_state
    COMMENT '사용자별 백신 접종 상태';

ALTER TABLE vaccinated_state
    ADD CONSTRAINT FK_vaccinated_state_user_id_user_user_id FOREIGN KEY (user_id)
        REFERENCES user (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE vaccinated_state
    ADD CONSTRAINT FK_vaccinated_state_vaccine_id_vaccine_id FOREIGN KEY (vaccine_id)
        REFERENCES vaccine (id) ON DELETE RESTRICT ON UPDATE RESTRICT;