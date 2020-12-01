
import java.sql.Connection;
import java.sql.SQLException;

public class main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		download.deleteCurrentFiles();
		DBmanager db = new DBmanager();
		Connection con = db.getConnection();
		download.deleteCurrentFiles();
		download.download();
		combination.combine();
		String [][] stock = new String[combination.getLengthOfNeededArray()][combination.getCols()];
		aktie[] a = new aktie[combination.getLengthOfNeededArray()];
		combination.fillStockArray(stock);
		combination.pushToDatabase(con, a, stock,db);
		System.out.println("Finish !");
	}
}
