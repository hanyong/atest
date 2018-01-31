package atest.websocket;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
@lombok.Setter
public class AppProperties {

	public List<String> debug;

}
