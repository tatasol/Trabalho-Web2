<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cadastro de Professor</title>
</head>
<body>
<h2>Cadastro de Professor</h2>
<form action="ProfessorServlet" method="post">
    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="nome" required>
    <br><br>
    <button type="submit">Cadastrar</button>
</form>
<br>
<a href="index.jsp">Voltar</a>
</body>
</html>