<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="PersonalDataChange" name="PersonalDataChange"
	id="paraFrm" theme="simple" validate="true">
	<s:hidden name="persDataChangeId" />
	<s:hidden name="hiddenCode" />
	<s:hidden name="applnStatus" />
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
					<td width="92%" class="txt"><strong class="text_head">Personal
					Data Change Form</strong></td>
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
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">

				<tr>
					<td colspan="4"><b><label class="set"
						name="emp.infomation" id="emp.infomation"
						ondblclick="callShowDiv(this);"><%=label.get("emp.infomation")%></label></b>
					</td>
				</tr>
				<tr>
					<td colspan="4">Note : <font color="">This form may be
					used by employees to notify Human Resources, Payroll, and the
					employees manager of changes to personal data. <br />
					Complete only those sections which require change.</font></td>
				</tr>
				<tr>
					<td width="25%"><label id="application.date"
						name="application.date" ondblclick="callShowDiv(this);"><%=label.get("application.date")%></label>
					:</td>
					<td width="25%"><s:textfield name="applicationDate"
						readonly="true" size="25" cssStyle="background-color: #F2F2F2;" /></td>


				</tr>

				<tr>
					<s:if test="bean.generalFlag">
						<td width="25%"><label id="emp.number" name="emp.number"
							ondblclick="callShowDiv(this);"><%=label.get("emp.number")%></label>
						:</td>
						<td width="25%" colspan="3"><s:textfield name="employeeToken"
							size="25" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" /><s:textfield
							name="employeeName" size="71" theme="simple" readonly="true" />
						<s:hidden name="employeeCode" />
					</s:if>
					<s:else>
						<td width="25%"><label id="emp.number" name="emp.number"
							ondblclick="callShowDiv(this);"><%=label.get("emp.number")%></label>
						:</td>
						<td width="25%" colspan="3"><s:textfield name="employeeToken"
							size="25" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" /><s:textfield
							name="employeeName" size="71" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" /> <s:hidden
							name="employeeCode" /> <s:if test="%{hiddenCode == ''}">
							<img src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16" id='ctrlHide'
								onclick="javascript:callsF9(500,325,'PersonalDataChange_f9Employee.action');">
						</s:if></td>
					</s:else>
				</tr>
				<tr>
					<td width="25%"><label id="area" name="area"
						ondblclick="callShowDiv(this);"><%=label.get("area")%></label> :</td>
					<!--<td width="25%"><s:textfield name="areaType" readonly="true"
						size="25" cssStyle="background-color: #F2F2F2;"/><s:hidden name="deptNumber"   />
					
					
					-->
					<td width="25%" colspan="1"><s:hidden name="deptCode" /> <s:textfield
						name="deptName" size="25" readonly="true"
							cssStyle="background-color: #F2F2F2;" /> <!--<img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'PersonalDataChange_f9deptNumber.action');">-->
						
						</td>



					</td>

					<td width="25%" align="left"><label id="work.phone"
						name="work.phone" ondblclick="callShowDiv(this);"><%=label.get("work.phone")%></label>
					:</td>

					<td width="25%"><s:textfield name="workPhone" size="25"
						maxlength="15" /></td>



				</tr>
				<tr>


					<td width="25%"><label id="status" name="status"
						ondblclick="callShowDiv(this);"><%=label.get("status")%></label> :</td>
					<td width="25%"><s:select disabled="true" cssStyle="width:145"
						name="applnStatus"
						list="#{'D':'Draft','P':'Pending','B':'Sent Back','A':'Approved','R':'Rejected',
						'N':'Cancelled','F':'Forwarded','C':'Applied For Cancellation','X':'Cancellation Approved','Z':'Cancellation Rejected'}" />

					</td>
				</tr>

			</table>
			</td>
		</tr>
		<!-- change.personal.infomation  -->
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="4"><b><label class="set"
						name="change.personal.infomation" id="change.personal.infomation"
						ondblclick="callShowDiv(this);"><%=label.get("change.personal.infomation")%></label></b>
					</td>
				</tr>
				<tr>
					<td colspan="4">Note : <font color="">If there is a
					legal change in name a copy of the employee's new Social Security
					card must be faxed to (610) 993-6334 in addition to the completion
					of this form. </font></td>
				</tr>
				<tr>
					<td colspan="4"><b><label class="set"
						name="change.name.to" id="change.name.to"
						ondblclick="callShowDiv(this);"><%=label.get("change.name.to")%></label>
					: </b></td>
				</tr>
				<tr>
					<td width="25%%" colspan="1"><label id="first.name"
						name="first.name" ondblclick="callShowDiv(this);"><%=label.get("first.name")%></label>
					:</td>
					<td width="25%"><s:textfield name="firstName" size="25"
						maxlength="100" /></td>

					<td width="25%"><label id="initial.name" name="initial.name"
						ondblclick="callShowDiv(this);"><%=label.get("initial.name")%></label>
					:</td>
					<td width="25%"><s:textfield name="initialName" size="25"
						maxlength="100" /></td>
				</tr>
				<tr>
					<td width="25%" colspan="1"><label id="last.name"
						name="last.name" ondblclick="callShowDiv(this);"><%=label.get("last.name")%></label>
					:</td>
					<td width="25%"><s:textfield name="lastName" size="25"
						maxlength="100" /></td>

					<td width="25%" colspan="1"><label class="set"
						id="change.marital.status" name="change.marital.status"
						ondblclick="callShowDiv(this);"><%=label.get("change.marital.status")%></label>
					:</td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:145" name="maritalStatus"
						list="#{'U':'Single ','M':'Married','D':'Divorce ','A':'Widower ','W':'Widow'}" /></td>

				</tr>

				<!-- Change Address to -->
				<tr>
					<td colspan="4"><b><label class="set"
						name="change.address.to" id="change.address.to"
						ondblclick="callShowDiv(this);"><%=label.get("change.address.to")%></label>
					</b></td>
				</tr>
				<tr>
					<td width="25%" colspan="1"><label id="street.address"
						name="street.address" ondblclick="callShowDiv(this);"><%=label.get("street.address")%></label>
					:</td>
					<td width="25%"><s:textarea name="streetAddress" cols="26"
						rows="2" onkeypress="return imposeMaxLength(event, this, 500);" /><img
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						id='ctrlHide' width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_streetAddress','street.address','','500','500');"></td>
					
					<td width="25%"><label id="area" name="area"
						ondblclick="callShowDiv(this);"><%=label.get("area")%></label> :</td>
					<td width="25%" colspan="1"><s:hidden name="empDeptCode" /> <s:textfield
						name="empDeptName" size="25" readonly="true"
							cssStyle="background-color: #F2F2F2;"/> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'PersonalDataChange_f9deptEmpNumber.action');"></td>
				</tr>
				<tr>
					<td width="25%" colspan="1"><label name="city" id="city"
						ondblclick="callShowDiv(this);"><%=label.get("city")%></label> :<font
						color="red" id='ctrlHide'></font></td>
					<td width="25%" colspan="1"><s:textfield size="25"
						name="cityName" /></td>


					<td width="25%"><label id="country" name="country"
						ondblclick="callShowDiv(this);"><%=label.get("country")%></label>
					:</td>
					<td width="25%"><s:textfield name="country" size="25" /></td>
				</tr>
				<tr>

					<td width="25%"><label id="state.prov" name="state.prov"
						ondblclick="callShowDiv(this);"><%=label.get("state.prov")%></label>
					:</td>
					<td width="25%"><s:textfield size="25" name="stateName"
						size="25" /></td>
					<td width="25%"><label id="zip" name="zip"
						ondblclick="callShowDiv(this);"><%=label.get("zip")%></label> :</td>
					<td width="25%"><s:textfield name="zip" size="25"
						maxlength="6" /></td>
				</tr>
				<tr>
					<td width="25%"><label id="change.home.phone.number"
						name="change.home.phone.number" ondblclick="callShowDiv(this);"><%=label.get("change.home.phone.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="homePhoneNumber" size="25"
						maxlength="15" /></td>

					<td width="25%"><label id="change.work.phone.number"
						name="change.work.phone.number" ondblclick="callShowDiv(this);"><%=label.get("change.work.phone.number")%></label>
					:</td>
					<td width="30%"><s:textfield name="workPhoneNumber" size="25"
						maxlength="15" /></td>
				</tr>
				<tr class="clsTRBody">

					<td width="25%" colspan="1"><label class="set"
						name="move.date" id="move.date" ondblclick="callShowDiv(this);"><%=label.get("move.date")%></label>
					:<font id='ctrlHide' color="red">*</font></td>

					<td width="25%" colspan="1" align="left" width="15%"><s:textfield
						name="moveDate" size="25" onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_moveDate','dd-mm-yyyy');" /> <s:a
						href="javascript:NewCal('paraFrm_moveDate','DDMMYYYY');">
						<img src="../pages/common/css/default/images/Date.gif" width="16"
							height="16" border="0" id='ctrlHide' />
					</s:a></td>

					<td width="25%"><label id="email.address" name="email.address"
						ondblclick="callShowDiv(this);"><%=label.get("email.address")%></label>
					:</td>
					<td width="25%"><s:textfield name="emailAddress" size="25"
						maxlength="150"  /></td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- Emergency Contact information -->
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="4"><b><label class="set"
						name="emergency.contact.information"
						id="emergency.contact.information" ondblclick="callShowDiv(this);"><%=label.get("emergency.contact.information")%></label>
					: </b></td>
				</tr>
				<tr>
					<td width="25%"><label id="change.emergency.contact.to"
						name="change.emergency.contact.to" ondblclick="callShowDiv(this);"><%=label.get("change.emergency.contact.to")%></label>
					:</td>
					<td width="25%"><s:textfield name="emergencyName" size="25"
						maxlength="100" /></td>


					<td width="25%"><label id="relation.to.employee"
						name="relation.to.employee" ondblclick="callShowDiv(this);"><%=label.get("relation.to.employee")%></label>
					:</td>
					<td width="25%" colspan="1"><s:textfield size="25"
						name="relationName" readonly="true" /> <s:hidden
						name="relationCode" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'PersonalDataChange_f9relation.action');"></td>
				</tr>
				<tr>
					<td width="25%"><label id="work.phone.number"
						name="work.phone.number" ondblclick="callShowDiv(this);"><%=label.get("work.phone.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="workPhoneNumberEmergency"
						size="25" maxlength="15" /></td>


					<td width="25%"><label id="home.phone.number"
						name="home.phone.number" ondblclick="callShowDiv(this);"><%=label.get("home.phone.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="homePhoneNumberEmergency"
						size="25" maxlength="15" /></td>
				</tr>
				<tr>
					<td width="25%"><label id="same.address.employee"
						name="same.address.employee" ondblclick="callShowDiv(this);"><%=label.get("same.address.employee")%></label>
					:</td>
					<td width="25%"><s:select cssStyle="width:100px"
						name="sameAddressType" cssStyle="width:145"
						list="#{'Y':'Yes ','N':'No'}" onchange="return callAddressType();" />
					<td width="25%"><label id="relation.address"
						name="relation.address" ondblclick="callShowDiv(this);"><%=label.get("relation.address")%></label>
					:</td>
					<td width="25%"><s:textarea name="relationAddress" cols="27"
						rows="2" id="relationAddress" /></td>
				</tr>
				<tr>
					<td colspan="4">Note : <font color="">When you Submit
					this form, it will automatically sent to Approvar as you selected
					below . </font></td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="forApproval">
			<tr>
				<td colspan="5">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="80%" colspan="2"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="20%"></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<s:else>
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
						<td><label id="default.manager" name="default.manager"
							ondblclick="callShowDiv(this);"><%=label.get("default.manager")%></label>
						:</td>
						<s:hidden name="firstApproverCode" />
						<s:hidden name="firstApproverToken" />
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
							size="25" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" /> <s:textfield
							name="selectapproverName" size="71" theme="simple"
							readonly="true" cssStyle="background-color: #F2F2F2;" /> <s:hidden
							name="approverCode" /> <img
							src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="16" id='ctrlHide'
							onclick="javascript:callsF9(500,325,'PersonalDataChange_f9Approver.action');">
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
				<td colspan="5">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="5">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="80%" colspan="2"><jsp:include
									page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:else>
	</table>
</s:form>


<script>

callAddressType();
	

	
	function callAddressType() {
		var addressCode= document.getElementById('paraFrm_sameAddressType').value;
		
		if(addressCode == 'N') {
			document.getElementById('relationAddress').readOnly=''; 
			document.getElementById('relationAddress').style.background='white';
		} else {
			document.getElementById('relationAddress').value='';
			document.getElementById('relationAddress').readOnly='true'; 
			document.getElementById('relationAddress').style.background='#F2F2F2'; 
		}
	}
	
	
	
	function approveFun() {
		var conf = confirm('Do you want to approve the application?');
		if(conf) {
			document.getElementById('paraFrm').action="PersonalDataChange_approve.action";
			document.getElementById('paraFrm').submit();
		}
	}
	
	function rejectFun() {
		var conf = confirm('Do you want to reject the application?');
		if(conf) {
			document.getElementById('paraFrm').action="PersonalDataChange_reject.action";
			document.getElementById('paraFrm').submit();
		}
	}
	
	function sendbackFun() {
		var conf = confirm('Do you want to send back the application?');
		if(conf) {
			document.getElementById('paraFrm').action="PersonalDataChange_sendBack.action";
			document.getElementById('paraFrm').submit();
		}
	}

	
	
	function sendforapprovalFun() {
		if(document.getElementById('paraFrm_employeeToken').value==""){
			alert("Please select a Employee");
  			return false;
		}
	
	
		
		if(trim(document.getElementById('paraFrm_moveDate').value) == "") {
			alert("Please select or enter the " + document.getElementById('move.date').innerHTML.toLowerCase());
			document.getElementById('paraFrm_moveDate').focus();
			return false;
		}
			
		
			if(!document.getElementById('paraFrm_moveDate').value==''){		
			var check1= validateDate('paraFrm_moveDate', 'move.date');
			if(!check1){
			return false;
		}
			}
		
		
		var firstApproverCodeVar = document.getElementById('paraFrm_firstApproverCode').value;
		
		if(firstApproverCodeVar=="")
		{
			var selectapproverNameVar = trim(document.getElementById('paraFrm_selectapproverName').value);
			if(selectapproverNameVar == "")
			{
				alert("Please Select Approver.");
		  		document.getElementById('paraFrm_selectapproverName').focus();
		 		return false;
		 	}	
		}
		try
			{
			if(!document.getElementById('paraFrm_emailAddress').value==''){
				var fields=["paraFrm_emailAddress"];
    var labels=["email.address"];
    var flag = ["enter"];
 	 if(!validateBlank(fields,labels,flag))
     return false;
	 
   if(!validateEmailAPS('paraFrm_emailAddress')){
		 	return false;
		 }
		 }
		 
		}catch(e)
	{
	//alert("Exception occured in draft function : "+e);
	}

		var con = confirm('Do you really want to send this application for approval?');
	 	
	 	if(con) {
	 	
	 		document.getElementById('paraFrm_applnStatus').value = 'P';	
			document.getElementById('paraFrm').target = "_self";
    		document.getElementById('paraFrm').action='PersonalDataChange_sendForApprovalFunction.action';
			document.getElementById('paraFrm').submit();
		}
	}

	function draftFun() {
		try {
			
			try {
			if(trim(document.getElementById('paraFrm_moveDate').value) == "") {
				alert("Please select or enter the "+document.getElementById('move.date').innerHTML.toLowerCase());
				document.getElementById('paraFrm_moveDate').focus();
				return false;
			}
			if(!document.getElementById('paraFrm_moveDate').value==''){		
			var check1= validateDate('paraFrm_moveDate', 'move.date');
			if(!check1){
			return false;
		}
			}
		
			} catch(e) 
		{
			alert("Exception occured in draft function : "+e);
		}
		try {	
		var firstApproverCodeVar = document.getElementById('paraFrm_firstApproverCode').value;
		
		if(firstApproverCodeVar=="")
		{
			var selectapproverNameVar = trim(document.getElementById('paraFrm_selectapproverName').value);
			if(selectapproverNameVar == "")
			{
				alert("Please Select Approver.");
		  		document.getElementById('paraFrm_selectapproverName').focus();
		 		return false;
		 	}	
		 	
		}
		} catch(e) 
		{
			alert("Exception occured in draft function : "+e);
		}
		
			try
			{
			if(!document.getElementById('paraFrm_emailAddress').value==''){
				var fields=["paraFrm_emailAddress"];
    var labels=["email.address"];
    var flag = ["enter"];
 	 if(!validateBlank(fields,labels,flag))
     return false;
	 
   if(!validateEmailAPS('paraFrm_emailAddress')){
		 	return false;
		 }
		 }
		 
		}catch(e)
	{
	//alert("Exception occured in draft function : "+e);
	}
			
		} catch(e) 
		{
			alert("Exception occured in draft function : "+e);
		}
		
		document.getElementById('paraFrm_applnStatus').value = 'D';	
			document.getElementById('paraFrm').target = "_self";
  			document.getElementById('paraFrm').action = 'PersonalDataChange_draftFunction.action';
			document.getElementById('paraFrm').submit();
	}

		function backtolistFun() 
{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'PersonalDataChange_back.action';
		document.getElementById('paraFrm').submit();
}
	function printFun() {	
	window.print();
	}
	
	function deleteFun() {
		var con = confirm('Do you want to delete the record(s) ?');
	 	
	 	if(con) {
			document.getElementById('paraFrm').target = "_self";
      		document.getElementById('paraFrm').action = 'PersonalDataChange_delete.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function reportFun()  {
		alert("No Record To Display Report ");
	}

	function cancelApprovedFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action="PersonalDataChange_cancel.action";
	  	document.getElementById('paraFrm').submit();  
	}
	function resetFun() 
{
		document.getElementById('paraFrm').target = "_self";     	
  		document.getElementById('paraFrm').action = 'PersonalDataChange_reset.action';
     	document.getElementById('paraFrm').submit();
}
function imposeMaxLength(Event, Object, MaxLen)
{
        return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
}
function callLength(type){
		
		 if(type=='addCnt'){
					
				//alert("i am in calllength method>>>>>>");
					
					var add =document.getElementById('paraFrm_streetAddress').value;
					var remain = 1000 - eval(add .length);
					document.getElementById('paraFrm_addCnt').value = remain;
					
						if(eval(remain)< 0){
					document.getElementById('paraFrm_streetAddress').style.background = '#FFFF99';
					
					}else document.getElementById('paraFrm_streetAddress').style.background = '#FFFFFF';
				
				}
				} 
				
	function addConfigEmail() {
	
	/*alert("emailid");*/
	try{
	var fields=["paraFrm_emailAddress"];
    var labels=["email.address"];
    var flag = ["enter"];
   
 	 if(!validateBlank(fields,labels,flag))
     return false;
	
   if(!validateEmailAPS('paraFrm_emailAddress')){
		 	return false;
		 }
		 
		 }catch(e)
		 {
		 alert(e);
		 }
	}
	
	
	function validateEmailAPS (name) {
	var name1 = document.getElementById(name).value.toLowerCase();
	var emailStr        = name1;
	if(emailStr=="")return true;
	
	var checkTLD        = 1;
	
	var knownDomsPat    = /^(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum)$/;
	
	var emailPat		= /^(.+)@(.+)$/;
	
	var specialChars	= "\\(\\)><@,;:-\\\\\\\"\\.\\[\\]";
	
	var validChars		= "\[^\\s" + specialChars + "\]";
	
	var quotedUser		= "(\"[^\"]*\")";
	
	var ipDomainPat		= /^\[(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\]$/;
	
	var atom			= validChars + '+';
	
	var word			= "(" + atom + "|" + quotedUser + ")";
	
	var userPat			= new RegExp("^" + word + "(\\." + word + ")*$");
	
	var domainPat		= new RegExp("^" + atom + "(\\." + atom +")*$");
	
	var matchArray		= emailStr.match(emailPat);

	if (matchArray==null) {
		alert("Email address seems incorrect (check @ and .'s)");
		document.getElementById(name).focus();
		return false;
	}
	var user			= matchArray[1];
	var domain			= matchArray[2];
	
/*	
	if(user.indexOf(".")!=-1 && user.split(".").length>2){
	 		alert("Only one dot is allowed in user name of email id");
			document.getElementById(name).focus();
			return false;
	 	}
*/	
	
	 	
	 	if(user.indexOf("_")!=-1 && user.split(".").length>1){
	 		alert("The username doesn't seem to be valid for email.");
			document.getElementById(name).focus();
			return false;
	 	}
	for (i=0; i<user.length; i++) {
		if (user.charCodeAt(i)>127 ) {
				alert("The username contains invalid characters.");
				document.getElementById(name).focus();
				return false;
		 }
	}

	if(domain.indexOf("-")!=-1 && domain.split("-").length>2){
	 		alert("The domain doesn't seem to be valid for email.");
			document.getElementById(name).focus();
			return false;
	 	}

	for (i=0; i<domain.length; i++) {
		if (domain.charCodeAt(i)>127 || domain.indexOf("_")!=-1) {
			alert("The domain name contains invalid characters.");
			document.getElementById(name).focus();
			return false;
   		}
	}


	if (user.match(userPat)==null) {
		alert("The username doesn't seem to be valid for email.");
		document.getElementById(name).focus();
		return false;
	}

	var IPArray=domain.match(ipDomainPat);
	if (IPArray!=null) {
		for (var i=1;i<=4;i++) {
			if (IPArray[i]>255) {
				alert("Destination IP address is invalid!");
				document.getElementById(name).focus();
				return false;
		   }
		}
			return true;
	}

	var atomPat		= new RegExp("^" + atom + "$");
	var domArr		= domain.split(".");
	var len    		= domArr.length;
	
	var dot         = domain.indexOf(".");
	var hiphen		= domain.indexOf("-");
	
	if(dot>hiphen && (dot-hiphen)<2){
		alert("The domain name does not seem to be valid");
		document.getElementById(name).focus();
		return false;
	}if(dot<hiphen && (hiphen-dot)<2){
		alert("The domain name does not seem to be valid");
		document.getElementById(name).focus();
		return false;
	}if(len>3){
		alert("Maximum two dots are allowed in domain name");
		document.getElementById(name).focus();
		return false;
	}
	
	for (i=0;i<len;i++) {
		if (domArr[i].search(atomPat)==-1) {
			alert("The domain name does not seem to be valid");
			document.getElementById(name).focus();
			return false;
	   }
	}if(domain.indexOf("-")!=-1){
		if(domain.indexOf("-")<2){
			alert("The domain name does not seem to be valid");
			document.getElementById(name).focus();
			return false;
		}
	}

	if (len<2) {
		alert("This email address is missing a hostname!");
		document.getElementById(name).focus();
		return false;
	}
	
	return true;
}
</script>