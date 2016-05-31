package main.action.seance;

import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.film.Film;
import main.entity.hall.Hall;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Alexander on 27.05.2016.
 */
public class ShowAddNewSeance extends ActionSupport {

    private List<Hall> halls;
    private List<Film> films;

    @Override
    public String execute() throws Exception {

        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {
            halls = daoFactory.getHallDao().getHallsCollection();
            films = daoFactory.getFilmDao().getFilmsCollection();

            return SUCCESS;
        } catch (DaoException e) {
            return ERROR;
        }
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public void setHalls(List<Hall> halls) {
        this.halls = halls;
    }

}
