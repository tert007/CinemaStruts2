package main.entity.ticket;

import main.entity.film.Film;
import main.entity.hall.Hall;
import main.entity.seance.Seance;
import main.entity.user.User;

/**
 * Created by Alexander on 02.04.2016.
 */
public class Ticket {
    private int id;
    private Seance seance;
    private User user;
    private int place;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Seance getSeance() {
        return seance;
    }

    public int getPrice() {
        return seance.getPrice();
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public Hall getHall() {
        return seance.getHall();
    }

    public Film getFilm() {
        return seance.getFilm();
    }

    public String getDateByString() {
        return seance.getDateByString();
    }

    public String getTimeByString() {
        return seance.getTimeByString();
    }
}
