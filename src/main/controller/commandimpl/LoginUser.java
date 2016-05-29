package main.controller.commandimpl;

import main.controller.Command;
import main.controller.CommandException;
import main.controller.PageHelper;
import main.controller.PageName;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

/**
 * Created by Alexander on 07.05.2016.
 */
public class LoginUser implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try{
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            if (!validateParams(login, password)) {
                String errorMessage = "Введены некорректные данные";

                request.setAttribute("errorMessage", errorMessage);
                return PageHelper.getPage(PageName.SIGN_IN_PAGE);
            }

            // Шифрование пароля
            User user = daoFactory.getUserDao().findUser(login);

            if (user != null && user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                String remember = request.getParameter("remember");

                return PageHelper.getPage(PageName.MAIN_PAGE);
            } else {
                String errorMessage = "Неверный логин или пароль";

                request.setAttribute("errorMessage", errorMessage);
                return PageHelper.getPage(PageName.SIGN_IN_PAGE);
            }
        } catch (DaoException ex){
            throw new CommandException(ex);
        }
    }

    private boolean validateParams(String login, String password) {
       if (login.isEmpty() || password.isEmpty()) {
           return false;
       }

       return true;
    }
}
