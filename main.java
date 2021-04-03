
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class main {
	public static ArrayList<String> minimalValueTimeList ;
	public static ArrayList<String> maximalValueTimeList ;
	public static frequenzy bestTime = null;
	public static Map<String,Integer> frequenzyMap = new HashMap<String,Integer>();
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
		
		Datavizualise.main(args);
		
		
		//
		//combination.combine();
		/*String [][] stock = new String[combination.getLengthOfNeededArray()][combination.getCols()];
		aktie[] a = new aktie[combination.getLengthOfNeededArray()];
		combination.fillStockArray(stock);
		combination.pushToDatabase(con, a, stock,db);*/
		
		
	}
	public static void work() throws ClassNotFoundException, SQLException, ParseException{
		download.deleteCurrentFiles();
		DBmanager db = new DBmanager();
		Connection con = db.getConnection();
		download.deleteCurrentFiles();
		if (db.stockAlreadyExistsInAktienList(con, download.stockSymbol) == false) {
			download.download();
			combination.VonCSVinDatenbank(con, db);
		}
		minimalValueTimeList = db.getTimeofMinOrMaxofDay(con,db.getIDfromStock(con, download.stockSymbol), 1); // 1 für min 2 für max
		String BuyTime = getBuyTime(1,minimalValueTimeList); // für min 
		maximalValueTimeList = db.getTimeofMinOrMaxofDay(con,db.getIDfromStock(con, download.stockSymbol), 2);
		String SellTime = getBuyTime(2,maximalValueTimeList); // für max 
		ArrayList<aktie> eintrage = db.readStockValues(con, db.getIDfromStock(con, download.stockSymbol));
		umsertzungStrategie.handel(eintrage, BuyTime, SellTime);
	}
	static String getBuyTime(int option, ArrayList<String> list) {
		// TODO Auto-generated method stub
		ArrayList<frequenzy> FList = new ArrayList<frequenzy>();
		for ( String str : list) {
			Integer i = frequenzyMap.get( str);
			if ( i == null) {
				i = new Integer( 1);
			} else {
				i = new Integer( i.intValue()+1);
			}
			frequenzyMap.put( str, i);
		}
		for (String key : frequenzyMap.keySet()) {
			frequenzy f = new frequenzy(key,frequenzyMap.get(key));
			FList.add(f);
		}
		bestTime = sortForBestTime(FList);
		if (option == 1) {
			return bestTime.getTime();
		} else {
			return bestTime.getTime();
		}
	}
	private static frequenzy sortForBestTime(ArrayList<frequenzy> fList) {
		frequenzy temp;
		if (fList.size() > 1) 
		{
			for (int x = 0; x < fList.size(); x++) 
			{ 
				for (int i=0; i < fList.size() - x - 1; i++) {
					if (fList.get(i).compareTo(fList.get(i+1)) > 0)
					{
						temp = fList.get(i);
						fList.set(i,fList.get(i+1) );
						fList.set(i+1, temp);
					}
				}
			}
		} 
		return fList.get(fList.size()-1);
	}
}
