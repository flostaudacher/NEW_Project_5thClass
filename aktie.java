import java.sql.Time;

public class aktie {
	private String date;
	private int symbol;
	private Time timestamp;
	private String value;
	public aktie(int symbol, String date, Time time, String value) {
		super();
		this.date = date;
		this.symbol = symbol;
		this.timestamp = time;
		this.value = value;
	}
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
	public aktie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public aktie(String date, int symbol, Time timestamp, String value) {
		super();
		this.date = date;
		this.symbol = symbol;
		this.timestamp = timestamp;
		this.value = value;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		return "aktie [date=" + date + ", symbol=" + symbol + ", timestamp=" + timestamp + ", value=" + value + "]";
	}
	
}