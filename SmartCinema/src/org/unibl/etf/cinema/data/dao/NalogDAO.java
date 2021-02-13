package org.unibl.etf.cinema.data.dao;

import org.unibl.etf.cinema.data.dto.Nalog;

public interface NalogDAO {
	
	boolean dodajNalog(Nalog nalog);
	boolean azurirajNalog(Nalog nalog);
	boolean obrisiNalog(Nalog nalog);
}
