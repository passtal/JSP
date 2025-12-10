package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/img")

// Servlet으로 생성하기 class로 생성 x 
// ctrl N 으로 생성하지말고 똑바로 생성

// class로 생성하더라도 시작하는 단어 대문자로 변경하고 상위servlet을 상속받으면 (extends XXX) 해결
public class ImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(
		HttpServletRequest request, 
		HttpServletResponse response
		) throws ServletException, IOException {
		
		// 다운로드할 파일명
		String fileName = request.getParameter("fileName");
		if ( fileName == null || fileName.isEmpty() ) {
			response.getWriter().println("파일명이 지정되지 않았습니다.");
			return;
		}
		
		// 파일 경로
		String downloadDir = "C:\\fileupload";
		String downloadFilePath = downloadDir + File.separator + fileName;
		File file = new File(downloadFilePath);
		
		// 파일 존재 여부
		if ( !file.exists() ) {
			response.getWriter().println("파일이 존재하지 않습니다.");
			return;
		}
		
		// 응답 헤더 설정
		// - Content-Type		: image/*
		
		// 파일명으로부터 이미지 확장자 추출
		// fileName : /a.b/A.B/XXX.jpg
		String ext
			= fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		
		// 확장자에 대응하는 Content-Type 지정
		String contentType = "image/jpeg";
		switch (ext) {
			case "jpg":
			case "jpeg":	contentType = "image/jpeg"; break;
			case "png":		contentType = "image/png"; break;
			case "gif":		contentType = "image/gif"; break;
			case "webp":	contentType = "image/webp"; break;
			default:
				System.out.println("이미지 형식이 아닙니다.");
		}
		
		// 응답 헤더 설정
		response.setContentType(contentType);
		
		//썸네일 이미지 출력
		// Client ← ServletOutPutStream ← *Server ← FileInputStream ← File System
		try {
			FileInputStream fis = new FileInputStream(file);
			ServletOutputStream sos = response.getOutputStream();
			
			byte[] buffer = new byte[4096];
			int data = -1;
			while((data = fis.read(buffer)) != -1) {
				sos.write(buffer, 0, data);
			}
			fis.close();
			sos.close();
		} catch (Exception e) {
			System.err.println("파일 다운로드 중 에러 발생!");
			e.printStackTrace();
		}
	}

}