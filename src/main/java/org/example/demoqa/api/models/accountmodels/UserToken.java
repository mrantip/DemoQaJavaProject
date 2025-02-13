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
public class UserToken{

	@JsonProperty("userName")
	private String userName;

	@JsonProperty("password")
	private String password;



	public String getPassword(){
		return password;
	}

	public String getUserName(){
		return userName;
	}
}