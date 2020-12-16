import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class combination {
	private static int cols = 7;
	public static String[][] CombinationArray = new String [10000000][cols];
	private static String InputLine = "";
	private static int Rowc;
	private static int lengthOfNeededArray;
	public static void combine() {
		// TODO Auto-generated method stub
		Rowc = 0;
		for (int i = download.fileName.length-1 ; i >= 0  ; i = i -1 ) {
			Scanner sc= null;
			try 
			{
				sc= new Scanner (new BufferedReader(new FileReader(download.fileName[i])));
				sc.nextLine();
				while (sc.hasNextLine()) {
					InputLine = sc.nextLine();
					InputLine = InputLine.replaceAll(" ", ",");
					String[] inArr = InputLine.split(",");
					for (int x = 0; x < inArr.length; x++) {
						CombinationArray[Rowc][x%7]=inArr[x];
					}
					Rowc++;
				}
			}catch (Exception e) {
				System.out.println(e);
			}
			NewArray();
		}
	}
	public void printFiles() {
		for (int i = 0; i < download.url.length; i++) {
			System.out.println(""+download.fileName[i]);
		}
	}
	public static void printCombineArray() {
		for (int Rowc = 0; Rowc < 2; Rowc++) {
			for (int Colc = 0; Colc < 6; Colc++) {
				System.out.print(CombinationArray[Rowc][Colc]);
			}
			System.out.println();
		}
	}
	public static void NewArray() {
		int Row = 0;
		do {
			Row++;
		}while(CombinationArray[Row][0] != null);
		lengthOfNeededArray = Row;
	}
	public static int getLengthOfNeededArray() {
		return lengthOfNeededArray;
	}
	public static void setLengthOfNeededArray(int lengthOfNeededArray) {
		combination.lengthOfNeededArray = lengthOfNeededArray;
	}
	public static int getCols() {
		return cols;
	}
	public static void setCols(int cols) {
		combination.cols = cols;
	}
	static void fillStockArray(String[][] stock) {
		for (int Rowc = 0; Rowc < combination.getLengthOfNeededArray(); Rowc++) {
			for (int Colc = 0; Colc < combination.getCols(); Colc++) {
				String s = "" + combination.CombinationArray[Rowc][Colc];
				stock[Rowc][Colc] = s;
			}
		}
	}
	static void pushToDatabase(Connection con, aktie[] a, String[][] stock, DBmanager db) throws SQLException {
		int Passcounter = 0;
		int Errorcounter = 0;
		if (db.stockAlreadyExistsInAktienList(con, download.stockSymbol) == false ){
			aktienListe l = new aktienListe (1, download.stockSymbol);
			db.newAktieInAktienListe(con, l);
		}
		for (int Rowc = 0; Rowc < combination.getLengthOfNeededArray(); Rowc++) {
			if (db.stockRowAlreadyExists(con, stock[Rowc][0], stock[Rowc][1]) == false) {
				a[Rowc] = new aktie(db.getIDfromStock(con, download.stockSymbol),stock[Rowc][0],stock[Rowc][1],stock[Rowc][4]);
				db.saveNewSpecificStockValue(con,a[Rowc]);
				Passcounter++;
			}
			else {
				Errorcounter++;
			}
		}
		System.out.println("Es wurden " + Passcounter + " neue Einträge eingetragen " + Errorcounter + " Einträge waren schon vorhanden");
	}

}