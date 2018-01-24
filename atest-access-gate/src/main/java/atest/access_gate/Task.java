package atest.access_gate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task extends LifecycleSupport implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(Task.class);
	
	public Task(int taskCount) {
		super(taskCount);
	}

	@Override
	public void run() {
		readyLatch.countDown();
		
		try {
			startLatch.await();
		} catch (InterruptedException e) {
			logger.error("task interrupted", e);
		}
		
		while(isRunning()) {
			logger.info("hello");
		}
	}

}
