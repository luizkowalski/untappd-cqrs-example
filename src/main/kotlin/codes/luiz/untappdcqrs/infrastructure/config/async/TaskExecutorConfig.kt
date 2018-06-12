package codes.luiz.untappdcqrs.infrastructure.config.async

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor


@Configuration
class TaskExecutorConfig : AsyncConfigurer {

  override fun getAsyncExecutor(): Executor {
    val executor = ThreadPoolTaskExecutor()
    val cores = Runtime.getRuntime().availableProcessors()
    executor.corePoolSize = cores
    executor.maxPoolSize = cores * 2
    executor.setQueueCapacity(cores * 200)
    executor.setThreadNamePrefix("untappd-cqrs-tp-")
    executor.initialize()

    return executor
  }
}