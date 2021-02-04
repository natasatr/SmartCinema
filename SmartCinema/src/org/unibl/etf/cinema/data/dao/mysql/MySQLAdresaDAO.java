package org.unibl.etf.cinema.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.cinema.data.dao.AdresaDAO;
import org.unibl.etf.cinema.data.dto.AdresaDTO;
import org.unibl.etf.cinema.util.ConnectionPool;
import org.unibl.etf.cinema.util.DBUtil;



public class MySQLAdresaDAO implements AdresaDAO {
	
	@Override 
	public List<AdresaDTO> sveAdrese(){
		List<AdresaDTO> retVal=new ArrayList<>();
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
				retVal.add(new AdresaDTO(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getInt(4)));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(rs, ps, conn);
		}
		return retVal;
	}
	
	@Override
	public AdresaDTO adresa(int AdresaID) {
		AdresaDTO retVal = new AdresaDTO();
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

			
				retVal=new AdresaDTO(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getInt(4));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(rs, ps, conn);
		}
		return retVal;
	}
	
	@Override /*vrati false on duplicate key, ali dobro je izvrseno
				jer se nije dodalo nista, vec update-ujemo vrijednost uklonjeno*/
	public boolean dodajAdresu(AdresaDTO adresa) {
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
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(ps);
		}
		return retVal;
	}
	
	@Override 
	public boolean azurirajAdresu(AdresaDTO adresa)
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
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(ps);
		}
		return retVal;
	}
	
	@Override 
	public boolean obrisiAdresu(AdresaDTO adresa) {
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
			
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(ps);
		}
		return retVal;
		
	}
	
	

}
