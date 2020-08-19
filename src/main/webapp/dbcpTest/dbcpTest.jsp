<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="commandUriTest.model.dto.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020-08-19
  Time: 오후 4:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>dbcpTest</title>
</head>
<body>
<%
    InitialContext initialContext = new InitialContext();
    DataSource dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/dbcp");

    Connection connection = dataSource.getConnection();
    String sql = "select * from users order by level desc, id;";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    ResultSet resultSet = preparedStatement.executeQuery();

    if (resultSet.next()) {
        out.print(resultSet.getString("name"));
    }
%>
</body>
</html>
