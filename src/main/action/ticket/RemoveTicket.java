package main.action.ticket;

import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.seance.Seance;
import main.entity.ticket.Ticket;
import main.entity.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Vadim on 07.05.2016.
 */
public class RemoveTicket extends ActionSupport {

    private String ticket_id;

    private User user;
    private List<Ticket> tickets;

    @Override
    public String execute() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {
            int ticketId = Integer.valueOf(ticket_id);

            Ticket ticket = daoFactory.getTicketDao().findTicketById(ticketId);

            daoFactory.getTicketDao().removeTicketById(ticketId);

            user = ticket.getUser();
            tickets = daoFactory.getTicketDao().findTicketsByUserId(user.getId());

            return SUCCESS;
        } catch (DaoException e){
            return ERROR;
        }
    }


    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
