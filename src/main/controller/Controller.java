package main.controller;

import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.user.User;
import main.entity.user.UserType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;

/**
 * Created by Alexander on 16.02.2016.
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("command");
        CommandName commandName = CommandName.valueOf(action.toUpperCase());
        UserType userTypeCommand = CommandHelper.getUserTypeCommand(commandName);

        Command command = null;

        User user = (User) request.getSession().getAttribute("user");
        UserType userType;

        if (user == null){

            userType = UserType.GUEST;
        } else {
            try {
                userType = DaoFactory.getDaoFactory().getUserDao().findUserById(user.getId()).getUserType();
            } catch (DaoException e) {
                userType = UserType.GUEST;
            }
        }

        if (userType == UserType.ADMIN) {
            command = CommandHelper.getCommand(commandName);
        }

        if (userType == UserType.USER) {
            if (userTypeCommand != UserType.ADMIN){
                command = CommandHelper.getCommand(commandName);
            }
        }

        if (userType == UserType.GUEST) {
            if (userTypeCommand == UserType.GUEST){
                command = CommandHelper.getCommand(commandName);
            }
        }

        String page;

        try {
            if (command == null){
                page = PageHelper.getPage(PageName.MAIN_PAGE);
            } else {
                page = command.execute(request);
            }
        } catch (CommandException e) {
            request.setAttribute("error", e);
            page = "/error.jsp";
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

}

