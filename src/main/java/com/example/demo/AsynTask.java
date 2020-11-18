package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.core.Ordered;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
@EnableAsync(mode = AdviceMode.PROXY,order = Ordered.HIGHEST_PRECEDENCE)
public class AsynTask implements SchedulingConfigurer, AsyncConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(AsynTask.class);
    private String resumeAutoAssignCron ="0/5 * * * * *";
    private int count;
    @Autowired
    private TaskScheduler taskScheduler;
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        TriggerTask triggrtTask = new TriggerTask(
                this::jobDetail,
                triggerContext -> new CronTrigger(resumeAutoAssignCron).nextExecutionTime(triggerContext)
        );
        scheduledTaskRegistrar.addTriggerTask(triggrtTask);
    }

    private void jobDetail(){
        count++;
        logger.info("ResumeAutoAssignDynamicTaskTest.jobDetail, count={}", count);
        if(count > 5){
            resumeAutoAssignCron = "0/10 * * * * *";
        }
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(20);
        scheduler.setThreadNamePrefix("task-");
        scheduler.setAwaitTerminationSeconds(60);
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        return scheduler;
    }
}
