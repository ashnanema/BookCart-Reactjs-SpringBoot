package com.bookcart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "Book")
public class Book {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "isbnNo")
    private Integer isbnNo;

	@Column(name="title")
	private String title;
	
	@Column(name="author")
	private String author;
	
	@Column(name="price")
	private double price;
	
	@Column(name="noOfCopies")
	private int noOfCopies;
	
	@Column(name="image")
	private String image;
	

}
