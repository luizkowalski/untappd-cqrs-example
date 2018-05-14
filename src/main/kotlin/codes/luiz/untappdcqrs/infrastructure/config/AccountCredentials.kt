package codes.luiz.untappdcqrs.infrastructure.config

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import lombok.ToString
import java.io.Serializable

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
class AccountCredentials : Serializable {
    var email: String? = null
    var password: String? = null
}