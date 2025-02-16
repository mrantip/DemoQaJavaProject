package org.example.demoqa.api.models.bookstoremodels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BooksModel{

//	@JsonProperty("books")
//	private List<BooksItem> books;

	@JsonProperty("books")
	private List<BookModel> books;

	public List<BookModel> getBooks(){
		return books;
	}

}