package org.unibl.etf.cinema.view.forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import org.unibl.etf.cinema.data.dao.DAOFactory;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.cinema.data.dao.DAOFactory;
import org.unibl.etf.cinema.data.dao.KinoDAO;
import org.unibl.etf.cinema.data.dao.SalaDAO;
import org.unibl.etf.cinema.data.dao.SjedisteDAO;
import org.unibl.etf.cinema.data.dao.VrstaSjedistaDAO;
import org.unibl.etf.cinema.data.dto.KinoDTO;
import org.unibl.etf.cinema.data.dto.SalaDTO;
import org.unibl.etf.cinema.data.dto.SjedisteDTO;
import org.unibl.etf.cinema.data.dto.VrstaSjedistaDTO;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class SjedisteDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private VrstaSjedistaDAO vsDAO = DAOFactory.getDAOFactory().getVrstaSjedistaDAO();
	private SjedisteDAO sjedisteDAO = DAOFactory.getDAOFactory().getSjedisteDAO();
	private SalaDAO salaDAO = DAOFactory.getDAOFactory().getSalaDAO();
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private SjedisteDTO sjedisteDTO;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_1;
	private DodatnaPonudaSearchFrame frame;

	/**
	 * Launch the application.
	 * 
	 * 
	 */
	public SjedisteDialog(DodatnaPonudaSearchFrame frame, SjedisteDTO sjedisteDTO) {
		this();
		this.sjedisteDTO = sjedisteDTO;
		this.frame = frame;

	}

	public static void main(String[] args) {
		try {
			SjedisteDialog dialog = new SjedisteDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SjedisteDialog() {
		setBounds(100, 100, 462, 358);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblInformacijeOSjedistu = new JLabel("Informacije o sjedistu");
		lblInformacijeOSjedistu.setFont(new Font("Arial", Font.PLAIN, 18));
		JLabel lblBroj = new JLabel("Broj");
		lblBroj.setFont(new Font("Arial", Font.PLAIN, 13));
		JLabel lblRed = new JLabel("Red");
		lblRed.setFont(new Font("Arial", Font.PLAIN, 13));
		JLabel lblVrstaSjedista = new JLabel("Vrsta sjedista");
		lblVrstaSjedista.setFont(new Font("Arial", Font.PLAIN, 13));
		textField = new JTextField();
		textField.setColumns(10);
		textField_1 = new JTextField();
		textField_1.setColumns(10);

		comboBox = new JComboBox(vsDAO.sveVrsteSjedista().toArray(new VrstaSjedistaDTO[] {}));
		comboBox.setSelectedIndex(-1);

		JLabel lblSala = new JLabel("Sala");
		lblSala.setFont(new Font("Arial", Font.PLAIN, 13));

		comboBox_1 = new JComboBox(salaDAO.sveSale().toArray(new SalaDTO[] {}));
		System.out.println(comboBox_1);
		comboBox_1.setSelectedIndex(-1);
		;

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblInformacijeOSjedistu)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblVrstaSjedista)
								.addComponent(lblSala)
								.addComponent(lblBroj)
								.addComponent(lblRed))
							.addGap(42)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
									.addGap(3))
								.addComponent(comboBox, 0, 281, Short.MAX_VALUE)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(textField_1, Alignment.LEADING)
									.addComponent(textField, Alignment.LEADING)))))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(26)
					.addComponent(lblInformacijeOSjedistu)
					.addGap(37)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBroj))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRed))
					.addGap(32)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSala)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblVrstaSjedista)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
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
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						sacuvajSjediste();

					}
				});

				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	public void sacuvajSjediste() {
		String polje1 = textField.getText();
		int broj = Integer.parseInt(polje1);
		String polje2 = textField_1.getText();
		int broj2 = Integer.parseInt(polje2);
		SalaDTO sala = (SalaDTO) comboBox_1.getSelectedItem();
		VrstaSjedistaDTO vs = (VrstaSjedistaDTO) comboBox.getSelectedItem();

		SjedisteDTO s = new SjedisteDTO(broj, broj2, sala, vs);

		if (sjedisteDTO == null) {
			try {
				sjedisteDAO.dodajSjedisteUSaluUKinu(s);
				// setVisible(false);
				dispose();
				// frame.refreshDP();

				JOptionPane.showMessageDialog(frame, "Sjediste dodano uspijesno.", "Sala je dodana",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " + ee.getMessage(), "Greska",
						JOptionPane.ERROR_MESSAGE);
			}

		} else {
			try {
				sjedisteDAO.azurirajSjedisteUSaliUKinu(s);

				// setVisible(false);
				dispose();
				// frame.refreshDP();

				JOptionPane.showMessageDialog(frame, "Sjediste azurirana uspijesno.", "Sala je dodana",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " + ee.getMessage(), "Greska",
						JOptionPane.ERROR_MESSAGE);
			}

		}
		((DodatnaPonudaSearchFrame) frame).azurirajTabeluSjedista();
	}

	public void dodajSalu() {
		String polje1 = textField.getText();
		int broj = Integer.parseInt(polje1);
		String polje2 = textField_1.getText();
		int broj2 = Integer.parseInt(polje2);
		SalaDTO sala = (SalaDTO) comboBox_1.getSelectedItem();
		VrstaSjedistaDTO vs = (VrstaSjedistaDTO) comboBox.getSelectedItem();

		SjedisteDTO s = new SjedisteDTO(broj, broj2, sala, vs);

		if (sjedisteDTO == null) {
			try {
				sjedisteDAO.dodajSjedisteUSaluUKinu(s);
				// setVisible(false);
				dispose();
				// frame.refreshDP();

				JOptionPane.showMessageDialog(frame, "Sjediste dodano uspijesno.", "Sala je dodana",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " + ee.getMessage(), "Greska",
						JOptionPane.ERROR_MESSAGE);
			}

		} else {
			try {
				sjedisteDAO.azurirajSjedisteUSaliUKinu(s);
				;

				// setVisible(false);
				dispose();
				// frame.refreshDP();

				JOptionPane.showMessageDialog(frame, "Sjediste azurirana uspijesno.", "Sala je dodana",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " + ee.getMessage(), "Greska",
						JOptionPane.ERROR_MESSAGE);
			}

		}
		((DodatnaPonudaSearchFrame) frame).azurirajTabeluSjedista();

	}

}
