# Servlet

Java 프로그래밍 언어를 기반으로 함
웹 응용 프로그램 개발기술

- 웹 서버와 상호 작용 (동적 웹페이지 생성 및 관리)
- 웹 어플의 서버측 로직처리
- HttpServlet 클래스를 상속한 자바 클래스 (/*public*/ class A extends B 의 구조)

- *** 클라이언트의 요청을 받고, 그 요청에 따라 데이터베이스와 interact, 그 과정에서 동적으로 HTML을 생성 ***


# Servlet Container (aka 웹 컨테이너)

- Servlet 이나 JSP 같은 웹 컴포넌트를 실행하는데 사용되는 소프트웨어

- 주요 기능 : 컨테이너, 정적 및 동적 웹 컨텐츠 제공, 웹 서버/어플리케이션 호스팅


# Servlet Lifecycle (Servlet 생명주기)

## 로딩 단계
- 최초 요청 시, 새로운 Servlet 객체를 생성
- 두 번째 이후부터는 메모리에 저장했던 객체를 불러와서 사용 (메모리가 포맷되면 다시 최초 요청으로 취급)

## 초기화 단계
- init() 메소드가 호출되는 단계
- Servlet 객체가 최초로 한 번 생성될 때 실행
- 매 요청마다 실행 x // 객체 생성 시에만 실행됨 (초기화 작업을 진행함)

## 실행 단계
- doGet		: Client 로부터 GET 요청 → 호출
- doPost	: Client 로부터 POST 요청 → 호출
- destroy	: Servlet 클래스가 사용되지 않을 때, Container로부터 Servlet을 제거하기 위해 호출

[생명주기 메소드에서 더보기]


# 요청 경로 매핑
- web.xml 을 사용한 매핑
- Annotation 을 사용한 매핑

## web.xml 사용

- STEP 1 : BoardListServlet 을 /board/list 경로로 매핑
- STEP 2 : /board/list 경로로 요청이 들어옴
- STEP 3 : BoardListServlet 에서 요청을 전달받음

↓↓↓↓↓↓↓↓↓↓ 예시 코드 ↓↓↓↓↓↓↓↓↓↓

/*
<web-app>
    <servlet>
        <servlet-name>BoardListServlet</servlet-name>
        <servlet-class>com.example.BoardListServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>BoardListServlet</servlet-name>
        <url-pattern>/board/list</url-pattern>
    </servlet-mapping>
</web-app>
*/

## Annotation 사용

- Annotation 딸깍~

↓↓↓↓↓↓↓↓↓↓ 예시 코드 ↓↓↓↓↓↓↓↓↓↓

/*
import javax.servlet.annotation.WebServlet;

/* @WebServlet("/board/list") *
@WebServlet(name = "BoardListServlet", urlPatterns = {"/board/list"})
public class BoardListServlet extends HttpServlet {
			/* doGet, doPost, ...*

}
*/


# 요청 처리

## HttpServletRequest
- Client 가 요청한 HTTP 정보를 나타내는 객체
- HttpServletRequest 사용시, 다양한 메소드와 속성을 이용해 요청 정보를 확인할 수 있음

.. 대략 많은 메소드가 존재하며 (첨부 사진 확인)

- 결과 예시 코드는 아래에서 확인 가능

↓↓↓↓↓↓↓↓↓↓ 코드 실행 결과 ↓↓↓↓↓↓↓↓↓↓

/*
Hello, World!
HTTP 요청 메서드: GET
파라미터 값: []
요청 URI: [/ContextPath/MyServlet]
요청 URL: [http://localhost:8080 /*ContextPath/MyServlet* ]
User-Agent 헤더: [사용자 에이전트 정보]
클라이언트 IP 주소: [클라이언트의 IP 주소]
*/


# 응답 처리
- 텍스트 데이터를 출력하기 위한 표준 출력 스트림 (문자열 / 텍스트데이터 / HTML) ~ 텍스트 기반의 데이터가 주 출력원

## 주요 기능
- 텍스트 데이터 출력
- 자동 줄 바꿈 처리
- 문자 인코딩 처리

/* PrintWriter : 주로 웹 어플리케이션에서 HTTP 응답을 생성할때 사용됨 */ (or 텍스트 파일에 데이터 기록)


# 페이지 이동
- forward & redirect 방식

## forward
- 서버 내부에서 요청 경로 A가 B로 요청을 전달하며 페이지로 이동하는 방식
- 요청정보 (request) / 응답정보 (response)

- 특징
- 원래의 요청정보 및 응답정보를 그대로 사용함
- Client는 이동한 페이지인 B를 응답받지만, 주소 표시줄의 요청 URL(요청정보)는 A 그대로임
- 즉, Client → (request) → Server → 생성:(A의 URL) → [forward] → 생성:(B의 URL) → (reponse) → Client (B의 URL을 제공받음. 화면에 표시)

## redirect
- 서버가 요청 경로 A로 요청을 받은 후, 클라이언트에게 B로 다시 요청하라고 지시하며 페이지를 이동하는 방식