package codes.luiz.untappdcqrs.domains.user.controllers.params

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.io.Serializable
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull

@Data
@AllArgsConstructor
@NoArgsConstructor
class SignUpParams : Serializable {

  @NotNull
  @Email
  var email: String? = null

  @NotNull
  var password: String? = null
}