package org.example.demoqa.api.models.bookstoremodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CollectionOfIsbnsItem{

	@JsonProperty("isbn")
	private String isbn;

}