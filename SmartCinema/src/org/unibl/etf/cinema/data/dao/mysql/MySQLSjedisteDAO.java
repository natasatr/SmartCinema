package org.unibl.etf.cinema.data.dao.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.cinema.data.dao.SjedisteDAO;
import org.unibl.etf.cinema.data.dto.SjedisteDTO;
import org.unibl.etf.cinema.data.dto.KinoDTO;
import org.unibl.etf.cinema.data.dto.SalaDTO;
import org.unibl.etf.cinema.data.dto.AdresaDTO;
import org.unibl.etf.cinema.data.dto.VrstaSjedistaDTO;
import org.unibl.etf.cinema.util.ConnectionPool;
import org.unibl.etf.cinema.util.DBUtil;


public class MySQLSjedisteDAO implements SjedisteDAO{
	@Override
	public List<SjedisteDTO> svaSjedistaUSaliUKinu(int SalaID){
		List<SjedisteDTO> retVal=new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select SjedisteID, sj.Broj, Red, Zauzeto, sj.Uklonjeno, SalaID, s.Broj, "
				+ " Kapacitet, s.Uklonjeno, KinoID, k.Naziv, Email, Telefon, AdresaID, "
				+ " a.Mjesto, a.Ulica, a.Broj, VrstaSjedistaID, v.Naziv, v.Uklonjeno" 
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
				retVal.add(new SjedisteDTO(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),
						new SalaDTO(rs.getInt(6),rs.getInt(7), rs.getInt(8), rs.getInt(9),
						new KinoDTO(rs.getInt(10),rs.getString(11),rs.getString(12),rs.getString(13),
								new AdresaDTO(rs.getInt(14),rs.getString(15), rs.getString(16), rs.getInt(17)))),
						new VrstaSjedistaDTO(rs.getInt(18),rs.getString(19), rs.getInt(20))));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(rs, ps, conn);
		}
		return retVal;
		
	}
	
	@Override
	public SjedisteDTO sjediste(int SjedisteID) {
		SjedisteDTO retVal = new SjedisteDTO();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select SjedisteID, sj.Broj, Red, Zauzeto, sj.Uklonjeno, SalaID, s.Broj, "
				+ " Kapacitet, s.Uklonjeno, KinoID, k.Naziv, Email, Telefon, AdresaID, "
				+ " a.Mjesto, a.Ulica, a.Broj, VrstaSjedistaID, v.Naziv, v.Uklonjeno" 
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
				retVal=new SjedisteDTO(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),
						new SalaDTO(rs.getInt(6),rs.getInt(7), rs.getInt(8), rs.getInt(9),
						new KinoDTO(rs.getInt(10),rs.getString(11),rs.getString(12),rs.getString(13),
								new AdresaDTO(rs.getInt(14),rs.getString(15), rs.getString(16), rs.getInt(17)))),
						new VrstaSjedistaDTO(rs.getInt(18),rs.getString(19), rs.getInt(20)));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(rs, ps, conn);
		}
		return retVal;
	}
	
	
	
	@Override
	public boolean dodajSjedisteUSaluUKinu(SjedisteDTO sjediste) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO sjediste (Broj, Red, Zauzeto, Uklonjeno, SALA_SalaID, VRSTA_SJEDISTA_VrstaSjedistaID)"
				+ "  VALUES "
				+ " ( ?, ?, ?, ?, ?, ? )";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, sjediste.getBroj());
			ps.setInt(2, sjediste.getRed());
			ps.setInt(3, 0);
			ps.setInt(4, 0);
			ps.setInt(5, sjediste.getSala().getSalaID());
			ps.setInt(6, sjediste.getVrstaSjedista().getVrstaSjedistaID());
			

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
	public boolean azurirajSjedisteUSaliUKinu(SjedisteDTO sjediste){
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
			
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(ps, conn);
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
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close( cs, conn);
		}
		return retVal;
	}
}
