<%@ taglib prefix="s" uri="/struts-tags" %> 

<%@ page language="java" import="org.paradyne.model.common.MessageInfo"  %>

<%@page import="javax.mail.Message"%>

<%@page import="javax.mail.Multipart"%>
<%@page import="javax.mail.BodyPart"%>
<s:form action="HomePage" id="paraFrm" validate="true" theme="simple">
<table class="bodyTable" width="100%">
  	<tr>
  		<td  width="100%" colspan="12"  class="pageHeader" align="center" ><b>Message Description</td>
  	</tr>
  	
  	<tr>
    	<td width="100%" colspan="3">&nbsp;</td>      	
  	</tr>
  	 	
  	
  	<tr>
  	  
    	<td  class="cellbg" align="left" colspan="3">Subject :<s:property value="subject"/></td>
    	
  	</tr>
  	<tr>
  	  
    	<td  class="cellbg" align="left" colspan="3">Sender :<s:property value="sender"/></td>
    	<s:hidden name="msgFromRep" value="%{sender}"/>
    	
  	</tr>
  		<tr>
  	  
    	<td  class="cellbg" align="left" colspan="3">Date :<s:property value="Date"/></td>
	
  	</tr>
  	
  	
  	<tr>
  	<td >&nbsp;</td>
  	</tr>
  	
  	 <tr>
  		<td><s:submit action="HomePage_msgReply"  cssClass="pagebutton"  value="Reply"/></td>
  	</tr> 	
    	<tr>
  	<td >&nbsp;</td>
  	</tr>

  <%Message message=(Message)request.getAttribute("message"); %>
  <%MessageInfo msginfo = new MessageInfo(); 
     
     %>
         
<tr>
			<td>
						
				<%
				msginfo.setMessage(message);
				String path=config.getServletContext().getRealPath("/")+"pages/download";
				System.out.println("--------------------------------Cserver path"+path);
				
				msginfo.setPath(path);
				
				msginfo.getTextAttch(message);
				
				String msg=msginfo.getText(message); 
								
				System.out.println("--------------------------------Content type"+message.getContentType());
				%>
				<%=msg %>
			
				<br><br>
				<% if (msginfo.hasAttachments1()) {
					Multipart mp = (Multipart)message.getContent();
				BodyPart part = mp.getBodyPart(1);
				String fileName = part.getFileName();
				System.out.println("--------------------------------filename"+fileName);%>
					<a href="#" onclick="callWin('<%= fileName%>')">
						Download Attachment</a>
				<%}%>
	
		</td>
  	</tr>
  	  	
  
   </table>
   </s:form>
   <script>
   function callWin(fileName)
   {
  
   window.open('../pages/download/'+fileName);
   }
   
   </script>
