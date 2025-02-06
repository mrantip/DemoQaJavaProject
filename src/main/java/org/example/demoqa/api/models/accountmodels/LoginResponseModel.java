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
public class LoginResponseModel{

	@JsonProperty("expires")
	private String expires;

	@JsonProperty("password")
	private String password;

	@JsonProperty("created_date")
	private String createdDate;

	@JsonProperty("isActive")
	private boolean isActive;

	@JsonProperty("userId")
	private String userId;

	@JsonProperty("token")
	private String token;

	@JsonProperty("username")
	private String username;

	public String getExpires(){
		return expires;
	}

	public String getPassword(){
		return password;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public boolean isIsActive(){
		return isActive;
	}

	public String getUserId(){
		return userId;
	}

	public String getToken(){
		return token;
	}

//	public String getUsername(){
//		return username;
//	}
}