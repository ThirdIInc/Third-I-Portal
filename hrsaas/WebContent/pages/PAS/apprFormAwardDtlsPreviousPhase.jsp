<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<%
 
 Object data[][] = (Object[][])request.getAttribute("data"); //PREVIOUS PHASES DATA
%>

<s:form action="ApprFormSection" validate="true" id="paraFrm"
	theme="simple">
		
	  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" >
         
  <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
              
            <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Previous Phase Award Details</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
       
      <tr> 
     <tr>
            <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="2">
             	<tr>
			    <td class="formth" width="3%">            			  
	             <label  name="awd.srno"  class = "set"  id="awd.srno" ondblclick="callShowDiv(this);"><%=label.get("awd.srno")%></label>          
			    </td>
			    <td class="formth" width="5%">            			  
	             <label  name="awd.phase"  class = "set"  id="awd.phase" ondblclick="callShowDiv(this);"><%=label.get("awd.phase")%></label>          
			    </td>
	            <td class="formth" width="20%" >
	            <label  name="awd.desc"  class = "set"  id="awd.desc" ondblclick="callShowDiv(this);"><%=label.get("awd.desc")%></label>            
	            </td>
	            <td class="formth" width="5%">            
	            <label  name="awd.date"  class = "set"  id="awd.date" ondblclick="callShowDiv(this);"><%=label.get("awd.date")%></label>
	            </td>
	            <td class="formth" width="10%">            
	            <label  name="awd.issue"  class = "set"  id="awd.issue" ondblclick="callShowDiv(this);"><%=label.get("awd.issue")%></label>
	            </td>
	            <td class="formth" width="59%">
	            <label  name="awd.comments"  class = "set"  id="awd.comments" ondblclick="callShowDiv(this);"><%=label.get("awd.comments")%></label>
	            </td>
			   </tr>				
				<%
					if(data!=null && data.length>0){
					for(int i=0;i<data.length;i++){	%> 
						<tr>
						 <td width="3%" class="sortableTD"><%=i+1 %></td>
						 <td width="5%" class="sortableTD"><%=data[i][1] %></td>
						 <td width="20%" class="sortableTD"><%=data[i][3] %></td>
						 <td width="5%" class="sortableTD" align="center"><%=data[i][4] %></td>
						 <td width="10%" class="sortableTD"><%=data[i][5] %></td>
						<td width="59%" class="sortableTD"><%=data[i][6] %>&nbsp;</td>						
						</tr>
				<%}}else{%>				
						 <tr>
						  <td colspan="6"> <font color="red">No Data To Display</font></td>
						 </tr>
				<%} %>
							
							         
      </table></td>
    </tr>
    </table></td></tr>
    
    
    
      
      
 </table>
	
	</td></tr></table>
	</s:form>


<script type="text/javascript">


</script>
