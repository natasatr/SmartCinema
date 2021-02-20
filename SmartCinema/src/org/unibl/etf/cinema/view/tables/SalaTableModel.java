package org.unibl.etf.cinema.view.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.cinema.data.dto.SalaDTO;

@SuppressWarnings("serial")
public class SalaTableModel extends AbstractTableModel{
	
private String[] naziviKolona= {"#", "BROJ_SALE", "KAPACITET"};
	
	private List<SalaDTO> sale;

	public SalaTableModel(List<SalaDTO> sale) {
		this.sale = sale;
	}
	
	public SalaDTO getSalaAtRow(int rowIndex) {
		return sale.get(rowIndex);
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
		return sale.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		SalaDTO red = sale.get(rowIndex);
		if (columnIndex == 0)
			return red.getSalaID();
		else if (columnIndex == 1)
			return red.getBroj();
		else if (columnIndex == 2)
			return red.getKapacitet() + " sjedista";
		
		else
			return null;
	}

}
