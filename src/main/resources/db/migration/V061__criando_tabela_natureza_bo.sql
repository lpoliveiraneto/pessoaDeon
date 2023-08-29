CREATE TABLE natureza_bo(
	id serial4 NOT NULL,
	fk_bo int4 NOT NULL,
	fk_natureza_deon int4 NOT NULL,
	CONSTRAINT natureza_bo_pkey PRIMARY KEY(id),
	FOREIGN KEY (fk_bo) REFERENCES bo_deon(id_bo),
	FOREIGN KEY (fk_natureza_deon) REFERENCES natureza_deon(id_natureza)
);