package org.unibl.etf.cinema.view.forms;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.cinema.data.dao.AdresaDAO;
import org.unibl.etf.cinema.data.dao.DAOFactory;
import org.unibl.etf.cinema.data.dao.FilmDAO;
//import org.unibl.etf.cinema.data.dao.FilmDAO;
import org.unibl.etf.cinema.data.dao.KinoDAO;
import org.unibl.etf.cinema.data.dao.ZaposleniDAO;
import org.unibl.etf.cinema.data.dto.KinoDTO;
import org.unibl.etf.cinema.data.dto.Zaposleni;

public class FilmoviGlavnaForma extends JFrame {

	private JPanel contentPane;

	private KinoDAO kinoDAO = DAOFactory.getDAOFactory().getKinoDAO();
	private AdresaDAO adresaDAO = DAOFactory.getDAOFactory().getAdresaDAO();
	private ZaposleniDAO zaposleniDAO = DAOFactory.getDAOFactory().getZaposleniDAO();
	private KinoDTO kino;
	private boolean kinoEditable = false;
	private FilmDAO filmDAO = DAOFactory.getDAOFactory().getFilmDAO();

	private JLabel[] labele = new JLabel[1];
	private JPanel[] paneli = new JPanel[1];
	private List<JTextField> kinoPolja = new ArrayList<>();

	private JLabel lblZaglavlje;
	private JPanel selektovaniPanel;

	private int INDEKS_OPCIJE = 0;

	private static final Color DEFAULT_MENU_BG_COLOR = new Color(65, 34, 72);
	private static final Font DEFAULT_MENU_FONT = new Font("Arial", Font.PLAIN, 16);
	private static final Color HOVER_MENU_BG_COLOR = new Color(65, 34, 72); // TODO
	private static final Font HOVER_MENU_FONT = new Font("Arial", Font.BOLD, 16);
	
	private Zaposleni prijavljeniKorisnik;	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {

		}
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FilmoviGlavnaForma frame = new FilmoviGlavnaForma(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FilmoviGlavnaForma(int nalogID) {
		// Posto je prijava uspjela, znaci da kino sigurno postoji
		//kino = kinoDAO.svaKina().get(0);
	//	prijavljeniKorisnik = zaposleniDAO.zaposleni(nalogID);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1112, 679);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 250, 0 };
		gbl_contentPane.rowHeights = new int[] { 50, 50, 50, 0 };
		gbl_contentPane.columnWeights = new double[] { 0, 1.0 };
		gbl_contentPane.rowWeights = new double[] { 0, 0, 0, 1 };
		contentPane.setLayout(gbl_contentPane);

		JPanel pnlLogo = new JPanel();
		pnlLogo.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_pnlLogo = new GridBagConstraints();
		gbc_pnlLogo.gridheight = 3;
		gbc_pnlLogo.insets = new Insets(0, 0, 0, 0);
		gbc_pnlLogo.fill = GridBagConstraints.BOTH;
		gbc_pnlLogo.gridx = 0;
		gbc_pnlLogo.gridy = 0;
		contentPane.add(pnlLogo, gbc_pnlLogo);
		pnlLogo.setLayout(new BorderLayout(0, 0));

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(FilmoviGlavnaForma.class.getResource("/org/unibl/etf/cinema/view/icons/mali_logo.png")));
		pnlLogo.add(lblLogo);

		JPanel pnlZaglavlje = new JPanel();
		pnlZaglavlje.setBackground(new Color(33, 20, 47));
		GridBagConstraints gbc_pnlZaglavlje = new GridBagConstraints();
		gbc_pnlZaglavlje.insets = new Insets(0, 0, 0, 0);
		gbc_pnlZaglavlje.fill = GridBagConstraints.BOTH;
		gbc_pnlZaglavlje.gridx = 1;
		gbc_pnlZaglavlje.gridy = 0;
		contentPane.add(pnlZaglavlje, gbc_pnlZaglavlje);
		pnlZaglavlje.setLayout(new BoxLayout(pnlZaglavlje, BoxLayout.X_AXIS));

		lblZaglavlje = new JLabel("Po\u010Detna strana");
		lblZaglavlje.setIcon(new ImageIcon(
				FilmoviGlavnaForma.class.getResource("/org/unibl/etf/cinema/view/icons/icon_pocetna_strana.png")));
		lblZaglavlje.setIconTextGap(10);
		lblZaglavlje.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblZaglavlje.setHorizontalAlignment(SwingConstants.LEFT);
		lblZaglavlje.setForeground(Color.WHITE);
		lblZaglavlje.setFont(new Font("Arial", Font.PLAIN, 16));
		lblZaglavlje.setBorder(new EmptyBorder(0, 15, 0, 0));
		pnlZaglavlje.add(lblZaglavlje);
		pnlZaglavlje.add(Box.createHorizontalGlue());
		
		final JLabel lblOdjava = new JLabel("Odjava");
		lblOdjava.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblOdjava.setBackground(HOVER_MENU_BG_COLOR);
				lblOdjava.setFont(HOVER_MENU_FONT);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblOdjava.setBackground(DEFAULT_MENU_BG_COLOR);
				lblOdjava.setFont(DEFAULT_MENU_FONT);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				odjava();
			}
		});
		lblOdjava.setBorder(new EmptyBorder(10, 15, 10, 15));
		lblOdjava.setForeground(Color.WHITE);
		lblOdjava.setFont(new Font("Arial", Font.PLAIN, 16));
		pnlZaglavlje.add(lblOdjava);

		JPanel pnlSadrzaj = new JPanel();
		GridBagConstraints gbc_pnlSadrzaj = new GridBagConstraints();
		gbc_pnlSadrzaj.gridheight = 3;
		gbc_pnlSadrzaj.insets = new Insets(0, 0, 0, 0);
		gbc_pnlSadrzaj.fill = GridBagConstraints.BOTH;
		gbc_pnlSadrzaj.gridx = 1;
		gbc_pnlSadrzaj.gridy = 1;
		contentPane.add(pnlSadrzaj, gbc_pnlSadrzaj);
		pnlSadrzaj.setLayout(new CardLayout(0, 0));
		
		JPanel pnlFilmovi = new JPanel();
		pnlSadrzaj.add(pnlFilmovi, "name_25683666976132");
		paneli[0] = pnlFilmovi;
		pnlFilmovi.setLayout(null);

		JPanel pnlMeni = new JPanel();
		pnlMeni.setBorder(new EmptyBorder(20, 0, 0, 0));
		pnlMeni.setBackground(new Color(33, 20, 47));
		GridBagConstraints gbc_pnlMeni = new GridBagConstraints();
		gbc_pnlMeni.insets = new Insets(0, 0, 0, 0);
		gbc_pnlMeni.fill = GridBagConstraints.BOTH;
		gbc_pnlMeni.gridx = 0;
		gbc_pnlMeni.gridy = 3;
		contentPane.add(pnlMeni, gbc_pnlMeni);
		GridBagLayout gbl_pnlMeni = new GridBagLayout();
		gbl_pnlMeni.columnWidths = new int[] { 0 };
		gbl_pnlMeni.rowHeights = new int[] { 50, 50, 50, 0 };
		gbl_pnlMeni.columnWeights = new double[] { 1.0 };
		gbl_pnlMeni.rowWeights = new double[] { 0, 0, 0, 1 };
		pnlMeni.setLayout(gbl_pnlMeni);

		JLabel lblFilmovi = new JLabel("Filmovi");
		lblFilmovi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FilmFrame noviFrame = new FilmFrame();
				noviFrame.setVisible(true);
				
			}
		});
		labele[0] = lblFilmovi;
		lblFilmovi.setBackground(new Color(99, 62, 109));
		lblFilmovi.setIconTextGap(10);
		lblFilmovi.setIcon(
				new ImageIcon(FilmoviGlavnaForma.class.getResource("/org/unibl/etf/cinema/view/icons/round_movie_white_18dp.png")));
		lblFilmovi.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblFilmovi.setHorizontalAlignment(SwingConstants.LEFT);
		lblFilmovi.setForeground(Color.WHITE);
		lblFilmovi.setFont(new Font("Arial", Font.PLAIN, 16));
		lblFilmovi.setBorder(new EmptyBorder(0, 15, 0, 0));
		GridBagConstraints gbc_lblKorisnici = new GridBagConstraints();
		gbc_lblKorisnici.fill = GridBagConstraints.BOTH;
		gbc_lblKorisnici.insets = new Insets(0, 0, 0, 0);
		gbc_lblKorisnici.gridx = 0;
		gbc_lblKorisnici.gridy = 0;
		pnlMeni.add(lblFilmovi, gbc_lblKorisnici);
		gbc_lblKorisnici.fill = GridBagConstraints.BOTH;

		for (int i = 0; i < labele.length; i++) {
			final int indeks = i;
			labele[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					labele[indeks].setBackground(HOVER_MENU_BG_COLOR);
					labele[indeks].setFont(HOVER_MENU_FONT);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					if (INDEKS_OPCIJE != indeks) {
						labele[indeks].setBackground(DEFAULT_MENU_BG_COLOR);
						labele[indeks].setFont(DEFAULT_MENU_FONT);
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {
					postaviZaglavlje(labele[indeks]);
					INDEKS_OPCIJE = indeks;
					postaviOstaleLabele();
					postaviSelektovaniPanel();
				}
			});
		}
	}




	private void postaviZaglavlje(JLabel label) {
		lblZaglavlje.setText(label.getText());
		lblZaglavlje.setIcon(label.getIcon());

	}

	private void postaviSelektovaniPanel() {
		for (int i = 0; i < paneli.length; i++) {
			if (i == INDEKS_OPCIJE) {
				paneli[i].setVisible(true);
			} else {
				paneli[i].setVisible(false);
			}
		}
	}

	private void postaviOstaleLabele() {
		for (int i = 0; i < labele.length; i++) {
			if (INDEKS_OPCIJE != i) {
				labele[i].setBackground(DEFAULT_MENU_BG_COLOR);
				labele[i].setFont(DEFAULT_MENU_FONT);
			}
		}
	}
	
	private void odjava() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LoginForma frame = new LoginForma();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		dispose();
	}
}

