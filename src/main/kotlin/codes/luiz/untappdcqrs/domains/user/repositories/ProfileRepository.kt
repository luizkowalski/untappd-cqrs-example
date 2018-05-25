package codes.luiz.untappdcqrs.domains.user.repositories

import codes.luiz.untappdcqrs.domains.user.models.Profile
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProfileRepository : CrudRepository<Profile, UUID> {
  fun findByUserId(uuid: UUID): Profile
}