CREATE SEQUENCE pessoa_juridica_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

create table if not exists tb_pessoa_juridica (
    id int8 not null,
    cnpj varchar(24) not null,
    nome_social varchar(512) not null,
    cep varchar(14),
    desc_endereco varchar(512) not null,
    numero varchar(10) not null,
    nome_bairro varchar(512) not null ,
    complemento varchar(255),
    cidade varchar(128) not null ,
    estado varchar(50) not null,
    ddd int4,
    numero_telefone int8,
    email varchar(128) not null,
    email_financeiro varchar(128),
    email_adicional varchar(128),
    email_fatura varchar(128),
    data_inclusao date not null,
    data_alteracao date,
    primary key (id)
);
