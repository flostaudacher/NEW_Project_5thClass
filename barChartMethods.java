import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class barChartMethods {
	private static double erfolg;
	private static double nichterfolg;
	public static Map<String,Integer> frequenzyMap = new HashMap<String,Integer>();
	public static Series<String, Number> create(Series<String, Number> series,String p) {
		series.setName("" + p);
		if (p.equals("positiv")) {
			Number n = ((erfolg / umsertzungStrategie.History.size()) * 100.0);
			series.getData().add(new XYChart.Data<>(p,n));
		}
		if (p.equals("negativ")) {
			Number n = ((nichterfolg / umsertzungStrategie.History.size()) * 100.0);
			series.getData().add(new XYChart.Data<>(p,n));
		}
		return series;
	}
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
	public static void calcChance () {
		for (int i  = 0; i < umsertzungStrategie.History.size(); i++) {
			if (umsertzungStrategie.History.get(i).isErfolg() == true) {
				erfolg  = erfolg + 1.0;
			}
			else {
				nichterfolg = nichterfolg + 1.0;
			}
		}
	}
}
