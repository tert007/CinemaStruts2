package main.action.film;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.bcel.internal.generic.RET;
import com.sun.org.apache.regexp.internal.RE;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.film.Film;

/**
 * Created by Vadim on 04.04.2016.
 */
public class FindFilmById extends ActionSupport {

    private String film_id;

    private Film film;

    @Override
    public String execute() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();

        try {
            int filmId = Integer.parseInt(film_id);

            this.film = daoFactory.getFilmDao().findFilmById(filmId);
            if (this.film == null){
                addActionError("Фильма с данным id нет");
                return SUCCESS;
            }

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

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
