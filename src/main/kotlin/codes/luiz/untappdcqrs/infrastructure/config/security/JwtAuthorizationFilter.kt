package codes.luiz.untappdcqrs.infrastructure.config

import codes.luiz.untappdcqrs.domains.user.models.User
import codes.luiz.untappdcqrs.infrastructure.config.security.ApiUserDetail
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthorizationFilter : AbstractAuthenticationProcessingFilter("/") {


    override fun attemptAuthentication(p0: HttpServletRequest?, p1: HttpServletResponse?): Authentication {
        println("Testing")
        val securityUser = ApiUserDetail(User())
        return PreAuthenticatedAuthenticationToken(securityUser, null, null)

    }

    override fun doFilter(req: ServletRequest?, res: ServletResponse?, chain: FilterChain?) {
        println("Im here")
    }

}