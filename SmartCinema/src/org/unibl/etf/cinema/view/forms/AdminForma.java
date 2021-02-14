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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import java.awt.Rectangle;
import java.text.NumberFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.ComponentOrientation;

public class AdminForma extends JFrame {

	private JPanel contentPane;
	private JTextField tfNaziv;
	private JTextField tfEmail;
	private JTextField tfBrojTelefona;
	private JTextField tfMjesto;
	private JTextField tfUlica;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1112, 679);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {250, 0};
		gbl_contentPane.rowHeights = new int[]{50, 50, 50, 0};
		gbl_contentPane.columnWeights = new double[]{0, 1.0};
		gbl_contentPane.rowWeights = new double[]{0,0,0,1};
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
		pnlZaglavlje.setBackground(new Color(33,20,47));
		GridBagConstraints gbc_pnlZaglavlje = new GridBagConstraints();
		gbc_pnlZaglavlje.insets = new Insets(0, 0, 0, 0);
		gbc_pnlZaglavlje.fill = GridBagConstraints.BOTH;
		gbc_pnlZaglavlje.gridx = 1;
		gbc_pnlZaglavlje.gridy = 0;
		contentPane.add(pnlZaglavlje, gbc_pnlZaglavlje);
		pnlZaglavlje.setLayout(new BoxLayout(pnlZaglavlje, BoxLayout.X_AXIS));
		
		JLabel lblZaglavlje = new JLabel("Po\u010Detna strana");
		lblZaglavlje.setIcon(new ImageIcon(AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/icon_pocetna_strana.png")));
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
		
		JPanel pnlKorisnici = new JPanel();
		pnlKorisnici.setBackground(new Color(240, 240, 240));
		pnlSadrzaj.add(pnlKorisnici, "name_885980018281400");
		
		JPanel pnlPocetnaStrana = new JPanel();
		pnlPocetnaStrana.setBackground(new Color(240, 240, 240));
		pnlSadrzaj.add(pnlPocetnaStrana, "name_872029912525600");
		
		JPanel pnlKino = new JPanel();
		pnlKino.setBackground(new Color(240, 240, 240));
		pnlSadrzaj.add(pnlKino, "name_872344083442100");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GroupLayout gl_pnlKino = new GroupLayout(pnlKino);
		gl_pnlKino.setHorizontalGroup(
			gl_pnlKino.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
		);
		gl_pnlKino.setVerticalGroup(
			gl_pnlKino.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlKino.createSequentialGroup()
					.addGap(100)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 454, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(36, Short.MAX_VALUE))
		);
		
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
		lblSlika.setIcon(new ImageIcon(AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/kino_forma.png")));
		
		JButton btnNewButton = new JButton("Sa\u010Duvaj");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(220, 20, 60));
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));
		
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
		
		JFormattedTextField ftfBroj = new JFormattedTextField();
		ftfBroj.setFont(new Font("Arial", Font.PLAIN, 16));
		ftfBroj.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblNaziv)
										.addGap(73))
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
										.addGap(34))
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblBrojTelefona, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
										.addGap(3))
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblMjesto, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
										.addGap(47))
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblUlica, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
										.addGap(73))
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblBroj, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
										.addGap(73)))
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(tfUlica, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
									.addComponent(tfMjesto, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
									.addComponent(tfBrojTelefona, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
									.addComponent(tfEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
									.addComponent(tfNaziv, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
									.addComponent(ftfBroj, Alignment.LEADING)))
							.addComponent(lblNaslov)))
					.addPreferredGap(ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
					.addComponent(lblSlika))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(25)
							.addComponent(lblNaslov)
							.addGap(29)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNaziv)
								.addComponent(tfNaziv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfEmail, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBrojTelefona, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfBrojTelefona, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
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
								.addComponent(ftfBroj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblSlika, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)))
					.addGap(32)
					.addComponent(btnNewButton)
					.addContainerGap(41, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		pnlKino.setLayout(gl_pnlKino);
		
		JPanel pnlMeni = new JPanel();
		pnlMeni.setBorder(new EmptyBorder(20, 0, 0, 0));
		pnlMeni.setBackground(new Color(33,20,47));
		GridBagConstraints gbc_pnlMeni = new GridBagConstraints();
		gbc_pnlMeni.insets = new Insets(0, 0, 0, 0);
		gbc_pnlMeni.fill = GridBagConstraints.BOTH;
		gbc_pnlMeni.gridx = 0;
		gbc_pnlMeni.gridy = 3;
		contentPane.add(pnlMeni, gbc_pnlMeni);
		GridBagLayout gbl_pnlMeni = new GridBagLayout();
		gbl_pnlMeni.columnWidths = new int[] {0};
		gbl_pnlMeni.rowHeights = new int[] {50, 50,50, 0};
		gbl_pnlMeni.columnWeights = new double[]{1.0};
		gbl_pnlMeni.rowWeights = new double[]{0, 0,0, 1};
		pnlMeni.setLayout(gbl_pnlMeni);
		
		JLabel lblPocetnaStrana = new JLabel("Po\u010Detna strana");
		lblPocetnaStrana.setBackground(new Color(65,34,72));
		lblPocetnaStrana.setIconTextGap(10);
		lblPocetnaStrana.setBorder(new EmptyBorder(0, 15, 0, 0));
		lblPocetnaStrana.setIcon(new ImageIcon(AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/icon_pocetna_strana.png")));
		lblPocetnaStrana.setForeground(new Color(255, 255, 255));
		lblPocetnaStrana.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPocetnaStrana.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblPocetnaStrana.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblPocetnaStrana = new GridBagConstraints();
		gbc_lblPocetnaStrana.insets = new Insets(0, 0, 0, 0);
		gbc_lblPocetnaStrana.fill = GridBagConstraints.BOTH;
		gbc_lblPocetnaStrana.gridx = 0;
		gbc_lblPocetnaStrana.gridy = 0;
		pnlMeni.add(lblPocetnaStrana, gbc_lblPocetnaStrana);
		
		JLabel lblKorisnici = new JLabel("Korisnici");
		lblKorisnici.setBackground(new Color(99,62,109));
		lblKorisnici.setIconTextGap(10);
		lblKorisnici.setIcon(new ImageIcon(AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/icon_korisnici.png")));
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
		lblKino.setBackground(new Color(127,83,135));
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
	}
}
