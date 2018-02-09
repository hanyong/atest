package atest.ribbon.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@org.springframework.boot.autoconfigure.ImportAutoConfiguration({
	org.springframework.cloud.netflix.archaius.ArchaiusAutoConfiguration.class,
	org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration.class,
	org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration.class,
})
//@org.springframework.boot.autoconfigure.EnableAutoConfiguration
public class App {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);
		App self = ctx.getBean(App.class);
		self.doMain();
	}
	
	@Autowired
	protected RibbonEcho echo;
	
	protected void doMain() {
		echo.execute();
	}
	
}
