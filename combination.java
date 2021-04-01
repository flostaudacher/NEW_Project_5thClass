import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class combination {
	private static int cols = 7;
	//public static String[][] CombinationArray = new String [10000000][cols];
	public static ArrayList<aktie> dataList = new ArrayList<aktie>();
	private static String InputLine = "";
	private static int Rowc;
	private static int lengthOfNeededArray;
	/*public static void combine() {
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
	}*/
	public void printFiles() {
		for (int i = 0; i < download.url.length; i++) {
			System.out.println(""+download.fileName[i]);
		}
	}
	/*
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
	}*/
/*	public static int getLengthOfNeededArray() {
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
	}*/
/*	static void pushToDatabase(Connection con, aktie[] a, String[][] stock, DBmanager db) throws SQLException, ParseException {
		int Passcounter = 0;
		int Errorcounter = 0;
		if (db.stockAlreadyExistsInAktienList(con, download.stockSymbol) == false ){
			aktienListe l = new aktienListe (1, download.stockSymbol);
			db.newAktieInAktienListe(con, l);
		}
		for (int Rowc = 0; Rowc < combination.getLengthOfNeededArray(); Rowc++) {
			boolean okay = db.stockRowAlreadyExists(con,db.getIDfromStock(con, download.stockSymbol),stock[Rowc][0], stock[Rowc][1]);
			if (okay==true) {
				float val =Float.parseFloat(stock[Rowc][4]);
				a[Rowc] = new aktie(db.getIDfromStock(con, download.stockSymbol),stock[Rowc][0],toSQLTime(stock[Rowc][1]),val);
				db.saveNewSpecificStockValue(con,a[Rowc]);
				Passcounter++;
			}
			if (okay==false) {
				Errorcounter++;
			}
		}
		System.out.println("Es wurden " + Passcounter + " neue Einträge eingetragen " + Errorcounter + " Einträge waren schon vorhanden");
	}*/
	public static Time toSQLTime(String string) throws ParseException {
		java.sql.Time sqlTime = Time.valueOf(string);
		return sqlTime;
	}
	
	static void VonCSVinDatenbank(Connection con, DBmanager db) throws SQLException, ParseException {
		for (int i = download.fileName.length-1 ; i >= 0  ; i = i -1 ) {
			Scanner sc= null;
			try 
			{
				sc= new Scanner (new BufferedReader(new FileReader(download.fileName[i])));
				sc.nextLine();
				while (sc.hasNextLine()) {
					System.out.println("Fülle gelich datenab nk 1");
					String temp[] = new String[7];
					InputLine = sc.nextLine();
					InputLine = InputLine.replaceAll(" ", ",");
					String[] inArr = InputLine.split(",");
					for (int x = 0; x < inArr.length; x++) {
						System.out.println(temp.length);
						System.out.println(inArr.length);
						temp[x%(temp.length)]=inArr[x];
					}
					Rowc++;
					float val = Float.parseFloat(temp[4]);
					String s = aktie.correctTheDate(temp[0], temp[1]);
					s = s.replaceAll("Z",":00");
					String [] ar = s.split("T");
					String d = ar[0];
					String t = ar[1];
					aktie a = new aktie(db.getIDfromStock(con, download.stockSymbol), d, combination.toSQLTime(t), val);
					System.out.println(a);
					dataList.add(a);
				}
			}catch (Exception e) {
				System.out.println(e);
			}
		}
		System.out.println("Fülle gelich datenab nk 3");
		pushCSVToDatabase(con,db);
	}
	static void pushCSVToDatabase(Connection con,  DBmanager db) throws SQLException, ParseException {
		int Passcounter = 0;
		int Errorcounter = 0;
		if (db.stockAlreadyExistsInAktienList(con, download.stockSymbol) == false ){
			aktienListe l = new aktienListe (1, download.stockSymbol);
			db.newAktieInAktienListe(con, l);
		}
		for (int Rowc = 0; Rowc < dataList.size(); Rowc++) {
			System.out.println("Fülle gelich datenab nk 3");
			boolean okay = db.stockRowAlreadyExists(con,db.getIDfromStock(con, download.stockSymbol),dataList.get(Rowc).getDate().toString(), dataList.get(Rowc).getTimestamp().toString());
			if (okay==true) {
				aktie temp = dataList.get(Rowc);
				System.out.println(temp);
				db.saveNewSpecificStockValue(con,temp);
				Passcounter++;
			}else {
				Errorcounter++;
			}
		}
		System.out.println("Es wurden " + Passcounter + " neue Einträge eingetragen " + Errorcounter + " Einträge waren schon vorhanden");
	}

}