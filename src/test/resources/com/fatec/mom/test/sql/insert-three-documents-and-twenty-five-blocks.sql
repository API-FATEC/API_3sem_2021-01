-- insert-three-documents-and-twenty-five-blocks.sql
DROP TABLE MOM_DOC_BLOCO CASCADE ;
DROP TABLE MOM_DOCUMENTO CASCADE ;
DROP TABLE MOM_BLOCO CASCADE ;

DROP SEQUENCE MOM_DOCUMENTO_SQ;
DROP SEQUENCE MOM_BLOCO_SQ;

--comment: Create table MOM_DOC
CREATE TABLE MOM_DOCUMENTO (
   DOC_COD NUMBER(10) CONSTRAINT PK_DOC_COD PRIMARY KEY,
   DOC_DATA_CRIA DATE DEFAULT CURRENT_DATE,
   DOC_NOME VARCHAR(30) NOT NULL,
   DOC_PN INTEGER NOT NULL,
   DOC_TRACO INTEGER NOT NULL,

   CONSTRAINT UK_DOC_PN_TRACO_NOM UNIQUE (DOC_NOME, DOC_PN, DOC_TRACO)
);
CREATE SEQUENCE MOM_DOCUMENTO_SQ;


--comment: Create table MOM_BLOCO
CREATE TABLE MOM_BLOCO (
   BLC_COD NUMBER(10) CONSTRAINT PK_BLOCO_COD PRIMARY KEY,
   BLC_SECAO VARCHAR(10) NOT NULL,
   BLC_SUB_SECAO VARCHAR(10),
   BLC_NUMERO INTEGER NOT NULL,
   BLC_NOME VARCHAR(30),
   BLC_CODIGO INTEGER NOT NULL
);
CREATE SEQUENCE MOM_BLOCO_SQ;

--comment: Create intermediate table between MOM_DOCUMENT and MOM_BLOCO as MOM_DOC_BLOCO
CREATE TABLE MOM_DOC_BLOCO (
   DOC_COD NUMBER(10) CONSTRAINT FK_DOC_BLOCO_DOC_COD REFERENCES MOM_DOCUMENTO(DOC_COD),
   BLC_COD NUMBER(10) CONSTRAINT FK_DOC_BLOCO_BLC_COD REFERENCES MOM_BLOCO(BLC_COD)
);

--comment: Inserts documents based on the model codelist
INSERT INTO MOM_DOCUMENTO(doc_cod, doc_nome, doc_pn, doc_traco) VALUES (5, 'ABC', 1234, 50);
INSERT INTO MOM_DOCUMENTO(doc_cod, doc_nome, doc_pn, doc_traco) VALUES (6, 'ABC', 1234, 55);
INSERT INTO MOM_DOCUMENTO(doc_cod, doc_nome, doc_pn, doc_traco) VALUES (7, 'ABC', 5555, 50);

--comment: Inserts blocks based on the model codelist
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (1, 0, 0, 'Letter', 50);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (2, 0, 0, 'Letter', 55);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (3, 0, 0, 'Letter', 60);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (4, 0, 1, 'Cover', 1);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (5, 0, 1, 'Cover', 2);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (6, 0, 1, 'Cover', 3);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (7, 0, 2, 'LEP', 1);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (8, 0, 2, 'LEP', 2);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (9, 0, 2, 'LEP', 3);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (10, 0, 3, 'TOC', 1);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (11, 0, 3, 'TOC', 2);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (12, 2, 4, 'Introduction', 1);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (13, 2, 4, 'Introduction', 2);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (14, 2, 4, 'Introduction', 3);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_sub_secao, blc_numero, blc_nome, blc_codigo) VALUES (15, 3, 1, 3, 'Episodio 2', 14);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_sub_secao, blc_numero, blc_nome, blc_codigo) VALUES (16, 3, 1, 3, 'Episodio 2', 15);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (17, 4, 2, 'Episodio 3', 1);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (18, 4, 2, 'Episodio 3', 2);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (19, 4, 2, 'Episodio 3', 3);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_sub_secao, blc_numero, blc_nome, blc_codigo) VALUES (20, 5, 4, 8, 'Episodio 1', 12);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_sub_secao, blc_numero, blc_nome, blc_codigo) VALUES (21, 5, 6, 3, 'Episodio 4', 1);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_sub_secao, blc_numero, blc_nome, blc_codigo) VALUES (22, 5, 6, 3, 'Episodio 4', 2);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (23, 'AP01', 2, 'Appendix', 1);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (24, 'S03', 5, 'Mars', 1);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (25, 'S03', 10, 'Copyright', 1);
INSERT INTO MOM_BLOCO(blc_cod, blc_secao, blc_numero, blc_nome, blc_codigo) VALUES (26, 'S03', 10, 'Copyright', 2);

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
