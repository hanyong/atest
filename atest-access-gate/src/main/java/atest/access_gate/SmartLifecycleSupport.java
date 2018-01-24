package atest.access_gate;

import org.springframework.context.SmartLifecycle;

public class SmartLifecycleSupport extends LifecycleSupport implements SmartLifecycle {

	public SmartLifecycleSupport(int taskCount) {
		super(taskCount);
	}

	@Override
	public int getPhase() {
		return 0;
	}

	@Override
	public boolean isAutoStartup() {
		return true;
	}

	@Override
	public void stop(Runnable callback) {
		super.stop();
		callback.run();
	}

}
