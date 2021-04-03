import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class barChartMethods {
	public static Map<String,Integer> frequenzyMap = new HashMap<String,Integer>();
	
	public static Series<String, Number> createdforMin(Series<String, Number> series) {
		getTimes(main.minimalValueTimeList);
		for (String key : frequenzyMap.keySet()) {
			series.getData().add(new XYChart.Data<String, Number>(key, frequenzyMap.get(key)));
		}
		return series;

	}
	public static Series<String, Number> createdforMax(Series<String, Number> series) {
		getTimes(main.maximalValueTimeList);
		for (String key : frequenzyMap.keySet()) {
			series.getData().add(new XYChart.Data<String, Number>(key, frequenzyMap.get(key)));
		}
		return series;
	}
	public static void getTimes(ArrayList<String> list) {
		// TODO Auto-generated method stub
		ArrayList<frequenzy> FList = new ArrayList<frequenzy>();
		for ( String str : list) {
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
		}
	}
}
