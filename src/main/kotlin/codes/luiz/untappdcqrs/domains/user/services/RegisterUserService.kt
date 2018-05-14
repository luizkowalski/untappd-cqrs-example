package codes.luiz.untappdcqrs.domains.user.services

import codes.luiz.untappdcqrs.domains.user.models.User
import codes.luiz.untappdcqrs.domains.user.repositories.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class RegisterUserService(
        val passwordEncoder: PasswordEncoder,
        val userRepository: UserRepository
) {

    fun registerUser(email: String, password: String): User {
        var user = User()
        user.email = email;
        user.password = passwordEncoder.encode(password)

        return userRepository.save(user)
    }
}