import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class textfeld extends Pane{
	private static double erfolg;
	private static double nichterfolg;
	private static ArrayList<Double> GewinnBeiJeweilgemTrade = new ArrayList<Double>();
	public static VBox showValues() {
		calcHitratio();
		VBox inhalte = new VBox();
		Label Hitratio = new Label("Hitratio :\t\t\t"+ getHitratio());
		Label ProfitFactor = new Label("ProfitFactor :\t\t"+ getProfitFactorORPayoutRatio(1));
		Label PayoutRatio = new Label("PayoutRatio :\t\t"+  getProfitFactorORPayoutRatio(2));
		inhalte.getChildren().addAll(Hitratio,ProfitFactor,PayoutRatio);
		inhalte.setStyle("-fx-border-style: solid inside;" + 
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" + 
                "-fx-border-radius: 5;" + 
                "-fx-border-color: black;");
		return inhalte;
	}
	private static double getProfitFactorORPayoutRatio(int option) {
		double SummeDerGewinner = 0;
		double SummeDerVerlierer = 0;
		int AnzahlGewinner = 0;
		int AnzahlVerlierer = 0;
		for (int i = 0; i < GewinnBeiJeweilgemTrade.size(); i++) {
			if (GewinnBeiJeweilgemTrade.get(i) > 0) {
				AnzahlGewinner++;
				SummeDerGewinner = SummeDerGewinner + GewinnBeiJeweilgemTrade.get(i);
			} if (GewinnBeiJeweilgemTrade.get(i) < 0) {
				AnzahlVerlierer++;
				SummeDerVerlierer = (SummeDerVerlierer + GewinnBeiJeweilgemTrade.get(i)*(-1));
			}
		}
		if (option == 1) {
			return  SummeDerGewinner / SummeDerVerlierer;
		}else {
			return (SummeDerGewinner/AnzahlGewinner) / (SummeDerVerlierer/AnzahlVerlierer);
		}
		
	}
	public static void calcHitratio () {
		for (int i  = 0; i < umsertzungStrategie.History.size(); i++) {
			if (umsertzungStrategie.History.get(i).isErfolg() == true) {
				erfolg  = erfolg + 1.0;
			}
			else {
				nichterfolg = nichterfolg + 1.0;
			}
		}
	}
	public static void calcProfitFactor(int stückzahl, double buyValue, double sellValue) {
		// TODO Auto-generated method stub
		double wertDesKaufes = stückzahl * buyValue;
		double wertDesVerkaufes = stückzahl * sellValue;
		double Gewinn =  wertDesKaufes - wertDesVerkaufes;
		GewinnBeiJeweilgemTrade.add(Gewinn);
	}
	private static double getHitratio() {
		return erfolg/(nichterfolg+erfolg)*100;
	}
}
