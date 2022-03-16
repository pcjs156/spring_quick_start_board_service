<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C/DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>새 글 등록</title>
</head>
<body>
<center>
    <h1>글 등록</h1>
    <a href="logout_proc.jsp">Log-out</a>

    <form action="insertBoard_proc.jsp" method="post">
        <table border="1" cellpadding="0" cellspacing="0">
            <tr>
                <td bgcolor="orange" width="70">제목</td>
                <td align="left"><input name="title" type="text"></td>
            </tr>
            <tr>
                <td bgcolor="orange">작성자</td>
                <td align="left"><input name="writer" type="text" size="10">
                </td>
            </tr>
            <tr>
                <td bgcolor="orange">내용</td>
                <td align="center"><textarea name="content" rows="10" cols="40"></textarea></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="새 글 등록">
                </td>
            </tr>
        </table>
    </form>
    <hr>
    <a href="getBoardList.jsp.jsp">글 목록</a>
</center>
</body>
</html>

