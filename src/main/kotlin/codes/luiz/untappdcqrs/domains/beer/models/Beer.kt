package codes.luiz.untappdcqrs.domains.beer.models

import codes.luiz.untappdcqrs.domains.common.models.BaseEntity
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "beers")
data class Beer(
        @Column(nullable = false)
        var name: String = "",

        @Column(nullable = false)
        var brewery: String = "",

        @Column(scale = 2, precision = 10)
        var averageRating: BigDecimal = BigDecimal(5.00),

        @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
        var totalCheckin: Int = 0
) : BaseEntity()