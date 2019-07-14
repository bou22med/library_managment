import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.Label;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class AdminLogin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
					frame.setResizable(false);
					frame.resize(529, 298);
					frame.setTitle("Authentification Admin");
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */ 
		Connection conn=null;
		ResultSet rs;
		PreparedStatement pst;
		private JPasswordField passwordField;
	public AdminLogin() {
		conn=connexion.Connectiondb();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 298);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(41, 85, 109, 14);
		panel.add(lblPassword);
		
		JButton btnNewButton = new JButton("Connecter");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sql="select * from admin where pwd=?";
				try {
					pst=conn.prepareStatement(sql);
					
					pst.setString(1,passwordField.getText());
					rs=pst.executeQuery();
					if(rs.next()){
						//JOptionPane.showMessageDialog(null, "mot de pass est correcte");
						rs.close();
						pst.close();
						setVisible(false);
						homeAdmin opr=new homeAdmin();
						opr.setVisible(true);}
						else{
							JOptionPane.showMessageDialog(null, "le mot de pass est incorect");
					}
					
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}finally {
					try {
						rs.close();
						pst.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				}
			}
		});
		btnNewButton.setBounds(41, 188, 142, 23);
		panel.add(btnNewButton);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\admin.png"));
		label.setBounds(346, 83, 131, 131);
		panel.add(label);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(169, 83, 142, 20);
		panel.add(passwordField);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Home hmm=new Home();
				hmm.setVisible(true);
			}
		});
		btnRetour.setForeground(Color.BLACK);
		btnRetour.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRetour.setBounds(194, 189, 142, 23);
		panel.add(btnRetour);
	}
}
