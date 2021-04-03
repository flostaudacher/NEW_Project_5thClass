import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;

public class barChart extends Pane{

	public static BarChart<String, Number> Barchart(int Option) {
		CategoryAxis xAxis = new CategoryAxis();  
		NumberAxis yAxis= new NumberAxis();
		BarChart<String, Number> barChart = new BarChart<>(xAxis,yAxis);
		XYChart.Series<Number, Number> data = new XYChart.Series<Number, Number>();
		if (Option == 1 ) {
			xAxis.setLabel("Times");
			yAxis.setLabel("Times of minval");
			barChart.setTitle("Time To Buy");
			data.setName("Analysation of data for best time to buy");
			barChart.getData().addAll(barChartMethods.createdforMin(new XYChart.Series<String,Number>()));
		}
		if (Option == 2) {
			xAxis.setLabel("Times");
			yAxis.setLabel("Times of Maxval");
			barChart.setTitle("Time To Sell");
			data.setName("Analysation of data for best time to Sell");
			barChart.getData().addAll(barChartMethods.createdforMax(new XYChart.Series<String,Number>()));
		}
		barChart.setCategoryGap(20);

		return barChart;
	}
}