package test.dao.databaseimpl;

import main.dao.DaoFactory;
import main.dao.FilmDao;
import main.dao.databaseimpl.FilmDatabaseDao;
import main.entity.film.AgeLimitation;
import main.entity.film.Film;
import main.entity.film.FilmGenre;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Vadim on 01.06.2016.
 */
public class FilmDatabaseDaoTest {
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

    @org.junit.Test
    public void getInstance() throws Exception {
        assertTrue(filmDatabaseDao instanceof FilmDao);
    }

    @org.junit.Test
    public void findFilmsByTitle() throws Exception {
        List<Film> films = filmDatabaseDao.findFilmsByTitle("test");
        boolean flag = false;
        for (Film buff : films) {
            flag = buff.getId() == (film.getId());
        }
        assertTrue(flag);
    }

    @org.junit.Test
    public void findFilmById() throws Exception {
        Film buff = filmDatabaseDao.findFilmById(filmId);
        assertTrue(buff.getId() == film.getId());
    }

    @org.junit.Test
    public void getFilmsCollection() throws Exception {
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

    @org.junit.Test
    public void addNewFilm() throws Exception {
        Film buff = filmDatabaseDao.findFilmById(film.getId());
        assertTrue(buff != null);
    }

    @org.junit.Test
    public void removeFilmById() throws Exception {
        assertTrue(filmDatabaseDao.removeFilmById(film.getId()));
    }

    @org.junit.Test
    public void updateFilm() throws Exception {
        Film filmBuff = new Film();
        filmBuff.setId(film.getId());
        filmBuff.setTitle("test2");
        filmBuff.setDescription("test2");
        filmBuff.setDirector("test2");
        filmBuff.setDate(new Date(1231232));
        filmBuff.setAgeLimitation(AgeLimitation.PG13);
        filmBuff.setGenre(FilmGenre.COMEDY);
        filmDatabaseDao.updateFilm(filmBuff);

        assertTrue(filmBuff.getTitle() == "test2");
    }
}