package test.action.film;

import com.opensymphony.xwork2.ActionSupport;
import main.action.film.AddNewFilm;
import main.dao.DaoFactory;
import main.entity.film.AgeLimitation;
import main.entity.film.Film;
import main.entity.film.FilmGenre;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Vadim on 01.06.2016.
 */
public class AddNewFilmTest {

    @Test
    public void execute() throws Exception {
        Film film = new Film();
        film.setTitle("test");
        film.setDescription("test");
        film.setDirector("test");
        film.setDate(new Date());
        film.setAgeLimitation(AgeLimitation.PG13);
        film.setGenre(FilmGenre.COMEDY);
        int filmId = DaoFactory.getDaoFactory().getFilmDao().addNewFilm(film);
        assertTrue(filmId > 0);
    }
}