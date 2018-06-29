package codes.luiz.untappdcqrs.domains.user.controllers

import codes.luiz.untappdcqrs.domains.common.services.AuthenticatedUser
import codes.luiz.untappdcqrs.domains.user.controllers.params.SignUpParams
import codes.luiz.untappdcqrs.domains.user.dtos.ProfileDTO
import codes.luiz.untappdcqrs.domains.user.repositories.ProfileRepository
import codes.luiz.untappdcqrs.domains.user.services.RegisterUserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("users")
class UserController(
        val registerUserService: RegisterUserService,
        val profileRepository: ProfileRepository
) {

  @PostMapping("sign_up")
  fun registerUser(@Valid @RequestBody params: SignUpParams): ResponseEntity<Any> {
    return try {
      var user = registerUserService.registerUser(params.email!!, params.password!!)
      ResponseEntity.status(HttpStatus.CREATED).body(user)
    } catch (exception: Exception) {
      println(exception)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong")
    }
  }

  @GetMapping(value = ["me", "profile"])
  fun getUserInfo(): ResponseEntity<ProfileDTO> {
    var profile = profileRepository.findByUserId(AuthenticatedUser.current().id()!!)

    var resp = ProfileDTO().apply {
      this.id = profile.userId
      this.email = profile.email
      this.checkinCount = profile.checkinCount
      this.uniqueCheckinCount = profile.uniqueCheckinCount
    }

    return ResponseEntity.ok().body(resp)
  }
}