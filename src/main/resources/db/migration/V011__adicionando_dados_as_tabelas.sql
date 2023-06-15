ALTER TABLE pessoa
    DROP COLUMN fk_endereco CASCADE;

ALTER TABLE pessoa
    ALTER COLUMN cpf TYPE varchar(20);