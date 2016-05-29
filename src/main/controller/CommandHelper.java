package main.controller;

import main.controller.commandimpl.*;
import main.entity.user.UserType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexander on 23.02.2016.
 */
public class CommandHelper {
    private static final Map<CommandName, Command> commands = new HashMap<CommandName, Command>();
    private static final Map<CommandName, UserType> commandsAvailableStatus = new HashMap<>();

    static {
        //User
        commands.put(CommandName.LOGIN_USER, new LoginUser());
        commandsAvailableStatus.put(CommandName.LOGIN_USER, UserType.GUEST);

        commands.put(CommandName.LOGOUT_USER, new LogoutUser());
        commandsAvailableStatus.put(CommandName.LOGOUT_USER, UserType.USER);

        commands.put(CommandName.REGISTRATION_USER, new RegistrationUser());
        commandsAvailableStatus.put(CommandName.REGISTRATION_USER, UserType.GUEST);

        commands.put(CommandName.FIND_USER_BY_ID, new FindUserById());
        commandsAvailableStatus.put(CommandName.FIND_USER_BY_ID, UserType.GUEST);

        commands.put(CommandName.FIND_USER_BY_LOGIN, new FindUserByLogin());
        commandsAvailableStatus.put(CommandName.FIND_USER_BY_LOGIN, UserType.GUEST);

        commands.put(CommandName.ADD_NEW_USER, new AddNewUser());
        commandsAvailableStatus.put(CommandName.ADD_NEW_USER, UserType.ADMIN);

        commands.put(CommandName.UPDATE_USER, new UpdateUser());
        commandsAvailableStatus.put(CommandName.UPDATE_USER, UserType.ADMIN);

        commands.put(CommandName.REMOVE_USER, new RemoveUser());
        commandsAvailableStatus.put(CommandName.REMOVE_USER, UserType.ADMIN);

        commands.put(CommandName.GET_USERS_COLLECTION, new GetUsersCollection());
        commandsAvailableStatus.put(CommandName.GET_USERS_COLLECTION, UserType.ADMIN);

        //Seances
        commands.put(CommandName.ADD_NEW_SEANCE,new AddNewSeance());
        commandsAvailableStatus.put(CommandName.ADD_NEW_SEANCE, UserType.ADMIN);

        commands.put(CommandName.SHOW_ADD_NEW_SEANCE, new ShowAddNewSeance());
        commandsAvailableStatus.put(CommandName.SHOW_ADD_NEW_SEANCE, UserType.ADMIN);

        commands.put(CommandName.UPDATE_SEANCE, new UpdateSeance());
        commandsAvailableStatus.put(CommandName.UPDATE_SEANCE, UserType.ADMIN);

        commands.put(CommandName.REMOVE_SEANCE, new RemoveSeance());
        commandsAvailableStatus.put(CommandName.REMOVE_SEANCE, UserType.ADMIN);

        commands.put(CommandName.SHOW_SEANCE_SITS, new ShowSeanceSits());
        commandsAvailableStatus.put(CommandName.SHOW_SEANCE_SITS, UserType.GUEST);

        commands.put(CommandName.FIND_SEANCE_BY_ID, new FindSeanceById());
        commandsAvailableStatus.put(CommandName.FIND_SEANCE_BY_ID, UserType.ADMIN);

        commands.put(CommandName.FIND_SEANCES_BY_FILM, new FindSeancesByFilm());
        commandsAvailableStatus.put(CommandName.FIND_SEANCES_BY_FILM, UserType.GUEST);

        commands.put(CommandName.GET_TODAY_SEANCES,new GetTodaySeances());
        commandsAvailableStatus.put(CommandName.GET_TODAY_SEANCES, UserType.GUEST);

        commands.put(CommandName.GET_SEANCES_BY_DATE, new GetSeancesByDate());
        commandsAvailableStatus.put(CommandName.GET_SEANCES_BY_DATE, UserType.GUEST);

        //Film
        commands.put(CommandName.ADD_NEW_FILM, new AddNewFilm());
        commandsAvailableStatus.put(CommandName.ADD_NEW_FILM, UserType.ADMIN);

        commands.put(CommandName.UPDATE_FILM, new UpdateFilm());
        commandsAvailableStatus.put(CommandName.UPDATE_FILM, UserType.ADMIN);

        commands.put(CommandName.REMOVE_FILM, new RemoveFilm());
        commandsAvailableStatus.put(CommandName.REMOVE_FILM, UserType.ADMIN);

        commands.put(CommandName.FIND_FILM_BY_ID, new FindFilmById());
        commandsAvailableStatus.put(CommandName.FIND_FILM_BY_ID, UserType.ADMIN);

        commands.put(CommandName.FIND_FILM_BY_TITLE, new FindFilmByTitle());
        commandsAvailableStatus.put(CommandName.FIND_FILM_BY_TITLE, UserType.GUEST);

        commands.put(CommandName.FIND_FILM_BY_DATE, new FindFilmsByDate());
        commandsAvailableStatus.put(CommandName.FIND_FILM_BY_DATE, UserType.GUEST);

        commands.put(CommandName.GET_FILMS_COLLECTION, new GetFilmsCollection());
        commandsAvailableStatus.put(CommandName.GET_FILMS_COLLECTION, UserType.GUEST);


        //Ticket
        commands.put(CommandName.BUY_TICKET, new BuyTicket());
        commandsAvailableStatus.put(CommandName.BUY_TICKET, UserType.USER);

        commands.put(CommandName.FIND_TICKET_BY_ID, new ShowSeanceSits());
        commandsAvailableStatus.put(CommandName.FIND_TICKET_BY_ID, UserType.ADMIN);

        commands.put(CommandName.UPDATE_TICKET, new UpdateTicket());
        commandsAvailableStatus.put(CommandName.UPDATE_TICKET, UserType.ADMIN);

        commands.put(CommandName.REMOVE_TICKET, new RemoveTicket());
        commandsAvailableStatus.put(CommandName.REMOVE_TICKET, UserType.ADMIN);

    }

    public static UserType getUserTypeCommand(CommandName commandName){
        return commandsAvailableStatus.get(commandName);
    }

    public static Command getCommand(CommandName commandName){
        return commands.get(commandName);
    }
}
