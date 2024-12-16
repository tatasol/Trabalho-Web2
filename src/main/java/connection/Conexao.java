package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/trabalho_web2";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static Connection conexao;

    public static Connection getConexao() throws SQLException, ClassNotFoundException {
        if (conexao == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return conexao;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        if (sql == null || sql.trim().isEmpty()) {
            throw new IllegalArgumentException("A consulta SQL não pode ser nula ou vazia.");
        }

        // Aqui, você criaria o PreparedStatement real
        System.out.println("Preparando o SQL: " + sql);

        // Simulação: normalmente, você retornaria um PreparedStatement do driver do banco
        return conexao.prepareStatement(sql);
    }
}
