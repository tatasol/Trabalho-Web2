<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cadastro de Aluno em Disciplina</title>
</head>
<body>
<h2>Cadastro de Aluno em Disciplina</h2>
<form action="AlunoDisciplinaServlet" method="post">
    <label for="nome">Nome do Aluno:</label>
    <input type="text" id="nome" name="nome" required>

    <label for="matricula">Matrícula:</label>
    <input type="text" id="matricula" name="matricula" required>

    <label for="disciplina">Disciplina:</label>
    <select id="disciplina" name="disciplina" required>
        <option value="">Selecione uma disciplina</option>
        <option value="matematica">Matemática</option>
        <option value="portugues">Português</option>
        <option value="historia">História</option>
        <option value="geografia">Geografia</option>
        <option value="informatica">Informática</option>
    </select>

    <label for="semestre">Semestre:</label>
    <select id="semestre" name="semestre" required>
        <option value="">Selecione o semestre</option>
        <option value="2024.1">2024.1</option>
        <option value="2024.2">2024.2</option>
    </select>

    <button type="submit">Cadastrar Aluno</button>
</form>
<br>
<a href="index.jsp">Voltar</a>
</body>
</html>