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
public class Response{

	@JsonProperty("books")
	private List<BooksItem> books;

	@JsonProperty("userId")
	private String userId;

	@JsonProperty("username")
	private String username;

	public List<BooksItem> getBooks(){
		return books;
	}

	public String getUserId(){
		return userId;
	}

	public String getUsername(){
		return username;
	}
}