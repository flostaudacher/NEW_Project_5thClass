import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class vizualise extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Times");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Times of minval");

		// Create a BarChart
		BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);

		// Series 1 - Data of 2014
		Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
		dataSeries1.setName("lol");
		
		for (String key : main.frequenzyMap.keySet()) {
			dataSeries1.getData().add(new XYChart.Data<String, Number>(key, main.frequenzyMap.get(key)));
		}
		
		barChart.getData().add(dataSeries1);
		barChart.setTitle("Frequenzy");

		VBox vbox = new VBox(barChart);

		primaryStage.setTitle("JavaFX BarChart");
		Scene scene = new Scene(vbox, 400, 200);

		primaryStage.setScene(scene);
		primaryStage.setHeight(300);
		primaryStage.setWidth(400);

		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}