package mjz.springframework.mjjms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling // this enables task scheduler
@EnableAsync // this combination of annotations sets up spring boot to go ahead and perform tasks out of a task pool
@Configuration
public class TaskConfig {

    @Bean
    TaskExecutor taskExecutor(){
        // gives us the ability ti run async task , and also tells spring to go and expect classes for scheduled tasks
        return new SimpleAsyncTaskExecutor();
    }
}
