package org.unibl.etf.cinema.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.cinema.data.dao.FilmDAO;
import org.unibl.etf.cinema.data.dto.FilmDTO;
import org.unibl.etf.cinema.util.ConnectionPool;
import org.unibl.etf.cinema.util.DBUtil;



public class MySQLFilmDAO implements FilmDAO{
	private static final String SQL_SELECT_ALL = "SELECT * FROM film";
	private static final String SQL_INSERT = "INSERT INTO film  (FilmID, Naziv, Trajanje, GodinaSnimanja, Reziser, Opis, URepetoaru, DatumPrvogPrikazivanja, Glumci , Zanr) VALUES (?, ?, ?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE film SET Naziv=?, Trajanje=?, GodinaSnimanja=?, Reziser=?, Opis=?, URepertoaru=?, DatiPrvogPrikazivanja=?, ,Glumci =?,  NazivZanra=? WHERE FilmID=?";
	private static final String SQL_DELETE = "DELETE FROM film WHERE FilmID=?";
	private static final String SQL_SEARCH = "SELECT * from film WHERE Naziv=?";

	@Override
	public List<FilmDTO> selectAll() {
		List<FilmDTO> retVal = new ArrayList<FilmDTO>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			rs = s.executeQuery(SQL_SELECT_ALL);

			while (rs.next()) {
				retVal.add(new FilmDTO(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}

		return retVal;
	}

	@Override
	public boolean insert(FilmDTO film) {	
		boolean retVal =false ;
		Connection conn = null;
		PreparedStatement ps = null;

		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(SQL_INSERT);
			ps.setInt(1, film.getFilmID());
			ps.setString(2, film.getNaziv());
			ps.setString(3, film.getTrajanje());
			ps.setInt(4, film.getGodinaSnimanja());
			ps.setString(5, film.getReziser());
			ps.setString(6, film.getOpis());
			ps.setString(7, film.isURepetoaru());
			ps.setString (8,  film.getDatumPrvogPrikazivanja());
			ps.setString(9, film.getGlumci());
			ps.setString(10, film.getZanr());

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
	public int update(FilmDTO film) {
	
		int retVal = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {

			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(SQL_UPDATE);
			ps.setInt(1, film.getFilmID());
			ps.setString(2, film.getNaziv());
			ps.setString(3, film.getTrajanje());
			ps.setInt(4, film.getGodinaSnimanja());
			ps.setString(5, film.getReziser());
			ps.setString(6, film.getOpis());
			ps.setString(7, film.isURepetoaru());
			ps.setString (8,  film.getDatumPrvogPrikazivanja());
			
			ps.setString(9, film.getGlumci());
			ps.setString(10, film.getZanr());
			retVal = ps.executeUpdate() ;
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(ps);
		}
		return retVal;
	}

	
	@Override
	public boolean delete(int i) {
	
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;
		
		try
		{
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(SQL_DELETE);
			ps.setInt(1, i);
			retVal = ps.executeUpdate() == 1;
			
		}catch(SQLException e)
		{
			e.printStackTrace();
	
		}finally
		{
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.close(ps);
		}
		return retVal;
	}

	@Override
	public List<FilmDTO> searchMovie(String name) {
		List<FilmDTO> retVal = new ArrayList<FilmDTO>();
		Connection c = null;
		PreparedStatement  ps = null;
		ResultSet rs = null;
		try {
			c = DBUtil.getConnection();
			ps = c.prepareStatement(SQL_SEARCH);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				retVal.add(new FilmDTO(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}

		return retVal;
		
	}



}
