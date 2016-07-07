<!--@author Reeba_Joseph @date 03-02-2010 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="GratuityConfig" validate="true" id="paraFrm"
	theme="simple">

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Gratuity
					Configuration </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="7">
			<table class="formbg" width="100%" border="0">
				<tr>
					<td colspan="1" width="24%"><label name="gratuity.applicable"
						id="gratuity.applicable" ondblclick="callShowDiv(this);"><%=label.get("gratuity.applicable")%></label>:
					</td>
					<td colspan="6" width="76%"><s:checkbox name="gratuityFlag"
						id="gratuityFlag" /></td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="7">
			<table class="formbg" width="100%" border="0">
				<tr>
					<td colspan="1" width="24%"><label name="minimum.tenure"
						id="minimum.tenure" ondblclick="callShowDiv(this);"><%=label.get("minimum.tenure")%></label>:
					</td>
					<td colspan="7"><s:textfield name="minimumTenure"
						id="minimumTenure" size="20" />years</td>
				</tr>
				<tr>
					<td colspan="1"><label name="average.salCalc"
						id="average.salCalc" ondblclick="callShowDiv(this);"><%=label.get("average.salCalc")%></label>:
					</td>
					<td width="25%" colspan="1"><s:textfield
						name="creditsCombine" id="creditsCombineId" size="20" /><s:hidden
										name="creditsCombineCode" />  <input type="button" value=" >> "
						id="creditsCombineBtn" onclick="callCreditCombination('creditsCombineCode','creditsCombineId');" /></td>
					<td width="25%" colspan="1"><label name="avgSalary.Option"
						id="avgSalary.Option" ondblclick="callShowDiv(this);"><%=label.get("avgSalary.Option")%></label>:</td>
					<td colspan="5"><s:hidden name="salOptionHidden"></s:hidden><input type="radio" name="salOption" id="LS" onclick="return callSalOption('L');" />Last salary drawn<input type="radio" name="salOption" id="AS" onclick="return callSalOption('A');" />Last 10 months average</td>
				</tr>
				<tr>
					<td colspan="1"><s:hidden name='roundOffHidden'/><label name="service.years" id="service.years"
						ondblclick="callShowDiv(this);"><%=label.get("service.years")%></label>:
					</td>
					<td colspan="7"><input type="radio" name="roundOffOption" id="NR" onclick=callroundOffOption('N'); />Nearest value
					<input type="radio"	name="roundOffOption"  id="FR" onclick=callroundOffOption('F'); />Floor value
					<input type="radio"	name="roundOffOption"  id="CR" onclick=callroundOffOption('C'); />Ceil value
					<input type="radio"	name="roundOffOption" id="AR" onclick=callroundOffOption('A'); />Actual value</td>
				</tr>
				<tr>
					<td colspan="1"><label name="formula" id="formula"
						ondblclick="callShowDiv(this);"><%=label.get("formula")%></label>:
					</td>
					<td colspan="7">Service Tenure * average salary * <s:textfield
						name="formula" id="formula" size="5" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="7">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td colspan="7" class="text_head"><strong
						class="forminnerhead">Gratuity applicable to :</strong></td>
				</tr>
				<tr>
					<td width="30%" colspan="1" class="formtext"><label
						class="set" name="division" id="division"
						ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
				  <font color="red">*</font> :</td>
				  <td width="8%" colspan="1"><s:textfield size="25"
						name="divisionName" readonly="true" /> <s:hidden
						name="divisionCode" /></td>
					<td width="8%" colspan="1"><img class="iconImage" id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" border="0" onclick="javascript:callDivision();" /></td>
					<td width="13%"></td>
				  <td width="21%" colspan="1" class="formtext"><label
						name="employee.type" id="employee.type"
						ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label> :</td>
				  <td width="5%" colspan="1"><s:textfield size="25"
						name="empTypeName" readonly="true" /> <s:hidden
						name="empTypeCode" /></td>
					<td width="15%" colspan="1"><img class="iconImage"  id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" border="0" onclick="javascript:callEmpType();" /></td>
				</tr>

				<tr>
					<td colspan="1" class="formtext"><label class="set"
						name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
					:</td>
					<td colspan="1"><s:textfield size="25" name="deptName"
						readonly="true" /> <s:hidden name="deptCode" /></td>
					<td width="8%" colspan="1"><img class="iconImage"
						id="ctrlHide" src="../pages/images/recruitment/search2.gif"
						width="16" height="15" border="0" onclick="javascript:callDept();" /></td>
					<td width="13%"></td>
				  <td width="21%" colspan="1" class="formtext"><label
						class="set" name="branch" id="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
				  <td width="5%" colspan="1"><s:textfield size="25"
						name="branchName" readonly="true" /> <s:hidden name="branchCode" /></td>
					<td width="15%" colspan="1"><img class="iconImage"
						id="ctrlHide" src="../pages/images/recruitment/search2.gif"
						width="16" height="15" border="0"
						onclick="javascript:callBranch();" /></td>
				</tr>

				<tr>
					<td colspan="1" class="formtext"><label name="grade"
						id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
					:</td>
					<td colspan="1"><s:textfield size="25" name="gradeName"
						readonly="true" /> <s:hidden name="gradeCode" /></td>
					<td colspan="1"><img class="iconImage" id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" border="0" onclick="javascript:callGrade();" /></td>
					<td></td>
					<td colspan="1" class="formtext"><label class="set"
						name="designation" id="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td colspan="1"><s:textfield size="25" name="desgName"
						readonly="true" /> <s:hidden name="desgCode" /></td>
					<td colspan="1"><img class="iconImage" id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" border="0" onclick="javascript:callDesignation();" /></td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="7">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td colspan="3" align="right" ><input type="button" class="add" value=" Add"
						onclick="addList();" id="ctrlHide" /></td>
					<td colspan="3" align="left">&nbsp;<input type="button" class="reset" value=" Clear"
						onclick="clearList();" id="ctrlHide" /></td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="3" class="text_head"><strong
						class="forminnerhead">Gratuity applied to :</strong></td>
				</tr>
				<tr>
					<td class="formth" width="5%" valign="top"><label
						class="set" id="serial.no1" name="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
					<td class="formth" width="20%" valign="top"><label
						class="set" id="division1" name="division"
						ondblclick="callShowDiv(this);"><%=label.get("division")%></label></td>
					<td class="formth" width="15%" valign="top"><label
						class="set" id="employee.type1" name="employee.type"
						ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label></td>
					<td class="formth" width="15%" valign="top"><label
						class="set" id="department1" name="department"
						ondblclick="callShowDiv(this);"><%=label.get("department")%></label></td>
					<td class="formth" width="20%" valign="top"><label
						class="set" id="branch1" name="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label></td>
					<td class="formth" width="15%" valign="top"><label
						class="set" id="grade1" name="grade"
						ondblclick="callShowDiv(this);"><%=label.get("grade")%></label></td>
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
							value="empTypeNameItr" /><s:hidden name="empTypeNameItr" /><s:hidden
							name="empTypeCodeItr" /></td>
						<td width="15%" class="sortableTD"><s:property
							value="deptNameItr" /><s:hidden name="deptNameItr" /><s:hidden
							name="deptCodeItr" /></td>
						<td width="20%" class="sortableTD"><s:property
							value="branchNameItr" /><s:hidden name="branchNameItr" /> <s:hidden
							name="branchCodeItr" /></td>
						<td width="15%" class="sortableTD"><s:property
							value="gradeNameItr" /><s:hidden name="gradeNameItr" /><s:hidden
							name="gradeCodeItr" /></td>
						<td width="20%" class="sortableTD"><s:property
							value="desgNameItr" /><s:hidden name="desgCodeItr" /></td>
						<s:hidden name="desgNameItr" />
						<td width="5%" class="sortableTD" align="center"><s:hidden
							name="gratuityCode" /> <input type="button" class="rowEdit"
							class="edit" title="Edit Record"
							onclick="editList('<%=k%>','<s:property value="divisionCodeItr" />',
							'<s:property value="deptCodeItr" />','<s:property value="branchCodeItr" />',
							'<s:property value="gradeCodeItr" />','<s:property value="desgCodeItr" />',
							'<s:property value="divisionNameItr" />','<s:property value="deptNameItr" />',
							'<s:property value="branchNameItr" />','<s:property value="gradeNameItr" />',
							'<s:property value="desgNameItr" />','<s:property value="empTypeCodeItr" />',
							'<s:property value="empTypeNameItr" />');" /></td>
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
		<s:hidden name="subcode" /><s:hidden name="creditCodeHid" />
		<s:hidden name="creditAbbrHid" /><s:hidden name="listLength" />
		<s:hidden name="hiddenEdit" />

	</table>
</s:form>

<script>
	/*function callFields(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'GratuityConfig_callFields.action';
		document.getElementById('paraFrm').submit();
	}*/
	callOnload();
function callOnload(){
	var salOption =document.getElementById('paraFrm_salOptionHidden').value;
	var roundOption =document.getElementById('paraFrm_roundOffHidden').value;
	document.getElementById(salOption+'S').checked=true;
	document.getElementById(roundOption+'R').checked=true;
	//alert(document.getElementById(salOption+'S').checked);
	//alert(document.getElementById(roundOption+'R').checked);
}
	function callDivision(){
		callsF9(500,325,'GratuityConfig_f9division.action');
	}
	
	function callGrade(){
		callsF9(500,325,'GratuityConfig_f9gradeaction.action');
	}
	
	function callDept(){
		callsF9(500,325,'GratuityConfig_f9dept.action');
	}
	
	function callBranch(){
		callsF9(500,325,'GratuityConfig_f9branch.action');
	}
	
	function callDesignation(){
		callsF9(500,325,'GratuityConfig_f9designation.action');
	}
	
	function callEmpType(){
		callsF9(500,325,'GratuityConfig_f9empType.action');
	}
	
	function addList(){
		try{
   		var divCode = document.getElementById('paraFrm_divisionCode').value;
	   	var deptCode = document.getElementById('paraFrm_deptCode').value;
	   	var brnCode = document.getElementById('paraFrm_branchCode').value;
	   	var gradeCode = document.getElementById('paraFrm_gradeCode').value;
	   	var desgCode = document.getElementById('paraFrm_desgCode').value;
	   	var eTypeCode = document.getElementById('paraFrm_empTypeCode').value;
	   	if(divCode==""){
			alert("Please select "+document.getElementById('division').innerHTML.toLowerCase());
	     	document.getElementById("paraFrm_divisionName").focus();
	     	return false;
	   	}
	   	if(divCode=="" && deptCode=="" && brnCode=="" && gradeCode=="" && desgCode=="" && eTypeCode==""){
	   		 alert("Please select atleast one category. ");
	     	 document.getElementById("paraFrm_divisionName").focus();
	     	 return false;
	   	}
	   	document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'GratuityConfig_addItem.action';
		document.getElementById('paraFrm').submit();
		}catch(e){
			//alert(e);
		}
   	}
   	
   	function clearList(){
   		document.getElementById('paraFrm_hiddenEdit').value="";
		document.getElementById('paraFrm_divisionCode').value="";
	   	document.getElementById('paraFrm_deptCode').value="";
	   	document.getElementById('paraFrm_branchCode').value="";
	   	document.getElementById('paraFrm_empTypeCode').value="";
	   	document.getElementById('paraFrm_desgCode').value="";
	   	document.getElementById('paraFrm_gradeCode').value="";
	   	document.getElementById('paraFrm_divisionName').value="";
	   	document.getElementById('paraFrm_deptName').value="";
	   	document.getElementById('paraFrm_branchName').value="";
	   	document.getElementById('paraFrm_empTypeName').value="";
	   	document.getElementById('paraFrm_desgName').value="";
	   	document.getElementById('paraFrm_gradeName').value="";
   	}
   	
   	function editList(srNo,divCode,deptCode,brnCode,gradeCode,desgCode,
   						divName,deptName,brnName,gradeName,desgName,eTypeCode,eTypeName) {
		try{
		document.getElementById('paraFrm_hiddenEdit').value=srNo;
		document.getElementById('paraFrm_divisionCode').value=divCode;
	   	document.getElementById('paraFrm_deptCode').value=deptCode;
	   	document.getElementById('paraFrm_branchCode').value=brnCode;
	   	document.getElementById('paraFrm_empTypeCode').value=eTypeCode;
	   	document.getElementById('paraFrm_desgCode').value=desgCode;
	   	document.getElementById('paraFrm_gradeCode').value=gradeCode;
	   	
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
	   	if(trim(gradeName)=="-"){
	   		document.getElementById('paraFrm_gradeName').value="";
	   	}else
	   		document.getElementById('paraFrm_gradeName').value=gradeName;
		
		}catch(e){
			alert(e);
		}
		return true;
	}
	
	 function callCreditCombination(setCreditCode, setCreditAbbr) {
		document.getElementById('paraFrm_creditCodeHid').value = setCreditCode;
		document.getElementById('paraFrm_creditAbbrHid').value = setCreditAbbr;
		
		window.open('','new','top=300,left=300,width=350,height=300,scrollbars=yes,status=no,resizable=no');
	 	document.getElementById("paraFrm").target = "new";
		document.getElementById("paraFrm").action = 'GratuityConfig_creditCombination.action'; 
	  	document.getElementById("paraFrm").submit();
	  	document.getElementById("paraFrm").target = "main";
	}
	
	function saveFun(){
		//if(document.getElementById("gratuityFlag").checked){
			if(document.getElementById("paraFrm_listLength").value=="0"){
		     	 alert("Please select atleast one category. ");
		     	 document.getElementById("paraFrm_divisionName").focus();
		     	 return false;
	     	 }
	    // } 
  	 	
  	 	document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'GratuityConfig_save.action';
		document.getElementById('paraFrm').submit();
	}
	function deleteList(rowId){
	var conf=confirm("Do you really want to remove this record ?");
  			if(conf) {
		document.getElementById('paraFrm_hiddenEdit').value=rowId;
		document.getElementById('paraFrm').action = 'GratuityConfig_deleteRow.action';
		document.getElementById('paraFrm').submit();
		}else{
			return false;
			}
	} 
	
	function callroundOffOption(value){
		document.getElementById('paraFrm_roundOffHidden').value=value;
	}
	function callSalOption(value){
		document.getElementById('paraFrm_salOptionHidden').value=value;
	}
</script>