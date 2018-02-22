package dataFromPrestashopToGazie;

import java.io.*;
import java.sql.*;
import java.util.*;

public class ConnectToDB {
	
	//Query di selezione dei clienti
	private final String QUERYGETCUSTOMERS = "SELECT * FROM ps_address";
	
	//Connessione a Prestashop
	private Connection connPre;
	private Statement stmtPre;
	private ResultSet rsPre;
    
    //Connessione a Gazie
	private Connection connGaz;
	private Statement stmtGaz;
	private ResultSet rsGaz;
    
    //Clienti
	private ArrayList<PrestashopCostumer> costumers = new ArrayList<PrestashopCostumer>();
	
	public ConnectToDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			System.out.println("Connecting to databases...");
			
			connPre = connectToPrestashop();
			connGaz = connectToGazie();
			
			System.out.println("Connected");
			
            System.out.println("Creating statements...");
            stmtPre = connPre.createStatement();
            stmtGaz = connGaz.createStatement();
            
            //Selezione dei clienti
            rsPre = stmtPre.executeQuery(QUERYGETCUSTOMERS);   
            
            System.out.println("Statements created");
            
            //Stringa contenente i dati letti dal DB di Prestashop
//            String str = "";
            getCostumersFromPrestashop(rsPre);  
            
//            BufferedWriter writer = new BufferedWriter(new FileWriter("temp.csv"));
//            writer.write(str);
//            writer.close();
            
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
	
	private void getCostumersFromPrestashop(ResultSet rs) {
		try {
			while(rs.next()){
			    getCostumerFromPrestashop(rs);
			}
			System.out.println("Num costumers: " + costumers.size());    
		} catch (SQLException e) {
			System.out.println("ERRORE getCostumersFromPrestashop: " + e.getMessage());
		}
	}
	
	private void getCostumerFromPrestashop(ResultSet rs) {
		PrestashopCostumer pc;
		try {
			pc = new PrestashopCostumer(rsPre.getString("firstname"), 
					rsPre.getString("lastname"), 
					rsPre.getString("address1"), 
					rsPre.getString("postcode"), 
					rsPre.getString("city"), 
					rsPre.getString("phone"));
			costumers.add(pc);
		} catch (SQLException e) {
			System.out.println("ERRORE getInformationFromPrestashop(): " + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new ConnectToDB();
	}
}
