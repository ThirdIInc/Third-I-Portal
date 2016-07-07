<!-- Added by ganesh -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="NewHireRehire" validate="true" id="paraFrm" validate="true" theme="simple">
<s:hidden name="hireStatus" />
<s:hidden name="listType" />
	
<s:hidden name="myPage" id="myPage" />
<s:hidden name="myPageInProcess" id="myPageInProcess" />
<s:hidden name="myPageSentBack" id="myPageSentBack" />

<s:hidden name="myPageApproved" id="myPageApproved" />
<s:hidden name="myPageApprovedCancel" id="myPageApprovedCancel" />

<s:hidden name="myPageCancel" id="myPageCancel" />

<s:hidden name="myPageRejected" id="myPageRejected" />
<s:hidden name="myPageCancelRejected" id="myPageCancelRejected" />

	<table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">New
					Hire/Rehire Form</strong></td>
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
							<td >
							<label  class = "set" name="employee.id" id="employee.id" ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label>
							:<font color="red"></font>
							</td>
							<td height="22" > <s:textfield
								name="empToken" size="10" maxlength="15" />
								</td>
								</tr>
						<tr>
							<td class="formtext"><label  name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font color="red"></font></td>
							<td nowrap="nowrap" ><s:hidden name="divCode" />
							<s:textfield name="divName" size="30" readonly="true" />
							<img src="../pages/images/recruitment/search2.gif" width="16"
								class="iconImage" height="15" id="ctrlHide"
								onclick="javascript:callsF9(500,325,'NewHireRehireApprover_f9divaction.action');" />
							</td>		
							<td class="formtext"><label  name="branch" id="branch"ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :<font color="red"></font></td>
							<td nowrap="nowrap" ><s:hidden name="centerCode" />
							<s:textfield name="centerName" size="30" maxlength="30"
								readonly="true"/> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="16" width="15" id="ctrlHide"
								onclick="javascript:callsF9(500,325,'NewHireRehireApprover_f9centeraction.action');" />
							</td>
							</tr>
						<tr>
							<td class="formtext"><label  name="designation" id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :<font color="red"></font></td>
							<td nowrap="nowrap" ><s:hidden name="rankCode" />
							<s:textfield name="rankName" size="30" readonly="true" />
							<img class="iconImage"  id="ctrlHide"
								src="../pages/images/recruitment/search2.gif" width="15"
								height="16"
								onclick="javascript:callsF9(500,325,'NewHireRehireApprover_f9rankaction.action');" /></td>
								
							<td class="formtext"><label  name="shift" id="shift" ondblclick="callShowDiv(this);"><%=label.get("shift")%></label> :<font color="red"></font></td>
							<td nowrap="nowrap" ><s:hidden name="shiftCodeAppr" />
							<s:textfield name="shiftTypeAppr" size="30" maxlength="30"
								readonly="true" /> <img class="iconImage"
								src="../pages/images/recruitment/search2.gif" height="16"
								width="15" id="ctrlHide"
								onclick="javascript:callsF9(500,325,'NewHireRehireApprover_f9shiftaction.action');" /></td>
								
							</tr>
							
							<tr>
							<td class="formtext"><label  name="employee.type" id="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label> :<font color="red"></font>
							</td>
							<td nowrap="nowrap"><s:hidden name="empTypeCode" />
							<s:textfield name="empTypeName" size="30" maxlength="30"
								readonly="true" /> <img class="iconImage"
								src="../pages/images/recruitment/search2.gif" height="16"
								width="15"id="ctrlHide"
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
		<s:if test="approverCommentsFlag">
			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td colspan="5" align="left"><b>Approver Comments</b></td>
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
		</s:if>
		<!-- Approver Comments Section Ends -->

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
						name="employee.infomation" id="employee.infomation"
						ondblclick="callShowDiv(this);"><%=label.get("employee.infomation")%></label></b>
					</td>
				</tr>
			<tr>
					<td><label class="set" name="applicationDate" id="applicationDate"
						ondblclick="callShowDiv(this);"><%=label.get("applicationDate")%>
					</label> :</td>
					<td><s:textfield name="applicationDate" id="paraFrm_applicationDate" theme="simple" size="10"
						maxlength="10"
						onblur="return validateDate('paraFrm_applicationDate','Date');"
						onkeypress="return numbersWithHiphen();" readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
					<td></td>
					<td></td>
				</tr>
				
				<tr>
					<td width="25%"><label id="emp.first.name"
						name="emp.first.name" ondblclick="callShowDiv(this);"><%=label.get("emp.first.name")%></label>
					:<font color="red" id='ctrlHide'>*</font></td>
					<td width="25%"><s:textfield name="empFirstName" size="25" maxlength="30" /></td>

					<td width="25%"><label id="emp.middle.name"
						name="emp.middle.name" ondblclick="callShowDiv(this);"><%=label.get("emp.middle.name")%></label>
					:</td>
					<td width="25%"><s:textfield name="empMiddleName" size="25" maxlength="30" /></td>

				</tr>

				<tr>
					<td width="25%"><label id="emp.last.name" name="emp.last.name"
						ondblclick="callShowDiv(this);"><%=label.get("emp.last.name")%></label>
					:<font color="red" id='ctrlHide'>*</font></td>
					<td width="25%"><s:textfield name="empLastName" size="25" maxlength="30" /></td>



				</tr>

				<tr>
					<td width="25%"><label id="social.security.number"
						name="social.security.number" ondblclick="callShowDiv(this);"><%=label.get("social.security.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="socialSecurityNumber" maxlength="11" 
						size="25" /></td>

					<td width="25%"><label id="social.insurance.number"
						name="social.insurance.number" ondblclick="callShowDiv(this);"><%=label.get("social.insurance.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="socialInsuranceNumber" maxlength="11" 
						size="25" /></td>

				</tr>

				<tr>
					<td width="25%"><label id="emp.home.address"
						name="emp.home.address" ondblclick="callShowDiv(this);"><%=label.get("emp.home.address")%></label>
					:</td>

					<td width="25%"><s:textarea name="empHomeAddress" cols="28"
						rows="2" onkeypress="return imposeMaxLength(event, this, 400);"/></td>
				</tr>

				<tr>
					<td width="25%" colspan="1"><label name="city" id="city"
						ondblclick="callShowDiv(this);"><%=label.get("city")%></label> :<font
						color="red" id='ctrlHide'></font></td>
					<td width="25%" colspan="1"><s:textfield size="25"
						name="cityName" maxlength="30" /> </td>


					<td width="25%"><label id="country" name="country"
						ondblclick="callShowDiv(this);"><%=label.get("country")%></label>
					:</td>
					<td width="25%"><s:textfield name="country" 
						size="25"  maxlength="30" /></td>
				</tr>
				<tr>

					<td width="25%"><label id="state.prov" name="state.prov"
						ondblclick="callShowDiv(this);"><%=label.get("state.prov")%></label>
					:</td>
					<td width="25%"><s:textfield size="25" name="stateName"
						 size="25"	 maxlength="30" /></td>
					<td width="25%"><label id="zip" name="zip"
						ondblclick="callShowDiv(this);"><%=label.get("zip")%></label> :</td>
					<td width="25%"><s:textfield name="zip" size="25"
						 maxlength="7" /></td>
				</tr>
				<tr>
					<td width="25%"><label id="home.phone.number"
						name="home.phone.number" ondblclick="callShowDiv(this);"><%=label.get("home.phone.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="homePhoneNumber" size="25"
						 maxlength="15" /></td>
					
					<td width="25%"><label id="email.address"
						name="email.address" ondblclick="callShowDiv(this);"><%=label.get("email.address")%></label>
					:<font color="red" id='ctrlHide'>*</font></td>
					<td width="25%"><s:textfield name="emailAddress" size="25"
						 maxlength="50" /></td>

				</tr>
				<tr>
					<td width="25%"><label id="req.number" name="req.number"
						ondblclick="callShowDiv(this);"><%=label.get("req.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="reqNumber" size="25"
						maxlength="7" /></td>


				</tr>
				<tr>
					<td colspan="4">Note : <font color="">Contact your
					HR Consultant if Req Number is unknown. </font></td>
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
						ondblclick="callShowDiv(this);"><%=label.get("sex")%></label> :<font color="red" id='ctrlHide'>*</font></td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:135" name="sex"
						list="#{'M':'Male ','F':'Female'}" /></td>

					<td width="25%" colspan="1"><label class="set"
						id="marital.status" name="marital.status"
						ondblclick="callShowDiv(this);"><%=label.get("marital.status")%></label>
					:<font color="red" id='ctrlHide'>*</font></td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:135" name="maritalStatus"
						list="#{'S':'Single ','M':'Married','D':'Divorsed ','W':'Widower '}" /></td>

				</tr>
				<tr>
					<td width="25%"><label id="high.education.level"
						name="high.education.level" ondblclick="callShowDiv(this);"><%=label.get("high.education.level")%></label>
					:</td>
					<td width="25%" colspan="1"><s:textfield size="25"
						name="qualifyName" readonly="true" /> <s:hidden name="qualCode" />
					<img src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'NewHireRehire_f9qualaction.action');"></td>

					<td width="25%" colspan="1"><label class="set"
						name="birth.date" id="birth.date" ondblclick="callShowDiv(this);"><%=label.get("birth.date")%></label>
					:<font color="red" id='ctrlHide'>*</font></td>

					<td width="25%" colspan="3" align="left" width="15%"><s:textfield
						name="birthDate" size="25"
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
					<td width="25%" colspan="1"><s:textfield size="25"
						name="mediumName" readonly="true" /> <s:hidden name="mediumCode" />
					<img src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'NewHireRehire_f9ReferralSource.action');"></td>

					<td width="25%" colspan="1"><label class="set"
						name="ethnic.group" id="ethnic.group"
						ondblclick="callShowDiv(this);"><%=label.get("ethnic.group")%></label>
					:</td>

					<td width="25%" colspan="1"><s:textfield size="25"
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
						name="hireDate" size="25" onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_hireDate','dd-mm-yyyy');" /> <s:a
						href="javascript:NewCal('paraFrm_hireDate','DDMMYYYY');">
						<img src="../pages/common/css/default/images/Date.gif" width="16"
							height="16" border="0" id='ctrlHide' />
					</s:a></td>


				</tr>
				<tr>
					<td width="25%"><label id="action.reason" name="action.reason"
						ondblclick="callShowDiv(this);"><%=label.get("action.reason")%></label>
					:<font color="red" id='ctrlHide'>*</font></td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" headerKey="-1" cssStyle="width:135" name="actionReason"
						list="#{'H':'Hire ','R':'ReHire','A':'Acquisition'}"
						onchange="return callActionReasonType();" /></td>
				</tr>

				<tr id="hire">
					<td width="25%"><label id="job.code" name="job.code"
						ondblclick="callShowDiv(this);"><%=label.get("job.code")%></label>
					:<font color="red" id='ctrlHide'>*</font></td>
					<td width="25%"><s:textfield name="jobCode" size="25" maxlength="8" /></td>

					<td width="25%"><label id="job.title" name="job.title"
						ondblclick="callShowDiv(this);"><%=label.get("job.title")%></label>
					:</td>
					<td width="25%"><s:textfield name="jobTitle" size="25" maxlength="30" /></td>

				</tr>

				<!--<tr id="rehire">
					<td width="25%"><label id="job.code" name="job.code"
						ondblclick="callShowDiv(this);"><%=label.get("job.code")%></label>
					:</td>
					<td width="25%"><s:textfield name="jobCode1" size="25" /></td>

					<td width="25%"><label id="job.title" name="job.title"
						ondblclick="callShowDiv(this);"><%=label.get("job.title")%></label>
					:</td>
					<td width="25%"><s:textfield name="jobTitle1" size="25" /></td>

				</tr>
				-->
				<tr id="acquisition">
					<td colspan="4">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1">
						<tr>
							<td width="25%" colspan="1"><label class="set"
								name="acquisition.date" id="acquisition.date"
								ondblclick="callShowDiv(this);"><%=label.get("acquisition.date")%></label>
							:<font id='ctrlHide' color="red">*</font></td>

							<td width="25%" colspan="3" align="left"><s:textfield
								name="acquisitionDate" size="25"
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
								size="25" maxlength="50" /></td>
						</tr>
						<tr>
							<td width="25%" colspan="1"><label class="set"
								name="preacqusition.date" id="preacqusition.date"
								ondblclick="callShowDiv(this);"><%=label.get("preacqusition.date")%></label>
							:<font id='ctrlHide' color="red"></font></td>

							<td width="25%" colspan="3" align="left"><s:textfield
								name="preacqusitionDate" size="25"
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
						onclick="setUserProfileRadioValue(this);callForWork();">
					</s:radio>&nbsp;</td>
					<td colspan="4">Employee works from home only.</td>
				</tr>

				<tr>

					<td width="15%"><s:radio name="userProfile"
						value="%{userProfile}" list="#{'Tr':'Travel'}"
						onclick="setUserProfileRadioValue(this);callForWork();">
					</s:radio></td>
					<td colspan="4">Employee visits multiple customer sites daily.
					<br>
					But does not spend 25% or more of his / her time at any one
					location.</td>
				</tr>


				<tr>

					<td width="15%"><s:radio name="userProfile"
						value="%{userProfile}" list="#{'Cu':'Customer Site'}"
						onclick="setUserProfileRadioValue(this);callForCustomer();">
					</s:radio></td>
					<td colspan="4">Employee works full time at customer site</td>
				</tr>

				<tr>

					<td width="15%"><s:radio name="userProfile"
						value="%{userProfile}" list="#{'De':'DecisionOne Office'}"
						onclick="setUserProfileRadioValue(this);callForDecisionOne();">
					</s:radio></td>
					<td colspan="4">Employee works 25% or more of his / her time
					at a DecisionOne office daily.</td>
				</tr>

				<tr>

					<td width="15%"><s:radio name="userProfile"
						value="%{userProfile}" list="#{'Va':'Variable Workforce Only'}"
						onclick="setUserProfileRadioValue(this);callForWork();">
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

					<td width="25%"><s:textfield size="25" name="shiftType"
						readonly="true" /> <s:hidden name="shiftCode" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'NewHireRehire_f9shiftaction.action');"></td>

					<td width="25%"><label id="reg.temp" name="reg.temp"
						ondblclick="callShowDiv(this);"><%=label.get("reg.temp")%></label>
					:</td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:135" name="regTemp"
						list="#{'R':'Regular ','T':'Temporary'}" /></td>


				</tr>
				<tr>
					<td width="25%"><label id="flsa.status" name="flsa.status"
						ondblclick="callShowDiv(this);"><%=label.get("flsa.status")%></label>
					:</td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:135" name="flsaStatus"
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
					<td width="25%"><s:textfield name="customerName" size="25" maxlength="80" /></td>
					<td width="25%"></td>
					<td width="25%"></td>
				</tr>
				<tr>
					<td width="25%" colspan="1"><label class="set"
						name="physical.location.address" id="physical.location.address"
						ondblclick="callShowDiv(this);"><%=label.get("physical.location.address")%></label>
					:<font id='ctrlHide' color="red"></font></td>

					<td><s:textarea name="physicalAddress" cols="28" rows="2" onkeypress="return imposeMaxLength(event, this, 400);"/></td>



				</tr>
				<tr>
					<td width="25%" colspan="1"><label name="city" id="city"
						ondblclick="callShowDiv(this);"><%=label.get("city")%></label> :</td>
					<td width="25%" colspan="1"><s:textfield size="25"
						name="custCityName" maxlength="50" /> </td>

				</tr>
				<tr>

					<td width="25%"><label id="state.prov" name="state.prov"
						ondblclick="callShowDiv(this);"><%=label.get("state.prov")%></label>
					:</td>
					<td width="25%"><s:textfield size="25" name="custStateName"
						 size="25" maxlength="30" /></td>
					<!-- ajax -->
					
					<td width="25%"><label id="zip" name="zip"
						ondblclick="callShowDiv(this);"><%=label.get("zip")%></label> :</td>
					<td width="25%" colspan="1"><s:textfield size="25" maxlength="6" 
						name="custZipCode"  /> <s:hidden name="custZipId" />
					</td>
					
					<!--<td width="25%" colspan="1"><s:textfield size="25" maxlength="6" 
						name="custZipCode" onkeyup="addSalaryPlan();" /> <s:hidden name="custZipId" />
					</td>


				--></tr>
				<tr>
					<td width="25%" colspan="1"><label class="set" name="shift"
						id="shift" ondblclick="callShowDiv(this);"><%=label.get("shift")%></label>
					:</td>

					<td width="25%" colspan="1"><s:textfield size="25"
						name="custShiftType" readonly="true" /> <s:hidden
						name="custShiftCode" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'NewHireRehire_f9custShiftaction.action');"></td>

					<td width="25%"><label id="reg.temp" name="reg.temp"
						ondblclick="callShowDiv(this);"><%=label.get("reg.temp")%></label>
					:</td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:135" name="custRegTemp"
						list="#{'R':'Regular ','T':'Temporary'}" /></td>


				</tr>
				<tr>
					<td width="25%"><label id="flsa.status" name="flsa.status"
						ondblclick="callShowDiv(this);"><%=label.get("flsa.status")%></label>
					:</td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:135"
						name="custflsaStatus" list="#{'E':'Exempt ','N':'Non Exempt'}" /></td>
						
				<td width="25%"><label id="fulltime.parttime"
						name="fulltime.parttime" ondblclick="callShowDiv(this);"><%=label.get("fulltime.parttime")%></label>
					:<font id='ctrlHide' color="red"></font></td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:138"
						name="custfulltimeParttime" list="#{'F':'Full Time ','P':'Part Time'}" /></td>
						
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
				<!--<tr>

					<td width="25%"><label id="salary.plan" name="salary.plan"
						ondblclick="callShowDiv(this);"><%=label.get("salary.plan")%></label>
					:</td>
					<td width="25%"><s:textfield size="25" name="salaryPlan" id="salaryPlan"
						onkeypress="return charactersonly(this)" readonly="true" size="25"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td width="25%"></td>
					<td width="25%"></td>
				</tr>
				--><tr>
					<td width="25%"><label id="pay.group" name="pay.group"
						ondblclick="callShowDiv(this);"><%=label.get("pay.group")%></label>
					:</td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:135" name="payGroup"
						list="#{'BWE':'Salaried Exempt ','BWH':'Hourly', 'NOP':'Canada ','VWF':'Variable WorkForce'}" /></td>

					<td width="25%" colspan="1"><label class="set" name="grade"
						id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
					:<font id='ctrlHide' color="red"></font></td>

					<td width="25%" colspan="1"><s:textfield size="25"
						name="cadreName" readonly="true" /> <s:hidden name="cadreCode" />
					<img src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'NewHireRehire_f9gradeaction.action');"></td>
				</tr>

				<tr>

					<td width="25%"><label id="weekly.hours" name="weekly.hours"
						ondblclick="callShowDiv(this);"><%=label.get("weekly.hours")%></label>
					:</td>
					<td width="25%"><s:textfield size="25" name="weeklyHours"
						size="25" onkeypress="return numbersWithColon();"  maxlength="5" /></td>

					<td width="25%"><label id="biweekly.salary"
						name="biweekly.salary" ondblclick="callShowDiv(this);"><%=label.get("biweekly.salary")%></label>
					: $</td>
					<td width="25%"><s:textfield size="25" name="biweeklySalary"
						id="biweeklySalaryValue" size="25" onkeypress="return numbersWithDot();" 
						onchange="javascript:multiplyNumbers()" maxlength="10" /></td>
				</tr>
				<tr>

					<td width="25%"><label id="annual.salary" name="annual.salary"
						ondblclick="callShowDiv(this);"><%=label.get("annual.salary")%></label>
					:</td>
					<td width="25%"><s:textfield size="25" name="annualSalary" 
						id="annualSalary" size="25" readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
				</tr>
					<tr>
					<td colspan="4">(Biweekly Salary * 26 ) </td>
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
					<td colspan="4">Note:<font color="">Office Mail Location is the City & state of the DecisionOne Office where interoffice mailings should
					be sent. Include the street address if more than one DecisionOne office is in the city.</font></td>
				</tr>
				<tr>
					<td width="25%" colspan="1"><label class="set"
						name="dept.number" id="dept.number"
						ondblclick="callShowDiv(this);"><%=label.get("dept.number")%></label>
					:<font id='ctrlHide' color="red">*</font></td>

					<td width="25%" colspan="1"><s:hidden name="deptCode" /> <s:textfield name="deptName" size="25" readonly="true"/>
					<img src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'NewHireRehire_f9deptNumber.action');"></td>

					
				</tr>
				<tr>
					<td width="25%" colspan="1"><label class="set"
						name="executive" id="executive" ondblclick="callShowDiv(this);"><%=label.get("executive")%></label>
					:<font id='ctrlHide' color="red"></font></td>

					<td width="25%" colspan="1"><s:textfield size="25"
						name="executiveName" readonly="true" /> <s:hidden
						name="executiveCode" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'NewHireRehire_f9rankaction.action');"></td>
				</tr>
				<tr>		
					<td width="25%"><label id="emp.number" name="emp.number"
							ondblclick="callShowDiv(this);"><%=label.get("emp.number")%></label>
						:<font color="red" id='ctrlHide'>*</font></td>
						<td width="25%" colspan="3"><s:textfield name="exeEmployeeToken"
							size="25" theme="simple" readonly="true" cssStyle="background-color: #F2F2F2;"/><s:textfield
							name="exeEmployeeName" size="71" theme="simple" readonly="true" cssStyle="background-color: #F2F2F2;"/>
						<s:hidden name="exeEmployeeCode" />
					
						<img
							src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="16" id='ctrlHide'
							onclick="javascript:callsF9(500,325,'NewHireRehire_f9Employee.action');">
							
							
						</td>
				</tr>
				<tr>
					<td width="25%" colspan="1"><label name="office.city"
						id="office.city" ondblclick="callShowDiv(this);"><%=label.get("office.city")%></label>
					:<font color="red" id='ctrlHide'></font></td>
					<td width="25%" colspan="1"><s:textfield size="25"
						name="officeCityName"  maxlength="50" /></td>

					<td width="25%"><label id="state.prov" name="state.prov"
						ondblclick="callShowDiv(this);"><%=label.get("state.prov")%></label>
					:</td>
					<td width="25%"><s:textfield size="25" name="OfficeStateName"
						  size="25"	maxlength="30" /> </td>

				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td><b><label id="payroll.information" name="payroll.information"
						ondblclick="callShowDiv(this);"><%=label.get("payroll.information")%></label>
					:</b></td>
				</tr>
				<tr>
					<td colspan="4">Note:<br/><font color=""> - Federal exemptions will be set at Single and Zero until an original W4 is received in payroll.<br/>
					- Paychecks will be mailed to the address on file.</font></td>
				</tr>
				<tr>
					<td colspan="4"><br/><font color=""> When you submit this form , it will be automatically sent to HR and Payroll. <br/>
					</font></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg"><!--
				<tr>
					<td><b><label id="form.approval" name="form.approval"
						ondblclick="callShowDiv(this);"><%=label.get("form.approval")%></label>
					:</b></td>
				</tr>
				<tr>
					<td width="20%"><label id="change.approval"
						name="change.approval" ondblclick="callShowDiv(this);"><%=label.get("change.approval")%></label>
					:<font color="red" id='ctrlHide'>*</font></td>
					<td width="60%" colspan="3"><s:textfield name="approverToken"
						size="25" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:textfield
						name="approverName" size="60" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:hidden
						name="approverCode" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'NewHireRehire_f9Approver.action');">
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
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="requestID" />
		<s:hidden name="hireRehireId" />
		<s:hidden name="creditMemoRadio" />
		
		<s:hidden name="listTypeDetailPage" />
		<input type="hidden" name="userProfileRadioOptionValue"
			id="userProfileRadioOptionValue"
			value='<s:property value="userProfileRadioOptionValue"/>' />
	</table>

</s:form>

<script>
radio();
callActionReasonType();
	//global variables
	var req;
	var which;

	function addSalaryPlan(url, formName) {
		var custZipCode = document.getElementById('paraFrm_custZipCode').value;
	
		try {
			url = 'NewHireRehire_addSalaryPlan.action?custZipCode=' + custZipCode + '&' + Math.random();
			
		} catch(e) {
			alert(e);
		}
		if (window.XMLHttpRequest) { // Non-IE browsers
			req = new XMLHttpRequest(); // XMLHttpRequest object is created
		    req.onreadystatechange = processStateChangeForSalaryPlan; // XMLHttpRequest object is configured with a callback function
		    try {
		    	/**
		    	 * open("HTTP method", "URL", syn/asyn), for asyn-true
		    	 * if false, send operations are synchronous, browser doesn't accept any input/output
		    	**/
		    	req.open("GET", url, true);
		    } catch (e) {
				alert("Problem Communicating with Server\n"+e);
			}
			req.send(null);
		} else if (window.ActiveXObject) { // IE 
			req = new ActiveXObject("Microsoft.XMLHTTP");
		    if (req) {
		    	req.onreadystatechange = processStateChangeForSalaryPlan;
		      	req.open("GET", url, true);
		       	req.send();
		    }
		}
	}
	
	function processStateChangeForSalaryPlan() {
			// 0 = uninitialized, 1 = loading, 2 = loaded, 3 = interactive (some data has been returned)!
		if(req.readyState == 4) { // Complete
			if (req.status == 200) { // OK response
		    	//responseXML: XML document of data returned from the server
		    	var res = req.responseText; // String version of data returned from the server
		    	//alert(res);
		    	document.getElementById('salaryPlan').value=res;
		    	
			}
			parent.frames[2].name = 'main';
		}
	}
	
		function chkHyphen( fld, pos ) { 
			for (var i = 0; i < pos.length; i++) {
			if ( pos[i] == fld.value.length ) fld.value += '-';
			}
		};
		function chkHyphenInsurance( fld, pos ) { 
			for (var i = 0; i < pos.length; i++) {
			if ( pos[i] == fld.value.length ) fld.value += '-';
			}
		};

	

	
	function callActionReasonType() {
	
	var actionReason= document.getElementById('paraFrm_actionReason').value;
		
		if(actionReason == 'H' || actionReason == 'R') {
		
			document.getElementById('hire').style.display='';
			
			
		} else {
			document.getElementById('hire').style.display='none';
			
		}
		
		
		if(actionReason == 'A') {
		
			document.getElementById('acquisition').style.display='';
			document.getElementById('hire').style.display='none';
			document.getElementById('rehire').style.display='none';
			
		} else {
			document.getElementById('acquisition').style.display='none';
			
		}
		
		
	}
	function radio()
	{
		 var barGainAgreement=document.getElementById('userProfileRadioOptionValue').value;
	// alert(barGainAgreement);
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
		 }
		  else if(barGainAgreement=='Va')
		 {
		 	 document.getElementById('paraFrm_userProfileVa').checked='Va';
		 	  document.getElementById('workBlock').style.display='';  
		 	  document.getElementById('customerBlock').style.display='none';  
		 	document.getElementById('decisionBlock').style.display='none';
		 } else 
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
	  document.getElementById('paraFrm_userProfileHo').checked='Ho';
	  
	   document.getElementById('workBlock').style.display=''; 
	    document.getElementById('paraFrm_regTemp').value=''; 
	    document.getElementById('paraFrm_shiftType').value=''; 
	    document.getElementById('paraFrm_flsaStatus').value=''; 
	      document.getElementById('paraFrm_fulltimeParttime').value=''; 
	     document.getElementById('decisionBlock').style.display='none';
	     document.getElementById('customerBlock').style.display='none';  
	  }
	  
	   
	  else if(document.getElementById('paraFrm_userProfileTr').checked )
	  { 
	  document.getElementById('paraFrm_userProfileTr').checked='Tr';
	  
	   document.getElementById('workBlock').style.display='';  
	   document.getElementById('paraFrm_regTemp').value='';
	     document.getElementById('paraFrm_shiftType').value=''; 
	    document.getElementById('paraFrm_flsaStatus').value=''; 
	     document.getElementById('paraFrm_fulltimeParttime').value=''; 
	    
	    
	     document.getElementById('decisionBlock').style.display='none';
	     document.getElementById('customerBlock').style.display='none';  
	  }
	  
	  else if(document.getElementById('paraFrm_userProfileVa').checked )
	  { 
	  document.getElementById('paraFrm_userProfileVa').checked='Va';
	  
	   document.getElementById('workBlock').style.display='';  
	   document.getElementById('paraFrm_regTemp').value='';
	     document.getElementById('paraFrm_shiftType').value=''; 
	    document.getElementById('paraFrm_flsaStatus').value=''; 
	      document.getElementById('paraFrm_fulltimeParttime').value=''; 
	     document.getElementById('decisionBlock').style.display='none';
	     document.getElementById('customerBlock').style.display='none';  
	  }
	   return true;
 }
 
 	function callForCustomer()
 	{//alert(22);
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
	      document.getElementById('paraFrm_custfulltimeParttime').value=''; 
	  
	    document.getElementById('salaryPlan').value='GBR';
	  
	   
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
	   
	 
	  document.getElementById('paraFrm_customerName').value='';
	  document.getElementById('paraFrm_physicalAddress').value='';
	  document.getElementById('paraFrm_custCityName').value='';
	  document.getElementById('paraFrm_custStateName').value='';
	  document.getElementById('paraFrm_custZipCode').value='';
	  document.getElementById('paraFrm_custShiftType').value='';
	   document.getElementById('paraFrm_custRegTemp').value='';
	    document.getElementById('paraFrm_custflsaStatus').value='';
	     document.getElementById('salaryPlan').value='GBR';
	  
	   
	    // document.getElementById('paraFrm_userProfile').value='D';
	     
	     
	  }
	    return true;
 	}
 	
 	
 	
function setType(id){	
	var setvalue=document.getElementById('paraFrm_creditMemoRadio').value = id.value;	
}	

function sendforapprovalFun()
{		
		try
		{
			
			
		//document.getElementById('paraFrm_hireStatus').value = 'D';	
		var empFirstName = document.getElementById('paraFrm_empFirstName').value;
		
		
		if(empFirstName=="")
		{
			alert("Please enter "+document.getElementById('emp.first.name').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_empFirstName').focus();
		 	return false;		
		}
		var empLastName = document.getElementById('paraFrm_empLastName').value;
		if(empLastName=="")
		{
			alert("Please enter "+document.getElementById('emp.last.name').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_empLastName').focus();
		 	return false;		
		}
		
		///Added for Email Address 
			var emailAddress = document.getElementById('paraFrm_emailAddress').value;
			
			if(emailAddress =="")
			{
			alert("Please enter Personal Email Address");
			return false;
			}			
			
			
			
			if(!validateEmail('paraFrm_emailAddress'))
   					{   
						return false;
					} 
		
		var hireDate = document.getElementById('paraFrm_hireDate').value;
		if(hireDate=="")
		{
			alert("Please enter "+document.getElementById('hire.date').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_hireDate').focus();
		 	return false;		
		}
		
		if(!document.getElementById('paraFrm_hireDate').value==''){		
			var check1= validateDate('paraFrm_hireDate', 'hire.date');
			if(!check1){
			return false;
		}
			}
			
		var actionReason = document.getElementById('paraFrm_actionReason').value;
		 if(actionReason=="-1")
 {
 alert("Please select Action Reason");
return false; 
 }	
			
			
		var jobCode=document.getElementById('job.code').innerHTML.toLowerCase();
		var jCode = document.getElementById('paraFrm_jobCode').value;
		var actionReason = document.getElementById('paraFrm_actionReason').value;
		if(actionReason=="H"){
			if(jCode==""){
				alert("Please enter "+jobCode);
				document.getElementById('paraFrm_jobCode').focus();
				return false;
			}
		}
		
		var acqDate=document.getElementById('acquisition.date').innerHTML.toLowerCase();
		var aDate = document.getElementById('paraFrm_acquisitionDate').value;
		
		if(actionReason=="A"){
			if(aDate==""){
				alert("Please enter "+acqDate);
				document.getElementById('paraFrm_acquisitionDate').focus();
				return false;
			}
			if(!document.getElementById('paraFrm_acquisitionDate').value==''){		
			var check1= validateDate('paraFrm_acquisitionDate', 'acquisition.date');
			if(!check1){
			return false;
		}
			}
			
		}
		var birthDate=document.getElementById('paraFrm_birthDate').value;
		if(birthDate=="")
		{
			alert("Please enter or select  "+document.getElementById('birth.date').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_birthDate').focus();
		 	return false;		
		}
		if(!document.getElementById('paraFrm_birthDate').value==''){		
			var check1= validateDate('paraFrm_birthDate', 'birth.date');
			if(!check1){
			return false;
		}
			}
		
		var radio=document.getElementById('paraFrm_userProfileHo').checked;
		if(document.getElementById('paraFrm_userProfileHo').value =radio ){
		if(document.getElementById('paraFrm_shiftType').value==""){
 					alert("Please select shift");
 					document.getElementById('paraFrm_shiftType').focus();
 					return false;
 				}
 			}	
 		var radio1=document.getElementById('paraFrm_userProfileCu').checked;
 		//alert(radio1);
		if(document.getElementById('paraFrm_userProfileCu').value =radio1){
		if(document.getElementById('paraFrm_customerName').value==""){
 					alert("Please enter Customer Name");
 					document.getElementById('paraFrm_customerName').focus();
 					return false;
 				}
 			
 				
 				
		}
		var radio2=document.getElementById('paraFrm_userProfileDe').checked;
 		//alert(radio2);
		if(document.getElementById('paraFrm_userProfileDe').value =radio2){
		if(document.getElementById('paraFrm_physicalAddress').value==""){
 					alert("Please enter Physical work location Address");
 					document.getElementById('paraFrm_physicalAddress').focus();
 					return false;
 				}
		}
		
		var radio3=document.getElementById('paraFrm_userProfileVa').checked;
		//alert(radio3);
		if(document.getElementById('paraFrm_userProfileVa').value =radio3){
		if(document.getElementById('paraFrm_shiftType').value==""){
 					alert("Please select shift");
 					document.getElementById('paraFrm_shiftType').focus();
 					return false;
 				}
 			}
		
		
		
		
		
		if(!document.getElementById('biweeklySalaryValue').value==''){
			if(isNaN(document.getElementById('biweeklySalaryValue').value)){
			alert('Please enter BiWeekly Salary as a number');
			document.getElementById('biweeklySalaryValue').focus();
			document.getElementById('biweeklySalaryValue').value='';
			return false;			
			}
			}
		
		var deptName = document.getElementById('paraFrm_deptName').value;
		if(deptName=="")
		{
			alert("Please select "+document.getElementById('dept.number').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_deptName').focus();
		 	return false;		
		}
		
		var sex = document.getElementById('paraFrm_sex').value;
		if(sex=="")
		{
			alert("Please select "+document.getElementById('sex').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_sex').focus();
		 	return false;		
		}
		
		
		var maritalStatus = document.getElementById('paraFrm_maritalStatus').value;
		if(maritalStatus=="")
		{
			alert("Please select "+document.getElementById('marital.status').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_maritalStatus').focus();
		 	return false;		
		}
			var exeEmployeeName = document.getElementById('paraFrm_exeEmployeeName').value;
		if(exeEmployeeName=="")
		{
			alert("Please select "+document.getElementById('emp.number').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_exeEmployeeName').focus();
		 	return false;		
		}
		
	//	if(document.getElementById('paraFrm_approverToken').value==""){
	//		alert("Please select Approval Name");
	//		document.getElementById('paraFrm_approverToken').focus();
  	//		return false;
	//	}
		
		}catch(e)
		{
			alert("Exception occurred in send for approver function."+e);
		}
		
	 var con=confirm('Do you really want to send this application for approval?');
	 if(con)
	 {
		document.getElementById('paraFrm_hireStatus').value = 'P';	
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action='NewHireRehire_sendForApprovalFunction.action';
		document.getElementById('paraFrm').submit();
	}		
}
function draftFun()
{	
	try
	
	{
			
		document.getElementById('paraFrm_hireStatus').value = 'D';	
		var empFirstName = document.getElementById('paraFrm_empFirstName').value;
		
		
		if(empFirstName=="")
		{
			alert("Please enter "+document.getElementById('emp.first.name').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_empFirstName').focus();
		 	return false;		
		}
		var empLastName = document.getElementById('paraFrm_empLastName').value;
		if(empLastName=="")
		{
			alert("Please enter "+document.getElementById('emp.last.name').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_empLastName').focus();
		 	return false;		
		}
		
		///Added for Email Address 
			var emailAddress = document.getElementById('paraFrm_emailAddress').value;
			
			if(emailAddress =="")
			{
			alert("Please enter Personal Email Address");
			return false;
			}
			
			if(!validateEmail('paraFrm_emailAddress'))
   					{   
						return false;
					} 
		
		var hireDate = document.getElementById('paraFrm_hireDate').value;
		if(hireDate=="")
		{
			alert("Please enter "+document.getElementById('hire.date').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_hireDate').focus();
		 	return false;		
		}
		if(!document.getElementById('paraFrm_hireDate').value==''){		
			var check1= validateDate('paraFrm_hireDate', 'hire.date');
			if(!check1){
			return false;
		}
			}
		
		var actionReason = document.getElementById('paraFrm_actionReason').value;
		 if(actionReason=="-1")
 {
 alert("Please select Action Reason");
return false; 
 }
		
		
		
						
		var jobCode=document.getElementById('job.code').innerHTML.toLowerCase();
		var jCode = document.getElementById('paraFrm_jobCode').value;
		var actionReason = document.getElementById('paraFrm_actionReason').value;
		if(actionReason=="H"){
			if(jCode==""){
				alert("Please enter "+jobCode);
				document.getElementById('paraFrm_jobCode').focus();
				return false;
			}
		}
		
		var acqDate=document.getElementById('acquisition.date').innerHTML.toLowerCase();
		var aDate = document.getElementById('paraFrm_acquisitionDate').value;
		
		if(actionReason=="A"){
			if(aDate==""){
				alert("Please enter "+acqDate);
				document.getElementById('paraFrm_acquisitionDate').focus();
				return false;
			}
			if(!document.getElementById('paraFrm_acquisitionDate').value==''){		
			var check1= validateDate('paraFrm_acquisitionDate', 'acquisition.date');
			if(!check1){
			return false;
		}
			}
		}
		var birthDate=document.getElementById('paraFrm_birthDate').value;
		if(birthDate=="")
		{
			alert("Please enter or select "+document.getElementById('birth.date').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_birthDate').focus();
		 	return false;		
		}
		
		if(!document.getElementById('paraFrm_birthDate').value==''){		
			var check1= validateDate('paraFrm_birthDate', 'birth.date');
			if(!check1){
			return false;
		}
			}
		
		var radio=document.getElementById('paraFrm_userProfileHo').checked;
		if(document.getElementById('paraFrm_userProfileHo').value =radio ){
		if(document.getElementById('paraFrm_shiftType').value==""){
 					alert("Please select shift");
 					document.getElementById('paraFrm_shiftType').focus();
 					return false;
 				}
 			}	
 		var radio1=document.getElementById('paraFrm_userProfileCu').checked;
 	//	alert(radio1);
		if(document.getElementById('paraFrm_userProfileCu').value =radio1){
		if(document.getElementById('paraFrm_customerName').value==""){
 					alert("Please enter Customer Name");
 					document.getElementById('paraFrm_customerName').focus();
 					return false;
 				}
 			
 				
		}
		var radio2=document.getElementById('paraFrm_userProfileDe').checked;
 		//alert(radio2);
		if(document.getElementById('paraFrm_userProfileDe').value =radio2){
		if(document.getElementById('paraFrm_physicalAddress').value==""){
 					alert("Please enter Physical work location Address");
 					document.getElementById('paraFrm_physicalAddress').focus();
 					return false;
 				}
		}
		
		var radio3=document.getElementById('paraFrm_userProfileVa').checked;
		//alert(radio3);
		if(document.getElementById('paraFrm_userProfileVa').value =radio3){
		if(document.getElementById('paraFrm_shiftType').value==""){
 					alert("Please select shift");
 					document.getElementById('paraFrm_shiftType').focus();
 					return false;
 				}
 			}
		
		
		
		
		
		if(!document.getElementById('biweeklySalaryValue').value==''){
			if(isNaN(document.getElementById('biweeklySalaryValue').value)){
			alert('Please enter BiWeekly Salary as a number');
			document.getElementById('biweeklySalaryValue').focus();
			document.getElementById('biweeklySalaryValue').value='';
			return false;			
			}
			}
			
			
			var deptName = document.getElementById('paraFrm_deptName').value;
		if(deptName=="")
		{
			alert("Please select "+document.getElementById('dept.number').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_deptName').focus();
		 	return false;		
		}
			
			
			var sex = document.getElementById('paraFrm_sex').value;
		if(sex=="")
		{
			alert("Please select "+document.getElementById('sex').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_sex').focus();
		 	return false;		
		}
		
		var maritalStatus = document.getElementById('paraFrm_maritalStatus').value;
		if(maritalStatus=="")
		{
			alert("Please select "+document.getElementById('marital.status').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_maritalStatus').focus();
		 	return false;		
		}
		
		var exeEmployeeName = document.getElementById('paraFrm_exeEmployeeName').value;
		if(exeEmployeeName=="")
		{
			alert("Please select "+document.getElementById('emp.number').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_exeEmployeeName').focus();
		 	return false;		
		}
		
	
		
		//if(document.getElementById('paraFrm_approverToken').value==""){
		//	alert("Please select Approval Name");
		//	document.getElementById('paraFrm_approverToken').focus();
  		//	return false;
		//}
		
	}catch(e)
	{
		alert("Exception occured in draft function : "+e);
	}
		
				
		// For disabaling the button after clicking once	
		for (var i = 0; i < document.all.length; i++) 
		{
			if(document.all[i].id == 'save') 
			{
				//alert(document.all[i]);
				document.all[i].disabled=true;
			}
		}
				
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action='NewHireRehire_draftFunction.action';
	  	document.getElementById('paraFrm').submit();		
		  
}


function resetFun() 
{
		document.getElementById('paraFrm').target = "_self";     	
  		document.getElementById('paraFrm').action = 'NewHireRehire_reset.action';
     	document.getElementById('paraFrm').submit();
}

function backtolistFun() 
{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'NewHireRehire_back.action';
		document.getElementById('paraFrm').submit();
}
function printFun() {	
	window.print();
	}

function deleteFun() 
{
	 var con=confirm('Do you want to delete this application ?');
	 if(con)
	 {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'NewHireRehire_delete.action';
		document.getElementById('paraFrm').submit();
	}
}

function cancelapplicationFun() 
{
	 var con=confirm('Do you want to cancel this application ?');
	 if(con)
	 {
		document.getElementById('paraFrm_hireStatus').value = 'C';	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'NewHireRehire_cancel.action';
		document.getElementById('paraFrm').submit();
	}
}
function multiplyNumbers(){
                {
                        var val1 = parseFloat(document.getElementById("biweeklySalaryValue").value);
                      var ansD = document.getElementById("annualSalary");
                        ansD.value =Math.round(26*val1*Math.pow(10,2))/Math.pow(10,2);;
                }
            }    
      
	function imposeMaxLength(Event, Object, MaxLen)
{
        return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
}		
		
</script>