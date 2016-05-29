package main.controller.commandimpl;

import main.controller.Command;
import main.controller.CommandException;
import main.controller.PageHelper;
import main.controller.PageName;
import main.dao.DaoException;
import main.dao.DaoFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Vadim on 07.05.2016.
 */
public class GetTicketsCollection implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            daoFactory.getTicketDao().findTicketById(id);
        } catch (DaoException e) {
            e.printStackTrace();
        }


        return PageHelper.getPage(PageName.MAIN_PAGE);
    }
}
