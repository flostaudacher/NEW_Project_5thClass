import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class download {
	private static final int PARTS_OF_API = 24;
	public static String stockSymbol;
	static String[] months = new String [PARTS_OF_API];
	static String[] url = new String [PARTS_OF_API];
	static String[] fileName = new String [PARTS_OF_API];
	static String fileNameBase = "C:\\Users\\flost\\eclipse-workspace\\ETF_BACKTESTING_BONUS\\data\\";
	private static final File DIRECTORYDATA= new File ("C:\\Users\\flost\\eclipse-workspace\\ETF_BACKTESTING_BONUS\\data");
	public static void download() {
		fillMonthsArray();
		String symbol = getSymbol();
		setSymbol(symbol);
		int zeit = 1;
		for (int i = 0; i < PARTS_OF_API; i++) {
			url[i] = createurl(i,symbol);
			fileName[i] =  fileNameBase + symbol +"_"+ months[i] +".csv";
			try (BufferedInputStream in = new BufferedInputStream(new URL(url[i]).openStream());
					FileOutputStream fileOutputStream =  new FileOutputStream(fileName[i])) {
				byte dataBuffer[]= new byte[1024];
				int bytesRead;
				while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
					fileOutputStream.write(dataBuffer, 0, bytesRead);
				}
			} catch (IOException e) {
				System.out.println("");
			}
			if (i +1 == 5*zeit) {
				try{
					zeit++;
				    Thread.sleep(60000);
				}catch(InterruptedException e){
				    e.printStackTrace();
				}
			}
		}
		zeit = 0;
	}
	private static void setSymbol(String symbol) {
		stockSymbol = symbol;
	}
	public static String getSymbol() {
		Scanner scanner = new Scanner(System.in); 
		System.out.print("Zu untersuchenedes Symbol : ");
		String s = scanner.nextLine();
		return s;
	}
	public static void deleteCurrentFiles() {
		File location= new File ("C:\\Users\\flost\\eclipse-workspace\\ETF_BACKTESTING_BONUS\\data");
		File[] files = location.listFiles();
		for (File file : files) {
			if (!file.delete()) {
				System.out.println("failed to delete");
			}
		}
	}
	private static void fillMonthsArray() {
		for (int i = 0; i < PARTS_OF_API; i++) {
			if (i < 12) {
				months [i] = "year1month" + (i+1);
			} else {
				months [i] = "year2month" + (i+1-12);
			}
		}
	}
	static String createurl(int Month, String wantedSymbol) {
		String baseURL = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY_EXTENDED&symbol=";
		String symbol = wantedSymbol;
		String extdendetBaseOne = "&interval=";
		String interval = "15min";
		String extdendetBaseTwo = "&slice=";
		String usedMonth = months[Month];
		String extdendetBaseThree = "&apikey=";
		String apiKey = "QGEDE2GAG8OY3OBU";
		String URL = baseURL + symbol +  extdendetBaseOne + interval + extdendetBaseTwo + usedMonth + extdendetBaseThree + apiKey;
		//System.out.println(URL);
		return URL;
	}
}
