package main.action.user;

import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.ticket.Ticket;
import main.entity.user.User;
import java.util.List;

/**
 * Created by Vadim on 01.05.2016.
 */
public class RemoveUser extends ActionSupport {

    private String user_id;
    private List<User> users;

    @Override
    public String execute() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {
            int userId = Integer.valueOf(user_id);

            if (daoFactory.getUserDao().removeUser(userId)) {
                users = daoFactory.getUserDao().getUsersCollection();

                List<Ticket> tickets = daoFactory.getTicketDao().findTicketsByUserId(userId);
                for (Ticket ticket : tickets) {
                    daoFactory.getTicketDao().removeTicketById(ticket.getId());
                }

                return SUCCESS;
            } else {
                return ERROR;
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


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
