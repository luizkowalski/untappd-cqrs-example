package codes.luiz.untappdcqrs.domains.user.controllers.params

import java.io.Serializable
import java.util.UUID
import lombok.Data

data class ProfileParam(
        var id: UUID? = null,
        var email: String = "",
        var checkinCount: Int = 0,
        var uniqueCheckinCount: Int = 0
) : Serializable
