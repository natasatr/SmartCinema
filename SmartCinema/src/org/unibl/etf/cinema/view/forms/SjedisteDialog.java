package org.unibl.etf.cinema.view.forms;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.unibl.etf.cinema.data.dao.DAOFactory;
import org.unibl.etf.cinema.data.dao.SalaDAO;
import org.unibl.etf.cinema.data.dao.SjedisteDAO;
import org.unibl.etf.cinema.data.dao.VrstaSjedistaDAO;
import org.unibl.etf.cinema.data.dto.SalaDTO;
import org.unibl.etf.cinema.data.dto.SjedisteDTO;
import org.unibl.etf.cinema.data.dto.VrstaSjedistaDTO;
import org.unibl.etf.cinema.util.UIUtils;

public class SjedisteDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfBroj;
	private JTextField tfRed;
	private VrstaSjedistaDAO vsDAO = DAOFactory.getDAOFactory().getVrstaSjedistaDAO();
	private SjedisteDAO sjedisteDAO = DAOFactory.getDAOFactory().getSjedisteDAO();
	private SalaDAO salaDAO = DAOFactory.getDAOFactory().getSalaDAO();
	@SuppressWarnings("rawtypes")
	private JComboBox cbVrstaSjedista;
	private SjedisteDTO sjedisteDTO;
	@SuppressWarnings("rawtypes")
	private JComboBox cbSala;
	private DodatnaPonudaSearchFrame frame;
	private JPanel buttonPane;

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
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 462, 408);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		JLabel lblInformacijeOSjedistu = new JLabel("Informacije o sjedi\u0161tu");
		lblInformacijeOSjedistu.setFont(new Font("Arial", Font.BOLD, 18));
		JLabel lblBroj = new JLabel("Broj");
		lblBroj.setFont(new Font("Arial", Font.PLAIN, 14));
		JLabel lblRed = new JLabel("Red");
		lblRed.setFont(new Font("Arial", Font.PLAIN, 14));
		JLabel lblVrstaSjedista = new JLabel("Vrsta sjedi\u0161ta");
		lblVrstaSjedista.setFont(new Font("Arial", Font.PLAIN, 14));
		tfBroj = new JTextField();
		tfBroj.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 0)));
		tfBroj.setFont(new Font("Arial", Font.PLAIN, 14));
		tfBroj.setColumns(10);
		tfRed = new JTextField();
		tfRed.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 0)));
		tfRed.setFont(new Font("Arial", Font.PLAIN, 14));
		tfRed.setColumns(10);

		cbVrstaSjedista = new JComboBox(vsDAO.sveVrsteSjedista().toArray(new VrstaSjedistaDTO[] {}));
		cbVrstaSjedista.setFont(new Font("Arial", Font.PLAIN, 14));
		cbVrstaSjedista.setSelectedIndex(-1);

		JLabel lblSala = new JLabel("Sala");
		lblSala.setFont(new Font("Arial", Font.PLAIN, 14));

		cbSala = new JComboBox(salaDAO.sveSale().toArray(new SalaDTO[] {}));
		cbSala.setFont(new Font("Arial", Font.PLAIN, 14));
		System.out.println(cbSala);
		cbSala.setSelectedIndex(-1);
		;
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(23)
					.addComponent(lblInformacijeOSjedistu))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(43)
					.addComponent(lblBroj)
					.addGap(82)
					.addComponent(tfBroj, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(43)
					.addComponent(lblRed)
					.addGap(81)
					.addComponent(tfRed, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(43)
					.addComponent(lblSala)
					.addGap(79)
					.addComponent(cbSala, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(43)
					.addComponent(lblVrstaSjedista)
					.addGap(22)
					.addComponent(cbVrstaSjedista, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(26)
					.addComponent(lblInformacijeOSjedistu)
					.addGap(37)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(6)
							.addComponent(lblBroj))
						.addComponent(tfBroj, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(6)
							.addComponent(lblRed))
						.addComponent(tfRed, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(7)
							.addComponent(lblSala))
						.addComponent(cbSala, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(7)
							.addComponent(lblVrstaSjedista))
						.addComponent(cbVrstaSjedista, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton cancelButton = new JButton("Otka\u017Ei");
				cancelButton.setBackground(UIUtils.BUTTON_COLOR);
				cancelButton.setFont(new Font("Arial", Font.PLAIN, 14));
				cancelButton.setForeground(Color.WHITE);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JButton okButton = new JButton("Sa\u010Duvaj");
				okButton.setBackground(UIUtils.BUTTON_COLOR);
				okButton.setFont(new Font("Arial", Font.PLAIN, 14));
				okButton.setForeground(Color.WHITE);
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
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 446, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
		);
		getContentPane().setLayout(groupLayout);
	}

	public void sacuvajSjediste() {
		String polje1 = tfBroj.getText();
		int broj = Integer.parseInt(polje1);
		String polje2 = tfRed.getText();
		int broj2 = Integer.parseInt(polje2);
		SalaDTO sala = (SalaDTO) cbSala.getSelectedItem();
		VrstaSjedistaDTO vs = (VrstaSjedistaDTO) cbVrstaSjedista.getSelectedItem();

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
		String polje1 = tfBroj.getText();
		int broj = Integer.parseInt(polje1);
		String polje2 = tfRed.getText();
		int broj2 = Integer.parseInt(polje2);
		SalaDTO sala = (SalaDTO) cbSala.getSelectedItem();
		VrstaSjedistaDTO vs = (VrstaSjedistaDTO) cbVrstaSjedista.getSelectedItem();

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

				JOptionPane.showMessageDialog(frame, "Sjediste azurirana uspjesno.", "Sala je dodana",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " + ee.getMessage(), "Greska",
						JOptionPane.ERROR_MESSAGE);
			}

		}
		((DodatnaPonudaSearchFrame) frame).azurirajTabeluSjedista();

	}

}
