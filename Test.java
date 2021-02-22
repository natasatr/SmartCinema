import java.util.List;

import org.unibl.etf.cinema.data.dao.mysql.MySQLFilmDAO;
import org.unibl.etf.cinema.data.dto.FilmDTO;



public class Test {
	public static void main(String[] args)
	{
		MySQLFilmDAO filmDAO = new MySQLFilmDAO();
		FilmDTO film = new FilmDTO(3, "asdfg","3h", 2024, "Milovan", "brzi i zestoki", "DA", "2056", "Kaca, Natasa","Akcija, komedija");
		filmDAO.insert(film);
		

		
		
		
		
	}

}
