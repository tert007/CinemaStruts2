package main.controller.commandimpl;

import main.controller.*;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.seance.Seance;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Alexander on 08.05.2016.
 */
public class GetTodaySeances implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try{
            List<Seance> seances = daoFactory.getSeanceDao().getTodaySeances();
            request.setAttribute("seance", seances);
            return PageHelper.getPage(PageName.SEANCES_PAGE);
        }catch (DaoException ex){
            throw new CommandException(ex);
        }

    }
}
