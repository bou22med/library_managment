import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class InscriptionUser extends JFrame {

	private JPanel contentPane;
	private JTextField nom;
	private JLabel lblPrenom;
	private JTextField prenom;
	private JLabel lblFiliere;
	private JLabel lblSpecialite;
	private JLabel lblNumeroDeLa;
	private JLabel lblUsename;
	private JTextField usename;
	private JLabel lblPassword;
	private JTextField pass;
	private JLabel label_6;
	private JTextField id;
	private JButton btnRetour;
	private JButton btnInscrire;

	/**
	 * Launch the application.
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
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InscriptionUser frame = new InscriptionUser();
					frame.setVisible(false);
					frame.setResizable(false);
					frame.setTitle("Inscription Utilisateur");
					frame.resize(980,614);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn=null;
	ResultSet rs;
	PreparedStatement pst;
	private JTextField Adres;
	private JTextField tel;
	private JTextField annee;
	/**
	 * Create the frame.
	 */
	public InscriptionUser() {
		conn=connexion.Connectiondb();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 981, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("Nom");
		lblId.setBounds(72, 58, 46, 14);
		panel.add(lblId);
		
		nom = new JTextField();
		nom.setBounds(235, 55, 162, 20);
		panel.add(nom);
		nom.setColumns(10);
		
		lblPrenom = new JLabel("Prenom");
		lblPrenom.setBounds(72, 120, 46, 14);
		panel.add(lblPrenom);
		
		prenom = new JTextField();
		prenom.setColumns(10);
		prenom.setBounds(235, 117, 162, 20);
		panel.add(prenom);
		
		lblFiliere = new JLabel("Adresse");
		lblFiliere.setBounds(72, 181, 46, 14);
		panel.add(lblFiliere);
		
		lblSpecialite = new JLabel("Numero telephone");
		lblSpecialite.setBounds(72, 256, 125, 14);
		panel.add(lblSpecialite);
		
		lblNumeroDeLa = new JLabel("Filiere");
		lblNumeroDeLa.setBounds(72, 320, 153, 14);
		panel.add(lblNumeroDeLa);
		
		lblUsename = new JLabel("Usename");
		lblUsename.setBounds(72, 428, 46, 14);
		panel.add(lblUsename);
		
		usename = new JTextField();
		usename.setColumns(10);
		usename.setBounds(235, 425, 162, 20);
		panel.add(usename);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(72, 488, 46, 14);
		panel.add(lblPassword);
		
		pass = new JTextField();
		pass.setColumns(10);
		pass.setBounds(235, 485, 162, 20);
		panel.add(pass);
		
		label_6 = new JLabel("Id");
		label_6.setBounds(72, 14, 46, 14);
		panel.add(label_6);
		
		id = new JTextField();
		id.setEditable(false);
		id.setColumns(10);
		id.setBounds(235, 11, 162, 20);
		panel.add(id);
		JComboBox fil = new JComboBox();
		fil.setModel(new DefaultComboBoxModel(new String[] {"Math", "Informatique"}));
		fil.setBounds(235, 317, 162, 20);
		panel.add(fil);
		
		btnRetour = new JButton("Retour");
		btnRetour.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\retour.png"));
		btnRetour.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Utilisateur user=new Utilisateur();
				user.setVisible(true);
			}
		});
		btnRetour.setBounds(806, 535, 131, 23);
		panel.add(btnRetour);
		btnInscrire = new JButton("Confirmer");
		btnInscrire.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\ajout.png"));
		btnInscrire.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnInscrire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(nom.getText().toString().equals("") && prenom.getText().toString().equals("") && Adres.getText().toString().equals("") && tel.getText().toString().equals(""))
					JOptionPane.showMessageDialog(null,"remplisser les champs");
				
					else{
				try {
					String sql="insert into Lecteur (Nom,Prenom,Adresse,n_tel,Filiere,Annee,username,pwd) values (?,?,?,?,?,?,?,?)";
					pst=conn.prepareStatement(sql);
					//pst.setString(1,id.getText());
					pst.setString(1,nom.getText());
					pst.setString(2,prenom.getText());
					pst.setString(3,Adres.getText() );
					pst.setString(4,tel.getText());
					pst.setString(5,(String) fil.getSelectedItem() );
					pst.setString(6,annee.getText());
					pst.setString(7,usename.getText());
					if(md5(pass.getText()).equals("")){
						return;
					}
					pst.setString(8,md5(pass.getText()));
					
					
					//pst.setString(8,pass.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null,"le compte a ete creer avec succes");
					rs.close();
					pst.close();
					
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(null, e);
				}
			}}
		});
		btnInscrire.setBounds(631, 535, 153, 23);
		panel.add(btnInscrire);
		
		Adres = new JTextField();
		Adres.setBounds(235, 178, 162, 20);
		panel.add(Adres);
		Adres.setColumns(10);
		
		tel = new JTextField();
		tel.setBounds(235, 253, 162, 20);
		panel.add(tel);
		tel.setColumns(10);
		
		
		
		JLabel lblNewLabel = new JLabel("Annee");
		lblNewLabel.setBounds(72, 381, 46, 14);
		panel.add(lblNewLabel);
		
		annee = new JTextField();
		annee.setBounds(235, 378, 162, 20);
		panel.add(annee);
		annee.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Admin\\workspace\\Memoire_licence\\img\\user.png"));
		label.setBounds(700, 117, 153, 198);
		panel.add(label);
		
		
	}
}
