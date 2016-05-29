package main.entity.hall;

/**
 * Created by Alexander on 02.04.2016.
 */
public class Hall {
    private int id;
    private int capacity;

    public Hall(){
    }

    public Hall(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
