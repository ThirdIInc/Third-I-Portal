
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%
			System.out.println("String value------- "
			+ request.getParameter("reqCode"));
%>
<%@page import="org.paradyne.lib.Utility;"%>
<s:form action="ConductInterview" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="jobDesc" />
	<s:hidden name="rolesResponsibility" />
	<s:hidden name="addNewFlag" />
	<s:hidden name="candidateEvaluationCode" />
	<s:hidden name="interviewDetailCode" />
	<s:hidden name="onHoldCalculation"/>
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<!-- Final Table -->

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Candidate
					Evaluation</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/common/css/default/images/help.gif" width="16"
						height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="5">
			<table width="100%">
				<tr>
					<td width="78%"><input type="button" class="token"
						theme="simple" value="Submit"
						onclick="return submitValidation();" /> <!--<s:submit cssClass="token"
							action="ConductInterview_print" theme="simple" value="Print"
							onclick="return validation();" /><s:submit cssClass="token"
							action="ConductInterview_printBlankForm" theme="simple"
							value="Print Blank Form" onclick="return validation();" />--> <s:if
						test="checkFlag">
						<s:submit cssClass="cancel"
							action="InterviewDetails_showInterviewCandList" theme="simple"
							value="  Cancel" onclick="return validation();" cssClass="cancel" />
					</s:if> <s:elseif test="addNewFlag">
						<s:submit cssClass="cancel" theme="simple"
							action="InterviewDetails_showInterviewCandList" value="  Cancel"
							onclick="return validation();" cssClass="cancel" />
					</s:elseif> <s:else>
						<s:submit cssClass="cancel" theme="simple"
							action="ConductInterview_input" value="  Cancel"
							onclick="return validation();" cssClass="cancel" />
					</s:else>
					
				 	
					</td>
					<td width="22%">
					<div align="right"><font color="red">* </font> Indicates
					Required</div>
					</td>
				</tr>
			</table>

			</td>
		</tr>
		<tr>
			<!--Schedule Test-->
			<td colspan="5">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td colspan="2"><strong class="formhead">Requisition
					Details</strong></td>
					<td colspan="2"><strong class="formhead">Interview
					Details</strong></td>
				</tr>
				<tr>
					<td colspan="2" width="50%">
					<table width="98%" border="0" cellpadding="1" cellspacing="1"
						class="formbg" id="reqDtls">
						<!-- table 6 -->
						<tr>
							<td width="50%" nowrap="nowrap"><label class="set"
								name="reqs.code" id="reqs.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label>
							:<font color="red"> * </font></td>
							<td width="30%"><s:textfield name="requisitionName"
								readonly="true" size="25" /></td>
							<s:hidden name="requisitionCode" />
							<s:hidden name="intDtlCode" />
							<s:hidden name="intCode" />
							<s:hidden name="bckToIntrFlag" />
							<s:hidden name="hiringManager" />
							<s:hidden name="hiringManagerCode" />
							<td width="20%"><s:if test="checkFlag"></s:if><s:else>
								<img src="../pages/images/recruitment/search2.gif" height="16"
									align="absmiddle" width="17" theme="simple"
									onclick="javascript:callsF9(500,325,'ConductInterview_f9Requisition.action'); ">
							</s:else></td>
						</tr>
						<s:if test="checkFlag"></s:if>
						<s:else>
							<tr>
								<td width="46%"></td>
								<td colspan="2"><input type="button" class="token"
									style="width: 152" value="  Quick Requisition  "
									id="CreateNewRequisition" onclick="createNewRequisition()" />
								</td>
							</tr>
						</s:else>
						<tr>
							<td><label class="set" name="position" id="position"
								ondblclick="callShowDiv(this);"><%=label.get("position")%></label>
							:</td>
							<td><s:textfield name="position" readonly="true" size="25" />
							<s:hidden name="positionCode" /></td>
						</tr>
						<tr>
							<td><label class="set" name="division" id="divsn"
								ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
							:</td>
							<td><s:textfield name="division" readonly="true" size="25" />
							<s:hidden name="divisionCode" /></td>
						</tr>
						<tr>
							<td><label class="set" name="branch" id="brnch"
								ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							:</td>
							<td><s:textfield name="branch" readonly="true" size="25" />
							<s:hidden name="branchCode" /></td>
						</tr>
						<tr>
							<td><label class="set" name="department" id="dept"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							:</td>
							<td><s:textfield name="department" readonly="true" size="25" />
							<s:hidden name="deptCode" /></td>
						</tr>
					</table>
					<!-- table 6 --></td>
					<td colspan="2" width="50%">
					<table width="98%" border="0" cellpadding="1" cellspacing="1"
						class="formbg" id="cndtDtls">
						<!-- table 6 -->
						<tr>
							<td width="50%"><label class="set" name="cand.name"
								id="cand.name" ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label>
							:<font color="red"> * </font></td>
							<td width="30%"><s:textfield name="candName" size="25"
								readonly="true" /></td>
							<s:hidden name="candCode" />
							<td width="20%"><s:if test="checkFlag"></s:if><s:else>
								<img src="../pages/images/recruitment/search2.gif" height="16"
									align="absmiddle" width="17" theme="simple"
									onclick="validateCandidate();">
							</s:else></td>
						</tr>
						<s:if test="checkFlag"></s:if>
						<s:else>
							<tr>
								<td width="46%"></td>
								<td><s:submit name="postCandidate" value="Post Candidate"
									cssClass="token" cssStyle="width:152"
									action="ConductInterview_forwardToPostCandidate"
									onclick="return validatePostCandidate();" /></td>
							</tr>
						</s:else>
						<tr>
							<td><label class="set" name="intRnd" id="intRnd"
								ondblclick="callShowDiv(this);"><%=label.get("intRnd")%></label>
							:<font color="red"> * </font></td>
							
							<!-- 
								<td><s:textfield name="intRound" size="25" maxLength="15" /></td>
							 -->
							
							<td>
								<s:select headerKey="-1" headerValue="-- Select --" name="intRound" cssStyle="width:100" list="tmap" />
							</td>
							
							 
						</tr>
						<tr>
							<td><label class="set" name="date" id="date"
								ondblclick="callShowDiv(this);"><%=label.get("date")%></label> :<font
								color="red"> * </font></td>
							<td><s:textfield name="intDate" size="25"
								onkeypress="return numbersWithHiphen();" maxlength="10" /></td>
							<td><a
								href="javascript:NewCal('paraFrm_intDate','DDMMYYYY');"> <img
								src="../pages/images/Date.gif" class="iconImage" height="16"
								align="absmiddle" width="16"> </a></td>
						</tr>
						<tr>
							<td><label class="set" name="time" id="time"
								ondblclick="callShowDiv(this);"><%=label.get("time")%></label> :<font
								color="red"> * </font></td>
							<td><s:textfield name="intTime" size="25"
								onkeypress="return numbersWithColon()" maxlength="5" /></td>
						</tr>
						<s:if test="checkFlag">
							<tr>
								<td><label class="set" name="prvIntDtls" id="prvIntDtls"
									ondblclick="callShowDiv(this);"><%=label.get("prvIntDtls")%></label>
								:</td>
								<td><input type="button" name="view" value="View"
									class="token"
									onclick="viewInterviewDetails('<s:property value="requisitionCode" />','<s:property value="candCode" />');" /></td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<td colspan="3">&nbsp;</td>
							</tr>
						</s:else>
					</table>
					<!-- table 6 --></td>
				</tr>
				<tr>
					<td colspan="4" width="100%">
						<table width="98%" border="0" cellpadding="1" cellspacing="1" id="evalDtls">
							<tr>
								<td colspan="4"><strong class="formhead">Recruiter
								Selection</strong></td>
							</tr>
							<s:if test="checkFlag">
							<tr>
								<td width="22%"><label class="set" name="assignRecruiter"
									id="assignRecruiter" ondblclick="callShowDiv(this);"><%=label.get("assignRecruiter")%></label>
								:<font color="red">*</font></td>
								<s:hidden name="recruiterId" />
								<s:hidden name="recruiterToken" />
								<td width="20%">
									<s:textfield name="recruiterName" size="25" readonly="true" />
								</td>
								<td colspan="2"></td>
							</tr>
							</s:if>
							<s:else>
							<tr>
								<td width="22%"><label class="set" name="assignRecruiter"
									id="assignRecruiter" ondblclick="callShowDiv(this);"><%=label.get("assignRecruiter")%></label>
								:<font color="red">*</font> </td>
								<s:hidden name="recruiterId" />
								<s:hidden name="recruiterToken" />
								<td width="20%">
									<s:textfield name="recruiterName" size="25" readonly="true" />
								</td>
								<td colspan="2" align="left">
									<img src="../pages/images/recruitment/search2.gif" height="16"
									align="absmiddle" width="17" theme="simple"
									onclick="validateRecruiter();">
								</td>
							</tr>
							</s:else>
							
							<tr>
								<td width="22%">
									<label class="set" id="selectGroup" ondblclick="callShowDiv(this);"><%=label.get("selectGroup")%></label>
									 : <font color="red">*</font>
								</td>
								<td width="20%">
									<s:textfield name="groupName" size="25" readonly="true" />
									<s:hidden name="groupId" />
									<s:hidden name="groupDesc" />
									<s:hidden name="groupAbbr" />
								</td>
								<td colspan="2" align="left">
									<img src="../pages/images/recruitment/search2.gif" height="16"
									align="absmiddle" width="17" theme="simple"
									onclick="javascript:callsF9(500,325,'ConductInterview_f9GroupAction.action'); ">
								</td>
							</tr>
						</table>
					</td>
				</tr>

				<!-- BEGIN Group Selection -->
				<s:if test="afterGroupSelectionFlag">
				<tr>
					<td colspan="4" width="100%">
					<table width="98%" border="0" cellpadding="1" cellspacing="1"
						class="formbg" id="evalDtls">
						<%!int i = 1;%>
						<tr>
							<td colspan="4">
								<strong class="formhead">Evaluation Details</strong>
							</td>
						</tr>
						<tr>
							<td class="formth" width="20%">
								<label class="set" id="parametersLabel" ondblclick="callShowDiv(this);"><%=label.get("parametersLabel")%></label> 
							</td>
							<td class="formth" width="40%">
								<label class="set" id="descriptionLabel" ondblclick="callShowDiv(this);"><%=label.get("descriptionLabel")%></label>
							</td>
							<td class="formth" width="10%">
								<label class="set" id="ratingLabel" ondblclick="callShowDiv(this);"><%=label.get("ratingLabel")%></label>
							</td>
							<td class="formth" width="30%">
								<label class="set" id="remarksLabel" ondblclick="callShowDiv(this);"><%=label.get("remarksLabel")%></label>
							</td>
						</tr>
						<%!int y = 0;%>

						<%
							try {
								Object[][] dataObj = (Object[][]) request.getAttribute("dataObj");
								y = dataObj.length;
								if (dataObj!=null && dataObj.length > 0) {
									for (int k = 0; k < dataObj.length; k++) {
						%>
						<tr>
							<td width="20%"><input type="hidden" name="parameterCode"
								value="<%=String.valueOf(dataObj[k][0])%>" /> <%=String.valueOf(dataObj[k][1])%></td>
							<td width="40%"><%=String.valueOf(dataObj[k][2])%></td>
							
							<s:if test="onHoldInterviewFlag">	
							<td width="10%" align="center">
								<s:select name="parameterName"
								list="#{'0':'NA','1':'1','2':'2','3':'3','4':'4','5':'5'}"
								id="<%="evalType"+(i++)%>" onchange="calculateScore();" 
								value="<%= Utility.checkNull(String.valueOf(dataObj[k][3]))%>" /> 
								<strong	class="formHead">/5</strong>
							</td>
							<td width="30%">
								<s:textarea theme="simple" cols="40" rows="3" name="evalRateComments" value="<%=Utility.checkNull(String.valueOf(dataObj[k][4]))%>" />
							</td>
							</s:if>
							
							<s:else>
							<td width="10%" align="center">
								<s:select name="parameterName"
								list="#{'0':'NA','1':'1','2':'2','3':'3','4':'4','5':'5'}"
								id="<%="evalType"+(i++)%>" onchange="calculateScore();" /> 
								<strong	class="formHead">/5</strong>
							</td>
							<td width="30%">
								<s:textarea theme="simple" cols="40" rows="3" name="evalRateComments" />
							</td>
							</s:else>	
						</tr>
						<%
								}
							}
							} catch (Exception e) {
								//e.printStackTrace();
							}
						%>
						
						<s:if test="parameterMappedOrNotFlag">
						<tr>
							<td colspan="4" align="center">
								<font color="red">There is no any parameter mapped to <s:property value="groupName" /> group</font>
							</td>
						</tr>
						</s:if>
						
						<tr>
							<td width="20%"><label class="set" name="evalSc" id="evalSc"
								ondblclick="callShowDiv(this);"><%=label.get("evalSc")%></label>
							:</td>
							<td width="29%">
								<s:textfield name="evalScore" maxlength="3" size="25" readonly="true" />
							</td>
							<td width="19%"><label class="set" name="perc" id="perc"
								ondblclick="callShowDiv(this);"><%=label.get("perc")%></label> :</td>
							<td width="25%">
								<s:textfield name="percentage" maxlength="3" size="25" readonly="true" />
							</td>
						</tr>
						<tr>
							<td colspan="4">
								Note : NA : <label class="set" name="notapplicable"
								id="notapplicable" ondblclick="callShowDiv(this);"><%=label.get("notapplicable")%></label>   , 1 : <label class="set" name="first" id="first"
								ondblclick="callShowDiv(this);"><%=label.get("first")%></label> , 2 : <label class="set" name="second" id="second"
								ondblclick="callShowDiv(this);"><%=label.get("second")%></label> , 3 : <label class="set" name="third" id="third"
								ondblclick="callShowDiv(this);"><%=label.get("third")%></label> , 4 : <label class="set" name="fourth" id="fourth"
								ondblclick="callShowDiv(this);"><%=label.get("fourth")%></label> , 5 : <label class="set" name="fifth" id="fifth"
								ondblclick="callShowDiv(this);"><%=label.get("fifth")%></label>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<%
				i = 1;
				%>
				</s:if>
				<!-- END Group Selection -->
				
				<tr>
					<td colspan="4" width="100%">
					<table width="98%" border="0" cellpadding="1" cellspacing="1" class="formbg" id="othrInfoDtls">
						<tr>
							<td colspan="4"><strong class="formhead">Other
							Information Details</strong></td>
						</tr>
						<tr>
							<td width="23%"><label class="set" name="curCtc" id="curCtc"
								ondblclick="callShowDiv(this);"><%=label.get("curCtc")%></label>
							:</td>
							<td width="19%" style="width: 70"><s:textfield
								name="currentCTC" onkeypress="return numbersWithDot();"
								maxlength="10" size="25" /></td>
							<td width="10%"></td>
							<td width="22%"><label class="set" name="relocate"
								id="relocate" ondblclick="callShowDiv(this);"><%=label.get("relocate")%></label>
							:</td>
							<td width="20%"><s:select name="readyReloc"
								list="#{'N':'No','Y':'Yes'}" cssStyle="width:153px" /></td>
							<td width="6%"></td>
						</tr>
						<tr>
							<td width="23%"><label class="set" name="negoCtc"
								id="negoCtc" ondblclick="callShowDiv(this);"><%=label.get("negoCtc")%></label>
							:</td>
							<td width="19%"><s:textfield name="negoCTC"
								onkeypress="return numbersWithDot();" maxlength="10" size="25" /></td>
							<td width="10%"></td>
							<td width="22%"><label class="set" name="intSts" id="intSts"
								ondblclick="callShowDiv(this);"><%=label.get("intSts")%></label>
							:<font color="red"> * </font></td>
							<td width="20%"><s:select name="intrStatus"
								list="#{'':'-------Select-------','S':'Selected','R':'Rejected','O':'OnHold'}"
								cssStyle="width:153px" onchange="showOfferCheck()" /></td>
							<td width="6%"></td>
						</tr>
						<tr>
							<td width="23%" nowrap="nowrap"><label class="set"
								name="expJoin" id="expJoin" ondblclick="callShowDiv(this);"><%=label.get("expJoin")%></label>
							:</td>
							<td width="19%"><s:textfield name="exptdJoinDate"
								onkeypress="return numbersWithHiphen();" maxlength="10"
								size="25" /></td>
							<td width="10%" align="left"><a
								href="javascript:NewCal('paraFrm_exptdJoinDate','DDMMYYYY');">
							<img src="../pages/images/Date.gif" class="iconImage" height="16"
								align="absmiddle" width="16"> </a></td>
							<td width="22%"><label class="set" name="employee.type"
								id="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
							:</td>
							<td width="20%"><s:textfield name="empType" readonly="true"
								size="25" /></td>
							<s:hidden name="empTypeCode" />
							<td width="6%"><img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="17" theme="simple"
								onclick="javascript:callsF9(500,325,'ConductInterview_f9EmployeeType.action'); "></td>
						</tr>
						<tr>
							<td width="23%"><label class="set" name="constr" id="constr"
								ondblclick="callShowDiv(this);"><%=label.get("constr")%></label>
							:</td>
							<td width="19%"><s:textarea name="constraints" cols="26"
								cssStyle="width:150" /></td>
							<td width="10%" align="left"><img
								src="../pages/images/zoomin.gif" height="12" align="absmiddle"
								width="12" theme="simple"
								onclick="javascript:callWindow('paraFrm_constraints','constr','','500','500');"></td>
							<td width="22%"><label class="set" name="rec.comments"
								id="comm" ondblclick="callShowDiv(this);"><%=label.get("rec.comments")%></label>
							:</td>
							<td width="20%"><s:textarea name="comments" cols="26"
								cssStyle="width:153" /></td>
							<td width="6%"><img src="../pages/images/zoomin.gif"
								height="12" align="absmiddle" width="12" theme="simple"
								onclick="javascript:callWindow('paraFrm_comments','comm','','500','500');"></td>
						</tr>
						
						<tr>
							<td width="23%"><label class="set" name="strength" id="strength"
								ondblclick="callShowDiv(this);"><%=label.get("strength")%></label>
							:</td>
							<td width="19%"><s:textarea name="strength" cols="26"
								cssStyle="width:150" /></td>
							<td width="10%" align="left"><img
								src="../pages/images/zoomin.gif" height="12" align="absmiddle"
								width="12" theme="simple"
								onclick="javascript:callWindow('paraFrm_strength','strength','','500','500');"></td>
							<td width="22%"><label class="set" name="weakness"
								id="weakness" ondblclick="callShowDiv(this);"><%=label.get("weakness")%></label>
							:</td>
							<td width="20%"><s:textarea name="weakness" cols="26"
								cssStyle="width:153" /></td>
							<td width="6%"><img src="../pages/images/zoomin.gif"
								height="12" align="absmiddle" width="12" theme="simple"
								onclick="javascript:callWindow('paraFrm_weakness','weakness','','500','500');"></td>
						</tr>
						<tr>
							<td width="23%" style="width: 190"><label class="set"
								name="make" id="make" ondblclick="callShowDiv(this);"><%=label.get("make")%></label>
							:</td>
							<td width="19%"><s:checkbox name="makeOffer" id="makeOffer"
								onclick="callMakeOffer();" /></td>
							<td width="10%"></td>
							<td width="22%"><label class="set" name="forwrd" id="forwrd"
								ondblclick="callShowDiv(this);"><%=label.get("forwrd")%></label>
							:</td>
							<td width="20%"><s:checkbox name="fwdNxtRnd" id="fwdNxtRnd"
								onclick="showRoundNo();" /></td>
							<td width="6%"></td>
						</tr>
						<tr>
							<td colspan="6">
							<div id="roundText" style="display: none;">
							<table width="100%" border="0">
								<tr>
									<td width="23%"><label class="set" name="rndType"
										id="rndType" ondblclick="callShowDiv(this);"><%=label.get("rndType")%></label>
									:<font color="red"> * </font></td>
									<td width="19%">
										<!-- 
											<s:textfield name="nxtRoundNo" size="25" maxlength="20" />
										 --> 
										<s:select headerKey="-1" headerValue="-- Select --" name="nxtRoundNo" cssStyle="width:100" list="tmap" />									 
									</td>
									<td width="10%"></td>

									<td width="22%" nowrap="nowrap"><label class="set"
										name="selIntr" id="selIntr" ondblclick="callShowDiv(this);"><%=label.get("selIntr")%></label>
									: <font color="red"> * </font> <s:hidden name="selectInterId" /></td>
									<td width="20%"><s:textfield name="selectInter" size="25"
										readonly="true" /> <s:hidden name="hEmpToken" /><s:hidden
										name="hBranch" /><s:hidden name="hDesg" /></td>
									<td width="6%"><img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="17" theme="simple"
										onclick="javascript:callsF9(500,325,'ConductInterview_f9SelectInterviewer.action'); "></td>
								</tr>
							</table>
							</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>

			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="2">
						<!--Table 7-->
						<tr>
							<td><input type="button" class="token" theme="simple"
								value="Submit" onclick="return submitValidation();" /> <!--<input type="button" class="token" theme="simple"
								value="Print" onclick="return validation();" />
							<input type="button" class="token" theme="simple"
								value="Print Blank Form" onclick="return validation();" />--> <s:if
								test="checkFlag">
								<s:submit cssClass="cancel"
									action="InterviewDetails_showInterviewCandList" theme="simple"
									value="  Cancel" onclick="return validation();"
									cssClass="cancel" />
							</s:if> <s:elseif test="addNewFlag">
								<s:submit cssClass="cancel" theme="simple"
									action="InterviewDetails_showInterviewCandList"
									value="  Cancel" onclick="return validation();"
									cssClass="cancel" />
							</s:elseif> <s:else>
								<s:submit cssClass="cancel" theme="simple"
									action="ConductInterview_input" value="  Cancel"
									onclick="return validation();" cssClass="cancel" />
							</s:else></td>
						</tr>
					</table>
					<!--Table 7--></td>
				</tr>
			</table>
			<!--Table 1--></td>
		</tr>
		<!--Schedule Test-->
	</table>
	<!-- Final Table -->
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js">
</script>
<script>
if(document.getElementById('fwdNxtRnd').checked) {
	document.getElementById('roundText').style.display = "";
}	

if (document.getElementById('paraFrm_onHoldCalculation').value == 'onHoldData') {
	calculateScore();
	showOfferCheck();
}
	
function showRoundNo()
{
		if(document.getElementById('fwdNxtRnd').checked)
		{
			document.getElementById('roundText').style.display = "";
			document.getElementById('makeOffer').checked = false;
		}
		else
		{
			document.getElementById('makeOffer').checked = true; 
			document.getElementById('roundText').style.display = "none";
		}
}

function callMakeOffer()
{
	if(document.getElementById('makeOffer').checked)
	{
		document.getElementById('fwdNxtRnd').checked = false;
		showRoundNo();
	}
	else
	{
		document.getElementById('fwdNxtRnd').checked = true;
		showRoundNo();
	}
}

function submitValidation()
{
  try
  {	
	var reqCode=document.getElementById('paraFrm_requisitionName').value;	
	var cand=document.getElementById('paraFrm_candName').value;
	var intRnd=document.getElementById('paraFrm_intRound').value;	
	var intDt=trim(document.getElementById('paraFrm_intDate').value);
	var time=trim(document.getElementById('paraFrm_intTime').value);
	
	if(reqCode==""){
			alert("Please select "+document.getElementById('reqs.code').innerHTML.toLowerCase());
			document.getElementById('paraFrm_requisitionName').focus();
			return false;
	}
	
	if(cand==""){
			alert("Please select "+document.getElementById('cand.name').innerHTML.toLowerCase());
			document.getElementById('paraFrm_candName').focus();
			return false;
	}
	
	if(intRnd=="-1"){
		alert("Please select "+document.getElementById('intRnd').innerHTML.toLowerCase());
		document.getElementById('paraFrm_intRound').focus();
		return false;
	}
	
	if(intDt==""){
		alert("Please select or enter "+document.getElementById('date').innerHTML.toLowerCase());
		document.getElementById('paraFrm_intDate').focus();
		return false;
	}
	
	if (time==""){
		alert("Please enter "+document.getElementById('time').innerHTML.toLowerCase());
		document.getElementById('paraFrm_intTime').focus();
		return false;
	}
	
	var addNewInterviewFlag = document.getElementById('paraFrm_addNewFlag').value;
	if(addNewInterviewFlag == 'true') {
		var recruiterIdVar = trim(document.getElementById('paraFrm_recruiterId').value);
		if (recruiterIdVar=="") {
	 		alert("Please select "+document.getElementById('assignRecruiter').innerHTML.toLowerCase());
	 		document.getElementById('paraFrm_recruiterName').focus();
			return false;
		}
	} 
	
	var groupName = trim(document.getElementById('paraFrm_groupName').value);
	if (groupName == "") {
		alert("Please " + document.getElementById('selectGroup').innerHTML.toLowerCase());
		document.getElementById('paraFrm_groupName').focus();
		return false;
	}
	
	if(!validateDate('paraFrm_intDate','date'))
		return false;
	if(!dateCheckLessThanToday('paraFrm_intDate','date'))
		return false;
	if(!validateTime('paraFrm_intTime','time'))
		return false;
	if(!validateDate('paraFrm_exptdJoinDate','expJoin'))
		return false;
	if(!dateCheckWithToday('paraFrm_exptdJoinDate','expJoin'))
		return false;
	if(document.getElementById('paraFrm_intrStatus').value=="")
	{
		alert("Please select interview status");
		document.getElementById('paraFrm_intrStatus').focus();
		return false;
	}
	var cons=document.getElementById('constr').innerHTML.toLowerCase();
  	var comments1=document.getElementById('comm').innerHTML.toLowerCase();
  	var constraints=document.getElementById('paraFrm_constraints').value;
  	var comments=document.getElementById('paraFrm_comments').value;
  	if(constraints != "" && constraints.length > 500){
			alert("Maximum length of "+cons+" is 500 characters.");
			return false;
	}
	if(comments != "" && comments.length > 500){
			alert("Maximum length of "+comments1+" is 500 characters.");
			return false;
	}
	if(document.getElementById('paraFrm_intrStatus').value=="S"){
		if(!document.getElementById('makeOffer').checked && !document.getElementById('fwdNxtRnd').checked)
		{
			alert("Please either select make offer or forward to next round");
			return false;
		}
		if(document.getElementById('fwdNxtRnd').checked)
		{
			var round=trim(document.getElementById('paraFrm_nxtRoundNo').value);
			if(round=="-1"){
						alert("Please select "+document.getElementById('rndType').innerHTML.toLowerCase());
						document.getElementById('paraFrm_nxtRoundNo').focus();
						return false;
			}	
			if(document.getElementById('paraFrm_selectInterId').value==""){
						alert("Please "+document.getElementById('selIntr').innerHTML.toLowerCase());
						return false;
			}	
			
			//fieldName = ["paraFrm_nxtRoundNo","paraFrm_selectInterId"];
			//labelName = ["round type","interviewer"];
			//flag = ["enter","select"];
			//if(!checkMandatory(fieldName,labelName,flag))
			//	return false;
		}
	}
		var con=confirm('Do you really want to submit the candidate evaluation ?');
		 if(con){
		    document.getElementById('paraFrm').action="ConductInterview_submitRec.action";
	  		document.getElementById('paraFrm').submit();
		    } else{
		     return false;
		    }
	//return true;
 } //End of try
 catch(e)
 {
 	alert("Exception occurred======>"+e);
 	return false;
 }
	
}

function showOfferCheck(){
	try {
		var status = document.getElementById('paraFrm_intrStatus').value;
	if(document.getElementById('paraFrm_intrStatus').value=="R"){
		document.getElementById('makeOffer').disabled = true;
		document.getElementById('makeOffer').checked = false;
		document.getElementById('fwdNxtRnd').disabled = true;
		document.getElementById('fwdNxtRnd').checked = false;
	}else if(document.getElementById('paraFrm_intrStatus').value=="O"){
		document.getElementById('makeOffer').disabled = true;
		document.getElementById('makeOffer').checked = false;
		document.getElementById('fwdNxtRnd').disabled = true;
		document.getElementById('fwdNxtRnd').checked = false;
	}else{
		document.getElementById('makeOffer').disabled = false;
		document.getElementById('fwdNxtRnd').disabled = false;
	}
	} catch(e) {
		//alert("showOfferCheck >>"+e);
	}
	
}


function validatePostCandidate()
{
	if(document.getElementById('paraFrm_requisitionCode').value == "")
	{
		alert("Please select "+document.getElementById('reqs.code').innerHTML.toLowerCase());
		return false;
	}
	return true;
}

function calculateScore() {
	try {
		var looplength = '<%=y%>';
		var evalScore=0;
		var evalPercentage=0;
		var total=0;
		for(i=1;i<=looplength;i++) {
			if(document.getElementById("evalType"+i).value!=0) {
				evalScore+=eval(document.getElementById("evalType"+i).value);
				total+=5;
			}
		}
		evalPercentage=(evalScore/total)*100;
		if(isNaN(evalPercentage)){
			evalPercentage=0;
		}
	
		document.getElementById('paraFrm_evalScore').value = evalScore;
		document.getElementById('paraFrm_percentage').value = Math.round(evalPercentage); 
	} catch(e) {
		//alert("Error in calculateScore >>"+e);
	}
}

function validateCandidate()
{
	if(document.getElementById('paraFrm_requisitionCode').value == "")
	{
		alert("Please select "+document.getElementById('reqs.code').innerHTML.toLowerCase());
		return false;
	}
	callsF9(500,325,'ConductInterview_f9candidate.action');
}

function validateRecruiter()
{
	if(document.getElementById('paraFrm_requisitionCode').value == "")
	{
		alert("Please select "+document.getElementById('reqs.code').innerHTML.toLowerCase());
		return false;
	}
	callsF9(500,325,'ConductInterview_f9Recruiter.action');
}

function viewInterviewDetails(reqCode,candCode)
{
	window.open('InterviewDetails_showConductedIntDetails.action?reqCode='+reqCode+'&candCode='+candCode,'','top=100,left=200,resizable=yes,scrollbars=yes,width=700,height=400');
}

 function createNewRequisition(){
		 //alert('dfsgfdsgsdf');
		 var createNewReqFlag = "candEval";
		 document.getElementById("paraFrm").action='EmployeeRequi_addNew.action?flag='+createNewReqFlag;
	 	 document.getElementById("paraFrm").submit();
		 
		 }
</script>