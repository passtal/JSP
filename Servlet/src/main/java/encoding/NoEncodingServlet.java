package encoding;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/no-encoding")
public class NoEncodingServlet extends HttpServlet {
       

	protected void doGet(
		HttpServletRequest request,
		HttpServletResponse response
		) throws ServletException, IOException {
		
		// 요청
		String name = request.getParameter("name");
		response.getWriter().append("name : " + name);
		
		// 응답
		response.getWriter().append("/no-encoding : 인코딩 설정 안함");
	}

	protected void doPost(
		HttpServletRequest request,
		HttpServletResponse response
		) throws ServletException, IOException {
		doGet(request, response);
	}
}