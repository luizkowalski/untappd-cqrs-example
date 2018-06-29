package codes.luiz.untappdcqrs.domains.user.dtos

import java.io.Serializable
import java.util.*

data class ProfileDTO(
        var id: UUID? = null,
        var email: String = "",
        var checkinCount: Int = 0,
        var uniqueCheckinCount: Int = 0
) : Serializable
