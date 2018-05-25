package codes.luiz.untappdcqrs.domains.user.controller

import codes.luiz.untappdcqrs.domains.user.services.RegisterUserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("users")
class UserController(
        val registerUserService: RegisterUserService
) {

  @PostMapping("sign_up")
  fun registerUser(
          @RequestParam
          email: String,
          @RequestParam
          password: String
  ): ResponseEntity<Any> {
    return try {
      var user = registerUserService.registerUser(email, password)
      ResponseEntity.status(HttpStatus.CREATED).body(user)
    } catch (exception: Exception) {
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong")
    }
  }

  @GetMapping("test")
  fun getUserInfo(): String {
    return "Success"
  }
}