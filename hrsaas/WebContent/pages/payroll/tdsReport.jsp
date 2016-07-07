<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="TDSReport" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">TDS
					Statutory Report</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="2">
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
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2" id="reportBodyTable"  >
						<!--<tr>
							<td colspan="1" width="20%">Branch and Department wise :</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="chkBrnDept" id="chkBrnDept" onclick="checkBranchDep();" /></td>
						</tr>
						
								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="brnDeptWise" name="brnDeptWise"
										ondblclick="callShowDiv(this);"><%=label.get("brnDeptWise")%></label>:</td>
									<td colspan="3" width="100%"><input type="checkbox" name="allFlag" id="paraFrm_allFlag" checked="checked" onclick="chkOption('0');"/>All &nbsp;&nbsp;<s:checkbox name="branchFlag" onclick="chkOption('1');"/><label><%=label.get("branch") + " Wise"%></label>
									&nbsp;&nbsp;<s:checkbox name="deptFlag" onclick="chkOption('1');"/><label><%=label.get("department") + " Wise"%></label></td>
								</tr>-->

						<tr>
							<td colspan="4">
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2" class="formbg">

								<tr>
									<td colspan="4"><b><label class="set"
										id="selectPeriod" name="selectPeriod"
										ondblclick="callShowDiv(this);"> <%=label.get("selectPeriod")%></label></b>
									</td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label class="set" id="month"
										name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label>:<font
										color="red">*</font></td>
									<td colspan="1" width="30%"><s:select theme="simple"
										name="month" cssStyle="width:152"
										list="#{'0':'Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
									</td>
									<td colspan="1" width="20%"><label class="set" id="year"
										name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%>:</label><font
										color="red">*</font></td>
									<td><s:textfield name="year"
										onkeypress="return numbersOnly();" theme="simple"
										maxlength="4" size="25" /></td>
								</tr>
							</table>
							</td>
						</tr>

						<tr>
							<td colspan="4">
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0" class="formbg">
								<tr>
									<td colspan="3"><b><label class="set"
										id="selectFilters" name="selectFilters"
										ondblclick="callShowDiv(this);"> <%=label.get("selectFilters")%></label></b>
									</td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font
										color="red">*</font></td>
									<td colspan="1" width="60%" ><s:hidden name="divCode" /> <s:textarea
										cols="100" rows="1" theme="simple" readonly="true"
										name="divName" /> 
									</td>
									<td>&nbsp;	
										<img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_divName',200,250,'TDSReport_f9div.action',event,'false','','right')">

									<s:hidden name="divAdd" /><s:hidden name="divEsiCode" /> <s:hidden
										name="divisionAbbrevation" /></td>

								</tr>
								<tr>
									<td colspan="1" width="20%"><label class="set" id="branch"
										name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
									:</td>
									<td colspan="1" width="60%"><s:hidden name="brnCode" /> <s:textarea
										cols="100" rows="1" theme="simple" readonly="true"
										name="brnName" />
									</td>
									<td>&nbsp;
										<img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_brnName',200,250,'TDSReport_f9brn.action',event,'false','','right')">
									</td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="department" name="department"
										ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
									:</td>
									<td width="60%"><s:hidden name="deptCode" /> <s:textarea cols="100"
										rows="1" theme="simple" readonly="true" name="deptName" /> 
									</td>
									<td>&nbsp;
										<img src="../pages/images/search2.gif" class="iconImage"
										height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_deptName',200,250,'TDSReport_f9dept.action',event,'false','','right')">
									</td>

								</tr>
								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="pay.bill" name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>
									:</td>
									<td width="60%"><s:hidden name="payBillNo" /> <s:textarea cols="100"
										rows="1" theme="simple" readonly="true" name="payBillName" />
									</td>
									<td>&nbsp;
										<img src="../pages/images/search2.gif" class="iconImage"
										height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_payBillName',200,250,'TDSReport_f9payBill.action',event,'false','','right')">
									</td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="employee.type" name="employee.type"
										ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
									:</td>
									<td colspan="1" width="60%"><s:hidden name="typeCode" />
									<s:textarea cols="100" rows="1" theme="simple" readonly="true"
										name="typeName" /> 
									</td>
									<td>&nbsp;
										<img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_typeName',200,250,'TDSReport_f9type.action',event,'false','','right')">
									</td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label class="set" id="grade"
										name="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
									:</td>
									<td colspan="1" width="60%" ><s:hidden name="cadreCode" />
									<s:textarea cols="100" rows="1" theme="simple" readonly="true"
										name="cadreName" />
									</td>
									<td>&nbsp;
										 <img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_cadreName',200,250,'TDSReport_f9grade.action',event,'false','','right')">
									</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td colspan="4">
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2" class="formbg">

								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="report.opttion" name="report.opttion"
										ondblclick="callShowDiv(this);"><%=label.get("report.opttion")%></label>:</td>
									<td colspan="1" width="30%"><s:select theme="simple"
										name="reportOption" cssStyle="width:152"
										list="#{'O':'All','S':'Only Salary','A':'Only Arrears','se':'Only Settlement'}" />
									</td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label class="set" id="onhold"
										name="onhold" ondblclick="callShowDiv(this);"><%=label.get("onhold")%></label>:</td>
									<td><s:select theme="simple" name="onHold"
										cssStyle="width:152" list="#{'A':'All','N':'No','Y':'Yes'}" />
									</td>
								</tr>
								<!-- UPDATED BY REEBA -->
								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="cons.arrears" name="cons.arrears"
										ondblclick="callShowDiv(this);"><%=label.get("cons.arrears")%></label>
									:</td>
									<td colspan="1" width="30%"><input type="checkbox"
										name="checkFlag" id="checkFlag" onclick="callCheck();" /></td>
									<td colspan="1" width="20%"><!-- <label  class = "set"  id="report.type" name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label> <font color="red">*</font>:  --></td>
									<td colspan="1" width="30%">
									</td>
								</tr>
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
			</td>
		</tr>
	</table>

	
	<s:hidden name="reportType" />
	<s:hidden name="reportAction" value='TDSReport_report.action' />

</s:form>
<script>
 setCurrentYear('paraFrm_year');
 function callCheck(){
 var checkDefault = document.getElementById('checkFlag').checked;
 
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
 
function check(name,type){
	document.getElementById('paraFrm').action=name;	
	document.getElementById('paraFrm').submit();	
}
	
function resetFun(){
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action="TDSReport_reset.action";	
	document.getElementById('paraFrm').submit();
}
	
function validateFields(){
	var month =document.getElementById("paraFrm_month").value;
	var year =document.getElementById("paraFrm_year").value;
	var division =document.getElementById("paraFrm_divCode").value;
	var rep = document.getElementById('paraFrm_reportType').value;
	 
	if(month =='0'){
		alert("Select "+document.getElementById('month').innerHTML.toLowerCase());
		return false;
	}
	if(year ==''){
		alert("Enter "+document.getElementById('year').innerHTML.toLowerCase());
		return false;
	}
	if(!checkYear('paraFrm_year','year')){
		return false;	 
	}
	if(division ==""){
		alert("Select "+document.getElementById('division').innerHTML.toLowerCase());
		return false;
	}
	if(rep=='0'){
		alert("Please select the "+document.getElementById('report.type').innerHTML.toLowerCase());
		return false;
	}		
	return true;
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
	if(type==0){
		if(document.getElementById('paraFrm_allFlag').checked){
			document.getElementById('paraFrm_branchFlag').checked=false;
			document.getElementById('paraFrm_deptFlag').checked=false;
		}else
			document.getElementById('paraFrm_branchFlag').checked=true;
	}else{
		if(!document.getElementById('paraFrm_branchFlag').checked 
		   && !document.getElementById('paraFrm_deptFlag').checked){
			document.getElementById('paraFrm_allFlag').checked=true;
		}else{
			document.getElementById('paraFrm_allFlag').checked=false;
		}
	}
}
function mailReportFun(type){
	if(!validateFields()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		//callDropdown(obj.name,200,250,'TDSReport_tdsMailReport.action','false');
		document.getElementById('paraFrm').action='TDSReport_tdsMailReport.action';
		document.getElementById('paraFrm').submit();
	}	
}

function alertsize(pixels){ 
	pixels+=30; 
	//alert('before'+ document.getElementById('myframe').style.height); 
	document.getElementById('reportFrame').style.height=pixels+"px"; 
	//alert('after'+ document.getElementById('myframe').style.height); 
	document.body.style.offsetHeight="0px"; 
}
</script>