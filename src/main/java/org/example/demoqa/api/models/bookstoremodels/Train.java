package org.example.demoqa.api.models.bookstoremodels;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Train{

	@JsonProperty("books")
	private List<BooksItem> books;

	public List<BooksItem> getBooks(){
		return books;
	}
}