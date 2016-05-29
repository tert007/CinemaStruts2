package main.controller.commandimpl;

import main.controller.Command;
import main.controller.CommandException;
import main.controller.PageHelper;
import main.controller.PageName;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.film.AgeLimitation;
import main.entity.film.Film;
import main.entity.film.FilmGenre;


import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.IllegalFormatException;

/**
 * Created by Alexander on 27.04.2016.
 */
public class AddNewFilm implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        String statusMessage;
        try {
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String director = request.getParameter("director");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

            String date = request.getParameter("date");
            long unixDate = simpleDateFormat.parse(date).getTime();

            AgeLimitation ageLimitation = AgeLimitation.valueOf(request.getParameter("age_limitation"));
            FilmGenre filmGenre = FilmGenre.valueOf(request.getParameter("genre"));

            Film film = new Film();

            film.setTitle(title);
            film.setDescription(description);
            film.setGenre(filmGenre);
            film.setDate(new Date(unixDate));
            film.setDirector(director);
            film.setAgeLimitation(ageLimitation);

            daoFactory.getFilmDao().addNewFilm(film);
            request.setAttribute("film", film);

            statusMessage = "Данные успешно добалены";
            request.setAttribute("statusMessage", statusMessage);

            return PageHelper.getPage(PageName.FILM_BY_ID_PAGE);
        } catch (IllegalArgumentException  e) {
            statusMessage = "Ошибка в типах-перечислителях";
            request.setAttribute("statusMessage", statusMessage);
            return PageHelper.getPage(PageName.NEW_FILM_PAGE);
        } catch (ParseException  e){
            statusMessage = "Ошибка в дате";
            request.setAttribute("statusMessage", statusMessage);
            return PageHelper.getPage(PageName.NEW_FILM_PAGE);
        } catch (DaoException e){
            throw new CommandException(e);
        }
    }
}
