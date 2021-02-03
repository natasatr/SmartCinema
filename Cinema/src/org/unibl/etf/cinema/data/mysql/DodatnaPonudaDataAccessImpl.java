package org.unibl.etf.cinema.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.cinema.data.DodatnaPonudaDataAccess;
import org.unibl.etf.cinema.entity.DodatnaPonuda;


public class DodatnaPonudaDataAccessImpl implements DodatnaPonudaDataAccess{
	
	@Override
	public List<DodatnaPonuda> sveDodatnePonude(){
		List<DodatnaPonuda> retVal=new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select DodatnaPonudaID, Naziv, Cijena from dodatna_ponuda "
				+ " order by DodatnaPonudaID asc " ;
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new DodatnaPonuda(rs.getInt(1),rs.getString(2), rs.getDouble(3)));
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
	public DodatnaPonuda dodatnaPonuda(String naziv) {
		DodatnaPonuda retVal = new DodatnaPonuda();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select DodatnaPonudaID, Naziv, Cijena from dodatna_ponuda" + 
				" where Naziv like ?";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1,
					MySQLUtilities.getInstance().preparePattern(naziv));
			rs = ps.executeQuery();

			
				retVal=new DodatnaPonuda(rs.getInt(1),rs.getString(2), rs.getDouble(3));
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
	public boolean dodajDodatnuPonudu(DodatnaPonuda dp) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO dodatna_ponuda VALUES "
				+ " (?, ?, ?)  ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, dp.getDodatnaPonudaID());
			ps.setString(2, dp.getNaziv());
			ps.setDouble(3, dp.getCijena());

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
	public boolean azurirajDodatnuPonudu(DodatnaPonuda dp) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE dodatna_ponuda SET "
				+ " Cijena =? "
				+ " WHERE Naziv=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setDouble(1, dp.getCijena());
			ps.setString(2, dp.getNaziv());

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
	public boolean obrisiDodatnuPonudu(String naziv) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM dodatna_ponuda "
				+ "WHERE Naziv=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, naziv);

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
		DodatnaPonudaDataAccessImpl dp=new DodatnaPonudaDataAccessImpl();
		//List<DodatnaPonuda> dod=dp.sveDodatnePonude();
		
		/*for(DodatnaPonuda d:dod)
			System.out.println(d);*/
		
		//DodatnaPonuda sjemenke=new DodatnaPonuda(10, "Sjemenke", 3);
		System.out.print(dp.obrisiDodatnuPonudu("Sjemenke"));
		
		
		
	}

}
