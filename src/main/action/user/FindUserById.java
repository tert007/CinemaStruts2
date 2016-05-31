package main.action.user;

import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.ticket.Ticket;
import main.entity.user.User;

import java.util.List;

/**
 * Created by Vadim on 30.04.2016.
 */
public class FindUserById extends ActionSupport {

    private String user_id;
    private User user;
    private List<Ticket> tickets;

    @Override
    public String execute() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {

            int userId = Integer.parseInt(user_id);

            user = daoFactory.getUserDao().findUserById(userId);
            tickets = daoFactory.getTicketDao().findTicketsByUserId(user.getId());

            if (user == null){
                addActionError("Нет такого пользователя");
                return ERROR;
            } else {
                return SUCCESS;
            }
        } catch (DaoException e){
            return ERROR;
        }
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

}
