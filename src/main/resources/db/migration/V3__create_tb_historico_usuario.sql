CREATE SEQUENCE historico_usuario_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

create table if not exists tb_historico_usuario (
    id int8 not null,
    usuario_id int8 not null,
    atividade varchar(4096) not null,
    data_inclusao date not null,
    data_alteracao date,
    primary key (id),
    constraint fk_usuario_historio foreign key(usuario_id) references tb_usuario(id)
);
