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
 * Created by Alexander on 10.05.2016.
 */
public class FindUserByLogin implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {
            String login = request.getParameter("login");
            User user = daoFactory.getUserDao().findUser(login);

            if (user == null){
                request.setAttribute("error_login", login);
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
