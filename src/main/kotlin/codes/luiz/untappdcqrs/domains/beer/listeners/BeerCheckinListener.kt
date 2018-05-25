package codes.luiz.untappdcqrs.domains.beer.listeners

import codes.luiz.untappdcqrs.domains.beer.repositories.BeerRepository
import codes.luiz.untappdcqrs.domains.checkin.events.CheckinCreated
import codes.luiz.untappdcqrs.domains.checkin.repositories.CheckinRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
@Async
class BeerCheckinListener(
        val checkinRepository: CheckinRepository,
        val beerRepository: BeerRepository
) {

  var logger: Logger = LoggerFactory.getLogger(BeerCheckinListener::class.java)

  @EventListener
  fun beerCheckedIn(event: CheckinCreated) {
    logger.info("Updating beer rating")
    var beerId = event.checkin.beerId
    var averageRating = checkinRepository.findAverageRatingFromBeer(beerId!!)

    var beer = beerRepository.findById(beerId).get()
    beer.averageRating = BigDecimal(averageRating.toString())
    beer.totalCheckin += 1

    beerRepository.save(beer)
  }
}