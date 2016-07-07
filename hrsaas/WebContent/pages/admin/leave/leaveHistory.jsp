
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="LeaveApplication" method="post" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td colspan="6" valign="bottom" class="txt">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom"
				background="../pages/common/css/default/images/lines.gif"
				class="txt"><img
				src="../pages/common/css/default/images/lines.gif" width="16"
				height="1" /></td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">


						<tr>
							<s:hidden name="eId" />
							<!-- value of emp id -->
							<td width="24%"><span class="formtext"> </span><span
								class="style2"></span></td>
							<td height="22" colspan="4"><s:select theme="simple"
								name="levstatus" cssStyle="width:100"
								list="#{'CM':'Current Month','PM':'Previous Month','CY':'Current Year','PY':'Previous Year','A':'All'}" />
							<s:submit cssClass="token" action="LeaveApplication_go"
								value="     Go " /></td>
							

						</tr>


					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="22" width="100%" class="formhead"><strong
								class="forminnerhead">&nbsp;&nbsp;Leave List </strong></td>
						</tr>
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortable">
								<tr>
									<td valign="top" class="formth" width="5%">Sr No</td>
									<td width="30%" valign="top" class="formth">Leave Type</td>
									<td width="15%" valign="top" class="formth">From Date</td>
									<td width="15%" valign="top" class="formth">To Date</td>
									<td width="12%" valign="top" class="formth">Leave Days</td>
									<td width="23%" valign="top" class="formth">Status</td>
								</tr>
								<%
								int count = 0, p = 0;
								%>
								<s:iterator value="att" status="stat">
									<tr>
										<td class="sortableTD" align="center"><%=++count%><s:hidden name="srNo"
											value="%{<%=count%>}" />
										<td class="sortableTD" align="left"><s:property value="slevType" /><s:hidden
											name="slevType" /> <s:hidden name="slevCode" /></td>
										<td class="sortableTD" align="center"><s:property value="sleaveFromDtl" /><s:hidden
											name="sleaveFromDtl" /></td>
										<td class="sortableTD" align="center"><s:property value="sleaveToDtl" /><s:hidden
											name="sleaveToDtl" /></td>
										<td class="sortableTD" align="center"><s:property value="slevClosingBalance" /><s:hidden
											name="slevClosingBalance" /><s:hidden name="availBalance" /><s:hidden
											name="slevLeaveDays"></s:hidden><s:hidden name="closeBalance" />
										</td>
										<td class="sortableTD" align="left"><s:property value="leavestatus" /><s:hidden
											name="leavestatus"></s:hidden></td>
									</tr>
									<%
									p = count;
									%>
								</s:iterator>
								<%
								p = 0;
								%>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</td>
	</tr>
	</table>
	</td>
	</table>
</s:form>
