<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020-08-14
  Time: 오후 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>insert</title>
    <style>
        table, td {
            border: 1px solid black;
            border-collapse: collapse;
            text-align: center;
        }
    </style>
</head>
<body>
<form action="/insertProcess.do" method="post">
    <table>
        <tr>
            <td>
                id
            </td>
            <td>
                <input type="text" name="id" required="true">
            </td>
        </tr>
        <tr>
            <td>
                password
            </td>
            <td>
                <input type="password" name="password" required="true">
            </td>
        </tr>
        <tr>
            <td>
                name
            </td>
            <td>
                <input type="text" name="name" required="true">
            </td>
        </tr>
        <tr>
            <td>
                email
            </td>
            <td>
                <input type="email" name="email" required="true">
            </td>
        </tr>
        <tr>
            <td>
                phone
            </td>
            <td>
                <input type="text" name="phone" required="true">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="insert">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
