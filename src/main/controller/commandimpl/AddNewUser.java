package main.controller.commandimpl;

import main.controller.Command;
import main.controller.CommandException;
import main.controller.PageHelper;
import main.controller.PageName;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.user.User;
import main.entity.user.UserType;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Alexander on 22.05.2016.
 */
public class AddNewUser implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            UserType userType = UserType.valueOf(request.getParameter("user_type"));
            String email = request.getParameter("email");
            int bonus = Integer.parseInt(request.getParameter("bonus_count"));

            if (!validateParams(login, password, email)){
                String errorMessage = "Введены некорректные данные";

                request.setAttribute("errorMessage", errorMessage);
                return PageHelper.getPage(PageName.CREATE_NEW_USER);
            }

            if (daoFactory.getUserDao().findUser(login) == null) {
                User user = new User();

                user.setLogin(login);
                user.setPassword(password); // Доработать шифрование
                user.setEmail(email);
                user.setUserType(userType);
                user.setBonusCount(bonus);
                daoFactory.getUserDao().addUser(user);
                Command users = new GetUsersCollection();
                return users.execute(request);
            } else {
                String errorMessage = "Данный логин уже занят";

                request.setAttribute("errorMessage", errorMessage);

                return PageHelper.getPage(PageName.CREATE_NEW_USER);
            }
        } catch (DaoException e){
            throw new CommandException(e);
        }
    }

    private boolean validateParams(String login, String password, String email) {
        if (login.isEmpty() || password.isEmpty() || email.isEmpty()) {
            return false;
        }

        return true;
    }
}
