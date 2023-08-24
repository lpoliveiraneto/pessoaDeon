CREATE TABLE protocolo(
	id_protocolo serial4 NOT NULL,
	numero varchar NOT NULL,
	data_registro DATE NOT NULL,
	fk_bo int4 NOT NULL,
	CONSTRAINT protocolo_pkey PRIMARY KEY(id_protocolo),
	FOREIGN KEY (fk_bo) REFERENCES bo_deon(id_bo)
);