package org.unibl.etf.cinema.view.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.cinema.data.dto.RezervacijaDTO;

public class RezervacijaTableModel extends AbstractTableModel {
	private String[] naziviKolona= {"#", "IME", "PREZIME", "DO DATUMA", "FILM", "TERMIN", "SALA", "RED", "BROJ" };
	private List<RezervacijaDTO> rezervacija;

	public RezervacijaTableModel(List<RezervacijaDTO> rezervacija) {
		this.rezervacija = rezervacija;
	}
	
	public RezervacijaDTO getRezervacijaAtRow(int rowIndex) {
		return rezervacija.get(rowIndex);
	}
	public void setRezervacija(List<RezervacijaDTO> rezervacija) {
        this.rezervacija = rezervacija;
	}
	
	@Override
	public int getColumnCount() {
		return naziviKolona.length;
	}

	@Override
	public String getColumnName(int column) {
		return naziviKolona[column];
	}

	@Override
	public int getRowCount() {
		return rezervacija.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		RezervacijaDTO red = rezervacija.get(rowIndex);
		if (columnIndex == 0)
			return red.getRezervacijaID();
		else if (columnIndex == 1)
			return red.getIme();
		else if (columnIndex == 2)
			return red.getPrezime();
		else if (columnIndex == 3)
			return red.getDoDatuma();
		else if (columnIndex == 4)
			return red.getKarta().getPfus().getFilm().getNaziv();
		else if (columnIndex == 5)
			return red.getKarta().getPfus().termin;
		else if (columnIndex == 6)
			return red.getKarta().getPfus().getSala().getBroj();
		else if (columnIndex == 7)
			return red.getKarta().getSjediste().getRed();
		else if (columnIndex == 8)
			return red.getKarta().getSjediste().getBroj();
		
		else
			return null;
	}

	public List<RezervacijaDTO> getRezervacija() {
        return rezervacija;
    }
}
