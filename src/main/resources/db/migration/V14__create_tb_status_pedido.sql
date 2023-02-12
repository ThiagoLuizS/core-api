CREATE SEQUENCE status_pedido_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

create table if not exists tb_status_pedido (
    id int8 not null,
    label varchar(50) not null,
    ordem varchar(50) not null,
    ativo bool default false,
    data_inclusao date not null,
    data_alteracao date,
    primary key (id)
);
