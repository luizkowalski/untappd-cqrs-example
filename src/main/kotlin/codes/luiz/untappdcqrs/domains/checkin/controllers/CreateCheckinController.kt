package codes.luiz.untappdcqrs.domains.checkin.controllers

import codes.luiz.untappdcqrs.domains.checkin.models.Checkin
import codes.luiz.untappdcqrs.domains.checkin.repositories.CheckinRepository
import codes.luiz.untappdcqrs.domains.common.services.AuthenticatedUser
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/checkin")
class CreateCheckinController(
        val checkinRepository: CheckinRepository
) {

  @PostMapping
  fun checkin(
          @RequestParam("beer_id")
          beerId: UUID
  ): ResponseEntity<Any> {

    var checkin = Checkin()
    checkin.beerId = beerId
    checkin.userId = AuthenticatedUser.current().id()

    checkinRepository.save(checkin)
    return ResponseEntity.ok("Created")
  }
}