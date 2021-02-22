package org.unibl.etf.cinema.view.forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager.LookAndFeelInfo;

import org.unibl.etf.cinema.data.dao.DAOFactory;
import org.unibl.etf.cinema.data.dao.NalogDAO;
import org.unibl.etf.cinema.data.dto.Nalog;
import org.unibl.etf.cinema.util.CryptoUtil;
import org.unibl.etf.cinema.util.UIUtils;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginForma extends JFrame {

	private JPanel contentPane;
	private JTextField tfKorisnickoIme;
	private JPasswordField pfLozinka;
	private JLabel lblLoginGreska;
	private JButton btnLogin;

	private NalogDAO nalogDAO = DAOFactory.getDAOFactory().getNalogDAO();

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 321);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 101, 10, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 56, 10, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 3;
		gbc_panel.gridwidth = 4;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);

		tfKorisnickoIme = new JTextField();
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
		pfLozinka.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER && provjeriLoginPolja()) {
					prijava();
				}
			}
		});

		JLabel lblKorisnickoIme = new JLabel("Korisni\u010Dko ime");
		lblKorisnickoIme.setFont(new Font("Arial", Font.PLAIN, 16));

		JLabel lblLozinka = new JLabel("Lozinka");
		lblLozinka.setFont(new Font("Arial", Font.PLAIN, 16));

		lblLoginGreska = new JLabel("Greska");
		lblLoginGreska.setVisible(false);

		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (provjeriLoginPolja()) {
					prijava();
				}
			}
		});

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addGap(48)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(lblKorisnickoIme)
								.addComponent(lblLozinka, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED).addGroup(
								gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblLoginGreska)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
												.addComponent(tfKorisnickoIme, GroupLayout.DEFAULT_SIZE, 189,
														Short.MAX_VALUE)
												.addComponent(pfLozinka))))
						.addGroup(gl_panel.createSequentialGroup().addGap(154).addComponent(btnLogin)))
				.addContainerGap(81, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap(106, Short.MAX_VALUE).addComponent(btnLogin).addGap(50)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblKorisnickoIme).addComponent(
						tfKorisnickoIme, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLozinka, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(pfLozinka, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblLoginGreska).addGap(14)));
		panel.setLayout(gl_panel);
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
				// TODO
				break;
			default:
				// TODO
			}

			final JFrame glavnaForma = forma;
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
