<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include
	file="/pages/ApplicationStudio/configAuth/authorizationChecking.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="include/javascript/sorttable.js"></script>

<s:form action="ProbationConfirmation" validate="true" id="paraFrm"
	target="main" theme="simple">
<s:hidden name="show" />
		<s:hidden name="empCode" />
		<s:hidden name="empDataflag" />
		<s:hidden name="probationCode" />
		<s:hidden name="confirmEmpflag" />
		<s:hidden name="showFlag" />
		<s:hidden name="unlockFlag" />
		<s:hidden name="lockFlag" />
		<s:hidden name="selectemployeetoken"/>
		<s:hidden name="selectemployeeName"/>
		<s:hidden name="probCode"/>
		<s:hidden name="stat"/>
		<s:hidden name="searchStatus" />

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<s:hidden name="show" />

			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Probation 
					Confirmation </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="confirmEmpflag">
		<tr>
			<td colspan="3"><input type="button" name="Add" class="add" value="Add New" onclick="return callSearch();"/></td>
		</tr>
		
		<tr>
		 	<td colspan="3">
		 		<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td>Employee</td>
					<td align="left"><s:hidden name="empTokenNo"/><s:hidden name="empeeCode"/><s:textfield
						size="15" name="empeeName"  ></s:textfield> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17" theme="simple"
						onclick="javascript:callsF9(500,325,'ProbationConfirmation_f9empaction.action');"></td>
					
					<td widht="10%">Branch</td>
					<td align="left"><s:hidden name="branchCode"/><s:textfield
						size="15" name="branchName" readonly="true"></s:textfield> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17" theme="simple"
						onclick="javascript:callsF9(500,325,'ProbationConfirmation_f9branchaction.action');"></td>
					
					<td widht="10%">Department</td>
					<td align="left"><s:hidden name="deptId"/><s:textfield
						size="15" name="deptName" readonly="true"></s:textfield> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17" theme="simple"
						onclick="javascript:callsF9(500,325,'ProbationConfirmation_f9departmentaction.action');"></td>
						
					<td><s:submit   cssClass="search"
							action="ProbationConfirmation_searchRecord"
							value="    Search " />
							<s:submit   cssClass="clear"
							action="ProbationConfirmation_clearsearchRecord"
							value="    Clear " />
							
							</td>
										
				</tr>
			</table>
		 	</td>
		</tr>
		</s:if>
		<s:if test="confirmEmpflag">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="2">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formbg">
							<tr>
								<td>
								<table width="99%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="27" class="formtxt"><a  onclick="setStatus('P');"
											href="#">Pending
										Confirmation Due List</a> | <a    onclick="setStatus('C');"
											href="#">Confirmed
										List</a> | <a    onclick="setStatus('E');"
											href="#">Extended
										Probation List</a> | <a   onclick="setStatus('T');"
											href="#">Terminated
										List</a></td>
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
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="30%" class="formtxt"><strong> <%
 	String status = (String) request.getAttribute("stat");
 	if (status != null) {
 		out.println(status);
 	} else {
 		out.println("Pending For Probation Confirmation List");
 	}
 %> </strong></td>
 				<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
 	int totalPage = (Integer) request.getAttribute("totalPage");
 	int pageNo = (Integer) request.getAttribute("pageNo");
 %> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'ProbationConfirmation_callstatus.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'ProbationConfirmation_callstatus.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'ProbationConfirmation_callstatus.action');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'ProbationConfirmation_callstatus.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'ProbationConfirmation_callstatus.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
						<!-- PAGING STARTS-->
					
					</tr>
				</table>
				</td>
			</tr>
			<!-- PAGING ENDS-->
			<!-- TABLE FOR Confirmation Due List STARTS -->
			<tr>
				<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="sortable">
					<tr class="td_bottom_border">
						<s:hidden name="myPage" id="myPage" />
						<!--  <td width="10%" valign="top" class="formth" nowrap="nowrap"><label
							name="srno" id="srno" ondblclick="callShowDiv(this);"></label></td>-->
						<td width="20%" valign="top" class="formth" nowrap="nowrap"><label
							name="empToken" id="empToken" ondblclick="callShowDiv(this);"><%=label.get("empToken")%></label></td>
						<td width="25%" valign="top" class="formth"><label
							name="empName" id="empName" ondblclick="callShowDiv(this);"><%=label.get("empName")%></label></td>
						<td width="20%" valign="top" class="formth"><label
							name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label></td>
						<td width="20%" valign="top" class="formth"><label
							name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label></td>

						<td width="20%" valign="top" class="formth"><label name="doj"
							id="doj" ondblclick="callShowDiv(this);"><%=label.get("doj")%></label></td>

						<s:if test="confirmFlag">
							<td width="20%" valign="top" class="formth" nowrap="nowrap"><label
								name="confirmdt" id="confirmdt" ondblclick="callShowDiv(this);"><%=label.get("confirmdt")%></label></td>
						</s:if>
						<s:if test="extenProbFlag">
							<td width="20%" valign="top" class="formth" nowrap="nowrap"><label
								name="extendProbDate" id="extendProbDate"
								ondblclick="callShowDiv(this);"><%=label.get("extendProbDate")%></label></td>
						</s:if>
						<s:if test="terminatedFlag">
							<td width="20%" valign="top" class="formth" nowrap="nowrap"><label
								name="terminateddt" id="terminateddt"
								ondblclick="callShowDiv(this);"><%=label.get("terminateddt")%></label></td>
						</s:if>
						
							<td width="10%" valign="top" class="formth" nowrap="nowrap">
						View Evaluation</td>
						

						<td width="10%" valign="top" class="formth" nowrap="nowrap">
						View Details</td>
					</tr>
					<s:if test="noData">
						<tr>
							<td width="100%" colspan="7" align="center"><font
								color="red">No Data To Display</font></td>
						</tr>
					</s:if>
					<%
					int y = 1;
					%>
					<%!int z = 0;%>

					<!-- For paging -->
					<%
					int count = 0;
					%>
					<%!int d = 0;%>

					<%
						int i = 0;
						int cn = pageNo * 20 - 20;
					%>
					<s:iterator value="list">
						<tr class="sortableTD">
						<s:hidden name="ittprobationCode" /><s:hidden
								name="ittprobationStatus" />
							<!-- <td width="10%" align="left" class="sortableTD" nowrap="nowrap">
							</td> -->
							<td class="sortableTD" width="20%" nowrap="nowrap"><s:property
								value="empToken" /><s:hidden name="employeeId" /></td>
							<td class="sortableTD" width="25%" nowrap="nowrap"><s:property
								value="empName" /></td>
							<td class="sortableTD" width="20%" nowrap="nowrap"><s:property
								value="branch" /></td>
							<td class="sortableTD" width="20%" nowrap="nowrap"><s:property
								value="department" /></td>
							<td class="sortableTD" width="20%" align="center" nowrap="nowrap"><s:property
								value="dateOfJoining" /></td>

							<s:if test="confirmFlag">
								<td class="sortableTD" width="20%" align="center"
									nowrap="nowrap"><s:property value="dateOfConfirm" /></td>
							</s:if>
							<s:if test="extenProbFlag">
								<td class="sortableTD" width="20%" align="center"
									nowrap="nowrap"><s:property value="extendedProbationDate" /></td>
							</s:if>
							<s:if test="terminatedFlag">
								<td class="sortableTD" width="20%" align="center"><s:property
									value="dateOfTermination" /></td>
							</s:if>
							<s:hidden
								name="evalstatus" />
								
								<s:hidden
								name="evalCodeItt" />
								
								<td class="sortableTD" width="20%" nowrap="nowrap"> 
								<s:if test="%{evalstatus=='_H'}">
								<input type="button" class="token"
									value="View Evaluation"
									onclick="viewEvalDetails('<s:property value="employeeId"/>','<s:property value="evalCodeItt"/> ');" />
									</s:if> 
									<s:else>
										&nbsp;
									</s:else>
								</td>
							

							<td class="sortableTD" width="20%"> 
							
								
							



							<input type="button" class="token" value="View Details"
								onclick="viewDetails('<s:property value="employeeId"/>','<s:property value="ittprobationCode"/>','<s:property value="ittprobationStatus"/>');" />
							</td>

						</tr>
						<%
						y++;
						%>
					</s:iterator>
					<%
					z = y;
					%>
				</table>
				</td>
			</tr>
			<!-- TABLE FOR Confirmation Due List ENDS -->
		</s:if>
		<!-- TABLE FOR EMPLOYEE DETAILS STARTS -->

		<s:if test="empDataflag">
			<tr>
				<td width="100%"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
			</tr>
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="98%" border="0" align="center" cellpadding="0"
							cellspacing="2">
							<tr>
								<td colspan="5" class="text_head"><strong
									class="forminnerhead">Employee Details </strong></td>
							</tr>
							<tr>
								<td width="20%" colspan="1"><label name="employee"
									id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
								<td width="80%" colspan="3"><s:textfield
									name="employeeToken" theme="simple" size="10" readonly="true" />
								<s:textfield readonly="true" name="employeeName" size="86" /><s:textfield
									name="empId" /></td>
							</tr>
							<tr>
								<td width="20%" colspan="1"><label name="currentdesg"
									id="currentdesg" ondblclick="callShowDiv(this);"><%=label.get("currentdesg")%></label>:</td>
								<td width="30%" colspan="1"><s:textfield
									name="currentDesignation" size="28" readonly="true" /><s:hidden
									name="currentDesignationCode" /> <img class="iconImage"
									class="iconImage" src="../pages/images/recruitment/search2.gif"
									id="ctrlHide" height="16" align="absmiddle" width="15"
									onclick="javascript:callsF9(500,325,'ProbationConfirmation_f9CurrentDesignationaction.action');" /></td>
								<td width="20%" colspan="1"><label name="newdesg"
									id="newdesg" ondblclick="callShowDiv(this);"><%=label.get("newdesg")%></label>:</td>
								<td width="30%" colspan="1"><s:textfield
									name="newDesignation" size="25" readonly="true" /><s:hidden
									name="newDesignationCode" /> <img class="iconImage"
									class="iconImage" src="../pages/images/recruitment/search2.gif"
									id="ctrlHide" height="16" align="absmiddle" width="15"
									onclick="javascript:callsF9(500,325,'ProbationConfirmation_f9NewDesignationaction.action');" /></td>
							</tr>
							<tr>
								<td width="20%" colspan="1"><label name="currentbranch"
									id="currentbranch" ondblclick="callShowDiv(this);"><%=label.get("currentbranch")%></label>:</td>
								<td width="30%" colspan="1"><s:textfield
									name="currentBranch" size="28" readonly="true" /><s:hidden
									name="currentBranchCode" /> <img class="iconImage"
									class="iconImage" src="../pages/images/recruitment/search2.gif"
									id="ctrlHide" height="16" align="absmiddle" width="15"
									onclick="javascript:callsF9(500,325,'ProbationConfirmation_f9CurrentBranchaction.action');" /></td>
								<td width="20%" colspan="1"><label name="newbranch"
									id="newbranch" ondblclick="callShowDiv(this);"><%=label.get("newbranch")%></label>:</td>
								<td width="30%" colspan="1"><s:textfield name="newBranch"
									size="25" readonly="true" /><s:hidden name="newBranchCode" />
								<img class="iconImage" class="iconImage"
									src="../pages/images/recruitment/search2.gif" height="16"
									id="ctrlHide" align="absmiddle" width="15"
									onclick="javascript:callsF9(500,325,'ProbationConfirmation_f9NewBranchaction.action');" /></td>
							</tr>
							<tr>
								<td width="20%" colspan="1"><label name="currentdept"
									id="currentdept" ondblclick="callShowDiv(this);"><%=label.get("currentdept")%></label>:</td>
								<td width="30%" colspan="1"><s:textfield
									name="currentDepartment" size="28" readonly="true" /><s:hidden
									name="currentDepartmentCode" /> <img class="iconImage"
									class="iconImage" src="../pages/images/recruitment/search2.gif"
									height="16" align="absmiddle" width="15" id="ctrlHide"
									onclick="javascript:callsF9(500,325,'ProbationConfirmation_f9CurrentDepartmentaction.action');" /></td>
								<td width="20%" colspan="1"><label name="newdept"
									id="newdept" ondblclick="callShowDiv(this);"><%=label.get("newdept")%></label>:</td>
								<td width="30%" colspan="1"><s:textfield
									name="newDepartment" size="25" readonly="true" /><s:hidden
									name="newDepartmentCode" /> <img class="iconImage"
									id="ctrlHide" class="iconImage"
									src="../pages/images/recruitment/search2.gif" height="16"
									align="absmiddle" width="15"
									onclick="javascript:callsF9(500,325,'ProbationConfirmation_f9NewDepartmentaction.action');" /></td>
							</tr>
							<tr>
								<td width="20%" colspan="1"><label name="currentdiv"
									id="currentdiv" ondblclick="callShowDiv(this);"><%=label.get("currentdiv")%></label>:</td>
								<td width="30%" colspan="1"><s:textfield
									name="currentdivision" size="28" readonly="true" /><s:hidden
									name="currentdivisionCode" /> <img class="iconImage"
									class="iconImage" src="../pages/images/recruitment/search2.gif"
									id="ctrlHide" height="16" align="absmiddle" width="15"
									onclick="javascript:callsF9(500,325,'ProbationConfirmation_f9Currentdivaction.action');" /></td>
								<td width="20%" colspan="1"><label name="newdiv"
									id="newdiv" ondblclick="callShowDiv(this);"><%=label.get("newdiv")%></label>:</td>
								<td width="30%" colspan="1"><s:textfield name="newdivision"
									size="25" readonly="true" /><s:hidden name="newdivisionCode" />
								<img class="iconImage" class="iconImage"
									src="../pages/images/recruitment/search2.gif" height="16"
									id="ctrlHide" align="absmiddle" width="15"
									onclick="javascript:callsF9(500,325,'ProbationConfirmation_f9Newdivaction.action');" /></td>
							</tr>
							<tr>
								<td width="20%" colspan="1"><label name="currentemptype"
									id="currentemptype" ondblclick="callShowDiv(this);"><%=label.get("currentemptype")%></label>:</td>
								<td width="30%" colspan="1"><s:textfield
									name="currentEmployeeType" size="28" readonly="true" /><s:hidden
									name="currentEmployeeTypeCode" /> <img class="iconImage"
									class="iconImage" src="../pages/images/recruitment/search2.gif"
									id="ctrlHide" height="16" align="absmiddle" width="15"
									onclick="javascript:callsF9(500,325,'ProbationConfirmation_f9CurrentEmpTypeaction.action');" /></td>
								<td width="20%" colspan="1"><label name="newemptype"
									id="newemptype" ondblclick="callShowDiv(this);"><%=label.get("newemptype")%></label>:</td>
								<td width="30%" colspan="1"><s:textfield
									name="newEmployeeType" size="25" readonly="true" /><s:hidden
									name="newEmployeeTypeCode" /> <img class="iconImage"
									class="iconImage" src="../pages/images/recruitment/search2.gif"
									id="ctrlHide" height="16" align="absmiddle" width="15"
									onclick="javascript:callsF9(500,325,'ProbationConfirmation_f9NewEmpTypeaction.action');" /></td>
							</tr>

							<tr>
								<td width="20%" colspan="1"><label name="currentgrade"
									id="currentgrade" ondblclick="callShowDiv(this);"><%=label.get("currentgrade")%></label>:</td>
								<td width="30%" colspan="1"><s:textfield
									name="currentGrade" size="28" readonly="true" /><s:hidden
									name="currentGradeCode" /><img class="iconImage"
									class="iconImage" src="../pages/images/recruitment/search2.gif"
									id="ctrlHide" height="16" align="absmiddle" width="15"
									onclick="javascript:callsF9(500,325,'ProbationConfirmation_f9CurrentGradeaction.action');" /></td>
								<td width="20%" colspan="1"><label name="newgrade"
									id="newgrade" ondblclick="callShowDiv(this);"><%=label.get("newgrade")%></label>:</td>
								<td width="30%" colspan="1"><s:textfield name="newGrade"
									size="25" readonly="true" /><s:hidden name="newGradeCode" /><img
									class="iconImage" class="iconImage"
									src="../pages/images/recruitment/search2.gif" id="ctrlHide"
									height="16" align="absmiddle" width="15"
									onclick="javascript:callsF9(500,325,'ProbationConfirmation_f9NewGradeaction.action');" /></td>
							</tr>


							<tr>
								<td width="20%" colspan="1"><label name="stat" id="stat"
									ondblclick="callShowDiv(this);"><%=label.get("stat")%></label><font
									color="red">*</font> :</td>
								<td width="80%" colspan="3"><!--<s:if test="showFlag"><s:select
						theme="simple" name="status"  disabled="true"
						cssStyle="width:130" id="chkFlag"
						list="#{'-1':'Select','C':'Confirmed','E':'Extended Probation','T':'Terminated'}" onchange="checkVal();"/></s:if>-->
								<s:select theme="simple" name="status" cssStyle="width:130"
									id="chkFlag"
									list="#{'-1':'Select','C':'Confirmed','E':'Extended Probation','T':'Terminated'}"
									onchange="checkVal();" /></td>
							</tr>
							<tr id="extendDaysId">
								<td width="20%" colspan="1" nowrap="nowrap"><label
									name="extprobationdays" id="extprobationdays"
									ondblclick="callShowDiv(this);"><%=label.get("extprobationdays")%></label>
								<font color="red">*</font>:</td>
								<td width="80%" colspan="3"><s:textfield
									name="extendedProbationDays" maxlength="3"
									onkeypress="return numbersOnly();" /></td>
							</tr>
							<tr id="confirmId">
								<td colspan="1" width="20%" id=""><label name="confirmdt"
									id="confirmdt" ondblclick="callShowDiv(this);"><%=label.get("confirmdt")%></label><font
									color="red">*</font>:</td>
								<td colspan="3" width="80%"><s:textfield name="confirmDate"
									onkeypress="return numbersWithHiphen();" /><s:a
									href="javascript:NewCal('paraFrm_confirmDate','DDMMYYYY');">
									<img class="iconImage" id="ctrlHide"
										src="../pages/images/recruitment/Date.gif" width="16"
										height="16" border="0" align="absmiddle" />
								</s:a></td>
							</tr>
							<tr id="terminatedId">
								<td colspan="1" width="20%"><label name="terminateddt"
									id="terminateddt" ondblclick="callShowDiv(this);"><%=label.get("terminateddt")%></label>
								<font color="red">*</font>:</td>
								<td colspan="3" width="80%"><s:textfield
									name="terminationDate" onkeypress="return numbersWithHiphen();" /><s:a
									href="javascript:NewCal('paraFrm_terminationDate','DDMMYYYY');">
									<img class="iconImage" id="ctrlHide"
										src="../pages/images/recruitment/Date.gif" width="16"
										height="16" border="0" align="absmiddle" />
								</s:a></td>
							</tr>
							</tr>
							<tr>
								<td colspan="1" width="20%"><label name="comments"
									id="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>
								:</td>
								<td colspan="3" width="80%"><s:textarea name="comments"
									rows="3" cols="75" /></td>
							</tr>
							<tr>
								<td colspan="1" width="20%"><label name="issueFlag"
									id="issueFlag" ondblclick="callShowDiv(this);"><%=label.get("issueFlag")%></label>
								:</td>
								<td colspan="3" width="80%"><s:select theme="simple" name="issueflag" cssStyle="width:130"
									id="issueflag"
									list="#{'N':'No','Y':'Yes'}"
									 /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<!-- TABLE FOR EMPLOYEE DETAILS ENDS -->
			<!-- TABLE FOR LEAVE POLICY STARTS -->
			<tr id="confirmId1">
				<td>
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="2" class="formbg">
					<tr>
						<td colspan="1" width="22%" class="formtext"><label
							class="set" name="policy" id="policy"
							ondblclick="callShowDiv(this);"><%=label.get("policy")%></label>
						:<font color="red">*</font></td>
						<td colspan="1" width="15%"><s:textfield size="25"
							name="policyName" readonly="true" /> <s:hidden name="policyCode" />
						</td>
						<td colspan="1" width="7%"><img class="iconImage"
							id="ctrlHide" src="../pages/images/recruitment/search2.gif"
							width="16" height="15" border="0"
							onclick="javascript:callPolicy();" /></td>
						<td colspan="1" width="56%"><!--<s:if test="goFlag">
							<s:submit value=" Go "
								action="ProbationConfirmation_getEntiteldLeaves"
								onclick="return go();" cssClass="token"></s:submit>
						</s:if>--></td>
					</tr>
				</table>
				</td>
			</tr>
			<!-- TABLE FOR LEAVE POLICY ENDS -->
			<!-- TABLE FOR LEAVE DETAILS STARTS -->
			<%!
				int m = 1;
			%>
			<tr id="confirmId2">
				<s:if test="balTableFlag">
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td width="100%">
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="100%" class="formtext"><strong
										class="forminnerhead">&nbsp;&nbsp;<label
										name="balDetails" id="balDetails"
										ondblclick="callShowDiv(this);"><%=label.get("balDetails")%></label></strong></td>
								</tr>
								<tr>
									<td>
									<table width="100%" border="0" cellpadding="0" cellspacing="0"
										class="sortable">
										<tr class="td_bottom_border">
											<td width="30%" valign="top" class="formth"><label
												name="levtype" id="levtype" ondblclick="callShowDiv(this);"><%=label.get("levtype")%></label></td>
											<td width="23%" valign="top" class="formth"><label
												name="leventitle" id="leventitle"
												ondblclick="callShowDiv(this);"><%=label.get("leventitle")%></label></td>


										</tr>
										
										<%!int total = 0;%>

										<s:iterator value="leaveList" status="stat">
											<tr class="sortableTD">
												<s:hidden name="leaveCode" value="%{leaveCode}" />
												<td class="sortableTD" width="30%" nowrap="nowrap"><s:property
													value="leaveName" /></td>
												<td class="sortableTD" width="23%" align="center"><input
													type="text" name="clBal"
													value='<s:property value="clBal"/>'
													style="text-align: right;" id="clBal<%=m %>" theme="simple"
													maxlength="5" onkeypress="return numbersWithDot();" /></td>

											</tr>

											<%
											m++;
											%>
										</s:iterator>
										<%
										total = m;
										%>
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</s:if>
			</tr>
			<input type="hidden" name="count" id="count" value="<%=m-1%>" />
			<!-- TABLE FOR LEAVE DETAILS ENDS -->



			<!-- TABLE FOR SELECT CONFIRMATION TEMPLATE -->
			<!-- GENERATE CONFIRMATION TEMPLATE -->
			<!--<tr>
				<td>
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="2" class="formbg">
					<tr>
						<td colspan="1" width="22%" class="formtext" nowrap="nowrap"><label
							class="set" name="select.conf.template" id="select.conf.template"
							ondblclick="callShowDiv(this);"><%=label.get("select.conf.template")%></label>
						:<font color="red">*</font></td>
						<td colspan="1" width="15%" id="ctrlShow"><s:textfield size="25"
							name="templateName" readonly="true" /> <s:hidden
							name="templateCode" /></td>
						<td colspan="1" width="4%" nowrap="nowrap" id="ctrlShow"><img
							class="iconImage" src="../pages/images/recruitment/search2.gif"
							width="16" id="ctrlShow" height="15" border="0"
							onclick="javascript:callsF9(500,325,'ProbationConfirmation_f9generateConfTemp.action');" /></td>

						<td colspan="1" width="22%" class="formtext"><label
							class="set" name="select.auth" id="select.auth"
							ondblclick="callShowDiv(this);"><%=label.get("select.auth")%></label>
						:<font color="red">*</font></td>
						<td colspan="1" width="15%" nowrap="nowrap" id="ctrlShow"><s:textfield
							size="25" name="authoName" readonly="true" /> <s:hidden
							name="authoCode" /><s:hidden name="authoToken" /> <img
							class="iconImage" id="ctrlShow"
							src="../pages/images/recruitment/search2.gif" width="16"
							height="15" border="0"
							onclick="javascript:callsF9(500,325,'ProbationConfirmation_f9autho.action');" />
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center"><s:submit
							value=" Preview Letter "
							action="ProbationConfirmation_generateConfTemp"
							onclick="return callGenerateConfTemp();" cssClass="token"></s:submit>
							<s:submit
							value=" Send Mail "
							action="ProbationConfirmation_sendmailConfTemp"
							onclick="return callGenerateConfTemp();" cssClass="token"></s:submit>

						</td>


					</tr>
				</table>
				</td>
			</tr>
-->



			<tr>
				<td width="100%"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
			</tr>

		</s:if>
		
	</table>


</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script>

function callSearch() {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'ProbationConfirmation_f9Allemployee.action';
		document.getElementById("paraFrm").submit();
	}

function setStatus(id)
{

	document.getElementById('myPage').value="";
   document.getElementById('paraFrm_searchStatus').value=id;
   document.getElementById('paraFrm').target = "_self";
   document.getElementById('paraFrm').action = 'ProbationConfirmation_callstatus.action';
		document.getElementById('paraFrm').submit();
}

 function backFun()
 {
 
 	document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ProbationConfirmation_back.action';
		document.getElementById('paraFrm').submit();
 
 }
 
 
 function viewEvalDetails(empCode,probCode)
 {
 	document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'probationEvaluation_viewRecord.action?empCode='+empCode+'&backBtnstatus=B&probCode='+probCode;
		document.getElementById('paraFrm').submit();
 }
 
 function lockFun()
 {
	try
	{
 var probCode = document.getElementById('paraFrm_probationCode').value; 
  
 if(probCode=="")
 {
 	alert("Please save the record first");
 	return false;
 }
 
  	var conf=confirm("Do you really want to lock probation confirmation ?");
					if(conf)
					{
						document.getElementById('paraFrm').target = "_self";
						document.getElementById('paraFrm').action = 'ProbationConfirmation_lock.action';
						document.getElementById('paraFrm').submit();
					}
					else
					{
						return false;
					}
		 return true;
	}
	catch(e){ alert(e); }	 
 }
 
 function unlockFun(){
  
   		doAuthorisation('5', 'Probation', 'U');
	}
	
	function doUnlock() {
	
			document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ProbationConfirmation_unlock.action';
		document.getElementById('paraFrm').submit()
	}
  
  function resetFun()
 {
 	document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ProbationConfirmation_reset.action';
		document.getElementById('paraFrm').submit();
 
 }

function callPolicy()
	{
	
	try
		{
	var policy = document.getElementById('paraFrm_policyCode').value;
		var confirmDate =document.getElementById('paraFrm_confirmDate').value;
 	if(document.getElementById('chkFlag').value=='C')
			{
				if(confirmDate=="")
				{
				alert("Please enter confirmation date");
				return false;
				}
			}
			 /*if(policy=="0" ||policy=="")
				{
					alert("Please select policy");
					return false;
				}*/
			callsF9(500,325,'ProbationConfirmation_f9policy.action');
		return true;
		}
		catch(e)
			{
				alert(e);
			}
	
			 
			
	}
	function callGenerateConfTemp()
	{
			if(document.getElementById('paraFrm_templateCode').value==''){
			alert("Please "+document.getElementById('select.conf.template').innerHTML.toLowerCase());
			return false;
			}
			if(document.getElementById('paraFrm_authoCode').value==''){
			alert("Please "+document.getElementById('select.auth').innerHTML.toLowerCase());
			return false;
			}
			
			//callsF9(500,325,'ProbationConfirmation_f9confirmationLetter.action');
	}
	
	 
	
	function go()
		{
		try
		{
	var policy = document.getElementById('paraFrm_policyCode').value;
		var confirmDate =document.getElementById('paraFrm_confirmDate').value;
 	if(document.getElementById('chkFlag').value=='C')
			{
				if(confirmDate=="")
				{
				alert("Please enter confirmation date");
				return false;
				}
			}
			 if(policy=="0" ||policy=="")
				{
					alert("Please select policy");
					return false;
				}
		return true;
		}
		catch(e)
			{
				alert(e);
			}
	}
	

 function callOnload()
 {
 if(document.getElementById('paraFrm_confirmEmpflag').value=='true')
	{
	document.getElementById('pageNoField').focus();
	}
 
 }

	 
    checkVal();	
   function callLock()
 {
 var probCode = document.getElementById('paraFrm_probationCode').value; 
 
 var status =document.getElementById('chkFlag').value;
			var confirmDate =document.getElementById('paraFrm_confirmDate').value;
			var terminatedDate =document.getElementById('paraFrm_terminationDate').value;
			var extendProbationDays =document.getElementById('paraFrm_extendedProbationDays').value;
			
 
 //alert("probCode-======"+probCode);
 if(probCode=="")
 {
 	alert("Please save the record first");
 	return false;
 }
  		//	alert("in status"+status);
			if(status=="-1")
			{
				alert("Please select status");
				return false;
				 
			}  
			if(status=="C")
			{
				if(confirmDate=="")
				{
				alert("Please enter confirmation date");
				return false;
				}
			}
			if(status=="T")
			{
				if(terminatedDate=="")
				{
				alert("Please enter termination date");
				return false;
				}
			}
			
			if(status=="E")
			{
				if(extendProbationDays=="")
				{
				alert("Please enter extended probation days");
				return false;
				}
			}
			  	
  	if(!validateDate('paraFrm_confirmDate','confirmdt'))
  	return false;
  		if(!validateDate('paraFrm_terminationDate', 'terminateddt'))
  	return false;
 
 
 return true;
 }
 
 
function checkVal(){

try
{
	 callOnload();
	
	if(document.getElementById('paraFrm_empDataflag').value=='true')
	{
	document.getElementById('confirmId').style.display = 'none';
	document.getElementById('terminatedId').style.display = 'none';
	document.getElementById('extendDaysId').style.display = 'none';
	document.getElementById('confirmId1').style.display = 'none';
	document.getElementById('confirmId2').style.display = 'none';
	
	if(document.getElementById('chkFlag').value == 'T'){
		document.getElementById('terminatedId').style.display = '';
		document.getElementById('extendDaysId').style.display = 'none';
		document.getElementById('confirmId').style.display = 'none';
		document.getElementById('confirmId1').style.display = 'none';
		document.getElementById('confirmId2').style.display = 'none';
	}
	if(document.getElementById('chkFlag').value == 'C'){
		document.getElementById('terminatedId').style.display = 'none';
		document.getElementById('extendDaysId').style.display = 'none';
		document.getElementById('confirmId').style.display = '';
		document.getElementById('confirmId1').style.display = '';
		document.getElementById('confirmId2').style.display = '';
	}
	if(document.getElementById('chkFlag').value == 'E'){
		document.getElementById('terminatedId').style.display = 'none';
		document.getElementById('extendDaysId').style.display = '';
		document.getElementById('confirmId').style.display = 'none';
		document.getElementById('confirmId1').style.display = 'none';
		document.getElementById('confirmId2').style.display = 'none';
	}
	
	}
	
	
	
	
	}catch(e){alert(e);}
}



function viewDetails(empCode,probCode,status){
	
	try
	{
	// alert("aa");
	// alert("empCode----------"+empCode);
	// alert("probCode----------------"+probCode);
	// alert("status----------------"+status);
	
			document.getElementById('paraFrm').action = 'ProbationConfirmation_showDetails.action?employeeId='+empCode+'&probCode='+probCode+'&stat='+status;
			document.getElementById('paraFrm').submit();
		
	}catch(e){ alert(e); }
	
	}
	
	function saveFun()
		{
		//alert("in call");
		try{
		 
			var status =document.getElementById('chkFlag').value;
			var confirmDate =document.getElementById('paraFrm_confirmDate').value;
			var terminatedDate =document.getElementById('paraFrm_terminationDate').value;
			var extendProbationDays =document.getElementById('paraFrm_extendedProbationDays').value;
			var policy = document.getElementById('paraFrm_policyCode').value;
			 var count=document.getElementById("count").value;
			// alert("count = "+count);
		//	alert("in status"+status);
			if(status=="-1")
			{
				alert("Please select status");
				return false;
				 
			}  
			if(status=="C")
			{
				if(confirmDate=="")
				{
				alert("Please enter confirmation date");
				return false;
				}
		 
				if(policy=="0" ||policy=="")
				{
					alert("Please select policy");
					return false;
				}
				/*if(count=="0")
				{
					alert("Please click on GO button");
					return false;	
				}*/
				
			}
			if(status=="T")
			{
				if(terminatedDate=="")
				{
				alert("Please enter termination date");
				return false;
				}
			}
			
			if(status=="E")
			{
				if(extendProbationDays=="")
				{
				alert("Please enter extended probation days");
				return false;
				}
			}
			  	
  	if(!validateDate('paraFrm_confirmDate','confirmdt'))
  	return false;
  		if(!validateDate('paraFrm_terminationDate', 'terminateddt'))
  	return false;
  	
  	
		var comment = trim(document.getElementById('paraFrm_comments').value);
		//alert('comment	:'+eval(comment.length));
		//alert('charLimit1	:'+eval(charLimit));
		if(eval(comment.length) > 500){
			alert("Maximum length of "+ document.getElementById('comments').innerHTML.toLowerCase()+" is 500 characters.");
			document.getElementById('paraFrm_comments').focus();
			return false;
		}
  		
  		 
  
  	document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ProbationConfirmation_save.action';
		document.getElementById('paraFrm').submit();
				 
		}catch(e){alert(e);}
		}  

</script>
