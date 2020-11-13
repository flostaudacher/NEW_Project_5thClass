import java.io.BufferedReader;
import java.io.FileReader;
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
		System.out.println(download.fileName.length);
		for (int i = download.fileName.length-1 ; i >= 0  ; i = i -1 ) {
			Scanner sc= null;
			try 
			{
				System.out.println();
				sc= new Scanner (new BufferedReader(new FileReader(download.fileName[i])));
				sc.nextLine();
				while (sc.hasNextLine()) {
					InputLine = sc.nextLine();
					InputLine = InputLine.replaceAll(" ", ",");
					String[] inArr = InputLine.split(",");
					for (int x = 0; x < inArr.length; x++) {
						CombinationArray[Rowc][x]=inArr[x];
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

}