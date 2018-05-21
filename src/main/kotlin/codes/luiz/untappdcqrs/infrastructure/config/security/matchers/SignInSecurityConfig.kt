package codes.luiz.untappdcqrs.infrastructure.config.security.matchers

import codes.luiz.untappdcqrs.infrastructure.config.JwtAuthorizationFilter
import codes.luiz.untappdcqrs.infrastructure.config.security.JwtLoginFilter
import codes.luiz.untappdcqrs.infrastructure.config.security.UserDetailServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
@EnableWebSecurity
@Order(1)
class SignInSecurityConfig(
        val authenticationEntryPoint: AppAuthenticationEntryPoint
) : WebSecurityConfigurerAdapter(true) {

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/sign_in", "/users/sign_up")
    }

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()

        http.addFilterBefore(JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }
}