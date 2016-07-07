<%@ taglib prefix="s" uri="/struts-tags" %>

<s:form action="LeaveExtension" method="post" id="paraFrm" validate="true" theme="simple">
		
  		<table class="bodyTable" width="100%">
  	<tr>
  			<td width="100%" colspan="6" class="pageHeader" align="center" >Leave Extension</td>
  	</tr>
  	<tr><td colspan="6">&nbsp;</td></tr>
  	<tr><td >Select Employee :</td>
  		<td colspan="5" ><s:textfield theme="simple" name="leave.empName" size="60" readonly="true" />
  			<img src="../pages/images/search.gif" class="iconImage" height="16" align="absmiddle"	width="16" onclick="javascript:callsF9(500,325,'LeaveExtension_f9EmployeeCode.action');">
  		</td>
  	</tr>
  	<tr><td colspan="6">&nbsp;</td></tr>
  	<tr><td width="100%" colspan="6" >
  			<table class="bodyTable" width="100%">
  				<tr><td class="headercell" width="15%">Token No.</td>
					<td class="headercell" width="50%">Employee Name</td>
					<td class="headercell" width="15%">Application Date</td>
					<td class="headercell" width="15%">Status</td>
					<td class="headercell" width="15%">&nbsp;</td>
  				</tr>
  				<s:iterator value="cancelList" > 
  					<tr>
  						<td width="15%"><s:property value="empCode" /></td>
						<td width="50%"><s:property value="empName" /></td>
						<td width="15%"><s:property value="applicationDate" /></td>
						<td width="15%"><s:property value="status" /></td>
						<td width="15%"><input type="button" class="pagebutton" style="cursor:hand"  onclick="callCancel('<s:property value="leaveCode" />');"  name="detail" value="See Detail" /></td>
  					</tr>   	
  				</s:iterator>
  			</table>
  		</td>
  	</tr>
  	<tr><td colspan="6">&nbsp;</td></tr>
  </table>
  <s:hidden name="leave.empCode" />
  <s:hidden name="leave.department" />
  <s:hidden name="leave.center" />
  <s:hidden name="leaveApplication.paracode" />
  <s:hidden name="leave.leaveCode" />
 </s:form>
 <script type="text/javascript">
 function callCancel(id){
		document.getElementById("paraFrm").action="LeaveExtension_getLeaveAllDetails.action";
		document.getElementById('leave.leaveCode').value=id;
		document.getElementById("paraFrm").submit();
	}
</script>
