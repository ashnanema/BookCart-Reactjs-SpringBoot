package com.bookcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDto {
	
	private Integer isbnNo;
	private String title;
	private String author;
	private double price;
	private int noOfCopies;
	private String image;
	
}
