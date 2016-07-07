<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="LeaveMisReport" method="post" id="paraFrm"
	theme="simple">
	<s:hidden name="reportAction" value='LeaveMisReport_report.action' />
	<s:hidden name="report" />
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<s:hidden name="leaveMisReport.empId" />
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Leave
					Balance MIS </strong></td>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" colspan="1"></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="6%" colspan="1"><a href="#" onclick="callReset();">
					<img src="../pages/images/buttonIcons/Refresh.png"
						class="iconImage" align="absmiddle" title="PDF"> Reset </a></td>
					<td width="*%" align="left" colspan="2"><s:if
						test="%{leaveMisReport.viewFlag}">
						<%@ include file="/pages/common/reportButtonPanel.jsp"%>
					</s:if></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">

				<tr>
					<td colspan="4"><input type="radio"
						name="leaveMisReport.dropDowntype" checked="true" value="1"
						id="dropDowntype1" onclick="myfun1();" /> <label name="conf1"
						id="conf1" ondblclick="callShowDiv(this);"><%=label.get("conf1")%></label></td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="1"><input type="radio" name="leaveMisReport.dropDowntype"
								value="2" id="dropDowntype2" onclick="myfun2();"
								onkeypress=" return callCheck11();" /> <label name="conf2"
								id="conf2" ondblclick="callShowDiv(this);"><%=label.get("conf2")%></label>:</td>
							<td align="left" colspan="1"><s:select name="leaveMisReport.month" disabled="true"
								list="#{'0':'Select','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" /></td>
							<td colspan="1"><label name="year" id="year"
								ondblclick="callShowDiv(this);"><%=label.get("year")%></label>:</td>
							<td colspan="1"><s:textfield name="leaveMisReport.year" disabled="true"
								maxlength="4" onkeypress="return numbersOnly();"></s:textfield></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="15%"><label class="set" id="division"
						name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%>:</label></td>
					<td colspan="1"><s:hidden name="leaveMisReport.divCode" /> <s:textarea
						cols="100" rows="1" theme="simple" readonly="true"
						name="leaveMisReport.divName" /></td>
					<td width="25%" colspan="2"><img
						src="../pages/images/search2.gif" class="iconImage" height="16"
						align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_leaveMisReport_divName',350,250,'LeaveMisReport_f9div.action',event,'false','no','right')"></td>
				</tr>
				<tr>
						<td width="15%"><label class="set" id="department"
							name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
						<td><s:hidden name="leaveMisReport.deptCode" /><s:textarea cols="100" rows="1" theme="simple" readonly="true"
							name="leaveMisReport.deptName" /></td>
						<td width="25%" colspan="2"><img src="../pages/images/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="callDropdown('paraFrm_leaveMisReport_deptName',350,250,'LeaveMisReport_f9dept.action',event,'false','no','right');"></td>
					</tr>
					<tr>
						<td width="15%"><label class="set" id="branch" name="branch"
							ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
						<td><s:hidden name="leaveMisReport.centerNo" /><s:textarea cols="100" rows="1" theme="simple" readonly="true"
							name="leaveMisReport.centerName" /></td>
						<td width="25%" colspan="2"><img src="../pages/images/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="callDropdown('paraFrm_leaveMisReport_centerName',350,250,'LeaveMisReport_f9center.action',event,'false','no','right')"></td>
					</tr>
					<tr>
						<td width="15%"><label class="set" id="designation"
							name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
						<td><s:hidden name="leaveMisReport.desgCode" /><s:textarea cols="100" rows="1" theme="simple" readonly="true"
							name="leaveMisReport.desgName" /></td>
						<td width="25%" colspan="2"><img src="../pages/images/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="callDropdown('paraFrm_leaveMisReport_desgName',350,250,'LeaveMisReport_f9desg.action',event,'false','no','right')"></td>
					</tr>
					<tr>
						<td width="15%"><label class="set" id="employee.type"
							name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%>:</label></td>
						<td><s:hidden name="leaveMisReport.typeCode" /><s:textarea cols="100" rows="1" theme="simple" readonly="true"
							name="leaveMisReport.empType" /></td>
						<td width="25%" colspan="2"><img src="../pages/images/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="callDropdown('paraFrm_leaveMisReport_desgName',350,250,'LeaveMisReport_f9type.action',event,'false','no','right')"></td>
					</tr>
					<tr>
						<td width="15%"><label class="set" id="levtype"
							name="levtype" ondblclick="callShowDiv(this);"><%=label.get("levtype")%></label>:</td>
						<td><s:hidden name="leaveMisReport.levCode" /> <s:textarea
							cols="100" rows="1" theme="simple" readonly="true"
							name="leaveMisReport.levType" /></td>
						<td width="25%" colspan="2"><img src="../pages/images/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="callDropdown('paraFrm_leaveMisReport_levType',350,250,'LeaveMisReport_f9ltypeaction.action',event,'false','no','right')"></td>
					</tr>
					<tr>
						<td width="15%" colspan="1"><label class="set" id="employee"
							name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
						<td width="20%" align="left" colspan="3"><s:textfield theme="simple"
							readonly="true" name="leaveMisReport.token" /><s:textfield size="73"
							theme="simple" readonly="true" name="leaveMisReport.empName" /><img src="../pages/images/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="callsF9(500,325,'LeaveMisReport_f9action.action')"></td>
						<s:hidden theme="simple" name="leaveMisReport.center" />
						<s:hidden theme="simple" name="leaveMisReport.rank" />
					</tr>
					<tr>
						<td width="15%"><label class="set" id="status" name="status"
							ondblclick="callShowDiv(this);"><%=label.get("status")%></label>:</td>
						<td colspan="3"><s:select name="leaveMisReport.serviceStatus"
							theme="simple"
							list="#{'':'Select','S':'Service','R':'Retired','N':'Resigned','E':'Terminated'}"
							cssStyle="width:140;z-index:5;" /></td>
					</tr>
					<!--<tr>
						<td class="formhead" colspan="10" align="center"><b>(OR)</b>
						</td>
					</tr>-->				
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="6%" colspan="1"><a href="#" onclick="callReset();">
					<img src="../pages/images/buttonIcons/Refresh.png"
						class="iconImage" align="absmiddle" title="PDF"> Reset </a></td>
					<td width="*%" align="left" colspan="3"><%@ include
						file="/pages/common/reportButtonPanelBottom.jsp"%>

					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">

	function myfun1(){	
			if(document.getElementById('dropDowntype1').checked){
				document.getElementById('paraFrm_leaveMisReport_month').value="0";
				document.getElementById('paraFrm_leaveMisReport_year').value="";
				document.getElementById('paraFrm_leaveMisReport_month').disabled=true;
				document.getElementById('paraFrm_leaveMisReport_year').disabled=true;
			}
	}
	function myfun2(){
			if(document.getElementById('dropDowntype2').checked){
				document.getElementById('paraFrm_leaveMisReport_month').disabled=false;
				document.getElementById('paraFrm_leaveMisReport_year').disabled=false;
				document.getElementById('paraFrm_leaveMisReport_month').value="0";
				document.getElementById('paraFrm_leaveMisReport_year').value="";
			}
	}

function callBalanceReport(){
	if(document.getElementById('dropDowntype2').checked){
		if(document.getElementById('paraFrm_leaveMisReport_month').value=="0"){
			alert('Please select '+document.getElementById('month').innerHTML.toLowerCase());
			document.getElementById('paraFrm_leaveMisReport_month').focus();
			return false;
		}
		if(document.getElementById('paraFrm_leaveMisReport_year').value==""){
			alert('Please enter '+document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_leaveMisReport_year').focus();
			return false;
		}
	}
		if(document.getElementById('dropDowntype1').checked){
	    	document.getElementById('paraFrm_leaveMisReport_month').value="0";
	    	document.getElementById('paraFrm_leaveMisReport_year').value="";
		}
	if(document.getElementById('paraFrm_leaveMisReport_divName').value==""){
			alert('Please select Division');
			return false;
		}
  	document.getElementById('paraFrm').target='_blank';
	document.getElementById('paraFrm').action='LeaveMisReport_report.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="main";
 }
	
function callEmployee(){
		/*var divCode=document.getElementById('paraFrm_leaveMisReport_divCode').value ;
		var divName=document.getElementById('division').innerHTML.toLowerCase();
		if(divCode=='' ){
			alert("Please select Division");
			return false;
		}*/
		callsF9(500,325,'LeaveMisReport_f9action.action');
 }
	

	
function callReport(type){
 try{
		if(!validateFields()){
				return false;
		} else {
				document.getElementById('paraFrm_report').value=type;
				callReportCommon(type);
		}
	}catch(e){
		alert(e);
	}
 }
function validateFields(){
		//var divNm   =document.getElementById("paraFrm_leaveMisReport_divCode").value;
		if(document.getElementById('dropDowntype2').checked){
			var month=document.getElementById('paraFrm_leaveMisReport_month').value ;
			var year=document.getElementById('paraFrm_leaveMisReport_year').value ;
			var yearL=document.getElementById('year').innerHTML.toLowerCase();		
			if(month=='0' ){
				alert("Please Select Month");
				return false;
			}			
			else if(year==''){
				alert("Please Select "+yearL);
				return false;
		}else { 
		return true;
		}
	}
	else{
 		return true;	    
	}
 }
function mailReportFun(type){
	try{
		if(!validateFields()){
				return false;
		} else {
			document.getElementById('paraFrm_report').value=type;
			document.getElementById('paraFrm').action='LeaveMisReport_mailReport.action';
			document.getElementById('paraFrm').submit();
		}
	}catch(e){
		alert(e);
	}
 }

	var obj = document.getElementById("topButtonTable").cloneNode(true);
	document.getElementById("bottomButtonTable").appendChild(obj);
function callReset(){	 
 			document.getElementById('paraFrm').target = "_self";
	 		document.getElementById('paraFrm').action="LeaveMisReport_clear.action";
	 		document.getElementById('paraFrm').submit();
 }

function setEmpBlank(){
	document.getElementById('paraFrm_leaveMisReport_empName').value="";
	document.getElementById('paraFrm_leaveMisReport_token').value="";
 }

function callDropdown11(obj,width, height, action,event,showSearch,multiple,align) {
		//setEmpBlank();
		getDropdown(action,width, height,obj,event,showSearch,multiple,align);
  }		

</script>
