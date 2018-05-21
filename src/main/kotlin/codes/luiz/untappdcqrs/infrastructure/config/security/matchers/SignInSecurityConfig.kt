package codes.luiz.untappdcqrs.infrastructure.config.security.matchers

import codes.luiz.untappdcqrs.infrastructure.config.JwtAuthorizationFilter
import codes.luiz.untappdcqrs.infrastructure.config.security.JwtLoginFilter
import codes.luiz.untappdcqrs.infrastructure.config.security.UserDetailServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@Order(1)
class SignInSecurityConfig(
        val passwordEncoder: PasswordEncoder,
        val userDetailServiceImpl: UserDetailServiceImpl
) : WebSecurityConfigurerAdapter() {

    @Autowired
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailServiceImpl).passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/sign_in").permitAll()

        http.addFilterBefore(JwtLoginFilter("/sign_in", authenticationManager()), UsernamePasswordAuthenticationFilter::class.java)
    }
}