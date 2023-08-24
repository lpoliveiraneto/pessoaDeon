CREATE TABLE bo_deon (
	id_bo serial4 NOT NULL,
	data_fato DATE NOT NULL,
	hora_fato TIME NOT NULL,
    data_registro TIMESTAMP NOT NULL,
    relato varchar NOT NULL,
    relato_editado varchar,
    CONSTRAINT bo_deon_pkey PRIMARY KEY (id_bo)
);
