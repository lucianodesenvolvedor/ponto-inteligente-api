create sequence sequence_empresa increment 1 minvalue 1 maxvalue 9223372036854775807 start 1 cache 1;
create sequence sequence_funcionario increment 1 minvalue 1 maxvalue 9223372036854775807 start 1 cache 1;
create sequence sequence_lancamento increment 1 minvalue 1 maxvalue 9223372036854775807 start 1 cache 1;

create table if not exists empresa (
  	id bigint primary key default nextval('sequence_empresa'::regclass),
  	cnpj varchar(255) not null,
  	data_atualizacao timestamp not null,
  	data_criacao timestamp not null,
  	razao_social varchar(255) not null
);

create table if not exists funcionario (
  	id bigint primary key default nextval('sequence_funcionario'::regclass),
	empresa_id bigint references empresa(id) not null,
  	cpf varchar(255) not null,
  	data_atualizacao timestamp not null,
  	data_criacao timestamp not null,
  	email varchar(255) not null,
  	nome varchar(255) not null,
  	perfil varchar(255) not null,
  	qtd_horas_almoco float not null,
  	qtd_horas_trabalho_dia float not null,
  	senha varchar(255) not null,
  	valor_hora decimal(19,2) not null 
);

create table if not exists lancamento (
  	id bigint primary key default nextval('sequence_lancamento'::regclass),
	funcionario_id bigint references funcionario(id),
  	data timestamp not null,
  	data_atualizacao timestamp not null,
  	data_criacao timestamp not null,
  	descricao varchar(255) not null,
  	localizacao varchar(255) not null,
  	tipo varchar(255) not null  
);