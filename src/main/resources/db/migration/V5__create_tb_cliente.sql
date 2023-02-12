CREATE SEQUENCE cliente_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

create table if not exists tb_cliente (
    id int8 not null,
    cnpj varchar(128) not null,
    nome varchar(128) not null,
    apelido varchar(128),
    descricao_endereco varchar(512) not null,
    numero_endereco varchar(50) not null,
    descricao_complemento varchar(512),
    nome_bairro varchar(200),
    nome_cidade varchar(200),
    codigo_uf varchar(50),
    numCep int4,
    ddd_telefone int4,
    numero_telefone int8,
    ddd_celular int4,
    numero_celular int8,
    fax_cliente varchar(128),
    nome_contato varchar(128),
    ddd_telefone_contato int4,
    numero_telefone_contato int8,
    ddd_celular_contato int4,
    numero_celular_contato int8,
    email_contato varchar(128),
    ativo bool default false,
    mne_usuario varchar(128) not null,
    data_inclusao date not null,
    data_alteracao date,
    primary key (id)
);
