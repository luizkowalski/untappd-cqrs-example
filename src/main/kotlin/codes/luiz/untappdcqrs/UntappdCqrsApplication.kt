package codes.luiz.untappdcqrs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication(exclude = [(SecurityAutoConfiguration::class)])
@EnableJpaAuditing
class UntappdCqrsApplication

fun main(args: Array<String>) {
    runApplication<UntappdCqrsApplication>(*args)
}
