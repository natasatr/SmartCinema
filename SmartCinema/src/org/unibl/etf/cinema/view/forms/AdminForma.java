package org.unibl.etf.cinema.view.forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicSpinnerUI;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import org.unibl.etf.cinema.data.dao.AdresaDAO;
import org.unibl.etf.cinema.data.dao.DAOFactory;
import org.unibl.etf.cinema.data.dao.KinoDAO;
import org.unibl.etf.cinema.data.dao.ZaposleniDAO;
import org.unibl.etf.cinema.data.dao.mysql.MySQLKinoDAO;
import org.unibl.etf.cinema.data.dto.AdresaDTO;
import org.unibl.etf.cinema.data.dto.KinoDTO;
import org.unibl.etf.cinema.data.dto.Zaposleni;
import org.unibl.etf.cinema.view.tables.ZaposleniTableModel;

import java.awt.Rectangle;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;

public class AdminForma extends JFrame {

	private JPanel contentPane;
	private JTextField tfNaziv;
	private JTextField tfEmail;
	private JTextField tfBrojTelefona;
	private JTextField tfMjesto;
	private JTextField tfUlica;
	private JTextField tfBroj;
	private JButton btnRight;
	private JButton btnLeft;

	private KinoDAO kinoDAO = DAOFactory.getDAOFactory().getKinoDAO();
	private AdresaDAO adresaDAO = DAOFactory.getDAOFactory().getAdresaDAO();
	private ZaposleniDAO zaposleniDAO = DAOFactory.getDAOFactory().getZaposleniDAO();
	private KinoDTO kino;
	private boolean kinoEditable = false;
	private JTable tblKorisnici;

	private JLabel[] labele = new JLabel[3];
	private JPanel[] paneli = new JPanel[3];

	private JLabel lblZaglavlje;
	private JPanel selektovaniPanel;

	private int INDEKS_OPCIJE = 0;

	private static final Color DEFAULT_MENU_BG_COLOR = new Color(65, 34, 72);
	private static final Font DEFAULT_MENU_FONT = new Font("Arial", Font.PLAIN, 16);
	private static final Color HOVER_MENU_BG_COLOR = new Color(65, 34, 72); // TODO
	private static final Font HOVER_MENU_FONT = new Font("Arial", Font.BOLD, 16);
	private JButton btnObrisi;
	private JButton btnAzuriraj;
	private JButton btnDodaj;

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
					AdminForma frame = new AdminForma();
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
	public AdminForma() {
		// Posto je prijava uspjela, znaci da kino sigurno postoji
		kino = kinoDAO.svaKina().get(0);

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

		JPanel pnlSadrzaj = new JPanel();
		GridBagConstraints gbc_pnlSadrzaj = new GridBagConstraints();
		gbc_pnlSadrzaj.gridheight = 3;
		gbc_pnlSadrzaj.insets = new Insets(0, 0, 0, 0);
		gbc_pnlSadrzaj.fill = GridBagConstraints.BOTH;
		gbc_pnlSadrzaj.gridx = 1;
		gbc_pnlSadrzaj.gridy = 1;
		contentPane.add(pnlSadrzaj, gbc_pnlSadrzaj);
		pnlSadrzaj.setLayout(new CardLayout(0, 0));

		JPanel pnlPocetnaStrana = new JPanel();
		paneli[0] = pnlPocetnaStrana;
		pnlPocetnaStrana.setBackground(new Color(240, 240, 240));
		pnlSadrzaj.add(pnlPocetnaStrana, "pnl_pocetna_strana");

		JPanel pnlKino = new JPanel();
		paneli[2] = pnlKino;
		pnlKino.setBackground(new Color(240, 240, 240));
		pnlSadrzaj.add(pnlKino, "pnl_kino");

		JPanel pnlKorisnici = new JPanel();
		paneli[1] = pnlKorisnici;
		pnlKorisnici.setBackground(new Color(240, 240, 240));
		pnlSadrzaj.add(pnlKorisnici, "pnl_korisnici");

		JScrollPane scrollPane = new JScrollPane();

		JPanel pnlOpcije = new JPanel();
		pnlOpcije.setOpaque(false);

		GroupLayout gl_pnlKorisnici = new GroupLayout(pnlKorisnici);
		gl_pnlKorisnici.setHorizontalGroup(gl_pnlKorisnici.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlKorisnici.createSequentialGroup().addGap(33)
						.addGroup(gl_pnlKorisnici.createParallelGroup(Alignment.TRAILING)
								.addComponent(pnlOpcije, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE))
						.addGap(33)));
		gl_pnlKorisnici.setVerticalGroup(gl_pnlKorisnici.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlKorisnici.createSequentialGroup().addGap(98)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE).addGap(36)
						.addComponent(pnlOpcije, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addGap(77)));

		btnObrisi = new JButton("");
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obrisiKorisnika();
			}
		});
		btnObrisi.setBackground(new Color(220, 20, 60));
		btnObrisi.setBounds(126, 5, 95, 36);
		btnObrisi.setIcon(
				new ImageIcon(AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/icon_delete.png")));

		btnDodaj = new JButton("");
		btnDodaj.setBackground(new Color(220, 20, 60));
		btnDodaj.setBounds(340, 5, 95, 36);
		btnDodaj.setIcon(new ImageIcon(AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/icon_add.png")));
		pnlOpcije.setLayout(null);

		btnAzuriraj = new JButton("");
		btnAzuriraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				azurirajKorisnika();
			}
		});
		btnAzuriraj.setBackground(new Color(220, 20, 60));
		btnAzuriraj.setBounds(233, 5, 95, 36);
		btnAzuriraj
				.setIcon(new ImageIcon(AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/icon_edit.png")));
		pnlOpcije.add(btnAzuriraj);
		pnlOpcije.add(btnObrisi);
		pnlOpcije.add(btnDodaj);

		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dodajKorisnika();
			}
		});

		tblKorisnici = new JTable();
		tblKorisnici.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						podesiDugmad();
					}
				});
			}
		});
		scrollPane.setViewportView(tblKorisnici);
		tblKorisnici.setFont(new Font("Arial", Font.PLAIN, 12));
		tblKorisnici.setModel(new ZaposleniTableModel(zaposleniDAO.sviZaposleni()));
		tblKorisnici.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblKorisnici.getColumnModel().getColumn(1).setPreferredWidth(120);
		tblKorisnici.getColumnModel().getColumn(2).setPreferredWidth(140);
		tblKorisnici.getColumnModel().getColumn(3).setPreferredWidth(140);
		tblKorisnici.getColumnModel().getColumn(4).setPreferredWidth(80);
		tblKorisnici.getColumnModel().getColumn(5).setPreferredWidth(120);
		tblKorisnici.getColumnModel().getColumn(6).setPreferredWidth(120);
		tblKorisnici.getColumnModel().getColumn(8).setPreferredWidth(200);
		pnlKorisnici.setLayout(gl_pnlKorisnici);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GroupLayout gl_pnlKino = new GroupLayout(pnlKino);
		gl_pnlKino.setHorizontalGroup(gl_pnlKino.createParallelGroup(Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE));
		gl_pnlKino.setVerticalGroup(gl_pnlKino.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlKino.createSequentialGroup().addGap(100)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 454, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(36, Short.MAX_VALUE)));

		JLabel lblNaslov = new JLabel("Podaci o kinu");
		lblNaslov.setFont(new Font("Arial", Font.BOLD, 18));

		JLabel lblNaziv = new JLabel("Naziv");
		lblNaziv.setFont(new Font("Arial", Font.PLAIN, 16));

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 16));

		JLabel lblBrojTelefona = new JLabel("Broj telefona");
		lblBrojTelefona.setFont(new Font("Arial", Font.PLAIN, 16));

		JLabel lblMjesto = new JLabel("Mjesto");
		lblMjesto.setFont(new Font("Arial", Font.PLAIN, 16));

		JLabel lblUlica = new JLabel("Ulica");
		lblUlica.setFont(new Font("Arial", Font.PLAIN, 16));

		JLabel lblBroj = new JLabel("Broj");
		lblBroj.setFont(new Font("Arial", Font.PLAIN, 16));

		JLabel lblSlika = new JLabel("");
		lblSlika.setIcon(
				new ImageIcon(AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/kino_forma.png")));

		btnRight = new JButton("Uredi");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (kinoEditable) {
					sacuvajKino();
					popuniPodatkeOKinu();
					btnRight.setText("Uredi");
				} else {
					setEditable(true);
					btnRight.setText("Saèuvaj");
				}
				kinoEditable = !kinoEditable;
				btnLeft.setVisible(!btnLeft.isVisible());
			}
		});
		btnRight.setForeground(Color.WHITE);
		btnRight.setBackground(new Color(220, 20, 60));
		btnRight.setFont(new Font("Arial", Font.PLAIN, 14));

		tfNaziv = new JTextField();
		tfNaziv.setFont(new Font("Arial", Font.PLAIN, 16));
		tfNaziv.setColumns(10);

		tfEmail = new JTextField();
		tfEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		tfEmail.setColumns(10);

		tfBrojTelefona = new JTextField();
		tfBrojTelefona.setFont(new Font("Arial", Font.PLAIN, 16));
		tfBrojTelefona.setColumns(10);

		tfMjesto = new JTextField();
		tfMjesto.setFont(new Font("Arial", Font.PLAIN, 16));
		tfMjesto.setColumns(10);

		tfUlica = new JTextField();
		tfUlica.setFont(new Font("Arial", Font.PLAIN, 16));
		tfUlica.setColumns(10);

		tfBroj = new JTextField();
		tfBroj.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println(tfBroj.getText().length());
				if (!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});

		tfBroj.setFont(new Font("Arial", Font.PLAIN, 16));

		btnLeft = new JButton("Otka\u017Ei");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popuniPodatkeOKinu();
				kinoEditable = false;
				btnLeft.setVisible(false);
				btnRight.setText("Uredi");
			}
		});
		btnLeft.setVisible(false);
		btnLeft.setForeground(Color.WHITE);
		btnLeft.setFont(new Font("Arial", Font.PLAIN, 14));
		btnLeft.setBackground(new Color(220, 20, 60));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(38)
				.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(btnLeft, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										ComponentPlacement.RELATED)
								.addComponent(btnRight, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
								.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup().addComponent(lblNaziv).addGap(73))
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 77,
														GroupLayout.PREFERRED_SIZE)
												.addGap(34))
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(lblBrojTelefona, GroupLayout.PREFERRED_SIZE, 108,
														GroupLayout.PREFERRED_SIZE)
												.addGap(3))
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(lblMjesto, GroupLayout.PREFERRED_SIZE, 64,
														GroupLayout.PREFERRED_SIZE)
												.addGap(47))
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(lblUlica, GroupLayout.PREFERRED_SIZE, 38,
														GroupLayout.PREFERRED_SIZE)
												.addGap(73))
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(lblBroj, GroupLayout.PREFERRED_SIZE, 38,
														GroupLayout.PREFERRED_SIZE)
												.addGap(73)))
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(tfUlica, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243,
												Short.MAX_VALUE)
										.addComponent(tfMjesto, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243,
												Short.MAX_VALUE)
										.addComponent(tfBrojTelefona, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243,
												Short.MAX_VALUE)
										.addComponent(tfEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243,
												Short.MAX_VALUE)
										.addComponent(tfNaziv, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243,
												Short.MAX_VALUE)
										.addComponent(tfBroj, Alignment.LEADING)))
								.addComponent(lblNaslov)))
				.addGap(90).addComponent(lblSlika).addGap(0, 0, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addGap(25).addComponent(lblNaslov).addGap(29)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNaziv).addComponent(
								tfNaziv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfEmail, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBrojTelefona, GroupLayout.PREFERRED_SIZE, 19,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(tfBrojTelefona, GroupLayout.PREFERRED_SIZE, 31,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMjesto, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfMjesto, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUlica, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfUlica, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBroj, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfBroj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup().addContainerGap()
								.addComponent(lblSlika, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)))
				.addGap(32)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(btnRight).addComponent(btnLeft,
						GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(41, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		pnlKino.setLayout(gl_pnlKino);

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

		JLabel lblPocetnaStrana = new JLabel("Po\u010Detna strana");
		labele[0] = lblPocetnaStrana;
		lblPocetnaStrana.setBackground(HOVER_MENU_BG_COLOR);
		lblPocetnaStrana.setIconTextGap(10);
		lblPocetnaStrana.setBorder(new EmptyBorder(0, 15, 0, 0));
		lblPocetnaStrana.setIcon(new ImageIcon(
				AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/icon_pocetna_strana.png")));
		lblPocetnaStrana.setForeground(new Color(255, 255, 255));
		lblPocetnaStrana.setFont(HOVER_MENU_FONT);
		lblPocetnaStrana.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblPocetnaStrana.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblPocetnaStrana = new GridBagConstraints();
		gbc_lblPocetnaStrana.insets = new Insets(0, 0, 0, 0);
		gbc_lblPocetnaStrana.fill = GridBagConstraints.BOTH;
		gbc_lblPocetnaStrana.gridx = 0;
		gbc_lblPocetnaStrana.gridy = 0;
		pnlMeni.add(lblPocetnaStrana, gbc_lblPocetnaStrana);

		JLabel lblKorisnici = new JLabel("Korisnici");
		labele[1] = lblKorisnici;
		lblKorisnici.setBackground(new Color(99, 62, 109));
		lblKorisnici.setIconTextGap(10);
		lblKorisnici.setIcon(
				new ImageIcon(AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/icon_korisnici.png")));
		lblKorisnici.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblKorisnici.setHorizontalAlignment(SwingConstants.LEFT);
		lblKorisnici.setForeground(Color.WHITE);
		lblKorisnici.setFont(new Font("Arial", Font.PLAIN, 16));
		lblKorisnici.setBorder(new EmptyBorder(0, 15, 0, 0));
		GridBagConstraints gbc_lblKorisnici = new GridBagConstraints();
		gbc_lblKorisnici.fill = GridBagConstraints.BOTH;
		gbc_lblKorisnici.insets = new Insets(0, 0, 0, 0);
		gbc_lblKorisnici.gridx = 0;
		gbc_lblKorisnici.gridy = 1;
		pnlMeni.add(lblKorisnici, gbc_lblKorisnici);

		JLabel lblKino = new JLabel("Kino");
		labele[2] = lblKino;
		lblKino.setBackground(new Color(127, 83, 135));
		lblKino.setIcon(new ImageIcon(AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/icon_kino.png")));
		lblKino.setIconTextGap(10);
		lblKino.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblKino.setHorizontalAlignment(SwingConstants.LEFT);
		lblKino.setForeground(Color.WHITE);
		lblKino.setFont(new Font("Arial", Font.PLAIN, 16));
		lblKino.setBorder(new EmptyBorder(0, 15, 0, 0));
		GridBagConstraints gbc_lblKino = new GridBagConstraints();
		gbc_lblKino.fill = GridBagConstraints.BOTH;
		gbc_lblKorisnici.fill = GridBagConstraints.BOTH;
		gbc_lblKino.gridx = 0;
		gbc_lblKino.gridy = 2;
		pnlMeni.add(lblKino, gbc_lblKino);

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
		popuniPodatkeOKinu();
		podesiDugmad();
	}

	private void popuniPodatkeOKinu() {
		tfNaziv.setText(kino.getNaziv());
		tfEmail.setText(kino.getEmail());
		tfBrojTelefona.setText(kino.getTelefon());
		tfMjesto.setText(kino.getAdresa().getMjesto());
		tfUlica.setText(kino.getAdresa().getUlica());
		tfBroj.setText(kino.getAdresa().getBroj() + "");

		setEditable(false);
	}

	private void sacuvajKino() {
		AdresaDTO adresa = new AdresaDTO(kino.getAdresa().getAdresaID(), tfMjesto.getText().trim(),
				tfUlica.getText().trim(), Integer.parseInt(tfBroj.getText().trim()));

		if (adresaDAO.azurirajAdresu(adresa)) {
			KinoDTO novoKino = new KinoDTO(kino.getKinoID(), tfNaziv.getText().trim(), tfEmail.getText().trim(),
					tfBrojTelefona.getText().trim(), adresa);
			if (kinoDAO.azurirajKino(novoKino)) {
				kino = novoKino;
			} else {
				// TODO poruka o gresci
			}
		} else {
			// TODO poruka o gresci
		}
	}

	private void setEditable(boolean editable) {
		tfNaziv.setEditable(editable);
		tfEmail.setEditable(editable);
		tfBrojTelefona.setEditable(editable);
		tfMjesto.setEditable(editable);
		tfUlica.setEditable(editable);
		tfBroj.setEditable(editable);
	}

	public void azurirajTabeluKorisnici() {
		ZaposleniTableModel model = (ZaposleniTableModel) tblKorisnici.getModel();
		model.setZaposleni(zaposleniDAO.sviZaposleni()); // TODO filtrirane podatke ubaciti
		model.fireTableDataChanged();
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

	public void azurirajTabeluKorisnika() {
		ZaposleniTableModel model = (ZaposleniTableModel) tblKorisnici.getModel();
		model.setZaposleni(zaposleniDAO.sviZaposleni());
		model.fireTableDataChanged();
	}

	private void dodajKorisnika() {
		new ZaposleniDialog(this, null).setVisible(true);
	}

	private void azurirajKorisnika() {
		Zaposleni zaposleni = ((ZaposleniTableModel) tblKorisnici.getModel())
				.getZaposleni(tblKorisnici.getSelectedRow());
		new ZaposleniDialog(this, zaposleni).setVisible(true);
	}

	private void obrisiKorisnika() {
		int row = tblKorisnici.getSelectedRow();

		Object[] opcije = { "Da", "Ne" };
		String message = "Da li ste sigurni da\nželite ukloniti korisnika?";
		int value = JOptionPane.showOptionDialog(this, message, "", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null, opcije, opcije[0]);
		if (value == 0) {
			Zaposleni zaposleni = ((ZaposleniTableModel) tblKorisnici.getModel()).getZaposleni(row);
			if (zaposleniDAO.obrisiZaposlenog(zaposleni)) {
				JOptionPane.showMessageDialog(this, "Zaposleni je uspješno obrisan.", "Uspjeh",
						JOptionPane.INFORMATION_MESSAGE);
				azurirajTabeluKorisnici();
			} else {
				JOptionPane.showMessageDialog(this, "Zaposleni nije uspješno obrisan.", "Greška",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	void podesiDugmad() {
		boolean enabled = tblKorisnici.getSelectedRow() != -1;
		btnAzuriraj.setEnabled(enabled);
		btnObrisi.setEnabled(enabled);
	}
}
