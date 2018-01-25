package atest.access_gate;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.hanyong.commons.AccessGate;

public class Task extends LifecycleSupport implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(Task.class);
	
	public AtomicLong count = new AtomicLong();
	public AtomicLong countSum = new AtomicLong();
	public AccessGate gate;

	public AtomicLong lastTime = new AtomicLong();
	
	public Task(int taskCount) {
		super(taskCount);
		long currentTime = System.currentTimeMillis();
		gate = new AccessGate(currentTime, 1000);
		lastTime.set(currentTime);
	}

	public long getDelay(long currentTime) {
		long delay = -lastTime.addAndGet(-currentTime);
		lastTime.set(currentTime);
		return delay;
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
			long currentTime = System.currentTimeMillis();
			if (gate.permit(currentTime)) {
				long delay = getDelay(currentTime);
				int count = gate.getAndResetCount();
				countSum.addAndGet(count);
				logger.info("delay={} count={} hello", delay, count);
			}
		}
	}

}
