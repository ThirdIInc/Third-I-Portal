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
				          <td width="93%" class="txt"><strong class="text_head">Previous Phase <label class="set" name="career.progression" id="career.progression"
								ondblclick="callShowDiv(this);"><%=label.get("career.progression")%></label> Details</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
       
      <tr> 
     <tr>
            <td><table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
             	<tr>
				 <td class="formth" width="3%">
	            <label name="career.sno" class = "set" id="career.sno" ondblclick="callShowDiv(this);"><%=label.get("career.sno") %></label>
	            </td>
	            <td class="formth" width="3%">
	            <label name="career.phase" class = "set" id="career.phase" ondblclick="callShowDiv(this);"><%=label.get("career.phase") %></label>
	            </td>	            
	            <td class="formth" width="45%"> 
	            <label name="career.desc" class = "set" id="career.desc" ondblclick="callShowDiv(this);"><%=label.get("career.desc") %></label>
	            </td>
	            <td class="formth" width="30%">
	             <label name="career.comments" class = "set" id="career.comments" ondblclick="callShowDiv(this);"><%=label.get("career.comments") %></label>
	            </td>
				 
				 	
				</tr>	
				<%if(data != null && data.length != 0) {%>				
				<%	for(int i=0;i<data.length;i++){	%>
						<tr>
						 <td class="td_bottom_border"><%=i+1 %></td>
						 <td class="td_bottom_border"><%=data[i][7] %></td>
						 <td class="td_bottom_border"><%=data[i][1] %></td>
						 <td class="td_bottom_border"><%=data[i][5] %></td>										
						</tr>
				<%}}else{	%>
						<tr>
						 <td class="td_bottom_border" align="center" colspan="4"><font color="red">No Data To Display</font></td>
						</tr>
				<%} %>
							
							         
      </table></td>
    </tr>
    </table></td></tr>
    
    
    
      
      
 </table>

  <label></label>
	
	</td></tr></table>
	</s:form>


<script type="text/javascript">


</script>
