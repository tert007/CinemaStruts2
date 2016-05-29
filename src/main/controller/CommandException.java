package main.controller;

/**
 * Created by Alexander on 03.04.2016.
 */
public class CommandException extends Exception {
    public CommandException(Exception e) { super(e);}

    public CommandException(String message){
        super(message);
    }

    public CommandException(String message, Exception e){
        super(message, e);
    }
}
