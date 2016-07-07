<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="QueryReport" id="paraFrm" target="main" theme="simple">
	<table class="bodyTable" width="100%" border="0">
		<tr>
			<td class="pageHeader" width="100%" colspan="4">
			<center>Query Report</center>
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="4" width="100%">
			<center>
			<table class="bodyTable" width="630">
				<tr>
					<td width="25%" colspan="1">Select Query Name:</td>
					<td width="75%" colspan="3"><s:hidden
						name="queryReport.queryId" value="%{queryReport.queryId}" /> <s:textfield
						name="queryReport.queryName" value="%{queryReport.queryName}" />
					<s:hidden name="queryReport.queryString"
						value="%{queryReport.queryString}" /> <img class="iconImage"
						src="../pages/images/search.gif" height="18" align="absmiddle"
						width="18"
						onclick="javascript:callsF9(500,325,'QueryReport_f9Query.action');">
					</td>

				</tr>
				<s:iterator value="queryReport.paraList">
					<tr>

						<td><s:label name="paraName" id="paraName"
							value="%{paraName}" /></td>
						<td><s:textfield name="paraValue" value="%{paraValue}"
							id="paraValue" /></td>

					</tr>

				</s:iterator>
				<tr>
					<td width="100%" colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td width="100%" colspan="4" align="center"><s:submit
						cssClass="pagebutton" action="QueryReport_view" theme="simple"
						value=" Go " /><s:submit cssClass="pagebutton"
						action="QueryReport_export" theme="simple" value=" Export " /></td>
				</tr>
			</table>
			</center>
			</td>
		</tr>
	</table>
	<table class="bodyTable" width="100%">



		<tr>
			<s:iterator value="queryReport.headerList">
				<td class="headercell"><s:property value="headerName" /><s:hidden
					value="%{headerName}" name="headers" /></td>
			</s:iterator>
		</tr>
		<%
		try {
		%>
		<%
		String[][] strObj = (String[][]) request.getAttribute("strObj");
		%>
		<%
		for (int i = 0; i < strObj.length; i++) {
		%>
		<tr>
			<%
			for (int j = 0; j < strObj[0].length; j++) {
			%>
			<td class="bodyCell" nowrap><%=strObj[i][j]%></td>
			<%
			}
			%>
		</tr>
		<%
		}
		%>
		<%
			} catch (Exception e) {
			}
		%>



	</table>
</s:form>