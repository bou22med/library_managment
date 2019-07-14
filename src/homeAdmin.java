import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class homeAdmin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					homeAdmin frame = new homeAdmin();
					frame.setResizable(false);
					frame.setVisible(false);
					frame.resize(1014, 621);
					frame.setTitle("Acceuil Administrateur");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public homeAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 964, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 102, 153));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				gstLivre livre=new gstLivre();
				livre.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\livre.png"));
		btnNewButton.setBounds(80, 240, 157, 125);
		panel.add(btnNewButton);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GstAdherents adr=new GstAdherents();
				adr.setVisible(true);
			}
		});
		button.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\user.png"));
		button.setBounds(375, 240, 157, 125);
		panel.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				gstReservation res=new gstReservation();
				res.setVisible(true);
			}
		});
		button_1.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\about.png"));
		button_1.setBounds(668, 240, 157, 125);
		panel.add(button_1);
		
		JLabel lblGestionDesLivres = new JLabel("gestion des livres");
		lblGestionDesLivres.setForeground(new Color(255, 102, 51));
		lblGestionDesLivres.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGestionDesLivres.setBounds(80, 401, 157, 27);
		panel.add(lblGestionDesLivres);
		
		JLabel lblGestionDesAdherants = new JLabel("gestion des adherants");
		lblGestionDesAdherants.setForeground(new Color(255, 0, 51));
		lblGestionDesAdherants.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGestionDesAdherants.setBounds(375, 401, 186, 27);
		panel.add(lblGestionDesAdherants);
		
		JLabel lblStatistic = new JLabel("Gestion des reservations");
		lblStatistic.setForeground(new Color(255, 102, 102));
		lblStatistic.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStatistic.setBounds(639, 401, 186, 27);
		panel.add(lblStatistic);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Operations", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		panel_1.setBounds(80, 220, 750, 147);
		panel.add(panel_1);
	}

}
