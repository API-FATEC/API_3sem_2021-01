--comment: Create table MOM_DOC
CREATE TABLE MOM_DOCUMENTO (
    DOC_COD NUMBER(10) PRIMARY KEY,
    DOC_DATA_CRIA DATE DEFAULT CURRENT_DATE,
    DOC_NOME VARCHAR(30) NOT NULL,
    DOC_PN INTEGER NOT NULL,

    UNIQUE (DOC_NOME, DOC_PN)
);
CREATE SEQUENCE MOM_DOCUMENTO_SQ;

--comment: Create table MOM_BLOCO
CREATE TABLE MOM_BLOCO (
    BLC_COD NUMBER(10) PRIMARY KEY,
    BLC_SECAO VARCHAR(10) NOT NULL,
    BLC_SUB_SECAO VARCHAR(10),
    BLC_NUMERO INTEGER NOT NULL,
    BLC_NOME VARCHAR(30),
    BLC_CODIGO INTEGER NOT NULL,
    BLC_ORDER integer,
    BLC_STATUS varchar(10),
    BLC_BASEPATH VARCHAR(256) NOT NULL,
    DOC_COD NUMBER(10) CONSTRAINT FK_MOM_BLOCO_DOC REFERENCES MOM_DOCUMENTO(DOC_COD)
);
CREATE SEQUENCE MOM_BLOCO_SQ;

CREATE TABLE MOM_REVISAO(
    REV_COD NUMBER(10) CONSTRAINT PK_MOM_REVISAO_COD PRIMARY KEY,
    REV_NAME VARCHAR(10) DEFAULT 'ORIGINAL',
    DOC_COD NUMBER(10) CONSTRAINT FK_MOM_REVISAO_DOC_COD REFERENCES MOM_DOCUMENTO(DOC_COD) NOT NULL,
    REV_STATUS VARCHAR(15) DEFAULT 'FINISHED',
    REV_CREATED_DATE DATE NOT NULL,
    REV_ULTIMA_ATUALIZACAO DATE
);
CREATE SEQUENCE MOM_REVISAO_SQ;

CREATE TABLE MOM_REVISAO_BLOCO(
    REV_COD NUMBER(10) CONSTRAINT FK_MOM_REV_BLC_REV REFERENCES MOM_REVISAO(REV_COD) NOT NULL,
    BLC_COD NUMBER(10) CONSTRAINT FK_MOM_REV_BLC_COD REFERENCES MOM_BLOCO(BLC_COD) NOT NULL
);

CREATE TABLE MOM.MOM_BLOCO_LINK(
    LNK_COD NUMBER(10) CONSTRAINT PK_MOM_BLOCO_LNK_COD PRIMARY KEY,
    LNK_FILENAME VARCHAR(100) NOT NULL,
    LNK_EXT VARCHAR(5),
    LNK_UPLOAD_DATE DATE NOT NULL,
    BLC_COD NUMBER(10) CONSTRAINT FK_MOM_BLOCO_LNK_BLC REFERENCES MOM_BLOCO(BLC_COD)
);
CREATE SEQUENCE MOM.MOM_BLOCO_LINK_SQ;

CREATE TABLE MOM.MOM_TAG_TIPO(
    TIP_COD NUMBER(10) CONSTRAINT PK_MOM_TAG_TIPO_COD PRIMARY KEY,
    TIP_NOM VARCHAR(30) NOT NULL
);
CREATE SEQUENCE MOM.MOM_TAG_TIPO_SQ;

CREATE TABLE MOM.MOM_TAG(
    TAG_COD NUMBER(10) CONSTRAINT PK_MOM_TAG_COD PRIMARY KEY,
    TAG_NOM VARCHAR(30) NOT NULL,
    TAG_CREATED DATE DEFAULT SYSDATE,
    TIP_COD NUMBER(10) CONSTRAINT FK_MOM_TAG_TYPE REFERENCES MOM_TAG_TIPO(TIP_COD)
);
CREATE SEQUENCE MOM.MOM_TAG_SQ;

CREATE TABLE MOM.MOM_TAG_DOC(
    TAG_COD NUMBER(10) CONSTRAINT FK_MOM_TAG_DOC_TAG REFERENCES MOM_TAG(TAG_COD),
    DOC_COD NUMBER(10) CONSTRAINT FK_MOM_TAG_DOC_DOC REFERENCES MOM_DOCUMENTO(DOC_COD)
);

CREATE TABLE MOM.MOM_TAG_BLC(
    TAG_COD NUMBER(10) CONSTRAINT FK_MOM_TAG_BLC_TAG REFERENCES MOM_TAG(TAG_COD),
    BLC_COD NUMBER(10) CONSTRAINT FK_MOM_TAG_BLC_BLC REFERENCES MOM_BLOCO(BLC_COD)
);

CREATE TABLE MOM.MOM_TRACO(
    TRA_COD NUMBER(10) CONSTRAINT PK_MOM_TRA_COD PRIMARY KEY,
    BLC_COD NUMBER(10) CONSTRAINT FK_MOM_TRA_BLC REFERENCES MOM_BLOCO(BLC_COD),
    DOC_COD NUMBER(10) CONSTRAINT FK_MOM_TRA_DOC REFERENCES MOM_DOCUMENTO(DOC_COD),
    TRA_NUM NUMBER(4) NOT NULL
);
CREATE SEQUENCE MOM.MOM_TRACO_SQ;