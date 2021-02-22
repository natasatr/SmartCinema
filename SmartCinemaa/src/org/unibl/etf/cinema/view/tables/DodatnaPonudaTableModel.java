package org.unibl.etf.cinema.view.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.cinema.data.dto.DodatnaPonudaDTO;

@SuppressWarnings("serial")
public class DodatnaPonudaTableModel extends AbstractTableModel{
	
	private String[] naziviKolona= {"#", "NAZIV", "CIJENA"};
	
	private List<DodatnaPonudaDTO> dodatnePonude;

	public DodatnaPonudaTableModel(List<DodatnaPonudaDTO> dodatnePonude) {
		this.dodatnePonude = dodatnePonude;
	}
	
	public DodatnaPonudaDTO getDodatnaPonudaAtRow(int rowIndex) {
		return dodatnePonude.get(rowIndex);
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
		return dodatnePonude.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		DodatnaPonudaDTO red = dodatnePonude.get(rowIndex);
		if (columnIndex == 0)
			return red.getDodatnaPonudaID();
		else if (columnIndex == 1)
			return red.getNaziv();
		else if (columnIndex == 2)
			return red.getCijena();
		
		else
			return null;
	}
	
	

}