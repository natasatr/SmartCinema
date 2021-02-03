package org.unibl.etf.cinema.data;

import java.util.List;

import org.unibl.etf.cinema.entity.Adresa;



public interface AdresaDataAccess {
	List<Adresa> sveAdrese();
	Adresa adresa(int AdresaID);
	boolean dodajAdresu(Adresa adresa);
	boolean azurirajAdresu(Adresa adresa);
	boolean obrisiAdresu(Adresa adresa);

}
