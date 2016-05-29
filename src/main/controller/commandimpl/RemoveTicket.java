package main.controller.commandimpl;

import main.controller.Command;
import main.controller.CommandException;
import main.controller.PageHelper;
import main.controller.PageName;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.ticket.Ticket;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Vadim on 07.05.2016.
 */
public class RemoveTicket implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {
            int ticketId = Integer.valueOf(request.getParameter("ticket_id"));
            request.removeAttribute("ticket_id");

            Ticket ticket = daoFactory.getTicketDao().findTicketById(ticketId);
            daoFactory.getTicketDao().removeTicketById(ticketId);

            request.setAttribute("user_id", ticket.getUser().getId());

            List<Ticket> tickets = daoFactory.getTicketDao().findTicketsByUserId(ticket.getUser().getId());

            request.setAttribute("user", ticket.getUser());
            request.setAttribute("tickets", tickets);

            return PageHelper.getPage(PageName.USER_PROFILE);
        } catch (DaoException e){
            throw new CommandException(e);
        }
    }
}
