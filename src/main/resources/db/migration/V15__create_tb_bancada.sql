CREATE SEQUENCE bancada_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

create table if not exists tb_bancada (
    id int8 not null,
    local_id int8 not null,
    descricao_bancada varchar(128) not null ,
    total_posicoes varchar(128) not null,
    ativo bool default false,
    mne_usuario varchar(128) not null,
    data_inclusao date not null,
    data_alteracao date,
    primary key (id),
    constraint fk_bancada_local foreign key(local_id) references tb_local(id)
);
