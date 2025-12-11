<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<form action="request02_pro.jsp">
		<div>
			<h3>취미</h3>
			<label for="movie">영화</label>
			<input type="checkbox" name="hobby" id="movie" value="영화">
			
			<label for="exercise">운동</label>
			<input type="checkbox" name="hobby" id="exercise" value="운동">
			
			<label for="coding">코딩</label>
			<input type="checkbox" name="hobby" id="coding" value="코딩">
			
			<label for="camp">캠핑</label>
			<input type="checkbox" name="hobby" id="camp" value="캠핑">
			<input type="submit" value="등록">
		</div>
	</form>
</body>
</html>