package org.unibl.etf.cinema.data;

import java.util.List;

import org.unibl.etf.cinema.entity.Posjetilac;



public interface PosjetilacDataAccess {
	
	List<Posjetilac> sviPosjetioci();
	boolean dodajPosjetioca(Posjetilac p);
	boolean obrisiPosjetioca(String ime, String prezime);
	

}
