
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="LeaveEncashment" method="post" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">


						<tr>
						
							<td width="24%"><span class="formtext"> </span><span
								class="style2"></span></td>
							<td height="22" colspan="4"><s:select theme="simple"
								name="encashSelect" cssStyle="width:150"
								list="#{'CM':'Current Month','PM':'Previous Month','CY':'Current Year','PY':'Previous Year','A':'All'}" />
							<s:submit cssClass="token" action="LeaveEncashment_go"
								value=" Go" /></td>
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
								class="forminnerhead">&nbsp;&nbsp;Leave Encashment List
							</strong></td>
						</tr>
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortable">
								<tr>
									<td class="formtext">
									<table width="100%" border="0" cellpadding="1" cellspacing="1">
										<tr>
											<td class="formth">Sr No.</td>
											<td class="formth">Leave Type</td>
											<td class="formth">Balance Leave</td>
											<td class="formth">Onhold Balance(Pending for Approval)</td>
											<td class="formth">Leave To Be Encashed</td>
											<td class="formth">Amount</td>
											<td class="formth">Status</td>
											</tr>
										<%
										int count = 0, p = 0;
										%>
										<s:iterator value="leaveEn.list">
											<tr>
												<td width="5%" class="sortableTD"><%=++count%></td>
												<s:hidden name="leaveId" />

												<td width="25%" class="sortableTD"><s:property value="leaveType" /></td>

												<td width="15%" class="sortableTD"><s:property value="levCloseBalance" /></td>

												<td width="15%" class="sortableTD"><s:property value="leaveOnhold" /> </td>

												<td width="10%" class="sortableTD"><s:property value="leaveEncashed" /></td>

												<td width="20%" class="sortableTD"><s:property value="encashAmount" /></td>
												
												<td width="10%" class="sortableTD"><s:property value="encashStatus" /></td>


											</tr>
										</s:iterator>
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
		<s:hidden name="eId" />
</s:form>
