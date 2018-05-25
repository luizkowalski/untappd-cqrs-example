package codes.luiz.untappdcqrs.domains.beer.models

import codes.luiz.untappdcqrs.domains.common.models.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "beers")
data class Beer(
        @Column(nullable = false)
        var name: String = "",

        @Column(nullable = false)
        var brewery: String = ""
) : BaseEntity()