package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Component
public class DTask implements SchedulingConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(DTask.class);
    private String resumeAutoAssignCron ="0/5 * * * * *";
    private int count;

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
}
