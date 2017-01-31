package atest;

import java.io.IOException;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;

public class Ws extends Endpoint {

	@Override
	public void onOpen(Session session, EndpointConfig config) {
		String msg = "Hello WebSocket";
		Basic remote = session.getBasicRemote();
		try {
			remote.sendText(msg);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

}
