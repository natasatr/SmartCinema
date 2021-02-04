package org.unibl.etf.cinema.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.cinema.data.dao.DodatnaPonudaDAO;
import org.unibl.etf.cinema.data.dto.DodatnaPonudaDTO;
import org.unibl.etf.cinema.util.ConnectionPool;
import org.unibl.etf.cinema.util.DBUtil;



public class MySQLDodatnaPonudaDAO implements DodatnaPonudaDAO{
	
	@Override
	public List<DodatnaPonudaDTO> sveDodatnePonude(){
		List<DodatnaPonudaDTO> retVal=new ArrayList<>();
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
				retVal.add(new DodatnaPonudaDTO(rs.getInt(1),rs.getString(2), rs.getDouble(3)));
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(rs, ps, conn);
		}
		return retVal;
	}
	
	@Override
	public DodatnaPonudaDTO dodatnaPonuda(String naziv) {
		DodatnaPonudaDTO retVal = new DodatnaPonudaDTO();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select DodatnaPonudaID, Naziv, Cijena from dodatna_ponuda" + 
				" where Naziv like ?";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1,naziv);
			rs = ps.executeQuery();

			
				retVal=new DodatnaPonudaDTO(rs.getInt(1),rs.getString(2), rs.getDouble(3));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(rs, ps, conn);
		}
		return retVal;
	}
	
	@Override
	public boolean dodajDodatnuPonudu(DodatnaPonudaDTO dp) {
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
			
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close( ps, conn);
		}
		return retVal;
	}
	
	@Override
	public boolean azurirajDodatnuPonudu(DodatnaPonudaDTO dp) {
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
			
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(ps, conn);
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
			
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close( ps, conn);
		}
		return retVal;
		
	}

}
