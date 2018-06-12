package codes.luiz.untappdcqrs.domains.checkin.services

import codes.luiz.untappdcqrs.domains.checkin.controllers.params.CreateCheckinParams
import codes.luiz.untappdcqrs.domains.checkin.models.Checkin
import codes.luiz.untappdcqrs.domains.checkin.repositories.CheckinRepository
import codes.luiz.untappdcqrs.domains.common.services.AuthenticatedUser
import org.springframework.stereotype.Service

@Service
class CreateCheckinService(
        val checkinRepository: CheckinRepository
) {

  fun createCheckin(params: CreateCheckinParams): Checkin {
    var checkin = Checkin().apply {
      this.beerId = params.beerId
      this.description = params.description
      this.rate = params.rating
      this.userId = AuthenticatedUser.current().id()
    }

    return checkinRepository.save(checkin)
  }
}