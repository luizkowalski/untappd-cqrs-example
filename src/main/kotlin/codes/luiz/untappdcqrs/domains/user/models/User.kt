package codes.luiz.untappdcqrs.domains.user.models

import codes.luiz.untappdcqrs.domains.common.models.BaseEntity
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Index
import javax.persistence.Table

@Entity
@Table(name = "users", indexes = [
  Index(name = "email_idx", columnList = "email", unique = true)
])
data class User(
        @Column(nullable = false, length = 50)
        var email: String = "",

        @Column(nullable = false)
        @JsonIgnore
        var password: String = ""

) : BaseEntity()