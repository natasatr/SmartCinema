package org.unibl.etf.cinema.data;

import java.util.List;

import org.unibl.etf.cinema.entity.VrstaSjedista;



public interface VrstaSjedistaDataAccess {

	List<VrstaSjedista> sveVrsteSjedista();
	boolean dodajVrstuSjedista(VrstaSjedista vs);
	boolean azurirajVrstuSjedista(VrstaSjedista vs);
	boolean obrisiVrstuSjedista(String naziv);
}
