package main.controller.commandimpl;

import main.controller.Command;
import main.controller.CommandException;
import main.controller.PageHelper;
import main.controller.PageName;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.ticket.Ticket;
import main.entity.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Vadim on 30.04.2016.
 */
public class FindUserById implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {
            int id = Integer.parseInt(request.getParameter("user_id"));
            User user = daoFactory.getUserDao().findUserById(id);

            if (user == null){
                return PageHelper.getPage(PageName.ERROR_REG_PAGE);

            } else {
                List<Ticket> tickets = daoFactory.getTicketDao().findTicketsByUserId(user.getId());

                request.setAttribute("user", user);
                request.setAttribute("tickets", tickets);
            }

            return PageHelper.getPage(PageName.USER_PROFILE);
        } catch (DaoException e){
            throw new CommandException(e);
        }

    }
}
