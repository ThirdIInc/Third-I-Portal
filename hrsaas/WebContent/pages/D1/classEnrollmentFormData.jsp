<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="ClassEnrollmentForm" name="ClassEnrollmentForm"
	id="paraFrm" theme="simple" validate="true">
		<s:hidden name="status" />
	<s:hidden name="hiddenCode" />
	<s:hidden name="trackingNo"/>

	<table width="100%" class="formbg" align="right">

		<tr>
			<td colspan="5">
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="92%" class="txt"><strong class="text_head">Class
					Enrollment Form</strong></td>
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
		  </s:if>
		<!-- Class Information -->
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
								
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
					<td width="25%"><label id="status" name="status"
						ondblclick="callShowDiv(this);"><%=label.get("status")%></label> :</td>
					<td width="25%"><s:select disabled="true" cssStyle="width:145"
						name="status"
						list="#{'D':'Draft','P':'Pending','B':'Sent Back','A':'Approved','R':'Rejected',
						'N':'Cancelled','F':'Forwarded','C':'Applied For Cancellation','X':'Cancellation Approved','Z':'Cancellation Rejected'}" />

					</td>
				</tr>
				
				<tr>

					<td width="25%" ><label id="course.name" name="course.name"
						ondblclick="callShowDiv(this);"><%=label.get("course.name")%></label>
					:<font color="red" id='ctrlHide'>*</font></td>
					
					<!--<td width="25%"><s:hidden name="courseId" /><s:textfield
									name="courseName" size="25" readonly="true" /> <img
									src="../pages/images/recruitment/search2.gif" class="iconImage" id='ctrlHide'
									height="18" align="absmiddle" width="18"
									onclick="javascript:callsF9(500,325,'ClassEnrollmentForm_f9CourseName.action');">
								</td>
					-->
					<td width="25%"><s:textfield
									name="courseName" size="25" maxlength="100" /> 
								</td>
					<td width="25%"><label id="course.location" name="course.location"
						ondblclick="callShowDiv(this);"><%=label.get("course.location")%></label>
					:<font color="red" id='ctrlHide'>*</font></td>
					<td width="25%"><s:textfield name="courseLocationName" size="25"
						maxlength="100" /></td>

				</tr>
				
				<tr>
					<td colspan="1" width="25%" class="formtext" height="22"><label
						name="start.date" id="start.date" ondblclick="callShowDiv(this);"><%=label.get("start.date")%></label>:<font color="red" id='ctrlHide'>*</font></td>
					<td colspan="1" width="25%"><s:textfield name="startDate"
						size="25" onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10" onfocus="clearText('paraFrm_startDate','dd-mm-yyyy');"/> <s:a
						href="javascript:NewCal('paraFrm_startDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"id='ctrlHide'
							height="16" align="absmiddle" width="16">
					</s:a></td>
					<td colspan="1" width="25%" class="formtext"><label
						name="end.date" id="end.date" ondblclick="callShowDiv(this);"><%=label.get("end.date")%></label>:<font color="red" id='ctrlHide'>*</font></td>
					<td colspan="1" width="25%"><s:textfield name="endDate"
						size="25" onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10" onfocus="clearText('paraFrm_endDate','dd-mm-yyyy');"/> <s:a
						href="javascript:NewCal('paraFrm_endDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"id='ctrlHide'
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

					<td width="25%"><s:hidden
						name="deptCode"  /> <s:textfield name="deptName" readonly="true"
						size="25" cssStyle="background-color: #F2F2F2;" /><img src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'ClassEnrollmentForm_f9deptNumber.action');"></td>
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
					:<font color="red" id='ctrlHide'>*</font></td>
					<td width="25%"><s:textfield name="streetAddress" size="25"
						maxlength="100" maxLength="60"/></td>
					<td width="25%" ><label name="city" id="city"
						ondblclick="callShowDiv(this);"><%=label.get("city")%></label> :<font color="red" id='ctrlHide'>*</font><font
						color="red" id='ctrlHide'></font></td>
					<td width="25%" ><s:textfield size="25"
						name="cityName"  maxlength="50"/> </td>
				</tr>
				<tr  id="usTable">
					<td width="25%"><label id="state" name="state"
						ondblclick="callShowDiv(this);"><%=label.get("state")%></label> :</td>
					<td width="25%"><s:textfield size="25" name="stateName" size="25" maxlength="50" /></td>

					<td width="25%"><label id="zip" name="zip"
						ondblclick="callShowDiv(this);"><%=label.get("zip")%></label> :</td>
					<td width="25%"><s:textfield name="stateZip" size="25"
						 maxlength="6" /></td>
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
						maxlength="6" /></td>
				</tr>

				<tr>
					<td width="25%"><label id="phone.number" name="phone.number"
						ondblclick="callShowDiv(this);"><%=label.get("phone.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="phoneNumber" size="25" maxlength="15" /></td>


					<td width="25%"><label id="fax.number" name="fax.number"
						ondblclick="callShowDiv(this);"><%=label.get("fax.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="faxNumber" size="25"
						 maxlength="15" /></td>
				</tr>

				<tr>
					<td width="25%"><label id="email.address" name="email.address"
						ondblclick="callShowDiv(this);"><%=label.get("email.address")%></label>
					:</td>
					<td width="25%"><s:textfield name="emailAddress" size="25" maxlength="150" /></td>


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

		
			<tr>
			<td width="100%" >
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="1" class="formbg">
					
					<tr >
						<td colspan="4" ><b><label id="form.approval" name="form.approval"
							ondblclick="callShowDiv(this);"><%=label.get("form.approval")%></label>
						:</b></td>
					</tr>
					<tr>
					<td ><label id="default.manager"
							name="default.manager" ondblclick="callShowDiv(this);"><%=label.get("default.manager")%></label>
						:</td>
					<s:hidden name="firstApproverCode" 

/><s:hidden
									name="firstApproverToken" />
					<s:iterator value="approverList">
									
										<td><s:hidden name="approverName" /><STRONG><s:property
											value="srNoIterator" /></STRONG> <s:property value="approverName" /></td>
									
								</s:iterator>
								
					</tr>
					<tr>
						<td width="20%"><label id="change.approval"
							name="change.approval" ondblclick="callShowDiv(this);"><%=label.get("change.approval")%></label>
						:<font color="red" id='ctrlHide'>*</font></td>
						<td width="60%" colspan="3"><s:textfield name="approverToken"
							size="25" theme="simple" readonly="true" cssStyle="background-color: #F2F2F2;"/> <s:textfield
							name="selectapproverName" size="71" theme="simple"
							readonly="true" cssStyle="background-color: #F2F2F2;"/> <s:hidden name="approverCode"/> 
							<img
							src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="16" id='ctrlHide'
							onclick="javascript:callsF9(500,325,'ClassEnrollmentForm_f9Approver.action');">
						</td>
					</tr>
					</table>
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
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%">
					
					</td>
				</tr>
			</table>
			</td>
		</tr>

	
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
		   document.getElementById('paraFrm_providence').readOnly='true'; 
		   document.getElementById('paraFrm_stateName').readOnly=''; 
		   document.getElementById('paraFrm_stateName').style.background='';  
		   document.getElementById('paraFrm_stateZip').style.background='';
		     document.getElementById('paraFrm_stateZip').readOnly=''; 
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
			document.getElementById('paraFrm_stateName').readOnly='true'; 
			document.getElementById('paraFrm_stateName').value=''; 
			 document.getElementById('paraFrm_stateZip').value=''; 
			document.getElementById('paraFrm_providence').readOnly=''; 
			document.getElementById('paraFrm_providence').style.background='';  
			document.getElementById('paraFrm_canadaZipcode').style.background=''; 
			document.getElementById('paraFrm_canadaZipcode').readOnly=''; 
			document.getElementById('paraFrm_providence').focus();
			document.getElementById('paraFrm_stateName').style.background='#F2F2F2';  
			document.getElementById('paraFrm_stateZip').style.background='#F2F2F2';
			
			
	  }
		else {
			 document.getElementById('paraFrm_providence').readOnly='true';
			 document.getElementById('paraFrm_stateName').readOnly='true';  
			document.getElementById('paraFrm_providence').style.background='#F2F2F2';  
			document.getElementById('paraFrm_stateName').style.background='#F2F2F2';  
			document.getElementById('paraFrm_canadaZipcode').readOnly='true';
			document.getElementById('paraFrm_canadaZipcode').style.background='#F2F2F2';  
			document.getElementById('paraFrm_stateZip').style.background='#F2F2F2';
			document.getElementById('paraFrm_stateZip').readOnly='true';  
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
	
	function sendforapprovalFun() {
	try
	{
		if(document.getElementById('paraFrm_courseName').value==""){
			alert("Please enter Course Name");
			document.getElementById('paraFrm_courseName').focus();
  			return false;
		}
		if(document.getElementById('paraFrm_courseLocationName').value==""){
			alert("Please enter Course location Name");
			document.getElementById('paraFrm_courseLocationName').focus();
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
			if(document.getElementById('paraFrm_employeeToken').value==""){
				alert("Please select a Employee");
				document.getElementById('paraFrm_employeeToken').focus();
	  			return false;
			}
			if(document.getElementById('paraFrm_streetAddress').value==""){
				alert("Please Enter Street Address");
				document.getElementById('paraFrm_streetAddress').focus();
	  			return false;
			}
			if(document.getElementById('paraFrm_cityName').value==""){
				alert("Please Enter City Name");
				document.getElementById('paraFrm_cityName').focus();
	  			return false;
			}
			
				try
			{
			if(!document.getElementById('paraFrm_emailAddress').value==''){
				var fields=["paraFrm_emailAddress"];
    var labels=["email.address"];
    var flag = ["enter"];
 	 if(!validateBlank(fields,labels,flag))
     return false;
	 
   if(!validateEmail('paraFrm_emailAddress')){
		 	return false;
		 }
		 }
		 
		}catch(e)
	{
	//alert("Exception occured in draft function : "+e);
	}
			
			
		}catch(e)
	{
		alert("Exception occured in draft function : "+e);
	}	

		var con = confirm('Do you really want to send this application for approval?');
	 	
	 	if(con) {
	 	
	 		document.getElementById('paraFrm_status').value = 'P';	
			document.getElementById('paraFrm').target = "_self";
    		document.getElementById('paraFrm').action='ClassEnrollmentForm_sendForApprovalFunction.action';
			document.getElementById('paraFrm').submit();
		}
	}

	function draftFun()
{	
	try
	{
		if(document.getElementById('paraFrm_courseName').value==""){
			alert("Please enter Course Name");
			document.getElementById('paraFrm_courseName').focus();
  			return false;
		}
		if(document.getElementById('paraFrm_courseLocationName').value==""){
			alert("Please enter Course location Name");
			document.getElementById('paraFrm_courseLocationName').focus();
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
			if(document.getElementById('paraFrm_employeeToken').value==""){
				alert("Please select a Employee");
				document.getElementById('paraFrm_employeeToken').focus();
	  			return false;
			}
			if(document.getElementById('paraFrm_streetAddress').value==""){
				alert("Please Enter Street Address");
				document.getElementById('paraFrm_streetAddress').focus();
	  			return false;
			}
			if(document.getElementById('paraFrm_cityName').value==""){
				alert("Please Enter City Name");
				document.getElementById('paraFrm_cityName').focus();
	  			return false;
			}
			
			try
			{
			if(!document.getElementById('paraFrm_emailAddress').value==''){
				var fields=["paraFrm_emailAddress"];
    var labels=["email.address"];
    var flag = ["enter"];
 	 if(!validateBlank(fields,labels,flag))
     return false;
	 
   if(!validateEmail('paraFrm_emailAddress')){
		 	return false;
		 }
		 }
		 
		}catch(e)
	{
	//alert("Exception occured in draft function : "+e);
	}
		
	}catch(e)
	{
		alert("Exception occured in draft function : "+e);
	}
		
				
		
			
		document.getElementById('paraFrm_status').value = 'D';	
			document.getElementById('paraFrm').target = "_self";
  			document.getElementById('paraFrm').action = 'ClassEnrollmentForm_draftFunction.action';
			document.getElementById('paraFrm').submit();
					
		  
	}
	
	

	function backtolistFun() 
{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ClassEnrollmentForm_back.action';
		document.getElementById('paraFrm').submit();
}
	function printFun() {	
	window.print();
	}
	function deleteFun() {
		var con = confirm('Do you want to delete the record(s) ?');
	 	
	 	if(con) {
			document.getElementById('paraFrm').target = "_self";
      		document.getElementById('paraFrm').action = 'ClassEnrollmentForm_delete.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function reportFun()  {
		alert("No Record To Display Report ");
	}

	function cancelApprovedFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action="ClassEnrollmentForm_cancel.action";
	  	document.getElementById('paraFrm').submit();  
	}
	function resetFun() 
{
		document.getElementById('paraFrm').target = "_self";     	
  		document.getElementById('paraFrm').action = 'ClassEnrollmentForm_reset.action';
     	document.getElementById('paraFrm').submit();
}
	
	

	
	
</script>