import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fridaycheck {
	static String [] days = {"Mo","Di","Mi","Do","Fr"};
	public static String checkForFriday(int Rowc) throws ParseException {
		String s = toSimpleDate(Import.arr[Rowc][0]); ;
		if (s.equals(days[4])) {
			return days[4];
		}
		for (int dayCounter = 0; dayCounter <= days.length; dayCounter++) {
			if (s.equals(days[dayCounter])) {
				return days[dayCounter];
			}
		}
		return "";
	}
	private static String toSimpleDate(String string) throws ParseException {
		Date date=new SimpleDateFormat("yyyy-MM-dd").parse(string); 
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
		return simpleDateformat.format(date);
	}
	public static void börsenfrei() {
		int zaehlerWoMaSein=0;
		do {
			if (Import.arr[zaehlerWoMaSein][7].equals("Do")) {
				if (Import.arr[zaehlerWoMaSein+1][7].equals("Mo")) {
					Import.arr[zaehlerWoMaSein][7] = "Fr";
				}
			}
			zaehlerWoMaSein++;
		}while (Import.arr.length > zaehlerWoMaSein);
		
		
	}
}
