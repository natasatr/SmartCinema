package org.unibl.etf.cinema.view.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.cinema.data.dto.SjedisteDTO;

@SuppressWarnings("serial")
public class SjedisteTableModel extends AbstractTableModel {

	private String[] naziviKolona = { "#", "Broj", "Red", "Vrsta sjedišta" };

	private List<SjedisteDTO> sjedista;

	public List<SjedisteDTO> getSjedista() {
		return sjedista;
	}

	public void setSjedista(List<SjedisteDTO> sjedista) {
		this.sjedista = sjedista;
	}

	public SjedisteTableModel(List<SjedisteDTO> sjedista) {
		this.sjedista = sjedista;
	}

	public SjedisteDTO getSjedisteAtRow(int rowIndex) {
		return sjedista.get(rowIndex);
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
		return sjedista.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		SjedisteDTO red = sjedista.get(rowIndex);
		if (columnIndex == 0)
			return red.getSjedisteID();
		else if (columnIndex == 1)
			return red.getBroj();
		else if (columnIndex == 2)
			return red.getRed();
		else if (columnIndex == 3)
			return red.getVrstaSjedista().getNaziv();
		// else if(columnIndex==4)
		// return red.getVrstaSjedista().getNaziv();

		else
			return null;
	}

}
