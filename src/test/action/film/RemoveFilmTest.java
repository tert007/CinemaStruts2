package test.action.film;

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
public class RemoveFilmTest {

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
        assertTrue(DaoFactory.getDaoFactory().getFilmDao().removeFilmById(film.getId()));
    }
}