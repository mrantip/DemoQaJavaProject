package org.example.demoqa.api.models.bookstoremodels;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddListOfBooksModel{

	@JsonProperty("collectionOfIsbns")
	private List<CollectionOfIsbnsItem> collectionOfIsbns;

	@JsonProperty("userId")
	private String userId;

	public List<CollectionOfIsbnsItem> getCollectionOfIsbns(){
		return collectionOfIsbns;
	}

	public String getUserId(){
		return userId;
	}

	public void setCollectionOfIsbns(List<CollectionOfIsbnsItem> collectionOfIsbns) {
		this.collectionOfIsbns = collectionOfIsbns;
	}
}