package main.action.user;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Alexander on 07.05.2016.
 */
public class LogoutUser extends ActionSupport {
    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();

        request.getSession().invalidate();

        return SUCCESS;
    }
}
