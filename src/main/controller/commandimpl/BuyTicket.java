package main.controller.commandimpl;

import main.controller.Command;
import main.controller.CommandException;
import main.controller.PageHelper;
import main.controller.PageName;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.seance.Seance;
import main.entity.ticket.Ticket;
import main.entity.user.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Alexander on 22.05.2016.
 */
public class BuyTicket implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {
            int place = Integer.parseInt(request.getParameter("place"));
            int seanceId = Integer.parseInt(request.getParameter("seance_id"));

            Seance seance = daoFactory.getSeanceDao().findSeanceById(seanceId);

            User user = (User)request.getSession().getAttribute("user");

            Ticket ticket = new Ticket();
            ticket.setUser(user);
            ticket.setPlace(place);
            ticket.setSeance(seance);

            daoFactory.getTicketDao().addNewTicket(ticket);

            //daoFactory.getTicketDao().findTicketsByUserId(user.getId());

            //return PageHelper.getPage(PageName.USER_PROFILE);

            return new FindSeanceById().execute(request);
        } catch (DaoException e){
            throw new CommandException(e);
        }
    }
}
