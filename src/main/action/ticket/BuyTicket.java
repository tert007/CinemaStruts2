package main.action.ticket;

import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoException;
import main.dao.DaoFactory;

/**
 * Created by Alexander on 22.05.2016.
 */
public class BuyTicket extends ActionSupport {
    @Override
    public String execute() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {

/*
            Seance seance = daoFactory.getSeanceDao().findSeanceById(seanceId);

            User user = (User)request.getSession().getAttribute("user");

            Ticket ticket = new Ticket();
            ticket.setUser(user);
            ticket.setPlace(place);
            ticket.setSeance(seance);

            daoFactory.getTicketDao().addNewTicket(ticket);
            */
            daoFactory.getTicketDao().findTicketsByUserId(1);


            return SUCCESS;
        } catch (DaoException e){
            return ERROR;
        }
    }


}
