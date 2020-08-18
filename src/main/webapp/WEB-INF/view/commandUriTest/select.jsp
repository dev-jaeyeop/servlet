<%@ page import="java.util.ArrayList" %>
<%@ page import="commandUriTest.model.dto.User" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020-08-14dasd
  Time: 오후 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>select</title>
</head>
<style>
    table, td {
        border: 1px solid black;
        border-collapse: collapse;
    }
</style>
<body>
<table>
    <%
        ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");

        if (users == null || users.size() < 1) {
    %>
    <script>
        alert("not found");
        // location.href = 'insert.jsp';
    </script>
    <%
    } else {
    %>
    <tr>
        <td>
            ID
        </td>
        <td>
            NAME
        </td>
    </tr>
    <%
        for (int i = 0; i < users.size(); i++) {
    %>
    <tr>
        <td>
            <%=users.get(i).getId()%>
        </td>
        <td>
            <%=users.get(i).getName()%>
        </td>
    </tr>
    <%
        }
    %>
    <tr>
        <td colspan="2" style="text-align: center">
            <input type="button" value="insert" onclick="location.href='/insert.do'">
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
