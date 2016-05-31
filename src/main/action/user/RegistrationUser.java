package main.action.user;

import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.user.User;
import main.entity.user.UserType;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Vadim on 30.04.2016.
 */
public class RegistrationUser extends ActionSupport {

    private String login;
    private String password;
    private String email;

    @Override
    public String execute() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        HttpServletRequest request = ServletActionContext.getRequest();
        try {
            if (!validateParams(login, password, email)){
                addActionError("Введены некорректные данные");

                return ERROR;
            }

            if (daoFactory.getUserDao().findUser(login) == null) {

                User user = new User();

                user.setLogin(login);
                user.setPassword(password);
                user.setEmail(email);
                user.setUserType(UserType.USER);

                daoFactory.getUserDao().addUser(user);

                request.getSession().setAttribute("user", user);

                return SUCCESS;
            } else {
                addActionError("Логин " + login + " уже занят");
                return ERROR;
            }
        } catch (DaoException e){
            return ERROR;
        }
    }

    private boolean validateParams(String login, String password, String email) {
        if (login.isEmpty() || password.isEmpty() || email.isEmpty()) {
            return false;
        }

        return true;
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
}
