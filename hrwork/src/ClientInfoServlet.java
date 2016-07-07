

import java.io.IOException;
import java.util.Comparator;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: Local
 *
 */
 public class ClientInfoServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
   @Override
   
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String poolName="";
		System.out.println("in do get method");
	   try {
		   String[] urlStr = request.getRequestURL().toString().split("/");
		  String ClientURL="";
		   String lastString = urlStr[urlStr.length-1];//(1,request.getRequestURL().length());
			System.out.println("urlStr[urlStr.length-2]"+urlStr[urlStr.length-2]); 
			System.out.println("urlStr[urlStr.length-1]"+urlStr[urlStr.length-1]); 
			
			System.out.println("lastString          "+lastString);
			System.out.println("lastString          "+lastString);
			
			
		   if(lastString.equals("recruit")){
			   ClientURL=urlStr[urlStr.length-2];
		   }
		   else if(lastString.equals("partner")){
			   ClientURL=urlStr[urlStr.length-2];
		   }
		   else if(lastString.equals("kiosk")){
			   ClientURL=urlStr[urlStr.length-2];
		   }
		   else if(lastString.equals("vendor")){
			   ClientURL=urlStr[urlStr.length-2];
		   }
		   else if(lastString.equals("crm")){
			   ClientURL=urlStr[urlStr.length-2];
		   }
		   else{
			   ClientURL=urlStr[urlStr.length-1];
		   }
			System.out.println("ClientURL"+ClientURL);   
		   poolName = existsClient(ClientURL);
		 
		   
		   if(poolName.equals("")) {
			   response.sendRedirect("http://" + request.getServerName() + ":"
						+ request.getServerPort()
						+ request.getContextPath()+"/error.html");
			   
			   
		   }else{
			   
		   poolName = new StringEncrypter(StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
					.encrypt(poolName);
		   String url="";
		   if(lastString.equals("recruit")){
			   System.out.println("@@@@@@@@@@ partner @@@@@@@@@@");
			   ClientURL=urlStr[urlStr.length-2];
			   url ="http://"+request.getServerName()+":"+request.getServerPort()+"/hrsaas/recruit/CandidateLogin_input.action?infoId="+poolName;
		   }else  if(lastString.equals("kiosk")){
			   System.out.println("^^^^^^^^");
			   ClientURL=urlStr[urlStr.length-2];
			   url ="http://"+request.getServerName()+":"+request.getServerPort()+"/hrsaas/kiosk/KioskLogin_input.action?infoId="+poolName;
		   }
		   
		   
		   else
			   if(lastString.equals("partner")){
			   System.out.println("@@@@@@@@@@ partner @@@@@@@@@@");
			   ClientURL=urlStr[urlStr.length-2];
			   url ="http://"+request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath()+"/recruit/PartnerLogin_input.action?infoId="+poolName;
		   }
			  
			   else
				   if(lastString.equals("vendor")){
				   System.out.println("@@@@@@@@@@ vendor @@@@@@@@@@");
				   ClientURL=urlStr[urlStr.length-2];
				   url ="http://"+request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath()+"/vendor/VendorLogin_input.action?infoId="+poolName;
			   }
				   else
					   if(lastString.equals("crm")){
					   System.out.println("@@@@@@@@@@ vendor @@@@@@@@@@");
					   ClientURL=urlStr[urlStr.length-2];
					   url ="http://"+request.getServerName()+":"+request.getServerPort()+"/hrsaas/cr/CustomerReportLogin_input.action?infoId="+poolName;
				   }
			   else{
			   url ="http://"+request.getServerName()+":"+request.getServerPort()+"/hrsaas/common/Login_input.action?infoId="+poolName;
		   }
		  
		  // String url ="http://192.168.25.26:8080/pims/common/Login_input.action?infoId="+poolName;
		   response.sendRedirect(url);
		   }
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
   
   public String existsClient(String ClientName) {
	 
	  String client = "";
		try {
			ResourceBundle boundle =ResourceBundle.getBundle("ClientInfoServlet");
			client = boundle.getString(ClientName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		   
		   return client;
	  }	  	    
}