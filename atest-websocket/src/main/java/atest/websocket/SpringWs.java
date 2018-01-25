package atest.websocket;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.springframework.web.socket.PingMessage;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class SpringWs extends AbstractWebSocketHandler {
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		session.sendMessage(new TextMessage("Hello WebSocket"));
//		ByteBuffer payload = ByteBuffer.wrap("hello".getBytes(StandardCharsets.UTF_8));
//		session.sendMessage(new PingMessage(payload));
	}

	@Override
	protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
//		String msg = new String(message.getPayload().array(), StandardCharsets.UTF_8);
//		System.out.println("spring get PongMessage: " + msg);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//		System.out.println("spring get message: " + message.getPayload());
		session.sendMessage(message);
	}
	
}
