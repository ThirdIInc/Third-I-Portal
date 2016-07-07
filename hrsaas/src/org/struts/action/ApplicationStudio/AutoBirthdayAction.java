package org.struts.action.ApplicationStudio;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Vector;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.paradyne.model.ApplicationStudio.AutoBirthdayModel;

public class AutoBirthdayAction extends ActionSupport
  implements ModelDriven, Preparable, ServletRequestAware, ServletResponseAware, ServletContextAware
{
  static Logger logger = Logger.getLogger(AutoBirthdayAction.class);
  public ActionContext actionContext = ActionContext.getContext();
  public HttpServletRequest request;
  public HttpServletResponse response;
  public HttpSession session;
  public ServletContext context;

  public ActionContext getActionContext()
  {
    return this.actionContext;
  }

  public void setActionContext(ActionContext actionContext) {
    this.actionContext = actionContext;
  }

  public HttpServletRequest getRequest() {
    return this.request;
  }

  public void setRequest(HttpServletRequest request) {
    this.request = request;
  }

  public HttpServletResponse getResponse() {
    return this.response;
  }

  public void setResponse(HttpServletResponse response) {
    this.response = response;
  }

  public HttpSession getSession() {
    return this.session;
  }

  public void setSession(HttpSession session) {
    this.session = session;
  }

  public ServletContext getContext() {
    return this.context;
  }

  public void setContext(ServletContext context) {
    this.context = context;
  }

  public void prepare_local() throws Exception {
  }

  public Object getModel() {
    return null;
  }

  public void sendBirthdayMail() {
    String serverName = this.request.getServerName();
    System.out.println("serverName::" + serverName);

    if (serverName.equals("localhost")) {
      AutoBirthdayModel model = new AutoBirthdayModel();
      ResourceBundle bundle = ResourceBundle.getBundle(getClass()
        .getName());
      Enumeration list = bundle.getKeys();
      int count = 0;
      Vector vect = new Vector();
      while (list.hasMoreElements())
      {
        String client = bundle.getString((String)list.nextElement());
        vect.add(client);
      }
      System.out.println("vect.size()--------------" + vect.size());
      for (int i = 0; i < vect.size(); i++) {
        String client = (String)vect.get(i);
        System.out.println("client--------------" + client);
        this.session.setAttribute("session_pool", client);
        model.initiate(this.context, this.session);

        model.getBirthdayData(this.request);

        System.out.println("client--------------" + i);
        model.terminate();
      }
      this.session.invalidate();
    } else {
      System.out.println(serverName + "  Not a proper Server");
    }
  }

  public void sendAnniversaryMail() {
    String serverName = this.request.getServerName();
    System.out.println("serverName::" + serverName);

    if (serverName.equals("localhost")) {
      AutoBirthdayModel model = new AutoBirthdayModel();
      ResourceBundle bundle = ResourceBundle.getBundle(getClass()
        .getName());
      Enumeration list = bundle.getKeys();
      int count = 0;
      Vector vect = new Vector();
      while (list.hasMoreElements())
      {
        String client = bundle.getString((String)list.nextElement());
        vect.add(client);
      }

      System.out.println("vect.size()--------------" + vect.size());
      for (int i = 0; i < vect.size(); i++) {
        String client = (String)vect.get(i);
        System.out.println("client--------------" + client);
        this.session.setAttribute("session_pool", client);
        model.initiate(this.context, this.session);
        model.getAnniversaryData(this.request);
        System.out.println("client--------------" + i);
        model.terminate();
      }
      this.session.invalidate();
    } else {
      System.out.println(serverName + "  Not a proper Server");
    }
  }

  public void prepare() throws Exception
  {
    setSession(this.request.getSession());
    setContext(this.session.getServletContext());
  }

  public void setServletRequest(HttpServletRequest httpServletRequest)
  {
    this.request = httpServletRequest;
  }

  public void setServletResponse(HttpServletResponse httpServletResponse)
  {
    this.response = httpServletResponse;
  }

  public void setServletContext(ServletContext servletContext)
  {
    this.context = servletContext;
  }
}