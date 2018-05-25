package codes.luiz.untappdcqrs.domains.user.listeners

import codes.luiz.untappdcqrs.domains.checkin.events.CheckinCreated
import codes.luiz.untappdcqrs.domains.checkin.repositories.CheckinRepository
import codes.luiz.untappdcqrs.domains.user.repositories.ProfileRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
@Async
class CheckinCreatedListener(
        val profileRepository: ProfileRepository,
        val checkinRepository: CheckinRepository
) {

  @EventListener
  fun increaseCheckinCount(event: CheckinCreated) {
    var profile = profileRepository.findByUserId(event.checkin.userId!!)
    profile.checkinCount += 1
    profile.uniqueCheckinCount = checkinRepository.findDistinctCheckinCount(event.checkin.userId!!)

    profileRepository.save(profile)
  }
}