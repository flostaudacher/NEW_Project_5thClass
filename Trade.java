import java.util.Date;

public class Trade {
	private String datum = null;
	private Double depotWert =  0.0;
	private int positionen = 0;
	private double kaufWert = 0;
	private double verkaufWert = 0;
	private boolean erfolg = false;
	public Trade() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Trade(String string, Double depotValue, int positionen, double buyValue, double sellValue, boolean erfolg) {
		super();
		this.datum = string;
		this.depotWert = depotValue;
		this.positionen = positionen;
		this.kaufWert = buyValue;
		this.verkaufWert = sellValue;
		this.erfolg = erfolg;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public Double getDepotWert() {
		return depotWert;
	}

	public void setDepotWert(Double depotWert) {
		this.depotWert = depotWert;
	}

	public int getPositionen() {
		return positionen;
	}

	public void setPositionen(int positionen) {
		this.positionen = positionen;
	}

	public double getKaufWert() {
		return kaufWert;
	}

	public void setKaufWert(double kaufWert) {
		this.kaufWert = kaufWert;
	}

	public double getVerkaufWert() {
		return verkaufWert;
	}

	public void setVerkaufWert(double verkaufWert) {
		this.verkaufWert = verkaufWert;
	}

	public boolean isErfolg() {
		return erfolg;
	}

	public void setErfolg(boolean erfolg) {
		this.erfolg = erfolg;
	}


	
}
