<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>



<table width="100%" border="0" cellpadding="0" cellspacing="2"
	 class="formbg">
	<tr><s:hidden name="hiddenStatus" />
		<td colspan="4"><a href="#"
			onclick="return callPensionDueList('PD');">Pension Due List</a> | <a
			href="#" onclick="return callViewList('PP');">Pension Processed
		List</a> | <a href="#" onclick="return callViewLockedList('PA');">Pension
		Active List</a> | <a href="#" onclick="return callViewOnholdList('PO');">Pension
		On-Hold List </a> | <a href="#"
			onclick="return callStopPensionList('PS');">Pension Stop List</a></td>
	</tr>
</table>

	<table width="100%" border="0" cellpadding="0" cellspacing="2"
		 class="formbg">
		<tr>
			<td>Employee Name :<font color="red" id='ctrlHide'></font><s:textfield
				name="employeeToken" size="22" theme="simple" readonly="true"
				cssStyle="background-color: #F2F2F2;" /> <s:textfield
				name="employeeName" size="35" theme="simple" readonly="true"
				cssStyle="background-color: #F2F2F2;" /> <s:hidden
				name="employeeCode" /> <img
				src="../pages/images/recruitment/search2.gif" height="16"
				align="absmiddle" width="16" id='ctrlHide'
				onclick="javascript:callsF9(500,325,'EmpConfForPension_f9Employee.action');">
			<input type="button" class="token" theme="simple"
				value="Search Employee" onclick="return applyFilters();" />&nbsp;<input type="button"
									class="reset" theme="simple" onclick="return callReset();"
									value="Reset" /></td>
		</tr>

	</table>


	<script type="text/javascript">
  if(document.getElementById("paraFrm_listFlag").value=="true"){
  callHideFilter();
  
  }



function callViewList(statusChk){
//callReset();

 document.getElementById('paraFrm_hiddenStatus').value=statusChk;
		document.getElementById('paraFrm').action="EmpConfForPension_viewCalculatedPension.action"
		document.getElementById('paraFrm').submit();
}
//METHOD ADDED BY GANESH
function callPensionDueList(statusChk){
//callReset();

 document.getElementById('paraFrm_hiddenStatus').value=statusChk;
		document.getElementById('paraFrm').action="EmpConfForPension_input.action"
		document.getElementById('paraFrm').submit();
}
function callViewLockedList(statusChk){
//callReset();

 document.getElementById('paraFrm_hiddenStatus').value=statusChk;
		document.getElementById('paraFrm').action="EmpConfForPension_viewLockedPension.action";
		document.getElementById('paraFrm').submit();
}
//METHOD ADDED BY REEBA
function callViewOnholdList(statusChk){
//callReset();

 document.getElementById('paraFrm_hiddenStatus').value=statusChk;
		document.getElementById('paraFrm').action="EmpConfForPension_viewOnholdPension.action";
		document.getElementById('paraFrm').submit();
}
//METHOD ADDED BY GANESH
function callStopPensionList(statusChk){
//callReset();

 document.getElementById('paraFrm_hiddenStatus').value=statusChk;
		document.getElementById('paraFrm').action="EmpConfForPension_viewStopPensionList.action";
		document.getElementById('paraFrm').submit();
}

function callBack()
 {
  document.getElementById('paraFrm').action='EmpConfForPension_callBack.action';
  document.getElementById('paraFrm').submit(); 
 }
function callReportForDisp(reportType)
{
 document.getElementById('paraFrm_reportType').value=reportType;
   callReport('EmpConfForPension_generateReport.action');    
}

 
//METHOD ADDED BY GANESH
function applyFilters(){
	try{
			var empToken= document.getElementById("paraFrm_employeeToken").value;
			var empName= document.getElementById("paraFrm_employeeName").value;
   			if(empToken=="" && empName==""  ){
   					alert("Please select employee name to apply filter");
   					return false;
   			} 
   		}
   		catch (e)
   		{
   			alert(e);
   		}	 
					document.getElementById('paraFrm').action='EmpConfForPension_applyFilters.action';
	   				document.getElementById('paraFrm').submit();
  }
  //METHOD ADDED BY GANESH
  function callReset(){
		 document.getElementById("paraFrm_employeeToken").value="";
		 document.getElementById("paraFrm_employeeName").value="";
		  document.getElementById("paraFrm_employeeCode").value="";
 }  
 function setStauts(statusChk){alert(statusChk);
 document.getElementById('paraFrm_hiddenStatus').value=statusChk;
 	var clickStatus = document.getElementById("paraFrm_hiddenStatus").value
 	alert(statusChk);
 }
</script>