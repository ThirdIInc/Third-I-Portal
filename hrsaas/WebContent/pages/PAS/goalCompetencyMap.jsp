<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="AppraisalCalendar" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="appraisalId" />
	<s:hidden name="backAction" />
	
	  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
    
   
    <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Goal / Competency Mapping</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
    
    <script>
   function setEmpDivCheck(){
    if(document.getElementById("paraFrm_hideEmpDivCheck").value=="Y"){
				document.getElementById("empDivCheck").checked=true;
			}else{
				document.getElementById("empDivCheck").checked="";
			}
			
	}
	
        function setJoinDateCheck(){
			if(document.getElementById("paraFrm_hideJoinDateCheck").value=="Y"){
				document.getElementById("joinDateCheck").checked=true;
				document.getElementById("joinDateDiv").style.display ='';
			
	}
	}
		function setEmpTypeCheck(){
			if(document.getElementById("paraFrm_hideEmpTypeCheck").value=="Y"){
				document.getElementById("empTypeCheck").checked=true;
			}else{
				document.getElementById("empTypeCheck").checked="";
			}
		}
		 function setEmpGradeCheck(){
			if(document.getElementById("paraFrm_hideEmpGradeCheck").value=="Y"){
				document.getElementById("empGradeCheck").checked=true;
			}
			else{
				document.getElementById("empGradeCheck").checked="";
			}
		}
		function setEmpDeptCheck(){
			if(document.getElementById("paraFrm_hideEmpDeptCheck").value=="Y"){
				document.getElementById("empDeptCheck").checked=true;
			}
			else{
				document.getElementById("empDeptCheck").checked="";
			}
		}
		function setAutoStartCheck(){
			if(document.getElementById("paraFrm_hideAutoStart").value=="Y"){
				document.getElementById("autoStart").checked=true;
			}
			else{
				document.getElementById("autoStart").checked="";
			}
		}
   </script><s:hidden name="edit"/><s:hidden name="importStartDate"></s:hidden><s:hidden name="importEndDate"></s:hidden>
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2">
         <s:if test="isApprove"></s:if><s:else>
          <tr>
           
	<td  width="60%">
					<input	type="button" class="add" value=" Goal/Competency Mapping "
							onclick="return goalCompetencyMapFun();" />
					<input type="button" class="back" value=" Back "
							onclick="return callBack();" />		
				</td>
	
	
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr></s:else>
        </table>
          </td>
    </tr>
    
    
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
              
            <tr>
              
              
              <td width="30%" colspan="1" height="20" class="formtext"><label  class = "set"  name="map.goal" id="map.goal" ondblclick="callShowDiv(this);"><%=label.get("map.goal")%></label>  :</td>
			  <td width="70%" colspan="3" height="20"><input type="checkbox"  name="goalMapCheck" class="checkbox" id="goalMapCheck"   onclick="return callGoalMapCheckCheck();"></input><s:hidden name="hiddengoalMap"/></td>
             
            </tr>
            <tr>
              
              
              <td width="30%" colspan="1" height="20" class="formtext"><label  class = "set"  name="map.competency" id="map.competency" ondblclick="callShowDiv(this);"><%=label.get("map.competency")%></label>  :</td>
			  <td width="70%" colspan="3" height="20"><input type="checkbox"  name="competencyMapCheck" class="checkbox" id="competencyMapCheck"   onclick="return callCompetencyMapCheck();"></input><s:hidden name="hiddencompetencyMap"/></td>
             
            </tr>
            
            <tr><td nowrap="nowrap" colspan="2" id="mapGoalComp">	
								<a href="#" onclick="callFun();">Click here to Map Goal and Competency </a> 
									</td></tr>
          
            </table></td>
          </tr>
      </table></td>
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

function callsetPhaseFlag()
{
	if(document.getElementById("importPhaseIdFlag").checked)
	{
		
		document.getElementById("hiddenimportPhaseIdFlag").value="Y";
	}
	else
	{
		document.getElementById("hiddenimportPhaseIdFlag").value="N";
	}
}
function callsetAppraiserFlag()
{
	if(document.getElementById("importAppriaserFlag").checked)
	{
		document.getElementById("importPhaseIdFlag").checked=true;
		document.getElementById("hiddenimportPhaseIdFlag").value="Y";
		document.getElementById("hiddenimportAppriaserFlag").value="Y";
	}
	else
	{
		
		document.getElementById("hiddenimportAppriaserFlag").value="N";
		 callsetPhaseFlag();
	}
}
function callsetTemplageFlag()
{
	if(document.getElementById("importTemplateFlag").checked)
	{
		document.getElementById("hiddenimportTemplateFlag").value="Y";
		document.getElementById("importPhaseIdFlag").checked=true;
		document.getElementById("importAppriaserFlag").checked=true;
		document.getElementById("hiddenimportPhaseIdFlag").value="Y";
		document.getElementById("hiddenimportAppriaserFlag").value="Y";
	}
	else
	{
		document.getElementById("hiddenimportTemplateFlag").value="N";
		callsetAppraiserFlag();
	}
}

function callFun()
{
	if(document.getElementById("paraFrm_appraisalId").value=="")
	{
		alert("Please Save Record");
		return false;
	}
	document.getElementById("paraFrm").action="AppraisalCalendar_mapGoalCompt.action";
	document.getElementById("paraFrm").submit();
}

function callGoalMapCheckCheck()
{
	if(document.getElementById("goalMapCheck").checked)
	{
		document.getElementById("paraFrm_hiddengoalMap").value='Y';
		document.getElementById('mapGoalComp').style.display ='inline';
	}else
	{
		
		document.getElementById("paraFrm_hiddengoalMap").value='N';
		if(!document.getElementById("competencyMapCheck").checked)
		{
			document.getElementById('mapGoalComp').style.display ='none';
		}
	}
}

function callCompetencyMapCheck()
{
	if(document.getElementById("competencyMapCheck").checked)
	{
		document.getElementById("paraFrm_hiddencompetencyMap").value='Y';
		document.getElementById('mapGoalComp').style.display ='inline';
	}else
	{
		document.getElementById("paraFrm_hiddencompetencyMap").value='N';
		if(!document.getElementById("goalMapCheck").checked)
		{
			document.getElementById('mapGoalComp').style.display ='none';
		}
	}
}
onload();
/* ======================================================================
	Function	: callChk
	Explanation : change the status of check box as mouse clicked on check box
	========================================================================== */
	

function cancelFun(){
	document.getElementById("paraFrm").action="AppraisalCalendar_cancel.action";
	document.getElementById("paraFrm").submit();
}

function addnewFun(){
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
		
		if(document.getElementById("paraFrm_isStarted").value=="Y"){
			alert("Appraisal process has been started.\You can't update the calendar.");
			return false;
		}
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
		
		if(document.getElementById("paraFrm_hideImportConfig").value=='Y')
		{
			if(document.getElementById("paraFrm_importAppraisalID").value=="")
				{
					alert("Please select Appraisal Code");
					return false;
				}
		}
		if(document.getElementById("paraFrm_hideImportContentConfig").value=='Y')
		{
				if(document.getElementById("paraFrm_appraisalIdPhase").value=="")
				{
					alert("Please select Appraisal Code");
					return false;
				}
			
		
			/*if(document.getElementById("paraFrm_appraisalIdPhase").value=="" && document.getElementById("paraFrm_appraisalIdRating").value=="" && document.getElementById("paraFrm_appraisalIdTemplate").value=="" && document.getElementById("paraFrm_appraisalIdAppraisers").value=="")
				{
					alert("Please select Appraisal code for partial import");
					return false;
				}
			if(document.getElementById("paraFrm_appraisalIdAppraisers").value!="")
			{
				if(document.getElementById("paraFrm_appraisalIdPhase").value=="")
				{
					alert("Please select phase");
					return false;
				}
				else if(document.getElementById("paraFrm_appraisalIdPhase").value!=document.getElementById("paraFrm_appraisalIdAppraisers").value)
				{
					alert("Appraisal code Should be same for Phase and Appriaser");
					return false;
				}
			}
			if(document.getElementById("paraFrm_appraisalIdTemplate").value!="")
			{
				if(document.getElementById("paraFrm_appraisalIdPhase").value=="")
				{
					alert("Please select phase Appraisal code");
					return false;
				}
				if(document.getElementById("paraFrm_appraisalIdAppraisers").value=="")
				{
					alert("Please select appraisers Appraisal code");
					return false;
				}
				else if(document.getElementById("paraFrm_appraisalIdPhase").value!=document.getElementById("paraFrm_appraisalIdAppraisers").value)
				{
					
					alert("Appraisal code Should be same for Phase,Appriaser and Template");
					return false;
				}else if(document.getElementById("paraFrm_appraisalIdPhase").value!=document.getElementById("paraFrm_appraisalIdTemplate").value)
				{
					alert("Appraisal code Should be same for Phase,Appriaser and Template");
					return false;
				}
			}*/
		}
		if((document.getElementById("paraFrm_hideImportConfig").value!='Y')&&(document.getElementById("paraFrm_hideImportContentConfig").value!='Y')) 
		{
			if(!criteriaValidation()){
			return false;
			}
		}
		selectList();
		document.getElementById("paraFrm").action="AppraisalCalendar_save.action";
		document.getElementById("paraFrm").submit();
		deSelectList();
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
function deSelectAllOptions(selId,availId){
		
		try{
		for(i=0;i<selId.length;i++)
		selId.options[i].selected=false;
		for(i=0;i<availId.length;i++)
		availId.options[i].selected=false;
	
	}catch(e)
	{
		alert(e);
	}
}
function selectAllOptionsList(selId,availId){
		
		try{
		for(i=0;i<selId.length;i++)
		selId.options[i].selected=true;
		for(i=0;i<availId.length;i++)
		availId.options[i].selected=true;
	
	}catch(e)
	{
		alert(e);
	}
}

	function validateFields(){
		
		return true;
	}

	
	
	function goalCompetencyMapFun() {
		try {
			
			if(!validateFields()) {
				return false;
			}   
		
		var con = confirm("Do you really want to Set Goal/Competency Mapping?");
		if (con) {
				
		var calCode = document.getElementById('paraFrm_appraisalId').value;
		alert(calCode);
			var backAction = "<%=request.getContextPath()%>/pas/AppraisalCalendar_callForEdit.action?calCode="+calCode;
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action='<%=request.getContextPath()%>/pas/AppraisalCalendar_goalCompetencyMap.action?calCode='+calCode+'&backAction='+backAction;
		document.getElementById('paraFrm').submit();
			
					
		} else {
			return false;
		}
	  } catch(e) {
	  	alert("Exception in processFun >>>>"+e);
	  	return false;
	  }
	}
	
	function callBack(){
		var calCode = document.getElementById('paraFrm_appraisalId').value;
		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById("paraFrm").action="AppraisalCalendar_callForEdit.action?calCode="+calCode;
		document.getElementById('paraFrm').submit();
	}
</script>
