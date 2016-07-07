<!-- Added by ganesh -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="PersonalRequisitionApprover" validate="true"
	id="paraFrm" validate="true" theme="simple">

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<s:hidden name="listType" />
	
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="myPagePendingCancel" id="myPagePendingCancel" />
	<s:hidden name="myPageApproved" id="myPageApproved" />
	<s:hidden name="myPageRejected" id="myPageRejected" />
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Personnel
					Requisition Approver Form</strong></td>
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
		<tr>
			<td>
			<table width="100%" class="formbg">
				<s:if test="commentFlag">
				<tr>
					<td colspan="2" id="ctrlShow"><b>Approver Comments</b><font
						id='ctrlHide' color="red">*</font></td>
					<td colspan="3" id="ctrlShow"><s:textarea theme="simple"
						cols="70" rows="3" name="approverComments" id="approverComments" onkeypress="return imposeMaxLength(event, this, 500);"/></td>
				</tr>
				</s:if>
				<tr>
					<td width="10%" class="formth">Sr. No.</td>
					<td width="25%" class="formth">Approver Name</td>
					<td width="40%" class="formth">Comments</td>
					<td width="15%" class="formth"> Date</td>
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
		<s:if test="forwardFlag">
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
						<td width="20%" id="ctrlShow"><label id="change.approval"
							name="change.approval" ondblclick="callShowDiv(this);"><%=label.get("change.approval")%></label>
						:<font color="red">*</font></td>
						<td width="60%" colspan="3" id="ctrlShow"><s:textfield
							name="approverToken" size="30" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" /> <s:textfield
							name="approverName" size="60" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" /> <s:hidden
							name="approverCode" /> <img
							src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="16" id='ctrlShow'
							onclick="javascript:callsF9(500,325,'PersonalRequisitionApprover_f9Approver.action');">
						</td>
					</tr>
					<tr>
						<td width="25%" id="ctrlShow"><label
							id="forwarded.for.approvar" name="forwarded.for.approvar"
							ondblclick="callShowDiv(this);"><%=label.get("forwarded.for.approvar")%></label>
						:<font color="red"></font></td>
						<td width="25%" id="ctrlShow"><s:if
							test="%{forwardedNameType == ''}">
							<s:select headerKey="" headerValue="--Select--"
								cssStyle="width:175" name="forwardedForApprovar"
								list="#{'Hm':'Hiring Manager ','Di':'Director ','Rm':'Resource Manager','Vp':'Vice President','Sv':' Senior Vice President','Hr':' Human Resource ','F':' Finance ','Pr':' President'}" />
						</s:if> <s:elseif test="%{forwardedNameType == 'Hm'}">
							<s:select 
								cssStyle="width:175;background-color: #F2F2F2;"
								name="forwardedForApprovar" list="#{'Di':'Director ','Rm':'Resource Manager','Vp':'Vice President','Sv':' Senior Vice President','Hr':' Human Resource ','F':' Finance ','Pr':' President'}" />
						</s:elseif> <s:elseif test="%{forwardedNameType == 'Di'}">
							<s:select 
								cssStyle="width:175;background-color: #F2F2F2;"
								name="forwardedForApprovar" list="#{'Rm':'Resource Manager','Vp':'Vice President','Sv':' Senior Vice President','Hr':' Human Resource ','F':' Finance ','Pr':' President'}" />
						</s:elseif> <s:elseif test="%{forwardedNameType == 'Rm'}">
							<s:select 
								cssStyle="width:175;background-color: #F2F2F2;"
								name="forwardedForApprovar" list="#{'Vp':'Vice President','Sv':' Senior Vice President','Hr':' Human Resource ','F':' Finance ','Pr':' President'}" />
						</s:elseif> <s:elseif test="%{forwardedNameType == 'Vp'}">
							<s:select 
								cssStyle="width:175;background-color: #F2F2F2;"
								name="forwardedForApprovar"
								list="#{'Sv':' Senior Vice President','Hr':' Human Resource ','F':' Finance ','Pr':' President'}" />
						</s:elseif> <s:elseif test="%{forwardedNameType == 'Sv'}">
							<s:select 
								cssStyle="width:175;background-color: #F2F2F2;"
								name="forwardedForApprovar" list="#{'Hr':' Human Resource ','F':' Finance ','Pr':' President'}" />
						</s:elseif> <s:elseif test="%{forwardedNameType == 'Hr'}">
							<s:select 
								cssStyle="width:175;background-color: #F2F2F2;"
								name="forwardedForApprovar" list="#{'Fi':' Finance ','Pr':' President'}" />
						</s:elseif> <s:elseif test="%{forwardedNameType == 'Fi'}">
							<s:select 
								cssStyle="width:175;background-color: #F2F2F2;"
								name="forwardedForApprovar" list="#{'Pr':' President'}" />
						</s:elseif>
						</td>
						<td width="25%"></td>
						<td width="25%"></td>
					</tr>


				</table>
				</td>
				<!-- Approver Comments Section Ends -->
			</tr>
		</s:if>
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
				<!--<tr>
					<td width="100%" height="22"  colspan="4">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1" >
						
						<tr>
							<td colspan="4" width="25%"><b><label class="set"
								name="tracking.no" id="tracking.no"
								ondblclick="callShowDiv(this);"><%=label.get("tracking.no")%></label> :</b>
							</td>
							<td width="25%">							
							<s:property value="trackingNo"/>
							</td>
							<td  width="25%"></td>
							<td width="25%"></td>
						</tr>
				</table>
				</td>
				</tr>
				--><tr>
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
					<td width="25%" colspan="1"><label name="position.title"
						id="position.title" ondblclick="callShowDiv(this);"><%=label.get("position.title")%></label>
					:<font color="red" id='ctrlHide'>*</font></td>
					<td width="25%" colspan="1"><s:textfield size="30"
						name="positionTitleName" /></td>

					<td width="25%"><label id="requisition" name="requisition"
						ondblclick="callShowDiv(this);"><%=label.get("requisition")%></label>
					:</td>
					<td width="25%"><s:textfield name="requisition" size="30" /></td>
				</tr>

				<tr>
					<td width="25%" colspan="1"><label name="band" id="band"
						ondblclick="callShowDiv(this);"><%=label.get("band")%></label> :<font
						color="red" id='ctrlHide'>*</font></td>
					<td width="25%" colspan="1"><s:textfield size="30"
						name="bandName" readonly="true" /> <s:hidden name="bandId" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'PersonalRequisitionApprover_f9band.action');"></td>

				</tr>


				<tr>
					<td width="25%"><label id="max.salary" name="max.salary"
						ondblclick="callShowDiv(this);"><%=label.get("max.salary")%></label>
					:</td>
					<td width="25%"><s:textfield name="maxSalary" size="30"
						onkeypress="return numbersOnly();" /></td>

					<td width="25%" colspan="1"><label class="set"
						name="position.date" id="position.date"
						ondblclick="callShowDiv(this);"><%=label.get("position.date")%></label>
					:<font id='ctrlHide' color="red">*</font></td>

					<td width="25%" colspan="3" align="left"><s:textfield
						name="positionDate" size="30"
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
					:<font id='ctrlHide' color="red"></font></td>

					<td width="25%" colspan="1"><s:textfield size="30"
						name="deptName" readonly="true" /> <s:hidden name="deptCode" />
					<img src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'PersonalRequisitionApprover_f9deptNumber.action');"></td>

					<td width="25%" colspan="1"><label class="set"
						name="executive" id="executive" ondblclick="callShowDiv(this);"><%=label.get("executive")%></label>
					:<font id='ctrlHide' color="red"></font></td>

					<td width="25%" colspan="1"><s:textfield size="30"
						name="executiveName" readonly="true" /> <s:hidden
						name="executiveCode" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'PersonalRequisitionApprover_f9rankaction.action');"></td>
				</tr>



				<tr>
					<td width="25%"><label id="work.location" name="work.location"
						ondblclick="callShowDiv(this);"><%=label.get("work.location")%></label>
					:</td>
					<td width="25%"><s:textfield name="workLocation" size="30" /></td>
				</tr>
				<tr>
					<td width="25%"><label id="hiring.manager"
						name="hiring.manager" ondblclick="callShowDiv(this);"><%=label.get("hiring.manager")%></label>
					:</td>
					<td width="60%" colspan="3"><s:textfield
						name="hiringManagerToken" size="30" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:textfield
						name="hiringManagerName" size="60" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:hidden
						name="hiringManagerCode" /></td>
				</tr>
				<tr>
					<td width="25%"><label id="hiring.manager.phone.number"
						name="hiring.manager.phone.number" ondblclick="callShowDiv(this);"><%=label.get("hiring.manager.phone.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="hiringManagerPhoneNumber"
						size="30" readonly="true" cssStyle="background-color: #F2F2F2;" /></td>


					<td width="25%"><label id="hiring.manager.fax.number"
						name="hiring.manager.fax.number" ondblclick="callShowDiv(this);"><%=label.get("hiring.manager.fax.number")%></label>
					:</td>
					<td width="25%"><s:textfield name="hiringManagerFaxNumber"
						size="30" readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
				</tr>

				<tr>
					<td width="25%"><label id="hiring.manager.email.address"
						name="hiring.manager.email.address"
						ondblclick="callShowDiv(this);"><%=label.get("hiring.manager.email.address")%></label>
					:</td>
					<td width="25%"><s:textfield name="hiringManagerEmailAddress"
						size="30" readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
				</tr>
				<tr>
					<td><label class="set" name="approved.existing.job"
						id="approved.existing.job" ondblclick="callShowDiv(this);"><%=label.get("approved.existing.job")%></label><font
						color="red" id="ctrlHide">*</font> :</td>

					<td><s:select headerKey="" headerValue="--Select--"
						name="approvedExistingJob" list="#{'Y':'Yes','N':'No'}" /></td>
				
					<td><label class="set" name="new.job.exist" id="new.job.exist"
						ondblclick="callShowDiv(this);"><%=label.get("new.job.exist")%></label><font
						color="red" id="ctrlHide">*</font> :</td>

					<td><s:select headerKey="" headerValue="--Select--"
						name="newJobExist" list="#{'Y':'Yes','N':'No'}" /></td>
				</tr>
				<tr>
					<td><label class="set" name="head.count" id="head.count"
						ondblclick="callShowDiv(this);"><%=label.get("head.count")%></label><font
						color="red" id="ctrlHide">*</font> :</td>

					<td><s:select headerKey="" headerValue="--Select--"
						name="headCount" list="#{'Y':'Yes','N':'No'}" /></td>
				
					<td><label class="set" name="replacement" id="replacement"
						ondblclick="callShowDiv(this);"><%=label.get("replacement")%></label><font
						color="red" id="ctrlHide">*</font> :</td>

					<td><s:select headerKey="" headerValue="--Select--"
						name="replacementType" list="#{'Y':'Yes','N':'No'}"
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
						<s:if test="%{requestID == ''}">
						<img
							src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="16" id='ctrlHide'
							onclick="javascript:callsF9(500,325,'PersonalRequisition_f9ReqEmployee.action');">
							</s:if>
							
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
					<td class="formtext" width="15%"><label name="position.type"
						id="position.type" ondblclick="callShowDiv(this);"> <%=label.get("position.type")%>
					</label></td>
					<td height="22" width="25%" colspan="3"><s:radio 
						name="positionType" value="%{positionType}"
						list="#{'Re':' Regular &nbsp;'}"
						onclick="callForRegular();setUserProfileRadioValue(this);">
					</s:radio> <s:radio name="positionType" value="%{positionType}"
						list="#{'Te':'Temporary &nbsp;'}"
						onclick="callForTemporary();setUserProfileRadioValue(this);">
					</s:radio> <s:radio name="positionType" value="%{positionType}"
						list="#{'Va':'Variable WorkForce &nbsp;'}"
						onclick="callForVariableWorkForce();setUserProfileRadioValue(this);">
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
							:</td>
							<td width="25%"><s:select headerKey=""
								headerValue="--Select--" cssStyle="width:175"
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
									:</td>
									<td width="25%" colspan="1"><s:textfield size="30"
										name="agencyName" /></td>
									<td width="25%" colspan="1"><label name="contractor.name"
										id="contractor.name" ondblclick="callShowDiv(this);"><%=label.get("contractor.name")%></label>
									:</td>
									<td width="25%" colspan="1"><s:textfield size="30"
										name="contractorName" /></td>
								</tr>
								<tr>
									<td width="25%" colspan="1"><label
										name="agency.phone.number" id="agency.phone.number"
										ondblclick="callShowDiv(this);"><%=label.get("agency.phone.number")%></label>
									:</td>
									<td width="25%" colspan="1"><s:textfield size="30"
										name="contractorPhoneNumber" /></td>
									<td width="25%" colspan="1"><label
										name="contractor.email.address" id="contractor.email.address"
										ondblclick="callShowDiv(this);"><%=label.get("contractor.email.address")%></label>
									:</td>
									<td width="25%" colspan="1"><s:textfield size="30"
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

							<td><s:textfield name="maximumBillRate" size="30"
								onkeypress="return numbersOnly();" /></td>

							<td width="25%"><label id="overtime.required"
								name="overtime.required" ondblclick="callShowDiv(this);"><%=label.get("overtime.required")%></label>
							:</td>
							<td width="25%"><s:select headerKey=""
								headerValue="--Select--" cssStyle="width:175"
								name="overtimeRequired" list="#{'Y':'Yes ','N':'No'}" /></td>

						</tr>

						<tr>

							<td width="25%"><label id="number.of.overtime"
								name="number.of.overtime" ondblclick="callShowDiv(this);"><%=label.get("number.of.overtime")%></label>
							:</td>
							<td width="25%"><s:textfield size="30"
								name="numberOfOvertime" /></td>

							<td width="25%"><label id="duration.of.assignment"
								name="duration.of.assignment" ondblclick="callShowDiv(this);"><%=label.get("duration.of.assignment")%></label>
							:</td>
							<td width="25%"><s:textfield size="30"
								name="durationOfAssignment" /></td>

						</tr>
						<tr>
							<td width="25%" colspan="1"><label class="set"
								name="reason.for.temporary.need" id="reason.for.temporary.need"
								ondblclick="callShowDiv(this);"><%=label.get("reason.for.temporary.need")%></label>
							:<font id='ctrlHide' color="red">*</font></td>

							<td><s:textarea name="reasonForTemporaryNeed" cols="35"
								rows="2" /></td>

						</tr>

					</table>
					</td>
				</tr>
				<tr id="variableWorkforce">
					<td colspan="4">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1" class="formbg">
						<tr>
							<td colspan="3"><label><b>Variable workfroce</b> </label> :</td>

						</tr>


						<tr>
							<td colspan="3"><label>The Variable workfroce is
							engaged to supply on demand service on per call,piece of work or
							Hourly basis </label> :</td>

						</tr>


						<tr>
							<td width="25%" colspan="1"><label class="set"
								name="variable.workfroce.rate" id="variable.workfroce.rate"
								ondblclick="callShowDiv(this);"><%=label.get("variable.workfroce.rate")%></label>
							:<font id='ctrlHide' color="red">*</font></td>

							<td><s:textfield name="variableWorkfroceRate" size="30"
								onkeypress="return numbersOnly();" /></td>
							<td width="25%"></td>
							<td width="25%"></td>
						</tr>

						<tr>
							<td class="formtext" width="15%"><label name="rate.type"
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

							<td width="25%"><label id="duration.of.assignment"
								name="duration.of.assignment" ondblclick="callShowDiv(this);"><%=label.get("duration.of.assignment")%></label>
							:</td>
							<td width="25%"><s:textfield size="30"
								name="durationOfVariableAssignment" /></td>
							<td width="25%"></td>
							<td width="25%"></td>
						</tr>
						<tr>
							<td width="25%" colspan="1"><label class="set"
								name="reason.for.variable.workforce.need"
								id="reason.for.variable.workforce.need"
								ondblclick="callShowDiv(this);"><%=label.get("reason.for.variable.workforce.need")%></label>
							:<font id='ctrlHide' color="red">*</font></td>

							<td><s:textarea name="reasonForVariableWorkforceNeed"
								cols="35" rows="2" /></td>
							<td width="25%"></td>
							<td width="25%"></td>
						</tr>

					</table>
					</td>
				</tr>
				<tr>
					<td width="25%" colspan="1"><label name="budget" id="budget"
						ondblclick="callShowDiv(this);"><%=label.get("budget")%></label> :</td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:175" name="budget"
						list="#{'Y':'Yes ','N':'No'}" /></td>
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
					<td><b><label id="position.requirement"
						name="position.requirement" ondblclick="callShowDiv(this);"><%=label.get("position.requirement")%></label>
					</b></td>
				</tr>
				<tr>
					<td width="25%"><label id="std.hour.per.week"
						name="std.hour.per.week" ondblclick="callShowDiv(this);"><%=label.get("std.hour.per.week")%></label>
					:</td>
					<td width="25%"><s:textfield name="stdHourPerWeek" size="30" /></td>

					<td width="25%"><label id="fulltime.parttime"
						name="fulltime.parttime" ondblclick="callShowDiv(this);"><%=label.get("fulltime.parttime")%></label>
					:</td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:175"
						name="fulltimeParttime" list="#{'F':'Full Time ','P':'Part Time'}" /></td>

				</tr>
				<tr>
					<td width="25%"><label id="education.requirements"
						name="education.requirements" ondblclick="callShowDiv(this);"><%=label.get("education.requirements")%></label>
					:</td>
					<td width="25%"><s:textfield name="educationRequirements"
						size="30" /></td>

					<td width="25%"><label id="experience.requirement"
						name="experience.requirement" ondblclick="callShowDiv(this);"><%=label.get("experience.requirement")%></label>
					:</td>
					<td width="25%"><s:textfield name="experienceRequirement"
						size="30" /></td>

				</tr>
				<tr>
					<td width="25%"><label id="essential.position.requirements"
						name="essential.position.requirements"
						ondblclick="callShowDiv(this);"><%=label.get("essential.position.requirements")%></label>
					:</td>
					<td width="25%"><s:textfield
						name="essentialPositionRequirements" size="30" /></td>

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
					<td><b><label id="justification" name="justification"
						ondblclick="callShowDiv(this);"><%=label.get("justification")%></label>
					</b></td>
				</tr>

				<tr>
					<td width="25%"><label id="reason" name="reason"
						ondblclick="callShowDiv(this);"><%=label.get("reason")%></label> :</td>
					<td><s:textarea name="reason" cols="35" rows="2" /></td>

					<td width="25%"></td>
					<td width="25%"></td>

				</tr>

			</table>
			</td>
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

		<!--<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
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
						size="30" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:textfield
						name="approverName" size="60" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:hidden
						name="approverCode" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'PersonalRequisitionApprover_f9Approver.action');">
					</td>
				</tr>
				<tr>
					<td width="25%"><label id="forwarded.for.approvar"
								name="forwarded.for.approvar" ondblclick="callShowDiv(this);"><%=label.get("forwarded.for.approvar")%></label>
							:</td>
					<td width="25%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:175"
						name="forwardedForApprovar" list="#{'Hm':'Hiring Manager ','Di':'Director ','Rm':'Resource Manager','Vp':'Vice President','Sv':' Senior Vice President','Hr':' Human Resource ','F':' Finance ','Pr':' President'}" /></td>
					<td width="25%"></td>
					<td width="25%"></td>		
				</tr>
				<tr>
					<td colspan="4"><b>Note : </b>Please Follow the Following Reporting Structure : <br/>
						 Hiring Manager >> Director >> Resource Manager >> Vice President >> Senior Vice President >> Human Resource >> Finance >> President</td>
				</tr>
				
			</table>
			</td>
		</tr>
		-->
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
		<s:hidden name="forwardedNameType" />
		<s:hidden name="creditMemoRadio" />
		<s:hidden name="persReqStatus" />
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
		 }
		
	}
	
	function setUserProfileRadioValue(id){
	//alert(id);
		var opt=document.getElementById('userProfileRadioOptionValue').value =id.value;	
}
function setRateTypeRadioValue(id){
	//alert(id);
		var opt=document.getElementById('rateTypeRadioOptionValue').value =id.value;	
}
 	
function forwardFun()
{	
		try
		{
		if(document.getElementById('paraFrm_approverToken').value==""){
			alert("Please select a Approvar");
			document.getElementById('paraFrm_approverToken').focus();
  			return false;
		}
		
		}catch(e)
		{
			alert("Exception occurred in send for approver function."+e);
		}
		
	 var con=confirm('Do you really want to forward this application for approval?');
	 if(con)
	 {
		document.getElementById('paraFrm_persReqStatus').value = 'F';	
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action='PersonalRequisitionApprover_approveApplication.action';
		document.getElementById('paraFrm').submit();
	}		
}
function approveFun()
{	
	 var con=confirm('Do you really want to approve this application ?');
	 if(con)
	 {
		document.getElementById('paraFrm_persReqStatus').value = 'A';	
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action='PersonalRequisitionApprover_approveApplication.action';
		document.getElementById('paraFrm').submit();
	}		
}

function rejectFun()
{
	var con = confirm("Do you really want to reject this application?");
	if(con)
	{
	document.getElementById('paraFrm_persReqStatus').value = 'R';	
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action = 'PersonalRequisitionApprover_rejectApplication.action';
		document.getElementById('paraFrm').submit();
	}	
}

function sendbackFun()
{
	var con = confirm("Do you really want to send back this application?");
	if(con)
	{
		document.getElementById('paraFrm_persReqStatus').value = 'B';	
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action = 'PersonalRequisitionApprover_sendBackApplication.action';
		document.getElementById('paraFrm').submit();
	}	
}

function backtolistFun() 
{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'PersonalRequisitionApprover_backToList.action';
		document.getElementById('paraFrm').submit();
}
function printFun() {	
	window.print();
	}
function imposeMaxLength(Event, Object, MaxLen)
{
        return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
}

function authorizedsignoffFun() {
	var vv=confirm("Do you really want to authorized sign off this application?");
	if(vv){
	document.getElementById('paraFrm_persReqStatus').value = 'S';
	//alert(document.getElementById('paraFrm_persReqStatus').value);	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'PersonalRequisitionApprover_approveApplication.action';
		document.getElementById('paraFrm').submit();
		}
	}		
	function closeFun() {
					window.close();
			}	
</script>