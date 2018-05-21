package codes.luiz.untappdcqrs.infrastructure.config.security.matchers

import codes.luiz.untappdcqrs.infrastructure.config.JwtAuthorizationFilter
import codes.luiz.untappdcqrs.infrastructure.config.security.JwtLoginFilter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy


//@Configuration
//@Order(Ordered.LOWEST_PRECEDENCE)
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilterAfter(JwtAuthorizationFilter(), JwtLoginFilter::class.java)
    }
}