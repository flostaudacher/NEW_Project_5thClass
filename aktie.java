import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class aktie {
	private String date;
	private int symbol;
	private Time timestamp;
	private String value;
	private String Weekday;
	static String [] days = {"Mo","Di","Mi","Do","Fr","Sa","So"};
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getSymbol() {
		return symbol;
	}
	public void setSymbol(int symbol) {
		this.symbol = symbol;
	}
	public Time getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Time timestamp) {
		this.timestamp = timestamp;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getWeekday() {
		return Weekday;
	}
	public void setWeekday(String weekday) {
		Weekday = weekday;
	}
	public aktie(int symbol, String date, Time timestamp, String value) throws ParseException {
		super();
		this.date = date;
		this.symbol = symbol;
		this.timestamp = timestamp;
		this.value = value;
		Weekday = calcWeekday(date);
	}
	public aktie() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Weekday == null) ? 0 : Weekday.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + symbol;
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		aktie other = (aktie) obj;
		if (Weekday == null) {
			if (other.Weekday != null)
				return false;
		} else if (!Weekday.equals(other.Weekday))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (symbol != other.symbol)
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "aktie [date=" + date + ", symbol=" + symbol + ", timestamp=" + timestamp + ", value=" + value
				+ ", Weekday=" + Weekday + "]";
	}
	public static String calcWeekday(String date) throws ParseException {
		String s = toSimpleDate(date); ;
		if (s.equals(days[4])) {
			return days[4];
		}
		for (int dayCounter = 0; dayCounter < days.length; dayCounter++) {
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
}