package org.unibl.etf.cinema.view.forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.unibl.etf.cinema.data.dao.DAOFactory;
import org.unibl.etf.cinema.data.dao.DodatnaPonudaDAO;
import org.unibl.etf.cinema.data.dto.DodatnaPonudaDTO;

public class DodatnaPonudaDialog extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	private DodatnaPonudaDTO dpDTO;
	private DodatnaPonudaSearchFrame frame;
	// private MySQLDodatnaPonudaDAO dpDAO;
	private DodatnaPonudaDAO dpDAO = DAOFactory.getDAOFactory().getDodatnaPonudaDAO();

	/**
	 * Create the dialog.
	 */
	public DodatnaPonudaDialog(DodatnaPonudaSearchFrame frame, DodatnaPonudaDTO dpDTO) {
		this();
		this.dpDTO = dpDTO;
		this.frame = frame;
	}

	/**
	 * Create the dialog.
	 */
	public DodatnaPonudaDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						sacuvajDP();

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);

			JLabel lblInformacijeODodatnoj = new JLabel("Informacije o dodatnoj ponudi");
			lblInformacijeODodatnoj.setFont(new Font("Arial", Font.PLAIN, 18));
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel
							.createSequentialGroup().addContainerGap().addComponent(lblInformacijeODodatnoj,
									GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(166, Short.MAX_VALUE)));
			gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
					gl_panel.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblInformacijeODodatnoj, GroupLayout.PREFERRED_SIZE, 28,
									GroupLayout.PREFERRED_SIZE)
							.addContainerGap()));
			panel.setLayout(gl_panel);
		}

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);

		JLabel lblNaziv = new JLabel("Naziv");
		lblNaziv.setFont(new Font("Arial", Font.PLAIN, 13));

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblCijena = new JLabel("Cijena");
		lblCijena.setFont(new Font("Arial", Font.PLAIN, 13));

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel
								.createParallelGroup(Alignment.LEADING).addComponent(lblNaziv).addComponent(lblCijena))
						.addGap(27)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false).addComponent(textField_1)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
						.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(30)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNaziv).addComponent(
						textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(33)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCijena))
				.addContainerGap(81, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
	}

	public void sacuvajDP() {
		String polje1 = textField.getText();

		String polje2 = textField_1.getText();
		double cijena = Double.parseDouble(polje2);
		DodatnaPonudaDTO dp = new DodatnaPonudaDTO(polje1, cijena);

		if (dpDTO == null) {
			try {
				dpDAO.dodajDodatnuPonudu(dp);
				setVisible(false);
				dispose();
				// frame.refreshDP();

				JOptionPane.showMessageDialog(frame, "Dodatna ponuda dodana uspijesno.", "Dodatna ponuda je dodana",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " + ee.getMessage(), "Greska",
						JOptionPane.ERROR_MESSAGE);
			}

		} else {
			try {
				dpDAO.azurirajDodatnuPonudu(dp);
				setVisible(false);
				dispose();
				// frame.refreshDP();

				JOptionPane.showMessageDialog(frame, "Dodatna ponuda azurirana uspijesno.", "Dodatna ponuda je dodana",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " + ee.getMessage(), "Greska",
						JOptionPane.ERROR_MESSAGE);
			}

		}
		((DodatnaPonudaSearchFrame) frame).azurirajTabeluDP();
	}

	public void dodajDP() {
		String polje1 = textField.getText();

		String polje2 = textField_1.getText();
		double cijena = Double.parseDouble(polje2);
		DodatnaPonudaDTO dp = new DodatnaPonudaDTO(polje1, cijena);

		if (dpDTO == null) {
			try {
				dpDAO.dodajDodatnuPonudu(dp);
				setVisible(false);
				dispose();
				// frame.refreshDP();

				JOptionPane.showMessageDialog(frame, "Dodatna ponuda dodana uspijesno.", "Dodatna ponuda je dodana",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " + ee.getMessage(), "Greska",
						JOptionPane.ERROR_MESSAGE);
			}

		} else {
			try {
				dpDAO.azurirajDodatnuPonudu(dp);
				setVisible(false);
				dispose();
				// frame.refreshDP();

				JOptionPane.showMessageDialog(frame, "Dodatna ponuda azurirana uspijesno.", "Dodatna ponuda je dodana",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " + ee.getMessage(), "Greska",
						JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	/*
	 * public void azurirajDP() { String polje1 = textField.getText();
	 * 
	 * String polje2 = textField_1.getText(); double
	 * cijena=Double.parseDouble(polje2); DodatnaPonudaDTO dp=new
	 * DodatnaPonudaDTO(polje1, cijena); try { dpDAO.azurirajDodatnuPonudu(dp);
	 * setVisible(false); dispose(); frame.refreshDP();
	 * 
	 * JOptionPane.showMessageDialog(frame,
	 * "Dodatna ponuda azurirana uspijesno.","Dodatna ponuda je azurirana",
	 * JOptionPane.INFORMATION_MESSAGE); }catch(Exception ee) {
	 * JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " +
	 * ee.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE); }
	 * 
	 * }
	 * 
	 * public void obrisiDP() { DodatnaPonudaDTO dp = ((DodatnaPonudaTableModel)
	 * table .getModel()).getDodatnaPonudaAtRow(table .getSelectedRow()); try {
	 * dpDAO.obrisiDodatnuPonudu(dp.getNaziv());; setVisible(false); dispose();
	 * frame.refreshDP();
	 * 
	 * JOptionPane.showMessageDialog(frame,
	 * "Dodatna ponuda obrisana uspijesno.","Dodatna ponuda je obrisana",
	 * JOptionPane.INFORMATION_MESSAGE); }catch(Exception ee) {
	 * JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " +
	 * ee.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE); } }
	 */
}
