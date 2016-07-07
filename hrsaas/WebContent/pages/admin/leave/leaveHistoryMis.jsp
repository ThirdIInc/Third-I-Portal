<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="LeaveHistoryMis" method="post" id="paraFrm"
	theme="simple">
	<s:hidden name="reportAction"
		value='LeaveHistoryMis_leaveHistoryReport.action' />
	<s:hidden name="report" />
	<s:hidden name="leaveHisMis.empId" />
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Leave
					History MIS Report </strong></td>
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
							<td width="8%" colspan="1"><s:if
								test="leaveHisMis.generalFlag">
								<a href="#" onclick="resetFun();"> <img
									src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
									align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;
									</s:if> <s:else>
								<a href="#" onclick="resetFun();"> <img
									src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
									align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;
									</s:else></td>
							<td width="92%" colspan="2"><%@ include
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
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg" id="reportBodyTable">
						<s:if test="leaveHisMis.generalFlag">
							<tr>
								<td colspan="1" width="20%" class="formtext" height="22"><label
									name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:</td>
								<td colspan="3" width="80%"><s:hidden
									name="leaveHisMis.divCode" /> <s:textarea
									name="leaveHisMis.divsion" theme="simple" rows="1" cols="100"
									readonly="true" /></td>
							</tr>
							<tr>
								<td colspan="1"><label name="employee" id="employee"
									ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
								<td colspan="3"><s:hidden theme="simple"
									name="leaveHisMis.token" /><s:textfield theme="simple"
									name="leaveHisMis.empName" size="25" readonly="true" /></td>
							</tr>
							<!--<tr>
								<td colspan="1"><label name="report.type" id="report.type"
									ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>:</td>
								<td colspan="3"><s:select theme="simple" name="leaveHisMis.reportType"
									headerKey="Pdf" headerValue="Pdf"
									list="#{'Xls':'Xls', 'Txt':'Text'}" /></td>
							</tr>-->
							<tr>
								<td colspan="1"><label name="fromdate" id="fromdate"
									ondblclick="callShowDiv(this);"><%=label.get("fromdate")%></label>:</td>
								<td colspan="1"><s:textfield theme="simple" name="fromDate"
									onkeypress="return numbersWithHiphen();" maxlength="10"
									size="25" /> <s:a
									href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" height="16" align="absmiddle" width="16">
									</s:a></td>
								<td><label name="todate" id="todate"
									ondblclick="callShowDiv(this);"><%=label.get("todate")%></label>:</td>
								<td><s:textfield theme="simple" name="toDate"
									maxlength="10" onkeypress="return numbersWithHiphen();"
									size="25" /><s:a
									href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" height="16" align="absmiddle" width="16">
								</s:a></td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<td colspan="1" width="20%" class="formtext" height="22"><label
									name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:</td>
								<td colspan="3" width="80%"><s:hidden
									name="leaveHisMis.divCode" /> <s:textarea
									name="leaveHisMis.divsion" theme="simple" rows="1" cols="100"
									readonly="true" /> <img
									src="../pages/images/recruitment/search2.gif" height="18"
									class="iconImage" align="absmiddle" width="18"
									onclick="javascript:callDropdown11('paraFrm_leaveHisMis_divsion',350,250,'LeaveHistoryMis_f9div.action',event,'false','no','right')" />
								</td>
							</tr>
							<tr>
								<td colspan="1" width="20%" class="formtext" height="22"><label
									name="department" id="department"
									ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
								:</td>
								<td colspan="3" width="30%"><s:hidden
									name="leaveHisMis.deptCode" value="%{leaveHisMis.deptCode}"
									theme="simple" /> <s:textarea name="leaveHisMis.deptName"
									theme="simple" rows="1" cols="100" readonly="true" /><img
									src="../pages/images/recruitment/search2.gif" height="18"
									class="iconImage" align="absmiddle" width="18"
									onclick="javascript:callDropdown11('paraFrm_leaveHisMis_deptName',350,250,'LeaveHistoryMis_f9dept.action',event,'false','no','right')" /></td>
							</tr>
							<tr>
								<td colspan="1" width="20%" class="formtext"><label
									name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
								:</td>
								<td colspan="3" width="30%"><s:hidden
									name="leaveHisMis.centerNo" value="%{leaveHisMis.centerNo}" />
								<s:textarea name="leaveHisMis.centerName" theme="simple"
									rows="1" cols="100" readonly="true" /> <img
									src="../pages/images/recruitment/search2.gif" height="18"
									align="absmiddle" width="18" class="iconImage"
									onclick="javascript:callDropdown11('paraFrm_leaveHisMis_centerName',350,250,'LeaveHistoryMis_f9center.action',event,'false','no','right')" /></td>
							</tr>
							<tr>
								<td colspan="1" width="20%" class="formtext" height="22"><label
									name="employee.type" id="employee.type"
									ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>:</td>
								<td colspan="3" width="30%"><s:hidden
									name="leaveHisMis.typeCode" value="%{leaveHisMis.typeCode}" />
									<s:textarea name="leaveHisMis.empType" theme="simple" rows="1"
									cols="100" readonly="true" /> <img
									src="../pages/images/recruitment/search2.gif" height="18"
									class="iconImage" align="absmiddle" width="18"
									onclick="javascript:callDropdown11('paraFrm_leaveHisMis_empType',350,250,'LeaveHistoryMis_f9type.action',event,'false','no','right')" /></td>
							</tr>
							<tr>
								<td colspan="1" width="20%" class="formtext"><label
									name="designation" id="designation"
									ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
								<td colspan="3" width="30%"><s:hidden
									name="leaveHisMis.desgCode" value="%{leaveHisMis.desgCode}" />
								<s:textarea name="leaveHisMis.desgName" theme="simple" rows="1"
									cols="100" readonly="true" /> <img
									src="../pages/images/recruitment/search2.gif" height="18"
									class="iconImage" align="absmiddle" width="18"
									onclick="javascript:callDropdown11('paraFrm_leaveHisMis_desgName',350,250,'LeaveHistoryMis_f9desg.action',event,'false','no','right')" /></td>
							</tr>
							<tr>
								<td colspan="1" width="20%" class="formtext" height="22"><label
									name="levtype" id="levtype" ondblclick="callShowDiv(this);"><%=label.get("levtype")%></label>:</td>
								<td colspan="3" width="30%"><s:hidden
									name="leaveHisMis.levCode" value="%{leaveHisMis.levCode}" /> <s:textarea
									name="leaveHisMis.levType" theme="simple" rows="1" cols="100"
									readonly="true" /> <img
									src="../pages/images/recruitment/search2.gif" height="18"
									class="iconImage" align="absmiddle" width="18"
									onclick="javascript:callDropdown11('paraFrm_leaveHisMis_levType',350,250,'LeaveHistoryMis_f9ltypeaction.action',event,'false','no','right')" /></td>
							</tr>
							<tr>
								<td colspan="1" width="20%" class="formtext" height="22"
									bordercolor="red"><label name="employee" id="employee"
									ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
								:</td>
								<td colspan="3" width="80%"><s:textfield size="25"
									readonly="true" name="leaveHisMis.token" /><s:textfield
									name="leaveHisMis.empName" size="72" readonly="true" /> <img
									src="../pages/images/recruitment/search2.gif" height="18"
									align="absmiddle" width="18" class="iconImage"
									onclick="F9Action();"></td>
								<s:hidden theme="simple" name="leaveHisMis.center" />

								<s:hidden theme="simple" name="leaveHisMis.rank" />
							</tr>
							<tr>
								<td colspan="1" width="20%" class="formtext"><label
									name="stat" id="stat" ondblclick="callShowDiv(this);"><%=label.get("stat")%></label>
								:</td>
								<td colspan="3" width="30%"><s:select theme="simple"
									list="#{'':'Select','D':'Draft','P':'Pending','B':'Sent Back','N':'Cancelled','R':'Rejected','A':'Approved','C':'Applied For Cancellation','X':'Cancellation Approved','Z':'Cancellation Rejected'}"
									name="leaveHisMis.status"></s:select></td>
							</tr>
							<tr>
								<td colspan="4">
								<table width="100%" cellpadding="0" cellspacing="0" border="0">
									<tr>
										<td colspan="1" width="20%" class="formtext" height="22"><label
											name="fromdate" id="fromdate" ondblclick="callShowDiv(this);"><%=label.get("fromdate")%></label>
										:</td>
										<td colspan="1"><s:textfield theme="simple"
											onkeypress="return numbersWithHiphen();" name="fromDate"
											maxlength="10" size="23" /> <s:a
											href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
											<img src="../pages/images/recruitment/Date.gif"
												class="iconImage" height="16" align="absmiddle" width="16">
										</s:a></td>
										<td colspan="1" class="formtext"><label name="todate"
											id="todate" ondblclick="callShowDiv(this);"><%=label.get("todate")%></label>
										:</td>
										<td colspan="1" align="left"><s:textfield theme="simple"
											onkeypress="return numbersWithHiphen();" name="toDate"
											maxlength="10" size="23" /> <s:a
											href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
											<img src="../pages/images/recruitment/Date.gif"
												class="iconImage" height="16" align="absmiddle" width="16">
										</s:a></td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td colspan="1" width="20%" class="formtext" height="22"><label
									name="frmdate" id="frmdate" ondblclick="callShowDiv(this);"><%=label.get("empStatus")%></label>:</td>
								<td colspan="1" width="30%"><s:select name="empStatus"
									cssStyle="width: 150px"
									list="#{'':'--Select--','S':'Service','R':'Retired','N':'Resigned','E':'Terminated'}" />
								</td>
								<td colspan="1" width="20%" class="formtext"></td>
								<td colspan="1" width="30%"></td>
							</tr>
							<!--<tr>
								<td class="formhead" colspan="4" align="center"><b>(OR)</b></td>
							</tr>
							-->
							<!--<tr>
								<td colspan="1" width="20%" class="formtext" height="22"><label
									name="report.type" id="report.type"
									ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>:</td>	
								<td colspan="3" width="80%"><s:select theme="simple"
									name="leaveHisMis.reportType" headerKey="Pdf" headerValue="Pdf"
									list="#{'Xls':'Xls', 'Txt':'Text'}" /></td>
							</tr>-->							
						</s:else>
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<div id="bottomButtonTable"></div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%">
						<tr>
							<td width="6%" nowrap="nowrap"><a href="#"
								onclick="resetFun();"> <img
								src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
								align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;</td>
							<td width="92%" colspan="3"><%@ include
								file="/pages/common/reportButtonPanelBottom.jsp"%></td>
						</tr>
					</table>
					</td>
				</tr>				
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">

	function callMisReport(){	
		if(!validateDate('paraFrm_fromDate', 'fromdate'))
  			return false;
  	
		if(!validateDate('paraFrm_toDate', 'todate'))
  			return false;
		if(document.getElementById('paraFrm_leaveHisMis_divsion').value==""){
			alert('Please select '+document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
  		document.getElementById('paraFrm').target='_blank';
		document.getElementById('paraFrm').action='LeaveHistoryMis_report.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target="main";		
	}
	
	function callEmployee(){
		var frmDate = document.getElementById("paraFrm_fromDate").value;
  	    var toDate  = document.getElementById("paraFrm_toDate").value;
		/*if(divCode=='' ){
			alert("Please select "+divName);
			return false;
		}*/
		 if(frmDate!=""){
  			if(!validateDate("paraFrm_fromDate","fromdate")){
				return false;
			}
	    }
	     if(toDate!=""){
  			if(!validateDate("paraFrm_toDate","todate")){
			return false;
	      }
	    }
		 if ((frmDate!="") && (toDate!="")) {
  			if(!dateDifferenceEqual(frmDate, toDate, 'paraFrm_fromDate', 'fromdate', 'todate'))
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
		document.getElementById('paraFrm').action='LeaveHistoryMis_mailReport.action';
		document.getElementById('paraFrm').submit();
		}
	}
	
	function F9Action(){
	 if(!callEmployee()){
				return false;
	 } else {
	  callsF9(500,325,'LeaveHistoryMis_f9action.action');
	 }
	}
	
	function resetFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="LeaveHistoryMis_clear.action";
		document.getElementById('paraFrm').submit();
	}
	
	function setEmpBlank(){
		document.getElementById('paraFrm_leaveHisMis_empName').value="";
		document.getElementById('paraFrm_leaveHisMis_token').value="";
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
