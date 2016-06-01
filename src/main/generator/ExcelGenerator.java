package main.generator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import main.entity.film.Film;
import main.entity.hall.Hall;
import main.entity.seance.Seance;
import main.entity.ticket.Ticket;
import main.entity.user.User;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;


public class ExcelGenerator {
    private static final ExcelGenerator instance = new ExcelGenerator();

    public static ExcelGenerator getInstance(){
        return instance;
    }

    private ExcelGenerator(){

    }

    public void generateFilm(Film film, OutputStream outputStream) throws DocumentException, IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Film " + film.getTitle());
        int i = 0;

            HSSFRow headRow = sheet.createRow(i);
            headRow.createCell(0).setCellValue("Film #" + film.getId());
            headRow.createCell(1).setCellValue("");
            headRow.createCell(2).setCellValue("");
            i++;

            HSSFRow destPlaceRow = sheet.createRow(i);
            destPlaceRow.createCell(0).setCellValue("");
            destPlaceRow.createCell(1).setCellValue("Film Description:");
            destPlaceRow.createCell(2).setCellValue(film.getDescription());
            i++;

            HSSFRow depTimeRow = sheet.createRow(i);
            depTimeRow.createCell(0).setCellValue("");
            depTimeRow.createCell(1).setCellValue("Film date:");
            depTimeRow.createCell(2).setCellValue(film.getDateByString());
            i++;

            HSSFRow firmOwnRow = sheet.createRow(i);
            firmOwnRow.createCell(0).setCellValue("");
            firmOwnRow.createCell(1).setCellValue("Film director");
            firmOwnRow.createCell(2).setCellValue(film.getDirector());
            i++;

            HSSFRow costRow = sheet.createRow(i);
            costRow.createCell(0).setCellValue("");
            costRow.createCell(1).setCellValue("Film genre:");
            costRow.createCell(2).setCellValue(film.getGenre().toString());
            i++;

            HSSFRow seatCountRow = sheet.createRow(i);
            seatCountRow.createCell(0).setCellValue("");
            seatCountRow.createCell(1).setCellValue("Film age Limitation:");
            seatCountRow.createCell(2).setCellValue(film.getAgeLimitation().toString());
            i++;

            HSSFRow emptyRow = sheet.createRow(i);
            emptyRow.createCell(0).setCellValue("");
            emptyRow.createCell(1).setCellValue("");
            emptyRow.createCell(2).setCellValue("");
            i++;


        cellsStyle(workbook, sheet, i);

        for (int j=0; j < 3 ; j++) {
            sheet.autoSizeColumn(j);
        }

        workbook.write(outputStream);
        outputStream.close();
    }

    public void generateUser(User user, OutputStream outputStream) throws DocumentException, IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("User " + user.getLogin());
        int i = 0;

        HSSFRow headRow = sheet.createRow(i);
        headRow.createCell(0).setCellValue("User #" + user.getId());
        headRow.createCell(1).setCellValue("");
        headRow.createCell(2).setCellValue("");
        i++;

        HSSFRow destPlaceRow = sheet.createRow(i);
        destPlaceRow.createCell(0).setCellValue("");
        destPlaceRow.createCell(1).setCellValue("User login:");
        destPlaceRow.createCell(2).setCellValue(user.getLogin());
        i++;

        HSSFRow depTimeRow = sheet.createRow(i);
        depTimeRow.createCell(0).setCellValue("");
        depTimeRow.createCell(1).setCellValue("User email:");
        depTimeRow.createCell(2).setCellValue(user.getEmail());
        i++;

        HSSFRow firmOwnRow = sheet.createRow(i);
        firmOwnRow.createCell(0).setCellValue("");
        firmOwnRow.createCell(1).setCellValue("User Type");
        firmOwnRow.createCell(2).setCellValue(user.getUserType().toString());
        i++;

        HSSFRow costRow = sheet.createRow(i);
        costRow.createCell(0).setCellValue("");
        costRow.createCell(1).setCellValue("User bonus count");
        costRow.createCell(2).setCellValue(user.getBonusCount());
        i++;

        cellsStyle(workbook, sheet, i);

        for (int j=0; j < 3 ; j++) {
            sheet.autoSizeColumn(j);
        }

        workbook.write(outputStream);
        outputStream.close();
    }

    public void generateSeance(Seance seance, OutputStream outputStream) throws DocumentException, IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Seance " + seance.getId());
        int i = 0;

        HSSFRow headRow = sheet.createRow(i);
        headRow.createCell(0).setCellValue("Seance #" + seance.getId());
        headRow.createCell(1).setCellValue("");
        headRow.createCell(2).setCellValue("");
        i++;

        HSSFRow destPlaceRow = sheet.createRow(i);
        destPlaceRow.createCell(0).setCellValue("");
        destPlaceRow.createCell(1).setCellValue("Seance film:");
        destPlaceRow.createCell(2).setCellValue(seance.getFilm().getTitle());
        i++;

        HSSFRow depTimeRow = sheet.createRow(i);
        depTimeRow.createCell(0).setCellValue("");
        depTimeRow.createCell(1).setCellValue("Seance film description:");
        depTimeRow.createCell(2).setCellValue(seance.getFilm().getDescription());
        i++;

        HSSFRow firmOwnRow = sheet.createRow(i);
        firmOwnRow.createCell(0).setCellValue("");
        firmOwnRow.createCell(1).setCellValue("Seance date");
        firmOwnRow.createCell(2).setCellValue(seance.getDateByString());
        i++;

        HSSFRow costRow = sheet.createRow(i);
        costRow.createCell(0).setCellValue("");
        costRow.createCell(1).setCellValue("Seance time");
        costRow.createCell(2).setCellValue(seance.getTimeByString());
        i++;

        HSSFRow seatCountRow = sheet.createRow(i);
        seatCountRow.createCell(0).setCellValue("");
        seatCountRow.createCell(1).setCellValue("Seance price:");
        seatCountRow.createCell(2).setCellValue(seance.getPrice());
        i++;

        HSSFRow emptyRow = sheet.createRow(i);
        emptyRow.createCell(0).setCellValue("");
        emptyRow.createCell(1).setCellValue("Seance Hall");
        emptyRow.createCell(2).setCellValue(seance.getHall().getId());
        i++;


        cellsStyle(workbook, sheet, i);

        for (int j=0; j < 3 ; j++) {
            sheet.autoSizeColumn(j);
        }

        workbook.write(outputStream);
        outputStream.close();
    }

    public void generateTicket(Ticket ticket, OutputStream outputStream) throws DocumentException, IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Ticket " + ticket.getId());
        int i = 0;

        HSSFRow headRow = sheet.createRow(i);
        headRow.createCell(0).setCellValue("Ticket #" + ticket.getId());
        headRow.createCell(1).setCellValue("");
        headRow.createCell(2).setCellValue("");
        i++;

        HSSFRow destPlaceRow = sheet.createRow(i);
        destPlaceRow.createCell(0).setCellValue("");
        destPlaceRow.createCell(1).setCellValue("Ticket film title:");
        destPlaceRow.createCell(2).setCellValue(ticket.getFilm().getTitle());
        i++;

        HSSFRow depTimeRow = sheet.createRow(i);
        depTimeRow.createCell(0).setCellValue("");
        depTimeRow.createCell(1).setCellValue("Ticket Hall:");
        depTimeRow.createCell(2).setCellValue(ticket.getHall().getId());
        i++;

        HSSFRow firmOwnRow = sheet.createRow(i);
        firmOwnRow.createCell(0).setCellValue("");
        firmOwnRow.createCell(1).setCellValue("Ticket place:");
        firmOwnRow.createCell(2).setCellValue(ticket.getPlace());
        i++;

        HSSFRow costRow = sheet.createRow(i);
        costRow.createCell(0).setCellValue("");
        costRow.createCell(1).setCellValue("Ticket Date");
        costRow.createCell(2).setCellValue(ticket.getDateByString());
        i++;

        HSSFRow seatCountRow = sheet.createRow(i);
        seatCountRow.createCell(0).setCellValue("");
        seatCountRow.createCell(1).setCellValue("Ticket Time:");
        seatCountRow.createCell(2).setCellValue(ticket.getTimeByString());
        i++;

        HSSFRow emptyRow = sheet.createRow(i);
        emptyRow.createCell(0).setCellValue("");
        emptyRow.createCell(1).setCellValue("Ticket price");
        emptyRow.createCell(2).setCellValue(ticket.getPrice());
        i++;


        cellsStyle(workbook, sheet, i);

        for (int j=0; j < 3 ; j++) {
            sheet.autoSizeColumn(j);
        }

        workbook.write(outputStream);
        outputStream.close();
    }

    public void generateHall(Hall hall, OutputStream outputStream) throws DocumentException, IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Hall " + hall.getId());
        int i = 0;

        HSSFRow headRow = sheet.createRow(i);
        headRow.createCell(0).setCellValue("Hall #" + hall.getId());
        headRow.createCell(1).setCellValue("");
        headRow.createCell(2).setCellValue("");
        i++;

        HSSFRow destPlaceRow = sheet.createRow(i);
        destPlaceRow.createCell(0).setCellValue("");
        destPlaceRow.createCell(1).setCellValue("Hall capacity:");
        destPlaceRow.createCell(2).setCellValue(hall.getCapacity());
        i++;

        HSSFRow depTimeRow = sheet.createRow(i);
        depTimeRow.createCell(0).setCellValue("");
        depTimeRow.createCell(1).setCellValue("Current date");
        depTimeRow.createCell(2).setCellValue(new Date());
        i++;


        cellsStyle(workbook, sheet, i);

        for (int j=0; j < 3 ; j++) {
            sheet.autoSizeColumn(j);
        }

        workbook.write(outputStream);
        outputStream.close();
    }



    private void cellsStyle(HSSFWorkbook workbook, HSSFSheet sheet, int i) {
        for (int j = 0; j < i; j++) {
            CellStyle headCellStyle = workbook.createCellStyle();
            Font font0 = workbook.createFont();
            font0.setBold(true);
            font0.setFontName(HSSFFont.FONT_ARIAL);
            headCellStyle.setFont(font0);
            headCellStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
            Row row0 = sheet.getRow(j);
            row0.getCell(0).setCellStyle(headCellStyle);

            CellStyle cellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setItalic(true);
            font.setFontName(HSSFFont.FONT_ARIAL);
            cellStyle.setFont(font);
            cellStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
            Row row1 = sheet.getRow(j);
            row1.getCell(1).setCellStyle(cellStyle);
        }
    }
}
