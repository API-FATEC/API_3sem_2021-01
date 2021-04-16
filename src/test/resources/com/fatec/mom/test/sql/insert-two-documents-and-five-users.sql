-- insert-two-documents-and-five-user.sql
DROP TABLE IF EXISTS MOM_DOCUMENTO CASCADE ;
DROP TABLE IF EXISTS MOM_USER CASCADE;

DROP SEQUENCE IF EXISTS MOM_DOCUMENTO_SQ;
DROP SEQUENCE IF EXISTS MOM_USER_SQ;

--comment: Create table MOM_DOC
CREATE TABLE MOM_DOCUMENTO (
   DOC_COD NUMBER(10) CONSTRAINT PK_DOC_COD PRIMARY KEY,
   DOC_DATA_CRIA DATE DEFAULT CURRENT_DATE,
   DOC_NOME VARCHAR(30) NOT NULL,
   DOC_PN INTEGER NOT NULL,
   DOC_TRACO INTEGER NOT NULL,

   CONSTRAINT UK_DOC_PN_TRACO_NOM UNIQUE (DOC_NOME, DOC_PN, DOC_TRACO)
);
CREATE SEQUENCE MOM_DOCUMENTO_SQ START WITH 3;

--comment: Create table MOM_USER
CREATE TABLE MOM_USER (
    USR_COD NUMBER(10) CONSTRAINT PK_USER_COD PRIMARY KEY,
    USR_EMAIL VARCHAR(50) NOT NULL,
    USR_ACTION VARCHAR(20) NOT NULL,
    USR_DOC NUMBER(10) CONSTRAINT FK_USER_DOC_COD REFERENCES MOM_DOCUMENTO(DOC_COD)
);
CREATE SEQUENCE MOM_USER_SQ START WITH 6;

INSERT INTO MOM_DOCUMENTO(doc_cod, doc_data_cria, doc_nome, doc_pn, doc_traco) VALUES (1, DATE'2021-06-23', 'Documento_Teste', 1111, 50);
INSERT INTO MOM_DOCUMENTO(doc_cod, doc_data_cria, doc_nome, doc_pn, doc_traco) VALUES (2, DATE'2021-06-23', 'Documento_Teste', 1111, 55);

INSERT INTO MOM_USER(usr_cod, usr_email, usr_action, usr_doc) VALUES (1, 'devanir.ramos@gmail.com', 'EDIT_CODELIST', 1);
INSERT INTO MOM_USER(usr_cod, usr_email, usr_action, usr_doc) VALUES (2, 'jackson95@gmail.com', 'NEW_BLOCK', 1);
INSERT INTO MOM_USER(usr_cod, usr_email, usr_action, usr_doc) VALUES (3, 'devanir.ramos@gmail.com', 'NEW_CODELIST', 2);
INSERT INTO MOM_USER(usr_cod, usr_email, usr_action, usr_doc) VALUES (4, 'devanir.ramos@gmail.com', 'NEW_BLOCK', 2);
INSERT INTO MOM_USER(usr_cod, usr_email, usr_action, usr_doc) VALUES (5, 'billiam.gx@gmail.com', 'EDIT_CODELIST', 2);