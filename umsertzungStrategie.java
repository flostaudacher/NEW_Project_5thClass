import java.sql.Time;
import java.util.ArrayList;


public class umsertzungStrategie {
	private static Double depotValue = 10000.0;
	private static int stückzahl = 0;
	static double buyValue;
	static double sellValue;
	public static ArrayList<Trade> History = new ArrayList<Trade>();
	static String s = "Mo";

	public static String handel(ArrayList<aktie> handel, String buyTime, String sellTime) {
		String dayOfBuy = null; 
		boolean KaufExcepted = false;
		boolean VerkaufExcepted = false;
		int zaehlerVerkauf = 0;
		int zaehlerKauf = 0;
		int kaufe =0 ;
		for (int Rowc = 0; Rowc < handel.size(); Rowc++) {
			if (handel.get(Rowc).getWeekday().equals(s)) {
				System.out.println(handel.get(Rowc).getTimestamp() + " = "+ buyTime);
			}
			KaufExcepted = handel.get(Rowc).getWeekday().equals(s) && toString(handel.get(Rowc).getTimestamp()).equals(buyTime);
			if (KaufExcepted == true && kaufe != 1) {
				dayOfBuy = handel.get(Rowc).getWeekday();
				buyStock(handel.get(Rowc));
				zaehlerKauf++;
				kaufe  = 1;
				System.out.println(zaehlerKauf);
			}
			VerkaufExcepted = (!handel.get(Rowc).getWeekday().equals(dayOfBuy)) && toString(handel.get(Rowc).getTimestamp()).equals(sellTime);
			if (VerkaufExcepted == true && kaufe == 1) {
				SellStock(handel.get(Rowc));
				dayOfBuy = null;
				zaehlerVerkauf ++;
				kaufe = 0;
				Trade t = new Trade(handel.get(Rowc).getDate(),depotValue,stückzahl,buyValue,sellValue,buyValue < sellValue);
				History.add(t);
				stückzahl = 0;
			}
		}
		return s;
	}
	public static String toString(Time timestamp) {
		String s = "" + timestamp;
		return s;
	}
	static void buyStock(aktie a) {
		double ValueBuy = a.getValue(); // ohne gebühren
		//double closingValueBuy = (Double.parseDouble(arr))*1.01; // mit gebühren.
		stückzahl = (int) ((depotValue - 5) / (ValueBuy)); // formel wo die Gebühren hin gehören
		buyValue = ValueBuy;
		depotValue = depotValue - stückzahl * (ValueBuy);
	}


	static void SellStock(aktie a) {
		double ValueSell = a.getValue(); 
		depotValue = depotValue + stückzahl * (ValueSell);
		sellValue = ValueSell;

	}
	public static double getValueAtSell() {
		// TODO Auto-generated method stub
		return sellValue;
	}

	public static double getValueAtBuy() {
		// TODO Auto-generated method stub
		return buyValue;
	}
}
