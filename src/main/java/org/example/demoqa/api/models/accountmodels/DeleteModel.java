package org.example.demoqa.api.models.accountmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteModel{

	@JsonProperty("code")
	private int code;

	@JsonProperty("message")
	private String message;

	public int getCode(){
		return code;
	}

	public String getMessage(){
		return message;
	}
}