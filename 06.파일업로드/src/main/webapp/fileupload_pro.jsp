<%@page import="org.apache.commons.fileupload2.jakarta.*"%>   <%-- Jakarta Servlet을 위한 Commons FileUpload 클래스 임포트 --%>
<%@page import="org.apache.commons.fileupload2.core.*"%>      <%-- Commons FileUpload의 코어 기능 클래스 임포트 --%>
<%@page import="java.nio.charset.StandardCharsets"%>          <%-- 문자 인코딩 상수 사용을 위한 임포트 --%>
<%@page import="java.io.FileOutputStream"%>                   <%-- 파일 저장을 위한 출력 스트림 클래스 --%>
<%@page import="java.io.InputStream"%>                        <%-- 파일 입력을 위한 입력 스트림 클래스 --%>
<%@page import="java.nio.charset.Charset"%>                   <%-- 문자셋 관련 클래스 --%>
<%@page import="java.io.File"%>                               <%-- 파일 및 디렉터리 제어용 클래스 --%>
<%@page import="java.util.List"%>                             <%-- 리스트 자료형 사용을 위한 임포트 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 파일 업로드 경로
	String uploadPath = "C:/upload/";
	File uploadDir = new File(uploadPath);	// 해당 경로에 대한 File 객체 생성
	
	// 해당 경로가 존재하지 않으면 폴더 생성
	if( !uploadDir.exists()) {
		uploadDir.mkdirs();
	}
	
	// 클라이언트 요청의 문자 인코딩 설정
	request.setCharacterEncoding("UTF-8");
	
	// 임시 파일 저장 경로 설정
	File repository = new File( System.getProperty("java.io.tmpdir") );
	
	// FileItemFactory 설정 : 업로드된 항목을 생성하는 팩토리 객체
	FileItemFactory<DiskFileItem> factory =  DiskFileItemFactory.builder().setFile(repository).get();
	
	// Servlet 기반 파일 업로드 객체 생성
	JakartaServletFileUpload<DiskFileItem, FileItemFactory<DiskFileItem>> upload
		= new JakartaServletFileUpload<>(factory);
	
	// request를 변환하기 위한 Context 객체 생성
	JakartaServletRequestContext context = new JakartaServletRequestContext(request);
	
	try {
		// 요청에서 form field 및 파일을 변환하여 리스트로 가져옴
		List<DiskFileItem> items = upload.parseRequest(context);
		
		for( DiskFileItem item : items) {
			
			// 텍스트 데이터인 경우
			if( item.isFormField()) {
				String name = item.getFieldName();
				String value = item.getString(StandardCharsets.UTF_8);
				out.println(name + " : " + value + "<br>");
			}
			// 파일 데이터인 경우
			else {
				String fieldName = item.getFieldName();
				String fileName = item.getName();
				
				// 업로드 파일 이름
				String uploadedName = System.currentTimeMillis() + "_" + fileName;
				
				// 파일명이 비어있지 않으면
				if( fileName != null && !fileName.isEmpty()) {
					
					// 저장할 파일 객체 생성
					File uploadedFile = new File(uploadDir, fileName);
					try (
						InputStream is = item.getInputStream();
						FileOutputStream fos = new FileOutputStream(uploadedFile);
					) {
						// 파일 저장
						is.transferTo(fos);
						
						// 업로드 결과 출력
						out.println("--------------------------------------------");
						out.println("필드 이름 : " + fieldName + "<br>");
						out.println("원본 파일명 : " + fileName + "<br>");
						out.println("저장 파일명 : " + uploadedName + "<br>");
						out.println("타입 : " + item.getContentType() + "<br>");
						out.println("경로 : " + uploadedFile.getAbsolutePath() + "<br>");
						out.println("크기 : " + uploadedFile.length() + "<br>");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	} catch(Exception e) {
		out.println("파일 업로드 중 에러 발생");
		e.printStackTrace();
	}
%>