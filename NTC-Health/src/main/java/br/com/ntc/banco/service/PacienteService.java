package br.com.ntc.banco.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.ntc.banco.factory.ConnectionFactory;
import br.com.ntc.model.Paciente;
import br.com.ntc.banco.dao.PacienteDao;
import br.com.ntc.banco.exception.BadInfoException;
import br.com.ntc.banco.exception.IdNotFoundException;

public class PacienteService implements AutoCloseable {

    private Connection conexao;
    private PacienteDao pacienteDao;

    public PacienteService() throws ClassNotFoundException, SQLException {
        conexao = ConnectionFactory.getConnection();
        pacienteDao = new PacienteDao(conexao);
    }

    public void close() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }

    public void cadastrarPaciente(Paciente paciente) throws ClassNotFoundException, SQLException, BadInfoException {
        validar(paciente);
        pacienteDao.cadastrarPaciente(paciente);
    }

    private void validar(Paciente paciente) throws BadInfoException {
        if (paciente.getNome() == null || paciente.getNome().length() > 50) {
            throw new BadInfoException("O nome do paciente não deve ser nulo e deve ter no máximo 50 caracteres.");
        }

        if (paciente.getIdade() <= 0 || paciente.getIdade() > 999) {
            throw new BadInfoException("A idade do paciente deve ser maior que zero e no máximo 999 anos.");
        }

        if (paciente.getCpf() == null || paciente.getCpf().length() > 14) {
            throw new BadInfoException("O CPF do paciente não deve ser nulo e deve ter no máximo 14 caracteres.");
        }

        if (paciente.getEmail() == null || paciente.getEmail().length() > 100) {
            throw new BadInfoException("O e-mail do paciente não deve ser nulo e deve ter no máximo 100 caracteres.");
        }

        if (paciente.getTelefone() == null || paciente.getTelefone().length() > 15) {
            throw new BadInfoException("O telefone do paciente não deve ser nulo e deve ter no máximo 15 caracteres.");
        }
    }


    public void atualizarPaciente(Paciente paciente) throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
        validar(paciente);
        pacienteDao.atualizarPaciente(paciente);
    }

    public void removerPaciente(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {
        pacienteDao.removerPaciente(id);
    }

    public List<Paciente> listarPacientes() throws ClassNotFoundException, SQLException {
        return pacienteDao.listarPacientes();
    }

    public Paciente pesquisarPorId(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {
        return pacienteDao.pesquisaPacientePorId(id);
    }
    
    public List<Paciente> pesquisarPorNome(String nome) throws SQLException {
        return pacienteDao.pesquisarPorNome(nome);
    }
}

