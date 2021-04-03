import java.text.DecimalFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class table extends Pane{
	public static ObservableList<Tabellenklasse> data = FXCollections.observableArrayList();

	static DecimalFormat f = new DecimalFormat("#0.00");

	@SuppressWarnings("unchecked")
	public static VBox tableCreat() {
		TableView<Tabellenklasse> table = new TableView<Tabellenklasse>();
		final Label label = new Label("Trading History");
		label.setFont(new Font("Arial", 20));
		table.setEditable(false);
		fill();
		TableColumn Datum = new TableColumn("Datum");
		Datum.impl_setReorderable(false);
		Datum.setCellValueFactory(
				new PropertyValueFactory<Tabellenklasse ,String>("Datum")
				);
		TableColumn Positionen = new TableColumn("Positionen");
		Positionen.impl_setReorderable(false);
		Positionen.setCellValueFactory(
				new PropertyValueFactory<Tabellenklasse ,String>("Positionen")
				);
		TableColumn Kaufpreis = new TableColumn("Kaufpreis");

		Kaufpreis.impl_setReorderable(false);
		Kaufpreis.setCellValueFactory(
				new PropertyValueFactory<Tabellenklasse ,String>("Kaufpreis")
				);
		TableColumn Verkaufspreis = new TableColumn("Verkaufpreis");
		Verkaufspreis.impl_setReorderable(false);
		Verkaufspreis.setCellValueFactory(
				new PropertyValueFactory<Tabellenklasse ,String>("Verkaufpreis")
				);
		TableColumn Umsatz = new TableColumn("Umsatz");
		Umsatz.impl_setReorderable(false);
		Umsatz.setCellValueFactory(
				new PropertyValueFactory<Tabellenklasse ,String>("Umsatz")
				);
		Umsatz.setCellFactory(column -> {
			return new TableCell<Tabellenklasse, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText("");
						setStyle("");
					} else {
						if (item.equals("true")) {
							setTextFill(Color.GREEN);
							setText("positiver Trade");
						} else {
							setTextFill(Color.RED);
							setText("negativer Trade");
							setStyle("");
						}
					}
				}
			};
		});

		table.setItems(data);
		table.getColumns().addAll(Datum, Positionen, Kaufpreis, Verkaufspreis,Umsatz);
		table.minWidth(550);
		final VBox vbox = new VBox();
		vbox.setMinWidth(550);
		vbox.getChildren().addAll(label, table);
		vbox.autosize();

		return vbox;
	}
	public static void fill() {

		for (int Rowc = 1; Rowc < umsertzungStrategie.History.size();Rowc++) {
			data.addAll(new Tabellenklasse(umsertzungStrategie.History.get(Rowc).getDatum(),umsertzungStrategie.History.get(Rowc).getPositionen(), umsertzungStrategie.History.get(Rowc).getKaufWert(),umsertzungStrategie.History.get(Rowc).getVerkaufWert(),umsertzungStrategie.History.get(Rowc).isErfolg()));
		}
	}
}