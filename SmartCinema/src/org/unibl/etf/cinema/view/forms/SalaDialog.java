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
import javax.swing.border.EmptyBorder;

import org.unibl.etf.cinema.data.dao.DAOFactory;
import org.unibl.etf.cinema.data.dao.KinoDAO;
import org.unibl.etf.cinema.data.dao.SalaDAO;
import org.unibl.etf.cinema.data.dto.KinoDTO;
import org.unibl.etf.cinema.data.dto.SalaDTO;
import org.unibl.etf.cinema.util.UIUtils;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;

public class SalaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	private SalaDAO salaDAO = DAOFactory.getDAOFactory().getSalaDAO();
	private KinoDAO kinoDAO = DAOFactory.getDAOFactory().getKinoDAO();
	// private MySQLKinoDAO kinoSQL
	private DodatnaPonudaSearchFrame frame;
	private SalaDTO salaDTO;
	private JTable table;
	private KinoDTO kinoDTO;
	private JPanel buttonPane;

	/**
	 * Launch the application.
	 */

	public SalaDialog(DodatnaPonudaSearchFrame frame, SalaDTO salaDTO) {
		this();
		this.salaDTO = salaDTO;
		this.frame = frame;
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
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 321, 267);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		JLabel lblInformacijeOSali = new JLabel("Informacije o sali");
		lblInformacijeOSali.setFont(new Font("Arial", Font.BOLD, 18));
		JLabel lblBrojSale = new JLabel("Broj sale");
		lblBrojSale.setFont(new Font("Arial", Font.PLAIN, 14));
		textField = new JTextField();
		textField.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 0)));
		textField.setFont(new Font("Arial", Font.PLAIN, 14));
		textField.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(26)
							.addComponent(lblInformacijeOSali))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(41)
							.addComponent(lblBrojSale)
							.addGap(29)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(53, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(25)
					.addComponent(lblInformacijeOSali)
					.addGap(35)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBrojSale)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(102, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("Sa\u010Duvaj");
				okButton.setBackground(UIUtils.BUTTON_COLOR);
				okButton.setForeground(Color.WHITE);
				okButton.setFont(new Font("Arial", Font.PLAIN, 14));
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						sacuvajSalu();

					}
				});
				{
					JButton cancelButton = new JButton("Otka\u017Ei");
					cancelButton.setBackground(UIUtils.BUTTON_COLOR);
					cancelButton.setFont(new Font("Arial", Font.PLAIN, 14));
					cancelButton.setForeground(Color.WHITE);
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
				}
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(63))
		);
		getContentPane().setLayout(groupLayout);
	}

	public void sacuvajSalu() {
		String polje1 = textField.getText();
		int broj = Integer.parseInt(polje1);
		KinoDTO kino = kinoDAO.svaKina().get(0);
		SalaDTO dp = new SalaDTO(broj, kino);

		if (salaDTO == null) {
			try {
				salaDAO.dodajSalu(dp);
				// setVisible(false);
				dispose();
				// frame.refreshDP();

				JOptionPane.showMessageDialog(frame, "Sala dodana uspijesno.", "Sala je dodana",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " + ee.getMessage(), "Greska",
						JOptionPane.ERROR_MESSAGE);
			}

		} else {
			try {
				salaDAO.azurirajSalu(dp);

				// setVisible(false);
				dispose();
				// frame.refreshDP();

				JOptionPane.showMessageDialog(frame, "Sala azurirana uspijesno.", "Sala je dodana",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " + ee.getMessage(), "Greska",
						JOptionPane.ERROR_MESSAGE);
			}

		}
		((DodatnaPonudaSearchFrame) frame).azurirajTabeluSala();
	}

	public void dodajSalu() {
		String polje1 = textField.getText();
		int broj = Integer.parseInt(polje1);
		KinoDTO kino = kinoDAO.svaKina().get(0);
		SalaDTO dp = new SalaDTO(broj, kino);

		if (salaDTO == null) {
			try {
				salaDAO.dodajSalu(dp);
				setVisible(false);
				dispose();
				// frame.refreshDP();

				JOptionPane.showMessageDialog(frame, "Sala dodana uspijesno.", "Sala je dodana",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " + ee.getMessage(), "Greska",
						JOptionPane.ERROR_MESSAGE);
			}

		} else {
			try {
				salaDAO.azurirajSalu(dp);

				setVisible(false);
				dispose();
				// frame.refreshDP();

				JOptionPane.showMessageDialog(frame, "Sala azurirana uspijesno.", "Sala je dodana",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " + ee.getMessage(), "Greska",
						JOptionPane.ERROR_MESSAGE);
			}

		}
		((DodatnaPonudaSearchFrame) frame).azurirajTabeluSala();

	}

}
