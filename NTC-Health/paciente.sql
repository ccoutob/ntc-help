CREATE TABLE tb_paciente (
    cd_paciente INT NOT NULL,
    nm_paciente VARCHAR(255 )NOT NULL,
    nr_idade INT NOT NULL,
    ds_cpf VARCHAR(14) NOT NULL, 
    ds_email VARCHAR(255) NOT NULL,
    ds_telefone VARCHAR(20) NOT NULL,
    PRIMARY KEY (cd_paciente)
); 