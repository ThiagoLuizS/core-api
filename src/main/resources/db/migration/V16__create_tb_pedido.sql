CREATE SEQUENCE pedido_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

create table if not exists tb_pedido (
    id int8 not null,
    cliente_id int8 not null,
    produto_id int8 not null,
    status_pedido_id int8 not null,
    numero_pedido int8 not null,
    numero_item int8 not null,
    quantidade_produto int8 not null,
    mne_usuario varchar(128) not null,
    data_inclusao date not null,
    data_alteracao date,
    primary key (id),
    constraint fk_pedido_cliente foreign key(cliente_id) references tb_cliente(id),
    constraint fk_pedido_produto foreign key(produto_id) references tb_produto(id),
    constraint fk_pedido_status_pedido foreign key(status_pedido_id) references tb_status_pedido(id)
);
