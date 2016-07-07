<!-- Created by manish sakpal on 1st March 2011 -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="NewHireRehireApprover" validate="true" id="paraFrm"
	validate="true" theme="simple">

		
	<s:textfield name="listType" />
	
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="myPagePendingCancel" id="myPagePendingCancel" />
	<s:hidden name="myPageApproved" id="myPageApproved" />
	<s:hidden name="myPageRejected" id="myPageRejected" />
	<table width="100%" align="right" cellpadding="2" cellspacing="1"
		class="formbg">
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">New
					Hire/Rehire Approver </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%">
					<div align="right"><span class="style2"></span> <font
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
						<td colspan="5">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2" class="formbg">
							<tr>
								<td colspan="2" id="ctrlShow"><b>Approver Comments</b><font
									id='ctrlHide' color="red">*</font></td>
								<td colspan="3" id="ctrlShow"><s:textarea theme="simple"
									cols="70" rows="3" name="approverComments"
									id="approverComments"
									onkeypress="return imposeMaxLength(event, this, 500);" /></td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td colspan="5">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2" class="formbg">
							<s:if test="labelFlag">
								<tr>
									<td colspan="5" class="text_head"><strong
										class="forminnerhead">Re-Hire : </strong></td>
								</tr>
								<tr>
								<td colspan="5" class="text_head"><strong
										class="forminnerhead">Select employee record to re-hire :</strong></td>
								</tr>
							</s:if>
							<s:else>
								<tr>
									<td colspan="5" class="text_head"><strong
										class="forminnerhead">New Hire: </strong></td>
								</tr>
								<tr>
								<td colspan="5" class="text_head"><strong
										class="forminnerhead">Enter information below to generate a new employee record : </strong></td>
								</tr>
							</s:else>


							<tr>
								<td colspan="1"><label class="set" name="employee.id"
									id="employee.id" ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label>
								:<font color="red">*</font></td>

								<s:if test="displayFlag=='true'">
									<td colspan="4" id="ctrlShow"><s:textfield name="empToken"
										size="10" maxlength="15" /></td>
								</s:if>

								<s:else>
									<td colspan="4" id="ctrlShow"><s:textfield
										name="newHiredEmployeeToken" size="25" theme="simple"
										readonly="true" /><s:textfield name="newHiredEmployeeName"
										size="71" theme="simple" readonly="true" /> <s:hidden
										name="newHiredEmployeeCode" /> <img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16" id='ctrlShow'
										onclick="javascript:callsF9(500,325,'NewHireRehireApprover_f9Employee.action');">
									</td>
								</s:else>

							</tr>
							<tr>
								<td class="formtext"><label name="division" id="division"
									ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font
									color="red">*</font></td>
								<td nowrap="nowrap" id="ctrlShow"><s:hidden name="divCode" />
								<s:textfield name="divName" size="30" readonly="true" /> <img
									src="../pages/images/recruitment/search2.gif" width="16"
									class="iconImage" height="15"
									onclick="javascript:callsF9(500,325,'NewHireRehireApprover_f9divaction.action');" />
								</td>
								<td class="formtext"><label name="branch" id="branch"
									ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
								:<font color="red">*</font></td>
								<td nowrap="nowrap" id="ctrlShow"><s:hidden
									name="centerCode" /> <s:textfield name="centerName" size="30"
									maxlength="30" readonly="true" /> <img
									src="../pages/images/recruitment/search2.gif" class="iconImage"
									height="16" width="15"
									onclick="javascript:callsF9(500,325,'NewHireRehireApprover_f9centeraction.action');" />
								</td>
							</tr>
							<tr>
								<td class="formtext"><label name="designation"
									id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
								:<font color="red">*</font></td>
								<td nowrap="nowrap" id="ctrlShow"><s:hidden name="rankCode" />
								<s:textfield name="rankName" size="30" readonly="true" /> <img
									class="iconImage" id="ctrlShow"
									src="../pages/images/recruitment/search2.gif" width="15"
									height="16"
									onclick="javascript:callsF9(500,325,'NewHireRehireApprover_f9rankaction.action');" /></td>

								<td class="formtext"><label name="shift" id="shift"
									ondblclick="callShowDiv(this);"><%=label.get("shift")%></label>
								:<font color="red">*</font></td>
								<td nowrap="nowrap" id="ctrlShow"><s:hidden
									name="shiftCodeAppr" /> <s:textfield name="shiftTypeAppr"
									size="30" maxlength="30" readonly="true" /> <img
									class="iconImage" src="../pages/images/recruitment/search2.gif"
									height="16" width="15"
									onclick="javascript:callsF9(500,325,'NewHireRehireApprover_f9shiftaction.action');" /></td>

							</tr>

							<tr>
								<td class="formtext"><label name="employee.type"
									id="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
								:<font color="red">*</font></td>
								<td nowrap="nowrap" id="ctrlShow"><s:hidden
									name="empTypeCode" /> <s:textfield name="empTypeName"
									size="30" maxlength="30" readonly="true" /> <img
									class="iconImage" src="../pages/images/recruitment/search2.gif"
									height="16" width="15"
									onclick="javascript:callsF9(500,325,'NewHireRehireApprover_f9typeaction.action');" /></td>


								<td colspan="1" id="ctrlShow"><label name="email.id"
									id="email.id" ondblclick="callShowDiv(this);"><%=label.get("email.id")%></label>:<font
									color="red"></font></td>
								<td colspan="1" id="ctrlShow"><s:textfield size="30"
									maxlength="50" name="emailId" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</s:if>
				<s:if test="empOfficeDtlFlag">
					<tr>
						<td colspan="5">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2" class="formbg">
							<tr>
								<td colspan="5" class="text_head"><strong
									class="forminnerhead">Official Details </strong></td>
							</tr>
							<tr>
								<td><label class="set" name="employee.id" id="employee.id"
									ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label>
								:<font color="red"></font></td>
								<td height="22"><s:textfield name="empToken" size="10"
									maxlength="15" /></td>
							</tr>
							<tr>
								<td class="formtext"><label name="division" id="division"
									ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font
									color="red"></font></td>
								<td nowrap="nowrap"><s:hidden name="divCode" /> <s:textfield
									name="divName" size="30" readonly="true" /> <img
									src="../pages/images/recruitment/search2.gif" width="16"
									class="iconImage" height="15" id="ctrlHide"
									onclick="javascript:callsF9(500,325,'NewHireRehireApprover_f9divaction.action');" />
								</td>
								<td class="formtext"><label name="branch" id="branch"
									ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
								:<font color="red"></font></td>
								<td nowrap="nowrap"><s:hidden name="centerCode" /> <s:textfield
									name="centerName" size="30" maxlength="30" readonly="true" />
								<img src="../pages/images/recruitment/search2.gif"
									class="iconImage" height="16" width="15" id="ctrlHide"
									onclick="javascript:callsF9(500,325,'NewHireRehireApprover_f9centeraction.action');" />
								</td>
							</tr>
							<tr>
								<td class="formtext"><label name="designation"
									id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
								:<font color="red"></font></td>
								<td nowrap="nowrap"><s:hidden name="rankCode" /> <s:textfield
									name="rankName" size="30" readonly="true" /> <img
									class="iconImage" id="ctrlHide"
									src="../pages/images/recruitment/search2.gif" width="15"
									height="16"
									onclick="javascript:callsF9(500,325,'NewHireRehireApprover_f9rankaction.action');" /></td>

								<td class="formtext"><label name="shift" id="shift"
									ondblclick="callShowDiv(this);"><%=label.get("shift")%></label>
								:<font color="red"></font></td>
								<td nowrap="nowrap"><s:hidden name="shiftCodeAppr" /> <s:textfield
									name="shiftTypeAppr" size="30" maxlength="30" readonly="true" />
								<img class="iconImage"
									src="../pages/images/recruitment/search2.gif" height="16"
									width="15" id="ctrlHide"
									onclick="javascript:callsF9(500,325,'NewHireRehireApprover_f9shiftaction.action');" /></td>

							</tr>

							<tr>
								<td class="formtext"><label name="employee.type"
									id="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
								:<font color="red"></font></td>
								<td nowrap="nowrap"><s:hidden name="empTypeCode" /> <s:textfield
									name="empTypeName" size="30" maxlength="30" readonly="true" />
								<img class="iconImage"
									src="../pages/images/recruitment/search2.gif" height="16"
									width="15" id="ctrlHide"
									onclick="javascript:callsF9(500,325,'NewHireRehireApprover_f9typeaction.action');" /></td>

								<td width="20%" colspan="1"><label name="email.id"
									id="email.id" ondblclick="callShowDiv(this);"><%=label.get("email.id")%></label>:</td>
								<td width="30%" colspan="1"><s:textfield size="30"
									maxlength="50" name="emailId" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</s:if>

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
						<td class="sortableTD" align="center"><%=++count%></td>
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

		<!-- Approver Comments Section Ends -->
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="4"><b><label class="set"
						name="employee.infomation" id="employee.infomation"
						ondblclick="callShowDiv(this);"><%=label.get("employee.infomation")%></label></b>
					</td>
				</tr>
				<tr>
					<td><label class="set" name="applicationDate"
						id="applicationDate" ondblclick="callShowDiv(this);"><%=label.get("applicationDate")%>
					</label> :</td>
					<td><s:property value="applicationDate" /></td>
					<td></td>
					<td></td>
				</tr>

				<tr>
					<td width="25%"><label id="emp.first.name"
						name="emp.first.name" ondblclick="callShowDiv(this);"><%=label.get("emp.first.name")%></label>
					:<font color="red" id='ctrlHide'>*</font></td>
					<td width="25%"><s:textfield name="empFirstName" size="30" /></td>

					<td width="25%"><label id="emp.middle.name"
						name="emp.middle.name" ondblclick="callShowDiv(this);"><%=label.get("emp.middle.name")%></label>
					:</td>
					<td width="25%"><s:textfield name="empMiddleName" size="30" /></td>

				</tr>

				<tr>
					<td width="25%"><label id="emp.last.name" name="emp.last.name"
						ondblclick="callShowDiv(this);"><%=label.get("emp.last.name")%></label>
					:</td>
					<td width="25%"><s:textfield name="empLastName" size="30" /></td>



				</tr>

				<tr>
					<td width="25%"><label id="social.security.number"
						name="social.security.number" ondblclick="callShowDiv(this);"><%=label.get("social.security.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="socialSecurityNumber"
						size="30" onkeypress="return numbersOnly();" /></td>

					<td width="25%"><label id="social.insurance.number"
						name="social.insurance.number" ondblclick="callShowDiv(this);"><%=label.get("social.insurance.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="socialInsuranceNumber"
						size="30" onkeypress="return numbersOnly();" /></td>

				</tr>

				<tr>
					<td width="25%"><label id="emp.home.address"
						name="emp.home.address" ondblclick="callShowDiv(this);"><%=label.get("emp.home.address")%></label>
					:</td>

					<td width="25%"><s:textarea name="empHomeAddress" cols="35"
						rows="2" /></td>
				</tr>

				<tr>
					<td width="25%" colspan="1"><label name="city" id="city"
						ondblclick="callShowDiv(this);"><%=label.get("city")%></label> :<font
						color="red" id='ctrlHide'>*</font></td>
					<td width="25%" colspan="1"><s:textfield size="30"
						name="cityName" readonly="true" /> <s:hidden name="cityId" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'NewHireRehire_f9cityaction.action');"></td>


					<td width="25%"><label id="country" name="country"
						ondblclick="callShowDiv(this);"><%=label.get("country")%></label>
					:</td>
					<td width="25%"><s:textfield name="country" readonly="true"
						size="30" cssStyle="background-color: #F2F2F2;" /></td>
				</tr>
				<tr>

					<td width="25%"><label id="state.prov" name="state.prov"
						ondblclick="callShowDiv(this);"><%=label.get("state.prov")%></label>
					:</td>
					<td width="25%"><s:textfield size="30" name="stateName"
						onkeypress="return charactersonly(this)" readonly="true" size="30"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td width="25%"><label id="zip" name="zip"
						ondblclick="callShowDiv(this);"><%=label.get("zip")%></label> :</td>
					<td width="25%"><s:textfield name="zip" size="30"
						onkeypress="return numbersOnly();" maxlength="6" /></td>
				</tr>
				<tr>
					<td width="25%"><label id="home.phone.number"
						name="home.phone.number" ondblclick="callShowDiv(this);"><%=label.get("home.phone.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="homePhoneNumber" size="30"
						onkeypress="return numbersOnly();" maxlength="11" /></td>
                 <td width="25%"><label id="email.address"
						name="email.address" ondblclick="callShowDiv(this);"><%=label.get("email.address")%></label>
					:</td>
					<td width="25%"><s:textfield name="emailAddress" size="25"
						 maxlength="50" /></td>


				</tr>
				<tr>
					<td width="25%"><label id="req.number" name="req.number"
						ondblclick="callShowDiv(this);"><%=label.get("req.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="reqNumber" size="30"
						onkeypress="return numbersOnly();" maxlength="11" /></td>


				</tr>
				<tr>
					<td colspan="4">Note : <font color="">Contact your HR
					Consultant if Req Number is unknown. </font></td>
				</tr>

			</table>
			</td>
		</tr>

		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="4"><b><label class="set"
						name="personal.infomation" id="personal.infomation"
						ondblclick="callShowDiv(this);"><%=label.get("personal.infomation")%></label></b>
					</td>
				</tr>

				<tr>
					<td width="25%"><label id="sex" name="sex"
						ondblclick="callShowDiv(this);"><%=label.get("sex")%></label> :</td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:175" name="sex"
						list="#{'M':'Male ','F':'Female'}" /></td>

					<td width="25%" colspan="1"><label class="set"
						id="marital.status" name="marital.status"
						ondblclick="callShowDiv(this);"><%=label.get("marital.status")%></label>
					:</td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:175" name="maritalStatus"
						list="#{'S':'Single ','M':'Married','D':'Divorsed ','W':'Widower '}" /></td>

				</tr>
				<tr>
					<td width="25%"><label id="high.education.level"
						name="high.education.level" ondblclick="callShowDiv(this);"><%=label.get("high.education.level")%></label>
					:</td>
					<td width="25%" colspan="1"><s:textfield size="30"
						name="qualifyName" readonly="true" /> <s:hidden name="qualCode" />
					<img src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'NewHireRehire_f9qualaction.action');"></td>

					<td width="25%" colspan="1"><label class="set"
						name="birth.date" id="birth.date" ondblclick="callShowDiv(this);"><%=label.get("birth.date")%></label>
					:</td>

					<td width="25%" colspan="3" align="left" width="15%"><s:textfield
						name="birthDate" size="30"
						onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_birthDate','dd-mm-yyyy');" /> <s:a
						href="javascript:NewCal('paraFrm_birthDate','DDMMYYYY');">
						<img src="../pages/common/css/default/images/Date.gif" width="16"
							height="16" border="0" id='ctrlHide' />
					</s:a></td>

				</tr>

				<tr>
					<td width="25%"><label id="referral.source"
						name="referral.source" ondblclick="callShowDiv(this);"><%=label.get("referral.source")%></label>
					:</td>
					<td width="25%" colspan="1"><s:textfield size="30"
						name="mediumName" readonly="true" /> <s:hidden name="mediumCode" />
					<img src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'NewHireRehire_f9ReferralSource.action');"></td>

					<td width="25%" colspan="1"><label class="set"
						name="ethnic.group" id="ethnic.group"
						ondblclick="callShowDiv(this);"><%=label.get("ethnic.group")%></label>
					:</td>

					<td width="25%" colspan="1"><s:textfield size="30"
						name="castName" readonly="true" /> <s:hidden name="castCode" />
					<img src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'NewHireRehire_f9castaction.action');"></td>

				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="4"><b><label class="set"
						name="job.infomation" id="job.infomation"
						ondblclick="callShowDiv(this);"><%=label.get("job.infomation")%></label></b>
					</td>
				</tr>
				<tr>
					<td width="25%" colspan="1"><label class="set"
						name="hire.date" id="hire.date" ondblclick="callShowDiv(this);"><%=label.get("hire.date")%></label>
					:<font id='ctrlHide' color="red">*</font></td>

					<td width="25%" colspan="3" align="left" width="15%"><s:textfield
						name="hireDate" size="30" onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_hireDate','dd-mm-yyyy');" /> <s:a
						href="javascript:NewCal('paraFrm_hireDate','DDMMYYYY');">
						<img src="../pages/common/css/default/images/Date.gif" width="16"
							height="16" border="0" id='ctrlHide' />
					</s:a></td>


				</tr>
				<tr>
					<td width="25%"><label id="action.reason" name="action.reason"
						ondblclick="callShowDiv(this);"><%=label.get("action.reason")%></label>
					:</td>
					<td width="25%"><s:hidden name="actionType" />&nbsp;&nbsp;<s:select
						headerKey="" headerValue="--Select--" cssStyle="width:175"
						name="actionReason"
						list="#{'H':'Hire ','R':'ReHire','A':'Acquisition'}"
						onchange="return callActionReasonType();" /></td>
				</tr>

				<tr id="hire">
					<td width="25%"><label id="job.code" name="job.code"
						ondblclick="callShowDiv(this);"><%=label.get("job.code")%></label>
					:<font color="red" id='ctrlHide'>*</font></td>
					<td width="25%"><s:textfield name="jobCode" size="30" /></td>

					<td width="25%"><label id="job.title" name="job.title"
						ondblclick="callShowDiv(this);"><%=label.get("job.title")%></label>
					:</td>
					<td width="25%"><s:textfield name="jobTitle" size="30" /></td>

				</tr>


				<tr id="acquisitionBlock">
					<td colspan="4">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1">
						<tr>
							<td width="25%" colspan="1"><label class="set"
								name="acquisition.date" id="acquisition.date"
								ondblclick="callShowDiv(this);"><%=label.get("acquisition.date")%></label>
							:<font id='ctrlHide' color="red">*</font></td>

							<td width="25%" colspan="3" align="left"><s:textfield
								name="acquisitionDate" size="30"
								onkeypress="return numbersWithHiphen();"
								onfocus="clearText('paraFrm_acquisitionDate','dd-mm-yyyy');" />
							<s:a
								href="javascript:NewCal('paraFrm_acquisitionDate','DDMMYYYY');">
								<img src="../pages/common/css/default/images/Date.gif"
									width="16" height="16" border="0" id='ctrlHide' />
							</s:a></td>

							<td width="25%" colspan="1"><label id="acquired.company"
								name="acquired.company" ondblclick="callShowDiv(this);"><%=label.get("acquired.company")%></label>
							:</td>
							<td width="25%"><s:textfield name="acquiredCompany"
								size="30" /></td>
						</tr>
						<tr>
							<td width="25%" colspan="1"><label class="set"
								name="preacqusition.date" id="preacqusition.date"
								ondblclick="callShowDiv(this);"><%=label.get("preacqusition.date")%></label>
							:<font id='ctrlHide' color="red">*</font></td>

							<td width="25%" colspan="3" align="left"><s:textfield
								name="preacqusitionDate" size="30"
								onkeypress="return numbersWithHiphen();"
								onfocus="clearText('paraFrm_preacqusitionDate','dd-mm-yyyy');" />
							<s:a
								href="javascript:NewCal('paraFrm_preacqusitionDate','DDMMYYYY');">
								<img src="../pages/common/css/default/images/Date.gif"
									width="16" height="16" border="0" id='ctrlHide' />
							</s:a></td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="4"><b><label class="set"
						name="physical.work.location" id="physical.work.location"
						ondblclick="callShowDiv(this);"><%=label.get("physical.work.location")%></label></b>
					</td>
				</tr>
				<tr>

					<td width="15%"><s:radio name="userProfile"
						value="%{userProfile}" list="#{'Ho':'Home'}"
						onclick="callForWork();setUserProfileRadioValue(this);">
					</s:radio>&nbsp;</td>
					<td colspan="4">Employee works from home only.</td>
				</tr>

				<tr>

					<td width="15%"><s:radio name="userProfile"
						value="%{userProfile}" list="#{'Tr':'Travel'}"
						onclick="callForWork();setUserProfileRadioValue(this);">
					</s:radio></td>
					<td colspan="4">Employee visits multiple customer sites daily.
					<br>
					But does not spend 25% or more of his / her time at any one
					location.</td>
				</tr>


				<tr>

					<td width="15%"><s:radio name="userProfile"
						value="%{userProfile}" list="#{'Cu':'Customer Site'}"
						onclick="callForCustomer();setUserProfileRadioValue(this);">
					</s:radio></td>
					<td colspan="4">Employee works full time at customer site</td>
				</tr>

				<tr>

					<td width="15%"><s:radio name="userProfile"
						value="%{userProfile}" list="#{'De':'DecisionOne Office'}"
						onclick="callForDecisionOne();setUserProfileRadioValue(this);">
					</s:radio></td>
					<td colspan="4">Employee works 25% or more of his / her time
					at a DecisionOne office daily.</td>
				</tr>

				<tr>

					<td width="15%"><s:radio name="userProfile"
						value="%{userProfile}" list="#{'Va':'Variable Workforce Only'}"
						onclick="callForWork();setUserProfileRadioValue(this);">
					</s:radio></td>
					<td colspan="4">Employee work location varies by call /
					assignment.</td>
				</tr>

			</table>
			</td>
		</tr>
		<tr id="workBlock">
			<td colspan="4">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="1" class="formbg">
				<tr>
					<td width="25%"><label class="set" name="shift" id="shift"
						ondblclick="callShowDiv(this);"><%=label.get("shift")%></label> :<font
						id='ctrlHide' color="red">*</font></td>

					<td width="25%"><s:textfield size="30" name="shiftType"
						readonly="true" /> <s:hidden name="shiftCode" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'NewHireRehire_f9shiftaction.action');"></td>

					<td width="25%"><label id="reg.temp" name="reg.temp"
						ondblclick="callShowDiv(this);"><%=label.get("reg.temp")%></label>
					:</td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:175" name="regTemp"
						list="#{'R':'Regular ','T':'Temporary'}" /></td>


				</tr>
				<tr>
					<td width="25%"><label id="flsa.status" name="flsa.status"
						ondblclick="callShowDiv(this);"><%=label.get("flsa.status")%></label>
					:</td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:175" name="flsaStatus"
						list="#{'E':'Exempt ','N':'Non Exempt'}" /></td>

					<td width="25%"><label id="fulltime.parttime"
						name="fulltime.parttime" ondblclick="callShowDiv(this);"><%=label.get("fulltime.parttime")%></label>
					:<font id='ctrlHide' color="red"></font></td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:138"
						name="fulltimeParttime" list="#{'F':'Full Time ','P':'Part Time'}" /></td>
				</tr>


			</table>
			</td>
		</tr>
		<tr id="decisionBlock">
			<td colspan="4">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr id="customerBlock">
					<td width="25%" colspan="1"><label id="customer.name"
						name="customer.name" ondblclick="callShowDiv(this);"><%=label.get("customer.name")%></label>
					:<font color="red" id='ctrlHide'>*</font></td>
					<td width="25%"><s:textfield name="customerName" size="30" /></td>
					<td width="25%"></td>
					<td width="25%"></td>
				</tr>
				<tr>
					<td width="25%" colspan="1"><label class="set"
						name="physical.location.address" id="physical.location.address"
						ondblclick="callShowDiv(this);"><%=label.get("physical.location.address")%></label>
					:<font id='ctrlHide' color="red">*</font></td>

					<td><s:textarea name="physicalAddress" cols="35" rows="2" /></td>



				</tr>
				<tr>
					<td width="25%" colspan="1"><label name="city" id="city"
						ondblclick="callShowDiv(this);"><%=label.get("city")%></label> :</td>
					<td width="25%" colspan="1"><s:textfield size="30"
						name="custCityName" readonly="true" /> <s:hidden
						name="custCityId" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'NewHireRehire_f9custCityaction.action');"></td>

				</tr>
				<tr>

					<td width="25%"><label id="state.prov" name="state.prov"
						ondblclick="callShowDiv(this);"><%=label.get("state.prov")%></label>
					:</td>
					<td width="25%"><s:textfield size="30" name="custStateName"
						onkeypress="return charactersonly(this)" readonly="true" size="30"
						cssStyle="background-color: #F2F2F2;" /></td>
					<!-- ajax -->

					<td width="25%"><label id="zip" name="zip"
						ondblclick="callShowDiv(this);"><%=label.get("zip")%></label> :<font
						id='ctrlHide' color="red">*</font></td>
					<td width="25%" colspan="1"><s:textfield size="30"
						name="custZipCode" onkeyup="addSalaryPlan();" /> <s:hidden
						name="custZipId" /></td>


				</tr>
				<tr>
					<td width="25%" colspan="1"><label class="set" name="shift"
						id="shift" ondblclick="callShowDiv(this);"><%=label.get("shift")%></label>
					:</td>

					<td width="25%" colspan="1"><s:textfield size="30"
						name="custShiftType" readonly="true" /> <s:hidden
						name="custShiftCode" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'NewHireRehire_f9custShiftaction.action');"></td>

					<td width="25%"><label id="reg.temp" name="reg.temp"
						ondblclick="callShowDiv(this);"><%=label.get("reg.temp")%></label>
					:</td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:175" name="custRegTemp"
						list="#{'R':'Regular ','T':'Temporary'}" /></td>


				</tr>
				<tr>
					<td width="25%"><label id="flsa.status" name="flsa.status"
						ondblclick="callShowDiv(this);"><%=label.get("flsa.status")%></label>
					:</td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:175"
						name="custflsaStatus" list="#{'E':'Exempt ','N':'Non Exempt'}" /></td>

					<td width="25%"><label id="fulltime.parttime"
						name="fulltime.parttime" ondblclick="callShowDiv(this);"><%=label.get("fulltime.parttime")%></label>
					:<font id='ctrlHide' color="red"></font></td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:138"
						name="custfulltimeParttime"
						list="#{'F':'Full Time ','P':'Part Time'}" /></td>
				</tr>
			</table>
			</td>
		</tr>



		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="4"><b><label class="set" name="salary.data"
						id="salary.data" ondblclick="callShowDiv(this);"><%=label.get("salary.data")%></label></b>
					</td>
				</tr>
				<!--
				<tr>

					<td width="25%"><label id="salary.plan" name="salary.plan"
						ondblclick="callShowDiv(this);"><%=label.get("salary.plan")%></label>
					:</td>
					<td width="25%"><s:textfield size="30" name="salaryPlan" id="salaryPlan"
						onkeypress="return charactersonly(this)" readonly="true" size="30"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td width="25%"></td>
					<td width="25%"></td>
				</tr>
				-->
				<tr>
					<td width="25%"><label id="pay.group" name="pay.group"
						ondblclick="callShowDiv(this);"><%=label.get("pay.group")%></label>
					:</td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:175" name="payGroup"
						list="#{'BWE':'Salaried Exempt ','BWH':'Hourly', 'NOP':'Canada ','VWF':'Variable WorkForce'}" /></td>

					<td width="25%" colspan="1"><label class="set" name="grade"
						id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
					:<font id='ctrlHide' color="red"></font></td>

					<td width="25%" colspan="1"><s:textfield size="30"
						name="cadreName" readonly="true" /> <s:hidden name="cadreCode" />
					<img src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'NewHireRehire_f9gradeaction.action');"></td>
				</tr>

				<tr>

					<td width="25%"><label id="weekly.hours" name="weekly.hours"
						ondblclick="callShowDiv(this);"><%=label.get("weekly.hours")%></label>
					:</td>
					<td width="25%"><s:textfield size="30" name="weeklyHours"
						size="30" onkeypress="return numbersOnly();" /></td>

					<td width="25%"><label id="biweekly.salary"
						name="biweekly.salary" ondblclick="callShowDiv(this);"><%=label.get("biweekly.salary")%></label>
					: $</td>
					<td width="25%"><s:textfield size="30" name="biweeklySalary"
						id="biweeklySalary" size="30" onkeypress="return numbersOnly();"
						onchange="javascript:multiplyNumbers()" /></td>
				</tr>
				<tr>

					<td width="25%"><label id="annual.salary" name="annual.salary"
						ondblclick="callShowDiv(this);"><%=label.get("annual.salary")%></label>
					:</td>
					<td width="25%"><s:textfield size="30" name="annualSalary"
						id="annualSalary" size="30" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
				</tr>
				<tr>
					<td colspan="4">(Biweekly Salary * 26 )</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="4"><b><label class="set"
						name="organization.data" id="organization.data"
						ondblclick="callShowDiv(this);"><%=label.get("organization.data")%></label></b>
					</td>
				</tr>
				<tr>
					<td colspan="4">Note:<font color="">Office Mail
					Location is the City & state of the DecisionOne Office where
					interoffice mailings should be sent. Include the street address if
					more than one DecisionOne office is in the city.</font></td>
				</tr>
				<tr>
					<td width="25%" colspan="1"><label class="set"
						name="dept.number" id="dept.number"
						ondblclick="callShowDiv(this);"><%=label.get("dept.number")%></label>
					:<font id='ctrlHide' color="red"></font></td>

					<td width="25%" colspan="1"><s:hidden name="deptCode" /> <s:textfield
						name="deptName" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'NewHireRehire_f9deptNumber.action');"></td>
				</tr>
				<tr>
					<td width="25%" colspan="1"><label class="set"
						name="executive" id="executive" ondblclick="callShowDiv(this);"><%=label.get("executive")%></label>
					:<font id='ctrlHide' color="red"></font></td>

					<td width="25%" colspan="1"><s:textfield size="30"
						name="executiveName" readonly="true" /> <s:hidden
						name="executiveCode" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'NewHireRehire_f9rankaction.action');"></td>
				</tr>
				<tr>
					<td width="25%"><label id="emp.number" name="emp.number"
						ondblclick="callShowDiv(this);"><%=label.get("emp.number")%></label>
					:</td>
					<td width="25%" colspan="3"><s:textfield
						name="exeEmployeeToken" size="25" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /><s:textfield
						name="exeEmployeeName" size="71" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:hidden
						name="exeEmployeeCode" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'NewHireRehire_f9Employee.action');">


					</td>
				</tr>
				<tr>
					<td width="25%" colspan="1"><label name="office.city"
						id="office.city" ondblclick="callShowDiv(this);"><%=label.get("office.city")%></label>
					:<font color="red" id='ctrlHide'></font></td>
					<td width="25%" colspan="1"><s:textfield size="30"
						name="officeCityName" readonly="true" /> <s:hidden
						name="officeCityId" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'NewHireRehire_f9offCityaction.action');"></td>

					<td width="25%"><label id="state.prov" name="state.prov"
						ondblclick="callShowDiv(this);"><%=label.get("state.prov")%></label>
					:</td>
					<td width="25%"><s:textfield size="30" name="OfficeStateName"
						onkeypress="return charactersonly(this)" readonly="true" size="30"
						cssStyle="background-color: #F2F2F2;" /></td>

				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td><b><label id="payroll.information"
						name="payroll.information" ondblclick="callShowDiv(this);"><%=label.get("payroll.information")%></label>
					:</b></td>
				</tr>
				<tr>
					<td colspan="4">Note:<br />
					<font color=""> - Federal exemptions will be set at Single
					and Zero until an original W4 is received in payroll.<br />
					- Paychecks will be mailed to the address on file.</font></td>
				</tr>
				<tr>
					<td colspan="4"><br />
					<font color=""> When you submit this form , it will be
					automatically sent to HR and Payroll. <br />
					</font></td>
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
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>



	<s:hidden name="hireRehireApprovalId" />
	<s:hidden name="creditMemoRadio" />
	<s:hidden name="status" />

	<input type="hidden" name="userProfileRadioOptionValue"
		id="userProfileRadioOptionValue"
		value='<s:property value="userProfileRadioOptionValue"/>' />

</s:form>
<script>

callActionReasonType();
radio();	
	function callActionReasonType() {
	try {
	var actionReason= document.getElementById('paraFrm_actionReason').value;
		if(actionReason == 'H' || actionReason == 'R') {
			document.getElementById('hire').style.display='';
			document.getElementById('acquisitionBlock').style.display='none';
			
		} else {
			document.getElementById('hire').style.display='none';
			document.getElementById('acquisitionBlock').style.display='none';
		}
		if(actionReason == 'A') {
			document.getElementById('acquisitionBlock').style.display='';
			document.getElementById('hire').style.display='none';
		} else {
			document.getElementById('acquisitionBlock').style.display='none';
		}
		if(actionReason == '') {
			document.getElementById('hire').style.display='none';
			document.getElementById('acquisitionBlock').style.display='none';
		}
		
		} catch(e) 
		{
			alert("Exception occured in draft function : "+e);
		}
		
	}
	function radio()
	{
	
		 var barGainAgreement=document.getElementById('userProfileRadioOptionValue').value;
	 //alert(barGainAgreement);
	 
	 
	  if(barGainAgreement=='Cu')
		 {//alert(barGainAgreement);
		 	 document.getElementById('paraFrm_userProfileCu').checked='Cu';
		 	 document.getElementById('customerBlock').style.display=''; 
		 	   document.getElementById('workBlock').style.display='none';  
		 	   
		 }
		else if(barGainAgreement=='De')
		 {
		 	 document.getElementById('paraFrm_userProfileDe').checked='De';
		 	  document.getElementById('customerBlock').style.display='none';  
		 	  document.getElementById('workBlock').style.display='none';  
			
		 }
		 else if(barGainAgreement=='Ho')
		 {
		 	 document.getElementById('paraFrm_userProfileHo').checked='Ho';
		 	  document.getElementById('workBlock').style.display='';  
		 	  document.getElementById('customerBlock').style.display='none';  
		 	document.getElementById('decisionBlock').style.display='none';
		 }
		 else if(barGainAgreement=='Tr')
		 {
		 	 document.getElementById('paraFrm_userProfileTr').checked='Tr';
		 	  document.getElementById('workBlock').style.display='';  
		 	  document.getElementById('customerBlock').style.display='none';  
		 	document.getElementById('decisionBlock').style.display='none';
		 }else if(barGainAgreement=='')
		 {
		 	document.getElementById('workBlock').style.display='none';  
		 	document.getElementById('customerBlock').style.display='none';  
		 	document.getElementById('decisionBlock').style.display='none';
		 }
		
	}
	
	function setUserProfileRadioValue(id){
	//alert(id);
		var opt=document.getElementById('userProfileRadioOptionValue').value =id.value;	
}

function callForWork()
 {   
   if(document.getElementById('paraFrm_userProfileHo').checked )
	  { 
	   document.getElementById('workBlock').style.display=''; 
	    document.getElementById('paraFrm_regTemp').value=''; 
	    document.getElementById('paraFrm_shiftType').value=''; 
	    document.getElementById('paraFrm_flsaStatus').value=''; 
	     document.getElementById('decisionBlock').style.display='none';
	     document.getElementById('customerBlock').style.display='none';  
	  }
	  
	   
	  else if(document.getElementById('paraFrm_userProfileTr').checked )
	  { 
	   document.getElementById('workBlock').style.display='';  
	   document.getElementById('paraFrm_regTemp').value='';
	     document.getElementById('paraFrm_shiftType').value=''; 
	    document.getElementById('paraFrm_flsaStatus').value=''; 
	     document.getElementById('decisionBlock').style.display='none';
	     document.getElementById('customerBlock').style.display='none';  
	  }
	   return true;
 }
 
 	function callForCustomer()
 	{
 		if(document.getElementById('paraFrm_userProfileCu').checked )
	  { 
	  document.getElementById('paraFrm_userProfileCu').checked='Cu';
		 
	  
	  document.getElementById('customerBlock').style.display='';  
	  document.getElementById('workBlock').style.display='none';  
	  document.getElementById('decisionBlock').style.display='';
	  
	  document.getElementById('paraFrm_customerName').value='';
	  document.getElementById('paraFrm_physicalAddress').value='';
	  document.getElementById('paraFrm_custCityName').value='';
	  document.getElementById('paraFrm_custStateName').value='';
	  document.getElementById('paraFrm_custZipCode').value='';
	  document.getElementById('paraFrm_custShiftType').value='';
	   document.getElementById('paraFrm_custRegTemp').value='';
	    document.getElementById('paraFrm_custflsaStatus').value='';
	  
	  
	  
	   
	     //document.getElementById('paraFrm_userProfile').value='C';
	     
	     
	  }
	   return true;
 	}
 	function callForDecisionOne()
 	{
 		if(document.getElementById('paraFrm_userProfileDe').checked )
	  { 
	  	document.getElementById('decisionBlock').style.display='';
	  document.getElementById('workBlock').style.display='none';  
	   document.getElementById('customerBlock').style.display='none';  
	   
	  	document.getElementById('paraFrm_salaryPlan').value='GBR';
	  document.getElementById('paraFrm_customerName').value='';
	  document.getElementById('paraFrm_physicalAddress').value='';
	  document.getElementById('paraFrm_custCityName').value='';
	  document.getElementById('paraFrm_custStateName').value='';
	  document.getElementById('paraFrm_custZipCode').value='';
	  document.getElementById('paraFrm_custShiftType').value='';
	   document.getElementById('paraFrm_custRegTemp').value='';
	    document.getElementById('paraFrm_custflsaStatus').value='';
	    
	  
	   
	    // document.getElementById('paraFrm_userProfile').value='D';
	     
	     
	  }
	    return true;
 	}
 	
 	
 	
function setType(id){	
	var setvalue=document.getElementById('paraFrm_creditMemoRadio').value = id.value;	
}	


 function approveFun()
{
  var actionType = document.getElementById('paraFrm_actionType').value;
  
  
  if(actionType=="R")
  {
	var newHiredEmployeeCode = document.getElementById('paraFrm_newHiredEmployeeCode').value;
		if(newHiredEmployeeCode=="")
		{
		alert("Please Select Employee");
		return false;
		}
}
   else
   {
   
    var empToken = document.getElementById('paraFrm_empToken').value;
		if(empToken=="")
		{
			alert("Please enter "+document.getElementById('employee.id').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_empToken').focus();
		 	return false;		
		}
	
	var divName = document.getElementById('paraFrm_divName').value;
		if(divName=="")
		{
			alert("Please enter "+document.getElementById('division').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_divName').focus();
		 	return false;		
		}
	
	var centerName = document.getElementById('paraFrm_centerName').value;
		if(centerName=="")
		{
			alert("Please enter "+document.getElementById('branch').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_centerName').focus();
		 	return false;		
		}
		
		var rankName = document.getElementById('paraFrm_rankName').value;
		if(rankName=="")
		{
			alert("Please enter "+document.getElementById('designation').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_rankName').focus();
		 	return false;		
		}
		
		var shiftTypeAppr = document.getElementById('paraFrm_shiftTypeAppr').value;
		if(shiftTypeAppr=="")
		{
			alert("Please enter "+document.getElementById('shift').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_shiftTypeAppr').focus();
		 	return false;		
		}
		
		var empTypeName = document.getElementById('paraFrm_empTypeName').value;
		if(empTypeName=="")
		{
			alert("Please enter "+document.getElementById('employee.type').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_empTypeName').focus();
		 	return false;		
		}
		var emailIdVar = document.getElementById('paraFrm_emailId').value;
		
		
		try
			{
			if(!document.getElementById('paraFrm_emailId').value==''){
				var fields=["paraFrm_emailId"];
    var labels=["email.id"];
    var flag = ["enter"];
 	 if(!validateBlank(fields,labels,flag))
     return false;
	 
   if(!validateEmail('paraFrm_emailId')){
		 	return false;
		 }
		 }
		 
		}catch(e)
	{
	alert(e);
	}
		
		
		if(document.getElementById('paraFrm_status').value == 'P')
		{
			document.getElementById('paraFrm_status').value = 'A';
		}
		
		if(document.getElementById('paraFrm_status').value == 'C')
		{
			document.getElementById('paraFrm_status').value = 'X';
		}

}		

	var con = confirm("Do you really want to Approve this application?");
	if(con)
	{
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action = 'NewHireRehireApprover_approveApplication.action';
		document.getElementById('paraFrm').submit();
	}	
}

///end of approve fun 




function rejectFun()
{
	var con = confirm("Do you really want to reject this application?");
	if(con)
	{
	document.getElementById('paraFrm_status').value = 'R';	
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action = 'NewHireRehireApprover_rejectApplication.action';
		document.getElementById('paraFrm').submit();
	}	
}

function sendbackFun()
{
	var con = confirm("Do you really want to send back this application?");
	if(con)
	{
		document.getElementById('paraFrm_status').value = 'B';	
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action = 'NewHireRehireApprover_sendBackApplication.action';
		document.getElementById('paraFrm').submit();
	}	
}
 
function backtolistFun() 
{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'NewHireRehireApprover_backToList.action';
		document.getElementById('paraFrm').submit();
}
function printFun() {	
	window.print();
	}
function imposeMaxLength(Event, Object, MaxLen)
{
        return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
}
function closeFun() {
					window.close();
			}
</script>
