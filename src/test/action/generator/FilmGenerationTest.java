package test.action.generator;

import main.dao.FilmDao;
import main.dao.databaseimpl.FilmDatabaseDao;
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
public class FilmGenerationTest {
    private FilmDatabaseDao filmDatabaseDao = FilmDatabaseDao.getInstance();
    private Film film = new Film();
    private int filmId;
    private Date date = new Date(1464740696);

    @org.junit.Before
    public void setUp() throws Exception {
        AgeLimitation ageLimitation = AgeLimitation.PG13;
        FilmGenre filmGenre = FilmGenre.DRAMA;

        film.setTitle("test");
        film.setDescription("test");
        film.setDirector("test");
        film.setDate(date);
        film.setAgeLimitation(ageLimitation);
        film.setGenre(filmGenre);
        filmId = filmDatabaseDao.addNewFilm(film);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        filmDatabaseDao.removeFilmById(filmId);
    }


    @Test
    public void getFilm_id() throws Exception {
        assertTrue(filmDatabaseDao instanceof FilmDao);
    }

    @Test
    public void setFilm_id() throws Exception {
        List<Film> films = filmDatabaseDao.findFilmsByTitle("test");
        boolean flag = false;
        for (Film buff : films) {
            flag = buff.getId() == (film.getId());
        }
        assertTrue(flag);
    }

    @Test
    public void execute() throws Exception {
        Film buff = filmDatabaseDao.findFilmById(filmId);
        assertTrue(buff.getId() == film.getId());
    }

    @Test
    public void getDoc_type() throws Exception {
        List<Film> filmsFirst = filmDatabaseDao.getFilmsCollection();

        Film filmBuff = new Film();
        filmBuff.setTitle("test");
        filmBuff.setDescription("test");
        filmBuff.setDirector("test");
        filmBuff.setDate(new Date(1231232));
        filmBuff.setAgeLimitation(AgeLimitation.PG13);
        filmBuff.setGenre(FilmGenre.COMEDY);
        filmDatabaseDao.addNewFilm(filmBuff);

        List<Film> filmsSecond = filmDatabaseDao.getFilmsCollection();
        assertTrue(filmsSecond.size() - filmsFirst.size() == 1);
    }

    @Test
    public void setDoc_type() throws Exception {
        Film buff = filmDatabaseDao.findFilmById(film.getId());
        assertTrue(buff != null);
    }
}