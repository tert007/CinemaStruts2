package main.action.seance;

import main.controller.Command;
import main.controller.CommandException;
import main.controller.PageHelper;
import main.controller.PageName;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.film.Film;
import main.entity.seance.Seance;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Alexander on 22.05.2016.
 */
public class FindSeancesByFilm implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try{
            int filmId = Integer.parseInt(request.getParameter("film_id"));
            Film film = daoFactory.getFilmDao().findFilmById(filmId);

            if (film == null){
                throw new CommandException("Film id not found");
            }

            List<Seance> seances = daoFactory.getSeanceDao().findSeancesByFilm(film);
            request.setAttribute("seance",seances);

            return PageHelper.getPage(PageName.SEANCES_PAGE);
        }
        catch (DaoException ex){
            throw new CommandException(ex);
        }
    }
}
