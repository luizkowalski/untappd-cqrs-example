package codes.luiz.untappdcqrs.domains.checkin.models

import codes.luiz.untappdcqrs.domains.checkin.events.CheckinCreated
import codes.luiz.untappdcqrs.domains.checkin.events.CheckinDeleted
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.domain.AbstractAggregateRoot
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "checkins", indexes = [
  Index(name = "idx_user_id_on_checkin", columnList = "userId"),
  Index(name = "idx_beer_id_on_checkin", columnList = "beerId")
])
@EntityListeners(AuditingEntityListener::class)
data class Checkin(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(
                name = "UUID",
                strategy = "org.hibernate.id.UUIDGenerator")
        var id: UUID? = null,

        @CreatedDate
        var createdAt: LocalDateTime? = null,

        @Column(nullable = false)
        var beerId: UUID? = null,

        @Column(nullable = false)
        var userId: UUID? = null,

        @Column(columnDefinition = "TEXT")
        var description: String = "",

        @Column
        var rate: Int = 5
) : AbstractAggregateRoot<Checkin>() {

  @PrePersist
  fun created() {
    registerEvent(CheckinCreated(this))
  }
}