package codes.luiz.untappdcqrs.domains.checkin.controllers.params

import com.fasterxml.jackson.annotation.JsonProperty
import com.sun.istack.NotNull
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.util.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Data
@AllArgsConstructor
@NoArgsConstructor
class PostCheckinParams {

  @NotNull
  @JsonProperty("beer_id")
  var beerId: UUID? = null

  var description: String = ""

  @Max(10)
  @Min(1)
  var rating: Int = 5
}