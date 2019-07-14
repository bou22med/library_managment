import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;



public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	Connection conn=null;
	ResultSet rs;
	PreparedStatement pst;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(false);
					frame.setResizable(false);
					frame.resize(1014, 621);
					frame.setTitle("Acceuil");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		conn=connexion.Connectiondb();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1017, 596);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		Image img=new ImageIcon(this.getClass().getResource("/home.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));;
		lblNewLabel.setBounds(194, 0, 817, 560);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				AdminLogin adm=new AdminLogin();
				adm.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\computer.png"));
		btnNewButton.setBounds(29, 147, 127, 97);
		panel.add(btnNewButton);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Utilisateur use=new Utilisateur();
				use.setVisible(true);
			}
		});
		button.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\male_user.png"));
		button.setBounds(29, 291, 127, 97);
		panel.add(button);
		
		JLabel lblUtilisateur = new JLabel("Utilisateur");
		lblUtilisateur.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUtilisateur.setBounds(29, 421, 127, 14);
		panel.add(lblUtilisateur);
		
		JLabel lblAdministrateur = new JLabel("Administrateur");
		lblAdministrateur.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAdministrateur.setBounds(29, 122, 127, 14);
		panel.add(lblAdministrateur);
	}

}
