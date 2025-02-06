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
public class TokenModel{

	@JsonProperty("result")
	private String result;

	@JsonProperty("expires")
	private String expires;

	@JsonProperty("token")
	private String token;

	@JsonProperty("status")
	private String status;

	public String getResult(){
		return result;
	}

	public String getExpires(){
		return expires;
	}

	public String getToken(){
		return token;
	}

	public String getStatus(){
		return status;
	}
}