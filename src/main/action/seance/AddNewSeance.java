package main.action.seance;

import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.film.Film;
import main.entity.hall.Hall;
import main.entity.seance.Seance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Vadim on 07.05.2016.
 */
public class AddNewSeance extends ActionSupport {

    private String film_id;
    private String hall_id;
    private String price;
    private String date;
    private String time;

    private Seance seance;
    private List<Hall> halls;
    private List<Film> films;

    @Override
    public String execute() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {
            int filmId = Integer.parseInt(film_id);
            int hallId = Integer.parseInt(hall_id);

            Film film = daoFactory.getFilmDao().findFilmById(filmId);
            Hall hall = daoFactory.getHallDao().findHallById(hallId);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:ss");

            long unixDate = dateFormat.parse(date).getTime() + timeFormat.parse(time).getTime();

            seance = new Seance();

            seance.setHall(hall);
            seance.setFilm(film);
            seance.setDate(unixDate);
            seance.setPrice(Integer.parseInt(price));

            daoFactory.getSeanceDao().addNewSeance(seance);

            halls = daoFactory.getHallDao().getHallsCollection();
            films = daoFactory.getFilmDao().getFilmsCollection();

            addActionMessage("Данные успешно добавлены!");
            return SUCCESS;
        } catch (ParseException e){
            addActionMessage("Ошибка!");
            return ERROR;
        } catch (DaoException e) {
            return ERROR;
        }
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFilm_id() {
        return film_id;
    }

    public void setFilm_id(String film_id) {
        this.film_id = film_id;
    }

    public String getHall_id() {
        return hall_id;
    }

    public void setHall_id(String hall_id) {
        this.hall_id = hall_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
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
}
