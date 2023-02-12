CREATE SEQUENCE tipo_local_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

create table if not exists tb_tipo_local (
    id int8 not null,
    des_tipo_local varchar(512) not null,
    mne_usuario varchar(128),
    data_inclusao date not null,
    data_alteracao date,
    primary key (id)
);
