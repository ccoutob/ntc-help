package br.com.ntc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class AgendaAcompanhamento {

    @JsonProperty("cd_agenda")
    @SerializedName("cd_agenda")
    private int id;

    @JsonProperty("nm_medico")
    @SerializedName("nm_medico")
    private String medico;

    @JsonProperty("ds_medicamentos")
    @SerializedName("ds_medicamentos")
    private String medicamentos;

    @JsonProperty("nr_intervalo")
    @SerializedName("nr_intervalo")
    private int intervalo;

    @JsonProperty("dt_inicio")
    @SerializedName("dt_inicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, yyyy, hh:mm:ss a", locale = "pt-BR")
    private Date dataInicio;

    @JsonProperty("dt_fim")
    @SerializedName("dt_fim")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, yyyy, hh:mm:ss a", locale = "pt-BR")
    private Date dataFim;

    @JsonProperty("cd_paciente")
    @SerializedName("cd_paciente")
    private int idPaciente;
	
	public AgendaAcompanhamento(){
		
	}
	
	public AgendaAcompanhamento(int id, String medico, String medicamentos, int intervalo, Date dataInicio, Date dataFim, int idPaciente) {
		this.id = id;
		this.medico = medico;
		this.medicamentos = medicamentos;
		this.intervalo = intervalo;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.idPaciente = idPaciente;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMedico() {
		return medico;
	}
	public void setMedico(String medico) {
		this.medico = medico;
	}
	public String getMedicamentos() {
		return medicamentos;
	}
	public void setMedicamentos(String medicamentos) {
		this.medicamentos = medicamentos;
	}
	public int getIntervalo() {
		return intervalo;
	}
	public void setIntervalo(int intervalo) {
		this.intervalo = intervalo;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	
	@Override
    public String toString() {
        return "Agenda { " +
                "ID: " + id + " | Medico: " + medico + " | Medicamentos: " + medicamentos + " | Intervalo: " + intervalo + " | Inicio: " + dataInicio + " | Fim: " + dataFim + " | Paciente: " + idPaciente + " } ";
    }
	
}