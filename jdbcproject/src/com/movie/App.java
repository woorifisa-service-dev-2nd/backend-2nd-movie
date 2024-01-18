package com.movie;

import java.util.Scanner;

import com.movie.controller.FilmController;

public class App {

	public static void main(String[] args) {
		
		FilmController FC = new FilmController();
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("1.검색기능 2.평점기능 3.종료 ");
			String input = null;
			while (true) {
	            input = scanner.nextLine();
	        	
	            if (input.matches("[1-3]")) {
	                break;
	            } else {
	                System.out.println("1부터 3사이의 번호를 입력해주세요.");
	            }
	        }
			
			if(input.equals("1"))
				FC.search();
			else if(input.equals("2"))
				FC.rating();
			else if(input.equals("3")) {
				System.out.println("종료 되었습니다.");
				break;
			}
		}
	}

}

