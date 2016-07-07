<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="LeavePolicy" id="paraFrm" validate="true" theme="simple"
	target="main">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">


		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Resign
					Employee List </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="sortable">
				<tr class="td_bottom_border">
					<td width="5%" valign="top" class="formth">Sr.No.</td>
					<td width="20%" valign="top" class="formth">Employee ID</td>
					<td width="45%" valign="top" class="formth">Employee Name</td>
					<td width="30%" valign="top" class="formth">Date of Leaving</td>
				</tr>
				<%
									int count = 1;
									%>
				<s:iterator value="resignList">
					<tr class="sortableTD">
						<td class="sortableTD" width="5%" align="center"><%=count%></td>
						<td class="sortableTD" width="20%"><s:property
							value="resignEmployeeToken" /></td>
						<td class="sortableTD" width="45%"><s:property
							value="resignEmployeeName" /></td>
						<td class="sortableTD" width="30%" align="center"><s:property
							value="resignationDate" /></td>
					</tr>

					<%
						count++;
						%>

				</s:iterator>

				<%
									if (count == 1) {
									%>
				<tr align="center">
					<td colspan="4" class="sortableTD" width="100%"><font
						color="red">No Data to display</font></td>
				</tr>
				<%
									}
									%>

			</table>
			</td>
		</tr>


	</table>
</s:form>