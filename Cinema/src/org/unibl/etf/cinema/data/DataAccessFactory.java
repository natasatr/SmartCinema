package org.unibl.etf.cinema.data;

import org.unibl.etf.cinema.data.mysql.MySQLDataAccessFactory;

public abstract class DataAccessFactory {
	
	public abstract AdresaDataAccess getAdresaDataAccess();
	public abstract KinoDataAccess getKinoDataAccess();
	public abstract PosjetilacDataAccess getPosjetilacDataAccess();
	public abstract SalaDataAccess getSalaDataAccess();
	public abstract SjedisteDataAccess getSjedisteDataAccess();
	public abstract DodatnaPonudaDataAccess getDodatnaPonudaDataAccess();
	public abstract VrstaSjedistaDataAccess getVrstaSjedistaDataAccess();
	
	public static DataAccessFactory getFactory(DataAccessFactoryType type) {
		if (DataAccessFactoryType.MySQL.equals(type)) {
			return new MySQLDataAccessFactory();
		}
		throw new IllegalArgumentException();
	}

}
