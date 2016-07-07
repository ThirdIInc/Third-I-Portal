<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="EmployeeCheckList" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table class="bodyTable" width="100%" colspan="4">


		<tr>
			<td width="100%" colspan="6" align="center"><b>Employee
			CheckList Report</b></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
		
		<td width="25%">Token No</td>
			<td width="25%"><s:property value="employeeCheckList.empToken" />
		
			
			<td width="25%">Employee Name :</td>
			<td><s:property value="employeeCheckList.empName" /></td>
			
		</tr>
		
		<tr><td>&nbsp;</td></tr>

		<tr>
			<!--<td width="25%">Department :</td>
			<td width="25%"><s:property value="employeeCheckList.deptName" /></td>
			--><td width="25%">Branch :</td>
			<td width="25%"><s:property value="employeeCheckList.center" /></td>
			<td width="25%">Designation :</td>
			<td width="25%"><s:hidden name="employeeCheckList.empID" /><s:property
				value="employeeCheckList.empRank" /></td>
		</tr>

	
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
	</table>
	<table class="bodyTable" width="100%" colspan="3">
	<tr><td>&nbsp;</td></tr>
		<tr>
			<td width="30%" class="bodycell">Check Name</td>
			<td width="40%" class="bodycell">Check Discription</td>
			<td width="30%" class="bodycell">Check Submit</td>
		</tr>
		<%
		int i = 0;
		%>
		<s:iterator value="employeeCheckList.chkList">
			<tr>
				<s:hidden name="chkId" />
				<td><s:property value="chkName" /></td>
				<td><s:property value="chkDesc" /></td>
				<td><s:property value="chkSubmit" /></td>
			</tr>
			<%
			i++;
			%>
		</s:iterator>


	</table>



</s:form>
