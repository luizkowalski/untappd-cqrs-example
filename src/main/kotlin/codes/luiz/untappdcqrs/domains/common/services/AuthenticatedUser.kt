package codes.luiz.untappdcqrs.domains.common.services

import codes.luiz.untappdcqrs.infrastructure.config.security.ApiUserDetail
import org.springframework.security.core.context.SecurityContextHolder

class AuthenticatedUser {

  companion object {
    fun current(): ApiUserDetail {
      return (SecurityContextHolder.getContext().authentication.principal as ApiUserDetail)
    }
  }

}