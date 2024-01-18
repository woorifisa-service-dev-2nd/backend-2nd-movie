package com.movie.controller;

import java.sql.Connection;
import java.util.Scanner;

import dev.movie.dao.FilmDAO;
import dev.movie.dao.RatingDAO;
import dev.movie.util.DBUtil;

public class FilmController {
	String path = "resources/jdbc.properties";
	final String DB_URL = "DB_URL";
	final String DATABASE_NAME = "DATABASE_NAME";
	final String USER = "USER";
	final String PASSWORD = "PASSWORD";
	Connection c = DBUtil.getConnection(path, DB_URL, DATABASE_NAME, USER, PASSWORD);
	
	
	
	public void search() {
		
		Scanner scanner = new Scanner(System.in);
		
        System.out.println("원하시는 검색을 골라주세요 ");
        System.out.println("1.키워드 검색 2.카테고리 검색 3.전체 출력");
        String num = null;
		
        while (true) {
        	num = scanner.nextLine();
            if (num.matches("[1-3]")) {
                break;
            } else {
                System.out.println("1부터 3 사이의 번호를 입력해주세요.");
            }
        }
        
        if(num.equals("1")) 
        	FilmDAO.filmKeyword(c);
        
        else if(num.equals("2")) 
        	FilmDAO.filmCategory(c);
        
        else if(num.equals("3")) 
        	FilmDAO.filmAll(c);
        	
	}
	
	///
	public void rating() {
		
		
		
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("원하시는 번호를 골라주세요 ");
        System.out.println("1.평점 조회 2.평점 추가 3.평점 삭제");
        String num = scanner.nextLine();
        
        //1
        if(num.equals("1")) {
        	RatingDAO.findAllRatings(c);
        	System.out.println();
        }
        /// 2
		
        else if(num.equals("2")) {
        	FilmDAO.filmKeyword(c);
        	
			System.out.println("영화Id 입력해주세요 ");
			String input1 = null;
	        while (true) {
	            input1 = scanner.nextLine();
	        	
	            if (input1.matches("(?:[1-9]|[1-9]\\d{1,2}|1000)")) {
	                break;
	            } else {
	                System.out.println("1부터 1000 사이의 번호를 입력해주세요.");
	            }
	        }
	        
	        System.out.println("평점을 입력해주세요 ");
	        String input2;
	        while (true) {
	        	input2 = scanner.nextLine();
	        	
	            if (input2.matches("[0-5]")) {
	                break;
	            } else {
	                System.out.println("0부터 5 사이의 번호를 입력해주세요.");
	            }
	        }
	        
	        
	        
	        int filmId = Integer.parseInt(input1);
	        int rating = Integer.parseInt(input2);
			
			RatingDAO.executeQuery(filmId, rating, c);
			System.out.println();
        }
        
        
        ////3
        else if(num.equals("3")) {
    		System.out.println("영화Id 입력해주세요 ");
    		String input1 = null;
            while (true) {
                input1 = scanner.nextLine();
            	
                if (input1.matches("(?:[1-9]|[1-9]\\d{1,2}|1000)")) {
                    break;
                } else {
                    System.out.println("1부터 1000 사이의 번호를 입력해주세요.");
                }
            }
            
   
            
            int filmId = Integer.parseInt(input1);
           
    		
    		RatingDAO.deleteRating(filmId, c);
    		System.out.println();
            }
	}

}
