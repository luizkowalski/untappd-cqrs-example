package codes.luiz.untappdcqrs.infrastructure.config

import codes.luiz.untappdcqrs.domains.user.models.User
import codes.luiz.untappdcqrs.domains.user.repositories.UserRepository
import codes.luiz.untappdcqrs.infrastructure.config.security.ApiUserDetail
import codes.luiz.untappdcqrs.infrastructure.services.JwtService
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtAuthorizationFilter(
        private val userRepository: UserRepository
) : OncePerRequestFilter() {

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
    }

    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        var token = req.getHeader(AUTHORIZATION_HEADER)
        if (token.isNullOrBlank() || !JwtService().validToken(token)) {
            res.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid token")
            return
        }

        var email = JwtService().getEmailFromToken(token)
        var user = userRepository.findByEmail(email)

        authenticateUser(user.get(), req)

        chain.doFilter(req, res)
    }

    private fun authenticateUser(user: User, req: HttpServletRequest) {
        val authentication = createFrameworkAuth(ApiUserDetail(user), req)
        SecurityContextHolder.getContext().authentication = authentication
    }

    private fun createFrameworkAuth(user: ApiUserDetail, req: HttpServletRequest): Authentication {
        val authentication = UsernamePasswordAuthenticationToken(user, null, user.authorities)
        authentication.details = WebAuthenticationDetailsSource().buildDetails(req)

        return authentication
    }
}