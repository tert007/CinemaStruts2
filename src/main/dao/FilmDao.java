package main.dao;

import main.entity.film.Film;

import java.util.Date;
import java.util.List;

/**
 * Created by Alexander on 02.04.2016.
 */
public interface FilmDao{
    Film findFilmById(int id) throws DaoException;
    List<Film> findFilmsByTitle(String title) throws DaoException;
    List<Film> findFilmsByDate(Date date) throws DaoException;
    List<Film> getFilmsCollection() throws DaoException;

    int addNewFilm(Film film) throws DaoException;
    boolean removeFilmById(int id) throws DaoException;
    boolean updateFilm(Film newFilm) throws DaoException;
}
