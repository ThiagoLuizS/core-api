CREATE SEQUENCE tentativa_acesso_usuario_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

create table if not exists tb_tentativa_acesso_usuario (
    id int8 not null,
    usuario_id int8 not null,
    ip varchar(128) not null,
    agente_usuario varchar(512),
    motivo varchar(512),
    data_inclusao date not null,
    data_alteracao date,
    primary key (id),
    constraint fk_usuario_tentativa_acesso_usuario foreign key(usuario_id) references tb_usuario(id)
);
