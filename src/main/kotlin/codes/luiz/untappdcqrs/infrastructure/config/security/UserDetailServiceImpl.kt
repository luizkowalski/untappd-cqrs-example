package codes.luiz.untappdcqrs.infrastructure.config.security

import codes.luiz.untappdcqrs.domains.user.repositories.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class UserDetailServiceImpl(
        val userRepository: UserRepository
) : UserDetailsService {
    val logger: Logger = LoggerFactory.getLogger(UserDetailServiceImpl::class.java)
    override fun loadUserByUsername(username: String): UserDetails? {
        var user = userRepository.findByEmail(username)
        if (user.isPresent) {
            logger.info("User loaded: {}", user.get().email)
            return ApiUserDetail(user.get())
        }
        logger.info("Couldn't find user")
        return null
    }
}