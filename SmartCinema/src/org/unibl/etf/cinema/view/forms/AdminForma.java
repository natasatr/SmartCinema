package org.unibl.etf.cinema.view.forms;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.unibl.etf.cinema.data.dao.AdresaDAO;
import org.unibl.etf.cinema.data.dao.DAOFactory;
import org.unibl.etf.cinema.data.dao.KinoDAO;
import org.unibl.etf.cinema.data.dao.ZaposleniDAO;
import org.unibl.etf.cinema.data.dto.AdresaDTO;
import org.unibl.etf.cinema.data.dto.KinoDTO;
import org.unibl.etf.cinema.data.dto.Zaposleni;
import org.unibl.etf.cinema.util.EmailValidator;
import org.unibl.etf.cinema.util.UIUtils;
import org.unibl.etf.cinema.util.Utils;
import org.unibl.etf.cinema.view.tables.ZaposleniTableModel;

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

	private JLabel[] labele = new JLabel[2];
	private JPanel[] paneli = new JPanel[2];
	private List<JTextField> kinoPolja = new ArrayList<>();

	private JLabel lblZaglavlje;
	private JPanel selektovaniPanel;

	private int INDEKS_OPCIJE = 0;

	private JButton btnObrisi;
	private JButton btnAzuriraj;
	private JButton btnDodaj;
	private JTextField tfPretraga;

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
					AdminForma frame = new AdminForma(1);
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
	public AdminForma(int nalogID) {
		setTitle("SmartCinema");
		// Posto je prijava uspjela, znaci da kino sigurno postoji
		kino = kinoDAO.svaKina().get(0);
		prijavljeniKorisnik = zaposleniDAO.zaposleni(nalogID);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 1112, 679);
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

		JPanel pnlKorisnici = new JPanel();
		paneli[0] = pnlKorisnici;
		pnlKorisnici.setBackground(new Color(240, 240, 240));
		pnlSadrzaj.add(pnlKorisnici, "pnl_korisnici");

		JPanel pnlKino = new JPanel();
		paneli[1] = pnlKino;
		pnlKino.setBackground(new Color(240, 240, 240));
		pnlSadrzaj.add(pnlKino, "pnl_kino");

		JScrollPane scrollPane = new JScrollPane();

		JPanel pnlOpcije = new JPanel();
		pnlOpcije.setOpaque(false);

		JPanel pnlPretraga = new JPanel();
		pnlPretraga.setBackground(UIUtils.BUTTON_COLOR);
		pnlPretraga.setBorder(null);

		GroupLayout gl_pnlKorisnici = new GroupLayout(pnlKorisnici);
		gl_pnlKorisnici.setHorizontalGroup(gl_pnlKorisnici.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlKorisnici.createSequentialGroup().addGroup(gl_pnlKorisnici
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlKorisnici.createSequentialGroup().addContainerGap().addComponent(pnlOpcije,
								GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlKorisnici.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_pnlKorisnici.createSequentialGroup().addContainerGap().addComponent(
										pnlPretraga, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pnlKorisnici.createSequentialGroup().addGap(36).addComponent(scrollPane,
										GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE))))
						.addGap(40)));
		gl_pnlKorisnici.setVerticalGroup(gl_pnlKorisnici.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlKorisnici.createSequentialGroup().addGap(36)
						.addComponent(pnlPretraga, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(31).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE).addGap(33)
						.addComponent(pnlOpcije, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addGap(50)));
		GridBagLayout gbl_pnlPretraga = new GridBagLayout();
		gbl_pnlPretraga.columnWidths = new int[] { 40, 150 };
		gbl_pnlPretraga.rowHeights = new int[] { 29 };
		gbl_pnlPretraga.columnWeights = new double[] { 0.0, 1.0 };
		gbl_pnlPretraga.rowWeights = new double[] { 1.0 };
		pnlPretraga.setLayout(gbl_pnlPretraga);

		JLabel lblNewLabel = new JLabel("");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		pnlPretraga.add(lblNewLabel, gbc_lblNewLabel);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(
				new ImageIcon(AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/icon_search.png")));

		tfPretraga = new JTextField();
		GridBagConstraints gbc_tfPretraga = new GridBagConstraints();
		gbc_tfPretraga.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_tfPretraga.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPretraga.gridx = 1;
		gbc_tfPretraga.gridy = 0;
		pnlPretraga.add(tfPretraga, gbc_tfPretraga);
		tfPretraga.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				azurirajTabeluKorisnika();
			}
		});
		tfPretraga.setFont(new Font("Arial", Font.PLAIN, 14));
		tfPretraga.setColumns(10);

		btnObrisi = new JButton("");
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obrisiKorisnika();
			}
		});
		btnObrisi.setBackground(UIUtils.BUTTON_COLOR);
		btnObrisi.setBounds(126, 5, 95, 36);
		btnObrisi.setIcon(
				new ImageIcon(AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/icon_delete.png")));

		btnDodaj = new JButton("");
		btnDodaj.setBackground(UIUtils.BUTTON_COLOR);
		btnDodaj.setBounds(340, 5, 95, 36);
		btnDodaj.setIcon(new ImageIcon(AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/icon_add.png")));
		pnlOpcije.setLayout(null);

		btnAzuriraj = new JButton("");
		btnAzuriraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				azurirajKorisnika();
			}
		});
		btnAzuriraj.setBackground(UIUtils.BUTTON_COLOR);
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
					if (provjeriPodatkeOKinu()) {
						sacuvajKino();
						popuniPodatkeOKinu();
						btnRight.setText("Uredi");
						kinoEditable = !kinoEditable;
						btnLeft.setVisible(!btnLeft.isVisible());
					} else {
						getToolkit().beep();
					}
				} else {
					setEditable(true);
					btnRight.setText("Saèuvaj");
					kinoEditable = !kinoEditable;
					btnLeft.setVisible(!btnLeft.isVisible());
				}
			}
		});
		btnRight.setForeground(Color.WHITE);
		btnRight.setBackground(UIUtils.BUTTON_COLOR);
		btnRight.setFont(new Font("Arial", Font.PLAIN, 14));

		tfNaziv = new JTextField();
		tfNaziv.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				UIUtils.ograniciDuzinuUnosa(e, tfNaziv);
			}
		});
		tfNaziv.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 0)));
		tfNaziv.setFont(new Font("Arial", Font.PLAIN, 16));
		tfNaziv.setColumns(10);
		kinoPolja.add(tfNaziv);

		tfEmail = new JTextField();
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				UIUtils.ograniciDuzinuUnosa(e, tfEmail);
			}
		});
		tfEmail.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 0)));
		tfEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		tfEmail.setColumns(10);
		kinoPolja.add(tfEmail);

		tfBrojTelefona = new JTextField();
		tfBrojTelefona.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				UIUtils.onemoguciUnosNonDigitKaraktera(e, tfBrojTelefona, true);
				UIUtils.ograniciDuzinuUnosa(e, tfBrojTelefona);
			}
		});
		tfBrojTelefona.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 0)));
		tfBrojTelefona.setFont(new Font("Arial", Font.PLAIN, 16));
		tfBrojTelefona.setColumns(10);
		kinoPolja.add(tfBrojTelefona);

		tfMjesto = new JTextField();
		tfMjesto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				UIUtils.ograniciDuzinuUnosa(e, tfMjesto);
			}
		});
		tfMjesto.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 0)));
		tfMjesto.setFont(new Font("Arial", Font.PLAIN, 16));
		tfMjesto.setColumns(10);
		kinoPolja.add(tfMjesto);

		tfUlica = new JTextField();
		tfUlica.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				UIUtils.ograniciDuzinuUnosa(e, tfUlica);
			}
		});
		tfUlica.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 0)));
		tfUlica.setFont(new Font("Arial", Font.PLAIN, 16));
		tfUlica.setColumns(10);
		kinoPolja.add(tfUlica);

		tfBroj = new JTextField();
		tfBroj.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 0)));
		tfBroj.setEditable(false);
		tfBroj.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				UIUtils.onemoguciUnosNonDigitKaraktera(e, tfBroj, true);
			}
		});

		tfBroj.setFont(new Font("Arial", Font.PLAIN, 16));
		kinoPolja.add(tfBroj);

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
		btnLeft.setBackground(UIUtils.BUTTON_COLOR);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(38)
				.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(btnLeft, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnRight, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false).addGroup(gl_panel
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
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(tfUlica, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243,
														Short.MAX_VALUE)
												.addComponent(tfMjesto, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														243, Short.MAX_VALUE)
												.addComponent(tfBrojTelefona, Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
												.addComponent(tfEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243,
														Short.MAX_VALUE))
										.addComponent(tfNaziv, GroupLayout.PREFERRED_SIZE, 243,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(tfBroj)))
								.addComponent(lblNaslov)))
				.addGap(90).addComponent(lblSlika).addGap(0, 0, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addGap(25).addComponent(lblNaslov).addGap(29)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNaziv)
								.addComponent(tfNaziv, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
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
								.addComponent(tfBroj, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(lblSlika,
								GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(btnRight).addComponent(btnLeft,
						GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
				.addGap(41)));
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

		JLabel lblKorisnici = new JLabel("Korisnici");
		lblKorisnici.setOpaque(true);
		labele[0] = lblKorisnici;
		lblKorisnici.setBackground(UIUtils.DEFAULT_MENU_BG_COLOR);
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
		gbc_lblKorisnici.gridy = 0;
		pnlMeni.add(lblKorisnici, gbc_lblKorisnici);

		JLabel lblKino = new JLabel("Kino");
		lblKino.setOpaque(true);
		labele[1] = lblKino;
		lblKino.setBackground(UIUtils.DEFAULT_MENU_BG_COLOR);
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
		gbc_lblKino.gridy = 1;
		pnlMeni.add(lblKino, gbc_lblKino);

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
		popuniPodatkeOKinu();
		podesiDugmad();
	}

	private void popuniPodatkeOKinu() {
		tfNaziv.setText(kino.getNaziv());
		tfEmail.setText(kino.getEmail());
		tfBrojTelefona.setText(kino.getTelefon());
		tfMjesto.setText(kino.getAdresa().getMjesto());
		tfUlica.setText(kino.getAdresa().getUlica());
		tfBroj.setText("2");

		setEditable(false);
	}

	private boolean provjeriPodatkeOKinu() {
		List<JTextField> nevalidnaPolja = UIUtils.praznaPolja(kinoPolja);

		EmailValidator validator = new EmailValidator();
		if (!validator.validate(tfEmail.getText().trim())) {
			if (!nevalidnaPolja.contains(tfEmail)) {
				nevalidnaPolja.add(tfEmail);
			}
		}

		if (!Utils.isIntegerNumber(tfBroj.getText().trim())) {
			if (!nevalidnaPolja.contains(tfBroj)) {
				nevalidnaPolja.add(tfBroj);
			}
		}

		UIUtils.postaviIvicePolja(nevalidnaPolja, kinoPolja);
		return nevalidnaPolja.isEmpty();
	}

	private void sacuvajKino() {
		try {
			AdresaDTO adresa = new AdresaDTO(kino.getAdresa().getAdresaID(), tfMjesto.getText().trim(),
					tfUlica.getText().trim(), Integer.parseInt(tfBroj.getText().trim()));
			KinoDTO novoKino = new KinoDTO(kino.getKinoID(), tfNaziv.getText().trim(), tfEmail.getText().trim(),
					tfBrojTelefona.getText().trim(), adresa);

			if (!adresaDAO.azurirajAdresu(adresa)) {
				throw new Exception();
			}
			if (!kinoDAO.azurirajKino(novoKino)) {
				throw new Exception();
			}

			kino = novoKino;
			JOptionPane.showMessageDialog(this, "Podaci o kinu su uspješno saèuvani.", "Uspjeh",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Podaci o kinu nisu saèuvani.", "Greška", JOptionPane.ERROR_MESSAGE);
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

	public void azurirajTabeluKorisnika() {
		String kljucnaRijec = tfPretraga.getText().trim();
		ZaposleniTableModel model = (ZaposleniTableModel) tblKorisnici.getModel();
		model.setZaposleni(zaposleniDAO.pretraga(kljucnaRijec));
		model.fireTableDataChanged();
	}

	private void dodajKorisnika() {
		JDialog dialog = new ZaposleniDialog(this, null);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}

	private void azurirajKorisnika() {
		Zaposleni zaposleni = ((ZaposleniTableModel) tblKorisnici.getModel())
				.getZaposleni(tblKorisnici.getSelectedRow());
		JDialog dialog = new ZaposleniDialog(this, zaposleni);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
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
				azurirajTabeluKorisnika();
			} else {
				JOptionPane.showMessageDialog(this, "Zaposleni nije uspješno obrisan.", "Greška",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void podesiDugmad() {
		boolean enabled = tblKorisnici.getSelectedRow() != -1;
		btnAzuriraj.setEnabled(enabled);
		btnObrisi.setEnabled(enabled);
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
