import java.util.ArrayList;

public class umsertzungStrategie {
	private static Double depotValue = 10000.0;
	private static int zaehler = 1; 
	private static int st�ckzahl = 0;
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
		double closingValueBuy = Double.parseDouble(a.getValue()); // ohne geb�hren
		//double closingValueBuy = (Double.parseDouble(arr))*1.01; // mit geb�hren.
		st�ckzahl = (int) ((depotValue - 5) / (closingValueBuy)); // formel wo die Geb�hren hin geh�ren
		buyValue = closingValueBuy;
		getValueAtBuy();
		depotValue = depotValue - st�ckzahl * (closingValueBuy);
	}


	static void SellStock(aktie a) {
		double closingValueSell = Double.parseDouble(a.getValue()); 
		depotValue = depotValue + st�ckzahl * (closingValueSell);
		sellValue = closingValueSell;
		getValueAtSell();
		st�ckzahl = 0;

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
