package main.controller.commandimpl;

import main.controller.*;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.dao.databaseimpl.UserDatabaseDao;
import main.entity.user.User;
import main.entity.user.UserType;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Vadim on 30.04.2016.
 */
public class UpdateUser implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            int bonus_count = Integer.parseInt(request.getParameter("bonus_count"));
            UserType user_type = UserType.valueOf(request.getParameter("user_type"));
            int id = Integer.parseInt(request.getParameter("user_id"));

            User buff = daoFactory.getUserDao().findUser(login);
            if (buff == null || buff.getId() == id) {
                User user = new User();
                user.setLogin(login);
                user.setPassword(password);
                user.setEmail(email);
                user.setBonusCount(bonus_count);

                user.setUserType(user_type);
                user.setId(id);

                daoFactory.getUserDao().updateUser(user);
                request.setAttribute("user", user);
                Command getUsersCollection = new GetUsersCollection();
                return getUsersCollection.execute(request);
            }
            else{
                request.setAttribute("errorMessage", "Данный логин уже существует");
                Command findUser = new FindUserById();
                return findUser.execute(request);
            }

            }catch (DaoException e){
                throw new CommandException(e);
        }
    }
}
