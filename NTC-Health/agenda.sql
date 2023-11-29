CREATE TABLE tb_agenda (
    cd_agenda INT NOT NULL,
    nm_medico VARCHAR2(50) NOT NULL,
    ds_medicamentos VARCHAR2(100) NOT NULL,
    nr_intervalo INT NOT NULL,
    dt_inicio DATE NOT NULL,
    dt_fim DATE NOT NULL,
    cd_paciente INT NOT NULL,
    PRIMARY KEY (cd_agenda)
);