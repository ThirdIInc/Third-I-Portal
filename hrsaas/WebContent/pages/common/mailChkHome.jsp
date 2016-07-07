<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	
		
			<tr><td width="100%" class="cellbg" colspan="6"> <a class="txt" target="blank" href="<s:url action="HomePage_mailChk"></s:url>">Inbox(<s:property value="noOfMails"/>)</a>&nbsp;
			
			</td>
			</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" bgcolor="#FFFFFF">
			 <tr>
   			<td  width="3%" class="whitetable" align="center">S.No.</td>
           
              <td  width="30%" class="whitetable"  align="center">Sender</td>
               <td  width="40%" class="whitetable"  align="center">Subject</td>
              <td  width="5%" class="whitetable"  align="center">Date</td>
         
           
              
   </tr>
            
  
  	
            <%int b=1;%>
      <s:iterator value="mailList" status="stat"> 
      
      	  <tr>
      	<td   class="whitetable1" width="3%"><%=b%></td>
        <s:hidden name="msgCode1" value="%{msgCode}"/>
                     
      	  <td  width="30%"  class="whitetable1"><a class="txt" target="blank" href="<s:url action="HomePage_description"><s:param name="msgNo" value="msgCode"/></s:url>"><s:property value="sender"/></a></td>
	    <td  width="40%"  class="whitetable1"><s:if test="%{attChkFlag}"><img src="../pages/images/attach.png" />&nbsp;</s:if><s:property value="subject" /></td>
	   
	    <td  width="5%"  class="whitetable1" ><s:property value="Date"/></td>
	    	    
	   
	  </tr>
	 	 <%
			b++;
			%>
       </s:iterator>	
		 
		</table>


		