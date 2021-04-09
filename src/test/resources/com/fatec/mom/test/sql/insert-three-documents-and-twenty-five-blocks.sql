-- insert-three-documents-and-twenty-five-blocks.sql
DROP TABLE IF EXISTS MOM_DOC_BLOCO CASCADE ;
DROP TABLE IF EXISTS MOM_DOCUMENTO CASCADE ;
DROP TABLE IF EXISTS MOM_BLOCO CASCADE ;

DROP SEQUENCE IF EXISTS MOM_DOCUMENTO_SQ;
DROP SEQUENCE IF EXISTS MOM_BLOCO_SQ;

--comment: Create table MOM_DOC
CREATE TABLE MOM_DOCUMENTO (
   DOC_COD NUMBER(10) CONSTRAINT PK_DOC_COD PRIMARY KEY,
   DOC_DATA_CRIA DATE DEFAULT CURRENT_DATE,
   DOC_NOME VARCHAR(30) NOT NULL,
   DOC_PN INTEGER NOT NULL,
   DOC_TRACO INTEGER NOT NULL,

   CONSTRAINT UK_DOC_PN_TRACO_NOM UNIQUE (DOC_NOME, DOC_PN, DOC_TRACO)
);
CREATE SEQUENCE MOM_DOCUMENTO_SQ start with 8;


--comment: Create table MOM_BLOCO
CREATE TABLE MOM_BLOCO (
   BLC_COD NUMBER(10) CONSTRAINT PK_BLOCO_COD PRIMARY KEY,
   BLC_SECAO VARCHAR(10) NOT NULL,
   BLC_SUB_SECAO VARCHAR(10),
   BLC_NUMERO INTEGER NOT NULL,
   BLC_NOME VARCHAR(30),
   BLC_CODIGO INTEGER NOT NULL,
   BLC_ORDER INTEGER
);
CREATE SEQUENCE MOM_BLOCO_SQ start with 27;

--comment: Create intermediate table between MOM_DOCUMENT and MOM_BLOCO as MOM_DOC_BLOCO
CREATE TABLE MOM_DOC_BLOCO (
   DOC_COD NUMBER(10) CONSTRAINT FK_DOC_BLOCO_DOC_COD REFERENCES MOM_DOCUMENTO(DOC_COD),
   BLC_COD NUMBER(10) CONSTRAINT FK_DOC_BLOCO_BLC_COD REFERENCES MOM_BLOCO(BLC_COD)
);

--comment: Inserts documents based on the model codelist
INSERT INTO MOM_DOCUMENTO(doc_cod, doc_data_cria, doc_nome, doc_pn, doc_traco) VALUES (5, DATE'2021-06-23', 'ABC', 1234, 50);
INSERT INTO MOM_DOCUMENTO(doc_cod, doc_data_cria, doc_nome, doc_pn, doc_traco) VALUES (6, DATE'2021-06-23', 'ABC', 1234, 55);
INSERT INTO MOM_DOCUMENTO(doc_cod, doc_data_cria, doc_nome, doc_pn, doc_traco) VALUES (7, DATE'2021-06-23', 'ABC', 1234, 60);

--comment: Inserts blocks based on the model codelist
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (1, 0, 0, 'Letter', 50, 1);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (2, 0, 0, 'Letter', 55, 2);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (3, 0, 0, 'Letter', 60, 3);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (4, 0, 1, 'Cover', 1, 4);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (5, 0, 1, 'Cover', 2, 5);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (6, 0, 1, 'Cover', 3, 6);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (7, 0, 2, 'LEP', 1, 7);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (8, 0, 2, 'LEP', 2, 8);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (9, 0, 2, 'LEP', 3, 9);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (10, 0, 3, 'TOC', 1, 10);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (11, 0, 3, 'TOC', 2, 11);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (12, 2, 4, 'Introduction', 1, 12);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (13, 2, 4, 'Introduction', 2, 13);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (14, 2, 4, 'Introduction', 3, 14);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_sub_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (15, 3, 1, 3, 'Episodio 2', 14, 15);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_sub_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (16, 3, 1, 3, 'Episodio 2', 15, 16);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (17, 4, 2, 'Episodio 3', 1, 17);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (18, 4, 2, 'Episodio 3', 2, 18);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (19, 4, 2, 'Episodio 3', 3, 19);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_sub_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (20, 5, 4, 8, 'Episodio 1', 12, 20);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_sub_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (21, 5, 6, 3, 'Episodio 4', 1, 21);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_sub_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (22, 5, 6, 3, 'Episodio 4', 2, 22);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (23, 'AP01', 2, 'Appendix', 1, 23);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (24, 'S03', 5, 'Mars', 1, 24);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (25, 'S03', 10, 'Copyright', 1, 25);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo, blc_order) VALUES (26, 'S03', 10, 'Copyright', 2, 26);

--comment: Relates blocks to documents and traits
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (5, 1);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (5, 4);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (5, 7);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (5, 10);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (5, 12);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (5, 15);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (5, 18);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (5, 20);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (5, 22);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (5, 23);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (5, 24);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (5, 25);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (6, 2);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (6, 5);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (6, 8);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (6, 11);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (6, 13);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (6, 16);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (6, 19);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (6, 20);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (6, 23);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (6, 24);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (6, 25);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (7, 3);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (7, 6);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (7, 9);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (7, 10);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (7, 14);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (7, 15);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (7, 17);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (7, 20);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (7, 21);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (7, 23);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (7, 24);
INSERT INTO MOM_DOC_BLOCO (doc_cod, blc_cod) VALUES (7, 26);
