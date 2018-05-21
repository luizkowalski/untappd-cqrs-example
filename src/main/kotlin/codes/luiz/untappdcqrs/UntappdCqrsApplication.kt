package codes.luiz.untappdcqrs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@SpringBootApplication
@EnableJpaAuditing
@EnableWebSecurity
class UntappdCqrsApplication

fun main(args: Array<String>) {
    runApplication<UntappdCqrsApplication>(*args)
}
