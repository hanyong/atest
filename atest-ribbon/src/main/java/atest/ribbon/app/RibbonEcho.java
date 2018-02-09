package atest.ribbon.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.stereotype.Service;

import atest.ribbon.client.RibbonEchoConfig;

@Service
@RibbonClient(name = RibbonEcho.NAME, configuration = RibbonEchoConfig.class)
public class RibbonEcho {

	private static final Logger logger = LoggerFactory.getLogger(RibbonEcho.class);
	
	public static final String NAME = "echo";
	
	@Autowired
	protected LoadBalancerClient loadBalancer;
	
	public void execute() {
		ServiceInstance service = loadBalancer.choose(NAME);
		logger.info("proxy pass to: host={} port={}", service.getHost(), service.getPort());
	}
	
}
