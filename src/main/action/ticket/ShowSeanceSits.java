package main.action.ticket;

import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.seance.Seance;
import java.util.List;

/**
 * Created by Vadim on 07.05.2016.
 */
public class ShowSeanceSits extends ActionSupport {

    private String seance_id;

    private Seance seance;
    private List<Integer> busyPlaces;

    @Override
    public String execute() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();

        try {
            int seanceId = Integer.parseInt(seance_id);

            seance = daoFactory.getSeanceDao().findSeanceById(seanceId);
            busyPlaces = daoFactory.getSeanceDao().getBusyPlaces(seanceId);
            return SUCCESS;
        } catch (DaoException e) {
            return ERROR;
        }
    }

    public String getSeance_id() {
        return seance_id;
    }

    public void setSeance_id(String seance_id) {
        this.seance_id = seance_id;
    }

    public List<Integer> getBusyPlaces() {
        return busyPlaces;
    }

    public void setBusyPlaces(List<Integer> busyPlaces) {
        this.busyPlaces = busyPlaces;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

}
