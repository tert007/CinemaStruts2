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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vadim on 01.05.2016.
 */
public class UpdateFilm implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        String statusMessage;
        try {
            int filmId = Integer.parseInt(request.getParameter("film_id"));

            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String director = request.getParameter("director");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date date = simpleDateFormat.parse(request.getParameter("date"));

            AgeLimitation ageLimitation = AgeLimitation.valueOf(request.getParameter("age_limitation"));
            FilmGenre filmGenre = FilmGenre.valueOf(request.getParameter("genre"));

            Film film = new Film();

            film.setId(filmId);
            film.setTitle(title);
            film.setDescription(description);
            film.setGenre(filmGenre);
            film.setDate(date);

            film.setDirector(director);
            film.setAgeLimitation(ageLimitation);

            daoFactory.getFilmDao().updateFilm(film);
            request.setAttribute("film", film);

            statusMessage = "Данные успешно обновлены";
            request.setAttribute("statusMessage", statusMessage);

            return PageHelper.getPage(PageName.FILM_BY_ID_PAGE);
        } catch (IllegalArgumentException  e) {
            statusMessage = "Ошибка в типах-перечислителях";
            request.setAttribute("statusMessage", statusMessage);
            return PageHelper.getPage(PageName.FILM_BY_ID_PAGE);
        } catch (ParseException  e){
            statusMessage = "Ошибка в дате";
            request.setAttribute("statusMessage", statusMessage);
            return PageHelper.getPage(PageName.FILM_BY_ID_PAGE);
        } catch (DaoException e){
            throw new CommandException(e);
        }
    }
}
