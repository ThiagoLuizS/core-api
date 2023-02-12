CREATE SEQUENCE perfil_usuario_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

create table if not exists tb_perfil_usuario (
    id int8 not null,
    nome varchar(128) not null,
    descricao varchar(512),
    mascara_permissao_base64 text not null,
    data_inclusao date not null,
    data_alteracao date,
    primary key (id)
);
