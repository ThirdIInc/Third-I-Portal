<!-- @author: Reeba Joseph @date: 22 OCT 2009 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="ExtraWorkingBenefits" method="post" id="paraFrm" validate="true"
	target="main" theme="simple">
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Extra
					Working Benefits </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="benefitsID" />
		<s:hidden name="myPage" />
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>

			</table>
			</td>
		</tr>



		<tr>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg" >
				<tr>
					<td width="20%" align="left" colspan="1" nowrap="nowrap"><label
						name="benefit.for" id="benefit.for1" ondblclick="callShowDiv(this);"><%=label.get("benefit.for")%></label> 
						<font
						color="red">*</font> :</td>
					<td align="left" colspan="4" width="80%" ><s:select theme="simple" id="benefitsFor"
						name="benefitsFor" headerKey="1" cssStyle="width:100"
						list="#{'HLD':'Holiday','NHD':'National Holiday','SUN':'Sunday','MON':'Monday','TUE':'Tuesday','WED':'Wednesday',
						'THU':'Thursday','FRI':'Friday','SAT':'Saturday' }" />
					</td>
				</tr>
				<tr>
					<td width="20%" colspan="1" nowrap="nowrap"><label
						name="benefit.type" id="benefit.type1"
						ondblclick="callShowDiv(this);"><%=label.get("benefit.type")%></label> 
						<font
						color="red">*</font>:</td>
					<td width="80%"  colspan="4"><s:select theme="simple" id="benefitsType"
						name="benefitsType" headerKey="1" cssStyle="width:120"
						list="#{'EP':'Extra-Day Pay','EL':'Extra-Day Leave','FB':'Fixed Benefits','VB':'Variable Benefits' }" 
						onchange="callBenefitType();"/>
					</td>
				</tr>
				<tr  id="leaveID_1">
					<td align="left" colspan="1" nowrap="nowrap"><label
						name="credited.to" id="credited.to"
						ondblclick="callShowDiv(this);"><%=label.get("credited.to")%></label>
						<font
						color="red">*</font> :</td>
					<td width="15%" colspan="1" align="left">
						<s:hidden name="leaveCode"  /> 
						<s:textfield name="leaveType" size="20" readonly="true" />
					</td>
					<td width="63%" colspan="3" align="left"><img
						id='ctrlHide' src="../pages/common/css/default/images/search2.gif"
						class="iconImage" height="15" align="absmiddle" width="16" 
						onclick="javascript:callsF9(500,325,'ExtraWorkingBenefits_f9leaves.action');"/></td>
				</tr>
				<tr id="leaveID_2">
					<td align="left" colspan="1" nowrap="nowrap"><label
						name="credited.amt" id="credited.amt"
						ondblclick="callShowDiv(this);"><%=label.get("credited.amt")%></label> 
						<font
						color="red">*</font> :</td>
					<td width="15%" colspan="1" align="left">
						<s:textfield name="totalLeave" id="totalLeave"
							maxlength="4" size="20" />
					</td>
					<td width="63%" colspan="3" align="left"></td>
				</tr>

				<tr class="td_bottom_border" id="fixedID_1" style="display: none">
					<td align="left" colspan="1" nowrap="nowrap"><label
						name="full.working" id="full.working"
						ondblclick="callShowDiv(this);"><%=label.get("full.working")%></label>
					<font
						color="red">*</font> :</td>
					<td width="15%" colspan="1"><s:textfield onkeypress="return numbersWithDot();"
						name="fullDayWorking" size="15" id="fullDayWorkingId" cssStyle="text-align: right"/></td>
					<td width="63%" colspan="3" align="left"><label
						name="rupees" id="rupees"
						ondblclick="callShowDiv(this);"><%=label.get("rupees")%></label></td>
				</tr>
				<tr class="td_bottom_border" id="fixedID_2" style="display: none">
					<td align="left" colspan="1" nowrap="nowrap"><label
						name="half.working" id="half.working"
						ondblclick="callShowDiv(this);"><%=label.get("half.working")%></label>
					<font
						color="red">*</font> :</td>
					<td width="15%" colspan="1"><s:textfield onkeypress="return numbersWithDot();"
						name="halfDayWorking" size="15" id="halfDayWorkingId" cssStyle="text-align: right"/></td>
					<td width="63%" colspan="3" align="left"><label
						name="rupees" id="rupees1"
						ondblclick="callShowDiv(this);"><%=label.get("rupees")%></label></td>
				</tr>
				<tr class="td_bottom_border" id="fixedID_3" style="display: none">
					<td align="left" colspan="1" nowrap="nowrap"><label
						name="hourly" id="hourly"
						ondblclick="callShowDiv(this);"><%=label.get("hourly")%></label> <font
						color="red">*</font> :</td>
					<td width="15%" colspan="1"><s:textfield onkeypress="return numbersWithDot();"
						name="hourlyWorking" size="15" id="hourlyWorkingId" cssStyle="text-align: right"/></td>
					<td width="63%" colspan="3" align="left"><label
						name="rupees" id="rupees2"
						ondblclick="callShowDiv(this);"><%=label.get("rupees")%></label></td>
				</tr>

				<tr class="td_bottom_border" id="variableID" style="display: none">
					<td align="left" colspan="1" nowrap="nowrap"><label
						name="formula" id="formula"
						ondblclick="callShowDiv(this);"><%=label.get("formula")%></label> <font
						color="red">*</font> :</td>
					<td width="15%" colspan="1" align="left">
					
					 <s:textfield name="formula" id="formula1" readonly="true" size="20"/>
					
						</td>
					<td width="63%" colspan="3" align="left"><img
						id='ctrlHide' src="../pages/common/css/default/images/search2.gif" 
						width="16" height="15"
						align="absmiddle" onclick="callFormula('formula1');" /></td>
				</tr>
				
				<tr class="td_bottom_border" id="fixed_variable">
					<td align="left" colspan="1" nowrap="nowrap"><label
						name="credited.to" id="credited.to1"
						ondblclick="callShowDiv(this);"><%=label.get("credited.to")%></label> <font
						color="red">*</font> :</td>
					<td width="15%" colspan="1" align="left">
						<s:hidden name="creditCode"  /> 
						<s:textfield name="creditType" size="20" readonly="true" />
					</td>
					<td width="63%" colspan="3" align="left"><img
						id='ctrlHide' src="../pages/common/css/default/images/search2.gif"
						class="iconImage" height="15" align="absmiddle" width="16" 
						onclick="javascript:callsF9(500,325,'ExtraWorkingBenefits_f9credits.action');"/></td>
				</tr>
				
				<tr class="td_bottom_border" id="checkDailyAttId">
					<td align="left" colspan="1" nowrap="nowrap"><label
						name="checkDailyAtt" id="checkDailyAtt"
						ondblclick="callShowDiv(this);"><%=label.get("checkDailyAtt")%></label>   :</td>
					<td width="15%" colspan="1" align="left">
						 
						<s:checkbox name="dailyAttendanceCheck" />
					</td>
					<td width="63%" colspan="3" align="left"> </td>
				</tr>
				
					<tr class="td_bottom_border" id="checkroundoffId">
						<td colspan="1" width="30%"><label name="Round" id="Round" ondblclick="callShowDiv(this);"><%= label.get("Round")%></label> :</td>
						<td width="75%" colspan="3"><s:select list="#{'0':'No Round','1':'Round off 15','2':'Round off 30','3':'Round off 45','4':'Round off 60'}" name="extraworkRound"></s:select> </td>
				</tr>

			</table>
			</td>
		</tr>

		<!--  
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4"><s:submit cssClass="add" value=" Add Categories"
						action="ExtraWorkingBenefits_addCategories" id="ctrlHide"/></td>
					<td width="22%">
					</td>
				</tr>

			</table>
			</td>
		</tr>
		-->
		
		<tr>
			<td colspan="4">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td colspan="4" class="text_head"><strong
						class="forminnerhead">Apply this benefit to :</strong></td>
				</tr>
				<tr>
					<td colspan="1" width="20%" class="formtext"><label
						class="set" name="division" id="division"
						ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					<font
						color="red">*</font> :</td>
					<td colspan="3" width="80%"><s:textfield size="25"
						name="divisionName" readonly="true" /> <s:hidden
						name="divisionCode" /> <img class="iconImage"  id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" border="0" onclick="javascript:callDivision();" /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%" class="formtext"><label
						class="set" name="department" id="department"
						ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="25"
						name="deptName" readonly="true" /> <s:hidden name="deptCode" />
					<img class="iconImage"  id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" border="0" onclick="javascript:callDept();" /></td>
					<td colspan="1" width="20%" class="formtext"><label
						class="set" name="branch" id="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="25"
						name="branchName" readonly="true" /> <s:hidden name="branchCode" />
					<img class="iconImage"  id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" border="0" onclick="javascript:callBranch();" /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%" class="formtext"><label
						name="employee.type" id="employee.type"
						ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="25"
						name="empTypeName" readonly="true" /> <s:hidden
						name="empTypeCode" /> <img class="iconImage"  id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" border="0" onclick="javascript:callEmpType();" /></td>
					<td colspan="1" width="20%" class="formtext"><label
						class="set" name="designation" id="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="25"
						name="desgName" readonly="true" /> <s:hidden name="desgCode" />
					<img class="iconImage"  id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" border="0" onclick="javascript:callDesignation();" /></td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		<tr >
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" align="center" ><input type="button" class="add" value=" Add to List"
						onclick="addList();" id="ctrlHide" /></td>
					<td width="22%">
					</td>
				</tr>

			</table>
			</td>
		</tr>
		
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="3" class="text_head"><strong
						class="forminnerhead">This benefit is applied to :</strong></td>
				</tr>
				<tr>
					<td class="formth" width="5%" valign="top"><label
						class="set" id="serial.no1" name="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
					<td class="formth" width="20%" valign="top"><label
						class="set" id="division1" name="division"
						ondblclick="callShowDiv(this);"><%=label.get("division")%></label></td>
					<td class="formth" width="15%" valign="top"><label
						class="set" id="department1" name="department"
						ondblclick="callShowDiv(this);"><%=label.get("department")%></label></td>
					<td class="formth" width="20%" valign="top"><label
						class="set" id="branch1" name="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label></td>
					<td class="formth" width="15%" valign="top"><label
						class="set" id="employee.type1" name="employee.type"
						ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label></td>
					<td class="formth" width="20%" valign="top"><label
						class="set" id="designation1" name="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					</td>
					<td class="formth" width="5%" valign="top">Edit</td>
					<td class="formth" width="5%" valign="top">Delete</td>
				</tr>
				<s:if test="noFilterData">
					<tr>
						<td width="100%" colspan="7" align="center"><font
							color="red">No Data To Display</font></td>
					</tr>
				</s:if>
				<%
				int k = 1;
				%>
				<s:iterator value="filterTableList" status="stat">
					<tr>
						<td width="5%" class="sortableTD"><%=k%><input type="hidden" name="srNo"
							value="<%=k%>" /></td>
						<td width="15%" class="sortableTD"><s:property
							value="divisionNameItr" /><s:hidden name="divisionNameItr" /><s:hidden
							name="divisionCodeItr" /></td>
						<td width="15%" class="sortableTD"><s:property
							value="deptNameItr" /><s:hidden name="deptNameItr" /><s:hidden
							name="deptCodeItr" /></td>
						<td width="20%" class="sortableTD"><s:property
							value="branchNameItr" /><s:hidden name="branchNameItr" /> <s:hidden
							name="branchCodeItr" /></td>
						<td width="15%" class="sortableTD"><s:property
							value="empTypeNameItr" /><s:hidden name="empTypeNameItr" /><s:hidden
							name="empTypeCodeItr" /></td>
						<td width="20%" class="sortableTD"><s:property
							value="desgNameItr" /><s:hidden name="desgCodeItr" /></td>
						<s:hidden name="desgNameItr" />
						<td width="5%" class="sortableTD" align="center"><s:hidden
							name="settingCode" /> <input type="button" class="rowEdit"
							class="edit" title="Edit Record"
							onclick="editList('<%=k%>','<s:property value="divisionCodeItr" />',
							'<s:property value="deptCodeItr" />','<s:property value="branchCodeItr" />',
							'<s:property value="empTypeCodeItr" />','<s:property value="desgCodeItr" />',
							'<s:property value="divisionNameItr" />','<s:property value="deptNameItr" />',
							'<s:property value="branchNameItr" />','<s:property value="empTypeNameItr" />',
							'<s:property value="desgNameItr" />');" /></td>
						<td width="5%" class="sortableTD" align="center"><input
							type="button" class="rowDelete" class="delete"
							title="Delete Record"
							onclick="deleteList('<%=k%>');" />
							<input type="hidden" name="hdeleteOp" id="hdeleteOp<%=k%>" />
							</td>
					</tr>
					<%
					k++;
					%>
				</s:iterator>
			</table>
			</td>
		</tr>
		
		<s:hidden name="subcode" />
		<s:hidden name="listLength" />
		<s:hidden name="hiddenEdit" />
	</table>
</s:form>

<script>
	/*document.getElementById('leaveID_1').style.display='none';
	document.getElementById('fixedID_1').style.display='none';
	document.getElementById('fixedID_2').style.display='none';
	document.getElementById('fixedID_3').style.display='none';
	document.getElementById('variableID').style.display='none';*/
	
	callBenefitType();

	function callBenefitType(){
		try{
		document.getElementById('leaveID_1').style.display='none';
		document.getElementById('leaveID_2').style.display='none';
		document.getElementById('fixedID_1').style.display='none';
		document.getElementById('fixedID_2').style.display='none';
		document.getElementById('fixedID_3').style.display='none';
		document.getElementById('variableID').style.display='none';
		document.getElementById('fixed_variable').style.display='none';
		
		document.getElementById('checkDailyAttId').style.display='none';
		document.getElementById('checkroundoffId').style.display='none';
		
		if(document.getElementById('benefitsType').value=='VB'){
			document.getElementById('variableID').style.display='';
			document.getElementById('fixed_variable').style.display='';
			document.getElementById('paraFrm_leaveType').value="";
			document.getElementById('paraFrm_leaveCode').value="";
			document.getElementById('totalLeave').value="";
			document.getElementById('fullDayWorkingId').value="";
			document.getElementById('halfDayWorkingId').value="";
			document.getElementById('hourlyWorkingId').value="";
			document.getElementById('checkDailyAttId').style.display='';
			document.getElementById('checkroundoffId').style.display='';
			//document.getElementById('paraFrm_creditType').value="";
			//document.getElementById('paraFrm_creditCode').value="";
			
			 
		}
		
		if(document.getElementById('benefitsType').value=='EL'){
			document.getElementById('leaveID_1').style.display='';
			document.getElementById('leaveID_2').style.display='';
			document.getElementById('fullDayWorkingId').value="";
			document.getElementById('halfDayWorkingId').value="";
			document.getElementById('hourlyWorkingId').value="";
			document.getElementById('formula1').value="";
			document.getElementById('paraFrm_creditType').value="";
			document.getElementById('paraFrm_creditCode').value="";
				document.getElementById('checkDailyAttId').value="";
					document.getElementById('checkroundoffId').value="";
		}
		
		if(document.getElementById('benefitsType').value=='FB'){
			document.getElementById('fixedID_1').style.display='';
			document.getElementById('fixedID_2').style.display='';
			document.getElementById('fixedID_3').style.display='';
			document.getElementById('fixed_variable').style.display='';
			document.getElementById('paraFrm_leaveType').value="";
			document.getElementById('paraFrm_leaveCode').value="";
			document.getElementById('totalLeave').value="";
			document.getElementById('formula1').value="";	
			//document.getElementById('paraFrm_creditType').value="";
			//document.getElementById('paraFrm_creditCode').value="";	
				document.getElementById('checkDailyAttId').value="";
				document.getElementById('checkroundoffId').value="";
		}
		
		if(document.getElementById('benefitsType').value=='EP'){
			document.getElementById('fixed_variable').style.display='';
			document.getElementById('formula1').value="";	
			document.getElementById('paraFrm_leaveType').value="";
			document.getElementById('paraFrm_leaveCode').value="";
			document.getElementById('totalLeave').value="";
			document.getElementById('fullDayWorkingId').value="";
			document.getElementById('halfDayWorkingId').value="";
			document.getElementById('hourlyWorkingId').value="";
			//document.getElementById('paraFrm_creditType').value="";
			//document.getElementById('paraFrm_creditCode').value="";
				document.getElementById('checkDailyAttId').value="";
				document.getElementById('checkroundoffId').value="";
		}
		}catch(e){
			alert(e);
		}
	}

	function callFormula(id){
		//alert(id);
		window.open('','new','top=100,left=300,width=400,height=400,scrollbars=no,status=no,resizable=no');
 		document.getElementById("paraFrm").target="new";
 		document.getElementById("paraFrm").action='<%=request.getContextPath()%>/payroll/FormulaBuilder_formulaCalc.action?id='+id; 
  		document.getElementById("paraFrm").submit();
  		document.getElementById("paraFrm").target="main";
  	 }
  	 
  	 function saveFun(){
  	 	if(document.getElementById('benefitsType').value=='EP'){
  	 		if(trim(document.getElementById('paraFrm_creditCode').value)==""){
  	 			alert("Please select "+document.getElementById('credited.to1').innerHTML.toLowerCase());
  	 			return false;
  	 		}
  	 	}
  	 	if(document.getElementById('benefitsType').value=='EL'){
  	 		if(trim(document.getElementById('paraFrm_leaveCode').value)==""){
  	 			alert("Please select "+document.getElementById('credited.to').innerHTML.toLowerCase());
  	 			return false;
  	 		}
  	 		if(trim(document.getElementById('totalLeave').value)==""){
  	 			alert("Please enter "+document.getElementById('credited.amt').innerHTML.toLowerCase());
  	 			return false;
  	 		}
  	 	}
  	 	if(document.getElementById('benefitsType').value=='FB'){
  	 		if(trim(document.getElementById('fullDayWorkingId').value)==""){
  	 			alert("Please enter "+document.getElementById('full.working').innerHTML.toLowerCase()+ " amount");
  	 			return false;
  	 		}
  	 		if(trim(document.getElementById('halfDayWorkingId').value)==""){
  	 			alert("Please enter "+document.getElementById('half.working').innerHTML.toLowerCase()+ " amount");
  	 			return false;
  	 		}
  	 		if(trim(document.getElementById('hourlyWorkingId').value)==""){
  	 			alert("Please enter "+document.getElementById('hourly').innerHTML.toLowerCase()+ " amount");
  	 			return false;
  	 		}
  	 		if(trim(document.getElementById('paraFrm_creditCode').value)==""){
  	 			alert("Please select "+document.getElementById('credited.to1').innerHTML.toLowerCase());
  	 			return false;
  	 		}
  	 	}
  	 	if(document.getElementById('benefitsType').value=='VB'){
  	 		if(trim(document.getElementById('formula1').value)==""){
  	 			alert("Please select "+document.getElementById('formula').innerHTML.toLowerCase());
  	 			return false;
  	 		}
  	 		if(trim(document.getElementById('paraFrm_creditCode').value)==""){
  	 			alert("Please select "+document.getElementById('credited.to1').innerHTML.toLowerCase());
  	 			return false;
  	 		}
  	 	}
  	 	if(document.getElementById("paraFrm_listLength").value=="0"){
	     	 alert("Please select atleast one category. ");
	     	 document.getElementById("paraFrm_divisionName").focus();
	     	 return false;
     	 } 
  	 	
  	 	
  	 	document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ExtraWorkingBenefits_save.action';
		document.getElementById('paraFrm').submit();
  	 }
  	 
  	 function resetFun(){
  	 	document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ExtraWorkingBenefits_reset.action';
		document.getElementById('paraFrm').submit();
  	 }
  	 
  	 function editFun(){
  	 	return true;
  	 }
  	 
  	 function deleteFun(){
  	 	var conf = confirm("Do you really want to delete this record?");
  		if(conf) {
	  	 	document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = 'ExtraWorkingBenefits_delete.action';
			document.getElementById('paraFrm').submit();
			return true;
		}else
			return false;
  	 }
  	 
  	 function backFun(){
  	 	document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ExtraWorkingBenefits_input.action';
		document.getElementById('paraFrm').submit();
  	 }
  	 
  	function callDivision(){
		callsF9(500,325,'ExtraWorkingBenefits_f9division.action');
	}
	
	function callEmpType(){
		callsF9(500,325,'ExtraWorkingBenefits_f9empType.action');
	}
	
	function callDept(){
		callsF9(500,325,'ExtraWorkingBenefits_f9dept.action');
	}
	
	function callBranch(){
		callsF9(500,325,'ExtraWorkingBenefits_f9branch.action');
	}
	
	function callDesignation(){
		callsF9(500,325,'ExtraWorkingBenefits_f9designation.action');
	}
	
   	function addList(){
   		var divCode = document.getElementById('paraFrm_divisionCode').value;
	   	var deptCode = document.getElementById('paraFrm_deptCode').value;
	   	var brnCode = document.getElementById('paraFrm_branchCode').value;
	   	var eTypeCode = document.getElementById('paraFrm_empTypeCode').value;
	   	var desgCode = document.getElementById('paraFrm_desgCode').value;
	   	if(divCode==""){
			alert("Please select "+document.getElementById('division').innerHTML.toLowerCase());
	     	document.getElementById("paraFrm_divisionName").focus();
	     	return false;
	   	}
	   	if(divCode=="" && deptCode=="" && brnCode=="" && eTypeCode=="" && desgCode==""){
	   		 alert("Please select atleast one category. ");
	     	 document.getElementById("paraFrm_divisionName").focus();
	     	 return false;
	   	}
	   	document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ExtraWorkingBenefits_addItem.action';
		document.getElementById('paraFrm').submit();
   	}
   	
   	function editList(srNo,divCode,deptCode,brnCode,eTypeCode,desgCode,
   						divName,deptName,brnName,eTypeName,desgName) {
		//document.getElementById('paraFrm_hiddenCode').value = srNo;
		try{
		//alert(srNo);
		document.getElementById('paraFrm_hiddenEdit').value=srNo;
		document.getElementById('paraFrm_divisionCode').value=divCode;
	   	document.getElementById('paraFrm_deptCode').value=deptCode;
	   	document.getElementById('paraFrm_branchCode').value=brnCode;
	   	document.getElementById('paraFrm_empTypeCode').value=eTypeCode;
	   	document.getElementById('paraFrm_desgCode').value=desgCode;
	   	
	   	if(trim(divName)=="-"){
	   		document.getElementById('paraFrm_divisionName').value="";
	   	}else
	   		document.getElementById('paraFrm_divisionName').value=divName;
	   	if(trim(deptName)=="-"){
	   		document.getElementById('paraFrm_deptName').value="";
	   	}else
	   		document.getElementById('paraFrm_deptName').value=deptName;
	   	if(trim(brnName)=="-"){
	   		document.getElementById('paraFrm_branchName').value="";
	   	}else
	   		document.getElementById('paraFrm_branchName').value=brnName;
	   	if(trim(eTypeName)=="-"){
	   		document.getElementById('paraFrm_empTypeName').value="";
	   	}else
	   		document.getElementById('paraFrm_empTypeName').value=eTypeName;
	   	if(trim(desgName)=="-"){
	   		document.getElementById('paraFrm_desgName').value="";
	   	}else
	   		document.getElementById('paraFrm_desgName').value=desgName;
		
		}catch(e){
			alert(e);
		}
		return true;
	}
	
	function deleteList(code) {
		var conf=confirm("Do you really want to delete this record ?");
  		if(conf){
			try{
			var i=eval(code)-1;
			document.getElementById('hdeleteOp'+code).value=eval(code)-1;
			//alert(document.getElementById('hdeleteOp'+code).value);
			document.getElementById("paraFrm").action="ExtraWorkingBenefits_deleteList.action";
			document.getElementById("paraFrm").submit();
			}catch(e){
				alert(e);
			}
			return true;
 			}
  		else{
  			 return false;
  		}
	}
	
</script>