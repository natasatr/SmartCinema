package org.unibl.etf.cinema.view.forms;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.unibl.etf.cinema.data.dao.AdresaDAO;
import org.unibl.etf.cinema.data.dao.DAOFactory;
import org.unibl.etf.cinema.data.dao.NalogDAO;
import org.unibl.etf.cinema.data.dao.RolaDAO;
import org.unibl.etf.cinema.data.dao.ZaposleniDAO;
import org.unibl.etf.cinema.data.dto.AdresaDTO;
import org.unibl.etf.cinema.data.dto.Nalog;
import org.unibl.etf.cinema.data.dto.Rola;
import org.unibl.etf.cinema.data.dto.Zaposleni;
import org.unibl.etf.cinema.util.CryptoUtil;
import org.unibl.etf.cinema.util.EmailValidator;
import org.unibl.etf.cinema.util.UIUtils;
import org.unibl.etf.cinema.util.Utils;
import javax.swing.JComboBox;

public class ZaposleniDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private Frame frame;
	private Zaposleni zaposleni;

	private ZaposleniDAO zaposleniDAO = DAOFactory.getDAOFactory().getZaposleniDAO();
	private NalogDAO nalogDAO = DAOFactory.getDAOFactory().getNalogDAO();
	private AdresaDAO adresaDAO = DAOFactory.getDAOFactory().getAdresaDAO();
	private RolaDAO rolaDAO = DAOFactory.getDAOFactory().getRolaDAO();

	private JPanel buttonPane;
	private JTextField tfIme;
	private JTextField tfPrezime;
	private JTextField tfEmail;
	private JTextField tfMjesto;
	private JTextField tfKorisnickoIme;
	private JTextField tfJmb;
	private JTextField tfUlica;
	private JTextField tfBroj;
	private JPasswordField pfLozinka;
	private JPasswordField pfPotvrdaLozinke;
	private JCheckBox chbKredencijali;
	private JTextField tfPlata;

	private List<JTextField> polja = new ArrayList<>();

	private static final int MIN_DUZINA_LOZINKE = 8;
	private static final int MIN_DUZINA_K_IMENA = 6;
	private JButton btnSacuvaj;
	private JButton btnOtkazi;
	private JLabel lblRola;
	private JComboBox cbRola;

	/**
	 * Create the dialog.
	 */
	public ZaposleniDialog(Frame frame, Zaposleni zaposleni) {
		this.frame = frame;
		this.zaposleni = zaposleni;

		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 481, 728);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(0, 10, 0, 0));

		JLabel lblNewLabel = new JLabel("JMB");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));

		tfJmb = new JTextField();
		tfJmb.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				UIUtils.ograniciDuzinuUnosa(e, tfJmb, 13);
				UIUtils.onemoguciUnosNonDigitKaraktera(e, tfJmb, false);
			}
		});
		tfJmb.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 0)));
		tfJmb.setFont(new Font("Arial", Font.PLAIN, 14));
		tfJmb.setColumns(10);
		polja.add(tfJmb);

		tfIme = new JTextField();
		tfIme.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				UIUtils.ograniciDuzinuUnosa(e, tfIme);
			}
		});
		tfIme.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 0)));
		tfIme.setFont(new Font("Arial", Font.PLAIN, 14));
		tfIme.setColumns(10);
		polja.add(tfIme);

		JLabel lblIme = new JLabel("Ime");
		lblIme.setFont(new Font("Arial", Font.PLAIN, 14));

		tfPrezime = new JTextField();
		tfPrezime.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				UIUtils.ograniciDuzinuUnosa(e, tfPrezime);
			}
		});
		tfPrezime.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 0)));
		tfPrezime.setFont(new Font("Arial", Font.PLAIN, 14));
		tfPrezime.setColumns(10);
		polja.add(tfPrezime);

		JLabel lblPrezime = new JLabel("Prezime");
		lblPrezime.setFont(new Font("Arial", Font.PLAIN, 14));

		tfEmail = new JTextField();
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				UIUtils.ograniciDuzinuUnosa(e, tfEmail);
			}
		});
		tfEmail.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 0)));
		tfEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		tfEmail.setColumns(10);
		polja.add(tfEmail);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));

		tfMjesto = new JTextField();
		tfMjesto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				UIUtils.ograniciDuzinuUnosa(e, tfMjesto);
			}
		});
		tfMjesto.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 0)));
		tfMjesto.setFont(new Font("Arial", Font.PLAIN, 14));
		tfMjesto.setColumns(10);
		polja.add(tfMjesto);

		JLabel lblMjesto = new JLabel("Mjesto");
		lblMjesto.setFont(new Font("Arial", Font.PLAIN, 14));

		tfKorisnickoIme = new JTextField();
		tfKorisnickoIme.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				UIUtils.ograniciDuzinuUnosa(e, tfKorisnickoIme);
			}
		});
		tfKorisnickoIme.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 0)));
		tfKorisnickoIme.setFont(new Font("Arial", Font.PLAIN, 14));
		tfKorisnickoIme.setColumns(10);
		polja.add(tfKorisnickoIme);

		JLabel lblKorisnikoIme = new JLabel("Korisni\u010Dko ime");
		lblKorisnikoIme.setFont(new Font("Arial", Font.PLAIN, 14));

		chbKredencijali = new JCheckBox("omogu\u0107iti izmjenu lozinke");
		chbKredencijali.setActionCommand("omogu\u0107iti izmjenu lozinke");
		chbKredencijali.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				postaviIzmjenuKredencijala(chbKredencijali.isSelected());
			}
		});
		chbKredencijali.setBackground(Color.WHITE);
		chbKredencijali.setVisible(zaposleni != null);

		tfUlica = new JTextField();
		tfUlica.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				UIUtils.ograniciDuzinuUnosa(e, tfUlica);
			}
		});
		tfUlica.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 0)));
		tfUlica.setFont(new Font("Arial", Font.PLAIN, 14));
		tfUlica.setColumns(10);
		polja.add(tfUlica);

		JLabel lblUlica = new JLabel("Ulica");
		lblUlica.setFont(new Font("Arial", Font.PLAIN, 14));

		tfBroj = new JTextField();
		tfBroj.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				UIUtils.onemoguciUnosNonDigitKaraktera(e, tfBroj, true);
			}
		});
		tfBroj.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 0)));
		tfBroj.setFont(new Font("Arial", Font.PLAIN, 14));
		tfBroj.setColumns(10);
		polja.add(tfBroj);

		JLabel lblBroj = new JLabel("Broj");
		lblBroj.setFont(new Font("Arial", Font.PLAIN, 14));

		JLabel lblLozinka = new JLabel("Lozinka");
		lblLozinka.setFont(new Font("Arial", Font.PLAIN, 14));

		pfLozinka = new JPasswordField();
		pfLozinka.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 0)));
		pfLozinka.setFont(new Font("Arial", Font.PLAIN, 14));
		pfLozinka.setColumns(10);

		JLabel lblPotvrdaLozinke = new JLabel("Potvrda lozinke");
		lblPotvrdaLozinke.setFont(new Font("Arial", Font.PLAIN, 14));

		pfPotvrdaLozinke = new JPasswordField();
		pfPotvrdaLozinke.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 0)));
		pfPotvrdaLozinke.setFont(new Font("Arial", Font.PLAIN, 14));
		pfPotvrdaLozinke.setColumns(10);

		JLabel lblBrojTelefona = new JLabel("Plata (KM)");
		lblBrojTelefona.setFont(new Font("Arial", Font.PLAIN, 14));

		tfPlata = new JTextField();
		tfPlata.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				UIUtils.ograniciDuzinuUnosa(e, tfPlata);
				UIUtils.onemoguciUnosNonDigitKarakteraOsimTacke(e, tfPlata);
			}
		});
		tfPlata.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 5, 0, 0)));
		tfPlata.setFont(new Font("Arial", Font.PLAIN, 14));
		tfPlata.setColumns(10);
		polja.add(tfPlata);
		
		lblRola = new JLabel("Rola");
		lblRola.setFont(new Font("Arial", Font.PLAIN, 14));
		
		cbRola = new JComboBox(new DefaultComboBoxModel<>(rolaDAO.sveRole().toArray()));
		cbRola.setFont(new Font("Arial", Font.PLAIN, 14));

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblIme, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPrezime, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBrojTelefona, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMjesto, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUlica, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBroj, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblKorisnikoIme, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRola, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(cbRola, 0, 226, Short.MAX_VALUE)
								.addComponent(tfBroj, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
								.addComponent(tfUlica, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
								.addComponent(tfMjesto)
								.addComponent(tfPlata, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
								.addComponent(tfPrezime, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
								.addComponent(tfJmb, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
								.addComponent(tfIme, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
								.addComponent(tfEmail, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
								.addComponent(tfKorisnickoIme)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblLozinka, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPotvrdaLozinke, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(pfPotvrdaLozinke)
								.addComponent(pfLozinka, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(chbKredencijali, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(84, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(tfJmb, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfIme, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIme, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfPrezime, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPrezime, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfEmail, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfPlata, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBrojTelefona, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMjesto, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfMjesto, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUlica, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfUlica, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBroj, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfBroj, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKorisnikoIme, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfKorisnickoIme, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRola, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbRola, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
					.addComponent(chbKredencijali)
					.addGap(26)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(6)
							.addComponent(lblLozinka, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addComponent(pfLozinka, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(pfPotvrdaLozinke, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPotvrdaLozinke, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				btnOtkazi = new JButton("Otka\u017Ei");
				btnOtkazi.setMnemonic(KeyEvent.VK_ESCAPE);
				btnOtkazi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnOtkazi.setFont(new Font("Arial", Font.PLAIN, 14));
				btnOtkazi.setBackground(UIUtils.BUTTON_COLOR);
				btnOtkazi.setForeground(Color.WHITE);
				btnOtkazi.setActionCommand("Cancel");
				buttonPane.add(btnOtkazi);
			}
			{
				btnSacuvaj = new JButton("Sa\u010Duvaj");
				btnSacuvaj.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (provjeriPodatkeOZaposlenom()) {
							sacuvajZaposlenog();
						} else {
							getToolkit().beep();
						}
					}
				});
				btnSacuvaj.setMnemonic(KeyEvent.VK_ENTER);
				btnSacuvaj.setBackground(UIUtils.BUTTON_COLOR);
				btnSacuvaj.setForeground(Color.WHITE);
				btnSacuvaj.setFont(new Font("Arial", Font.PLAIN, 14));
				btnSacuvaj.setActionCommand("OK");
				buttonPane.add(btnSacuvaj);
				getRootPane().setDefaultButton(btnSacuvaj);
			}
		}

		JLabel lblHeader = new JLabel("Podaci o zaposlenom");
		lblHeader.setVerticalAlignment(SwingConstants.BOTTOM);
		lblHeader.setForeground(Color.BLACK);
		lblHeader.setBackground(new Color(65, 34, 72));
		lblHeader.setBorder(new EmptyBorder(0, 20, 0, 0));
		lblHeader.setFont(new Font("Arial", Font.BOLD, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblHeader, GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 442, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 465, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblHeader, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 568, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

		this.popuniPodatke();
		postaviIzmjenuKredencijala(zaposleni == null);
	}

	private void popuniPodatke() {
		if (zaposleni != null) {
			tfJmb.setText(zaposleni.getJmb());
			tfIme.setText(zaposleni.getIme());
			tfPrezime.setText(zaposleni.getPrezime());
			tfEmail.setText(zaposleni.getEmail());
			tfPlata.setText(Utils.formatDecimalNumber(zaposleni.getPlata()));
			tfMjesto.setText(zaposleni.getAdresa().getMjesto());
			tfUlica.setText(zaposleni.getAdresa().getUlica());
			tfBroj.setText(zaposleni.getAdresa().getBroj() + "");
			cbRola.setSelectedItem(zaposleni.getNalog().getRola());
			tfKorisnickoIme.setText(zaposleni.getNalog().getKorisnickoIme());
		}
	}

	private void postaviIzmjenuKredencijala(boolean izmjenaOmogucena) {
		pfLozinka.setEnabled(izmjenaOmogucena);
		pfPotvrdaLozinke.setEnabled(izmjenaOmogucena);
	}

	private boolean provjeriPodatkeOZaposlenom() {
		List<JTextField> nevalidnaPolja = UIUtils.praznaPolja(polja);

		String jmb = tfJmb.getText().trim();
		if (jmb.length() != 13 || !Utils.isIntegerNumber(jmb)) {
			if (!nevalidnaPolja.contains(tfJmb)) {
				nevalidnaPolja.add(tfJmb);
			}
		}

		EmailValidator validator = new EmailValidator();
		if (!validator.validate(tfEmail.getText().trim())) {
			if (!nevalidnaPolja.contains(tfEmail)) {
				nevalidnaPolja.add(tfEmail);
			}
		}

		if (!Utils.isDecimalNumber(tfPlata.getText().trim())) {
			if (!nevalidnaPolja.contains(tfPlata)) {
				nevalidnaPolja.add(tfPlata);
			}
		}

		if (!Utils.isIntegerNumber(tfBroj.getText().trim())) {
			if (!nevalidnaPolja.contains(tfBroj)) {
				nevalidnaPolja.add(tfBroj);
			}
		}

		if (cbRola.getSelectedIndex() == -1) {
			cbRola.setBackground(UIUtils.ERROR_COLOR);
		} else {
			cbRola.setBackground(UIUtils.COMBO_BOX_COLOR);
		}
		
		String korisnickoIme = tfKorisnickoIme.getText().trim();
		if (korisnickoIme.length() < MIN_DUZINA_K_IMENA ||
				(zaposleni == null || !zaposleni.getNalog().getKorisnickoIme().equals(korisnickoIme)) && nalogDAO.postoji(korisnickoIme)) {
			if (!nevalidnaPolja.contains(tfKorisnickoIme)) {
				nevalidnaPolja.add(tfKorisnickoIme);
			}
		}

		if (zaposleni == null || chbKredencijali.isSelected()) {
			String lozinka1 = new String(pfLozinka.getPassword()).trim();
			String lozinka2 = new String(pfPotvrdaLozinke.getPassword()).trim();

			if (lozinka1.length() < MIN_DUZINA_LOZINKE || !lozinka1.equals(lozinka2)) {
				UIUtils.dodajCrvenuIvicu(pfLozinka);
				UIUtils.dodajCrvenuIvicu(pfPotvrdaLozinke);
			} else {
				UIUtils.dodajPodrazumijevanuIvicu(pfLozinka);
				UIUtils.dodajPodrazumijevanuIvicu(pfPotvrdaLozinke);
			}
		}

		UIUtils.postaviIvicePolja(nevalidnaPolja, polja);
		return nevalidnaPolja.isEmpty();
	}

	private void sacuvajZaposlenog() {
		try {
			AdresaDTO adresa = new AdresaDTO(tfMjesto.getText().trim(), tfUlica.getText().trim(),
					Integer.parseInt(tfBroj.getText().trim()));
			Rola rola = rolaDAO.rola("Radnik");
			Nalog nalog = new Nalog(tfKorisnickoIme.getText(), rola);
			Zaposleni noviZaposleni = new Zaposleni(tfJmb.getText().trim(), tfIme.getText().trim(),
					tfPrezime.getText().trim(), Double.parseDouble(tfPlata.getText().trim()), tfEmail.getText().trim(),
					adresa, nalog);

			if (zaposleni == null) {
				if (!adresaDAO.dodajAdresu(adresa)) {
					throw new Exception();
				}
				if (!nalogDAO.dodajNalog(nalog, CryptoUtil.hash(new String(pfLozinka.getPassword())))) {
					throw new Exception();
				}
				if (!zaposleniDAO.dodajZaposlenog(noviZaposleni)) {
					throw new Exception();
				}
			} else {
				adresa.setAdresaID(zaposleni.getAdresa().getAdresaID());
				nalog.setNalogID(zaposleni.getNalog().getNalogID());
				noviZaposleni.setZaposleniID(zaposleni.getZaposleniID());

				if (!adresaDAO.azurirajAdresu(adresa)) {
					throw new Exception();
				}
				if (!nalogDAO.azurirajNalog(nalog,
						chbKredencijali.isSelected() ? CryptoUtil.hash(new String(pfLozinka.getPassword())) : null)) {
					throw new Exception();
				}
				if (!zaposleniDAO.azurirajZaposlenog(noviZaposleni)) {
					throw new Exception();
				}
			}

			JOptionPane.showMessageDialog(this, "Podaci o korisniku su uspješno saèuvani.", "Uspjeh",
					JOptionPane.INFORMATION_MESSAGE);
			((AdminForma) frame).azurirajTabeluKorisnika();
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Podaci o korisniku nisu saèuvani.", "Greška",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
