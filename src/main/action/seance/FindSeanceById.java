package main.action.seance;

import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.film.Film;
import main.entity.hall.Hall;
import main.entity.seance.Seance;

import java.util.List;

/**
 * Created by Vadim on 07.05.2016.
 */
public class FindSeanceById extends ActionSupport {

    private String seance_id;

    private Seance seance;
    private List<Hall> halls;
    private List<Film> films;

    @Override
    public String execute() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try{

            int seanceId = Integer.parseInt(seance_id);

            seance = daoFactory.getSeanceDao().findSeanceById(seanceId);
            halls = daoFactory.getHallDao().getHallsCollection();
            films = daoFactory.getFilmDao().getFilmsCollection();

            return SUCCESS;
        }
        catch (DaoException e){
            return ERROR;
        }
    }


    public String getSeance_id() {
        return seance_id;
    }

    public void setSeance_id(String seance_id) {
        this.seance_id = seance_id;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public void setHalls(List<Hall> halls) {
        this.halls = halls;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
