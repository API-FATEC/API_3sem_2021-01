--liquibase formatted sql

--changeset tslino:1
drop table if exists MOM_BLOCO cascade;
drop table if exists MOM_DOCUMENTO cascade;
drop table if exists MOM_DOC_BLOCO cascade;
drop sequence if exists MOM_DOCUMENTO_SQ;
drop sequence if exists MOM_BLOCO_SQ;

--changeset tslino:2
--comment: Create table MOM.MOM_DOC
CREATE TABLE MOM_DOCUMENTO (
   DOC_COD SERIAL CONSTRAINT PK_DOC_COD PRIMARY KEY,
   DOC_DATA_CRIA DATE DEFAULT CURRENT_DATE,
   DOC_NOME VARCHAR(30) NOT NULL,
   DOC_PN INTEGER NOT NULL,
   DOC_TRACO INTEGER NOT NULL,

   CONSTRAINT UK_DOC_PN_TRACO_NOM UNIQUE (DOC_NOME, DOC_PN, DOC_TRACO)
);
CREATE SEQUENCE MOM_DOCUMENTO_SQ;


--changeset tslino:3
--comment: Create table MOM_BLOCO
CREATE TABLE MOM_BLOCO (
   BLC_COD SERIAL CONSTRAINT PK_BLOCO_COD PRIMARY KEY,
   BLC_SECAO VARCHAR(10) NOT NULL,
   BLC_SUB_SECAO VARCHAR(10),
   BLC_NUMERO INTEGER NOT NULL,
   BLC_NOME VARCHAR(30),
   BLC_CODIGO INTEGER NOT NULL
);
CREATE SEQUENCE mom.MOM_BLOCO_SQ;

--changeset tslino:4
--comment: Create intermediate table between MOM_DOCUMENT and MOM_BLOCO as MOM_DOC_BLOCO
CREATE TABLE MOM_DOC_BLOCO (
   DOC_COD SERIAL CONSTRAINT FK_DOC_BLOCO_DOC_COD REFERENCES MOM_DOCUMENTO(DOC_COD),
   BLC_COD SERIAL CONSTRAINT FK_DOC_BLOCO_BLC_COD REFERENCES MOM_BLOCO(BLC_COD)
);

--changeset tslino:5
--comment: Inserts documents based on the model codelist
INSERT INTO MOM_DOCUMENTO(doc_cod, doc_nome, doc_pn, doc_traco) VALUES (nextval('MOM_DOCUMENTO_SQ'), 'ABC', 1234, 50);
INSERT INTO MOM_DOCUMENTO(doc_cod, doc_nome, doc_pn, doc_traco) VALUES (nextval('MOM_DOCUMENTO_SQ'), 'ABC', 1234, 55);
INSERT INTO MOM_DOCUMENTO(doc_cod, doc_nome, doc_pn, doc_traco) VALUES (nextval('MOM_DOCUMENTO_SQ'), 'ABC', 5555, 50);

--changeset tslino:6
-- --comment: Inserts blocks based on the model codelist
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 0, 0, 'Letter', 50);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 0, 0, 'Letter', 55);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 0, 0, 'Letter', 60);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 0, 1, 'Cover', 1);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 0, 1, 'Cover', 2);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 0, 1, 'Cover', 3);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 0, 2, 'LEP', 1);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 0, 2, 'LEP', 2);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 0, 2, 'LEP', 3);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 0, 3, 'TOC', 1);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 0, 3, 'TOC', 2);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 2, 4, 'Introduction', 1);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 2, 4, 'Introduction', 2);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 2, 4, 'Introduction', 3);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_sub_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 3, 1, 3, 'Episodio 2', 14);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_sub_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 3, 1, 3, 'Episodio 2', 15);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 4, 2, 'Episodio 3', 1);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 4, 2, 'Episodio 3', 2);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 4, 2, 'Episodio 3', 3);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_sub_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 5, 4, 8, 'Episodio 1', 12);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_sub_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 5, 6, 3, 'Episodio 4', 1);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_sub_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 5, 6, 3, 'Episodio 4', 2);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 'AP01', 2, 'Appendix', 1);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 'S03', 5, 'Mars', 1);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 'S03', 10, 'Copyright', 1);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (nextval('MOM_BLOCO_SQ'), 'S03', 10, 'Copyright', 2);
