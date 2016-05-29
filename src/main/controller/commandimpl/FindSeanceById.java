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
 * Created by Vadim on 07.05.2016.
 */
public class FindSeanceById implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try{

            int seanceId = Integer.parseInt(request.getParameter("seance_id"));
            Seance seance = daoFactory.getSeanceDao().findSeanceById(seanceId);

            List<Hall> halls = daoFactory.getHallDao().getHallsCollection();
            List<Film> films = daoFactory.getFilmDao().getFilmsCollection();

            request.setAttribute("seance",seance);
            request.setAttribute("halls", halls);
            request.setAttribute("films", films);

            return PageHelper.getPage(PageName.FIND_SEANCE_BY_ID);
        }
        catch (DaoException ex){
            throw new CommandException(ex);
        }
    }
}
