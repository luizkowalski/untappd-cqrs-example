package codes.luiz.untappdcqrs.infrastructure.config.async

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.AsyncTaskExecutor
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor


@Configuration
class TaskExecutorConfig {

  @Bean
  fun getAsync(): AsyncTaskExecutor {
    val executor = ThreadPoolTaskExecutor()
    val cores = Runtime.getRuntime().availableProcessors()
    executor.corePoolSize = cores
    executor.maxPoolSize = cores * 2
    executor.setQueueCapacity(cores * 200)
    executor.setThreadNamePrefix("untappd-cqrs-threadpool-")
    return executor
  }
}