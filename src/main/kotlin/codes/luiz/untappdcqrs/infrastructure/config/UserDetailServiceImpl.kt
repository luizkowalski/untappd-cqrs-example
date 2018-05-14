package codes.luiz.untappdcqrs.infrastructure.config

import codes.luiz.untappdcqrs.domains.user.repositories.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class UserDetailServiceImpl(
        val userRepository: UserRepository
) : UserDetailsService {
    val logger = LoggerFactory.getLogger(UserDetailServiceImpl::class.java)
    override fun loadUserByUsername(username: String): UserDetails? {
        logger.info("Loading user: {}", username)
        var user = userRepository.findByEmail(username)
        if (user != null) {
            logger.info("User loaded: {}", user.email)
            return ApiUserDetail(user)
        }
        logger.info("Couldnt find user")
        return null
    }
}