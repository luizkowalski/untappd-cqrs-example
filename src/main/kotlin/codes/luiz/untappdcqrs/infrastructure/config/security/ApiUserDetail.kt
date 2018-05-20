package codes.luiz.untappdcqrs.infrastructure.config.security

import codes.luiz.untappdcqrs.domains.user.models.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class ApiUserDetail(val user: User) : UserDetails {
    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return user.email
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true;
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return Collections.emptyList<GrantedAuthority>()
    }
}