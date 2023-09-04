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

INSERT INTO public.remetente_email (id_remetente,email,senha,host,porta,data_cadastro,status) VALUES
	 (1,'sigma@policiacivil.ma.gov.br','#Sig@ma*Pc&2022%','smtp.ma.gov.br','587','2023-08-21 17:23:25',true);