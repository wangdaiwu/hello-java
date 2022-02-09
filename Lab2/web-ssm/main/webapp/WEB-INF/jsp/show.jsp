<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页</title>
    <%@ include file="/WEB-INF/jsp/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("a.deleteClass").click(function () {
                return confirm("你确定要删除 username 为 " + $(this).parent().parent().find("td:first").text() + " 的数据吗?");
            });
        });
    </script>
</head>
<body>
<h1>TABLE User</h1>
<div class="main">
    <table>
        <tr>
            <th>username</th>
            <th>password</th>
            <th>操作</th>
        </tr>

        <c:forEach items="${requestScope.userList}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>
                    <a class="deleteClass" href="deleteUser/${user.username}">删除</a>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td></td>
            <td></td>
            <td><a href="editUser">添加User</a></td>
        </tr>
    </table>
</div>
<br/>
<h1>TABLE Person</h1>
<div class="main">
    <table>
        <tr>
            <th>username</th>
            <th>name</th>
            <th>age</th>
            <th>telno</th>
            <th>操作</th>
        </tr>

        <c:forEach items="${requestScope.personList}" var="person">
            <tr>
                <td>${person.username}</td>
                <td>${person.name}</td>
                <td>${person.age}</td>
                <td>${person.phone}</td>
                <td>
                    <a class="deleteClass" href="deletePerson/${person.username}">删除</a>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="editPerson">添加Person</a></td>
        </tr>
    </table>
</div>
</body>
</html>