<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/pages/TravelManagement/TravelProcess/tmsAjax.js"></script>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="TravelQuickBooking" validate="true" id="paraFrm"
	theme="simple">
	<div id='div_Id'
		style='position: absolute; z-index: 3; width: 450px; height: 195px; display: none; border: 2px solid; top: 200px; left: 200px; padding: 10px;'
		class="formbg">
	<table width="100%">
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td width="93%" align="center" class="formth" style="cursor: move"
						onmouseout="Drag.end();"
						onmouseover="Drag.init(document.getElementById('div_Id'), null, 0, 350, 0, 700);">
					<b><label id="moduleName" style="cursor: move" /></b></td>
					<td width="7%" align="center" border="1" class="formth"
						style="font-family: Arial; cursor: pointer" onclick="hide_Div();">
					<b>X</b></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td width="23%" nowrap="nowrap">To mail Id:</td>
					<td><s:textfield name="toMailId" size="70" id="toId"
						readonly="true" /></td>
				</tr>

				<tr>
					<td width="23%" nowrap="nowrap">CC:</td>
					<td><s:textarea theme="simple" cols="70" rows="3"
						name="ccMailId" id="ccMailId" /></td>
				</tr>

				<tr>
					<td colspan="4" width="100%"><b> Note: </b></td>
				</tr>

				<tr>
					<td colspan="4" width="100%">1.In CC Field email Ids should be
					(;) seperated.</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td width="50%" align="right"><input type="button"
						id="ctrlShow" value="Send Mail" class="token"
						onclick="sendMail();" /></td>
					<td><input id="ctrlShow" type="button" value="Close"
						class="token" onclick="hide_Div();"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</div>





	<input type="hidden" name="fieldName" id="paraFrm_fieldName">
	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="98%" align="left"><strong class="formhead">
					Booking Detail</strong></td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- button starts -->
		<%
			int srCounter = 0;
			int fieldVal = 0;
			int srCounterJourney = 0;
			int srCounterAccomodation = 0;
			int accomVal = 0;
			int srCounterLocalConveyance = 0;
			int localConveyanceVal = 0;
		%>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%" align="left">
					<s:if test="showFlag">
 			
 						<s:submit value="  Save" action="TravelQuickBooking_saveBooking"
								id="save" onclick="return saveValidate();" theme="simple"
								cssClass="save" />

							<s:submit value="  Finalize"
								action="TravelQuickBooking_finalalize" theme="simple"
								cssClass="save" onclick="return chkFinalize();"/>

							<input type="button" class="token" theme="simple"
								onclick="return callDiv();" value=" Send Mail" />

							<!-- <s:submit value="  Send Mail"
								action="TravelQuickBooking_sendMail" theme="simple"
								cssClass="token" />
						 -->
						 
						 	<input type="button" name="reportBtn" value="TRF Report" class="token" onclick="return reportFun();"
								/>
								
								<s:if test='%{travelQuickBook.revokeFlag}'></s:if>
								<s:else>
								
									<s:submit value="  Revoke"
								action="TravelQuickBooking_revoke" theme="simple"
								cssClass="save" onclick="return checkRevoke();" />
								</s:else>
							 
							<s:submit name="backButton" value="Back" cssClass="back"
								onclick="return backFun();" >
							</s:submit>

					 

					</s:if> <s:elseif test="bookingPageFlag">

						<s:submit name="backButton" value="Back" cssClass="back"
							onclick="return backFun()" />

					</s:elseif> <s:else>
					<s:if test='%{travelQuickBook.revokeFlag}'><s:submit value="  Save" action="TravelQuickBooking_saveBooking"
								id="save" onclick="return saveValidate();" theme="simple"
								cssClass="save" /></s:if>
						<s:submit name="backButton" value="Back" cssClass="back"
							action="TravelQuickBooking_back" />
						
						<s:else>
							<input type="button" class="token" theme="simple"
								onclick="return callDiv();" value=" Send Mail" />
								
									<s:submit value="  Revoke"
								action="TravelQuickBooking_revoke" theme="simple"
								cssClass="save" />
								
									<input type="button" name="reportBtn" value="TRF Report" class="token" onclick="return reportFun();"
								/>
						</s:else>




					</s:else></td>
					<td width="22%" align="right"></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- button ends -->
		<!-- Employee Information table starts -->


		<s:if test='%{travelQuickBook.revokeFlag}'>
			<tr>
				<td>
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
				<td>
				<table class="formbg" width="100%">
					<tr>
						<td colspan="2"><font color="red">The following policy
						deviations have occured </font>: <s:property value="policyViolationMsg" /></td>
					</tr>
					<!--  
					<tr>
						<td td width="25%">Violation Approval Comments<font color="red">*</font>:</td>
						<td><textarea name="approverViolationComments"
							id="paraFrm_approverViolationComments" cols="70" rows="3"></textarea></td>
					</tr>
					-->
				</table>
				</td>
			</tr>
		</s:if>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" class="formbg">

				<tr>
					<td colspan="4" class="text_head"><strong
						class="forminnerhead">Employee Information</strong></td>
				</tr>

				<tr>
					<td colspan="1" width="10%"><label class="set"
						name="initiator.name" id="initiator.name"
						ondblclick="callShowDiv(this);"><%=label.get("initiator.name")%></label>:
					</td>
					<td colspan="3" width="90%"><s:property value="initiatorName" />
					<s:hidden name="initiatorCode"></s:hidden> 
					</td>
				</tr>

				<tr>
					<td colspan="1" width="10%"><label class="set"
						name="application.date" id="application.date"
						ondblclick="callShowDiv(this);"><%=label.get("application.date")%>
					</label> :</td>
					<td colspan="1" width="10%"><s:property
						value="applicationDate" /></td>
					<td colspan="1" width="10%"><label class="set"
						name="appStatus" id="appStatus" ondblclick="callShowDiv(this);"><%=label.get("appStatus")%>
					</label> :</td>
					<td colspan="1" width="70%"><s:property
						value="applicationStatus" /></td>
				</tr>

				<tr>
					<td colspan="1" width="10%"><label class="set"
						name="travel.StartDate" id="travel.StartDate"
						ondblclick="callShowDiv(this);"><%=label.get("travel.StartDate")%></label>
					<font color="red">*</font> :</td>
					<td colspan="1" width="10%" nowrap="nowrap"><s:textfield name="travelStartDate"
						id="paraFrm_travelStartDate" /> 	<a href="javascript:NewCal('paraFrm_travelStartDate','DDMMYYYY');"> <img
							src="../pages/images/Date.gif" class="iconImage" height="16"
							align="absmiddle" width="16"> </a> </td>
					<td colspan="1" width="10%"><label class="set"
						name="travel.EndDate" id="travel.EndDate"
						ondblclick="callShowDiv(this);"><%=label.get("travel.EndDate")%></label><font
						color="red">*</font>:</td>
					<td colspan="1" width="70%"><s:textfield name="travelEndDate"
						id="paraFrm_travelEndDate" /> 
						<a href="javascript:NewCal('paraFrm_travelEndDate','DDMMYYYY');"> <img
							src="../pages/images/Date.gif" class="iconImage" height="16"
							align="absmiddle" width="16"> </a> </td>
				</tr>
				<tr>
					<td colspan="1" width="10%"><label class="set"
						name="travel.requestName" id="travel.requestName"
						ondblclick="callShowDiv(this);"><%=label.get("travel.requestName")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="10%"><s:property
						value="travelRequestName" /></td>
					<td colspan="1" width="10%"><label class="set"
						name="travel.purpose" id="travel.purpose"
						ondblclick="callShowDiv(this);"><%=label.get("travel.purpose")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="70%"><s:property value="travelPurpose" />

					<s:hidden name="travelPurposeId"></s:hidden></td>
				</tr>
				<tr>
					<td colspan="1" width="10%"><label class="set"
						name="travel.project" id="travel.project"
						ondblclick="callShowDiv(this);"><%=label.get("travel.project")%></label>
					:</td>
					<td colspan="1" width="10%"><s:textfield name="travelProject"
						cssStyle="border:none" readonly="true"></s:textfield> <!--
					
					<s:property value="travelProject" id="travelProject"/>
					--></td>
					<td colspan="1" width="10%" nowrap="nowrap"><label class="set"
						name="travel.otherproject" id="travel.otherproject"
						ondblclick="callShowDiv(this);"><%=label.get("travel.otherproject")%></label>
					:</td>
					<td colspan="1" width="70%"><s:if test="buttonShowFlag">

						<s:textfield name="travelOtherProject" />
						<input type="button" value="Add To Project Master    " class="add"
							onclick="callValidation();">

					</s:if></td>
				</tr>
				<tr>
					<td colspan="1" width="10%"><label class="set"
						name="travel.customer" id="travel.customer"
						ondblclick="callShowDiv(this);"><%=label.get("travel.customer")%></label>
					:</td>
					<td colspan="1" width="10%"><s:textfield name="travelCustomer"
						readonly="true" cssStyle="border:none"></s:textfield></td>
					<td colspan="1" width="10%" nowrap="nowrap"><label class="set"
						name="travel.othercustomer" id="travel.othercustomer"
						ondblclick="callShowDiv(this);"><%=label.get("travel.othercustomer")%></label>
					:</td>
					<td colspan="1" width="70%"><s:if test="buttonShowFlag">
						<s:textfield name="travelOtherCustomer"
							value="%{travelOtherCustomer}" />
						<input type="button" value="Add To Customer Master" class="add"
							onclick="callValidationCustomer();">

					</s:if></td>
				</tr>
				<tr>
					<td colspan="1" width="10%"><label class="set"
						name="travel.type" id="travel.type"
						ondblclick="callShowDiv(this);"><%=label.get("travel.type")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="10%"><s:property value="travelType" /></td>
					<td colspan="1" width="10%">&nbsp;&nbsp;</td>
					<td colspan="1" width="70%">&nbsp;&nbsp;</td>
				</tr>

				<tr>
					<td colspan="1" width="10%"><label class="set"
						name="travel.comments" id="travel.comments"
						ondblclick="callShowDiv(this);"><%=label.get("travel.comments")%></label>
					<font color="red">*</font>:</td>
					<td colspan="3" width="90%"><s:if test="buttonShowFlag">

						<s:textarea theme="simple" cols="70" rows="3" name="adminComments"
							id="adminComments" />
						</textarea>
					</s:if> <s:else>
						<s:property value="adminComments" />
					</s:else></td>

				</tr>


				<tr>
					<td width="10%" colspan="1"></td>
					<td width="90%" colspan="3"><s:if test="empId != 0">
						<input type="button" name="ViewPolicy" value="View Policy"
							class="token"
							onclick="viewPolicy('<s:property value="hiddenGradeCode" />')"; > &nbsp;&nbsp;&nbsp;&nbsp;</s:if><input
						type="button" name="ViewReq" value="View Application"
						class="token"
						onclick="viewDetails('<s:property value="hiddenApplicationCode" />')"; >
					</td>
				</tr>


			</table>
			</td>
		</tr>


		<!-- Employee Information table ends -->

		<!-- Employee Information table starts -->
		<tr>
			<td>
			<table width="100%" align="center" class="formbg" theme="simple">
				<tr>
					<td><strong>Employee Information</strong></td>
				</tr>


				<tr>
					<td width="100%" colspan="6">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
							<td class="formth" colspan="1" width="5%"><label
								name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
							<td class="formth" colspan="1" width="25%"><label
								name="travel.employee.guest" id="travel.employee.guest"
								ondblclick="callShowDiv(this);"><%=label.get("travel.employee.guest")%></label></td>

							<td class="formth" colspan="1" width="10%"><label name="dob"
								id="dob" ondblclick="callShowDiv(this);"><%=label.get("dob")%></label></td>

							<td class="formth" colspan="1" width="10%"><label
								name="travel.employee.age" id="travel.employee.age"
								ondblclick="callShowDiv(this);"><%=label.get("travel.employee.age")%></label></td>
								
							<td class="formth" colspan="1" width="10%"><label
								name="grade" id="grade"
								ondblclick="callShowDiv(this);"><%=label.get("grade")%></label></td>
								
							<td class="formth" colspan="1" width="10%"><label
								name="designation" id="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label></td>
								
										
							<td class="formth" colspan="1" width="15%"><label
								name="travel.employee.contact" id="travel.employee.contact"
								ondblclick="callShowDiv(this);"><%=label.get("travel.employee.contact")%></label></td>
							<td class="formth" colspan="1" width="15%"><label
								name="travel.advance.amount" id="travel.advance.amount"
								ondblclick="callShowDiv(this);"><%=label.get("travel.advance.amount")%></label></td>

						</tr>
						<s:iterator value="travellerList">
							<tr>
								<td class="sortableTD" align="center" width="5%"><%=++srCounter%></td>
								<td class="sortableTD" align="left" width="25%"><s:property
									value="%{employeeNameFromList}" />&nbsp;</td>
								<td class="sortableTD" align="center" width="10%"><s:property
									value="%{dateOfBirth}" />&nbsp;</td>
								<td class="sortableTD" align="center" width="10%"><s:property
									value="%{employeeAgeFromList}" />&nbsp;</td>
									
								
									<td class="sortableTD" align="left" width="10%"><s:property
									value="%{employeeGrade}" />&nbsp;</td>
									
								
									<td class="sortableTD" align="left" width="10%"><s:property
									value="%{employeeDesignation}" />&nbsp;</td>		
									
								<td class="sortableTD" align="center" width="15%"><s:property
									value="%{employeeContactFromList}" />&nbsp;</td>
								<td class="sortableTD" align="center" width="15%" nowrap="nowrap">
									<s:property value="currencyEmployeeAdvance"/> 
									<s:property value="%{employeeAdvanceFromList}" />&nbsp;
								</td>
							</tr>
						</s:iterator>
					</table>
					</td>
				</tr>

				<!-- Employee Information table ends -->
			</table>
			</td>
		</tr>
		<!-- Journey Details table starts -->
		<tr>
			<td>
			<table width="100%" align="center" class="formbg" theme="simple">
				<td>Journey Details</td>
				<td></td>
				<td align="right"><s:if test="buttonShowFlag">
					<input type="button" value="Add" Class="token" theme="simple"
						onclick="addRowToJourneyBlock();" />
				</s:if></td>
				</tr>

				<!-- iterator for Journey Details   -->


				<tr>
					<td colspan="4">
					<table width="100%" id="journeyTable" class="sortable" border="0">
						<tr>
							<td class="formth"><label class="set" name="sno" id="sno1"
								ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
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
							</td>
							<td class="formth" colspan="2"><label class="set"
								name="agencyName1" id="agencyName1"
								ondblclick="callShowDiv(this);"><%=label.get("agencyName")%></label>
							<font color="red">*</font></td>
							<td class="formth" colspan="2"><label class="set"
								name="journeyMedium1" id="journeyMedium1"
								ondblclick="callShowDiv(this);"><%=label.get("journeyMedium")%></label>
							<font color="red">*</font></td>
							<td class="formth"><label class="set"
								name="bustrainflightno" id="bustrainflightno"
								ondblclick="callShowDiv(this);"><%=label.get("bustrainflightno")%></label>
							<font color="red">*</font></td>
							<td class="formth"><label class="set" name="ticket.number"
								id="ticket.number" ondblclick="callShowDiv(this);"><%=label.get("ticket.number")%></label>
							<font color="red">*</font></td>
							<td class="formth">
								<label class="set" name="bestFare" id="bestFare" ondblclick="callShowDiv(this);"><%=label.get("bestFare")%></label>
							<font color="red">*</font></td>
							<td class="formth"><label class="set" name="actualFare"
								id="actualFare" ondblclick="callShowDiv(this);"><%=label.get("actualFare")%></label>
							<font color="red">*</font></td>
							 
								<td class="formth"><label class="set" name="cancellation"
									id="cancellation" ondblclick="callShowDiv(this);"><%=label.get("cancellation")%></label>
								 </td>
						 
					 
							<td class="formth"><label class="set" name="travel.details"
								id="travel.details" ondblclick="callShowDiv(this);"><%=label.get("travel.details")%></label>
							</td>
							<td class="formth" colspan="2"><label class="set"
								name="travel.fileupload" id="travel.fileupload"
								ondblclick="callShowDiv(this);"><%=label.get("travel.fileupload")%></label>
							<font color="red">*</font></td>
							<s:if test="buttonShowFlag">
								<td class="formth">&nbsp;</td>
							</s:if>
							<s:else>
								<td class="formth">&nbsp;</td>
							</s:else>


						</tr>
						<!-- iterator for saved journey record starts-->

						<s:iterator value="journeyList">

							<tr>
								<td class="sortableTD" align="center"><%=++srCounterJourney%></td>
								<td class="sortableTD" align="center"><input type="text"
									id="paraFrm_journeyFromPlace<%=fieldVal%>"
									name="journeyFromPlace"
									value='<s:property value="journeyFromPlace"/>' size="10"
									onkeyup="setFieldId(event,<%=fieldVal%>,'TravelQuickBooking_f9City.action?fieldName=journeyFromPlace<%=fieldVal%>','paraFrm_journeyFromPlace<%=fieldVal%>');" /></td>
								<td class="sortableTD" align="center"><input type="text"
									id="paraFrm_journeyToPlace<%=fieldVal%>" name="journeyToPlace"
									value='<s:property value="journeyToPlace"/>' size="10"
									onkeyup="setFieldId(event,<%=fieldVal%>,'TravelQuickBooking_f9City.action?fieldName=journeyToPlace<%=fieldVal%>','paraFrm_journeyToPlace<%=fieldVal%>');" /></td>
								<td align="center" class="sortableTD"><input type="text"
									name='jourMode' id="paraFrm_jourMode<%=fieldVal%>"
									readonly="true" theme="simple" size="20"
									value='<s:property value="jourMode"/>' /> <input type="hidden"
									name='journeyModeId'
									value='<s:property value="journeyModeId"/>'
									id="paraFrm_journeyModeId<%=fieldVal%>" /></td>
								<td align="center" class="sortableTD"><img
									src="../pages/images/recruitment/search2.gif" class="iconImage"
									height="16" align="center" width="16"
									onclick="setFieldId(event,<%=fieldVal%>,'TravelQuickBooking_f9JourneyMode.action?fieldName=<%=fieldVal%>','paraFrm_jourMode<%=fieldVal%>');">
								</td>
								<td class="sortableTD" align="center"><input type="text"
									id="paraFrm_journeyDate<%=fieldVal%>" name="journeyDate"
									value='<s:property value="journeyDate"/>' size="7" /></td>
								<td align="center" class="sortableTD"><s:a
									href="javascript:NewCal('paraFrm_journeyDate0','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" align="center">
								</s:a></td>
								<td class="sortableTD" align="center"><input type="text"
									id="paraFrm_journeyTime<%=fieldVal%>" name="journeyTime"
									value='<s:property value="journeyTime"/>' size="5" /></td>
								<td class="sortableTD" align="center"><input type="hidden"
									id="paraFrm_journeyAgencyId<%=fieldVal%>"
									name="journeyAgencyId"
									value='<s:property value="journeyAgencyId"/>' /> <input
									type="text" readonly="true"
									id="paraFrm_journeyAgency<%=fieldVal%>" name="journeyAgency"
									value='<s:property value="journeyAgency"/>' size="10" /></td>
								<td align="center" class="sortableTD"><img
									src="../pages/images/recruitment/search2.gif" class="iconImage"
									height="16" align="center" width="16"
									onclick="callAgency(event,<%=fieldVal%>);"></td>
								<td class="sortableTD" align="center"><input type="hidden"
									id="paraFrm_journeyMediumId<%=fieldVal%>"
									name="journeyMediumId"
									value='<s:property value="journeyMediumId"/>' /> <input
									type="text" readonly="true"
									id="paraFrm_journeyMedium<%=fieldVal%>" name="journeyMedium"
									value='<s:property value="journeyMedium"/>' size="10" /></td>
								<td align="center" class="sortableTD"><img
									src="../pages/images/recruitment/search2.gif" class="iconImage"
									height="16" align="center" width="16"
									onclick="callCarrier(event,'<%=fieldVal%>'); " /></td>
								<td class="sortableTD" align="center"><input type="text"
									id="paraFrm_busTrainNo<%=fieldVal%>" name="busTrainNo"
									value='<s:property value="busTrainNo"/>' size="5" /></td>

								<td class="sortableTD" align="center"><input type="text"
									id="paraFrm_ticketNo<%=fieldVal%>" name="ticketNo"
									value='<s:property value="ticketNo"/>' size="5" /></td>

								<td class="sortableTD" align="center" nowrap="nowrap">
								<s:if test="travelQuickBook.defaultCurrencyFlag">
									<input type="text" size="3" style="border: none;cursor: pointer;" id="paraFrm_currencyCost<%=fieldVal%>" 
									  	name="currencyCost" value='<s:property value="currencyCost"/>' 									  	
									  	readonly="readonly"									  	
									  	/> 
								 </s:if>
								 <s:else>
								 <input type="text" size="3" style="border: none;cursor: pointer;" id="paraFrm_currencyCost<%=fieldVal%>" 
									  	name="currencyCost" value='<s:property value="currencyCost"/>' 
									  	title="Click here to change currency" 
									  	readonly="readonly"
									  	onclick="javascript:callDropdown('paraFrm_currencyCost<%=fieldVal%>', 200, 250, 'TravelQuickBooking_f9JourneyCurrency.action?currencyName=journey&currencyID=<%=fieldVal%>',event,'false');"
									  	/> 
								 </s:else>
									<input type="text"
									id="paraFrm_cost<%=fieldVal%>" name="cost"
									value='<s:property value="cost"/>' size="5"
									onkeypress="return numbersOnly();" /> 
									
								</td>
								
								<td class="sortableTD" align="center" nowrap="nowrap">
								<s:if test="travelQuickBook.defaultCurrencyFlag">									
									 <input type="text" size="3" style="border: none;cursor: pointer;" id="paraFrm_currencyActualCost<%=fieldVal%>" 
									  	name="currencyActualCost" value='<s:property value="currencyActualCost"/>' 									  	 
									  	readonly="readonly"									  	 
									 /> 
									
								</s:if>
								<s:else>
								 <input type="text" size="3" style="border: none;cursor: pointer;" id="paraFrm_currencyActualCost<%=fieldVal%>" 
									  	name="currencyActualCost" value='<s:property value="currencyActualCost"/>' 
									  	title="Click here to change currency" 
									  	readonly="readonly"
									  	onclick="javascript:callDropdown('paraFrm_currencyActualCost<%=fieldVal%>', 200, 250, 'TravelQuickBooking_f9JourneyCurrency.action?currencyName=journey&currencyID=<%=fieldVal%>',event,'false');" 
									 /> 
									
								</s:else>
									
									
									<input type="text"
									id="paraFrm_actualCost<%=fieldVal%>" name="actualCost"
									value='<s:property value="actualCost"/>' size="5"
									onkeypress="return numbersWithDot();" />
								</td>
								 
								<td class="sortableTD" align="center" nowrap="nowrap">
									<s:if test="travelQuickBook.defaultCurrencyFlag">
									<input type="text" size="3" style="border: none;cursor: pointer;" id="paraFrm_currencyCancelAmountJourney<%=fieldVal%>" 
								  	name="currencyCancelAmountJourney" value='<s:property value="currencyCancelAmountJourney"/>' 								  	 
								  	readonly="readonly"								  	
								  	/> 
									</s:if>
									<s:else>
										<input type="text" size="3" style="border: none;cursor: pointer;" id="paraFrm_currencyCancelAmountJourney<%=fieldVal%>" 
								  	name="currencyCancelAmountJourney" value='<s:property value="currencyCancelAmountJourney"/>' 
								  	title="Click here to change currency" 
								  	readonly="readonly"
								  	onclick="javascript:callDropdown('paraFrm_currencyCancelAmountJourney<%=fieldVal%>', 200, 250, 'TravelQuickBooking_f9JourneyCurrency.action?currencyName=journey&currencyID=<%=fieldVal%>',event,'false');" 
								  	/> 
									</s:else>
									
									
									<input type="text"
									id="paraFrm_cancelAmountJourney<%=fieldVal%>"
									name="cancelAmountJourney"
									value='<s:property value="cancelAmountJourney"/>' size="8"
									onkeypress="return numbersWithDot();" />
								</td>
						 		 
						 		 

								<td class="sortableTD" align="center"><input type="text"
									id="paraFrm_journeydetails<%=fieldVal%>" name="journeydetails"
									value='<s:property value="journeydetails"/>' size="10" /></td>
								<td class="sortableTD" align="center"><input type="text"
									name="journeyFileUpload" size="20" readonly="true"
									id="paraFrm_journeyFileUpload<%=fieldVal%>"
									value='<s:property value="journeyFileUpload"/>' size="20" /></td>
								<s:if test="%{travelQuickBook.buttonShowFlag}">
									<td class="sortableTD" align="center" nowrap="nowrap"><input
										type="button" name="uploadLoc" value="Upload" class="token"
										onclick="uploadTicketFile('paraFrm_journeyFileUpload<%=fieldVal%>');" />
									</td>
								</s:if>
								<td class="sortableTD" align="center" nowrap="nowrap"><input
									type="button" class="token" value="Show"
									onclick="return showRecord('paraFrm_journeyFileUpload<%=fieldVal%>');"/ >

								</td>
								<td align="center" class="sortableTD"><img
									src="../pages/common/css/icons/delete.gif"
									onclick="deleteCurrentRow(this);" /></td>
							</tr>
							<%
							fieldVal++;
							%>
						</s:iterator>
						<!-- iterator for saved journey record starts-->
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Journey Details table ends -->
		<!-- Lodging Details table starts here -->

		<s:if test="lodgeDtlFlag">

			<tr>
				<td width="100%" id="lodgingBlock" style='display: block'>

				<table width="100%" class="formbg">
					<tr>
						<td colspan="1">Lodging Details</td>
						<td></td>
						<td align="right"><s:if test="buttonShowFlag">
							<input type="button" value="Add" Class="token" theme="simple"
								onclick="addRowToAccomodationBlock();" />
						</s:if></td>
					</tr>
					<tr>
						<td width="100%" colspan="3">
						<table width="100%" border="0" id="accomodationTable">
							<tr>
								<td class="formth"><label class="set" name="sno" id="sno2"
									ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
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
								<td class="formth" colspan="2"><label class="set"
									name="hotelname1" id="hotelname1"
									ondblclick="callShowDiv(this);"><%=label.get("hotelname")%></label>
								<font color="red">*</font></td>
								<td class="formth"><label class="set" name="noOfDays"
									id="noOfDays" ondblclick="callShowDiv(this);"><%=label.get("noOfDays")%></label>
								<font color="red">*</font></td>
								<td class="formth"><label class="set" name="travel.recRate"
									id="travel.recRate" ondblclick="callShowDiv(this);"><%=label.get("travel.recRate")%></label>
								<font color="red">*</font></td>
								<td class="formth"><label class="set" name="corporaterate"
									id="corporaterate" ondblclick="callShowDiv(this);"><%=label.get("corporaterate")%></label>
								<font color="red">*</font></td>
								 
							 
									<td class="formth"><label class="set" name="cancellation"
										id="cancellation1" ondblclick="callShowDiv(this);"><%=label.get("cancellation")%></label>
								 </td>
							 
							 
								<td class="formth"><label class="set" name="travel.details"
									id="travel.details1" ondblclick="callShowDiv(this);"><%=label.get("travel.details")%></label>
								</td>
								<td class="formth" colspan="2"><label class="set"
									name="travel.fileupload" id="travel.fileupload1"
									ondblclick="callShowDiv(this);"><%=label.get("travel.fileupload")%></label>
								<font color="red">*</font></td>
								<s:if test="buttonShowFlag">
									<td class="formth" align="center">&nbsp;</td>
								</s:if>
							</tr>

							<s:iterator value="accomodationList">
								<tr>
									<td class="sortableTD" align="center"><%=++srCounterAccomodation%></td>
									<td class="sortableTD" align="center"><input type="hidden"
										name='accomodationHotelTypeId'
										id="paraFrm_accomodationHotelTypeId<%=accomVal%>"
										value='<s:property value="accomodationHotelTypeId"/>' /> <input
										type="text" name='accomodationHotelType' readonly="true"
										id="paraFrm_accomodationHotelType<%=accomVal%>"
										value='<s:property value="accomodationHotelType"/>' size="10" />

									</td>
									<td align="center" class="sortableTD"><img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" align="center"
										onclick="setFieldId(event,<%=accomVal%>,'TravelQuickBooking_f9LodgingType.action?fieldName=<%=accomVal%>','paraFrm_accomodationHotelType<%=accomVal%>');" />
									</td>
									<td class="sortableTD" align="center"><input type="hidden"
										name='accomodationRoomTypeId'
										id="paraFrm_accomodationRoomTypeId<%=accomVal%>"
										value='<s:property value="accomodationRoomTypeId"/>' /> <input
										type="text" name="accomodationRoomType" readonly="true"
										id="paraFrm_accomodationRoomType<%=accomVal%>"
										value='<s:property value="accomodationRoomType"/>' size="10" /></td>
									<td align="center" class="sortableTD"><img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="16" align="center" width="16"
										onclick="callRoomType(event,<%=accomVal%>);" /></td>
									<td class="sortableTD" align="center"><input type="text"
										name="accomodationCity"
										id="paraFrm_accomodationCity<%=accomVal%>"
										value='<s:property value="accomodationCity"/>' size="10"
										onkeyup="setFieldId(event,<%=fieldVal%>,'TravelQuickBooking_f9City.action?fieldName=accomodationCity<%=accomVal%>','paraFrm_accomodationCity<%=accomVal%>');" /></td>
									<td class="sortableTD" align="center"><input type="text"
										name="accomodationPrefLocation"
										id="paraFrm_accomodationPrefLocation<%=accomVal%>"
										value='<s:property value="accomodationPrefLocation"/>'
										size="10" /></td>
									<td class="sortableTD" align="center"><input type="text"
										name="accomodationFromDate"
										id="paraFrm_accomodationFromDate<%=accomVal%>"
										value='<s:property value="accomodationFromDate"/>' size="7" /></td>
									<td align="center" class="sortableTD"><s:a
										href="javascript:NewCal('paraFrm_accomodationFromDate0','DDMMYYYY');">
										<img src="../pages/images/recruitment/Date.gif"
											class="iconImage" align="center">
									</s:a></td>
									<td class="sortableTD" align="center"><input type="text"
										name="accomodationFromTime"
										id="paraFrm_accomodationFromTime<%=accomVal%>"
										value='<s:property value="accomodationFromTime"/>' size="5" /></td>
									<td class="sortableTD" align="center"><input type="text"
										name="accomodationToDate"
										id="paraFrm_accomodationToDate<%=accomVal%>"
										value='<s:property value="accomodationToDate"/>' size="7" /></td>
									<td align="center" class="sortableTD"><s:a
										href="javascript:NewCal('paraFrm_accomodationToDate0','DDMMYYYY');">
										<img src="../pages/images/recruitment/Date.gif"
											class="iconImage" align="center">
									</s:a></td>
									<td class="sortableTD" align="center"><input type="text"
										name="accomodationToTime"
										id="paraFrm_accomodationToTime<%=accomVal%>"
										value='<s:property value="accomodationToTime"/>' size="5" /></td>
									<td class="sortableTD" align="center"><input type="hidden"
										id="paraFrm_accomodationHotelNameId<%=accomVal%>"
										name="accomodationHotelNameId"
										value='<s:property value="accomodationHotelNameId"/>' /> <input
										type="text" readonly="true" name="accomodationHotelName"
										id="paraFrm_accomodationHotelName<%=accomVal%>"
										value='<s:property value="accomodationHotelName"/>' size="10" /></td>
									<td align="center" class="sortableTD"><img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="16" align="center" width="16"
										onclick="callCityhotel(event,<%=accomVal%>);" /></td>

									<td class="sortableTD" align="center"><input type="text"
										name="noOfDays" id="paraFrm_noOfDays<%=accomVal%>"
										value='<s:property value="noOfDays"/>' size="5"
										onkeypress="return numbersOnly();" />
									</td>
									
									<td class="sortableTD" align="center" nowrap="nowrap">
									<s:if test="travelQuickBook.defaultCurrencyFlag">
										<input type="text" size="3" style="border: none;cursor: pointer;" id="paraFrm_currencyBookingAmount<%=accomVal%>" 
									  	name="currencyBookingAmount" value='<s:property value="currencyBookingAmount"/>' 									  	
									  	readonly="readonly"									  	
									  	/> 
									</s:if>
									<s:else>
										<input type="text" size="3" style="border: none;cursor: pointer;" id="paraFrm_currencyBookingAmount<%=accomVal%>" 
									  	name="currencyBookingAmount" value='<s:property value="currencyBookingAmount"/>' 
									  	title="Click here to change currency" 
									  	readonly="readonly"
									  	onclick="javascript:callDropdown('paraFrm_currencyBookingAmount<%=accomVal%>', 200, 250, 'TravelQuickBooking_f9LodgingCurrency.action?currencyName=lodging&currencyID=<%=accomVal%>',event,'false');"
									  	/> 
									</s:else>
										
										
										<input type="text"
										name="bookingAmount" id="paraFrm_bookingAmount<%=accomVal%>"
										value='<s:property value="bookingAmount"/>' size="5"
										onkeypress="return numbersOnly();" />
									</td>
									
									<td class="sortableTD" align="center" nowrap="nowrap">
									<s:if test="travelQuickBook.defaultCurrencyFlag">
										<input type="text" size="3" style="border: none;cursor: pointer;" id="paraFrm_currencyCorporateRate<%=accomVal%>" 
									  	name="currencyCorporateRate" value='<s:property value="currencyCorporateRate"/>' 									  	
									  	readonly="readonly"									  	
									  	/> 
									</s:if>
									<s:else>
										<input type="text" size="3" style="border: none;cursor: pointer;" id="paraFrm_currencyCorporateRate<%=accomVal%>" 
									  	name="currencyCorporateRate" value='<s:property value="currencyCorporateRate"/>' 
									  	title="Click here to change currency" 
									  	readonly="readonly"
									  	onclick="javascript:callDropdown('paraFrm_currencyCorporateRate<%=accomVal%>', 200, 250, 'TravelQuickBooking_f9LodgingCurrency.action?currencyName=lodging&currencyID=<%=accomVal%>',event,'false');"
									  	/> 
									</s:else>
										
										
										<input type="text" name="corporateRate" id="paraFrm_corporateRate<%=accomVal%>"
										value='<s:property value="corporateRate"/>' size="5" onkeypress="return numbersOnly();" />
									</td>
									 
									<td class="sortableTD" align="center" nowrap="nowrap">
									<s:if test="travelQuickBook.defaultCurrencyFlag">
										<input type="text" size="3" style="border: none;cursor: pointer;" id="paraFrm_currencyCancelAmountAccom<%=accomVal%>" 
									  	name="currencyCancelAmountAccom" value='<s:property value="currencyCancelAmountAccom"/>' 									  	
									  	readonly="readonly"									  	
									  	/> 
									</s:if>
									<s:else>
										<input type="text" size="3" style="border: none;cursor: pointer;" id="paraFrm_currencyCancelAmountAccom<%=accomVal%>" 
									  	name="currencyCancelAmountAccom" value='<s:property value="currencyCancelAmountAccom"/>' 
									  	title="Click here to change currency" 
									  	readonly="readonly"
									  	onclick="javascript:callDropdown('paraFrm_currencyCancelAmountAccom<%=accomVal%>', 200, 250, 'TravelQuickBooking_f9LodgingCurrency.action?currencyName=lodging&currencyID=<%=accomVal%>',event,'false');"
									  	/> 
									</s:else>
										
										
										<input type="text"
											id="paraFrm_cancelAmountAccom<%=accomVal%>"
											name="cancelAmountAccom"
											value='<s:property value="cancelAmountAccom"/>' size="8"
											onkeypress="return numbersWithDot();" />
									</td>
											
									<td class="sortableTD" align="center"><input type="text"
										name="bookingDetails" id="paraFrm_bookingDetails<%=accomVal%>"
										value='<s:property value="bookingDetails"/>' size="10" /></td>

									<td class="sortableTD" align="center"><input type="text"
										name="bookingFileUpload" size="20" readonly="true"
										id="paraFrm_bookingFileUpload<%=accomVal%>"
										value='<s:property value="bookingFileUpload" />' size="20" /></td>

									<s:if test="%{travelQuickBook.buttonShowFlag}">
										<td class="sortableTD" align="center" nowrap="nowrap"><input
											type="button" name="uploadLoc" value="Upload" class="token"
											onclick="uploadTicketFile('paraFrm_bookingFileUpload<%=accomVal%>');" />
										</td>
									</s:if>

									<td class="sortableTD" align="center" nowrap="nowrap"><input
										type="button" name="show" value="Show" class="token"
										onclick="showRecord('paraFrm_bookingFileUpload<%=accomVal%>');" />

									</td>
									<td align="center" class="sortableTD"><img
										src="../pages/common/css/icons/delete.gif"
										onclick="deleteCurrentRow(this);" /></td>

								</tr>
								<%
								accomVal++;
								%>
							</s:iterator>
						</table>
						</div>
						</td>
					</tr>
				</table>
				</div>
				</td>
			</tr>

		</s:if>
		<!-- Lodging Details table ends here -->
		<!-- Local Conveyance Details table starts here -->

		<s:if test="localConvFlag">

			<tr>
				<td id="conveyanceBlock" style='display: block'>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td colspan="1">Local Conveyance Details</td>
						<td></td>
						<td align="right"><s:if test="buttonShowFlag">
							<input type="button" value="Add" Class="token" theme="simple"
								onclick="addRowToLocalConveyanceBlock();" />
						</s:if></td>
					</tr>

					<tr>
						<td width="100%" colspan="3">
						<table width="100%" border="0" id="localConveyanceTable">
							<tr>
								<td class="formth"><label class="set" name="sno" id="sno3"
									ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
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
								<td class="formth"><label class="set"
									name="travel.tariffcost" id="travel.tariffcost"
									ondblclick="callShowDiv(this);"><%=label.get("travel.tariffcost")%></label>
								<font color="red">*</font></td>
								<td class="formth"><label class="set" name="cancellation"
										id="cancellation2" ondblclick="callShowDiv(this);"><%=label.get("cancellation")%></label>
									 </td>
								<td class="formth"><label class="set" name="travel.details"
									id="travel.details2" ondblclick="callShowDiv(this);"><%=label.get("travel.details")%></label>
								</td>
								<td class="formth" colspan="2"><label class="set"
									name="travel.fileupload" id="travel.fileupload2"
									ondblclick="callShowDiv(this);"><%=label.get("travel.fileupload")%></label>
								</td>
								<td class="formth">&nbsp;</td>
							</tr>
							<s:iterator value="localConveyanceList">
								<tr>
									<td class="sortableTD" align="center"><%=++srCounterLocalConveyance%></td>
									<td class="sortableTD" align="center"><input type="hidden"
										name='localConveyanceCode'
										id="paraFrm_localConveyanceCode<%=localConveyanceVal%>"
										value='<s:property value="localConveyanceCode"/>' /> <input
										type="text" name="localConveyanceCity"
										id="paraFrm_localConveyanceCity<%=localConveyanceVal%>"
										value='<s:property value="localConveyanceCity"/>' size="10"
										onkeyup="setFieldId(event,<%=localConveyanceVal%>,'TravelQuickBooking_f9City.action?fieldName=localConveyanceCity<%=localConveyanceVal%>','paraFrm_localConveyanceCity<%=localConveyanceVal%>');" /></td>
									<td class="sortableTD" align="center"><input type="text"
										name="localConveyanceTravelDetail"
										id="paraFrm_localConveyanceTravelDetail<%=localConveyanceVal%>"
										value='<s:property value="localConveyanceTravelDetail"/>'
										size="10" /></td>
									<td class="sortableTD" align="center"><input type="text"
										name="localConveyanceTravelMedium"
										id="paraFrm_localConveyanceTravelMedium<%=localConveyanceVal%>"
										value='<s:property value="localConveyanceTravelMedium"/>'
										size="10" /></td>
									<td class="sortableTD" align="center"><input type="text"
										name="localConveyanceFromDate"
										id="paraFrm_localConveyanceFromDate<%=localConveyanceVal%>"
										value='<s:property value="localConveyanceFromDate"/>' size="7" /></td>
									<td align="center" class="sortableTD"><s:a
										href="javascript:NewCal('paraFrm_localConveyanceFromDate0','DDMMYYYY');">
										<img src="../pages/images/recruitment/Date.gif"
											class="iconImage" align="center">
									</s:a></td>
									<td class="sortableTD" align="center"><input type="text"
										name="localConveyanceFromTime"
										id="paraFrm_localConveyanceFromTime<%=localConveyanceVal%>"
										value='<s:property value="localConveyanceFromTime"/>' size="5" /></td>
									<td class="sortableTD" align="center"><input type="text"
										name="localConveyanceToDate"
										id="paraFrm_localConveyanceToDate<%=localConveyanceVal%>"
										value='<s:property value="localConveyanceToDate" />' size="7" /></td>
									<td align="center" class="sortableTD"><s:a
										href="javascript:NewCal('paraFrm_localConveyanceToDate0','DDMMYYYY');">
										<img src="../pages/images/recruitment/Date.gif"
											class="iconImage" align="center">
									</s:a></td>
									<td class="sortableTD" align="center"><input type="text"
										name="localConveyanceToTime"
										id="paraFrm_localConveyanceToTime<%=localConveyanceVal%>"
										value='<s:property value="localConveyanceToTime"/>' size="5" />
									</td>
									
									<td class="sortableTD" align="center" nowrap="nowrap">
									<s:if test="travelQuickBook.defaultCurrencyFlag">
										<input type="text" size="3" style="border: none;cursor: pointer;" id="paraFrm_currencyLocalConveyanceTariffCost<%=localConveyanceVal%>" 
									  	name="currencyLocalConveyanceTariffCost" value='<s:property value="currencyLocalConveyanceTariffCost"/>' 									  	 
									  	readonly="readonly"									  	
									  	/> 
									</s:if>
									<s:else>
										<input type="text" size="3" style="border: none;cursor: pointer;" id="paraFrm_currencyLocalConveyanceTariffCost<%=localConveyanceVal%>" 
									  	name="currencyLocalConveyanceTariffCost" value='<s:property value="currencyLocalConveyanceTariffCost"/>' 
									  	title="Click here to change currency" 
									  	readonly="readonly"
									  	onclick="javascript:callDropdown('paraFrm_currencyLocalConveyanceTariffCost<%=localConveyanceVal%>', 200, 250, 'TravelQuickBooking_f9CoveyanceCurrency.action?currencyName=conveyance&currencyID=<%=localConveyanceVal%>',event,'false');"
									  	/> 
									</s:else>
										
										
										<input type="text" name="localConveyanceTariffCost"
										id="paraFrm_localConveyanceTariffCost<%=localConveyanceVal%>"
										value='<s:property value="localConveyanceTariffCost"/>'
										size="5" />
									</td>

									<td class="sortableTD" align="center" nowrap="nowrap">
										<s:if test="travelQuickBook.defaultCurrencyFlag">
										<input type="text" size="3" style="border: none;cursor: pointer;" id="paraFrm_currencyCancelAmountLocalConv<%=localConveyanceVal%>" 
									  	name="currencyCancelAmountLocalConv" value='<s:property value="currencyCancelAmountLocalConv"/>' 									  	 
									  	readonly="readonly"									  	
									  	/> 
									</s:if>
									<s:else>
										<input type="text" size="3" style="border: none;cursor: pointer;" id="paraFrm_currencyCancelAmountLocalConv<%=localConveyanceVal%>" 
									  	name="currencyCancelAmountLocalConv" value='<s:property value="currencyCancelAmountLocalConv"/>' 
									  	title="Click here to change currency" 
									  	readonly="readonly"
									  	onclick="javascript:callDropdown('paraFrm_currencyCancelAmountLocalConv<%=localConveyanceVal%>', 200, 250, 'TravelQuickBooking_f9CoveyanceCurrency.action?currencyName=conveyance&currencyID=<%=localConveyanceVal%>',event,'false');"
									  	/> 
									</s:else>
										
										
										<input type="text"
											id="paraFrm_cancelAmountLocalConv<%=localConveyanceVal%>"
											name="cancelAmountLocalConv"
											value='<s:property value="cancelAmountLocalConv"/>' size="8"
											onkeypress="return numbersWithDot();" />
									</td>

									<td class="sortableTD" align="center"><input type="text"
										name="localConveyanceDetails"
										value='<s:property value="localConveyanceDetails"/>'
										id="paraFrm_localConveyanceDetails<%=localConveyanceVal%>"
										size="10" /></td>
									<td class="sortableTD" align="center"><input type="text"
										name="localConveyanceFileUpload" size="20" readonly="true"
										id="paraFrm_localConveyanceFileUpload<%=localConveyanceVal%>"
										value='<s:property value="localConveyanceFileUpload" />' /></td>
									<td class="sortableTD" align="center" nowrap="nowrap"><s:if
										test="%{travelQuickBook.buttonShowFlag}">
										<input type="button" name="uploadLoc" value="Upload"
											class="token"
											onclick="uploadTicketFile('paraFrm_localConveyanceFileUpload<%=localConveyanceVal%>');" />
									</s:if></td>

									<td class="sortableTD" align="center" nowrap="nowrap"><input
										type="button" name="show" value="Show" class="token"
										onclick="showRecord('paraFrm_localConveyanceFileUpload<%=localConveyanceVal%>');" />
									</td>

									<td align="center" class="sortableTD"><img
										src="../pages/common/css/icons/delete.gif"
										onclick="deleteCurrentRow(this);"></td>


								</tr>
								<%
								localConveyanceVal++;
								%>
							</s:iterator>
						</table>
						</div>
						</td>
					</tr>


					<%
					int k = 0;
					%>
					<input type="hidden" name="hLocCount" id="hLocCount" value="<%=k%>">
					<s:hidden name="delLoc" theme="simple" />
				</table>
				</div>
				</td>
			</tr>

		</s:if>

		<!-- Local Conveyance Details table ends here -->

		<!-- button starts -->

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
							<td width="78%" align="left"><s:if test="showFlag">
 
							<s:submit value="  Save" action="TravelQuickBooking_saveBooking"
								id="save" onclick="return saveValidate();" theme="simple"
								cssClass="save" />

							<s:submit value="  Finalize"
								action="TravelQuickBooking_finalalize" theme="simple"
								cssClass="save" onclick="return chkFinalize();"/>

							<input type="button" class="token" theme="simple"
								onclick="return callDiv();" value=" Send Mail" />

							<!-- <s:submit value="  Send Mail"
								action="TravelQuickBooking_sendMail" theme="simple"
								cssClass="token" />
						 -->
						 
						 <input type="button" name="reportBtn" value="TRF Report" class="token" onclick="return reportFun();"
								/>
								
								<s:if test='%{travelQuickBook.revokeFlag}'></s:if>
								<s:else>
								
									<s:submit value="  Revoke"
								action="TravelQuickBooking_revoke" theme="simple"
								cssClass="save" />
								</s:else>
								
							<s:submit name="backButton" value="Back" cssClass="back"
								onclick="return backFun();">
							</s:submit>

					 

					</s:if> <s:elseif test="bookingPageFlag">

						<s:submit name="backButton" value="Back" cssClass="back"
							onclick="return backFun();" />

					</s:elseif> <s:else>
					<s:if test='%{travelQuickBook.revokeFlag}'><s:submit value="  Save" action="TravelQuickBooking_saveBooking"
								id="save" onclick="return saveValidate();" theme="simple"
								cssClass="save" /></s:if>
						<s:submit name="backButton" value="Back" cssClass="back"
							onclick="return backFun();"/>
						
						<s:else>
							<input type="button" class="token" theme="simple"
								onclick="return callDiv();" value=" Send Mail" />
								
									<s:submit value="  Revoke"
								action="TravelQuickBooking_revoke" theme="simple"
								cssClass="save" />
							<input type="button" name="reportBtn" value="TRF Report" class="token"
							 onclick="return reportFun();" />
								
						</s:else>




					</s:else></td>
					<td width="22%" align="right"></td>
				</tr>

				<!-- button ends -->


				<s:hidden name="hiddenApplicationCode" />
				<s:hidden name="hiddenGradeCode" />
				<s:hidden name="path" value="%{getText('data_path')}" id="pathFld" />
				<s:hidden name="buttonShowFlag"></s:hidden>
				<s:hidden name="lodgeDtlFlag"></s:hidden>
				<s:hidden name="localConvFlag"></s:hidden>

				<s:hidden name="toEamilAddress" />
					<s:hidden  name="source"/>  
				<s:hidden name="defaultCurrency"/>
				<s:hidden name="travelQuickBook.revokeFlag" />

			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>

function checkRevoke()
{
	 var conf=confirm("Are you sure !\n You want to revoke this record ?");
  				if(conf){
  					
  					return true;
  				}else{
  				return false;
  				}
	
}
function backFun(){
try{

	document.getElementById('paraFrm').target = "_self";
	if(document.getElementById('paraFrm_source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('paraFrm_source').value=='myservices')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}else{
		document.getElementById('paraFrm').action = 'TravelQuickBooking_back.action';
		}
		
	document.getElementById('paraFrm').submit();
	}catch(e){
	alert(e);
	}
}

function reportFun(){
	
	document.getElementById('paraFrm').action="TravelApplication_report.action";
		document.getElementById('paraFrm').submit();
	
	
	}

function callValidation()
{

var otherProject  =document.getElementById('paraFrm_travelOtherProject').value;

var applicationCode  =document.getElementById('paraFrm_hiddenApplicationCode').value;

if(otherProject=="")
{
	alert("Please enter other project");
	return false;
}

 conf=confirm("Do you really want to add data into master?");
 	 if(conf)
 	 {
  addProjectToMaster('<%=request.getContextPath()%>/TMS/TravelQuickBooking_addDataToMaster.action?','TravelForm',otherProject,applicationCode);
  	}
  	else
  	{
  			return false;
  	}
  	
return true;
}


function callValidationCustomer()
{

var othercustomer  =document.getElementById('paraFrm_travelOtherCustomer').value;

var applicationCode  =document.getElementById('paraFrm_hiddenApplicationCode').value;

if(othercustomer=="")
{
	alert("Please enter other customer");
	return false;
}

 conf=confirm("Do you really want to add data into master?");
 	 if(conf)
 	 {
  addCustomerToMaster('<%=request.getContextPath()%>/TMS/TravelQuickBooking_addDataToCustomerMaster.action?','TravelForm',othercustomer,applicationCode);
  	}
  	else
  	{
  			return false;
  	}
  	
return true;
}


function sendMail()
	{
	try
	{
  
		 document.getElementById('paraFrm').action='TravelQuickBooking_sendMail.action';
		 document.getElementById('paraFrm').submit();
		return true;
	}catch(e){	alert(e);}	
	}
	


function hide_Div() {
		document.getElementById('div_Id').style.display = 'none';
	}


function callDiv()
	{	
	try{
	
 	document.getElementById('toId').value =document.getElementById('paraFrm_toEamilAddress').value;
		document.getElementById('div_Id').style.display = '';
		document.getElementById('ccMailId').focus();
		
		}catch(e){ alert(e);}
	}
	


function viewPolicy(gradId){		   
	
	win=window.open('','win','top=260,left=250,width=650,height=600,scrollbars=yes,status=no,resizable=no');
	document.getElementById("paraFrm").target="win";
	document.getElementById("paraFrm").action="TravelApplication_getTravelPolicy.action?gradeId="+gradId;
	document.getElementById("paraFrm").submit();	
	document.getElementById("paraFrm").target="main"; 
	}
 
 function viewDetails(applicationCode){		   
	
	win=window.open('','win','top=260,left=250,width=650,height=600,scrollbars=yes,status=no,resizable=no');
	document.getElementById("paraFrm").target="win";
	document.getElementById("paraFrm").action="TravelApplication_viewApplication.action?applicationId="+applicationCode;
	document.getElementById("paraFrm").submit();	
	document.getElementById("paraFrm").target="main"; 
	}
 

/* COMMON FUNCITONS*/



function showRecord(fieldName)
	{
		
		var fileName =document.getElementById(fieldName).value;
		//alert(fileName);
		
		if(fileName=="")
		{
			alert("Please upload file.");
			return false ; 
		}
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "TravelQuickBooking_viewAttachedProof.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		
		return true ; 
	}


 function uploadTicketFile(fldId)
{

	var path=document.getElementById('pathFld').value+"/TMS/<%=session.getAttribute("session_pool")%>/Tickets";
	window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fldId,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
}


	/*function setFieldId(id,action){
	try{
			document.getElementById('paraFrm_fieldName').value=id;
	        callsF9(500,325,action);
	        }catch(e){ alert(e);}
	}*/
	
	function setFieldId(event,id,action,textFieldName){
	try{
	        callDropdown(textFieldName,200,250,action,event,'false');
	        }catch(e){alert(e);}
	}
	
	
	function deleteRowFromTable(tableName){
	
	try{var table = document.getElementById(tableName);
		table.deleteRow(1);}
	catch(e){alert("value of e---------"+e);}
		
	}

function addRowToJourneyBlock()	{
	
		//var journeyId = document.getElementById("paraFrm_jourModeId").value;
		//var journeyMode = document.getElementById("paraFrm_jourMode").value;
	
		  var tbl = document.getElementById('journeyTable');
		  var lastRow = tbl.rows.length;
		  // if there's no header row in the table, then iteration = lastRow + 1
		   var iteration = lastRow-1;
		  var row = tbl.insertRow(lastRow);
		  // left cell
		  var cell0 = row.insertCell(0);
		  var column0 = document.createTextNode(iteration);
		  cell0.className='sortableTD';
		  cell0.align='center';
		  cell0.appendChild(column0);
	  
   		  var cell1 = row.insertCell(1);
      	  var column1 = document.createElement('input');
      	  cell1.className='sortableTD';
		  column1.type = 'text';
		  column1.name = 'journeyFromPlace';
		  column1.id = 'paraFrm_journeyFromPlace'+iteration;
		  column1.size='10';
		  column1.maxLength='20';
		  column1.onkeyup = function(event){
		  try {
		   	setFieldId(event,iteration,'TravelQuickBooking_f9City.action?fieldName=journeyFromPlace'+iteration,'paraFrm_journeyFromPlace'+iteration);
		  	
		  }catch(e){alert(e);}
		  };
		  cell1.align='center';
		  cell1.appendChild(column1);
  
		  var cell2 = row.insertCell(2);
		  var column2 = document.createElement('input');
		  cell2.className='sortableTD';
  		  column2.type = 'text';
		  column2.name = 'journeyToPlace';
		  column2.id = 'paraFrm_journeyToPlace'+iteration;
		  column2.size='10';
		  column2.maxLength='20';
		  column2.onkeyup = function(event){
		  try {
		   	setFieldId(event,iteration,'TravelQuickBooking_f9City.action?fieldName=journeyToPlace'+iteration,'paraFrm_journeyToPlace'+iteration);
		  	
		  }catch(e){alert(e);}
		  };
		  cell2.align='center';
		  cell2.appendChild(column2);
		  
		  var cell3 = row.insertCell(3);
		  var column3 = document.createElement('input');
		  cell3.className='sortableTD';
		  column3.type = 'text';
		  column3.readOnly = 'true';
  		  column3.name = 'jourMode';
		  column3.id = 'paraFrm_jourMode'+iteration;
		  column3.size ='20';
		  column3.maxLength ='20';
		  cell3.align='center';
		  cell3.appendChild(column3);
		  
		  var cell4 = row.insertCell(4);
		  var column4 = document.createElement('img');
		  cell4.className='sortableTD';
		  column4.type='image';
		  column4.src="../pages/images/recruitment/search2.gif";
		  column4.align='center';
		  column4.id='img'+ iteration;
		  column4.theme='simple';
		  column4.onclick=function(event){
		  try {
		   	setFieldId(event,iteration,'TravelQuickBooking_f9JourneyMode.action?fieldName='+iteration,'paraFrm_jourMode'+iteration);
		  	
		  }catch(e){alert(e);}
		  };
		  cell4.appendChild(column4);
		  
		  var cell5 = row.insertCell(5);
		  var column5 = document.createElement('input');
		  cell5.className='sortableTD';
		  column5.type = 'text';
  		  column5.name = 'journeyDate';
		  column5.id = 'paraFrm_journeyDate'+iteration;
		  column5.size ='7';
		  column5.maxLength ='10';
		  cell5.align='center';
		  cell5.appendChild(column5);
		  
		  var cell6 = row.insertCell(6);
		  var column6 = document.createElement('img');
		  cell6.className='sortableTD';
		  column6.type='image';
		  column6.src="../pages/images/recruitment/Date.gif";
		  column6.align='center';
		  column6.id='img'+ iteration;
		  column6.theme='simple';
		  column6.onclick=function(){
		  try {
				NewCal('paraFrm_journeyDate'+iteration,'DDMMYYYY');
		  }catch(e){alert(e);}
		  };
		  cell6.appendChild(column6);
		  
		  var cell7 = row.insertCell(7);
		  var column7 = document.createElement('input');
		  cell7.className='sortableTD';
		  column7.type = 'text';
  		  column7.name = 'journeyTime';
		  column7.id = 'paraFrm_journeyTime'+iteration;
		  column7.size ='5';
		  column7.maxLength ='10';
		  cell7.align='center';
		  cell7.appendChild(column7);
		  
		  var cell8 = row.insertCell(8);
		  var column8 = document.createElement('input');
		  cell8.className='sortableTD';
		  column8.type = 'text';
		  column8.readOnly = 'true';
  		  column8.name = 'journeyAgency';
		  column8.id = 'paraFrm_journeyAgency'+iteration;
		  column8.size ='10';
		  column8.maxLength ='20';
		  cell8.align='center';
		  cell8.appendChild(column8);
		  
		  var cell9 = row.insertCell(9);
		  var column9 = document.createElement('img');
		  cell9.className='sortableTD';
		  column9.type='image';
		  column9.src="../pages/images/recruitment/search2.gif";
		  column9.align='center';
		  column9.id='img'+ iteration;
		  column9.theme='simple';
		  column9.onclick=function(event){
		  try {
		  
		  
		   if(document.getElementById('paraFrm_jourMode'+iteration).value == ""){
		  		alert("Please select journey mode class");
		  		return false;
		  		}else{
		  		callAgency(event,iteration);
		  		//setFieldId(event,iteration,'TravelQuickBooking_f9TravelMedium.action?fieldName='+iteration,'paraFrm_journeyMedium'+iteration);
		   	}
		   
		  // 	setFieldId(event,iteration,'TravelQuickBooking_f9AgencyName.action?fieldName='+iteration,'paraFrm_journeyAgency'+iteration);
		  	
		  }catch(e){alert(e);}
		  };
		  cell9.appendChild(column9);
		  
		  var cell10 = row.insertCell(10);
		  var column10 = document.createElement('input');
		  cell10.className='sortableTD';
		  column10.type = 'text';
		  column10.readOnly = 'true';
  		  column10.name = 'journeyMedium';
		  column10.id = 'paraFrm_journeyMedium'+iteration;
		  column10.size ='10';
		  column10.maxLength ='20';
		  cell10.align='center';
		  cell10.appendChild(column10);
		  
		  var cell11 = row.insertCell(11);
		  var column11 = document.createElement('img');
		  cell11.className='sortableTD';
		  column11.type='image';
		  column11.src="../pages/images/recruitment/search2.gif";
		  column11.align='center';
		  column11.id='img'+ iteration;
		  column11.theme='simple';
		  column11.onclick=function(event){
		  try {
		  
		  if(document.getElementById('paraFrm_jourMode'+iteration).value == ""){
		  		alert("Please select journey mode class");
		  		return false;
		  		}else{
		  		callCarrier(event,iteration);
		  		//setFieldId(event,iteration,'TravelQuickBooking_f9TravelMedium.action?fieldName='+iteration,'paraFrm_journeyMedium'+iteration);
		   	}
		  	
		  }catch(e){alert(e);}
		  };
		  cell11.appendChild(column11);
		  
		  var cell12 = row.insertCell(12);
		  var column12 = document.createElement('input');
		  cell12.className='sortableTD';
		  column12.type = 'text';
  		  column12.name = 'busTrainNo';
		  column12.id = 'paraFrm_busTrainNo'+iteration;
		  column12.size ='5';
		  column12.maxLength ='10';
		  cell12.align='center';
		  cell12.appendChild(column12);
		  
		  var cell13= row.insertCell(13);
		  var column13 = document.createElement('input');
		  cell13.className='sortableTD';
		  column13.type = 'text';
  		  column13.name = 'ticketNo';
		  column13.id = 'paraFrm_ticketNo'+iteration;
		  column13.size ='5';
		  column13.maxLength ='10';
		  cell13.align='center';
		  cell13.appendChild(column13);
		  
		  var cell14= row.insertCell(14);
		  var column14 = document.createElement('input');
		  cell14.className='sortableTD';
		  column14.type = 'text';
  		  column14.name = 'cost';
		  column14.id = 'paraFrm_cost'+iteration;
		  column14.size ='5';
		  column14.maxLength ='10';
		  column14.onkeypress=function(){
		   		return numbersOnly();
		  	};
		  
		 /* column14.onblur = function(){
		   		setCurrencyValues('paraFrm_currencyCost'+iteration, 'paraFrm_currencyActualCost'+iteration, 'paraFrm_currencyCancelAmountJourney'+iteration);
		  	};
		  */		
		  cell14.align='center';
		  
		  
		  var cell15= row.insertCell(15);
		  var column15 = document.createElement('input');
		  cell15.className='sortableTD';
		  column15.type = 'text';
  		  column15.name = 'actualCost';
		  column15.id = 'paraFrm_actualCost'+iteration;
		  column15.size ='5';
		  column15.maxLength ='10';
		  column15.onkeypress=function(){
		   		return numbersWithDot();  
		  	};
		  cell15.align='center';

		 
		  var cell16= row.insertCell(16);
		  var column16 = document.createElement('input');
		  cell16.className='sortableTD';
		  column16.type = 'text';
  		  column16.name = 'cancelAmountJourney';
		  column16.id = 'paraFrm_cancelAmountJourney'+iteration;
		  column16.size ='8';
		  column16.maxLength ='10';
		  cell16.align='center';
		 
		 
		  
		  var cell17= row.insertCell(17);
		  var column17 = document.createElement('input');
		  cell17.className='sortableTD';
		  column17.type = 'text';
  		  column17.name = 'journeydetails';
		  column17.id = 'paraFrm_journeydetails'+iteration;
		  column17.size ='10';
		  column17.maxLength ='10';
		  cell17.align='center';
		  cell17.appendChild(column17);
		  
		  var cell18= row.insertCell(18);
		  var column18 = document.createElement('input');
		  cell18.className='sortableTD';
		  column18.type = 'text';
  		  column18.name = 'journeyFileUpload';
		  column18.id = 'paraFrm_journeyFileUpload'+iteration;
		  column18.size ='20';
		  column18.maxLength ='20';
		  cell18.align='center';
		  cell18.appendChild(column18);
		  
		  var cell19 = row.insertCell(19);
		  var column19 = document.createElement('input');
		  cell19.className='sortableTD';
		  column19.type='button';
		  column19.align='center';
		  column19.name='uploadBtn';
		  column19.value='Upload';
		  column19.onclick=function(){
		  try {
		  var uploadField= 'paraFrm_journeyFileUpload'+iteration;
		   	uploadTicketFile(uploadField); 
		  }catch(e){alert(e);}
		  };
		  cell19.appendChild(column19);
		  
		  
		  var cell20= row.insertCell(20);
		  var column20 = document.createElement('input');
		  cell20.className='sortableTD';
		  column20.type='button';
		  column20.align='center';
		  column20.name='showBtn';
		  column20.value='Show';
		  column20.onclick=function(){
		  try {
		  return showRecord('paraFrm_journeyFileUpload'+iteration);
		  }catch(e){alert(e);}
		  };
		  cell20.appendChild(column20);

		  var cell21= row.insertCell(21);
		  var column21 = document.createElement('img');
		  cell21.className='sortableTD';
		  column21.type='image';
		  column21.src="../pages/common/css/icons/delete.gif";
		  column21.align='absmiddle';
	  	  column21.id='img'+ iteration;
		  column21.theme='simple';
		  cell21.align='center';

		  column21.onclick=function(){
		  try {
		   deleteCurrentRow(this);
		  }catch(e){alert(e);}
		  };
		  cell21.appendChild(column21);
		  
		  var column22= document.createElement('input');
		  column22.type = 'hidden';
  		  column22.name = 'journeyModeId';
		  column22.id = 'paraFrm_journeyModeId'+iteration;
		  column22.maxLength ='2';
		  cell0.appendChild(column22);
		  
		  var column23= document.createElement('input');
		  column23.type = 'hidden';
  		  column23.name = 'journeyAgencyId';
		  column23.id = 'paraFrm_journeyAgencyId'+iteration;
		  column23.maxLength ='2';
		  cell0.appendChild(column23);

		  var column24= document.createElement('input');
		  column24.type = 'hidden';
  		  column24.name = 'journeyMediumId';
		  column24.id = 'paraFrm_journeyMediumId'+iteration;
		  column24.maxLength ='2';
		  cell0.appendChild(column24);
		  
		  
		  var column25 = document.createElement('input');
		  column25.type='text';
		  column25.name = 'currencyCost';
  		  column25.value = document.getElementById('paraFrm_defaultCurrency').value; 
		  column25.id = 'paraFrm_currencyCost'+iteration;
		  column25.size ='3';
		  column25.style.border="none";
		  column25.readOnly = 'true';
		  column25.title="Click here to change currency" 
		  column25.onclick=function(event){
		  try {
		   	callDropdown('paraFrm_currencyCost'+iteration, 200, 250, 'TravelQuickBooking_f9JourneyCurrency.action?currencyName=journey&currencyID='+iteration,event,'false');
		  }catch(e){alert(e);}
		  };
		  cell14.appendChild(column25);
		  cell14.appendChild(column14);
		  
		  var column26 = document.createElement('input');
		  column26.type='text';
		  column26.name = 'currencyActualCost';
  		  column26.value = document.getElementById('paraFrm_defaultCurrency').value; 
		  column26.id = 'paraFrm_currencyActualCost'+iteration;
		  column26.size ='3';
		  column26.style.border="none";
		  column26.readOnly = 'true';
		  column26.title="Click here to change currency" 
		  column26.onclick=function(event){
		  try {
		   	callDropdown('paraFrm_currencyActualCost'+iteration, 200, 250, 'TravelQuickBooking_f9JourneyCurrency.action?currencyName=journey&currencyID='+iteration,event,'false');
		  }catch(e){alert(e);}
		  };
		  cell15.appendChild(column26);
		  cell15.appendChild(column15);
		  
		  var column27 = document.createElement('input');
		  column27.type='text';
		  column27.name = 'currencyCancelAmountJourney';
  		  column27.value = document.getElementById('paraFrm_defaultCurrency').value; 
		  column27.id = 'paraFrm_currencyCancelAmountJourney'+iteration;
		  column27.size ='3';
		  column27.style.border="none";
		  column27.readOnly = 'true';
		  column27.title="Click here to change currency" 
		  column27.onclick=function(event){
		  try {
		   	callDropdown('paraFrm_currencyCancelAmountJourney'+iteration, 200, 250, 'TravelQuickBooking_f9JourneyCurrency.action?currencyName=journey&currencyID='+iteration,event,'false');
		  }catch(e){alert(e);}
		  };
		  cell16.appendChild(column27);
		  cell16.appendChild(column16);
	}
	/*LODGING DETAILS BLOCK BEGINS*/
	
	function addRowToAccomodationBlock()	{
	
		  var tbl = document.getElementById('accomodationTable');
		  var lastRow = tbl.rows.length;
		  // if there's no header row in the table, then iteration = lastRow + 1
		  var iteration = lastRow-1;
		  var row = tbl.insertRow(lastRow);
		  // left cell
		  var cell0 = row.insertCell(0);
		  var column0 = document.createTextNode(iteration);
		  cell0.className='sortableTD';
		  cell0.align='center';
		  cell0.appendChild(column0);
	  
   		  var cell1 = row.insertCell(1);
      	  var column1 = document.createElement('input');
      	  cell1.className='sortableTD';
		  column1.type = 'text';
		  column1.readOnly = 'true';
		  column1.name = 'accomodationHotelType';
		  column1.id = 'paraFrm_accomodationHotelType'+iteration;
		  column1.size='10';
		  column1.maxLength='20';
		  cell1.align='center';
		  cell1.appendChild(column1);
		  
		  var cell2 = row.insertCell(2);
		  var column2 = document.createElement('img');
		  column2.type='image';
		  column2.src="../pages/images/recruitment/search2.gif";
		  column2.align='center';
		  column2.id='img'+ iteration;
		  column2.theme='simple';
		  column2.onclick=function(event){
		  try {
		  	setFieldId(event,iteration,'TravelQuickBooking_f9LodgingType.action?fieldName='+iteration,'paraFrm_accomodationHotelType'+iteration);
		  }catch(e){alert(e);}
		  };
		  cell2.appendChild(column2);

		  var cell3 = row.insertCell(3);
		  var column3 = document.createElement('input');
		  cell3.className='sortableTD';
  		  column3.type = 'text';
  		  column3.readOnly = 'true';
		  column3.name = 'accomodationRoomType';
		  column3.id = 'paraFrm_accomodationRoomType'+iteration;
		  column3.size='10';
		  column3.maxLength='20';
		  cell3.align='center';
		  cell3.appendChild(column3);
		  
		  var cell4 = row.insertCell(4);
		  var column4 = document.createElement('img');
		  column4.type='image';
		  cell4.className='sortableTD';
		  column4.src="../pages/images/recruitment/search2.gif";
		  column4.align='center';
		  column4.id='img'+ iteration;
		  column4.theme='simple';
		  column4.onclick=function(event){
		  try {
		  		if(document.getElementById('paraFrm_accomodationHotelType'+iteration).value == ""){
		  		alert("Please select Hotel Type");
		  		return false;
		  		}else{
		  			callRoomType(event,iteration);
		  			//setFieldId(event,iteration,'TravelQuickBooking_f9RoomType.action?fieldName='+iteration,'paraFrm_accomodationRoomType'+iteration);
		  		}
		   	
		  }catch(e){alert(e);}
		  };
		  cell4.appendChild(column4);
		  
		  var cell5 = row.insertCell(5);
		  var column5 = document.createElement('input');
		  cell5.className='sortableTD';
		  column5.type = 'text';
  		  column5.name = 'accomodationCity';
		  column5.id = 'paraFrm_accomodationCity'+iteration;
		  column5.size ='10';
		  column5.maxLength ='20';
		  column5.onkeyup = function(event){
		  try {
		   	setFieldId(event,iteration,'TravelQuickBooking_f9City.action?fieldName=accomodationCity'+iteration,'paraFrm_accomodationCity'+iteration);
		  	
		  }catch(e){alert(e);}
		  };
		  cell5.align='center';
		  cell5.appendChild(column5);
		  
		  var cell6 = row.insertCell(6);
		  var column6 = document.createElement('input');
		  cell6.className='sortableTD';
		  column6.type = 'text';
  		  column6.name = 'accomodationPrefLocation';
		  column6.id = 'paraFrm_accomodationPrefLocation'+iteration;
		  column6.size ='10';
		  column6.maxLength ='20';
		  cell6.align='center';
		  cell6.appendChild(column6);
		  
		  var cell7 = row.insertCell(7);
		  var column7 = document.createElement('input');
		  cell7.className='sortableTD';
		  column7.type = 'text';
  		  column7.name = 'accomodationFromDate';
		  column7.id = 'paraFrm_accomodationFromDate'+iteration;
		  column7.size ='7';
		  column7.maxLength ='10';
		  cell7.align='center';
		  cell7.appendChild(column7);
		  
		  var cell8 = row.insertCell(8);
		  var column8 = document.createElement('img');
		  column8.type='image';
		  cell8.className='sortableTD';
		  column8.src="../pages/images/recruitment/Date.gif";
		  column8.align='center';
		  column8.id='img'+ iteration;
		  column8.theme='simple';
		  column8.onclick=function(){
		  try {
				NewCal('paraFrm_accomodationFromDate'+iteration,'DDMMYYYY');
		  }catch(e){alert(e);}
		  };
		  cell8.appendChild(column8);
		  
		  var cell9 = row.insertCell(9);
		  var column9 = document.createElement('input');
		  cell9.className='sortableTD';
		  column9.type = 'text';
  		  column9.name = 'accomodationFromTime';
		  column9.id = 'paraFrm_accomodationFromTime'+iteration;
		  column9.size ='5';
		  column9.maxLength ='10';
		  cell9.align='center';
		  cell9.appendChild(column9);
		 
		  var cell10 = row.insertCell(10);
		  var column10 = document.createElement('input');
		  cell10.className='sortableTD';
		  column10.type = 'text';
  		  column10.name = 'accomodationToDate';
		  column10.id = 'paraFrm_accomodationToDate'+iteration;
		  column10.size ='7';
		  column10.maxLength ='10';
		  cell10.align='center';
		  cell10.appendChild(column10);
		  
		  var cell11 = row.insertCell(11);
		  var column11 = document.createElement('img');
		  cell11.className='sortableTD';
		  column11.type='image';
		  column11.src="../pages/images/recruitment/Date.gif";
		  column11.align='center';
		  column11.id='img'+ iteration;
		  column11.theme='simple';
		  column11.onclick=function(){
		  try {
				NewCal('paraFrm_accomodationToDate'+iteration,'DDMMYYYY');
		  }catch(e){alert(e);}
		  };
		  cell11.appendChild(column11);
		  
		  var cell12 = row.insertCell(12);
		  var column12 = document.createElement('input');
		  cell12.className='sortableTD';
		  column12.type = 'text';
  		  column12.name = 'accomodationToTime';
		  column12.id = 'paraFrm_accomodationToTime'+iteration;
		  column12.size ='5';
		  column12.maxLength ='10';
		  cell12.align='center';
		  cell12.appendChild(column12);
		  
		  var cell13 = row.insertCell(13);
		  var column13 = document.createElement('input');
		  cell13.className='sortableTD';
  		  column13.type = 'text';
  		  column13.readOnly = 'true';
		  column13.name = 'accomodationHotelName';
		  column13.id = 'paraFrm_accomodationHotelName'+iteration;
		  column13.size='10';
		  column13.maxLength='20';
		  cell13.align='center';
		  cell13.appendChild(column13);
		  
		  var cell14 = row.insertCell(14);
		  var column14 = document.createElement('img');
		  column14.type='image';
		  cell14.className='sortableTD';
		  column14.src="../pages/images/recruitment/search2.gif";
		  column14.align='center';
		  column14.id='img'+ iteration;
		  column14.theme='simple';
		  column14.onclick=function(event){
		  try {
		  if(document.getElementById('paraFrm_accomodationCity'+iteration).value == ""){
		  		alert("Please select city");
		  		return false;
		  		}else{
		  			callCityhotel(event,iteration);
		  		}
		  	//setFieldId(event,iteration,'TravelQuickBooking_f9HotelName.action?fieldName='+iteration,'paraFrm_accomodationHotelName'+iteration);
		  }catch(e){alert(e);}
		  };
		  cell14.appendChild(column14);
		  
		  var cell15 = row.insertCell(15);
		  var column15 = document.createElement('input');
		  cell15.className='sortableTD';
		  column15.type = 'text';
  		  column15.name = 'noOfDays';
		  column15.id = 'paraFrm_noOfDays'+iteration;
		  column15.size ='5';
		  column15.maxLength ='10';
		  column15.onkeypress=function(){
		   		return numbersOnly();
		  	};
		  cell15.align='center';
		  cell15.appendChild(column15);
		  
		  var cell16 = row.insertCell(16);
		  var column16 = document.createElement('input');
		  cell16.className='sortableTD';
		  column16.type = 'text';
  		  column16.name = 'bookingAmount';
		  column16.id = 'paraFrm_bookingAmount'+iteration;
		  column16.size ='5';
		  column16.maxLength ='10';
		  column16.onkeypress=function(){
		   		return numbersOnly();
		  	};
		  cell16.align='center';
		 
		  
		  var cell17 = row.insertCell(17);
		  var column17 = document.createElement('input');
		  cell17.className='sortableTD';
		  column17.type = 'text';
  		  column17.name = 'corporateRate';
		  column17.id = 'paraFrm_corporateRate'+iteration;
		  column17.size ='5';
		  column17.maxLength ='10';
		  column17.onkeypress=function(){
		   		return numbersOnly();
		  	};
		  cell17.align='center';
		  
		 
		  
		  var cell18 = row.insertCell(18);
		  var column18 = document.createElement('input');
		  cell18.className='sortableTD';
		  column18.type = 'text';
  		  column18.name = 'cancelAmountAccom';
		  column18.id = 'paraFrm_cancelAmountAccom'+iteration;
		  column18.size ='8';
		  column18.maxLength ='10';
		  cell18.align='center';
		 
		  
		  var cell19 = row.insertCell(19);
		  var column19 = document.createElement('input');
		  cell19.className='sortableTD';
		  column19.type = 'text';
  		  column19.name = 'bookingDetails';
		  column19.id = 'paraFrm_bookingDetails'+iteration;
		  column19.size ='10';
		  column19.maxLength ='10';
		  cell19.align='center';
		  cell19.appendChild(column19);
		  
		  var cell20 = row.insertCell(20);
		  var column20 = document.createElement('input');
		  cell20.className='sortableTD';
		  column20.type = 'text';
  		  column20.name = 'bookingFileUpload';
		  column20.id = 'paraFrm_bookingFileUpload'+iteration;
		  column20.size ='20';
		  column20.maxLength ='20';
		  cell20.align='left';
		  cell20.appendChild(column20);
		  
		  var cell21 = row.insertCell(21);
		  var column21 = document.createElement('input');
		  cell21.className='sortableTD';
		  column21.type='button';
		  column21.align='center';
		  column21.name='uploadBtn';
		  column21.value='Upload';
		  column21.onclick=function(){
		  try {
		  var uploadField= 'paraFrm_bookingFileUpload'+iteration;
		   	uploadTicketFile(uploadField); 
		  }catch(e){alert(e);}
		  };
		  cell21.appendChild(column21);
		  
		  var cell22= row.insertCell(22);
		  var column22 = document.createElement('input');
		  cell22.className='sortableTD';
		  column22.type='button';
		  column22.align='center';
		  column22.name='showBtn';
		  column22.value='Show';
		  column22.onclick=function(){
		  try {
		  return showRecord('paraFrm_bookingFileUpload'+iteration);
		  }catch(e){alert(e);}
		  };
		  cell22.appendChild(column22);
		  
		  
		  
		  var cell23= row.insertCell(23);
		  var column23 = document.createElement('img');
		  cell23.className='sortableTD';
		  column23.type='image';
		  column23.src="../pages/common/css/icons/delete.gif";
		  column23.align='absmiddle';
		  column23.id='img'+ iteration;
		  column23.theme='simple';
		  cell23.align='center';

		  column23.onclick=function(){
		  try {
		   deleteCurrentRow(this);
		  }catch(e){alert(e);}
		  };
		  cell23.appendChild(column23);
		  
		  var column24 = document.createElement('input');
		  column24.type = 'hidden';
  		  column24.name = 'accomodationHotelTypeId';
		  column24.id = 'paraFrm_accomodationHotelTypeId'+iteration;
		  column24.maxLength ='2';
		  cell1.appendChild(column24);
		  
		  var column25 = document.createElement('input');
		  column25.type = 'hidden';
  		  column25.name = 'accomodationRoomTypeId';
		  column25.id = 'paraFrm_accomodationRoomTypeId'+iteration;
		  column25.maxLength ='2';
		  cell1.appendChild(column25);

		  var column26 = document.createElement('input');
		  column26.type = 'hidden';
  		  column26.name = 'accomodationHotelNameId';
		  column26.id = 'paraFrm_accomodationHotelNameId'+iteration;
		  column26.maxLength ='2';
		  cell1.appendChild(column26);
		  
		  
		  var column27 = document.createElement('input');
		  column27.type='text';
		  column27.name = 'currencyCorporateRate';
  		  column27.value = document.getElementById('paraFrm_defaultCurrency').value; 
		  column27.id = 'paraFrm_currencyCorporateRate'+iteration;
		  column27.size ='3';
		  column27.style.border="none";
		  column27.readOnly = 'true';
		  column27.title="Click here to change currency" 
		  column27.onclick=function(event){
		  try {
		   	callDropdown('paraFrm_currencyCorporateRate'+iteration, 200, 250, 'TravelQuickBooking_f9LodgingCurrency.action?currencyName=lodging&currencyID='+iteration,event,'false');
		  }catch(e){alert(e);}
		  };
		  cell17.appendChild(column27);
		  cell17.appendChild(column17);
		   
		  var column28 = document.createElement('input');
		  column28.type='text';
		  column28.name = 'currencyCancelAmountAccom';
  		  column28.value = document.getElementById('paraFrm_defaultCurrency').value; 
		  column28.id = 'paraFrm_currencyCancelAmountAccom'+iteration;
		  column28.size ='3';
		  column28.style.border="none";
		  column28.readOnly = 'true';
		  column28.title="Click here to change currency" 
		  column28.onclick=function(event){
		  try {
		   	callDropdown('paraFrm_currencyCancelAmountAccom'+iteration, 200, 250, 'TravelQuickBooking_f9LodgingCurrency.action?currencyName=lodging&currencyID='+iteration,event,'false');
		  }catch(e){alert(e);}
		  };
		  cell18.appendChild(column28);
		  cell18.appendChild(column18);
		   
		  var column29 = document.createElement('input');
		  column29.type='text';
		  column29.name = 'currencyBookingAmount';
  		  column29.value = document.getElementById('paraFrm_defaultCurrency').value; 
		  column29.id = 'paraFrm_currencyBookingAmount'+iteration;
		  column29.size ='3';
		  column29.style.border="none";
		  column29.readOnly = 'true';
		  column29.title="Click here to change currency" 
		  column29.onclick=function(event){
		  try {
		   	callDropdown('paraFrm_currencyBookingAmount'+iteration, 200, 250, 'TravelQuickBooking_f9LodgingCurrency.action?currencyName=lodging&currencyID='+iteration,event,'false');
		  }catch(e){alert(e);}
		  };
		  cell16.appendChild(column29);
		  cell16.appendChild(column16);
		   
		  
	}
	
	/* LOCAL CONVEYANCE BLOCK BEGINS*/
	
	function addRowToLocalConveyanceBlock()	{
	
		  var tbl = document.getElementById('localConveyanceTable');
		  var lastRow = tbl.rows.length;
		  // if there's no header row in the table, then iteration = lastRow + 1
		  var iteration = lastRow-1;
		  var row = tbl.insertRow(lastRow);
		  // left cell
		  var cell0 = row.insertCell(0);
		  var column0 = document.createTextNode(iteration);
		  cell0.className='sortableTD';
		  cell0.align='center';
		  cell0.appendChild(column0);
	  
   		  var cell1 = row.insertCell(1);
      	  var column1 = document.createElement('input');
      	  cell1.className='sortableTD';
		  column1.type = 'text';
		  column1.name = 'localConveyanceCity';
		  column1.id = 'paraFrm_localConveyanceCity'+iteration;
		  column1.size='10';
		  column1.maxLength='20';
		  column1.onkeyup = function(event){
		  try {
		   	setFieldId(event,iteration,'TravelQuickBooking_f9City.action?fieldName=localConveyanceCity'+iteration,'paraFrm_localConveyanceCity'+iteration);
		  }catch(e){alert(e);}
		  };
		  cell1.align='center';
		  cell1.appendChild(column1);
  
		  var cell2 = row.insertCell(2);
		  var column2 = document.createElement('input');
		  cell2.className='sortableTD';
  		  column2.type = 'text';
		  column2.name = 'localConveyanceTravelDetail';
		  column2.id = 'paraFrm_localConveyanceTravelDetail'+iteration;
		  column2.size='10';
		  column2.maxLength='20';
		  cell2.align='center';
		  cell2.appendChild(column2);
		  
		  var cell3 = row.insertCell(3);
		  var column3 = document.createElement('input');
		  cell3.className='sortableTD';
		  column3.type = 'text';
  		  column3.name = 'localConveyanceTravelMedium';
		  column3.id = 'paraFrm_localConveyanceTravelMedium'+iteration;
		  column3.size ='10';
		  column3.maxLength ='20';
		  cell3.align='center';
		  cell3.appendChild(column3);
		  
		  var cell4 = row.insertCell(4);
		  var column4 = document.createElement('input');
		  cell4.className='sortableTD';
		  column4.type = 'text';
  		  column4.name = 'localConveyanceFromDate';
		  column4.id = 'paraFrm_localConveyanceFromDate'+iteration;
		  column4.size ='7';
		  column4.maxLength ='10';
		  cell4.align='center';
		  cell4.appendChild(column4);
		  
		  var cell5 = row.insertCell(5);
		  var column5 = document.createElement('img');
		  column5.type='image';
		  cell5.className='sortableTD';
		  column5.src="../pages/images/recruitment/Date.gif";
		  column5.align='center';
		  cell5.align='center';
		  column5.id='img'+ iteration;
		  column5.theme='simple';
		  column5.onclick=function(){
		  try {
				NewCal('paraFrm_localConveyanceFromDate'+iteration,'DDMMYYYY');
		  }catch(e){alert(e);}
		  };
		  cell5.appendChild(column5);
		  
		  
		  var cell6 = row.insertCell(6);
		  var column6 = document.createElement('input');
		  cell6.className='sortableTD';
		  column6.type = 'text';
  		  column6.name = 'localConveyanceFromTime';
		  column6.id = 'paraFrm_localConveyanceFromTime'+iteration;
		  column6.size ='5';
		  column6.maxLength ='10';
		  cell6.align='center';
		  cell6.appendChild(column6);
		  
		  var cell7 = row.insertCell(7);
		  var column7 = document.createElement('input');
		  cell7.className='sortableTD';
		  column7.type = 'text';
  		  column7.name = 'localConveyanceToDate';
		  column7.id = 'paraFrm_localConveyanceToDate'+iteration;
		  column7.size ='7';
		  column7.maxLength ='10';
		  cell7.align='center';
		  cell7.appendChild(column7);
		  
		  var cell8 = row.insertCell(8);
		  var column8 = document.createElement('img');
		  cell8.className='sortableTD';
		  column8.type='image';
		  column8.src="../pages/images/recruitment/Date.gif";
		  column8.align='center';
		  cell8.align='center';
		  column8.id='img'+ iteration;
		  column8.theme='simple';
		  column8.onclick=function(){
		  try {
				NewCal('paraFrm_localConveyanceToDate'+iteration,'DDMMYYYY');
		  }catch(e){alert(e);}
		  };
		  cell8.appendChild(column8);
		 
		  var cell9 = row.insertCell(9);
		  var column9 = document.createElement('input');
		  cell9.className='sortableTD';
		  column9.type = 'text';
  		  column9.name = 'localConveyanceToTime';
		  column9.id = 'paraFrm_localConveyanceToTime'+iteration;
		  column9.size ='5';
		  column9.maxLength ='10';
		  cell9.align='center';
		  cell9.appendChild(column9);
		  
		  var cell10 = row.insertCell(10);
		  var column10 = document.createElement('input');
		  cell10.className='sortableTD';
		  column10.type = 'text';
  		  column10.name = 'localConveyanceTariffCost';
		  column10.id = 'paraFrm_localConveyanceTariffCost'+iteration;
		  column10.size ='5';
		  column10.maxLength ='10';
		  cell10.align='center';
		  
		 
		  var cell11 = row.insertCell(11);
		  var column11 = document.createElement('input');
		  cell11.className='sortableTD';
		  column11.type = 'text';
  		  column11.name = 'cancelAmountLocalConv';
		  column11.id = 'paraFrm_cancelAmountLocalConv'+iteration;
		  column11.size ='8';
		  column11.maxLength ='10';
		  cell11.align='center';
		  
		  
		  var cell12 = row.insertCell(12);
		  var column12 = document.createElement('input');
		  cell12.className='sortableTD';
		  column12.type = 'text';
  		  column12.name = 'localConveyanceDetails';
		  column12.id = 'paraFrm_localConveyanceDetails'+iteration;
		  column12.size ='10';
		  column12.maxLength ='10';
		  cell12.align='center';
		  cell12.appendChild(column12);
		  
		  var cell13 = row.insertCell(13);
		  var column13 = document.createElement('input');
		  cell13.className='sortableTD';
		  column13.type = 'text';
  		  column13.name = 'localConveyanceFileUpload';
		  column13.id = 'paraFrm_localConveyanceFileUpload'+iteration;
		  column13.size ='20';
		  column13.maxLength ='20';
		  cell13.align='center';
		  cell13.appendChild(column13);
		  
		  var cell14 = row.insertCell(14);
		  var column14 = document.createElement('input');
		  cell14.className='sortableTD';
		  column14.type='button';
		  cell14.align='center';
		  column14.name='uploadBtn';
		  column14.value='Upload';
		  
		  column14.onclick=function(){
		  try {
		  var uploadField= 'paraFrm_localConveyanceFileUpload'+iteration;
		   	uploadTicketFile(uploadField); 
		  }catch(e){alert(e);}
		  };
		  cell14.appendChild(column14);
		  
		   var cell15= row.insertCell(15);
		  var column15 = document.createElement('input');
		  cell15.className='sortableTD';
		  column15.type='button';
		  cell15.align='center';
		  column15.name='showBtn';
		  column15.value='Show';
		  column15.onclick=function(){
		  try {
		  return showRecord('paraFrm_localConveyanceFileUpload'+iteration);
		  }catch(e){alert(e);}
		  };
		  cell15.appendChild(column15);
		  
		  var cell16= row.insertCell(16);
		  var column16 = document.createElement('img');
		  cell16.className='sortableTD';
		  column16.type='image';
		  column16.src="../pages/common/css/icons/delete.gif";
		  column16.align='absmiddle';
		  column16.id='img'+ iteration;
		  column16.theme='simple';
		  cell16.align='center';

		  column16.onclick=function(){
		  try {
		   deleteCurrentRow(this);
		  }catch(e){alert(e);}
		  };
		  cell16.appendChild(column16);
		  
		  
		  var column17 = document.createElement('input');
		  column17.type='text';
		  column17.name = 'currencyLocalConveyanceTariffCost';
  		  column17.value = document.getElementById('paraFrm_defaultCurrency').value; 
		  column17.id = 'paraFrm_currencyLocalConveyanceTariffCost'+iteration;
		  column17.size ='3';
		  column17.style.border="none";
		  column17.readOnly = 'true';
		  column17.title="Click here to change currency" 
		  column17.onclick=function(event){
		  try {
		   	callDropdown('paraFrm_currencyLocalConveyanceTariffCost'+iteration, 200, 250, 'TravelQuickBooking_f9CoveyanceCurrency.action?currencyName=conveyance&currencyID='+iteration,event,'false');
		  }catch(e){alert(e);}
		  };
		  cell10.appendChild(column17);
		  cell10.appendChild(column10);
		
		
		  var column18 = document.createElement('input');
		  column18.type='text';
		  column18.name = 'currencyCancelAmountLocalConv';
  		  column18.value = document.getElementById('paraFrm_defaultCurrency').value; 
		  column18.id = 'paraFrm_currencyCancelAmountLocalConv'+iteration;
		  column18.size ='3';
		  column18.style.border="none";
		  column18.readOnly = 'true';
		  column18.title="Click here to change currency" 
		  column18.onclick=function(event){
		  try {
		   	callDropdown('paraFrm_currencyCancelAmountLocalConv'+iteration, 200, 250, 'TravelQuickBooking_f9CoveyanceCurrency.action?currencyName=conveyance&currencyID='+iteration,event,'false');
		  }catch(e){alert(e);}
		  };
		  cell11.appendChild(column18);
		  cell11.appendChild(column11);
		
	}
	
	function chkFinalize(){
		var fields=["adminComments"];
 	   	var labels=["travel.comments"];
   		var flag = ["enter"];
   			
   			if(!validateBlank(fields,labels,flag)){
    			 	return false;
    		}else if(!validateApproval()){
				return false;
			}else{
    			document.getElementById('paraFrm').target = "_self";
		 		document.getElementById('paraFrm').action = "TravelQuickBooking_saveBooking.action";
				document.getElementById('paraFrm').submit();
    		}
	}

	
function saveValidate(){

   var fields=["adminComments"];
 	   	var labels=["travel.comments"];
   		var flag = ["enter"];
	
	if(!validateBlank(fields,labels,flag)){
    		return false;
    }
    	
	if(!validateApproval()){
			return false;
	}
	
    
	var journeyTableRows=document.getElementById("journeyTable").rows.length-1;
 	for( var i=0; i<journeyTableRows;i++){	
 		if(!document.getElementById("paraFrm_journeyDate"+i).value==""){
		 	 if(!validateDateFormat('paraFrm_journeyDate'+i,'Journey Date')){
		 					return false;
		 	  }
 		}	
 		if(!document.getElementById("paraFrm_journeyTime"+i).value==""){
		  if(!validateTimeMethod('paraFrm_journeyTime'+i, 'Time')){
		 		return false;
		  }
 	   } 			
 	} 	
 	if(document.getElementById('paraFrm_lodgeDtlFlag').value=='true'){	
 		var accomodationTableRows=document.getElementById("accomodationTable").rows.length-1;
 		for( var i=0; i<accomodationTableRows;i++){
		 	if(!validateDateFormat('paraFrm_accomodationFromDate'+i,'From Date')){
		 					return false;
		 	} 
		 	if(!validateTimeMethod('paraFrm_accomodationFromTime'+i, 'Time')){
		 			return false;
		 	} 			
		 	if(!validateDateFormat('paraFrm_accomodationToDate'+i,'From Date')){
		 					return false;
		 	} 			 
			if(!validateTimeMethod('paraFrm_accomodationToTime'+i, 'Time')){
			 					return false;
			}
 		} 
	}		
	if(document.getElementById('paraFrm_localConvFlag').value=='true'){
 		var localConveyanceTableRows=document.getElementById("localConveyanceTable").rows.length-1;
 				//local conveyance block
        for( var i=0; i<localConveyanceTableRows;i++){ 			 
 			if(!validateDateFormat('paraFrm_localConveyanceFromDate'+i,'From Date')){
 					return false;
 			}
 			if(!validateTimeMethod('paraFrm_localConveyanceFromTime'+i, 'Time')){
 					return false;
 		    } 				
 			if(!validateDateFormat('paraFrm_localConveyanceToDate'+i,'To Date')){
 					return false;
 			} 				 
 			if(!validateTimeMethod('paraFrm_localConveyanceToTime'+i, 'Time')){
 					return false;
 			}	
 		}
 	}
	for (var i = 0; i < document.all.length; i++) {
		if(document.all[i].id == 'save') {
					document.all[i].disabled=true;
		}
	}
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = "TravelQuickBooking_saveBooking.action";
	document.getElementById('paraFrm').submit();
	return true;
}
	
	/*
	function saveValidate(){
		
		 
		 var fields=["adminComments"];
 	   var labels=["travel.comments"];
   		 var flag = ["enter"];
 	 
     
		if(!validateApproval()){
			return false;
		}else{
			
				if(!validateBlank(fields,labels,flag))
    			 return false;
	 	
			for (var i = 0; i < document.all.length; i++) {
			if(document.all[i].id == 'save') {
			//alert(document.all[i]);
			document.all[i].disabled=true;
		}
		}
	 	
			document.getElementById('paraFrm').target = "_self";
		 	document.getElementById('paraFrm').action = "TravelQuickBooking_saveBooking.action";
			document.getElementById('paraFrm').submit();
		}
	return true;
	}
	
	*/

	
	function validateApproval(){
	
	 
		var journeyTableRows=document.getElementById("journeyTable").rows.length-1;
		

		try{
		
		/*Journey details block*/
			  for( var i=0; i<journeyTableRows;i++){
 			
 				if(document.getElementById('paraFrm_journeyFromPlace'+i).value==""){
 					alert("Please enter From place ");
 					return false;
 				}
 				if(document.getElementById('paraFrm_journeyToPlace'+i).value==""){
 					alert("Please enter Journey to place ");
 					return false;
 				}
 				if(document.getElementById('paraFrm_jourMode'+i).value==""){
 					alert("Please select Journey Mode ");
 					return false;
 				}
 				if(document.getElementById('paraFrm_journeyDate'+i).value==""){
 					alert("Please enter Journey Date ");
 					return false;
 				}
 				if(!validateDateFormat('paraFrm_journeyDate'+i,'Journey Date')){
 					return false;
 				}
 				if(!checkDateBetweenForApplication('paraFrm_travelStartDate','paraFrm_travelEndDate','paraFrm_journeyDate'+i,'Travel start date','Travel end date','Journey Date')){
 					return false;
 				}
 				if(document.getElementById('paraFrm_journeyTime'+i).value==""){
 					alert("Please enter Journey Time ");
 					return false;
 				}
 				if(!validateTimeMethod('paraFrm_journeyTime'+i, 'Time')){
 					return false;
 				}
 				if(document.getElementById('paraFrm_journeyAgency'+i).value==""){
 					alert("Please select Journey agency name ");
 					return false;
 				}
 				if(document.getElementById('paraFrm_journeyMedium'+i).value==""){
 					alert("Please select Carrier ");
 					return false;
 				}
 				if(document.getElementById('paraFrm_busTrainNo'+i).value==""){
 					alert("Please enter Journey Bus/Train No ");
 					return false;
 				}
 				if(document.getElementById('paraFrm_ticketNo'+i).value==""){
 					alert("Please enter Journey Ticket No ");
 					return false;
 				}
 				if(document.getElementById('paraFrm_cost'+i).value==""){
 					alert("Please enter Journey best fare ");
 					return false;
 				}
 				
 				if(document.getElementById('paraFrm_actualCost'+i).value==""){
 					alert("Please enter Journey actual fare ");
 					return false;
 				}
 		 	 	
 				if(document.getElementById('paraFrm_journeyFileUpload'+i).value==""){
 					alert("Please select Journey File Upload ");
 					return false;
 				}
 				
 				
 			} 
 			/*Accomodation block*/
 			
 		
 			
 			if(document.getElementById('paraFrm_lodgeDtlFlag').value=='true')
 			{
 			
 			var accomodationTableRows=document.getElementById("accomodationTable").rows.length-1;
 			
 			for( var i=0; i<accomodationTableRows;i++){
 			 
 			 	if(document.getElementById('paraFrm_accomodationHotelType'+i).value == ""){
 					alert("Please select accomodation hotel type");
 					return false;
 				}
 				if(document.getElementById('paraFrm_accomodationRoomType'+i).value == ""){
 					alert("Please select accomodation room type");
 					return false;
 				}
 				if(document.getElementById('paraFrm_accomodationCity'+i).value== ""){
 					alert("Please enter accomodation city");
 					return false;
 				}
 				/*if(document.getElementById('paraFrm_accomodationPrefLocation'+i).value == ""){
 					alert("Please enter accomodation preferred location");
 					return false;
 				}*/
 				if(document.getElementById('paraFrm_accomodationFromDate'+i).value == ""){
 					alert("Please select accomodation from date");
 					return false;
 				}
 				if(!validateDateFormat('paraFrm_accomodationFromDate'+i,'From Date')){
 					return false;
 				}
 				if(!checkDateBetweenForApplication('paraFrm_travelStartDate','paraFrm_travelEndDate','paraFrm_accomodationFromDate'+i,'Travel start date','Travel end date','Accomodation From Date')){
 					return false;
 				}
 				if(document.getElementById('paraFrm_accomodationFromTime'+i).value == ""){
 					alert("Please enter accomodation from time");
 					return false;
 				}
 				if(!validateTimeMethod('paraFrm_accomodationFromTime'+i, 'Time')){
 				
 					return false;
 				}
 				if(!validateTimeMethod('paraFrm_accomodationFromTime'+i, 'Time')){
 				
 					return false;
 				}
 				if(document.getElementById('paraFrm_accomodationToDate'+i).value == ""){
 					alert("Please select accomodation to date");
 					return false;
 				}
 				if(!validateDateFormat('paraFrm_accomodationToDate'+i,'From Date')){
 					return false;
 				}
 				if(!checkDateBetweenForApplication('paraFrm_accomodationFromDate'+i,'paraFrm_travelEndDate','paraFrm_accomodationToDate'+i,'Accomodation From Date','Travel end date','Accomodation To Date')){
 					return false;
 				}
 				if(document.getElementById('paraFrm_accomodationToTime'+i).value == ""){
 					alert("Please enter lodging to time");
 					return false;
 				}
 				if(!validateTimeMethod('paraFrm_accomodationToTime'+i, 'Time')){
 					return false;
 				}
 				if(!validateTimeMethod('paraFrm_accomodationToTime'+i, 'Time')){
 				
 					return false;
 				}
 				
 				if(document.getElementById('paraFrm_accomodationHotelName'+i).value == ""){
 					alert("Please select lodging hotel name");
 					return false;
 				}
 				
 				if(document.getElementById('paraFrm_noOfDays'+i).value == ""){
 					alert("Please enter lodging no of days");
 					return false;
 				}
 				if(document.getElementById('paraFrm_bookingAmount'+i).value == ""){
 					alert("Please enter lodging rec rate");
 					return false;
 				}
 				if(document.getElementById('paraFrm_corporateRate'+i).value == ""){
 					alert("Please enter lodging corporate rate");
 					return false;
 				}
 			 
 				
 				if(document.getElementById('paraFrm_bookingFileUpload'+i).value == ""){
 					alert("Please enter lodging File Upload");
 					return false;
 				}
 			} 
 			
 			}
 			
 			if(document.getElementById('paraFrm_localConvFlag').value=='true')
 			
 			{
 			
 			
 		var localConveyanceTableRows=document.getElementById("localConveyanceTable").rows.length-1;
 				//local conveyance block
 			for( var i=0; i<localConveyanceTableRows;i++){
 			
 				if(document.getElementById('paraFrm_localConveyanceCity'+i).value ==""){
 					alert("Please enter local conveyance city");
 					return false;
 				}
 			/*	if(document.getElementById('paraFrm_localConveyanceTravelDetail'+i).value ==""){
 					alert("Please enter local conveyance travel detail");
 					return false;
 				}
 				*/
 				if(document.getElementById('paraFrm_localConveyanceTravelMedium'+i).value ==""){
 					alert("Please select local conveyance medium");
 					return false;
 				}
 				if(document.getElementById('paraFrm_localConveyanceFromDate'+i).value ==""){
 					alert("Please enter local conveyance from date");
 					return false;
 				}
 				if(!validateDateFormat('paraFrm_localConveyanceFromDate'+i,'From Date')){
 					return false;
 				}
 				if(!checkDateBetweenForApplication('paraFrm_travelStartDate','paraFrm_travelEndDate','paraFrm_localConveyanceFromDate'+i,'Travel start date','Travel end date','Local Conveyance From Date')){
 					return false;
 				}
 				if(document.getElementById('paraFrm_localConveyanceFromTime'+i).value ==""){
 					alert("Please enter local conveyance from Time");
 					return false;
 				}
 				if(!validateTimeMethod('paraFrm_localConveyanceFromTime'+i, 'Time')){
 				
 					return false;
 				}
 				if(document.getElementById('paraFrm_localConveyanceToDate'+i).value ==""){
 					alert("Please enter local conveyance to date");
 					return false;
 				}
 				if(!validateDateFormat('paraFrm_localConveyanceToDate'+i,'To Date')){
 					return false;
 				}
 				if(!checkDateBetweenForApplication('paraFrm_localConveyanceFromDate'+i,'paraFrm_travelEndDate','paraFrm_localConveyanceToDate'+i,'Local Conveyance From Date','Travel end date','Local Conveyance To Date')){
 					return false;
 				}
 				if(document.getElementById('paraFrm_localConveyanceToTime'+i).value ==""){
 					alert("Please enter local conveyance to time");
 					return false;
 				}
 				if(!validateTimeMethod('paraFrm_localConveyanceToTime'+i, 'Time')){
 					return false;
 				}
 				if(document.getElementById('paraFrm_localConveyanceTariffCost'+i).value ==""){
 					alert("Please enter local conveyance tariff cost");
 					return false;
 				}
 					 
 				
 			}
 			
 			}
 			
 	 
 		
		}catch(e){alert(e);}
		
		 return true ; 
		}
		
		
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
 	
	
	function callCarrier(event,id)
	{
	
	try{
	var journeyModeId = document.getElementById('paraFrm_journeyModeId'+id).value;
	
 
	 setFieldId(event,id,'TravelQuickBooking_f9TravelMedium.action?fieldName='+id+'&fieldValue='+journeyModeId,'paraFrm_journeyMedium'+id);
	
	}catch(e)
	{
			//alert("vishu---------"+e);
	}
	
	}
	
	
	
	function callRoomType(event,id)
	{
	
	try{
	var hotelId = document.getElementById('paraFrm_accomodationHotelTypeId'+id).value;
 
 	 setFieldId(event,id,'TravelQuickBooking_f9RoomType.action?fieldName='+id+'&fieldValue='+hotelId,'paraFrm_accomodationRoomType'+id);
	
	}catch(e)
	{
			//alert("vishu---------"+e);
	}
	
	}
	
	
	
	function callCityhotel(event,id)
	{
	
	try{
	var cityName = document.getElementById('paraFrm_accomodationCity'+id).value;
	
	
	var roomType = document.getElementById('paraFrm_accomodationRoomTypeId'+id).value;
	
	
	var hotelType = document.getElementById('paraFrm_accomodationHotelTypeId'+id).value;
 
   setFieldId(event,id,'TravelQuickBooking_f9HotelName.action?fieldName='+id+'&selectedCity='+cityName+'&roomType='+roomType+'&hotelType='+hotelType,'paraFrm_accomodationHotelName'+id);
	
	}catch(e)
	{
			//alert("vishu---------"+e);
	}
	
	}
	
	
	
	function callAgency(event,id)
	{
	
	try{
	var journeyMode = document.getElementById('paraFrm_journeyModeId'+id).value;;
  
   setFieldId(event,id,'TravelQuickBooking_f9AgencyName.action?fieldName='+id+'&fieldValue='+journeyMode,'paraFrm_journeyAgency'+id);
	
	}catch(e)
	{
			alert("vishu---------"+e);
	}
	
	}
	
		function deleteCurrentRow(obj){
		var delRow = obj.parentNode.parentNode;
		var tbl = delRow.parentNode.parentNode;
		var rIndex = delRow.sectionRowIndex;
		var rowArray = new Array(delRow);
		deleteRows(rowArray);
		
	}
	
		function deleteRows(rowObjArray){
		for (var i=0; i<rowObjArray.length; i++) {
			var rIndex = rowObjArray[i].sectionRowIndex;
			rowObjArray[i].parentNode.deleteRow(rIndex);
		}
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
	
		if((validTime < starttime) || (validTime > endtime)) { 
			alert(""+enteredlabel+" should be between "+startlabel+" and "+endlabel);
			document.getElementById(enteredDt).focus();
			return false;
		}
		return true;
 	}
	
	
function setCurrencyValues(currency, field1, field2){
	/*	
		var tbl = document.getElementById('journeyTable');
		var lastRow = tbl.rows.length;
		var iteration = lastRow-1;
		
		for(var i = 0; i<iteration; i++) {
			document.getElementById('paraFrm_currencyCost'+i).value = document.getElementById('paraFrm_currencyCost'+(iteration - 1)).value;
			document.getElementById('paraFrm_currencyActualCost'+i).value = document.getElementById('paraFrm_currencyCost'+(iteration - 1)).value;
			document.getElementById('paraFrm_currencyCancelAmountJourney'+i).value = document.getElementById('paraFrm_currencyCost'+(iteration - 1)).value;
			//alert("Actual >>" + document.getElementById('paraFrm_currencyActualCost'+i).value);
			//alert("Cancel >>" + document.getElementById('paraFrm_currencyCancelAmountJourney'+i).value);
		}
	*/	
		//alert("currency >>"+currency);
		//alert("field1 >>"+currency);
		//alert("field2 >>"+currency);
		
		document.getElementById(field1).value = document.getElementById(currency).value;
		document.getElementById(field2).value = document.getElementById(currency).value;
	}	

</script>


