<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>���� ������</title>
</head>
<body>
    <div>
        <form action="sendMail.jsp" method="post">
            <table>
                <tr><th colspan="2">JSP ���� ������</th></tr>
                <tr><td>����</td><td><input type="text" name="subject" /></td></tr>
                <tr><td>����</td><td><textarea name="content" style="width:300px; height:200px;"></textarea></td></tr>
                <tr><td colspan="2" style="text-align:right;"><input type="submit" value="������"/></td></tr>
            </table>
        </form>
    </div>
</body>
</html>

