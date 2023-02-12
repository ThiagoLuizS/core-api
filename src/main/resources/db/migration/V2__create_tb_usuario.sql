CREATE SEQUENCE usuario_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

create table if not exists tb_usuario (
    id int8 not null,
    perfil_usuario_id int8 not null,
    username varchar(128) not null,
    mascara_permissao_base64 text not null,
    nome varchar(128) not null,
    email varchar(128) not null,
    hash_senha text not null,
    ativo bool default false,
    data_senha_alterada date,
    token_senha_redefinida text,
    bloqueado bool default false,
    senha_expirada bool default false,
    data_inclusao date not null,
    data_alteracao date,
    primary key (id),
    constraint fk_perfil_usuario foreign key(perfil_usuario_id) references tb_perfil_usuario(id)
);
