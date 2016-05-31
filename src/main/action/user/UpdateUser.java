package main.action.user;

import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.ticket.Ticket;
import main.entity.user.User;
import main.entity.user.UserType;

import java.util.List;

/**
 * Created by Vadim on 30.04.2016.
 */
public class UpdateUser extends ActionSupport {

    private String user_id;

    private String login;
    private String password;
    private String email;
    private String user_type;
    private String bonus_count;

    private User user;
    private List<Ticket> tickets;

    @Override
    public String execute() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {
            daoFactory.getSeanceDao();

            int userId = Integer.parseInt(user_id);

            UserType userType = UserType.valueOf(user_type);
            int bonusCount = Integer.parseInt(bonus_count);

            User user = daoFactory.getUserDao().findUser(login);

            if (user == null || user.getId() == userId) {
                user = new User();

                user.setId(userId);
                user.setLogin(login);
                user.setPassword(password);
                user.setEmail(email);
                user.setBonusCount(bonusCount);
                user.setUserType(userType);

                daoFactory.getUserDao().updateUser(user);

                this.user = user;
                this.tickets = daoFactory.getTicketDao().findTicketsByUserId(userId);

                addActionMessage("Данные успешно добавлены");
                return SUCCESS;
            } else {
                this.user = daoFactory.getUserDao().findUserById(userId);
                this.tickets = daoFactory.getTicketDao().findTicketsByUserId(userId);

                addActionError("Логин " + login + " уже занят");
                return SUCCESS;
            }
        } catch (DaoException e){
            return ERROR;
        }
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getBonus_count() {
        return bonus_count;
    }

    public void setBonus_count(String bonus_count) {
        this.bonus_count = bonus_count;
    }


    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
