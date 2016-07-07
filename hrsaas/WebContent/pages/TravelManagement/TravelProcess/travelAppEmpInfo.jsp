
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/pages/TravelManagement/TravelProcess/tmsAjax.js"></script>

<s:form name="paraFrm" action="" validate="" id="paraFrm" theme="simple">

	
	<s:hidden name="appStatus" />
	<s:hidden name="listType" />
	<s:hidden name="appId" />
	<s:hidden name="appCode" />
	<s:hidden name="hAppFor" />
	<s:hidden name="empId" />
	<s:hidden name="initId" id="paraFrm_initId" />
	<s:hidden name="InitGradeId" id="paraFrm_InitGradeId" />
	<s:hidden name="initiatorGradeId" id="paraFrm_initiatorGradeId" />
	<s:hidden name="editFlag" />
	<s:hidden name="delApp" theme="simple" />
	<input type="hidden" name="fieldName" id="paraFrm_fieldName">
	<s:hidden name="hiddenApplicationCode" />
	<s:hidden name="checkReportingStructure" />
	<s:hidden name="employeeDateOfBirth" />
	<s:hidden name="policyCode" value="%{policyCode}" />
	<s:hidden name="counterVal" />
	<s:hidden name="defaultCurrency" />
    <s:hidden name="joiningDate" />
	<s:hidden name="source" id="source" />

	<%
		String journeyChk = "";
		String accomodationChk = "";
		String localConvChk = "";
		String accomDisplay = "";
		String localConvDisplay = "";
		try {
			journeyChk = (String) request
			.getAttribute("journeyRadioStatus");
			accomodationChk = (String) request
			.getAttribute("accomodationRadioStatus");
			localConvChk = (String) request
			.getAttribute("localConvRadioStatus");
			accomDisplay = (String) request
			.getAttribute("accomodationDisplay");
			localConvDisplay = (String) request
			.getAttribute("localConDisplay");
		} finally {
			if (journeyChk == null) {
				journeyChk = "";
			}
			if (accomodationChk == null) {
				accomodationChk = "";
			}
			if (accomDisplay == null) {
				accomDisplay = "";
			}
			if (localConvChk == null) {
				localConvChk = "";
			}
			if (localConvDisplay == null) {
				localConvDisplay = "";
			}
		}
	%>
	<s:hidden name="journeyCheck" value="<%=journeyChk%>"
		id="paraFrm_journeyCheck" />
	<s:hidden name="accomCheck" value="<%=accomodationChk%>"
		id="paraFrm_accomCheck" />
	<s:hidden name="locConCheck" value="<%=localConvChk%>"
		id="paraFrm_locConCheck" />
	<s:hidden name="isSelfFlag" id="paraFrm_isSelfFlag" />
	<s:hidden name="checkempids" />
	<s:hidden name="jourId" />
	<s:hidden name="level" />
	<s:hidden name="accomodationCityId" />
	<s:hidden name="checkApproveRejectStatus" />
	<s:hidden name="policyViolationMsg" />
	<s:hidden name="datePolicyViolationMsg" />
	<s:hidden name="policyViolated" />
	<s:hidden name="datePolicyViolated" />

	<table width="100%" border="0" cellpadding="1" cellspacing="1"
		class="formbg" height="100%">
		<tr>
			<td valign="bottom" class="txt" colspan="3">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Travel
					Application</strong></td>
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
					<td width="50%" colspan="2"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<s:if test='%{showRevokeStatus}'>
			<tr>
				<td colspan="3">
				<table class="formbg" width="100%">
					<tr>
						<td colspan="2"><font color="red"><b>Application
						has been revoked.</b></font></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<s:if test='%{violationFlag =="Y"}'>
			<tr>
				<td colspan="3">
				<table class="formbg" width="100%">
					<tr>
						<td colspan="2"><font color="red">The following policy
						deviations have occured </font>: <s:property value="policyViolationMsg" /></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>

		<!-- TABLE FOR PREVIOUS APPROVER COMMENTS START-->

		<s:if test="approverListFlag">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="1" cellspacing="0"
					class="formbg">
					<tr>
						<td width="100%" colspan="7">
						<table width="100%" border="0" cellpadding="1" cellspacing="1">
							<tr>
								<td width="100%" nowrap="nowrap" colspan="7"><strong>
								Approver Details :</strong></td>

							</tr>
							<tr>
								<td class="formth" width="10%" height="22" valign="top">Sr.No.</td>
								<td class="formth" width="15%" height="22" valign="top">Approver
								ID</td>
								<td class="formth" width="25%" height="22" valign="top">
								Approver Name</td>
								<td class="formth" width="10%" height="22" valign="top">
								Date</td>
								<td class="formth" width="10%" height="22" valign="top">Status
								</td>
								<td class="formth" width="30%" height="22" valign="top">Comments
								</td>

							</tr>
							<%
							int m = 1;
							%>
							<s:iterator value="approverCommentList" status="stat">
								<tr>
									<td width="10%" class="sortableTD"><%=m%><s:hidden
										name="appSrNo" value="%{<%=m%>}" /></td>
									<td width="15%" class="sortableTD"><s:property
										value="prevApproverID" /><s:hidden name="prevApproverID" /></td>
									<td width="25%" class="sortableTD"><s:property
										value="prevApproverName" /><s:hidden name="prevApproverName" /></td>
									<td width="10%" class="sortableTD" align="center"><s:property
										value="prevApproverDate" /><s:hidden name="prevApproverDate" /></td>
									<td width="10%" class="sortableTD">&nbsp;<s:property
										value="prevApproverStatus" /><s:hidden
										name="prevApproverStatus" /></td>
									<td width="30%" class="sortableTD">&nbsp;<s:property
										value="prevApproverComment" /><s:hidden
										name="prevApproverComment" /></td>
								</tr>
								<%
								m++;
								%>
							</s:iterator>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>

		</s:if>
		<!-- TABLE FOR PREVIOUS APPROVER COMMENTS END-->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" class="formbg">
				<!-- New/Sent Back applications -->
				<tr>
					<td><label class="set" name="trainitor" id="trainitor"
						ondblclick="callShowDiv(this);"><%=label.get("trainitor")%></label>:
					</td>
					<td colspan="3"><s:textfield name="initToken" theme="simple"
						id="paraFrm_initToken" size="20" readonly="true" /> <s:textfield
						name="initName" id="paraFrm_initiatorName" theme="simple"
						size="50" readonly="true" /></td>
					<s:hidden name="age" id="paraFrm_initiatorAge" />
					<s:hidden name="initiatorDateOfBirth"
						id="paraFrm_initiatorDateOfBirth" />
					<s:hidden name="contact" id="paraFrm_initiatorContact" />
					<s:hidden name="advAmount" id="paraFrm_initiatorAdvDetails" />
				</tr>
				<tr>
					<td><label class="set" name="traappdate" id="traappdate"
						ondblclick="callShowDiv(this);"><%=label.get("traappdate")%>
					</label> :</td>
					<td><s:textfield name="appDate" id="paraFrm_appDate"
						theme="simple" size="10" maxlength="10"
						onblur="return validateDate('paraFrm_appDate','Date');"
						onkeypress="return numbersWithHiphen();" readonly="true" /></td>
					<td><label class="set" name="appStatus" id="appStatus"
						ondblclick="callShowDiv(this);"><%=label.get("appStatus")%>
					</label> :</td>
					<td><s:property value="appStatus" /></td>
				</tr>

				<tr>
					<td width="25%"><label class="set" name="Trastdate"
						id="Trastdate" ondblclick="callShowDiv(this);"><%=label.get("Trastdate")%></label>
					<font color="red">*</font> :</td>
					<td width="25%"><s:textfield name="startDate" theme="simple"
						size="10" maxLength="10" onkeypress="return numbersWithHiphen();" />
					<s:if test="appRejFlag">

					</s:if> <s:else>
						<s:a href="javascript:NewCal('paraFrm_startDate','DDMMYYYY');">
							<img src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="18" align="absmiddle" width="18">
						</s:a>
					</s:else></td>
					<td width="25%"><label class="set" name="Traenddate"
						id="Traenddate" ondblclick="callShowDiv(this);"><%=label.get("Traenddate")%></label><font
						color="red">*</font>:</td>
					<td width="25%"><s:textfield name="endDate" theme="simple"
						size="10" maxLength="10" onkeypress="return numbersWithHiphen();" />
					<s:if test="appRejFlag">

					</s:if> <s:else>
						<a href="javascript:NewCal('paraFrm_endDate','DDMMYYYY');"> <img
							src="../pages/images/Date.gif" class="iconImage" height="16"
							align="absmiddle" width="16"> </a>
					</s:else></td>
				</tr>
				<tr>
					<td><label class="set" name="TraReqname" id="TraReqname"
						ondblclick="callShowDiv(this);"><%=label.get("TraReqname")%></label>
					<font color="red">*</font>:</td>
					<td><s:textfield name="trvlReqName" theme="simple"
						maxlength="100" /></td>
					<td><label class="set" name="Trapurpose" id="Trapurpose"
						ondblclick="callShowDiv(this);"><%=label.get("Trapurpose")%></label>
					<font color="red">*</font>:</td>
					<td><s:hidden name="purposeId" /> <s:textfield name="purpose"
						theme="simple" size="20" readonly="true"
						onkeyup="javascript:callDropdown('paraFrm_purpose',200,250,'TravelApplication_f9Purpose.action',event,'false');" />
					<s:if test="%{trvlApp.appRejFlag}">

					</s:if> <s:else>
						<img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="javascript:callDropdown('paraFrm_purpose',200,250,'TravelApplication_f9Purpose.action',event,'false');">
					</s:else></td>
				</tr>
				<tr>
					<td><label class="set" name="travelProj" id="travelProj"
						ondblclick="callShowDiv(this);"><%=label.get("travelProject")%></label>
					:</td>
					<td><s:hidden name="projectId" id="paraFrm_projectId" /> <s:textfield
						name="project" id="paraFrm_project" theme="simple" size="20"
						readonly="true" /> <s:if test="%{trvlApp.appRejFlag}">

					</s:if> <s:else>
						<img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="javascript:callDropdown('paraFrm_project',200,250,'TravelApplication_f9Project.action','false');setBlankField('paraFrm_otherProject');">
					</s:else></td>
					<td width="20%"><label class="set" name="travelCust"
						id="travelCust" ondblclick="callShowDiv(this);"><%=label.get("otherProject")%></label>
					:</td>
					<td><s:textfield name="otherProject" id="paraFrm_otherProject"
						theme="simple" maxlength="100"
						onkeypress="setBlankField('paraFrm_project');" /></td>
				</tr>
				<tr>
					<td><label class="set" name="travelCust" id="travelCust"
						ondblclick="callShowDiv(this);"><%=label.get("travelCustomer")%></label>
					:</td>
					<td><s:hidden name="customerId" /> <s:textfield
						name="customerName" id="paraFrm_customerName" theme="simple"
						size="20" onkeyup="return chkProjectSelection();" /> <s:if
						test="%{trvlApp.appRejFlag}">
					</s:if> <s:else>
						<img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick=" return chkProjectSelection();" />
					</s:else></td>
					<td width="20%"><label class="set" name="travelCust"
						id="travelCust" ondblclick="callShowDiv(this);"><%=label.get("otherCustomer")%></label>
					:</td>
					<td><s:textfield name="otherCustomerName"
						id="paraFrm_otherCustomerName" theme="simple" maxlength="100"
						onkeypress="setBlankField('paraFrm_customerName');" /></td>
				</tr>
				<tr>
					<td><label class="set" name="TravelType" id="TravelType"
						ondblclick="callShowDiv(this);"><%=label.get("TravelType")%></label>
					<font color="red">*</font>:</td>
					<td><s:hidden name="trvlTypeId" /> <s:textfield
						name="trvlType" theme="simple" size="20" readonly="true" /> <s:if
						test="%{trvlApp.appRejFlag}">
					</s:if> <s:else>
						<img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="javascript:callDropdown('paraFrm_trvlType',200,250,'TravelApplication_f9TravelType.action','false');">
					</s:else></td>
				</tr>

				<!-- ##################################### KEEP INFORMED #########################-->
				<tr>
					<td align="left" colspan="3">
					<table width="100%" border="0" id="keepInformedTable">
						<tr>
							<td width="188px"><label class="set" name="keepInformedTo"
								id="keepInformedTo" ondblclick="callShowDiv(this);"><%=label.get("keepInformedTo")%></label>
							:</td>
							<td><s:hidden name="keepHidden" /> <s:hidden
								name="informCode" id="paraFrm_informCode" /> <s:hidden
								name="informToken" /> <s:if test="%{trvlApp.appRejFlag}">
							</s:if> <s:else>
								<s:textfield name="informName" id="paraFrm_informName" size="40"
									readonly="true" />
								<img src="../pages/images/recruitment/search2.gif" width="16"
									height="15" class="iconImage"
									onclick="javascript:callsF9(500,325,'TravelApplication_f9informTo.action');" />
								<input type="button" value="Add" Class="add"
									onclick="return callAddKeepInfo();">
							</s:else></td>
						</tr>
						<%
						int counter = 1;
						%>
						<tr>
							<s:iterator value="keepInformedList">
								<tr>
									<td width="188px"><s:hidden name="keepInformToCode"
										id="paraFrm_keepHidden<%=counter%>" /><%=counter%> ) <s:property
										value="keepInformToName" /> &nbsp;</td>
									<s:if test="%{trvlApp.appRejFlag}">
									</s:if>
									<s:else>
										<td><img src="../pages/common/css/icons/delete.gif"
											onclick="deleteCurrentRow(this);"></td>
									</s:else>
								</tr>
								<%
								counter++;
								%>
							</s:iterator>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<%
			int srCounter = 1;
			int srCounterJourney = 0;
			int srCounterAccomodation = 0;
			int srCounterLocalConveyance = 0;
		%>
		<!-- ########################################## NEW EMPLOYEE INFO BLOCK ############################################# -->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td>Employee Information</td>
									<s:if test="%{trvlApp.appRejFlag}">
									</s:if>
									<s:else>
										<td align="right"><s:if test="%{trvlApp.isSelfFlag}">
											<input type="button" value=" Self " class="add"
												theme="simple"
												onclick="addSelfToList('paraFrm_initToken','paraFrm_initiatorName','paraFrm_initiatorAge','paraFrm_initiatorContact','paraFrm_initiatorAdvDetails','S','paraFrm_initId','paraFrm_initiatorGradeId','paraFrm_initiatorDateOfBirth');checkAddButtonPress();" />
										</s:if> <s:if test="isEmployeeFlag">
											<input type="button" value="Add Employee" class="token"
												theme="simple"
												onclick="calculate();addEmployeeToList('addOtherEmployeeToTravellerList');" />
										</s:if> <s:if test="isGuestFlag">
											<input type="button" value="Add Guest" Class="token"
												theme="simple"
												onclick="addEmployeeToList('addGuestToTravellerList');" />
										</s:if></td>
									</s:else>
								</tr>
								<tr>
									<td colspan="2">
									<table width="100%" id="tblRef" class="sortable" border="0">
										<tr>
											<td valign="top" class="formth" align="center"><b><label
												name="refName" id="refName" ondblclick="callShowDiv(this);"><%=label.get("empguest")%></label></b></td>
											<td valign="top" class="formth" align="center"><b><label
												name="prof" id="prof" ondblclick="callShowDiv(this);"><%=label.get("dateofbirth")%></label></b></td>
											<td valign="top" class="formth" align="center"><b><label
												name="prof" id="prof" ondblclick="callShowDiv(this);"><%=label.get("EmpAge")%></label></b></td>
											<td valign="top" class="formth" align="center"><b><label
												name="cont" id="cont" ondblclick="callShowDiv(this);"><%=label.get("ContactNO")%></label>
											</b><font color="red">*</font></td>
											<td valign="top" class="formth" align="center" colspan="2"><b><label
												name="advA" id="advA" ondblclick="callShowDiv(this);"><%=label.get("advanceDetails")%></label></b></td>
											<s:if test="%{trvlApp.appRejFlag}">
											</s:if>
											<s:else>
												<td class="formth" align="center">&nbsp</td>
											</s:else>
										</tr>
										<s:iterator value="travellerList">
											<tr>
												<td class="sortableTD" align="center"><input
													type="hidden"
													id="paraFrm_employeeTypeFromList<%=srCounter%>"
													name="employeeTypeFromList"
													value='<s:property value="employeeTypeFromList"/>' /> <input
													type="hidden" id="paraFrm_travellerGradeId<%=srCounter%>"
													name="travellerGradeId"
													value='<s:property value="travellerGradeId"/>' /> <input
													type="hidden"
													id="paraFrm_employeeTravellerIdFromList<%=srCounter%>"
													name="employeeTravellerIdFromList"
													value='<s:property value="employeeTravellerIdFromList"/>' />
												<input type="text"
													id="paraFrm_employeeNameFromList<%=srCounter%>"
													name="employeeNameFromList"
													value='<s:property value="employeeNameFromList"/>'
													size="30" readonly="true" /></td>
												<s:if test='%{employeeTypeFromList=="G"}'>
													<td class="sortableTD" align="center"><input
														type="text"
														id="paraFrm_employeeDateOfBirthFromList<%=srCounter%>"
														name="employeeDateOfBirthFromList" readonly="true"
														size="10" style="background-color: #F2F2F2;" /></td>
												</s:if>
												<s:else>
													<td class="sortableTD" align="center"><input
														type="text"
														id="paraFrm_employeeDateOfBirthFromList<%=srCounter%>"
														name="employeeDateOfBirthFromList"
														value='<s:property value="employeeDateOfBirthFromList"/>'
														size="10" readonly="true" /></td>
												</s:else>
												<td class="sortableTD" align="center"><input
													type="text" id="paraFrm_employeeAgeFromList<%=srCounter%>"
													name="employeeAgeFromList"
													value='<s:property value="employeeAgeFromList"/>' size="5"
													onkeypress="return numbersOnly();" readonly="true" /></td>
												<td class="sortableTD" align="center"><input
													type="text"
													id="paraFrm_employeeContactFromList<%=srCounter%>"
													name="employeeContactFromList"
													value='<s:property value="employeeContactFromList"/>'
													size="20" onkeypress="return numbersOnly();" /></td>

												<s:if test='%{employeeTypeFromList=="G"}'>
													<td class="sortableTD" align="center"><input
														type="text"
														id="paraFrm_employeeAdvanceFromList<%=srCounter%>"
														name="employeeAdvanceFromList" value="0" readonly="true"
														size="10" style="background-color: #F2F2F2;" /></td>
												</s:if>
												<s:else>
													<td class="sortableTD" align="center" nowrap="nowrap">
													<input type="text" size="3"
														style="border: none; cursor: pointer;"
														id="paraFrm_currencyEmployeeAdvance<%=srCounter%>"
														name="currencyEmployeeAdvance"
														value='<s:property value="currencyEmployeeAdvance"/>'
														title="Click here to change currency" readonly="readonly"
														onclick="javascript:callDropdown('paraFrm_currencyEmployeeAdvance<%=srCounter%>', 200, 250, 'TravelApplication_f9Currency.action?currencyID=<%=srCounter%>',event,'false','no','right');" />

													<input type="text"
														id="paraFrm_employeeAdvanceFromList<%=srCounter%>"
														name="employeeAdvanceFromList"
														value='<s:property value="employeeAdvanceFromList"/>'
														size="10" onkeypress="return numbersWithDot();" /></td>
												</s:else>

												<s:if test="%{trvlApp.appRejFlag}">
												</s:if>
												<s:else>
													<td align="center" class="sortableTD"><img
														src="../pages/common/css/icons/delete.gif"
														onclick="deleteEmployeeCurrentRow(this, '<s:property value="employeeTypeFromList"/>');">
													</td>
												</s:else>
											</tr>
											<%
											srCounter++;
											%>
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
		<!-- ################################# NEW EMPLOYEE INFO BLOCK ENDS ##################################################### -->

		<!---------------------------------------- JOURNEY DETAILS [BEGINS] ------------------------------------>
		<%
			int i = 0;
			int j = 0;
			int fieldVal = 0;
			int accomVal = 0;
			int localConveyanceVal = 0;
		%>
		<tr>
			<td colspan="3">
			<table width="100%" class="formbg" border="0">
				<s:if test="appRejFlag">
					<tr>
						<td width="40%">Journey Details</td>
						<td width="40%"><input type="radio" name="journeyRadio"
							disabled="disabled" onclick="checkJourney(this);" value="S"
							<%=journeyChk.equals("S")?"checked":"" %>>Self Managed <input
							type="radio" name="journeyRadio" value="C" " disabled="disabled"
							<%=journeyChk.equals("C")?"checked":"" %>
							onclick="checkJourney(this);">Company Managed</td>
						<td align="right" width="20%">&nbsp;</td>
					</tr>

				</s:if>
				<s:else>
					<tr>
						<td width="40%">Journey Details</td>
						<td width="40%"><input type="radio" name="journeyRadio"
							onclick="checkJourney(this);" value="S"
							<%=journeyChk.equals("S")?"checked":"" %>>Self Managed <input
							type="radio" name="journeyRadio" value="C"
							"
						<%=journeyChk.equals("C")?"checked":"" %>
							onclick="checkJourney(this);">Company Managed</td>
						<td align="right" width="20%"><input type="button"
							value="   Add   " Class="token" theme="simple"
							onclick="addJourneyDetials();" /></td>
					</tr>
				</s:else>
				<tr id="JourTable">
					<td colspan="4">
					<table width="100%" id="journeyTable" class="sortable" border="0">
						<thead>
						<tr>
							<td class="formth"><label class="set" name="Frplace"
								id="Frplace1" ondblclick="callShowDiv(this);"><%=label.get("Frplace")%></label>
							<font color="red">*</font></td>
							<td class="formth"><label class="set" name="Toplace"
								id="Toplace0" ondblclick="callShowDiv(this);"><%=label.get("Toplace")%></label>
							<font color="red">*</font></td>
							<td class="formth" colspan="2"><label class="set"
								name="JMclass" id="JMclass" ondblclick="callShowDiv(this);"><%=label.get("JMclass")%></label>
							<font color="red">*</font></td>
							<td class="formth" colspan="2"><label class="set"
								name="Jourdate" id="Jourdate0" ondblclick="callShowDiv(this);"><%=label.get("Jourdate")%></label>
							<font color="red">*</font></td>
							<td class="formth"><label class="set" name="Timing"
								id="Timing" ondblclick="callShowDiv(this);"><%=label.get("Timing")%></label>
							<font color="red">*</font></td>
							<!-- New/Sent Back applications -->
							<s:if test="%{trvlApp.appRejFlag}">

							</s:if>
							<s:else>
								<td class="formth" align="center">&nbsp;</td>
							</s:else>

						</tr>
						</thead>
						<s:if test="%{trvlApp.appRejFlag}">

						</s:if>

						<s:else>
							<s:if test='%{saveFlag!="save"}'>
								<tr>
									<td align="center" class="sortableTD"><input type="hidden" name="lucky" id="lucky<%=fieldVal%>" value="<%=fieldVal%>"/><input type="text"
										name="journeyFromPlace"
										id="paraFrm_journeyFromPlace<%=fieldVal%>" theme="simple"
										size="20"
										onkeyup="setFieldId(event,<%=fieldVal%>,'TravelApplication_f9City.action?fieldName=journeyFromPlace<%=fieldVal%>','paraFrm_journeyFromPlace<%=fieldVal%>');" /></td>
									<td align="center" class="sortableTD"><input type="text"
										name="journeyToPlace" id="paraFrm_journeyToPlace<%=fieldVal%>"
										theme="simple" size="20"
										onkeyup="setFieldId(event,<%=fieldVal%>,'TravelApplication_f9City.action?fieldName=journeyToPlace<%=fieldVal%>','paraFrm_journeyToPlace<%=fieldVal%>');" /></td>
									<td align="center" class="sortableTD"><input type="text"
										name='journeyMode' id="paraFrm_journeyMode<%=fieldVal%>"
										readOnly="true" theme="simple" size="20" /> <input
										type="hidden" name='journeyModeId'
										id="paraFrm_journeyModeId<%=fieldVal%>" /></td>
									<td align="center" class="sortableTD"><img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="16" align="center" width="16"
										onclick="setFieldId(event,<%=fieldVal%>,'TravelApplication_f9JourneyMode.action?fieldName=<%=fieldVal%>','paraFrm_journeyMode<%=fieldVal%>');">
									</td>
									<td align="center" class="sortableTD"><input type="text"
										name="journeyDate" id="paraFrm_journeyDate<%=fieldVal%>"
										theme="simple" size="7"
										onkeypress="return numbersWithHiphen();" /></td>
									<td align="center" class="sortableTD"><s:a
										href="javascript:NewCal('paraFrm_journeyDate0','DDMMYYYY');">
										<img src="../pages/images/recruitment/Date.gif"
											class="iconImage" align="center">
									</s:a></td>
									<td align="center" class="sortableTD"><input type="text"
										name="journeyTime" id="paraFrm_journeyTime<%=fieldVal%>"
										theme="simple" size="5" /></td>
									<td align="center" class="sortableTD"><s:if
										test="%{trvlApp.appRejFlag}">

									</s:if> <s:else>
										<img src="../pages/common/css/icons/delete.gif"
											onclick="deleteCurrentRow(this);">
									</s:else></td>
								</tr>
							</s:if>

						</s:else>
						<s:iterator value="journeyList">
							<tr>
								<td class="sortableTD" align="center"><input type="hidden" name="lucky" id="lucky<%=fieldVal%>" value="<%=fieldVal%>"/><input type="text"
									name="journeyFromPlace"
									onkeyup="setFieldId(event,<%=fieldVal%>,'TravelApplication_f9City.action?fieldName=journeyFromPlace<%=fieldVal%>','paraFrm_journeyFromPlace<%=fieldVal%>');"
									id="paraFrm_journeyFromPlace<%=fieldVal%>"
									value='<s:property value="journeyFromPlace"/>' size="20" />&nbsp;</td>
								<td class="sortableTD" align="center"><input type="text"
									name="journeyToPlace" id="paraFrm_journeyToPlace<%=fieldVal%>"
									value='<s:property value="journeyToPlace"/>' size="20"
									onkeyup="setFieldId(event,<%=fieldVal%>,'TravelApplication_f9City.action?fieldName=journeyToPlace<%=fieldVal%>','paraFrm_journeyToPlace<%=fieldVal%>');" />&nbsp;</td>
								<td class="sortableTD" align="center"><input type="hidden"
									name='journeyModeId' id="paraFrm_journeyModeId<%=fieldVal%>"
									value='<s:property value="journeyModeId"/>' />&nbsp; <input
									type="text" name='journeyMode'
									id="paraFrm_journeyMode<%=fieldVal%>"
									value='<s:property value="journeyMode"/>' size="20" />&nbsp;</td>
								<td align="center" class="sortableTD"><img
									src="../pages/images/recruitment/search2.gif" class="iconImage"
									height="16" align="center" width="16" id="img<%=fieldVal%>"
									onclick="setFieldId(event,<%=fieldVal%>,'TravelApplication_f9JourneyMode.action?fieldName=<%=fieldVal%>','paraFrm_journeyMode<%=fieldVal%>');">
								</td>
								<td class="sortableTD" align="center" colspan="1"><input
									type="text" name="journeyDate"
									id="paraFrm_journeyDate<%=fieldVal%>"
									value='<s:property value="journeyDate"/>' size="7" />&nbsp;</td>
								<td align="center" class="sortableTD"><a
									href="javascript:NewCal('paraFrm_journeyDate<%=fieldVal%>','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" align="center" id="img<%=fieldVal%>"> </a></td>
								<td class="sortableTD" align="center"><input type="text"
									name="journeyTime" id="paraFrm_journeyTime<%=fieldVal%>"
									value='<s:property value="journeyTime"/>' size="10" />&nbsp;</td>
								<td align="center" class="sortableTD"><s:if
									test="%{trvlApp.appRejFlag}">

								</s:if> <s:else>
									<img src="../pages/common/css/icons/delete.gif"
										onclick="deleteCurrentRow(this);" id="img<%=fieldVal%>">
								</s:else></td>
							</tr>
							
						
							<%
							fieldVal++;
							%>
						</s:iterator>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!---------------------------------------- JOURNEY DETAILS [ENDS] ------------------------------------>
		<!---------------------------------------- LODGING DETAILS [BEGINS] ------------------------------------>

		<s:if test='%{true}'>
			<tr>
				<td colspan="3">
				<table width="100%" class="formbg">
					<s:if test="appRejFlag">
						<tr>
							<td width="40%">Lodging Details</td>
							<td width="40%"><input type="radio" name="accomodationRadio"
								value="S" disabled="disabled"
								onclick="hideShowOnRadioClick('accomTable','S','accomAdd'); checkAccomo(this);"
								<%=accomodationChk.equals("S")?"checked":"" %>>Self
							Managed <input type="radio" name="accomodationRadio" value="C"
								disabled="disabled"
								onclick="hideShowOnRadioClick('accomTable','C','accomAdd'); checkAccomo(this);"
								<%=accomodationChk.equals("C")?"checked":"" %>>Company
							Managed</td>
							<td align="right" width="20%">&nbsp;</td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td width="40%">Accomodation Details</td>
							<td width="40%"><input type="radio" name="accomodationRadio"
								value="S"
								onclick="hideShowOnRadioClick('accomTable','S','accomAdd'); checkAccomo(this);"
								<%=accomodationChk.equals("S")?"checked":"" %>>Self
							Managed <input type="radio" name="accomodationRadio" value="C"
								onclick="hideShowOnRadioClick('accomTable','C','accomAdd'); checkAccomo(this);"
								<%=accomodationChk.equals("C")?"checked":"" %>>Company
							Managed</td>
							<td align="right" width="20%"><input type="button"
								id="accomAdd" value="   Add   " Class="token" theme="simple"
								onclick="addAccomodationDetials();" /></td>
						</tr>
					</s:else>
					<tr id="accomTable"
						style='display: <%= accomodationChk .       equals( "C") ?       "" :       "none" %>;'>
						<td width="100%" colspan="3">
						<table width="100%" border="0" id="accomodationTable">
							<tr>
								<td class="formth" colspan="2"><label class="set"
									name="LodType" id="LodType" ondblclick="callShowDiv(this);"><%=label.get("LodType")%></label>
								<font color="red">*</font></td>
								<td class="formth" colspan="2"><label class="set"
									name="RoomType" id="RoomType2" ondblclick="callShowDiv(this);"><%=label.get("RoomType")%></label>
								<font color="red">*</font></td>
								<td class="formth"><label class="set" name="traCity"
									id="traCity" ondblclick="callShowDiv(this);"><%=label.get("traCity")%></label>
								<font color="red">*</font></td>
								<td class="formth"><label class="set" name="Prefloc"
									id="Prefloc" ondblclick="callShowDiv(this);"><%=label.get("Prefloc")%></label>
								</td>
								<td class="formth" colspan="2"><label class="set"
									name="FrDate" id="FrDate" ondblclick="callShowDiv(this);"><%=label.get("FrDate")%></label>
								<font color="red">*</font></td>
								<td class="formth"><label class="set" name="FrTime"
									id="FrTime" ondblclick="callShowDiv(this);"><%=label.get("FrTime")%></label>
								<font color="red">*</font></td>
								<td class="formth" colspan="2"><label class="set"
									name="ToDate" id="ToDate" ondblclick="callShowDiv(this);"><%=label.get("ToDate")%></label><font
									color="red">*</font></td>
								<td class="formth"><label class="set" name="ToTime"
									id="ToTime" ondblclick="callShowDiv(this);"><%=label.get("ToTime")%></label>
								<font color="red">*</font></td>
								<s:if test="%{trvlApp.appRejFlag}">

								</s:if>
								<s:else>
									<td class="formth" align="center">&nbsp;</td>
								</s:else>

							</tr>
							<s:iterator value="accomodationList">
								<tr>
									<td class="sortableTD" align="center">
									<input type="hidden" size="3" name="accomText" readonly="true" id="accomText<%=accomVal%>"/>
									<input type="hidden"
										name='accomodationHotelTypeId'
										id="paraFrm_accomodationHotelTypeId<%=accomVal%>"
										value='<s:property value="accomodationHotelTypeId"/>' /><input
										type="text" name='accomodationHotelType'
										id="paraFrm_accomodationHotelType<%=accomVal%>"
										value='<s:property value="accomodationHotelType"/>' size="6" />&nbsp;</td>
									<td align="center" class="sortableTD"><img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" align="center"
										onclick="setFieldId(event,<%=accomVal%>,'TravelApplication_f9LodgingType.action?fieldName=<%=accomVal%>','paraFrm_accomodationHotelType<%=accomVal%>');">

									</td>
									<td class="sortableTD" align="center"><input type="hidden"
										name='accomodationRoomTypeId'
										id="paraFrm_accomodationRoomTypeId<%=accomVal%>"
										value='<s:property value="accomodationRoomTypeId"/>' /> <input
										type="text" name="accomodationRoomType"
										id="paraFrm_accomodationRoomType<%=accomVal%>"
										value='<s:property value="accomodationRoomType"/>' size="6" />&nbsp;</td>
									<td align="center" class="sortableTD"><img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" align="center"
										onclick="callRoomType(event,<%=accomVal%>);" /></td>
									<td class="sortableTD" align="center"><input type="text"
										name="accomodationCity"
										id="paraFrm_accomodationCity<%=accomVal%>"
										value='<s:property value="accomodationCity"/>' size="10"
										onkeyup="setFieldId(event,<%=accomVal%>,'TravelApplication_f9City.action?fieldName=accomodationCity<%=accomVal%>','paraFrm_accomodationCity<%=accomVal%>');" />&nbsp;</td>
									<td class="sortableTD" align="center"><input type="text"
										name="accomodationPrefLocation"
										id="paraFrm_accomodationPrefLocation<%=accomVal%>"
										value='<s:property value="accomodationPrefLocation"/>'
										size="10" />&nbsp;</td>
									<td class="sortableTD" align="center"><input type="text"
										name="accomodationFromDate"
										id="paraFrm_accomodationFromDate<%=accomVal%>"
										value='<s:property value="accomodationFromDate"/>' size="7" />&nbsp;</td>
									<td align="center" class="sortableTD"><a
										href="javascript:NewCal('paraFrm_accomodationFromDate<%=accomVal%>','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" align="center"> </a></td>
									<td class="sortableTD" align="center"><input type="text"
										name="accomodationFromTime"
										id="paraFrm_accomodationFromTime<%=accomVal%>"
										value='<s:property value="accomodationFromTime"/>' size="5" />&nbsp;</td>
									<td class="sortableTD" align="center"><input type="text"
										name="accomodationToDate"
										id="paraFrm_accomodationToDate<%=accomVal%>"
										value='<s:property value="accomodationToDate"/>' size="7" />&nbsp;</td>
									<td align="center" class="sortableTD"><a
										href="javascript:NewCal('paraFrm_accomodationToDate<%=accomVal%>','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" align="center"> </a></td>
									<td class="sortableTD" align="center"><input type="text"
										name="accomodationToTime"
										id="paraFrm_accomodationToTime<%=accomVal%>"
										value='<s:property value="accomodationToTime"/>' size="5" />&nbsp;</td>
									<td align="center" class="sortableTD"><s:if
										test="%{trvlApp.appRejFlag}">
									</s:if> <s:else>
										<img src="../pages/common/css/icons/delete.gif"
											onclick="deleteAccoRow(this);" id="img<%=accomVal%>">
									</s:else></td>
									<%
									accomVal++;
									%>
								</tr>
							</s:iterator>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<!---------------------------------------- LODGING DETAILS [ENDS] ------------------------------------>
		<!---------------------------------------- LOCAL CONVEYANCE DETAILS [BEGINS] ------------------------------------>
		<tr>
			<td colspan="3">
			<table width="100%" class="formbg" border="0">
				<s:if test="appRejFlag">
					<tr>
						<td width="40%">Local Conveyance Details</td>
						<td width="40%"><input type="radio" name="localConvRadio"
							disabled="disabled" value="S"
							onclick="hideShowOnRadioClick('localConv','S','localConvAdd'); checkLocConv(this);"
							<%=localConvChk.equals("S")?"checked":"" %>>Self Managed
						<input type="radio" name="localConvRadio" value="C"
							disabled="disabled"
							onclick="hideShowOnRadioClick('localConv','C','localConvAdd'); checkLocConv(this);"
							<%=localConvChk.equals("C")?"checked":"" %>>Company
						Managed</td>
						<td align="right" width="20%">&nbsp;</td>
					</tr>

				</s:if>
				<s:else>
					<tr>
						<td width="40%">Local Conveyance Details</td>
						<td width="40%"><input type="radio" name="localConvRadio"
							value="S"
							onclick="hideShowOnRadioClick('localConv','S','localConvAdd'); checkLocConv(this);"
							<%=localConvChk.equals("S")?"checked":"" %>>Self Managed
						<input type="radio" name="localConvRadio" value="C"
							onclick="hideShowOnRadioClick('localConv','C','localConvAdd'); checkLocConv(this);"
							<%=localConvChk.equals("C")?"checked":"" %>>Company
						Managed</td>
						<td align="right" width="20%"><input type="button"
							value="   Add   " id="localConvAdd" Class="token" theme="simple"
							onclick="addLocalConveyanceDetials();" /></td>
					</tr>
				</s:else>

				<tr id="localConv"
					style='display: <%= localConvChk .       equals( "C") ?       "" :       "none" %>;'>
					<td width="100%" colspan="3">
					<table width="100%" border="0" id="localConveyanceTable">
						<tr>
							<td class="formth"><label class="set" name="traCity"
								id="traCity1" ondblclick="callShowDiv(this);"><%=label.get("traCity")%></label><font
								color="red">*</font></td>
							<td class="formth"><label class="set" name="TraDet"
								id="TraDet1" ondblclick="callShowDiv(this);"><%=label.get("TraDet")%></label>
							</td>
							<td class="formth"><label class="set" name="locMedium"
								id="locMedium" ondblclick="callShowDiv(this);"><%=label.get("locMedium")%></label>
							<font color="red">*</font></td>
							<td class="formth" colspan="2"><label class="set"
								name="FrDate" id="FrDate3" ondblclick="callShowDiv(this);"><%=label.get("FrDate")%></label>
							<font color="red">*</font></td>
							<td class="formth"><label class="set" name="FrTime"
								id="FrTime3" ondblclick="callShowDiv(this);"><%=label.get("FrTime")%></label>
							<font color="red">*</font></td>
							<td class="formth" colspan="2"><label class="set"
								name="ToDate" id="ToDate3" ondblclick="callShowDiv(this);"><%=label.get("ToDate")%></label>
							<font color="red">*</font></td>
							<td class="formth"><label class="set" name="ToTime"
								id="ToTime4" ondblclick="callShowDiv(this);"><%=label.get("ToTime")%></label>
							<font color="red">*</font></td>

							<s:if test="%{trvlApp.appRejFlag}">

							</s:if>
							<s:else>
								<td class="formth" align="center">&nbsp</td>
							</s:else>

						</tr>
						<s:iterator value="localConveyanceList">
							<tr>
								<td class="sortableTD" align="center">
								<input type="hidden" name="localConvText"  id="localConvText<%=localConveyanceVal%>"/>
								<input type="hidden"
									name='localConveyanceCode'
									id="paraFrm_localConveyanceCode<%=localConveyanceVal%>"
									value='<s:property value="localConveyanceCode"/>' /> <input
									type="text" name="localConveyanceCity"
									id="paraFrm_localConveyanceCity<%=localConveyanceVal%>"
									value='<s:property value="localConveyanceCity"/>' size="10"
									onkeyup="setFieldId(event,<%=localConveyanceVal%>,'TravelApplication_f9City.action?fieldName=localConveyanceCity<%=localConveyanceVal%>','paraFrm_localConveyanceCity<%=localConveyanceVal%>');" />&nbsp;</td>
								<td class="sortableTD" align="center"><input type="text"
									name="localConveyanceTravelDetail"
									id="paraFrm_localConveyanceTravelDetail<%=localConveyanceVal%>"
									value='<s:property value="localConveyanceTravelDetail"/>'
									size="10" />&nbsp;</td>
								<td class="sortableTD" align="center"><input type="text"
									name="localConveyanceTravelMedium"
									id="paraFrm_localConveyanceTravelMedium<%=localConveyanceVal%>"
									value='<s:property value="localConveyanceTravelMedium"/>'
									size="10" />&nbsp;</td>
								<td class="sortableTD" align="center"><input type="text"
									name="localConveyanceFromDate"
									id="paraFrm_localConveyanceFromDate<%=localConveyanceVal%>"
									value='<s:property value="localConveyanceFromDate"/>' size="7" />&nbsp;</td>
								<td align="center" class="sortableTD"><a
									href="javascript:NewCal('paraFrm_localConveyanceFromDate<%=localConveyanceVal%>','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" align="center"> </a></td>
								<td class="sortableTD" align="center"><input type="text"
									name="localConveyanceFromTime"
									id="paraFrm_localConveyanceFromTime<%=localConveyanceVal%>"
									value='<s:property value="localConveyanceFromTime"/>' size="5" />&nbsp;</td>
								<td class="sortableTD" align="center"><input type="text"
									name="localConveyanceToDate"
									id="paraFrm_localConveyanceToDate<%=localConveyanceVal%>"
									value='<s:property value="localConveyanceToDate"/>' size="7" />&nbsp;</td>
								<td align="center" class="sortableTD"><a
									href="javascript:NewCal('paraFrm_localConveyanceToDate<%=localConveyanceVal%>','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" align="center"> </a></td>
								<td class="sortableTD" align="center"><input type="text"
									name="localConveyanceToTime"
									id="paraFrm_localConveyanceToTime<%=localConveyanceVal%>"
									value='<s:property value="localConveyanceToTime"/>' size="5" />&nbsp;</td>
								<td align="center" class="sortableTD"><s:if
									test="%{trvlApp.appRejFlag}">

								</s:if> <s:else>

									<img src="../pages/common/css/icons/delete.gif"
										onclick="deleteConvRow(this);" id="img<%=localConveyanceVal%>">
								</s:else></td>
							</tr>
							<%
							localConveyanceVal++;
							%>
						</s:iterator>
					</table>
					</td>
				</tr>
				<%
				int k = 0;
				%>
				<input type="hidden" name="hLocCount" id="hLocCount" value="<%=k%>">
				<s:hidden name="delLoc" theme="simple" />
			</table>
			</td>
		</tr>
		<!---------------------------------------- LOCAL CONVEYANCE DETAILS [ENDS] ------------------------------------>
		<!-------------------------------- APPLICANT COMMENTS [BEGINS]--------------------------------------------------->

		<tr>
			<td colspan="3">
			<table width="100%" class="formbg">
				<tr>
					<td width="20%"><label class="set" name="applicantComments"
						id="applicantComments" ondblclick="callShowDiv(this);"><%=label.get("applicantComments")%></label>:</td>
					<td><!-- <textarea rows="4" cols="60" name="applComm" id="paraFrm_applComm" onkeyup="callLength('applComm','descCnt','300');"><s:property value="applComm" /></textarea> -->
					<s:if test="%{trvlApp.appRejFlag}">
						<s:property value="applComm" />
					</s:if> <s:else>
						<textarea rows="4" cols="60" name="applComm" id="paraFrm_applComm"
							onkeyup="callLength('applComm','descCnt','500');"><s:property
							value="applComm" /></textarea>

						<img src="../pages/images/zoomin.gif" class="iconImage"
							height="12" align="absmiddle" width="12"
							onclick="callWindow('paraFrm_applComm','applicantComments','','paraFrm_descCnt',500);">
					Remaining chars <input type="text" name="descCnt"
							id="paraFrm_descCnt" size="5" readonly="readonly">
					</s:else></td>
				</tr>
			</table>
			</td>
		</tr>

		<!-------------------------------- APPLICANT COMMENTS [ENDS]--------------------------------------------------->

		<tr>
			<td width="50%" colspan="2"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
	<div id="policyDiv"
		style='position: absolute; z-index: 3; width: 535px; height: 270px; display: none; border: 2px solid; top: 100px; left: 100px; padding: 10px;'
		class="formbg">
	<table width="100%" border="0" cellpadding="2" cellspacing="2"
		class="formbg">
		<tr>
			<td colspan="3"><b><font color="red" size="2">Policy
			deviated by following:</font></b></td>
			<td>&nbsp;</td>
		</tr>
		<%
		int index = 1;
		%>
		<tr>
			<td colspan="3" valign="top" align="center">
			<div id="policyDiv1"
				style='width: 500px; height: 100px; display: block; border: 2px solid; overflow: scroll;'
				class="formbg"></div>
			</td>
		</tr>

		<tr>
			<td>Deviation Reason<font color="red">*</font>:</td>
			<td><textarea name="violationReason"
				id="paraFrm_violationReason" rows="4" cols="50"></textarea></td>
		</tr>
		<tr>
			<td colspan="3"><b>Do you really want to send for approval?</b>
			<input type="button" value=" Yes  "
				onclick="sendForApprovalWithViolation();" class="token"> <input
				type="button" value="   No  " onclick="hideWindow();" class="token"></td>
		</tr>
	</table>
	</div>
	<s:hidden name="isAddClick"></s:hidden>
	<s:hidden name="draftOrSend"></s:hidden>
</s:form>
<script>

/*############# COMMON FUNCTION'S STARTS ############################################*/
 	onloadPolicyCheck();
 	onLoad();
 	calculate();
 	
 	function onloadPolicyCheck(){
 		if(document.getElementById('paraFrm_datePolicyViolated').value=="true" && document.getElementById('paraFrm_policyViolated').value=="false"){
 			var conf = confirm(document.getElementById('paraFrm_datePolicyViolationMsg').value);
 			if(conf){
 				document.getElementById('paraFrm').target = "_self";
	 			document.getElementById('paraFrm').action = 'TravelApplication_sendForApprovalWithViolation.action';
				document.getElementById('paraFrm').submit();
 			
 			} else {
 				document.getElementById('policyDiv').style.display='none';
 			}
 		}
 		if(document.getElementById('paraFrm_datePolicyViolated').value=="false" && document.getElementById('paraFrm_policyViolated').value=="true"){
 			document.getElementById('policyDiv').style.display='block';
 			document.getElementById('policyDiv1').innerHTML=document.getElementById('paraFrm_policyViolationMsg').value;
 		}
 		if(document.getElementById('paraFrm_datePolicyViolated').value=="true" && document.getElementById('paraFrm_policyViolated').value=="true"){
 			var conf = confirm(document.getElementById('paraFrm_datePolicyViolationMsg').value);
 			if(conf){
 				document.getElementById('policyDiv').style.display='block';	
 			} else {
 				document.getElementById('policyDiv').style.display='none';
 			}
 				document.getElementById('policyDiv').style.display='block';
 				document.getElementById('policyDiv1').innerHTML=document.getElementById('paraFrm_policyViolationMsg').value;
 		}
 	
 	}
	
	function hideWindow(){
		document.getElementById('policyDiv').style.display='none';
	}
 	
 	function calculate()
 	 	{
 	 	try{
 	 		
 	 	var tbl = document.getElementById('tblRef');
		  var tableLength = tbl.rows.length-1;
	//alert('in calc lastRow'+tableLength);
	var value = tableLength ;
	var id ='0';
	for(var i=1;i<=value;i++)
	{
	id+= document.getElementById('paraFrm_employeeTravellerIdFromList'+i).value+",";
		 
	} 	
 	  id = id.substring(0,id.length-1);
 	  
 	  if(id=="")
  {
  		id=0;
  }
 	  
  document.getElementById('paraFrm_checkempids').value=id;
 
 	 	}
 	 	catch(e) { //alert(e);
 	 	}
 	 
 }	
 	
 	function checkAddButtonPress() {
 		document.getElementById('paraFrm_isAddClick').value='Y';
 	}
		
	
	function sendForApprovalWithViolation(){
		
		if(document.getElementById("paraFrm_violationReason").value==""){
			alert("Please specify reason for violation");
			return false;
		}
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = 'TravelApplication_sendForApprovalWithViolation.action';
		document.getElementById('paraFrm').submit();
	}
 	
 	function draftFun(){
 	
 	//checkDatewithJoiningDate();
 	if(!validateDraft()){
			return false;
		} else {
 		var approver = document.getElementById('paraFrm_checkReportingStructure').value;
 		var employee = document.getElementById('paraFrm_initiatorName').value;
 		if(approver==0) {
 			alert("Reporting Structure Not Defined for the Employee\n"+employee);
 			return false;
 		}
 		var policyCode = document.getElementById('paraFrm_policyCode').value;
 		if(policyCode=="0")
 		{
 			alert("Travel Policy Not Defined for the Employee\n"+employee);
 			return false;
 		}
 			document.getElementById('paraFrm_draftOrSend').value='N';
 			getEmployeeIds();
 		}
 	} 
 	
 	function backFun(){
 		document.getElementById('paraFrm').target = "_self";
 		if(document.getElementById('source').value=='mymessages')
		{
			document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
			document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}
		else if(document.getElementById('source').value=='mytimecard')
		{
			document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_mytimeCard.action';
		}
		else
		{
			document.getElementById('paraFrm').action="TravelApplication_back.action";
		}
		document.getElementById('paraFrm').submit();
 	}
 	
 	function revokeFun(){
 		var conf=confirm("Are you sure you want to revoke this record ?");
  		if(conf) {
	 		document.getElementById('paraFrm').target = "_self";
	 		document.getElementById('paraFrm').action="TravelApplication_revoke.action";
			document.getElementById('paraFrm').submit();
		}
 	}
 	
 	function deleteFun(){
 		var conf=confirm("Are you sure you want to delete this record ?");
  			if(conf) {
		document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = "TravelApplication_delete.action";
		document.getElementById('paraFrm').submit();
		}
 	}
 	
 	function viewpolicyFun(){
 	
 		var gradeId = document.getElementById('paraFrm_initiatorGradeId').value;
 		win=window.open('','win','top=260,left=250,width=650,height=600,scrollbars=yes,status=no,resizable=no');
		document.getElementById("paraFrm").target="win";
		document.getElementById("paraFrm").action="TravelApplication_getTravelPolicy.action?gradeId="+gradeId;
		document.getElementById("paraFrm").submit();	
		document.getElementById("paraFrm").target="main"; 
 	}  
 	
 	function sendforapprovalFun(){
 		
 		try{
 		
 		//alert("sendforapprovalFun1");
 		if(!validateDraft()){
 		//alert("sendforapprovalFun if");
			return false;
		} else {
		
		//alert("sendforapprovalFun else");
			var approver = document.getElementById('paraFrm_checkReportingStructure').value;
 			var employee = document.getElementById('paraFrm_initiatorName').value;
 		if(approver==0)	{
 			alert("Reporting Structure Not Defined for the Employee\n"+employee);
 			return false;
 		}
 		var policyCode = document.getElementById('paraFrm_policyCode').value;
 		if(policyCode=="0")
 		{
 			alert("Travel Policy Not Defined for the Employee\n"+employee);
 			return false;
 		}
			document.getElementById('paraFrm_draftOrSend').value='P';
 			getEmployeeIds();
		}
 		
 		}catch(e){
 		alert(e);
 		}
 	} 
	
	function deleteRowFromTable(tableName){
		var table = document.getElementById(tableName);
		table.deleteRow(1);
	}
	
	function callAddKeepInfo(){
		try{
		  var keepInformCode = document.getElementById("paraFrm_informCode").value;
		  var keepInformedName = document.getElementById("paraFrm_informName").value;
		  if(keepInformedName==""){
		  	alert("Please select keep informed to");
		  	return false;
		  }
		  var tbl = document.getElementById('keepInformedTable');
		  var lastRow = tbl.rows.length -1;
		  var iteration = lastRow+" ) ";
		  var row = tbl.insertRow(lastRow);
		  
		  var cell0 = row.insertCell(0);
		  var column0 = document.createTextNode(iteration);
		  cell0.appendChild(column0);
	  
   		  var cell1 = row.insertCell(1);
		  var column1 = document.createElement('input');
  		  column1.type = 'text';
  		  column1.style.border = 'none';
		  column1.name = 'keepInformToName';
		  column1.value = keepInformedName; /*value to be set in the added cell*/
		  column1.id = 'keepInformToName'+iteration;
		  column1.size='20';
		  column1.maxLength='50';
		  cell1.align='left';
		  cell0.appendChild(column1);
		  
		  var cell2= row.insertCell(2);
		  var column2 = document.createElement('img');
		  column2.type='image';
		  column2.src="../pages/common/css/icons/delete.gif";
		  column2.align='absmiddle';
		  column2.id='img'+ iteration;
		  column2.theme='simple';
		  cell2.align='left';

		  column2.onclick=function(){
		  try {
		   	deleteCurrentRow(this);
		  	
		  }catch(e){alert(e);}
		  };
		  cell1.appendChild(column2);
		  
		  var column3 = document.createElement('input');
		  column3.type = 'hidden';
  		  column3.name = 'keepInformToCode';
  		  column3.value = keepInformCode; /*value to be set in the added cell*/
		  column3.id = 'keepInformToCode'+iteration;
		  column3.maxLength ='2';
		  cell2.appendChild(column3);
		  
	
		}catch(e){alert(e);}
		document.getElementById("paraFrm_informName").value="";
	
	}
	
	/*function setFieldId(id,action){
	try{
			document.getElementById('paraFrm_fieldName').value=id;
	      //  callsF9(500,325,action);
	      
	        }catch(e){alert(e);}
	}*/
	
	
	function setFieldId(event,id,action,textFieldName){
	try{
	        callDropdown(textFieldName,200,250,action,event,'false');
	        }catch(e){alert(e);}
	}
	
	/*############# COMMON FUNCTION'S ENDS ############################*/
	
	
	/* ########### EMPLOYEE INFORMATION BLOCK STARTS ##################*/
	
	function addSelfToList(initiatorToken,initiatorName, initiatorAge, initiatorContact, advanceAmount, empType, initEmployeeId, empGradeId,empDateOfBirth, currencyEmployeeAdvance ){
		
		var contact= "";
		var age= "";
		var name = document.getElementById(initiatorName).value;
		var token = document.getElementById(initiatorToken).value;
		var advAmt = document.getElementById(advanceAmount).value;
		var empId = document.getElementById(initEmployeeId).value;
		var employeeGradeId = document.getElementById(empGradeId).value;
		var employeeBirthDate = document.getElementById(empDateOfBirth).value;
		
		if(document.getElementById(initiatorAge).value == "null"){
			age = "" ;
		}else{
			age = document.getElementById(initiatorAge).value;
		}
		
		if(document.getElementById(initiatorContact).value == "null"){
			contact = "" ;
		}else{
			contact = document.getElementById(initiatorContact).value;
		}
		
			if(advAmt == "null"){
			advAmt = 0 ;
		}else{
			advAmt =advAmt;  
		}
		advAmt=0;
		
		addRowToRef(token,name, age, contact, advAmt, empType, empId, employeeGradeId, employeeBirthDate, currencyEmployeeAdvance);
	}
	
	/*FUNCTION TO ADD OTHER EMPLOYEE & GUEST TO THE LIST*/
	function addEmployeeToList(method){
	
		window.open('','new','top=50,left=300,width=650,height=220,scrollbars=no,status=no,resizable=no');
		document.getElementById("paraFrm").target="new";
	 	document.getElementById("paraFrm").action="TravelApplication_"+method+".action"; 
	  	document.getElementById("paraFrm").submit();
	
	}      
	/*******THIS FUNCTION ADDS NEW ROW WITH VALUES TO THE EMPLOYEE INFORMATION BLOCK********/                 						
	 
	
	function addRowToRef(token ,name, age, contact, advanceAmount, emptype, empId, empGradeId, birthDate, currencyEmployeeAdvance)	{
	 
	  
	  	 if(emptype == "S"){
		  
		  	if(document.getElementById('paraFrm_counterVal').value == 2){
		  		
		  			var isSelfExist=false;
		  			for (var i = 0; i < document.getElementsByTagName("*").length; i++) {
						if(document.getElementsByTagName("*")[i].name == 'employeeTypeFromList'){
							if(document.getElementsByTagName("*")[i].value == "S"){
						 	alert("Self is already added to the list");
						 	return false;
							}
						}
					}
		  	}
		  }
		  var tbl = document.getElementById('tblRef');
		  var lastRow = tbl.rows.length;
		  // if there's no header row in the table, then iteration = lastRow + 1
		 
		  var iteration = lastRow;
		  var row = tbl.insertRow(lastRow);
	  	
   		  var cell0 = row.insertCell(0);
      	  var column0 = document.createElement('input');
      	  var columnT = document.createElement('input');
      	  cell0.className='sortableTD';
		  column0.type = 'text';
		  columnT.type = 'hidden';
		  column0.name = 'employeeNameFromList';
		  column0.value = name; /*value to be set in the added cell*/
		  columnT.value = token; /*value to be set in the added cell*/
		  column0.id = 'paraFrm_employeeNameFromList'+iteration;
		  column0.readOnly='true';
		  column0.size='30';
		  columnT.size='15';
		  column0.maxLength='50';
		  cell0.align='center';
		  cell0.appendChild(columnT);
		  cell0.appendChild(column0);
  		
  		if( emptype == "G" ){	
		  var cell1 = row.insertCell(1);
		  var column1 = document.createElement('input');
		  cell1.className='sortableTD';
  		  column1.type = 'text';
  		  column1.readOnly='true';
  		  column1.style.background='#F2F2F2';
		  column1.name = 'employeeDateOfBirthFromList';
		  column1.value = birthDate; /*value to be set in the added cell*/
		  column1.id = 'paraFrm_employeeDateOfBirthFromList'+iteration;
		  column1.size='10';
		  cell1.align='center';
		  cell1.appendChild(column1);
		  }else{
		  var cell1 = row.insertCell(1);
		  var column1 = document.createElement('input');
		  cell1.className='sortableTD';
  		  column1.type = 'text';
  		  column1.readOnly = 'true';
		  column1.name = 'employeeDateOfBirthFromList';
		  column1.value = birthDate; /*value to be set in the added cell*/
		  column1.id = 'paraFrm_employeeDateOfBirthFromList'+iteration;
		  column1.size='10';
		  cell1.align='center';
		  cell1.appendChild(column1);
		  }
		  
		  var cell2 = row.insertCell(2);
		  var column2 = document.createElement('input');
		  cell2.className='sortableTD';
  		  column2.type = 'text';
		  column2.name = 'employeeAgeFromList';
		  column2.value = age; /*value to be set in the added cell*/
		  column2.id = 'paraFrm_employeeAgeFromList'+iteration;
		  column2.readOnly='true';
		  column2.size='5';
		  column2.maxLength='3';
		  column2.onkeypress=function(){
		   		return numbersOnly();
		  	};
		  cell2.align='center';
		  cell2.appendChild(column2);
		  
		  var cell3 = row.insertCell(3);
		  var column3 = document.createElement('input');
		  cell3.className='sortableTD';
		  column3.type = 'text';
  		  column3.name = 'employeeContactFromList';
  		  column3.value = contact; /*value to be set in the added cell*/
		  column3.id = 'paraFrm_employeeContactFromList'+iteration;
		  column3.size ='20';
		  column3.maxLength ='13';
		  column3.onkeypress=function(){
		   		return numbersOnly();
		  	};
		  cell3.align='center';
		  cell3.appendChild(column3);
		  
		  var cell4 = row.insertCell(4);
		  var column4 = document.createElement('input');
		  cell4.className='sortableTD';
		  column4.type='text';
		  column4.name = 'currencyEmployeeAdvance';
  		  column4.value = document.getElementById('paraFrm_defaultCurrency').value; 
		  column4.id = 'paraFrm_currencyEmployeeAdvance'+iteration;
		  column4.size ='3';
		  column4.style.border="none";
		  column4.readOnly = 'true';
		  column4.title="Click here to change currency" 
		  column4.onclick=function(event){
		  try {
		   	callDropdown('paraFrm_currencyEmployeeAdvance'+iteration, 200, 250, 'TravelApplication_f9Currency.action?currencyID='+iteration,event,'false','no','right');
		  }catch(e){alert(e);}
		  };
		  cell4.appendChild(column4);
		  
		  if( emptype == "G" ){
			  	
			  	var column5 = document.createElement('input');
			  	column5.type = 'text';
			  	column5.readOnly = 'true';
			  	column5.style.background='#F2F2F2';
	  		  	column5.name = 'employeeAdvanceFromList';
	  		  	column5.value = advanceAmount; /*value to be set in the added cell*/
			  	column5.id = 'paraFrm_employeeAdvanceFromList'+iteration;
			  	column5.size ='10';
			  	column5.maxLength ='10';
			  	cell4.appendChild(column5);
			  }else{
			  	var column5 = document.createElement('input');
			  	column5.type = 'text';
	  		  	column5.name = 'employeeAdvanceFromList';
	  		  	column5.value = advanceAmount; /*value to be set in the added cell*/
			  	column5.id = 'paraFrm_employeeAdvanceFromList'+iteration;
			  	column5.size ='10';
			  	column5.maxLength ='10';
			  	column5.onkeypress=function(){
			   		return numbersWithDot();
		  	};
		  	cell4.appendChild(column5);
		  }
		  
		  var cell5= row.insertCell(5);
		  var column6 = document.createElement('img');
		  cell5.className='sortableTD';
		  column6.type='image';
		  column6.src="../pages/common/css/icons/delete.gif";
		  column6.align='absmiddle';
		  column6.id='img'+ iteration;
		  column6.theme='simple';
		  cell5.align='center';
		
		  column6.onclick=function(){
		  try {
		  	
		   deleteEmployeeCurrentRow(this, emptype);
		  	
		  }catch(e){alert(e);}
		  };
		  cell5.appendChild(column6);
		  
		  
		  var column7 = document.createElement('input');
		  column7.type = 'hidden';
  		  column7.name = 'employeeTypeFromList';
  		  column7.value = emptype; /*value to be set in the added cell*/
		  column7.id = 'paraFrm_employeeTypeFromList'+iteration;
		  column7.maxLength ='2';
		  cell5.appendChild(column7);

		  var column8 = document.createElement('input');
		  column8.type = 'hidden';
  		  column8.name = 'employeeTravellerIdFromList';
  		  column8.value = empId; /*value to be set in the added cell*/
		  column8.id = 'paraFrm_employeeTravellerIdFromList'+iteration;
		  column8.maxLength ='2';
		  cell5.appendChild(column8);
		 
		  var column9 = document.createElement('input');
		  column9.type = 'hidden';
  		  column9.name = 'travellerGradeId';
  		  column9.value = empGradeId; /*value to be set in the added cell*/
		  column9.id = 'paraFrm_travellerGradeId'+iteration;
		  column9.maxLength ='2';
		  cell5.appendChild(column9);
		  
		  document.getElementById('paraFrm_counterVal').value =2 ;
	}
	
	
	function removeCheckedEmployee_Old(tableID){
		try{
		 	var table = document.getElementById(tableID); 
            var rowCount = table.rows.length; 
            
             for(var i=1; i<rowCount; i++) { 
                 var row = table.rows[i]; 
                 var chkbox = row.cells[5].childNodes[5]; 
                 if(null != chkbox && true == chkbox.checked) { 
                     table.deleteRow(i); 
                 } 
             } 
		}catch(e){alert(e);}
	}
	
	
	
	 function removeCheckedEmployee(tableID) { 
             try { 
             var table = document.getElementById(tableID); 
             var rowCount = table.rows.length - 1; 
             var row = ""; 
             var chkbox = ""; 

             if(rowCount == 0){
             	alert("No rows to delete");
             }
             
             for(var i=1; i<=rowCount; i++) { 
               	row = table.rows[i];
               	chkbox = row.cells[5].childNodes[5];	
                if(chkbox.checked == true) { 
                    table.deleteRow(i); 
                 }  
             } 
             }catch(e) { 
                 alert(e); 
             } 
         } 
	
   
	
	var TABLE_NAME = 'journeyTable'; // this should be named in the HTML
	var ROW_BASE = 0;
	var hasLoaded = false;
	/* ########################### EMPLOYEE INFORMATION BLOCK ENDS ##############################*/  
	
	/* ########################### JOURNEY DETAILS BLOCK STARTS ##############################*/  
	function addJourneyDetials(){
	
		addRowToJourneyBlock();
	
	}
	
	function addRowToJourneyBlock()	{
	
		  var tbl = document.getElementById('journeyTable');
		  var lastRow = tbl.rows.length;
		  var iteration = lastRow-1;
		  var row = tbl.insertRow(lastRow);
	  
   		  var cell0 = row.insertCell(0);
   		  //added by lucky
   		   var luckytext = document.createElement('input');
   		    luckytext.type = 'hidden';
		  luckytext.name = 'lucky';
		  luckytext.id = 'lucky'+iteration;
		  luckytext.value=iteration;
		   cell0.appendChild(luckytext);
		  
      	  var column0 = document.createElement('input');
      	  cell0.className='sortableTD';
      	   	   
		  column0.type = 'text';
		  column0.name = 'journeyFromPlace';
		  column0.id = 'paraFrm_journeyFromPlace'+iteration;
		  column0.size='20';
		  column0.maxLength='50';
		  column0.onkeyup = function(event){
		  try {
		   	setFieldId(event,iteration,'TravelApplication_f9City.action?fieldName=journeyFromPlace'+iteration+'&randomId='+Math.random(),'paraFrm_journeyFromPlace'+iteration);
		  	
		  }catch(e){alert(e);}
		  };
		  cell0.align='center';
		  cell0.appendChild(column0);
  
		  var cell1 = row.insertCell(1);
		  var column1 = document.createElement('input');
		  cell1.className='sortableTD';
  		  column1.type = 'text';
		  column1.name = 'journeyToPlace';
		  column1.id = 'paraFrm_journeyToPlace'+iteration;
		  column1.size='20';
		  column1.maxLength='50';
		  column1.onkeyup = function(event){
		  try {
		   	setFieldId(event,iteration,'TravelApplication_f9City.action?fieldName=journeyToPlace'+iteration+'&randomId='+Math.random(),'paraFrm_journeyToPlace'+iteration);
		  	
		  }catch(e){alert(e);}
		  };
		  cell1.align='center';
		  cell1.appendChild(column1);
		  
		  var cell2 = row.insertCell(2);
		  var column2 = document.createElement('input');
		  cell2.className='sortableTD';
		  column2.type = 'text';
		  column2.readOnly = 'true';
  		  column2.name = 'journeyMode';
		  column2.id = 'paraFrm_journeyMode'+iteration;
		  column2.size ='20';
		  column2.maxLength ='50';
		  cell2.align='center';
		  cell2.appendChild(column2);
		  
		  var cell3 = row.insertCell(3);
		  var column3 = document.createElement('img');
		  cell3.className='sortableTD';
		  column3.type='image';
		  column3.src="../pages/images/recruitment/search2.gif";
		  column3.align='center';
		  column3.id='img'+ iteration;
		  column3.theme='simple';
		  column3.onclick=function(event){
		  try {
		   	setFieldId(event,iteration,'TravelApplication_f9JourneyMode.action?fieldName='+iteration+'&randomId='+Math.random(),'paraFrm_journeyMode'+iteration);
		  }catch(e){alert(e);}
		  };
		  cell3.appendChild(column3);
		  
		  var cell4 = row.insertCell(4);
		  var column4 = document.createElement('input');
		  cell4.className='sortableTD';
		  column4.type = 'text';
  		  column4.name = 'journeyDate';
  		  column4.onkeypress=function(){
		   return numbersWithHiphen();
		  };
		  column4.id = 'paraFrm_journeyDate'+iteration;
		  column4.size ='7';
		  column4.maxLength ='10';
		  cell4.align='center';
		  cell4.appendChild(column4);
		  
		  var cell5 = row.insertCell(5);
		  var column5 = document.createElement('img');
		  cell5.className='sortableTD';
		  column5.type='image';
		  column5.src="../pages/images/recruitment/Date.gif";
		  column5.align='center';
		  column5.id='img'+ iteration;
		  column5.theme='simple';
		  column5.onclick=function(){
		  try {
				NewCal('paraFrm_journeyDate'+iteration,'DDMMYYYY');
		  }catch(e){alert(e);}
		  };
		  cell5.appendChild(column5);
		  
		  var cell6 = row.insertCell(6);
		  var column6 = document.createElement('input');
		  cell6.className='sortableTD';
		  column6.type = 'text';
  		  column6.name = 'journeyTime';
		  column6.id = 'paraFrm_journeyTime'+iteration;
		  column6.size ='5';
		  column6.maxLength ='10';
		  cell6.align='center';
		  cell6.appendChild(column6);
		  
		  
		  /*DELETE BUTTON*/
		  
		  var cell7= row.insertCell(7);
		  var column7 = document.createElement('img');
		  cell7.className='sortableTD';
		  column7.type='image';
		  column7.src="../pages/common/css/icons/delete.gif";
		  column7.align='absmiddle';
		  column7.id='img'+ iteration;
		  column7.theme='simple';
		  cell7.align='center';

		  column7.onclick=function(){
		  try {
		   	deleteCurrentRow(this);
		  	
		  }catch(e){alert(e);}
		  };
		  cell7.appendChild(column7);
		  
		  
		  var column8 = document.createElement('input');
		  column8.type = 'hidden';
  		  column8.name = 'journeyModeId';
		  column8.id = 'paraFrm_journeyModeId'+iteration;
		  column8.maxLength ='2';
		  cell7.appendChild(column8);
	      row.myRow = new myRowObject(luckytext, column0, column1, column2,column3,column4,column5,column6,column7,column8);
	}
	
	function reorderRows(tbl, startingIndex)
	{
			if (tbl.tBodies[0].rows[startingIndex]) {
			var count = startingIndex + ROW_BASE;
				for (var i=startingIndex; i<tbl.tBodies[0].rows.length; i++) {
				try{
				
				var iteration_id=tbl.tBodies[0].rows[i].myRow.one.value-1;
				
				tbl.tBodies[0].rows[i].myRow.one.value=tbl.tBodies[0].rows[i].myRow.one.value-1;
				
			
				}catch(e){
				alert(e);
				}
			try{
				var oneId=tbl.tBodies[0].rows[i].myRow.one.id.toString();
				var twoId=tbl.tBodies[0].rows[i].myRow.two.id.toString();
				var threeId=tbl.tBodies[0].rows[i].myRow.three.id.toString();
				var fourId=tbl.tBodies[0].rows[i].myRow.four.id.toString();
				var fiveId=tbl.tBodies[0].rows[i].myRow.five.id.toString();
				var sixId=tbl.tBodies[0].rows[i].myRow.six.id.toString();
				var sevenId=tbl.tBodies[0].rows[i].myRow.seven.id.toString();
				var eightId=tbl.tBodies[0].rows[i].myRow.eight.id.toString();
				var nineId=tbl.tBodies[0].rows[i].myRow.nine.id.toString();
				var tenId=tbl.tBodies[0].rows[i].myRow.ten.id.toString();
				}catch(e){
				alert(e);
				}
				
				tbl.tBodies[0].rows[i].myRow.one.id=oneId.substr(0,oneId.length-1)+iteration_id;
				tbl.tBodies[0].rows[i].myRow.two.id=twoId.substr(0,twoId.length-1)+iteration_id;
				
				 document.getElementById(tbl.tBodies[0].rows[i].myRow.two.id).onkeyup = function(event){
				  try {
				   var idFrom=this.id;
				  var newFrom=idFrom.split('_')[1];
				  
				   	setFieldId(event,iteration_id,'TravelApplication_f9City.action?fieldName='+newFrom+'&randomId='+Math.random(),this.id);
				  	
				  }catch(e){alert(e);}
				  };
				  
					tbl.tBodies[0].rows[i].myRow.three.id=threeId.substr(0,threeId.length-1)+iteration_id;
						
						 tbl.tBodies[0].rows[i].myRow.three.onkeyup = function(event){
				  try {
				   var idTo=this.id;
				  var newTo=idTo.split('_')[1];
				  
				   	setFieldId(event,iteration_id,'TravelApplication_f9City.action?fieldName='+newTo+'&randomId='+Math.random(),this.id);
				  	
				  }catch(e){alert(e);}
				  };
				tbl.tBodies[0].rows[i].myRow.four.id=fourId.substr(0,fourId.length-1)+iteration_id;
				tbl.tBodies[0].rows[i].myRow.five.id=fiveId.substr(0,fiveId.length-1)+iteration_id;
				
				 tbl.tBodies[0].rows[i].myRow.five.onclick=function(event){
		       try {
		         var idJM=this.id;
				  var newJM=idJM.split('g')[1];
		  
		  		setFieldId(event,iteration_id,'TravelApplication_f9JourneyMode.action?fieldName='+newJM+'&randomId='+Math.random(),'paraFrm_journeyMode'+newJM);
					  }catch(e){alert(e);}
					  };
				tbl.tBodies[0].rows[i].myRow.six.id=sixId.substr(0,sixId.length-1)+iteration_id;
				tbl.tBodies[0].rows[i].myRow.seven.id=sevenId.substr(0,sevenId.length-1)+iteration_id;
				 tbl.tBodies[0].rows[i].myRow.seven.onclick=function(){
		   		 try {
		             var idDate=this.id;
				     var newDate=idDate.split('img')[1];
					NewCal('paraFrm_journeyDate'+newDate,'DDMMYYYY');
		  			}catch(e){alert(e);}
				  };
				tbl.tBodies[0].rows[i].myRow.eight.id=eightId.substr(0,eightId.length-1)+iteration_id;
				tbl.tBodies[0].rows[i].myRow.nine.id=nineId.substr(0,nineId.length-1)+iteration_id;
				
				 tbl.tBodies[0].rows[i].myRow.nine.onclick=function(){
				  try {
				   	deleteCurrentRow(this);
				  	
				  }catch(e){alert(e);}
				  };
				try{
				tbl.tBodies[0].rows[i].myRow.ten.id=tenId.substr(0,tenId.length-1)+iteration_id;
				
				}catch(e){
				alert(e);
				}
				
				count++;
				}
			}
	}
	
		// CONFIG:
// myRowObject is an object for storing information about the table rows
function myRowObject(one, two, three, four,five,six,seven,eight,nine,ten)
{
	this.one = one; // text object
	this.two = two; // input text object
	this.three = three; // input checkbox object
	this.four = four; // input radio object
	this.five=five;
	this.six=six;
	this.seven=seven;
	this.eight=eight;
	this.nine=nine;
	this.ten=ten;
}
	
	
	/* ########################### JOURNEY DETAILS BLOCK ENDS ##############################*/  

	/* ########################### ACCOMODATION DETAILS BLOCK STARTS ##############################*/  
	function addAccomodationDetials(){
	
		addRowToAccomodationBlock();
	
	}
	
	function addRowToAccomodationBlock()	{
	try{
		  var tbl = document.getElementById('accomodationTable');
		  var lastRow = tbl.rows.length;
		  var iteration = lastRow-1;
		  var row = tbl.insertRow(lastRow);
	  
   		  var cell0 = row.insertCell(0);
   		  
   		try{
   		  var accomText = document.createElement('input');
   		    accomText.type = 'hidden';
		    accomText.name = 'accomText';
		    accomText.id = 'accomText'+iteration;
		    accomText.value=iteration;
		   cell0.appendChild(accomText);
   		}catch(e){
   		alert(e);
   		}
   		  
      	  var column0 = document.createElement('input');
      	  cell0.className='sortableTD';
		  column0.type = 'text';
		  column0.readOnly = 'true';
		  column0.name = 'accomodationHotelType';
		  column0.id = 'paraFrm_accomodationHotelType'+iteration;
		  column0.size='6';
		  column0.maxLength='20';
		  cell0.align='center';
		  cell0.appendChild(column0);
		  
		  var cell1 = row.insertCell(1);
		  var column1 = document.createElement('img');
		  cell1.className='sortableTD';
		  column1.type='image';
		  column1.src="../pages/images/recruitment/search2.gif";
		  column1.align='center';
		  column1.id='img'+ iteration;
		  column1.theme='simple';
		  column1.onclick=function(event){
		  try {
		  setFieldId(event,iteration,'TravelApplication_f9LodgingType.action?fieldName='+iteration,'paraFrm_accomodationHotelType'+iteration);
		  }catch(e){alert(e);}
		  };
		  cell1.appendChild(column1);
  
		  var cell2 = row.insertCell(2);
		  var column2 = document.createElement('input');
		  cell2.className='sortableTD';
  		  column2.type = 'text';
  		  column2.readOnly = 'true';
		  column2.name = 'accomodationRoomType';
		  column2.id = 'paraFrm_accomodationRoomType'+iteration;
		  column2.size='6';
		  column2.maxLength='20';
		  cell2.align='center';
		  cell2.appendChild(column2);
		  
		  var cell3 = row.insertCell(3);
		  var column3 = document.createElement('img');
		  cell3.className='sortableTD';
		  column3.type='image';
		  column3.src="../pages/images/recruitment/search2.gif";
		  column3.align='center';
		  column3.id='img'+ iteration;
		  column3.theme='simple';
		  column3.onclick=function(event){
		  try {
		  	callRoomType(event,iteration);
		  	//setFieldId(event,iteration,'TravelApplication_f9RoomType.action?fieldName='+iteration,'paraFrm_accomodationRoomType'+iteration);
		  }catch(e){alert(e);}
		  };
		  cell3.appendChild(column3);
		  
		  var cell4 = row.insertCell(4);
		  var column4 = document.createElement('input');
		  cell4.className='sortableTD';
		  column4.type = 'text';
  		  column4.name = 'accomodationCity';
		  column4.id = 'paraFrm_accomodationCity'+iteration;
		  column4.size ='10';
		  column4.maxLength ='50';
		  column4.onkeyup = function(event){
		  try {
		   	setFieldId(event,iteration,'TravelApplication_f9City.action?fieldName=accomodationCity'+iteration,'paraFrm_accomodationCity'+iteration);
		  	
		  }catch(e){alert(e);}
		  };
		  cell4.align='center';
		  cell4.appendChild(column4);
		  
		  var cell5 = row.insertCell(5);
		  var column5 = document.createElement('input');
		  cell5.className='sortableTD';
		  column5.type = 'text';
  		  column5.name = 'accomodationPrefLocation';
		  column5.id = 'paraFrm_accomodationPrefLocation'+iteration;
		  column5.size ='10';
		  column5.maxLength ='50';
		  cell5.align='center';
		  cell5.appendChild(column5);
		  
		  var cell6 = row.insertCell(6);
		  var column6 = document.createElement('input');
		  cell6.className='sortableTD';
		  column6.type = 'text';
  		  column6.name = 'accomodationFromDate';
  		  column6.onkeypress=function(){
		   return numbersWithHiphen();
		  };
		  column6.id = 'paraFrm_accomodationFromDate'+iteration;
		  column6.size ='7';
		  column6.maxLength ='10';
		  cell6.align='center';
		  cell6.appendChild(column6);
		  
		  var cell7 = row.insertCell(7);
		  var column7 = document.createElement('img');
		  cell7.className='sortableTD';
		  column7.type='image';
		  column7.src="../pages/images/recruitment/Date.gif";
		  column7.align='center';
		  column7.id='img'+ iteration;
		  column7.theme='simple';
		  column7.onclick=function(){
		  try {
				NewCal('paraFrm_accomodationFromDate'+iteration,'DDMMYYYY');
		  }catch(e){alert(e);}
		  };
		  cell7.appendChild(column7);
		  
		  var cell8 = row.insertCell(8);
		  var column8 = document.createElement('input');
		  cell8.className='sortableTD';
		  column8.type = 'text';
  		  column8.name = 'accomodationFromTime';
		  column8.id = 'paraFrm_accomodationFromTime'+iteration;
		  column8.size ='5';
		  column8.maxLength ='10';
		  cell8.align='center';
		  cell8.appendChild(column8);
		 
		  var cell9 = row.insertCell(9);
		  var column9 = document.createElement('input');
		  cell9.className='sortableTD';
		  column9.type = 'text';
  		  column9.name = 'accomodationToDate';
  		  column9.onkeypress=function(){
		   return numbersWithHiphen();
		  };
		  column9.id = 'paraFrm_accomodationToDate'+iteration;
		  column9.size ='7';
		  column9.maxLength ='10';
		  cell9.align='center';
		  cell9.appendChild(column9);
		  
		  var cell10 = row.insertCell(10);
		  var column10 = document.createElement('img');
		  cell10.className='sortableTD';
		  column10.type='image';
		  column10.src="../pages/images/recruitment/Date.gif";
		  column10.align='center';
		  column10.id='img'+ iteration;
		  column10.theme='simple';
		  column10.onclick=function(){
		  try {
				NewCal('paraFrm_accomodationToDate'+iteration,'DDMMYYYY');
		  }catch(e){alert(e);}
		  };
		  cell10.appendChild(column10);
		  
		  var cell11 = row.insertCell(11);
		  var column11 = document.createElement('input');
		  cell11.className='sortableTD';
		  column11.type = 'text';
  		  column11.name = 'accomodationToTime';
		  column11.id = 'paraFrm_accomodationToTime'+iteration;
		  column11.size ='5';
		  column11.maxLength ='5';
		  cell11.align='center';
		  cell11.appendChild(column11);
		  
		  
		  var cell12= row.insertCell(12);
		  var column12 = document.createElement('img');
		  cell12.className='sortableTD';
		  column12.type='image';
		  column12.src="../pages/common/css/icons/delete.gif";
		  column12.align='absmiddle';
		  column12.id='img'+ iteration;
		  column12.theme='simple';
		  cell12.align='center';

		  column12.onclick=function(){
		  try {
		  	
		   	deleteAccoRow(this);
		  	
		  }catch(e){alert(e);}
		  };
		  cell12.appendChild(column12);
		  
		  var column13 = document.createElement('input');
		  column13.type = 'hidden';
  		  column13.name = 'accomodationHotelTypeId';
		  column13.id = 'paraFrm_accomodationHotelTypeId'+iteration;
		  column13.maxLength ='2';
		  cell0.appendChild(column13);
		  
		  var column14 = document.createElement('input');
		  column14.type = 'hidden';
  		  column14.name = 'accomodationRoomTypeId';
		  column14.id = 'paraFrm_accomodationRoomTypeId'+iteration;
		  column14.maxLength ='2';
		  cell0.appendChild(column14);
		 row.myRow = new myAccomRowObject(accomText, column0, column1, column2,column3,column4,column5,column6,column7,column8,column9,column10,column11,column12,column13,column14); 
	}catch(e){
	alert(e);
	}
	}

	//Added by tinshuk begins
	function reorderAccoRows(tbl, startingIndex)
	{
	 if (tbl.tBodies[0].rows[startingIndex]) {
	 
		var count = startingIndex + ROW_BASE;
			for (var i=startingIndex; i<tbl.tBodies[0].rows.length; i++) {
				var iteration_id=tbl.tBodies[0].rows[i].myRow.accomText.value-1;
				
				tbl.tBodies[0].rows[i].myRow.accomText.value=tbl.tBodies[0].rows[i].myRow.accomText.value-1;
				
			  try{
				var accomId=tbl.tBodies[0].rows[i].myRow.accomText.id.toString();
				var oneId=tbl.tBodies[0].rows[i].myRow.column0.id.toString();
				var twoId=tbl.tBodies[0].rows[i].myRow.column1.id.toString();
				var threeId=tbl.tBodies[0].rows[i].myRow.column2.id.toString();
				var fourId=tbl.tBodies[0].rows[i].myRow.column3.id.toString();
				var fiveId=tbl.tBodies[0].rows[i].myRow.column4.id.toString();
				var sixId=tbl.tBodies[0].rows[i].myRow.column5.id.toString();
				var sevenId=tbl.tBodies[0].rows[i].myRow.column6.id.toString();
				var eightId=tbl.tBodies[0].rows[i].myRow.column7.id.toString();
				var nineId=tbl.tBodies[0].rows[i].myRow.column8.id.toString();
				var tenId=tbl.tBodies[0].rows[i].myRow.column9.id.toString();
				var elevenId=tbl.tBodies[0].rows[i].myRow.column10.id.toString();
				var twelveId=tbl.tBodies[0].rows[i].myRow.column11.id.toString();
				var therteenId=tbl.tBodies[0].rows[i].myRow.column12.id.toString();
				var fourteenId=tbl.tBodies[0].rows[i].myRow.column13.id.toString();
				var fifteenId=tbl.tBodies[0].rows[i].myRow.column14.id.toString();
				}catch(e){
				alert(e);
				}
				tbl.tBodies[0].rows[i].myRow.accomText.id=accomId.substr(0,accomId.length-1)+iteration_id;
				tbl.tBodies[0].rows[i].myRow.column0.id=oneId.substr(0,oneId.length-1)+iteration_id;
				tbl.tBodies[0].rows[i].myRow.column1.id=twoId.substr(0,twoId.length-1)+iteration_id;
				 tbl.tBodies[0].rows[i].myRow.column1.onclick=function(event){
				 try{
				  var idLodge=this.id;
				  var newLodge=idLodge.split('g')[1];
				 setFieldId(event,iteration_id,'TravelApplication_f9LodgingType.action?fieldName='+newLodge,'paraFrm_accomodationHotelType'+newLodge);
				 }catch(e){
				 alert(e);
				 }
				 }
				 	
				tbl.tBodies[0].rows[i].myRow.column2.id=threeId.substr(0,threeId.length-1)+iteration_id;
				tbl.tBodies[0].rows[i].myRow.column3.id=fourId.substr(0,fourId.length-1)+iteration_id;
				
							tbl.tBodies[0].rows[i].myRow.column3.onclick=function(event){
					  try {
					  var idRoom=this.id;
			     	  var newRoom=idRoom.split('g')[1];
					  	callRoomType(event,newRoom);
					  }catch(e){alert(e);}
					  };
				tbl.tBodies[0].rows[i].myRow.column4.id=fiveId.substr(0,fiveId.length-1)+iteration_id;
				tbl.tBodies[0].rows[i].myRow.column4.onkeyup = function(event){
				  try {
				  var idCity=this.id;
				  var newCity=idCity.split('_')[1];
				  	setFieldId(event,iteration_id,'TravelApplication_f9City.action?fieldName='+newCity,this.id);
				  }catch(e){alert(e);}
				  };
				tbl.tBodies[0].rows[i].myRow.column5.id=sixId.substr(0,sixId.length-1)+iteration_id;
				tbl.tBodies[0].rows[i].myRow.column6.id=sevenId.substr(0,sevenId.length-1)+iteration_id;
				tbl.tBodies[0].rows[i].myRow.column7.id=eightId.substr(0,eightId.length-1)+iteration_id;
				tbl.tBodies[0].rows[i].myRow.column7.onclick=function(){
					  try {
					  
					   var idDate=this.id;
				       var newDate=idDate.split('img')[1];
				  
							NewCal('paraFrm_accomodationFromDate'+newDate,'DDMMYYYY');
					  }catch(e){alert(e);}
					  };
				tbl.tBodies[0].rows[i].myRow.column8.id=nineId.substr(0,nineId.length-1)+iteration_id;
				tbl.tBodies[0].rows[i].myRow.column9.id=tenId.substr(0,tenId.length-1)+iteration_id;
				
				tbl.tBodies[0].rows[i].myRow.column9.onkeypress=function(){
				   return numbersWithHiphen();
				   };
				tbl.tBodies[0].rows[i].myRow.column10.id=elevenId.substr(0,elevenId.length-1)+iteration_id;
				tbl.tBodies[0].rows[i].myRow.column10.onclick=function(){
					  try {
					    var idToDate=this.id;
				        var newToDate=idToDate.split('img')[1];
							NewCal('paraFrm_accomodationToDate'+newToDate,'DDMMYYYY');
					  }catch(e){alert(e);}
					  };
				tbl.tBodies[0].rows[i].myRow.column11.id=twelveId.substr(0,twelveId.length-1)+iteration_id;
				tbl.tBodies[0].rows[i].myRow.column12.id=therteenId.substr(0,therteenId.length-1)+iteration_id;
				
				tbl.tBodies[0].rows[i].myRow.column12.onclick=function(){
					   	deleteAccoRow(this);
					  };
				tbl.tBodies[0].rows[i].myRow.column13.id=fourteenId.substr(0,fourteenId.length-1)+iteration_id;
				tbl.tBodies[0].rows[i].myRow.column14.id=fifteenId.substr(0,fifteenId.length-1)+iteration_id;
				count++;
			}
		}
	}
	

function deleteAccoRow(obj){
	try{
       var delRow = obj.parentNode.parentNode;
		var tbl = delRow.parentNode.parentNode;
		var rIndex = delRow.sectionRowIndex;
		var rowArray = new Array(delRow);
		var conf= confirm("Do you really want to Delete Records");
		if(conf){
		 deleteRows(rowArray);
		 reorderAccoRows(tbl, rIndex);
		}
	}catch(e){
	alert(e);
	}
}

 function myAccomRowObject(accomText, column0, column1, column2,column3,column4,column5,column6,column7,column8,column9,column10,column11,column12,column13,column14)
{
	try{
	this.accomText = accomText;
	this.column0 = column0; 
	this.column1 = column1; 
	this.column2 = column2; 
	this.column3 = column3;
	this.column4 = column4;
	this.column5 = column5;
	this.column6 = column6;
	this.column7 = column7;
	this.column8 = column8;
	this.column9 = column9;
	this.column10 = column10;
	this.column11 = column11;
	this.column12 = column12;
	this.column13 = column13;
	this.column14 = column14;
	}catch(e){
	alert(e);
	}
}
	//Added by tinshuk ends
	/* ########################### ACCOMODATION DETAILS BLOCK ENDS ##############################*/  

/* ########################### LOCAL CONVEYANCE DETAILS BLOCK STARTS ##############################*/  
	
	function addLocalConveyanceDetials(){
		addRowToLocalConveyanceBlock();
	}
	
	function addRowToLocalConveyanceBlock()	{
	
		  var tbl = document.getElementById('localConveyanceTable');
		  var lastRow = tbl.rows.length;
		  var iteration = lastRow-1;
		  var row = tbl.insertRow(lastRow);
	  
   		  var cell0 = row.insertCell(0);
   		  
   		  try{
   		  var localConvText = document.createElement('input');
   		    localConvText.type = 'hidden';
		  localConvText.name = 'localConvText';
		  localConvText.id = 'localConvText'+iteration;
		  localConvText.value=iteration;
		   cell0.appendChild(localConvText);
   		}catch(e){
   		alert(e);
   		}
      	  var column0 = document.createElement('input');
      	  cell0.className='sortableTD';
		  column0.type = 'text';
		  column0.name = 'localConveyanceCity';
		  column0.id = 'paraFrm_localConveyanceCity'+iteration;
		  column0.size='10';
		  column0.maxLength='50';
		  column0.onkeyup = function(event){
		  try {
		   	setFieldId(event,iteration,'TravelApplication_f9City.action?fieldName=localConveyanceCity'+iteration,'paraFrm_localConveyanceCity'+iteration);
		  	
		  }catch(e){alert(e);}
		  };
		  cell0.align='center';
		  cell0.appendChild(column0);
  
		  var cell1 = row.insertCell(1);
		  var column1 = document.createElement('input');
		  cell1.className='sortableTD';
  		  column1.type = 'text';
		  column1.name = 'localConveyanceTravelDetail';
		  column1.id = 'paraFrm_localConveyanceTravelDetail'+iteration;
		  column1.size='10';
		  column1.maxLength='50';
		  cell1.align='center';
		  cell1.appendChild(column1);
		  
		  var cell2 = row.insertCell(2);
		  var column2 = document.createElement('input');
		  cell2.className='sortableTD';
		  column2.type = 'text';
  		  column2.name = 'localConveyanceTravelMedium';
		  column2.id = 'paraFrm_localConveyanceTravelMedium'+iteration;
		  column2.size ='10';
		  column2.maxLength ='50';
		  cell2.align='center';
		  cell2.appendChild(column2);
		  
		  var cell3 = row.insertCell(3);
		  var column3 = document.createElement('input');
		  cell3.className='sortableTD';
		  column3.type = 'text';
  		  column3.name = 'localConveyanceFromDate';
  		  column3.onkeypress=function(){
		   return numbersWithHiphen();
		  };
		  column3.id = 'paraFrm_localConveyanceFromDate'+iteration;
		  column3.size ='7';
		  column3.maxLength ='10';
		  cell3.align='left';
		  cell3.appendChild(column3);
		  
		  var cell4 = row.insertCell(4);
		  var column4 = document.createElement('img');
		  cell4.className='sortableTD';
		  column4.type='image';
		  column4.src="../pages/images/recruitment/Date.gif";
		  column4.align='center';
		  column4.id='img'+ iteration;
		  column4.theme='simple';
		  column4.onclick=function(){
		  try {
				NewCal('paraFrm_localConveyanceFromDate'+iteration,'DDMMYYYY');
		  }catch(e){alert(e);}
		  };
		  cell4.appendChild(column4);
		  
		  
		  var cell5 = row.insertCell(5);
		  var column5 = document.createElement('input');
		  cell5.className='sortableTD';
		  column5.type = 'text';
  		  column5.name = 'localConveyanceFromTime';
		  column5.id = 'paraFrm_localConveyanceFromTime'+iteration;
		  column5.size ='5';
		  column5.maxLength ='5';
		  cell5.align='center';
		  cell5.appendChild(column5);
		  
		  var cell6 = row.insertCell(6);
		  var column6 = document.createElement('input');
		  cell6.className='sortableTD';
		  column6.type = 'text';
  		  column6.name = 'localConveyanceToDate';
  		  column6.onkeypress=function(){
		   return numbersWithHiphen();
		  };
		  column6.id = 'paraFrm_localConveyanceToDate'+iteration;
		  column6.size ='7';
		  column6.maxLength ='10';
		  cell6.align='center';
		  cell6.appendChild(column6);
		  
		  var cell7 = row.insertCell(7);
		  var column7 = document.createElement('img');
		  cell7.className='sortableTD';
		  column7.type='image';
		  column7.src="../pages/images/recruitment/Date.gif";
		  column7.align='center';
		  column7.id='img'+ iteration;
		  column7.theme='simple';
		  column7.onclick=function(){
		  try {
				NewCal('paraFrm_localConveyanceToDate'+iteration,'DDMMYYYY');
		  }catch(e){alert(e);}
		  };
		  cell7.appendChild(column7);
		 
		  var cell8 = row.insertCell(8);
		  var column8 = document.createElement('input');
		  cell8.className='sortableTD';
		  column8.type = 'text';
  		  column8.name = 'localConveyanceToTime';
		  column8.id = 'paraFrm_localConveyanceToTime'+iteration;
		  column8.size ='5';
		  column8.maxLength ='5';
		  cell8.align='center';
		  cell8.appendChild(column8);
		  
		  var cell9= row.insertCell(9);
		  var column9 = document.createElement('img');
		  cell9.className='sortableTD';
		  column9.type='image';
		  column9.src="../pages/common/css/icons/delete.gif";
		  column9.align='absmiddle';
		  column9.id='img'+ iteration;
		  column9.theme='simple';
		  cell9.align='center';

		  column9.onclick=function(){
		  try {
		   deleteConvRow(this);
		  }catch(e){alert(e);}
		  };
		  cell9.appendChild(column9);
		  
		  var column10 = document.createElement('input');
		  column10.type = 'hidden';
  		  column10.name = 'localConveyanceCode';
		  column10.id = 'paraFrm_localConveyanceCode'+iteration;
		  column10.maxLength ='2';
		  cell8.appendChild(column10);
		 
		  row.myRow = new myConvRowObject(localConvText, column0, column1, column2,column3,column4,column5,column6,column7,column8,column9,column10);
		
	}
	//Added by tinshuk begins
function deleteConvRow(obj){
	try{
       var delRow = obj.parentNode.parentNode;
		var tbl = delRow.parentNode.parentNode;
		var rIndex = delRow.sectionRowIndex;
		var rowArray = new Array(delRow);
		var conf= confirm("Do you really want to Delete Records");
		if(conf){
		 deleteRows(rowArray);
		 reorderConvRows(tbl, rIndex);
		}
	}catch(e){
	alert(e);
	}
}
 function myConvRowObject(localConvText, column0, column1, column2,column3,column4,column5,column6,column7,column8,column9,column10)
{
	try{
	this.localConvText = localConvText;
	this.column0 = column0; 
	this.column1 = column1; 
	this.column2 = column2; 
	this.column3 = column3;
	this.column4 = column4;
	this.column5 = column5;
	this.column6 = column6;
	this.column7 = column7;
	this.column8 = column8;
	this.column9 = column9;
	this.column10 = column10;
	}catch(e){
	alert(e);
	}
}

function reorderConvRows(tbl, startingIndex)
	{
	 if (tbl.tBodies[0].rows[startingIndex]) {
		var count = startingIndex + ROW_BASE;
			for (var i=startingIndex; i<tbl.tBodies[0].rows.length; i++) {
				var iteration_id=tbl.tBodies[0].rows[i].myRow.localConvText.value-1;
				
				tbl.tBodies[0].rows[i].myRow.localConvText.value=tbl.tBodies[0].rows[i].myRow.localConvText.value-1;
		
		   try{
				var convId=tbl.tBodies[0].rows[i].myRow.localConvText.id.toString();
				var oneId=tbl.tBodies[0].rows[i].myRow.column0.id.toString();
				var twoId=tbl.tBodies[0].rows[i].myRow.column1.id.toString();
				var threeId=tbl.tBodies[0].rows[i].myRow.column2.id.toString();
				var fourId=tbl.tBodies[0].rows[i].myRow.column3.id.toString();
				var fiveId=tbl.tBodies[0].rows[i].myRow.column4.id.toString();
				var sixId=tbl.tBodies[0].rows[i].myRow.column5.id.toString();
				var sevenId=tbl.tBodies[0].rows[i].myRow.column6.id.toString();
				var eightId=tbl.tBodies[0].rows[i].myRow.column7.id.toString();
				var nineId=tbl.tBodies[0].rows[i].myRow.column8.id.toString();
				var tenId=tbl.tBodies[0].rows[i].myRow.column9.id.toString();
				var elevenId=tbl.tBodies[0].rows[i].myRow.column10.id.toString();
				
				}catch(e){
				alert(e);
				}
				
				tbl.tBodies[0].rows[i].myRow.localConvText.id=convId.substr(0,convId.length-1)+iteration_id;
				tbl.tBodies[0].rows[i].myRow.column0.id=oneId.substr(0,oneId.length-1)+iteration_id;
				
				 tbl.tBodies[0].rows[i].myRow.column0.onkeyup = function(event){
		         try {
		             var idCity=this.id;
				     var newCity=idCity.split('_')[1];
		   			setFieldId(event,iteration_id,'TravelApplication_f9City.action?fieldName='+newCity,idCity);
		  	
		 			 }catch(e){alert(e);}
		  		};
				
				tbl.tBodies[0].rows[i].myRow.column1.id=twoId.substr(0,twoId.length-1)+iteration_id;
				
				tbl.tBodies[0].rows[i].myRow.column2.id=threeId.substr(0,threeId.length-1)+iteration_id;
				tbl.tBodies[0].rows[i].myRow.column3.id=fourId.substr(0,fourId.length-1)+iteration_id;
				
		       tbl.tBodies[0].rows[i].myRow.column3.onkeypress=function(){
				   return numbersWithHiphen();
				  };
				
				tbl.tBodies[0].rows[i].myRow.column4.id=fiveId.substr(0,fiveId.length-1)+iteration_id;
				tbl.tBodies[0].rows[i].myRow.column4.onclick=function(){
		  			try {
		  			    var idFromDate=this.id;
				        var newFromDate=idFromDate.split('img')[1];
							NewCal('paraFrm_localConveyanceFromDate'+newFromDate,'DDMMYYYY');
		  					}catch(e){alert(e);}
		  					};
				
				tbl.tBodies[0].rows[i].myRow.column5.id=sixId.substr(0,sixId.length-1)+iteration_id;
				tbl.tBodies[0].rows[i].myRow.column6.id=sevenId.substr(0,sevenId.length-1)+iteration_id;
				
				tbl.tBodies[0].rows[i].myRow.column6.onkeypress=function(){
				   return numbersWithHiphen();
				  };
				
				tbl.tBodies[0].rows[i].myRow.column7.id=eightId.substr(0,eightId.length-1)+iteration_id;
				tbl.tBodies[0].rows[i].myRow.column7.onclick=function(){
					  try {
					     var idToDate=this.id;
				        var newToDate=idToDate.split('img')[1];
							NewCal('paraFrm_localConveyanceToDate'+newToDate,'DDMMYYYY');
					  }catch(e){alert(e);}
					  };
				
				tbl.tBodies[0].rows[i].myRow.column8.id=nineId.substr(0,nineId.length-1)+iteration_id;
				tbl.tBodies[0].rows[i].myRow.column9.id=tenId.substr(0,tenId.length-1)+iteration_id;
				
 				tbl.tBodies[0].rows[i].myRow.column9.onclick=function(){
		 			 try {
		 			  deleteConvRow(this);
		  				}catch(e){alert(e);}
		 			};
				tbl.tBodies[0].rows[i].myRow.column10.id=elevenId.substr(0,elevenId.length-1)+iteration_id;
			
				count++;
		   }
		 }	
	}	
	
	//Added by tinshuk ends
	/* ########################### LOCAL CONVEYANCE DETAILS BLOCK ENDS ##############################*/  

 function checkAppStatus(obj,id)
  {
   
  	try{
  	var approverComments = document.getElementById('paraFrm_approverComments').value;
   	var conf;
  	document.getElementById("paraFrm_checkApproveRejectStatus").value=id; 
  	if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="A") {
      	 var fieldName=["paraFrm_approverComments"];
		    var lableName=["approverComm"];
		    var flag = ["enter"];
		    	if(!(validateBlank(fieldName,lableName,flag))){
		    	document.getElementById('paraFrm_approverComments').focus();
					return false;
		        }
      
       conf=confirm("Do you really want to approve this application ?");
      }
      if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="R")
       conf=confirm("Do you really want to reject this application ?");
     
       if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="B")
       {
		     var fieldName=["paraFrm_approverComments"];
		    var lableName=["approverComm"];
		    var flag = ["enter"];
		    	if(!(validateBlank(fieldName,lableName,flag))){
					return false;
		        }
		    
       }
       if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="B")
       conf=confirm("Do you really want to send back this application ?");
       
        
 		 if(conf)
				{
				 	obj.disabled=true;
			 		document.getElementById("paraFrm").target="main";
					document.getElementById("paraFrm").action="TravelApplication_approveRejSendBackTravelApp.action";
		  			document.getElementById("paraFrm").submit();
		  			window.close();
				 }
				 else
				 {
				 return false; 
  				}
  				
  			}catch(e){alert(e);}	
  		return true; 		
  }
	
	
	////////////////////////////////////////////////////////////////////////////
	
	 	function validateDraft(){
 	
 		try{
 			var tbl = document.getElementById('tblRef');
		  	var lastRow = tbl.rows.length;
		  	
 			/*Rows in employee information, journey, accomodation & local conveyance tables*/
 			
 			
 			var journeyTableRows=document.getElementById("journeyTable").rows.length-1;
 			var accomodationTableRows=document.getElementById("accomodationTable").rows.length-1;
 			var localConveyanceTableRows=document.getElementById("localConveyanceTable").rows.length-1;
 			
 			var startDate = document.getElementById("paraFrm_startDate").value;
 			var joiningDate = document.getElementById("paraFrm_joiningDate").value;
 			var endDate = document.getElementById("paraFrm_endDate").value;
 			var requestName = document.getElementById("paraFrm_trvlReqName").value;
 			var travelPurpose = document.getElementById("paraFrm_purpose").value;
 			var travelProject = document.getElementById("paraFrm_project").value;
 			var travelCustomer = document.getElementById("paraFrm_customerName").value;
 			var travelType = document.getElementById("paraFrm_trvlType").value;
 			
 			var employeeRow=lastRow-1;
 			
 			/* Application data block*/
 		
 			if(startDate==""){
 				alert("Please enter Travel start date");
 				document.getElementById('paraFrm_startDate').focus();
 				return false;
 			}
 			if(!validateDateFormat('paraFrm_startDate','Travel Start Date')){
 					return false;
 			}
 			var datediff1 = checkDateForApplication('paraFrm_joiningDate','paraFrm_startDate','Date Of Joining', 'Travel start date');
	  	  	if(!datediff1){
	  			return false;
	  		}
 			if(endDate==""){
 				alert("Please enter Travel end date");
 				document.getElementById('paraFrm_endDate').focus();
 				return false;
 			}
 			if(!validateDateFormat('paraFrm_endDate','Travel End Date')){
 					return false;
 			}
 			
 			var datediff = checkDateForApplication('paraFrm_startDate','paraFrm_endDate','Travel start date', 'Travel end date');
	  	  	if(!datediff){
	  			return false;
	  		}
	  		
	  		
	  		var fields=["paraFrm_trvlReqName"];
   			var labels=["TraReqname"];
    		var flag = ["enter"];
 	 		if(!validateBlank(fields,labels,flag))
    		 return false;
	  	
 			if(travelPurpose==""){
 				alert("Please select Travel Purpose");
 				document.getElementById('paraFrm_purpose').focus();
 				return false;
 			}
 			if(travelType==""){
 				alert("Please select Travel Type");
 				document.getElementById('paraFrm_trvlType').focus();
 				return false;
 			}
 			/* Check if employee is added*/
 			if( lastRow == 1){
		  		alert("Please add employee information");
		  		return false;
		  	}
 			
 			/*employee block to check 10 digit contact no */
 			for (var i = 0; i < document.getElementsByTagName("*").length; i++) {
				if(document.getElementsByTagName("*")[i].name == 'employeeContactFromList'){
				var contactNo = document.getElementsByTagName("*")[i].value;
					if(document.getElementsByTagName("*")[i].value == "" || contactNo.length < 10){
						alert("Please enter min 10 digit contact no");
						return false;
					}
				}
			}
 			/*for(var e=1; e<=employeeRow; e++){
 					var contactNo=document.getElementById('paraFrm_employeeContactFromList'+e).value;
 					if(contactNo.length < 10 ){
 						alert("Please enter min 10 digit contact no");
 						document.getElementById('paraFrm_employeeContactFromList'+e).focus();
 						return false;
 					}
 				}*/
 			
 			
 			/*journey block*/
 			
 			if(document.getElementById('paraFrm_journeyCheck').value==""){
 				alert("Please select self or company managed journey");
 				return false;
 			}
 			
 			if( journeyTableRows < 1){
		  		alert("Please add journey details");
		  		return false;
		  	}
 			
 			//alert("inside validate --"+journeyTableRows);
 			for( var i=0; i<journeyTableRows;i++){
 			//alert(i);
 			//alert(document.getElementById('paraFrm_journeyFromPlace'+i));
 			
 				if(document.getElementById('paraFrm_journeyFromPlace'+i).value==""){
 					alert("Please enter journey from place");
 					document.getElementById('paraFrm_journeyFromPlace'+i).focus();
 					return false;
 				}
 			//alert(2);
 				if(document.getElementById('paraFrm_journeyToPlace'+i).value==""){
 					alert("Please enter journey to place");
 					document.getElementById('paraFrm_journeyToPlace'+i).focus();
 					return false;
 				}
 				
 				if(document.getElementById('paraFrm_journeyMode'+i).value==""){
 					alert("Please select Journey Mode");
 					document.getElementById('paraFrm_journeyMode'+i).focus();
 					return false;
 				}
 				if(document.getElementById('paraFrm_journeyDate'+i).value==""){
 					alert("Please enter journey Date");
 					document.getElementById('paraFrm_journeyDate'+i).focus();
 					return false;
 				}
 				if(!validateDateFormat('paraFrm_journeyDate'+i,'Journey Date')){
 					return false;
 				}
 				
 				if(!checkDateBetweenForApplication('paraFrm_startDate','paraFrm_endDate','paraFrm_journeyDate'+i,'Travel start date','Travel end date','Journey Date')){
 					//alert(3);
 					return false;
 				}
 				if(document.getElementById('paraFrm_journeyTime'+i).value==""){
 					alert("Please enter journey Time");
 					document.getElementById('paraFrm_journeyTime'+i).focus();
 					return false;
 				}
 				if(!validateTimeMethod('paraFrm_journeyTime'+i, 'Time')){
 				//alert(4);
 					return false;
 				}
 				}
 			
 			/*accomodation block*/
 				
 			if (document.getElementById('paraFrm_accomCheck').value =="C"){
 				if( accomodationTableRows < 1){
		  			alert("Please add accomodation details");
		  			return false;
		  		}
 				for( var i=0; i<accomodationTableRows;i++){
 			
 				if(document.getElementById('paraFrm_accomodationHotelType'+i).value == ""){
 					alert("Please select accomodation hotel type");
 					return false;
 				}
 				if(document.getElementById('paraFrm_accomodationRoomType'+i).value == ""){
 					alert("Please select room type");
 					document.getElementById('paraFrm_accomodationRoomType'+i).focus();
 					return false;
 				}
 				if(document.getElementById('paraFrm_accomodationCity'+i).value== ""){
 					alert("Please enter accomodation city");
 					document.getElementById('paraFrm_accomodationCity'+i).focus();
 					return false;
 				}
 				if(document.getElementById('paraFrm_accomodationFromDate'+i).value == ""){
 					alert("Please select accomodation form date");
 					document.getElementById('paraFrm_accomodationFromDate'+i).focus();
 					return false;
 				}
 				if(!validateDateFormat('paraFrm_accomodationFromDate'+i,'Accomodation From Date')){
 					return false;
 				}
 				if(!checkDateBetweenForApplication('paraFrm_startDate','paraFrm_endDate','paraFrm_accomodationFromDate'+i,'Travel start date','Travel end date','Accomodation From Date')){
 					return false;
 				}
 				if(document.getElementById('paraFrm_accomodationFromTime'+i).value == ""){
 					alert("Please enter accomodation from time");
 					document.getElementById('paraFrm_accomodationFromTime'+i).focus();
 					return false;
 				}
 				if(!validateTimeMethod('paraFrm_accomodationFromTime'+i, 'Accomodation From Time')){
 				
 					return false;
 				}
 				if(document.getElementById('paraFrm_accomodationToDate'+i).value == ""){
 					alert("Please select accomodation to date");
 					document.getElementById('paraFrm_accomodationToDate'+i).focus();
 					return false;
 				}
 				if(!validateDateFormat('paraFrm_accomodationToDate'+i,'Accomodation To Date')){
 					return false;
 				}
 				if(!checkDateBetweenForApplication('paraFrm_accomodationFromDate'+i,'paraFrm_endDate','paraFrm_accomodationToDate'+i,'Accomodation From Date','Travel end date','Accomodation To Date')){
 					return false;
 				}
 				if(document.getElementById('paraFrm_accomodationToTime'+i).value == ""){
 					alert("Please enter accomodation to time");
 					document.getElementById('paraFrm_accomodationToTime'+i).focus();
 					return false;
 				}
 				if(!validateTimeMethod('paraFrm_accomodationToTime'+i, 'Accomodation To Time')){
 					return false;
 				}
 				/*if(!validateTimeDifference( 'paraFrm_accomodationFromDate'+i, 'paraFrm_accomodationToDate'+i, 'paraFrm_accomodationFromTime'+i, 'paraFrm_accomodationToTime'+i, 'Accomodation From Time', 'Accomodation To Time')){
 					return false;
 				}*/
 			}
 			}/*end of if*/
 		
 		/*local conveyance*/
 			
 			if( document.getElementById('paraFrm_locConCheck').value=="C"){
 				if( localConveyanceTableRows < 1){
		  			alert("Please add local conveyance details");
		  			return false;
		  		}
 				for( var i=0; i<localConveyanceTableRows;i++){
 			
 				if(document.getElementById('paraFrm_localConveyanceCity'+i).value ==""){
 					alert("Please enter city");
 					document.getElementById('paraFrm_localConveyanceCity'+i).focus();
 					return false;
 				}
 				if(document.getElementById('paraFrm_localConveyanceTravelMedium'+i).value ==""){
 					alert("Please enter local conveyance travel medium");
 					document.getElementById('paraFrm_localConveyanceTravelMedium'+i).focus();
 					return false;
 				}
 				if(document.getElementById('paraFrm_localConveyanceFromDate'+i).value ==""){
 					alert("Please enter local conveyance from date");
 					document.getElementById('paraFrm_localConveyanceFromDate'+i).focus();
 					return false;
 				}
 				if(!validateDateFormat('paraFrm_localConveyanceFromDate'+i,'Local Conveyance From Date')){
 					return false;
 				}
 				if(!checkDateBetweenForApplication('paraFrm_startDate','paraFrm_endDate','paraFrm_localConveyanceFromDate'+i,'Travel start date','Travel end date','Local Conveyance From Date')){
 					return false;
 				}
 				if(document.getElementById('paraFrm_localConveyanceFromTime'+i).value ==""){
 					alert("Please enter local conveyance from Time");
 					document.getElementById('paraFrm_localConveyanceFromTime'+i).focus();
 					return false;
 				}
 				if(!validateTimeMethod('paraFrm_localConveyanceFromTime'+i, 'Local Conveyance From Time')){
 					return false;
 				}
 				if(document.getElementById('paraFrm_localConveyanceToDate'+i).value ==""){
 					alert("Please enter local conveyance to date");
 					document.getElementById('paraFrm_localConveyanceToDate'+i).focus();
 					return false;
 				}
 				if(!validateDateFormat('paraFrm_localConveyanceToDate'+i,'Local Conveyance To Date')){
 					return false;
 				}
 				if(!checkDateBetweenForApplication('paraFrm_localConveyanceFromDate'+i,'paraFrm_endDate','paraFrm_localConveyanceToDate'+i,'Local Conveyance From Date','Travel end date','Local Conveyance To Date')){
 					return false;
 				}
 				if(document.getElementById('paraFrm_localConveyanceToTime'+i).value ==""){
 					alert("Please enter local conveyance to time");
 					document.getElementById('paraFrm_localConveyanceToTime'+i).focus();
 					return false;
 				}
 				
 				if(!validateTimeMethod('paraFrm_localConveyanceToTime'+i, 'Local Conveyance To Time')){
 					return false;
 				}
 				}
 			}
 			
 			
 		}catch(e){
 			//alert(e);
 			return false;
 		}
 		return true ; 
 	}
 	
 	
 	
 	function checkDateBetweenForApplication(startDate,enddate,enteredDt,startlabel,endlabel,enteredlabel){
 	
 		var fromDate     = document.getElementById(startDate).value;
		var toDate       = document.getElementById(enddate).value;
		var enteredDate  = document.getElementById(enteredDt).value;
		
	var strDate   = fromDate.split("-"); 
	var starttime = new Date(strDate[2],strDate[1]-1,strDate[0]); 
	
	var endDate   = toDate.split("-"); 
	var endtime   = new Date(endDate[2],endDate[1]-1,endDate[0]); 
	
	var validDate   = enteredDate.split("-");
	var validTime   = new Date(validDate[2],validDate[1]-1,validDate[0]); 
	
	if((validTime < starttime) || (validTime > endtime)) 
	{ 
		alert(""+enteredlabel+" should be between "+startlabel+" and "+endlabel);
		document.getElementById(enteredDt).focus();
		return false;
	}
	return true;
 	
 	}
 	
 	function checkDateForApplication(fromDate,todate,fromlabel,tolabel){
 		var fromDt =document.getElementById(fromDate).value ;
		var toDate = document.getElementById(todate).value ;
 		try{
 			var datediff = dateDifferenceChk(fromDt,toDate,fromDate,fromlabel,tolabel);
  	  		if(!datediff){
  				return false;
  			}
  		}catch(e){alert(e);}
 		return true;
 	}
	
 	function dateDifferenceChk(fromDate, toDate, fieldName, fromLabName, toLabName){
	var strDate1 = fromDate.split("-");
	var starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	var strDate2 = toDate.split("-"); 
	var endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
	
	if(endtime < starttime) { 
		alert(""+toLabName+" should be greater or equal to "+fromLabName);
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
}

	function validateTimeMethod(name, labName) {
		var time = document.getElementById(name).value;
		if(time == "") { return true; }
		
		var timeExp = /^[0-9]{2}[:]?[0-9]{2}$/;
		var timeArray = time.split(":");
		var hour = timeArray[0];
		var min = timeArray[1];
	
		if(!(time.match(timeExp)) || time.length < 5) {
			alert(labName + " should be in 24Hours HH:MM format");
			document.getElementById(name).focus();
			return false;
		}
	
		if(hour > 23) {
			alert("Hour " + hour + " is not valid");
			document.getElementById(name).focus();
			return false;
		}
	
		if(min > 59) {
			alert("Minute " + min + " is not valid");
			document.getElementById(name).focus();
			return false;
		}
		return true;
	}
	
 	
 	//////////////////////
 	
 	function validateDateFormat(fieldName, labName){

	var date = document.getElementById(fieldName).value;
	if(date=='') return true;
	var dateFormat = /^[0-9]{2}[-]?[0-9]{2}[-]?[0-9]{4}$/;
	
	if(!(date.match(dateFormat)) || date.length<10){
		alert(""+labName+" should be in DD-MM-YYYY format");
		document.getElementById(fieldName).focus();
		return false;
	}
	var dateArray = date.split("-");
	var day   = dateArray[0];
	var month = dateArray[1];
	var year  = dateArray[2];
	
	if(day<1 || day>31){
		alert("Day "+day+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if(month<1 || month>12){
		alert("Month "+month+" is not a valid month");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if(day>29 && month==2){
		window.alert("Day "+day+" of the month "+month+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if((month==2 && day==29) && ((year%4!=0) || (year%100==0 && year%400!=0))){
		window.alert("29th of February is not a valid date in "+year);
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if (day>30 && (month == 2 || month==4 || month==6 || month==9 || month==11)) {
		window.alert("Day "+day+" of the month "+month+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
}
 	function deleteCurrentRow(obj){
		var delRow = obj.parentNode.parentNode;
		var tbl = delRow.parentNode.parentNode;
		var rIndex = delRow.sectionRowIndex;
		var rowArray = new Array(delRow);
		var conf= confirm("Do you really want to Delete Records");
		if(conf){
		 deleteRows(rowArray);
		 reorderRows(tbl,rIndex);
		}
	}
	
	
	
	
 	function  deleteEmployeeCurrentRow(obj, emptype){
 	
 	if(emptype == "S"){
 		document.getElementById('paraFrm_counterVal').value=1;
 		document.getElementById('paraFrm_isAddClick').value='';
 	}
		var conf= confirm("Do you really want to Delete Records");
		var delRow = obj.parentNode.parentNode;
		var tbl = delRow.parentNode.parentNode;
		var rIndex = delRow.sectionRowIndex;
		var rowArray = new Array(delRow);
		if(conf){
		deleteRows(rowArray);
		}
		
	}
	
	function deleteRows(rowObjArray){
	try{
		for (var i=0; i<rowObjArray.length; i++) {
			var rIndex = rowObjArray[i].sectionRowIndex;
			rowObjArray[i].parentNode.deleteRow(rIndex);
		}
	}catch(e){
	alert(e);
	}
	}
 	
 	function hideShowOnRadioClick(tableId, chkValue, addButton){
 		try{
 			var accomodationTableRows=document.getElementById("accomodationTable").rows.length-1;
 			var localConveyanceTableRows=document.getElementById("localConveyanceTable").rows.length-1;
 			
 			if(chkValue == "S"){
				document.getElementById(tableId).style.display="none";
				document.getElementById(addButton).style.display="none";
			}else{
		    	document.getElementById(tableId).style.display="";
		    	document.getElementById(addButton).style.display="";
		    	if(tableId=="localConv"){
		    	if(localConveyanceTableRows<"1"){
		    		addLocalConveyanceDetials();
		    		}
		    	}else{
		    	if(accomodationTableRows<"1"){
		    			addAccomodationDetials();
		    		}
		    	}
			}
 		}catch(e){alert(e);}
 		return true;
 	}
 	
 	function checkJourney(id){
 		document.getElementById('paraFrm_journeyCheck').value=id.value;
 	}
 	function checkAccomo(id){
 		document.getElementById('paraFrm_accomCheck').value=id.value;
 	}
 	function checkLocConv(id){
 		document.getElementById('paraFrm_locConCheck').value=id.value;
 	}
 	
 	function callRoomType(event,id) {
		try{
			var hotelId = document.getElementById('paraFrm_accomodationHotelTypeId'+id).value;
			if(document.getElementById('paraFrm_accomodationHotelType'+id).value == ""){
		  		alert("Please select Hotel Type");
		  		return false;
			}else {
 	 			setFieldId(event,id,'TravelApplication_f9RoomType.action?fieldName='+id+'&fieldValue='+hotelId,'paraFrm_accomodationRoomType'+id);
 	 		}
		}catch(e){
			alert(e);
		}
	}
	
	function setBlankField(fieldname){
		
		document.getElementById(fieldname).value ='';
	}
	
	function onLoad(){
	
		//initialize journeyTable begins
	var tbl = document.getElementById('journeyTable');
	
		try{
			for (var i=0; i<tbl.rows.length-1; i++) {
            var luckytext =document.getElementById('lucky'+i);
			var column0 =document.getElementById('paraFrm_journeyFromPlace'+i);
			var column1 =document.getElementById('paraFrm_journeyToPlace'+i);
			var column2 =document.getElementById('paraFrm_journeyMode'+i);
			var column3 =document.getElementById('img'+i);
			var column4 =document.getElementById('paraFrm_journeyDate'+i);
			var column5 =document.getElementById('img'+i);
			var column6 =document.getElementById('paraFrm_journeyTime'+i);
			var column7 =document.getElementById('img'+i);
			var column8 =document.getElementById('paraFrm_journeyModeId'+i);
			
			 var row =tbl.rows[i+1];
			 row.myRow = new myRowObject(luckytext, column0, column1, column2,column3,column4,column5,column6,column7,column8);
			
			}
			}catch(e){
			alert(e)
			}
			//initialize journeyTable ends
			
			//Added by tinshuk begins
			  //initialize accomodationTable begins
			try{
			 var tbl1 =document.getElementById('accomodationTable');
				for (var i=0; i<tbl1.rows.length-1; i++) {
			 	var accomText = document.getElementById('accomText'+i);
			 	document.getElementById('accomText'+i).value=i;
			 	var  column0 = document.getElementById('paraFrm_accomodationHotelType'+i);
			 	var column1 = document.getElementById('img'+i);
			 	var column2 = document.getElementById('paraFrm_accomodationRoomType'+i);
			 	var column3 = document.getElementById('img'+i);
			 	var column4 = document.getElementById('paraFrm_accomodationCity'+i);
			 	var column5 = document.getElementById('paraFrm_accomodationPrefLocation'+i);
			 	var column6 = document.getElementById('paraFrm_accomodationFromDate'+i);
			 	var column7 = document.getElementById('img'+i);
			 	var column8 = document.getElementById('paraFrm_accomodationFromTime'+i);
			 	var column9 = document.getElementById('paraFrm_accomodationToDate'+i);
			 	var column10 = document.getElementById('img'+i);
			 	var column11 = document.getElementById('paraFrm_accomodationToTime'+i);
			 	var column12 = document.getElementById('img'+i);
			 	var column13 = document.getElementById('paraFrm_accomodationHotelTypeId'+i);
			 	var column14 = document.getElementById('paraFrm_accomodationRoomTypeId'+i);
			 	
			 	var row1 =tbl1.rows[i+1];
			    row1.myRow = new myAccomRowObject(accomText, column0, column1, column2,column3,column4,column5,column6,column7,column8,column9,column10,column11,column12,column13,column14);
			 	
			 	}
			}catch(e){
			alert(e);
			}
		   //initialize accomodationTable ends
		 
		 
		  //initialize localConveyanceTable begins
		  
			try{
			 var tbl2 =document.getElementById('localConveyanceTable');
				for (var i=0; i<tbl2.rows.length-1; i++) {
			 	var localConvText = document.getElementById('localConvText'+i);
			 	document.getElementById('localConvText'+i).value=i;
			 	var  column0 = document.getElementById('paraFrm_localConveyanceCity'+i);
			 	var column1 = document.getElementById('paraFrm_localConveyanceTravelDetail'+i);
			 	var column2 = document.getElementById('paraFrm_localConveyanceTravelMedium'+i);
			 	var column3 = document.getElementById('paraFrm_localConveyanceFromDate'+i);
			 	var column4 = document.getElementById('img'+i);
			 	var column5 = document.getElementById('paraFrm_localConveyanceFromTime'+i);
			 	var column6 = document.getElementById('paraFrm_localConveyanceToDate'+i);
			 	var column7 = document.getElementById('img'+i);
			 	var column8 = document.getElementById('paraFrm_localConveyanceToTime'+i);
			 	var column9 = document.getElementById('img'+i);
			 	var column10 = document.getElementById('paraFrm_localConveyanceCode'+i);
			 	
			 	var row2 =tbl2.rows[i+1];
			    row2.myRow = new myConvRowObject(localConvText, column0, column1, column2,column3,column4,column5,column6,column7,column8,column9,column10);
			 	
			 	}
			}catch(e){
			alert(e);
			}
		 
		  //initialize localConveyanceTable ends
		//Added by tinshuk ends  
		  
		  
		if(document.getElementById('paraFrm_counterVal').value == 1){
			addSelfToList('paraFrm_initToken','paraFrm_initiatorName','paraFrm_initiatorAge','paraFrm_initiatorContact','paraFrm_initiatorAdvDetails','S','paraFrm_initId','paraFrm_initiatorGradeId','paraFrm_initiatorDateOfBirth','paraFrm_currencyEmployeeAdvance');
		}
	
	if (document.getElementById('paraFrm_accomCheck').value =="S"){
			document.getElementById('accomAdd').style.display="none";
		}
	if (document.getElementById('paraFrm_locConCheck').value =="S"){
			document.getElementById('localConvAdd').style.display="none";
		}
	}
	

	function reportFun(){
	
	document.getElementById('paraFrm').action="TravelApplication_report.action";
		document.getElementById('paraFrm').submit();
	
	
	}
	function getEmployeeIds(){
	var empIds='';
	var empAdvances='';
	var empGrades='';
	var empNames='';
	var empDetails='';
	var empTypes='';
	try{
	for (var i = 0; i < document.getElementsByTagName("*").length; i++) {
		if(document.getElementsByTagName("*")[i].name == 'employeeNameFromList'){
			empNames+=document.getElementsByTagName("*")[i].value+"-";
		}
		
		if(document.getElementsByTagName("*")[i].name == 'employeeAdvanceFromList'){
			empAdvances+=document.getElementsByTagName("*")[i].value+"-";
		}
		
		if(document.getElementsByTagName("*")[i].name == 'employeeTravellerIdFromList'){
			empIds+=document.getElementsByTagName("*")[i].value+"-";
		}
		
		if(document.getElementsByTagName("*")[i].name == 'travellerGradeId'){
			empGrades+=document.getElementsByTagName("*")[i].value+"-";
		}
		if(document.getElementsByTagName("*")[i].name == 'employeeTypeFromList'){
			empTypes+=document.getElementsByTagName("*")[i].value+"-";
		}
		
	}
	if(empIds!=''){
		empIds = empIds.substring(0,empIds.length-1);
		empAdvances = empAdvances.substring(0,empAdvances.length-1);
		empGrades = empGrades.substring(0,empGrades.length-1);
		empNames = empNames.substring(0,empNames.length-1);
		empTypes = empTypes.substring(0,empTypes.length-1);
		empDetails=empIds+"$"+empAdvances+" "+"$"+empGrades+"$"+empNames+"$"+empTypes;
	}
	
	if(empDetails!=''){
	var url='TravelApplication_checkForAdvanceAllow.action?pqr='+Math.random()+'&empDetails='+empDetails;
		checkForAdvanceAllow(url);
	}
	
 	}catch(e) { 
 	//alert(e);
	}
	}
	
	function chkProjectSelection(fieldId){
		var value = document.getElementById('paraFrm_project').value;
		var valueId = document.getElementById('paraFrm_projectId').value;
		
		if( value == ""){
			alert("Please select project");
			 document.getElementById('paraFrm_customerName').value="";
			return false;
		} else {
			setBlankField('paraFrm_otherCustomerName');
			callDropdown('paraFrm_customerName',200,250,'TravelApplication_f9Customer.action?fieldName='+valueId,'false');
			
		}
	}

</script>
