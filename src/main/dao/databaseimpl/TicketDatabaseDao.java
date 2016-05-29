package main.dao.databaseimpl;

import main.dao.DaoException;
import main.dao.TicketDao;
import main.entity.seance.Seance;
import main.entity.ticket.Ticket;
import main.entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadim on 04.04.2016.
 */
public class TicketDatabaseDao extends Connector implements TicketDao {

    private static final String tableName = "ticket";

    private static final String columnId = "id";
    private static final String columnSeanceId = "id_seance";
    private static final String columnUserId = "id_user";
    private static final String columnPlace = "place";


    private static final String[] columnsName = {
            columnId,
            columnSeanceId,
            columnUserId,
            columnPlace
    };

    private TicketDatabaseDao(){
        super();
    }

    private static final TicketDatabaseDao instance = new TicketDatabaseDao();

    public static TicketDatabaseDao getInstance() {
        return instance;
    }

    private static final String[] getValues(Ticket ticket) throws DaoException{
        String[] result = {
                String.valueOf(ticket.getId()),
                String.valueOf(ticket.getSeance().getId()),
                String.valueOf(ticket.getUser().getId()),
                String.valueOf(ticket.getPlace())
        };

        return result;
    }

    @Override
    public Ticket findTicketById(int id) throws DaoException {
        ResultSet resultSet = null;
        try {
            resultSet = databaseController.select(tableName, columnsName, columnId + "=" + id);
            resultSet.next();

            return createTicketFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Ticket> findTicketsBySeanceId(int seanceId) throws DaoException {
        ResultSet resultSet = null;
        try {
            resultSet = databaseController.select(tableName, columnsName, columnSeanceId + "=" + seanceId);

            return createTicketsCollectionFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Ticket> findTicketsByUserId(int userId) throws DaoException {
        ResultSet resultSet = null;
        try {
            resultSet = databaseController.select(tableName, columnsName, columnUserId + "=" + userId);

            return createTicketsCollectionFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int addNewTicket(Ticket ticket) throws DaoException {
        try {
            return databaseController.insert(tableName, columnsName, getValues(ticket));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean removeTicketById(int id) throws DaoException {
        try {
            return databaseController.remove(tableName, columnId + "=" + id);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateTicket(Ticket newTicket) throws DaoException {
        try {
            return databaseController.update(tableName, columnsName, getValues(newTicket), columnId + "=" + newTicket.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private List<Ticket> createTicketsCollectionFromResultSet(ResultSet resultSet) throws DaoException{
        List<Ticket> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(createTicketFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Ticket createTicketFromResultSet(ResultSet resultSet) throws  DaoException {
        Ticket ticket = new Ticket();
        try {
            ticket.setId(resultSet.getInt(columnId));
            ticket.setPlace(resultSet.getInt(columnPlace));

            int seanceId = resultSet.getInt(columnSeanceId);
            Seance seance = SeanceDatabaseDao.getInstance().findSeanceById(seanceId);
            ticket.setSeance(seance);

            int userId = resultSet.getInt(columnUserId);
            User user = UserDatabaseDao.getInstance().findUserById(userId);

            ticket.setUser(user);

            return ticket;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
