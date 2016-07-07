<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="AppraisalCalendar" validate="true" id="paraFrm"
	theme="simple">

	<s:hidden name="appraisalId" />
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">


		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Appraisal
					Calendar</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<script>
   
		function setAutoStartCheck(){
			if(document.getElementById("paraFrm_hideAutoStart").value=="Y"){
				document.getElementById("autoStart").checked=true;
			}
			else{
				document.getElementById("autoStart").checked="";
			}
		}
   </script>
		<s:hidden name="edit" />
		<s:hidden name="addNew" />
		<s:hidden name="onload" />
		<s:hidden name="importStartDate"></s:hidden>
		<s:hidden name="importEndDate"></s:hidden>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2">
				<s:if test="isApprove"></s:if>
				<s:else>
					<tr>
						<td width="21.5%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="62.5%" align="left"><s:if test="appraisalId!=''">
							<input type="button" value="Import Appraisal" class="add"
								onclick="return importAppraisalFun();" />
							<input type="button" class="add" value=" Eligibility Criteria "
								onclick="return eligibilityCriteriaFun();" />
							<input type="button" class="add"
								value=" Goal / Competency Mapping "
								onclick="return goalCompetencyMapFun();" />
						</s:if></td>
						<td width="15%" align="right"><font color="red">*</font> Indicates
						Required
						</td>
						
					</tr>
				</s:else>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2">
						<tr>
							<td width="25%" colspan="1" height="20" class="formtext"><label
								class="set" name="appraisal.code" id="appraisal.code"
								ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label>
							<font color="red">*</font> :</td>
							<td width="25%" colspan="1" height="20"><s:textfield
								name="appraisalCode" size="20" maxlength="45"
								onkeypress="return allCharacters();" /><s:hidden
								name="isStarted" /></td>
							<td width="25%" colspan="1" height="20" class="formtext"><label
								class="set" name="valid.till" id="valid.till"
								ondblclick="callShowDiv(this);"><%=label.get("valid.till")%></label>
							<font color="red">*</font> :</td>
							<td width="25%" colspan="1" height="20"><s:textfield
								name="validTill" size="20"
								onkeypress="return numbersWithHiphen();" maxlength="10" /><s:a
								href="javascript:NewCal('paraFrm_validTill','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="18" align="absmiddle" width="18"
									id="ctrlHide">
							</s:a></td>
						</tr>
						<tr>
							<td width="25%" colspan="1" height="20" class="formtext"><label
								class="set" name="start.date" id="start.date"
								ondblclick="callShowDiv(this);"><%=label.get("start.date")%></label>
							<font color="red">*</font> :</td>
							<td width="25%" colspan="1" height="20"><s:textfield
								name="startDate" size="20"
								onkeypress="return numbersWithHiphen();" maxlength="10" /><s:a
								href="javascript:NewCal('paraFrm_startDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="18" align="absmiddle" width="18"
									id="ctrlHide">
							</s:a></td>

							<td width="25%" colspan="1" height="20" class="formtext"><label
								class="set" name="end.date" id="end.date"
								ondblclick="callShowDiv(this);"><%=label.get("end.date")%></label>
							<font color="red">*</font> :</td>
							<td width="25%" colspan="1" height="20"><s:textfield
								name="endDate" size="20"
								onkeypress="return numbersWithHiphen();" maxlength="10" /><s:a
								href="javascript:NewCal('paraFrm_endDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="18" align="absmiddle" width="18"
									id="ctrlHide">
							</s:a></td>

						</tr>
						<tr>
							<td width="25%" class="formtext" height="20"><label
								class="set" name="apprCal.isApprScoreShow"
								id="apprCal.isApprScoreShow" ondblclick="callShowDiv(this);"><%=label.get("apprCal.isApprScoreShow")%></label>
							:</td>
							<td width="25%" height="20"><input type="checkbox"
								class="checkbox" id="isScoreShow" name="isScoreShow"
								onclick="callChkScore();" /><s:hidden name="hiddenisScoreShow" /><script>setAutoStartCheck();</script>
							<td width="25%" colspan="1" height="20" class="formtext">&nbsp;</td>
							<td width="25%" colspan="1" height="20">&nbsp;</td>
						</tr>
					</table>
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
					<td>
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2">
						<tr>
							<td width="25%" colspan="1" height="20" class="formtext"><label
								class="set" name="comments" id="comments"
								ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>
							:</td>
							<td width="75%" colspan="3" height="20"><s:textarea
								name="comments" cols="100" rows="3" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2">
				<s:if test="isApprove"></s:if>
				<s:else>
					<tr>
						<td width="22.5%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="77.5%"><s:if test="appraisalId!=''">
							<input type="button" class="add" value=" Import Appraisal "
								onclick="return importAppraisalFun();" />
							<input type="button" class="add" value=" Eligibility Criteria "
								onclick="return eligibilityCriteriaFun();" />
							<input type="button" class="add"
								value=" Goal / Competency Mapping "
								onclick="return goalCompetencyMapFun();" />
						</s:if></td>
					</tr>
				</s:else>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>


<script type="text/javascript">
//autoDate();
function callChkScore()
{
	if(document.getElementById('isScoreShow').checked)
	{
		document.getElementById('paraFrm_hiddenisScoreShow').value="Y";
	}else
	{
		document.getElementById('paraFrm_hiddenisScoreShow').value="N";
	}
}

onload();
/* ======================================================================
	Function	: callChk
	Explanation : change the status of check box as mouse clicked on check box
	========================================================================== */
	
	
	function callChk(){
	//document.getElementById("paraFrm_autoStart").value='Y';
		 if(document.getElementById("paraFrm_hideAutoStart").value=='Y'){
		   	document.getElementById("autoStart").value='N';
		   	document.getElementById("paraFrm_hideAutoStart").value='N';
		   	
		  	  }else  {
		  	document.getElementById("autoStart").value='Y';
		  	document.getElementById("paraFrm_hideAutoStart").value='Y';
		  	 }
	}



function cancelFun(){
	document.getElementById("paraFrm").action="AppraisalCalendar_cancel.action";
	document.getElementById("paraFrm").submit();
}

function addnewFun(){
		document.getElementById("paraFrm").target="main";
		document.getElementById("paraFrm").action="AppraisalCalendar_addNew.action";
		document.getElementById("paraFrm").submit();
}
function editFun(){
		document.getElementById("paraFrm").action="AppraisalCalendar_edit.action";
		document.getElementById("paraFrm").submit();
}
function searchFun(){
		javascript:callsF9(500,325,'AppraisalCalendar_f9Search.action');	
}


function saveFun(){
		try{
		
		var fields=["paraFrm_appraisalCode","paraFrm_validTill","paraFrm_startDate","paraFrm_endDate"];
		var labels=[document.getElementById("appraisal.code").innerHTML,document.getElementById("valid.till").innerHTML,
		document.getElementById("start.date").innerHTML,document.getElementById("end.date").innerHTML];
		var types=["enter","enter","enter","enter"];
		 var f9Fields=["paraFrm_appraisalCode"];
		 
		var validTill = document.getElementById("paraFrm_validTill").value;
		var startDate = document.getElementById("paraFrm_startDate").value;
		var endDate = document.getElementById("paraFrm_endDate").value;
		
		
		if(!(checkMandatory(fields,labels,types))){
		return false;
		}
		if(!validateDate('paraFrm_validTill',"valid.till")){
		return false;
		}
		if(!validateDate('paraFrm_startDate',"start.date")){
		return false;
		}
		if(!validateDate('paraFrm_endDate',"end.date")){
		return false;
		}
		if(!dateDifferenceEqual(startDate, endDate, "paraFrm_endDate", "start.date", "end.date")){
		return false;
		}
		if(!dateDifferenceEqual(endDate, validTill, "paraFrm_validTill","end.date", "valid.till")){
		return false;
		} 
		
		if(!f9specialchars(f9Fields))
		return false;
		
		
		document.getElementById("paraFrm").action="AppraisalCalendar_save.action";
		document.getElementById("paraFrm").submit();
	
		}catch(e){
		alert(e);
		}
}
function onload(){
		if(document.getElementById('paraFrm_hiddenisScoreShow').value=='N')	
		   {
		   		
		   		document.getElementById('isScoreShow').checked=false;
		   		
		   }else if(document.getElementById('paraFrm_hiddenisScoreShow').value=='Y')
		   {
		   		
		   		document.getElementById('isScoreShow').checked=true;
		   }
		if(document.getElementById("paraFrm_hiddengoalMap").value=="Y")
		{
			document.getElementById('mapGoalComp').style.display ='inline';
			document.getElementById('goalMapCheck').checked=true;
		}
		if(document.getElementById("paraFrm_hiddencompetencyMap").value=="Y")
		{
			document.getElementById('mapGoalComp').style.display ='inline';
			document.getElementById('competencyMapCheck').checked=true;
		}
		
		if(document.getElementById("paraFrm_hiddengoalMap").value!="Y" && document.getElementById("paraFrm_hiddencompetencyMap").value!="Y")
		{
			document.getElementById('mapGoalComp').style.display ='none';
		}
		if(document.getElementById("paraFrm_hideJoinDateCheck").value=="Y"){
		document.getElementById("joinDateDiv").style.display ='';
		}else{
		document.getElementById("joinDateDiv").style.display ='none';
		}
		var condn = document.getElementById("paraFrm_joinDateCondition").value;
			if(condn=="2"){
				document.getElementById("joinDateToDiv").style.display ='';
				document.getElementById("joinDateFromDiv").style.display ='none';
			}else{
				document.getElementById("joinDateToDiv").style.display ='none';
				document.getElementById("joinDateFromDiv").style.display ='';
		}
		if(document.getElementById("paraFrm_hideEmpDivCheck").value=="Y"){
				document.getElementById("divisionDiv").style.display ='';
			}else{
			document.getElementById("divisionDiv").style.display ='none';
			}
		if(document.getElementById("paraFrm_hideEmpTypeCheck").value=="Y"){
				document.getElementById("empTypeDiv").style.display ='';
			}else{
			document.getElementById("empTypeDiv").style.display ='none';
			}
		if(document.getElementById("paraFrm_hideEmpTypeCheck").value=="Y"){
				document.getElementById("empTypeDiv").style.display ='';
			}else{
			document.getElementById("empTypeDiv").style.display ='none';
			}
		if(document.getElementById("paraFrm_hideEmpGradeCheck").value=="Y"){
				document.getElementById("empGradeDiv").style.display ='';
			}else{
			document.getElementById("empGradeDiv").style.display ='none';
			}
		if(document.getElementById("paraFrm_hideEmpDeptCheck").value=="Y"){
				document.getElementById("empDeptDiv").style.display ='';
			}else{
			document.getElementById("empDeptDiv").style.display ='none';
			}
			
		document.getElementById("importDiv").style.display ='none';
		document.getElementById("importContentDiv").style.display ='none';
}


	function validateFields(){
		try{
		
		var fields=["paraFrm_appraisalCode","paraFrm_validTill","paraFrm_startDate","paraFrm_endDate"];
		var labels=[document.getElementById("appraisal.code").innerHTML,document.getElementById("valid.till").innerHTML,
		document.getElementById("start.date").innerHTML,document.getElementById("end.date").innerHTML];
		var types=["enter","enter","enter","enter"];
		 var f9Fields=["paraFrm_appraisalCode"];
		 
		var validTill = document.getElementById("paraFrm_validTill").value;
		var startDate = document.getElementById("paraFrm_startDate").value;
		var endDate = document.getElementById("paraFrm_endDate").value;
		
		
		if(!(checkMandatory(fields,labels,types))){
		return false;
		}
		if(!validateDate('paraFrm_validTill',"valid.till")){
		return false;
		}
		if(!validateDate('paraFrm_startDate',"start.date")){
		return false;
		}
		if(!validateDate('paraFrm_endDate',"end.date")){
		return false;
		}
		if(!dateDifferenceEqual(startDate, endDate, "paraFrm_endDate", "start.date", "end.date")){
		return false;
		}
		if(!dateDifferenceEqual(endDate, validTill, "paraFrm_validTill","end.date", "valid.till")){
		return false;
		} 
		
		if(!f9specialchars(f9Fields))
		return false;
		
		}catch(e){
		alert(e);
		
		}
		return true;
	}

	function importAppraisalFun() {
		try {
			
			if(!validateFields()) {
				return false;
			}   
		
		var con = confirm("Do you really want to import Apraisal Details?");
		if (con) {
				
	var calCode = document.getElementById('paraFrm_appraisalId').value;
	///alert(calCode);
		var backAction = "<%=request.getContextPath()%>/pas/AppraisalCalendar_callForEdit.action?calCode="+calCode;
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action='<%=request.getContextPath()%>/pas/AppraisalCalendar_importAppraisal.action?calCode='+calCode+'&backAction='+backAction;
	document.getElementById('paraFrm').submit();
			
					
		} else {
			return false;
		}
	  } catch(e) {
	  	///alert("Exception in processFun >>>>"+e);
	  	return false;
	  }
	}
	
	
	function eligibilityCriteriaFun() {
		try {
			
			if(!validateFields()) {
				return false;
			}   
		
		var con = confirm("Do you really want to Set Eligibility Criteria?");
		if (con) {
				
		var calCode = document.getElementById('paraFrm_appraisalId').value;
		///alert(calCode);
			var backAction = "<%=request.getContextPath()%>/pas/AppraisalCalendar_callForEdit.action?calCode="+calCode;
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action='<%=request.getContextPath()%>/pas/AppraisalCalendar_eligibilityCriteria.action?calCode='+calCode+'&backAction='+backAction;
		document.getElementById('paraFrm').submit();
			
					
		} else {
			return false;
		}
	  } catch(e) {
	  ///	alert("Exception in processFun >>>>"+e);
	  	return false;
	  }
	}
	
	function goalCompetencyMapFun() {
		try {
			
			if(!validateFields()) {
				return false;
			}   
		
		var con = confirm("Do you really want to Set Goal/Competency Mapping?");
		if (con) {
				
		var calCode = document.getElementById('paraFrm_appraisalId').value;
		///alert(calCode);
			var backAction = "<%=request.getContextPath()%>/pas/AppraisalCalendar_callForEdit.action?calCode="+calCode;
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action='<%=request.getContextPath()%>/pas/AppraisalCalendar_goalCompetencyMap.action?calCode='+calCode+'&backAction='+backAction;
		document.getElementById('paraFrm').submit();
			
					
		} else {
			return false;
		}
	  } catch(e) {
	  	///alert("Exception in processFun >>>>"+e);
	  	return false;
	  }
	}
	
	function deleteFun() {
	var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'AppraisalCalendar_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
</script>
