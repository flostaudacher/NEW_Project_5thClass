
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	public static frequenzy bestTime = null;
	@Override
	public void start(Stage primaryStage) throws Exception {
		ArrayList<frequenzy> FList = new ArrayList<frequenzy>();
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Times");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Times of minval");

		// Create a BarChart
		BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);

		// Series 1 - Data of 2014
		Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
		dataSeries1.setName("lol");
		Map<String,Integer> frequenzyMap = new HashMap<String,Integer>();

		for ( String str : main.minimalValueTimeList) {
			Integer i = frequenzyMap.get( str);
			if ( i == null) {
				i = new Integer( 1);
			} else {
				i = new Integer( i.intValue()+1);
			}
			frequenzyMap.put( str, i);
		}
		for (String key : frequenzyMap.keySet()) {
			frequenzy f = new frequenzy(key,frequenzyMap.get(key));
			FList.add(f);
			dataSeries1.getData().add(new XYChart.Data<String, Number>(key, frequenzyMap.get(key)));
		}
		bestTime = sortForBestTime(FList);
		System.out.println("Bester Zeitpunkt Uhrzeit = "+ bestTime.getTime() + " Anzahl der Minimalwerte = " + bestTime.getTimesMinVal());
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

	private frequenzy sortForBestTime(ArrayList<frequenzy> fList) {
		frequenzy temp;
		if (fList.size() > 1) // check if the number of orders is larger than 1
		{
			for (int x = 0; x < fList.size(); x++) // bubble sort outer loop
			{ 
				for (int i=0; i < fList.size() - x - 1; i++) {
					if (fList.get(i).compareTo(fList.get(i+1)) > 0)
					{
						temp = fList.get(i);
						fList.set(i,fList.get(i+1) );
						fList.set(i+1, temp);
					}
				}
			}
		}
		return fList.get(fList.size()-1);
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}