package org.unibl.etf.cinema.view.tables;

import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.cinema.data.dto.PrikazivanjeFilmaUSaliDTO;
import org.unibl.etf.cinema.data.dto.Zaposleni;

public class TerminiTableModel extends AbstractTableModel {
		
	private String[] naziviKolona= {"#", "Naziv", "Termin"};

	private List<PrikazivanjeFilmaUSaliDTO> termini;
	
	 public TerminiTableModel(List<PrikazivanjeFilmaUSaliDTO> termini) {
		this.termini=termini;
	}
	
	 public void setTermini(List<PrikazivanjeFilmaUSaliDTO> termini) {
		 this.termini = termini;
	 }
	 
	@Override
	public int getRowCount() {
		return termini.size();
		
	}

	@Override
	public int getColumnCount() {
		
		return naziviKolona.length;
	}

	@Override
	public String getColumnName(int col)
	{
		return naziviKolona[col];
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		PrikazivanjeFilmaUSaliDTO temp = termini.get(rowIndex);
		
		if(columnIndex==0)
			return temp.getTerminID();
		else if(columnIndex==1)
			return temp.getFilm().getNaziv();
		else if(columnIndex==2)
			return temp.termin;
		else return temp.getFilm().getNaziv();
	}
		


}
