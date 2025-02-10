package org.example.demoqa.api.models.bookstoremodels;

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
public class BookModel{

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