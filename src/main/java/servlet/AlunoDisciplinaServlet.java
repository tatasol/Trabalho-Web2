package servlet;


import connection.Conexao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AlunoDisciplinaServlet")
public class AlunoDisciplinaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Configurar encoding para suportar caracteres especiais
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // Capturar parâmetros do formulário
        String nome = request.getParameter("nome");
        String matricula = request.getParameter("matricula");
        String disciplina = request.getParameter("disciplina");
        String semestre = request.getParameter("semestre");

        // Validação básica dos campos
        if (nome == null || nome.trim().isEmpty() ||
                matricula == null || matricula.trim().isEmpty() ||
                disciplina == null || disciplina.trim().isEmpty() ||
                semestre == null || semestre.trim().isEmpty()) {

            // Redirecionar com mensagem de erro se campos estiverem vazios
            request.setAttribute("erro", "Todos os campos são obrigatórios");
            request.getRequestDispatcher("aluno-disciplina-cadastro.jsp")
                    .forward(request, response);
            return;
        }

        // Tentar realizar o cadastro no banco de dados
        try {
            // Assumindo que você tem uma classe de conexão com o banco de dados
            Conexao conexao = (Conexao) Conexao.getConexao();

            // Preparar SQL para inserção
            String sql = "INSERT INTO aluno_disciplina (nome, matricula, disciplina, semestre) " +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, matricula);
            stmt.setString(3, disciplina);
            stmt.setString(4, semestre);

            // Executar inserção
            int linhasAfetadas = stmt.executeUpdate();

            // Verificar se o cadastro foi realizado com sucesso
            if (linhasAfetadas > 0) {
                // Redirecionar para página de sucesso
                request.setAttribute("mensagem", "Aluno cadastrado com sucesso!");
                request.getRequestDispatcher("sucesso.jsp")
                        .forward(request, response);
            } else {
                // Caso não tenha inserido
                request.setAttribute("erro", "Erro ao cadastrar aluno");
                request.getRequestDispatcher("aluno-disciplina-cadastro.jsp")
                        .forward(request, response);
            }

            // Fechar recursos
            stmt.close();

        } catch (SQLException e) {
            // Tratamento de erro de banco de dados
            e.printStackTrace();
            request.setAttribute("erro", "Erro no banco de dados: " + e.getMessage());
            request.getRequestDispatcher("erro.jsp")
                    .forward(request, response);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para processar requisições GET (opcional)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Pode redirecionar para página de cadastro ou listar alunos
        request.getRequestDispatcher("aluno-disciplina-cadastro.jsp")
                .forward(request, response);
    }
}