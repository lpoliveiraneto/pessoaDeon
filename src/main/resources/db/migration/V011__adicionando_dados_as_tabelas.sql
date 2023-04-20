ALTER TABLE pessoa
    DROP COLUMN fk_endereco CASCADE;

ALTER TABLE pessoa
    ALTER COLUMN cpf TYPE varchar(20);


INSERT INTO
    pessoa (nome, nome_mae, nome_pai, alcunha, cpf, data_nascimento, sexo, nome_social,
        orientacao_sexual, identidade_genero, deficiencia, cor_pele, fk_pais, fk_estado, fk_cidade)
        VALUES('Leonides', 'Luiza', 'Oliveira', 'Neto', '033.787.043-81', '1989-11-01', 'M', 'Neto', 'HE', 'H', 'NONE',
            'PR', 1, 10, 2587);

INSERT INTO
    pessoa (nome, nome_mae, nome_pai, alcunha, cpf, data_nascimento, sexo, nome_social,
        orientacao_sexual, identidade_genero, deficiencia, cor_pele, fk_pais, fk_estado, fk_cidade)
        VALUES('Jefferson', 'Leticia', 'Paulo', 'Jefferson da caixa', '035.311.777-42', '1993-09-01', 'M', 'Jefferson',
            'HE', 'H', 'NONE', 'BR', 1, 10, 2587);


INSERT INTO
    pessoa (nome, nome_mae, nome_pai, alcunha, cpf, data_nascimento, sexo, nome_social,
        orientacao_sexual, identidade_genero, deficiencia, cor_pele, fk_pais, fk_estado, fk_cidade)
        VALUES('Israel', 'Cleitiane', 'Fábio', 'Israel', '072.987.683-79', '1999-07-15', 'M', 'Israel',
            'HE', 'H', 'NONE', 'BR', 1, 10, 2587);


INSERT INTO logradouro (cep, logradouro, bairro, complemento, localidade, uf, ibge, gia, ddd, siafi)
        VALUES('65058-274', 'Avenida 05', 'Jardim América', '', 'São Luís', 'MA', '2111300', '', '98', '0921');


INSERT INTO logradouro (cep, logradouro, bairro, complemento, localidade, uf, ibge, gia, ddd, siafi)
        VALUES('65058-274', 'Avenida 05', 'Jardim América', '', 'São Luís', 'MA', '2111300', '', '98', '0921');

INSERT INTO logradouro (cep, logradouro, bairro, complemento, localidade, uf, ibge, gia, ddd, siafi)
        VALUES('01215-010', 'Rua Helvétia', 'Campos Elíseos', '', 'São Paulo', 'SP', '3550308', '1004', '11', '7107');

INSERT INTO logradouro (cep, logradouro, bairro, complemento, localidade, uf, ibge, gia, ddd, siafi)
        VALUES('65045-380', 'Avenida Edson Brandão', 'Cutim Anil', '', 'São Luís', 'MA', '2111300', '', '98', '0921');
