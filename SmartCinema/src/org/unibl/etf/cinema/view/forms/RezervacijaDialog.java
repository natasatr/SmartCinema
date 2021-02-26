package org.unibl.etf.cinema.view.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.cinema.data.dao.DAOFactory;
import org.unibl.etf.cinema.data.dao.KartaDAO;
import org.unibl.etf.cinema.data.dao.RezervacijaDAO;
import org.unibl.etf.cinema.data.dto.KartaDTO;
import org.unibl.etf.cinema.data.dto.RezervacijaDTO;
import org.unibl.etf.cinema.util.UIUtils;

public class RezervacijaDialog extends JDialog {

	RezervacijaDAO rezervacijaDAO = DAOFactory.getDAOFactory().getRezervacijaDAO();
	KartaDAO kartaDAO = DAOFactory.getDAOFactory().getKartaDAO();
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	KartaDTO karta;
	RezervacijaDTO rezervacija;
	
	
	public RezervacijaDialog(KartaDTO karta) {
		this.karta = karta;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblNewLabel = new JLabel("Ime: ");
		JLabel lblNewLabel_1 = new JLabel("Prezime: ");
		textField = new JTextField();
		textField.setColumns(10);
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addGap(68)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(186, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(41)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(48)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(101, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dodajRezervaciju();
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Otka\u017Ei");
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						azurirajKartu();
					}
					
				});
				cancelButton.setBackground(UIUtils.BUTTON_COLOR);
				cancelButton.setFont(new Font("Arial", Font.PLAIN, 14));
				cancelButton.setForeground(Color.WHITE);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		
		}
	}


	protected void dodajRezervaciju() {
		String ime = textField.getText().toString();
		String prezime = textField_1.getText().toString();
		rezervacija = new RezervacijaDTO(new Timestamp(System.currentTimeMillis()),
				new Timestamp(System.currentTimeMillis()),ime,prezime, karta);
		if(rezervacijaDAO.dodajRezervaciju(rezervacija))
			JOptionPane.showMessageDialog(this, "Karta uspješno rezervisana.", "Uspjeh",
					JOptionPane.INFORMATION_MESSAGE);	
	}


	protected void azurirajKartu() {
		kartaDAO.obrisiKartu(this.karta.getKartaID());
		dispose();
		
		
	}

}
