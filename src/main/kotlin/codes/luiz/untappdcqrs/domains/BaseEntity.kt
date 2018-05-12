package codes.luiz.untappdcqrs.domains

import lombok.Data
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*
import javax.persistence.EntityListeners
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(
                name = "UUID",
                strategy = "org.hibernate.id.UUIDGenerator")
        var id: UUID? = null,

        @CreatedDate
        var createdAt: LocalDateTime? = null,

        @LastModifiedDate
        var updatedAt: LocalDateTime? = null
)