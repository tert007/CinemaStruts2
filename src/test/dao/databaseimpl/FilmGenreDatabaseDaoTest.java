package test.dao.databaseimpl;

import main.dao.DaoFactory;
import main.dao.FilmDao;
import main.dao.FilmGenreDao;
import main.dao.databaseimpl.FilmDatabaseDao;
import main.dao.databaseimpl.FilmGenreDatabaseDao;
import main.entity.film.Film;
import main.entity.film.FilmGenre;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Vadim on 01.06.2016.
 */
public class FilmGenreDatabaseDaoTest {
    private FilmGenreDatabaseDao filmGenreDao = FilmGenreDatabaseDao.getInstance();
    private FilmGenre filmGenre = FilmGenre.DRAMA;

    @Test
    public void getInstance() throws Exception {
        assertTrue(filmGenreDao instanceof FilmGenreDao);
    }

    @Test
    public void findFilmGenreById() throws Exception {
        FilmGenre buff = filmGenreDao.findFilmGenreById(1);
        assertTrue(!buff.equals(null));
    }

    @Test
    public void findIdByFilmGenreValue() throws Exception {
        int id = filmGenreDao.findIdByFilmGenreValue(filmGenre);
        assertTrue(id > 0);
    }

    @Test
    public void getFilmGenresCollection() throws Exception {
        List<FilmGenre> filmGenres = filmGenreDao.getFilmGenresCollection();
        assertTrue(filmGenres.size() > 0);
    }
}