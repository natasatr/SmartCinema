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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.cinema.data.dao.DAOFactory;
import org.unibl.etf.cinema.data.dao.ZaposleniDAO;
import org.unibl.etf.cinema.data.dto.Zaposleni;
import org.unibl.etf.cinema.util.UIUtils;

public class RadnikForm extends JFrame {

	private JPanel contentPane;
	private JLabel lblZaglavlje;
	private Zaposleni prijavljeniKorisnik;
	private JLabel[] labele = new JLabel[3];
	private JPanel[] paneli = new JPanel[3];
	private int INDEKS_OPCIJE = 0;
	
	private ZaposleniDAO zaposleniDAO= DAOFactory.getDAOFactory().getZaposleniDAO();
	
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
	}
 
	/**
	 * Create the frame.
	 */
	public RadnikForm(int nalogID) {
		setTitle("SmartCinema");
		this.prijavljeniKorisnik = zaposleniDAO.zaposleni(nalogID); 
		//setBounds(50, 50, 500, 500);
		// Posto je prijava uspjela, znaci da kino sigurno postoji
		//kino = kinoDAO.svaKina().get(0);
		//prijavljeniKorisnik = ZaposleniDAO.zaposleni(nalogID);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1112, 679);
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
		lblLogo.setIcon(new ImageIcon(AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/mali_logo.png")));
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
				AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/icon_pocetna_strana.png")));
		lblZaglavlje.setIconTextGap(10);
		lblZaglavlje.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblZaglavlje.setHorizontalAlignment(SwingConstants.LEFT);
		lblZaglavlje.setForeground(Color.WHITE);
		lblZaglavlje.setFont(new Font("Arial", Font.PLAIN, 16));
		lblZaglavlje.setBorder(new EmptyBorder(0, 15, 0, 0));
		pnlZaglavlje.add(lblZaglavlje);
		pnlZaglavlje.add(Box.createHorizontalGlue());

		final JLabel lblOdjava = new JLabel("Odjava");
		lblOdjava.setBackground(UIUtils.DEFAULT_MENU_BG_COLOR);
		lblOdjava.setOpaque(true);
		lblOdjava.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblOdjava.setBackground(UIUtils.HOVER_MENU_BG_COLOR);
				lblOdjava.setFont(UIUtils.HOVER_MENU_FONT);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblOdjava.setBackground(UIUtils.DEFAULT_MENU_BG_COLOR);
				lblOdjava.setFont(UIUtils.DEFAULT_MENU_FONT);
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
		
		JPanel pnlKupiKartu = new KupiKartuForm(prijavljeniKorisnik);
		paneli[0] = pnlKupiKartu;
		pnlKupiKartu.setBackground(new Color(240, 240, 240));
		pnlSadrzaj.add(pnlKupiKartu,"pnlKupi");

		JPanel pnlRezervacije = new RezervacijeForm(); 
		paneli[1] = pnlRezervacije;
		pnlRezervacije.setBackground(new Color(240, 240, 240));
		pnlSadrzaj.add(pnlRezervacije, "pnl_kino");
		
		JPanel pnlTermini = new TerminiForm();
		paneli[2] = pnlTermini;
		pnlRezervacije.setBackground(new Color(240,240,240));
		pnlSadrzaj.add(pnlTermini,"pnl_Termini");
		
		
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

		JLabel lblKarte = new JLabel("Kupi");
		lblKarte.setOpaque(true);
		labele[0] = lblKarte;
		lblKarte.setBackground(UIUtils.DEFAULT_MENU_BG_COLOR);
		lblKarte.setIconTextGap(10);
		lblKarte.setIcon(
				new ImageIcon(RadnikForm.class.getResource("/org/unibl/etf/cinema/view/icons/round_shopping_cart_white_36dp.png")));
		lblKarte.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblKarte.setHorizontalAlignment(SwingConstants.LEFT);
		lblKarte.setForeground(Color.WHITE);
		lblKarte.setFont(new Font("Arial", Font.PLAIN, 16));
		lblKarte.setBorder(new EmptyBorder(0, 15, 0, 0));
		GridBagConstraints gbc_lblKarte = new GridBagConstraints();
		gbc_lblKarte.fill = GridBagConstraints.BOTH;
		gbc_lblKarte.insets = new Insets(0, 0, 0, 0);
		gbc_lblKarte.gridx = 0;
		gbc_lblKarte.gridy = 0;
		pnlMeni.add(lblKarte, gbc_lblKarte);

		JLabel lblRezervacije = new JLabel("Rezervacije");
		lblRezervacije.setOpaque(true);
		labele[1] = lblRezervacije;
		lblRezervacije.setBackground(UIUtils.DEFAULT_MENU_BG_COLOR);
		lblRezervacije.setIcon(new ImageIcon(RadnikForm.class.getResource("/org/unibl/etf/cinema/view/icons/round_inventory_white_36dp.png")));
		lblRezervacije.setIconTextGap(10);
		lblRezervacije.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblRezervacije.setHorizontalAlignment(SwingConstants.LEFT);
		lblRezervacije.setForeground(Color.WHITE);
		lblRezervacije.setFont(new Font("Arial", Font.PLAIN, 16));
		lblRezervacije.setBorder(new EmptyBorder(0, 15, 0, 0));
		GridBagConstraints gbc_lblRezervacije = new GridBagConstraints();
		gbc_lblRezervacije.fill = GridBagConstraints.BOTH;
		gbc_lblKarte.fill = GridBagConstraints.BOTH;
		gbc_lblRezervacije.gridx = 0;
		gbc_lblRezervacije.gridy = 1;
		pnlMeni.add(lblRezervacije, gbc_lblRezervacije);

		JLabel lblTermini = new JLabel("Termini");
		lblTermini.setOpaque(true);
		labele[2] = lblTermini;
		lblTermini.setBackground(UIUtils.DEFAULT_MENU_BG_COLOR);
		lblTermini.setIcon(new ImageIcon(AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/icon_kino.png")));
		lblTermini.setIconTextGap(10);
		lblTermini.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblTermini.setHorizontalAlignment(SwingConstants.LEFT);
		lblTermini.setForeground(Color.WHITE);
		lblTermini.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTermini.setBorder(new EmptyBorder(0, 15, 0, 0));
		GridBagConstraints gbc_lblTermini = new GridBagConstraints();
		gbc_lblTermini.fill = GridBagConstraints.BOTH;
		gbc_lblRezervacije.fill = GridBagConstraints.BOTH;
		gbc_lblKarte.fill = GridBagConstraints.BOTH;

		gbc_lblTermini.gridx = 0;
		gbc_lblTermini.gridy = 2;
		pnlMeni.add(lblTermini, gbc_lblTermini);

		
		
		for (int i = 0; i < labele.length; i++) {
			final int indeks = i;
			labele[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					labele[indeks].setBackground(UIUtils.HOVER_MENU_BG_COLOR);
					labele[indeks].setFont(UIUtils.HOVER_MENU_FONT);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					if (INDEKS_OPCIJE != indeks) {
						labele[indeks].setBackground(UIUtils.DEFAULT_MENU_BG_COLOR);
						labele[indeks].setFont(UIUtils.DEFAULT_MENU_FONT);
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
		labele[0].setBackground(UIUtils.HOVER_MENU_BG_COLOR);
		labele[0].setFont(UIUtils.HOVER_MENU_FONT);
		postaviSelektovaniPanel();
		postaviZaglavlje(labele[0]);


	}
	

	private void postaviZaglavlje(JLabel label) {
		lblZaglavlje.setText(label.getText());
		lblZaglavlje.setIcon(label.getIcon());

	}

	private void postaviSelektovaniPanel() {
		for (int i = 0; i < paneli.length; i++) {
			paneli[i].setVisible(INDEKS_OPCIJE == i);
		}
	}

	private void postaviOstaleLabele() {
		for (int i = 0; i < labele.length; i++) {
			if (INDEKS_OPCIJE != i) {
				labele[i].setBackground(UIUtils.DEFAULT_MENU_BG_COLOR);
				labele[i].setFont(UIUtils.DEFAULT_MENU_FONT);
			}
		}
	}

	private void odjava() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new LoginForma().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		dispose();
	}

}
