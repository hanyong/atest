package atest.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

// 服务端不需要此类
//@org.springframework.context.annotation.Configuration
public class SpringClient {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringClient.class, SpringWs.class);
		app.setApplicationContextClass(AnnotationConfigApplicationContext.class);
		WebSocketConnectionManager conn = app.run(args).getBean(WebSocketConnectionManager.class);
		conn.start();
	}
	
	@Bean
	public WebSocketClient webSocketClient() {
		return new StandardWebSocketClient();
	}
	
	@Bean
	public WebSocketConnectionManager conn(WebSocketClient client, WebSocketHandler handler) {
		return new WebSocketConnectionManager(client, handler, "ws://localhost:8080/ws");
	}
	
}
