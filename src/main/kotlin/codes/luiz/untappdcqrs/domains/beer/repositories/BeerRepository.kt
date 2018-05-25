package codes.luiz.untappdcqrs.domains.beer.repositories

import codes.luiz.untappdcqrs.domains.beer.models.Beer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BeerRepository : CrudRepository<Beer, UUID>