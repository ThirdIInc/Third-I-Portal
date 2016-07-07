<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.HashMap"%>
<s:form action="LeaveAudit" validate="true" id="ParaFrm" theme="simple">
<table class="bodyTable" width="90%" border="0">
      <tr>
  			<td  colspan="6" class="PAGEHEADER" align="center" >Leave Audit </td>
  	  </tr>
  	  
  	  <tr>
	    <td >&nbsp;</td>
	  </tr>
	  
	 <tr>
	  		<td     >Select Employee : </td>
	  		<td >
	  		<s:hidden name="leaveAudit.invCode" />
	  		<s:hidden name="leaveAudit.empID" />
	  		<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"
	  		width="18" onclick="javascript:callsF9(500,325,'LeaveAudit_f9empaction.action');"></td>
	</tr>
	<tr>
	  		<td    >Employee Name : </td>
	  		<td  colspan="3">
	  		<s:textfield name="leaveAudit.empTokenNo" size="15" readonly="true"/>
	  		<s:textfield name="leaveAudit.empName" size="40" readonly="true"/>
	  		</td>
	</tr>
	
	
	<tr>
	  		<td    >Rank :</td>
	  		<td  ><s:textfield size="25" name="leaveAudit.department" readonly="true" /></td>
   
	  		<td    >Center :</td>
	  		<td  ><s:textfield size="25" name="leaveAudit.center" readonly="true" /></td>  
	 </tr> 
	
	 <tr>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	  <s:if test="%{leaveAudit.auditList}">
	  <table width="100%">
			<tr>	
              <td   height="15" class="headercell">Sr No.</td>
             
              <td   height="15" class="headercell">Leave Days</td>
              <td   height="15" class="headercell">Leave From Date</td>
              <td   height="15" class="headercell">Leave To Date</td>
              <td   height="15" class="headercell">Leave Audit Flag</td>
                 
				                        
            </tr>
            
     	
     	 <% int i=0,s=1;HashMap afdata=(HashMap)request.getAttribute("data"); %>
      <s:iterator value="auditList" status="stat"> 
      <%String audFlag=(String)afdata.get(""+i); %>
      	  <tr>
      	<td  class="bodyCell"><%=s%></td>
	  
	    <td  class="bodyCell"><s:property value="leaveDays"/></td>
	    <td  class="bodyCell"><s:property value="leaveFromDate"/></td>
	    <td  class="bodyCell"><s:property value="leaveToDate"/></td>
	    <td  class="bodyCell" align="center"> <s:if test="%{chkFlag}"><input type="checkbox" class="checkbox" checked="checked" name="leaveChk" value="<%=audFlag.equals("Y")?"checked":""%>" onclick="callChk(<%=i%>)"   />
	    </s:if> <s:else><input type="checkbox" class="checkbox" name="leaveChk" value="<%=audFlag.equals("Y")?"checked":""%>" onclick="callChk(<%=i%>)"   /></s:else>
  		 <input type="hidden" name="leaveAuditFlag" id="<%=i%>" value="<%=audFlag.equals("null")?"N":audFlag%>" /> </td>
	    
	 
	   <td  ><s:hidden name="leaveDtlId" /></td>
	       
	 	</tr>
	 	
	 	 <%
			i++;s++;
			%>
	 	</s:iterator>
	 	<tr>
	    <td>&nbsp;</td>
	 	 </tr>
         <tr>
         <s:if test="%{auditList}">
         <td colspan="6" align="center">
         <s:submit cssClass="pagebutton" action="LeaveAudit_save" theme="simple" value="save" /></td>
         </s:if>
         <s:else>
         <td></td></s:else>
         </tr>
         </table>  
         </s:if>
         <s:else></s:else>
         </tr> 
         
             	
</table>
<s:hidden name="leaveAudit.empId" />

</s:form>

<script>
function callChk(id){

 if(document.getElementById(id).value=='Y'){
  document.getElementById(id).value='N';
 }else  if(document.getElementById(id).value=='N'){
  document.getElementById(id).value='Y';
 } 
}
</script>