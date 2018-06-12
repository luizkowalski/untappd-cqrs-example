package codes.luiz.untappdcqrs.domains.checkin.services

import codes.luiz.untappdcqrs.domains.checkin.events.CheckinDeleted
import codes.luiz.untappdcqrs.domains.checkin.repositories.CheckinRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class DeleteCheckinService(
        val checkinRepository: CheckinRepository,
        val applicationEventPublisher: ApplicationEventPublisher
) {

  // We need to manually register the event since
  // Spring's aggregator will not fire the events when entity is removed
  @Transactional
  fun deleteCheckin(id: UUID) {
    var checkin = checkinRepository.findById(id).orElseThrow { RuntimeException("Checkin not found") }
    checkinRepository.delete(checkin)

    applicationEventPublisher.publishEvent(CheckinDeleted(checkin))
  }
}