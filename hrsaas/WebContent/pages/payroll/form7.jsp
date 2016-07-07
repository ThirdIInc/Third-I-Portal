<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="Form7" validate="true" id="paraFrm" validate="true"	theme="simple">
<table width="100%" class="formbg">
<s:hidden name="divAbbr"/>
	<tr>
		<td colspan="3">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt">
						<img src="../pages/images/recruitment/review_shared.gif" width="25"	height="25" />
					</td>
					<td width="93%" class="txt">
						<strong class="text_head">Form 7 Report</strong>
					</td>
					<td width="3%" valign="top" align="right">
						<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
			<tr valign="middle">
				<td nowrap="nowrap">
					<a href="#" onclick="resetFun();">
						<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle"> Reset </a>&nbsp;
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
		<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" class="formbg" id='reportBodyTable'>
				<tr>
					<td colspan="4">
						<strong>
							<label id="selectPeriod" name="selectPeriod"	ondblclick="callShowDiv(this);"><%=label.get("selectPeriod")%></label>
						</strong>
					</td>
				</tr>
				<tr>
					<td width="25%"><label  class = "set"  id="frmMonth" name="frmMonth" ondblclick="callShowDiv(this);"><%=label.get("frmMonth")%></label><font color="red">*</font>:</td>
					<td width="25%">
						<s:select theme="simple" name="fromMonth" cssStyle="width:152" list="#{'0':'Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}"/>
					</td>
					<td>
						<label  class = "set"  id="frmYear" name="frmYear" ondblclick="callShowDiv(this);"><%=label.get("frmYear")%></label><font color="red">*</font>:
					</td>
					<td width="25%">
						<s:textfield name="fromYear" onkeypress="return numbersOnly();" theme="simple" maxlength="4" size="25" />
					</td>
				</tr>
				<tr>
					<td>
						<label class = "set"  id="toMon" name="toMon" ondblclick="callShowDiv(this);"><%=label.get("toMon")%></label><font color="red">*</font>:
					</td>
					<td>
						<s:select theme="simple" name="toMonth" cssStyle="width:152" list="#{'0':'Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}"/>
					</td>
					<td>
						<label  class = "set"  id="toyr" name="toyr" ondblclick="callShowDiv(this);"><%=label.get("toyr")%></label><font color="red">*</font>:
					</td>
					<td>
						<s:textfield name="toYear" onkeypress="return numbersOnly();" theme="simple" maxlength="4" size="25" />
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<strong>
							<label id="selectReportFilter" name="selectReportFilter" ondblclick="callShowDiv(this);"><%=label.get("selectReportFilter")%></label>
						</strong>
					</td>
				</tr>
				<tr>
					<td>
						<label class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font color="red">*</font>:
					</td>
					<td><s:hidden name="divId" />
						<s:textfield name="divName" theme="simple" readonly="true" maxlength="50" size="25" />
						<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:callsF9(500,325,'Form7_f9div.action');">
					</td>
					<td>
						<label class = "set"  id="hold" name="hold" ondblclick="callShowDiv(this);"><%=label.get("hold")%></label>:
					</td>
					<td>
						<s:select theme="simple" name="onHold" cssStyle="width:152" list="#{'A':'All','N':'No','Y':'Yes'}"/>
					</td>
				</tr>
				<tr>
					<td>
						<label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:
					</td>
					<td colspan="3"><s:hidden name="brnId"/>
						<s:textarea name="brnName" cols="100" rows="1" readonly="true"/>
						<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:callDropdown('paraFrm_brnName',200,250,'Form7_f9brn.action',event,'false','no','right');">
					</td>
				</tr>
				<tr>
					<td>
						<label  class = "set" id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:
					</td>
					<td colspan="3"><s:hidden name="deptId"/>
						<s:textarea name="deptName" cols="100" rows="1" readonly="true"/>
						<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:callDropdown('paraFrm_deptName',200,250,'Form7_f9dept.action',event,'false','no','right');">
					</td>
				</tr>
				<tr>
					<td>
						<label  class = "set"  id="employee.type" name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>:
					</td>
					<td colspan="3"><s:hidden name="typeId" />
						<s:textarea name="typeName" cols="100" rows="1" readonly="true"/>
						<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:callDropdown('paraFrm_typeName',200,250,'Form7_f9type.action',event,'false','no','right');">
					</td>
				</tr>
				<tr>
					<td>
						<label  class = "set" id="pay.bill" name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>:
					</td>
					<td colspan="3"><s:hidden name="payId" />
						<s:textarea name="payName" cols="100" rows="1" readonly="true" />
						<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:callDropdown('paraFrm_payName',200,250,'Form7_f9payBill.action',event,'false','no','right');">
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
			<tr valign="middle">
				<td nowrap="nowrap">
					<a href="#" onclick="resetFun();">
						<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle"> Reset </a>&nbsp;
				</td>
				<td width="100%">
					<%@ include file="/pages/common/reportButtonPanelBottom.jsp"%>
				</td>
			</tr>
		</table>
		</td>
	</tr>
<s:hidden name="reportType" />
<s:hidden name="reportAction" value='Form7_report.action'/>
</table>
</s:form>
<script>

function callReport(type){
	document.getElementById('paraFrm_reportType').value=type;
	if(!validateFields()){
			return false;
		} else {
			callReportCommon(type);
		}
}

function mailReportFun(type){
	if(!validateFields()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		document.getElementById('paraFrm').action='Form7_mailReport.action';
		document.getElementById('paraFrm').submit();
	}	
}

function reportFun(){
	if(!validateFields()){
			return false;
		} else {
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action="Form7_report.action";
			document.getElementById('paraFrm').submit();
		}
}
	
function validateFields(){
	var fy=document.getElementById("paraFrm_fromYear").value;
	var ty=document.getElementById("paraFrm_toYear").value;
 
 	if(document.getElementById("paraFrm_fromMonth").value=='0'){
 		alert("Please select the "+document.getElementById('frmMonth').innerHTML.toLowerCase());
 		return false;
	}
 	if(document.getElementById("paraFrm_toMonth").value=='0'){
 		alert("Please select the "+document.getElementById('toMonth').innerHTML.toLowerCase());
 		return false;
	}
 	if(document.getElementById("paraFrm_fromYear").value==""){
 		alert("Please enter the "+document.getElementById('frmYear').innerHTML.toLowerCase());
 		return false;
 	}
	if(document.getElementById("paraFrm_toYear").value==""){
 		alert("Please enter the "+document.getElementById('toyear').innerHTML.toLowerCase());
 		return false;
	}
 	if(document.getElementById("paraFrm_divName").value==""){
 		alert("Please select the "+document.getElementById('division').innerHTML.toLowerCase());
 		return false;
	}	
 	if(ty<fy){
 		alert("To Year can't be less than  From Year ");
		return false; 		
	}
	return true;
}
	
function resetFun(){
	 document.getElementById("paraFrm_fromMonth").value='0';
	 document.getElementById("paraFrm_toMonth").value='0';
	 document.getElementById("paraFrm_typeName").value="";
	 document.getElementById("paraFrm_typeId").value="";
	 document.getElementById("paraFrm_divId").value="";
	 document.getElementById("paraFrm_divName").value="";
	 document.getElementById("paraFrm_brnId").value="";
	 document.getElementById("paraFrm_brnName").value="";
	 document.getElementById("paraFrm_deptName").value="";
	 document.getElementById("paraFrm_deptId").value="";
	 document.getElementById("paraFrm_typeName").value="";
	 document.getElementById("paraFrm_fromYear").value="";
	 document.getElementById("paraFrm_toYear").value="";
	 document.getElementById("paraFrm_payId").value="";
	 document.getElementById("paraFrm_payName").value="";
}
</script>