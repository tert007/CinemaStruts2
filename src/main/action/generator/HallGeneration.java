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


public class HallGeneration extends ActionSupport {
    private String doc_type;
    private String hall_id;

    @Override
    public String execute() throws Exception{
        HttpServletResponse response = ServletActionContext.getResponse();
        PdfGenerator pdfGenerator = PdfGenerator.getInstance();
        CsvGenerator csvGenerator = CsvGenerator.getInstance();
        ExcelGenerator excelGenerator = ExcelGenerator.getInstance();
        DaoFactory daoFactory = DaoFactory.getDaoFactory();

        int hallId = Integer.valueOf(hall_id);
        String fileName = "Hall";

        try {
            switch (doc_type) {
                case "PDF": {
                    fileName += ".pdf";
                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + fileName);
                    pdfGenerator.generateHall(daoFactory.getHallDao().findHallById(hallId), response.getOutputStream());
                }
                break;

                case "CSV": {
                    fileName += ".csv";
                    response.setContentType("text/csv");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + fileName);
                    csvGenerator.generateHall(daoFactory.getHallDao().findHallById(hallId),response.getOutputStream());
                }
                break;

                case "EXCEL": {
                    fileName += ".xls";
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + fileName);
                    excelGenerator.generateHall(daoFactory.getHallDao().findHallById(hallId),response.getOutputStream());
                    break;
                }
            }
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }


    public String getHall_id() {
        return hall_id;
    }

    public void setHall_id(String hall_id) {
        this.hall_id = hall_id;
    }
    public String getDoc_type() {
        return doc_type;
    }

    public void setDoc_type(String doc_type) {
        this.doc_type = doc_type;
    }

}
