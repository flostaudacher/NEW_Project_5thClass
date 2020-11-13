
public class aktienListe {
	private int AktienID;
	private String symbol;
	public aktienListe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public aktienListe(int aktienID, String symbol) {
		super();
		AktienID = aktienID;
		this.symbol = symbol;
	}
	public int getAktienID() {
		return AktienID;
	}
	public void setAktienID(int aktienID) {
		AktienID = aktienID;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	@Override
	public String toString() {
		return "aktienListe [AktienID=" + AktienID + ", symbol=" + symbol + "]";
	}
	
}
