package codes.luiz.untappdcqrs.infrastructure.config.security.matchers

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class NoAuthSecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/users/sign_up")
    }
}