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
public class BooksItem{

	@JsonProperty("website")
	private String website;

	@JsonProperty("pages")
	private int pages;

	@JsonProperty("subTitle")
	private String subTitle;

	@JsonProperty("author")
	private String author;

	@JsonProperty("isbn")
	private String isbn;

	@JsonProperty("publisher")
	private String publisher;

	@JsonProperty("description")
	private String description;

	@JsonProperty("title")
	private String title;

	@JsonProperty("publish_date")
	private String publishDate;

}