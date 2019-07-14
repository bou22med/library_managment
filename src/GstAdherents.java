import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class GstAdherents extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GstAdherents frame = new GstAdherents();
					frame.resize(1014, 621);
					frame.setResizable(false);
					frame.setTitle("Gestion des Adherents");
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

	
	private String md5(String c){
		try {
			MessageDigest digst=MessageDigest.getInstance("MD5");
			digst.update(new String(c).getBytes("UTF8"));
			
			String str=new String(digst.digest());
			return str;
		} catch (Exception e) {
			return"";
		}
		
	}
	
	Connection conn=null;
	ResultSet rs;
	PreparedStatement pst;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField adres;
	private JTextField textField_4;
	private JTextField nmr;
	private JComboBox comboBox_1;
	public void effacer(){
		textField_2.setText("");
		textField_3.setText("");
		adres.setText("");
		nmr.setText("");
		comboBox_1.setSelectedItem("Math");;
		textField_4.setText("");
		textField_5.setText("");
		textField_6.setText("");
		
		
		}
	
	public void actualiser(){
		
		try {
			String sql="select * from Lecteur";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public GstAdherents() {
		conn=connexion.Connectiondb();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1014, 621);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"id", "Nom", "Prenom", "Specialite"}));
		comboBox.setBounds(588, 53, 101, 20);
		panel.add(comboBox);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				try {
					String selection=(String) comboBox.getSelectedItem();
					String sql="select * from Lecteur  where "+selection+"=?";
					pst=conn.prepareStatement(sql);
					pst.setString(1, textField.getText());
					rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
				} catch (Exception e) {
					//e.printStackTrace();
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		textField.setBounds(805, 53, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(358, 106, 593, 388);
		panel.add(scrollPane);
		
		 comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Math", "Informatique"}));
		comboBox_1.setBounds(173, 311, 162, 20);
		panel.add(comboBox_1);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				try {
					int row=table.getSelectedRow();
					String id=(table.getModel().getValueAt(row, 0)).toString();
					String sql="select * from Lecteur where id='"+id+"'";
					pst=conn.prepareStatement(sql);
					rs=pst.executeQuery();
					while(rs.next()){
						textField_1.setText(rs.getString("id"));
						textField_2.setText(rs.getString("Nom"));
						textField_3.setText(rs.getString("Prenom"));
						adres.setText(rs.getString("Adresse"));
						nmr.setText(rs.getString("n_tel"));
						 comboBox_1.setSelectedItem(rs.getString("Filiere"));
						 textField_4.setText(rs.getString("Annee"));
						//specialite.setText(rs.getString("Specialite"));
						
						textField_5.setText(rs.getString("username"));
						textField_6.setText(rs.getString("pwd"));
					}
					pst.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("Id");
		label.setBounds(10, 31, 46, 14);
		panel.add(label);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(173, 28, 162, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(173, 72, 162, 20);
		panel.add(textField_2);
		
		JLabel label_1 = new JLabel("Nom");
		label_1.setBounds(10, 75, 46, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Prenom");
		label_2.setBounds(10, 123, 46, 14);
		panel.add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(173, 120, 162, 20);
		panel.add(textField_3);
		
	
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(10, 175, 46, 14);
		panel.add(lblAdresse);
		
		
		
		JLabel lblNumeroTelephone = new JLabel("Numero telephone");
		lblNumeroTelephone.setBounds(10, 243, 125, 14);
		panel.add(lblNumeroTelephone);
		
		JLabel lblFiliere = new JLabel("Filiere");
		lblFiliere.setBounds(10, 314, 153, 14);
		panel.add(lblFiliere);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(173, 435, 162, 20);
		panel.add(textField_5);
		
		JLabel label_6 = new JLabel("Usename");
		label_6.setBounds(10, 438, 80, 14);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("Password");
		label_7.setBounds(10, 505, 80, 14);
		panel.add(label_7);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(173, 502, 162, 20);
		panel.add(textField_6);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\ajout.png"));
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(adres.getText().toString().equals("") && textField_2.getText().toString().equals("") && textField_3.getText().toString().equals("") && nmr.getText().toString().equals("")){
					JOptionPane.showMessageDialog(null,"Remplissez les champs s'il vous plait");
				}
				else{
				try {
					String sql="insert into Lecteur (Nom,Prenom,Adresse,n_tel,Filiere,Annee,username,pwd) values (?,?,?,?,?,?,?,?)";
					pst=conn.prepareStatement(sql);
					//pst.setString(1,id.getText());
					pst.setString(1,textField_2.getText());
					pst.setString(2,textField_3.getText());
					pst.setString(3,adres.getText() );
					pst.setString(4,nmr.getText());
					pst.setString(5, (String) comboBox_1.getSelectedItem());
					pst.setString(6,adres.getText());
					pst.setString(7,textField_5.getText());
					
					if(md5(textField_6.getText()).equals("")){
						return;
					}
					pst.setString(8,md5(textField_6.getText()));
					//pst.setString(8,textField_6.getText());
					pst.execute();
					//JOptionPane.showMessageDialog(null,"l'ajout du compte est fait avec succes");
					rs.close();
					pst.close();
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}actualiser();}
			}
		});
		btnAjouter.setBounds(754, 545, 106, 23);
		panel.add(btnAjouter);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\suup.png"));
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int action=JOptionPane.showConfirmDialog(null,"voulez vous supprimer cet etudiant","supprime",JOptionPane.YES_NO_OPTION);
				if(action==0)
				try {
					String query="delete from Lecteur  where id='"+textField_1.getText()+"'";
					PreparedStatement pst=conn.prepareStatement(query);
					
					pst.execute();
					JOptionPane.showMessageDialog(null,"le changement a ete bien fait");
					pst.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,e);
				}actualiser();
			}
		});
		btnSupprimer.setBounds(619, 545, 125, 23);
		panel.add(btnSupprimer);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\modifier.png"));
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="update Lecteur set Nom='"+textField_2.getText()+"', Prenom='"+textField_3.getText()+"', Adresse='"+adres.getText()+"',n_tel='"+nmr.getText()+"',Filiere='"+comboBox_1.getSelectedItem()+"',Annee='"+textField_4.getText()+"' where id='"+textField_1.getText()+"'";
					PreparedStatement pst=conn.prepareStatement(query);
					
					pst.execute();
					JOptionPane.showMessageDialog(null,"le changement a ete bien fait");
					pst.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,e);
				}actualiser();
			}
		});
		btnModifier.setBounds(495, 545, 114, 23);
		panel.add(btnModifier);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\rech.png"));
		btnRechercher.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sql="select * from Lecteur";
				try {
					pst=conn.prepareStatement(sql);
					rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		btnRechercher.setBounds(358, 546, 127, 23);
		panel.add(btnRechercher);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				homeAdmin home=new homeAdmin();
				home.setVisible(true);
			}
		});
		btnRetour.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\retour.png"));
		btnRetour.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRetour.setBounds(870, 545, 108, 23);
		panel.add(btnRetour);
		
		adres = new JTextField();
		adres.setBounds(175, 172, 160, 20);
		panel.add(adres);
		adres.setColumns(10);
		
		JLabel lblAnnee = new JLabel("Annee");
		lblAnnee.setBounds(10, 380, 46, 14);
		panel.add(lblAnnee);
		
		textField_4 = new JTextField();
		textField_4.setBounds(175, 377, 160, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		nmr = new JTextField();
		nmr.setBounds(173, 240, 162, 20);
		panel.add(nmr);
		nmr.setColumns(10);
		
		JButton btnEffacer = new JButton("Effacer");
		btnEffacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				effacer();
			}
		});
		btnEffacer.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\effacer.png"));
		btnEffacer.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEffacer.setBounds(107, 546, 106, 23);
		panel.add(btnEffacer);
		actualiser();
	}
}
