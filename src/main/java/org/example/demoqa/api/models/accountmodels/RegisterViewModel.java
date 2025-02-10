package org.example.demoqa.api.models.accountmodels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterViewModel {

	@JsonProperty("books")
	private List<BooksItem> books;

	@JsonProperty("userID")
	private String userID;

	@JsonProperty("username")
	private String username;

}