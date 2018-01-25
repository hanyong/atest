package io.github.hanyong.commons;

public abstract class AbstractStopWatch {

	public void start() {
		start(System.currentTimeMillis());
	}

	public abstract void start(long currentTime);

	public void stop() {
		start(System.currentTimeMillis());
	}

	public abstract void stop(long currentTime);
	
	public abstract boolean isStopped();
	
	public abstract long getStopTime();
	
	public long getTime() {
		// call getter once, check stopped ourselves, keep status consistent
		long currentTime = getStopTime();
		// if not stopped, use current time
		if (currentTime == 0) {
			currentTime = System.currentTimeMillis();
		}
		return getTime(currentTime);
	}
	
	public abstract long getTime(long currentTime);
	
	public abstract long getTimeAndReset(long currentTime);
	
}
