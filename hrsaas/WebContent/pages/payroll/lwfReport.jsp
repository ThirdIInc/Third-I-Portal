<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/pages/common/labelManagement.jsp" %>
 <s:form action="LWFReport" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">LWF Statutory  Report</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		
		<tr>
			<td colspan="3" align="right" width="100%">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
							</td>
						</tr>
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								id='topButtonTable'>
								<tr valign="middle">
									<td nowrap="nowrap"><a href="#" onclick="resetFun();">
									<img src="../pages/images/buttonIcons/Refresh.png"
										class="iconImage" align="absmiddle" title="Reset"> Reset
									</a>&nbsp;&nbsp;</td>
									<td width="100%"><%@ include
										file="/pages/common/reportButtonPanel.jsp"%>
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
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="2" id="reportBodyTable" >
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" class="formbg">
						<tr>
							<td colspan="4" class="formhead"><strong
								class="forminnerhead">
								<label class="set"  id="selectPeriod" name="selectPeriod" ondblclick="callShowDiv(this);"><%=label.get("selectPeriod")%></label>
								</strong>
							</td>
						</tr>
						<tr>
							<td colspan="1" width="20%"><label  class = "set"  id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label>:<font color="red">*</font></td>
							<td colspan="1" width="30%">
							<s:select theme="simple" name="month" cssStyle="width:152"
							list="#{'0':'Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
							</td>
							
							<td colspan="1" width="20%"><label  class = "set"  id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label>:<font color="red">*</font></td>
							<td>
							<s:textfield name="year" onkeypress="return numbersOnly();" theme="simple" maxlength="4" size="25"  />
							</td>
						</tr>	
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2" class="formbg">
						<tr>
							<td colspan="3">
								<b><label class="set" id="selectFilters" name="selectFilters" ondblclick="callShowDiv(this);"><%=label.get("selectFilters")%></label></b>
							</td>
						</tr>
						<tr>
							
							<td colspan="1" width="20%"><label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font color="red">*</font></td>
							<td colspan="1" width="30%">
							<s:hidden name="divisionAbbrevation" />
							<s:hidden name="divCode" />
							<s:textarea name="divName" cols="100" rows="1" theme="simple" readonly="true" />
							<s:hidden name="divAdd" /><s:hidden name="divEsiCode" />
							</td>
							<td colspan="1" >
							<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16" onclick="javascript:callDropdown('paraFrm_divName',200,250,'LWFReport_f9div.action',event,'false','','right')">
							</td>
						</tr>	
						<tr>
							<td colspan="1" width="20%"><label  class = "set"  id="onhold" name="onhold" ondblclick="callShowDiv(this);"><%=label.get("onhold")%></label>:</td>
							<td colspan="2">
								<s:select theme="simple" name="onHold" cssStyle="width:152"
							list="#{'A':'All','N':'No','Y':'Yes'}" />
							</td>
						</tr>
						<!--<tr>
							
							<td colspan="1" width="20%"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
							<td colspan="1" width="30%">
							<s:hidden name="brnCode" />
							<s:textfield name="brnName" theme="simple"  readonly="true" maxlength="50" size="25" />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'LWFReport_f9brn.action');">	
							
							</td>
							
							<td colspan="1" width="20%"><label  class = "set"  id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
							<td>
							<s:hidden name="deptCode" />
								<s:textfield name="deptName" theme="simple"  readonly="true" maxlength="50" size="25" />
								<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'LWFReport_f9dept.action');">
							</td>
						</tr>		
						<tr>
							
							<td colspan="1" width="20%"><label  class = "set"  id="employee.type" name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label> :</td>
							<td colspan="1" width="30%">
							<s:hidden name="typeCode" />
							<s:textfield name="typeName" theme="simple" readonly="true"  maxlength="50" size="25" />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'LWFReport_f9type.action');">	
							
							</td>
							
							<td colspan="1" width="20%"><label  class = "set"  id="pay.bill" name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label> :</td>
							<td>
							<s:hidden name="payBillNo" />
							<s:textfield name="payBillName" theme="simple"  readonly="true" maxlength="50" size="25" />

							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'LWFReport_f9payBill.action');">
							</td>
						</tr>-->
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0" class="formbg">
						<!--<tr>
							<td colspan="1" width="20%">Branch and Department wise :</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="chkBrnDept" id="chkBrnDept" onclick="checkBranchDep();" /></td>
						</tr>
						-->
						<!-- 
						<tr>
							<td colspan="1" width="20%">
								<label class="set" id="brnDeptWise" name="brnDeptWise"
									ondblclick="callShowDiv(this);"><%=label.get("brnDeptWise")%></label>:
							</td>
							<td colspan="3" width="100%">
								<input type="checkbox" name="allFlag" id="paraFrm_allFlag" 
								checked="checked" onclick="chkOption('0');"/>
								All &nbsp;&nbsp;<s:checkbox name="branchFlag" onclick="chkOption('1');"/>
								<label><%=label.get("branch") + " Wise"%></label>&nbsp;&nbsp;
								<s:checkbox name="deptFlag" onclick="chkOption('1');"/>
								<label><%=label.get("department") + " Wise"%></label>
							</td>
						</tr>
						 -->
					<tr>
						<td colspan="1" width="20%"><label  class = "set"  id="report.opttion" name="report.opttion" ondblclick="callShowDiv(this);"><%=label.get("report.opttion")%></label>:</td>
						<td colspan="1" width="30%" >
						<!-- <s:select theme="simple" name="reportOption" cssStyle="width:152"
							list="#{'O':'All','S':'Only Salary','A':'Only Arrears','se':'Only Settlement'}" />
						-->
						<s:hidden name="reportOption" value="O" /> 
						<s:checkbox name="salary" />&nbsp;<label  class = "set"  id="salary" name="salary" ondblclick="callShowDiv(this);"><%=label.get("salary")%></label>
						<s:checkbox name="arrears" onchange="" />&nbsp;<label  class = "set"  id="arrears" name="arrears" ondblclick="callShowDiv(this);"><%=label.get("arrears")%></label>
						<td colspan="1" width="20%">&nbsp;</td>
						<td colspan="1" width="30%">&nbsp;</td>	
						</td>
					</tr>
					<!-- UPDATED BY REEBA -->
					<!-- 
					<tr>
						<td colspan="1" width="20%"><label  class = "set"  id="cons.arrears" name="cons.arrears" ondblclick="callShowDiv(this);"><%=label.get("cons.arrears")%></label> :</td> 
						<td colspan="1" width="30%"><input type="checkbox" name="checkFlag"  id="checkFlag"
							 onclick="callCheck();" /></td>
						<td colspan="1" width="20%">&nbsp;</td>
						<td colspan="1" width="30%">&nbsp;</td>
					</tr>
					 -->		
						</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
								id='topButtonTable'>
								<tr valign="middle">
									<td nowrap="nowrap"><a href="#" onclick="resetFun();">
									<img src="../pages/images/buttonIcons/Refresh.png"
										class="iconImage" align="absmiddle" title="Reset"> Reset
									</a>&nbsp;&nbsp;</td>
									<td width="100%"><%@ include
										file="/pages/common/reportButtonPanelBottom.jsp"%>
									</td>
								</tr>
							</table>
			</td>
		</tr>
		</table>
		</td></tr></table>
		
	<s:hidden name="reportType" />
	<s:hidden name="reportAction" value='LWFReport_lwfReport.action?mailStatus=false' />
		
		</s:form>
<script>
function setReportOption(){
	var checkSalary = document.getElementById('paraFrm_salary').checked;
	var checkArrears = document.getElementById('paraFrm_arrears').checked;
	
	if(checkSalary){
		if(checkArrears){
			document.getElementById('paraFrm_reportOption').value = "O";
		}else{
			document.getElementById('paraFrm_reportOption').value = "S";
		}
	}else{
		document.getElementById('paraFrm_reportOption').value = "A";
	}
	
	if(checkSalary == false){
		if(checkArrears == false){
			alert("Please select atleast one report option");
			//document.getElementById('paraFrm_reportOption').value = "O";
			return false;
		}
	}
	return true;
	
}

function mailReportFun(type){
	if(!validateFields()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		document.getElementById('paraFrm').action='LWFReport_lwfmailReport.action';
		document.getElementById('paraFrm').submit();
	}	
}

setCurrentYear('paraFrm_year');

function callCheck(){
	var checkDefault = document.getElementById('checkFlag').checked;
 	//var checkEdli=document.getElementById('checkEdliSal').checked;
	if(checkDefault){
 			document.getElementById('checkFlag').value="Y";
 	}
}

function callReport(type){
	if(!validateFields()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		callReportCommon(type);
	}
}
	
function validateFields() {
	var month =document.getElementById("paraFrm_month").value;
	var year =document.getElementById("paraFrm_year").value;
	var division =document.getElementById("paraFrm_divCode").value;
	var rep = document.getElementById('paraFrm_reportType').value;
	 
	if(month =='0'){
		alert("Please select "+document.getElementById('month').innerHTML.toLowerCase());
		return false;
	}
	if(year ==''){
		alert("Please enter "+document.getElementById('year').innerHTML.toLowerCase());
		return false;
	}
	if(!checkYear('paraFrm_year','year')){
		return false;	 
	}
	if(division ==""){
		alert("Please select "+document.getElementById('division').innerHTML.toLowerCase());
		return false;
	}
	if(rep=='0'){
		alert("Please select the "+document.getElementById('report.type').innerHTML.toLowerCase());
		return false;
	}
	if(!setReportOption()){
		return false;
	}
	return true;	
}
	
function resetFun(){
	document.getElementById('paraFrm').action='LWFReport_reset.action';
	document.getElementById('paraFrm').submit();
}
	
function checkBranchDep(){
	var chk=document.getElementById('chkBrnDept').checked;

	if(chk){
		document.getElementById('chkBrnDept').value="Y";
 	}
}
 
function getYear(){
	var current = new Date();
	var year =current.getFullYear();
	var yr =document.getElementById("paraFrm_year").value;
	if(yr==''){
		document.getElementById("paraFrm_year").value =year;
	}
}
getYear();
function chkOption(type){
	if(type==0)	{
		if(document.getElementById('paraFrm_allFlag').checked){
			document.getElementById('paraFrm_branchFlag').checked=false;
			document.getElementById('paraFrm_deptFlag').checked=false;
		}else
			document.getElementById('paraFrm_branchFlag').checked=true;
	}else{
		if(!document.getElementById('paraFrm_branchFlag').checked 
		   && !document.getElementById('paraFrm_deptFlag').checked)	{
			document.getElementById('paraFrm_allFlag').checked=true;
		}else{
			document.getElementById('paraFrm_allFlag').checked=false;
		}
	}
}

function alertsize(pixels){ 
	pixels+=30; 
	//alert('before'+ document.getElementById('myframe').style.height); 
	document.getElementById('reportFrame').style.height=pixels+"px"; 
	//alert('after'+ document.getElementById('myframe').style.height); 
	document.body.style.offsetHeight="0px"; 
}
function checkSalary(){
	document.getElementById('paraFrm_salary').checked = "true";
}
checkSalary()

</script>