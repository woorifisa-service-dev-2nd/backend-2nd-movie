package dev.movie.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Rating {
	private int ratingId;
	private int filmId;
	private int grade;
	private String title;
}
