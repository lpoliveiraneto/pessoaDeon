CREATE TABLE telefone(
    id_telefone int4 NOT NULL AUTO_INCREMENT,
    telefone varchar (10) NOT NULL,
    atual boolean NULL,
    fk_pessoa bigint(20) NOT NULL,
    CONSTRAINT telefone_pkey PRIMARY KEY (id_telefone),
    FOREIGN KEY (fk_pessoa) REFERENCES pessoa(id_pessoa)
);

CREATE TABLE email(
    id_email int4 NOT NULL AUTO_INCREMENT,
    email varchar (10) NOT NULL,
    atual boolean NULL,
    fk_pessoa bigint(20) NOT NULL,
    CONSTRAINT email_pkey PRIMARY KEY (id_email),
    FOREIGN KEY (fk_pessoa) REFERENCES pessoa(id_pessoa)
);