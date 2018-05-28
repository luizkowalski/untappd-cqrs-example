package codes.luiz.untappdcqrs.domains.checkin.events

import codes.luiz.untappdcqrs.domains.checkin.models.Checkin

data class CheckinDeleted(
        val checkin: Checkin
)