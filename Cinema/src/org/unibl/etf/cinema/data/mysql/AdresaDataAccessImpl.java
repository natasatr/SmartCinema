package org.unibl.etf.cinema.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.cinema.data.AdresaDataAccess;
import org.unibl.etf.cinema.entity.Adresa;



public class AdresaDataAccessImpl implements AdresaDataAccess{
	
	@Override 
	public List<Adresa> sveAdrese(){
		List<Adresa> retVal=new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select AdresaID, Naziv, Broj, Uklonjeno from adresa "
				+ " order by Naziv asc " ;
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Adresa(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getInt(4)));
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps, rs);
		}
		return retVal;
	}
	
	@Override
	public Adresa adresa(int AdresaID) {
		Adresa retVal = new Adresa();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select AdresaID, Naziv, Broj, Uklonjeno from adresa " + 
				" where AdresaID like ?";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1,AdresaID);
			rs = ps.executeQuery();

			
				retVal=new Adresa(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getInt(4));
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps, rs);
		}
		return retVal;
	}
	
	@Override /*vrati false on duplicate key, ali dobro je izvrseno
				jer se nije dodalo nista, vec update-ujemo vrijednost uklonjeno*/
	public boolean dodajAdresu(Adresa adresa) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO adresa VALUES "
				+ " (?, ?, ?, ? ) on duplicate key update Uklonjeno=values(Uklonjeno) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1,adresa.getAdresaID());
			ps.setString(2, adresa.getNaziv());
			ps.setInt(3, adresa.getBroj());
			ps.setInt(4, 0);

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps);
		}
		return retVal;
	}
	
	@Override 
	public boolean azurirajAdresu(Adresa adresa)
	{
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE adresa SET "
				+ " Naziv =?, "
				+ " Broj= ?  "
				+ " WHERE AdresaID=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, adresa.getNaziv());
			ps.setInt(2, adresa.getBroj());
			ps.setInt(3, adresa.getAdresaID());

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps);
		}
		return retVal;
	}
	
	@Override 
	public boolean obrisiAdresu(Adresa adresa) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = " update adresa set Uklonjeno=1"
				+ " where AdresaID = ? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, adresa.getAdresaID());

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps);
		}
		return retVal;
		
	}
	
	public static void main(String args[]) {
		AdresaDataAccessImpl a=new AdresaDataAccessImpl();
		
		Adresa a1=new Adresa(6,"Vase Pelagica",26, 0);
		
		
		
		
			System.out.println(a.azurirajAdresu(a1));
		
		
		
		
	}

}
