package org.unibl.etf.cinema.view.forms;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.unibl.etf.cinema.data.dao.DAOFactory;
import org.unibl.etf.cinema.data.dao.FilmDAO;
import org.unibl.etf.cinema.data.dao.PrikazivanjeFilmaUSaliDAO;
import org.unibl.etf.cinema.data.dao.mysql.MySQLRezervacijaDAO;
import org.unibl.etf.cinema.data.dto.FilmDTO;
import org.unibl.etf.cinema.data.dto.PrikazivanjeFilmaUSaliDTO;
import org.unibl.etf.cinema.view.tables.TerminiTableModel;

public class TerminiForm extends JPanel {

	private PrikazivanjeFilmaUSaliDAO pfusDAO= DAOFactory.getDAOFactory().getPrikazivanjeFilmaUSaliDAO();
	private FilmDAO filmDAO = DAOFactory.getDAOFactory().getFilmDAO();

	
	private JTable table;
	JButton btnObrisi = new JButton("");
	JButton btnDodaj = new JButton("");
	private JTextField tf_Pretraga;
	
	
	public TerminiForm() {

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
		
		tf_Pretraga = new JTextField();
		tf_Pretraga.setFont(new Font("Arial", Font.PLAIN, 14));
		tf_Pretraga.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				azurirajTabeluTermina();
			}
		});
		tf_Pretraga.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		pnlPretraga.add(tf_Pretraga, gbc_textField);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel pnlOpcije = new JPanel();
		pnlOpcije.setLayout(null);
		pnlOpcije.setOpaque(false);
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObrisiSelektovaniTermin();
			}
		});
		
		
		btnObrisi.setIcon(new ImageIcon(RezervacijeForm.class.getResource("/org/unibl/etf/cinema/view/icons/icon_delete.png")));
		btnObrisi.setEnabled(false);
		btnObrisi.setBackground(new Color(220, 20, 60));
		btnObrisi.setBounds(126, 5, 95, 36);
		pnlOpcije.add(btnObrisi);
		
		
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dodajTermin();
			}
		});
	
		btnDodaj.setIcon(new ImageIcon(RezervacijeForm.class.getResource("/org/unibl/etf/cinema/view/icons/icon_add.png")));
		btnDodaj.setEnabled(true);
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
		table.setModel(new TerminiTableModel(pfusDAO.termini()));
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		//scrollPane.setColumnHeaderView(table);
		setLayout(gl_pnlKorisnici);
		
	}


	protected void azurirajTabeluTermina() {
		String kljucnaRijec = tf_Pretraga.getText().trim();
		TerminiTableModel model = (TerminiTableModel) table.getModel();
		if(!tf_Pretraga.getText().toString().trim().equals("")) {
		List<PrikazivanjeFilmaUSaliDTO> termini = new ArrayList<PrikazivanjeFilmaUSaliDTO>();
		List<FilmDTO> filmovi = filmDAO.searchMovie(kljucnaRijec);
		for (FilmDTO filmDTO : filmovi) {
			termini.addAll(pfusDAO.sviTerminizaFilm(filmDTO));
		}
		model.setTermini(termini);
		}
		else
			model.setTermini(pfusDAO.termini());
		model.fireTableDataChanged();
	}
	
	
	protected void dodajTermin() {
		Object[] opcije = { "Da", "Ne" };
		String message = "Da li ste sigurni da\nželite dodati novi termin?";
		int value = JOptionPane.showOptionDialog(this, message, "", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null, opcije, opcije[0]);
		if (value == 0) {
			new TerminDialog().setVisible(true);
		}		
	}
	
	
	
	
	
	protected void ObrisiSelektovaniTermin() {
		int row = table.getSelectedRow();

		Object[] opcije = { "Da", "Ne" };
		String message = "Da li ste sigurni da\nželite ukloniti Termin \'"+ table.getValueAt(row, 2).toString()+"\' za film \'"+table.getValueAt(row, 1).toString()+"\'?";
		int value = JOptionPane.showOptionDialog(this, message, "", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null, opcije, opcije[0]);
		if (value == 0) {
			if (pfusDAO.obrisi((int)table.getValueAt(row,0))){
				new MySQLRezervacijaDAO().obrisiRezervacijeZaTermin((int)table.getValueAt(row,0));
				JOptionPane.showMessageDialog(this, "Termin uspješno obrisan.", "Uspjeh",
						JOptionPane.INFORMATION_MESSAGE);
				azurirajTabeluTermina();
			} else {
				JOptionPane.showMessageDialog(this, "Termin nije uspješno obrisan.", "Greška",
						JOptionPane.ERROR_MESSAGE);
			}
		}		
	}
	
	
	private void podesiDugme() {
		boolean enabled = table.getSelectedRow() != -1;
		btnObrisi.setEnabled(enabled);
	}
}


