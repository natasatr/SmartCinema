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
import org.unibl.etf.cinema.util.UIUtils;

import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class DodatnaPonudaDialog extends JDialog {
	private JTextField tfNaziv;
	private JTextField tfCijena;
	private JTable table;

	private DodatnaPonudaDTO dpDTO;
	private DodatnaPonudaSearchFrame frame;
	// private MySQLDodatnaPonudaDAO dpDAO;
	private DodatnaPonudaDAO dpDAO = DAOFactory.getDAOFactory().getDodatnaPonudaDAO();
	private JPanel panel_1;
	private JPanel buttonPane;

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
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 450, 292);
		{
			buttonPane = new JPanel();
			buttonPane.setBorder(new EmptyBorder(0, 0, 0, 25));
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton cancelButton = new JButton("Otka\u017Ei");
				cancelButton.setBackground(UIUtils.BUTTON_COLOR);
				cancelButton.setForeground(Color.WHITE);
				cancelButton.setFont(new Font("Arial", Font.PLAIN, 14));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JButton okButton = new JButton("Sa\u010Duvaj");
				okButton.setBackground(UIUtils.BUTTON_COLOR);
				okButton.setForeground(Color.WHITE);
				okButton.setFont(new Font("Arial", Font.PLAIN, 14));
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
			panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);

			JLabel lblInformacijeODodatnoj = new JLabel("Informacije o dodatnoj ponudi");
			lblInformacijeODodatnoj.setFont(new Font("Arial", Font.BOLD, 18));
			GroupLayout gl_panel_1 = new GroupLayout(panel_1);
			gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addGap(26)
						.addComponent(lblInformacijeODodatnoj, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(77, Short.MAX_VALUE))
			);
			gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.TRAILING)
					.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
						.addContainerGap(12, Short.MAX_VALUE)
						.addComponent(lblInformacijeODodatnoj, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
			);
			panel_1.setLayout(gl_panel_1);
		}

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);

		JLabel lblNaziv = new JLabel("Naziv");
		lblNaziv.setFont(new Font("Arial", Font.PLAIN, 14));

		tfNaziv = new JTextField();
		tfNaziv.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 0)));
		tfNaziv.setFont(new Font("Arial", Font.PLAIN, 14));
		tfNaziv.setColumns(10);

		JLabel lblCijena = new JLabel("Cijena");
		lblCijena.setFont(new Font("Arial", Font.PLAIN, 14));

		tfCijena = new JTextField();
		tfCijena.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 0)));
		tfCijena.setFont(new Font("Arial", Font.PLAIN, 14));
		tfCijena.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(35, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblNaziv)
							.addGap(34)
							.addComponent(tfNaziv, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblCijena)
							.addGap(27)
							.addComponent(tfCijena, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNaziv)
						.addComponent(tfNaziv, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfCijena, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCijena))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(12, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
		);
		getContentPane().setLayout(groupLayout);
	}

	public void sacuvajDP() {
		String polje1 = tfNaziv.getText();

		String polje2 = tfCijena.getText();
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
		String polje1 = tfNaziv.getText();

		String polje2 = tfCijena.getText();
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
