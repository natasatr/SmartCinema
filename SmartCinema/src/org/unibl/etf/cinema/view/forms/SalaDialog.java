package org.unibl.etf.cinema.view.forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.cinema.data.dao.DAOFactory;
import org.unibl.etf.cinema.data.dao.KinoDAO;
import org.unibl.etf.cinema.data.dao.SalaDAO;
import org.unibl.etf.cinema.data.dao.mysql.MySQLKinoDAO;
import org.unibl.etf.cinema.data.dto.KinoDTO;
import org.unibl.etf.cinema.data.dto.SalaDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class SalaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	
	private SalaDAO salaDAO=DAOFactory.getDAOFactory().getSalaDAO();
	private KinoDAO kinoDAO=DAOFactory.getDAOFactory().getKinoDAO();
	//private MySQLKinoDAO kinoSQL
	private DodatnaPonudaSearchFrame frame;
	private SalaDTO salaDTO;
	private JTable table;
	private KinoDTO kinoDTO;

	/**
	 * Launch the application.
	 */
	
	public SalaDialog(DodatnaPonudaSearchFrame frame,SalaDTO salaDTO )
	{
		this();
		this.salaDTO= salaDTO;
		this.frame=frame;
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SalaDialog dialog = new SalaDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SalaDialog() {
		setBounds(100, 100, 321, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblInformacijeOSali = new JLabel("Informacije o sali");
		lblInformacijeOSali.setFont(new Font("Arial", Font.PLAIN, 18));
		JLabel lblBrojSale = new JLabel("Broj sale");
		lblBrojSale.setFont(new Font("Arial", Font.PLAIN, 13));
		textField = new JTextField();
		textField.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblBrojSale)
							.addGap(55)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblInformacijeOSali))
					.addContainerGap(216, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(25)
					.addComponent(lblInformacijeOSali)
					.addGap(66)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBrojSale)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(91, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						sacuvajSalu();
					
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void sacuvajSalu() {
		String polje1 = textField.getText();
		int broj=Integer.parseInt(polje1);
		KinoDTO kino=kinoDAO.svaKina().get(0);
		SalaDTO dp=new SalaDTO(broj, kino);
		
		if(salaDTO==null) {
		try
		{
			salaDAO.dodajSalu(dp);
			//setVisible(false);
			dispose();
			//frame.refreshDP();
			
			JOptionPane.showMessageDialog(frame, "Sala dodana uspijesno.","Sala je dodana", JOptionPane.INFORMATION_MESSAGE);
		}catch(Exception ee)
		{
			JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " + ee.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
		}	
		
	}else {try
	{
		salaDAO.azurirajSalu(dp);
		
		//setVisible(false);
		dispose();
		//frame.refreshDP();
		
		JOptionPane.showMessageDialog(frame, "Sala azurirana uspijesno.","Sala je dodana", JOptionPane.INFORMATION_MESSAGE);
	}catch(Exception ee)
	{
		JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " + ee.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
	}
		
	}
		((DodatnaPonudaSearchFrame )frame).azurirajTabeluSala();
	}
	
	public void dodajSalu() {
		String polje1 = textField.getText();
		int broj=Integer.parseInt(polje1);
		KinoDTO kino=kinoDAO.svaKina().get(0);
		SalaDTO dp=new SalaDTO(broj, kino);
		
		if(salaDTO==null) {
		try
		{
			salaDAO.dodajSalu(dp);
			setVisible(false);
			dispose();
			//frame.refreshDP();
			
			JOptionPane.showMessageDialog(frame, "Sala dodana uspijesno.","Sala je dodana", JOptionPane.INFORMATION_MESSAGE);
		}catch(Exception ee)
		{
			JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " + ee.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
		}	
		
	}else {try
	{
		salaDAO.azurirajSalu(dp);
		
		setVisible(false);
		dispose();
		//frame.refreshDP();
		
		JOptionPane.showMessageDialog(frame, "Sala azurirana uspijesno.","Sala je dodana", JOptionPane.INFORMATION_MESSAGE);
	}catch(Exception ee)
	{
		JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " + ee.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
	}
		
	}
		((DodatnaPonudaSearchFrame )frame).azurirajTabeluSala();
		
	
		
	
	}

}
