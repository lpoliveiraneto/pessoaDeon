CREATE TABLE listas.tipo_documento (
	id_tipo_documento serial4 NOT NULL,
	descricao varchar NOT NULL,
	valor varchar NOT NULL,
	CONSTRAINT tipo_documento_pk PRIMARY KEY (id_tipo_documento)
);