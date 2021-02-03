package org.unibl.etf.cinema.data;

import java.util.List;

import org.unibl.etf.cinema.entity.DodatnaPonuda;


public interface DodatnaPonudaDataAccess {
	List<DodatnaPonuda> sveDodatnePonude();
	DodatnaPonuda dodatnaPonuda(String naziv);
	boolean dodajDodatnuPonudu(DodatnaPonuda dp);
	boolean azurirajDodatnuPonudu(DodatnaPonuda dp);
	boolean obrisiDodatnuPonudu(String naziv);
	

}
