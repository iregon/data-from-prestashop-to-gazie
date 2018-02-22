package dataFromPrestashopToGazie;

import java.sql.*;

public class ConnectToDB {
	
	//Connessione a Prestashop
	Connection connPre;
	Statement stmtPre;
    ResultSet rsPre;
    
    //Connessione a Gazie
  	Connection connGaz;
  	Statement stmtGaz;
    ResultSet rsGaz;
	
	public ConnectToDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			System.out.println("Connecting to database...");
			
			connPre = connectToPrestashop();
			connGaz = connectToGazie();
			
			System.out.println("Connected");
			
            System.out.println("Creating statement...");
            stmtPre = connPre.createStatement();
            stmtGaz = connGaz.createStatement();
            String sql;
            sql = "SELECT * FROM ps_address";
            rsPre = stmtPre.executeQuery(sql);           
            
            while(rsPre.next()){
//                int id = rsPre.getInt("id_address");

//                System.out.println("ID: " + id);
            }
            
            rsPre.close();
            stmtPre.close();
            connPre.close();
            
//            rsGaz.close();
            stmtGaz.close();
            connGaz.close();

		} catch (ClassNotFoundException e) {
			System.out.println("ERRORE class: " + e.getMessage());
		} 
		catch (SQLException e) {
			System.out.println("ERRORE sql: " + e.getMessage());
		}
		finally{
		      //finally block used to close resources
		      try{
		         if(stmtPre!=null)
		            stmtPre.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(connPre!=null)
		            connPre.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }

	}
	
	private Connection connectToPrestashop() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/prestashop?user=root&password=");
		} catch (SQLException e) {
			return null;
		}
	}
	
	private Connection connectToGazie() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/gazie?user=root&password=");
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		new ConnectToDB();
	}
}
