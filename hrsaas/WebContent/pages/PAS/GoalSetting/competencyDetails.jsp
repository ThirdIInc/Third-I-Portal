<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="EmpGoalSetting" method="post" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee Competencies </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				
						
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortable">
								<tr>
									<td valign="top" class="formth" width="5%">Sr No</td>
									<td width="70%" valign="top" class="formth">Competency</td>
									
								</tr>
								<%
								int count = 0, p = 0;
								%>
								<s:iterator value="empGoalList" status="stat">
									<tr>
										<td class="sortableTD"><%=++count%><s:hidden name="srNo" value="%{<%=count%>}" />
										<td class="sortableTD" align="left"><s:property value="competency" />&nbsp;</td>
									</tr>
									<%
									p = count;
									%>
								</s:iterator>
								 
								<%
							if (p == 0) {
							%>
							<tr align="center">
								<td colspan="2" class="sortableTD" width="100%"><font
									color="red">No Data to display</font></td>
							</tr>
							<%
							}
							%>
							</table>
							</td>
						</tr>
					</table>
			</td>
		</tr>
	</table>
	
</s:form>
