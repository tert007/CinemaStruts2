package main.controller.commandimpl;

import main.controller.Command;
import main.controller.CommandException;
import main.controller.PageHelper;
import main.controller.PageName;
import main.dao.DaoFactory;
import main.entity.seance.Seance;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Vadim on 07.05.2016.
 */
public class GetSeancesByDate implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            String startDate = request.getParameter("start_date");
            String finishDate = request.getParameter("finish_date");

            Date startDay = dateFormat.parse(startDate);
            Date finishDay = dateFormat.parse(finishDate);

            List<Seance> seances = daoFactory.getSeanceDao().findSeancesByDate(startDay, finishDay);
            request.setAttribute("seance",seances);
            return PageHelper.getPage(PageName.SEANCES_PAGE);
        }catch (Exception ex){
            throw new CommandException(ex);
        }
    }
}
