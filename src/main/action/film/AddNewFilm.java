package main.action.film;

import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.film.AgeLimitation;
import main.entity.film.Film;
import main.entity.film.FilmGenre;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alexander on 27.04.2016.
 */
public class AddNewFilm extends ActionSupport {

    private String title;
    private String description;
    private String director;
    private String date;
    private String age_limitation;
    private String genre;

    private Film film;

    @Override
    public String execute() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

            long unixDate = simpleDateFormat.parse(date).getTime();

            AgeLimitation ageLimitation = AgeLimitation.valueOf(age_limitation);
            FilmGenre filmGenre = FilmGenre.valueOf(genre);

            film = new Film();

            film.setTitle(title);
            film.setDescription(description);
            film.setGenre(filmGenre);
            film.setDate(new Date(unixDate));
            film.setDirector(director);
            film.setAgeLimitation(ageLimitation);

            daoFactory.getFilmDao().addNewFilm(film);
            addActionMessage("Фильм добавлен!");
            return SUCCESS;
        } catch (IllegalArgumentException  e) {
            addActionError("Ошибка в типах-перечислителях");
            return SUCCESS;
        } catch (ParseException  e){
            addActionError("Ошибка в дате");
            return SUCCESS;
        } catch (DaoException e){
            return ERROR;
        }
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAge_limitation() {
        return age_limitation;
    }

    public void setAge_limitation(String age_limitation) {
        this.age_limitation = age_limitation;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
