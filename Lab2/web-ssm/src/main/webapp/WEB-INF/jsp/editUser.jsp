<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>添加User</title>
    <%@ include file="/WEB-INF/jsp/head.jsp" %>
    <script type="text/javascript">
        $(function () {

            $("#username").blur(function () {
                var usernameText = $("#username").val();
                var usernamePatt = /^\w{1,10}$/;
                if (!usernamePatt.test(usernameText)) {
                    $("#usernameError").text("invalid username");
                } else {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/checkUsername",
                        data:"username=" + $("#username").val(),
                        type:"post",
                        dataType:"json",
                        success:function (data) {
                            if (data.existsUsername) {
                                $("#usernameError").text("username already exists");
                            } else {
                                $("#usernameError").text("");
                            }
                        }
                    })
                }
            });

            $("#password").blur(function () {
                var passwordText = $("#password").val();
                var passwordPatt = /^\w{1,8}$/;
                if (!passwordPatt.test(passwordText)) {
                    $("#passwordError").text("invalid password");
                } else {
                    $("#passwordError").text("");
                }
            });

            $("#submitButton").click(function () {
                var usernameText = $("#username").val();
                var usernamePatt = /^\w{1,10}$/;
                if (!usernamePatt.test(usernameText)) {
                    $("#usernameError").text("invalid username");
                    return false;
                } else {
                    var hasUsername = false;
                    $.ajax({
                        url: "${pageContext.request.contextPath}/checkUsername",
                        data:"username=" + $("#username").val(),
                        type: "post",
                        dataType:"json",
                        success:function (data) {
                            if (data.existsUsername) {
                                $("#usernameError").text("username already exists");
                                hasUsername = true;
                            } else {
                                $("#usernameError").text("");
                            }
                        },
                        async:false
                    });
                    if (hasUsername === true) {
                        return false;
                    }
                }

                var passwordText = $("#password").val();
                var passwordPatt = /^\w{1,8}$/;
                if (!passwordPatt.test(passwordText)) {
                    $("#passwordError").text("invalid password");
                    return false;
                } else {
                    $("#passwordError").text("");
                }
            });
        });
    </script>
</head>
<body>
<div class="main">
    <form action="addUser" method="post">
<%--        <input type="hidden" name="action" value="addUser"/>--%>
        <table>
            <tr>
                <th>username</th>
                <th>password</th>
                <th colspan="2">操作</th>
            </tr>
            <tr>
                <td><input id="username" name="username" type="text" class="inputClass"/></td>
                <td><input id="password" name="password" type="text" class="inputClass"/></td>
                <td><input id="submitButton" type="submit" value="提交"/></td>
            </tr>
            <tr>
                <td><span id="usernameError"></span></td>
                <td><span id="passwordError"></span></td>
                <td></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
