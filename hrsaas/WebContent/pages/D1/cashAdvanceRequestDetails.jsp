<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="CashAdvanceRequest" name="CashAdvanceRequest"
	id="paraFrm" theme="simple" validate="true">
<s:hidden name="status" />
	<s:hidden name="hiddenCode" />
	<s:hidden name="trackingNo" />

	<table width="100%" class="formbg" align="right">

		<tr>
			<td colspan="5">
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="92%" class="txt"><strong class="text_head">Cash
					Advance Request Form</strong></td>
					<td width="4%" valign="middle" align="right" class="txt"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</td>
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
					<td width="20%">
					<div align="right"><span class="style2"></span><font
						color="red">*</font>Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Approver Comments Section Begins -->
		<s:if test="approverCommentFlag">
			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td colspan="5"><b>Approver Comments</b></td>
					</tr>
					<tr>
						<td width="10%" class="formth">Sr. No.</td>
						<td width="25%" class="formth">Approver Name</td>
						<td width="40%" class="formth">Comments</td>
						<td width="15%" class="formth">Approved Date</td>
						<td width="10%" class="formth">Status</td>
					</tr>
					<%
						int count = 0;
					%>
					<s:iterator value="approverCommentList">
						<tr>
							<td class="sortableTD"><%=++count%></td>
							<td class="sortableTD"><s:property value="apprName" /></td>
							<td class="sortableTD"><s:property value="apprComments" /></td>
							<td class="sortableTD" align="center"><s:property
								value="apprDate" /></td>
							<td class="sortableTD"><s:property value="apprStatus" /></td>
						</tr>
					</s:iterator>
					<%
						if (count == 0) {
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
		</s:if>
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
						name="employeeCode" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'CashAdvanceRequest_f9Employee.action');">
				</tr>
				<tr>

					<td width="25%" align="left"><label id="emp.designation" name="emp.designation"
						ondblclick="callShowDiv(this);"><%=label.get("emp.designation")%></label> :</td>

					<td width="25%"><s:textfield name="designation" readonly="true"
						size="25" cssStyle="background-color: #F2F2F2;" /></td>

					<td width="25%"><label id="no.employee.travel"
						name="no.employee.travel" ondblclick="callShowDiv(this);"><%=label.get("no.employee.travel")%></label>
					:<font color="red" id='ctrlHide'>*</font></td>
					<td width="25%"><s:textfield name="noOfEmployeeTravel"
						size="25" maxlength="4" onkeypress="return numbersOnly();" /></td>

				</tr>
				<tr>
					<td width="25%"><label id="employee.address"
						name="employee.address" ondblclick="callShowDiv(this);"><%=label.get("employee.address")%></label>
					:</td>
					<td width="25%"><s:textarea name="employeeAddress" cols="26" rows="2" onkeypress="return imposeMaxLength(event, this, 400);"/></td>
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
						maxlength="6" /></td>
				</tr>
				<tr>
					<td width="25%"><label id="phone.number" name="phone.number"
						ondblclick="callShowDiv(this);"><%=label.get("phone.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="phoneNumber" size="25"
						maxlength="15"  /></td>

					<td width="25%"><label id="status" name="status"
						ondblclick="callShowDiv(this);"><%=label.get("status")%></label> :</td>
					<td width="25%"><s:select disabled="true" cssStyle="width:145"
						name="status"
						list="#{'D':'Draft','P':'Pending','B':'Sent Back','A':'Approved','R':'Rejected',
						'N':'Cancelled','F':'Forwarded','C':'Applied For Cancellation','X':'Cancellation Approved','Z':'Cancellation Rejected'}" />

					</td>

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
					:<font color="red" id='ctrlHide'>*</font></td>
					<td><s:textarea name="businessPurpose" cols="26" rows="2" onkeypress="return imposeMaxLength(event, this, 400);"/></td>

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
						name="start.date" id="start.date" ondblclick="callShowDiv(this);"><%=label.get("start.date")%></label>:<font color="red" id='ctrlHide'>*</font></td>
					<td colspan="1" width="25%"><s:textfield name="startDate"
						size="25" onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10" /> <s:a
						href="javascript:NewCal('paraFrm_startDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage" id="ctrlHide"
							height="16" align="absmiddle" width="16">
					</s:a></td>
					<td colspan="1" width="25%" class="formtext"><label
						name="end.date" id="end.date" ondblclick="callShowDiv(this);"><%=label.get("end.date")%></label>:<font color="red" id='ctrlHide'>*</font></td>
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
					:<font color="red" id='ctrlHide'>*</font></td>
					<td width="25%"><s:textfield name="tripDuration" size="25"
						maxlength="3" onkeypress="return numbersOnly();" /></td>

				</tr>
				<tr>
					<td width="25%"><label id="advance.amount"
						name="advance.amount" ondblclick="callShowDiv(this);"><%=label.get("advance.amount")%></label>
					:<font color="red" id='ctrlHide'>*</font><br>
					(Limit - $1500)</td>
					<td width="25%"><s:textfield name="advanceAmount" size="25"
						maxlength="10" onkeypress="return numbersWithDot();"  /></td>

					<td colspan="1" width="25%" class="formtext"><label
						name="advance.needed.date" id="advance.needed.date"
						ondblclick="callShowDiv(this);"><%=label.get("advance.needed.date")%></label>:<font color="red" id='ctrlHide'>*</font></td>
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
		

		<input type="hidden" name="locationOption" id="locationOption"
			value='<s:property value="locationOption"/>' />
	</table>
</s:form>


<script>

	
	function sendforapprovalFun() {
	
		try
	{
		var noOfEmployeeTravelVar = document.getElementById('paraFrm_noOfEmployeeTravel').value;
		if(noOfEmployeeTravelVar=="")
		{
				alert("Please Enter Number of Employee Traveling");
		  		document.getElementById('paraFrm_noOfEmployeeTravel').focus();
		 		return false;
		}
		var businessPurposeVar = document.getElementById('paraFrm_businessPurpose').value;
		if(businessPurposeVar=="")
		{
				alert("Please Enter Business Purpose for Travel");
		  		document.getElementById('paraFrm_businessPurpose').focus();
		 		return false;
		}
		var startDateVar = document.getElementById('paraFrm_startDate').value;
		if(startDateVar=="")
		{
				alert("Please Enter or Select Start Date");
		  		document.getElementById('paraFrm_startDate').focus();
		 		return false;
		}
		if(!document.getElementById('paraFrm_startDate').value==''){		
			var check1= validateDate('paraFrm_startDate', 'start.date');
			if(!check1){
			return false;
		}
			}
			
		var endDateVar = document.getElementById('paraFrm_endDate').value;
		if(endDateVar=="")
		{
				alert("Please Enter or Select End Date");
		  		document.getElementById('paraFrm_endDate').focus();
		 		return false;
		}
		if(!document.getElementById('paraFrm_endDate').value==''){		
			var check1= validateDate('paraFrm_endDate', 'end.date');
			if(!check1){
			return false;
		}
			}
		
		if(!dateDifferenceEqual(document.getElementById('paraFrm_startDate').value, document.getElementById('paraFrm_endDate').value, 'paraFrm_startDate', 'start.date', 'end.date')){
	      document.getElementById('paraFrm_endDate').focus();
	      return false;
					      
   }
   
		var tripDurationeVar = document.getElementById('paraFrm_tripDuration').value;
		if(tripDurationeVar=="")
		{
				alert("Please Enter Trip Duration");
		  		document.getElementById('paraFrm_tripDuration').focus();
		 		return false;
		}
		var advanceAmountVar = document.getElementById('paraFrm_advanceAmount').value;
		if(advanceAmountVar=="")
		{
				alert("Please Enter Advanced Amount");
		  		document.getElementById('paraFrm_advanceAmount').focus();
		 		return false;
		}
		
		var advanceNeededDateVar = document.getElementById('paraFrm_advanceNeededDate').value;
		if(advanceNeededDateVar=="")
		{
				alert("Please Enter or Select Advanced Needed Date");
		  		document.getElementById('paraFrm_advanceNeededDate').focus();
		 		return false;
		}
		if(!document.getElementById('paraFrm_advanceNeededDate').value==''){		
			var check1= validateDate('paraFrm_advanceNeededDate', 'advance.needed.date');
			if(!check1){
			return false;
		}
			}
			
		if ((document.getElementById('paraFrm_advanceAmount').value) > 1500) {
    			 alert("Amount must be less than 1500\n");
    			document.getElementById('paraFrm_advanceAmount').focus();
		 		return false;
			}
		
		var approverVar = document.getElementById('paraFrm_approverName').value;
		
		if(approverVar=="")
		{
			
				alert("Please Select Approver Name.");
		  		document.getElementById('paraFrm_approverName').focus();
		 		return false;
		 		
		}
		
		if(isNaN(document.getElementById('paraFrm_noOfEmployeeTravel').value)){
			alert('Please enter Number of Employee Travel as a number');
			document.getElementById('paraFrm_noOfEmployeeTravel').focus();
			return false;			
			}
		
		if(isNaN(document.getElementById('paraFrm_tripDuration').value)){
			alert('Please enter Trip Duration as a number');
			document.getElementById('paraFrm_tripDuration').focus();
			return false;			
			}
		
		if(isNaN(document.getElementById('paraFrm_advanceAmount').value)){
			alert('Please enter Advance Amount as a number');
			document.getElementById('paraFrm_advanceAmount').focus();
			return false;			
			}
			
	}catch(e)
	{
		alert("Exception occured in draft function : "+e);
	}
		
			

		var con = confirm('Do you really want to send this application for approval?');
	 	
	 	if(con) {
	 	
	 		document.getElementById('paraFrm_status').value = 'P';	
			document.getElementById('paraFrm').target = "_self";
    		document.getElementById('paraFrm').action='CashAdvanceRequest_sendForApprovalFunction.action';
			document.getElementById('paraFrm').submit();
		}
	}

	function draftFun()
{	
	try
	{
		var noOfEmployeeTravelVar = document.getElementById('paraFrm_noOfEmployeeTravel').value;
		if(noOfEmployeeTravelVar=="")
		{
				alert("Please Enter Number of Employee Traveling");
		  		document.getElementById('paraFrm_noOfEmployeeTravel').focus();
		 		return false;
		}
		var businessPurposeVar = document.getElementById('paraFrm_businessPurpose').value;
		if(businessPurposeVar=="")
		{
				alert("Please Enter Business Purpose for Travel");
		  		document.getElementById('paraFrm_businessPurpose').focus();
		 		return false;
		}
		var startDateVar = document.getElementById('paraFrm_startDate').value;
		if(startDateVar=="")
		{
				alert("Please Enter or Select Start Date");
		  		document.getElementById('paraFrm_startDate').focus();
		 		return false;
		}
		if(!document.getElementById('paraFrm_startDate').value==''){		
			var check1= validateDate('paraFrm_startDate', 'start.date');
			if(!check1){
			return false;
		}
			}
			
		var endDateVar = document.getElementById('paraFrm_endDate').value;
		if(endDateVar=="")
		{
				alert("Please Enter or Select End Date");
		  		document.getElementById('paraFrm_endDate').focus();
		 		return false;
		}
		if(!document.getElementById('paraFrm_endDate').value==''){		
			var check1= validateDate('paraFrm_endDate', 'end.date');
			if(!check1){
			return false;
		}
			}
		if(!dateDifferenceEqual(document.getElementById('paraFrm_startDate').value, document.getElementById('paraFrm_endDate').value, 'paraFrm_startDate', 'start.date', 'end.date')){
	      document.getElementById('paraFrm_endDate').focus();
	      return false;
					      
   }
		var tripDurationeVar = document.getElementById('paraFrm_tripDuration').value;
		if(tripDurationeVar=="")
		{
				alert("Please Enter Trip Duration");
		  		document.getElementById('paraFrm_tripDuration').focus();
		 		return false;
		}
		var advanceAmountVar = document.getElementById('paraFrm_advanceAmount').value;
		if(advanceAmountVar=="")
		{
				alert("Please Enter Advanced Amount");
		  		document.getElementById('paraFrm_advanceAmount').focus();
		 		return false;
		}
		
		var advanceNeededDateVar = document.getElementById('paraFrm_advanceNeededDate').value;
		if(advanceNeededDateVar=="")
		{
				alert("Please Enter or Select Advanced Needed Date");
		  		document.getElementById('paraFrm_advanceNeededDate').focus();
		 		return false;
		}
		
		if(!document.getElementById('paraFrm_advanceNeededDate').value==''){		
			var check1= validateDate('paraFrm_advanceNeededDate', 'advance.needed.date');
			if(!check1){
			return false;
		}
			}
			
			
		if ((document.getElementById('paraFrm_advanceAmount').value) > 1500) {
    			 alert("Amount must be less than 1500\n");
    			document.getElementById('paraFrm_advanceAmount').focus();
		 		return false;
			}
		
		var approverVar = document.getElementById('paraFrm_approverName').value;
		
		if(approverVar=="")
		{
			
				alert("Please Select Approver Name.");
		  		document.getElementById('paraFrm_approverName').focus();
		 		return false;
		 		
		}
		
		
		if(isNaN(document.getElementById('paraFrm_noOfEmployeeTravel').value)){
			alert('Please enter Number of Employee Travel as a number');
			document.getElementById('paraFrm_noOfEmployeeTravel').focus();
			return false;			
			}
		
		if(isNaN(document.getElementById('paraFrm_tripDuration').value)){
			alert('Please enter Trip Duration as a number');
			document.getElementById('paraFrm_tripDuration').focus();
			return false;			
			}
		
		if(isNaN(document.getElementById('paraFrm_advanceAmount').value)){
			alert('Please enter Advance Amount as a number');
			document.getElementById('paraFrm_advanceAmount').focus();
			return false;			
			}
			
	}catch(e)
	{
		alert("Exception occured in draft function : "+e);
	}
		
				
		
			
		document.getElementById('paraFrm_status').value = 'D';	
			document.getElementById('paraFrm').target = "_self";
  			document.getElementById('paraFrm').action = 'CashAdvanceRequest_draftFunction.action';
			document.getElementById('paraFrm').submit();
					
		  
	}
	
	

	function backtolistFun() 
{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CashAdvanceRequest_back.action';
		document.getElementById('paraFrm').submit();
}
	function printFun() {	
	window.print();
	}
	
	function deleteFun() {
		var con = confirm('Do you want to delete the record(s) ?');
	 	
	 	if(con) {
			document.getElementById('paraFrm').target = "_self";
      		document.getElementById('paraFrm').action = 'CashAdvanceRequest_delete.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function reportFun()  {
		alert("No Record To Display Report ");
	}

	function cancelApprovedFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action="CashAdvanceRequest_cancel.action";
	  	document.getElementById('paraFrm').submit();  
	}
	function resetFun() 
{
		document.getElementById('paraFrm').target = "_self";     	
  		document.getElementById('paraFrm').action = 'CashAdvanceRequest_reset.action';
     	document.getElementById('paraFrm').submit();
}
	
	function imposeMaxLength(Event, Object, MaxLen)
{
        return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
}	

	
	
</script>