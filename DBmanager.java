import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
		String sql = "insert into aktie (Symbol,Datum,Zeitpunkt,StockValue,Weekday) values (?,?,?,?,?)";
		PreparedStatement stm = null;
		try {
			stm = con.prepareStatement(sql);;
			Date date = Date.valueOf(stock.getDate());
			stm.setInt(1, stock.getSymbol());		
			stm.setDate(2, date);	
			stm.setTime(3, stock.getTimestamp());	
			stm.setString(4, stock.getValue());
			stm.setString(5,stock.getWeekday());
			stm.executeUpdate();
		}
		finally {
			if (stm != null)
				stm.close();
		}
	}
	public ArrayList<aktie> readStockValues (Connection con, int Symbol, String time, String Buyday) throws SQLException, ParseException{
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<aktie> result = new ArrayList<aktie>();
		try {
			String sql = "select Symbol, Datum, Zeitpunkt, StockValue, Weekday from aktie where Symbol = ? and Zeitpunkt = ? and Weekday = ? group by Datum order by Datum";
			stm = con.prepareStatement(sql);
			stm.setInt(1, Symbol);
			stm.setString(2, time);
			stm.setString(3,Buyday);
			rs = stm.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String Datum = rs.getString(2);
				Time Zeitpunkt = rs.getTime(3);
				String StockValue = rs.getString(4);
				String Weekday = rs.getString(5);
				aktie a = new aktie(Symbol,Datum,Zeitpunkt,StockValue);
				a.setWeekday(Weekday);
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
	public void newAktieInAktienListe(Connection con, aktienListe l) throws SQLException {
		String sql = "insert into aktienListe (symbol) values (?)";
		PreparedStatement stm = null;
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, l.getSymbol());	
			stm.executeUpdate();
		}
		finally {
			if (stm != null)
				stm.close();
		}
	}
	public boolean stockAlreadyExistsInAktienList (Connection con, String symbol) throws SQLException {
		boolean result = false;
		PreparedStatement stm = null;
		ResultSet rs = null; 
		try {
			String sql = "select count(*) from aktienListe where symbol = ?";
			stm = con.prepareStatement(sql);
			stm.setString(1, symbol);
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
	public int getIDfromStock(Connection con, String symbol) throws SQLException {
		PreparedStatement stm = null;
		ResultSet rs = null;
		int result = 0;
		try {
			String sql = "select ID from aktienListe where Symbol = ?";
			stm = con.prepareStatement(sql);
			stm.setString(1, symbol);
			rs = stm.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
		}
		finally
		{
			if(stm != null)
				stm.close();
		}
		return result;	
	}
	public ArrayList<String> getTimeofMinOrMaxofDay(Connection con, int symbol, int i) throws SQLException {
		PreparedStatement stm = null;
		String sql = "";
		if (i == 1) {
			sql = "select Zeitpunkt,min(StockValue) as minVal from aktie where (Symbol = ? and Zeitpunkt between '04:00' and '20:00')group by Datum";
		} else {
			sql = "select Zeitpunkt,max(StockValue) as maxVal from aktie where (Symbol = ? and Zeitpunkt between '04:00' and '20:00')group by Datum;";
		}
		ResultSet rs = null;
		String zeitpunkt = "";
		String minStockValue ="";
		ArrayList<String> result = new 	ArrayList<String> ();
		try {
			stm = con.prepareStatement(sql);
			stm.setInt(1, symbol);
			rs = stm.executeQuery();
			while(rs.next()) {
				zeitpunkt = rs.getString(1);	
				minStockValue = rs.getString(2);
				result.add(zeitpunkt);
			}
		}
		finally
		{
			if(stm != null)
				stm.close();
		}
		return result;
	}
	
	public void releaseConnection (Connection con) 
			throws SQLException {
		if (con != null)
			con.close();	
	}
}
