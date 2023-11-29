package br.com.ntc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class Paciente {
	@JsonProperty("cd_paciente")
    @SerializedName("cd_paciente")
	private int id;
	@JsonProperty("nm_paciente")
    @SerializedName("nm_paciente")
	private String nome;
	@JsonProperty("nr_idade")
    @SerializedName("nr_idade")
	private int idade;
	@JsonProperty("ds_cpf")
    @SerializedName("ds_cpf")
	private String cpf;
	@JsonProperty("ds_email")
    @SerializedName("ds_email")
	private String email;
	@JsonProperty("ds_telefone")
    @SerializedName("ds_telefone")
	private String telefone;
	
	public Paciente() {
		
	}

	public Paciente(int id, String nome, int idade, String cpf, String email, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Override
    public String toString() {
        return "Paciente { " +
                "ID: " + id + " | Nome: " + nome + " | Idade: " + idade + " | Cpf: " + cpf + " | Email: " + email + " | Telefone: " + telefone + " } ";
    }
	
	
}