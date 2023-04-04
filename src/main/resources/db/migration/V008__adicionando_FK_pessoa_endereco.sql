ALTER TABLE endereco
   ADD FOREIGN KEY (fk_pessoa) REFERENCES pessoa(id_pessoa);
