package codes.luiz.untappdcqrs.infrastructure.config.security

import codes.luiz.untappdcqrs.infrastructure.services.JwtService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtLoginFilter(url: String, authManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter() {

    init {
        setFilterProcessesUrl(url)
        authenticationManager = authManager
    }

    @Throws(AuthenticationException::class, IOException::class, ServletException::class)
    override fun attemptAuthentication(request: HttpServletRequest,
                                       response: HttpServletResponse): Authentication {

        var credentials = ObjectMapper().readValue(request.inputStream, AccountCredentials::class.java)
        println("Reading credentials")
        return authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        credentials.email,
                        credentials.password,
                        Collections.emptyList()
                )
        );
    }

    override fun successfulAuthentication(request: HttpServletRequest,
                                          response: HttpServletResponse, chain: FilterChain, authResult: Authentication) {
        println("Successful!")
        var details = (authResult.principal as ApiUserDetail)

        println("Create token!")
        var token = JwtService().createToken(details.user)
        println("Token: $token")

        response.addHeader("Authorization", token)
        SecurityContextHolder.getContext().authentication = authResult
        return;
    }

    override fun unsuccessfulAuthentication(request: HttpServletRequest,
                                            response: HttpServletResponse, failed: AuthenticationException?) {
        println("Failed to auth")
        response.sendError(HttpStatus.UNAUTHORIZED.value(), "Not authorized")
    }

}
