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
 * Created by Alexander on 25.05.2016.
 */
public class UpdateTicket implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {
            int ticketId = Integer.parseInt(request.getParameter("ticket_id"));
            int place = Integer.parseInt(request.getParameter("place"));

            Ticket ticket = daoFactory.getTicketDao().findTicketById(ticketId);
            ticket.setPlace(place);
            daoFactory.getTicketDao().updateTicket(ticket);

            List<Ticket> tickets = daoFactory.getTicketDao().findTicketsByUserId(ticket.getUser().getId());

            request.setAttribute("user", ticket.getUser());
            request.setAttribute("tickets", tickets);

            return PageHelper.getPage(PageName.USER_PROFILE);
        } catch (DaoException e){
            throw new CommandException(e);
        }
    }
}
