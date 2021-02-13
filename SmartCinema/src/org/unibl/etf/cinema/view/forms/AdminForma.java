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
	private JTextField tfGrad;
	private JTextField textField;

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
		pnlZaglavlje.setBackground(new Color(37,36,51));
		GridBagConstraints gbc_pnlZaglavlje = new GridBagConstraints();
		gbc_pnlZaglavlje.insets = new Insets(0, 0, 0, 0);
		gbc_pnlZaglavlje.fill = GridBagConstraints.BOTH;
		gbc_pnlZaglavlje.gridx = 1;
		gbc_pnlZaglavlje.gridy = 0;
		contentPane.add(pnlZaglavlje, gbc_pnlZaglavlje);
		pnlZaglavlje.setLayout(new BoxLayout(pnlZaglavlje, BoxLayout.X_AXIS));
		
		JLabel lblZaglavlje = new JLabel("Po\u010Detna strana");
		lblZaglavlje.setIcon(new ImageIcon(AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/pocetna_strana.png")));
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
		
		JPanel pnlFilmovi = new JPanel();
		pnlSadrzaj.add(pnlFilmovi, "name_885980018281400");
		
		JPanel pnlPocetnaStrana = new JPanel();
		pnlPocetnaStrana.setBackground(new Color(222,212,221));
		pnlSadrzaj.add(pnlPocetnaStrana, "name_872029912525600");
		
		JPanel pnlKino = new JPanel();
		pnlSadrzaj.add(pnlKino, "name_872344083442100");
		pnlKino.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setBounds(0, 100, 801, 403);
		panel.setBorder(null);
		panel.setBackground(new Color(255, 255, 255));
		pnlKino.add(panel);
		
		JLabel lblPodaciOKinu = new JLabel("Podaci o kinu");
		lblPodaciOKinu.setFont(new Font("Arial", Font.BOLD, 18));
		
		JLabel lblNaziv = new JLabel("Naziv");
		lblNaziv.setFont(new Font("Arial", Font.PLAIN, 16));
		
		tfNaziv = new JTextField();
		tfNaziv.setFont(new Font("Arial", Font.PLAIN, 16));
		tfNaziv.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		
		tfEmail = new JTextField();
		tfEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		tfEmail.setColumns(10);
		
		JLabel lblBrojTelefona = new JLabel("Broj telefona");
		lblBrojTelefona.setFont(new Font("Arial", Font.PLAIN, 16));
		
		tfBrojTelefona = new JTextField();
		tfBrojTelefona.setFont(new Font("Arial", Font.PLAIN, 16));
		tfBrojTelefona.setColumns(10);
		
		JLabel lblGrad = new JLabel("Grad");
		lblGrad.setFont(new Font("Arial", Font.PLAIN, 16));
		
		tfGrad = new JTextField();
		tfGrad.setFont(new Font("Arial", Font.PLAIN, 16));
		tfGrad.setColumns(10);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 16));
		textField.setColumns(10);
		
		JLabel lblUlica = new JLabel("Ulica");
		lblUlica.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JLabel lblBroj = new JLabel("Broj");
		lblBroj.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JFormattedTextField ftfBroj = new JFormattedTextField(NumberFormat.getNumberInstance());
		ftfBroj.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JButton btnSacuvaj = new JButton("Sa\u010Duvaj");
		btnSacuvaj.setMnemonic(KeyEvent.VK_ENTER);
		btnSacuvaj.setBackground(new Color(255,112,111));
		btnSacuvaj.setForeground(new Color(255, 255, 255));
		btnSacuvaj.setFont(new Font("Arial", Font.PLAIN, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(55)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
								.addGap(51)
								.addComponent(tfEmail, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNaziv)
								.addGap(65)
								.addComponent(tfNaziv, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblGrad, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
									.addGap(35)
									.addComponent(tfGrad, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblBrojTelefona, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfBrojTelefona, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblUlica, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblBroj, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
									.addGap(35)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(ftfBroj)
										.addComponent(textField, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
										.addComponent(btnSacuvaj, Alignment.TRAILING)))))
						.addComponent(lblPodaciOKinu))
					.addGap(396))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addComponent(lblPodaciOKinu)
					.addGap(43)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNaziv)
						.addComponent(tfNaziv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(tfEmail, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(tfBrojTelefona, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblBrojTelefona, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(tfGrad, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblGrad, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblUlica, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(21)
							.addComponent(lblBroj, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(ftfBroj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addComponent(btnSacuvaj)
					.addGap(23))
		);
		panel.setLayout(gl_panel);
		
		JPanel pnlMeni = new JPanel();
		pnlMeni.setBorder(new EmptyBorder(20, 0, 0, 0));
		pnlMeni.setBackground(new Color(152,118,161));
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
		lblPocetnaStrana.setOpaque(true);
		lblPocetnaStrana.setIconTextGap(10);
		lblPocetnaStrana.setBorder(new EmptyBorder(0, 15, 0, 0));
		lblPocetnaStrana.setIcon(new ImageIcon(AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/pocetna_strana.png")));
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
		lblKorisnici.setOpaque(true);
		lblKorisnici.setBackground(new Color(99,62,109));
		lblKorisnici.setIconTextGap(10);
		lblKorisnici.setIcon(new ImageIcon(AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/korisnici.png")));
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
		lblKino.setOpaque(true);
		lblKino.setBackground(new Color(127,83,135));
		lblKino.setIcon(new ImageIcon(AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/kino.png")));
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
