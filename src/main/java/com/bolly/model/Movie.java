package com.bolly.model;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * @author ankit.bhatnagar
 *
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
	private int id;
	private String name;
	private String onlineStreamLink;
	private int rating;
	private String review;
	private String writer;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date releaseDate;
	private int releaseYear;
	private List<Integer> typeIds;
	private Person director;
	private List<Person> actors;
}
