package main.entity.seance;

import main.entity.film.Film;
import main.entity.hall.Hall;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alexander on 02.04.2016.
 */
public class Seance {
    private int id;
    private Hall hall;
    private Film film;
    private Date date;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDate(long unixTime) {
        this.date = new Date(unixTime);
    }

    public String getDateByString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(date);
    }

    public String getTimeByString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date);
    }

    public Date getDate(){
        return date;
    }
}
