package io.github.hanyong.commons;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 保存 {@linkplain ApplicationArguments}。
 * 方便测试程序在应用启动后访问解析后的 args（似乎用 {@linkplain ConfigurationProperties} 更好）。
 * @author hanyong
 */
public class ApplicationArgumentsAware implements ApplicationRunner {

	@lombok.Getter
	private ApplicationArguments args;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.args = args;
	}

}
