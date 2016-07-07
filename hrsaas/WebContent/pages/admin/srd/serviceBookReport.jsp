<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="ServiceBook" id="paraFrm" validate="true" target="main" theme="simple">
	<table width="100%" class="formbg" align="right">
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="3%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif"
							width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Employee Service Book Report</strong></td>
						<td width="3%" valign="top" class="txt">
						<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<table width="100%">
					<tr>
						<td width="78%">
							<s:if test="%{sbk.generalFlag}"></s:if>
							<s:else>
								<input type="button" class="search" value="Search" onclick="javascript:callsF9(500,325,'ServiceBook_f9action.action');" />
							</s:else>
							<s:if test="%{sbk.viewFlag}">
								<s:submit cssClass="token" theme="simple" value='Report' action="ServiceBook_report" onclick="return callReport();" />
								
								<s:hidden name="repType" value="xls" />
							</s:if>
						</td>
						<td width="22%"><div align="right"><font color="red">*</font> Indicates Required</div></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
		<tr>
			<td colspan="3">
				<table width="100%" class="formbg">
					<tr>
						<td>
							<table width="100%" align="center">
								<tr>
									<td width="100%" colspan="5" class="text_head">
										<strong class="forminnerhead">
											<label class="set" id="employee.report" name="employee.report" ondblclick="callShowDiv(this);"><%=label.get("employee.report")%></label>
										</strong>
									</td>
								</tr>
								<tr>
									<td width="15%" class="formtext">
										<label id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :<font color="red">*</font>
									</td>
									<td width="85%" colspan="3" nowrap="nowrap">
										<s:textfield name="token" readonly="true" theme="simple" size="20" cssStyle="background-color: #F2F2F2;" />
										
										<s:textfield readonly="true" name="empName" theme="simple" size="100" cssStyle="background-color: #F2F2F2;" />
										
										<s:hidden name="empId" />
									</td>
									<td></td><td></td>
								</tr>
								<tr>
									<td width="15%" class="formtext">
										<label id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :
									</td>
									<td width="35%">
										<s:textfield name="center" theme="simple" size="40" readonly="true" cssStyle="background-color: #F2F2F2;" />
									</td>
									<td width="15%">
										<label id="designation" name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :
									</td>
									<td>
										<s:textfield name="rank" theme="simple" size="40" readonly="true" cssStyle="background-color: #F2F2F2;" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script>
	function callReport() {
		if(document.getElementById('paraFrm_empName').value == "") {
			alert("Please select the " + document.getElementById('employee').innerHTML.toLowerCase());
			return false;	
		}
				
		document.getElementById('paraFrm').target = "_blank";
				
		return true;
	}
</script>