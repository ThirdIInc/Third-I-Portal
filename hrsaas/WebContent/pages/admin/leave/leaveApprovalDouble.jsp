
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:form action="LeaveApproval" method="post" id="paraFrm" validate="true" theme="simple">
		
  		<table class="bodyTable" width="100%">
  	<tr>
  			<td width="100%" colspan="6" class="pageHeader" align="center" >Leave Approval</td>
  	</tr>
  	<tr>
    	<td width="100%" colspan="6">&nbsp;</td>
  	</tr>
  	
  	<tr>
    	<td width="100%" colspan="6">&nbsp;</td>
  	</tr>
  	
  	
  	
  	
  	</table>
  	<table class="bodyTable" width="100%">
  	<tr>  			
  		<td class="headercell" width="15%">Token No.</td>
		<td class="headercell" width="60%">Employee Name</td>
		<td class="headercell" width="15%">Application Date</td>
		<!--<td class="headercell" width="15%">From Date</td>
		<td class="headercell" width="15%">To Date</td>
		<td class="headercell" width="10%">Total Days</td>
		--><td class="headercell" width="12%">Details</td>
	</tr>	
		<s:iterator value="appList" status="stat">            	
	 <tr>	 
	      <td class="bodyCell" width="15%"><s:property value="empId"/></td>
	      <td class="bodyCell" width="60%"><s:property value="empName"/></td>
	      <td class="bodyCell" width="20%"><s:property value="appDate"/></td><!--
          <td class="bodyCell" width="15%"><s:property value="fromDate"/></td>
	      <td class="bodyCell" width="15%"><s:property value="toDate"/></td>
	      <td class="bodyCell" width="8%"><s:property value="totalDays"/></td>
	      --><td class="bodyCell" width="12%">
	      <a href="<s:url action="LeaveApplication_callByLeaveApprove"><s:param name="appCode" value="appCode"/></s:url>">Show Details</a></td>
	  </tr>           
	</s:iterator>
	
	<tr>
    	<td width="100%" colspan="6">&nbsp;</td>
  	</tr>
  	
  	<tr>
  		<td width="100%" colspan="6" align="center" ></td>
  	</tr>
  
 	</table>
 	<s:hidden name="hiddenAppCode" />
  	</s:form>
<script>
  	function callLeaveApplication(appCode){
  		document.getElementById('paraFrm').target="main";
 		document.getElementById("paraFrm").action="LeaveApplication_callByLeaveApprove.action";
  		document.getElementById("hiddenAppCode").value=appCode;  		
    	document.getElementById("paraFrm").submit();
  	}
  </script>