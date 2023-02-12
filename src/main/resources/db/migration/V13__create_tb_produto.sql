CREATE SEQUENCE produto_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

create table if not exists tb_produto (
    id int8 not null,
    tipo_produto_id int8 not null,
    nome_produto varchar(50) not null ,
    descricao_comprimento varchar(50) not null,
    descricao_largura varchar(50) not null,
    descricao_altura varchar(50),
    descricao_capacidade varchar(50),
    descricao_peso varchar(50),
    mne_usuario varchar(128) not null,
    data_inclusao date not null,
    data_alteracao date,
    primary key (id),
    constraint fk_produto_tipo_produto foreign key(tipo_produto_id) references tb_tipo_produto(id)
);
