package codes.luiz.untappdcqrs.infrastructure.config.security.matchers

import codes.luiz.untappdcqrs.domains.user.repositories.UserRepository
import codes.luiz.untappdcqrs.infrastructure.config.JwtAuthorizationFilter
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SignInSecurityConfig(
        val authenticationEntryPoint: AppAuthenticationEntryPoint,
        val userRepository: UserRepository
) : WebSecurityConfigurerAdapter(true) {

  override fun configure(web: WebSecurity) {
    web.ignoring().antMatchers("/sign_in", "/users/sign_up")
  }

  override fun configure(http: HttpSecurity) {
    http.cors().and().csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .anyRequest().authenticated()

    http.addFilterBefore(JwtAuthorizationFilter(userRepository), UsernamePasswordAuthenticationFilter::class.java)
  }
}