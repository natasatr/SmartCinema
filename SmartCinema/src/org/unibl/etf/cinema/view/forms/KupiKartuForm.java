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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.unibl.etf.cinema.data.dao.DAOFactory;
import org.unibl.etf.cinema.data.dao.FilmDAO;
import org.unibl.etf.cinema.data.dao.KartaDAO;
import org.unibl.etf.cinema.data.dao.PrikazivanjeFilmaUSaliDAO;
import org.unibl.etf.cinema.data.dao.RezervacijaDAO;
import org.unibl.etf.cinema.data.dao.SjedisteDAO;
import org.unibl.etf.cinema.data.dao.ZaposleniDAO;
import org.unibl.etf.cinema.data.dao.mysql.MySQLFilmDAO;
import org.unibl.etf.cinema.data.dao.mysql.MySQLPrikazivanjeFilmaUSaliDAO;
import org.unibl.etf.cinema.data.dto.FilmDTO;
import org.unibl.etf.cinema.data.dto.KartaDTO;
import org.unibl.etf.cinema.data.dto.PrikazivanjeFilmaUSaliDTO;
import org.unibl.etf.cinema.data.dto.RezervacijaDTO;
import org.unibl.etf.cinema.data.dto.SjedisteDTO;
import org.unibl.etf.cinema.data.dto.Zaposleni;
import org.unibl.etf.cinema.view.tables.RezervacijaTableModel;
import org.unibl.etf.cinema.view.tables.SjedisteTableModel;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import javax.swing.JComboBox;


public class KupiKartuForm extends JPanel{

	private KartaDAO kartaDAO = DAOFactory.getDAOFactory().getKartaDAO();
	private PrikazivanjeFilmaUSaliDAO pfusDAO= DAOFactory.getDAOFactory().getPrikazivanjeFilmaUSaliDAO();
	private PrikazivanjeFilmaUSaliDAO pfusDAO2= DAOFactory.getDAOFactory().getPrikazivanjeFilmaUSaliDAO();
	private SjedisteDAO sjedisteDAO = DAOFactory.getDAOFactory().getSjedisteDAO();
	private FilmDAO filmDAO = DAOFactory.getDAOFactory().getFilmDAO();
	private ZaposleniDAO zaposleniDAO = DAOFactory.getDAOFactory().getZaposleniDAO();
	private RezervacijaDAO rezervacijaDAO = DAOFactory.getDAOFactory().getRezervacijaDAO();
	private JComboBox comboBox;
	private JComboBox comboBox_2;
	//private Date termin;
	private JScrollPane scrollPane;
	private JButton btnRezervisi;
	private JButton btnKupi;
	private String naziv, termin;
	private Zaposleni zaposleni;
	
	
public JTextField tfPretraga;
private JTable table;
private JTable table_1;
public KupiKartuForm() {
	zaposleni = zaposleniDAO.zaposleni(6);
	setBackground(Color.GRAY);
	
	JPanel panel = new JPanel();
	
	scrollPane = new JScrollPane();

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
	table.setModel(new SjedisteTableModel(null));
	table.setFont(new Font("Arial", Font.PLAIN, 12));
	table.getColumnModel().getColumn(0).setPreferredWidth(50);
	table.getColumnModel().getColumn(1).setPreferredWidth(120);
	table.getColumnModel().getColumn(2).setPreferredWidth(140);
	table.getColumnModel().getColumn(3).setPreferredWidth(140);
	table.getColumnModel().getColumn(4).setPreferredWidth(80);
	
	JPanel pnlOpcije = new JPanel();
	pnlOpcije.setLayout(null);
	pnlOpcije.setOpaque(false);
	
	btnRezervisi = new JButton("Rezervi\u0161i");
	btnRezervisi.setEnabled(false);
	btnRezervisi.setBackground(new Color(220, 20, 60));
	btnRezervisi.setBounds(126, 5, 95, 36);
	pnlOpcije.add(btnRezervisi);
	
	btnKupi = new JButton("kupi");
	btnKupi.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			kupiKartu();
		}
	});
	btnKupi.setEnabled(false);
	btnKupi.setBackground(new Color(220, 20, 60));
	btnKupi.setBounds(340, 5, 95, 36);
	pnlOpcije.add(btnKupi);
	
	GroupLayout gl_pnlKorisnici = new GroupLayout(this);
	gl_pnlKorisnici.setHorizontalGroup(
		gl_pnlKorisnici.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_pnlKorisnici.createSequentialGroup()
				.addGap(29)
				.addGroup(gl_pnlKorisnici.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_pnlKorisnici.createSequentialGroup()
						.addComponent(pnlOpcije, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
					.addGroup(gl_pnlKorisnici.createSequentialGroup()
						.addGroup(gl_pnlKorisnici.createParallelGroup(Alignment.LEADING)
							.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
							.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
						.addGap(32))))
	);
	gl_pnlKorisnici.setVerticalGroup(
		gl_pnlKorisnici.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_pnlKorisnici.createSequentialGroup()
				.addContainerGap()
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(pnlOpcije, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(29, Short.MAX_VALUE))
	);
	
	
	
	JLabel lblNewLabel = new JLabel("Odaberi Film");
	
	JLabel lblNewLabel_1 = new JLabel("OdaberiTermin");
	
	comboBox = new JComboBox();
	comboBox_2 = new JComboBox();
	List<FilmDTO> filmDTO = filmDAO.selectAll();
	for (FilmDTO f : filmDTO) {
		comboBox_2.addItem(f);
	}

	comboBox_2.addItemListener(new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent e) {
			ucitajTermineZaFilm();
			
			}
		});
	
	/*comboBox.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			ucitajSjedistaUSali();
		}
	});*/
	
	JButton btnNewButton = new JButton("pretrazi");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			ucitajSjedistaUSali();
		
		}
	});
		
	GroupLayout gl_panel = new GroupLayout(panel);
	gl_panel.setHorizontalGroup(
		gl_panel.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panel.createSequentialGroup()
				.addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
					.addComponent(comboBox_2, 0, 176, Short.MAX_VALUE)
					.addComponent(lblNewLabel))
				.addGap(25)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addComponent(lblNewLabel_1)
						.addGap(43)
						.addComponent(btnNewButton)
						.addGap(26))
					.addGroup(gl_panel.createSequentialGroup()
						.addComponent(comboBox, 0, 0, Short.MAX_VALUE)
						.addContainerGap())))
	);
	gl_panel.setVerticalGroup(
		gl_panel.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panel.createSequentialGroup()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(20)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel)
							.addComponent(lblNewLabel_1)))
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(btnNewButton)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
					.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	);
	panel.setLayout(gl_panel);
	setLayout(gl_pnlKorisnici);
	}
	

	protected void kupiKartu() {
		int row = table.getSelectedRow();
		termin = comboBox.getSelectedItem().toString();
		naziv = comboBox.getSelectedItem().toString();
		String kljucnaRijec = comboBox.getSelectedItem().toString();
		List<PrikazivanjeFilmaUSaliDTO> pf = pfusDAO.sviTerminizaFilm((FilmDTO)comboBox_2.getSelectedItem());
		//PrikazivanjeFilmaUSaliDTO fp = pf.get(0);
		System.out.println(pf);
		Object[] opcije = { "Da", "Ne" };
		String message = "Da li ste sigurni da\nželite kupiti ovu kartu?";
		int value = JOptionPane.showOptionDialog(this, message, "", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null, opcije, opcije[0]);
		if (value == 0) {
			SjedisteDTO sjedisteDTO = ((SjedisteTableModel) table.getModel()).getSjedisteAtRow(row);
			System.out.println(sjedisteDTO);
			for (PrikazivanjeFilmaUSaliDTO pfus : pf) {
				
			if( kartaDAO.dodajKartu(new KartaDTO((5.0), new Date(System.currentTimeMillis()),false,sjedisteDTO,zaposleniDAO.zaposleni(6),pfusDAO.terminFilma(naziv,termin)))); 
			/*{
				if(kartaDAO.dodajKartu(kartaDAO.getByID(112))) {
				JOptionPane.showMessageDialog(this, "Karta uspješno Kupljena.", "Uspjeh",
						JOptionPane.INFORMATION_MESSAGE);
				//azurirajTabeluRezervacija();
			} else {
				JOptionPane.showMessageDialog(this, "Karta nije uspješno Kupljena.", "Greška",
						JOptionPane.ERROR_MESSAGE);
			}*/
			}
		}
	}			
		
			
		//new KartaDTO(5.0,"sada",new Date(1),new Date(System.currentTimeMillis()),false,sjedisteDTO,Zaposleni(),pfusDAO.sviTerminizaFilm(filmDAO.searchMovie((FilmDTO)comboBox_2.getSelectedItem())))
		
		
		
		



	protected void ucitajSjedistaUSali() {
		
		scrollPane.setViewportView(table);
		termin = comboBox.getSelectedItem().toString();
		naziv = comboBox.getSelectedItem().toString();
		SjedisteTableModel model = (SjedisteTableModel) table.getModel();
		FilmDTO f = (FilmDTO) comboBox_2.getSelectedItem();
		
		List<PrikazivanjeFilmaUSaliDTO> pf = pfusDAO.sviTerminizaFilm(f); 
		for (PrikazivanjeFilmaUSaliDTO pfus : pf) {
			if(termin.equals(pfus.termin)) {
			model.setSjediste(pfusDAO2.listaSjedistaZaTermin(pfus.getSala().getSalaID(), termin));
			model.fireTableDataChanged();
			}
		}
	}
	
	protected void ucitajTermineZaFilm() {
		comboBox.removeAllItems();
		
		List<PrikazivanjeFilmaUSaliDTO>pfus =pfusDAO.sviTerminizaFilm((FilmDTO)comboBox_2.getSelectedItem());
		for (PrikazivanjeFilmaUSaliDTO prikazivanjeFilmaUSaliDTO : pfus) {
			comboBox.addItem(prikazivanjeFilmaUSaliDTO);
		}	
	}
	
	private void podesiDugme() {
		boolean enabled = table.getSelectedRow() != -1;
		btnKupi.setEnabled(enabled);
		btnRezervisi.setEnabled(enabled);
	}
	
}






