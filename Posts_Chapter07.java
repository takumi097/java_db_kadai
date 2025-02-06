package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Posts_Chapter07 {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement preparedStatement = null;
        Statement statement = null;
        
        
        String[][] userpost = {
        		{"1003", "2023-02-08", "昨日は徹夜でした・・", "13"},
        		{"1002", "2023-02-08", "お疲れ様です！", "12"},
        		{"1003", "2023-02-09", "今日も頑張ります", "18"},
        		{"1001", "2023-02-09", "無理は禁物ですよ！", "17"},
        		{"1002", "2023-02-09", "明日から連休です", "20"},
        };

        
        
        try {
            // データベースに接続
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost/challenge_java",
                "root",
                "igariTKM-0907"
            );

            System.out.println("データベース接続成功 : " + con);

            System.out.println("レコード追加を実行します");
            

          String insertsql = "INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES (?, ?, ?, ?);";
          preparedStatement = con.prepareStatement(insertsql);
          
          
            
            int rowCnt = 0;
            for(int i =0; i < userpost.length; i++ ) {
            	preparedStatement.setString(1, userpost[i][0]);
            	preparedStatement.setString(2, userpost[i][1]);
            	preparedStatement.setString(3, userpost[i][2]);
            	preparedStatement.setString(4, userpost[i][3]);
            	rowCnt += preparedStatement.executeUpdate();
            }
            
            System.out.println( rowCnt + "件のレコードが追加されました");
            System.out.println("ユーザーIDが1002のレコードを検索しました");
            
           String selectsql = "SELECT * FROM posts WHERE user_id = 1002;";
           statement = con.createStatement();
           ResultSet result = statement.executeQuery(selectsql);
           
           while(result.next()) {
        	   String posted_at = result.getString("posted_at");
        	   String post_content = result.getString("post_content");
        	   int likes = result.getInt("likes");
        	   System.out.println( result.getRow() + "件目 : 投稿日 = " + posted_at + "/ 投稿内容 = " + post_content + "/ いいね数 = " + likes);
        	   
           }
        
        
        
        
        
        
        } catch(SQLException e) {
            System.out.println("エラー発生：" + e.getMessage());
        } finally {
           
        	
        	
            if( preparedStatement != null ) {
                try { preparedStatement.close(); } catch(SQLException ignore) {}
            }
            if (statement != null) {
            	try { statement.close(); } catch (SQLException ignore) {}
            }
            if( con != null ) {
                try { con.close(); } catch(SQLException ignore) {}
            }
        }
    }










	}


