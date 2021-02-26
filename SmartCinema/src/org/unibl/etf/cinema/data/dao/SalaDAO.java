package org.unibl.etf.cinema.data.dao;

import java.util.List;

import org.unibl.etf.cinema.data.dto.SalaDTO;

public interface SalaDAO {
	List<SalaDTO> sveSale();

	List<SalaDTO> sveSaleUKinu(String nazivKina);

	boolean dodajSalu(SalaDTO sala);

	boolean azurirajSalu(SalaDTO sala);

	boolean obrisiSalu(int SalaID);

}
