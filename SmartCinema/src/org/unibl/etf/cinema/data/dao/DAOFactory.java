package org.unibl.etf.cinema.data.dao;

import org.unibl.etf.cinema.data.dao.mysql.MySQLDAOFactory;

public abstract class DAOFactory {
	public abstract AdresaDAO getAdresaDAO();
	public abstract KinoDAO getKinoDAO();
	public abstract PosjetilacDAO getPosjetilacDAO();
	public abstract SalaDAO getSalaDAO();
	public abstract SjedisteDAO getSjedisteDAO();
	public abstract DodatnaPonudaDAO getDodatnaPonudaDAO();
	public abstract VrstaSjedistaDAO getVrstaSjedistaDAO();
	
	public static DAOFactory getDAOFactory() {
        return new MySQLDAOFactory();
    }

}
