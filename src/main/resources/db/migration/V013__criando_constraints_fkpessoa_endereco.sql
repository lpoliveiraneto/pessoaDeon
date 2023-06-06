ALTER TABLE endereco ADD CONSTRAINT endereco_fk FOREIGN KEY (fk_pessoa) REFERENCES public.pessoa(id_pessoa);
