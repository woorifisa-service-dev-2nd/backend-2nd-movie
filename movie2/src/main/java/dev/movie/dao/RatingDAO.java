package dev.movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.movie.model.Rating;

public class RatingDAO {
	public static void findAllRatings(Connection conn) {
		List<Rating> ratings = new ArrayList<Rating>(); 
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = conn.createStatement();

			String selectQuery = "SELECT r.film_id, r.rating_id, r.grade, f.title FROM rating r JOIN film f ON r.film_id = f.film_id";
			resultSet = statement.executeQuery(selectQuery);

			while (resultSet.next()) {
				int filmId = resultSet.getInt("r.film_id");
				int grade = resultSet.getInt("r.grade");
				int ratingId = resultSet.getInt("r.rating_id");
				String title = resultSet.getString("f.title");

				ratings.add(Rating.builder()
						.ratingId(ratingId)
						.filmId(filmId)
						.grade(grade)
						.title(title)
						.build());
			}
			
			ratings.forEach(r -> System.out.printf("영화 ID: %d, 제목: %s, 평점: %d\n", r.getFilmId(), r.getTitle(), r.getGrade()));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addRating(int filmId, int grade, Connection conn) {
		if (grade >= 0 && grade <= 5) { 
			try {
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO rating (film_id, grade) VALUES (?, ?)");
				pstmt.setInt(1,  filmId);
				pstmt.setInt(2, grade);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
        } else {
            System.out.println("0~5점으로 입력하세요.");
        }
	}
	
	public static void updateRating(int filmId, int grade, Connection conn) {
		if (grade >= 0 && grade <= 5) {
			try {
				PreparedStatement pstmt = conn.prepareStatement("UPDATE rating SET grade = ? WHERE film_id = ?");
				pstmt.setInt(1,  grade);
				pstmt.setInt(2, filmId);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("0~5점으로 입력하세요.");
		}
	}
	
	public static void deleteRating(int filmId, Connection conn) {
		if (!isGradeEmpty(filmId, conn)) {
			try {
				PreparedStatement pstmt = conn.prepareStatement("DELETE FROM rating WHERE film_id = ?");
				pstmt.setInt(1,  filmId);
				pstmt.executeUpdate();
				System.out.println("삭제되었습니다.");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("평점이 없는 영화입니다.");
		}
	}
	
	public static void executeQuery(int filmId, int grade, Connection conn) {
		if (isGradeEmpty(filmId, conn)) {
			addRating(filmId, grade, conn);
			System.out.println("등록되었습니다.");
		} else {
			updateRating(filmId, grade, conn);
			System.out.println("수정되었습니다.");
		}
		
	}
	
	public static boolean isGradeEmpty(int filmId, Connection conn) {

		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM rating WHERE film_id = ?");
			pstmt.setInt(1,  filmId);
			ResultSet rs = pstmt.executeQuery();
			
			return !rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
