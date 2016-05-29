package main.controller.commandimpl;

import main.controller.Command;
import main.controller.CommandException;
import main.controller.PageHelper;
import main.controller.PageName;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.film.Film;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Vadim on 04.04.2016.
 */
public class FindFilmById implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        String statusMessage;
        try {
            int filmId = Integer.parseInt(request.getParameter("film_id"));

            Film film = daoFactory.getFilmDao().findFilmById(filmId);
            if (film == null){
                statusMessage = "Фильма с данным id нет";
                request.setAttribute("statusMessage", statusMessage);
                return PageHelper.getPage(PageName.FILM_BY_ID_PAGE);
            }

            request.setAttribute("film", film);
            return PageHelper.getPage(PageName.FILM_BY_ID_PAGE);
        } catch (DaoException e){
            throw new CommandException(e);
        }
    }
}
