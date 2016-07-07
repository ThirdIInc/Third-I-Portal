<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="OfferDetails" id="paraFrm" theme="simple">
	<s:hidden name="offerCode" />
	<s:hidden name="addcandFlag" />
	<s:hidden name="buttonName" />
	 <s:hidden name="offerLetterRegCode"/>
	<s:hidden name="hiddenOfferCode" />
	<s:hidden name="hiddenRequisitionCode"/>
	<table class="formbg" width="100%">
		<!-- main table -->
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Offer
					Details </strong></td>
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
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" colspan="2"><s:submit value=" Save"
						cssClass="save" action="OfferDetails_save"
						onclick="return validateSave();" /> 
						<!--	<input type="button" value="Print Offer" class="token"/>	-->
						<!--	<input type="button" value="Preview Offer" class="token"/>	--> 
						<input type="button" value="Preview Offer in Doc" class="token" onclick="return validatePreviewForTemplate()" /> 
						<input type="button" value="Preview Offer in PDF" class="token" onclick="return validatePreviewForTemplatePDF()" />
						<s:submit id="mydisplay1" value="Email Offer" cssClass="token" onclick="return validatePreviewForEmailTemplate()" action="OfferDetails_emailoffer" /> 
						<input type="button" value=" Cancel" class="cancel" onclick="return cancel();" /> 
						<!--	
								<s:if test="addcandFlag">
			     					<s:submit value="    Cancel" cssClass="del" action="CreateOffer_input"/>
			     				</s:if>
			     				<s:else>
			     					<s:submit value="    Cancel" cssClass="del" action="OfferDetails_reset"/>
			     				</s:else>
	              		-->
	               </td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>


		<tr>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<!-- table 1 -->
				<tr>
					<td colspan="2" width="50%"><strong class="formhead">Requisition
					Details </strong></td>
					<td colspan="2" width="50%"><strong class="formhead">Candidate
					Details</strong></td>
				</tr>


				<tr>
					<td width="50%" class="txt" colspan="2">

					<table width="100%" border="0" height="165" align="center"
						cellpadding="0" cellspacing="0" class="formbg">
						<tr>
							<td width="100%">
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								<tr>
									<td width="45%"><label class="set" name="reqs.code"
										id="requisition.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label>
									&nbsp:<font color="red" size="2">*</font></td>
									<s:hidden name="requisitionCode" />
									<s:hidden name="reqStatus" id="reqStatus"/> 
									
									
									
									
									<s:hidden name="hiringManager" />
									<td width="35%" align="right"><s:textfield name="requisitionName"
										size="25" readonly="true" /></td>
									<td width="20%"><s:if test="addcandFlag"></s:if> <s:else>
										<img class="iconImage"
											src="../pages/images/recruitment/search2.gif" width="15"
											height="16"
											onclick="javascript:callsF9(500,325,'OfferDetails_f9RequisitionAction.action');" />
									</s:else></td>
								</tr>


								<s:if test="addcandFlag"></s:if>
								<s:else>
									<tr>
										<td width="45%"></td>
										<td width="35%"><input type="button" class="token"
											value="Create New Requisition" id = "CreateNewRequisition" onclick="createNewRequisition();" />
										</td>
										<td width="20%">&nbsp;</td>
									</tr>
								</s:else>


								<tr>
									<td width="45%"><label class="set" name="position"
										id="position" ondblclick="callShowDiv(this);"><%=label.get("position")%></label>
									:</td>
									<s:hidden name="positionCode" />
									<td width="35%"><s:textfield name="position" size="25"
										readonly="true" /></td>
									<td width="20%"><img class="iconImage"
										src="../pages/images/recruitment/search2.gif" width="15"
										height="15"
										onclick="javascript:callsF9(500,325,'OfferDetails_f9actionDesg.action');" />
									</td>

								</tr>

								<tr>
									<td width="45%"><label class="set" name="division"
										id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
									:</td>
									<s:hidden name="divisionCode" />
									<td width="35%"><s:textfield name="division" size="25"
										readonly="true" /></td>
									<td width="20%"><img class="iconImage"
										src="../pages/images/recruitment/search2.gif" width="15"
										height="15"
										onclick="javascript:callsF9(500,325,'OfferDetails_f9actionDiv.action');" />
									</td>
								</tr>
								<tr>
									<td width="45%"><label class="set" name="branch"
										id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
									:</td>
									<s:hidden name="branchCode" />
									<td width="35%"><s:textfield name="branch" size="25"
										readonly="true" /></td>
									<td width="20%"><img class="iconImage"
										src="../pages/images/recruitment/search2.gif" width="15"
										height="15"
										onclick="javascript:callsF9(500,325,'OfferDetails_f9actionBrn.action');" />
									</td>
								</tr>

								<tr>
									<td width="45%"><label class="set" name="department"
										id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
									:</td>
									<s:hidden name="deptCode" />
									<s:hidden name = "requiredByDate"/>
									<td width="35%"><s:textfield name="department"
										size="25" readonly="true" /></td>
									<td width="20%"><img class="iconImage"
										src="../pages/images/recruitment/search2.gif" width="15"
										height="15"
										onclick="javascript:callsF9(500,325,'OfferDetails_f9actionDept.action');" />
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>

					</td>

					<td width="50%" class="txt" colspan="2">
					<table width="100%" border="0" cellpadding="0" height="165"
						cellspacing="0" class="formbg">
						<!-- table 3 -->
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								<tr>
									<td width="20%"><label class="set" name="cand.name"
										id="candidate.name" ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label>
									&nbsp:<font color="red" size="2">*</font></td>
									<s:hidden name="candidateCode" />
									<td width="10%" nowrap="nowrap"><s:textfield
										name="candidateName" size="25" readonly="true" /> <s:if
										test="addcandFlag"></s:if> <s:else>
										<img class="iconImage"
											src="../pages/images/recruitment/search2.gif" width="15"
											height="16" onclick="postCandidate('image');" />
									</s:else></td>
								</tr>

								<s:if test="addcandFlag"></s:if>
								<s:else>
									<tr>
										<td width="44%">&nbsp;</td>
										<td width="6%"><s:submit value="Post Candidate   "
											cssClass="token" action="OfferDetails_toPostResume"
											onclick="return postCandidate('button');" /></td>
									</tr>
								</s:else>


								<tr>
									<td width="44%" nowrap="nowrap"><label class="set"
										name="expected.date" id="expected.date"
										ondblclick="callShowDiv(this);"><%=label.get("expected.date")%></label>:</td>
									<td width="6%" nowrap="nowrap"><s:textfield
										name="joiningDate" size="25" maxlength="10"
										onblur="return validateDate('paraFrm_joiningDate','expected.date');"
										onkeypress="return numbersWithHiphen();" /> <s:a
										href="javascript:NewCal('paraFrm_joiningDate','DDMMYYYY');">
										<img class="iconImage"
											src="../pages/images/recruitment/Date.gif" width="16"
											height="16" border="0" align="absmiddle" />
									</s:a></td>
								</tr>

								<tr>
									<td width="44%"><label class="set" name="offer.date"
										id="offer.date" ondblclick="callShowDiv(this);"><%=label.get("offer.date")%></label>:</td>
									<td nowrap="nowrap" width="6%"><s:textfield
										name="offerDate" size="25" maxlength="10"
										onblur="return validateDate('paraFrm_offerDate','offer.date');"
										onkeypress="return numbersWithHiphen();" /> <s:a
										href="javascript:NewCal('paraFrm_offerDate','DDMMYYYY');">
										<img class="iconImage"
											src="../pages/images/recruitment/Date.gif" width="16"
											height="16" border="0" align="absmiddle" />
									</s:a></td>
								</tr>

								<tr>
									<td width="44%"><label class="set" name="current.ctc"
										id="current.ctc" ondblclick="callShowDiv(this);"><%=label.get("current.ctc")%></label>
									:</td>
									<td width="6%" nowrap="nowrap"><s:textfield
										name="currentCtc" size="25"
										onblur="return valCTC('paraFrm_currentCtc','current.ctc');"
										onkeypress="return numbersWithDot();" maxlength="10" /></td>
								</tr>



								<tr>
									<td width="44%"><label class="set" name="negotiated.ctc"
										id="negotiated.ctc" ondblclick="callShowDiv(this);"><%=label.get("negotiated.ctc")%></label>
									:</td>
									<td width="6%" nowrap="nowrap"><s:textfield
										name="negotiatedCtc" size="25"
										onkeypress="return numbersWithDot();"
										onblur="return valCTC('paraFrm_negotiatedCtc','negotiated.ctc');"
										maxlength="10" /></td>
								</tr>
								
								
								<tr>
							    	<td width="44%">
							    		<label  class = "set" name="candidateEmailIDLabel" id="candidateEmailIDLabel" 
									           	ondblclick="callShowDiv(this);"><%=label.get("candidateEmailIDLabel")%></label> : 
									</td>
									<td width="6%" nowrap="nowrap"  >
									      	<s:textfield name="candidateEmailID" size="25" readonly="true"/>
									</td>
							    </tr>
							    			
							</table>
							</td>
						</tr>
					</table>
					<!-- table 3 --></td>
				</tr>
			</table>
			<!-- table 1 --></td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				id="evalDtls" class="formbg">
				<!-- table 6 -->
				<tr>
					<td colspan="6"><strong class="formhead">Recruiter
					Selection</strong></td>
				</tr>
				<s:hidden name="hBranch" />
				<s:hidden name="hDesg" />
				<s:if test="addcandFlag">
					<tr>
						<td width="21%"><label class="set" name="rec.name"
							id="rec.name" ondblclick="callShowDiv(this);"><%=label.get("rec.name")%></label>
						:</td>
						<s:hidden name="recruiterId" />
						<s:hidden name="recruiterToken" />
						<td width="20%">
							<s:textfield name="recruiterName" size="25" readonly="true" />
						</td>
						<td colspan="4" align="left">&nbsp;</td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td width="21%"><label class="set" name="assignRecruiter"
							id="assignRecruiter" ondblclick="callShowDiv(this);"><%=label.get("assignRecruiter")%></label>
						:<font color="red">*</font></td>
						<s:hidden name="recruiterId" />
						<s:hidden name="recruiterToken" />
						<td width="20%">
							<s:textfield name="recruiterName" size="25" readonly="true" />
						</td>
						<td><img src="../pages/images/recruitment/search2.gif"
							height="16" align="absmiddle" width="17" theme="simple"
							onclick="validateRecruiter('image');"></td>
						<td colspan="3" align="left">&nbsp;</td>
					</tr>
				</s:else>
				<!-- KEEP INFORMED TO BLOCK STARTS -->

				<tr>
					<td width="21%"><strong>Keep Informed To : </strong></td>
					<td width="20%"><s:hidden name="employeeId" /> <s:hidden
						name="employeeToken" /> 
						<s:textfield name="employeeName" size="25" readonly="true" /></td>
					<td><img src="../pages/common/css/default/images/search2.gif"
						class="iconImage" width="16" height="15"
						onclick="javascript:getKeepInformedEmp();" /></td>
					<td><s:submit name="" value=" Add" cssClass=" add"
						action="OfferDetails_addKeepInformedEmpList"
						onclick="return callKeepInformed();" /></td>
					<td colspan="2" align="left">&nbsp;</td>
				</tr>

				<tr valign="top">
					<td colspan="3" rowspan="5">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">

						<%
							int counter11 = 0;
							int counter2 = 0;
						%>

						<s:iterator value="keepInformedList" status="stat">
							<tr>
								<td width="10%"><%=++counter11%><s:hidden name="serialNo" /></td>
								<td width="60%">
									<s:hidden name="keepInformedEmpName" /><s:property value="keepInformedEmpName"  />
									<s:hidden name="keepInformedEmpId"  />
								</td>
								<td width="30%">
									<a href="#"	onclick="callForRemove(<%=counter11%>);">Remove</a>
								</td>
							</tr>
							<%
							counter2 = counter11;
							%>
						</s:iterator>
						<%
						counter2 = 0;
						%>
					</table>
					</td>
					<td></td>
				</tr>
				<!-- KEEP INFORMED TO BLOCK ENDS -->
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<!-- table 4 -->
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">

						<tr>
							<td width="23%"><label class="set" name="job.desc"
								id="job.descc" ondblclick="callShowDiv(this);"><%=label.get("job.desc")%></label>
							&nbsp;<font color="red" size="2">*</font></td>
							<td width="20%"><s:textfield name="jobDescription" size="25"
								maxlength="50" /></td>
							<s:hidden name="jobCode" />
							<td width="6%" align="left"><img class="iconImage"
								src="../pages/images/recruitment/search2.gif" width="15"
								height="16"
								onclick="javascript:callsF9(500,325,'OfferDetails_f9JobDescAction.action');" /></td>
							<td width="30%">&nbsp;</td>
							<td width="15%"><input type="button"
								id="DefineSalaryStructure" value="Define Salary Structure"
								class="token" style="width: 150px"
								onclick="callSalaryStructure()" /></td>
							<td width="20%">&nbsp;</td>
						</tr>


						<tr>
							<td width="23%"><label class="set" name="roles.res"
								id="roles.res" ondblclick="callShowDiv(this);"><%=label.get("roles.res")%></label>
							:</td>
							<td width="20%"><s:textarea name="rolesResponsibility"
								rows="3" cols="24" /></td>
							<td width="7%" valign="bottom" align="left"><img
								src="../pages/images/zoomin.gif" height="12" align="absmiddle"
								width="12" theme="simple"
								onclick="javascript:callWindow('paraFrm_rolesResponsibility','roles.res','','500','500');"></td>

							<td width="25%"><label class="set" name="offered.ctc"
								id="offered.ctc" ondblclick="callShowDiv(this);"><%=label.get("offered.ctc")%></label>
							:</td>
							<td width="15%" nowrap="nowrap"><s:textfield
								name="offeredCtc" size="25"
								onblur="return valCTC('paraFrm_offeredCtc','offered.ctc');"
								onkeypress="return numbersWithDot();" maxlength="10" /></td>
							<td width="20%">&nbsp;</td>
						</tr>

						<tr>
							<td width="23%"><label class="set" name="hiring.mgr"
								id="hiring.mgr" ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:textfield
								name="hiringMgr" size="25" readonly="true" /></td>
							<s:hidden name="hiringMgrCode" />
							<td width="7%" align="left"><img class="iconImage"
								src="../pages/images/recruitment/search2.gif" width="15"
								height="15"
								onclick="javascript:callsF9(500,325,'OfferDetails_f9HireMgrAction.action');" /></td>
							<td width="25%"><label class="set" name="joining.date"
								id="joining.date" ondblclick="callShowDiv(this);"><%=label.get("joining.date")%></label>
							&nbsp:<font color="red" size="2">*</font></td>
							<td width="15%" nowrap="nowrap"><s:textfield
								name="expJoiningDate" size="25" maxlength="10"
								onblur="return validateDate('paraFrm_expJoiningDate','joining.date');"
								onkeypress="return numbersWithHiphen();" /></td>
							<td width="20%"><s:a
								href="javascript:NewCal('paraFrm_expJoiningDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>
						<tr>
							<td width="23%"><label class="set" name="reporting.to"
								id="reporting.to" ondblclick="callShowDiv(this);"><%=label.get("reporting.to")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:textfield
								name="reportingTo" size="25" readonly="true" /></td>
							<s:hidden name="reportingToCode" />
							<td width="7%" align="left"><img class="iconImage"
								src="../pages/images/recruitment/search2.gif" width="15"
								height="16"
								onclick="javascript:callsF9(500,325,'OfferDetails_f9ReportingAction.action');" /></td>
							<td width="25%"><label class="set" name="reporting.to.admin"
								id="reporting.to.admin" ondblclick="callShowDiv(this);"><%=label.get("reporting.to.admin")%></label>
							:</td>
							<td width="15%" nowrap="nowrap"><s:textfield
								name="reportingToAdmin" size="25" readonly="true" /></td>
							<s:hidden name="reportingToAdminCode" />
							<td width="18%" valign="bottom"><img class="iconImage"
								src="../pages/images/recruitment/search2.gif" width="15"
								height="16"
								onclick="javascript:callsF9(500,325,'OfferDetails_f9ReportingAdminAction.action');" /></td>
						</tr>
						<tr>
							<td width="23%"><label class="set" name="grade" id="grade"
								ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:textfield name="grade"
								size="25" readonly="true" /></td>
							<s:hidden name="gradeCode" />
							<td width="7%" align="left"><img class="iconImage"
								src="../pages/images/recruitment/search2.gif" width="15"
								height="16"
								onclick="javascript:callsF9(500,325,'OfferDetails_f9GradeAction.action');" /></td>
							<td width="25%"><label class="set" name="joining.formality"
								id="joining.formality" ondblclick="callShowDiv(this);"><%=label.get("joining.formality")%></label>
							:</td>
							<td width="15%" nowrap="nowrap"><s:textarea
								name="testRequirements" rows="2" cols="24" /></td>
							<s:hidden name="testReqCode" />
							<td width="20%" valign="bottom"><img class="iconImage"
								src="../pages/images/recruitment/search2.gif" width="15"
								height="16" onclick="callCheckList();" /></td>
						</tr>

						<tr>
							<td width="23%"><label class="set" name="probation.period"
								id="probation.period" ondblclick="callShowDiv(this);"><%=label.get("probation.period")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:checkbox name="probation"
								onclick="showMonths();" /> <span id="months"><s:textfield
								name="months" size="10" maxlength="3" />Months</span></td>
							<td width="7%" align="left">&nbsp;</td>
							<td width="25%"><label class="set" name="signing.authority"
								id="signing.authority" ondblclick="callShowDiv(this);"><%=label.get("signing.authority")%></label>
							: <font color="red" size="2">*</font></td>
							<td width="15%" nowrap="nowrap"><s:textfield
								name="signingAuthority" size="25" readonly="true" /></td>
							<s:hidden name="authorityCode" />
							<td width="20%"><img class="iconImage"
								src="../pages/images/recruitment/search2.gif" width="15"
								height="16"
								onclick="javascript:callsF9(500,325,'OfferDetails_f9SigningAuthAction.action');" /></td>
						</tr>

						<tr>
							<td width="23%"><label class="set" name="bg.check"
								id="bg.check" ondblclick="callShowDiv(this);"><%=label.get("bg.check")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:checkbox
								name="backgroundCheck" /></td>
							<td width="7%" align="left">&nbsp;</td>
							<td width="25%"><label class="set" name="designation"
								id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
							:</td>
							<td width="15%" nowrap="nowrap"><s:textfield
								name="designation" size="25" readonly="true" /></td>
							<td width="20%"></td>
						</tr>

						<tr>
							<td width="23%"><label class="set" name="offer.status"
								id="offer.status" ondblclick="callShowDiv(this);"><%=label.get("offer.status")%></label>
							&nbsp:<font color="red" size="2">*</font></td>
							<td width="20%" nowrap="nowrap"><s:select name="offerStatus"
								cssStyle="width:154" headerKey="" headerValue="Select"
								list="#{'D':'Due', 'S':'Send For Approval', 'I':'Issued', 'OA':'Accepted', 'OR':'Rejected', 'C':'Canceled'}" /></td>
							<td width="7%" align="left">&nbsp;</td>
							<td width="25%"><label class="set" name="employee.type"
								id="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
							&nbsp:<font color="red" size="2">*</font></td>
							<td width="15%"><s:textfield name="empType" size="25"
								readonly="true" /></td>
							<s:hidden name="empTypeCode" />
							<td width="20%"><img class="iconImage"
								src="../pages/images/recruitment/search2.gif" width="15"
								height="16"
								onclick="javascript:callsF9(500,325,'OfferDetails_f9EmpTypeAction.action');" /></td>
						</tr>

						<tr>
							<td width="23%"><label class="set" name="remarks"
								id="remarks" ondblclick="callShowDiv(this);"><%=label.get("remarks")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:textarea name="remarks"
								rows="2" cols="24" /></td>
							<td width="7%" valign="bottom" align="left"><img
								src="../pages/images/zoomin.gif" height="12" align="absmiddle"
								width="12" theme="simple"
								onclick="javascript:callWindow('paraFrm_remarks','remarks','','500','500');"></td>

							<td width="25%"><label class="set" name="cand.constraints"
								id="cand.constraints" ondblclick="callShowDiv(this);"><%=label.get("cand.constraints")%></label>
							:</td>
							<td width="15%" nowrap="nowrap"><s:textarea
								name="candConstraints" rows="2" cols="24" /></td>
							<td width="10%" valign="bottom"><img
								src="../pages/images/zoomin.gif" height="12" align="absmiddle"
								width="12" theme="simple"
								onclick="javascript:callWindow('paraFrm_candConstraints','cand.constraints','','500','500');"></td>
						</tr>

					<!-- 
						<tr>
							<td width="23%"><label class="set"
								name="offer.emailtemplate" id="offer.emailtemplate"
								ondblclick="callShowDiv(this);"><%=label.get("offer.emailtemplate")%></label>
							:<font color="red" size="2">*</font></td>

							<td width="20%" nowrap="nowrap"><s:textfield
								name="emailtemplate" size="25" readonly="true" /></td>
							<s:hidden name="emailtemplateCode" />
							<td width="7%" align="left"><img class="iconImage"
								src="../pages/images/recruitment/search2.gif" width="15"
								height="16"
								onclick="javascript:callsF9(500,325,'OfferDetails_f9OfferEmailTemplate.action');" /></td>
							
							<td width="23%">
								<label class="set" name="offer.template" id="offer.template" ondblclick="callShowDiv(this);"><%=label.get("offer.template")%></label>
								&nbsp:<font color="red" size="2">*</font>
							</td>
							<td width="20%" nowrap="nowrap">
								<s:textfield name="template" size="25" readonly="true" />
							</td>
							<s:hidden name="templateCode" />
							<td width="7%"><img class="iconImage"
								src="../pages/images/recruitment/search2.gif" width="15"
								height="16"
								onclick="javascript:callsF9(500,325,'OfferDetails_f9OfferTemplate.action');" />
							</td>
						</tr>
					 -->	
						
						
						<tr>
							<td width="23%">
								<label class="set" name="offer.template" id="offer.template" ondblclick="callShowDiv(this);"><%=label.get("offer.template")%></label>
								&nbsp:<font color="red" size="2">*</font>
							</td>
							<td width="20%" nowrap="nowrap">
								<s:textfield name="template" size="25" readonly="true" />
							</td>
							<s:hidden name="templateCode" />
							<td width="10%"><img class="iconImage"
								src="../pages/images/recruitment/search2.gif" width="15"
								height="16"
								onclick="javascript:callsF9(500,325,'OfferDetails_f9OfferTemplate.action');" />
							</td>
							
							<td width="23%"><label class="set" name="attachmentOfAnnexure"
								id="attachmentOfAnnexure" ondblclick="callShowDiv(this);"><%=label.get("attachmentOfAnnexure")%></label>
							:</td>
							<td width="15%" nowrap="nowrap">
								<s:hidden name="dataPath" />
								<s:textfield name="annextureFileName" size="25" readonly="true" cssStyle="background-color: #F2F2F2;" />
							</td>
							<td width="10%" valign="bottom">&nbsp;</td>
						</tr>
						
						<tr>
							<td width="23%">&nbsp;</td>
							<td width="20%">&nbsp;</td>
							<td width="7%">&nbsp;</td>
							<td colspan="3" align="right">
								<input type="button" value="Upload File" class="token" onclick="uploadFile('annextureFileName');" />
								<input type="button" value="Show File" class="token" onclick="viewAttachedFile();" />
							</td>
						</tr>
						
					</table>
					</td>
				</tr>
			</table>
			<!-- table 4 --></td>
		</tr>

		<tr>
			<s:hidden name="myofferStatus" />
			<td width="100%" colspan="3"><s:submit value=" Save"
				cssClass="save" action="OfferDetails_save"
				onclick="return validateSave();" /> 
			<!--	<input type="button" value="Print Offer" class="token"/>	-->
			<!--	<input type="button" value="Preview Offer" class="token"/>	-->
			<input type="button" value="Preview Offer in Doc" class="token"
				onclick="return validatePreviewForTemplate()" /> 
			<input type="button" value="Preview Offer in PDF" class="token"
				onclick="return validatePreviewForTemplatePDF()" /> 	
			<s:submit id="mydisplay" value="Email Offer" cssClass="token"
				onclick="return validatePreviewForEmailTemplate()"
				action="OfferDetails_emailoffer" /> 
			<input type="button" value=" Cancel" class="cancel" onclick="return cancel();" /> 
			<!--	<s:if test="addcandFlag">
			     		<s:submit value="    Cancel" cssClass="del" action="CreateOffer_input"/>
			     	</s:if>
			    	<s:else>
			    		<s:submit value="    Cancel" cssClass="del" action="OfferDetails_reset"/>
			    	</s:else>
	     	-->
	     	</td>
		</tr>

	</table>
	<!-- main table -->

	<s:hidden name="checkRemove"></s:hidden>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script>
showMonths();
function showMonths(){
	try {
			var probationCheck =  document.getElementById('paraFrm_probation').checked;
			//alert("probationCheck : " + probationCheck);
			if(probationCheck) {
				document.getElementById('months').style.display = '';
			} else {
				document.getElementById('months').style.display = 'none';
			}
		var myofferstatus = trim(document.getElementById('paraFrm_offerStatus').value);
		//alert("myofferstatus : " + myofferstatus);
		var myvalue= trim(document.getElementById('paraFrm_offerCode').value);
		//alert("myvalue : " + myvalue);
		if (myvalue != "" && myofferstatus != "") {
			document.getElementById('paraFrm_myofferStatus').value = myofferstatus;
			if(document.getElementById('paraFrm_myofferStatus').value=='I') {		
				document.getElementById('mydisplay').style.display = '';
				document.getElementById('mydisplay1').style.display = '';
			} else {		
				document.getElementById('mydisplay').style.display = 'none';
				document.getElementById('mydisplay1').style.display = 'none';
			}
		} 
		else {
				document.getElementById('mydisplay').style.display = 'none';
				document.getElementById('mydisplay1').style.display = 'none';
		}
	}
	catch(e) {
		alert("Error occured in showMonths : "+ e);
	}	
}
	
	function callCheckList(){
		window.open('','new','top=100,left=300,width=400,height=400,scrollbars=yes,status=no,resizable=no');
		
	 	document.getElementById("paraFrm").target = "new";
		document.getElementById("paraFrm").action = 'OfferDetails_joiningChecklist.action'; 
	  	document.getElementById("paraFrm").submit();
	  	document.getElementById("paraFrm").target = "main";
	}
	
	function validateSave(){
		var offerDate = document.getElementById("paraFrm_offerDate").value;
		var joiningDate = document.getElementById("paraFrm_expJoiningDate").value;
		var reqStatus = document.getElementById("reqStatus").value;
		
		
		
		var fieldName = ["paraFrm_requisitionName", "paraFrm_candidateName"];
		var lableName = ["requisition.code", "candidate.name"];
		var flag      = ["select", "select"];
		
		if(!validateBlank(fieldName, lableName, flag))return false;
		
		var recruiterIDVar = document.getElementById('paraFrm_recruiterId').value;
		if(recruiterIDVar == ""){
			alert("Please select "+document.getElementById('assignRecruiter').innerHTML.toLowerCase());
			document.getElementById('paraFrm_recruiterName').focus();
			return false;
		}
		
		
		var newFieldName = ["paraFrm_jobDescription", "paraFrm_expJoiningDate" ,
							"paraFrm_signingAuthority","paraFrm_offerStatus","paraFrm_empType" ];
		var newLableName = ["job.descc", "joining.date" ,
							"signing.authority","offer.status","employee.type" ];
		var newFlag      = ["select", "enter/select","select" ,"select","select"];
		
		if(!validateBlank(newFieldName, newLableName, newFlag))return false;
		
		if(document.getElementById("paraFrm_joiningDate").value != ""){
			if(!validateDate("paraFrm_joiningDate", "expected.date"))return false;
		}
		
		if(offerDate != ""){
			if(!validateDate("paraFrm_offerDate","offer.date"))return false;
		}
		
		var rnr = document.getElementById('paraFrm_rolesResponsibility').value;
	  	
		if(rnr != "" && rnr.length > 1000){
			alert("Maximum length of <%=label.get("roles.res")%> is 1000 characters.");
			return false;
		}
		
		var joiningForm = document.getElementById('paraFrm_testRequirements').value;
	  	
		if(joiningForm != "" && joiningForm.length > 500){
			alert("Maximum length of <%=label.get("joining.formality")%> is 500 characters.");
			return false;
		}
		
		var fieldName2 = ["paraFrm_offerStatus"];
		var lableName2 = ["offer.status"];
		var flag2      = ["select"];
		
		if(!validateBlank(fieldName2, lableName2, flag2))return false;
		
		if(joiningDate != ""){
			if(!validateDate("paraFrm_expJoiningDate", "joining.date"))return false;
		}
		
		if(!dateDifferenceEqual(offerDate, joiningDate, "paraFrm_expJoiningDate","offer.date","joining.date"))
		return false;
		
		if(document.getElementById("paraFrm_probation").checked){
			if(document.getElementById("paraFrm_months").value == ""){
				alert("Please enter months");
				document.getElementById("paraFrm_months").focus();
				return false;
			}
		}
		
		
		
		var remarks = document.getElementById('paraFrm_remarks').value;
	  	
		if(remarks != "" && remarks.length > 500){
			alert("Maximum length of <%=label.get("remarks")%> is 500 characters.");
			return false;
		}
		
		var constraints = document.getElementById('paraFrm_candConstraints').value;
	  	
		if(constraints != "" && constraints.length > 500){
			alert("Maximum length of <%=label.get("cand.constraints")%> is 500 characters.");
			return false;
		}
		
		
		
		if(!valCTC('paraFrm_currentCtc','current.ctc'))
		return false;
		if(!valCTC('paraFrm_negotiatedCtc','negotiated.ctc'))
		return false;
		if(!valCTC('paraFrm_offeredCtc','offered.ctc'))
		return false;
		
		/*
			var EmailTemplateCode = document.getElementById('paraFrm_emailtemplateCode').value;
			if(EmailTemplateCode == ''){
				alert("Please select "+document.getElementById('offer.emailtemplate').innerHTML.toLowerCase()); 
				return false;
			}
		*/
		
		var TemplateCode = document.getElementById('paraFrm_templateCode').value;
		
		if(TemplateCode == ''){
			alert("Please select "+document.getElementById('offer.template').innerHTML.toLowerCase()); 
			return false;
		}
		///alert(document.getElementById('paraFrm_offerStatus').value);
		var offerStatus = document.getElementById('paraFrm_offerStatus').value;
		if(reqStatus == 'C' && (offerStatus=='D' || offerStatus=='I' || offerStatus=='S'|| offerStatus=='OA')){
			alert("All vacancies for this requisition are already processed."); 
			return false;
		}
		
		//var fieldName3 = ["paraFrm_emailtemplate"];
		//var lableName3 = ["offer.emailtemplate"];
		//var flag3      = ["select"];
		
		//if(!validateBlank(fieldName3, lableName3, flag3))return false;
		
		//var fieldName1 = ["paraFrm_template"];
		//var lableName1 = ["offer.template"];
		//var flag1      = ["select"];
		
		//if(!validateBlank(fieldName1, lableName1, flag1))return false;
		
		var con=confirm('Do you really want to save the offer details ?');
		 if(con){
			return true;
		 }
		 else{
			return false;
		 }
		
		return true;
	}
	
	
	function validatePreviewForTemplate(){
		var fieldName = ["paraFrm_requisitionName", "paraFrm_candidateName","paraFrm_signingAuthority"];
		var lableName = ["requisition.code", "candidate.name", 
							"signing.authority"];
		var flag      = ["select", "select", "select"];
		
		if(!validateBlank(fieldName, lableName, flag))return false;		
		if(document.getElementById("paraFrm_template").value=="") {
			alert("Please "+document.getElementById("offer.template").innerHTML.toLowerCase());
			document.getElementById("paraFrm_template").focus();
			return false;
		}
		callReport("OfferDetails_previewoffer.action");
	}
	
	function validatePreviewForTemplatePDF(){
		var fieldName = ["paraFrm_requisitionName", "paraFrm_candidateName","paraFrm_signingAuthority"];
		var lableName = ["requisition.code", "candidate.name", 
							"signing.authority"];
		var flag      = ["select", "select", "select"];
		
		if(!validateBlank(fieldName, lableName, flag))return false;	
			
		if(document.getElementById("paraFrm_template").value=="") {
			alert("Please "+document.getElementById("offer.template").innerHTML.toLowerCase());
			document.getElementById("paraFrm_template").focus();
			return false;
		}
			
			
		callReport("OfferDetails_previewOfferPDF.action");
	}
		
	function postCandidate(type){
		var fieldName = ["paraFrm_requisitionName"];
		var lableName = ["requisition.code"];
		var flag      = ["select"];
		
		if(!validateBlank(fieldName, lableName, flag))return false;	
		
		if(type == "image")
			javascript:callsF9(500,325,'OfferDetails_f9CandidateAction.action');
		
		return true;
	}
	
	function validateRecruiter(type){
		var fieldName = ["paraFrm_requisitionName"];
		var lableName = ["requisition.code"];
		var flag      = ["select"];
		
		if(!validateBlank(fieldName, lableName, flag))return false;	
		
		if(type == "image")
			javascript:callsF9(500,325,'OfferDetails_f9Recruiter.action');
		
		return true;
	}
	
	function validatePreviewForEmailTemplate(){
		var fieldName = ["paraFrm_requisitionName", "paraFrm_candidateName","paraFrm_signingAuthority"];
		var lableName = ["requisition.code", "candidate.name", 
							"signing.authority"];
		var flag      = ["select", "select", "select"];
		
		if(!validateBlank(fieldName, lableName, flag))return false;	
		
		var con = confirm("Do you really want to email this offer?");
   			if(con)
   			{
   				return true;
   			}else {
   				return false;
   			}	
	}
	
	
	function cancel(){
		var buttonName = document.getElementById("paraFrm_buttonName").value;
		var actionName = "";
		
		if(buttonName == "int"){
			document.getElementById('paraFrm').action = "SchdInterviewList_interviewStatus.action?status=Y";
			document.getElementById('paraFrm').submit();
		}
		else{
		 	document.getElementById('paraFrm').action = "CreateOffer_input.action";
			document.getElementById('paraFrm').submit();
		}
		
		return false;
	}
	
	function callSalaryStructure(){
		var offer = document.getElementById('paraFrm_offeredCtc').value;
		var reqCode = document.getElementById('paraFrm_requisitionCode').value;
		var candCode = document.getElementById('paraFrm_candidateCode').value;
		var req=document.getElementById('requisition.code').innerHTML.toLowerCase();
		var can=document.getElementById('candidate.name').innerHTML.toLowerCase();
		var offerctc=document.getElementById('offered.ctc').innerHTML.toLowerCase();
				
		if(reqCode==""){
			alert('Please select '+req);
			return false;
		}
		if(candCode==""){
			alert('Please select '+can);
			return false;
		}
		if(offer==""){
			alert('Please enter ' +offerctc);
			return false;
		}
		else if(!valCTC('paraFrm_offeredCtc','offered.ctc'))
		return false;
		/*
		 else if(isNaN(offer)) { 
		  alert("Only numbers are allowed in CTC field.");
		 document.getElementById('paraFrm_offeredCtc').focus();
		 }
		*/
		//document.getElementById('paraFrm').target="_blank";
  		//callsF9(800,525,'OfferSalaryStructure_f9salgradeAction.action');
  		//document.getElementById('paraFrm').action ='OfferDetails_defineSalaryStructure.action?offer='+offer+'&reqCode='+reqCode+'&candCode='+candCode ;
  		//document.getElementById('paraFrm').submit();
  		//document.getElementById('paraFrm').target="main";
		
		window.open('OfferDetails_defineSalaryStructure.action?offer='+offer+'&reqCode='+reqCode+'&candCode='+candCode,'salaryStructure','top=100,left=200,resizable=yes,scrollbars=yes,width=900,height=400');
	}
	
	
	
	function valCTC(ctcfieldname,ctclabelname)
	{
	
	var amount=document.getElementById(ctcfieldname).value;
	var amountlabel=document.getElementById(ctclabelname).innerHTML.toLowerCase();
	
		
	if(trim(amount)!=""){
		if(isNaN(amount)) { 
		  alert("Only numbers are allowed in "+amountlabel+" field.");
		  document.getElementById(ctcfieldname).focus();
		  return false;
		 
		 }	
		 }
		  return true;
		 }
		 
		  function createNewRequisition(){
		 //alert('dfsgfdsgsdf');
		 var createNewReqFlag = "offerFlag";
		 document.getElementById("paraFrm").action='EmployeeRequi_addNew.action?flag='+createNewReqFlag;
	 	 document.getElementById("paraFrm").submit();
		 
		 }
		 callMe();
		 function callMe()
		 { 
		   document.getElementById('CreateNewRequisition').value="Create New Requisition";
		    document.getElementById('CreateNewRequisition').style.width=152;
		    document.getElementById('DefineSalaryStructure').value="Define Salary Structure";
		    document.getElementById('DefineSalaryStructure').style.width=150;
		 }
		 
	 function getKeepInformedEmp() {
		try {
			callsF9(500,325,'OfferDetails_f9KeepInformedEmployee.action');
		}
		catch(e) {
			alert(e);
		} 
	}
	 
    function callForRemove(id) {
    	var conf=confirm("Are you sure !\n You want to Remove this record ?");
		if(conf){
			document.getElementById('paraFrm_checkRemove').value=id;
			document.getElementById('paraFrm').target="_self";
			document.getElementById("paraFrm").action="OfferDetails_removeKeepInformed.action";
	  		document.getElementById("paraFrm").submit();
	  		}	
	  		else {
	  			return false;
	  			}
	  	return true;			
    }
	    
    function callKeepInformed()	{
		var emp =document.getElementById('paraFrm_employeeId').value;
		if(emp=="") {
			alert("Please select keep informed to ");
			return false;
		}
		return true;
	}
	
	function viewAttachedFile()
	{
		var annextureFileName = document.getElementById('paraFrm_annextureFileName').value;
		if(annextureFileName == '') {
			alert('File is not attached.');
			return false;
		 }
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = "OfferDetails_viewAttachedAnnexure.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
	
	function uploadFile(fieldName) {
		//alert(fieldName);
		var dataPath = document.getElementById('paraFrm_dataPath').value;
		window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path=' + dataPath + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
	}
</script>