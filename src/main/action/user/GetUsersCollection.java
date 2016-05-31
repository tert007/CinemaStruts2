package main.action.user;

import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetUsersCollection extends ActionSupport {

    private List<User> users;

    @Override
    public String execute() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {

            users = daoFactory.getUserDao().getUsersCollection();

            return SUCCESS;
        } catch (DaoException e){
            return ERROR;
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
