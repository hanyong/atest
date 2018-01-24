package atest.websocket;

import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

//@org.springframework.context.annotation.Configuration
public class WsConfig implements ApplicationRunner {

	@Autowired
	protected ServerContainer serverContainer;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		ServerEndpointConfig sec = ServerEndpointConfig.Builder.create(Ws.class, "/ws").build();
		serverContainer.addEndpoint(sec);
	}

}
