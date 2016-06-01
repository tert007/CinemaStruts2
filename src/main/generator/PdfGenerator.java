package main.generator;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;
import main.entity.film.Film;

import java.io.IOException;
import com.itextpdf.text.DocumentException;
import main.entity.hall.Hall;
import main.entity.seance.Seance;
import main.entity.ticket.Ticket;
import main.entity.user.User;

import java.io.OutputStream;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.List;

public class PdfGenerator {
    private static final PdfGenerator instance = new PdfGenerator();
    private String backGroundImage;
    public void setImage(String image){
        backGroundImage = image;
    }

    public static PdfGenerator getInstance(){
        return instance;
    }
    BaseFont baseFont;
    private PdfGenerator(){
        try{
            baseFont = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
        }catch (DocumentException e){

        }catch (IOException e){

        }

    }

    public void generateFilm(Film film, OutputStream outputStream) throws DocumentException{
        Document doc = new Document(PageSize.A4,50,50,50,50);
        PdfWriter pdfWriter = PdfWriter.getInstance(doc, outputStream);
        doc.open();

        Paragraph title = new Paragraph("Film\nDate " + film.getDateByString(), FontFactory.getFont(FontFactory.HELVETICA,
                18, Font.ITALIC, new CMYKColor(0, 255, 255,17)));
        title.setAlignment(Element.ALIGN_CENTER);

        doc.add(title);
        Font font = new Font(baseFont,11,Font.BOLD);
        doc.add(Chunk.NEWLINE);

            doc.add(new Chunk("Film #", font));
            doc.add(new Phrase(String.valueOf(film.getTitle()), new Font(baseFont, 10, Font.UNDERLINE)));
            doc.add(Chunk.NEWLINE);
            doc.add(Chunk.NEWLINE);

            doc.add(new Chunk("Description: ", font));
            doc.add(new Phrase(film.getDescription(), new Font(baseFont, 10, Font.UNDERLINE)));
            doc.add(Chunk.NEWLINE);
            doc.add(Chunk.NEWLINE);

            doc.add(new Chunk("Director: ", font));
            doc.add(new Phrase(film.getDirector(), new Font(baseFont, 10, Font.UNDERLINE)));
            doc.add(Chunk.NEWLINE);
            doc.add(Chunk.NEWLINE);

            doc.add(new Chunk("Date: ", font));
            doc.add(new Phrase(film.getDateByString(), new Font(baseFont, 10, Font.UNDERLINE)));
            doc.add(Chunk.NEWLINE);
            doc.add(Chunk.NEWLINE);

            doc.add(new Chunk("Age Limitation: ", font));
            doc.add(new Phrase(film.getAgeLimitation().toString(), new Font(baseFont, 10, Font.UNDERLINE)));
            doc.add(Chunk.NEWLINE);
            doc.add(Chunk.NEWLINE);

            doc.add(Chunk.NEWLINE);
            doc.add(Chunk.NEWLINE);

        doc.close();
    }

    public void generateTicket(Ticket ticket, OutputStream outputStream) throws DocumentException{
        Document doc = new Document(PageSize.A4,50,50,50,50);
        PdfWriter pdfWriter = PdfWriter.getInstance(doc, outputStream);
        doc.open();

        Paragraph title = new Paragraph("Ticket\nDate " + ticket.getSeance().getDateByString(), FontFactory.getFont(FontFactory.HELVETICA,
                18, Font.ITALIC, new CMYKColor(0, 255, 255,17)));
        title.setAlignment(Element.ALIGN_CENTER);

        doc.add(title);
        Font font = new Font(baseFont,11,Font.BOLD);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Ticket #", font));
        doc.add(new Phrase(String.valueOf(ticket.getId()), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Ticket Film: ", font));
        doc.add(new Phrase(ticket.getFilm().getTitle(), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Hall: ", font));
        doc.add(new Phrase(String.valueOf(ticket.getHall().getId()), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Date: ", font));
        doc.add(new Phrase(ticket.getSeance().getDateByString(), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Time : ", font));
        doc.add(new Phrase(ticket.getSeance().getTimeByString(), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Price : ", font));
        doc.add(new Phrase(String.valueOf(ticket.getPrice()), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.close();
    }

    public void generateUser(User user, OutputStream outputStream) throws DocumentException{
        Document doc = new Document(PageSize.A4,50,50,50,50);
        PdfWriter pdfWriter = PdfWriter.getInstance(doc, outputStream);
        doc.open();

        Paragraph title = new Paragraph("User\nLogin " + user.getLogin(), FontFactory.getFont(FontFactory.HELVETICA,
                18, Font.ITALIC, new CMYKColor(0, 255, 255,17)));
        title.setAlignment(Element.ALIGN_CENTER);

        doc.add(title);
        Font font = new Font(baseFont,11,Font.BOLD);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("User #", font));
        doc.add(new Phrase(String.valueOf(user.getId()), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("User Login: ", font));
        doc.add(new Phrase(user.getLogin(), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("User email: ", font));
        doc.add(new Phrase(user.getEmail(), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("User type: ", font));
        doc.add(new Phrase(user.getUserType().toString(), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Bonus count : ", font));
        doc.add(new Phrase(String.valueOf(user.getBonusCount()), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.close();
    }

    public void generateFilm(Seance seance, OutputStream outputStream) throws DocumentException{
        Document doc = new Document(PageSize.A4,50,50,50,50);
        PdfWriter pdfWriter = PdfWriter.getInstance(doc, outputStream);
        doc.open();

        Paragraph title = new Paragraph("Seance\nDate " + seance.getDateByString(), FontFactory.getFont(FontFactory.HELVETICA,
                18, Font.ITALIC, new CMYKColor(0, 255, 255,17)));
        title.setAlignment(Element.ALIGN_CENTER);

        doc.add(title);
        Font font = new Font(baseFont,11,Font.BOLD);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Seance #", font));
        doc.add(new Phrase(String.valueOf(seance.getId()), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Seance Date: ", font));
        doc.add(new Phrase(seance.getDate().toString(), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Seance Time: ", font));
        doc.add(new Phrase(seance.getTimeByString(), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Seance Film title: ", font));
        doc.add(new Phrase(seance.getFilm().getTitle(), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Seance Film description : ", font));
        doc.add(new Phrase(seance.getFilm().getDescription(), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Seance price : ", font));
        doc.add(new Phrase(String.valueOf(seance.getPrice()), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.close();
    }

    public void generateHall(Hall hall, OutputStream outputStream) throws DocumentException{
        Document doc = new Document(PageSize.A4,50,50,50,50);
        PdfWriter pdfWriter = PdfWriter.getInstance(doc, outputStream);
        doc.open();

        Paragraph title = new Paragraph("Hall\nDate " + new Date(), FontFactory.getFont(FontFactory.HELVETICA,
                18, Font.ITALIC, new CMYKColor(0, 255, 255,17)));
        title.setAlignment(Element.ALIGN_CENTER);

        doc.add(title);
        Font font = new Font(baseFont,11,Font.BOLD);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Hall #", font));
        doc.add(new Phrase(String.valueOf(hall.getId()), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Hall capacity: ", font));
        doc.add(new Phrase(String.valueOf(hall.getCapacity()), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Seance Time: ", font));
        Date date = new Date();
        doc.add(new Phrase(date.toString(), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.close();
    }



}
