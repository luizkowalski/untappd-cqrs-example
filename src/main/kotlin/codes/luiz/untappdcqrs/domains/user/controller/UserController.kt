package codes.luiz.untappdcqrs.domains.user.controller

import codes.luiz.untappdcqrs.domains.user.services.RegisterUserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UserController(
        val registerUserService: RegisterUserService
) {

    @PostMapping
    fun registerUser(
            @RequestParam
            email: String,
            @RequestParam
            password: String
    ) : ResponseEntity<Any> {
        var user = registerUserService.registerUser(email, password)
        return ResponseEntity.status(HttpStatus.CREATED).body(user)
    }
}