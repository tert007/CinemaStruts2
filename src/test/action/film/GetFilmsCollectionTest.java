package test.action.film;

import main.dao.DaoFactory;
import main.entity.film.AgeLimitation;
import main.entity.film.Film;
import main.entity.film.FilmGenre;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Vadim on 01.06.2016.
 */
public class GetFilmsCollectionTest {

    @Test
    public void execute() throws Exception {
        List<Film> filmsFirst = DaoFactory.getDaoFactory().getFilmDao().getFilmsCollection();

        Film filmBuff = new Film();
        filmBuff.setTitle("test");
        filmBuff.setDescription("test");
        filmBuff.setDirector("test");
        filmBuff.setDate(new Date(1231232));
        filmBuff.setAgeLimitation(AgeLimitation.PG13);
        filmBuff.setGenre(FilmGenre.COMEDY);
        DaoFactory.getDaoFactory().getFilmDao().addNewFilm(filmBuff);

        List<Film> filmsSecond = DaoFactory.getDaoFactory().getFilmDao().getFilmsCollection();
        assertTrue(filmsSecond.size() - filmsFirst.size() == 1);
    }
}