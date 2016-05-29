package main.action.seance;

import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.seance.Seance;

import java.util.List;

/**
 * Created by Alexander on 08.05.2016.
 */
public class GetTodaySeances extends ActionSupport {
    private List<Seance> seances;

    public List<Seance> getSeances() {
        return seances;
    }

    public void setSeances(List<Seance> seances) {
        this.seances = seances;
    }

    @Override
    public String execute() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try{

            setSeances(daoFactory.getSeanceDao().getTodaySeances());

            return SUCCESS;
        } catch (DaoException ex){
            return ERROR;
        }

    }
}
