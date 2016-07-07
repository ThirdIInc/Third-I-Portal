<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

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

	<table width="100%" border="0" cellpadding="1" cellspacing="1"
		class="formbg" height="100%">
		<tr>
			<td valign="bottom" class="txt">
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
					<td width="35%" colspan="2">
					
			<input type="button"
						class="token" value="     Close " onclick="callClose();" />
					
					</td>
					<td width="30%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test='%{violationFlag =="Y"}'>
			<tr>
				<td>
				<table class="formbg" width="100%">
					<tr>
						<td colspan="2"><font color="red">The following policy deviations have occured </font>:
						 <s:property	value="policyViolationMsg" /></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>

		<s:if test="approverListFlag">
					<tr>
				<td colspan="7">
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
								Date </td>
								<td class="formth" width="10%" height="22" valign="top">Status
								</td>
								<td class="formth" width="30%" height="22" valign="top">Comments
								</td>
								</td>

							</tr>
							
						 
							<%
							int m = 1;
							%>
				<s:iterator value="approverCommentList" status="stat">
					<tr>
					<td width="10%" class="sortableTD"><%=m%><s:hidden name="appSrNo" value="%{<%=m%>}"/> </td>
						<td width="15%" class="sortableTD"><s:property value="prevApproverID"/><s:hidden name="prevApproverID"/></td>
							<td width="25%" class="sortableTD"><s:property value="prevApproverName"/><s:hidden name="prevApproverName"/></td>
								<td width="10%" class="sortableTD" align="center"><s:property value="prevApproverDate"/><s:hidden name="prevApproverDate"/></td>
								<td width="10%" class="sortableTD">&nbsp;<s:property value="prevApproverStatus"/><s:hidden name="prevApproverStatus"/></td>
									<td width="30%" class="sortableTD">&nbsp;<s:property value="prevApproverComment"/><s:hidden name="prevApproverComment"/></td>
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
					<s:hidden name="contact" id="paraFrm_initiatorContact" />
					<s:hidden name="advAmount" id="paraFrm_initiatorAdvDetails" />
				</tr>

				<tr>
					<td><label class="set" name="traappdate" id="traappdate"
						ondblclick="callShowDiv(this);"><%=label.get("traappdate")%>
					</label> :</td>
					<td><s:textfield name="appDate" id="paraFrm_appDate" theme="simple" size="10"
						maxlength="10"
						onblur="return validateDate('paraFrm_appDate','Date');"
						onkeypress="return numbersWithHiphen();" readonly="true" /></td>
						
					<td><label class="set" name="grade" id="grade1"
						ondblclick="callShowDiv(this);"><%=label.get("grade")%>
					</label> :</td>
					<td><s:property value="empBand" /></td>	
				
				</tr>

				<tr>
					<td width="25%"><label class="set" name="Trastdate"
						id="Trastdate" ondblclick="callShowDiv(this);"><%=label.get("Trastdate")%></label>
					<font color="red">*</font> :</td>
					<td width="25%"><s:textfield name="startDate" theme="simple" readonly="true"
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
					<td width="25%"><s:textfield name="endDate" theme="simple"  readonly="true"
						size="10" maxLength="10" onkeypress="return numbersWithHiphen();" />
					<s:if test="appRejFlag">

					</s:if> <s:else>
						<a href="javascript:NewCal('paraFrm_endDate','DDMMYYYY');"> <img
							src="../pages/images/Date.gif" class="iconImage" height="16"
							align="absmiddle" width="16" onclick="return checkDateForAccomodationLocalConvBlock('paraFrm_appDate','paraFrm_startDate', 'Application Date','Travel Start Date');"> </a>
					</s:else></td>
				</tr>
				<tr>
					<td><label class="set" name="TraReqname" id="TraReqname"
						ondblclick="callShowDiv(this);"><%=label.get("TraReqname")%></label>
					<font color="red">*</font>:</td>
					<td><s:textfield name="trvlReqName" theme="simple"  readonly="true"
						maxlength="100"
						onkeypress="return checkDateForApplicationBlock('paraFrm_startDate','paraFrm_endDate');" /></td>
					<td><label class="set" name="Trapurpose" id="Trapurpose"
						ondblclick="callShowDiv(this);"><%=label.get("Trapurpose")%></label>
					<font color="red">*</font>:</td>
					<td><s:hidden name="purposeId" /> <s:textfield name="purpose"
						theme="simple" size="20" readonly="true" /> <s:if
						test="appRejFlag">

					</s:if> <s:else>
						<img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="javascript:callsF9(500,325,'TravelApplication_f9Purpose.action');">
					</s:else></td>
				</tr>
				<tr>
					<td><label class="set" name="travelProj" id="travelProj"
						ondblclick="callShowDiv(this);"><%=label.get("travelProject")%></label>
					:</td>
					<td><s:hidden name="projectId" /> <s:textfield name="project"
						theme="simple" size="20" readonly="true" /> <s:if
						test="appRejFlag">

					</s:if> <s:else>
						<img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="javascript:callsF9(500,325,'TravelApplication_f9Project.action');">
					</s:else></td>
					<td width="20%"><label class="set" name="travelCust" id="travelCust"
						ondblclick="callShowDiv(this);"><%=label.get("otherProject")%></label>
					:</td>
					<td><s:textfield name="otherProject" theme="simple"  readonly="true"
						maxlength="100" /></td>
				</tr>
				<tr>
					<td><label class="set" name="travelCust" id="travelCust"
						ondblclick="callShowDiv(this);"><%=label.get("travelCustomer")%></label>
					:</td>
					<td><s:hidden name="customerId" /> <s:textfield
						name="customerName" theme="simple" size="20" readonly="true" /> <s:if
						test="appRejFlag">

					</s:if> <s:else>
						<img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="javascript:callsF9(500,325,'TravelApplication_f9Customer.action');">
					</s:else></td>
					<td width="20%"><label class="set" name="travelCust" id="travelCust"
						ondblclick="callShowDiv(this);"><%=label.get("otherCustomer")%></label>
					:</td>
					<td><s:textfield name="otherCustomerName" theme="simple"  readonly="true"
						maxlength="100" /></td>
				</tr>
				<tr>
					<td><label class="set" name="TravelType" id="TravelType"
						ondblclick="callShowDiv(this);"><%=label.get("TravelType")%></label>
					<font color="red">*</font>:</td>
					<td><s:hidden name="trvlTypeId" /> <s:textfield
						name="trvlType" theme="simple" size="20" readonly="true" /> <s:if
						test="appRejFlag">

					</s:if> <s:else>
						<img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="javascript:callsF9(500,325,'TravelApplication_f9TravelType.action');">
					</s:else></td>
							
					<td><label class="set" name="appStatus" id="appStatus"
						ondblclick="callShowDiv(this);"><%=label.get("appStatus")%>
					</label> :</td>
					<td><s:property value="appStatus" /></td>
				</tr>

				<!-- ##################################### KEEP INFORMED #########################-->
				<tr>
					<td align="left" colspan="4">
					<table width="100%" border="0" id="keepInformedTable">
						<tr>
							<td width="188px"><label class="set" name="keepInformedTo"
								id="keepInformedTo" ondblclick="callShowDiv(this);"><%=label.get("keepInformedTo")%></label></span>:</td>
							<td><s:hidden name="keepHidden" /> <s:hidden
								name="informCode" id="paraFrm_informCode" /> <s:hidden
								name="informToken" /> <s:if test="appRejFlag">

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


						<s:iterator value="keepInformedList">
							<tr>
								<td><s:hidden name="keepInformToCode"
									id="paraFrm_keepHidden<%=counter%>" /> &nbsp; <%=counter%> ) <s:property
									value="keepInformToName" /> &nbsp;</td>
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
			int srCounter = 0;
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
									<td align="right"><s:if test="appRejFlag">

									</s:if> <s:else>
										<input type="button" value="Self" class="add" theme="simple"
											onclick="addSelfToList('paraFrm_initToken','paraFrm_initiatorName','paraFrm_initiatorAge','paraFrm_initiatorContact','paraFrm_initiatorAdvDetails','S','paraFrm_initId','paraFrm_initiatorGradeId');" />
										<input type="button" value="Add Employee" class="token"
											theme="simple"
											onclick="addEmployeeToList('addOtherEmployeeToTravellerList');" />
										<input type="button" value="Add Guest" Class="token"
											theme="simple"
											onclick="addEmployeeToList('addGuestToTravellerList');" />
									</s:else></td>
								</tr>
								<tr>
									<td colspan="2">
									<table width="100%" id="tblRef" class="sortable" border="0">
							 			<tr>
											<td valign="top" class="formth" nowrap="nowrap"><b><label
												class="set" name="sr" id="sr"
												ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b></td>
											<td valign="top" class="formth" align="center"><b><label
												name="refName" id="refName" ondblclick="callShowDiv(this);"><%=label.get("empguest")%></label></b></td>
											<td valign="top" class="formth" align="center"><b><label
												name="prof" id="prof" ondblclick="callShowDiv(this);"><%=label.get("dateofbirth")%></label></b></td>
											<td valign="top" class="formth" align="center"><b><label
												name="prof" id="prof" ondblclick="callShowDiv(this);"><%=label.get("EmpAge")%></label></b></td>
												<td  class="formth" align="center"><b><label
												name="grade" id="grade2" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label></b></td>
											<td valign="top" class="formth" align="center"><b><label
												name="cont" id="cont" ondblclick="callShowDiv(this);"><%=label.get("ContactNO")%></label></b></td>
											<td valign="top" class="formth" align="center"><b><label
												name="advA" id="advA" ondblclick="callShowDiv(this);"><%=label.get("advanceDetails")%></label></b></td>

											<s:if test="%{trvlApp.appRejFlag}">

											</s:if>
											<s:else>
												<td class="formth" align="center">&nbsp</td>
											</s:else>

										</tr>
										<s:iterator value="travellerList">
											<tr>
												<td class="sortableTD" align="center"><%=++srCounter%></td>
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


												<input type="text"  readonly="true"
													id="paraFrm_employeeNameFromList<%=srCounter%>"
													name="employeeNameFromList"
													value='<s:property value="employeeNameFromList"/>'
													size="30" /></td>
												<td class="sortableTD" align="center"><input readonly="true"
													type="text" id="paraFrm_employeeDateOfBirthFromList<%=srCounter%>"
													name="employeeDateOfBirthFromList"
													value='<s:property value="employeeDateOfBirthFromList"/>' size="10" /></td>
												<td class="sortableTD" align="center"><input  readonly="true"
													type="text" id="paraFrm_employeeAgeFromList<%=srCounter%>"
													name="employeeAgeFromList"
													value='<s:property value="employeeAgeFromList"/>' size="20" /></td>
											
											<td class="sortableTD" align="center"><input  readonly="true"
													type="text" id="paraFrm_employeeBandFromList<%=srCounter%>"
													name="employeeBandFromList"
													value='<s:property value="employeeBandFromList"/>' size="10" readonly="true"/></td>
											
												<td class="sortableTD" align="center"><input  readonly="true"
													type="text"
													id="paraFrm_employeeContactFromList<%=srCounter%>"
													name="employeeContactFromList"
													value='<s:property value="employeeContactFromList"/>'
													size="20" /></td>

												<td class="sortableTD" align="center" nowrap="nowrap">
													<input type="text" size="3" style="border: none;" id="paraFrm_currencyEmployeeAdvance<%=srCounter%>" 
														  	name="currencyEmployeeAdvance" value='<s:property value="currencyEmployeeAdvance"/>' 
														  	readonly="readonly"
													/> 
												
													<input  readonly="true"
															type="text"
															id="paraFrm_employeeAdvanceFromList<%=srCounter%>"
															name="employeeAdvanceFromList"
															value='<s:property value="employeeAdvanceFromList"/>'
															size="10" />
												</td>

												<td align="center" class="sortableTD"><s:if
													test="%{trvlApp.appRejFlag}">

												</s:if> <s:else>
													<img src="../pages/common/css/icons/delete.gif"
														onclick="deleteRowFromTable('tblRef');">
												</s:else></td>
											</tr>
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
			<td colspan="4">
			<table width="100%" class="formbg" border="0">
				<tr>
					<td width="40%">Journey Details</td>
					<td width="40%"><input type="radio" name="journeyRadio"
						value="S" onclick="hideShowOnRadioClick('JourTable','S');" <%=journeyChk.equals("S")?"checked":"" %>>Self
					Managed <input type="radio" name="journeyRadio" value="C" onclick="hideShowOnRadioClick('JourTable','C');"
						<%=journeyChk.equals("C")?"checked":"" %>>Company Managed
					</td>
					<td align="right" width="20%"><s:if test="appRejFlag">

					</s:if> <s:else>
						<input type="button" value="Add" Class="token" theme="simple"
							onclick="addJourneyDetials();" />
					</s:else></td>
				</tr>
				<tr id="JourTable">
					<td colspan="4">
					<table width="100%" id="journeyTable" class="sortable" border="0">

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

						<s:if test="%{trvlApp.appRejFlag}">

						</s:if>

						<s:else>
							<s:if test='%{saveFlag!="save"}'>
								<tr>
									<td align="center" class="sortableTD"><input type="text"  readonly="true"
										name="journeyFromPlace"
										id="paraFrm_journeyFromPlace<%=fieldVal%>" theme="simple"
										size="20" /></td>
									<td align="center" class="sortableTD"><input type="text"  readonly="true"
										name="journeyToPlace" id="paraFrm_journeyToPlace<%=fieldVal%>"
										theme="simple" size="20" /></td> 
									<td align="center" class="sortableTD"><input type="text"  readonly="true"
										name='journeyMode' id="paraFrm_journeyMode<%=fieldVal%>"
										readOnly="true" theme="simple" size="20" /> <input
										type="hidden" name='journeyModeId'
										id="paraFrm_journeyModeId<%=fieldVal%>" /></td>
									<td align="center" class="sortableTD">
									&nbsp;
									<!-- 	<img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="16" align="center" width="16"
										onclick="setFieldId(<%=fieldVal%>,'TravelApplication_f9JourneyMode.action');"> -->
								
									</td>
									<td align="center" class="sortableTD"><input type="text"  readonly="true"
										name="journeyDate" id="paraFrm_journeyDate<%=fieldVal%>"
										theme="simple" size="7"
										onkeypress="return numbersWithHiphen();" /></td>
									<td align="center" class="sortableTD"><s:a
										href="javascript:NewCal('paraFrm_journeyDate0','DDMMYYYY');">
										<img src="../pages/images/recruitment/Date.gif"
											class="iconImage" align="center">
									</s:a></td>
									<td align="center" class="sortableTD"><input type="text"  readonly="true"
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
								<td class="sortableTD" align="center"><input type="text"  readonly="true"
									name="journeyFromPlace"
									id="paraFrm_journeyFromPlace<%=fieldVal%>"
									value='<s:property value="journeyFromPlace"/>' size="20" />&nbsp;</td>
								<td class="sortableTD" align="center"><input type="text"  readonly="true"
									name="journeyToPlace" id="paraFrm_journeyToPlace<%=fieldVal%>"
									value='<s:property value="journeyToPlace"/>' size="20" />&nbsp;</td>
								<td class="sortableTD" align="center"><input type="hidden"
									name='journeyModeId' id="paraFrm_journeyModeId<%=fieldVal%>"
									value='<s:property value="journeyModeId"/>' />&nbsp; <input
									type="text" name='journeyMode'  readonly="true"
									id="paraFrm_journeyMode<%=fieldVal%>"
									value='<s:property value="journeyMode"/>' size="20" />&nbsp;</td>
								<td align="center" class="sortableTD">
								&nbsp;
								<!--<img
									src="../pages/images/recruitment/search2.gif" class="iconImage"
									height="16" align="center" width="16"
									onclick="setFieldId(<%=fieldVal%>,'TravelApplication_f9JourneyMode.action');">
								--></td>
								<td class="sortableTD" align="center" colspan="2"><input
									type="text" name="journeyDate" readonly="true"
									id="paraFrm_journeyDate<%=fieldVal%>"
									value='<s:property value="journeyDate"/>' size="7" />&nbsp;</td>
								<td class="sortableTD" align="center"><input type="text" readonly="true"
									name="journeyTime" id="paraFrm_journeyTime<%=fieldVal%>"
									value='<s:property value="journeyTime"/>' size="10" />&nbsp;</td>
								<td align="center" class="sortableTD"><s:if
									test="%{trvlApp.appRejFlag}">

								</s:if> <s:else>
									<img src="../pages/common/css/icons/delete.gif"
										onclick="deleteCurrentRow(this);">
								</s:else></td>
							</tr>
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

				<td width="100%" id="lodgingBlock"
					style='display: <%= accomDisplay .       equals( "YES") ?       "block" :       "none" %>;'>

				<table width="100%" class="formbg">
					<tr>
						<td width="40%">Lodging Details</td>
						<td width="40%"><input type="radio" name="accomodationRadio"
							value="S" onclick="hideShowOnRadioClick('accomTable','S');" <%=accomodationChk.equals("S")?"checked":"" %>>Self
						Managed <input type="radio" name="accomodationRadio" value="C" onclick="hideShowOnRadioClick('accomTable','C');"
							<%=accomodationChk.equals("C")?"checked":"" %>>Company
						Managed</td>
						<td align="right" width="20%"><s:if test="appRejFlag">

						</s:if> <s:else>
							<input type="button" value="Add" Class="token" theme="simple"
								onclick="addAccomodationDetials();" />
						</s:else></td>
					</tr>
					<tr id="accomTable">
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
								</td>
							</tr>
							<s:if test='%{saveFlag!="save"}'>
								<tr>
									<td align="center" class="sortableTD"><input type="hidden"
										name='accomodationHotelTypeId'
										id="paraFrm_accomodationHotelTypeId<%=fieldVal%>" /> <input  
										type="text" name='accomodationHotelType'  readonly="true"
										id="paraFrm_accomodationHotelType<%=fieldVal%>" theme="simple"
										readOnly="true" size="6" /></td>
									<td align="center" class="sortableTD">
									&nbsp;
									<!--<img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" align="center"
										onclick="setFieldId(<%=fieldVal%>,'TravelApplication_f9LodgingType.action');">
									--></td>
									<td align="center" class="sortableTD"><input type="hidden"
										name='accomodationRoomTypeId'
										id="paraFrm_accomodationRoomTypeId<%=fieldVal%>" /> <input
										type="text" name='accomodationRoomType' readonly="true"
										id="paraFrm_accomodationRoomType<%=fieldVal%>" theme="simple"
										readOnly="true" size="6" /></td>
									<td align="center" class="sortableTD">&nbsp;<!--<img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" align="center"
										onclick="setFieldId(<%=fieldVal%>,'TravelApplication_f9RoomType.action');">
									--></td>
									<td align="center" class="sortableTD"><input type="hidden"
										name='accomodationCityId'
										id="paraFrm_accomodationCityId<%=fieldVal%>" /> <input
										type="text" name="accomodationCity"  readonly="true"
										id="paraFrm_accomodationCity<%=fieldVal%>" theme="simple"
										size="10" /></td>
									<td align="center" class="sortableTD"><input type="text"
										name="accomodationPrefLocation"  readonly="true"
										id="paraFrm_accomodationPrefLocation<%=fieldVal%>"
										theme="simple" size="10" /></td>
									<td align="center" class="sortableTD"><input type="text"
										name="accomodationFromDate"  readonly="true"
										id="paraFrm_accomodationFromDate<%=fieldVal%>" theme="simple"
										size="7" onkeypress="return numbersWithHiphen();" /></td>
									<td align="center" class="sortableTD"><s:a
										href="javascript:NewCal('paraFrm_accomodationFromDate0','DDMMYYYY');">
										<img src="../pages/images/recruitment/Date.gif"
											class="iconImage" align="center">
									</s:a></td>
									<td align="center" class="sortableTD"><input type="text"
										name="accomodationFromTime"  readonly="true"
										id="paraFrm_accomodationFromTime<%=fieldVal%>" theme="simple"
										size="5" /></td>

									<td align="left" class="sortableTD"><input type="text"
										name="accomodationToDate"  readonly="true"
										id="paraFrm_accomodationToDate<%=fieldVal%>" theme="simple"
										size="7" onkeypress="return numbersWithHiphen();" /></td>
									<td align="center" class="sortableTD"><s:a
										href="javascript:NewCal('paraFrm_accomodationToDate0','DDMMYYYY');">
										<img src="../pages/images/recruitment/Date.gif"
											class="iconImage" align="center">
									</s:a></td>
									<td align="center" class="sortableTD"><input type="text"
										name="accomodationToTime"  readonly="true"
										id="paraFrm_accomodationToTime<%=fieldVal%>" theme="simple"
										size="5"
										onkeypress="checkDateForAccomodationLocalConvBlock('paraFrm_accomodationFromDate0','paraFrm_accomodationToDate0','From date','To date');" /></td>
									<td align="center" class="sortableTD"><s:if
										test="%{trvlApp.appRejFlag}">

									</s:if> <s:else>
										<img src="../pages/common/css/icons/delete.gif"
											onclick="deleteCurrentRow(this);">
									</s:else></td>
								</tr>
							</s:if>

							<s:iterator value="accomodationList">
								<tr>
									<td class="sortableTD" align="center"><input type="hidden"
										name='accomodationHotelTypeId'
										id="paraFrm_accomodationHotelTypeId<%=accomVal%>"
										value='<s:property value="accomodationHotelTypeId"/>' /> <input
										type="text" name='accomodationHotelType'  readonly="true"
										id="paraFrm_accomodationHotelType<%=accomVal%>"
										value='<s:property value="accomodationHotelType"/>' size="6" />&nbsp;</td>
									<td align="center" class="sortableTD">&nbsp;<!--<img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" align="center"
										onclick="setFieldId(<%=fieldVal%>,'TravelApplication_f9LodgingType.action');">
									--></td>
									<td class="sortableTD" align="center"><input type="hidden"
										name='accomodationRoomTypeId'
										id="paraFrm_accomodationRoomTypeId<%=accomVal%>"
										value='<s:property value="accomodationRoomTypeId"/>' /> <input
										type="text" name="accomodationRoomType"   readonly="true"
										id="paraFrm_accomodationRoomType<%=accomVal%>"
										value='<s:property value="accomodationRoomType"/>' size="6" />&nbsp;</td>
									<td align="center" class="sortableTD">&nbsp;<!--<img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" align="center"
										onclick="setFieldId(<%=fieldVal%>,'TravelApplication_f9RoomType.action');">
									--></td>
									<td class="sortableTD" align="center"><input type="text"
										name="accomodationCity"  readonly="true"
										id="paraFrm_accomodationCity<%=accomVal%>"
										value='<s:property value="accomodationCity"/>' size="10" />&nbsp;</td>
									<td class="sortableTD" align="center"><input type="text"
										name="accomodationPrefLocation"  readonly="true"
										id="paraFrm_accomodationPrefLocation<%=accomVal%>"
										value='<s:property value="accomodationPrefLocation"/>'
										size="10" />&nbsp;</td>
									<td class="sortableTD" align="center" colspan="2"><input
										type="text" name="accomodationFromDate"  readonly="true"
										id="paraFrm_accomodationFromDate<%=accomVal%>"
										value='<s:property value="accomodationFromDate"/>' size="7" />&nbsp;</td>
									<td class="sortableTD" align="center"><input type="text"
										name="accomodationFromTime"  readonly="true"
										id="paraFrm_accomodationFromTime<%=accomVal%>"
										value='<s:property value="accomodationFromTime"/>' size="5" />&nbsp;</td>
									<td class="sortableTD" align="center" colspan="2"><input
										type="text" name="accomodationToDate"  readonly="true"
										id="paraFrm_accomodationToDate<%=accomVal%>"
										value='<s:property value="accomodationToDate"/>' size="7" />&nbsp;</td>
									<td class="sortableTD" align="center"><input type="text"
										name="accomodationToTime"  readonly="true"
										id="paraFrm_accomodationToTime<%=accomVal%>"
										value='<s:property value="accomodationToTime"/>' size="5" />&nbsp;</td>
									<td align="center" class="sortableTD"><s:if
										test="%{trvlApp.appRejFlag}">

									</s:if> <s:else>

										<img src="../pages/common/css/icons/delete.gif"
											onclick="deleteCurrentRow(this);">
									</s:else></td>
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
			<td id="conveyanceBlock"
				style='display: <%= localConvDisplay .       equals( "YES") ?       "block" :       "none" %>;'>
			<table width="100%" class="formbg" border="0">
				<tr>
					<td width="40%">Local Conveyance Details</td>
					<td width="40%"><input type="radio" name="localConvRadio"
						value="S" onclick="hideShowOnRadioClick('localConv','S');" <%=localConvChk.equals("S")?"checked":"" %>>Self
					Managed <input type="radio" name="localConvRadio" value="C" onclick="hideShowOnRadioClick('localConv','C');"
						<%=localConvChk.equals("C")?"checked":"" %>>Company
					Managed</td>
					<td align="right" width="20%"><s:if test="appRejFlag">

					</s:if> <s:else>
						<input type="button" value="Add" Class="token" theme="simple"
							onclick="addLocalConveyanceDetials();" />
					</s:else></td>
				</tr>

				<tr id="localConv" >
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
						<s:if test='%{saveFlag!="save"}'>
							<tr>
								<td align="center" class="sortableTD"><input type="text"
									name="localConveyanceCity"
									id="paraFrm_localConveyanceCity<%=fieldVal%>" theme="simple"
									size="10" /></td>
								<td align="center" class="sortableTD"><input type="text"
									name="localConveyanceTravelDetail"
									id="paraFrm_localConveyanceTravelDetail<%=fieldVal%>"
									theme="simple" size="10" /></td>
								<td align="center" class="sortableTD"><input type="text"
									name="localConveyanceTravelMedium"
									id="paraFrm_localConveyanceTravelMedium<%=fieldVal%>"
									theme="simple" size="10" /></td>
								<td align="center" class="sortableTD"><input type="text"
									name="localConveyanceFromDate"
									id="paraFrm_localConveyanceFromDate<%=fieldVal%>"
									theme="simple" size="7"
									onkeypress="return numbersWithHiphen();" /></td>
								<td align="center" class="sortableTD"><s:a
									href="javascript:NewCal('paraFrm_localConveyanceFromDate0','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" align="center">
								</s:a></td>
								<td align="center" class="sortableTD"><input type="text"
									name="localConveyanceFromTime"
									id="paraFrm_localConveyanceFromTime<%=fieldVal%>"
									theme="simple" size="5" /></td>
								<td align="center" class="sortableTD"><input type="text"
									name="localConveyanceToDate"
									id="paraFrm_localConveyanceToDate<%=fieldVal%>" theme="simple"
									size="7" onkeypress="return numbersWithHiphen();" /></td>
								<td align="center" class="sortableTD"><s:a
									href="javascript:NewCal('paraFrm_localConveyanceToDate0','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" align="center">
								</s:a></td>
								<td align="center" class="sortableTD"><input type="text"
									name="localConveyanceToTime"
									id="paraFrm_localConveyanceToTime<%=fieldVal%>" theme="simple"
									size="5"
									onkeypress="checkDateForAccomodationLocalConvBlock('paraFrm_localConveyanceFromDate0','paraFrm_localConveyanceToDate0','From date','To date');" /></td>
								<td align="center" class="sortableTD"><s:if
									test="%{trvlApp.appRejFlag}">

								</s:if> <s:else>
									<img src="../pages/common/css/icons/delete.gif"
										onclick="deleteCurrentRow(this);">
								</s:else></td>
							</tr>
						</s:if>
						<s:if test='%{localConveyanceFlag=="YES"}'>
							<s:iterator value="localConveyanceList">
								<tr>
									<td class="sortableTD" align="center"><input type="hidden"
										name='localConveyanceCode'
										id="paraFrm_localConveyanceCode<%=localConveyanceVal%>"
										value='<s:property value="localConveyanceCode"/>' /> <input
										type="text" name="localConveyanceCity"
										id="paraFrm_localConveyanceCity<%=localConveyanceVal%>"
										value='<s:property value="localConveyanceCity"/>' size="10" />&nbsp;</td>
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
									<td class="sortableTD" align="center" colspan="2"><input
										type="text" name="localConveyanceFromDate"
										id="paraFrm_localConveyanceFromDate<%=localConveyanceVal%>"
										value='<s:property value="localConveyanceFromDate"/>' size="7" />&nbsp;</td>
									<td class="sortableTD" align="center"><input type="text"
										name="localConveyanceFromTime"
										id="paraFrm_localConveyanceFromTime<%=localConveyanceVal%>"
										value='<s:property value="localConveyanceFromTime"/>' size="5" />&nbsp;</td>
									<td class="sortableTD" align="center" colspan="2"><input
										type="text" name="localConveyanceToDate"
										id="paraFrm_localConveyanceToDate<%=localConveyanceVal%>"
										value='<s:property value="localConveyanceToDate"/>' size="7" />&nbsp;</td>
									<td class="sortableTD" align="center"><input type="text"
										name="localConveyanceToTime"
										id="paraFrm_localConveyanceToTime<%=localConveyanceVal%>"
										value='<s:property value="localConveyanceToTime"/>' size="5" />&nbsp;</td>
									<td align="center" class="sortableTD"><s:if
										test="%{trvlApp.appRejFlag}">

									</s:if> <s:else>

										<img src="../pages/common/css/icons/delete.gif"
											onclick="deleteCurrentRow(this);">
									</s:else></td>
								</tr>
							</s:iterator>
						</s:if>
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
			<td class="formbg">
			<table width="100%">
				<tr>
					<td width="20%"><label class="set" name="applicantComments"
						id="applicantComments" ondblclick="callShowDiv(this);"><%=label.get("applicantComments")%></label>
					</td>
					<td><!-- <textarea rows="4" cols="60" name="applComm" id="paraFrm_applComm" onkeyup="callLength('applComm','descCnt','300');"><s:property value="applComm" /></textarea> -->
					<s:if test="%{trvlApp.appRejFlag}">
						<s:property	value="applComm" />
					</s:if> <s:else>
					<textarea rows="4" cols="60" name="applComm" id="paraFrm_applComm"
						onkeyup="callLength('applComm','descCnt','500');"><s:property	value="applComm" /></textarea>
						
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
		<!-------------------- BUTTON PANEL --------------------------------------->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="35%" colspan="2">
					
				<input type="button"
						class="token" value="     Close " onclick="callClose();" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<s:hidden name="jourId" />
	<s:hidden name="level" />
	<s:hidden name="accomodationCityId" />
	<s:hidden name="checkApproveRejectStatus"></s:hidden>
	<s:hidden name="policyViolationMsg" /> 

	<s:hidden name="policyViolated"></s:hidden>
	<div id="policyDiv"
		style='position: absolute; z-index: 3; width: 535px; height: 270px; display: none; border: 2px solid; top: 100px; left: 100px; padding: 10px;'
		class="formbg">
	<table width="100%" border="0" cellpadding="2" cellspacing="2"
		class="formbg">
		<tr>
			<td colspan="3"><b><font color="red" size="2">Policy
			violated by following:</font></b></td>
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
			<td>Violation Reason<font color="red">*</font>:</td>
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
</s:form>
<script>

   function callClose()
 {
 
 	var conf=confirm("Are you sure !\n You want to Close this window ?");
  
  		 		if(conf)
			 		{
			 				window.close();
			 				return true;
			 			
			 		}
			 		else
			 		{
			 		return false;
			 		}
  
 }
 


</script>
