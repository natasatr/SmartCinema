package org.unibl.etf.cinema.view.tables;

import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.cinema.data.dto.FilmDTO;
import org.unibl.etf.cinema.data.dto.Zaposleni;

public class FilmTableModell extends AbstractTableModel {
	
	
	private static final int FILMID_COL = 0;
	private static final int NAZIV_COL =1;
	private static final int TRAJANJE_COL =2;
	private static final int GODINA_SNIMANJA_COL=3;
	private static final int REZISER_COL=4;
	private static final int OPIS_COL=5;
	private static final int U_REPERTOARU_COL=6;
	private static final int DATUM_PRIKAZIVANJA_COL=7;
	private static final int GLUMCI_COL = 8;
	private static final int NAZIV_ZANRA_COL=9;
	
	private String[] naziviKolona= {"Film ID", "Naziv", "Trajanje", "Godina Snimanja", "Reziser", "Opis", "U repertoaru", "Datum prikazivanja",  "Glumci",  "Naziv Zanra"};

	private List<FilmDTO> filmovi;
	 public FilmTableModell(List<FilmDTO> filmovi) {
		this.filmovi=filmovi;
	}
	 
	
	 
	@Override
	public int getRowCount() {
		return filmovi.size();
		
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
		FilmDTO temp = filmovi.get(rowIndex);
		switch(columnIndex)
		{
		case FILMID_COL:
		return temp.getFilmID();
		case NAZIV_COL:
			return temp.getNaziv();
		case TRAJANJE_COL:
			return temp.getTrajanje();
		case GODINA_SNIMANJA_COL:
			return temp.getGodinaSnimanja();
		case REZISER_COL:
			return temp.getReziser();
		case OPIS_COL:
			return temp.getOpis();
		case U_REPERTOARU_COL:
			return temp.isURepetoaru();
		case DATUM_PRIKAZIVANJA_COL:
			return temp.getDatumPrvogPrikazivanja();
		case GLUMCI_COL:
			return temp.getGlumci();
		case NAZIV_ZANRA_COL:
			return temp.getZanr();
			default:
				return temp.getNaziv();
		}
		
		
	}
	@Override 
	public Class getColumnClass(int c)
	{
		return getValueAt(0, c).getClass();
	}
	

}
