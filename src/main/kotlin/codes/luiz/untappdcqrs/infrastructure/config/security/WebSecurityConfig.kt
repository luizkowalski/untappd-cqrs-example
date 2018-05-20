package codes.luiz.untappdcqrs.infrastructure.config.security

import codes.luiz.untappdcqrs.infrastructure.config.JwtAuthorizationFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
//@EnableWebMvc
class WebSecurityConfig(
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
                .antMatchers("/users/sign_up", "sign_in").permitAll()
                .anyRequest().authenticated()

        http.addFilterBefore(JwtLoginFilter("/sign_in", authenticationManager()), UsernamePasswordAuthenticationFilter::class.java)
        http.addFilterAfter(JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }
}