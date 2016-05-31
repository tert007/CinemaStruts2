package main.action.film;

import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.film.Film;
import main.entity.seance.Seance;
import main.entity.ticket.Ticket;

import java.util.List;


/**
 * Created by Vadim on 01.05.2016.
 */
public class RemoveFilm extends ActionSupport {

    private String film_id;
    private List<Film> films;

    @Override
    public String execute() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {
            int filmId = Integer.parseInt(film_id);

            Film film = daoFactory.getFilmDao().findFilmById(filmId);

            List<Seance> seances = daoFactory.getSeanceDao().findSeancesByFilm(film);
            for (Seance seance : seances) {
                List<Ticket> tickets = daoFactory.getTicketDao().findTicketsBySeanceId(seance.getId());
                for (Ticket ticket : tickets) {
                    daoFactory.getTicketDao().removeTicketById(ticket.getId());
                }

                daoFactory.getSeanceDao().removeSeanceById(seance.getId());
            }

            daoFactory.getFilmDao().removeFilmById(filmId);

            films = daoFactory.getFilmDao().getFilmsCollection();

            return SUCCESS;
        } catch (DaoException e){
            return ERROR;
        }
    }

    public String getFilm_id() {
        return film_id;
    }

    public void setFilm_id(String film_id) {
        this.film_id = film_id;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
