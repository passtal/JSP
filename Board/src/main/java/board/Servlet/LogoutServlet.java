package board.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import board.Service.PersistenceLoginsService;
import board.Service.PersistenceLoginsServiceImpl;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		System.out.println("로그아웃...");
		HttpSession session = request.getSession();
		
		// 쿠키 제거
		// id, 자동 로그인, token, username
		Cookie rememberIdCookie = new Cookie("rememberID", "");
		Cookie usernameCookie = new Cookie("username", "");
		Cookie rememberMeCookie = new Cookie("rememberMe", "");
		Cookie tokenCookie = new Cookie("token", "");
		Cookie[] deleteCookies = { rememberIdCookie, usernameCookie, rememberMeCookie, tokenCookie };
		for (int i = 0; i < deleteCookies.length; i++) {
			Cookie cookie = deleteCookies[i];
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		// 자동 로그인 토큰 삭제
		PersistenceLoginsService persistenceLoginService
			= new PersistenceLoginsServiceImpl();
		String username = (String) session.getAttribute("loginId");
		boolean result = persistenceLoginService.delete(username);
		System.out.println("자동 로그인 토큰 삭제 : " + result);
		
		// 아이디 저장 토큰 삭제 → ??
		
		
		session.invalidate();				// 세션 속성 모두 제거 : 세션 무효화
		
		response.sendRedirect(root + "/");	// 로그아웃 후 메인화면으로 이동
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}