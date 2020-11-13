public class aktie {
	private String date;
	private String symbol;
	private String timestamp;
	private String value;
	public aktie(String symbol, String date, String stock, String value) {
		super();
		this.date = date;
		this.symbol = symbol;
		this.timestamp = stock;
		this.value = value;
	}
	public aktie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "aktie [date=" + date + ", symbol=" + symbol + ", timestamp=" + timestamp + ", value=" + value + "]";
	}
	
}
