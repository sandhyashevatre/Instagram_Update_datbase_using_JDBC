import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBFunction {

    public static void main(String[] args) {
           Connection cnx = null;
           PreparedStatement stmt = null;
           PreparedStatement comt = null;
           Statement likeStmt = null;
           Statement commentStmt = null;
           ResultSet rs = null;
           int i=1;

           try {
               cnx= DriverManager.getConnection("jdbc:mysql://localhost:3306/socialMedia","SandhyaShevatre","sandhya@123");
               System.out.println("Connection Succeeded!");
              
               String queryToGetLikes = "SELECT COUNT(*) AS likecount FROM users,posts,Likes WHERE posts.post_id = Likes.post_id AND Likes.user_id = users.user_id ORDER BY likecount DESC limit 1";
               String queryToGetComments = "SELECT COUNT(*) AS commentcount FROM users,posts,Comment WHERE posts.post_id = Comment.post_id AND Comment.user_id = users.user_id ORDER BY commentcount DESC limit 1";
               String queryLine = "INSERT INTO Likes(like_id, user_id, post_id) VALUES (?,?,?)";
               stmt = cnx.prepareStatement(queryLine);
               likeStmt = cnx.createStatement();
               rs = likeStmt.executeQuery(queryToGetLikes);
               while(rs.next())
            	   i = rs.getInt("likecount");
               
               String commentQuery = "INSERT INTO Comment (comment_id, comment_text, user_id, post_id) VALUES (?,?,?,?)";
               comt = cnx.prepareStatement(commentQuery);

               Scanner sc = new Scanner(System.in);                   

               System.out.println("Enter USER ID for like...");
               int user_id_L = sc.nextInt();

               System.out.println("Enter POST ID for like...");
               int post_id_L = sc.nextInt();               

               i++;
               stmt.setInt(1, i);
               stmt.setInt(2, user_id_L);
               stmt.setInt(3, post_id_L);           

               commentStmt = cnx.createStatement();
               rs = commentStmt.executeQuery(queryToGetComments);
               while(rs.next())
            	   i = rs.getInt("commentcount");
               
               System.out.println("Enter  USER ID for Comment");
               int user_id_C = sc.nextInt();

               System.out.println("Enter POST ID for Comment ");
               int post_id_C = sc.nextInt();
               
               String comment = "";
               System.out.println("Enter Comment ID for Comment");
               comment = sc.nextLine();
               
               i++;
               comt.setInt(1,i);
               comt.setString(2, comment);
               comt.setInt(3,user_id_C);
               comt.setInt(4, post_id_C);
               stmt.executeUpdate();
               comt.executeUpdate();

           } catch(SQLException e) {
               e.printStackTrace();
           }
    }
}