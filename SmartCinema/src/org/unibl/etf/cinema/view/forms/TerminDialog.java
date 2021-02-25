package org.unibl.etf.cinema.view.forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.cinema.data.dao.DAOFactory;
import org.unibl.etf.cinema.data.dao.FilmDAO;
import org.unibl.etf.cinema.data.dao.PrikazivanjeFilmaUSaliDAO;
import org.unibl.etf.cinema.data.dao.SalaDAO;
import org.unibl.etf.cinema.data.dto.FilmDTO;
import org.unibl.etf.cinema.data.dto.PrikazivanjeFilmaUSaliDTO;
import org.unibl.etf.cinema.data.dto.SalaDTO;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TerminDialog extends JDialog {

	PrikazivanjeFilmaUSaliDAO pfusDAO = DAOFactory.getDAOFactory().getPrikazivanjeFilmaUSaliDAO();
	FilmDAO filmDAO = DAOFactory.getDAOFactory().getFilmDAO();
	SalaDAO salaDAO = DAOFactory.getDAOFactory().getSalaDAO();
	
	private final JPanel contentPanel = new JPanel();
	private JTextField tfTermin;
	private JComboBox comboBox;
	
	
	private JComboBox comboBox_1;

	public TerminDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblFilm = new JLabel("Odaberite Film");
		lblFilm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel lblSala = new JLabel("Odaberite Salu");
		lblSala.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel lblTermin = new JLabel("Termin:");
		lblTermin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel lblNewLabel_3 = new JLabel("Unesite potrebne podatke:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfTermin = new JTextField();
		tfTermin.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox_1 = new JComboBox();
		
		List<FilmDTO> filmovi = filmDAO.selectAll();
		for (FilmDTO filmDTO : filmovi) {
			comboBox_1.addItem(filmDTO.getFilmID()+"#"+filmDTO.getNaziv());
		}
		
		List<SalaDTO>sale = salaDAO.sveSale();
		for (SalaDTO salaDTO : sale) {
			comboBox.addItem(salaDTO);
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFilm)
								.addComponent(lblSala)
								.addComponent(lblTermin))
							.addGap(48)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(comboBox_1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(tfTermin))))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblNewLabel_3)
					.addGap(28)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFilm)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSala)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTermin)
						.addComponent(tfTermin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(69, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dodajTermin();
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void dodajTermin() {
		String[] s = comboBox_1.getSelectedItem().toString().split("#",2);
		SalaDTO sala = (SalaDTO)comboBox.getSelectedItem();
		String termin = tfTermin.getText().toString().trim();
		List<FilmDTO> f = filmDAO.searchMovie(s[1]);
		for (FilmDTO filmDTO : f) {
			if(filmDTO.getFilmID()== Integer.parseInt(s[0]))
			if(pfusDAO.dodaj(new PrikazivanjeFilmaUSaliDTO(filmDTO, sala, termin))) {
				JOptionPane.showMessageDialog(this, "Termin uspješno dodan.", "Uspjeh",
						JOptionPane.INFORMATION_MESSAGE);
				}else {
				JOptionPane.showMessageDialog(this, "Termin nije uspješno dodan.", "Greška",
						JOptionPane.ERROR_MESSAGE);
			}
	}
	}
}

