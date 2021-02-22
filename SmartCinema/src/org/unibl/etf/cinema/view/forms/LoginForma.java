package org.unibl.etf.cinema.view.forms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.unibl.etf.cinema.data.dao.DAOFactory;
import org.unibl.etf.cinema.data.dao.NalogDAO;
import org.unibl.etf.cinema.data.dto.Nalog;
import org.unibl.etf.cinema.util.UIUtils;

public class LoginForma extends JFrame {

	private JPanel contentPane;
	private JTextField tfKorisnickoIme;
	private JPasswordField pfLozinka;
	private JLabel lblLoginGreska;
	private JButton btnLogin;

	private NalogDAO nalogDAO = DAOFactory.getDAOFactory().getNalogDAO();
	private JLabel lblNewLabel;

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
					LoginForma frame = new LoginForma();
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
	public LoginForma() {
		setTitle("SmartCinema - Prijava");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 456);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 101, 10, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 56, 10, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JPanel pnlLogin = new JPanel();
		pnlLogin.setBackground(new Color(255, 255, 255));
		pnlLogin.setLayout(null);
		GridBagConstraints gbc_pnlLogin = new GridBagConstraints();
		gbc_pnlLogin.gridheight = 3;
		gbc_pnlLogin.gridwidth = 4;
		gbc_pnlLogin.fill = GridBagConstraints.BOTH;
		gbc_pnlLogin.gridx = 0;
		gbc_pnlLogin.gridy = 0;
		contentPane.add(pnlLogin, gbc_pnlLogin);

		tfKorisnickoIme = new JTextField();
		tfKorisnickoIme.setFont(new Font("Arial", Font.PLAIN, 14));
		tfKorisnickoIme.setBounds(60, 188, 239, 33);
		tfKorisnickoIme.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER && provjeriLoginPolja()) {
					prijava();
				}
			}
		});
		tfKorisnickoIme.setColumns(10);

		pfLozinka = new JPasswordField();
		pfLozinka.setFont(new Font("Arial", Font.PLAIN, 14));
		pfLozinka.setBounds(60, 253, 239, 33);
		pfLozinka.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER && provjeriLoginPolja()) {
					prijava();
				}
			}
		});

		JLabel lblKorisnickoIme = new JLabel("Korisni\u010Dko ime");
		lblKorisnickoIme.setBounds(62, 168, 104, 19);
		lblKorisnickoIme.setFont(new Font("Arial", Font.PLAIN, 14));

		JLabel lblLozinka = new JLabel("Lozinka");
		lblLozinka.setBounds(61, 233, 104, 19);
		lblLozinka.setFont(new Font("Arial", Font.PLAIN, 14));

		lblLoginGreska = new JLabel("Pogre\u0161no korisni\u010Dko ime ili lozinka!");
		lblLoginGreska.setBounds(70, 353, 230, 19);
		lblLoginGreska.setForeground(Color.RED);
		lblLoginGreska.setFont(new Font("Arial", Font.PLAIN, 14));
		lblLoginGreska.setVisible(false);

		btnLogin = new JButton("Prijava");
		btnLogin.setFont(new Font("Arial", Font.PLAIN, 16));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBounds(60, 298, 239, 43);
		btnLogin.setBackground(new Color(220, 20, 60));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (provjeriLoginPolja()) {
					prijava();
				}
			}
		});
		pnlLogin.add(btnLogin);
		pnlLogin.add(lblLoginGreska);
		pnlLogin.add(lblLozinka);
		pnlLogin.add(pfLozinka);
		pnlLogin.add(lblKorisnickoIme);
		pnlLogin.add(tfKorisnickoIme);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon(LoginForma.class.getResource("/org/unibl/etf/cinema/view/icons/login_logo.png")));
		lblNewLabel.setBounds(31, 16, 282, 140);
		pnlLogin.add(lblNewLabel);
	}

	private boolean provjeriLoginPolja() {

		boolean greska = false;
		if (tfKorisnickoIme.getText().isBlank()) {
			UIUtils.dodajCrvenuIvicu(tfKorisnickoIme);
			greska = true;
		} else
			UIUtils.dodajPodrazumijevanuIvicu(tfKorisnickoIme);

		if (pfLozinka.getPassword().length == 0) {
			UIUtils.dodajCrvenuIvicu(pfLozinka);
			greska = true;
		} else {
			UIUtils.dodajPodrazumijevanuIvicu(pfLozinka);
		}

		return !greska;
	}

	private void prijava() {
		Nalog nalog = nalogDAO.prijava(tfKorisnickoIme.getText().trim(), new String(pfLozinka.getPassword()).trim());
		lblLoginGreska.setVisible(nalog == null);

		if (nalog != null) {
			JFrame forma = null;
			switch (nalog.getRola().getNaziv()) {
			case "Administrator1":
				forma = new AdminForma(nalog.getNalogID());
				break;
			case "Administrator2":
				forma = new FilmoviGlavnaForma(nalog.getNalogID());
				break;
			default:
				forma = new DodatnaPonudaSearchFrame();
			}

			final JFrame glavnaForma = forma;
			forma.setExtendedState(JFrame.MAXIMIZED_BOTH);
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					try {
						glavnaForma.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			dispose();
		}

	}
}
