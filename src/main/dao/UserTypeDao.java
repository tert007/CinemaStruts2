package main.dao;

import main.entity.user.UserType;

import java.util.List;

/**
 * Created by Alexander on 01.05.2016.
 */
public interface UserTypeDao {
    UserType findUserTypeById(int id) throws DaoException;
    int findIdByUserTypeValue(UserType userType) throws DaoException;
    List<UserType> getUserTypesCollection() throws DaoException;
}
