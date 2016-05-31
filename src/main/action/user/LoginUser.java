package main.action.user;

import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.user.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Alexander on 07.05.2016.
 */
public class LoginUser extends ActionSupport {

    private String login;
    private String password;

    @Override
    public String execute() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        HttpServletRequest request = ServletActionContext.getRequest();
        try{
            if (!validateParams(login, password)) {
                addActionError("Введены некорректные данные");

                return ERROR;
            }

            User user = daoFactory.getUserDao().findUser(login);

            if (user != null && user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                return SUCCESS;
            } else {
                String errorMessage = "Неверный логин или пароль";

                addActionError("Введены некорректные данные");
                return ERROR;
            }
        } catch (DaoException ex){
            return ERROR;
        }
    }

    private boolean validateParams(String login, String password) {
       if (login.isEmpty() || password.isEmpty()) {
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
}
