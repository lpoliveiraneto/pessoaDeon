CREATE TABLE usuarios (
	id_usuario serial4 NOT NULL,
	login varchar(255) NOT NULL,
	senha varchar(255) NOT NULL,
	CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario)
);