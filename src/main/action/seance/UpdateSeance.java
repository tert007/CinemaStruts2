package main.action.seance;

import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.seance.Seance;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vadim on 07.05.2016.
 */
public class UpdateSeance extends ActionSupport {

    private String seance_id;
    private String film_id;
    private String hall_id;
    private String price;
    private String date;
    private String time;

    private Seance seance;

    @Override
    public String execute() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

            long unixTime = dateFormat.parse(date).getTime() + timeFormat.parse(time).getTime();

            int seanceId = Integer.parseInt(seance_id);
            int filmId = Integer.parseInt(film_id);
            int hallId = Integer.parseInt(hall_id);

            seance = daoFactory.getSeanceDao().findSeanceById(seanceId);

            seance.setFilm(daoFactory.getFilmDao().findFilmById(filmId));
            seance.setHall(daoFactory.getHallDao().findHallById(hallId));
            seance.setDate(unixTime);
            seance.setPrice(Integer.parseInt(price));

            daoFactory.getSeanceDao().updateSeance(seance);

            addActionMessage("Данные успешно обновлены");
            return SUCCESS;
        } catch (ParseException e) {
            addActionError("Ошибка");
            return SUCCESS;
        } catch (DaoException e){
            return ERROR;
        }
    }

    public String getSeance_id() {
        return seance_id;
    }

    public void setSeance_id(String seance_id) {
        this.seance_id = seance_id;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

}
