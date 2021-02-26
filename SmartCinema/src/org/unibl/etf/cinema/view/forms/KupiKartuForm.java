package org.unibl.etf.cinema.view.forms;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.unibl.etf.cinema.data.dao.DAOFactory;
import org.unibl.etf.cinema.data.dao.FilmDAO;
import org.unibl.etf.cinema.data.dao.KartaDAO;
import org.unibl.etf.cinema.data.dao.PrikazivanjeFilmaUSaliDAO;
import org.unibl.etf.cinema.data.dao.RezervacijaDAO;
import org.unibl.etf.cinema.data.dao.SjedisteDAO;
import org.unibl.etf.cinema.data.dao.ZaposleniDAO;
import org.unibl.etf.cinema.data.dto.FilmDTO;
import org.unibl.etf.cinema.data.dto.KartaDTO;
import org.unibl.etf.cinema.data.dto.PrikazivanjeFilmaUSaliDTO;
import org.unibl.etf.cinema.data.dto.SjedisteDTO;
import org.unibl.etf.cinema.data.dto.Zaposleni;
import org.unibl.etf.cinema.view.tables.SjedisteTableModel;


public class KupiKartuForm extends JPanel{

	private KartaDAO kartaDAO = DAOFactory.getDAOFactory().getKartaDAO();
	private PrikazivanjeFilmaUSaliDAO pfusDAO= DAOFactory.getDAOFactory().getPrikazivanjeFilmaUSaliDAO();
	private SjedisteDAO sjedisteDAO = DAOFactory.getDAOFactory().getSjedisteDAO();
	private FilmDAO filmDAO = DAOFactory.getDAOFactory().getFilmDAO();
	private ZaposleniDAO zaposleniDAO = DAOFactory.getDAOFactory().getZaposleniDAO();
	private RezervacijaDAO rezervacijaDAO = DAOFactory.getDAOFactory().getRezervacijaDAO();
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_2;
	private JScrollPane scrollPane;
	private JButton btnRezervisi;
	private JButton btnKupi;
	private String naziv, termin;
	private Zaposleni zaposleni;
	
	
public JTextField tfPretraga;
private JTable table;
private JTable table_1;

public KupiKartuForm() {}

public KupiKartuForm(Zaposleni zaposleni) {
	this.zaposleni = zaposleni;
	
	
	JPanel pnlPretraga = new JPanel();
	pnlPretraga.setBounds(199, 11, 241, 29);
	pnlPretraga.setBorder(null);
	pnlPretraga.setBackground(new Color(220, 20, 60));
	GridBagLayout gbl_pnlPretraga = new GridBagLayout();
	gbl_pnlPretraga.columnWidths = new int[]{316, 180, 50, 0};
	gbl_pnlPretraga.rowHeights = new int[]{22, 0, 0};
	gbl_pnlPretraga.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
	gbl_pnlPretraga.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
	pnlPretraga.setLayout(gbl_pnlPretraga);
	setLayout(null);
	
	scrollPane = new JScrollPane();
	scrollPane.setViewportView(table);
	
	JPanel pnlOpcije = new JPanel();
	pnlOpcije.setLayout(null);
	pnlOpcije.setOpaque(false);
	btnRezervisi = new JButton();
	btnRezervisi.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			rezervisiKartu();
			ucitajSjedistaUSali();
		}
	});
	btnRezervisi.setIcon(new ImageIcon(KupiKartuForm.class.getResource("/org/unibl/etf/cinema/view/icons/icon_edit.png")));
	btnRezervisi.setEnabled(false);
	btnRezervisi.setBackground(new Color(220, 20, 60));
	btnRezervisi.setBounds(126, 5, 95, 36);
	pnlOpcije.add(btnRezervisi);
	
	btnKupi = new JButton();	
	btnKupi.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			kupiKartu();
		}
	});
	btnKupi.setIcon(new ImageIcon(KupiKartuForm.class.getResource("/org/unibl/etf/cinema/view/icons/icon_add.png")));
	btnKupi.setEnabled(false);
	btnKupi.setBackground(new Color(220, 20, 60));
	btnKupi.setBounds(340, 5, 95, 36);
	pnlOpcije.add(btnKupi);
	GroupLayout gl_pnlKorisnici = new GroupLayout(this);
	gl_pnlKorisnici.setHorizontalGroup(
		gl_pnlKorisnici.createParallelGroup(Alignment.TRAILING)
			.addGroup(Alignment.LEADING, gl_pnlKorisnici.createSequentialGroup()
				.addContainerGap()
				.addGroup(gl_pnlKorisnici.createParallelGroup(Alignment.LEADING)
					.addComponent(pnlPretraga, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
					.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
					.addComponent(pnlOpcije, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE))
				.addGap(40))
	);
	gl_pnlKorisnici.setVerticalGroup(
		gl_pnlKorisnici.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_pnlKorisnici.createSequentialGroup()
				.addContainerGap()
				.addComponent(pnlPretraga, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
				.addGap(28)
				.addComponent(pnlOpcije, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
				.addGap(50))
	);
	
	JLabel lblNewLabel = new JLabel("Odaberi film");
	GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
	gbc_lblNewLabel.anchor = GridBagConstraints.SOUTHWEST;
	gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel.gridx = 0;
	gbc_lblNewLabel.gridy = 0;
	pnlPretraga.add(lblNewLabel, gbc_lblNewLabel);
	
	JLabel lblNewLabel_1 = new JLabel("Odaberi termin");
	GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
	gbc_lblNewLabel_1.anchor = GridBagConstraints.SOUTHWEST;
	gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel_1.gridx = 1;
	gbc_lblNewLabel_1.gridy = 0;
	pnlPretraga.add(lblNewLabel_1, gbc_lblNewLabel_1);
	
	comboBox_2 = new JComboBox<String>();
	comboBox_2.addItem((" "));

	List<FilmDTO> filmDTO = new ArrayList<FilmDTO>();
	List<PrikazivanjeFilmaUSaliDTO>filmovi = pfusDAO.termini();
	for (PrikazivanjeFilmaUSaliDTO prikazivanjeFilmaUSaliDTO : filmovi) {
		
		if(!filmDTO.contains(prikazivanjeFilmaUSaliDTO.getFilm())) {
			filmDTO.add(prikazivanjeFilmaUSaliDTO.getFilm());
			comboBox_2.addItem(prikazivanjeFilmaUSaliDTO.getFilm().getNaziv());
		}
		
	}

	comboBox_2.addItemListener(new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(comboBox_2.getSelectedIndex()!=0)
				ucitajTermineZaFilm();
			else comboBox.removeAllItems();
			}
		});

	GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
	gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
	gbc_comboBox_2.insets = new Insets(0, 0, 0, 5);
	gbc_comboBox_2.gridx = 0;
	gbc_comboBox_2.gridy = 1;
	pnlPretraga.add(comboBox_2, gbc_comboBox_2);
	
	comboBox = new JComboBox<String>();
	comboBox.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			if(comboBox.getSelectedIndex()>0)
			{
				ucitajSjedistaUSali();
				
			}
			podesiDugme();
		}
	});
	
	GridBagConstraints gbc_comboBox = new GridBagConstraints();
	gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
	gbc_comboBox.insets = new Insets(0, 0, 0, 5);
	gbc_comboBox.gridx = 1;
	gbc_comboBox.gridy = 1;
	pnlPretraga.add(comboBox, gbc_comboBox);
	
	/*JButton btnPretrazi = new JButton("");
	btnPretrazi.setIcon(new ImageIcon(KupiKartuForm.class.getResource("/org/unibl/etf/cinema/view/icons/icon_search.png")));
	btnPretrazi.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()>0)
			{
				ucitajSjedistaUSali();
			}
				//else table.setModel(new SjedisteTableModel(new ArrayList<SjedisteDTO>()));

		
		}
	});
	GridBagConstraints gbc_btnPretrazi = new GridBagConstraints();
	gbc_btnPretrazi.gridx = 2;
	gbc_btnPretrazi.gridy = 1;
	pnlPretraga.add(btnPretrazi, gbc_btnPretrazi);
	*/
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
	
	
	
	setLayout(gl_pnlKorisnici);
	
	
	
	}
	

	protected void rezervisiKartu() {
		int row = table.getSelectedRow();
		termin = comboBox.getSelectedItem().toString();
		naziv = comboBox_2.getSelectedItem().toString();		
		Object[] opcije = { "Da", "Ne" };
		String message = "Da li ste sigurni da\nželite rezervisati ovu kartu?";
		int value = JOptionPane.showOptionDialog(this, message, "", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null, opcije, opcije[0]);
		if (value == 0) {
			SjedisteDTO sjedisteDTO = ((SjedisteTableModel) table.getModel()).getSjedisteAtRow(row);
			KartaDTO karta = new KartaDTO((5.0), new Timestamp(System.currentTimeMillis()),
					false,sjedisteDTO, zaposleni,pfusDAO.terminFilma(naziv,termin));
					if(!kartaDAO.getByMovieName(naziv,termin).contains(karta)) {
			if( kartaDAO.dodajKartu(karta))  {
				
				karta = kartaDAO.getKartu(karta.getPfus().getFilm().getNaziv(), karta.getPfus().getTerminID(),karta.getSjediste().getSjedisteID());
				new RezervacijaDialog(karta).setVisible(true);
			} 
		}else 
		{
			JOptionPane.showMessageDialog(this, "Sjedalo nije slobodno.", "Greška",
					JOptionPane.ERROR_MESSAGE);
			}
		}	
}


	protected void kupiKartu() {
		int row = table.getSelectedRow();
		termin = comboBox.getSelectedItem().toString();
		naziv = comboBox_2.getSelectedItem().toString();		
		Object[] opcije = { "Da", "Ne" };
		String message = "Da li ste sigurni da\nželite kupiti ovu kartu?";
		int value = JOptionPane.showOptionDialog(this, message, "", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null, opcije, opcije[0]);
		if (value == 0) {
			SjedisteDTO sjedisteDTO = ((SjedisteTableModel) table.getModel()).getSjedisteAtRow(row);
					KartaDTO karta = new KartaDTO((5.0), new Timestamp(System.currentTimeMillis()),true,sjedisteDTO, zaposleni, pfusDAO.terminFilma(naziv,termin));
					if(!kartaDAO.Karte().contains(karta)) {
			if( kartaDAO.dodajKartu(karta)) {
				JOptionPane.showMessageDialog(this, "Karta uspješno Kupljena.", "Uspjeh",
						JOptionPane.INFORMATION_MESSAGE);
				odstampajKartu(kartaDAO.getKartu(naziv, pfusDAO.terminFilma(naziv,termin).getTerminID(), sjedisteDTO.getSjedisteID()));
				}
			}
			else 
			{
				JOptionPane.showMessageDialog(this, "Karta nije uspješno Kupljena.", "Greška",
						JOptionPane.ERROR_MESSAGE);
				}
				
		}
		ucitajSjedistaUSali();
	}
	

	public void ucitajSjedistaUSali() {		
		//scrollPane.setViewportView(table);
		termin = comboBox.getSelectedItem().toString();
		naziv = comboBox_2.getSelectedItem().toString();
		List<KartaDTO> karte = kartaDAO.getByMovieName(naziv,termin);
		List<SjedisteDTO> sjedista = pfusDAO.listaSjedistaZaTermin(pfusDAO.terminFilma(naziv,termin).getSala().getSalaID(),pfusDAO.terminFilma(naziv, termin).termin);
		table.setModel(new SjedisteTableModel(sjedista));
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		
		
		for (KartaDTO k : karte) {
			if(sjedista.contains(k.getSjediste()))
				sjedista.remove(k.getSjediste());
		}
		scrollPane.setViewportView(table);
		SjedisteTableModel model = (SjedisteTableModel) table.getModel();
		model.setSjedista(sjedista);
		model.fireTableDataChanged();
		
		
		
	}
	
	protected void ucitajTermineZaFilm() {
		comboBox.removeAllItems();
		comboBox.addItem("");
		List<PrikazivanjeFilmaUSaliDTO>pfus =pfusDAO.sviTerminizaFilm(filmDAO.searchMovie(comboBox_2.getSelectedItem().toString().trim()).get(0));
		for (PrikazivanjeFilmaUSaliDTO prikazivanjeFilmaUSaliDTO : pfus) {
			comboBox.addItem(prikazivanjeFilmaUSaliDTO.termin);
		}	
	}
	
	private void podesiDugme() {
		boolean enabled = table.getSelectedRow() != -1;
		if(comboBox_2.getSelectedIndex()>0 && comboBox.getSelectedIndex()>0) {
			btnKupi.setEnabled(enabled);
			btnRezervisi.setEnabled(enabled);
		}else {
			btnKupi.setEnabled(false);
			btnRezervisi.setEnabled(false);
		
		}
	}
	public void ucitavanjeTermina() {
		comboBox_2.removeAll();
		comboBox_2.addItem((" "));
		List<FilmDTO> filmDTO = new ArrayList<FilmDTO>();
		List<PrikazivanjeFilmaUSaliDTO>filmovi = pfusDAO.termini();
		for (PrikazivanjeFilmaUSaliDTO prikazivanjeFilmaUSaliDTO : filmovi) {
			
			if(!filmDTO.contains(prikazivanjeFilmaUSaliDTO.getFilm())) {
				filmDTO.add(prikazivanjeFilmaUSaliDTO.getFilm());
				comboBox_2.addItem(prikazivanjeFilmaUSaliDTO.getFilm().getNaziv());
			}
			
		}
	}
	
	
	public void odstampajKartu(KartaDTO karta) {
		try {
		      FileWriter myWriter = new FileWriter("out\\"+karta.getKartaID()+" Karta.pdf");
		      myWriter.write("FILM:     "+karta.getPfus().getFilm().getNaziv()+"\n"+
		    		  		 "SALA:     "+karta.getPfus().getSala().getBroj()+"\n"+
		    		  		 "RED:      "+karta.getSjediste().getRed()+"\n"+
		    		  		 "SJEDISTE: "+karta.getSjediste().getBroj()+"\n"+
		    		  		 "TERMIN :  "+karta.getPfus().termin);
		      myWriter.close();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
	}
	
}






