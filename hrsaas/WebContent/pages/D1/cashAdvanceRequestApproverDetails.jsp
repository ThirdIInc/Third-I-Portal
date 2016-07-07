<!-- Added by manish sakpal -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="CashAdvanceRequestApproval" validate="true" id="paraFrm" validate="true" theme="simple">
	
		<s:hidden name="level" />
		<s:hidden name="cashAdvApprHiddenID" />
		<s:hidden name="hrApprover"/>
		
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Cash Advance Request Approval</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</td>
					<td width="20%">
					<div align="right"><span class="style2"></span><font
						color="red">*</font>Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- Approver Comments Section Begins -->
		<tr>
			<td>
				<table width="100%" class="formbg">
					<s:if test="approverCommentsFlag">
					<tr>
						<td colspan="2" id="ctrlShow"><b>Approver Comments</b><font id='ctrlHide'
							color="red">*</font></td>
						<td colspan="3" id="ctrlShow"><s:textarea theme="simple" cols="70" rows="3"
							name="approverComments" id="approverComments" onkeypress="return imposeMaxLength(event, this, 500);"/></td>
					</tr>
					</s:if>
					<tr>
						<td width="10%" class="formth">Sr. No.</td>
						<td width="25%" class="formth">Approver Name</td>
						<td width="40%" class="formth">Comments</td>
						<td width="15%" class="formth">Date</td>
						<td width="10%" class="formth">Status</td>
					</tr>
					<%
						int count = 0;
					%>
					<s:iterator value="approverCommentList">
						<tr>
							<td class="sortableTD" align="center"><%=++count%></td>
							<td class="sortableTD"><s:property value="apprName" /></td>
							<td class="sortableTD"><s:property value="apprComments" /></td>
							<td class="sortableTD" align="center"><s:property value="apprDate" /></td>
							<td class="sortableTD"><s:property value="apprStatus" /></td>
						</tr>
					</s:iterator>
					<%
						if(count == 0) {
					%>
					<tr>
						<td width="100%" colspan="5" align="center"><font color="red">No
						Data To Display</font></td>
					</tr>
					<%
						}
					%>
				</table>
			 </td>
		  </tr>
		  <!-- Approver Comments Section Ends -->
		
		

		<!-- Class Information -->
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td width="100%" height="22" colspan="4">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1">

						<tr>

							<td width="100%">This Travel advance must be reconciled
							through Account payable within 30 days of the issue date of the
							check, or it will be deducted from employees paycheck <br>
							<b>Instruction For Approval</b><br>
							Employees should click Submit button to send this memo to VP for
							approval.<br>
							VP should then forward the memo to Finance.</td>

						</tr>
					</table>
					</td>
				</tr>



			</table>
			</td>
		</tr>

		<!-- Attandance Information -->
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="4"><b><label class="set"
						name="employeee.infomation" id="employeee.infomation"
						ondblclick="callShowDiv(this);"><%=label.get("employeee.infomation")%></label></b>
					</td>
				</tr>

				<tr>

					<td width="25%"><label id="emp.number" name="emp.number"
						ondblclick="callShowDiv(this);"><%=label.get("emp.number")%></label>
					:</td>
					<td width="25%" colspan="3"><s:textfield name="employeeToken"
						size="25" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /><s:textfield
						name="employeeName" size="71" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:hidden
						name="employeeCode" />
				</tr>
				<tr>

					<td width="25%" align="left"><label id="emp.designation" name="emp.designation"
						ondblclick="callShowDiv(this);"><%=label.get("emp.designation")%></label> :</td>

					<td width="25%"><s:textfield name="designation" readonly="true"
						size="25" cssStyle="background-color: #F2F2F2;" /></td>

					<td width="25%"><label id="no.employee.travel"
						name="no.employee.travel" ondblclick="callShowDiv(this);"><%=label.get("no.employee.travel")%></label>
					:</td>
					<td width="25%"><s:textfield name="noOfEmployeeTravel"
						size="25" /></td>

				</tr>
				<tr>
					<td width="25%"><label id="employee.address"
						name="employee.address" ondblclick="callShowDiv(this);"><%=label.get("employee.address")%></label>
					:</td>
					<td width="25%"><s:textarea name="employeeAddress" cols="26" rows="2" onkeypress="return imposeMaxLength(event, this, 500);"/></td>
					<td width="25%"><label name="city" id="city"
						ondblclick="callShowDiv(this);"><%=label.get("city")%></label> :<font
						color="red" id='ctrlHide'></font></td>
					<td width="25%"><s:textfield size="25" name="cityName"
						maxlength="50" /></td>
				</tr>
				<tr>


					<td width="25%"><label id="state" name="state"
						ondblclick="callShowDiv(this);"><%=label.get("state")%></label> :</td>
					<td width="25%"><s:textfield size="25" name="stateName"
						size="25" maxlength="50" /></td>



					<td width="25%"><label id="zip" name="zip"
						ondblclick="callShowDiv(this);"><%=label.get("zip")%></label> :</td>
					<td width="25%"><s:textfield name="stateZip" size="25"
						maxlength="6" onkeypress="return numbersOnly();" /></td>
				</tr>
				<tr>
					<td width="25%"><label id="phone.number" name="phone.number"
						ondblclick="callShowDiv(this);"><%=label.get("phone.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="phoneNumber" size="25"
						maxlength="11" onkeypress="return numbersOnly();" /></td>



				</tr>

			</table>
			</td>
		</tr>

		<!-- Location Information -->
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="4"><b><label class="set"
						name="travel.infomation" id="travel.infomation"
						ondblclick="callShowDiv(this);"><%=label.get("travel.infomation")%></label></b>
					</td>
				</tr>
				<tr>
					<td width="25%"><label id="business.purpose"
						name="business.purpose" ondblclick="callShowDiv(this);"><%=label.get("business.purpose")%></label>
					:</td>
					<td><s:textarea name="businessPurpose" cols="26" rows="2" onkeypress="return imposeMaxLength(event, this, 500);"/></td>

					<td width="25%" colspan="1"><label class="set"
						name="dept.change" id="dept.change"
						ondblclick="callShowDiv(this);"><%=label.get("dept.change")%></label>
					:<font id='ctrlHide' color="red"></font></td>

					<td width="25%" colspan="1"><s:textfield size="25"
						name="departmentName" readonly="true" /> <s:hidden name="departmentCode" />
					<img src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'CashAdvanceRequest_f9deptNumber.action');"></td>
				</tr>
				<tr>
					<td colspan="1" width="25%" class="formtext" height="22"><label
						name="start.date" id="start.date" ondblclick="callShowDiv(this);"><%=label.get("start.date")%></label>:</td>
					<td colspan="1" width="25%"><s:textfield name="startDate"
						size="25" onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10" /> <s:a
						href="javascript:NewCal('paraFrm_startDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage" id="ctrlHide"
							height="16" align="absmiddle" width="16">
					</s:a></td>
					<td colspan="1" width="25%" class="formtext"><label
						name="end.date" id="end.date" ondblclick="callShowDiv(this);"><%=label.get("end.date")%></label>:</td>
					<td colspan="1" width="25%"><s:textfield name="endDate"
						size="25" onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10" /> <s:a
						href="javascript:NewCal('paraFrm_endDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage" id="ctrlHide"
							height="16" align="absmiddle" width="16">
					</s:a></td>
				</tr>
				<tr>
					<td width="25%"><label id="trip.duration" name="trip.duration"
						ondblclick="callShowDiv(this);"><%=label.get("trip.duration")%></label>
					:</td>
					<td width="25%"><s:textfield name="tripDuration" size="25"
						maxlength="11" onkeypress="return numbersOnly();" /></td>

				</tr>
				<tr>
					<td width="25%"><label id="advance.amount"
						name="advance.amount" ondblclick="callShowDiv(this);"><%=label.get("advance.amount")%></label>
					:<br>
					(Limit - $1500)</td>
					<td width="25%"><s:textfield name="advanceAmount" size="25"
						maxlength="4" onkeypress="return numbersOnly();" /></td>

					<td colspan="1" width="25%" class="formtext"><label
						name="advance.needed.date" id="advance.needed.date"
						ondblclick="callShowDiv(this);"><%=label.get("advance.needed.date")%></label>:</td>
					<td colspan="1" width="25%"><s:textfield
						name="advanceNeededDate" size="25"
						onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10" /> <s:a
						href="javascript:NewCal('paraFrm_advanceNeededDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage" id="ctrlHide"
							height="16" align="absmiddle" width="16">
					</s:a></td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr id="PP">
					<td width="25%"><label id="comments" name="comments"
						ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>
					:</td>
					<td width="75%" ><s:textarea
						name="comments" cols="98" rows="3"
						onkeypress="return imposeMaxLength(event, this, 500);" /></td>
					
					
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">

				<tr>
					<td colspan="4"><b><label id="form.approval"
						name="form.approval" ondblclick="callShowDiv(this);"><%=label.get("form.approval")%></label>
					:</b></td>
				</tr>
				
				<tr>
					<td width="20%"><label id="vp.approval"
						name="vp.approval" ondblclick="callShowDiv(this);"><%=label.get("vp.approval")%></label>
					:<font color="red" id='ctrlHide'>*</font></td>
					<td width="60%" colspan="3"><s:textfield name="approverToken"
						size="25" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:textfield
						name="approverName" size="71" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:hidden
						name="approverCode" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'CashAdvanceRequest_f9Approver.action');">
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">

				<tr>
					<td width="20%"><b>Completed By:</b></td>
					<td width="20%"><s:hidden name="initiatorCode" /> <s:property
						value="initiatorName" /></td>
					<td width="20%"><b>Completed On:</b></td>
					<td width="20%"><s:hidden name="initiatorDate"></s:hidden> <s:property
						value="initiatorDate" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%"></td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="status" />

		<input type="hidden" name="locationOption" id="locationOption"
			value='<s:property value="locationOption"/>' />
	</table>
</s:form>

<script>

function approveFun()
{
	var con = confirm("Do you really want to approve this application?");
	if(con)
	{
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action = 'CashAdvanceRequestApproval_approveApplication.action';
		document.getElementById('paraFrm').submit();
	}
}

function rejectFun()
{
	var con = confirm("Do you really want to reject this application?");
	if(con)
	{
		document.getElementById('paraFrm').target = "_self";
	    document.getElementById('paraFrm').action = 'CashAdvanceRequestApproval_rejectApplication.action';
		document.getElementById('paraFrm').submit();
	}
}

function sendbackFun()
{
	var con = confirm("Do you really want to sendback this application?");
	if(con)
	{
		document.getElementById('paraFrm').target = "_self";
	    document.getElementById('paraFrm').action = 'CashAdvanceRequestApproval_sendBackApplication.action';
		document.getElementById('paraFrm').submit();
	}
}


function backtolistFun() 
{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CashAdvanceRequestApproval_backToList.action';
		document.getElementById('paraFrm').submit();
}

function printFun() {	
	window.print();
	}

function deleteFun() 
{
	 var con=confirm('Do you want to delete this application ?');
	 if(con)
	 {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CashAdvanceRequestApproval_delete.action';
		document.getElementById('paraFrm').submit();
	}
}

function cancelapplicationFun() 
{
	 var con=confirm('Do you want to cancel this application ?');
	 if(con)
	 {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CashAdvanceRequestApproval_cancel.action';
		document.getElementById('paraFrm').submit();
	}
}
function imposeMaxLength(Event, Object, MaxLen)
{
        return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
}
function closeFun() {
					window.close();
			}
</script>