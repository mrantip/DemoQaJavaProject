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
public class BooksIsbnModel{

	@JsonProperty("books")
	private List<BooksItem> books;

	public List<BooksItem> getBooks(){
		return books;
	}
}