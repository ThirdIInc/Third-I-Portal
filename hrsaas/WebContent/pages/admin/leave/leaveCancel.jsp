<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="../pages/common/Ajax.js"></script>
<%@include file="/pages/common/labelManagement.jsp" %>
	 
<s:form action="LeaveCancel" method="post" name="LeaveCancel"
	id="paraFrm" theme="simple" target="main">

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Leave
			Cancel Application</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
					<td colspan="2"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
				</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				<tr>
					<td><label
								name="application" id="application"
								ondblclick="callShowDiv(this);"><%=label.get("application")%></label> <font color="red">*</font>:</td>
					<td><s:textfield name="leave.empToken" theme="simple" size="20"
						readonly="true" cssStyle="background-color: #F2F2F2;"/>
					<s:textfield name="leave.empName" theme="simple" size="60"
						readonly="true" cssStyle="background-color: #F2F2F2;"/> <img
						src="../pages/images/recruitment/search2.gif" height="18"  class="iconImage"
						align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'LeaveCancel_f9appAction.action');">
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
					<td colspan="2"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
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
								class="forminnerhead"><label
								name="leaveDtl" id="leaveDtl"
								ondblclick="callShowDiv(this);"><%=label.get("leaveDtl")%></label>  </strong></td>
						</tr>
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortable">
								<tr class="td_bottom_border">
									<td valign="top" class="formth" width="10%"><label
								name="srno" id="srno"
								ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
									<td width="45%" valign="top" class="formth"><label
								name="levtype" id="levtype"
								ondblclick="callShowDiv(this);"><%=label.get("levtype")%></label></td>
									<td width="15%" valign="top" class="formth"><label
								name="frmdate" id="frmdate"
								ondblclick="callShowDiv(this);"><%=label.get("frmdate")%></label></td>
									<td width="15%" valign="top" class="formth"><label
								name="todate" id="todate"
								ondblclick="callShowDiv(this);"><%=label.get("todate")%></label></td>
									<td width="15%" valign="top" class="formth"><label
								name="levdays" id="levdays"
								ondblclick="callShowDiv(this);"><%=label.get("levdays")%></label></td>
								</tr>

								<%
								int count = 0, p = 0;
								%>
								<s:iterator value="att" status="stat">
									<tr class="sortableTD">
										<td width="10%" class="sortableTD"><%=++count%><s:hidden name="srNo"
											value="%{<%=count%>}" />
										<td width="45%" class="sortableTD"><s:property value="leaveType" /><s:hidden
											name="leaveType" /> <s:hidden name="leaveTypeCode" /></td>
										<td width="15%" class="sortableTD"><s:property value="leaveFromDate" /><s:hidden
											name="leaveFromDate" /></td>
										<td width="15%" class="sortableTD"><s:property value="leaveTodate" /><s:hidden
											name="leaveTodate" /></td>
										<td width="15%" align="center" class="sortableTD"><s:property value="leaveDays" /><s:hidden
											name="leaveDays" /></td>
										<td>
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
		<tr>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">

				<tr>

					<td align="center" width="20%" colspan="1">
					<input type="button" name="Submit222" class="token" value="Cancel Leaves" onclick="return cancelLeave(this);"/>
				</td>
				</tr>

			</table>
			</td>
		</tr>
	</table>
	<s:hidden name="leave.empCode" />
	<s:hidden name="leave.appDate" />
	<s:hidden name="leave.status" />
	<s:hidden name="leave.levAppCode" />
</s:form>

<script>

function cancelLeave(obj)
{

	var empcode = document.getElementById('paraFrm_leave_empCode').value ;
	
	if(empcode == "")
	{
		alert("Please select "+document.getElementById('application').innerHTML.toLowerCase());
		return false;
	}
	 var con=confirm('Do you want to cancel the application?');
	 if(con)
	 {
	 	obj.disabled=true; 
	 		document.getElementById("paraFrm").target="_self";
	 	document.getElementById("paraFrm").action="LeaveCancel_cancelLeaves.action";
	 	document.getElementById("paraFrm").submit();
	 }
 
		return true;
}
</script>
