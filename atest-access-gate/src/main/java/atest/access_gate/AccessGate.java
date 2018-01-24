package atest.access_gate;

import java.util.concurrent.atomic.AtomicInteger;

public class AccessGate {

	protected long lastTime;
	protected long delay;
	protected AtomicInteger count = new AtomicInteger();
	
	public AccessGate(long currentTime, long delay) {
		this.lastTime = currentTime;
		this.delay = delay;
	}
	
	/**
	 * FIXME 并发时可能会有问题
	 */
	public boolean permit(long currentTime) {
		count.incrementAndGet();
		if (currentTime - lastTime >= delay) {
			lastTime = currentTime;
			return true;
		}
		return false;
	}
	
	public int getAndResetCount() {
		int n = count.get();
		count.getAndAdd(-n);
		return n;
	}
	
}
