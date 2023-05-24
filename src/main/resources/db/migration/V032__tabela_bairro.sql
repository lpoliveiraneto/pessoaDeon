CREATE TABLE bairro (
	id_bairro serial4 NOT NULL,
	id_bairro_api serial4 NOT NULL,
	descricao varchar(255) NULL,
	fk_cidade serial4 NOT NULL,
	CONSTRAINT bairro_pkey PRIMARY KEY (id_bairro)
);

ALTER TABLE bairro ADD CONSTRAINT barri_fk_cidade_fkey FOREIGN KEY (fk_cidade) REFERENCES cidade(id_cidade);