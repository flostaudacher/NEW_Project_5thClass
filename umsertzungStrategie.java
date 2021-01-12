import java.util.ArrayList;

public class umsertzungStrategie {
	private static Double depotValue = 10000.0;
	private static int zaehler = 1; 
	private static int stückzahl = 0;
	static double buyValue;
	static double sellValue;
	public static Double handel(ArrayList<aktie> kaufe, ArrayList<aktie> verkaufe) {
		// TODO Auto-generated method stub
		if (kaufe.get(zaehler) == null) {
			return depotValue;
		}
		buyStock(kaufe.get(zaehler));
		SellStock(verkaufe.get(zaehler));
		zaehler ++;
		return handel(kaufe,verkaufe);
	}
	static void buyStock(aktie a) {
		double closingValueBuy = Double.parseDouble(a.getValue()); // ohne gebühren
		//double closingValueBuy = (Double.parseDouble(arr))*1.01; // mit gebühren.
		stückzahl = (int) ((depotValue - 5) / (closingValueBuy)); // formel wo die Gebühren hin gehören
		buyValue = closingValueBuy;
		getValueAtBuy();
		depotValue = depotValue - stückzahl * (closingValueBuy);
	}


	static void SellStock(aktie a) {
		double closingValueSell = Double.parseDouble(a.getValue()); 
		depotValue = depotValue + stückzahl * (closingValueSell);
		sellValue = closingValueSell;
		getValueAtSell();
		stückzahl = 0;

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
