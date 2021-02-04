package org.unibl.etf.cinema.data.dao;

import java.util.List;

import org.unibl.etf.cinema.data.dto.KinoDTO;

public interface KinoDAO {
	List<KinoDTO> svaKina();
	KinoDTO kino(String naziv);
	boolean dodajKino(KinoDTO kino);
	boolean azurirajKino(KinoDTO kino);
	boolean obrisiKino(String naziv);

}
