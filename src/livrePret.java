import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;

public class livrePret extends JFrame {

	private JPanel contentPane;
	 private static JTable tab;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					livrePret frame = new livrePret();
					frame.setVisible(false);
					frame.setResizable(false);
					frame.resize(694, 408);
					frame.setTitle("Livre Prete");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	 
		static Connection conn=null;
		static ResultSet rs;
		static PreparedStatement pst;
	
public static void actualiserr(){
		
		try {
			String sql="select * from Preter";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			tab.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, e);
		}
	}
	public livrePret() {
		
		conn=connexion.Connectiondb();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 694, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 11, 606, 286);
		panel.add(scrollPane);
		
		tab = new JTable();
		scrollPane.setViewportView(tab);
		
		JButton btnAnnulerPret = new JButton("Annuler Pret");
		btnAnnulerPret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int action=JOptionPane.showConfirmDialog(null,"est ce que vous etes sur pour annuler le pret","Annuler",JOptionPane.YES_NO_OPTION);
				if(action==0){
				try {
					String query="delete from Preter ";
					PreparedStatement pst=conn.prepareStatement(query);
					
					pst.execute();
					JOptionPane.showMessageDialog(null,"le pret est annulee");
					pst.close();
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(null,e);
				}actualiserr();
			}}
			
		});
		btnAnnulerPret.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\suup.png"));
		btnAnnulerPret.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAnnulerPret.setBounds(133, 308, 140, 23);
		panel.add(btnAnnulerPret);
		
		JButton btnNewButton = new JButton("Retour");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				gstReservation res=new gstReservation();
				//res.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\retour.png"));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(338, 308, 146, 23);
		panel.add(btnNewButton);
		actualiserr();
	}
	
}
