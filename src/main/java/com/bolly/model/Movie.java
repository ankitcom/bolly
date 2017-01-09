package com.bolly.model;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author ankit.bhatnagar
 *
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(Include.NON_NULL)
public class Movie {
	private int id;
	private String name;
	private String onlineStreamLink;
	private byte rating;
	private String review;
	private String writer;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date releaseDate;
	private Short releaseYear;
	private Set<Byte> typeIds;
	private Person director;
	private Set<Person> actors;
	private String imageUrl;
}
