create table user(
	user_id int unsigned auto_increment not null,
	user_email varchar(30) unique not null,
    user_password varchar(20) not null,
    user_name varchar(10) not null,
    user_phonenumber char(12) unique not null,
    user_idnumber char(14) unique not null,  # 주민번호
    user_zipcode char(7) not null,  # 우편번호
    user_si varchar(15) not null,  # 시
    user_gu varchar(10) not null,  # 구
    user_dong varchar(10) not null,  # 동
    user_address varchar(20) not null,  # 상세주소
    user_latitude float not null,  # 위도
    user_longtitude float not null,  # 경도
    primary key (user_id)
);