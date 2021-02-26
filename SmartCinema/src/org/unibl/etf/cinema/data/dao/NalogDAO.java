package org.unibl.etf.cinema.data.dao;

import org.unibl.etf.cinema.data.dto.Nalog;

public interface NalogDAO {
	boolean dodajNalog(Nalog nalog, String hash);

	boolean azurirajNalog(Nalog nalog, String hash);

	boolean obrisiNalog(Nalog nalog);

	Nalog prijava(String korisnickoIme, String lozinka);
	
	boolean postoji(String korisnickoIme);
}
