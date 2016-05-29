package main.controller.commandimpl;

import main.controller.Command;
import main.controller.CommandException;
import main.controller.PageHelper;
import main.controller.PageName;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.seance.Seance;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Vadim on 07.05.2016.
 */
public class RemoveSeance implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        int id = Integer.parseInt(request.getParameter("seance_id"));

        try {
            daoFactory.getSeanceDao().removeSeanceById(id);
            List<Seance> seances = daoFactory.getSeanceDao().getTodaySeances();

            request.setAttribute("seance", seances);
            return PageHelper.getPage(PageName.SEANCES_PAGE);
        } catch (DaoException e) {
            throw new CommandException(e);
        }
    }
}
