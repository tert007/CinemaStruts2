package main.action.seance;

import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoFactory;
import main.entity.seance.Seance;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Vadim on 07.05.2016.
 */
public class GetSeancesByDate extends ActionSupport {

    private String start_day;
    private String finish_day;

    private List<Seance> seances;

    @Override
    public String execute() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try{

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

            Date startDay = dateFormat.parse(start_day);
            Date finishDay = dateFormat.parse(finish_day);

            seances = daoFactory.getSeanceDao().findSeancesByDate(startDay, finishDay);
            return SUCCESS;
        }catch (Exception ex){
            return ERROR;
        }
    }


    public String getStart_day() {
        return start_day;
    }

    public void setStart_day(String start_day) {
        this.start_day = start_day;
    }

    public String getFinish_day() {
        return finish_day;
    }

    public void setFinish_day(String finish_day) {
        this.finish_day = finish_day;
    }

    public List<Seance> getSeances() {
        return seances;
    }

    public void setSeances(List<Seance> seances) {
        this.seances = seances;
    }
}
