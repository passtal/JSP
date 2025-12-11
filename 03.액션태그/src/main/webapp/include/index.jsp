<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>액션태그 - include</title>
</head>
<body>
	<%
		String menu1 = "Home";
		String menu2 = "Board";
		String menu3 = "Gallery";
	%>
	<%-- 액션 태그 include --%>
	<jsp:include page="header.jsp">
		<jsp:param value="menu1" name="home"/>
		<jsp:param value="menu2" name="board"/>
		<jsp:param value="menu3" name="gallery"/>
	</jsp:include>
	<main>
		<div class="container">
			<h1>메인 영역</h1>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</main>
</body>
</html>