<!-- Added by manish sakpal -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="PersonalDataChangeApproval" validate="true" id="paraFrm" validate="true" theme="simple">

	<s:hidden name="level" />
	<s:hidden name="applnStatus" />
		
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
					<td width="93%" class="txt"><strong class="text_head">Personal Data Change Approval</strong></td>
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
						<td colspan="2" id="ctrlShow"><b>Approver Comments</b><font 
							color="red">*</font></td>
						<td colspan="3"  id="ctrlShow"><s:textarea theme="simple" cols="70" rows="3"
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
					<td colspan="4">Note : <font color="">This form may be used by employees to notify Human Resources, Payroll, and the
					employees manager of changes to personal data. <br/>Complete only those sections which require change.</font></td>
				</tr>
				<tr>
					<td width="25%"><label id="application.date" name="application.date"
						ondblclick="callShowDiv(this);"><%=label.get("application.date")%></label>
					:</td>
					<td width="25%"><s:textfield name="applicationDate" readonly="true"
						size="25" cssStyle="background-color: #F2F2F2;" /></td>

					
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
							onclick="javascript:callsF9(500,325,'PersonalDataChange_f9Employee.action');">
							</s:if>
							
						</td>
					</s:else>
				</tr>
				<tr>
					<td width="25%"><label id="area" name="area"
						ondblclick="callShowDiv(this);"><%=label.get("area")%></label>
					:</td>
					<!--<td width="25%"><s:textfield name="areaType" readonly="true"
						size="25" cssStyle="background-color: #F2F2F2;"/><s:hidden name="deptNumber"   />
					
					
					--><td width="25%" colspan="1"><s:hidden
						name="deptCode"  /> <s:textfield name="deptName" size="25" readonly="true"/>
					<img src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'PersonalDataChange_f9deptNumber.action');"></td>

					<td width="25%" align="left"><label id="work.phone" name="work.phone"
						ondblclick="callShowDiv(this);"><%=label.get("work.phone")%></label> :</td>

					<td width="25%"><s:textfield name="workPhone" readonly="true"
						size="25" cssStyle="background-color: #F2F2F2;" /></td>
						
						
						
				</tr>
				<!--<tr>
					

					<td width="25%"><label id="status" name="status"
						ondblclick="callShowDiv(this);"><%=label.get("status")%></label> :</td>
					<td width="25%"><s:select disabled="true" cssStyle="width:145"
						name="applnStatus"
						list="#{'P':'Pending','B':'Sent Back','A':'Approved','R':'Rejected',
						'N':'Cancelled','F':'Forwarded','C':'Applied For Cancellation','X':'Cancellation Approved','Z':'Cancellation Rejected'}" />

					</td>
				</tr>
				
			--></table>
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
					<td colspan="4">Note : <font color="">If there is a legal change in name a copy of the employee's new Social Security card must be faxed
					to (610) 993-6334 in addition to the completion of this form. </font></td>
				</tr>
				<tr>
					<td><b><label class="set" name="change.name.to"
						id="change.name.to" ondblclick="callShowDiv(this);"><%=label.get("change.name.to")%></label>
					: </b></td>
				</tr>
				<tr>
					<td width="25%%"><label id="first.name" name="first.name"
						ondblclick="callShowDiv(this);"><%=label.get("first.name")%></label>
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
					<td width="25%"><label id="last.name" name="last.name"
						ondblclick="callShowDiv(this);"><%=label.get("last.name")%></label>
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
					<td colspan="4" ><b><label class="set" name="change.address.to"
						id="change.address.to" ondblclick="callShowDiv(this);"><%=label.get("change.address.to")%></label>
					</b></td>
				</tr>
				<tr>
					<td  width="25%" colspan="1" ><label id="street.address"
						name="street.address" ondblclick="callShowDiv(this);"><%=label.get("street.address")%></label>
					:</td>
					<td  width="5%"><s:textarea name="streetAddress" cols="26" rows="2" /><img
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						id='ctrlHide' width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_streetAddress','street.address','','1000','1000');">
					<!--<td id='ctrlHide'>Remaining chars<s:textfield name="addCnt"
						readonly="true" size="5"></s:textfield></td>
					
				-->
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
						name="cityName"  /> </td>


					<td width="25%"><label id="country" name="country"
						ondblclick="callShowDiv(this);"><%=label.get("country")%></label>
					:</td>
					<td width="25%"><s:textfield name="country" 
						size="25" /></td>
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
						onkeypress="return numbersOnly();" maxlength="6" /></td>
				</tr>
				<tr>
					<td width="25%"><label id="change.home.phone.number"
						name="change.home.phone.number" ondblclick="callShowDiv(this);"><%=label.get("change.home.phone.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="homePhoneNumber" size="25"
						onkeypress="return numbersOnly();" maxlength="11" /></td>

					<td width="25%"><label id="change.work.phone.number"
						name="change.work.phone.number" ondblclick="callShowDiv(this);"><%=label.get("change.work.phone.number")%></label>
					:</td>
					<td width="30%"><s:textfield name="workPhoneNumber" size="25"
						onkeypress="return numbersOnly();" maxlength="11" /></td>
				</tr>
				<tr class="clsTRBody">

					<td width="25%" colspan="1"><label class="set"
						name="move.date" id="move.date" ondblclick="callShowDiv(this);"><%=label.get("move.date")%></label> :<font id='ctrlHide'
							color="red">*</font></td>

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
					<td width="25%"><s:textfield name="emailAddress" size="25" maxlength="150" onchange="return addConfigEmail();" /></td>
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
						size="25" onkeypress="return numbersOnly();" maxlength="11" /></td>


					<td width="25%"><label id="home.phone.number"
						name="home.phone.number" ondblclick="callShowDiv(this);"><%=label.get("home.phone.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="homePhoneNumberEmergency"
						size="25" onkeypress="return numbersOnly();" maxlength="11" /></td>
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
					<td colspan="4">Note : <font color="">When you Approve this form, it will automatically be sent to HR.
					 </font></td>
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
				</tr>
			</table>
			</td>
		</tr>
			
			<s:hidden name="persDataChangeApproverId" />
			
			<s:hidden name="listTypeDetailPage" />
	</table>
</s:form>
<script>

function approveFun()
{
	var con = confirm("Do you really want to approve this application?");
	if(con)
	{
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action = 'PersonalDataChangeApproval_approveApplication.action';
		document.getElementById('paraFrm').submit();
	}
}

function rejectFun()
{
	var con = confirm("Do you really want to reject this application?");
	if(con)
	{
			document.getElementById('paraFrm').target = "_self";
		    document.getElementById('paraFrm').action = 'PersonalDataChangeApproval_rejectApplication.action';
			document.getElementById('paraFrm').submit();
	}
}

function sendbackFun()
{	
	var con = confirm("Do you really want to sent back this application?");
	if(con)
	{
		document.getElementById('paraFrm').target = "_self";
	    document.getElementById('paraFrm').action = 'PersonalDataChangeApproval_sendBackApplication.action';
		document.getElementById('paraFrm').submit();
	}
}


function backtolistFun() 
{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'PersonalDataChangeApproval_backToList.action';
		document.getElementById('paraFrm').submit();
}

function printFun() {	
	window.print();
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

</script>