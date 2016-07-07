<!-- Added by ganesh -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="PersonalRequisition" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="trackingNo" />
	<s:hidden name="persReqStatus" />
	<s:hidden name="listType" />
	
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="myPageInProcess" id="myPageInProcess" />
	<s:hidden name="myPageSentBack" id="myPageSentBack" />
	
	<s:hidden name="myPageApproved" id="myPageApproved" />
	<s:hidden name="myPageApprovedCancel" id="myPageApprovedCancel" />
	
	<s:hidden name="myPageCancel" id="myPageCancel" />
	
	<s:hidden name="myPageRejected" id="myPageRejected" />
	<s:hidden name="myPageCancelRejected" id="myPageCancelRejected" />
	
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
					<td width="93%" class="txt"><strong class="text_head">Personnel
					Requisition Form </strong></td>
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
				<tr >
					<td colspan="4" ><font style="font-size: 1.0em;"><b>Instructions
					: </b> <br />
					1. Hiring Manager Act as initiator. Complete form with all relevant information. </font></td>
				</tr>
				<tr>
					<td colspan="4" ><font style="font-size: 1.0em;">
					2. Hiring Manager select next approver Name in the next approver Field with Forwarded To manager & </br> &nbsp;&nbsp;&nbsp;click on forward button to forward request to next approver</font></td>
				</tr>
				<tr>
					<td colspan="4" ><font style="font-size: 1.0em;">
					3. Next approver again selects next approver & forwards it for approval or click 'Authorized-Signoff 'approve request.</font></td>
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
						name="requisition.infomation" id="requisition.infomation"
						ondblclick="callShowDiv(this);"><%=label.get("requisition.infomation")%></label></b>
					</td>
				</tr>
				<tr>
					<td><label class="set" name="applicationDate"
						id="applicationDate" ondblclick="callShowDiv(this);"><%=label.get("applicationDate")%>
					</label> :</td>
					<td><s:textfield name="applicationDate"
						id="paraFrm_applicationDate" theme="simple" size="15"
						maxlength="10"
						onblur="return validateDate('paraFrm_applicationDate','Date');"
						onkeypress="return numbersWithHiphen();" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td width="25%"><label id="status" name="status"
						ondblclick="callShowDiv(this);"><%=label.get("status")%></label> :</td>
					<td width="25%"><s:select disabled="true" cssStyle="width:145"
						name="persReqStatus"
						list="#{'D':'Draft','P':'Pending','B':'Sent Back','A':'Approved','R':'Rejected',
						'N':'Cancelled','F':'Forwarded','C':'Applied For Cancellation','X':'Cancellation Approved','Z':'Cancellation Rejected'}" />

					</td>
				</tr>

				<tr>
					<td width="25%" colspan="1"><label name="position.title"
						id="position.title" ondblclick="callShowDiv(this);"><%=label.get("position.title")%></label>
					:<font color="red" id='ctrlHide'>*</font></td>
					<td width="25%" colspan="1"><s:textfield size="25" maxlength="50"
						name="positionTitleName" /></td>


					<td width="25%"><label id="requisition" name="requisition"
						ondblclick="callShowDiv(this);"><%=label.get("requisition")%></label>
					:<font color="red" id='ctrlHide'>*</font></td>
					<td width="25%"><s:textfield name="requisition" size="25" maxlength="5" onkeypress="return numbersOnly();"/></td>
				</tr>

				<tr>
					<td width="25%" colspan="1"><label name="band" id="band"
						ondblclick="callShowDiv(this);"><%=label.get("band")%></label> :<font
						color="red" id='ctrlHide'>*</font></td>
					<td width="25%" colspan="1"><s:textfield size="25"
						name="bandName" readonly="true" /> <s:hidden name="bandId" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'PersonalRequisition_f9band.action');"></td>

				</tr>


				<tr>
					<td width="25%"><label id="max.salary" name="max.salary"
						ondblclick="callShowDiv(this);"><%=label.get("max.salary")%></label>
					:</td>
					<td width="25%"><s:textfield name="maxSalary" size="25" maxlength="10"
						onkeypress="return numbersWithDot();" 
						/></td>

					<td width="25%">
						<label id="position.date" name="position.date" ondblclick="callShowDiv(this);"><%=label.get("position.date")%></label> 
							<br>(DD-MM-YYYY) :<font color="red" id="ctrlHide">*</font>
					</td>

					<td width="25%" colspan="3" align="left"><s:textfield
						name="positionDate" size="25"
						onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_positionDate','dd-mm-yyyy');" /> <s:a
						href="javascript:NewCal('paraFrm_positionDate','DDMMYYYY');">
						<img src="../pages/common/css/default/images/Date.gif" width="16"
							height="16" border="0" id='ctrlHide' />
					</s:a></td>

				</tr>
				<tr>
					<td width="25%" colspan="1"><label class="set"
						name="dept.number" id="dept.number"
						ondblclick="callShowDiv(this);"><%=label.get("dept.number")%></label>
					:<font id='ctrlHide' color="red">*</font></td>

					<td width="25%" colspan="1"><s:hidden
						name="deptCode"  /> <s:textfield name="deptName" size="25" readonly="true"/>
					<img src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'PersonalRequisition_f9deptNumber.action');"></td>

					<td width="25%" colspan="1"><label class="set"
						name="executive" id="executive" ondblclick="callShowDiv(this);"><%=label.get("executive")%></label>
					:<font id='ctrlHide' color="red"></font></td>

					<td width="25%" colspan="1"><s:textfield size="25"
						name="executiveName" maxlength="200"/> </td>
				</tr>



				<tr>
					<td width="25%"><label id="work.location" name="work.location"
						ondblclick="callShowDiv(this);"><%=label.get("work.location")%></label>
					:</td>
					<td width="25%"><s:textfield name="workLocation" size="25" maxlength="100"/></td>
				</tr>
				<tr>
					<td width="25%"><label id="hiring.manager"
						name="hiring.manager" ondblclick="callShowDiv(this);"><%=label.get("hiring.manager")%></label>
					:</td>
					<td width="60%" colspan="3"><s:textfield
						name="hiringManagerToken" size="25" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:textfield
						name="hiringManagerName" size="60" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:hidden
						name="hiringManagerCode" /><img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'PersonalRequisition_f9Employee.action');"></td>
				</tr>
				<tr>
					<td width="25%"><label id="hiring.manager.phone.number"
						name="hiring.manager.phone.number" ondblclick="callShowDiv(this);"><%=label.get("hiring.manager.phone.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="hiringManagerPhoneNumber"
						size="25" maxlength="15"/></td>


					<td width="25%"><label id="hiring.manager.fax.number"
						name="hiring.manager.fax.number" ondblclick="callShowDiv(this);"><%=label.get("hiring.manager.fax.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="hiringManagerFaxNumber" maxlength="15"
						size="25" /></td>
				</tr>

				<tr>
					<td width="25%"><label id="hiring.manager.email.address"
						name="hiring.manager.email.address"
						ondblclick="callShowDiv(this);"><%=label.get("hiring.manager.email.address")%></label>
					:</td>
					<td width="25%"><s:textfield name="hiringManagerEmailAddress"
						size="25"  maxlength="150"/></td>
				</tr>
				<tr>
					<td><label class="set" name="approved.existing.job"
						id="approved.existing.job" ondblclick="callShowDiv(this);"><%=label.get("approved.existing.job")%></label><font
						color="red"></font> :</td>

					<td><s:select headerKey="" headerValue="--Select--"
						cssStyle="width:135" name="approvedExistingJob"
						list="#{'Y':'Yes','N':'No'}" /></td>

					<td><label class="set" name="new.job.exist" id="new.job.exist"
						ondblclick="callShowDiv(this);"><%=label.get("new.job.exist")%></label><font
						color="red"></font> :</td>

					<td><s:select headerKey="" headerValue="--Select--"
						cssStyle="width:135" name="newJobExist"
						list="#{'Y':'Yes','N':'No'}" /></td>
				</tr>
				<tr>
					<td><label class="set" name="head.count" id="head.count"
						ondblclick="callShowDiv(this);"><%=label.get("head.count")%></label><font
						color="red"></font> :</td>

					<td><s:select headerKey="" headerValue="--Select--"
						cssStyle="width:135" name="headCount" list="#{'Y':'Yes','N':'No'}" /></td>

					<td><label class="set" name="replacement" id="replacement"
						ondblclick="callShowDiv(this);"><%=label.get("replacement")%></label><font
						color="red"></font> :</td>

					<td><s:select headerKey="" headerValue="--Select--"
						cssStyle="width:135" name="replacementType"
						list="#{'Y':'Yes','N':'No'}"
						onchange="return callReplacementType();" /></td>
				</tr>

				<tr id="replacementProcess">
				<td colspan="4">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1" class="">
				<tr>
					<td width="20%" colspan="1"><label id="req.replacing" name="req.replacing"
						ondblclick="callShowDiv(this);"><%=label.get("req.replacing")%></label>
					:<font id='ctrlHide' color="red">*</font></td>
					<!--<td width="25%"><s:textfield name="reqReplacing" size="25" maxlength="50"/></td>

					-->
						
					<td width="60%" colspan="3" ><s:textfield name="reqReplacingToken"
							size="25" theme="simple" readonly="true" cssStyle="background-color: #F2F2F2;"/><s:textfield
							name="reqReplacingName" size="60" theme="simple" readonly="true" cssStyle="background-color: #F2F2F2;"/>
						<s:hidden name="reqReplacingCode" />
					
						<img
							src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="16" id='ctrlHide'
							onclick="javascript:callsF9(500,325,'PersonalRequisition_f9ReqEmployee.action');">
						
							
						</td>
						
					</tr>
					<tr>
					
					
					<td width="20%" colspan="1"><label class="set"
						name="termination.date" id="termination.date"
						ondblclick="callShowDiv(this);"><%=label.get("termination.date")%></label>
					:<font id='ctrlHide' color="red">*</font></td>

					<td width="25%" colspan="3" align="left"><s:textfield
						name="terminationDate" size="25"
						onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_terminationDate','dd-mm-yyyy');" /> <s:a
						href="javascript:NewCal('paraFrm_terminationDate','DDMMYYYY');">
						<img src="../pages/common/css/default/images/Date.gif" width="16"
							height="16" border="0" id='ctrlHide' />
					</s:a></td>
					
					
				</tr>
				</table>
				</td>
				</tr>
			
				<tr>
					<td width="25%" colspan="1"><label name="budget" id="budget"
						ondblclick="callShowDiv(this);"><%=label.get("budget")%></label> :</td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:138" name="budget"
						list="#{'Y':'Yes ','N':'No'}" /></td>
					<td width="25%"></td>
					<td width="25%"></td>
				</tr>
				<tr>
					<td class="formtext" width="15%"><label name="position.type"
						id="position.type" ondblclick="callShowDiv(this);"> <%=label.get("position.type")%>
					</label></td>
					<td height="22" width="25%" colspan="3"><s:radio
						name="positionType" value="%{positionType}"
						list="#{'Re':' Regular &nbsp;'}"
						onclick="setUserProfileRadioValue(this);callForRegular();">
					</s:radio> <s:radio name="positionType" value="%{positionType}"
						list="#{'Te':'Temporary &nbsp;'}"
						onclick="setUserProfileRadioValue(this);callForTemporary();">
					</s:radio> <s:radio name="positionType" value="%{positionType}"
						list="#{'Va':'Variable WorkForce &nbsp;'}"
						onclick="setUserProfileRadioValue(this);callForVariableWorkForce();">
					</s:radio></td>


				</tr>
				<tr id="decisionBlock">
					<td colspan="4">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1" class="formbg">
						<tr>
							<td colspan="3"><label><b>Temporary</b> </label> :</td>

						</tr>

						<tr>
							<td width="25%"><label id="type.temporary"
								name="type.temporary" ondblclick="callShowDiv(this);"><%=label.get("type.temporary")%></label>
							:<font id='ctrlHide' color="red">*</font></td>
							<td width="25%"><s:select headerKey=""
								headerValue="--Select--" cssStyle="width:138"
								name="temporaryType" list="#{'A':'Agency ','D':'DecisionOne'}"
								onchange="return callTemporaryType();" /></td>
						</tr>

						<tr id="customerBlock">
							<td colspan="4">
							<table width="100%" border="0" align="center" cellpadding="2"
								cellspacing="1" class="">
								<tr>
									<td width="25%" colspan="1"><label name="agency.name"
										id="agency.name" ondblclick="callShowDiv(this);"><%=label.get("agency.name")%></label>
									:<font id='ctrlHide' color="red">*</font></td>
									<td width="25%" colspan="1"><s:textfield size="25"
										name="agencyName" maxlength="50"/></td>
									<td width="25%" colspan="1"><label name="contractor.name"
										id="contractor.name" ondblclick="callShowDiv(this);"><%=label.get("contractor.name")%></label>
									:<font id='ctrlHide' color="red">*</font></td>
									<td width="25%" colspan="1"><s:textfield size="25"
										name="contractorName" maxlength="50"/></td>
								</tr>
								<tr>
									<td width="25%" colspan="1"><label
										name="agency.phone.number" id="agency.phone.number"
										ondblclick="callShowDiv(this);"><%=label.get("agency.phone.number")%></label>
									:<font id='ctrlHide' color="red">*</font></td>
									<td width="25%" colspan="1"><s:textfield size="25" maxlength="15"
										name="contractorPhoneNumber" /></td>
									<td width="25%" colspan="1"><label
										name="contractor.email.address" id="contractor.email.address"
										ondblclick="callShowDiv(this);"><%=label.get("contractor.email.address")%></label>
									:<font id='ctrlHide' color="red">*</font></td>
									<td width="25%" colspan="1"><s:textfield size="25"
										name="contractorEmailAddress" /></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td width="25%" colspan="1"><label class="set"
								name="maximum.bill.rate" id="maximum.bill.rate"
								ondblclick="callShowDiv(this);"><%=label.get("maximum.bill.rate")%></label>
							:<font id='ctrlHide' color="red">*</font></td>

							<td><s:textfield name="maximumBillRate" size="25"
								onkeypress="return numbersOnly();" maxlength="4"/></td>

							<td width="25%"><label id="overtime.required"
								name="overtime.required" ondblclick="callShowDiv(this);"><%=label.get("overtime.required")%></label>
							:</td>
							<td width="25%"><s:select headerKey=""
								headerValue="--Select--" cssStyle="width:138"
								name="overtimeRequired" list="#{'Y':'Yes ','N':'No'}" /></td>

						</tr>

						<tr>

							<td width="25%"><label id="number.of.overtime"
								name="number.of.overtime" ondblclick="callShowDiv(this);"><%=label.get("number.of.overtime")%></label>
							:</td>
							<td width="25%"><s:textfield size="25"
								name="numberOfOvertime" onkeypress="return numbersOnly();" maxlength="4"/></td>

							<td width="25%"><label id="duration.of.assignment"
								name="duration.of.assignment" ondblclick="callShowDiv(this);"><%=label.get("duration.of.assignment")%></label>
							:</td>
							<td width="25%"><s:textfield size="25"
								name="durationOfAssignment" onkeypress="return numbersOnly();" maxlength="4"/></td>

						</tr>
						<tr>
							<td width="25%" colspan="1"><label class="set"
								name="reason.for.temporary.need" id="reason.for.temporary.need"
								ondblclick="callShowDiv(this);"><%=label.get("reason.for.temporary.need")%></label>
							:<font id='ctrlHide' color="red">*</font></td>

							<td colspan="2" width="65%" ><s:textarea name="reasonForTemporaryNeed" cols="70"
								rows="2" onkeypress="return imposeMaxLength(event, this, 400);"/><img
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						id='ctrlHide' width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_reasonForTemporaryNeed','reason.for.temporary.need','','400','400');"></td></td>
							

						</tr>

					</table>
					</td>
				</tr>
				<tr id="variableWorkforce">
					<td colspan="4">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1" class="formbg">
						<tr>
							<td colspan="4"><label><b>Variable workfroce</b> </label> :</td>

						</tr>


						<tr>
							<td colspan="4"><label>The Variable workfroce is
							engaged to supply on demand service on per call,piece of work or
							Hourly basis </label> :</td>

						</tr>


						<tr>
							<td width="25%" colspan="1"><label class="set"
								name="variable.workfroce.rate" id="variable.workfroce.rate"
								ondblclick="callShowDiv(this);"><%=label.get("variable.workfroce.rate")%></label>
							:<font id='ctrlHide' color="red">*</font></td>

							<td  colspan="1"><s:textfield name="variableWorkfroceRate" size="25"
								onkeypress="return numbersOnly();" maxlength="4"/></td>
							<td width="25%"  colspan="1"></td>
							<td width="25%"></td>
						</tr>

						<tr>
							<td class="formtext" width="15%"  colspan="1"><label name="rate.type"
								id="rate.type" ondblclick="callShowDiv(this);"> <%=label.get("rate.type")%>
							</label></td>
							<td height="22" width="25%" colspan="3"><s:radio
								name="rateType" value="%{rateType}"
								list="#{'Pc':' Per Call &nbsp;'}"
								onclick="setRateTypeRadioValue(this);">
							</s:radio> <s:radio name="rateType" value="%{rateType}"
								list="#{'Pp':'Per Piece &nbsp;'}"
								onclick="setRateTypeRadioValue(this);">
							</s:radio> <s:radio name="rateType" value="%{rateType}"
								list="#{'Ph':'Per Hour &nbsp;'}"
								onclick="setRateTypeRadioValue(this);">
							</s:radio></td>
							<td width="25%"></td>
							<td width="25%"></td>

						</tr>

						<tr>

							<td width="25%"  colspan="1"><label id="duration.of.assignment"
								name="duration.of.assignment" ondblclick="callShowDiv(this);"><%=label.get("duration.of.assignment")%></label>
							:</td>
							<td width="25%"  colspan="1"><s:textfield size="25" onkeypress="return numbersOnly();"
								name="durationOfVariableAssignment" maxlength="4"/></td>
							<td width="25%"  colspan="1"></td>
							<td width="25%"  colspan="1"></td>
						</tr>
						<tr>
							<td width="25%" colspan="1"><label class="set"
								name="reason.for.variable.workforce.need"
								id="reason.for.variable.workforce.need"
								ondblclick="callShowDiv(this);"><%=label.get("reason.for.variable.workforce.need")%></label>
							:<font id='ctrlHide' color="red">*</font></td>

							<td  colspan="2"><s:textarea name="reasonForVariableWorkforceNeed"
								cols="75" rows="2" onkeypress="return imposeMaxLength(event, this, 400);"/><img
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						id='ctrlHide' width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_reasonForVariableWorkforceNeed','reason.for.variable.workforce.need','','400','400');"></td></td>
							
							
						</tr>

					</table>
					</td>
				</tr>
				

			</table>
			</td>
		</tr>

		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td><b><label id="position.requirement"
						name="position.requirement" ondblclick="callShowDiv(this);"><%=label.get("position.requirement")%></label>
					</b></td>
				</tr>
				<tr>
					<td width="25%"><label id="std.hour.per.week"
						name="std.hour.per.week" ondblclick="callShowDiv(this);"><%=label.get("std.hour.per.week")%></label>
					:<font id='ctrlHide' color="red">*</font></td>
					<td width="25%"><s:textfield name="stdHourPerWeek" size="15"
						onkeypress="return numbersWithColon();"  maxlength="5"/>(HH24:MM)</td>

					<td width="25%"><label id="fulltime.parttime"
						name="fulltime.parttime" ondblclick="callShowDiv(this);"><%=label.get("fulltime.parttime")%></label>
					:<font id='ctrlHide' color="red">*</font></td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:138"
						name="fulltimeParttime" list="#{'F':'Full Time ','P':'Part Time'}" /></td>

				</tr>
				<tr>
					<td width="25%"><label id="education.requirements"
						name="education.requirements" ondblclick="callShowDiv(this);"><%=label.get("education.requirements")%></label>
					:<font id='ctrlHide' color="red">*</font></td>
					<td width="25%"><s:textfield name="educationRequirements"
						size="25" maxlength="50"/></td>

					<td width="25%"><label id="experience.requirement"
						name="experience.requirement" ondblclick="callShowDiv(this);"><%=label.get("experience.requirement")%></label>
					:<font id='ctrlHide' color="red">*</font></td>
					<td width="25%"><s:textfield name="experienceRequirement"
						size="25"  maxlength="10"/></td>

				</tr>
				<tr>
					<td width="25%"><label id="essential.position.requirements"
						name="essential.position.requirements"
						ondblclick="callShowDiv(this);"><%=label.get("essential.position.requirements")%></label>
					:<font id='ctrlHide' color="red">*</font></td>
					<td width="25%"><s:textfield
						name="essentialPositionRequirements" size="25"
						 maxlength="45"/></td>

					<td width="25%"></td>
					<td width="25%"></td>

				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="3"><b><label id="justification" name="justification"
						ondblclick="callShowDiv(this);"><%=label.get("justification")%></label>
					</b></td>
				</tr>

				<tr>
					<td width="25%"><label id="reason" name="reason"
						ondblclick="callShowDiv(this);"><%=label.get("reason")%></label> :</td>
					<td colspan="2"><s:textarea name="reason" cols="100" rows="2" onkeypress="return imposeMaxLength(event, this, 400);"/><img
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						id='ctrlHide' width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_reason','reason','','400','400');"></td>

				
				

				</tr>

			</table>
			</td>
		</tr>


		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				
				<tr>
					<td><b><label id="form.approval" name="form.approval"
						ondblclick="callShowDiv(this);"><%=label.get("form.approval")%></label>
					:</b></td>
				</tr>
				<tr>
					<td colspan="4" ><b>Note : </b>Please
					Follow the Following Reporting Structure : <br />
					 Director >> Resource Manager >> Vice President >>
					Senior Vice President >> Human Resource >> Finance >> President<br></td>
				</tr>
				<s:if test="cancellationFlag">
					<tr>
						<td width="20%"><label id="change.approval"
							name="change.approval" ondblclick="callShowDiv(this);"><%=label.get("change.approval")%></label>
						:<font color="red" id='ctrlHide'>*</font></td>
						<td width="60%" colspan="3" id="ctrlShow"><s:textfield
							name="approverToken" size="25" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" /> <s:textfield
							name="approverName" size="60" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" /> <s:hidden
							name="approverCode" /> <img
							src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="16" id="ctrlShow"
							onclick="javascript:callsF9(500,325,'PersonalRequisition_f9Approver.action');">
						</td>
					</tr>
					<tr>
						<td width="25%"><label id="forwarded.for.approvar"
							name="forwarded.for.approvar" ondblclick="callShowDiv(this);"><%=label.get("forwarded.for.approvar")%></label>
						:</td>
						<td width="25%" id="ctrlShow"><s:select headerKey="Hm"
							id="forwardedForApprovar" cssStyle="width:138"
							name="forwardedForApprovar" list="#{'Di':'Director '}" /></td>
						<td width="25%"></td>
						<td width="25%"></td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td width="20%"><label id="change.approval"
							name="change.approval" ondblclick="callShowDiv(this);"><%=label.get("change.approval")%></label>
						:<font color="red" id='ctrlHide'>*</font></td>
						<td width="60%" colspan="3"><s:textfield
							name="approverToken" size="25" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" /> <s:textfield
							name="approverName" size="60" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" /> <s:hidden
							name="approverCode" /> <img
							src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="16" id="ctrlHide"
							onclick="javascript:callsF9(500,325,'PersonalRequisition_f9Approver.action');">
						</td>
					</tr>
					<tr>
						<td width="25%"><label id="forwarded.for.approvar"
							name="forwarded.for.approvar" ondblclick="callShowDiv(this);"><%=label.get("forwarded.for.approvar")%></label>
						:</td>
						<td width="25%"><s:select headerKey=""
							headerValue="--Select--" cssStyle="width:138"
							name="forwardedForApprovar"
							list="#{'Di':'Director ','Rm':'Resource Manager','Vp':'Vice President','Sv':' Senior Vice President','Hr':' Human Resource ','F':' Finance ','Pr':' President'}" /></td>
						<td width="25%"></td>
						<td width="25%"></td>
					</tr>
				</s:else>
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
		<s:hidden name="creditMemoRadio" />


		
		<s:hidden name="listTypeDetailPage" />
		<input type="hidden" name="userProfileRadioOptionValue"
			id="userProfileRadioOptionValue"
			value='<s:property value="userProfileRadioOptionValue"/>' />
		<input type="hidden" name="rateTypeRadioOptionValue"
			id="rateTypeRadioOptionValue"
			value='<s:property value="rateTypeRadioOptionValue"/>' />
	</table>

</s:form>

<script>
	radio()
callReplacementType();
callTemporaryType();
callForVariableWorkForce();
	function callReplacementType() {
	
	var actionReason= document.getElementById('paraFrm_replacementType').value;
		
		if(actionReason == 'Y' ) {
		
			document.getElementById('replacementProcess').style.display='';
			
			
			
		} else {
			document.getElementById('replacementProcess').style.display='none';
			
			document.getElementById('paraFrm_reqReplacingCode').value='';
			document.getElementById('paraFrm_reqReplacingToken').value='';
			document.getElementById('paraFrm_reqReplacingName').value='';
			document.getElementById('paraFrm_terminationDate').value='';
			
		}
		
		
		
	}
	function callTemporaryType()
	{
		var actionReason= document.getElementById('paraFrm_temporaryType').value;
		
		if(actionReason == 'A' ) {
		
			document.getElementById('customerBlock').style.display='';
			
			
		} else {
			document.getElementById('customerBlock').style.display='none';
			
		}
	}
	
	
		function radio()
	{
		 var barGainAgreement=document.getElementById('userProfileRadioOptionValue').value;
	// alert(barGainAgreement);
	  if(barGainAgreement=='Re')
		 {//alert(barGainAgreement);
		 	 document.getElementById('paraFrm_positionTypeRe').checked='Re';
		 	 
		 	 document.getElementById('decisionBlock').style.display='none';
		 	 document.getElementById('variableWorkforce').style.display='none';
		 	   
		 }
		else if(barGainAgreement=='Te')
		 {
		 	 document.getElementById('paraFrm_positionTypeTe').checked='Te';
		 	  document.getElementById('customerBlock').style.display='none';  
		 	  document.getElementById('decisionBlock').style.display='';  
			document.getElementById('variableWorkforce').style.display='none';
		 }
		
		  else if(barGainAgreement=='Va')
		 {
		 	 document.getElementById('paraFrm_positionTypeVa').checked='Va';
		 	 document.getElementById('variableWorkforce').style.display='';
		 	  document.getElementById('customerBlock').style.display='none';  
		 	document.getElementById('decisionBlock').style.display='none';
		 } else 
		 {
		 	//document.getElementById('workBlock').style.display='none';  
		 ///	document.getElementById('customerBlock').style.display='none';  
		 	document.getElementById('decisionBlock').style.display='none';
		 	document.getElementById('variableWorkforce').style.display='none';
		 	document.getElementById('paraFrm_positionTypeRe').checked='Re';
		 }
		
	}
	
	function setUserProfileRadioValue(id){
	//alert(id.value);
		document.getElementById('userProfileRadioOptionValue').value =id.value;	
}
function setRateTypeRadioValue(id){
	//alert(id);
		document.getElementById('rateTypeRadioOptionValue').value =id.value;	
}

 
 	function callForTemporary()
 	{
 		if(document.getElementById('paraFrm_positionTypeTe').checked )
	  { 
	  document.getElementById('paraFrm_positionTypeTe').checked='Te';
	    document.getElementById('decisionBlock').style.display='';
	  
	  document.getElementById('paraFrm_variableWorkfroceRate').value='';
	  document.getElementById('rateTypeRadioOptionValue').value='';
	  document.getElementById('paraFrm_durationOfVariableAssignment').value='';
	  document.getElementById('paraFrm_reasonForVariableWorkforceNeed').value='';
		 
	 
	  document.getElementById('variableWorkforce').style.display='none';
	  document.getElementById('workBlock').style.display='none';  
	   
	     //document.getElementById('paraFrm_userProfile').value='C';
	     
	  }
	   return true;
 	}
 	
 	
 	function callForVariableWorkForce()
	{
 		if(document.getElementById('paraFrm_positionTypeVa').checked )
	  { 
	  document.getElementById('paraFrm_positionTypeVa').checked='Va';
		 
	   document.getElementById('variableWorkforce').style.display='';
	   
	   document.getElementById('paraFrm_temporaryType').value='';
	  document.getElementById('paraFrm_agencyName').value='';
	  document.getElementById('paraFrm_contractorName').value='';
	  document.getElementById('paraFrm_contractorPhoneNumber').value='';
	  document.getElementById('paraFrm_contractorEmailAddress').value='';
	  document.getElementById('paraFrm_maximumBillRate').value='';
	   document.getElementById('paraFrm_overtimeRequired').value='';
	    document.getElementById('paraFrm_numberOfOvertime').value='';
	  
	  document.getElementById('paraFrm_durationOfAssignment').value='';
	    document.getElementById('paraFrm_reasonForTemporaryNeed').value='';
	    
	  document.getElementById('decisionBlock').style.display='none';
	  document.getElementById('workBlock').style.display='none';  
	 
	     //document.getElementById('paraFrm_userProfile').value='C';
	     
	  }
	   return true;
 	}
 	
 	function callForRegular()
 	{
 	document.getElementById('decisionBlock').style.display='none';
	    document.getElementById('variableWorkforce').style.display='none';
	    
 		 document.getElementById('paraFrm_temporaryType').value='';
	  document.getElementById('paraFrm_agencyName').value='';
	  document.getElementById('paraFrm_contractorName').value='';
	  document.getElementById('paraFrm_contractorPhoneNumber').value='';
	  document.getElementById('paraFrm_contractorEmailAddress').value='';
	  document.getElementById('paraFrm_maximumBillRate').value='';
	   document.getElementById('paraFrm_overtimeRequired').value='';
	    document.getElementById('paraFrm_numberOfOvertime').value='';
	  
	  document.getElementById('paraFrm_durationOfAssignment').value='';
 		  document.getElementById('paraFrm_reasonForTemporaryNeed').value='';
 		  
 		  document.getElementById('paraFrm_variableWorkfroceRate').value='';
	  document.getElementById('rateTypeRadioOptionValue').value='';
	  document.getElementById('paraFrm_durationOfVariableAssignment').value='';
	  document.getElementById('paraFrm_reasonForVariableWorkforceNeed').value='';
 	}
 	
 	
 	
function sendforapprovalFun() {		
	if(!validateMandetoryFields()) {
		return false;
	} else {
	 	var con=confirm('Do you really want to send this application for approval?');
	 	if(con) {
			document.getElementById('paraFrm_persReqStatus').value = 'P';	
			document.getElementById('paraFrm').target = "_self";
	    	document.getElementById('paraFrm').action='PersonalRequisition_sendForApprovalFunction.action';
			document.getElementById('paraFrm').submit();
		}
	}			
}

function draftFun() {	
	if(!validateMandetoryFields()) {
		return false;
	} else {
		document.getElementById('paraFrm_persReqStatus').value = 'D';	
		document.getElementById('paraFrm').target = "_self";
     	document.getElementById('paraFrm').action='PersonalRequisition_draftFunction.action';
  		document.getElementById('paraFrm').submit();	
	}
}


function resetFun()  {
	document.getElementById('paraFrm').target = "_self";     	
 	document.getElementById('paraFrm').action = 'PersonalRequisition_reset.action';
    document.getElementById('paraFrm').submit();
}

function backtolistFun()  {
	document.getElementById('paraFrm').target = "_self";
    document.getElementById('paraFrm').action = 'PersonalRequisition_back.action';
	document.getElementById('paraFrm').submit();
}

function printFun() {	
	window.print();
}

function deleteFun() {
	 var con=confirm('Do you want to delete this application ?');
	 if(con) {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'PersonalRequisition_delete.action';
		document.getElementById('paraFrm').submit();
	}
}

function cancelapplicationFun() {
	try{
		if(document.getElementById('paraFrm_approverToken').value==""){
			alert("Please select a Approval Name");
			document.getElementById('paraFrm_approverToken').focus();
  			return false;
		}
	}catch(e) {
		alert("Exception occured in cancel function : "+e);
	}
	
	 var con=confirm('Do you want to cancel this application ?');
	 if(con) {
		document.getElementById('paraFrm_persReqStatus').value = 'C';	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'PersonalRequisition_cancel.action';
		document.getElementById('paraFrm').submit();
	}
}

function imposeMaxLength(Event, Object, MaxLen) {
   return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
}

function validateMandetoryFields() {
	try {
		var positionTitleName = document.getElementById('paraFrm_positionTitleName').value;
		if(positionTitleName=="") {
			alert("Please enter "+document.getElementById('position.title').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_positionTitleName').focus();
		 	return false;		
		}
		
		var requisitionVar = document.getElementById('paraFrm_requisition').value;
		if(requisitionVar=="") {
			alert("Please enter "+document.getElementById('requisition').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_requisition').focus();
		 	return false;		
		}
		
		if(isNaN(document.getElementById('paraFrm_requisition').value)){
			alert('Please enter Requisition as a number');
			document.getElementById('paraFrm_requisition').focus();
			return false;			
		}
				
		var bandNameVar = document.getElementById('paraFrm_bandName').value;
		if(bandNameVar=="") {
			alert("Please select "+document.getElementById('band').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_bandName').focus();
		 	return false;		
		}
		
		if(trim(document.getElementById('paraFrm_positionDate').value) == "") {
			alert("Please select or enter "+document.getElementById('position.date').innerHTML.toLowerCase());
			document.getElementById('paraFrm_positionDate').focus();
			return false;
		} else {
			if(!validateDate('paraFrm_positionDate', 'position.date')) {
				return false;
			}
		}
		
		 
		if(!document.getElementById('paraFrm_maxSalary').value==''){	
			if(isNaN(document.getElementById('paraFrm_maxSalary').value)){
				alert('Please enter Salary as a number');
				document.getElementById('paraFrm_maxSalary').focus();
				return false;			
			}	
		}
			
		if(isNaN(document.getElementById('paraFrm_maximumBillRate').value)){
			alert('Please enter Maximum BillRate as a number');
			document.getElementById('paraFrm_maximumBillRate').focus();
			return false;			
		}
			
		if(!document.getElementById('paraFrm_numberOfOvertime').value==''){
			if(isNaN(document.getElementById('paraFrm_numberOfOvertime').value)){
				alert('Please enter Number of Overtime as a number');
				document.getElementById('paraFrm_numberOfOvertime').focus();
				return false;			
			}
		}
		
		if(!document.getElementById('paraFrm_durationOfAssignment').value==''){
			if(isNaN(document.getElementById('paraFrm_durationOfAssignment').value)){
				alert('Please enter Duration Of Assignment as a number');
				document.getElementById('paraFrm_durationOfAssignment').focus();
				return false;			
			}
		}
			
		if(isNaN(document.getElementById('paraFrm_variableWorkfroceRate').value)){
			alert('Please enter Variable Workfroce Rate as a number');
			document.getElementById('paraFrm_variableWorkfroceRate').focus();
			return false;			
		}
			
		if(!document.getElementById('paraFrm_durationOfVariableAssignment').value==''){
			if(isNaN(document.getElementById('paraFrm_durationOfVariableAssignment').value)){
				alert('Please enter Duration Of Variable Assignment as a number');
				document.getElementById('paraFrm_durationOfVariableAssignment').focus();
				return false;			
			}
		}
		 
		var deptNameVar = document.getElementById('paraFrm_deptName').value;
		if(deptNameVar=="") {
			alert("Please select "+document.getElementById('dept.number').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_deptName').focus();
		 	return false;		
		}
		 
		if(document.getElementById('paraFrm_replacementType').value =='Y' && document.getElementById('paraFrm_reqReplacingToken').value=="") {
			alert("Please enter "+document.getElementById('req.replacing').innerHTML.toLowerCase());
			document.getElementById('paraFrm_reqReplacingToken').focus();
		 	return false;	
		}
	 
		 
		if(document.getElementById('paraFrm_replacementType').value=='Y' && document.getElementById('paraFrm_terminationDate').value=="") {
			alert("Please select or enter "+document.getElementById('termination.date').innerHTML.toLowerCase());
			document.getElementById('paraFrm_terminationDate').focus();
		 	return false;	
		}
		
		if(!document.getElementById('paraFrm_terminationDate').value==''){		
			var check1= validateDate('paraFrm_terminationDate', 'termination.date');
			if(!check1){
				return false;
			}
		}
		
		 
		if(document.getElementById('userProfileRadioOptionValue').value=='Te' && document.getElementById('paraFrm_temporaryType').value=="") {
			alert("Please select Type of Temporary.");
			document.getElementById('paraFrm_temporaryType').focus();
		 	return false;	
		}
		 
	 
		if(document.getElementById('userProfileRadioOptionValue').value=='Te' && document.getElementById('paraFrm_temporaryType').value=="A" && document.getElementById('paraFrm_agencyName').value=="") {
				alert("Please enter "+document.getElementById('agency.name').innerHTML.toLowerCase());
				document.getElementById('paraFrm_agencyName').focus();
				return false;
		}
	 
	 
		if(document.getElementById('userProfileRadioOptionValue').value=='Te' && document.getElementById('paraFrm_temporaryType').value=="A" && document.getElementById('paraFrm_contractorName').value=="") {
				alert("Please enter "+document.getElementById('contractor.name').innerHTML.toLowerCase());
				document.getElementById('paraFrm_contractorName').focus();
				return false;
		}
		 
	 
		if(document.getElementById('userProfileRadioOptionValue').value=='Te' && document.getElementById('paraFrm_temporaryType').value=="A" && document.getElementById('paraFrm_contractorPhoneNumber').value=="") {
			alert("Please enter "+document.getElementById('agency.phone.number').innerHTML.toLowerCase());
			document.getElementById('paraFrm_contractorPhoneNumber').focus();
			return false;
		}
		 
	 
		if(document.getElementById('userProfileRadioOptionValue').value=='Te' && document.getElementById('paraFrm_temporaryType').value=="A" && document.getElementById('paraFrm_contractorEmailAddress').value==""){
				alert("Please enter Contractor Email Address");
				document.getElementById('paraFrm_contractorEmailAddress').focus();
				return false;
		} else {
		 	if(!validateEmail('paraFrm_contractorEmailAddress')){
		 		return false;
		 	}
		} 
		 

		if(document.getElementById('userProfileRadioOptionValue').value=='Te' && document.getElementById('paraFrm_maximumBillRate').value=="") {
			alert("Please enter "+document.getElementById('maximum.bill.rate').innerHTML.toLowerCase());
			document.getElementById('paraFrm_maximumBillRate').focus();
			return false;
		}
	 
	
		if(document.getElementById('userProfileRadioOptionValue').value=='Te' && document.getElementById('paraFrm_reasonForTemporaryNeed').value == "") {
			alert("Please enter "+document.getElementById('reason.for.temporary.need').innerHTML.toLowerCase());
			document.getElementById('paraFrm_reasonForTemporaryNeed').focus();
			return false;
		}
	 
		
		var stdHourPerWeekVar = document.getElementById('paraFrm_stdHourPerWeek').value;
		if(stdHourPerWeekVar=="") {
			alert("Please select "+document.getElementById('std.hour.per.week').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_stdHourPerWeek').focus();
		 	return false;		
		}
		
		var fulltimeParttimeVar = document.getElementById('paraFrm_fulltimeParttime').value;
		if(fulltimeParttimeVar=="") {
			alert("Please select "+document.getElementById('fulltime.parttime').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_fulltimeParttime').focus();
		 	return false;		
		}	
		
		var educationRequirementsVar = document.getElementById('paraFrm_educationRequirements').value;
		if(educationRequirementsVar=="") {
			alert("Please select "+document.getElementById('education.requirements').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_educationRequirements').focus();
		 	return false;		
		}
		
		var experienceRequirementVar = document.getElementById('paraFrm_experienceRequirement').value;
		if(experienceRequirementVar=="") {
			alert("Please select "+document.getElementById('experience.requirement').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_experienceRequirement').focus();
		 	return false;		
		}	
		
		var essentialPositionRequirementsVar = document.getElementById('paraFrm_essentialPositionRequirements').value;
		if(essentialPositionRequirementsVar=="") {
			alert("Please select "+document.getElementById('essential.position.requirements').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_essentialPositionRequirements').focus();
		 	return false;		
		}	
		
	 
		if(document.getElementById('userProfileRadioOptionValue').value=='Va' && document.getElementById('paraFrm_variableWorkfroceRate').value=="" ) {
			alert("Please enter "+document.getElementById('variable.workfroce.rate').innerHTML.toLowerCase());
			document.getElementById('paraFrm_variableWorkfroceRate').focus();
			return false;
		}
		 
		if(document.getElementById('userProfileRadioOptionValue').value=='Va' && document.getElementById('paraFrm_reasonForVariableWorkforceNeed').value=="" ) {
			alert("Please enter "+document.getElementById('reason.for.variable.workforce.need').innerHTML.toLowerCase());
			document.getElementById('paraFrm_reasonForVariableWorkforceNeed').focus();
			return false;
		}
		 
	 
		if(document.getElementById('paraFrm_approverToken').value==""){
			alert("Please select a Approval Name");
			document.getElementById('paraFrm_approverToken').focus();
  			return false;
		}
		 
		var forwardNameType = document.getElementById('paraFrm_forwardedForApprovar').value;
		if(forwardNameType!='Di') {
			alert("Please Follow the Following Reporting Structure :\nDirector >> Resource Manager >> Vice President >> Senior Vice President >> Human Resource >> Finance >> President");
		  	document.getElementById('paraFrm_forwardedForApprovar').focus();
		 	return false;		
		}
		 
		 return true;	
	} catch(e) {
		alert("Unable to process this application : "+e);
		return false;
	}
				
		// For disabaling the button after clicking once	
		/*
			for (var i = 0; i < document.all.length; i++) {
				if(document.all[i].id == 'save') {
					document.all[i].disabled=true;
				}
			}
		*/
}	
</script>