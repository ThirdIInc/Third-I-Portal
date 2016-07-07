<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="BonusAllowance" validate="true" id="paraFrm" theme="simple">
<table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg">
<s:hidden name="bonusAllowanceID"/>
<s:hidden name="divisionID"/>
<s:hidden name="branchID"/>
<s:hidden name="paybillID"/>
<s:hidden name="departmentID"/>
<s:hidden name="processedToMonth" />
<s:hidden name="toYear" />
<s:hidden name="payInSalaryCheckBox" />
<s:hidden name="bonusAllowanceStatus" />
<s:hidden name="myPage" id="myPage" />
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Bonus/Allowance
					Processing </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<s:if test="unlockStatusFlag">
				<td valign="bottom" class="txt">
			 		<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
				</td>
			</s:if>
			<s:else>
				<td>
					<input type="button" value="Back" onclick="backFun()" btnClass="token">
				</td>
			</s:else>	
		</tr>
		
		<tr>
			<td valign="bottom" class="txt">
				<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" class="formbg">
					<tr>
						 <td colspan="3">
						 	<b><label name="addEmployeeLabel" id="addEmployeeLabel" ondblclick="callShowDiv(this);"><%=label.get("addEmployeeLabel")%></label>:</b>
						 </td>
					</tr>
					<tr>
						 <td width="20%">
						 	<label name="selectEmployeeLabel" id="selectEmployeeLabel" ondblclick="callShowDiv(this);"><%=label.get("selectEmployeeLabel")%></label>:<font color="red">*</font>
						 </td>
						 <td width="80%" colspan="2">
						 	<s:hidden name="employeeIDEditRecord"/>
						 	<s:textfield name="employeeTokenEditRecord" cssStyle="background-color: #F2F2F2;" readonly="true"/> &nbsp;
						 	<s:textfield name="employeeNameEditRecord" cssStyle="background-color: #F2F2F2;" size="55" readonly="true"/>
							<s:if test="unlockStatusFlag">
						 	<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
											onclick="callsF9(500,325,'BonusAllowance_f9EditRecordsEmployee.action');">	
						 	</s:if>	
						 </td>
					</tr>
					
					<tr>
						 <td>
						 	<label name="bonusAllowanceAmountLabel" id="bonusAllowanceAmountLabel" ondblclick="callShowDiv(this);"><%=label.get("bonusAllowanceAmountLabel")%></label>:
						 </td>
						 <td colspan="2">
						 	<s:textfield name="bonusAllowanceAmountEditRecord" onkeypress="return numbersWithDot();"/>
						 </td>
					</tr>
					
					<tr>
						 <td>
						 	<label name="tdsLabel" id="tdsLabel" ondblclick="callShowDiv(this);"><%=label.get("tdsLabel")%></label>:
						 </td>
						 <td colspan="2">
						 	<s:textfield name="tdsEditRecord" onkeypress="return numbersWithDot();"/>
						 </td>
					<!-- 
						 <td width="25%">
						 	<input type="button" value="Add" class="token" onclick="addEmployeeToList();">	
						 </td>
					 -->	
					</tr>
				</table>
			</td>
		</tr>
		<%
			int totalPage = 0;
			int pageNo = 0;
		%>
		<tr>
			<td colspan=2">
			<table class="formbg" border="0" id="processedEmpTableID" width="100%" cellspacing="1" cellspadding="1">
				<tr>
					<td width="60%" colspan="4"><strong><label name="empList" id="empList"
						ondblclick="callShowDiv(this);"><%=label.get("empList")%></label></strong>
					</td>
					<td colspan="2" width="40%" align="right" id="ctrlShow"><b>Page:</b>
						<%
							totalPage = (Integer) request.getAttribute("totalEmpPage");
							pageNo = (Integer) request.getAttribute("pageEmpNo");
						%> <a
							onclick="callPage('1', 'F', '<%=totalPage%>', 'BonusAllowance_viewEmployeeRecords.action');"
							href="#"> <img height="10" width="10" class="iconImage"
							src="../pages/common/img/first.gif" title="First Page"> </a>&nbsp;
						<a
							onclick="callPage('P', 'P', '<%=totalPage%>', 'BonusAllowance_viewEmployeeRecords.action');"
							href="#"> <img height="10" width="10" class="iconImage"
							src="../pages/common/img/previous.gif" title="Previous Page">
						</a> <input type="text" onkeypress="callPageText(event, '1', 'BonusAllowance_viewEmployeeRecords.action');return numbersOnly();"
							maxlength="10" value='<%=pageNo%>' size="3" id="pageNoField" name="pageNoField"> of <%=totalPage%> 
							<a onclick="callPage('N', 'N', '<%=totalPage%>', 'BonusAllowance_viewEmployeeRecords.action')"
							href="#"> <img class="iconImage" src="../pages/common/img/next.gif" title="Next Page"> </a>&nbsp;
							<a onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'BonusAllowance_viewEmployeeRecords.action');"
							href="#"> <img height="10" width="10" class="iconImage" src="../pages/common/img/last.gif" title="Last Page"> </a>
					</td>
				</tr>
				<tr>
					<td class="formth" width="15%"><label
						name="empTokenEditRecordItrLabel" id="empTokenEditRecordItrLabel"
						ondblclick="callShowDiv(this);"><%=label.get("empTokenEditRecordItrLabel")%></label>
					</td>

					<td class="formth" width="25%"><label name="empNameEditRecordItrLabel"
						id="empNameEditRecordItrLabel" ondblclick="callShowDiv(this);"><%=label.get("empNameEditRecordItrLabel")%></label>
					</td>

					<td class="formth" nowrap="nowrap" width="15%"><label
						name="bonusAllowanceAmountEditRecordItrLabel" id="bonusAllowanceAmountEditRecordItrLabel" ondblclick="callShowDiv(this);"><%=label.get("bonusAllowanceAmountEditRecordItrLabel")%></label>
					</td>

					<td class="formth" nowrap="nowrap" width="15%"><label
						name="taxEditRecordItrLabel" id="taxEditRecordItrLabel" ondblclick="callShowDiv(this);"><%=label.get("taxEditRecordItrLabel")%></label>
					</td>

					<td class="formth" width="15%"><label
						name="totalAmountEditRecordItrLabel" id="totalAmountEditRecordItrLabel"
						ondblclick="callShowDiv(this);"><%=label.get("totalAmountEditRecordItrLabel")%></label>
					</td>
				
					<td class="formth" width="15%">
						<!--  
							<a href="#" onclick="deleteSelectedEmployees();"><u>Remove Employee</u></a>
						 -->
						<s:if test="unlockStatusFlag">
						 <input type="button" value="Remove Employee" id="ctrlShow" onclick="deleteSelectedEmployees();">
						 </s:if>		
					</td>
				</tr>
				<% int cnt = pageNo * 20 - 20; %>
				
				<s:if test="employeeListEditRecordFlag">
					<%
						int empCount = 0;
					%>
					<s:iterator value="employeeListEditRecordIterator">
						<tr onmouseover="javascript:newRowColor(this);"
							title="Double click for edit"
							onmouseout="javascript:oldRowColor(this);"
							ondblclick="javascript:callEmployeeEditRecord('<s:property value="bonusAllowanceID"/>',
							                                              '<s:property value="empIDEditRecordItr"/>',
							                                              '<s:property value="empTokenEditRecordItr"/>',
							                                              '<s:property value="empNameEditRecordItr"/>',
							                                              '<s:property value="bonusAllowanceAmountEditRecordItr"/>',
							                                              '<s:property value="taxEditRecordItr"/>'
							                                              );">
							
							
							<!-- <s:hidden name="bonusAllowanceIDEditRecordItr" /> -->

							<td class="sortableTD" width="10%">
								<s:property value="empTokenEditRecordItr" />
								<s:hidden name="empTokenEditRecordItr" />
								<input type="hidden" name="empIDEditRecordItr" id="paraFrm_empIDEditRecordItr<%=empCount %>" 
									value='<s:property value="empIDEditRecordItr" />'/>
							</td>

							<td class="sortableTD" width="20%">
								<s:property value="empNameEditRecordItr" />
								<s:hidden value="empNameEditRecordItr" />
							</td>

							<td class="sortableTD" width="10%" align="right">
							 	<s:property value="bonusAllowanceAmountEditRecordItr" />
							 	<s:hidden value="bonusAllowanceAmountEditRecordItr" />
							 </td>

							<td class="sortableTD" width="10%" align="right">
								<s:property value="taxEditRecordItr" />
								<s:hidden value="taxEditRecordItr" />
							</td>

							<td class="sortableTD" width="10%" align="right">
								<s:property value="totalAmountEditRecordItr" />
								<s:hidden value="totalAmountEditRecordItr" />
							</td>

							<td class="sortableTD" width="10%" align="center">
								<input type="checkbox" name="removeEmployeeEditRecordItrCheckbox" id="paraFrm_removeEmployeeEditRecordItrCheckbox<%=empCount%>" />
							</td>
							<%
								empCount++;
							%>
						</tr>
					</s:iterator>
					
				</s:if>
				<s:else>
					<tr>
						<td colspan="6" align="center"><font color="red">There
						is no data to display</font></td>
					</tr>
				</s:else>
			</table>
		</td>
		</tr>

		<tr>
			<s:if test="unlockStatusFlag">
				<td valign="bottom" class="txt">
			 		<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
				</td>
			</s:if>
			<s:else>
				<td>
					<input type="button" value="Back" onclick="backFun()" btnClass="token">
				</td>
			</s:else>	
		</tr>
		
	</table>
</s:form>

<script>
function backFun() {
	document.getElementById('paraFrm').target = '_self';
	document.getElementById('paraFrm').action = 'BonusAllowance_backToProcessingPage.action';
	document.getElementById('paraFrm').submit();
	
} 

function callEmployeeEditRecord(bonusCode, employeeID, employeeToken, employeeName, bonusAmount, tdsAmount) {
	document.getElementById('paraFrm_employeeIDEditRecord').value = employeeID;
	document.getElementById('paraFrm_employeeTokenEditRecord').value = employeeToken;
	document.getElementById('paraFrm_employeeNameEditRecord').value = employeeName;
	document.getElementById('paraFrm_bonusAllowanceAmountEditRecord').value = bonusAmount;
	document.getElementById('paraFrm_tdsEditRecord').value = tdsAmount;
}

function deleteSelectedEmployees() {
	try {
		var table = document.getElementById('processedEmpTableID'); 
		var rowCount = table.rows.length; 
		var iteration = rowCount-2;
		var finalEmpToRemove = ""; 
		for (var i=0; i<iteration; i++) {
			var selectedEmp = document.getElementById('paraFrm_removeEmployeeEditRecordItrCheckbox'+i).checked;
			if (selectedEmp) {
				finalEmpToRemove += document.getElementById('paraFrm_empIDEditRecordItr'+i).value+",";
			}
		}
				
		if (finalEmpToRemove=="") {
			alert("Please select atleast one employee.");
			return false;
		}
		var con = confirm("Do you really want to remove selected employee(s)?");
		if (con) {
		 	document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action = 'BonusAllowance_deleteSelectedEmployee.action?finalEmpToRemove='+finalEmpToRemove;
			document.getElementById('paraFrm').submit();
		} else {
			return false;
		}
	} catch (e) {
		alert("Deleted employee >>>>"+e);
	}
}

//function addEmployeeToList() {
function saveFun() {
	var employeeID = trim(document.getElementById('paraFrm_employeeIDEditRecord').value);
	var bonusAllowanceAmountEditRecord = trim(document.getElementById('paraFrm_bonusAllowanceAmountEditRecord').value);
	var tdsEditRecord = trim(document.getElementById('paraFrm_tdsEditRecord').value);
	if (employeeID == "") {
		alert("Please "+document.getElementById('selectEmployeeLabel').innerHTML.toLowerCase());
		document.getElementById('paraFrm_employeeNameEditRecord').focus();
		return false;
	}
	
	if (eval(tdsEditRecord)>eval(bonusAllowanceAmountEditRecord)) {
		alert(document.getElementById('tdsLabel').innerHTML.toLowerCase()+" should not be greater than "+document.getElementById('bonusAllowanceAmountLabel').innerHTML.toLowerCase());
		document.getElementById('paraFrm_tdsEditRecord').focus();
		return false;
	}
	
	var con = confirm("Do you really want save this details?");
	if (con) {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = 'BonusAllowance_addSelectedEmployeeToList.action';
		document.getElementById('paraFrm').submit();
	}
}
</script>