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
				          <td width="93%" class="txt"><strong class="text_head">Eligibility Criteria</strong></td>
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
   </script><s:hidden name="importStartDate"></s:hidden><s:hidden name="importEndDate"></s:hidden>
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2">
         <s:if test="isApprove"></s:if><s:else>
          <tr>
	<td  width="80%">
					<input type="button"
							class="add" value="Save Eligibility Criteria " onclick="return eligibilityCriteriaFun();" /> 
					<input type="button" class="back" value=" Back "
							onclick="return callBack();" />
				</td>
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr></s:else>
        </table>
          </td>
    </tr>
    <tr>
			<td>
				<fieldset><legend class="legend1"> Set Eligibility Criteria </legend>
					<table width="99%" border="0" align="center" cellpadding="2"
											cellspacing="1">
    <tr>
      <td colspan="3" ><div
									style=""
									bgcolor="#FFFFFF" align="center"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td><table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
              
               <tr>
               <td colspan="3" width="100%" ><input type="checkbox"  name="empDivCheck" class="checkbox" id="empDivCheck"   onclick="return callDivCheck();"></input><s:hidden name="hideEmpDivCheck"/>
               <script>setEmpDivCheck();</script><label  class = "set" name="based.on.division" id="based.on.division" ondblclick="callShowDiv(this);"><%=label.get("based.on.division")%></label> 
               </td>
               </tr>
               <tr>
               <td>
               		<div id="divisionDiv">
               			<table border="0" width="100%">
               				<tr>
               					<td width="25%" class="formtext" colspan="1" nowrap="nowrap"><label  class = "set" name="select.employee.div" id="select.employee.div" ondblclick="callShowDiv(this);"><%=label.get("select.employee.div")%></label> :</td>
               					<td width="25%" colspan="1" nowrap="nowrap"><s:if test="edit"><s:optiontransferselect size="10" doubleSize="10" doubleId="selDivId" id="availDivId"
								label="Employee Division" rightTitle="Selected Division" leftTitle="Available Division" 
								addToLeftLabel="<< Remove" addToRightLabel="Add >>" addAllToLeftLabel="Remove All"
								addAllToRightLabel="Add All" selectAllLabel="Select All" cssStyle="width:100px"
								doubleCssStyle="width:100px" name="availDiv" multiple="true"
								buttonCssClass="token" list="%{hashmapDiv}"
								doubleName="selDiv" doubleList="%{hashmapDivSel}" /></s:if>
								<s:else><s:optiontransferselect size="10" doubleSize="10" doubleId="selDivId" id="availDivId"
								label="Employee Division" rightTitle="Selected Division" leftTitle="Available Division" 
								addToLeftLabel="<< Remove" addToRightLabel="Add >>" addAllToLeftLabel="Remove All"
								addAllToRightLabel="Add All" selectAllLabel="Select All" cssStyle="width:100px"
								doubleCssStyle="width:100px" name="availDiv" multiple="true"
								buttonCssClass="token" list="%{hashmapDiv}"
								doubleName="selDiv" doubleList="{}" />
								</s:else></td>
								<td width="25%" class="formtext" colspan="1"></td>
               					<td width="25%" colspan="1" nowrap="nowrap"></td>
              			    </tr>
         				</table>
         			</div>
         	   </td>
    </tr>
          <tr>
               <td colspan="3" width="100%" ><input type="checkbox"  name="joinDateCheck" class="checkbox" id="joinDateCheck"   onclick="return callJoinDateCheck();"></input><s:hidden name="hideJoinDateCheck"></s:hidden>
               <label  class = "set" name="based.on.join.date" id="based.on.join.date" ondblclick="callShowDiv(this);"><%=label.get("based.on.join.date")%></label> 
               </td>
               </tr>
               <tr>
               <td><div id="joinDateDiv"><table border="0" width="100%">
               <tr>
               <td width="25%" class="formtext" colspan="1" nowrap="nowrap"><label name="employee.join.date" class = "set"  id="employee.join.date" ondblclick="callShowDiv(this);"><%=label.get("employee.join.date")%></label> :</td>
               		<td width="25%" colspan="1" nowrap="nowrap"><s:select
								name="joinDateCondition" headerKey="1"
								headerValue="On" cssStyle="width:150"
								list="#{'2':'Between','3':'Before','4':'After','5':'On or Before','6':'On or After' }"
								onchange="return callJoinDateCond();" /></td>
					<td width="25%" class="formtext" colspan="1"></td>
               		<td width="25%" colspan="1" nowrap="nowrap"></td>
               </tr>
               
               <tr>
               <td width="25%" class="formtext" colspan="4">
               <div id="joinDateFromDiv">
               <table width="100%" border="0">
               <tr>
               <td width="25%" class="formtext" colspan="1" align="right"><label  class = "set" name="join.date"  id="join.date" ondblclick="callShowDiv(this);"><%=label.get("join.date")%></label> <font color="red">*</font>:</td>
               		<td width="75%" colspan="3" nowrap="nowrap"><s:textfield name="joinDate" size="20" onkeypress="return numbersWithHiphen();" maxlength="10"></s:textfield>
               		<s:a href="javascript:NewCal('paraFrm_joinDate','DDMMYYYY');">
					 <img src="../pages/images/recruitment/Date.gif" class="iconImage" height="18" align="absmiddle" width="18"></s:a></td>
               </tr>
               </table>
               </div>
               </td>
               </tr>
               <tr>
               <td width="25%" class="formtext" colspan="4">
               <div id="joinDateToDiv">
               <table width="100%" border="0">
               <tr>
               <td width="25%" class="formtext" colspan="1" align="right"><label  class = "set" name="from.date" id="from.date" ondblclick="callShowDiv(this);"><%=label.get("from.date")%></label> <font color="red">*</font>:</td>
               		<td width="25%" colspan="1" nowrap="nowrap"><s:textfield name="joinFromDate" size="20" onkeypress="return numbersWithHiphen();" maxlength="10"></s:textfield>
               		<s:a href="javascript:NewCal('paraFrm_joinFromDate','DDMMYYYY');">
					 <img src="../pages/images/recruitment/Date.gif" class="iconImage" height="18" align="absmiddle" width="18"></s:a></td>
               <td width="25%" class="formtext" colspan="1" align="right"><label  class = "set" name="to.date"  id="to.date" ondblclick="callShowDiv(this);"><%=label.get("to.date")%></label> <font color="red">*</font>:</td>
               		<td width="25%" colspan="1" nowrap="nowrap"><s:textfield name="joinToDate" size="20" onkeypress="return numbersWithHiphen();" maxlength="10"></s:textfield>
               		<s:a href="javascript:NewCal('paraFrm_joinToDate','DDMMYYYY');">
					 <img src="../pages/images/recruitment/Date.gif" class="iconImage" height="18" align="absmiddle" width="18"></s:a></td>
               </tr>
               </table></div>
               </td></tr>
                <script>setJoinDateCheck();</script>
           
      </table></td>
    </tr>
    <tr>
               <td colspan="3" width="100%" >
               <input type="checkbox"  name="empTypeCheck" class="checkbox" id="empTypeCheck"  
               onclick="return callEmpTypeCheck();"></input>
              <!-- <s:checkbox name="empTypeCheck" onclick="return callEmpTypeCheck();"/>-->
               <s:hidden name="hideEmpTypeCheck"/>
               <script>setEmpTypeCheck();</script><label  class = "set" name="based.on.emp.type" id="based.on.emp.type" ondblclick="callShowDiv(this);"><%=label.get("based.on.emp.type")%></label> 
               </td>
               </tr>
    <tr>
               <td>
               		<div id="empTypeDiv">
               			<table border="0" width="100%">
               				<tr>
               					<td width="25%" class="formtext" colspan="1" nowrap="nowrap"><label  class = "set" name="select.employee.type" id="select.employee.type" ondblclick="callShowDiv(this);"><%=label.get("select.employee.type")%></label> :</td>
               					<td width="25%" colspan="1" nowrap="nowrap"><s:if test="edit"><s:optiontransferselect size="10" doubleSize="10" doubleId="selTypeId" id="availTypeId"
								label="Employee Type" rightTitle="Selected Type" leftTitle="Available Type" 
								addToLeftLabel="<< Remove" addToRightLabel="Add >>" addAllToLeftLabel="Remove All"
								addAllToRightLabel="Add All" selectAllLabel="Select All" cssStyle="width:100px"
								doubleCssStyle="width:100px" name="availType" multiple="true"
								buttonCssClass="token" list="%{hashmapType}"
								doubleName="selType" doubleList="%{hashmapTypeSel}" /></s:if>
								<s:else><s:optiontransferselect size="10" doubleSize="10" doubleId="selTypeId" id="availTypeId"
								label="Font Family" rightTitle="Selected Type" leftTitle="Available Type" 
								addToLeftLabel="<< Remove" addToRightLabel="Add >>" addAllToLeftLabel="Remove All"
								addAllToRightLabel="Add All" selectAllLabel="Select All" cssStyle="width:100px"
								doubleCssStyle="width:100px" name="availType" multiple="true"
								buttonCssClass="token" list="%{hashmapType}"
								doubleName="selType" doubleList="{}" />
								</s:else></td>
								<td width="25%" class="formtext" colspan="1"></td>
               					<td width="25%" colspan="1" nowrap="nowrap"></td>
              			    </tr>
         				</table>
         			</div>
         	   </td>
    </tr>
    <tr>
               <td colspan="3" width="100%" ><input type="checkbox"  name="empGradeCheck" class="checkbox" id="empGradeCheck"   onclick="return callEmpGradeCheck();"></input><s:hidden name="hideEmpGradeCheck"></s:hidden>
               <script>setEmpGradeCheck();</script><label  class = "set" name="based.on.emp.grade" id="based.on.emp.grade" ondblclick="callShowDiv(this);"><%=label.get("based.on.emp.grade")%></label> 
               </td>
               </tr>
    <tr>
               <td>
               		<div id="empGradeDiv">
               			<table border="0" width="100%">
               				<tr>
               					<td width="25%" class="formtext" colspan="1" nowrap="nowrap"><label  class = "set" name="select.employee.grade"  id="select.employee.grade" ondblclick="callShowDiv(this);"><%=label.get("select.employee.grade")%></label> :</td>
               					<td width="25%" colspan="1" nowrap="nowrap"><s:if test="edit"><s:optiontransferselect size="10" doubleSize="10" doubleId="selGradeId" id="availGradeId"
								label="Employee Grade" rightTitle="Selected Grade" leftTitle="Available Grade"  
								addToLeftLabel="<< Remove" addToRightLabel="Add >>" addAllToLeftLabel="Remove All"
								addAllToRightLabel="Add All" selectAllLabel="Select All" cssStyle="width:100px"
								doubleCssStyle="width:100px" doubleName="selGrade" multiple="true"
								buttonCssClass="token" doubleList="%{hashmapGradeSel}"
								name="availGrade" list="%{hashmapGrade}" /></s:if>
								<s:else>
								<s:optiontransferselect size="10" doubleSize="10" doubleId="selGradeId" id="availGradeId"
								label="Employee Grade" rightTitle="Selected Grade" leftTitle="Available Grade"  
								addToLeftLabel="<< Remove" addToRightLabel="Add >>" addAllToLeftLabel="Remove All"
								addAllToRightLabel="Add All" selectAllLabel="Select All" cssStyle="width:100px"
								doubleCssStyle="width:100px" doubleName="selGrade" multiple="true"
								buttonCssClass="token" doubleList="{}"
								name="availGrade" list="%{hashmapGrade}" /></s:else></td>
								<td width="25%" class="formtext" colspan="1"></td>
               					<td width="25%" colspan="1" nowrap="nowrap"></td>
               			    </tr>
         				</table>
         			</div>
         	   </td>
    </tr>
    <tr>
               <td colspan="3" width="100%" ><input type="checkbox"  name="empDeptCheck" class="checkbox" id="empDeptCheck"  onclick="return callEmpDeptCheck();"></input><s:hidden name="hideEmpDeptCheck"></s:hidden>
              <script>setEmpDeptCheck();</script> <label  class = "set" name="based.on.emp.dept"  id="based.on.emp.dept" ondblclick="callShowDiv(this);"><%=label.get("based.on.emp.dept")%></label> 
               </td>
               </tr>
    <tr>
               <td>
               		<div id="empDeptDiv">
               			<table border="0" width="100%">
               				<tr>
               					<td width="25%" class="formtext" colspan="1" nowrap="nowrap"><label  class = "set" name="select.employee.dept"  id="select.employee.dept" ondblclick="callShowDiv(this);"><%=label.get("select.employee.dept")%></label> :</td>
               					<td width="25%" colspan="1" nowrap="nowrap"><s:if test="edit"><s:optiontransferselect size="10" doubleSize="10" doubleId="selDeptId" id="availDeptId"
								label="Employee Department" rightTitle="Selected Department" leftTitle="Available Department" 
								addToLeftLabel="<< Remove" addToRightLabel="Add >>" addAllToLeftLabel="Remove All"
								addAllToRightLabel="Add All" selectAllLabel="Select All" cssStyle="width:100px"
								doubleCssStyle="width:100px" doubleName="selDept" multiple="true"
								buttonCssClass="token" doubleList="%{hashmapDeptSel}"
								name="availDept" list="%{hashmapDept}" /></s:if>
								<s:else>
								<s:optiontransferselect size="10" doubleSize="10" doubleId="selDeptId" id="availDeptId"
								label="Employee Department" rightTitle="Selected Department" leftTitle="Available Department" 
								addToLeftLabel="<< Remove" addToRightLabel="Add >>" addAllToLeftLabel="Remove All"
								addAllToRightLabel="Add All" selectAllLabel="Select All" cssStyle="width:100px"
								doubleCssStyle="width:100px" doubleName="selDept" multiple="true"
								buttonCssClass="token" doubleList="{}"
								name="availDept" list="%{hashmapDept}" /></s:else></td>
								<td width="25%" class="formtext" colspan="1"></td>
               					<td width="25%" colspan="1" nowrap="nowrap"></td>
               			    </tr>
         				</table>
         			</div>
         	   </td>
    </tr>
      </table>
      </td></tr>
  			</table></div>
  		</td>
  </tr>
  </table>
  </fieldset>
  </td>
  </tr>
   <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2">
         <s:if test="isApprove"></s:if><s:else>
          <tr>
	<td  width="80%">
					<input type="button"
							class="add" value="Save Eligibility Criteria " onclick="return eligibilityCriteriaFun();" /> 
					<input type="button" class="back" value=" Back "
							onclick="return callBack();" />
				</td>
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr></s:else>
        </table>
          </td>
    </tr>
	</table>
	</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js">
</script>
<script type="text/javascript">
function eligibilityCriteriaFun()
{
	
	selectList();
	document.getElementById('paraFrm').action = "AppraisalCalendar_saveEligibilityCriteria.action";
	document.getElementById('paraFrm').submit();
	////deSelectList();
}

//autoDate();

///onload();
/* ======================================================================
	Function	: callChk
	Explanation : change the status of check box as mouse clicked on check box
	========================================================================== */
function callDivCheck(){
		if(document.getElementById("paraFrm_hideEmpDivCheck").value=='Y'){
		   	document.getElementById("empDivCheck").value='N';
		   	document.getElementById("paraFrm_hideEmpDivCheck").value='N';
		 
		  	  }else  {
		  	 
		  	document.getElementById("empDivCheck").value='Y';
		  		document.getElementById("paraFrm_hideEmpDivCheck").value='Y';
		  	
		} 
document.getElementById("divisionDiv").style.display ='';
}
function callJoinDateCheck(){
		if(document.getElementById("paraFrm_hideJoinDateCheck").value=='Y'){
		   	document.getElementById("joinDateCheck").value='N';
		   	document.getElementById("paraFrm_hideJoinDateCheck").value='N';
		   	document.getElementById("joinDateDiv").style.display ='none';
		  	  }else  {
		  	document.getElementById("joinDateCheck").value='Y';
		  	document.getElementById("paraFrm_hideJoinDateCheck").value='Y';
		  	document.getElementById("joinDateDiv").style.display ='';
		  	document.getElementById("joinDateToDiv").style.display ='none';
		} 

}
function callJoinDateCond(){
	var condn = document.getElementById("paraFrm_joinDateCondition").value;
	if(condn=="2"){
		document.getElementById("joinDateToDiv").style.display ='';
		document.getElementById("joinDateFromDiv").style.display ='none';
	}else{
		document.getElementById("joinDateToDiv").style.display ='none';
		document.getElementById("joinDateFromDiv").style.display ='';
	}
	
}

function callEmpTypeCheck(){
		if(document.getElementById("paraFrm_hideEmpTypeCheck").value=='Y'){
		   	document.getElementById("empTypeCheck").value='N';
		   	document.getElementById("paraFrm_hideEmpTypeCheck").value='N';
		   	document.getElementById("empTypeDiv").style.display ='none';
		  	  }else {
		  	document.getElementById("empTypeCheck").value='Y';
		  	document.getElementById("paraFrm_hideEmpTypeCheck").value='Y';
		  	document.getElementById("empTypeDiv").style.display ='';
		} 

}
function callEmpGradeCheck(){
		if(document.getElementById("paraFrm_hideEmpGradeCheck").value=='Y'){
		   	document.getElementById("empGradeCheck").value='N';
		   	document.getElementById("paraFrm_hideEmpGradeCheck").value='N';
		   	document.getElementById("empGradeDiv").style.display ='none';
		  	  }else  {
		  	document.getElementById("empGradeCheck").value='Y';
		  		document.getElementById("paraFrm_hideEmpGradeCheck").value='Y';
		  	document.getElementById("empGradeDiv").style.display ='';
		} 

}
function callEmpDeptCheck(){
		if(document.getElementById("paraFrm_hideEmpDeptCheck").value=='Y'){
		   	document.getElementById("empDeptCheck").value='N';
		   	document.getElementById("paraFrm_hideEmpDeptCheck").value='N';
		   ///	document.getElementById("empDeptDiv").style.display ='none';
		  	  }else  {
		  	document.getElementById("empDeptCheck").value='Y';
		  	document.getElementById("paraFrm_hideEmpDeptCheck").value='Y';
		  ///	document.getElementById("empDeptDiv").style.display ='';
		} 
document.getElementById("empDeptDiv").style.display ='';
}
function selectList()
{
	var selId = document.getElementById('selTypeId');
	var availId = document.getElementById('availTypeId');
	selectAllOptionsList(selId,availId);
	
	selId = document.getElementById('selDivId');
	 availId = document.getElementById('availDivId');
	selectAllOptionsList(selId,availId);
	
	 selId = document.getElementById('selGradeId');
	 availId = document.getElementById('availGradeId');
	selectAllOptionsList(selId,availId);
	
	 selId = document.getElementById('selDeptId');
	 availId = document.getElementById('availDeptId');
	selectAllOptionsList(selId,availId);
}

function deSelectList()
{
	try{
	var selId = document.getElementById('selTypeId');
	var availId = document.getElementById('availTypeId');
	deSelectAllOptions(selId,availId);
	
	 selId = document.getElementById('selGradeId');
	 availId = document.getElementById('availGradeId');
	deSelectAllOptions(selId,availId);
	
	 selId = document.getElementById('selDeptId');
	 availId = document.getElementById('availDeptId');
	deSelectAllOptions(selId,availId);
	
	selId = document.getElementById('selDivId');
	 availId = document.getElementById('availDivId');
	deSelectAllOptions(selId,availId);
	}catch(e){
		alert(e);
	}
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
function criteriaValidation(){
	selectList();
	var check ="unchecked";
	if(document.getElementById("paraFrm_hideJoinDateCheck").value=="Y"){
				check = "checked";
			if(document.getElementById('paraFrm_joinDateCondition').value=="2"){
					var fromDate = document.getElementById('paraFrm_joinFromDate').value;
					var toDate = document.getElementById('paraFrm_joinToDate').value;
					if(fromDate==""){
						deSelectList();
						alert("Please enter join date from.");
						document.getElementById('paraFrm_joinFromDate').focus();
						return false;
					}else {
						if(!validateDate('paraFrm_joinFromDate',"from.date")){
						deSelectList();
						return false;
						}
					}
					if(toDate==""){
						deSelectList();
						alert("Please enter join date To.");
						document.getElementById('paraFrm_joinToDate').focus();
						return false;
					}else {
						if(!validateDate('paraFrm_joinToDate',"to.date")){
						deSelectList();
						return false;
						}
					}
					if(!dateDifferenceEqual(fromDate, toDate, "paraFrm_joinToDate", "from.date", "to.date")){
							deSelectList();
							return false;
							}
				}else {
					if(document.getElementById('paraFrm_joinDate').value==""){
						deSelectList();
						alert("Please enter join date.");
						document.getElementById('paraFrm_joinDate').focus();
						return false;
					}else {
						if(!validateDate('paraFrm_joinDate',"join.date")){
						deSelectList();
						return false;
						}
					}
				}
				
			}
	
	if(document.getElementById("paraFrm_hideEmpDivCheck").value=="Y"){
	 		check = "checked";
			if(document.getElementById('selDivId').value==""){
				deSelectList();
				alert("Please select atleast one employee division.");
				document.getElementById('selDivId').focus();
				return false;
			}
	}
	 if(document.getElementById("paraFrm_hideEmpTypeCheck").value=="Y"){
	 		check = "checked";
			if(document.getElementById('selTypeId').value==""){
				deSelectList();
				alert("Please select atleast one employee type.");
				document.getElementById('selTypeId').focus();
				return false;
			}
	}
	 if(document.getElementById("paraFrm_hideEmpGradeCheck").value=="Y"){
	 		check = "checked";
			if(document.getElementById('selGradeId').value==""){
				deSelectList();
				alert("Please select atleast one employee grade.");
				document.getElementById('selGradeId').focus();
				return false;
			}
	}
	 if(document.getElementById("paraFrm_hideEmpDeptCheck").value=="Y"){
	 		check = "checked";
			if(document.getElementById('selDeptId').value==""){
				deSelectList();
				alert("Please select atleast one employee department.");
				document.getElementById('selDeptId').focus();
				return false;
			}
	}
	if(check=="unchecked"){
		deSelectList();
		alert("Please select atleast one criteria.");
		return false;
	}
	///deSelectList();
	return true;
}

  function autoDate () {
	var tDay = new Date();
	var tMonth = tDay.getMonth()+1;
	var tDate = tDay.getDate();
		document.getElementById("importDiv").style.display ='none';
		document.getElementById("importContentDiv").style.display ='none';
		document.getElementById("joinDateDiv").style.display ='none';
		document.getElementById("empTypeDiv").style.display ='none';
		document.getElementById("divisionDiv").style.display ='none';
		document.getElementById("empGradeDiv").style.display ='none';
		document.getElementById("empDeptDiv").style.display ='none';
		if ( tMonth < 10) tMonth = "0"+tMonth;
		if ( tDate < 10) tDate = "0"+tDate;
		if(document.getElementById('paraFrm_code').value=="")
	document.getElementById("paraFrm_assignDate1").value = tDate+"-"+tMonth+"-"+tDay.getFullYear();
				//alert("date"+document.getElementById("paraFrm_vchDate").value);
}

function onload(){
		try{
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
			
		///document.getElementById("importDiv").style.display ='none';
		///document.getElementById("importContentDiv").style.display ='none';
		}catch(e){
		alert(e);
		}
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
	function callBack(){
		var apprId = document.getElementById('paraFrm_appraisalId').value;
		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById("paraFrm").action="AppraisalCalendar_callForEdit.action?apprId="+apprId;
		document.getElementById('paraFrm').submit();
	}
	
	
</script>
