package atest.access_gate;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.Validate;

public class AccessGate {

	protected AtomicLong lastTime = new AtomicLong();
	protected final long delay;
	protected AtomicInteger count = new AtomicInteger();
	
	public AccessGate(long currentTime, long delay) {
		Validate.isTrue(delay > 0L, "delay must greater than 0: %s", delay);
		this.delay = delay;
		reset(currentTime);
	}
	
	public void reset(long currentTime) {
		this.lastTime.set(currentTime);
		this.count.set(0);
	}
	
	public long getDelay() {
		return delay;
	}
	
	/**
	 * 尝试一次访问，返回是否允许访问。
	 */
	public boolean permit(long currentTime) {
		final long time = attempt(currentTime);
		return time >= getDelay();
	}
	
	/**
	 * 尝试一次访问，返回间隔时间。<br/>
	 * 如果满足时间间隔，则访问成功，自动重置访问时间，返回结果大于或等于 {@link #getDelay()}。<p/>
	 * 
	 * 如果只关心是否访问成功，不关心间隔时间，可以使用 {@link #permit(long)}。<br/>
	 */
	public long attempt(long currentTime) {
		count.incrementAndGet();
		long time = 0L;
		for (;;) {
			long lastTime = this.lastTime.get();
			time = currentTime - lastTime;
			// 不满足时间间隔
			if (time < delay) {
				break;
			}
			// 满足时间间隔，尝试访问
			if (this.lastTime.compareAndSet(lastTime, currentTime)) {
				break;
			}
		}
		return time;
	}
	
	public int getAndResetCount() {
		int n = count.get();
		count.getAndAdd(-n);
		return n;
	}
	
}
