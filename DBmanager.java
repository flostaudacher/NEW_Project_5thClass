import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DBmanager {
	public Connection getConnection() 
			throws ClassNotFoundException, SQLException {

		Connection con = null;		
		Class.forName("com.mysql.jdbc.Driver");		
		con = DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/aktien?serverTimezone=UTC&useSSL=false",   // DB
				"root",                                 								// User
				"13456stau"                             							    // Passwort
				);
		return con;
	}
	public static void saveNewSpecificStockValue(Connection con, aktie stock) throws SQLException {
		String sql = "insert into aktie (symbol,Datum,Zeitpunkt,StockValue) values (?,?,?,?)";
		PreparedStatement stm = null;
		try {
			stm = con.prepareStatement(sql);
			Date date = java.sql.Date.valueOf( stock.getDate());
			stm.setString(1, stock.getSymbol());		
			stm.setDate(2, date);	
			stm.setString(3, stock.getTimestamp());	
			stm.setString(4, stock.getValue());	
			stm.executeUpdate();
		}
		finally {
			if (stm != null)
				stm.close();
		}
	}
	public ArrayList<aktie> readStockValues (Connection con, String Symbol) throws SQLException{
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<aktie> result = new ArrayList<aktie>();
		try {
			String sql = "select Datum, Zeitpunkt, StockValue from aktie where Symbol = ?";
			stm = con.prepareStatement(sql);
			stm.setString(1, Symbol);
			rs = stm.executeQuery();
			while(rs.next()) {
				String Datum = rs.getString(1);
				String Zeitpunkt = rs.getString(2);
				String StockValue = rs.getString(3);
				aktie a = new aktie(Symbol,Datum,Zeitpunkt,StockValue);
				result.add(a);
			}
		}
		finally
		{
			if(stm != null)
				stm.close();
		}
		return result;	
	}
	public ArrayList<aktie> getMinValueOfDay (Connection con, String Symbol, String date) throws SQLException{
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<aktie> result = new ArrayList<aktie>();
		try {
			String sql = "select Zeitpunkt,StockValue from aktie where Symbol = ? and where Zeitpunkt = ?";
			stm = con.prepareStatement(sql);
			stm.setString(1, Symbol);
			Date datum = java.sql.Date.valueOf( date);
			stm.setDate(2,datum);
			rs = stm.executeQuery();
			while(rs.next()) {;
			String Zeitpunkt = rs.getString(1);
			String StockValue = rs.getString(2);
			aktie a = new aktie(Symbol,date,Zeitpunkt,StockValue);
			result.add(a);
			}
		}
		finally
		{
			if(stm != null)
				stm.close();
		}
		return result;	
	}
	public boolean stockRowAlreadyExists(Connection con, String datum, String zeitpunkt) throws SQLException{
		boolean result = false;
		PreparedStatement stm = null;
		ResultSet rs = null; 
		try {
			String sql = "select count(*) from aktie where Datum = ? and Zeitpunkt = ?";
			stm = con.prepareStatement(sql);
			stm.setString(1, datum);
			stm.setString(2, zeitpunkt);
			rs = stm.executeQuery();
			if (rs.next()) {
				int anzahl = rs.getInt(1);
				result = anzahl == 1;
			}
		}
		finally {
			if (stm != null) {
				stm.close();
			}
		}
		return result;
	}
	public void releaseConnection (Connection con) 
			throws SQLException {
		if (con != null)
			con.close();	
	}
}
