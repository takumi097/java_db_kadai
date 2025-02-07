package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Scores_Chapter10 {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement statement = null;
		
		
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					"igariTKM-0907"
					);
			
			System.out.println("データベース接続成功 : " + con);
			System.out.println("レコード更新を実行します");
			
			statement = con.createStatement();
			String insertsql = "UPDATE score SET score_math = 95, score_english = 80 WHERE id = 5;";
			
			int rowCnt = statement.executeUpdate(insertsql);
			System.out.println( rowCnt + "件のレコードが更新されました");
		
		System.out.println("数学・英語の点数が高い順に並べ替えました");
		String selectsql = "SELECT id, name, score_math, score_english FROM score ORDER BY score_math DESC, score_english DESC;";
		
		ResultSet result = statement.executeQuery(selectsql);
		
		while(result.next()) {
			int id = result.getInt("id");
			String name = result.getString("name");
			int score_math = result.getInt("score_math");
			int score_english = result.getInt("score_english");
			
			System.out.println( result.getRow() + "件目 : 生徒ID = " + id + "/ 氏名 = " + name + "/ 数学 = " + score_math + "/ 英語 = " + score_english );
		}
		
		
		} catch(SQLException e) {
			System.out.println("エラー発生 : " + e.getMessage()); 
		}
		if (con != null) {
			try { con.close(); } catch(SQLException ignre) {}
		}
		if (statement != null) {
        	try { statement.close(); } catch (SQLException ignore) {}
        }
        if( con != null ) {
            try { con.close(); } catch(SQLException ignore) {}
        }

	}
}

