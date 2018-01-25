package io.github.hanyong.commons;

/**
 * 非线程安全的 StopWatch。
 * @author hanyong
 */
public class StopWatchUnsafe extends AbstractStopWatch {

	protected long startTime;
	protected long stopTime;
	
	@Override
	public void start(long currentTime) {
		startTime = currentTime;
		stopTime = 0;
	}
	
	@Override
	public void stop(long currentTime) {
		stopTime = currentTime;
	}
	
	@Override
	public boolean isStopped() {
		return stopTime != 0L;
	}
	
	public long getStopTime() {
		return stopTime;
	}
	
	@Override
	public long getTime(long currentTime) {
		return currentTime - startTime;
	}
	
	@Override
	public long getTimeAndReset(long currentTime) {
		long time = getTime(currentTime);
		start(currentTime);
		return time;
	}
	
}
