import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;

public class vizualization extends Pane {

	public static AreaChart<Number, Number> areachart() {

		NumberAxis xAxis= new NumberAxis();
		xAxis.setLabel("times traded");
		NumberAxis yAxis= new NumberAxis();
		yAxis.setLabel("Depot Value");
		AreaChart<Number, Number> areaChart = new AreaChart<Number,Number>(xAxis,yAxis);
		areaChart.setTitle("depot value in relation to times traded");

		XYChart.Series<Number, Number> data = new XYChart.Series<Number, Number>();
		data.setName("Backtesting result for last 2 years");

		vizualizingMethods.create(data);
		areaChart.setCreateSymbols(false);
		areaChart.getData().add(data);
		return areaChart;
	}

}