<%@page import="org.paradyne.lib.Utility"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:form action="TaxChallan" validate="true" id="paraFrm" theme="simple">
<s:hidden name="savedChallanFlag" />
<s:hidden name="applicationType"/>
	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Tax Challan </strong></td>
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
      			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
	         		<tr>
	            		<td nowrap="nowrap">
	            			<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
	  					</td>
	            		<td width="80%">
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
			<td>
			<table width="100%" border="0" class="formbg" id='reportBodyTable'>
				<tr>
					<td>
					<table width="100%" class="formbg">
						<tr>
							<td colspan="4">
							<strong class="formhead"><label
								id=selectPeriod name="selectPeriod"
								ondblclick="callShowDiv(this);"><%=label.get("selectPeriod")%></label>
							</strong></td>
						</tr>
						<tr>
							<td width="20%"><label class="set" id="taxSelMon"
								name="taxSelMon" ondblclick="callShowDiv(this);"><%=label.get("taxSelMon")%></label>
							:</td>
							<s:hidden name="month" />
							<td width="25%"><s:hidden value="month" /><s:property value="monthName" /></td>
							<td width="25%" ><label class="set" id="taxYear" name="taxYear"
								ondblclick="callShowDiv(this);"><%=label.get("taxYear")%></label>
							:</td>
							<s:hidden name="year" />
							<td width="25%" colspan="2"><s:property value="year" />
						</tr>
					</table>
					</td>
					</tr>
					<tr>
					<td colspan="4">
						<table width="100%" border="0"  class="formbg">
						<tr>
							<td colspan="4">
							<strong class="formhead"><label
								id=selectReportFilter name="selectReportFilter"
								ondblclick="callShowDiv(this);"><%=label.get("selectReportFilter")%></label>
							</strong></td>
						</tr>
						<tr>
							<s:hidden name="challanID" />
							<td width="20%"><label class="set" id="division"
								name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:</td>
							<td width="25%"><s:hidden name="divName" /><s:property
								value="divName" /></td>
							<td colspan="1" width="20%"><label id="nhold" name="nhold"
								ondblclick="callShowDiv(this);"><%=label.get("nhold")%></label> :</td>
							<td colspan="2"><s:property value="onHold" /></td>
							<s:hidden name="divId" />
							<s:hidden name="onHold" />
						</tr>
						<tr>
							<td width="10%" nowrap="nowrap">Include</td>
							<td width="10%" nowrap="nowrap"><s:checkbox name="includeSalary" id="includeSalary"/>Salary</td>
							<td width="10%" nowrap="nowrap"><s:checkbox name="includeArrears" id="includeArrears" />Arrears</td>
							<td width="10%" nowrap="nowrap"><s:checkbox name="includeSettlement" id="includeSettlement"/>Settlement</td>
							<td width="10%" nowrap="nowrap"><s:checkbox name="includeBonus" id="includeBonus"/>Bonus</td>
							<td width="10%" nowrap="nowrap"><s:checkbox name="includeOverTime" id="includeOverTime"/>OT</td>
							<td width="10%" nowrap="nowrap"><s:checkbox name="includeLeaveEncashment" id="includeLeaveEncashment"/>Leave Encashment</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="100%">
					<table width="100%" class="formbg">
						<tr>
							<td width="25%"><label class="set" id="taxTds" name="taxTds"
								ondblclick="callShowDiv(this);"><%=label.get("taxTds")%></label> :</td>
							<td width="25%"><s:textfield name="tax" id="tax" size="25"
								readonly="true" /></td>
							<td width="25%"><label class="set" id="taxSurChrg"
								name="taxSurChrg" ondblclick="callShowDiv(this);"><%=label.get("taxSurChrg")%></label>:</td>
							<td><s:textfield name="surcharge" id="surcharge" size="25"
								readonly="true" /></td>
						</tr>
		
						<tr>
							<td width="25%"><label class="set" id="taxIntAmt"
								name="taxIntAmt" ondblclick="callShowDiv(this);"><%=label.get("taxIntAmt")%></label>
							:</td>
							<td width="25%"><s:textfield name="intAmt" size="25"
								onkeypress="return numbersWithDot();" onkeyup="sumAmt()" /></td>
							<td width="25%"><label class="set" id="taxOthrAmt"
								name="taxOthrAmt" ondblclick="callShowDiv(this);"><%=label.get("taxOthrAmt")%></label>
							:</td>
							<td><s:textfield name="othrAmt" size="25"
								onkeypress="return numbersWithDot();" onkeyup="sumTot()" /></td>
						</tr>
		
						<tr>
							<td width="25%"><label class="set" id="taxEduCess"
								name="taxEduCess" ondblclick="callShowDiv(this);"><%=label.get("taxEduCess")%></label>
							:</td>
							<td width="25%"><s:textfield name="eduCess" id="eduCess"
								size="25" readonly="true" /></td>
							<td width="25%"><label class="set" id="taxTotTax"
								name="taxTotTax" ondblclick="callShowDiv(this);"><%=label.get("taxTotTax")%></label>
							:</td>
							<td><s:textfield name="totalTax" id="totalTax" size="25"
								readonly="true" /></td>
						</tr>
						<tr>
							<td width="25%"><label class="set" id="bank" name="bank"
								ondblclick="callShowDiv(this);"><%=label.get("bank")%></label> :</td>
							<td width="25%"><s:textfield name="bank" size="25"
								readonly="true" /> <img id="ctrlHide"
								src="../pages/images/search2.gif" class="iconImage" height="18"
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'TaxChallan_f9bank.action');"></td>
							<td width="25%"><label class="set" id="taxBsrCd"
								name="taxBsrCd" ondblclick="callShowDiv(this);"><%=label.get("taxBsrCd")%></label>
							:</td>
							<td><s:textfield name="bsrCode" size="25" readonly="true" /></td>
							<s:hidden name="micr" theme="simple" />
						</tr>
						<tr>
							<td width="25%"><label class="set" id="taxAckNo"
								name="taxAckNo" ondblclick="callShowDiv(this);"><%=label.get("taxAckNo")%></label>:</td>
							<td width="25%"><s:textfield name="ackNo" size="25" /></td>
							<td width="25%"></td>
							<td></td>
						</tr>
						<tr>
							<td width="25%"><label class="set" id="taxTdsDepByBkEntry"
								name="taxTdsDepByBkEntry" ondblclick="callShowDiv(this);"><%=label.get("taxTdsDepByBkEntry")%></label><font
								color="red">*</font> :</td>
							<td width="25%"><s:select name="bookEntry"
								cssStyle="width:150;z-index:5;" onchange="return showtext();"
								disabled="false" list="#{'1':'--Select--','Y':'Yes','N':'No'}" /></td>
							<td width="25%"><label class="set" id="dateOfAmtPayment"
								name="dateOfAmtPayment" ondblclick="callShowDiv(this);"><%=label.get("dateOfAmtPayment")%></label>
							<font color="red">*</font> :</td>
							<td><s:textfield name="paymentDate" size="25"
								onblur="validateDate('paraFrm_paymentDate','Date of amount payment')" />
							<s:a href="javascript:NewCal('paraFrm_paymentDate','DDMMYYYY');">
								<img id="ctrlHide" src="../pages/images/Date.gif"
									class="iconImage" height="16" align="absmiddle" width="16">
							</s:a></td>
						</tr>
						<tr id="div2" style="display: none;">
							<td width="25%"><label class="set" id="taxVochChalanNo"
								name="taxVochChalanNo" ondblclick="callShowDiv(this);"><%=label.get("taxVochChalanNo")%></label>.<font
								color="red">*</font> :</td>
							<td width="25%"><s:textfield name="challanNo" size="25" /></td>
		
							<td width="25%"><label class="set" id="taxChalanTaxDepDate"
								name="taxChalanTaxDepDate" ondblclick="callShowDiv(this);"><%=label.get("taxChalanTaxDepDate")%></label> :</td>
							<td><s:textfield name="challanDate" size="25" /> <s:a
								href="javascript:NewCal('paraFrm_challanDate','DDMMYYYY');">
								<img id="ctrlHide" src="../pages/images/Date.gif"
									class="iconImage" height="16" align="absmiddle" width="16">
							</s:a></td>
						</tr>
						<tr id="div3" style="display: none;">
							<td width="25%"><label class="set" id="taxChkNo"
								name="taxChkNo" ondblclick="callShowDiv(this);"><%=label.get("taxChkNo")%></label>
							<font color="red">*</font>:</td>
							<td width="25%"><s:textfield name="chequeNo" size="25" /></td>
		
							<td width="25%"><label class="set" id="taxChkDate"
								name="taxChkDate" ondblclick="callShowDiv(this);"><%=label.get("taxChkDate")%></label>
							<font color="red">*</font> :</td>
							<td><s:textfield name="chequeDate" size="25"
								onblur="validateDate('paraFrm_chequeDate','Cheque Date')" /> <s:a
								href="javascript:NewCal('paraFrm_chequeDate','DDMMYYYY');">
								<img id="ctrlHide" src="../pages/images/Date.gif"
									class="iconImage" height="16" align="absmiddle" width="16">
							</s:a></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- ---------------------------------------------------------------------------------- -->
		</tr>
		<tr>
      		<td>
      			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
	         		<tr>
	            		<td nowrap="nowrap">
	            			<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
	  					</td>
	            		<td width="80%">
						<%@ include file="/pages/common/reportButtonPanelBottom.jsp"%>
					</td>
	          		</tr>
          		</table>
            </td>
    	</tr>
	</table>
<s:hidden name="surchargePercen" id="surchargePercen" />
<s:hidden name="eduCessPercen" id="eduCessPercen" />
<s:hidden name="rebateLimit" id="rebateLimit" />
<s:hidden name="reportType" />
<s:hidden name="reportAction" value='TaxChallan_report.action'/>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>
showtext();

function mailReportFun(type){
	document.getElementById('paraFrm_reportType').value=type;
	document.getElementById('paraFrm').action='TaxChallan_mailReport.action';
	document.getElementById('paraFrm').submit();
}

function callReport(type){
	document.getElementById('paraFrm_reportType').value=type;
	callReportCommon(type);
}
	
function showtext(){
		
	var entry=document.getElementById("paraFrm_bookEntry").value;
	
	if(entry=="Y"){
		document.getElementById('div2').style.display='none';
		document.getElementById('div3').style.display='';
	}
	else if(entry=="N"){
		document.getElementById('div3').style.display='none';
		document.getElementById('div2').style.display='';
	} else {
		document.getElementById('div3').style.display='none';
		document.getElementById('div2').style.display='none';
	}
}
function saveFun(){
	if(!validateFields()){
 			return false;
 		}else{
			document.getElementById("paraFrm").action="TaxChallan_saveOnNextPage.action";
	  		document.getElementById("paraFrm").target="main";
	   		document.getElementById("paraFrm").submit();
   		}
}
function validateFields(){
	try{
		var frmMon=document.getElementById("paraFrm_month").value;
		var dt=document.getElementById("paraFrm_challanDate").value;
		var fromYear=document.getElementById("paraFrm_year").value;
		var toMon=dt.substring(3,5);
		var toYear=dt.substring(6,10);
		var entry=document.getElementById("paraFrm_bookEntry").value;
		var chalDt=document.getElementById("paraFrm_challanDate").value;
		var chalN=document.getElementById("paraFrm_challanNo").value;
		var chqDt=document.getElementById("paraFrm_chequeDate").value;
		var payDate = document.getElementById("paraFrm_paymentDate").value;
		var chqN=trim(document.getElementById("paraFrm_chequeNo").value);
		
		 var selMon=document.getElementById('taxSelMon').innerHTML.toLowerCase();
		 var year=document.getElementById('taxYear').innerHTML.toLowerCase();
		 var totTax=document.getElementById('taxTotTax').innerHTML.toLowerCase();
		 var chkNo=document.getElementById('taxChkNo').innerHTML.toLowerCase();
		 var chkDate=document.getElementById('taxChkDate').innerHTML.toLowerCase();
		 var paymentDt=document.getElementById('dateOfAmtPayment').innerHTML.toLowerCase();
		 var bookEntry=document.getElementById('taxTdsDepByBkEntry').innerHTML.toLowerCase();
		 var vouChalanNo=document.getElementById('taxVochChalanNo').innerHTML.toLowerCase();
		 var chalanTaxDepDate=document.getElementById('taxChalanTaxDepDate').innerHTML.toLowerCase();
		 var year=document.getElementById('taxYear').innerHTML.toLowerCase();
		 
		 var chqMsg=checkDate(chqDt);
		 var challanMsg=checkDate(chalDt);
		 
		 if(document.getElementById("paraFrm_bookEntry").value=='1'){
			alert("Please select the "+bookEntry+".");	
			return false;	
		}
		
		if(payDate==""){
			alert("Please enter "+paymentDt+".");
			return false;
		}	
		
		if(!validateDate('paraFrm_paymentDate','dateOfAmtPayment'))
  		    	return false;
  		    	
  		if(entry=="Y"){
			if(chqN==""){
				alert("Plese enter the "+chkNo+".");
				return false;
			}
			if(chqDt==""){
				alert("Please enter "+chkDate+".");		
				return false;
			}
		   if(!validateDate('paraFrm_chequeDate','taxChkDate'))
  		    return false;
  		    
  		    /*if(!chqMsg){
		  		alert(chkDate+" is outside of Quarter.");
		  		return false;
  			}*/
  		}
  		
  		if(entry=='N'){
			if(chalN==""){
				alert("Please enter "+vouChalanNo+".");
				return false;
			}
			if(chalDt!=""){
				if(!validateDate('paraFrm_challanDate','taxChalanTaxDepDate'))
	    		return false;
			}  
			
		}    	
   	}catch(e){alert(e)}
   	return true;
}

function viewandeditemployeesFun(){
	try{
		document.getElementById("paraFrm").action="TaxChallan_viewAndEditEmp.action";
	  	document.getElementById("paraFrm").target="main";
	   	document.getElementById("paraFrm").submit();
   	}catch(e){alert(e)}
}
		
function editFun(){
	return true;
}		
		
function deleteFun(){
try{
	var con=confirm('Do you want to delete the challan?');
	var challanId = document.getElementById('paraFrm_challanID').value;
	 if(con){
		document.getElementById("paraFrm").action='TaxChallan_deleteChallan.action?challanId='+challanId;
	  	document.getElementById("paraFrm").target="main";
	   	document.getElementById("paraFrm").submit();	
     } 
     else{
       return false;
     }
     }catch(e){alert(e)}
}

function backFun(){
		document.getElementById("paraFrm").action='TaxChallan_input.action?';
	  	document.getElementById("paraFrm").target="main";
	   	document.getElementById("paraFrm").submit();	
}

function checkDate(dt){
try{
	var frmMon=document.getElementById("paraFrm_month").value;
	var fromYear=document.getElementById("paraFrm_year").value;
	var toMon=dt.substring(3,5);
	var toYear=dt.substring(6,10);
		if(frmMon=="4" || frmMon=="5" || frmMon=="6" ){
			if(toMon>6 || toMon<4 ){
				return false;
			}
			else if(fromYear!=toYear){
				return false;
			}
			else{
			 	return true;
			}
		}		
		if(frmMon=="7" || frmMon=="8" || frmMon=="9"){
			if(toMon>9 || toMon<7){
				return false;
			  }
			  else if(fromYear!=toYear){
				return false;
			 }
			 else {
			 	return true;
			 }
		}
			
		if(frmMon=="10" || frmMon=="11" || frmMon=="12"){
			if(toMon<10){
				return false;
			  }
			  else if(fromYear!=toYear){
				return false;
			 }
			 else {
			    return true;
			 }
		}	
			
		if(frmMon=="1" || frmMon=="2" || frmMon=="3"){
			if(toMon>3){
				return false;
			  }
			  else if(fromYear!=toYear){
				return false;
			 }
			 else {
			    return true;
			 }
		}	
		}catch(e){alert(e)}
	}
</script>

