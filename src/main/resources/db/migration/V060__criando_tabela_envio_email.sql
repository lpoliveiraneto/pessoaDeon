CREATE TABLE public.envio_email (
	id serial NOT NULL,
	data_registro timestamp(0) NOT NULL,
	destinatario varchar NOT NULL,
	status varchar NOT NULL
);
ALTER TABLE public.envio_email ADD CONSTRAINT envio_email_pk PRIMARY KEY (id)