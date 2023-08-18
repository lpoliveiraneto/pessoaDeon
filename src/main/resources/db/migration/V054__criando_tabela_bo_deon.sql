CREATE TABLE bo_deon (
	id_bo serial4 NOT NULL,
	data_fato DATE NOT NULL,
	hora_fato TIME NOT NULL,
    data_registro TIMESTAMP NOT NULL,
    protocolo varchar(16) NOT NULL,
    relato varchar,
    relato_editado varchar,
    CONSTRAINT bo_deon_pkey PRIMARY KEY (id_bo)
);
