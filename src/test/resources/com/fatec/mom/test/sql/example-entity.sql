drop table if exists EXAMPLE_ENTITY cascade;

create table EXAMPLE_ENTITY (
    cod_entity number(4) primary key,
    nome varchar(30)
);

insert into EXAMPLE_ENTITY(cod_entity, nome) VALUES (1, 'Tobias Lino');
insert into EXAMPLE_ENTITY(cod_entity, nome) VALUES (2, 'Wallace Caetano');