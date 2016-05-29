package main.controller.commandimpl;

import main.controller.Command;
import main.controller.CommandException;
import main.controller.PageHelper;
import main.controller.PageName;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetUsersCollection implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {
            List<User> users = daoFactory.getUserDao().getUsersCollection();
            request.setAttribute("users", users);

            String page = PageHelper.getPage(PageName.USERS_PAGE);
            return page;
        } catch (DaoException e){
            throw new CommandException(e);
        }


    }
}
