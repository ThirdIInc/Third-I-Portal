<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="LeaveEncashmentReport" method="post" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Leave
					Encashment Report </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right" width="100%">
				<div align="right"><font color="red">*</font> Indicates Required</div>
			</td>
		</tr>
		
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap">
						<a href="#" onclick="resetFun();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" 
						 title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%">
						<%@ include file="/pages/common/reportButtonPanel.jsp"%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
			<tr>
		<td>
		<div name="htmlReport" id='reportDiv'
			style="background-color: #FFFFFF;  height: 400; width: 100%; display: none;border-top: 1px #cccccc solid;">
		<iframe id="reportFrame" frameborder="0" onload=alertsize();
			style="vertical-align: top; float: left; border: 0px solid;"
			allowtransparency="yes" src="../pages/common/loading.jsp" scrolling="auto"
			marginwidth="0" marginheight="0" vspace="0" name="htmlReport"
			width="100%" height="200"></iframe> </div>
		</td>
		
	</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id="reportBodyTable" class="formbg" >
				<s:hidden name="reportType" />
				<s:hidden name="reportAction" value='LeaveEncashmentReport_report.action'/>
				<s:if test="generalFlag">

					<tr>
						<td width="20%" colspan="1" class="formtext"><label
							name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
						<font color="red">*</font> :</td>
						<td colspan="3" width="80%"><s:hidden name="divCode" /> <s:textarea
										cols="75" rows="1" theme="simple" readonly="true"
										name="divName" /> </td>

					</tr>

					<tr>
						<td colspan="1" width="20%" class="formtext" height="22"><label
							name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
						:</td>
						<td colspan="3" width="80%"><s:textfield theme="simple"
							name="token" size="25" /><s:textfield theme="simple"
							name="empName" size="75" readonly="true" /><s:hidden
							theme="simple" name="empId" /></td>

					</tr>

					<tr>
						<td colspan="1" width="20%" class="formtext" height="22"><label
							name="frmdate" id="frmdate" ondblclick="callShowDiv(this);"><%=label.get("frmdate")%></label>:</td>
						<td colspan="1" width="30%"><s:textfield name="frmDate"
							size="25" onkeypress="return numbersWithHiphen();" theme="simple"
							maxlength="10" /> <s:a
							href="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');">
							<img src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="16" align="absmiddle" width="16">
						</s:a></td>
						<td colspan="1" width="20%" class="formtext"><label
							name="todate" id="todate" ondblclick="callShowDiv(this);"><%=label.get("todate")%></label>:</td>
						<td colspan="1" width="30%"><s:textfield name="toDate"
							size="25" onkeypress="return numbersWithHiphen();" theme="simple"
							maxlength="10" /> <s:a
							href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
							<img src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="16" align="absmiddle" width="16">
						</s:a></td>
					</tr>

					<!-- 
				<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label
						name="report.type" id="report.type"
						ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>
					:</td>
					<td colspan="3" width="80%"><s:select theme="simple" name="reportType"
						headerKey="Pdf" headerValue="Pdf"
						list="#{'Xls':'Xls', 'Txt':'Text'}" /></td>
				</tr>
				 -->
				</s:if>
				<s:else>
				<tr>
					<td colspan="5">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formbg" >
							<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">Select Period</strong></td>
						</tr>
							<tr>
						<td colspan="1" width="20%" class="formtext" height="22"><label
							name="frmdate" id="frmdate" ondblclick="callShowDiv(this);"><%=label.get("frmdate")%></label>:</td>
						<td colspan="1" width="30%"><s:textfield name="frmDate"
							size="25" onkeypress="return numbersWithHiphen();" theme="simple"
							maxlength="10" /> <s:a
							href="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');">
							<img src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="16" align="absmiddle" width="16">
						</s:a></td>
						<td colspan="1" width="20%" class="formtext"><label
							name="todate" id="todate" ondblclick="callShowDiv(this);"><%=label.get("todate")%></label>:</td>
						<td colspan="1" width="30%"><s:textfield name="toDate"
							size="25" onkeypress="return numbersWithHiphen();" theme="simple"
							maxlength="10" /> <s:a
							href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
							<img src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="16" align="absmiddle" width="16">
						</s:a></td>
					</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formbg" >
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">Select Filter</strong></td>
						</tr>
						<tr>
						<td width="20%" colspan="1" class="formtext"><label
							name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
						<font color="red">*</font> :</td>
						<td colspan="3" width="80%"><s:hidden name="divCode" /> 
						
						<s:textarea
										cols="105" rows="1" theme="simple" readonly="true"
										name="divName" /> 
						
						
							<img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_divName',200,250,'LeaveEncashmentReport_f9div.action',event,'false','','right')">
							
						</td>

					</tr>
					
					<tr>
						<td width="20%" colspan="1" class="formtext"><label
							name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
						:<s:hidden name="deptID" value="%{deptID}" /></td>
						<td colspan="3" width="80%">
						
						
								<s:textarea
										cols="105" rows="1" theme="simple" readonly="true"
										name="deptName" /> 
						
						
							<img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_deptName',200,250,'LeaveEncashmentReport_f9deptaction.action',event,'false','','right')">
							
						</td>

					</tr>
					
					<tr>
						<td width="20%" colspan="1" class="formtext"><label
							name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
						:</td>
						<td colspan="3" width="80%">
						<s:hidden name="centerId" />
						
						<s:textarea
										cols="105" rows="1" theme="simple" readonly="true"
										name="centerName" /> 
						
						
							<img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_centerName',200,250,'LeaveEncashmentReport_f9center.action',event,'false','','right')">
							
						</td>

					</tr>
					
					<!--<tr>
						<td width="20%" colspan="1" class="formtext"><label
							name="employee.type" id="employee.type"
							ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
						:</td>
						<td colspan="3" width="80%"><s:hidden name="typeCode"
							value="%{typeCode}" theme="simple" /> 
						
						<s:textarea
										cols="105" rows="1" theme="simple" readonly="true"
										name="empType" /> 
						
						
							<img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_empType',200,250,'LeaveEncashmentReport_f9type.action',event,'false','','right')">
							
						</td>

					</tr>
					-->
					<tr>
						<td colspan="1" width="20%">
							<label class="set" id="pay.bill" name="pay.bill" 
								ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>
						:</td>
						<td width="80%">
							<s:hidden name="payBillNo" /> 
							<s:textarea cols="105" rows="1" theme="simple" readonly="true" name="payBillName" />
							<img src="../pages/images/search2.gif" class="iconImage"
								height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_payBillName',200,250,'LeaveEncashmentReport_f9payBill.action',event,'false','','right')">
						</td>
					</tr>
					
					<tr>
						<td colspan="1" width="20%">
							<label class="set" id="grade" name="grade" 
								ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
						:</td>
						<td colspan="1" width="60%" >
							<s:hidden name="cadreCode" />
							<s:textarea cols="105" rows="1" theme="simple" readonly="true" name="cadreName" />
							<img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_cadreName',200,250,'LeaveEncashmentReport_f9grade.action',event,'false','','right')">
						</td>
					</tr>
					
					<tr>
						<td colspan="1" width="20%">
							<label class="set" id="costcenter" name="costcenter" 
								ondblclick="callShowDiv(this);"><%=label.get("costcenter")%></label>
						:</td>
						<td colspan="1" width="60%" >
							<s:hidden name="costcenterid" />
							<s:textarea cols="105" rows="1" theme="simple" readonly="true" name="costcentername" />
							<img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_costcentername',200,250,'LeaveEncashmentReport_f9Costcenter.action',event,'false','','right')">
						</td>
					</tr>
					
					<tr>
						<td colspan="1" width="20%">
							<label class="set" id="leavetype" name="leavetype" 
								ondblclick="callShowDiv(this);"><%=label.get("leavetype")%></label>
						:</td>
						<td colspan="1" width="60%" >
							<s:hidden name="leaveTypeCode" />
							<s:textarea cols="105" rows="1" theme="simple" readonly="true" name="leaveTypeName" />
							<img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_leaveTypeName',200,250,'LeaveEncashmentReport_f9leaveType.action',event,'false','','right')">
						</td>
					</tr>
					
					<!--<tr>
						<td width="20%" colspan="1" class="formtext"><label
							name="designation" id="designation"
							ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
						:<s:hidden name="desgID" value="%{desgID}" /></td>
						<td colspan="3" width="80%"> 
						
						
						
						<s:textarea
										cols="105" rows="1" theme="simple" readonly="true"
										name="desgName" /> 
						
						
							<img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_desgName',200,250,'LeaveEncashmentReport_f9desigaction.action',event,'false','','right')">
							
						</td>

					</tr>
					-->
					<tr>
						<td colspan="1" width="20%" class="formtext" height="22"><label
							name="stat" id="stat" ondblclick="callShowDiv(this);"><%=label.get("stat")%></label>
						:</td>
						<td colspan="3"><s:select theme="simple"
							list="#{'':'Select','P':'Pending','R':'Rejected','A':'Approved'}"
							name="status"></s:select></td>
					</tr>

					<tr>
						<td height="15" class="formhead" colspan="4" align="center"><b>(OR)</b>
						</td>
					</tr>

					<tr>
						<td colspan="1" width="20%" class="formtext" height="22"><label
							name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
						:</td>
						<td colspan="3" width="80%"><s:textfield theme="simple"
							name="token" size="25" /><s:textfield theme="simple"
							name="empName" size="75" readonly="true" /><s:hidden
							theme="simple" name="empId" /><img
							src="../pages/images/recruitment/search2.gif" height="18"
							class="iconImage" align="absmiddle" width="18"
							onclick="javascript:callEmployee();"></td>

					</tr>
						</table>
					</td>
				</tr>

					<!-- <tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label
						name="report.type" id="report.type"
						ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>
					:</td>
					<td colspan="3" width="80%"><s:select theme="simple" name="reportType"
						headerKey="Pdf" headerValue="Pdf"
						list="#{'Xls':'Xls', 'Txt':'Text'}" /></td>
				</tr>
				 -->
				</s:else>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td colspan="1" width="20%" class="formtext" height="22">
						<label name="showOnhold" id="showOnhold" ondblclick="callShowDiv(this);">
							<%=label.get("showOnhold")%>
						</label> 
					</td>
					<td colspan="1" width="80%" class="formtext" height="22">
						<s:checkbox name="allCheck" onclick="onHoldCheck('allCheck');" /> 
						<label name="all" id="all" ondblclick="callShowDiv(this);">
							<%=label.get("all")%>
						</label>&nbsp;
						<s:checkbox name="inclSalCheck" onclick="onHoldCheck('inclSalCheck');" /> 
						<label name="inclSal" id="inclSal" ondblclick="callShowDiv(this);">
							<%=label.get("inclSal")%>
						</label>&nbsp;
						<s:checkbox name="notInclSalCheck" onclick="onHoldCheck('notInclSalCheck');" /> 
						<label name="notInclSal" id="notInclSal" ondblclick="callShowDiv(this);">
							<%=label.get("notInclSal")%>
						</label>&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="2">
					</td>
				</tr>
				<tr>
					<td colspan="2" class="formhead" height="22">
						<strong>
							Select any/all of the following options to add in the report:
						 </strong>
					</td>
				</tr>
				<tr>
					<td width="20%"></td>
					<td colspan="1" width="80%" class="formtext" height="22">
						<s:checkbox name="bankCheck" /> 
						<label name="bank" id="bank" ondblclick="callShowDiv(this);">
							<%=label.get("bank")%>
						</label>&nbsp;
						<s:checkbox name="accNoCheck" />
						<label name="accNo" id="accNo" ondblclick="callShowDiv(this);">
							<%=label.get("accNo")%>
						</label>&nbsp;
						
						<s:checkbox name="divCheck" /> 
						<label name="division" id="division" ondblclick="callShowDiv(this);">
							<%=label.get("division")%>
						</label>&nbsp;
						<s:checkbox name="branchCheck" />
						<label name="branch" id="branch" ondblclick="callShowDiv(this);">
							<%=label.get("branch")%>
						</label>&nbsp;
						<s:checkbox name="gradeCheck" /> 
						<label name="grade" id="grade" ondblclick="callShowDiv(this);">
							<%=label.get("grade")%>
						</label>&nbsp;
						<s:checkbox name="costCenterCheck" />
						<label name="costcenter" id="costcenter" ondblclick="callShowDiv(this);">
							<%=label.get("costcenter")%>
						</label>&nbsp;
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap">
						<a href="#" onclick="resetFun();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" > 
							Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%">
						<%@ include file="/pages/common/reportButtonPanelBottom.jsp"%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
			<tr>
		<td>
		<div name="htmlReport" id='reportDiv'
			style="background-color: #FFFFFF;  height: 400; width: 100%; display: none;border-top: 1px #cccccc solid;">
		<iframe id="reportFrame" frameborder="0" onload=alertsize();
			style="vertical-align: top; float: left; border: 0px solid;"
			allowtransparency="yes" src="../pages/common/loading.jsp" scrolling="auto"
			marginwidth="0" marginheight="0" vspace="0" name="htmlReport"
			width="100%" height="200"></iframe> </div>
		</td>
		
	</tr>
	</table>
	</td>
	</tr>
	
	</table>
	<s:hidden name="today" />
</s:form>
<SCRIPT LANGUAGE="JavaScript">

var obj = document.getElementById("topButtonTable").cloneNode(true);
document.getElementById("bottomButtonTable").appendChild(obj);

</SCRIPT>

<script type="text/javascript">
function resetFun(){
	document.getElementById('paraFrm').action='LeaveEncashmentReport_reset.action';
	document.getElementById('paraFrm').submit();
}

function validateFields()
	{
		if(document.getElementById('paraFrm_divName').value==""){
			alert('Please select '+document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
  	
  	var frmDate = document.getElementById("paraFrm_frmDate").value;
  	var toDate  = document.getElementById("paraFrm_toDate").value;
   	var count	= 0;
	
	 
  	
  	 if(frmDate!=""){
  			if(!validateDate("paraFrm_frmDate","frmdate"))
				return false;
	}
	if(toDate!=""){
  			if(!validateDate("paraFrm_toDate","todate")){
			return false;
	}
  	}
  	
  	 if ((frmDate!="") && (toDate!="")) {
  			if(!dateDifferenceEqual(frmDate, toDate, 'paraFrm_frmDate', 'frmdate', 'todate'))
			return false;
	}
	return true;
	}
	
	 function mailReportFun(type){
		if(!validateFields()){
			return false;
		} else {
		document.getElementById('paraFrm_reportType').value=type;
		document.getElementById('paraFrm').action='LeaveEncashmentReport_mailReport.action';
			document.getElementById('paraFrm').submit();
			//callDropdown(obj.name,200,250,'Form6Report_mailReport.action','false');
			//return true;
		}	
	}  
	
	function callReport(type){
		document.getElementById('paraFrm_reportType').value=type;
		if(!validateFields()){
				return false;
			} else {
				/*document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="ESICReport_report.action";
				document.getElementById('paraFrm').submit();*/
				callReportCommon(type);
			}
	}




	function callEmployee(){
	try{
		var divCode=document.getElementById('paraFrm_divCode').value ;
		var divName=document.getElementById('division').innerHTML.toLowerCase();
		if(divCode=='' ){
			alert("Please select "+divName);
			return false;
		}
	}catch(e){
		alert(e);
	}
		callsF9(500,325,'LeaveEncashmentReport_f9action.action');
	}

	function autoDate () {
		var tDay = new Date();
		var tMonth = tDay.getMonth()+1;
		var tDate = tDay.getDate();
		if ( tMonth < 10) tMonth = "0"+tMonth;
		if ( tDate < 10) tDate = "0"+tDate;
		document.getElementById("paraFrm_today").value = tDate+"-"+tMonth+"-"+tDay.getFullYear();
		
	}

	function check(name,type){
		document.getElementById('paraFrm_reportType').value=type;
		if(document.getElementById('paraFrm_divName').value==""){
			alert('Please select '+document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		var frmDate = document.getElementById("paraFrm_frmDate").value;
		var toDate  = document.getElementById("paraFrm_toDate").value;
		var count	= 0;
		if(frmDate!=""){
			if(!validateDate("paraFrm_frmDate","frmdate"))
				return false;
		}
		if(toDate!=""){
			if(!validateDate("paraFrm_toDate","todate")){
				return false;
			}
  		}
		if ((frmDate!="") && (toDate!="")) {
			if(!dateDifferenceEqual(frmDate, toDate, 'paraFrm_frmDate', 'frmdate', 'todate'))
				return false;
		}
		document.getElementById('paraFrm').action=name;	
		document.getElementById('paraFrm').submit();	
	}
	
	function onHoldCheck(val){
		if(val == 'allCheck'){
			document.getElementById('paraFrm_allCheck').checked=true;
			document.getElementById('paraFrm_inclSalCheck').checked=false;
			document.getElementById('paraFrm_notInclSalCheck').checked=false;
		}else if(val == 'inclSalCheck'){
			document.getElementById('paraFrm_allCheck').checked=false;
			document.getElementById('paraFrm_inclSalCheck').checked=true;
			document.getElementById('paraFrm_notInclSalCheck').checked=false;
		} else {
			document.getElementById('paraFrm_allCheck').checked=false;
			document.getElementById('paraFrm_inclSalCheck').checked=false;
			document.getElementById('paraFrm_notInclSalCheck').checked=true;
		}
	}

autoDate();

  
</script>
