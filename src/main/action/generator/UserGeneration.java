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


public class UserGeneration extends ActionSupport {
    private String doc_type;
    private String user_id;

    @Override
    public String execute() throws Exception{
        HttpServletResponse response = ServletActionContext.getResponse();
        PdfGenerator pdfGenerator = PdfGenerator.getInstance();
        CsvGenerator csvGenerator = CsvGenerator.getInstance();
        ExcelGenerator excelGenerator = ExcelGenerator.getInstance();
        DaoFactory daoFactory = DaoFactory.getDaoFactory();

        int userId = Integer.valueOf(user_id);
        String fileName = "User";

        try {
            switch (doc_type) {
                case "PDF": {
                    fileName += ".pdf";
                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + fileName);
                    pdfGenerator.generateUser(daoFactory.getUserDao().findUserById(userId), response.getOutputStream());
                }
                break;

                case "CSV": {
                    fileName += ".csv";
                    response.setContentType("text/csv");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + fileName);
                    csvGenerator.generateUser(daoFactory.getUserDao().findUserById(userId),response.getOutputStream());
                }
                break;

                case "EXCEL": {
                    fileName += ".xls";
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + fileName);
                    excelGenerator.generateUser(daoFactory.getUserDao().findUserById(userId),response.getOutputStream());
                    break;
                }
            }
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDoc_type() {
        return doc_type;
    }

    public void setDoc_type(String doc_type) {
        this.doc_type = doc_type;
    }

}
