/**
 * 
 */
package com.chuangqi.config.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 任务配置
 * @author wmk
 *
 */
@Configuration
public class TaskConfig {
	@Bean
	public TaskScheduler taskScheduler(){
		ThreadPoolTaskScheduler threadPoolTaskScheduler=new ThreadPoolTaskScheduler();
		return threadPoolTaskScheduler;
	}
}
