package org.unibl.etf.cinema.view.forms;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.unibl.etf.cinema.data.dao.DAOFactory;
import org.unibl.etf.cinema.data.dao.KartaDAO;
import org.unibl.etf.cinema.data.dao.RezervacijaDAO;
import org.unibl.etf.cinema.data.dao.ZaposleniDAO;
import org.unibl.etf.cinema.data.dto.RezervacijaDTO;
import org.unibl.etf.cinema.data.dto.Zaposleni;
import org.unibl.etf.cinema.view.tables.RezervacijaTableModel;
import org.unibl.etf.cinema.view.tables.ZaposleniTableModel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class RezervacijeForm extends JPanel {

	/**
	 * Create the panel.
	 * @param tfPretraga 
	 */
	
	private ZaposleniDAO zaposleniDAO = DAOFactory.getDAOFactory().getZaposleniDAO();
	private RezervacijaDAO rezervacijaDAO = DAOFactory.getDAOFactory().getRezervacijaDAO();
	private JTable table;
	JButton btnObrisi = new JButton("");
	JButton btnDodaj = new JButton("");

	
	public JTextField tfPretraga;
	private JTextField textField;
	public RezervacijeForm() {
		
		this.setBorder(null);
		
		JPanel pnlPretraga = new JPanel();
		pnlPretraga.setBounds(199, 11, 241, 29);
		pnlPretraga.setBorder(null);
		pnlPretraga.setBackground(new Color(220, 20, 60));
		GridBagLayout gbl_pnlPretraga = new GridBagLayout();
		gbl_pnlPretraga.columnWidths = new int[]{40, 150, 0};
		gbl_pnlPretraga.rowHeights = new int[]{29, 0};
		gbl_pnlPretraga.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlPretraga.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnlPretraga.setLayout(gbl_pnlPretraga);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(RezervacijeForm.class.getResource("/org/unibl/etf/cinema/view/icons/icon_search.png")));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		pnlPretraga.add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				azurirajTabeluRezervacija();
			}
		});
		textField.setFont(new Font("Arial", Font.PLAIN, 14));
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		pnlPretraga.add(textField, gbc_textField);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel pnlOpcije = new JPanel();
		pnlOpcije.setLayout(null);
		pnlOpcije.setOpaque(false);
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObrisiSelektovanuRezervaciju();
			}
		});
		
		
		btnObrisi.setIcon(new ImageIcon(RezervacijeForm.class.getResource("/org/unibl/etf/cinema/view/icons/icon_delete.png")));
		btnObrisi.setEnabled(false);
		btnObrisi.setBackground(new Color(220, 20, 60));
		btnObrisi.setBounds(126, 5, 95, 36);
		pnlOpcije.add(btnObrisi);
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				izvrsiKupovinuRezervisaneKarte();
			}
		});
		
	
		btnDodaj.setIcon(new ImageIcon(RezervacijeForm.class.getResource("/org/unibl/etf/cinema/view/icons/icon_add.png")));
		btnDodaj.setEnabled(false);
		btnDodaj.setBackground(new Color(220, 20, 60));
		btnDodaj.setBounds(340, 5, 95, 36);
		pnlOpcije.add(btnDodaj);
		GroupLayout gl_pnlKorisnici = new GroupLayout(this);
		gl_pnlKorisnici.setHorizontalGroup(
			gl_pnlKorisnici.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlKorisnici.createSequentialGroup()
					.addGroup(gl_pnlKorisnici.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlKorisnici.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE))
						.addGroup(gl_pnlKorisnici.createSequentialGroup()
							.addContainerGap()
							.addComponent(pnlOpcije, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlKorisnici.createSequentialGroup()
							.addContainerGap()
							.addComponent(pnlPretraga, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)))
					.addGap(40))
		);
		gl_pnlKorisnici.setVerticalGroup(
			gl_pnlKorisnici.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlKorisnici.createSequentialGroup()
					.addGap(36)
					.addComponent(pnlPretraga, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
					.addGap(28)
					.addComponent(pnlOpcije, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addGap(50))
		);
		
		table = new JTable();
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						podesiDugme();
					}
				});
			}
		});
		
		scrollPane.setViewportView(table);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.setModel(new RezervacijaTableModel(rezervacijaDAO.rezervacijeZaFilm(textField.getText())));
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(140);
		table.getColumnModel().getColumn(3).setPreferredWidth(140);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		//scrollPane.setColumnHeaderView(table);
		setLayout(gl_pnlKorisnici);
		
	}
	
	
	protected void izvrsiKupovinuRezervisaneKarte() {
		int row = table.getSelectedRow();
		Object[] opcije = { "Da", "Ne" };
		String message = "Da li ste sigurni da\nželite izvrsiti kupovinu karate za odabranu rezervaciju?";
		int value = JOptionPane.showOptionDialog(this, message, "", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null, opcije, opcije[0]);
		if (value == 0) {
			RezervacijaDTO rezervacijaDTO = ((RezervacijaTableModel) table.getModel()).getRezervacijaAtRow(row);
			if (!rezervacijaDAO.azurirajRezervaciju(rezervacijaDTO)) {
				JOptionPane.showMessageDialog(this, "Karta uspješno kupljena.", "Uspjeh",
						JOptionPane.INFORMATION_MESSAGE);
				azurirajTabeluRezervacija();
			} else {
				JOptionPane.showMessageDialog(this, "Karta nije uspješno kupljena.", "Greška",
						JOptionPane.ERROR_MESSAGE);
			}
		}		
	}


	protected void ObrisiSelektovanuRezervaciju() {
		int row = table.getSelectedRow();

		Object[] opcije = { "Da", "Ne" };
		String message = "Da li ste sigurni da\nželite ukloniti rezervaciju?";
		int value = JOptionPane.showOptionDialog(this, message, "", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null, opcije, opcije[0]);
		if (value == 0) {
			RezervacijaDTO rezervacijaDTO = ((RezervacijaTableModel) table.getModel()).getRezervacijaAtRow(row);
			if (rezervacijaDAO.obrisiRezervaciju(rezervacijaDTO.getRezervacijaID())) {
				JOptionPane.showMessageDialog(this, "Rezervacija uspješno obrisana.", "Uspjeh",
						JOptionPane.INFORMATION_MESSAGE);
				azurirajTabeluRezervacija();
			} else {
				JOptionPane.showMessageDialog(this, "Rezervacija nije uspješno obrisana.", "Greška",
						JOptionPane.ERROR_MESSAGE);
			}
		}		
	}


	private void podesiDugme() {
		boolean enabled = table.getSelectedRow() != -1;
		btnObrisi.setEnabled(enabled);
		btnDodaj.setEnabled(enabled);
	}
	
	protected void azurirajTabeluRezervacija() {
		String kljucnaRijec = textField.getText().trim();
		RezervacijaTableModel model = (RezervacijaTableModel) table.getModel();
		model.setRezervacija(rezervacijaDAO.rezervacijeZaFilm(kljucnaRijec));
		model.fireTableDataChanged();
	} 

	protected void kupiKartu() {
		KartaDAO karta = DAOFactory.getDAOFactory().getKartaDAO();
		System.out.println(karta.Karte());
	}
	
	
	public JPanel display() {
		return this;
	}
}






