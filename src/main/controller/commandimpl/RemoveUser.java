package main.controller.commandimpl;

import main.controller.Command;
import main.controller.CommandException;
import main.dao.DaoException;
import main.dao.DaoFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Vadim on 01.05.2016.
 */
public class RemoveUser implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {
            int id = Integer.valueOf(request.getParameter("user_id"));
            daoFactory.getUserDao().removeUser(id);

            Command getUsers = new GetUsersCollection();
            return getUsers.execute(request);
        } catch (DaoException e){
            throw new CommandException(e);
        }
    }
}
