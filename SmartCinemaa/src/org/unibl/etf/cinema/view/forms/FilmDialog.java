package org.unibl.etf.cinema.view.forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.cinema.data.dao.mysql.MySQLFilmDAO;
import org.unibl.etf.cinema.data.dto.FilmDTO;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class FilmDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_9;
	private JTextField textField_13;
	
	private MySQLFilmDAO filmDAO;
	private FilmFrame frame;


	/**
	 * Create the dialog.
	 */
	public FilmDialog(FilmFrame frame, MySQLFilmDAO filmDAO )
	{
		this();
		this.filmDAO= filmDAO;
		this.frame=frame;
	}

	
	public FilmDialog() {
		setBounds(100, 100, 1112, 418);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(33,20,47));
		contentPanel.setForeground(new Color(0, 0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblNewLabel = new JLabel("Film ID:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel.setBackground(new Color(0, 0, 139));
			lblNewLabel.setForeground(new Color(250, 250, 210));
			contentPanel.add(lblNewLabel, "2, 2, left, default");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "4, 2, fill, default");
			textField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Naziv:");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_1.setBackground(new Color(0, 0, 139));
			lblNewLabel_1.setForeground(new Color(250, 250, 210));
			contentPanel.add(lblNewLabel_1, "2, 4");
		}
		{
			textField_1 = new JTextField();
			contentPanel.add(textField_1, "4, 4, fill, default");
			textField_1.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Trajanje:");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_2.setBackground(new Color(0, 0, 139));
			lblNewLabel_2.setForeground(new Color(250, 250, 210));
			contentPanel.add(lblNewLabel_2, "2, 6");
		}
		{
			textField_2 = new JTextField();
			contentPanel.add(textField_2, "4, 6, fill, default");
			textField_2.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Godina Snimanja:");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_3.setBackground(new Color(0, 0, 139));
			lblNewLabel_3.setForeground(new Color(250, 250, 210));
			contentPanel.add(lblNewLabel_3, "2, 8");
		}
		{
			textField_3 = new JTextField();
			contentPanel.add(textField_3, "4, 8, fill, default");
			textField_3.setColumns(10);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Reziser:");
			lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_4.setBackground(new Color(0, 0, 139));
			lblNewLabel_4.setForeground(new Color(250, 250, 210));
			contentPanel.add(lblNewLabel_4, "2, 10");
		}
		{
			textField_4 = new JTextField();
			contentPanel.add(textField_4, "4, 10, fill, default");
			textField_4.setColumns(10);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Opis:");
			lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_5.setBackground(new Color(0, 0, 139));
			lblNewLabel_5.setForeground(new Color(250, 250, 210));
			contentPanel.add(lblNewLabel_5, "2, 12");
		}
		{
			textField_5 = new JTextField();
			contentPanel.add(textField_5, "4, 12, fill, default");
			textField_5.setColumns(10);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("U repertoaru:");
			lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_6.setBackground(new Color(0, 0, 139));
			lblNewLabel_6.setForeground(new Color(250, 250, 210));
			contentPanel.add(lblNewLabel_6, "2, 14");
		}
		{
			textField_6 = new JTextField();
			contentPanel.add(textField_6, "4, 14, fill, default");
			textField_6.setColumns(10);
		}
		{
			JLabel lblNewLabel_7 = new JLabel("Datum projekcije:");
			lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_7.setBackground(new Color(0, 0, 139));
			lblNewLabel_7.setForeground(new Color(250, 250, 210));
			contentPanel.add(lblNewLabel_7, "2, 16");
		}
		{
			textField_7 = new JTextField();
			contentPanel.add(textField_7, "4, 16, fill, default");
			textField_7.setColumns(10);
		}
		{
			JLabel lblNewLabel_9 = new JLabel("Glumci :");
			lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_9.setBackground(new Color(0, 0, 139));
			lblNewLabel_9.setForeground(new Color(250, 250, 210));
			contentPanel.add(lblNewLabel_9, "2, 18");
		}
		{
			textField_9 = new JTextField();
			contentPanel.add(textField_9, "4, 18, fill, default");
			textField_9.setColumns(10);
		}
		{
			JLabel lblNewLabel_13 = new JLabel("Naziv zanra:");
			lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_13.setBackground(new Color(0, 0, 139));
			lblNewLabel_13.setForeground(new Color(250, 250, 210));
			contentPanel.add(lblNewLabel_13, "2, 20");
		}
		{
			textField_13 = new JTextField();
			contentPanel.add(textField_13, "4, 20, fill, default");
			textField_13.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Sacuvaj");
				okButton.setBackground(new Color(250, 250, 210));
				okButton.setForeground(new Color(0, 0, 0));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						sacuvajFilm();
					}

					
				});
				okButton.setActionCommand("Sacuvaj");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Odustani");
				cancelButton.setBackground(new Color(250, 250, 210));
				cancelButton.setForeground(new Color(0, 0, 0));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						setVisible(true);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void sacuvajFilm() {
		String polje1 = textField.getText().toString();
		int id = Integer.parseInt(polje1);
		String polje2 = textField_1.getText().toString();
		String polje3 = textField_2.getText().toString();
		String polje4 = textField_3.getText();
		int godina = Integer.parseInt(polje4);
		
		String polje5 = textField_4.getText().toString();
		String polje6 = textField_5.getText().toString();
		
		String polje7 = textField_6.getText().toString();
		String polje8 = textField_7.getText().toString();
		String polje10 = textField_9.getText().toString();
		String polje11 = textField_13.getText().toString();
		

		
		FilmDTO film = new FilmDTO(id, polje2, polje3, godina, polje5, polje6, polje7, polje8,polje10,polje11);
		try
		{
			System.out.println("Usao");
			filmDAO.insert(film);
			System.out.println("jdiajdai");
			setVisible(false);
			dispose();
			frame.refresh();
			
			JOptionPane.showMessageDialog(frame, "Film dodan uspijesno.","Film je dodan", JOptionPane.INFORMATION_MESSAGE);
		}catch(Exception ee)
		{
			JOptionPane.showMessageDialog(frame, "Greska pri cuvanju zaposlenog: " + ee.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
		}
	
		
	}

}
