package main.dao;

import main.entity.film.FilmGenre;

import java.util.List;

/**
 * Created by Alexander on 01.05.2016.
 */
public interface FilmGenreDao {
    FilmGenre findFilmGenreById(int id) throws DaoException;
    int findIdByFilmGenreValue(FilmGenre filmGenre) throws DaoException;
    List<FilmGenre> getFilmGenresCollection() throws DaoException;
}
