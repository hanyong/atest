package atest.websocket;

import java.io.IOException;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;

@javax.websocket.ClientEndpoint
public class Ws extends Endpoint {

	@javax.websocket.OnOpen
	@Override
	public void onOpen(Session session, EndpointConfig config) {
		String msg = "ws";
		Basic remote = session.getBasicRemote();
		try {
			remote.sendText(msg);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
//	@javax.websocket.OnMessage
//	public void onMessage(String message) {
//		System.out.println("Endpoint onMessage: " + message);
//	}

}
