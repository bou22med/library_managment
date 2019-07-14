import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class connexion {
	Connection conn=null;
	public static Connection Connectiondb(){
		try {
			 Class.forName("org.sqlite.JDBC");
		       Connection conn=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Admin\\workspace\\Memoire_licence\\Memoire_gestBibiotheque.sqlite");
		       //JOptionPane.showMessageDialog(null,"ghhhh");
		       return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e);
			return null;
		}}
}
