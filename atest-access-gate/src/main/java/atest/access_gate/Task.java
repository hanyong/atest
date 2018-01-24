package atest.access_gate;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task extends LifecycleSupport implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(Task.class);
	
	public AtomicLong count = new AtomicLong();
	public AtomicLong countSum = new AtomicLong();
	public AccessGate gate;
	
	public Task(int taskCount) {
		super(taskCount);
		gate = new AccessGate(System.currentTimeMillis(), 1000);
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
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// pass
			}
			count.incrementAndGet();
			if (gate.permit(System.currentTimeMillis())) {
				int count = gate.getAndResetCount();
				countSum.addAndGet(count);
				logger.info("[{}] hello", count);
			}
		}
	}

}
