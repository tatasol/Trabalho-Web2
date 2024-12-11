package DAO;

import model.Disciplina;
import connection.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {

    public void cadastrarDisciplina(Disciplina disciplina) throws ClassNotFoundException {
        String sql = "INSERT INTO disciplina (nome) VALUES (?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, disciplina.getNome());
            ps.executeUpdate();
            System.out.println("Disciplina cadastrada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar disciplina: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Disciplina buscarDisciplinaPorId(Long id) throws ClassNotFoundException {
        String sql = "SELECT * FROM disciplina WHERE id = ?";
        Disciplina disciplina = null;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                disciplina = new Disciplina();
                disciplina.setId(rs.getLong("id"));
                disciplina.setNome(rs.getString("nome"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar disciplina: " + e.getMessage());
            e.printStackTrace();
        }
        return disciplina;
    }

    public List<Disciplina> listarDisciplinas() throws ClassNotFoundException {
        String sql = "SELECT * FROM disciplina";
        List<Disciplina> disciplinas = new ArrayList<>();

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setId(rs.getLong("id"));
                disciplina.setNome(rs.getString("nome"));
                disciplinas.add(disciplina);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar disciplinas: " + e.getMessage());
            e.printStackTrace();
        }
        return disciplinas;
    }
}