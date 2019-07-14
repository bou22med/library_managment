import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class gstLivre extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField cdouvre;
	private JTextField titre;
	private JTextField auteur;
	private JTextField lieuedit;
	private JTextField textField;
	private JComboBox type;
	private JDateChooser dateChooser;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gstLivre frame = new gstLivre();
					frame.setResizable(false);
					frame.setVisible(false);
					frame.resize(1014,621);
					frame.setTitle("Gestion des Livres");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
public void effacer(){
		cdouvre.setText("");
		titre.setText("");
		type.setSelectedItem("Livre");;
		auteur.setText("");
		textField_1.setText("");
		dateChooser.setDate(null);
		lieuedit.setText("");
		}
public void actualiser(){
		
		try {
			String sql="select * from Ouvrage ";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Create the frame.
	 */
	Connection conn=null;
	ResultSet rs;
	PreparedStatement pst;
	private JTextField textField_1;
	public gstLivre() {
		conn=connexion.Connectiondb();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1001, 619);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(354, 92, 621, 302);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					
					
					int row=table.getSelectedRow();
					String id=(table.getModel().getValueAt(row, 0)).toString();
					
					String sql="select * from Ouvrage where Ouvrage.code_ouvre='"+id+"' ";
					pst=conn.prepareStatement(sql);
					rs=pst.executeQuery();
					while(rs.next()){
						cdouvre.setText(rs.getString("code_ouvre"));
						titre.setText(rs.getString("titre"));
						type.setSelectedItem(rs.getString("type"));
						auteur.setText(rs.getString("auteur"));
						textField_1.setText(rs.getString("Nbr_exemplaire"));
						dateChooser.setDate(rs.getDate("dt_edit"));
						
						
					
						 lieuedit.setText(rs.getString("lieu_edit"));
						//specialite.setText(rs.getString("Specialite"));
						
						
					}
					pst.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
					// TODO: handle exception
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Code Ouvrage");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(28, 108, 117, 19);
		panel.add(lblNewLabel);
		
		cdouvre = new JTextField();
		cdouvre.setBounds(181, 107, 137, 20);
		panel.add(cdouvre);
		cdouvre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Titre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(28, 147, 117, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblTypeDouvrage = new JLabel("Type d'ouvrage");
		lblTypeDouvrage.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTypeDouvrage.setBounds(28, 179, 117, 20);
		panel.add(lblTypeDouvrage);
		
		titre = new JTextField();
		titre.setBounds(181, 146, 137, 20);
		panel.add(titre);
		titre.setColumns(10);
		
		 type = new JComboBox();
		type.setModel(new DefaultComboBoxModel(new String[] {"Livre", "Memoire"}));
		type.setBounds(181, 181, 137, 20);
		panel.add(type);
		
		JLabel lblNewLabel_2 = new JLabel("Auteur");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(28, 223, 90, 14);
		panel.add(lblNewLabel_2);
		
		auteur = new JTextField();
		auteur.setBounds(181, 222, 137, 20);
		panel.add(auteur);
		auteur.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Date d'edition");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(28, 319, 117, 17);
		panel.add(lblNewLabel_3);
		
		JLabel lblLieuDedition = new JLabel("Lieu d'edition");
		lblLieuDedition.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLieuDedition.setBounds(28, 361, 117, 14);
		panel.add(lblLieuDedition);
		
		lieuedit = new JTextField();
		lieuedit.setBounds(181, 360, 137, 20);
		panel.add(lieuedit);
		lieuedit.setColumns(10);
		
		JButton btnEffacer = new JButton("Effacer");
		btnEffacer.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\effacer.png"));
		btnEffacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				effacer();
			}
		});
		btnEffacer.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEffacer.setBounds(104, 447, 117, 23);
		panel.add(btnEffacer);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\retour.png"));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				homeAdmin home=new homeAdmin();
				home.setVisible(true);
			}
		});
		btnRetour.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRetour.setBounds(870, 515, 105, 23);
		panel.add(btnRetour);
		
		JButton btnSupprmer = new JButton("Supprimer");
		btnSupprmer.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\suup.png"));
		btnSupprmer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int action=JOptionPane.showConfirmDialog(null,"est que vous etes sur pour supprimer","supprime",JOptionPane.YES_NO_OPTION);
				if(action==0)
				try {
					String query="delete from Ouvrage  where code_ouvre='"+cdouvre.getText()+"'";
					PreparedStatement pst=conn.prepareStatement(query);
					
					pst.execute();
					JOptionPane.showMessageDialog(null,"la suppression a ete faite avec succee");
					pst.close();
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(null,e);
				}actualiser();
			}
		});
		btnSupprmer.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSupprmer.setBounds(740, 515, 120, 23);
		panel.add(btnSupprmer);
		
		JButton btnNewButton = new JButton("Modifier");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\modifier.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					SimpleDateFormat format=new SimpleDateFormat("YYYY-MM-DD");
					String date=format.format(dateChooser.getDate());
					String query="update Ouvrage set code_ouvre='"+cdouvre.getText()+"', titre='"+titre.getText()+"', type='"+type.getSelectedItem()+"',auteur='"+auteur.getText()+"',Nbr_exemplaire='"+textField_1.getText()+"',dt_edit='"+date+"',lieu_edit='"+lieuedit.getText()+"' where code_ouvre='"+cdouvre.getText()+"'";
					//String query="update Ouvrage set code_ouvre='"+cdouvre.getText()+"', titre='"+titre.getText()+"', type='"+type.getSelectedItem()+"',auteur='"+auteur.getText()+"',Nbr_exemplaire='"+textField_1.getText()+"',dt_edit='"+((JTextField)dateChooser.getDateEditor().getUiComponent()).getText()+"',lieu_edit='"+lieuedit.getText()+"' where code_ouvre='"+cdouvre.getText()+"'";
					PreparedStatement pst=conn.prepareStatement(query);
					
					pst.execute();
					JOptionPane.showMessageDialog(null,"le changement a ete bien fait");
					pst.close();
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(null,e);
				}actualiser();
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(613, 515, 117, 23);
		panel.add(btnNewButton);

		
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\ajout.png"));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cdouvre.getText().toString().equals("") && titre.getText().toString().equals("") && auteur.getText().toString().equals("") && lieuedit.getText().toString().equals("")  ){
					JOptionPane.showMessageDialog(null,"remplisser les champs");
				}else{
				try {
					SimpleDateFormat format=new SimpleDateFormat("YYYY-MM-DD");
					String date=format.format(dateChooser.getDate());
					
					String sql="insert into Ouvrage (Code_ouvre,titre,type,auteur,Nbr_exemplaire,dt_edit,lieu_edit) values (?,?,?,?,?,?,?)";
					pst=conn.prepareStatement(sql);
					//pst.setString(1,id.getText());
					pst.setString(1,cdouvre.getText());
					pst.setString(2,titre.getText());
					pst.setString(3,(String) type.getSelectedItem() );
					pst.setString(4,auteur.getText());
					pst.setString(5,textField_1.getText());
					pst.setString(6,((JTextField)dateChooser.getDateEditor().getUiComponent()).getText());
					//pst.setString(6,date);
					pst.setString(7,lieuedit.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null,"l'ajout du compte est fait avec succes");
					rs.close();
					pst.close();
					
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(null, e);
				}actualiser();}
				
			}
		});
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAjouter.setBounds(498, 515, 105, 23);
		panel.add(btnAjouter);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					//String selection=(String) comboBox.getSelectedItem();
					//String sql="select * from Ouvrage  where "+selection+"=? like '%"+selection+"%'";
					String sql="select * from Ouvrage  where titre like '%"+textField.getText()+"%' or type like '%"+textField.getText()+"%' or code_ouvre like '%"+textField.getText()+"%' or auteur like '%"+textField.getText()+"%'";
					pst=conn.prepareStatement(sql);
					//pst.setString(1, textField.getText());
					rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
				} catch (Exception e) {
					//e.printStackTrace();
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		textField.setBounds(533, 61, 165, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\rech.png"));
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualiser();
			}
		});
		btnRechercher.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRechercher.setBounds(812, 60, 153, 23);
		panel.add(btnRechercher);
		
		 dateChooser = new JDateChooser();
		dateChooser.setBounds(182, 316, 136, 20);
		panel.add(dateChooser);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Edition", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		panel_2.setBounds(10, 293, 334, 143);
		panel.add(panel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Nombre d'exemplaire");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(28, 256, 137, 19);
		panel.add(lblNewLabel_4);
		
		textField_1 = new JTextField();
		textField_1.setBounds(181, 256, 137, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Ouvrage", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		panel_1.setBounds(10, 81, 334, 201);
		panel.add(panel_1);
		actualiser();
	}
}
