package org.unibl.etf.cinema.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.cinema.data.KinoDataAccess;
import org.unibl.etf.cinema.entity.Adresa;
import org.unibl.etf.cinema.entity.Kino;



public class KinoDataAccessImpl implements KinoDataAccess{
	
	@Override
	public List<Kino> svaKina(){
		List<Kino> retVal=new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select KinoID, k.Naziv, Email, Telefon, AdresaID, a.Naziv, Broj, a.Uklonjeno, k.Uklonjeno from kino k"
				+ " inner join adresa a on a.AdresaID=k.ADRESA_AdresaID  "
				+ " order by k.Naziv asc " ;
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Kino(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),
						new Adresa(rs.getInt(5),rs.getString(6),rs.getInt(7),rs.getInt(8)),rs.getInt(9)));
			
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
	public Kino kino(String naziv) {
		Kino retVal = new Kino();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select KinoID, k.Naziv, Email, Telefon, AdresaID, a.Naziv, Broj, a.Uklonjeno, k.Uklonjeno from kino k\"\r\n" + 
				"				+ \" inner join adresa a on a.AdresaID=k.ADRESA_AdresaID  " + 
				" where k.Naziv like ?";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1,
					MySQLUtilities.getInstance().preparePattern(naziv));
			rs = ps.executeQuery();

			
				retVal=new Kino(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),
						new Adresa(rs.getInt(5),rs.getString(6),rs.getInt(7),rs.getInt(8)),rs.getInt(9));
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps, rs);
		}
		return retVal;
	}
	
	@Override // radi na duplicate key, false je jer se ne doda, nego azurira
	public boolean dodajKino(Kino kino) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO kino VALUES "
				+ " (?, ?, ?, ?, ? ,? ) on duplicate key update Uklonjeno=0 ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, kino.getKinoID());
			ps.setString(2, kino.getNaziv());
			ps.setString(3, kino.getEmail());
			ps.setString(4, kino.getTelefon());
			ps.setInt(5, kino.getAdresa().AdresaID);
			ps.setBoolean(6, false);

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
	public boolean azurirajKino(Kino kino) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE kino SET "
				+ "Naziv =? ,"
				+ "Email = ? ,"
				+ "Telefon = ? "
				+ "WHERE Naziv=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, kino.getNaziv());
			ps.setString(2, kino.getEmail());
			ps.setString(3, kino.getTelefon());
			ps.setString(4, kino.getNaziv());

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
	public boolean obrisiKino(String naziv) {
			boolean retVal = false;
			Connection conn = null;
			PreparedStatement ps = null;

			String query = " update kino set Uklonjeno=1"
					+ " where Naziv = ? ";
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
		KinoDataAccessImpl kino=new KinoDataAccessImpl();
		//List<Kino> kina=kino.svaKina();
		
		/*for(Kino k:kina)
			System.out.println(k);*/
		Adresa a1=new Adresa(6,"Vase Pelagica",26, 0);
		Kino kino5=new Kino(5, "Kino 5", "kino5555555@gmail.com", "265656",a1 , 0);
		System.out.print(kino.dodajKino(kino5));
		
		
	}
	}
	
