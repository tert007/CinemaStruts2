package main.action.seance;

import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.film.Film;
import main.entity.seance.Seance;

import java.util.List;

/**
 * Created by Alexander on 22.05.2016.
 */
public class FindSeancesByFilm extends ActionSupport {

    private String film_id;

    private List<Seance> seances;

    @Override
    public String execute() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try{
            int filmId = Integer.parseInt(film_id);
            Film film = daoFactory.getFilmDao().findFilmById(filmId);

            if (film == null){
                addActionError("Фильм не найден");
                return ERROR;
            }

            seances = daoFactory.getSeanceDao().findSeancesByFilm(film);
            return SUCCESS;
        }
        catch (DaoException e){
            return ERROR;
        }
    }

    public String getFilm_id() {
        return film_id;
    }

    public void setFilm_id(String film_id) {
        this.film_id = film_id;
    }

    public List<Seance> getSeances() {
        return seances;
    }

    public void setSeances(List<Seance> seances) {
        this.seances = seances;
    }

}
