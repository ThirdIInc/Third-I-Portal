<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>

<s:form action="DepartmentLocationChange" validate="true" id="paraFrm"
	validate="true" theme="simple">

	<s:hidden name="authorizedToken" />
	<s:hidden name="readOnlyDetails" />
	<s:hidden name="deptCode" />
	<s:hidden name="initiator" />
	<s:hidden name="fname"></s:hidden>
	<s:hidden name="forApproval" id="forApproval" />
	<s:hidden name="forFlagComment" id="forFlagComment" />
	<s:hidden name="mname"></s:hidden>
	<s:hidden name="lname"></s:hidden>
	<s:hidden name="region"></s:hidden>
	<s:hidden name="area"></s:hidden>
		<s:hidden name="level" />

	<input type="hidden" id="labelCount" />
	
	<%
		String valueChk = "";
		String check1 = "";

		try {

			if (request.getAttribute("physicalLocation") != null) {
				valueChk = (String) request
				.getAttribute("physicalLocation");
			} else {
				valueChk = "";
			}
			if (request.getAttribute("workLocation") != null) {
				check1 = (String) request.getAttribute("workLocation");
			} else {
				check1 = "";
			}
			System.out.println("JSP value1:---" + valueChk);
			System.out.println("JSP value2:---" + check1);

		} finally {
			if (valueChk == null) {
				valueChk = "";
			}
			if (check1 == null) {
				check1 = "";
			}
		}
	%>

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">


			<tr>
				<td valign="bottom" class="txt" width="100%" colspan="5">
				<table width="100%" border="0" align="right" cellpadding="0"
					cellspacing="0" class="formbg">

					<tr>
						<td><strong class="text_head"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Department
						and Location Change Application </strong></td>

						<td width="3%" valign="top" class="txt">
						<div align="right"><img
							src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		


		<tr>
			<td width="100%" colspan="5">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">

				<tr>

					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

	<tr>
			<td width="100%" colspan="5">
			<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">

						<tr>
							<td colspan="4" width="100%">This form may be used by MANAGERS to notify Human
							Resources of changes to department and/or location date for
							their direct reports.<br/>
<br/>
							<b>DO NOT USE THIS FORM IF</b> there is a change to
							Executive,Region or Area.Such changes typically require
							additional information (e.g requisition number, salary, grade,
							title,etc) and approval signatures not captured on this form.To
							process changes to Executive,Region or Area, submit an Employee
							Action Record (EAR) with appropriate signatures to Human
							Resources.
							</td> </tr>
			</table>
			</td>
		</tr>


			<s:if test="forApproval">
			<tr>
				<td width="100%" colspan="5">
				<table width="100%" class="formbg">
					<tr>
						<td width="20%" colspan="2"><b>Approver Comments</b></td>
						<td></td>
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
				
				<tr>
					<td width="100%" colspan="5">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">

				
						<tr>
							<td colspan="3" width="100%"><b>Employee Information</b></td>
						</tr>

						<tr>
						
							<s:if test="deptlocChangeBean.generalFlag">
						<td width="20%"><label id="emp_no1" name="emp_no1"
							ondblclick="callShowDiv(this);"><%=label.get("emp_no1")%></label>
						:</td>
						<td width="80%" colspan="3"><s:textfield name="empNum"
							size="20" theme="simple" readonly="true" /><s:textfield
							name="empName" size="68" theme="simple" readonly="true" />
						<s:hidden name="empId" />
					</s:if>
					<s:else>
							<td colspan="1" width="20%"><label class="set" name="emp_no1"
								id="emp_no1" ondblclick="callShowDiv(this);"><%=label.get("emp_no1")%></label>
							<font color="red" id="ctrlHide">*</font>:</td>
							<td colspan="3" width="80%"><s:hidden name="empId" /><s:textfield
								size="20" theme="simple" maxlength="30" name="empNum"
								readonly="true" cssStyle="background-color: #F2F2F2;" /> <s:textfield
								name="empName" size="68" readonly="true"
								cssStyle="background-color: #F2F2F2;"></s:textfield> 
								<s:if test="draftFlag"></s:if>
								<s:else>
								<img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'DepartmentLocationChange_f9empaction.action');"
								id="ctrlHide"></s:else></td>
						</s:else>
						</tr>

						<tr>
							<td colspan="1" width="20%"><label class="set"
								name="executive" id="executive" ondblclick="callShowDiv(this);"><%=label.get("executive")%></label>
							:</td>
							<td colspan="1" width="30%"><s:textfield size="20"
								theme="simple" maxlength="50" name="executive" 
								 /></td>

							<!--<td colspan="1" width="20%"><label class="set" name="region"
								id="region" ondblclick="callShowDiv(this);"><%=label.get("region")%></label>
							:</td>
							<td colspan="1" width="30%"><s:textfield size="20"
								theme="simple" maxlength="30" name="region" readonly="true"
								cssStyle="background-color: #F2F2F2;" /></td>

						-->
						
						<!--<td colspan="1" width="20%"><label class="set"
								name="dept_no" id="dept_no" ondblclick="callShowDiv(this);"><%=label.get("dept_no")%></label>
							:</td>
							<td colspan="1" width="30%"><s:textfield size="20"
								theme="simple" maxlength="20" name="deptNum" readonly="true"
								cssStyle="background-color: #F2F2F2;" /></td>
						-->
						<td colspan="1" width="20%"><label class="set"
								name="dept_no" id="dept_no" ondblclick="callShowDiv(this);"><%=label.get("dept_no")%></label>
							:</td>
							<td width="25%" colspan="1"><s:hidden
						name="deptCodeSelect"  /> <s:textfield name="deptNum" size="25" readonly="true"/>
					<img src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'DepartmentLocationChange_f9deptNumber.action');"></td>
						
						</tr>
						<tr>
							<!--<td colspan="1" width="20%"><label class="set" name="area"
								id="area" ondblclick="callShowDiv(this);"><%=label.get("area")%></label>
							:</td>
							<td colspan="1" width="30%"><s:textfield size="20"
								theme="simple" maxlength="30" name="area" readonly="true"
								cssStyle="background-color: #F2F2F2;" /></td>

							-->
							

						</tr>
						<tr>
							<td colspan="1" width="20%" nowrap="nowrap"><label class="set"
								name="eff_date_of_change" id="eff_date_of_change"
								ondblclick="callShowDiv(this);"><%=label.get("eff_date_of_change")%></label>
							<font color="red" id="ctrlHide">*</font>:</td>
							<td colspan="1" width="30%"><s:textfield
								name="effectivedateofChange" size="20"
								value="%{effectivedateofChange}" theme="simple" readonly="true"
								cssStyle="background-color: #F2F2F2;" /><s:a
								href="javascript:NewCal('paraFrm_effectivedateofChange','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="18" align="absmiddle" width="18"
									id="ctrlHide">
							</s:a></td>

							<td colspan="1" width="20%">Status:</td>
							<td colspan="1" width="30%">
							<s:hidden name="applnActualStatus"></s:hidden>
							<s:select		 name="applnStatus" disabled="true"
								list="#{'D':'Draft','P':'PENDING','B':'Sent Back','A':'APPROVED','R':'REJECTED',
						'N':'CANCELLED','F':'FORWARDED','C':'APPLIED FOR CANCELLATION','X':'Cancellation Approved','Z':'Cancellation Rejected'}"
								cssStyle="width:126" /></td>

						</tr>
					</table>
					</td>
				</tr>




				<tr>
					<td width="100%" colspan="5">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td colspan="4" width="100%"><b>Department / Location
							Information</b></td>
						</tr>

						<tr>
							<td colspan="1" width="20%">&nbsp;&nbsp;&nbsp;</td>
							<td colspan="1" width="30%">From</td>
							<td colspan="1" width="20%">&nbsp;&nbsp;&nbsp;</td>
							<td colspan="1" width="30%">To</td>

						</tr>
						<tr>
							<td colspan="1" width="20%"><label class="set"
								name="dept_no" id="dept_no" ondblclick="callShowDiv(this);"><%=label.get("dept_no")%></label>
							<font color="red" id="ctrlHide">*</font>:</td>
							<td colspan="1" width="30%"><s:textfield size="20"
								theme="simple" maxlength="30" name="from_department_Num"
								readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
							<td colspan="1" width="20%">&nbsp;</td>

							<td colspan="1" width="30%"><s:hidden name="to_departmentID" /><s:textfield
								size="20" theme="simple" maxlength="30" name="to_department_Num"
								readonly="true" cssStyle="background-color: #F2F2F2;" /><img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'DepartmentLocationChange_f9dept.action');"
								id="ctrlHide"></td>

						</tr>

						<tr>
							<td colspan="1" width="20%"><label class="set"
								name="work_phone" id="work_phone"
								ondblclick="callShowDiv(this);"><%=label.get("work_phone")%></label>
							<font color="red" id="ctrlHide">*</font>:</td>
							<td colspan="1" width="30%"><s:textfield size="20"
								theme="simple" maxlength="30" name="from_workPhone"
								readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
							<td colspan="1" width="20%">&nbsp;</td>
							<td colspan="1" width="30%"><s:textfield size="20"
								theme="simple" maxlength="15" name="to_workPhone" /></td>


						</tr>


						<tr>
							<td colspan="1" width="20%"><label class="set"
								name="manager_name" id="manager_name"
								ondblclick="callShowDiv(this);"><%=label.get("manager_name")%></label>
							<font color="red" id="ctrlHide">*</font>:</td>
							<td colspan="1" width="30%"><s:textfield size="20"
								theme="simple" maxlength="30" name="from_managerName"
								readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
							<td colspan="1" width="20%">&nbsp;</td>
							<td colspan="1" width="30%" nowrap="nowrap"><s:hidden
								name="to_managerID" /> 
								<s:hidden name="managerToken"></s:hidden>
								<s:textfield size="20" theme="simple"
								maxlength="30" name="to_managerName" readonly="true"
								cssStyle="background-color: #F2F2F2;" /><img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'DepartmentLocationChange_f9manager.action');"
								id="ctrlHide"></td>
						<!--<tr>

							<td colspan="1" width="20%">Physical Work Location Change :
							</td>
							<td colspan="3" width="80%"><input type="radio"
								name="physicaLocation" id="homeId"
								onclick="setRadioValue(this);" value="H"
								<%=valueChk.equals("H")?"checked":"" %> /> Home:Employee Works
							from Home</td>



						</tr>

						<tr>
							<td colspan="1" width="20%">&nbsp;</td>
							<td colspan="3" width="80%"><input type="radio"
								name="physicaLocation" id="travel"
								onclick="setRadioValue(this);" value="T" <%=valueChk.equals("T")?"checked":"" %>/> Travel:Employee
							Visits multiple customer sites daily</td>

						</tr>

						<tr>
							<td colspan="1" width="20%">&nbsp;</td>
							<td colspan="3" width="80%"><input type="radio"
								name="physicaLocation" id="custsiteId"
								onclick="setRadioValue(this);" value="C" <%=valueChk.equals("C")?"checked":"" %>/> Custmor
							Site:Employee work full time at customer site</td>

						</tr>
						<tr>
							<td colspan="1" width="20%">&nbsp;</td>
							<td colspan="3" width="80%"><input type="radio"
								name="physicaLocation" id="decisiononeofficeId"
								onclick="setRadioValue(this);" value="O" <%=valueChk.equals("O")?"checked":"" %>/> Decision One
							Office:Employee works 25% or more time at office</td>


						</tr>

						<tr>
							<td colspan="1" width="20%">&nbsp;</td>
							<td colspan="3" width="80%"><input type="radio"
								name="physicaLocation" id="variableworkForceId"
								onclick="setRadioValue(this);" value="W" <%=valueChk.equals("W")?"checked":"" %>/> Variable Work Force
							Only:Employee Location varies by call/Assignment</td>


						</tr>

						-->
						
						<tr>
							<td colspan="1" width="20%"><label class="set"
								name="address" id="address" ondblclick="callShowDiv(this);"><%=label.get("address")%></label><font
								color="red" id="ctrlHide">*</font>:</td>
							<td colspan="1" width="30%"><s:hidden name="cityId" /><s:textfield
								size="20" theme="simple" maxlength="30" name="address1" />
							<td colspan="1" width="20%"><label class="set" name="city"
								id="city" ondblclick="callShowDiv(this);"><%=label.get("city")%></label><font
								color="red" id="ctrlHide">*</font>:</td>
							<td colspan="1" width="30%" nowrap="nowrap"><s:textfield
								size="20" theme="simple" maxlength="20" name="city"
								 /></td>
						</tr>

						<tr>
							<td colspan="1" width="20%"></td>
							<td colspan="1" width="30%"><s:textfield size="20"
								theme="simple" maxlength="30" name="address2" /></td>
							<td colspan="1" width="20%"><label class="set" name="state"
								id="state" ondblclick="callShowDiv(this);"><%=label.get("state")%></label><font
								color="red" id="ctrlHide"*</font>:</td>
							<td colspan="1" width="30%"><s:textfield size="20"
								theme="simple" maxlength="20" name="state" 
								 /></td>

						</tr>

						<tr>
							<td colspan="1" width="20%"></td>
							<td colspan="1" width="20%"><s:textfield size="20"
								theme="simple" maxlength="30" name="address3" /></td>
							<td colspan="1" width="15%"><label class="set"
								name="country" id="country" ondblclick="callShowDiv(this);"><%=label.get("country")%></label>
							:</td>
							<td colspan="1" width="30%"><s:textfield size="20"
								theme="simple" maxlength="20" name="country" 
								 /></td>
						</tr>


						<tr>
							<td colspan="1" width="20%"><label class="set" name="phone1"
								id="phone1" ondblclick="callShowDiv(this);"><%=label.get("phone1")%></label><font
								color="red" id="ctrlHide">*</font>:</td>
							<td colspan="1" width="30%"><s:textfield size="20"
								theme="simple" maxlength="15" name="phone1"  /></td>

							<!--<td colspan="1" width="20%"><label class="set" name="phone2"
								id=phone2 ondblclick="callShowDiv(this);"><%=label.get("phone2")%></label></td>
							<td colspan="1" width="30%"><s:textfield size="20"
								theme="simple" maxlength="30" name="phone2" /></td>
						-->
						<td colspan="1" width="20%"></td>
						<td colspan="1" width="30%"></td>
						</tr>

					</table>
					</td>
				</tr>


				<tr>
					<td width="100%" colspan="5">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td colspan="4" width="100%"><b><label class="set"
								name="note" id="note" ondblclick="callShowDiv(this);"><%=label.get("note")%></label></b></td>
						</tr>

						<tr>
							<td colspan="4" width="100%">Office Mail Location is the
							City & State of the DecisionOne Office where inter office
							mailings should be sent.For employees assigned by management to
							work from a home office,the Office Mail Location is the
							employee's home address</td>

						</tr>
						<tr>
							<td colspan="4" width="100%"><b>Office Mail Location:</b>
							(To request a change answer the following question)</td>
						</tr>
						<tr>
							<td colspan="4" width="100%">Has the employee been assigned
							by management to work from home Office? Yes:<input type="radio"
								name="workHome" id="yes" onclick="setRadioValue1(this);"
								value="Y" <%=check1.equals("Y")?"checked":"" %> /> No:<input
								type="radio" name="workHome" id="no"
								onclick="setRadioValue1(this);" value="N"
								<%=check1.equals("N")?"checked":"" %> /></td>
						</tr>
					</table>
					</td>
				</tr>


			<tr>
					<td width="100%" colspan="5">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td colspan="4" width="100%"><b><label class="set"
								name="note" id="note" ondblclick="callShowDiv(this);"><%=label.get("note")%>:</label></b>
						
							When you Submit this form, it will automatically sent to Approvar as you selected below or to line manager. </td>
						</tr>
						
					</table>
					</td>
				</tr>


				


						<tr>
							<td width="100%" colspan="5">
							<table width="100%" border="0" align="center" cellpadding="2"
								cellspacing="1" class="formbg">




								<tr>
									<td><b><label class="set" name="from_app"
										id="from_app" ondblclick="callShowDiv(this);"><%=label.get("from_app")%></label></b>
									</td>
								</tr>
								<tr>
									<td><label class="set" name="first_app"
										id="first_app" ondblclick="callShowDiv(this);"><%=label.get("first_app")%></label>
									:</td>

									<td width="80%" colspan="3"><s:hidden
										name="firstApproverCode" /> <s:property value="appToken" />&nbsp;&nbsp;&nbsp;
									<s:property value="approverName" /> <!--<s:textfield
						name="appToken" size="20" value="%{appToken}"
						theme="simple" readonly="true" /> 
					<s:textfield
						name="approverName" size="60" value="%{approverName}"
						theme="simple" readonly="true" /> 
					--></td>
								</tr>
								<s:if test="secondAppFlag">
								<tr>
									<td><label class="set" name="second_app"
										id="second_app" ondblclick="callShowDiv(this);"><%=label.get("second_app")%></label>
									:</td>

									<td width="80%" colspan="3"><s:hidden
										name="secondApproverId" /> <s:property value="secondApproverToken" />&nbsp;&nbsp;&nbsp;
									<s:property value="secondApproverName" /> 
								</td>
								</tr>
								</s:if>
								
								<tr>
									<td width="20%" ><label class="set" name="app_name"
										id="app_name" ondblclick="callShowDiv(this);"><%=label.get("app_name")%></label>:</td>

									<td width="80%" colspan="3"><s:textfield
										name="approverToken" size="20" theme="simple" readonly="true" />
									<s:textfield name="selectapproverName" size="70" theme="simple"
										readonly="true" /> <s:hidden name="approverCode" /> <img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16" id='ctrlHide'
										onclick="javascript:callsF9(500,325,'DepartmentLocationChange_f9approver.action');">
									</td>

								</tr>
							</table>
							</td>
						</tr>

	<tr>
						<td width="100%" colspan="5">
							<table width="100%" border="0" align="center" cellpadding="2"
								cellspacing="1" class="formbg">
								
								<tr><td width="20%"><b>Completed By:</b></td>
								<td width="40%" nowrap="nowrap">
								<s:hidden name="completedByCode"></s:hidden>
								<s:hidden name="completedByToken"></s:hidden>
								<s:hidden name="completedBy"></s:hidden>
								<s:property value="completedByToken"/>-
								<s:property value="completedBy"/>   </td>
								<td width="20%"><b>Completed On:</b></td>
								<td width="20%">
								<s:hidden name="completedDate"></s:hidden>
								<s:property value="completedDate"/>
								</td>
								
								</tr>
								</table></td></tr>






						</td>
						</tr>
					

			
<tr>
					
	
				
	

		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
	<s:hidden name="radioValue" id="radioValue" value="<%=valueChk %>" />
	<s:hidden name="radioYNValue" id="radioYNValue" value="<%=check1 %>" />

</s:form>


<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script>


	function makeFieldsDisabled() {	
		var readOnlyDetails = document.getElementById('paraFrm_readOnlyDetails').value;
		var applnStatus = document.getElementById('paraFrm_applnStatus').value;

		if(!(applnStatus == 'D' || applnStatus == 'B' || applnStatus == '')) {
			disableFormFields();
		}

		if(readOnlyDetails == 'true' && (applnStatus == 'F' || applnStatus == 'R' || applnStatus == 'A')) {
			document.getElementById('approverComments').style.display = 'none';
		} else if(applnStatus == 'B') {
			document.getElementById('approverComments').style.display = 'none';
		}
	}
	
	function disableFormFields() {
		var labelCount = 0;
				
		for (var i = 0; i < document.all.length; i++) {
			if(document.all[i].id != 'approverComments') {
				if(document.all[i].id == 'ctrlHide') {
					document.all[i].style.display = 'none';
				} else if((document.all[i].type == 'checkbox' || document.all[i].type == 'radio') 
				&& document.all[i].id != 'ctrlShow' && document.all[i].parentNode.id != 'ctrlShow') {
					document.all[i].disabled = true;
				} else if((document.all[i].type == 'text' || document.all[i].type == 'password' || document.all[i].type == 'textarea' 
				|| document.all[i].type == 'select-one' || document.all[i].type == 'select-multiple' || document.all[i].type == 'button') 
				&& document.all[i].name != 'navigationButtons' && document.all[i].id != 'ctrlShow' && document.all[i].name != 'label' 
				&& document.all[i].name != 'newLabel' && document.all[i].name != 'changeLabelButtons' && document.all[i].id != 'authPassword') {
					hideFieldsHere(document.all[i], labelCount);
				}
			}
		}
		
		document.getElementById('labelCount').value = labelCount;
	}
	
	function hideFieldsHere(field, labelCount) {
		if(field.parentNode.id != 'ctrlShow') {
			if(field.type != 'button') {
				var lbl = document.createElement('label');
				lbl.id = 'lbl' + labelCount;
				labelCount++;
				
				if(field.type == 'select-one') {
					for(var j = 0; j < field.childNodes.length; j++) {
						if(field.childNodes[j].innerHTML != undefined) {
							if(field.childNodes[j].innerHTML.toLowerCase().indexOf('select') == -1 || 
							field.childNodes[j].innerHTML.toLowerCase().indexOf('select') == 0) {
								if(field.childNodes[j].selected == true) {
									lbl.innerHTML = field.childNodes[j].innerHTML;
								}
							}
						}
					}
				} else if(field.type == 'select-multiple') {
					var recordSelected = 0;

					for(var j = 0; j < field.childNodes.length; j++) {
						if(field.childNodes[j].innerHTML != undefined) {
							if(field.childNodes[j].innerHTML.toLowerCase().indexOf('select') == -1 || 
							field.childNodes[j].innerHTML.toLowerCase().indexOf('select') == 0) {
								if(field.childNodes[j].selected == true) {
									recordSelected += 1;
									
									if(recordSelected == 1) {
										lbl.innerHTML += field.childNodes[j].innerHTML;
									} else {
										lbl.innerHTML += ', ' + field.childNodes[j].innerHTML;
									}
								}
							}
						}
					}
				} else {
					lbl.innerHTML = field.value + ' ';
				}
	
				var cell = field.parentNode;
				cell.appendChild(lbl);
			}
			
			field.style.display = 'none';
		}
	}
function draftFun()
{
try{
  	var empNum = document.getElementById('paraFrm_empNum').value;
  	var effectivedateofChange = document.getElementById('paraFrm_effectivedateofChange').value;
  	var to_department_Num = document.getElementById('paraFrm_to_department_Num').value;
  	var to_workPhone = document.getElementById('paraFrm_to_workPhone').value;
  	var to_managerName = document.getElementById('paraFrm_to_managerName').value;
  	var address1 = document.getElementById('paraFrm_address1').value;
  	var city = document.getElementById('paraFrm_city').value;
  	var phone1 = document.getElementById('paraFrm_phone1').value;
  	
  	if((document.getElementById('paraFrm_empNum').value==""))
  	
  	{
  	alert("Please Select Employee");
  	return false;
  	
  	
  	}
  	
  	  	if((document.getElementById('paraFrm_effectivedateofChange').value==""))
  	
  	{
  	alert("Please Enter/Select Effective Date of Change");
  	return false;
  	
  	
  	}
  	
  	  	if((document.getElementById('paraFrm_to_department_Num').value==""))
  	
  	{
  	alert("Please Select To Department Number");
  	return false;
  	
  	
  	}
  	
  	if((document.getElementById('paraFrm_to_workPhone').value==""))
  	
  	{
  	alert("Please Enter Work Phone");
  	return false;
  	
  	
  	}
  	
  		if(!(to_workPhone==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < to_workPhone.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(to_workPhone.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==to_workPhone.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  	
  		}
  	
  	
  	if((document.getElementById('paraFrm_to_managerName').value==""))
  	
  	{
  	alert("Please Select To Manager Name ");
  	return false;
  	
  	
  	}
  	
  			  	if((document.getElementById('paraFrm_address1').value==""))
  	
  	{
  	alert("Please Enter the Address");
  	return false;
  	
  	
  	}
  	
  		if(!(address1==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < address1.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(address1.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==address1.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		}
  		
  		
  		
  	if((document.getElementById('paraFrm_city').value==""))
  	
  	{
  	alert("Please enter city");
  	return false;
  	
  	
  	}

	if((document.getElementById('paraFrm_phone1').value==""))
  	
  	{
  	alert("Please enter phone");
  	return false;
  	
  	
  	}  	
  	
  		if(!(phone1==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < phone1.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(phone1.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==phone1.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  	}
  	
  				var defaultApproverName=document.getElementById('paraFrm_firstApproverCode').value
	   			var approverName=document.getElementById('paraFrm_selectapproverName').value
				if(trim(defaultApproverName)=="0" ){
					if(trim(approverName)==""){
						alert("Please select approver ");
						document.getElementById('paraFrm_selectapproverName').focus();
						return false;
					}
				}
  	 var con=confirm('Do you want to draft the record(s) ?');
	 if(con){
      	 document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'DepartmentLocationChange_draft.action';
		document.getElementById('paraFrm').submit();
      }
      }catch(e){
      alert("Exception occured in draft function : "+e);
      }
  }	 


function searchFun() {
		
		
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 200, left = 300, width = 1000, height = 600, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='DepartmentLocationChange_f9empaction.action';
		document.getElementById("paraFrm").submit();
	}



function resetFun() {
		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'DepartmentLocationChange_reset.action';
     	document.getElementById('paraFrm').submit(); 
     	    
  	}
	
	
	function reportFun()
	{
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = 'DepartmentLocationChange_report.action';
			document.getElementById('paraFrm').submit();
	}
	
	function cancelapplicationFun() {
	 var con=confirm('Do you want to cancel the application ?');
	 if(con){
				document.getElementById('paraFrm').target = "_self";
		      	document.getElementById('paraFrm').action = 'DepartmentLocationChange_updateStatus.action';
				document.getElementById('paraFrm').submit();
			}
	}
	
	function backtolistFun() {	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="DepartmentLocationChange_input.action";
		document.getElementById('paraFrm').submit();
	}
	
	function printFun() {	
	window.print();
	}
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'DepartmentLocationChange_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	
function sendforapprovalFun()
{ 
	try{
	var empNum = document.getElementById('paraFrm_empNum').value;
  	var effectivedateofChange = document.getElementById('paraFrm_effectivedateofChange').value;
  	var to_department_Num = document.getElementById('paraFrm_to_department_Num').value;
  	var to_workPhone = document.getElementById('paraFrm_to_workPhone').value;
  	var to_managerName = document.getElementById('paraFrm_to_managerName').value;
  	var address1 = document.getElementById('paraFrm_address1').value;
  	var city = document.getElementById('paraFrm_city').value;
  	var phone1 = document.getElementById('paraFrm_phone1').value;
  	
  	if((document.getElementById('paraFrm_empNum').value==""))
  	
  	{
  	alert("Please Select Employee");
  	return false;
  	
  	
  	}
  	
  	  	if((document.getElementById('paraFrm_effectivedateofChange').value==""))
  	
  	{
  	alert("Please Enter/Select Effective Date of Change");
  	return false;
  	
  	
  	}
  	
  	  	if((document.getElementById('paraFrm_to_department_Num').value==""))
  	
  	{
  	alert("Please Select To Department Number");
  	return false;
  	
  	
  	}
  	
  	if((document.getElementById('paraFrm_to_workPhone').value==""))
  	
  	{
  	alert("Please Enter Work Phone");
  	return false;
  	
  	
  	}
  	
  		if(!(to_workPhone==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < to_workPhone.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(to_workPhone.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==to_workPhone.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  	
  		}
  	
  	
  	if((document.getElementById('paraFrm_to_managerName').value==""))
  	
  	{
  	alert("Please Select To Manager Name ");
  	return false;
  	
  	
  	}
  	
  			  	if((document.getElementById('paraFrm_address1').value==""))
  	
  	{
  	alert("Please Enter the Address");
  	return false;
  	
  	
  	}
  	
  		if(!(address1==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < address1.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(address1.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==address1.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		}
  		
  		
  		
  	if((document.getElementById('paraFrm_city').value==""))
  	
  	{
  	alert("Please enter city");
  	return false;
  	
  	
  	}

	if((document.getElementById('paraFrm_phone1').value==""))
  	
  	{
  	alert("Please enter phone");
  	return false;
  	
  	
  	}  	
  	
  		if(!(phone1==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < phone1.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(phone1.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==phone1.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  	}
  	
  			var defaultApproverName=document.getElementById('paraFrm_firstApproverCode').value
	   			var approverName=document.getElementById('paraFrm_selectapproverName').value
				if(trim(defaultApproverName)=="0" ){
					if(trim(approverName)==""){
						alert("Please select approver ");
						document.getElementById('paraFrm_selectapproverName').focus();
						return false;
					}
				}
  	 var con=confirm('Do you want to send the record(s) for approval ?');
	 if(con){
		    document.getElementById('paraFrm').target = "_self";
	  		document.getElementById('paraFrm').action = 'DepartmentLocationChange_approve.action';
	     	document.getElementById('paraFrm').submit(); 
     	}  
     	}catch(e){}  

}	
	
	
function setRadioValue(id){
		var opt=document.getElementById('radioValue').value =id.value;	
	}


function setRadioValue1(id){
		var opt=document.getElementById('radioYNValue').value =id.value;	
	}
	
	function approveFun() {
	var con=confirm('Do you want to approve the application ?');
	 if(con){
				document.getElementById('paraFrm').action = 'DepartmentLocationChange_approveData.action';
				document.getElementById('paraFrm').submit();
				document.getElementById("paraFrm").target = 'main';
				window.opener.viewList();
				window.close();
			}
		}
		
		function rejectFun() {
		document.getElementById('paraFrm').action = 'DepartmentLocationChange_reject.action';
		document.getElementById('paraFrm').submit();
		document.getElementById("paraFrm").target = 'main';
		window.opener.viewList();
		window.close();
	}
	function sendbackFun() {
		document.getElementById('paraFrm').action = 'DepartmentLocationChange_sendBack.action';
		document.getElementById('paraFrm').submit();
		document.getElementById("paraFrm").target = 'main';
		window.opener.viewList();
		window.close();
	
	}
	
	
		/* Numvers Only Function*/
function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
            
            if(charCode!=46)
            {
             if (charCode > 31 && (charCode < 48 || charCode > 57))
                return false;
             }
             return true;
      
      }	
	

	
</script>
