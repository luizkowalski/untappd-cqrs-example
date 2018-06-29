package codes.luiz.untappdcqrs.domains.checkin.controllers

import codes.luiz.untappdcqrs.domains.beer.repositories.BeerRepository
import codes.luiz.untappdcqrs.domains.checkin.controllers.params.CreateCheckinParams
import codes.luiz.untappdcqrs.domains.checkin.services.CreateCheckinService
import codes.luiz.untappdcqrs.domains.checkin.services.DeleteCheckinService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/checkin")
class CheckinController(
        val createCheckinService: CreateCheckinService,
        val deleteCheckinService: DeleteCheckinService,
        val beerRepository: BeerRepository) {

  @PostMapping
  fun checkin(@Valid @RequestBody params: CreateCheckinParams): ResponseEntity<Any> {
    beerExists(params.beerId!!)
    var checkin = createCheckinService.createCheckin(params)
    return ResponseEntity.status(HttpStatus.CREATED).body(checkin)
  }

  @DeleteMapping(value = "/{id}")
  fun deleteCheckin(@PathVariable("id") checkinId: UUID): ResponseEntity<String> {
    deleteCheckinService.deleteCheckin(checkinId)
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("")
  }


  // Validates the existence of a beer
  // If the beer exists, then do nothing, otherwise, throw an exception
  private fun beerExists(beerId: UUID) {
    beerRepository.findById(beerId).orElseThrow { RuntimeException("Beer not found") }
  }
}