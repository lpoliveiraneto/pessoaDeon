CREATE TABLE telefone (
    id_telefone serial4 NOT NULL,
    telefone varchar (10) NOT NULL,
    atual boolean NULL,
    fk_pessoa int4 NOT NULL,
    CONSTRAINT telefone_pkey PRIMARY KEY (id_telefone)
);

CREATE TABLE email (
    id_email serial4 NOT NULL,
    email varchar (10) NOT NULL,
    atual boolean NULL,
    fk_pessoa int4 NOT NULL,
    CONSTRAINT email_pkey PRIMARY KEY (id_email)
);