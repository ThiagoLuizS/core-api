CREATE SEQUENCE unidade_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

create table if not exists tb_unidade (
    id int8 not null,
    cliente_id int8 not null,
    desc_unidade_resumida varchar(512),
    nome_unidade varchar(128) not null,
    mne_responsavel varchar(50),
    ativo bool default false,
    mne_usuario varchar(128) not null,
    data_inclusao date not null,
    data_alteracao date,
    primary key (id),
    constraint fk_cliente_unidade foreign key(cliente_id) references tb_cliente(id)
);
