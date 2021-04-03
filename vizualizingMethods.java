import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class vizualizingMethods {
	public static void create(Series<Number, Number> data) {
		createTheChart(data);
	}
	public static void createTheChart(Series<Number, Number> data) {

		for (int Rowc = 1; Rowc < umsertzungStrategie.History.size(); Rowc++) {
				data.getData().add(new XYChart.Data<Number, Number>( Rowc, umsertzungStrategie.History.get(Rowc).getDepotWert()));
		}
	}
}