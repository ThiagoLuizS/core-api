CREATE SEQUENCE projeto_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

create table if not exists tb_projeto (
    id int8 not null,
    unidade_id int8 not null,
    nome_projeto varchar(50) not null ,
    numero_projeto varchar(50) not null,
    descricao_projeto_resumido varchar(50) not null,
    descricao_especificacoes varchar(50),
    descricao_projeto varchar(50),
    mne_responsavel varchar(50),
    ativo bool default false,
    mne_usuario varchar(128) not null,
    data_inclusao date not null,
    data_alteracao date,
    primary key (id),
    constraint fk_projeto_unidade foreign key(unidade_id) references tb_unidade(id)
);
