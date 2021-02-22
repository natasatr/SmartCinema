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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.unibl.etf.cinema.data.dao.DAOFactory;
import org.unibl.etf.cinema.data.dao.DodatnaPonudaDAO;
import org.unibl.etf.cinema.data.dao.KinoDAO;
import org.unibl.etf.cinema.data.dao.SalaDAO;
import org.unibl.etf.cinema.data.dao.SjedisteDAO;
import org.unibl.etf.cinema.data.dao.mysql.MySQLDodatnaPonudaDAO;
import org.unibl.etf.cinema.data.dao.mysql.MySQLKinoDAO;
import org.unibl.etf.cinema.data.dao.mysql.MySQLSalaDAO;
import org.unibl.etf.cinema.data.dao.mysql.MySQLSjedisteDAO;
import org.unibl.etf.cinema.data.dto.DodatnaPonudaDTO;
import org.unibl.etf.cinema.data.dto.KinoDTO;
import org.unibl.etf.cinema.data.dto.SalaDTO;
import org.unibl.etf.cinema.data.dto.SjedisteDTO;
import org.unibl.etf.cinema.util.UIUtils;
import org.unibl.etf.cinema.view.tables.DodatnaPonudaTableModel;
import org.unibl.etf.cinema.view.tables.SalaTableModel;
import org.unibl.etf.cinema.view.tables.SjedisteTableModel;

public class DodatnaPonudaSearchFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblZaglavlje;
	private int INDEKS_OPCIJE = 0;

	private MySQLKinoDAO kinoSQL;
	private JLabel[] labele = new JLabel[3];
	private JPanel[] paneli = new JPanel[3];
	private List<JTextField> kinoPolja = new ArrayList<>();
	private JTable tblDodatnePonude;
	private DodatnaPonudaSearchFrame frame;

	private JTextField textField;
	private JTextField textField_1;
	private JTextField tfPretraga;
	// private JTextField tfNaziv;
	// private JTextField tfCijena;
	private DodatnaPonudaDTO dp;

	private DodatnaPonudaDAO dodatnaPonudaDAO = DAOFactory.getDAOFactory().getDodatnaPonudaDAO();
	private SalaDAO salaDAO = DAOFactory.getDAOFactory().getSalaDAO();
	private KinoDAO KinoDAO = DAOFactory.getDAOFactory().getKinoDAO();
	private SjedisteDAO sjedisteDAO = DAOFactory.getDAOFactory().getSjedisteDAO();
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JTable table_3;
	private JButton btnBrisi;
	private SalaDTO salaDTO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodatnaPonudaSearchFrame frame = new DodatnaPonudaSearchFrame();
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DodatnaPonudaSearchFrame() {
		try {
			dodatnaPonudaDAO = new MySQLDodatnaPonudaDAO();
			salaDAO = new MySQLSalaDAO();
			KinoDAO = new MySQLKinoDAO();
			sjedisteDAO = new MySQLSjedisteDAO();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "ERROR " + e, "error ", JOptionPane.ERROR_MESSAGE);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1112, 679);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 250, 0 };
		gbl_contentPane.rowHeights = new int[] { 50, 50, 50, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0 };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0 };
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
		pnlLogo.add(lblLogo, BorderLayout.EAST);

		JPanel pnlZaglavlje = new JPanel();
		pnlZaglavlje.setBackground(new Color(33, 20, 47));
		GridBagConstraints gbc_pnlZaglavlje = new GridBagConstraints();
		gbc_pnlZaglavlje.insets = new Insets(0, 0, 0, 0);
		gbc_pnlZaglavlje.fill = GridBagConstraints.BOTH;
		gbc_pnlZaglavlje.gridx = 1;
		gbc_pnlZaglavlje.gridy = 0;
		contentPane.add(pnlZaglavlje, gbc_pnlZaglavlje);
		pnlZaglavlje.setLayout(new BoxLayout(pnlZaglavlje, BoxLayout.X_AXIS));

		lblZaglavlje = new JLabel("");

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

		JPanel pnlDodatnaPonuda = new JPanel();
		paneli[0] = pnlDodatnaPonuda;
		pnlDodatnaPonuda.setBackground(new Color(240, 240, 240));
		pnlSadrzaj.add(pnlDodatnaPonuda, "pnl_dodatna_ponuda");

		JScrollPane scrollPane_1 = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		GroupLayout gl_pnlDodatnaPonuda = new GroupLayout(pnlDodatnaPonuda);
		gl_pnlDodatnaPonuda.setHorizontalGroup(
			gl_pnlDodatnaPonuda.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlDodatnaPonuda.createSequentialGroup()
					.addGroup(gl_pnlDodatnaPonuda.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlDodatnaPonuda.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlDodatnaPonuda.createSequentialGroup()
							.addGap(40)
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)))
					.addGap(40))
		);
		gl_pnlDodatnaPonuda.setVerticalGroup(
			gl_pnlDodatnaPonuda.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlDodatnaPonuda.createSequentialGroup()
					.addGap(100)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
					.addGap(37)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(25))
		);

		JButton btnDodaj = new JButton("");
		btnDodaj.setBackground(UIUtils.BUTTON_COLOR);
		btnDodaj.setIcon(new ImageIcon(
				DodatnaPonudaSearchFrame.class.getResource("/org/unibl/etf/cinema/view/icons/icon_add.png")));
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dodajDP();
				// azurirajTabeluDP();

			}
		});

		btnBrisi = new JButton("");
		btnBrisi.setBackground(UIUtils.BUTTON_COLOR);
		btnBrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obrisiDO();

			}
		});

		btnBrisi.setIcon(new ImageIcon(
				DodatnaPonudaSearchFrame.class.getResource("/org/unibl/etf/cinema/view/icons/icon_delete.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(215, Short.MAX_VALUE)
					.addComponent(btnBrisi, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDodaj, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnBrisi)
						.addComponent(btnDodaj, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);

		table_1 = new JTable();
		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						podesiDugmad();
					}
				});
			}
		});

		scrollPane_1.setViewportView(table_1);

		table_1.setFont(new Font("Arial", Font.PLAIN, 12));
		table_1.setModel(new DodatnaPonudaTableModel(dodatnaPonudaDAO.sveDodatnePonude()));
		table_1.getColumnModel().getColumn(0).setMaxWidth(50);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(250);

		pnlDodatnaPonuda.setLayout(gl_pnlDodatnaPonuda);

		JPanel pnlSale = new JPanel();
		paneli[1] = pnlSale;
		pnlSale.setBackground(new Color(240, 240, 240));
		pnlSadrzaj.add(pnlSale, "pnl_sale");

		JScrollPane scrollPane_2 = new JScrollPane();

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		
		JPanel panel_3 = new JPanel();

		GroupLayout gl_pnlSale = new GroupLayout(pnlSale);
		gl_pnlSale.setHorizontalGroup(
			gl_pnlSale.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlSale.createSequentialGroup()
					.addGroup(gl_pnlSale.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlSale.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlSale.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_pnlSale.createSequentialGroup()
								.addContainerGap()
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_pnlSale.createSequentialGroup()
								.addGap(40)
								.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE))))
					.addGap(40))
		);
		gl_pnlSale.setVerticalGroup(
			gl_pnlSale.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlSale.createSequentialGroup()
					.addGap(25)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
					.addGap(37)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(25))
		);
		
		JButton btnOsvezi = new JButton("");
		btnOsvezi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				
					azurirajTabeluSala();
					azurirajTabeluSjedista();

				} catch (Exception e1) {
					
				}
			}
		});
		btnOsvezi.setBackground(UIUtils.BUTTON_COLOR);
		btnOsvezi.setIcon(new ImageIcon(DodatnaPonudaSearchFrame.class.getResource("/org/unibl/etf/cinema/view/icons/round_refresh_white_18dp.png")));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
					.addContainerGap(49, Short.MAX_VALUE)
					.addComponent(btnOsvezi, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(btnOsvezi, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);

		JButton btnDodajSalu = new JButton("");
		btnDodajSalu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dodajSalu();
				// azurirajTabeluDP();

			}
		});

		btnDodajSalu.setIcon(new ImageIcon(
				DodatnaPonudaSearchFrame.class.getResource("/org/unibl/etf/cinema/view/icons/icon_add.png")));
		btnDodajSalu.setBackground(UIUtils.BUTTON_COLOR);

		JButton btnBrisiSalu = new JButton("");
		btnBrisiSalu.setBackground(UIUtils.BUTTON_COLOR);
		btnBrisiSalu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obrisiSalu();
			}
		});

		btnBrisiSalu.setIcon(new ImageIcon(
				DodatnaPonudaSearchFrame.class.getResource("/org/unibl/etf/cinema/view/icons/icon_delete.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(211, Short.MAX_VALUE)
					.addComponent(btnBrisiSalu, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDodajSalu, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnBrisiSalu)
						.addComponent(btnDodajSalu, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);

		table_2 = new JTable();
		table_2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						podesiDugmad();
					}
				});
			}
		});
		scrollPane_2.setViewportView(table_2);
		table_2.setFont(new Font("Arial", Font.PLAIN, 12));
		table_2.setModel(new SalaTableModel(salaDAO.sveSale()));
		table_2.getColumnModel().getColumn(0).setMaxWidth(50);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(250);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(250);

		pnlSale.setLayout(gl_pnlSale);

		JPanel pnlSjedista = new JPanel();
		paneli[2] = pnlSjedista;
		pnlSjedista.setBackground(new Color(240, 240, 240));
		pnlSadrzaj.add(pnlSjedista, "pnl_sjedista");

		JScrollPane scrollPane_3 = new JScrollPane();

		JButton btnNewButton = new JButton("");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				azurirajTabeluSjedista();
			}
		});
		btnNewButton.setBackground(new Color(220, 20, 60));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton.setIcon(
				new ImageIcon(AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/icon_search.png")));

		
		List<SalaDTO> sale = salaDAO.sveSale();
		comboBox_1 = new JComboBox(sale.toArray(new SalaDTO[] {}));
		comboBox_1.setSelectedIndex(sale.isEmpty() ? -1 : 0);

		JLabel lblOdaberiteSalu = new JLabel("Odaberite salu");
		lblOdaberiteSalu.setFont(new Font("Arial", Font.PLAIN, 14));

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);

		GroupLayout gl_pnlSjedista = new GroupLayout(pnlSjedista);
		gl_pnlSjedista.setHorizontalGroup(
			gl_pnlSjedista.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlSjedista.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_pnlSjedista.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlSjedista.createSequentialGroup()
							.addComponent(lblOdaberiteSalu)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(40))
		);
		gl_pnlSjedista.setVerticalGroup(
			gl_pnlSjedista.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlSjedista.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_pnlSjedista.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblOdaberiteSalu, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
						.addComponent(comboBox_1))
					.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
					.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
					.addGap(58)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(25))
		);

		JButton btnDodajSjediste = new JButton("\r\n");
		btnDodajSjediste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dodajSjediste();
			}
		});

		btnDodajSjediste.setIcon(new ImageIcon(
				DodatnaPonudaSearchFrame.class.getResource("/org/unibl/etf/cinema/view/icons/icon_add.png")));
		btnDodajSjediste.setBackground(UIUtils.BUTTON_COLOR);

		JButton btnObrisiSjediste = new JButton("");
		btnObrisiSjediste.setBackground(UIUtils.BUTTON_COLOR);
		btnObrisiSjediste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obrisiSjediste();
			}
		});

		btnObrisiSjediste.setIcon(new ImageIcon(
				DodatnaPonudaSearchFrame.class.getResource("/org/unibl/etf/cinema/view/icons/icon_delete.png")));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap(221, Short.MAX_VALUE)
					.addComponent(btnObrisiSjediste, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDodajSjediste, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(20, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnObrisiSjediste)
						.addComponent(btnDodajSjediste, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(19))
		);
		panel_2.setLayout(gl_panel_2);

		table_3 = new JTable();
		table_3.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						podesiDugmad();
					}
				});
			}
		});
		scrollPane_3.setViewportView(table_3);
		table_3.setFont(new Font("Arial", Font.PLAIN, 12));
		table_3.setModel(new SjedisteTableModel(sjedisteDAO.svaSjedista()));
		table_3.getColumnModel().getColumn(0).setMaxWidth(50);
		table_3.getColumnModel().getColumn(1).setPreferredWidth(240);
		table_3.getColumnModel().getColumn(2).setPreferredWidth(240);

		pnlSjedista.setLayout(gl_pnlSjedista);

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

		JLabel lblDodatnePonude = new JLabel("Dodatne ponude");
		labele[0] = lblDodatnePonude;
		lblDodatnePonude.setBackground(new Color(99, 62, 109));
		lblDodatnePonude.setIconTextGap(10);
		lblDodatnePonude.setBorder(new EmptyBorder(0, 15, 0, 0));
		lblDodatnePonude.setIcon(new ImageIcon(
				AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/round_fastfood_white_18dp.png")));
		lblDodatnePonude.setForeground(new Color(255, 255, 255));
		lblDodatnePonude.setFont(new Font("Arial", Font.BOLD, 16));
		lblDodatnePonude.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblDodatnePonude.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblPocetnaStrana = new GridBagConstraints();
		gbc_lblPocetnaStrana.insets = new Insets(0, 0, 0, 0);
		gbc_lblPocetnaStrana.fill = GridBagConstraints.BOTH;
		gbc_lblPocetnaStrana.gridx = 0;
		gbc_lblPocetnaStrana.gridy = 0;
		pnlMeni.add(lblDodatnePonude, gbc_lblPocetnaStrana);

		JLabel lblSale = new JLabel("Sale");
		labele[1] = lblSale;
		lblSale.setBackground(new Color(99, 62, 109));
		lblSale.setIconTextGap(10);
		lblSale.setIcon(new ImageIcon(
				AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/round_meeting_room_white_18dp.png")));
		lblSale.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblSale.setHorizontalAlignment(SwingConstants.LEFT);
		lblSale.setForeground(Color.WHITE);
		lblSale.setFont(new Font("Arial", Font.BOLD, 16));
		lblSale.setBorder(new EmptyBorder(0, 15, 0, 0));
		GridBagConstraints gbc_lblSale = new GridBagConstraints();
		gbc_lblSale.fill = GridBagConstraints.BOTH;
		gbc_lblSale.insets = new Insets(0, 0, 0, 0);
		gbc_lblSale.gridx = 0;
		gbc_lblSale.gridy = 1;
		pnlMeni.add(lblSale, gbc_lblSale);

		JLabel lblSjedista = new JLabel("Sjedi\u0161ta");
		labele[2] = lblSjedista;
		lblSjedista.setBackground(new Color(99, 62, 109));
		lblSjedista.setIconTextGap(10);
		lblSjedista.setIcon(new ImageIcon(
				AdminForma.class.getResource("/org/unibl/etf/cinema/view/icons/round_event_seat_white_18dp.png")));
		lblSjedista.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblSjedista.setHorizontalAlignment(SwingConstants.LEFT);
		lblSjedista.setForeground(Color.WHITE);
		lblSjedista.setFont(new Font("Arial", Font.BOLD, 16));
		lblSjedista.setBorder(new EmptyBorder(0, 15, 0, 0));
		GridBagConstraints gbc_lblSjedista = new GridBagConstraints();
		gbc_lblSjedista.fill = GridBagConstraints.BOTH;
		gbc_lblSjedista.insets = new Insets(0, 0, 0, 0);
		gbc_lblSjedista.gridx = 0;
		gbc_lblSjedista.gridy = 2;
		pnlMeni.add(lblSjedista, gbc_lblSjedista);

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

			podesiDugmad();
		}

	}

	/*
	 * private void setEditable(boolean editable) { tfNaziv.setEditable(editable);
	 * tfCijena.setEditable(editable); }
	 * 
	 * private void popuniPodatkeODP() { tfNaziv.setText(dp.getNaziv());
	 * tfCijena.setText(Double.toString(dp.getCijena()));
	 * 
	 * setEditable(false); }
	 */
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
				labele[i].setBackground(UIUtils.DEFAULT_MENU_BG_COLOR);
				labele[i].setFont(UIUtils.DEFAULT_MENU_FONT);
			}
		}
	}

	public void azurirajTabeluDP() {

		DodatnaPonudaTableModel model = (DodatnaPonudaTableModel) table_1.getModel();
		model.setDodatnePonude(dodatnaPonudaDAO.sveDodatnePonude());
		model.fireTableDataChanged();
	}

	public void azurirajTabeluSala() {
		// List<SalaDTO> sale=new ArrayList<>();
		// KinoDTO dp=(KinoDTO) comboBox.getSelectedItem();
		// String nazivKina=dp.getNaziv();
		SalaTableModel model = (SalaTableModel) table_2.getModel();
		model.setSale(salaDAO.sveSale());
		model.fireTableDataChanged();
	}

	public void azurirajTabeluSjedista() {
		azurirajTabeluSala();
		SalaDTO dp = (SalaDTO) comboBox_1.getSelectedItem();
		int salaID = dp.getSalaID();
		SjedisteTableModel model = (SjedisteTableModel) table_3.getModel();
		model.setSjedista(sjedisteDAO.svaSjedistaUSaliUKinu(salaID));
		model.fireTableDataChanged();
	}

	private void dodajKorisnika() {
		new ZaposleniDialog(this, null).setVisible(true);
	}

	/*
	 * private void azurirajKorisnika() { DodatnaPonudaDTO dp =
	 * ((DodatnaPonudaTableModel)
	 * tblDodatnePonude.getModel()).getDodatnaPonudaAtRow(tblDodatnePonude.
	 * getSelectedRow());
	 * 
	 * new DPDialog(this, dp).setVisible(true); }
	 * 
	 * private void obrisiKorisnika() { int row = tblKorisnici.getSelectedRow();
	 * 
	 * Object[] opcije = { "Da", "Ne" }; String message =
	 * "Da li ste sigurni da\nželite ukloniti korisnika?"; int value =
	 * JOptionPane.showOptionDialog(this, message, "", JOptionPane.YES_NO_OPTION,
	 * JOptionPane.WARNING_MESSAGE, null, opcije, opcije[0]); if (value == 0) {
	 * Zaposleni zaposleni = ((ZaposleniTableModel)
	 * tblKorisnici.getModel()).getZaposleni(row); if
	 * (zaposleniDAO.obrisiZaposlenog(zaposleni)) {
	 * JOptionPane.showMessageDialog(this, "Zaposleni je uspješno obrisan.",
	 * "Uspjeh", JOptionPane.INFORMATION_MESSAGE); azurirajTabeluKorisnika(); } else
	 * { JOptionPane.showMessageDialog(this, "Zaposleni nije uspješno obrisan.",
	 * "Greška", JOptionPane.ERROR_MESSAGE); } } }
	 * 
	 */

	public void sacuvajSalu() {
		String polje1 = textField.getText();
		int broj = Integer.parseInt(polje1);
		KinoDTO kino = kinoSQL.svaKina().get(0);
		SalaDTO dp = new SalaDTO(broj, kino);

		try {
			salaDAO.dodajSalu(dp);
			// setVisible(false);
			dispose();
			// frame.refreshDP();

			JOptionPane.showMessageDialog(frame, "Sala dodana uspijesno.", "Sala je dodana",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception ee) {
			JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " + ee.getMessage(), "Greska",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	/*
	 * public void sacuvaSjediste() { String polje1 = textField.getText(); int
	 * broj=Integer.parseInt(polje1); String polje2 =textField_1.getText(); int
	 * broj2=Integer.parseInt(polje2); SalaDTO sala=(SalaDTO)
	 * comboBox_1.getSelectedItem(); VrstaSjedistaDTO vs=(VrstaSjedistaDTO)
	 * comboBox.getSelectedItem();
	 * 
	 * SjedisteDTO s=new SjedisteDTO(broj, broj2, sala,vs);
	 * 
	 * 
	 * try { sjedisteDAO.dodajSjedisteUSaluUKinu(s); //setVisible(false); dispose();
	 * //frame.refreshDP();
	 * 
	 * JOptionPane.showMessageDialog(frame,
	 * "Sjediste dodano uspijesno.","Sala je dodana",
	 * JOptionPane.INFORMATION_MESSAGE); }catch(Exception ee) {
	 * JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " +
	 * ee.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE); }
	 * 
	 * 
	 * }
	 */

	public void sacuvajDP() {
		String polje1 = textField.getText().trim();

		String polje2 = textField_1.getText().trim();
		double cijena = Double.parseDouble(polje2);
		DodatnaPonudaDTO dp = new DodatnaPonudaDTO(polje1, cijena);

		try {
			dodatnaPonudaDAO.dodajDodatnuPonudu(dp);
			setVisible(false);
			dispose();
			// frame.refreshDP();

			JOptionPane.showMessageDialog(frame, "Dodatna ponuda dodana uspijesno.", "Dodatna ponuda je dodana",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception ee) {
			JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " + ee.getMessage(), "Greska",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void dodajSalu() {
		new SalaDialog(this, null).setVisible(true);
	}

	private void dodajDP() {
		new DodatnaPonudaDialog(this, null).setVisible(true);
	}

	private void dodajSjediste() {
		new SjedisteDialog(this, null).setVisible(true);
	}

	private void obrisiDO() {
		int row = table_1.getSelectedRow();

		Object[] opcije = { "Da", "Ne" };
		String message = "Da li ste sigurni da\nželite ukloniti dodatnu ponudu?";
		int value = JOptionPane.showOptionDialog(this, message, "", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null, opcije, opcije[0]);
		if (value == 0) {
			DodatnaPonudaDTO d = ((DodatnaPonudaTableModel) table_1.getModel()).getDodatnaPonudaAtRow(row);
			if (dodatnaPonudaDAO.obrisiDodatnuPonudu(d.getNaziv())) {
				JOptionPane.showMessageDialog(this, "Dodatna ponuda je uspješno obrisana.", "Uspjeh",
						JOptionPane.INFORMATION_MESSAGE);
				azurirajTabeluDP();
			} else {
				JOptionPane.showMessageDialog(this, "Dodatna ponuda je uspješno obrisana.", "Greška",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void obrisiSjediste() {
		int row = table_3.getSelectedRow();

		Object[] opcije = { "Da", "Ne" };
		String message = "Da li ste sigurni da\nželite ukloniti sjediste?";
		int value = JOptionPane.showOptionDialog(this, message, "", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null, opcije, opcije[0]);
		if (value == 0) {
			SjedisteDTO d = ((SjedisteTableModel) table_3.getModel()).getSjedisteAtRow(row);
			if (sjedisteDAO.obrisiSjedisteIzSaleKina(d.getBroj(), d.getRed(), d.getSala().getSalaID())) {
				JOptionPane.showMessageDialog(this, "Sjediste je uspješno obrisano.", "Uspjeh",
						JOptionPane.INFORMATION_MESSAGE);
				azurirajTabeluSjedista();
			} else {
				JOptionPane.showMessageDialog(this, "Sjediste nije obrisano.", "Greška", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void obrisiSalu() {
		int row = table_2.getSelectedRow();

		Object[] opcije = { "Da", "Ne" };
		String message = "Da li ste sigurni da\nželite ukloniti salu?";
		int value = JOptionPane.showOptionDialog(this, message, "", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null, opcije, opcije[0]);
		if (value == 0) {
			SalaDTO s = ((SalaTableModel) table_2.getModel()).getSalaAtRow(row);

			if (salaDAO.obrisiSalu(s.getKino().getKinoID(), s.getBroj())

			) {
				JOptionPane.showMessageDialog(this, "Sala je uspješno obrisana.", "Uspjeh",
						JOptionPane.INFORMATION_MESSAGE);
				azurirajTabeluSala();
			} else {
				JOptionPane.showMessageDialog(this, "Sala je uspješno obrisana.", "Greška", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	/*
	 * public void refreshDP() {
	 * 
	 * /*List<DodatnaPonudaDTO> dp = dodatnaPonudaDAO.sveDodatnePonude();
	 * DodatnaPonudaTableModel model = new DodatnaPonudaTableModel(dp);
	 */
	// table.setModel(new
	// DodatnaPonudaTableModel(dodatnaPonudaDAO.sveDodatnePonude()));
	// }

	private void podesiDugmad() {
		boolean enabled = table_1.getSelectedRow() != -1;

	}
}
