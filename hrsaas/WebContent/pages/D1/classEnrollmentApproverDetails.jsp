<!-- Added by manish sakpal -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="ClassEnrollmentApproverForm" validate="true" id="paraFrm" validate="true" theme="simple">
	<s:hidden name="status" />
		<s:hidden name="level" />
		<s:hidden name="classEnrollmentApproverId" />
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
					<td width="93%" class="txt"><strong class="text_head">Class Enrollment Approval</strong></td>
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
					<td width="100%" height="22"  colspan="4">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1" >
						
						<tr>
							<td colspan="4" width="25%">
							</td>
							<td width="25%">							
							<s:hidden name="trackingNo"/>
							</td>
							<td  width="25%"></td>
							<td width="25%"></td>
						</tr>
				</table>
				</td>
				</tr>
				
				<tr>
					<td colspan="4"><b><label class="set"
						name="class.infomation" id="class.infomation"
						ondblclick="callShowDiv(this);"><%=label.get("class.infomation")%></label></b>
					</td>
				</tr>
				<tr>
					<td><label class="set" name="course.appl.date" id="course.appl.date"
						ondblclick="callShowDiv(this);"><%=label.get("course.appl.date")%>
					</label> :</td>
					<td><s:textfield name="applDate" id="paraFrm_applDate" theme="simple" size="10"
						maxlength="10"
						onblur="return validateDate('paraFrm_appDate','Date');"
						onkeypress="return numbersWithHiphen();" readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
					<td></td>
					<td></td>
				</tr>
				
				<tr>

					<td width="25%" ><label id="course.name" name="course.name"
						ondblclick="callShowDiv(this);"><%=label.get("course.name")%></label>
					:<font color="red" id='ctrlHide'>*</font></td>
					
					<td width="25%"><s:hidden name="courseId" /><s:textfield
									name="courseName" size="25" readonly="true" /> <img
									src="../pages/images/recruitment/search2.gif" class="iconImage" id='ctrlHide'
									height="18" align="absmiddle" width="18"
									onclick="javascript:callsF9(500,325,'ClassEnrollmentForm_f9CourseName.action');">
								</td>
					<td width="25%"><label id="course.location" name="course.location"
						ondblclick="callShowDiv(this);"><%=label.get("course.location")%></label>
					:</td>
					<td width="25%"><s:textfield name="courseLocationName" size="25"
						maxlength="100" cssStyle="background-color: #F2F2F2;" readonly="true"/></td>

				</tr>
				

				<tr>


					<td colspan="1" width="25%" class="formtext" height="22"><label
						name="start.date" id="start.date" ondblclick="callShowDiv(this);"><%=label.get("start.date")%></label>:</td>
					<td colspan="1" width="25%"><s:textfield name="startDate"
						size="25" onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10" cssStyle="background-color: #F2F2F2;" readonly="true"/> <s:a
						href="javascript:NewCal('paraFrm_startDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage" id='ctrlHide'
							height="16" align="absmiddle" width="16">
					</s:a></td>
					<td colspan="1" width="25%" class="formtext"><label
						name="end.date" id="end.date" ondblclick="callShowDiv(this);"><%=label.get("end.date")%></label>:</td>
					<td colspan="1" width="25%"><s:textfield name="endDate"
						size="25" onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10" cssStyle="background-color: #F2F2F2;" readonly="true"/> <s:a
						href="javascript:NewCal('paraFrm_endDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage" id='ctrlHide'
							height="16" align="absmiddle" width="16">
					</s:a></td>
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
						name="attandance.infomation" id="attandance.infomation"
						ondblclick="callShowDiv(this);"><%=label.get("attandance.infomation")%></label></b>
					</td>
				</tr>

				<tr>
					<s:if test="bean.generalFlag">
						<td width="25%"><label id="emp.number" name="emp.number"
							ondblclick="callShowDiv(this);"><%=label.get("emp.number")%></label>
						:</td>
						<td width="25%" colspan="3"><s:textfield name="employeeToken"
							size="25" theme="simple" readonly="true" cssStyle="background-color: #F2F2F2;"/><s:textfield
							name="employeeName" size="71" theme="simple" readonly="true" />
						<s:hidden name="employeeCode" />
					</s:if>
					<s:else>
						<td width="25%"><label id="emp.number" name="emp.number"
							ondblclick="callShowDiv(this);"><%=label.get("emp.number")%></label>
						:</td>
						<td width="25%" colspan="3"><s:textfield name="employeeToken"
							size="25" theme="simple" readonly="true" cssStyle="background-color: #F2F2F2;"/><s:textfield
							name="employeeName" size="71" theme="simple" readonly="true" cssStyle="background-color: #F2F2F2;"/>
						<s:hidden name="employeeCode" />
						<s:if test="%{hiddenCode == ''}">
						<img
							src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="16" id='ctrlHide'
							onclick="javascript:callsF9(500,325,'ClassEnrollmentForm_f9Employee.action');">
						</s:if></td>
					</s:else>
				</tr>
				<tr>
					<td width="25%" align="left"><label id="area" name="area"
						ondblclick="callShowDiv(this);"><%=label.get("area")%></label> :</td>

					<td width="25%"><s:textfield name="deptName" readonly="true"
						size="25" cssStyle="background-color: #F2F2F2;" /></td>
					<td width="25%"></td>
					<td width="25%"></td>
					<!--<td width="25%"><label id="dept.number" name="dept.number"
						ondblclick="callShowDiv(this);"><%=label.get("dept.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="deptNumber" readonly="true"
						size="25" cssStyle="background-color: #F2F2F2;" /></td>

					-->
				</tr>


				
				<!--<tr>

					<td width="25%"><label id="attendee.manager"
						name="attendee.manager" ondblclick="callShowDiv(this);"><%=label.get("attendee.manager")%></label>
					:</td>
					<td colspan="3"><s:textfield name="attendeeToken" size="25"
						theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /><s:textfield
						name="attendeeName" size="80" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:hidden
						name="attendeeCode" /><img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'ClassEnrollmentForm_f9AttendeeManager.action');">
					</td><td width="25%"></td><td width="25%"></td>

				</tr>



			--></table>
			</td>
		</tr>

		<!-- Location Information -->
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="4"><b><label class="set"
						name="location.infomation" id="location.infomation"
						ondblclick="callShowDiv(this);"><%=label.get("location.infomation")%></label></b>
					</td>
				</tr>

				<tr>
					<td width="25%" class="formtext"><label name="location"
						id="location" ondblclick="callShowDiv(this);"><%=label.get("location")%></label>
					:</td>
					<td width="25%">
					<s:radio
						name="location" value="%{location}"
						list="#{'Us':' US &nbsp;'}"
						onclick="callLocationType();setlocationRadioValue(this);">
					</s:radio> <s:radio name="location" value="%{location}"
						list="#{'Ca':'Canada &nbsp;'}"
						onclick="callLocationType();setlocationRadioValue(this);"></s:radio> 
					
					<!--<s:radio
						name="location" 
						list="#{'Us':'US &nbsp;','Ca':'Canada'}"
						onclick="callLocationType();"></s:radio>
					
					
					--></td><td width="25%"></td><td width="25%"></td>
				</tr>
				<tr>
					<td width="25%"><label id="street.address"
						name="street.address" ondblclick="callShowDiv(this);"><%=label.get("street.address")%></label>
					:</td>
					<td width="25%"><s:textfield name="streetAddress" size="25"
						maxlength="100" maxLength="60"/></td>
					<td width="25%" ><label name="city" id="city"
						ondblclick="callShowDiv(this);"><%=label.get("city")%></label> :<font
						color="red" id='ctrlHide'></font></td>
					<td width="25%" ><s:textfield size="25"
						name="cityName"  maxlength="50"/> </td>
				</tr>
				<tr id="usTable">
					<td width="25%"><label id="state" name="state"
						ondblclick="callShowDiv(this);"><%=label.get("state")%></label> :</td>
					<td width="25%"><s:textfield size="25" name="stateName" size="25" maxlength="50" /></td>

					<td width="25%"><label id="zip" name="zip"
						ondblclick="callShowDiv(this);"><%=label.get("zip")%></label> :</td>
					<td width="25%"><s:textfield name="stateZip" size="25"
						 maxlength="6" onkeypress="return numbersOnly();"/></td>
				</tr>

				<tr id="canadaTable">
					<td width="25%"><label id="providence" name="providence"
						ondblclick="callShowDiv(this);"><%=label.get("providence")%></label>
					:</td>
					<td width="25%"><s:textfield name="providence" size="25" maxlength="50"/></td>


					<td width="25%"><label id="zipcode" name="zipcode"
						ondblclick="callShowDiv(this);"><%=label.get("zipcode")%></label>
					:</td>
					<td width="25%"><s:textfield name="canadaZipcode" size="25"
						maxlength="6" onkeypress="return numbersOnly();"/></td>
				</tr>

				<tr>
					<td width="25%"><label id="phone.number" name="phone.number"
						ondblclick="callShowDiv(this);"><%=label.get("phone.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="phoneNumber" size="25" maxlength="11" onkeypress="return numbersOnly();"/></td>


					<td width="25%"><label id="fax.number" name="fax.number"
						ondblclick="callShowDiv(this);"><%=label.get("fax.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="faxNumber" size="25"
						onkeypress="return numbersOnly();" maxlength="11" onkeypress="return numbersOnly();"/></td>
				</tr>

				<tr>
					<td width="25%"><label id="email.address" name="email.address"
						ondblclick="callShowDiv(this);"><%=label.get("email.address")%></label>
					:</td>
					<td width="25%"><s:textfield name="emailAddress" size="25" /></td>


					<!--<td width="25%"><label id="region" name="region"
						ondblclick="callShowDiv(this);"><%=label.get("region")%></label> :</td>
					<td width="25%"><s:textfield name="region" size="25"
						onkeypress="return numbersOnly();" maxlength="6" /></td>
				--></tr>

				<!--<tr>
					<td width="25%"><label id="service.area" name="service.area"
						ondblclick="callShowDiv(this);"><%=label.get("service.area")%></label>
					:</td>
					<td width="25%"><s:textfield name="serviceArea" size="25" /></td>
					<td width="25%"></td><td width="25%"></td>
				</tr>
			--></table>
			</td>
		</tr>

		<!-- Signature Information -->
		<!--<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="4"><b><label class="set" name="signatures"
						id="signatures" ondblclick="callShowDiv(this);"><%=label.get("signatures")%></label></b>
					</td>
				</tr>
				
				<tr>
						<td width="25%"><label id="initiator.sign" name="initiator.sign"
						ondblclick="callShowDiv(this);"><%=label.get("initiator.sign")%></label>
					:</td>
					<td width="25%"><s:textfield name="initiatorSign" 
						size="25" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
						
				<td  width="25%" class="formtext"><label
						name="initiator.submission.date" id="initiator.submission.date" ondblclick="callShowDiv(this);"><%=label.get("initiator.submission.date")%></label>:</td>
						
						<td colspan="1" width="30%"><s:textfield name="initiatorSubmissionDate"
						size="25" onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10"  cssStyle="background-color: #F2F2F2;"/> <s:a
						href="javascript:NewCal('paraFrm_initiatorSubmissionDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16">
					</s:a></td>
					
				</tr>
				
				<tr>
						<td width="25%"><label id="manager.sign" name="manager.sign"
						ondblclick="callShowDiv(this);"><%=label.get("manager.sign")%></label>
					:</td>
					<td width="25%"><s:textfield name="managerSign" 
						size="25" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
						
				<td width="25%" class="formtext"><label
						name="manager.submission.date" id="manager.submission.date" ondblclick="callShowDiv(this);"><%=label.get("manager.submission.date")%></label>:</td>
						
						<td  width="25%"><s:textfield name="managerSubmissionDate"
						size="25" onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10"  cssStyle="background-color: #F2F2F2;"/> <s:a
						href="javascript:NewCal('paraFrm_managerSubmissionDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16">
					</s:a></td>
					
				</tr>
			</table
			</td>
		</tr>

		--><tr>
						<td colspan="4">
							<table width="100%" border="0" align="center" cellpadding="2"
								cellspacing="1" class="formbg">
								
								<tr><td width="20%"><b>Completed By:</b></td>
								<td width="20%">
								<s:hidden name="initiatorCode"/>
								<s:property value="initiatorName"/>   </td>
								<td width="20%"><b>Completed On:</b></td>
								<td width="20%">
								<s:hidden name="initiatorDate"></s:hidden>
								<s:property value="initiatorDate"/>
								</td>
								</tr>
								</table></td></tr>
			
			<tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
			<s:hidden name="requestID" />
			<s:hidden name="creditMemoRadio" />
			
			<s:hidden name="listTypeDetailPage" />
			<input type="hidden" name="locationOption"
			id="locationOption"
			value='<s:property value="locationOption"/>' />
	</table>
</s:form>
<script>
radio()
	callLocationType();
	function callLocationType() {
		if(document.getElementById('paraFrm_locationUs').checked)
	  { 
	  document.getElementById('canadaTable').style.display='none';
			document.getElementById('usTable').style.display='';  
		  
		   document.getElementById('paraFrm_stateName').style.background='';  
		   document.getElementById('paraFrm_stateZip').style.background='';
		    
		   document.getElementById('paraFrm_providence').value=''; 
		   document.getElementById('paraFrm_canadaZipcode').value=''; 
		   document.getElementById('paraFrm_stateName').focus();
			document.getElementById('paraFrm_providence').style.background='#F2F2F2';
			document.getElementById('paraFrm_canadaZipcode').style.background='#F2F2F2';  
			
	  }
	   else if(document.getElementById('paraFrm_locationCa').checked)
	  {  
	  document.getElementById('canadaTable').style.display='';  
			document.getElementById('usTable').style.display='none';
			
			document.getElementById('paraFrm_stateName').value=''; 
			 document.getElementById('paraFrm_stateZip').value=''; 
			
			document.getElementById('paraFrm_providence').style.background='';  
			document.getElementById('paraFrm_canadaZipcode').style.background=''; 
			
			document.getElementById('paraFrm_providence').focus();
			document.getElementById('paraFrm_stateName').style.background='#F2F2F2';  
			document.getElementById('paraFrm_stateZip').style.background='#F2F2F2';
			
			
	  }
		else {
			
			document.getElementById('paraFrm_providence').style.background='#F2F2F2';  
			document.getElementById('paraFrm_stateName').style.background='#F2F2F2';  
			
			document.getElementById('paraFrm_canadaZipcode').style.background='#F2F2F2';  
			document.getElementById('paraFrm_stateZip').style.background='#F2F2F2';
		
		}
		
	}
	function radio()
	{
		 var barGainAgreement=document.getElementById('locationOption').value;
	 //alert(barGainAgreement);
	  if(barGainAgreement=='Us')
		 {//alert(barGainAgreement);
		 	 document.getElementById('paraFrm_locationUs').checked='Us';
		 	// document.getElementById('canadaTable').style.display='none';  
		 }
		else if(barGainAgreement=='Ca')
		 {
		 	//document.getElementById('canadaTable').style.display='';
		 //	document.getElementById('usTable').style.display='none';
		 }
		 else 
		 {
		 	document.getElementById('paraFrm_locationUs').checked='Us';
		 }
		
	}
	
	function setlocationRadioValue(id){
	//alert(id);
		var opt=document.getElementById('paraFrm_locationOption').value =id.value;	
}
function approveFun()
{
	var con = confirm("Do you really want to approve this application?");
	if(con)
	{
		if(document.getElementById('paraFrm_status').value == 'P')
		{
			document.getElementById('paraFrm_status').value = 'A';
		}
		
		if(document.getElementById('paraFrm_status').value == 'C')
		{
			document.getElementById('paraFrm_status').value = 'X';
		}
		
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action = 'ClassEnrollmentApproverForm_approveApplication.action';
		document.getElementById('paraFrm').submit();
	}
}

function rejectFun()
{
var con = confirm("Do you really want to reject this application?");
if(con)
	{
	document.getElementById('paraFrm').target = "_self";
    document.getElementById('paraFrm').action = 'ClassEnrollmentApproverForm_rejectApplication.action';
	document.getElementById('paraFrm').submit();
	}
}

function sendbackFun()
{
var con = confirm("Do you really want to sendback this application?");
	if(con)
	{
	document.getElementById('paraFrm').target = "_self";
    document.getElementById('paraFrm').action = 'ClassEnrollmentApproverForm_sendBackApplication.action';
	document.getElementById('paraFrm').submit();
	}
}

function printFun() {	
	window.print();
	}
function backtolistFun() 
{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ClassEnrollmentApproverForm_backToList.action';
		document.getElementById('paraFrm').submit();
}


function deleteFun() 
{
	 var con=confirm('Do you want to delete this application ?');
	 if(con)
	 {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ClassEnrollmentApproverForm_delete.action';
		document.getElementById('paraFrm').submit();
	}
}

function cancelapplicationFun() 
{
	 var con=confirm('Do you want to cancel this application ?');
	 if(con)
	 {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ClassEnrollmentApproverForm_cancel.action';
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