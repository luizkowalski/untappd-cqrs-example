package codes.luiz.untappdcqrs.infrastructure.services

import codes.luiz.untappdcqrs.domains.user.models.User
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

class JwtService {

    private val algorithm = Algorithm.HMAC256("APPSECRET")
    private val issuer = "APP"

    fun createToken(user: User): String {

        return JWT.create().withIssuer(issuer)
                .withClaim("user", user.email)
                .sign(algorithm)
    }

    fun validToken(token: String): Boolean {
        return try {
            JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build().verify(token)
            true
        } catch (e: Exception) {
            println(e)
            false
        }

    }

}