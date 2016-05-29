package main.controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Alexander on 23.02.2016.
 */
public interface Command {
    String execute(HttpServletRequest request) throws CommandException;
}
