package encoding;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/encoding")
public class EncodingServlet extends HttpServlet {

	protected void doGet(
		HttpServletRequest request, 
		HttpServletResponse response
		) throws ServletException, IOException {
		
		// 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8;");
		// 응답 출력 객체
		PrintWriter writer = response.getWriter();
		
		// 요청
		String name = request.getParameter("name");
		System.out.println("name : " + name);
		writer.println("name : " + name);
		// 응답
		writer.println( "/encoding : 인코딩 설정 (UTF-8)" );
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(
		HttpServletRequest request, 
		HttpServletResponse response
		) throws ServletException, IOException {
		doGet(request, response);
	}

}
