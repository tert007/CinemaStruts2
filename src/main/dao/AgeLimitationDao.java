package main.dao;

import main.entity.film.AgeLimitation;

import java.util.List;

/**
 * Created by Alexander on 02.05.2016.
 */
public interface AgeLimitationDao {
    AgeLimitation findAgeLimitationById(int id) throws DaoException;
    int findIdByAgeLimitationValue(AgeLimitation ageLimitation) throws DaoException;
    List<AgeLimitation> getAgeLimitationsCollection() throws DaoException;
}
