package br.com.ntc.banco.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import br.com.ntc.banco.factory.ConnectionFactory;
import br.com.ntc.model.AgendaAcompanhamento;
import br.com.ntc.banco.dao.AgendaDao;
import br.com.ntc.banco.exception.BadInfoException;
import br.com.ntc.banco.exception.IdNotFoundException;

public class AgendaService implements AutoCloseable {

    private Connection conexao;
    private AgendaDao agendaDao;

    public AgendaService() throws ClassNotFoundException, SQLException {
        conexao = ConnectionFactory.getConnection();
        agendaDao = new AgendaDao(conexao);
    }

    public void close() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }

    public void cadastrarAgenda(AgendaAcompanhamento agenda) throws ClassNotFoundException, SQLException, BadInfoException {
        validar(agenda);
        agendaDao.cadastrarAgenda(agenda);
    }

    private void validar(AgendaAcompanhamento agenda) throws BadInfoException {

        if (agenda.getMedico() == null || agenda.getMedico().length() > 50) {
            throw new BadInfoException("O nome do médico não deve ser nulo e deve ter no máximo 50 caracteres.");
        }

        if (agenda.getMedicamentos() == null || agenda.getMedicamentos().length() > 100) {
            throw new BadInfoException("A lista de medicamentos não deve ser nula e deve ter no máximo 100 caracteres.");
        }

        if (agenda.getIntervalo() <= 0 || agenda.getIntervalo() > 999) {
            throw new BadInfoException("O intervalo deve ser maior que zero e no máximo 999 horas.");
        }

        if (agenda.getDataInicio() == null || !validarDataInicio(new Date(agenda.getDataInicio().getTime()))) {
            throw new BadInfoException("A data de início não pode ser nula e deve ser posterior à data atual.");
        }

        if (agenda.getDataFim() == null || !validarDataFim(new Date(agenda.getDataFim().getTime()))) {
            throw new BadInfoException("A data de fim não pode ser nula e deve ser posterior à data atual.");
        }

        if (agenda.getIdPaciente() <= 0 || agenda.getIdPaciente() > 99999) {
            throw new BadInfoException("O ID do paciente deve ser maior que zero e no máximo 99999.");
        }
    }

    private boolean validarDataInicio(Date dataInicio) {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataInicioConvertida = dataInicio.toLocalDate();
        return !dataInicioConvertida.isBefore(dataAtual);
    }

    private boolean validarDataFim(Date dataFim) {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataFimConvertida = dataFim.toLocalDate();
        return !dataFimConvertida.isBefore(dataAtual);
    }


    public void atualizarAgenda(AgendaAcompanhamento agenda) throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
        validar(agenda);
        agendaDao.atualizarAgenda(agenda);
    }

    public void removerAgenda(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {
        agendaDao.removerAgenda(id);
    }

    public List<AgendaAcompanhamento> listarAgendas() throws ClassNotFoundException, SQLException {
        return agendaDao.listarAgendas();
    }

    public List<AgendaAcompanhamento> pesquisarPorMedico(String medico) throws SQLException {
        return agendaDao.pesquisaAgendaPorMedico(medico);
    }

    public AgendaAcompanhamento pesquisarPorId(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {
        return agendaDao.pesquisaAgendaPorId(id);
    }
}
