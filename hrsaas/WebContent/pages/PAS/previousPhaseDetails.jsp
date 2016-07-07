<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

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
				          <td width="93%" class="txt"><strong class="text_head">Previous Phase Details</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
       
      <tr> 
     <tr>
            <td><table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
              <tr>
					<td class="formth" width="5%"><label name="appraisal.form.section.srno" class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.section.srno")%></label></td>
					<td class="formth" width="10%"><label  name="appraisal.form.phasename" class = "set"  id="appraisal.form.phasename" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.phasename")%></label></td>
					<td class="formth" width="30%" align="left"><label name="appraisal.form.ques.desc" class = "set"  id="appraisal.form.ques.desc" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.ques.desc")%></label></td>
					<td class="formth" width="5%" align="left"><label  name="appraisal.form.ques.wt" class = "set"  id="appraisal.form.ques.wt" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.ques.wt")%></label></td>
					<td class="formth" width="5%" align="left"><label  name="appraisal.form.rating" class = "set"  id="appraisal.form.rating" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.rating")%></label></td>
					<td class="formth" width="5%" align="left"><label  name="appraisal.form.ques.avg" class = "set"  id="appraisal.form.ques.avg" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.ques.avg")%></label></td>
					<td class="formth" width="40%" align="left"><label name="appraisal.form.comment1" class = "set"  id="appraisal.form.comment1" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.comment1")%></label></td>
					
				</tr>
				<% Object [][] phaseData =(Object[][])request.getAttribute("previousPhaseData"); %>			
				<%if(phaseData != null && phaseData.length != 0) {
							 %>
							<% for(int j=1,l=0;l<phaseData.length;l++) {%>
				<tr>
              	 	<td width="5%" align="center" class="td_bottom_border"><%=j %></td>
					<td width="10%" align="center" class="td_bottom_border"><%=String.valueOf(phaseData[l][0]) %></td>
					<td width="30%" align="left" class="td_bottom_border"><%=String.valueOf(phaseData[l][1]) %></td>
					<td width="5%" align="center" class="td_bottom_border"><%=String.valueOf(phaseData[l][2]) %></td>
					<td width="5%" align="center" class="td_bottom_border">
					<%if(String.valueOf(phaseData[l][2]).equals("0")) { %>
						<%=String.valueOf(phaseData[l][3]) %>
					<% } else{ %>
						<%=String.valueOf(phaseData[l][3]) %>
					<% }  %>
					</td>	
					<td width="5%" align="center" class="td_bottom_border" >
					<%if(String.valueOf(phaseData[l][2]).equals("0")) { %>
						<%=String.valueOf(phaseData[l][4]) %>
					<% } else{ %>
						<%=String.valueOf(phaseData[l][4]) %>
					<% }  %>
					</td>
					<td width="40%" align="left" class="td_bottom_border"><%=String.valueOf(phaseData[l][5]) %></td>
					
					<%j++; %> 
				</tr> 
				<%} } else {%>
				
						 <tr>
				  			<td class="td_bottom_border" colspan="6" align="center" > <font color="red">No Data To Display</font></td>
				 		</tr>
				<%} %>			
							         
      </table></td>
    </tr>
    </table></td></tr>
    </table></td></tr>
    
    
      
      
 </table>

  <label></label>
	
	
	</s:form>


<script type="text/javascript">


</script>
