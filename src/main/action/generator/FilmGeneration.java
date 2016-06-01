package main.action.generator;


import com.itextpdf.text.DocumentException;
import com.opensymphony.xwork2.ActionSupport;
import main.dao.DaoFactory;
import main.generator.CsvGenerator;
import main.generator.ExcelGenerator;
import main.generator.PdfGenerator;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class FilmGeneration extends ActionSupport {
    private String doc_type;

    public String getFilm_id() {
        return film_id;
    }

    public void setFilm_id(String film_id) {
        this.film_id = film_id;
    }

    private String film_id;

    @Override
    public String execute() throws Exception{
        HttpServletResponse response = ServletActionContext.getResponse();
        PdfGenerator pdfGenerator = PdfGenerator.getInstance();
        CsvGenerator csvGenerator = CsvGenerator.getInstance();
        ExcelGenerator excelGenerator = ExcelGenerator.getInstance();
        DaoFactory daoFactory = DaoFactory.getDaoFactory();

        int filmId = Integer.valueOf(film_id);
        String fileName = "Film";

        try {
            switch (doc_type) {
                case "PDF": {
                    fileName += ".pdf";
                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + fileName);
                    pdfGenerator.generateFilm(daoFactory.getFilmDao().findFilmById(filmId), response.getOutputStream());
                }
                break;

                case "CSV": {
                    fileName += ".csv";
                    response.setContentType("text/csv");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + fileName);
                    csvGenerator.generateFilm(daoFactory.getFilmDao().findFilmById(filmId),response.getOutputStream());
                }
                break;

                case "EXCEL": {
                    fileName += ".xls";
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + fileName);
                    excelGenerator.generateFilm(daoFactory.getFilmDao().findFilmById(filmId),response.getOutputStream());
                    break;
                }
            }
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    public String getDoc_type() {
        return doc_type;
    }

    public void setDoc_type(String doc_type) {
        this.doc_type = doc_type;
    }

}
