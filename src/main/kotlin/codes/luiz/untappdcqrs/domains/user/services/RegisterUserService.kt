package codes.luiz.untappdcqrs.domains.user.services

import codes.luiz.untappdcqrs.domains.user.models.Profile
import codes.luiz.untappdcqrs.domains.user.models.User
import codes.luiz.untappdcqrs.domains.user.repositories.ProfileRepository
import codes.luiz.untappdcqrs.domains.user.repositories.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RegisterUserService(
        val passwordEncoder: PasswordEncoder,
        val profileRepository: ProfileRepository,
        val userRepository: UserRepository
) {

  @Transactional
  fun registerUser(emailAddress: String, password: String): User {
    var user = User().apply {
      this.email = emailAddress
      this.password = passwordEncoder.encode(password)
    }

    user = userRepository.save(user)

    var profile = Profile().apply {
      checkinCount = 0
      uniqueCheckinCount = 0
      userId = user.id
      this.email = user.email
    }

    profileRepository.save(profile)

    return user
  }
}