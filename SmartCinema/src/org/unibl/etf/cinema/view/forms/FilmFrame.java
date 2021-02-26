package org.unibl.etf.cinema.view.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.cinema.data.dao.mysql.MySQLFilmDAO;
import org.unibl.etf.cinema.data.dto.FilmDTO;
import org.unibl.etf.cinema.view.tables.FilmTableModell;

public class FilmFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	private MySQLFilmDAO filmDAO;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FilmFrame frame = new FilmFrame();
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
	public FilmFrame() {setBounds(100, 100, 1112, 679);
		try
		{
			filmDAO = new MySQLFilmDAO();
		}catch(Exception e )
		{
			JOptionPane.showMessageDialog(this,"Error" +e, "Error", JOptionPane.ERROR_MESSAGE);
		}
	
		
		
		setTitle("Film Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(33,20,47));
		
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Unesi");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Pretraga");
		btnNewButton.setBackground(new Color(250, 250, 210));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String Naziv = textField.getText();
					List<FilmDTO> filmovi = null;
					if(Naziv != null && Naziv.trim().length()>0)
					{
						filmovi = filmDAO.searchMovie(Naziv);
					}
					
					FilmTableModell model = new FilmTableModell(filmovi);
					table.setModel(model);
				}catch(Exception ee)
				{
					JOptionPane.showMessageDialog(FilmFrame.this, "Error" +ee +"Error" +JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Prikazi sve");
		btnNewButton_1.setBackground(new Color(250, 250, 210));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<FilmDTO> filmovi = filmDAO.selectAll();
				FilmTableModell model = new FilmTableModell(filmovi);
				table.setModel(model);
				
			}
		});
		panel.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(33,20,47));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton_2 = new JButton("Dodaj");
		btnNewButton_2.setBackground(new Color(250, 250, 210));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FilmDialog dialog = new FilmDialog(FilmFrame.this, filmDAO);
				dialog.setVisible(true);
			}
		});
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_5 = new JButton("Obrisi");
		btnNewButton_5.setBackground(new Color(250, 250, 210));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int vrsta = table.getSelectedRow();
					if(vrsta < 0 )
					{
						JOptionPane.showMessageDialog(FilmFrame.this, "Prvo je potrebno selektovati film!", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					int response = JOptionPane.showConfirmDialog(FilmFrame.this, "OBRISI Film? " , "Potvrdi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(response != JOptionPane.YES_OPTION)
					{
						
						return;
					}
					int id= (int) table.getValueAt(vrsta, 0);
					filmDAO.delete(id);
					
					refresh();
					
					JOptionPane.showMessageDialog(FilmFrame.this, "Film je uspijesno obrisan.", "F Obrisan", JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception ee)
				{
					ee.printStackTrace();
				}
				
			}
		});
		
		
	
		panel_1.add(btnNewButton_5);
		
		JButton btnNewButton_3 = new JButton("Odustani");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setBackground(new Color(250, 250, 210));
		panel_1.add(btnNewButton_3);
	}

	public void refresh() {
		
		List<FilmDTO> filmovi = filmDAO.selectAll();
		FilmTableModell model = new FilmTableModell(filmovi);
		table.setModel(model);
	}

}
