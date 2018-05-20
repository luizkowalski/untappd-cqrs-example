package codes.luiz.untappdcqrs.domains.user.repositories

import codes.luiz.untappdcqrs.domains.user.models.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User, UUID> {
    fun findByEmail(email: String): Optional<User>
}