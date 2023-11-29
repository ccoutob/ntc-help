package br.com.ntc.banco.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ntc.banco.exception.IdNotFoundException;
import br.com.ntc.model.AgendaAcompanhamento;

public class AgendaDao {

	private Connection conexao;
	
	public AgendaDao() {
		
	}
	
	public AgendaDao(Connection conexao) {
		this.conexao = conexao;
	}
	
	public void cadastrarAgenda(AgendaAcompanhamento agenda) throws SQLException {
        String sql = "INSERT INTO tb_agenda "
                + "(cd_agenda, nm_medico, ds_medicamentos, nr_intervalo, dt_inicio, dt_fim, cd_paciente) "
                + "values (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setInt(1, agenda.getId());
            stm.setString(2, agenda.getMedico());
            stm.setString(3, agenda.getMedicamentos());
            stm.setInt(4, agenda.getIntervalo());
            stm.setDate(5, new java.sql.Date(agenda.getDataInicio().getTime()));
            stm.setDate(6, new java.sql.Date(agenda.getDataFim().getTime()));
            stm.setInt(7, agenda.getIdPaciente());

            stm.executeUpdate();
        }
    }
	
	private AgendaAcompanhamento parse(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("cd_agenda");
        String medico = resultado.getString("nm_medico");
        String medicamentos = resultado.getString("ds_medicamentos");
        int intervalo = resultado.getInt("nr_intervalo");
        Date dataInicio = resultado.getDate("dt_inicio");
        Date dataFim = resultado.getDate("dt_fim");
        int idPaciente = resultado.getInt("cd_paciente");

        return new AgendaAcompanhamento(id, medico, medicamentos, intervalo, dataInicio, dataFim, idPaciente);
    }
	
	public List<AgendaAcompanhamento> listarAgendas() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_agenda");
        ResultSet resultado = stm.executeQuery();

        List<AgendaAcompanhamento> lista = new ArrayList<>();

        while (resultado.next()) {
            AgendaAcompanhamento agenda = parse(resultado);
            lista.add(agenda);
        }

        return lista;
    }
	
	public AgendaAcompanhamento pesquisaAgendaPorId(int id) throws SQLException, IdNotFoundException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_agenda WHERE cd_agenda = ?");
        stm.setInt(1, id);
        ResultSet resultado = stm.executeQuery();

        if (!resultado.next()) {
            throw new IdNotFoundException("Agenda não encontrada!");
        }

        return parse(resultado);
    }
	
	public List<AgendaAcompanhamento> pesquisaAgendaPorMedico(String medico) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_agenda WHERE nm_medico LIKE ?");
        stm.setString(1, "%" + medico + "%");
        ResultSet resultado = stm.executeQuery();

        List<AgendaAcompanhamento> lista = new ArrayList<>();

        while (resultado.next()) {
            AgendaAcompanhamento agenda = parse(resultado);
            lista.add(agenda);
        }

        return lista;
    }
	
	public void atualizarAgenda(AgendaAcompanhamento agenda) throws SQLException, IdNotFoundException {
        PreparedStatement stm = conexao.prepareStatement(
                "UPDATE tb_agenda SET nm_medico = ?, ds_medicamentos = ?, nr_intervalo = ?, dt_inicio = ?, dt_fim = ?, cd_paciente = ? WHERE cd_agenda = ?");

        stm.setString(1, agenda.getMedico());
        stm.setString(2, agenda.getMedicamentos());
        stm.setInt(3, agenda.getIntervalo());
        stm.setDate(4, new java.sql.Date(agenda.getDataInicio().getTime()));
        stm.setDate(5, new java.sql.Date(agenda.getDataFim().getTime()));
        stm.setInt(6, agenda.getIdPaciente());
        stm.setInt(7, agenda.getId());

        int linha = stm.executeUpdate();
        if (linha == 0)
            throw new IdNotFoundException("Agenda não encontrada para atualização");
    }
	
	public void removerAgenda(int id) throws SQLException, IdNotFoundException {
        PreparedStatement stm = conexao.prepareStatement("DELETE FROM tb_agenda WHERE cd_agenda = ?");
        stm.setInt(1, id);

        int linha = stm.executeUpdate();

        if (linha == 0)
            throw new IdNotFoundException("Agenda não encontrada para remoção");
    }
}

