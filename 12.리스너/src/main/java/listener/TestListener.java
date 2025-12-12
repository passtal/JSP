package listener;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class TestListener implements ServletContextListener {

    public TestListener() {
    	System.out.println("ServletContextListener 객체 생성");
    }

    public void contextInitialized(ServletContextEvent sce)  {
    	System.out.println("TestListener - 웹 어플리케이션 시작");
    	// TODO : DB 연결 설정
    }

    public void contextDestroyed(ServletContextEvent sce)  {
    	System.out.println("TestListener - 웹 어플리케이션 종료");
    	// TODO : 자원 해제
    }
	
}