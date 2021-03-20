drop table if exists MOM_USER cascade;
drop table if exists MOM_PERM cascade;

create table MOM_USER (
    COD_USER number(10) primary key,
    NAME varchar(100),
    EMAIL varchar(100),
    USERNAME varchar(30) not null,
    PASSWORD varchar(100) not null,
    ACTIVE BIT
);

create table MOM_PERM (
    COD_PERM number(10) primary key,
    PERM varchar(30) not null,
    COD_USER number(10) references MOM_USER(COD_USER)
);

create sequence MOM_SQ_USER increment by 1 start with 0 maxvalue 9999999999 no cycle;
create sequence MOM_SQ_PERM increment by 1 start with 0 maxvalue 9999999999 no cycle;

insert into MOM_USER(COD_USER, NAME, EMAIL, USERNAME, PASSWORD, ACTIVE) values (MOM_SQ_USER.nextval(), 'Tobias Lino', 'tslino@mail.com', 'tslino', '$2a$10$l9BavzhTVCxjHzkN5Lcz0.ckfUQv1hgZJHmucSaibCfgE8KS0POGy', 1);
insert into MOM_USER(COD_USER, NAME, EMAIL, USERNAME, PASSWORD, ACTIVE) values (MOM_SQ_USER.nextval(), 'Wallace Caetano', 'wcaetano@mail.com', 'wcaetano', '$2a$10$l9BavzhTVCxjHzkN5Lcz0.ckfUQv1hgZJHmucSaibCfgE8KS0POGy', 1);

insert into MOM_PERM(COD_PERM, PERM, COD_USER) VALUES (MOM_SQ_PERM.nextval(), 'ADMIN', 1);
insert into MOM_PERM(COD_PERM, PERM, COD_USER) VALUES (MOM_SQ_PERM.nextval(), 'EDITOR', 2);
insert into MOM_PERM(COD_PERM, PERM, COD_USER) VALUES (MOM_SQ_PERM.nextval(), 'REVIEWER', 2);
insert into MOM_PERM(COD_PERM, PERM, COD_USER) VALUES (MOM_SQ_PERM.nextval(), 'VIEWER', 2);