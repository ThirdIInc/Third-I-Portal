<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="Form1" validate="true" id="paraFrm" theme="simple">
	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Form1</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<!--
					<td width="78%"><input type="button" class="token"
						onclick="return validation()" value="   Report   "> <s:submit
						cssClass="reset" action="Form1_reset" theme="simple"
						value="    Reset" /></td>						
					-->
					<td>
						<div align="right"><font color="red">*</font> Indicates	Required</div>
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
									</a>&nbsp;&nbsp;
								</td>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg" id="reportBodyTable" >
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"	cellspacing="2">
						<tr>
						<td>
						<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" class="formbg">
						<tr>
							<td colspan="4">
								<b><label class="set" id="selectFinancialYear" name="selectFinancialYear" ondblclick="callShowDiv(this);">
								<%=label.get("selectFinancialYear")%></label></b>
							</td>
						</tr>
						<tr>
							<td height="22" width="15%" class="formtext"><label  class = "set"  id="finYearFrm" name="finYearFrm" ondblclick="callShowDiv(this);"><%=label.get("finYearFrm")%></label>
							 <font color="red">*</font> :</td>
							<td><s:textfield name="fromYear" size="10" theme="simple"
								maxlength="4" onkeypress="return numbersOnly();" onblur="add()"></s:textfield>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<label  class = "set"  id="finYearTo" name="finYearTo" ondblclick="callShowDiv(this);"><%=label.get("finYearTo")%></label>:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <s:textfield
								name="toYear" size="10" theme="simple" maxlength="4"
								onkeypress="return numbersOnly();" readonly="true"></s:textfield>
							</td>
						</tr>
						</table>
					</td>
				</tr>
		
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" class="formbg">			
						
						<tr>
							<td colspan="4">
								<b><label class="set" id="selectEmployee" name="selectEmployee" ondblclick="callShowDiv(this);">
								<%=label.get("selectEmployee")%></label></b>
							</td>
						</tr>
						<tr>
							<td height="22" width="20%" class="formtext"><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							<font color="red">*</font> :</td>
							<td colspan="3"><s:hidden name="empId" /> <s:hidden
								name="division" /> <s:textfield name="empToken" size="10"
								theme="simple" maxlength="70" readonly="true" /> <s:textfield
								name="empName" size="30" theme="simple" maxlength="70"
								readonly="true"></s:textfield> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'Form1_f9Employee.action');">
							</td>
						</tr>


						<tr>
							<td height="22" class="formtext"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
							<td height="22"><s:textfield size="27" name="centerName"
								readonly="true" /></td>
						
							<td class="formtext"><label  class = "set"  id="designation" name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :</td>
							<td><s:textfield size="27" name="rankName" readonly="true" /></td>
						</tr>
					</table>
					</td>
					</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<!--<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><input type="button" class="token"
						onclick="return validation()" value="   Report   "> <s:submit
						cssClass="reset" action="Form1_reset" theme="simple"
						value="    Reset" /></td>
				</tr>
			</table>
			</td> -->
			<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"	id='topButtonTable'>
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
	<s:hidden name="reportType" />
	<s:hidden name="reportAction" value='Form1_report.action' />
</s:form>


<script>
function callReport(type){
	if(!validation()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		callReportCommon(type);
	}	
} 

function resetFun(){
	document.getElementById('paraFrm').action='Form1_reset.action';
	document.getElementById('paraFrm').submit();
}

function mailReportFun(type){
	if(!validation()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		document.getElementById('paraFrm').action='Form1_mailReport.action';
		document.getElementById('paraFrm').submit();
	}	
}

function getYear(){
	var current = new Date();
	var year =current.getFullYear();
	var yr =document.getElementById("paraFrm_fromYear").value;
	if(yr==''){
		document.getElementById("paraFrm_fromYear").value =year;
	}
}

getYear();

function validation(){
	var name=document.getElementById('paraFrm_empName').value;
	var fromYear=document.getElementById('paraFrm_fromYear').value;
	var toYear=document.getElementById('paraFrm_toYear').value;
	
	if(name==""){
	  alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());	  
	  return false;
	}
	if(fromYear==""){
	  alert("Please enter "+document.getElementById('finYearFrm').innerHTML.toLowerCase());	  
	  return false;
	}
	if(toYear==""){
	  alert("Please enter "+document.getElementById('finYearTo').innerHTML.toLowerCase());	  
	  return false;
	}
	return true;
	//callReport('Form1_report.action');
}
function add()
   {
      var from = document.getElementById('paraFrm_fromYear').value;
    
    if(from=="")
    {
    	 document.getElementById('paraFrm_toYear').value="";
    }
    else
    {
   	 var x=eval(from) +1;
	  document.getElementById('paraFrm_toYear').value=x;
	  }
   }
</script>

