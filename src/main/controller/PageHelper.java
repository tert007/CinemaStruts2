package main.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexander on 30.04.2016.
 */
public class PageHelper {
    private static final Map<PageName, String> pages = new HashMap<PageName, String>();

    static
    {
        pages.put(PageName.MAIN_PAGE, "/index.jsp");
        pages.put(PageName.SIGN_IN_PAGE, "/signin.jsp");

        pages.put(PageName.REGISTRATION_PAGE, "/registration.jsp");
        pages.put(PageName.SUCCESS_REGISTRATION_PAGE, "/success_registration.jsp");

        pages.put(PageName.SEANCES_PAGE, "/seance.jsp");
        pages.put(PageName.SHOW_SEANCE_SITS_PAGE,"/show_seance_sits.jsp");

        pages.put(PageName.USER_PROFILE, "/user_profile.jsp");


        pages.put(PageName.USERS_PAGE, "/users.jsp");
        pages.put(PageName.ADD_NEW_SEANCE, "/add_new_seance.jsp");


        pages.put(PageName.ERROR_REG_PAGE, "/error_reg.jsp");
        pages.put(PageName.SUCCESS_UPDATE_PAGE, "/success_update.jsp");
        pages.put(PageName.FIND_SEANCE_BY_ID, "/find_seance_by_id.jsp");

        pages.put(PageName.FILM_BY_ID_PAGE,"/find_film_by_id.jsp");
        pages.put(PageName.FILMS_BY_DATE_PAGE,"/films.jsp");
        pages.put(PageName.FILMS_BY_TITLE_PAGE,"/films.jsp");
        pages.put(PageName.FILMS_PAGE,"/films.jsp");
        pages.put(PageName.NEW_FILM_PAGE,"/add_new_film.jsp");

        pages.put(PageName.ERROR_PAGE, "/error.jsp");
        pages.put(PageName.TICKET_BY_ID, "/ticket_by_id.jsp");
    }

    public static String getPage(PageName pageName){
        return pages.get(pageName);
    }
}
