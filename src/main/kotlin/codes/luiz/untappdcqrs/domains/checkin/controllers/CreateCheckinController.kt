package codes.luiz.untappdcqrs.domains.checkin.controllers

import codes.luiz.untappdcqrs.domains.beer.repositories.BeerRepository
import codes.luiz.untappdcqrs.domains.checkin.controllers.params.PostCheckinParams
import codes.luiz.untappdcqrs.domains.checkin.models.Checkin
import codes.luiz.untappdcqrs.domains.checkin.repositories.CheckinRepository
import codes.luiz.untappdcqrs.domains.common.services.AuthenticatedUser
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/checkin")
class CreateCheckinController(
        val checkinRepository: CheckinRepository,
        val beerRepository: BeerRepository) {

  @PostMapping
  fun checkin(@Validated @RequestBody params: PostCheckinParams): ResponseEntity<Any> {

    var checkin = Checkin()
    checkin.beerId = beerRepository.findById(params.beerId!!).orElseThrow { RuntimeException("Beer not found") }.id
    checkin.userId = AuthenticatedUser.current().id()
    checkin.description = params.description
    checkin.rate = params.rating

    checkinRepository.save(checkin)
    return ResponseEntity.status(HttpStatus.CREATED).body("")
  }
}