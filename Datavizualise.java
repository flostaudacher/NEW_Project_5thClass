import java.sql.SQLException;
import java.text.ParseException;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Datavizualise extends Application{
	Scene sce1, sce2;
	Stage st;
	AreaChart<Number, Number> areaChart;
	BarChart<String, Number> barchartMin;
	BarChart<String, Number> barchartMax;
	VBox kennzahlen;
	VBox tabelle;
	GridPane layout1 = new GridPane();
	Button GoBackToOptionMenu = new Button("Go Back to choose new Parameters");

	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		init(primaryStage);
	}
	private void init(Stage primaryStage) {
		/**
		 * Alles von hier bis zum nächsten komment behandelt den Backtest
		 */
		
		st = primaryStage;
		st.setMaximized(true);
		sce1 = new Scene(layout1, 1900, 1000);


		/**
		 * hier bis zum nächsten komment ist das auswahlmenü
		 */
		Button DisplayBackTest = new Button("Commit the Backtest");
		GridPane.setConstraints(DisplayBackTest, 10, 10);
		GridPane layout2 = new GridPane();
		layout2.setPadding(new Insets(10, 10, 10, 10));
		layout2.setVgap(5);
		layout2.setHgap(5);
		
		final TextField Symbol = new TextField();
		Symbol.setPromptText("Enter the Stocksymbol you want to backtest");
		Symbol.setPrefColumnCount(10);
		Symbol.getText();
		GridPane.setConstraints(Symbol, 0, 0);
		
		ChoiceBox<String> dayOfBuy = new ChoiceBox<String>();
		dayOfBuy.getItems().addAll("Mo","Di","Mi","Do","Fr");
		dayOfBuy.setValue("Fr");
		GridPane.setConstraints(dayOfBuy, 1, 0);
		
		
		DisplayBackTest.setOnAction(e -> {
			try {
				getChoice(dayOfBuy,Symbol);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		layout2.getChildren().addAll(Symbol,dayOfBuy,DisplayBackTest);
		sce2 = new Scene(layout2,500,500);

		st.setScene(sce2);
		st.setTitle("Untersuchung der ausgewälten Aktie");
		st.show();  

	}
	private Object getChoice(ChoiceBox<String> dayOfBuy, TextField symbol) throws ClassNotFoundException, SQLException, ParseException {
		String ChosenDayOfBuy = dayOfBuy.getValue();
		String ChosenSymbol = symbol.getText();
		umsertzungStrategie.setDayofBuy(ChosenDayOfBuy);
		download.stockSymbol = ChosenSymbol;
		main.work();
		areaChart = vizualization.areachart();
		barchartMin =  barChart.Barchart(1);
		barchartMax = barChart.Barchart(2);
		tabelle = table.tableCreat();
		kennzahlen = textfeld.showValues();
		GoBackToOptionMenu.setOnAction(e -> goBack());
		layout1.setConstraints(areaChart, 0, 0);
		layout1.setConstraints(barchartMin, 2, 0);
		layout1.setConstraints(barchartMax, 1, 0);
		layout1.setConstraints(tabelle, 1, 1);
		layout1.setConstraints(kennzahlen, 0, 1);
		layout1.setConstraints(GoBackToOptionMenu, 2, 1);
		layout1.getChildren().addAll(areaChart,barchartMin,barchartMax,tabelle,kennzahlen,GoBackToOptionMenu);

		layout1.setVgap(100);
		layout1.setHgap(100);
		st.setScene(sce1);
		return null;
	}
	private Object goBack() {
		st.setScene(sce2);
		areaChart.getData().clear();
		barchartMin.getData().clear();
		barchartMax.getData().clear();
		tabelle.getChildren().remove(true);
		kennzahlen.getChildren().clear();
		layout1.getChildren().clear();
		return null;
	}
	public static void main(String[] args) {
		launch(args);
	}
}