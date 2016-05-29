package main.controller.commandimpl;

import main.controller.Command;
import main.controller.CommandException;
import main.controller.PageHelper;
import main.controller.PageName;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.film.Film;
import main.entity.hall.Hall;
import main.entity.seance.Seance;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Vadim on 07.05.2016.
 */
public class AddNewSeance implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        String statusMessage;
        try {
            Film film = daoFactory.getFilmDao().findFilmById(Integer.parseInt(request.getParameter("film_id")));
            Hall hall = daoFactory.getHallDao().findHallById(Integer.parseInt(request.getParameter("hall_id")));

            int price = Integer.parseInt(request.getParameter("price"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:ss");

            Date date = dateFormat.parse(request.getParameter("date"));
            Date time = timeFormat.parse(request.getParameter("time"));

            long unixTime = date.getTime()+ time.getTime();

            Seance seance = new Seance();

            seance.setHall(hall);
            seance.setFilm(film);
            seance.setDate(unixTime);
            seance.setPrice(price);

            daoFactory.getSeanceDao().addNewSeance(seance);

            List<Hall> halls = daoFactory.getHallDao().getHallsCollection();
            List<Film> films = daoFactory.getFilmDao().getFilmsCollection();

            request.setAttribute("halls", halls);
            request.setAttribute("films", films);
            request.setAttribute("seance", seance);

            statusMessage = "Данные успешно добавлены!";
            request.setAttribute("statusMessage", statusMessage);
            return PageHelper.getPage(PageName.FIND_SEANCE_BY_ID);
        } catch (ParseException e){
            statusMessage = "Ошибка в дате";
            request.setAttribute("statusMessage", statusMessage);
            return PageHelper.getPage(PageName.ADD_NEW_SEANCE);
        } catch (DaoException e) {
            throw new CommandException(e);
        }
    }
}
