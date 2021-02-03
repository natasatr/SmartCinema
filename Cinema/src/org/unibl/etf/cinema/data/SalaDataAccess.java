package org.unibl.etf.cinema.data;

import java.util.List;


import org.unibl.etf.cinema.entity.Sala;



public interface SalaDataAccess {
	List<Sala> sveSaleUKinu(String nazivKina);
	boolean dodajSalu(Sala sala);
	boolean azurirajSalu(Sala sala);
	boolean obrisiSalu(int  KinoID, int broj);

}
