package org.example.demoqa.api.models.bookstoremodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteIsbnUserModel{

	@JsonProperty("isbn")
	private String isbn;

	@JsonProperty("userId")
	private String userId;

}