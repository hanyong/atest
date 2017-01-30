package atest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class);
	}
	
	@Bean
	public ServletRegistrationBean httpServletRegistrationBean(Http http) {
		ServletRegistrationBean reg = new ServletRegistrationBean(http, "/http");
		return reg;
	}
	
}
