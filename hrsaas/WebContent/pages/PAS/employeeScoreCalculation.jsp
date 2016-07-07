<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js">
</script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript" src="../pages/common/include/javascript/superTables.js"></script>
<script type="text/javascript" src="../pages/common/include/javascript/syncscroll.js"></script>

<s:form action="EmployeeCTCCalculation" validate="true" id="paraFrm"
	 theme="simple">
	<div align="center" id="overlay"
		style="z-index: 3; position: absolute; width: 790px; height: 920px; margin: 0px; left: 0; top: 100; background-color: #A7BEE2; filter: progid :     DXImageTransform .     Microsoft .     alpha(opacity =     15); -moz-opacity: .1; opacity: .1; display: none;">
	</div>

	<div id="confirmationDiv"
		style='position: absolute; z-index: 3; 100 px; height: 150px; visibility: hidden; top: 200px; left: 100px;'>
	</div>

	<table width="100%" border="0" align="center" cellpadding="0"
		cellspacing="0" class="formbg">
		<s:hidden name="myPage" id="myPage" />
		<s:hidden name="searchFlag" />
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee
					CTC Calculation</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%"><input type="button" class="save"
				onclick="return saveScore();" value=" Save " />&nbsp; <input
				type="button" class="token" onclick="return generateReport();"
				value=" Report " /></td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" class="formbg" border="0">
				<tr>
					<td colspan="6" class="text_head"><strong
						class="forminnerhead">Select Filters </strong></td>
				</tr>
				<tr>
					<td width="20%" nowrap="nowrap"><label name="apprId"
						id="apprId1" ondblclick="callShowDiv(this);"><%=label.get("apprId")%></label>
					<font color="red">*</font>:</td>
					<td width="30%"><s:hidden name="appraisalId" /><s:hidden
						name="promotionDate" /><s:hidden name="promoEffDate" /> <s:textfield
						name="appraisalCode" readonly="true" size="20" /> <img
						id="ctrlHide" src="../pages/images/recruitment/search2.gif"
						height="18" class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'EmployeeCTCCalculation_f9appraisal.action');">
					</td>
					<td width="20%" nowrap="nowrap"><label name="groupHead"
						id="groupHead1" ondblclick="callShowDiv(this);"><%=label.get("groupHead")%></label>
					:</td>
					<td width="30%"><s:hidden name="groupHeadId" /> <s:hidden
						name="groupHeadToken" /> <s:textfield name="groupHeadName"
						readonly="true" size="20" /> <img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'EmployeeCTCCalculation_f9groupHead.action');">
					</td>
				</tr>
				<tr>

					<td width="20%" nowrap="nowrap"><label name="manager"
						id="manager1" ondblclick="callShowDiv(this);"><%=label.get("manager")%></label>
					:</td>
					<td width="30%"><s:hidden name="managerId" /> <s:hidden
						name="managerToken" /> <s:textfield name="managerName"
						readonly="true" size="20" /> <img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'EmployeeCTCCalculation_f9manager.action');">
					</td>
					<td width="20%" nowrap="nowrap"><label name="dept" id="dept1"
						ondblclick="callShowDiv(this);"><%=label.get("dept")%></label> :</td>
					<td width="30%"><s:hidden name="departmentId" /> <s:textfield
						name="departmentName" size="20" readonly="true" /> <img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'EmployeeCTCCalculation_f9department.action');">
					</td>
				</tr>
				<tr>

					<td width="20%" nowrap="nowrap"><label name="branch"
						id="branch1" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
					:</td>
					<td width="30%"><s:hidden name="branchId" /> <s:textfield
						name="branchName" size="20" readonly="true" /> <img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage"  align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'EmployeeCTCCalculation_f9branch.action');">
					</td>
					<td width="20%" nowrap="nowrap"><label name="emp" id="emp"
						ondblclick="callShowDiv(this);"><%=label.get("emp")%></label> :</td>
					<td width="30%"><s:hidden name="empId" /> <s:textfield
						name="empName" size="20" readonly="true" /> <img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'EmployeeCTCCalculation_f9emp.action');">
					</td>
				</tr>
				
				<tr>

					<td width="20%" nowrap="nowrap"><label name="status"
						id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label>
					:</td>
					<td width="30%"><s:select name='statusFilter' list="#{'A':'All','P':'Pending','I':'In Process','F':'Finalized','M':'Mail Sent','B':'Published'}"></s:select>
					</td>
					
				</tr>

				<tr>
					<td align="center" colspan="4"><input type="button"
						class="token" onclick="return searchRecord();" value=" View " />&nbsp;
					<input type="button" class="token"
						onclick="return calculatedCtc();" value="Calculate" />&nbsp;
					<input type="button" class="token" onclick="return clearFilter();"
						value=" Clear " /></td>
						<td align="right">
					<s:if test="%{publishFlag == 'true'}">
					<input type="button" class="token"
						onclick="return publishAppraisal();" value=" Publish " />
						</s:if>
						&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>

		<s:if test="%{listType == 'pending'}">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" class="formbg">
					<tr>
						<td width="40%"><b>Employee List</b></td>
						<%
							int totalPage = 0;
							int pageNo = 0;
						%>

						<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
 	totalPage = (Integer) request.getAttribute("totalPage");
 	pageNo = (Integer) request.getAttribute("pageNo");
 %> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'EmployeeCTCCalculation_getEmployeeListByAppraisalID.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'EmployeeCTCCalculation_getEmployeeListByAppraisalID.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'EmployeeCTCCalculation_getEmployeeListByAppraisalID.action');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'EmployeeCTCCalculation_getEmployeeListByAppraisalID.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'EmployeeCTCCalculation_getEmployeeListByAppraisalID.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</tr>
					<tr>
						<td colspan="3">
						
								<table  width="100%" class="sorttable">
									<tr>

										<!--  <td class="formth" width="5%"><b><label class="set"
							name="empId" id="empId1" ondblclick="callShowDiv(this);"><%//=label.get("empId")%></label></b>
						</td>-->
										<td class="formth" width="15%"><b><label class="set"
											name="empName" id="empName1" ondblclick="callShowDiv(this);"><%=label.get("empName")%></label></b>
										</td>
										<td class="formth" width="5%"><b><label class="set"
											name="empGrade" id="empGrade1"
											ondblclick="callShowDiv(this);"><%=label.get("empGrade")%></label></b>
										</td>
										<td class="formth" width="10%"><b><label class="set"
											name="is.promotion" id="is.promotion" ondblclick="callShowDiv(this);"><%=label.get("is.promotion")%></label></b>
										</td>
										<td class="formth" width="5%"><b><label class="set"
											name="emp.PropGrade" id="emp.PropGrade"
											ondblclick="callShowDiv(this);"><%=label.get("emp.PropGrade")%></label></b>
										</td>
										<td class="formth" width="7%"><b><label class="set"
											name="score" id="score1" ondblclick="callShowDiv(this);"><%=label.get("score")%></label></b>
										</td>
										<td class="formth" width="7%"><b><label class="set"
											name="oldCtc" id="oldCtc1" ondblclick="callShowDiv(this);"><%=label.get("oldCtc")%></label></b>
										</td>
										<td class="formth" width="7%"><b><label class="set"
											name="perIncr" id="perIncr1" ondblclick="callShowDiv(this);"><%=label.get("perIncr")%></label></b>
										</td>
										<td class="formth" width="7%"><b><label class="set"
											name="newCtc" id="newCtc1" ondblclick="callShowDiv(this);"><%=label.get("newCtc")%></label></b>
										</td>
										<td class="formth" width="7%"><b><label class="set"
											name="criticalIncr1" id="criticalIncr1"
											ondblclick="callShowDiv(this);"><%=label.get("criticalIncr")%></label></b>
										</td>
										<td class="formth" width="7%"><b><label class="set"
											name="modCtc" id="modCtc1" ondblclick="callShowDiv(this);"><%=label.get("modCtc")%></label></b>
										</td>
										<td class="formth" width="10%"><b><label class="set"
											name="formula" id="formula1" ondblclick="callShowDiv(this);"><%=label.get("formula")%></label></b>
										</td>
										<td class="formth" width="10%"><b><label class="set"
											name="template.name" id="template.name1"
											ondblclick="callShowDiv(this);"><%=label.get("template.name")%></label></b>
										</td>
										
										<td class="formth" width="10%"><b><label class="set"
											name="status" id="status1" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></b>
										</td>
										
										<td class="formth" width="7%"><b><label class="set"
											name="action" id="action" ondblclick="callShowDiv(this);"><%=label.get("action")%></label></b>
										</td>
										
										<td class="formth" width="7%"><b>View</b>
										</td>
									</tr>
									<s:if test="pendingLength">
										<%
										int count = 0;
										%>
										<%
										int cn = pageNo * 20 - 20;
										%>

										<s:iterator value="pendingScoreList">
										<s:hidden name="ratingChangedFlag"></s:hidden>
											<s:if test="ratingChangedFlag"><tr bgcolor="#C0EDFE"></s:if>
											<s:else><tr></s:else>
												<!--  <td class="sortableTD" width="7%"><s:hidden name="appraisalIdItt" />
								<input type="hidden" name="employeeIdItt" id="paraFrm_employeeIdItt<%//=count%>" 
								value='<s:property value="employeeIdItt"/>' />&nbsp;
								<s:property value="employeeTokenItt" />
								
								</td>--><s:hidden  name="employeeTokenItt" ></s:hidden><s:hidden name="actionName"></s:hidden>
												<td class="sortableTD" width="10%" nowrap="nowrap">
												<s:if test="promCodeItt==''"><s:property
													value="employeeNameItt" />
												</s:if><s:else>
												<a
													href="javascript:void(0);"
													onclick="callPreviewLetter(<%=count%>,'<s:property value="employeeTokenItt" />','<s:property value="promCodeItt"/>');"><s:property
													value="employeeNameItt" /></a> </s:else><s:hidden
													name="employeeNameItt"></s:hidden> &nbsp; <input
													type="hidden" name="employeeIdItt"
													id="paraFrm_employeeIdItt<%//=count%>"
													value='<s:property value="employeeIdItt"/>' /> <s:hidden
													value="employeeTokenItt" /></td>
												<td class="sortableTD" align="center" width="7%" nowrap="nowrap"><input
													type="hidden" name="empGradeIdItt"
													id="paraFrm_empGradeIdItt<%=count%>"
													value='<s:property value="empGradeIdItt"/>' /> <s:property
													value="empGradeItt" />&nbsp; <s:hidden name="empGradeItt"></s:hidden>
												</td>
												<input type='hidden' name="promotionFlag" id="promotionFlagHidden<%=count%>" value='<s:property value='promotionFlag'/>'/>
												
												<td class="sortableTD" width="7%" align="center" nowrap="nowrap">
												<s:if test="promotionFlag">
													<input id='promotionFlag<%=count%>'  type="checkbox" checked="checked"   name="prmotionFlagHidden" onclick="callPromotion(this,'<%=count%>')" /> 
												</s:if>
												<s:else><input id='promotionFlag<%=count%>' type="checkbox" name="prmotionFlagHidden" onclick="callPromotion(this,'<%=count%>')"  /></s:else>
													</td>
												
												<!--<s:else>
												<td class="sortableTD" width="7%" align="center">
												<s:if test="promotionFlag">
													<input id='promotionFlag<%//=count%>' disabled="disabled" type="checkbox" checked="checked"  name="prmotionFlagHidden" onclick="callPromotion(this,'<%//=count%>')" /> 
												</s:if>
												<s:else><input id='promotionFlag<%//=count%>' disabled="disabled" type="checkbox" name="prmotionFlagHidden" onclick="callPromotion(this,'<%//=count%>')"  /></s:else>
													</td>
												</s:else>-->
												 
												<td class="sortableTD" width="12%" nowrap="nowrap"><input type="hidden"
													name='empNewGradeIdItt' id="paraFrm_empNewGradeIdItt<%=count%>"
													value='<s:property value="empNewGradeIdItt"/>' />&nbsp; <input
													size="5" readonly="true" type="text" value='<s:property value="empNewGradeItt"/>' id="paraFrm_empNewGradeItt<%=count%>"
													name="empNewGradeItt"/>  <!-- onclick="setFieldId(event,<%//=count%>,'EmployeeCTCCalculation_f9empGrade.action?fieldName=<%//=count%>','paraFrm_empNewGradeItt<%//=count%>');" -->
													
													<img src="../pages/images/recruitment/search2.gif"
													class="iconImage" height="16" align="center" width="16"
													onclick="setFieldId(event,<%=count%>,'EmployeeCTCCalculation_f9empGrade.action?fieldName=<%=count%>','paraFrm_empNewGradeItt<%=count%>');"></td>
												</td>
												
												<!--<s:else>
												<td class="sortableTD" width="7%" nowrap="nowrap"><input type="hidden"
													name='empNewGradeIdItt' id="paraFrm_empNewGradeIdItt<=count%>"
													value='<s:property value="empNewGradeIdItt"/>' /><s:hidden name="empNewGradeItt"></s:hidden> <s:property value="empNewGradeItt"/>
													</td>
												</td></s:else>-->
												<td class="sortableTD" align="center" width="7%"><s:property
													value="employeeScoreItt" /> <s:hidden
													name="employeeScoreItt"></s:hidden></td>
												<td class="sortableTD" align="center" width="7%"><s:property
													value="oldCtcItt" /> <s:hidden name="oldCtcItt"></s:hidden>
												</td>
												<td class="sortableTD" align="center" width="7%" nowrap="nowrap"><input
													type="hidden" name="percentIncrementItt"
													id="paraFrm_percentIncrementItt<%=count%>"
													value='<s:property value="percentIncrementItt"/>' /> <s:property
													value="percentIncrementItt" />&nbsp;</td>
												<td class="sortableTD" align="center" width="7%"><s:property
													value="newCtcItt" /> <s:hidden name="newCtcItt"></s:hidden><s:hidden
													name="promCodeItt"></s:hidden></td><input type="hidden" name="empMailId" id="empMailId<%=count%>" value="<s:property value='empMailId'/>"/>
												
												<td class="sortableTD" align="center" width="7%" nowrap="nowrap"><input
													type="text" name="criticalIncrementItt"
													id="paraFrm_criticalIncrementItt<%=count%>" size="5"
													value='<s:property value="criticalIncrementItt"/>'
													onblur="calculateModCTC(<%=count%>, '<s:property
												value="oldCtcItt" />','<s:property value="percentIncrementItt" />');" />
								
												</td>
												<td class="sortableTD" align="center" width="7%" nowrap="nowrap"><input
													type="text" name="modCtcItt"
													id="paraFrm_modCtcItt<%=count%>" size="5"
													value='<s:property value="modCtcItt"/>'
													onblur="calculateHike(<%=count%>,'<s:property
													value="oldCtcItt" />','<s:property 
												value="percentIncrementItt" />')" />
												</td>
												<td class="sortableTD" width="15%" nowrap="nowrap"><input type="text"
													name='formulaCode' id="paraFrm_formulaCode<%=count%>"
													value='<s:property value="formulaCode"/>' /> &nbsp; <input
													size="12" type="text" id="paraFrm_formulaName<%=count%>"
													name="formulaName" value='<s:property value="formulaName"/>' readonly="true" /> <img
													src="../pages/images/recruitment/search2.gif"
													class="iconImage" height="16" align="center" width="16"
													onclick="setFieldId(event,<%=count%>,'EmployeeCTCCalculation_f9formula.action?fieldName=<%=count%>','paraFrm_formulaName<%=count%>');"></td>
												</td>
												
												<!--<s:else>
												
												<td class="sortableTD" align="center" width="7%">
												<s:property value="criticalIncrementItt"/>
												<input
													type="hidden" name="criticalIncrementItt"
													id="paraFrm_criticalIncrementItt<=count%>" size="5"
													value='<s:property value="criticalIncrementItt"/>'/>
								
												</td>
												<td class="sortableTD" align="center" width="7%"><s:property value="modCtcItt"/><input
													type="hidden" name="modCtcItt"
													id="paraFrm_modCtcItt<=count%>" size="5"
													value='<s:property value="modCtcItt"/>'/>
												</td>
												<td class="sortableTD" width="10%" nowrap="nowrap"><input type="hidden"
													name='formulaCode' id="paraFrm_formulaCode<=count%>"
													value='<s:property value="formulaCode"/>' /> <s:property value="formulaName"/><input
													type="hidden" id="paraFrm_formulaName<=count%>"
													name="formulaName" value='<s:property value="formulaName"/>' readonly="true" /> </td>
												</td>
												</s:else>-->
												<td class="sortableTD" width="15%" nowrap="nowrap"><input type="hidden"
													id="paraFrm_templateCode<%=count%>" name="templateCode" value='<s:property value="templateCode"/>'/> <input
													type="text" id="paraFrm_templateName<%=count%>"
													size="12"  name="templateName" value='<s:property value="templateName"/>' readonly="true" /> <img
													src="../pages/images/recruitment/search2.gif"
													class="iconImage" height="16" align="center" width="16"
													onclick="setFieldId(event,<%=count%>,'EmployeeCTCCalculation_f9template.action?fieldName=<%=count%>','paraFrm_templateName<%=count%>');"></td>
												</td>
												<td class="sortableTD" width="7%" nowrap="nowrap">
												<s:if test="actionName==''">Pending</s:if><s:property
													value='status' /></td>
												
												<td width="7%" class="sortableTD" align="center" nowrap="nowrap"> <s:if
													test="actionName==''">&nbsp;</s:if>
													<s:elseif
													test="actionName=='In Process'">&nbsp;<!--<input type="button" class="token"
														value='Re-Process'
														onclick="callReProcess('paraFrm_formulaCode<=count%>',
									'<s:property value='employeeIdItt'/>','<s:property value='oldCtcItt'/>','<s:property value='promCodeItt'/>','<s:property value='employeeScoreItt'/>',
								'<=count%>')" />--></s:elseif>
													<s:else>
													<input type="button" class="token"
														value='<s:property value='actionName'/>'
														onclick="callAction1('<s:property value='actionName'/>','paraFrm_formulaCode<%=count%>',
									'<s:property value='employeeIdItt'/>','<s:property value='oldCtcItt'/>','<s:property value='promCodeItt'/>','<s:property value='employeeScoreItt'/>',
								'<%=count%>','<s:property value='employeeTokenItt'/>')" />
												</s:else></td>
												
												<td width="7%" class="sortableTD" align="center" nowrap="nowrap"> 
												<s:if test="promCodeItt==''">&nbsp;</s:if>
													<s:else>
													<input type="button" class="token"
														value='View'
														onclick="viewPromotion('<s:property value='employeeIdItt'/>','<s:property value='promCodeItt'/>','<s:property value='status' />')" />
												</s:else></td>
											</tr>
											<%
											count++;
											%>
										</s:iterator>
									</s:if>
									<s:if test="pendingLength"></s:if>
									<s:else>
										<tr align="center">
											<td colspan="14" class="sortableTD" width="100%"><font
												color="red">No Data to display</font></td>
										</tr>
									</s:else>
								</table>
								
						</td>
					</tr>
					<tr>
						<td colspan="3" width="100%"><input type="button"
							class="save" onclick="return saveScore();" value=" Save " /> <input
							type="button" class="token" onclick="return generateReport();"
							value=" Report " /></td>
					</tr>
				</table>
					<table width="100%" class="formbg">
						<tr>
							<td style="color: red;">Note:</td>
						</tr>
						<tr>
							<td style="color: red;">Records in Blue colour represent employee's score changed from review panel.</td>
						</tr>
						<tr>
							<td style="color: red;">Click on Employee Name to preview letter.</td>
						</tr>
					</table>
				</td>
			</tr>
		</s:if>
		<s:hidden name="pendingLength" />
		<s:hidden name="autoCode" />
	</table>
</s:form>
<script>
function searchRecord(){
		//alert("----------2---------");
		if(document.getElementById('paraFrm_appraisalCode').value==""){
			alert("Please select appraisal code");
			return false;
		}else{
			document.getElementById('paraFrm_searchFlag').value=true;  
			document.getElementById('paraFrm').action = 'EmployeeCTCCalculation_getEmployeeListByAppraisalID.action';
			document.getElementById('paraFrm').submit();
			document.getElementById("paraFrm").target = 'main';
		}
	}
	
	function saveScore(){
		if(document.getElementById('paraFrm_appraisalCode').value==""){
			alert("Please select appraisal code");
			return false;
		} else if(document.getElementById('paraFrm_pendingLength').value=="false"){
			alert("Please click view to display employee list");
			return false;
		}else{
			document.getElementById('paraFrm_searchFlag').value=true;  
			document.getElementById('paraFrm').action = 'EmployeeCTCCalculation_saveModerateScore.action';
			document.getElementById('paraFrm').submit();
			
		}	
	}
	
	function showConfirmationDiv(){
		displayConfirmDiv();
		document.getElementById('confirmationDiv').style.display='block';
	}
	
	function callPage(id, pageImg, totalPageHid, action) {
		//alert("-------------");
		pageNo = document.getElementById('pageNoField').value;	
		actPage = document.getElementById('myPage').value; 
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField').value = actPage;
			    document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField').value=actPage;
			    document.getElementById('pageNoField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }else{
		   		displayConfirmDiv();
				document.getElementById('confirmationDiv').style.display='block';
			} 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
	        }else{
		   		displayConfirmDiv();
				document.getElementById('confirmationDiv').style.display='block';
			}
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
	        }else{
		   		displayConfirmDiv();
				document.getElementById('confirmationDiv').style.display='block';
			}
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
			}else{
		   		displayConfirmDiv();
				document.getElementById('confirmationDiv').style.display='block';
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPage').value = id;
		 
	}
	
	function displayConfirmDiv(){
		document.getElementById("confirmationDiv").style.visibility = 'visible';
		document.getElementById("confirmationDiv").innerHTML = '<table width=500 height=100 border=0 class=formbg>'
		+'<tr><td class="txt"><b><center>Please select anyone of the option given below</td></tr>'
		+'<tr><td><b><center><input type="button" value="Proceed With Save" onclick="proceedWithSave();" class="token"/>'
		+'&nbsp;<input type="button" class="token" value="Proceed Without Save" onclick="proceedWithoutSave();"/>'
		+'&nbsp;<input type="button" class="token" value="Cancel" onclick="cancel();"/></td></tr></table>';
 		document.getElementById("overlay").style.display = "block";
	}
	
	function proceedWithSave(){
		try{
		//alert("---proceeding--------");
		document.getElementById("confirmationDiv").style.visibility = 'hidden';
		document.getElementById('confirmationDiv').style.display='none';
		document.getElementById("overlay").style.display = "none";
		enableBlockDiv();
		document.getElementById('paraFrm').action="EmployeeCTCCalculation_saveModerateScore.action";
		document.getElementById('paraFrm').submit();
		}
		catch(e){}
	}
	function proceedWithoutSave(){
		try{
		enableBlockDiv();
		//alert("---proceeding--2------");
		document.getElementById("confirmationDiv").style.visibility = 'hidden';
		document.getElementById('confirmationDiv').style.display='none';
		document.getElementById("overlay").style.display = "none";
		document.getElementById('paraFrm').action="EmployeeCTCCalculation_getEmployeeListByAppraisalID.action";
		document.getElementById('paraFrm').submit();
		}catch(e){}
	}
	
	function cancel(){
		document.getElementById("confirmationDiv").style.visibility = 'hidden';
		document.getElementById("overlay").style.display = "none";
	}

	function enableBlockDiv(){
		try{
			document.getElementById("overlay").style.visibility = "visible";
			document.getElementById("overlay").style.display = "block";
		}catch(e){}
	  }
	function disableBlockDiv(){
  		try{
			document.getElementById("overlay").style.visibility = "hidden";
			document.getElementById("overlay").style.display = "none";
			}catch(e){
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
			}
	  }
	  
	function generateReport(){
		document.getElementById('paraFrm').action="EmployeeCTCCalculation_genReport.action";
		document.getElementById('paraFrm').submit();
	}
	function calculateHike(count,oldCtc,oldPercentage){
		var modCTC = document.getElementById('paraFrm_modCtcItt'+count).value;
		var calculatedHike = 0;
		//alert('oldCtc='+oldCtc);
		//alert('oldPercentage='+oldPercentage);
		var newPercentage=0.0;
		//newPercentage=eval(percentVal)+eval(oldPercentage);
		//alert(modCTC+"-----"+oldCtc);
	//	alert(newPercentage);
		newPercentage=((eval(modCTC)-eval(oldCtc))/oldCtc)*100 ;
		calculatedHike=eval(newPercentage)-eval(oldPercentage);
		if(isNaN(calculatedHike)){
			calculatedHike=0;
		}
		//document.getElementById('paraFrm_criticalIncrementItt'+count).value = eval(calculatedHike).twoDecimals();
		document.getElementById('paraFrm_criticalIncrementItt'+count).value = twoDecimal(calculatedHike, 2);
	
	}
	
	function twoDecimal(num,round) {
  		if (round) return num.toFixed(2);
  		var stringNum = String(num);
  		if (stringNum.indexOf('.')==-1) return stringNum+'.00';
  		var newNum = stringNum.split('.');
  		if (newNum.len==2) {
	    newNum[1]=(newNum[1].length>2)?newNum[1].subString(0,2):newNum[1];
	  }
	  if (newNum[1].length==1) newnum[1]+="0"
	  return newNum[0]+'.'+newNum[1]
	}
	function calculatedCtc(){
		var appraisalCode=document.getElementsByName("appraisalCode")[0].value;
		if(appraisalCode==""){
			alert("Please Select Appraisal code");
		}else{
		    document.getElementById('paraFrm_searchFlag').value=true;  
			document.getElementById('paraFrm').action ="EmployeeCTCCalculation_caliculateCtc.action";
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target = 'main'; 	
		}	 	
	}
	
	
	function callPreviewLetter(id,empToken,promCode){
		var templateId=document.getElementById('paraFrm_templateCode'+id).value;
		if(templateId==''){
			alert('Please select template to preview');
			return false;
		}
	 	actionName="EmployeeCTCCalculation_previewLetter.action?templateId="+templateId+"&empToken="+empToken+"&promCode="+promCode;
		document.getElementById('paraFrm').action =actionName;
		document.getElementById('paraFrm').target='_self';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target='main'
	}
	

	function setFieldId(event,id,action,textFieldName){
	try{
			//document.getElementById('paraFrm_fieldName').value=id;
	        //callsF9(500,325,action);
	        callDropdown(textFieldName,200,250,action,event,'false');
	        }catch(e){alert(e);}
	}
	
	function callAction1(actionName,formulaCode,empId,currentCTC,promCode,empScore,rowId,empToken){
	try{
			var code=document.getElementById(formulaCode).value;
			var newGrade=document.getElementById('paraFrm_empNewGradeIdItt'+rowId).value;
			var templateId=document.getElementById('paraFrm_templateCode'+rowId).value;
			var empMailId=document.getElementById('empMailId'+rowId).value;
			var promotionFlag=document.getElementById('promotionFlag'+rowId).checked;
			var finalCTC=document.getElementById('paraFrm_modCtcItt'+rowId).value;
			var actionNameToCall="";
		if(actionName=='Process'){
		if(code==''){
			alert("Please select formula");
			return false;
		}
		if(finalCTC==''){
			alert("Please select "+document.getElementById('modCTC').value);
			return false;
		}
		
				 	actionNameToCall="EmployeeCTCCalculation_processPromotion.action?empCode="+empId+"&formulaCode="+code+"&currentCTC="
				 	+currentCTC+"&empRating="+empScore+"&finalCTC="+finalCTC+"&promoFlag="+promotionFlag+"&newGrade="+newGrade;
				}else{
				 	if(empMailId=='NOTAVLB'||empMailId==''){
				 		alert('E-Mail id not available');
				 		return false;
				 	}
				 	if(templateId=='NOTAVLB'||templateId==''){
				 		alert('Please select template to send mail');
				 		return false;
				 	}
				 	
				 	actionNameToCall="EmployeeCTCCalculation_sendMail.action?templateId="+templateId+"&promCode="+promCode+"&empMailId="+empMailId
				 			+"&empCode="+empId+"&empToken="+empToken;
				 	}
	
		var conf=confirm('Do you really want to '+actionName+' ?');
			 	if(!conf){
			 	return false;
			 	}
		
			document.getElementById('paraFrm').action =actionNameToCall;
			document.getElementById('paraFrm').submit();
			}catch(e){
			alert(e);
	 }	 	
	}
		function calculateModCTC(count,oldCtc,oldPercentage){
		var percentVal = document.getElementById('paraFrm_criticalIncrementItt'+count).value;
		var calculatedCtc = 0;
		
		var newPercentage=0.0;
		newPercentage=eval(percentVal)+eval(oldPercentage);
		calculatedCtc = eval(oldCtc)+(oldCtc*(newPercentage/100));
		if(isNaN(calculatedCtc)){
			calculatedCtc=0;
		}
		
		document.getElementById('paraFrm_modCtcItt'+count).value =Math.round(eval(calculatedCtc));
	
	}
	
	
	function clearFilter(){
		document.getElementById('paraFrm_searchFlag').value=false;  
		document.getElementById('paraFrm_appraisalId').value="";  
		document.getElementById('paraFrm_appraisalCode').value="";  
		document.getElementById('paraFrm_departmentId').value="";  
		document.getElementById('paraFrm_departmentName').value="";  
		document.getElementById('paraFrm_empName').value="";
		document.getElementById('paraFrm_empId').value="";
		document.getElementById('paraFrm_managerName').value="";
		document.getElementById('paraFrm_managerId').value="";
		document.getElementById('paraFrm_branchId').value="";
		document.getElementById('paraFrm_branchName').value="";
		document.getElementById('paraFrm_groupHeadName').value="";
		document.getElementById('paraFrm_groupHeadId').value="";
		document.getElementById('paraFrm').action = 'EmployeeCTCCalculation_input.action';
		document.getElementById('paraFrm').submit();
}

function callPromotion(obj,rowId){
	document.getElementById('promotionFlagHidden'+rowId).value=obj.checked;
}

function callReProcess(formulaCode,empId,currentCTC,promCode,empScore,rowId){
	var code=document.getElementById(formulaCode).value;
	var newGrade=document.getElementById('paraFrm_empNewGradeIdItt'+rowId).value;
	var templateId=document.getElementById('paraFrm_templateCode'+rowId).value;
	var promotionFlag=document.getElementById('promotionFlag'+rowId).checked;
	var finalCTC=document.getElementById('paraFrm_modCtcItt'+rowId).value;
	var actionNameToCall="";
	//alert(finalCTC);
	if(code==''){
		alert("Please select formula");
		return false;
	}
	if(finalCTC==''){
		alert("Please select "+document.getElementById('modCTC').value);
		return false;
	}
	
			 	actionNameToCall="EmployeeCTCCalculation_reProcessPromotion.action?empCode="+empId+"&formulaCode="+code+"&currentCTC="
			 	+currentCTC+"&empRating="+empScore+"&finalCTC="+finalCTC+"&promoFlag="+promotionFlag+"&newGrade="+newGrade+"&promCode="+promCode;
			 	
		var conf=confirm('Do you really want to Re-Process ?');
			 	if(!conf){
			 	return false;
			 	}
			 	
			document.getElementById('paraFrm').action =actionNameToCall;
			document.getElementById('paraFrm').submit();
}
	function viewPromotion(empId,promCode,status){
		//alert(promCode);
		//alert(status);
		var lockStatus="";
		if(status=="In Process"){
			lockStatus="unlocked";
		}else{
			lockStatus="Locked";
		}
		document.getElementById('paraFrm_autoCode').value=promCode;
		var path='<%=request.getContextPath()%>';
		//alert(lockStatus);
		document.getElementById('paraFrm').target = '_blank';
		// callButton('NA', 'Y', 2);
	 	document.getElementById("paraFrm").action=path+"/pramotion/PromotionMaster_callForEdit.action?status="+lockStatus;
	    document.getElementById("paraFrm").submit();
	    	document.getElementById("paraFrm").target="main";
	}
//gridScroll();
function gridScroll(){
	try{
		//enableBlockDiv();
		myST = superTable("thetable", { fixedCols : 4,rightCols:3,viewCols:7});
		//disableBlockDiv();
	}catch(e){
		alert(e);
	}
}

function publishAppraisal(){
	var conf=confirm("Are you sure you want to publish appraisals ?");
	
	if(conf){
		document.getElementById('paraFrm').action="EmployeeCTCCalculation_publishFinalizedAppraisals.action";
		document.getElementById('paraFrm').submit();
	}
}	
</script>
