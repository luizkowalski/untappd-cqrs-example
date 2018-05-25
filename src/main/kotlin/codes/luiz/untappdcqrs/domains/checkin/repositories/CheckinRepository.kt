package codes.luiz.untappdcqrs.domains.checkin.repositories

import codes.luiz.untappdcqrs.domains.checkin.models.Checkin
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CheckinRepository : CrudRepository<Checkin, UUID> {

  @Query("select count(distinct(beer_id)) from checkins where user_id = ?1", nativeQuery = true)
  fun findDistinctCheckinCount(userId: UUID): Int

  @Query("SELECT AVG(rate) FROM checkins where beer_id = ?1", nativeQuery = true)
  fun findAverageRatingFromBeer(beerId: UUID): Float
}