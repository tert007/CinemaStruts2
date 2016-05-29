package main.controller.commandimpl;

import main.controller.Command;
import main.controller.CommandException;
import main.controller.PageHelper;
import main.controller.PageName;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.film.Film;
import main.entity.hall.Hall;
import main.entity.seance.Seance;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Alexander on 27.05.2016.
 */
public class ShowAddNewSeance implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {
            List<Hall> halls = daoFactory.getHallDao().getHallsCollection();
            List<Film> films = daoFactory.getFilmDao().getFilmsCollection();

            request.setAttribute("halls", halls);
            request.setAttribute("films", films);

            return PageHelper.getPage(PageName.ADD_NEW_SEANCE);
        } catch (DaoException e) {
            throw new CommandException(e);
        }
    }
}
