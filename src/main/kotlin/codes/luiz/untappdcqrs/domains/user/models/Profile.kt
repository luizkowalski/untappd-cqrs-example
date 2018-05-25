package codes.luiz.untappdcqrs.domains.user.models

import codes.luiz.untappdcqrs.domains.common.models.BaseEntity
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Index
import javax.persistence.Table

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profiles", indexes = [
  (Index(name = "idx_user_id_on_profile", columnList = "userId"))
])
data class Profile(
        @Column
        var checkinCount: Int = 0,

        @Column
        var uniqueCheckinCount: Int = 0,

        @Column
        var userId: UUID? = null
) : BaseEntity()