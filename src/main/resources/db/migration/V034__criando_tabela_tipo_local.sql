CREATE TABLE tipoLocal(
	id_tipo_local serial NOT NULL,
	nome varchar(255) NOT NULL,
	valor varchar(255) NOT NULL,
	CONSTRAINT tipoLocal_pkey PRIMARY KEY (id_tipo_local)
);