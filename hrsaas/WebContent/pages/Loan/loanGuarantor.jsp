<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="LoanProcessing" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom"
				background="../pages/images/recruitment/lines.gif" class="txt"><img
				src="../pages/images/recruitment/lines.gif" width="16" height="1" /></td>
		</tr>
		
		
		
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td colspan="4" class="formhead"><strong
								class="forminnerhead">Guarantor Details </strong></td>
						</tr>
							
			
		
		
					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
						<tr>
							<td width="5%" class="formth">Sr.No.</td>
							<td width="15%" class="formth">Employee ID</td>
							<td width="25%" class="formth">Employee Name</td>
							<td width="25%" class="formth">Department</td>
							<td width="20%" class="formth">Branch</td>
							<td width="20%" class="formth">Designation</td>
							
						</tr>
						<% int i = 0;%>

						
					<s:iterator value="gurantorList">

							<tr>

								<td class="border2" width="5%"><%=i+1 %></td>
								<td class="border2" width="15%"><s:property value="guarId"/></td>
								<td class="border2" width="25%" align="left"><s:property value="guarName"/></td>
								<td class="border2" width="25%" align="left"><s:property value="guarDept"/></td>
								<td class="border2" width="20%" align="left"><s:property value="guarBranch"/></td>
								<td class="border2" width="20%" align="left"><s:property value="guarDesg"/></td>
																
							</tr>

							<%
			i++;
			%>
						
					</s:iterator>
			
					</table>

					</td>
				
			</table>
			</td>
		</tr>
		
		</table></td></tr></table>
		
	
</s:form>


	
</script>