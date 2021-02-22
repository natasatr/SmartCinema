package org.unibl.etf.cinema.view.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.cinema.data.dto.Zaposleni;
import org.unibl.etf.cinema.util.Utils;

public class ZaposleniTableModel extends AbstractTableModel {
	private List<Zaposleni> zaposleni;
	private final String[] header = { "#", "JMB", "Ime", "Prezime", "Plata (KM)", "Email", "Korisnièko ime", "Rola",
			"Adresa" };

	public ZaposleniTableModel(List<Zaposleni> zaposleni) {
		this.zaposleni = zaposleni;
	}

	public void setZaposleni(List<Zaposleni> zaposleni) {
		this.zaposleni = zaposleni;
	}

	public Zaposleni getZaposleni(int rowIndex) {
		return zaposleni.get(rowIndex);
	}

	@Override
	public int getRowCount() {
		return zaposleni.size();
	}

	@Override
	public int getColumnCount() {
		return header.length;
	}

	@Override
	public String getColumnName(int column) {
		return (column > header.length) ? "" : header[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Zaposleni z = zaposleni.get(rowIndex);
		Object value = null;
		switch (columnIndex) {
		case 0:
			value = rowIndex + 1 + ".";
			break;
		case 1:
			value = z.getJmb();
			break;
		case 2:
			value = z.getIme();
			break;
		case 3:
			value = z.getPrezime();
			break;
		case 4:
			value = Utils.formatDecimalNumber(z.getPlata());
			break;
		case 5:
			value = z.getEmail();
			break;
		case 6:
			value = z.getNalog().getKorisnickoIme();
			break;
		case 7:
			value = z.getNalog().getRola().getNaziv();
			break;
		case 8:
			value = z.getAdresa().toString();
			break;
		default:
			value = "";
			break;
		}
		return value;
	}

	public List<Zaposleni> getZaposleni() {
		return zaposleni;
	}
}
