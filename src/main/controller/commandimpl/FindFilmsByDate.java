package main.controller.commandimpl;

import main.controller.Command;
import main.controller.CommandException;
import main.controller.PageHelper;
import main.controller.PageName;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.film.Film;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

/**
 * Created by Vadim on 04.04.2016.
 */
public class FindFilmsByDate implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        String date = request.getParameter("date");

        try {
            List<Film> films = daoFactory.getFilmDao().findFilmsByDate(Date.valueOf(date));
            request.setAttribute("films", films);

            return PageHelper.getPage(PageName.FILMS_BY_DATE_PAGE);
        } catch (DaoException e){
            throw new CommandException(e);
        }
    }
}
