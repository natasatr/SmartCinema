package org.unibl.etf.cinema.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.cinema.data.dao.PosjetilacDAO;
import org.unibl.etf.cinema.data.dto.PosjetilacDTO;
import org.unibl.etf.cinema.util.ConnectionPool;
import org.unibl.etf.cinema.util.DBUtil;

public class MySQLPosjetilacDAO implements PosjetilacDAO {
	@Override
	public List<PosjetilacDTO> sviPosjetioci(){
		List<PosjetilacDTO> retVal=new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select PosjetilacID, Ime, Prezime from posjetilac "
				+ " order by Prezime asc " ;
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next())
				retVal.add(new PosjetilacDTO(rs.getInt(1),rs.getString(2), rs.getString(3)));
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(rs, ps, conn);
		}
		return retVal;
	}
	
	@Override
	public boolean dodajPosjetioca(PosjetilacDTO p) {
		
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO posjetilac VALUES "
				+ " (?, ?, ? ) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, p.getPosjetilacID());
			ps.setString(2, p.getIme());
			ps.setString(3, p.getPrezime());

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
	public boolean obrisiPosjetioca(String ime, String prezime) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM posjetilac "
				+ "WHERE Ime = ? "
				+ " and prezime = ?";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, ime);
			ps.setString(2, prezime);

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
