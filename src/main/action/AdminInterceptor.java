package main.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.TextParseUtil;
import main.entity.user.User;
import main.entity.user.UserType;

import java.util.*;


public class AdminInterceptor extends AbstractInterceptor {

    private Map<String, Set> roleActions = Collections.emptyMap();

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        String actionName = actionInvocation.getProxy().getActionName();
        Map session = actionInvocation.getInvocationContext().getSession();



        User user = (User) session.get("user");

        String ib =actionInvocation.invoke();

        ib += "12";

        //if (!actionName.equals("login_user")){
            if (acceptRights(user, actionName)) {

                ActionSupport action = ActionHelper.getCommand( ActionName.valueOf(actionName.toUpperCase()) );
                return action.execute();
            }
            else {
                return Action.LOGIN;
            }

        //}

        //return actionInvocation.invoke();
    }

    public void setRoleActions(String roleActionsParam) {
        StringTokenizer stringTokenizer = new StringTokenizer(roleActionsParam, ";");
        roleActions = new HashMap<>(stringTokenizer.countTokens());

        while (stringTokenizer.hasMoreTokens()) {
            String[] roleActionArray = stringTokenizer.nextToken().trim().split(":");
            if (roleActionArray.length == 2) {
                String role = roleActionArray[0];
                Set actions = TextParseUtil.commaDelimitedStringToSet(roleActionArray[1]);
                roleActions.put(role, actions);
            }
        }
    }

    private boolean acceptRights(User user, String actionName) {
        UserType userType;
        UserType userTypeCommand = ActionHelper.getUserTypeCommand(ActionName.valueOf(actionName.toUpperCase()));

        if (user == null) {
            userType = UserType.GUEST;
        } else {
            userType = user.getUserType();
        }

        if (userType == UserType.ADMIN) {
            return true;
        }

        if (userType == UserType.USER) {
            if (userTypeCommand != UserType.ADMIN) {
                return true;
            }
        }

        if (userType == UserType.GUEST) {
            if (userTypeCommand == UserType.GUEST) {
                return true;
            }
        }

        return false;
    }
}