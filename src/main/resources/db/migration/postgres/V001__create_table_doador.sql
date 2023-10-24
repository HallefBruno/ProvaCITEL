CREATE TABLE IF NOT EXISTS doador
(
    id serial NOT NULL PRIMARY KEY, 
    nome character varying(255),
    cpf character varying(13),
    rg character varying(15),
    data_nasc date,
    sexo character varying(11),
    mae character varying(255),
    pai character varying(255),
    email character varying(255),
    cep character varying(12),
    endereco character varying(255),
    numero integer,
    bairro character varying(255),
    cidade character varying(255),
    estado character varying(2),
    telefone_fixo character varying(14),
    celular character varying(14),
    altura character varying(5),
    peso character varying(3),
    tipo_sanguineo character varying(5)
);
