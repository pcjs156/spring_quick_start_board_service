<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C/DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>로그인</title>
    <style type="text/css">
        .field-label-cell {
            background-color: orange;
        }
    </style>
</head>
<body>
<center>
    <h1>로그인</h1>
    <hr>
    <form action="login_proc.jsp" method="post">
        <table border="1" cellpadding="0" cellspacing="0">
            <tr>
                <td class="field-label-cell">ID</td>
                <td><input type="text" name="id"></td>
            </tr>
            <tr>
                <td class="field-label-cell">PW</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Login">
                </td>
            </tr>
        </table>
    </form>
    <hr>
</center>
</body>
</html>
