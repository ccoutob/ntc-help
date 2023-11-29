package br.com.ntc.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ntc.model.Paciente;

public class PacienteDao {

    private Connection conexao;

    public PacienteDao() {

    }

    public PacienteDao(Connection conexao) {
        this.conexao = conexao;
    }

    public void cadastrarPaciente(Paciente paciente) throws SQLException {
        String sql = "INSERT INTO tb_paciente "
                + "(cd_paciente, nm_paciente, nr_idade, ds_cpf, ds_email, ds_telefone) "
                + "values (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setInt(1, paciente.getId());
            stm.setString(2, paciente.getNome());
            stm.setInt(3, paciente.getIdade());
            stm.setString(4, paciente.getCpf());
            stm.setString(5, paciente.getEmail());
            stm.setString(6, paciente.getTelefone());

            stm.executeUpdate();
        }
    }

    private Paciente parse(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("cd_paciente");
        String nome = resultado.getString("nm_paciente");
        int idade = resultado.getInt("nr_idade");
        String cpf = resultado.getString("ds_cpf");
        String email = resultado.getString("ds_email");
        String telefone = resultado.getString("ds_telefone");

        return new Paciente(id, nome, idade, cpf, email, telefone);
    }

    public List<Paciente> listarPacientes() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_paciente");
        ResultSet resultado = stm.executeQuery();

        List<Paciente> lista = new ArrayList<>();

        while (resultado.next()) {
            Paciente paciente = parse(resultado);
            lista.add(paciente);
        }

        return lista;
    }

    public Paciente pesquisaPacientePorId(int id) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_paciente WHERE cd_paciente = ?");
        stm.setInt(1, id);
        ResultSet resultado = stm.executeQuery();

        if (!resultado.next()) {
            // Trate aqui o caso em que o paciente não é encontrado
            return null;
        }

        return parse(resultado);
    }
    
    public List<Paciente> pesquisarPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM tb_paciente WHERE nm_paciente LIKE ?";
        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setString(1, "%" + nome + "%");
            ResultSet resultado = stm.executeQuery();
            List<Paciente> lista = new ArrayList<>();
            while (resultado.next()) {
                Paciente paciente = parse(resultado);
                lista.add(paciente);
            }
            return lista;
        }
    }

    public void atualizarPaciente(Paciente paciente) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement(
                "UPDATE tb_paciente SET nm_paciente = ?, nr_idade = ?, ds_cpf = ?, ds_email = ?, ds_telefone = ? WHERE cd_paciente = ?");

        stm.setString(1, paciente.getNome());
        stm.setInt(2, paciente.getIdade());
        stm.setString(3, paciente.getCpf());
        stm.setString(4, paciente.getEmail());
        stm.setString(5, paciente.getTelefone());
        stm.setInt(6, paciente.getId());

        stm.executeUpdate();
    }

    public void removerPaciente(int id) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("DELETE FROM tb_paciente WHERE cd_paciente = ?");
        stm.setInt(1, id);

        stm.executeUpdate();
    }
}
