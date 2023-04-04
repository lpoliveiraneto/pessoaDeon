ALTER TABLE telefone
   ADD FOREIGN KEY (fk_pessoa) REFERENCES pessoa(id_pessoa);

ALTER TABLE email
    ADD FOREIGN KEY (fk_pessoa) REFERENCES pessoa(id_pessoa);