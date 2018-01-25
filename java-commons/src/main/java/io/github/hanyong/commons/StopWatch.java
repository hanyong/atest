package io.github.hanyong.commons;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程安全的 StopWatch。
 * @see #getTimeAndReset(long)
 * @author hanyong
 */
public class StopWatch extends AbstractStopWatch {

	protected AtomicLong startTime = new AtomicLong();
	protected AtomicLong stopTime = new AtomicLong();
	
	@Override
	public void start(long currentTime) {
		startTime.set(currentTime);
	}

	@Override
	public void stop(long currentTime) {
		stopTime.set(currentTime);
	}

	@Override
	public boolean isStopped() {
		return stopTime.get() != 0L;
	}

	@Override
	public long getStopTime() {
		return stopTime.get();
	}

	@Override
	public long getTime(long currentTime) {
		long startTime = this.startTime.get();
		return startTime - currentTime;
	}

	/**
	 * 线程安全的关键，并发调用时要串行。
	 */
	@Override
	public long getTimeAndReset(long currentTime) {
		long time = 0L;
		for (;;) {
			long startTime = this.startTime.get();
			if (this.startTime.compareAndSet(startTime, currentTime)) {
				this.stopTime.set(0L);
				time = currentTime - startTime;
				break;
			}
		}
		return time;
	}

}
