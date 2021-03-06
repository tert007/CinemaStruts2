package main.action.ticket;

import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.ticket.Ticket;
import main.entity.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Alexander on 25.05.2016.
 */
public class UpdateTicket extends ActionSupport {

    private String ticket_id;

    private String place;
    private Ticket ticket;
    private List<Integer> busyPlaces;

    @Override
    public String execute() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {
            int ticketId = Integer.parseInt(ticket_id);

            ticket = daoFactory.getTicketDao().findTicketById(ticketId);
            ticket.setPlace(Integer.valueOf(place));

            daoFactory.getTicketDao().updateTicket(ticket);

            busyPlaces = daoFactory.getSeanceDao().getBusyPlaces(ticket.getSeance().getId());

            addActionMessage("Данные успешно обновлены");
            return SUCCESS;
        } catch (DaoException e){
            return ERROR;
        }
    }


    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public List<Integer> getBusyPlaces() {
        return busyPlaces;
    }

    public void setBusyPlaces(List<Integer> busyPlaces) {
        this.busyPlaces = busyPlaces;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
