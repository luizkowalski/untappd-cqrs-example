package codes.luiz.untappdcqrs.infrastructure.config

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthorizationFilter : OncePerRequestFilter() {
    override fun doFilterInternal(p0: HttpServletRequest, p1: HttpServletResponse, p2: FilterChain) {
        println("Why am I here?")
        p2.doFilter(p0, p1)
    }
}