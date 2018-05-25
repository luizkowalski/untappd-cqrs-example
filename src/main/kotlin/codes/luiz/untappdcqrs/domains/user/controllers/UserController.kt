package codes.luiz.untappdcqrs.domains.user.controllers

import codes.luiz.untappdcqrs.domains.common.services.AuthenticatedUser
import codes.luiz.untappdcqrs.domains.user.controllers.params.ProfileParam
import codes.luiz.untappdcqrs.domains.user.repositories.ProfileRepository
import codes.luiz.untappdcqrs.domains.user.repositories.UserRepository
import codes.luiz.untappdcqrs.domains.user.services.RegisterUserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("users")
class UserController(
        val registerUserService: RegisterUserService,
        val profileRepository: ProfileRepository
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

  @GetMapping("me")
  fun getUserInfo(): ResponseEntity<ProfileParam> {
    var profile = profileRepository.findByUserId(AuthenticatedUser.current().id()!!)

    var resp = ProfileParam()
    resp.id = AuthenticatedUser.current().id()
    resp.email = AuthenticatedUser.current().username

    resp.checkinCount = profile.checkinCount
    resp.uniqueCheckinCount = profile.uniqueCheckinCount

    return ResponseEntity.ok().body(resp)
  }


}