
import java.sql.Connection;
import java.sql.SQLException;

public class main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		DBmanager db = new DBmanager();
		Connection con = db.getConnection();

		download.deleteCurrentFiles();
		download.download();
		combination.combine();
		String [][] stock = new String[combination.getLengthOfNeededArray()][combination.getCols()];
		aktie[] a = new aktie[combination.getLengthOfNeededArray()];
		fillStockArray(stock);
		pushToDatabase(con, a, stock,db);
		System.out.println("Finish !");
		download.deleteCurrentFiles();
	}
	private static void fillStockArray(String[][] stock) {
		for (int Rowc = 0; Rowc < combination.getLengthOfNeededArray(); Rowc++) {
			for (int Colc = 0; Colc < combination.getCols(); Colc++) {
				String s = "" + combination.CombinationArray[Rowc][Colc];
				stock[Rowc][Colc] = s;
			}
		}
	}
	private static void pushToDatabase(Connection con, aktie[] a, String[][] stock, DBmanager db) throws SQLException {
		int Passcounter = 0;
		int Errorcounter = 0;
		for (int Rowc = 0; Rowc < combination.getLengthOfNeededArray(); Rowc++) {
			if (db.stockRowAlreadyExists(con, stock[Rowc][0], stock[Rowc][1]) == false) {
				a[Rowc] = new aktie(download.stockSymbol,stock[Rowc][0],stock[Rowc][1],stock[Rowc][4]);
				DBmanager.saveNewSpecificStockValue(con,a[Rowc]);
				Passcounter++;
			}
			else {
				Errorcounter++;
			}
		}
		System.out.println("Es wurden " + Passcounter + " neue Einträge eingetragen " + Errorcounter + " Einträge waren schon vorhanden");
	}
}
