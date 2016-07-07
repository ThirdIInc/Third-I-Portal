<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="LeaveApplicationMis" method="post" id="paraFrm"
	theme="simple">
	<s:hidden name="reportAction"
		value='LeaveApplicationMis_leaveMisReport.action' />
	<s:hidden name="report" />
	<s:hidden name="leaveAppMis.empId" />
	<table width="100%" border="0" class="formbg" align="right">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Leave
					Application MIS Report </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td align="right" width="100%">
			<div align="right"><font color="red">*</font> Indicates
			Required</div>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="6%" nowrap="nowrap"><a href="#"
								onclick="resetFun();"> <img
								src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
								align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;</td>
							<td width="90%" colspan="1"><%@ include
								file="/pages/common/reportButtonPanel.jsp"%></td>
						</tr>
						<tr>
							<td>
							<div name="htmlReport" id='reportDiv'
								style="background-color: #FFFFFF; height: 400; width: 100%; display: none; border-top: 1px #cccccc solid;">
							<iframe id="reportFrame" frameborder="0" onload=alertsize();
								style="vertical-align: top; float: left; border: 0px solid;"
								allowtransparency="yes" src="../pages/common/loading.jsp"
								scrolling="auto" marginwidth="0" marginheight="0" vspace="0"
								name="htmlReport" width="100%" height="200"></iframe></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="0" cellspacing="1"
						class="formbg" id="reportBodyTable">
						<s:if test="leaveAppMis.generalFlag">
							<tr>
								<td colspan="1" width="20%" class="formtext"><label
									name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:</td>
								<td colspan="3"><s:hidden name="divCode" /> <s:textfield
									name="divsion" theme="simple" size="30" readonly="true" /></td>

							</tr>
							<td width="12%"><label name="employee" id="employee"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
							<td><s:hidden theme="simple" name="leaveAppMis.token" /><s:textfield
								theme="simple" name="leaveAppMis.empName" size="25"
								readonly="true" /></td>

							<!--<td><label name="report.type" id="report.type"
								ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>:</td>
							<td><s:select theme="simple" name="leaveAppMis.reportType"
								headerKey="Pdf" headerValue="Pdf"
								list="#{'Xls':'Xls', 'Txt':'Text'}" /></td>-->
							<tr>
								<td><label name="frmdate" id="frmdate"
									ondblclick="callShowDiv(this);"><%=label.get("frmdate")%></label>:</td>
								<td><s:textfield theme="simple" name="frmDate"
									maxlength="10" size="25"
									onkeypress="return numbersWithHiphen();" /> <s:a
									href="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" height="16" align="absmiddle" width="16">
								</s:a></td>
								<td><label name="todate" id="todate"
									ondblclick="callShowDiv(this);"><%=label.get("todate")%></label>:</td>
								<td><s:textfield theme="simple" name="toDate"
									onkeypress="return numbersWithHiphen();" maxlength="10"
									size="25" /> <s:a
									href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" height="16" align="absmiddle" width="16">
								</s:a></td>

							</tr>
						</s:if>
						<s:else>
							<tr>
								<td width="20%" class="formtext"><label name="division"
									id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:</td>
								<td width="50%" colspan="2"><s:hidden name="divCode" /> <s:textarea
									name="divsion" theme="simple" rows="1" cols="75"
									readonly="true" /></td>
								<td width="30%"><img
									src="../pages/images/recruitment/search2.gif" height="18"
									align="absmiddle" width="18" class="iconImage"
									onclick="javascript:callDropdown('paraFrm_divsion',350,250,'LeaveApplicationMis_f9MultiDiv.action',event,'false','no','right')"></td>
							</tr>
							<tr>
								<td width="20%" class="formtext" height="22"><label
									name="department" id="department"
									ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
								<td colspan="2" width="50%"><s:hidden
									name="leaveAppMis.deptCode" value="%{leaveAppMis.deptCode}"
									theme="simple" /> <s:textarea name="leaveAppMis.deptName"
									theme="simple" rows="1" cols="75" readonly="true" /></td>
								<td width="30%"><img
									src="../pages/images/recruitment/search2.gif" height="18"
									class="iconImage" align="absmiddle" width="18"
									onclick="javascript:callDropdown11('paraFrm_leaveAppMis_deptName',350,250,'LeaveApplicationMis_f9MultiDept.action',event,'false','no','right')"></td>
							</tr>
							<tr>
								<td width="20%" class="formtext"><label name="branch"
									id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
								<td colspan="2" width="50%"><s:hidden
									name="leaveAppMis.centerNo" value="%{leaveAppMis.centerNo}"
									theme="simple" /> <s:textarea name="leaveAppMis.centerName"
									theme="simple" rows="1" cols="75" readonly="true" /></td>
								<td width="30%"><img
									src="../pages/images/recruitment/search2.gif" height="18"
									align="absmiddle" width="18" class="iconImage"
									onclick="javascript:callDropdown11('paraFrm_leaveAppMis_centerName',350,250,'LeaveApplicationMis_f9Brch.action',event,'false','no','right')"></td>
							</tr>
							<tr>
								<td width="20%" class="formtext" height="22"><label
									name="designation" id="designation"
									ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
								<td colspan="2" width="50%"><s:hidden
									name="leaveAppMis.desgCode" value="%{leaveAppMis.desgCode}"
									theme="simple" /> <s:textarea name="leaveAppMis.desgName"
									theme="simple" rows="1" cols="75" readonly="true" /></td>
								<td width="30%"><img
									src="../pages/images/recruitment/search2.gif" height="18"
									class="iconImage" align="absmiddle" width="18"
									onclick="javascript:callDropdown11('paraFrm_leaveAppMis_desgName',350,250,'LeaveApplicationMis_f9MultiRank.action',event,'false','no','right')"></td>
							</tr>
							<!--<tr>
								<td height="15" class="formhead" colspan="4" align="center"><b>(OR)</b>
								</td>
							</tr>
							--><tr>
								<td colspan="1" width="20%" class="formtext" height="22"><label
									name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
								<td colspan="2" width="50%"><s:textfield theme="simple"
									readonly="true" name="leaveAppMis.token" size="25" /><s:textfield
									theme="simple" name="leaveAppMis.empName" size="47"
									readonly="true" /></td>
									<td width="30%" colspan="1"><img
									src="../pages/images/recruitment/search2.gif" height="18"
									class="iconImage" align="absmiddle" width="18"
									onclick="F9Action();"></td>
								<s:hidden theme="simple" name="leaveAppMis.center" />
								<s:hidden theme="simple" name="leaveAppMis.rank" />
							</tr>
							<tr>
								<td width="20%" class="formtext"><label name="frmdate"
									id="frmdate" ondblclick="callShowDiv(this);"><%=label.get("frmdate")%></label>:</td>
								<td colspan="2" width="55%"><s:textfield name="frmDate"
									maxlength="10" onkeypress="return numbersWithHiphen();"
									size="30" theme="simple" /> <s:a
									href="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" height="16" width="16" align="absmiddle">
								</s:a></td>
							</tr>
							<tr>
								<td width="20%" class="formtext"><label name="todate"
									id="todate" ondblclick="callShowDiv(this);"><%=label.get("todate")%></label>:</td>
								<td colspan="2" width="55%"><s:textfield name="toDate"
									maxlength="10" onkeypress="return numbersWithHiphen();"
									size="30" theme="simple" /> <s:a
									href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" height="16" align="absmiddle" width="16">
								</s:a></td>
							</tr>
							<tr>
								<td colspan="1" width="20%" class="formtext" height="22"><label name="stat"
									id="stat" ondblclick="callShowDiv(this);"><%=label.get("stat")%></label>:</td>
								<td colspan="2" width="40%"><s:select theme="simple" cssStyle="width: 150px"
									list="#{'':'Select','D':'Draft','P':'Pending','B':'Sent Back','N':'Cancelled','R':'Rejected','A':'Approved','C':'Applied For Cancellation','X':'Cancellation Approved','Z':'Cancellation Rejected'}"
									name="leaveAppMis.status"></s:select></td>
							</tr>
							<tr>
								<td colspan="1" width="20%" class="formtext" height="22"><label
									name="empStatus" id="empStatus" ondblclick="callShowDiv(this);"><%=label.get("empStatus")%></label>:</td>
								<td colspan="2" width="40%"><s:select theme="simple" name="empStatus"
									cssStyle="width: 150px"
									list="#{'':'--Select--','S':'Service','R':'Retired','N':'Resigned','E':'Terminated'}" /></td>
								<td colspan="1" width="20%" class="formtext"></td>
								<td colspan="1" width="30%"></td>
							</tr>
						</s:else>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<div id="bottomButtonTable"></div>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td width="6%" nowrap="nowrap"><a href="#"
						onclick="resetFun();"> <img
						src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
						align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;</td>
					<td width="92%" colspan="6"><%@ include
						file="/pages/common/reportButtonPanelBottom.jsp"%></td>
				</tr>
			</table>
			</td>
		</tr>


	</table>
</s:form>

<script type="text/javascript">

	function callMisReport(){

		if(!validateDate('paraFrm_frmDate', 'frmdate'))
  	return false;
  	
		if(!validateDate('paraFrm_toDate', 'todate'))
  	return false;
	if(document.getElementById('paraFrm_divsion').value==""){
			alert('Please select '+document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
  	document.getElementById('paraFrm').target='_blank';
	document.getElementById('paraFrm').action='LeaveApplicationMis_report.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="main";
	}
	
	function callEmployee(){
		//var divCode=document.getElementById('paraFrm_divCode').value ;
		//var divName=document.getElementById('division').innerHTML.toLowerCase();
		var frmDate = document.getElementById("paraFrm_frmDate").value;
  	    var toDate  = document.getElementById("paraFrm_toDate").value;
		/*if(divCode=='' ){
			alert("Please select "+divName);
			return false;
		}*/
		 if(frmDate!=""){
  			if(!validateDate("paraFrm_frmDate","frmdate")){
				return false;
			}
	    }
	     if(toDate!=""){
  			if(!validateDate("paraFrm_toDate","todate")){
			return false;
	      }
	    }
		//callsF9(500,325,'LeaveApplicationMis_f9action.action');
		 if ((frmDate!="") && (toDate!="")) {
  			if(!dateDifferenceEqual(frmDate, toDate, 'paraFrm_frmDate', 'frmdate', 'todate'))
			return false;
	   }
		return true;
	}
	
	function callReport(type){
	 if(!callEmployee()){
				return false;
	 } else {
	 document.getElementById('paraFrm_report').value=type;
	 callReportCommon(type);
	 }		
 	}
 	
 	function mailReportFun(type){
 	if(!callEmployee()){
				return false;
	 } else {
  		document.getElementById('paraFrm_report').value=type;		
		//callDropdown(obj.name,200,250,'LeaveApplicationMis_mailReport.action','false');
		document.getElementById('paraFrm').action='LeaveApplicationMis_mailReport.action';
		document.getElementById('paraFrm').submit();
		//return true;
		}
	}
	
	function resetFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="LeaveApplicationMis_clear.action";
		document.getElementById('paraFrm').submit();
	}
	
	function F9Action(){
	 if(!callEmployee()){
				return false;
	 } else {
	  callsF9(500,325,'LeaveApplicationMis_f9action.action');
	 }
	}
	
	function setEmpBlank(){
	document.getElementById('paraFrm_leaveAppMis_empName').value="";
	document.getElementById('paraFrm_leaveAppMis_token').value="";
  }

    function callDropdown11(obj,width, height, action,event,showSearch,multiple,align) {
		//setEmpBlank();
		callDropdown(obj,width, height,action,event,showSearch,multiple,align);
}		
</script>

<script language="JavaScript">
  var obj= document.getElementById("topButtonTable").cloneNode(true);
  document.getElementById("topButtonTable").appendChild(obj);
</script>



