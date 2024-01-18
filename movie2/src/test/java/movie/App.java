package movie;

import java.sql.Connection;

import dev.movie.dao.RatingDAO;
import dev.movie.util.DBUtil;

public class App {

	public static void main(String[] args) {
		
		String path = "src/test/resources/jdbc.properties";
		final String DB_URL = "DB_URL";
		final String DATABASE_NAME = "DATABASE_NAME";
		final String USER = "USER";
		final String PASSWORD = "PASSWORD";
		
		Connection conn = DBUtil.getConnection(path, DB_URL, DATABASE_NAME, USER, PASSWORD);
		
		RatingDAO.findAllRatings(conn);
		
		RatingDAO.deleteRating(1, conn);
//		RatingDAO.deleteRating(9, conn);
//		RatingDAO.deleteRating(14, conn);
//		RatingDAO.deleteRating(20, conn);
//		RatingDAO.deleteRating(25, conn);
//		RatingDAO.deleteRating(100, conn);
		
		RatingDAO.executeQuery(0, 0, conn);
//		
//		RatingDAO.findAllRatings(conn);
		
		
	}

}
