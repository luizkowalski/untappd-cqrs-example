package codes.luiz.untappdcqrs.domains.user.services

import codes.luiz.untappdcqrs.domains.user.models.Profile
import codes.luiz.untappdcqrs.domains.user.models.User
import codes.luiz.untappdcqrs.domains.user.repositories.ProfileRepository
import codes.luiz.untappdcqrs.domains.user.repositories.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class RegisterUserService(
        val passwordEncoder: PasswordEncoder,
        val profileRepository: ProfileRepository,
        val userRepository: UserRepository
) {

  fun registerUser(email: String, password: String): User {
    var user = User()
    user.email = email;
    user.password = passwordEncoder.encode(password)

    var savedUser = userRepository.save(user)
    profileRepository.save(Profile(0, 0, savedUser.id))

    return savedUser
  }
}