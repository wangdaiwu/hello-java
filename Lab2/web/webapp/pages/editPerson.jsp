<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>添加Person</title>
    <%@ include file="/pages/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("#username").blur(function () {
                var usernameText = $("#username").val();
                var usernamePatt = /^\w{1,10}$/;
                if (!usernamePatt.test(usernameText)) {
                    $("#usernameError").text("invalid username");
                } else {
                    $("#usernameError").text("");
                }
            })

            $("#name").blur(function () {
                var nameText = $("#name").val();
                var namePatt = /^[\u4e00-\u9fa5_a-zA-Z0-9_]{1,8}$/;
                if (!namePatt.test(nameText)) {
                    $("#nameError").text("invalid name");
                } else {
                    $.ajax({
                        url:"${pageContext.request.contextPath}/labServlet",
                        data:"action=hasName&name=" + $("#name").val(),
                        success:function (data) {
                            if (data.hasName) {
                                $("#nameError").text("name already exists");
                                hasName = true;
                            } else {
                                $("#nameError").text("");
                            }
                        },
                        dataType:"json"
                    });
                }
            })

            $("#teleno").blur(function () {
                var telenoText = $("#teleno").val();
                var telenoPatt = /^[0-9]{11,11}$/;
                if (!telenoPatt.test(telenoText)) {
                    $("#telenoError").text("invalid telephone numbers");
                } else {
                    $("#telenoError").text("");
                }
            })

            $("#submitButton").click(function () {
                var usernameText = $("#username").val();
                var usernamePatt = /^\w{1,10}$/;
                if (!usernamePatt.test(usernameText)) {
                    $("#usernameError").text("invalid username");
                    return false;
                } else {
                    $("#usernameError").text("");
                }

                var nameText = $("#name").val();
                var namePatt = /^[\u4e00-\u9fa5_a-zA-Z0-9_]{1,8}$/;
                if (!namePatt.test(nameText)) {
                    $("#nameError").text("invalid name");
                    return false;
                } else {
                    var hasName = false;
                    $.ajax({
                        url:"${pageContext.request.contextPath}/labServlet",
                        data:"action=hasName&name=" + $("#name").val(),
                        success:function (data) {
                            if (data.hasName) {
                                $("#nameError").text("name already exists");
                                hasName = true;
                            } else {
                                $("#nameError").text("");
                            }
                        },
                        dataType:"json",
                        async:false
                    });
                    if (hasName == true) {
                        return false;
                    }
                }

                var telenoText = $("#teleno").val();
                var telenoPatt = /^[0-9]{11,11}$/;
                if (!telenoPatt.test(telenoText)) {
                    $("#telenoError").text("invalid telephone numbers");
                    return false;
                } else {
                    $("#telenoError").text("");
                }
            });
        });
    </script>
</head>
<body>
<div class="main">
    <form action="labServlet" method="get">
        <input type="hidden" name="action" value="addPerson"/>
        <table>
            <tr>
                <th>username</th>
                <th>name</th>
                <th>age</th>
                <th>teleno</th>
                <th colspan="2">操作</th>
            </tr>
            <tr>
                <td><input id="username" name="username" type="text" class="inputClass"/></td>
                <td><input id="name" name="name" type="text" class="inputClass"/></td>
                <td><input id="age" name="age" type="text" class="inputClass"/></td>
                <td><input id="teleno" name="teleno" type="text" class="inputClass"/></td>
                <td><input id="submitButton" type="submit" value="提交"/></td>
            </tr>
            <tr>
                <td><span  id="usernameError"></span></td>
                <td><span  id="nameError"></span></td>
                <td></td>
                <td><span  id="telenoError"></span></td>
                <td></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
