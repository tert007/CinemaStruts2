package main.generator;

import com.itextpdf.text.DocumentException;
import main.entity.film.Film;
import main.entity.hall.Hall;
import main.entity.seance.Seance;
import main.entity.ticket.Ticket;
import main.entity.user.User;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CsvGenerator {
    private static CsvGenerator instance = new CsvGenerator();

    public static CsvGenerator getInstance(){
        return instance;
    }

    private CsvGenerator(){
    }


    private static CellProcessor[] getProcessorsForFilm() {
        final CellProcessor[] processors = new CellProcessor[] {
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new FmtDate("dd.MM.yyyy")
        };

        return processors;
    }

    public void generateFilm(Film film, OutputStream outputStream) throws DocumentException, IOException {
        ICsvListWriter listWriter = null;
        listWriter = new CsvListWriter(new OutputStreamWriter(outputStream), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
        final String[] header = {"Id","Title","Description","Genre","Director","AgeLimitation","Date"};
        final List<Object> filmData = new ArrayList<Object>() {{
            add(film.getId());
            add(film.getTitle());
            add(film.getDescription());
            add(film.getGenre().toString());
            add(film.getDirector());
            add(film.getAgeLimitation().toString());
            add(film.getDate());
        }};

        final CellProcessor[] processors = getProcessorsForFilm();
        listWriter.writeHeader(header);
        listWriter.write(filmData, processors);

        if (listWriter != null) {
            listWriter.close();
        }
    }

    private static CellProcessor[] getProcessorsForTicket() {
        final CellProcessor[] processors = new CellProcessor[] {
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new NotNull()
        };

        return processors;
    }

    public void generateTicket(Ticket ticket, OutputStream outputStream) throws DocumentException, IOException {
        ICsvListWriter listWriter = null;
        listWriter = new CsvListWriter(new OutputStreamWriter(outputStream), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
        final String[] header = {"Date","Place","Time","UserLogin","UserEmail","Price","FilmTitle"};
        final List<Object> ticketData = new ArrayList<Object>() {{
            add(ticket.getDateByString());
            add(ticket.getPlace());
            add(ticket.getTimeByString());
            add(ticket.getUser().getLogin());
            add(ticket.getUser().getEmail());
            add(ticket.getPrice());
            add(ticket.getFilm().getGenre().toString());
        }};

        final CellProcessor[] processors = getProcessorsForTicket();
        listWriter.writeHeader(header);
        listWriter.write(ticketData, processors);

        if (listWriter != null) {
            listWriter.close();
        }
    }

    private static CellProcessor[] getProcessorsForSeance() {
        final CellProcessor[] processors = new CellProcessor[] {
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new NotNull()
        };

        return processors;
    }

    public void generateSeance(Seance seance, OutputStream outputStream) throws DocumentException, IOException {
        ICsvListWriter listWriter = null;
        listWriter = new CsvListWriter(new OutputStreamWriter(outputStream), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
        final String[] header = {"HallNumber","FilmTitle","FilmDescription","Date","Time","Price"};
        final List<Object> seanceData = new ArrayList<Object>() {{
            add(seance.getHall().getId());
            add(seance.getFilm().getTitle());
            add(seance.getFilm().getDescription());
            add(seance.getDateByString());
            add(seance.getTimeByString());
            add(seance.getPrice());
        }};

        final CellProcessor[] processors = getProcessorsForSeance();
        listWriter.writeHeader(header);
        listWriter.write(seanceData, processors);

        if (listWriter != null) {
            listWriter.close();
        }
    }

    private static CellProcessor[] getProcessorsForUser() {
        final CellProcessor[] processors = new CellProcessor[] {
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new NotNull()
        };

        return processors;
    }

    public void generateUser(User user, OutputStream outputStream) throws DocumentException, IOException {
        ICsvListWriter listWriter = null;
        listWriter = new CsvListWriter(new OutputStreamWriter(outputStream), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
        final String[] header = {"Id","Login","Email","BonusCount","UserType"};
        final List<Object> userData = new ArrayList<Object>() {{
            add(user.getId());
            add(user.getLogin());
            add(user.getEmail());
            add(user.getBonusCount());
            add(user.getUserType().toString());
        }};

        final CellProcessor[] processors = getProcessorsForUser();
        listWriter.writeHeader(header);
        listWriter.write(userData, processors);

        if (listWriter != null) {
            listWriter.close();
        }
    }

    private static CellProcessor[] getProcessorsForHall() {
        final CellProcessor[] processors = new CellProcessor[] {
                new NotNull(),
                new NotNull(),
                new NotNull()
        };

        return processors;
    }

    public void generateHall(Hall hall, OutputStream outputStream) throws DocumentException, IOException {
        ICsvListWriter listWriter = null;
        listWriter = new CsvListWriter(new OutputStreamWriter(outputStream), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
        final String[] header = {"Number","Capacity","CurDate"};
        final List<Object> userData = new ArrayList<Object>() {{
            add(hall.getId());
            add(hall.getCapacity());
            add(new Date());
        }};

        final CellProcessor[] processors = getProcessorsForUser();
        listWriter.writeHeader(header);
        listWriter.write(userData, processors);

        if (listWriter != null) {
            listWriter.close();
        }
    }



}
