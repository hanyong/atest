package atest.access_gate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.context.Lifecycle;

public class LifecycleSupport implements Lifecycle {

	public CountDownLatch readyLatch;

	// 先设置标识位，再打开栅栏
	public AtomicBoolean running = new AtomicBoolean();
	public CountDownLatch startLatch;
	
	public LifecycleSupport(int taskCount) {
		readyLatch = new CountDownLatch(taskCount);
		startLatch = new CountDownLatch(1);
	}
	
	public void awaitReady() throws InterruptedException {
		readyLatch.await();
	}
	
	@Override
	public void start() {
		running.set(true);
		startLatch.countDown();
	}

	@Override
	public void stop() {
		running.set(false);
	}

	@Override
	public boolean isRunning() {
		return running.get();
	}

}
