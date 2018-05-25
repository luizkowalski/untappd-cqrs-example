package codes.luiz.untappdcqrs.domains.common.controllers

import codes.luiz.untappdcqrs.domains.common.services.JwtService
import codes.luiz.untappdcqrs.domains.user.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("sign_in")
class SignInController(
        val passwordEncoder: PasswordEncoder,
        val userRepository: UserRepository
) {


  @PostMapping
  fun signIn(
          @RequestParam
          email: String,
          @RequestParam
          password: String
  ): ResponseEntity<Any> {
    var user = userRepository.findByEmail(email)
    if (user.isPresent && passwordEncoder.matches(password, user.get().password)) {
      var token = JwtService().createToken(user.get())
      return ResponseEntity.status(HttpStatus.OK).body(token)
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized")
  }


}