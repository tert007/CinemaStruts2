package test.dao.databaseimpl;

import main.dao.DaoFactory;
import main.dao.FilmDao;
import main.dao.SeanceDao;
import main.dao.databaseimpl.SeanceDatabaseDao;
import main.entity.film.AgeLimitation;
import main.entity.film.Film;
import main.entity.film.FilmGenre;
import main.entity.hall.Hall;
import main.entity.seance.Seance;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Vadim on 01.06.2016.
 */
public class SeanceDatabaseDaoTest {
    private SeanceDatabaseDao seanceDatabaseDao = SeanceDatabaseDao.getInstance();
    private Seance seance = new Seance();
    private Film film = new Film();
    private int filmId;
    private Date date = new Date();
    private Hall hall = new Hall();
    private int seanceId;

    @Before
    public void setUp() throws Exception {

        AgeLimitation ageLimitation = AgeLimitation.PG13;
        FilmGenre filmGenre = FilmGenre.DRAMA;

        film.setTitle("test");
        film.setDescription("test");
        film.setDirector("test");
        film.setDate(date);
        film.setAgeLimitation(ageLimitation);
        film.setGenre(filmGenre);
        filmId = DaoFactory.getDaoFactory().getFilmDao().addNewFilm(film);

        hall = DaoFactory.getDaoFactory().getHallDao().findHallById(1);

        seance.setFilm(film);
        seance.setHall(hall);
        seance.setDate(date.getTime());
        seance.setPrice(30000);
        seanceId = seanceDatabaseDao.addNewSeance(seance);
    }

    @After
    public void tearDown() throws Exception {
        seanceDatabaseDao.removeSeanceById(seanceId);
        DaoFactory.getDaoFactory().getFilmDao().removeFilmById(filmId);
    }

    @Test
    public void getInstance() throws Exception {
        assertTrue(seanceDatabaseDao instanceof SeanceDao);
    }

    @Test
    public void findSeanceById() throws Exception {
        Seance buff = seanceDatabaseDao.findSeanceById(seanceId);
        assertTrue(buff.getId() == seanceId);
    }

    @Test
    public void findSeancesByDate() throws Exception {
        List<Seance> seances = seanceDatabaseDao.findSeancesByDate(date,date);
        boolean flag = false;
        for (Seance buff: seances) {
           flag = buff.getId() == seanceId;
        }
        assertTrue(flag);
    }

    @Test
    public void findSeancesByFilm() throws Exception {
        List<Seance> seances = seanceDatabaseDao.findSeancesByFilm(film);
        boolean flag = false;
        for (Seance buff: seances) {
            flag = buff.getId() == seanceId;
        }
        assertTrue(flag);
    }

    @Test
    public void getBusyPlaces() throws Exception {
        List<Integer> places = seanceDatabaseDao.getBusyPlaces(seanceId);
        assertTrue(places.size() == 0);
    }

    @Test
    public void getTodaySeances() throws Exception {
        List<Seance> seances = seanceDatabaseDao.getTodaySeances();
        boolean flag = false;
        for (Seance buff: seances) {
            flag = buff.getId() == seanceId;
        }
        assertTrue(flag);
    }

    @Test
    public void addNewSeance() throws Exception {
        Seance buffSeance = new Seance();
        buffSeance.setFilm(film);
        buffSeance.setHall(hall);
        buffSeance.setDate(date.getTime());
        buffSeance.setPrice(30000);
        int buffSeanceId = seanceDatabaseDao.addNewSeance(seance);
        assertTrue(buffSeanceId > 0);
        seanceDatabaseDao.removeSeanceById(buffSeanceId);
    }

    @Test
    public void removeSeanceById() throws Exception {
        assertTrue(seanceDatabaseDao.removeSeanceById(seanceId));
    }

    @Test
    public void updateSeance() throws Exception {
        Seance newSeance = new Seance();
        newSeance.setFilm(film);
        newSeance.setHall(hall);
        newSeance.setDate(date.getTime());
        newSeance.setPrice(10000);
        newSeance.setId(seanceId);
        assertTrue(seanceDatabaseDao.updateSeance(newSeance));
    }
}