package test.dao.action.ticket;

import main.dao.TicketDao;
import main.dao.databaseimpl.FilmDatabaseDao;
import main.dao.databaseimpl.SeanceDatabaseDao;
import main.dao.databaseimpl.TicketDatabaseDao;
import main.dao.databaseimpl.UserDatabaseDao;
import main.entity.film.AgeLimitation;
import main.entity.film.Film;
import main.entity.film.FilmGenre;
import main.entity.hall.Hall;
import main.entity.seance.Seance;
import main.entity.ticket.Ticket;
import main.entity.user.User;
import main.entity.user.UserType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Alexander on 01.06.2016.
 */
public class FindTicketByIdTest {
    private TicketDatabaseDao ticketDatabaseDao = TicketDatabaseDao.getInstance();
    private Ticket ticket = new Ticket();
    private int ticketId;

    private Film film = new Film();
    private int filmId;

    private Seance seance = new Seance();
    private int seanceId;

    private Hall hall = new Hall();

    private User user = new User();
    private int userId;

    private int place = 1;


    @Before
    public void setUp() throws Exception {
        user.setLogin("test");
        user.setPassword("test");
        user.setEmail("test");
        user.setUserType(UserType.USER);
        user.setBonusCount(1000);

        userId = UserDatabaseDao.getInstance().addUser(user);
        user.setId(userId);

        hall.setId(1);
        hall.setCapacity(125);

        film.setAgeLimitation(AgeLimitation.PG18);
        film.setDescription("test");
        film.setDirector("test");
        film.setTitle("test");
        film.setDate(new Date());
        film.setGenre(FilmGenre.DRAMA);

        filmId = FilmDatabaseDao.getInstance().addNewFilm(film);
        film.setId(filmId);


        seance.setFilm(film);
        seance.setDate(new Date().getTime());
        seance.setPrice(1000);
        seance.setHall(hall);

        seanceId = SeanceDatabaseDao.getInstance().addNewSeance(seance);
        seance.setId(seanceId);

        ticket.setPlace(place);
        ticket.setSeance(seance);
        ticket.setUser(user);

        ticketId = ticketDatabaseDao.addNewTicket(ticket);
        ticket.setId(ticketId);
    }

    @After
    public void tearDown() throws Exception {
        ticketDatabaseDao.removeTicketById(ticketId);

        SeanceDatabaseDao.getInstance().removeSeanceById(seanceId);
        FilmDatabaseDao.getInstance().removeFilmById(filmId);
        UserDatabaseDao.getInstance().removeUser(userId);
    }

    @Test
    public void testGetInstance() throws Exception {
        assertTrue(ticketDatabaseDao instanceof TicketDao);
    }

    @Test
    public void testFindTicketById() throws Exception {
        assertTrue(ticketDatabaseDao.findTicketById(ticketId).getId() == ticketId);
    }

    @Test
    public void testFindTicketsBySeanceId() throws Exception {
        List<Ticket> tickets = ticketDatabaseDao.findTicketsBySeanceId(seanceId);
        boolean flag = false;

        for (Ticket buf : tickets) {
            if (buf.getId() == ticketId) {
                flag = true;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void testFindTicketsByUserId() throws Exception {
        List<Ticket> tickets = ticketDatabaseDao.findTicketsByUserId(userId);
        boolean flag = false;

        for (Ticket buf : tickets) {
            if (buf.getId() == ticketId) {
                flag = true;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void testAddNewTicket() throws Exception {
        User bufUser = new User();

        bufUser.setLogin("test");
        bufUser.setPassword("test");
        bufUser.setEmail("test");
        bufUser.setUserType(UserType.USER);
        bufUser.setBonusCount(1000);

        int bufUserId = UserDatabaseDao.getInstance().addUser(bufUser);
        bufUser.setId(bufUserId);

        Hall bufHall = new Hall();
        bufHall.setId(1);
        bufHall.setCapacity(125);

        Film bufFilm = new Film();

        bufFilm.setAgeLimitation(AgeLimitation.PG18);
        bufFilm.setDescription("test");
        bufFilm.setDirector("test");
        bufFilm.setTitle("test");
        bufFilm.setDate(new Date());
        bufFilm.setGenre(FilmGenre.DRAMA);

        int bufFilmId = FilmDatabaseDao.getInstance().addNewFilm(bufFilm);
        bufFilm.setId(bufFilmId);

        Seance bufSeance = new Seance();

        bufSeance.setFilm(bufFilm);
        bufSeance.setDate(new Date().getTime());
        bufSeance.setPrice(1000);
        bufSeance.setHall(bufHall);

        int bufSeanceId = SeanceDatabaseDao.getInstance().addNewSeance(bufSeance);
        bufSeance.setId(bufSeanceId);

        Ticket bufTicket = new Ticket();

        bufTicket.setPlace(1000);
        bufTicket.setSeance(bufSeance);
        bufTicket.setUser(bufUser);

        int bufTicketId = ticketDatabaseDao.addNewTicket(bufTicket);
        bufTicket.setId(bufTicketId);

        assertTrue(bufTicketId > 0);

        ticketDatabaseDao.removeTicketById(bufTicketId);

        SeanceDatabaseDao.getInstance().removeSeanceById(bufSeanceId);
        FilmDatabaseDao.getInstance().removeFilmById(bufFilmId);
        UserDatabaseDao.getInstance().removeUser(bufUserId);
    }

    @Test
    public void testRemoveTicketById() throws Exception {
        User bufUser = new User();

        bufUser.setLogin("test");
        bufUser.setPassword("test");
        bufUser.setEmail("test");
        bufUser.setUserType(UserType.USER);
        bufUser.setBonusCount(1000);

        int bufUserId = UserDatabaseDao.getInstance().addUser(bufUser);
        bufUser.setId(bufUserId);

        Hall bufHall = new Hall();
        bufHall.setId(1);
        bufHall.setCapacity(125);

        Film bufFilm = new Film();

        bufFilm.setAgeLimitation(AgeLimitation.PG18);
        bufFilm.setDescription("test");
        bufFilm.setDirector("test");
        bufFilm.setTitle("test");
        bufFilm.setDate(new Date());
        bufFilm.setGenre(FilmGenre.DRAMA);

        int bufFilmId = FilmDatabaseDao.getInstance().addNewFilm(bufFilm);
        bufFilm.setId(bufFilmId);

        Seance bufSeance = new Seance();

        bufSeance.setFilm(bufFilm);
        bufSeance.setDate(new Date().getTime());
        bufSeance.setPrice(1000);
        bufSeance.setHall(bufHall);

        int bufSeanceId = SeanceDatabaseDao.getInstance().addNewSeance(bufSeance);
        bufSeance.setId(bufSeanceId);

        Ticket bufTicket = new Ticket();

        bufTicket.setPlace(1000);
        bufTicket.setSeance(bufSeance);
        bufTicket.setUser(bufUser);

        int bufTicketId = ticketDatabaseDao.addNewTicket(bufTicket);
        bufTicket.setId(bufTicketId);

        boolean flag = false;

        flag = ticketDatabaseDao.removeTicketById(bufTicketId);

        SeanceDatabaseDao.getInstance().removeSeanceById(bufSeanceId);
        FilmDatabaseDao.getInstance().removeFilmById(bufFilmId);
        UserDatabaseDao.getInstance().removeUser(bufUserId);

        assertTrue(flag);
    }

    @Test
    public void testUpdateTicket() throws Exception {
        ticket.setPlace(50);
        ticketDatabaseDao.updateTicket(ticket);

        assertTrue(ticketDatabaseDao.findTicketById(ticketId).getPlace() == 50);
    }
}