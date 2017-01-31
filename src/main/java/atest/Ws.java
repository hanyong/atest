package atest;

import java.io.IOException;
import java.io.OutputStream;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;

public class Ws extends Endpoint {

	@Override
	public void onOpen(Session session, EndpointConfig config) {
		byte[] msg = { 'w', 's', };
		Basic remote = session.getBasicRemote();
		try {
			OutputStream out = remote.getSendStream();
			for (int i = 0; i < 1; ++i) {
				for (int j = 0; j < 4; ++j) {
					out.write(msg[j % msg.length]);
				}
				out.flush();
			}
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

}
