package main.action.ticket;

import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.seance.Seance;
import main.entity.ticket.Ticket;
import main.entity.user.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Alexander on 22.05.2016.
 */
public class BuyTicket extends ActionSupport {

    private String seance_id;
    private String place;

    private Seance seance;
    private List<Integer> busyPlaces;
    private User user;
    private List<Ticket> tickets;

    @Override
    public String execute() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        HttpServletRequest request = ServletActionContext.getRequest();
        try {
            int seanceId = Integer.valueOf(seance_id);

            seance = daoFactory.getSeanceDao().findSeanceById(seanceId);
            user = (User)request.getSession().getAttribute("user");

            Ticket ticket = new Ticket();
            ticket.setUser(user);
            ticket.setPlace(Integer.valueOf(place));
            ticket.setSeance(seance);

            daoFactory.getTicketDao().addNewTicket(ticket);

            seance = daoFactory.getSeanceDao().findSeanceById(seanceId);
            busyPlaces = daoFactory.getSeanceDao().getBusyPlaces(seanceId);
            return SUCCESS;
        } catch (DaoException e){
            return ERROR;
        }
    }

    public String getSeance_id() {
        return seance_id;
    }

    public void setSeance_id(String seance_id) {
        this.seance_id = seance_id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public List<Integer> getBusyPlaces() {
        return busyPlaces;
    }

    public void setBusyPlaces(List<Integer> busyPlaces) {
        this.busyPlaces = busyPlaces;
    }


}
