package org.unibl.etf.cinema.data.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.cinema.data.SjedisteDataAccess;
import org.unibl.etf.cinema.entity.Sala;
import org.unibl.etf.cinema.entity.Sjediste;
import org.unibl.etf.cinema.entity.VrstaSjedista;
import org.unibl.etf.cinema.entity.Adresa;
import org.unibl.etf.cinema.entity.Kino;




public class SjedisteDataAccessImpl implements SjedisteDataAccess{
	
	@Override
	public List<Sjediste> svaSjedistaUSaliUKinu(int SalaID){
		List<Sjediste> retVal=new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select SjedisteID, sj.Broj, Red, Zauzeto, sj.Uklonjeno, SalaID, s.Broj, "
				+ " Kapacitet, s.Uklonjeno, KinoID, k.Naziv, Email, Telefon, AdresaID, "
				+ " a.Naziv, a.Broj, a.Uklonjeno, k.Uklonjeno, VrstaSjedistaID, v.Naziv, v.Uklonjeno" 
				+ " from sjediste sj "
				+ " inner join sala s on SALA_SalaID=SalaId "
				+ " inner join kino k on KINO_KinoID=KinoID "
				+ " inner join adresa a on ADRESA_AdresaID= AdresaID "
				+ " inner join vrsta_sjedista  v on VRSTA_SJEDISTA_VrstaSjedistaID=VrstaSjedistaID "
				+ " where SALA_SalaID= ? "
				+ " and sj.Uklonjeno= 0 "
				+ " order by SjedisteID asc " ;
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, SalaID);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Sjediste(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),
						new Sala(rs.getInt(6),rs.getInt(7), rs.getInt(8), rs.getInt(9),
						new Kino(rs.getInt(10),rs.getString(11),rs.getString(12),rs.getString(13),
								new Adresa(rs.getInt(14),rs.getString(15),rs.getInt(16),rs.getInt(17)),
								rs.getInt(18))),
						new VrstaSjedista(rs.getInt(19),rs.getString(20), rs.getInt(21))));
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
	public Sjediste sjediste(int SjedisteID) {
		Sjediste retVal = new Sjediste();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select SjedisteID, sj.Broj, Red, Zauzeto, sj.Uklonjeno, SalaID, s.Broj, "
				+ " Kapacitet, s.Uklonjeno, KinoID, k.Naziv, Email, Telefon, AdresaID, "
				+ " a.Naziv, a.Broj, a.Uklonjeno, k.Uklonjeno, VrstaSjedistaID, v.Naziv, v.Uklonjeno" 
				+ " from sjediste sj "
				+ " inner join sala s on SALA_SalaID=SalaId "
				+ " inner join kino k on KINO_KinoID=KinoID "
				+ " inner join adresa a on ADRESA_AdresaID= AdresaID "
				+ " inner join vrsta_sjedista  v on VRSTA_SJEDISTA_VrstaSjedistaID=VrstaSjedistaID "
				+ " where SjedisteID= ? ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, SjedisteID);
			rs = ps.executeQuery();

			if (rs.next())
				retVal=new Sjediste(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),
						new Sala(rs.getInt(6),rs.getInt(7), rs.getInt(8), rs.getInt(9),
						new Kino(rs.getInt(10),rs.getString(11),rs.getString(12),rs.getString(13),
								new Adresa(rs.getInt(14),rs.getString(15),rs.getInt(16),rs.getInt(17)),
								rs.getInt(18))),
						new VrstaSjedista(rs.getInt(19),rs.getString(20), rs.getInt(21)));
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
	public boolean dodajSjedisteUSaluUKinu(Sjediste sjediste) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO sjediste VALUES "
				+ " (?, ?, ?, ?, ?, ?, ? ) on duplicate key update Uklonjeno=values(Uklonjeno) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, sjediste.getSjedisteID());
			ps.setInt(2, sjediste.getBroj());
			ps.setInt(3, sjediste.getRed());
			ps.setInt(4, 0);
			ps.setInt(5, 0);
			ps.setInt(6, sjediste.getSala().getSalaID());
			ps.setInt(7, sjediste.getVrstaSjedista().getVrstaSjedistaID());
			

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
	public boolean azurirajSjedisteUSaliUKinu(Sjediste sjediste){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE sjediste  SET"
				+ " Zauzeto = ? ,"
				+ " VRSTA_SJEDISTA_VrstaSjedistaID =? "
				+ " WHERE SjedisteID=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, sjediste.isZauzeto());
			ps.setInt(2, sjediste.getVrstaSjedista().getVrstaSjedistaID());
			ps.setInt(3, sjediste.getSjedisteID());

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

	
	/* ovom procedurom se postavlja Uklonjeno=1 za sjediste
	 * i umanjuje se kapacitet sale za 1 iz koje je to sjedise
	 */
	@Override
	public boolean obrisiSjedisteIzSaleKina(int broj, int red, int SalaID) {
		boolean retVal = false;
		Connection conn = null;
		CallableStatement cs = null;

		String query = "{CALL smanji_kapacitet_sale(?, ?, ?)}";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);
			cs.setInt(1, broj);
			cs.setInt(2, red);
			cs.setInt(3, SalaID);

			cs.execute();
			retVal = cs.getBoolean(5);
			if (!retVal)
				MySQLUtilities.getInstance().showErrorMessage(cs.getString(6));
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(cs);
		}
		return retVal;
	}
	public static void main(String args[]) {
		//SjedisteDataAccessImpl sj=new SjedisteDataAccessImpl();
		//List<Sjediste> sjed=sj.svaSjedistaUSaliUKinu(1);
		
		/*for(Sjediste s:sjed)
			System.out.println(s);*/
		
		
		
		
		
	}

}
