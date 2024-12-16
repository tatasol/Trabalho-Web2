package DAO;

import model.Periodo;
import connection.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeriodoDAO {

    public void cadastrarPeriodo(Periodo periodo) throws ClassNotFoundException {
        String sql = "INSERT INTO periodo (disciplina_id, data_inicio, data_fim) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, periodo.getDisciplina().getId());
            ps.setDate(2, Date.valueOf(periodo.getDataInicio()));
            ps.setDate(3, Date.valueOf(periodo.getDataFim()));

            ps.executeUpdate();
            System.out.println("Período cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar período: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Periodo> listarPeriodos() throws ClassNotFoundException {
        String sql = "SELECT * FROM periodo";
        List<Periodo> periodos = new ArrayList<>();

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Periodo periodo = new Periodo();
                periodo.setId(rs.getLong("id"));
                // Seta os valores de disciplina, dataInicio e dataFim, caso sejam necessários
                periodos.add(periodo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar períodos: " + e.getMessage());
            e.printStackTrace();
        }
        return periodos;
    }
}