
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class main {
	public static ArrayList<String> minimalValueTimeList ;
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
		download.deleteCurrentFiles();
		DBmanager db = new DBmanager();
		Connection con = db.getConnection();
		download.deleteCurrentFiles();
		download.download();
		combination.combine();
		String [][] stock = new String[combination.getLengthOfNeededArray()][combination.getCols()];
		aktie[] a = new aktie[combination.getLengthOfNeededArray()];
		combination.fillStockArray(stock);
		combination.pushToDatabase(con, a, stock,db);
		minimalValueTimeList = db.getTimeofMinofDay(con,db.getIDfromStock(con, download.stockSymbol));
		Map<String,Integer> frequenzyMap = new HashMap<String,Integer>();

		for ( String str : main.minimalValueTimeList) {
			Integer i = frequenzyMap.get( str);
			if ( i == null) {
				i = new Integer( 1);
			} else {
				i = new Integer( i.intValue()+1);
			}
			frequenzyMap.put( str, i);
		}
		System.out.println(	minimalValueTimeList);
		vizualise.main(args);	
	}
}
