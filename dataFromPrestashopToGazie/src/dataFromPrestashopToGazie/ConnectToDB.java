package dataFromPrestashopToGazie;

import java.sql.*;

public class ConnectToDB {
	
	Connection conn;
	Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;
	
	public ConnectToDB(String percorsoDB, String user, String passwd) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			System.out.println("Connecting to database...");
			
			conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/" + percorsoDB + "?" + "user=" + user + "&password=" + passwd);
			
			System.out.println("Connected");
			
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM ps_address";
            rs = stmt.executeQuery(sql);           
            
            while(rs.next()){
                int id  = rs.getInt("id_address");

                System.out.println("ID: " + id);
            }
            
            rs.close();
            stmt.close();
            conn.close();

		} catch (ClassNotFoundException e) {
			System.out.println("ERRORE class: " + e.getMessage());
		} 
		catch (SQLException e) {
			System.out.println("ERRORE sql: " + e.getMessage());
		}
		finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }

	}
	
	public static void main(String[] args) {
		new ConnectToDB("prestashop", "root", "");
	}
}
