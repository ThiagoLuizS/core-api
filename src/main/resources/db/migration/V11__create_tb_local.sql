CREATE SEQUENCE local_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

create table if not exists tb_local (
    id int8 not null,
    projeto_id int8 not null,
    tipo_local_id int8 not null,
    tipo_administracao_id int8 not null,
    mne_local varchar(50) not null ,
    mne_local_resumido varchar(50) not null,
    des_local varchar(50) not null,
    des_especificacoes_local varchar(50),
    possui_bancadas bool default false,
    armazenagem bool default false,
    ativo bool default false,
    mne_responsavel varchar(128),
    mne_usuario varchar(128) not null,
    data_inclusao date not null,
    data_alteracao date,
    primary key (id),
    constraint fk_local_projeto foreign key(projeto_id) references tb_projeto(id),
    constraint fk_local_tipo_local foreign key(tipo_local_id) references tb_tipo_local(id),
    constraint fk_local_tipo_administracao foreign key(tipo_administracao_id) references tb_tipo_administracao(id)
);
