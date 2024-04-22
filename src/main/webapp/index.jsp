<%--
  Created by IntelliJ IDEA.
  User: emerson.araujo
  Date: 18/04/2024
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Curso de JSP</title>
</head>
<body>


<h1>Curso de JSP</h1>

<form action="ServletLogin" method="post">
<input type="hidden" value="<%= request.getParameter("url")%>">
    <table>
        <tr>   <%-- tr = LINHA  --%>
            <td><label>Login</label></td>
            <td> <input name="login" type="text"> </td> <%-- td = CÉLULA --%>
        </tr>

        <tr>
            <td><label>Senha</label></td>
            <td> <input name="senha" type="password"> </td>
        </tr>

        <tr>
            <td/>
            <td> <input type="submit" value="Enviar"> </td>
        </tr>
    </table>
</form>

<h4>${msg}</h4>

</body>
</html>
