<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="ESICChallan" validate="true" id="paraFrm" validate="true" theme="simple">
<s:hidden name="esicCode"/>
<s:hidden name="amt"/>
<s:hidden name="earningType"/>
	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong class="formhead">
						<img src="../pages/images/recruitment/review_shared.gif" width="25"	height="25" /></strong>
					</td>
					<td width="93%" class="txt">
						<strong class="text_head">ESIC Challan</strong>
					</td>
					<td width="3%" valign="top" class="txt">
						<div align="right">
							<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
      		<td>
      			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
	         		<tr>
	            		<td nowrap="nowrap">
	            			<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
	  					</td>
	            		<td width="70%">
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
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg" id='reportBodyTable'>
					<tr>
						<td colspan="4">
							<strong class="formhead">
							<label id=selectPeriod name="selectPeriod" ondblclick="callShowDiv(this);"><%=label.get("selectPeriod")%></label></strong>
						</td>
					</tr>
					<tr>
						<td width="20%">
							<label id="months" name="months" ondblclick="callShowDiv(this);"><%=label.get("months")%></label>:<font color="red">*</font>
						</td>
						<td width="30%">
							<s:select name="month" list="#{'':'--Select--','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" theme="simple" />
						</td>
						<td width="20%">
							<label id="years" name="years" ondblclick="callShowDiv(this);"><%=label.get("years")%></label>:<font color="red">*</font>
						</td>
						<td width="30%">
							<s:textfield name="year" maxlength="4" onkeypress="return numbersOnly();" size="25"/>
						</td>
					</tr>
					<tr>
						<td>
							<label id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font color="red">*</font>
						</td>
						<td colspan="1" nowrap="nowrap">
							<s:textfield name="division" size="25" maxlength="15" readonly="true" />
							<s:hidden name="divCode"/>
							<img src="../pages/images/search2.gif" height="16" align="middle"
							width="16" theme="simple" onclick="javascript:callsF9(500,325,'ESICChallan_f9actionDiv.action');" id="ctrlHide"></td>
							
						<td colspan="1" width="20%"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
							<td colspan="1" width="20%"><s:hidden name="brnId" /> <s:textfield
								name="brnName" theme="simple" readonly="true" maxlength="50"
								size="25" /> <img src="../pages/images/recruitment/search2.gif"  id="ctrlHide"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'ESICChallan_f9brn.action');">
							</td>	
							
					</tr>
					<tr>
						<td>
							<label class="set" id="includeEarning" name="includeEarning" ondblclick="callShowDiv(this);"><%=label.get("includeEarning")%></label>:<font color="red">*</font>
						</td>
						<td colspan="3" nowrap="nowrap">
							<s:checkbox name="salaryCheck" onclick="showText1();"/>&nbsp;
							<label class="set" id="salary1" name="salary" ondblclick="callShowDiv(this);"><%=label.get("salary")%></label>&nbsp;
							<s:checkbox name="arrearsCheck" onclick="showText1();"/>&nbsp;
							<label class="set" id="arrear1" name="arrear" ondblclick="callShowDiv(this);"><%=label.get("arrear")%></label>&nbsp;
							<s:checkbox name="settlementCheck" onclick="showText1();"/>&nbsp;
							<label class="set" id="settlement" name="settlement" ondblclick="callShowDiv(this);"><%=label.get("settlement")%></label>
						</td>
					</tr>
					<tr id="holdId" style="display:none;" >
						<td>
							<label id="hold" name="hold" ondblclick="callShowDiv(this);"><%=label.get("hold")%></label>:</td>
						<td colspan="3">
							<s:select name="onHold" list="#{'A':'All','Y':'Yes','N':'No'}" theme="simple" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg">
					<tr>
						<td width="20%">
							<label id="empCont"	name="empCont" ondblclick="callShowDiv(this);"><%=label.get("empCont")%></label>:
						</td>
						<td width="30%">
							<s:textfield name="employeeContribution" onkeypress="return numbersOnly();" size="25"/>
						</td>
						<td width="20%">
							<label id="employerCont" name="employerCont" ondblclick="callShowDiv(this);"><%=label.get("employerCont")%></label>:
						</td>
						<td width="30%">
							<s:textfield name="employerContribution" onkeypress="return numbersOnly();" size="25"/>
						</td>
					</tr>
					<tr>
						<td>
							<label id="challanAmt" name="challanAmt" ondblclick="callShowDiv(this);"><%=label.get("challanAmt")%></label>:
						</td>
						<td>
							<s:textfield name="challanAmount" onkeypress="return numbersOnly();" size="25"/>
						</td>
						<td>
							<label id="totWages" name="totWages" ondblclick="callShowDiv(this);"><%=label.get("totWages")%></label>:
						</td>
						<td>
							<s:textfield name="totalWages" onkeypress="return numbersOnly();" size="25"/>
						</td>
					</tr>
					<tr>
						<td>
							<label id="noOfEmp" name="noOfEmp" ondblclick="callShowDiv(this);"><%=label.get("noOfEmp")%></label>:
						</td>
						<td>
							<s:textfield name="noOfEmployees" onkeypress="return numbersOnly();" size="25"/>
						</td>
						<td>
							<label id="challanno" name="challanno" ondblclick="callShowDiv(this);"><%=label.get("challanno")%></label>:<font color="red">*</font>
						</td>
						<td colspan="3">
							<s:textfield name="challan" size="25" maxlength="12" onkeypress="return numbersOnly();"/>
						</td>
					</tr>
					<tr>
						<td>
							<label id="payMode"	name="payMode" ondblclick="callShowDiv(this);"><%=label.get("payMode")%></label>:<font color="red">*</font>
						</td>
						<td>
							<s:select name="paymentMode" onchange="showText();" list="#{'':'--Select--','1':'Cheque','2':'Cash','3':'Draft'}" />
						</td>
						<td>
							<label id="payDate"	name="payDate" ondblclick="callShowDiv(this);"><%=label.get("payDate")%></label>:<font color="red">*</font>
						</td>
						<td>
							<s:textfield name="paymentDate" size="25" maxlength="10" onkeypress="return numbersWithHiphen();" /> 
							<s:a href="javascript:NewCal('paraFrm_paymentDate','DDMMYYYY');">
							<img class="iconImage" src="../pages/images/recruitment/Date.gif" width="16" height="16" border="0" align="absmiddle" id="ctrlHide"/></s:a>
						</td>
					</tr>
					<tr id="chqId">
						<td>
							<label id="chequeno" name="chequeno" ondblclick="callShowDiv(this);"><%=label.get("chequeno")%></label>:<font color="red">*</font>
						</td>
						<td>
							<s:textfield name="chequeNo" maxlength="12" size="25" theme="simple" onkeypress="return numbersOnly();" />
						</td>
						<td>
							<label id="bank" name="bank" ondblclick="callShowDiv(this);"><%=label.get("bank")%></label>:<font color="red"></font>
						</td>
						<td><s:hidden name="bankId"/>
							<s:textfield name="bankName" maxlength="12" size="25" theme="simple" onkeypress="return numbersOnly();" />
							<img src="../pages/images/search2.gif" height="16" align="middle"
							width="16" theme="simple" onclick="javascript:callsF9(500,325,'ESICChallan_f9banks.action');" id="ctrlHide">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
      		<td>
      			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
	         		<tr>
	            		<td nowrap="nowrap">
	            			<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
	  					</td>
	            		<td width="70%">
						<%@ include file="/pages/common/reportButtonPanelBottom.jsp"%>
					</td>
	          		</tr>
          		</table>
            </td>
    	</tr>
	</table>
<s:hidden name="reportType" />
<s:hidden name="reportAction" value='ESICChallan_report.action'/>
</s:form>
<script>
showText();
showText1();
function saveFun(){
	var empCount = document.getElementById('paraFrm_noOfEmployees').value;
	if(!validateFields()){
 			return false;
	}else{
		if(empCount == 0){
			var con=confirm('Do you want to save the record with 0 employees ?');
	 		if(con){
				document.getElementById('paraFrm').target="main";
				document.getElementById('paraFrm').action='ESICChallan_save.action';
				document.getElementById('paraFrm').submit();
			}else{
				document.getElementById('paraFrm_noOfEmployees').focus(); 
			}
		}else{
			document.getElementById('paraFrm').target="main";
			document.getElementById('paraFrm').action='ESICChallan_save.action';
			document.getElementById('paraFrm').submit();
		}
	}
}

function processFun(){
	var month=document.getElementById('paraFrm_month').value;
	var year=document.getElementById('paraFrm_year').value;	
	var division=document.getElementById('paraFrm_division').value;
	var salCheck = document.getElementById('paraFrm_salaryCheck').checked;	
	var arrCheck = document.getElementById('paraFrm_arrearsCheck').checked;	
	var setCheck = document.getElementById('paraFrm_settlementCheck').checked;	
	
	if(month==""){
		alert("Please select "+document.getElementById('months').innerHTML.toLowerCase());	
		return false;
	}
	if(year==""){
		alert("Please enter "+document.getElementById('years').innerHTML.toLowerCase());	
		document.getElementById('paraFrm_year').focus();  
		return false;
	}	
	if(division==""){
		alert("Please enter "+document.getElementById('division').innerHTML.toLowerCase());	
		document.getElementById('paraFrm_division').focus(); 
		return false;
	}
	if(salCheck==false && arrCheck==false && setCheck==false){
			alert("Please select "+document.getElementById('includeEarning').innerHTML.toLowerCase());	  
			return false;
	}	
	document.getElementById('paraFrm').target="main";
	document.getElementById('paraFrm').action='ESICChallan_processESICChallan.action';
	document.getElementById('paraFrm').submit();
}
function resetFun(){
	document.getElementById('paraFrm').target="main";
	document.getElementById('paraFrm').action='ESICChallan_clear.action';
	document.getElementById('paraFrm').submit();
}

function backFun(){
	document.getElementById('paraFrm').target="main";
	document.getElementById('paraFrm').action='ESICChallan_back.action';
	document.getElementById('paraFrm').submit();
}

function editFun(){
	document.getElementById('paraFrm').target="main";
	document.getElementById('paraFrm').action='ESICChallan_edit.action';
	document.getElementById('paraFrm').submit();
}

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
		document.getElementById('paraFrm').action='ESICChallan_mailReport.action';
		document.getElementById('paraFrm').submit();
	}	
}

function reportFun(){
	if(!validateFields()){
			return false;
		} else {
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action="ESICChallan_report.action";
			document.getElementById('paraFrm').submit();
		}
}
	
function showText(){			
	var chq = document.getElementById('paraFrm_paymentMode').value;	
	if(chq == "1" ){
	document.getElementById('chqId').style.display='';
	}else if(chq=="2" || chq==""){
	document.getElementById('chqId').style.display='none';
	}
}
function showText1(){	
	var checkedType="";		
	var salCheck = document.getElementById('paraFrm_salaryCheck').checked;	
	var arrCheck = document.getElementById('paraFrm_arrearsCheck').checked;	
	var setCheck = document.getElementById('paraFrm_settlementCheck').checked;	
	
	if(salCheck == true){
		document.getElementById('holdId').style.display='';
		checkedType= "S,";
	}else{
		document.getElementById('holdId').style.display='none';
	}
	if(arrCheck == true){
		checkedType += "R,";
	}
	if(setCheck == true){
		checkedType += "E,";
	}
	checkedType=checkedType.substring(0, checkedType.length-1);
	document.getElementById('paraFrm_earningType').value = checkedType;
}
				
function validateFields(){
	try{
		var month=document.getElementById('paraFrm_month').value;
		var year=document.getElementById('paraFrm_year').value;	
		var division=document.getElementById('paraFrm_division').value;
		var mode=document.getElementById('paraFrm_paymentMode').value;
		var date=document.getElementById('paraFrm_paymentDate').value;
		var challan=document.getElementById('paraFrm_challan').value;
		var salCheck = document.getElementById('paraFrm_salaryCheck').checked;	
		var arrCheck = document.getElementById('paraFrm_arrearsCheck').checked;	
		var setCheck = document.getElementById('paraFrm_settlementCheck').checked;	
		var empCount = document.getElementById('paraFrm_noOfEmployees').value;
		
		if(month==""){
		  alert("Please select "+document.getElementById('months').innerHTML.toLowerCase());	
		  return false;
		}
		if(year==""){
		  alert("Please enter "+document.getElementById('years').innerHTML.toLowerCase());	
		  document.getElementById('paraFrm_year').focus();  
		  return false;
		}	
		if(division==""){
		  alert("Please enter "+document.getElementById('division').innerHTML.toLowerCase());	
		  document.getElementById('paraFrm_division').focus(); 
		  return false;
		}	
		if(salCheck==false && arrCheck==false && setCheck==false){
			alert("Please select "+document.getElementById('includeEarning').innerHTML.toLowerCase());	  
			return false;
		}
		if(empCount==""){
			alert("Please enter "+document.getElementById('noOfEmp').innerHTML.toLowerCase());	
			document.getElementById('paraFrm_noOfEmployees').focus(); 
			return false;
		}
		if(challan==""){
		  alert("Please enter "+document.getElementById('challanno').innerHTML.toLowerCase());	
		  document.getElementById('paraFrm_challan').focus(); 
		  return false;
		}
		if(mode==""){
		  alert("Please enter "+document.getElementById('payMode').innerHTML.toLowerCase());	  
		  return false;
		}
		if(mode==1){
			  if(document.getElementById('paraFrm_chequeNo').value==""){
			  alert("Please enter the "+document.getElementById('chequeno').innerHTML.toLowerCase());
			  document.getElementById('paraFrm_chequeNo').focus(); 
			  return false;		
			}		
		}
		if(date==""){
		  alert("Please enter "+document.getElementById('payDate').innerHTML.toLowerCase());	
		  document.getElementById('paraFrm_paymentDate').focus(); 
		  return false;
		}
	}catch(e){}
	return true;
}
</script>