package org.unibl.etf.cinema.data.mysql;

import org.unibl.etf.cinema.data.AdresaDataAccess;
import org.unibl.etf.cinema.data.DataAccessFactory;
import org.unibl.etf.cinema.data.DodatnaPonudaDataAccess;
import org.unibl.etf.cinema.data.KinoDataAccess;
import org.unibl.etf.cinema.data.PosjetilacDataAccess;
import org.unibl.etf.cinema.data.SalaDataAccess;
import org.unibl.etf.cinema.data.SjedisteDataAccess;
import org.unibl.etf.cinema.data.VrstaSjedistaDataAccess;

public class MySQLDataAccessFactory extends DataAccessFactory{
	
	@Override
	public AdresaDataAccess getAdresaDataAccess() {
		return new AdresaDataAccessImpl();
	}
	
	
	@Override
	public KinoDataAccess getKinoDataAccess() {
		return new KinoDataAccessImpl();
	}
	
	@Override
	public PosjetilacDataAccess getPosjetilacDataAccess() {
		return new PosjetilacDataAccessImpl();
	}
	
	@Override
	public SalaDataAccess getSalaDataAccess() {
		return new SalaDataAccessImpl();
	}
	
	@Override
	public SjedisteDataAccess getSjedisteDataAccess() {
		return new SjedisteDataAccessImpl();
	}
	
	
	@Override 
	public  VrstaSjedistaDataAccess getVrstaSjedistaDataAccess() {
		return new  VrstaSjedistaDataAccessImpl();
	}

	@Override 
	public DodatnaPonudaDataAccess getDodatnaPonudaDataAccess() {
		return new DodatnaPonudaDataAccessImpl();
	}
	
		

}
