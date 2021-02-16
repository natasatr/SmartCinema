package org.unibl.etf.cinema.data.dao;

import java.util.List;

import org.unibl.etf.cinema.data.dto.Zaposleni;

public interface ZaposleniDAO {
	List<Zaposleni> sviZaposleni();
	boolean dodajZaposlenog(Zaposleni zaposleni);
	boolean azurirajZaposlenog(Zaposleni zaposleni);
	boolean obrisiZaposlenog(Zaposleni zaposleni);
}
