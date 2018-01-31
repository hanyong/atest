package atest.websocket;

import javax.servlet.ServletContext;
import javax.websocket.server.ServerContainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import ch.qos.logback.classic.Level;

@SpringBootApplication
public class App implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Bean
	public ServletRegistrationBean httpServletRegistrationBean(Http http) {
		ServletRegistrationBean reg = new ServletRegistrationBean(http, "/http");
		return reg;
	}
	
	@Bean
	public ServerContainer serverContainer(ServletContext context) {
		return (ServerContainer) context.getAttribute(ServerContainer.class.getName());
	}
	
	@Autowired
	protected AppProperties appProperties;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (appProperties.debug != null) {
			for (String debug : appProperties.debug) {
				logger.info("debug {}", debug);
				ch.qos.logback.classic.Logger log = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(debug);
				if (!log.isDebugEnabled()) {
					log.setLevel(Level.DEBUG);
				}
			}
		}
	}
	
}
