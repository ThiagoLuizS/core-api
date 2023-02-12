CREATE SEQUENCE tipo_produto_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

create table if not exists tb_tipo_produto (
    id int8 not null,
    des_tipo_produto varchar(50) not null,
    mne_usuario varchar(128) not null,
    data_inclusao date not null,
    data_alteracao date,
    primary key (id)
);
