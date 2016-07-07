
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="ReimbursementClmAppl" method="post" id="paraFrm"
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
								class="style2"></span>Show History:</td>
							<td height="22" colspan="4"><s:select theme="simple"
								name="levstatus" cssStyle="width:150"
								list="#{'CM':'Current Month','CY':'Current Year','A':'All'}" />
							<s:submit cssClass="token" action="ReimbursementClmAppl_go"
								value="     Go " /></td>
							</td>

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
								class="forminnerhead">&nbsp;&nbsp;History</strong></td>
						</tr>
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortable">
								<tr>
									<td valign="top" class="formth" width="5%">Sr No</td>
									<td width="30%" valign="top" class="formth">Application ID</td>
									<td width="15%" valign="top" class="formth">Reimbursement Head</td>
									<td width="15%" valign="top" class="formth">Claim Date</td>
									<td width="12%" valign="top" class="formth">Claim Amount</td>
									<td width="12%" valign="top" class="formth">Claim Approved Amount</td>
									<td width="23%" valign="top" class="formth">Status</td>
								</tr>
								<%
								int count = 0, p = 0;
								%>
								<s:iterator value="claimHistory" status="stat">
									<tr>
										<td class="sortableTD"><%=++count%><s:hidden name="srNo"
											value="%{<%=count%>}" />
										<td class="sortableTD"><s:property value="applicationId" /></td>
										<td class="sortableTD"><s:property value="reimbursementHead" /></td>
										<td class="sortableTD" align="center"><s:property value="claimHistoryDate" />&nbsp;</td>
										<td class="sortableTD" align="center"><s:property value="claimHistoryAmount" />&nbsp;</td>
										<td class="sortableTD" align="center"><s:property value="claimHistoryApprovedAmount" />&nbsp;</td>
										<td class="sortableTD" align="center"><s:property value="claimHistoryStatus" />&nbsp;</td>
									</tr>
									<%
									p = count;
									%>
								</s:iterator>
								 
								<%
							if (p == 0) {
							%>
							<tr align="center">
								<td colspan="7" class="sortableTD" width="100%"><font
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
			</td>
		</tr>
	</table>
	</td>
	</tr>
	</table>
	</td>
	</table>
	<s:hidden name="historyCreditCode"></s:hidden>
</s:form>
