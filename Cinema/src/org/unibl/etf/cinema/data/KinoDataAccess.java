package org.unibl.etf.cinema.data;

import java.util.List;

import org.unibl.etf.cinema.entity.Kino;



public interface KinoDataAccess {

	List<Kino> svaKina();
	Kino kino(String naziv);
	boolean dodajKino(Kino kino);
	boolean azurirajKino(Kino kino);
	boolean obrisiKino(String naziv);
}
