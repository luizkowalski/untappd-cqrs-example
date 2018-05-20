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

    @PostMapping(path = ["sign_up"])
    fun registerUser(
            @RequestParam
            email: String,
            @RequestParam
            password: String
    ): ResponseEntity<Any> {
        var user = registerUserService.registerUser(email, password)
        return ResponseEntity.status(HttpStatus.CREATED).body(user)
    }


    @GetMapping("test")
    fun getUserInfo(): String {
        return "Success"
    }
}