package dev.movie.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Film {
	private int film_Id;
	private String title;
	private String description;
	private int release_Year;
	private int length;
	private String rating;
	private int grade;
	

}
