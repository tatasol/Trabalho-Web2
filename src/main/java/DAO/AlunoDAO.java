package DAO;

import model.Aluno;
import connection.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public void cadastrarAluno(Aluno aluno) throws ClassNotFoundException {
        String sql = "INSERT INTO aluno (nome) VALUES (?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, aluno.getNome());
            ps.executeUpdate();
            System.out.println("Aluno cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Aluno buscarAlunoPorId(Long id) throws ClassNotFoundException {
        String sql = "SELECT * FROM aluno WHERE id = ?";
        Aluno aluno = null;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                aluno = new Aluno();
                aluno.setId(rs.getLong("id"));
                aluno.setNome(rs.getString("nome"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar aluno: " + e.getMessage());
            e.printStackTrace();
        }
        return aluno;
    }

    public List<Aluno> listarAlunos() throws ClassNotFoundException {
        String sql = "SELECT * FROM aluno";
        List<Aluno> alunos = new ArrayList<>();

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getLong("id"));
                aluno.setNome(rs.getString("nome"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar alunos: " + e.getMessage());
            e.printStackTrace();
        }
        return alunos;
    }
}