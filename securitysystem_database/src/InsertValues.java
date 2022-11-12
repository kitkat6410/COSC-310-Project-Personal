import java.sql.*;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class InsertValues
{
	/**
	 * Connection to database
	 */
	private Connection con;
	

	/**
	 * Main method is only used for convenience.  Use JUnit test file to verify your answer.
	 * 
	 * @param args
	 * 		none expected
	 * @throws SQLException
	 * 		if a database error occurs
	 * @throws ParseException 
	 * 		during date conversions
	 */	
	public static void main(String[] args) throws SQLException, ParseException
	{
		InsertValues app = new InsertValues();
		
		app.connect();
		app.init();
						
		
      
        
        app.close();        		
	}
	public PreparedStatement addLog(String numDoor, Boolean access, String accessLevel, String accessType) throws SQLException
		{
			// TODO: Use a PreparedStatement and return it at the end of the method
			String SQL = ("INSERT INTO door VALUES (?, ?, ?, ?, null)");
			PreparedStatement pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, numDoor);
			pstmt.setBoolean(2, access);
			pstmt.setString(3, accessLevel);
			pstmt.setString(4, accessType);
			pstmt.execute();
			return pstmt;
		}
        

	/**
	 * Makes a connection to the database and returns connection to caller.
	 * 
	 * @return
	 * 		connection
	 * @throws SQLException
	 * 		if an error occurs
	 */
	public Connection connect() throws SQLException
	{
	    // TODO: Fill in your connection information
		String url = "jdbc:mysql://localhost/mydb"; 
		String uid = "testuser";        
		String pw = "304testpw";

		System.out.println("Connecting to database.");
		// Note: Must assign connection to instance variable as well as returning it back to the caller
		con = DriverManager.getConnection(url, uid, pw);
		return con;		                       
	}
	
	/**
	 * Closes connection to database.
	 */
	public void close()
	{
		System.out.println("Closing database connection.");
		try
		{
			if (con != null)
	            con.close();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
		
	/**
	 * Creates the database and initializes the data.
	 */
	public void init()
	{
	    String fileName = "bin/data/office.ddl";
	    Scanner scanner = null;
	    
	    try
	    {
	        // Create statement
	        Statement stmt = con.createStatement();
	        
	        scanner = new Scanner(new File(fileName));
	        // Read commands separated by ;
	        scanner.useDelimiter(";");
	        while (scanner.hasNext())
	        {
	            String command = scanner.next();
	            if (command.trim().equals(""))
	                continue;
	            // System.out.println(command);        // Uncomment if want to see commands executed
	            stmt.execute(command);
	        }	        
	    }
	    catch (Exception e)
	    {
	        System.out.println(e);
	    }      
	    if (scanner != null)
	    	scanner.close();
	    
	    System.out.println("Data successfully loaded.");
	}
}
	
	