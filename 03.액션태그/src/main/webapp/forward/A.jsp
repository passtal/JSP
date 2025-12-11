<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>액션태그 - forward - A화면</title>
</head>
<body>
	<h1>forward A화면</h1>                       
	<jsp:forward page="B.jsp">
	<jsp:param value="김조은" name="name"/>
	<jsp:param value="20" name="age"/>
	</jsp:forward>
	<p>---------------------</p>
</body>
</html>