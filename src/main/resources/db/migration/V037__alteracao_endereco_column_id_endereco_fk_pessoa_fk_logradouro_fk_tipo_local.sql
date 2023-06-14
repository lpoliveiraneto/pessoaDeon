ALTER TABLE public.endereco ADD fk_tipo_local integer NULL;

ALTER TABLE endereco
    ADD CONSTRAINT endereco_fk_tipo_local FOREIGN KEY (fk_tipo_local) REFERENCES tipoLocal(id_tipo_local);

ALTER TABLE endereco
    ALTER COLUMN referencia TYPE varchar(255) USING referencia::varchar;