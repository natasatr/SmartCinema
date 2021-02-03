package org.unibl.etf.cinema.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.cinema.data.PosjetilacDataAccess;
import org.unibl.etf.cinema.entity.Posjetilac;



public class PosjetilacDataAccessImpl implements PosjetilacDataAccess {
	@Override
	public List<Posjetilac> sviPosjetioci(){
		List<Posjetilac> retVal=new ArrayList<>();
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
				retVal.add(new Posjetilac(rs.getInt(1),rs.getString(2), rs.getString(3)));
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
	public boolean dodajPosjetioca(Posjetilac p) {
		
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
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps);
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
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps);
		}
		return retVal;
	}
	
	public static void main(String args[]) {
		PosjetilacDataAccessImpl po=new PosjetilacDataAccessImpl();
		List<Posjetilac> pos=po.sviPosjetioci();
		
		for(Posjetilac p:pos)
			System.out.println(p);
		
		//Posjetilac pp=new Posjetilac(5, "Marija", "Jekic");
		System.out.print(po.obrisiPosjetioca("Marija", "Jekic"));
	}
	}


