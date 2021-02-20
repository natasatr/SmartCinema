package org.unibl.etf.cinema.data.dao.mysql;


import org.unibl.etf.cinema.data.dao.*;

public class MySQLDAOFactory extends DAOFactory{
	
	@Override
	public AdresaDAO getAdresaDAO() {
		return new MySQLAdresaDAO();
	}
	
	@Override
	public KinoDAO getKinoDAO() {
		return new MySQLKinoDAO();
	}
	
	@Override
	public NalogDAO getNalogDAO() {
		return new MySQLNalogDAO();
	}

	@Override
	public SalaDAO getSalaDAO() {
		return new MySQLSalaDAO();
	}
	
	@Override
	public SjedisteDAO getSjedisteDAO() {
		return new MySQLSjedisteDAO();
	}
	
	
	@Override 
	public DodatnaPonudaDAO getDodatnaPonudaDAO() {
		return new  MySQLDodatnaPonudaDAO();
	}

	@Override 
	public VrstaSjedistaDAO getVrstaSjedistaDAO() {
		return new MySQLVrstaSjedistaDAO();
	}
	
	@Override
	public ZaposleniDAO getZaposleniDAO() {
		return new MySQLZaposleniDAO();
	}


	@Override
	public RolaDAO getRolaDAO() {
		return new MySQLRolaDAO();
	}

}
