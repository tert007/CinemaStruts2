package main.action;

import com.opensymphony.xwork2.*;
import main.action.film.AddNewFilm;
import main.action.film.FindFilmById;
import main.action.film.RemoveFilm;
import main.action.film.UpdateFilm;
import main.action.seance.*;
import main.action.ticket.BuyTicket;
import main.action.ticket.RemoveTicket;
import main.action.ticket.ShowSeanceSits;
import main.action.ticket.UpdateTicket;
import main.action.user.*;
import main.entity.user.UserType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexander on 01.06.2016.
 */
public class ActionHelper {
    private static final Map<ActionName, ActionSupport> commands = new HashMap<>();
    private static final Map<ActionName, UserType> commandsAvailableStatus = new HashMap<>();

    static {
        //User
        commands.put(ActionName.LOGIN_USER, new LoginUser());
        commandsAvailableStatus.put(ActionName.LOGIN_USER, UserType.GUEST);

        commands.put(ActionName.LOGOUT_USER, new LogoutUser());
        commandsAvailableStatus.put(ActionName.LOGOUT_USER, UserType.USER);

        commands.put(ActionName.REGISTRATION_USER, new RegistrationUser());
        commandsAvailableStatus.put(ActionName.REGISTRATION_USER, UserType.GUEST);

        commands.put(ActionName.FIND_USER_BY_ID, new FindUserById());
        commandsAvailableStatus.put(ActionName.FIND_USER_BY_ID, UserType.GUEST);

        commands.put(ActionName.ADD_NEW_USER, new AddNewUser());
        commandsAvailableStatus.put(ActionName.ADD_NEW_USER, UserType.ADMIN);

        commands.put(ActionName.UPDATE_USER, new UpdateUser());
        commandsAvailableStatus.put(ActionName.UPDATE_USER, UserType.ADMIN);

        commands.put(ActionName.REMOVE_USER, new RemoveUser());
        commandsAvailableStatus.put(ActionName.REMOVE_USER, UserType.ADMIN);

        commands.put(ActionName.GET_USERS_COLLECTION, new GetUsersCollection());
        commandsAvailableStatus.put(ActionName.GET_USERS_COLLECTION, UserType.ADMIN);

        //Seances
        commands.put(ActionName.ADD_NEW_SEANCE,new AddNewSeance());
        commandsAvailableStatus.put(ActionName.ADD_NEW_SEANCE, UserType.ADMIN);

        commands.put(ActionName.SHOW_ADD_NEW_SEANCE, new ShowAddNewSeance());
        commandsAvailableStatus.put(ActionName.SHOW_ADD_NEW_SEANCE, UserType.ADMIN);

        commands.put(ActionName.UPDATE_SEANCE, new UpdateSeance());
        commandsAvailableStatus.put(ActionName.UPDATE_SEANCE, UserType.ADMIN);

        commands.put(ActionName.REMOVE_SEANCE, new RemoveSeance());
        commandsAvailableStatus.put(ActionName.REMOVE_SEANCE, UserType.ADMIN);

        commands.put(ActionName.SHOW_SEANCE_SITS, new ShowSeanceSits());
        commandsAvailableStatus.put(ActionName.SHOW_SEANCE_SITS, UserType.GUEST);

        commands.put(ActionName.FIND_SEANCE_BY_ID, new FindSeanceById());
        commandsAvailableStatus.put(ActionName.FIND_SEANCE_BY_ID, UserType.ADMIN);

        commands.put(ActionName.FIND_SEANCES_BY_FILM, new FindSeancesByFilm());
        commandsAvailableStatus.put(ActionName.FIND_SEANCES_BY_FILM, UserType.GUEST);

        commands.put(ActionName.GET_TODAY_SEANCES,new GetTodaySeances());
        commandsAvailableStatus.put(ActionName.GET_TODAY_SEANCES, UserType.GUEST);

        commands.put(ActionName.GET_SEANCES_BY_DATE, new GetSeancesByDate());
        commandsAvailableStatus.put(ActionName.GET_SEANCES_BY_DATE, UserType.GUEST);

        //Film
        commands.put(ActionName.ADD_NEW_FILM, new AddNewFilm());
        commandsAvailableStatus.put(ActionName.ADD_NEW_FILM, UserType.ADMIN);

        commands.put(ActionName.UPDATE_FILM, new UpdateFilm());
        commandsAvailableStatus.put(ActionName.UPDATE_FILM, UserType.ADMIN);

        commands.put(ActionName.REMOVE_FILM, new RemoveFilm());
        commandsAvailableStatus.put(ActionName.REMOVE_FILM, UserType.ADMIN);

        commands.put(ActionName.FIND_FILM_BY_ID, new FindFilmById());
        commandsAvailableStatus.put(ActionName.FIND_FILM_BY_ID, UserType.ADMIN);

        //Ticket
        commands.put(ActionName.BUY_TICKET, new BuyTicket());
        commandsAvailableStatus.put(ActionName.BUY_TICKET, UserType.USER);

        commands.put(ActionName.FIND_TICKET_BY_ID, new ShowSeanceSits());
        commandsAvailableStatus.put(ActionName.FIND_TICKET_BY_ID, UserType.ADMIN);

        commands.put(ActionName.UPDATE_TICKET, new UpdateTicket());
        commandsAvailableStatus.put(ActionName.UPDATE_TICKET, UserType.ADMIN);

        commands.put(ActionName.REMOVE_TICKET, new RemoveTicket());
        commandsAvailableStatus.put(ActionName.REMOVE_TICKET, UserType.ADMIN);

    }

    public static UserType getUserTypeCommand(ActionName actionName){
        return commandsAvailableStatus.get(actionName);
    }

    public static ActionSupport getCommand(ActionName actionName){
        return commands.get(actionName);
    }
}