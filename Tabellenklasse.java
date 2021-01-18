import javafx.beans.property.SimpleStringProperty;

public class Tabellenklasse {
	private final SimpleStringProperty Datum;
	private final SimpleStringProperty Positionen;
	private final SimpleStringProperty Kaufpreis;
	private final SimpleStringProperty Verkaufpreis;
	private final SimpleStringProperty Umsatz;

	Tabellenklasse(String Datum, int i, double d, double e, boolean b) {
		this.Datum = new SimpleStringProperty(Datum);
		String x =""+i;
		this.Positionen = new SimpleStringProperty(x);
		String f ="" +d;
		this.Kaufpreis = new SimpleStringProperty(f);
		String g ="" +e;
		this.Verkaufpreis = new SimpleStringProperty(g);
		String h = "" + b;
		this.Umsatz = new SimpleStringProperty(h);
	}

	public String getDatum() {
		return Datum.get();
	}

	public String getPositionen() {
		return Positionen.get();
	}

	public String getKaufpreis() {
		return Kaufpreis.get();
	}

	public String getVerkaufpreis() {
		return Verkaufpreis.get();
	}

	public String getUmsatz() {
		return Umsatz.get();
	}
}