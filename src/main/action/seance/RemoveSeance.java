package main.action.seance;

import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoException;
import main.dao.DaoFactory;
import main.entity.seance.Seance;
import main.entity.ticket.Ticket;

import java.util.List;

/**
 * Created by Vadim on 07.05.2016.
 */
public class RemoveSeance extends ActionSupport {

    private String seance_id;
    private List<Seance> seances;

    @Override
    public String execute() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        try {
            int seanceId = Integer.parseInt(seance_id);
            daoFactory.getSeanceDao().removeSeanceById(seanceId);

            List<Ticket> tickets = daoFactory.getTicketDao().findTicketsBySeanceId(seanceId);
            for (Ticket ticket : tickets) {
                daoFactory.getTicketDao().removeTicketById(ticket.getId());
            }

            seances = daoFactory.getSeanceDao().getTodaySeances();

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

    public List<Seance> getSeances() {
        return seances;
    }

    public void setSeances(List<Seance> seances) {
        this.seances = seances;
    }
}
