CREATE TABLE USR_INFO(
    id bigint not null auto_increment primary key,
    usr_key varchar(32) not null unique,
    usr_reg_num varchar(50) not null,
    usr_nm varchar(20) not null,
    usr_icm_amt bigint default 0 not null
);

CREATE TABLE USR_INFO(
    id bigint not null auto_increment primary key,
    usr_key varchar(32) not null unique,
    usr_reg_num varchar(50) not null,
    usr_nm varchar(20) not null,
    usr_icm_amt bigint default 0 not null
);