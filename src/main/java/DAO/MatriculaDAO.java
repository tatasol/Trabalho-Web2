package DAO;

import model.Matricula;
import connection.Conexao;

import java.sql.*;

public class MatriculaDAO {

    public void matricularAluno(Matricula matricula) throws ClassNotFoundException {
        String sql = "INSERT INTO matricula (aluno_id, disciplina_id, periodo_id, resultado) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, matricula.getAluno().getId());
            ps.setLong(2, matricula.getDisciplina().getId());
            ps.setLong(3, matricula.getPeriodo().getId());
            ps.setString(4, matricula.getResultado());

            ps.executeUpdate();
            System.out.println("Aluno matriculado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao matricular aluno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Matricula buscarMatriculaPorId(Long id) throws ClassNotFoundException {
        String sql = "SELECT * FROM matricula WHERE id = ?";
        Matricula matricula = null;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                matricula = new Matricula();
                matricula.setId(rs.getLong("id"));
                // Preencher os dados de aluno, disciplina, período e resultado
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar matrícula: " + e.getMessage());
            e.printStackTrace();
        }
        return matricula;
    }
}