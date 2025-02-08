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
public class BookDeleteResultModel{

	@JsonProperty("message")
	private String message;

	@JsonProperty("userId")
	private String userId;

	public String getMessage(){
		return message;
	}

	public String getUserId(){
		return userId;
	}
}