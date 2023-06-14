
ALTER TABLE logradouro DROP COLUMN gia;
ALTER TABLE logradouro DROP COLUMN ddd;
ALTER TABLE logradouro DROP COLUMN siafi;

ALTER TABLE logradouro RENAME COLUMN bairro TO fk_bairro;
ALTER TABLE logradouro ALTER COLUMN fk_bairro TYPE int4 USING fk_bairro::int4;;

ALTER TABLE logradouro RENAME COLUMN localidade TO fk_cidade;
ALTER TABLE logradouro ALTER COLUMN fk_cidade TYPE int4 USING fk_cidade::int4;;

ALTER TABLE logradouro RENAME COLUMN uf TO fk_estado;
ALTER TABLE logradouro ALTER COLUMN fk_estado TYPE int4 USING fk_estado::int4;;

ALTER TABLE logradouro ADD CONSTRAINT logradouro_fk_bairro FOREIGN KEY (fk_bairro) REFERENCES bairro(id_bairro);
ALTER TABLE logradouro ADD CONSTRAINT logradouro_fk_cidade FOREIGN KEY (fk_cidade) REFERENCES cidade(id_cidade);
ALTER TABLE logradouro ADD CONSTRAINT logradouro_fk_estado FOREIGN KEY (fk_estado) REFERENCES estado(id_estado);