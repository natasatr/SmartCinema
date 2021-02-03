package org.unibl.etf.cinema.data;

import java.util.List;

import org.unibl.etf.cinema.entity.Sjediste;



public interface SjedisteDataAccess {

	List<Sjediste> svaSjedistaUSaliUKinu(int SalaID);
	Sjediste sjediste(int SjedisteID);
	boolean dodajSjedisteUSaluUKinu(Sjediste sjediste);
	boolean azurirajSjedisteUSaliUKinu(Sjediste sjediste);
	boolean obrisiSjedisteIzSaleKina(int broj, int red,int SalaID);
}
