import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class gstReservation extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
    private JDateChooser dateChooser_1;
    private JDateChooser dateChooser;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gstReservation frame = new gstReservation();
					frame.setVisible(false);
					frame.setResizable(false);
					frame.resize(1014, 621);
					frame.setTitle("Reservation des Livres");
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
public void actualiser(){
		
		try {
			String sql="select * from Pret";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public gstReservation() {
		conn=connexion.Connectiondb();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1009, 617);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(298, 107, 675, 299);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					int row=table.getSelectedRow();
					String id=(table.getModel().getValueAt(row, 0)).toString();
					String sql="select * from Pret  where id='"+id+"'";
					pst=conn.prepareStatement(sql);
					rs=pst.executeQuery();
					while(rs.next()){
						textField.setText(rs.getString("code_ouvre"));
						textField_1.setText(rs.getString("titre"));
						textField_2.setText(rs.getString("code_carte_etudiant"));
						textField_3.setText(rs.getString("Nom"));
						dateChooser.setDate(rs.getDate("date_de_pret"));
						//dateChooser.setDate(dateChooser.getCalendar());
						dateChooser_1.setDate(rs.getDate("date_de_restitution"));
						
					}
					pst.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblCodeDouvre = new JLabel("code d'ouvre");
		lblCodeDouvre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCodeDouvre.setBounds(10, 109, 102, 18);
		panel.add(lblCodeDouvre);
		
		textField = new JTextField();
		textField.setBounds(179, 107, 109, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblTitre = new JLabel("titre");
		lblTitre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTitre.setBounds(10, 158, 102, 18);
		panel.add(lblTitre);
		
		textField_1 = new JTextField();
		textField_1.setBounds(179, 156, 109, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCodeCarteEtudiant = new JLabel("Code carte etudiant");
		lblCodeCarteEtudiant.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCodeCarteEtudiant.setBounds(10, 216, 135, 20);
		panel.add(lblCodeCarteEtudiant);
		
		textField_2 = new JTextField();
		textField_2.setBounds(179, 217, 109, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom etudiant");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNom.setBounds(10, 278, 116, 14);
		panel.add(lblNom);
		
		textField_3 = new JTextField();
		textField_3.setBounds(179, 276, 109, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblDateDePret = new JLabel("Date de pret");
		lblDateDePret.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDateDePret.setBounds(10, 338, 135, 18);
		panel.add(lblDateDePret);
		
		JLabel lblDateDeRestitution = new JLabel("Date de restitution");
		lblDateDeRestitution.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDateDeRestitution.setBounds(10, 392, 135, 14);
		panel.add(lblDateDeRestitution);
		
		 dateChooser = new JDateChooser();
		dateChooser.setBounds(179, 338, 109, 20);
		panel.add(dateChooser);
		
		 dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(179, 386, 109, 20);
		panel.add(dateChooser_1);
		
		JButton btnEffacer = new JButton("Effacer");
		btnEffacer.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEffacer.setBounds(97, 430, 109, 23);
		panel.add(btnEffacer);
		
		JButton btnAnnulerReservation = new JButton("Annuler Reservation");
		btnAnnulerReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int action=JOptionPane.showConfirmDialog(null,"est ce que vous etes sur pour annuler la reservation","Annuler",JOptionPane.YES_NO_OPTION);
				if(action==0){
				try {
					String query="delete from Pret ";
					PreparedStatement pst=conn.prepareStatement(query);
					
					pst.execute();
					JOptionPane.showMessageDialog(null,"la reservation a ete annulee");
					pst.close();
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(null,e);
				}actualiser();
			}}
		});
		btnAnnulerReservation.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAnnulerReservation.setBounds(791, 462, 182, 23);
		panel.add(btnAnnulerReservation);
		
		JButton btnNewButton = new JButton("Confirmer Reservation");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().toString().equals("") && textField_2.getText().toString().equals("")   ){
					JOptionPane.showMessageDialog(null,"remplisser les champs");
				}
				else{
					try {
						SimpleDateFormat format=new SimpleDateFormat("YYYY-MM-DD");
						String date=format.format(dateChooser.getDate());
						
						SimpleDateFormat formatt=new SimpleDateFormat("YYYY-MM-DD");
						String dat=format.format(dateChooser_1.getDate());
						
						String sql="insert into Preter (Code_carte_etudiant,code_ouvre,date_de_pret,date_de_restitution) values (?,?,?,?)";
						pst=conn.prepareStatement(sql);
						//pst.setString(1,id.getText());
						pst.setString(1,textField_2.getText());
						pst.setString(2,textField.getText());
						
						
						
						//pst.setString(6,((JTextField)dateChooser.getDateEditor().getUiComponent()).getText());
						pst.setString(3,date);
						pst.setString(4,dat);
						
						pst.execute();
						JOptionPane.showMessageDialog(null,"le livre est prete");
						rs.close();
						pst.close();
						
					} catch (Exception e) {
						//JOptionPane.showMessageDialog(null, e);
					}actualiser();}
					
				}
				
					
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(590, 463, 191, 23);
		panel.add(btnNewButton);
		
		JButton btnLivrePrete = new JButton("livres prete");
		btnLivrePrete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				livrePret pret=new livrePret();
				pret.setVisible(true);
				pret.actualiserr();
			}
		});
		btnLivrePrete.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLivrePrete.setBounds(397, 462, 182, 23);
		panel.add(btnLivrePrete);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				homeAdmin home=new homeAdmin();
				home.setVisible(true);
			}
		});
		btnRetour.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRetour.setBounds(599, 519, 182, 23);
		panel.add(btnRetour);
		actualiser();
	}
}
