package atest;

import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class Client {
	
	public static void main(String[] args) throws Exception {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		URI path = new URI("ws://localhost:8080/ws");
		Session session = container.connectToServer(new Ws(), path);
		session.addMessageHandler(new MessageHandler.Whole<String>() {
			@Override
			public void onMessage(String message) {
				System.out.println("Client get message: " + message);
			}
		});
		Thread.sleep(5000);
	}
	
}
