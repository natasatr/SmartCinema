package org.unibl.etf.cinema.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.cinema.data.SalaDataAccess;
import org.unibl.etf.cinema.entity.Sala;
import org.unibl.etf.cinema.entity.Adresa;
import org.unibl.etf.cinema.entity.Kino;

public class SalaDataAccessImpl implements SalaDataAccess{
	
	@Override
	public List<Sala> sveSaleUKinu(String nazivKina){
		List<Sala> retVal=new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select SalaID, s.Broj, Kapacitet, s.Uklonjeno, KinoID, k.Naziv, Email, Telefon, AdresaID,"
				+ " a.Naziv, a.Broj, a.Uklonjeno, k.Uklonjeno from sala s"
				+ " inner join kino k on KINO_KinoID=KinoID "
				+ " inner join adresa a on ADRESA_AdresaID=AdresaID "
				+ " where k.Naziv= ? "
				+ " order by SalaID asc " ;
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, nazivKina);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Sala(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getInt(4),
						new Kino(rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8),
								new Adresa(rs.getInt(9),rs.getString(10),rs.getInt(11),rs.getInt(12)),
								rs.getInt(13))));
			
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
	public boolean dodajSalu(Sala sala) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO sala VALUES "
				+ " (?, ?, ?, ?, ? ) on duplicate key update Uklonjeno=0 ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, sala.getSalaID());
			ps.setInt(2, sala.getBroj());
			ps.setInt(3, sala.getKapacitet());
			ps.setInt(4, 0);
			ps.setInt(5, sala.getKino().getKinoID());

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
	public boolean azurirajSalu(Sala sala) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE sala SET "
				+ "Broj =?, "
				+ " Kapacitet= ? "
				+ " WHERE SalaID=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, sala.getBroj());
			ps.setInt(2, sala.getKapacitet());
			ps.setInt(3, sala.getSalaID());

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
	public boolean obrisiSalu(int KinoID, int broj) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = " update sala set Uklonjeno=1 "
				+ " where Broj = ? "
				+ " and KINO_KinoID= ?  ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, broj);
			ps.setInt(2, KinoID);

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
		SalaDataAccessImpl ss=new SalaDataAccessImpl();
		List<Sala> sale=ss.sveSaleUKinu("Kino 1");
		
		for(Sala s:sale)
			System.out.println(s);
		
		
		
	}

}
