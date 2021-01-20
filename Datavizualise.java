import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Datavizualise extends Application{
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		init(primaryStage);
	}
	private void init(Stage primaryStage) {
		AreaChart<Number, Number> areaChart;
		BarChart<String, Number> barchartChance;
		BarChart<String, Number> barchartMin;
		BarChart<String, Number> barchartMax;
		VBox tabelle;
		Stage st = new Stage();
		FlowPane flowpane = new FlowPane();

		Scene sce = new Scene(flowpane, 1550, 500);
		areaChart = vizualization.areachart();
		barchartChance = barChart.Barchart(0);
		barchartMin =  barChart.Barchart(1);
		barchartMax = barChart.Barchart(2);
		tabelle = table.tableCreat();

		flowpane.getChildren().addAll(areaChart,barchartChance,barchartMin,barchartMax,tabelle);

		flowpane.setVgap(100);
		flowpane.setHgap(100);
		st.setTitle("Untersuchte Aktie ist "+ download.stockSymbol);
		st.setScene(sce);
		st.show();  

	}
	public static void main(String[] args) {
		launch(args);
	}
}