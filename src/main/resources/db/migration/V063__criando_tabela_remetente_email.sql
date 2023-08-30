CREATE TABLE public.remetente_email (
	id_remetente serial NOT NULL,
	email varchar(50) NULL,
	senha varchar(255) NULL,
	host varchar(20) NULL,
	porta varchar(10) NULL,
	data_cadastro timestamp(0) NULL,
	status bool NULL,
	CONSTRAINT remetente_email_pk PRIMARY KEY (id_remetente)
);