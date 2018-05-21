package codes.luiz.untappdcqrs.infrastructure.config

import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthorizationFilter : OncePerRequestFilter() {
    override fun doFilterInternal(p0: HttpServletRequest, p1: HttpServletResponse, chain: FilterChain) {
        println("I Should not be called for permitted endpoints ")
        chain.doFilter(p0, p1)
    }

}