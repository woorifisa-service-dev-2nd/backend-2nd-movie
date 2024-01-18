package dev.movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dev.movie.model.Film;
import dev.movie.util.DBUtil;




public class FilmDAO {
	
	
	public static void filmCategory(Connection c) {
	
		
		Scanner scanner = new Scanner(System.in);
        System.out.println("영화 카테고리 번호를 입력하세요 ");
        System.out.println("(1.action 2.animation 3.children 4.classics 5.comedy)");
        String category = null;
		
        while (true) {
            category = scanner.nextLine();
            if (category.matches("[1-5]")) {
                break;
            } else {
                System.out.println("1부터 5 사이의 번호를 입력해주세요.");
            }
        }
		
		List<Film> films = new ArrayList<>();
		Connection connection = c;
		
      
        String sql = "select a.film_id,a.title,a.description,a.rating,a.release_year,a.length,c.grade from film a \n"
        		+ "join film_category b\n"
        		+ "on a.film_id = b.film_id\n"
        		+ "left join rating c \n"
        		+ "on a.film_id = c.film_id\n"
        		+ "where b.category_id = ?\n"
        		+ "limit 10;";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        	
        	pstmt.setString(1, category);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	Film film = new Film();
               //데이터 설정
            	film.setFilm_Id(rs.getInt("film_id"));
            	film.setTitle(rs.getString("title"));
            	film.setDescription(rs.getString("description"));
            	film.setRating(rs.getString("rating"));
            	film.setRelease_Year(rs.getInt("release_year"));
            	film.setLength(rs.getInt("length"));
            	film.setGrade(rs.getInt("grade"));
            	
                films.add(film);
            }
            if(films.isEmpty()) {
            	System.out.println("검색 결과 - 해당하는 영화가 없습니다.");
            }
            else {
	            for (Film film : films) {
	            	System.out.println("영화Id: " + film.getFilm_Id());
	                System.out.println("제목: " + film.getTitle());
	                System.out.println("설명: " + film.getDescription());
	                System.out.println("등급: " + film.getRating());
	                System.out.println("출시연도: " + film.getRelease_Year());
	                System.out.println("길이: " + film.getLength() + "분");
	                System.out.println("평점: " + film.getGrade());
	                System.out.println();
	            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	
	/////////////////  키워드 검색
	public static void filmKeyword(Connection c) {
		Scanner scanner = new Scanner(System.in);
		String keyword = null;
		while (true) {
	        System.out.println("관련 키워드를 입력하세요: ");
	        keyword = scanner.nextLine();
	        // 키워드가 비어있지 않은 경우 반복문을 빠져나옴
	        if (keyword != null && !keyword.trim().isEmpty()) {
	            break;
	        } else {
	            System.out.println("유효하지 않은 키워드입니다. 다시 입력해주세요.");
	        }
	    }
		
		List<Film> films = new ArrayList<>();
		Connection connection = c;
		
		String sql = "select a.film_id,a.title,a.description,a.rating,a.release_year,a.length,c.grade from film a \n"
        		+ "left join rating c \n"
        		+ "on a.film_id = c.film_id\n"
        		+ "WHERE title LIKE ? \n"
        		+ "OR description LIKE ? \n"
        		+ "limit 10;";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        	pstmt.setString(1, "%" + keyword + "%");
        	pstmt.setString(2, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	Film film = new Film();
               //데이터 설정
            	film.setFilm_Id(rs.getInt("film_id"));
            	film.setTitle(rs.getString("title"));
            	film.setDescription(rs.getString("description"));
            	film.setRating(rs.getString("rating"));
            	film.setRelease_Year(rs.getInt("release_year"));
            	film.setLength(rs.getInt("length"));
            	film.setGrade(rs.getInt("grade"));
            	
                films.add(film);
            }
            if(films.isEmpty()) {
            	System.out.println("검색 결과 - 해당하는 영화가 없습니다.");
            }else {
	            for (Film film : films) {
	            	System.out.println("영화Id: " + film.getFilm_Id());
	                System.out.println("제목: " + film.getTitle());
	                System.out.println("설명: " + film.getDescription());
	                System.out.println("등급: " + film.getRating());
	                System.out.println("출시연도: " + film.getRelease_Year());
	                System.out.println("길이: " + film.getLength() + "분");
	                System.out.println("평점: " + film.getGrade());
	                System.out.println();
	            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	
	///////////////  전체 출력
	public static void filmAll(Connection c) {
		
		
        System.out.println("영화 전체 조회");
		
		List<Film> films = new ArrayList<>();
		Connection connection = c;
		
       
       String sql = "select a.film_id,a.title,a.description,a.rating,a.release_year,a.length,c.grade from film a \n"
       		+ "left join rating c \n"
       		+ "on a.film_id = c.film_id\n"
       		+ "limit 10;";
       
       try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
           ResultSet rs = pstmt.executeQuery();
           while (rs.next()) {
           	Film film = new Film();
              //데이터 설정
           	film.setFilm_Id(rs.getInt("film_id"));
           	film.setTitle(rs.getString("title"));
           	film.setDescription(rs.getString("description"));
           	film.setRating(rs.getString("rating"));
           	film.setRelease_Year(rs.getInt("release_year"));
           	film.setLength(rs.getInt("length"));
           	film.setGrade(rs.getInt("grade"));
           	
               films.add(film);
           }
           for (Film film : films) {
           	System.out.println("영화Id: " + film.getFilm_Id());
               System.out.println("제목: " + film.getTitle());
               System.out.println("설명: " + film.getDescription());
               System.out.println("등급: " + film.getRating());
               System.out.println("출시연도: " + film.getRelease_Year());
               System.out.println("길이: " + film.getLength() + "분");
               System.out.println("평점: " + film.getGrade());
               System.out.println();
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }

}
