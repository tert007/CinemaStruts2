package main.action.user;

import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.user.User;
import main.entity.user.UserType;

/**
 * Created by Alexander on 22.05.2016.
 */
public class AddNewUser extends ActionSupport {

    private String login;
    private String password;
    private String email;
    private String user_type;
    private String bonus_count;

    private User user;

    @Override
    public String execute() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {
            UserType userType = UserType.valueOf(user_type);
            int bonusCount = Integer.parseInt(bonus_count);

            if (!validateParams(login, password, email)){
                addActionError("Введены некорректные данные");

                return ERROR;
            }

            if (daoFactory.getUserDao().findUser(login) == null) {
                User user = new User();

                user.setLogin(login);
                user.setPassword(password); // Доработать шифрование
                user.setEmail(email);
                user.setUserType(userType);
                user.setBonusCount(bonusCount);

                daoFactory.getUserDao().addUser(user);

                this.user = user;
                addActionMessage("Пользователь успешно создан");
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
